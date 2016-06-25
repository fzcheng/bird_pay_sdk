package com.cheyooh.service.dal.mybatis;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;

import com.cheyooh.service.dal.mybatis.parse.DBSXMLConfigBuilder;
import com.cheyooh.service.dal.mybatis.parse.SelfDBConfigEntity;
import com.cheyooh.tools.cfg.CfgPath;
import com.cheyooh.tools.cfg.EnvTools;
import com.cheyooh.tools.log.Logger;

/**
 * @author zzg.zhou@mbook.com.cn {MSN:zzgzhou@hotmail.com, QQ:11039850}
 * 
 */
public class ConfigurationManager extends Configuration {
	static Logger logger = new Logger(ConfigurationManager.class);
	
	//定义扫描配置文件的路径，支持set方法设置。
	public static String CFG_ROOT_PATH = System.getProperty("cfg.dal.path", CfgPath.getCfgPathRoot()+"/mybatis");
	
	//定义汇总的数据配置文件名称
	public static final String MYBATIS_DATABASE_CONFIG = "db.xml";
	
	static{
		if(new File(CFG_ROOT_PATH).exists()==false){
			CFG_ROOT_PATH = ConfigurationManager.class.getResource("/mybatis").getPath();
			
			if(new File(CFG_ROOT_PATH).exists()==false){
				logger.error("DBCfg path not found: "+new File(CFG_ROOT_PATH).getAbsolutePath());
			}else{
				logger.warn("DBCfg class path: "+new File(CFG_ROOT_PATH).getAbsolutePath());
			}
		}else{
			logger.info("DBCfg file path: "+new File(CFG_ROOT_PATH).getAbsolutePath());
		}
	}	 

	private static Long configLastModifyDate = null;
	
	private static ConfigurationManager cm = new ConfigurationManager();

	public static ConfigurationManager getInstance() {
		return cm;
	}
	
	private ReentrantReadWriteLock lock=new ReentrantReadWriteLock();

	
	private RuntimeConfig rc=new RuntimeConfig();
	
	private Map<String, XmlConfiguration> hXmlConfigs = new HashMap<String, XmlConfiguration>();

	private MemorySesssionFactory msf;
	
	private Timer backgroundTimer;

	private int cfgXmlCheckTime = 5;
	private int cfgDatasouceCloseDelay = 30;
	
	/**
	 * 配置文件最后一次载入的时间
	 */
	private long lastLoadTime=0L;
	
	private ConfigurationManager() {
		
	}
	
	private void checkAndReloadMemorySessionFactory(){
//		File fcfg = new File(CFG_ROOT_PATH+"/memory_db.xml");
//		if(msf==null){			
//			if (fcfg.exists()) {
//				logger.info("Load memory session factory: "+fcfg.getAbsolutePath());
//				
//				XmlConfiguration xc = new XmlConfiguration(fcfg.getAbsolutePath());
//				msf=new MemorySesssionFactory(xc);
//			}			 
//		}else {
//			long tm=((XmlConfiguration)msf.getConfiguration()).getLastUpdateTime();
//			if(tm!=fcfg.lastModified()){
//				logger.info("Reload memory session factory: "+fcfg.getAbsolutePath());
//				
//				MemorySesssionFactory old=msf;
//				
//				XmlConfiguration xc = new XmlConfiguration(fcfg.getAbsolutePath());
//				msf=new MemorySesssionFactory(xc);
//				
//				Set<SqlSessionFactory> closing=new HashSet<SqlSessionFactory>();
//				closing.add(old);
//				delayCloseFactories(closing, cfgDatasouceCloseDelay);				 
//			}
//		}
	}

	private void startBackgroundTimer() {
		if (backgroundTimer == null) {
			backgroundTimer = new Timer(true);
			backgroundTimer.scheduleAtFixedRate(new TimerTask() {
				@Override
				public void run() {
					if (reload()) {
						logger.info("Mybatis config reload ok.");
					}
				}

			}, cfgXmlCheckTime * 1000, cfgXmlCheckTime * 1000);
		}
	}

	private void stopBackgroundThread() {
		if (backgroundTimer != null) {
			backgroundTimer.cancel();
			backgroundTimer = null;
		}
	}

	private void delayCloseFactories(final Set<SelfSessionFactory> closingFactories,int delay) {	
		if(closingFactories!=null && closingFactories.size()>0){
			if(delay>0){
				startBackgroundTimer();
				
				// 延时关闭数据源
				backgroundTimer.schedule(new TimerTask() {
					public void run() {
						freePooledDataSource(closingFactories);
					}
				}, delay * 1000);
			}else{
				//立即关闭数据源
				freePooledDataSource(closingFactories);
			}
		}
	}
	
	private void freePooledDataSource(Set<SelfSessionFactory> factories){
		for (SelfSessionFactory factory : factories) {
			DataSource ds = factory.getConfiguration().getEnvironment().getDataSource();
			
			if (ds instanceof PooledDataSource) {
				PooledDataSource pool = (PooledDataSource) ds;
				pool.forceCloseAll();

				logger.info("Free pool datasource: " + factory);
			}
		}
	}
  
	private boolean init=false;
	public synchronized void checkInit(){
		if(init==false){
			init=true;		
			reload();
			startBackgroundTimer();
		}
	}
	
	public MappedStatement getMappedStatement(String id) {
		if(init==false)checkInit();
		
		lock.readLock().lock();
		try{
			MappedStatement s= rc.hStatements.get(id);
			if(s==null){
				s=rc.checkMissingMappedStatement(id);				
				if(s!=null){
					logger.info("Found new statement: "+id);
					 
					rc.hStatements.put(id,s);					 
				}else{
					logger.error("Statement not found: "+id);
				}
			}
			return s;
		}finally{
			lock.readLock().unlock();
		}
	}
	
	public WRSqlSession openWriteSession(String id) {
		if(init==false)checkInit();
		
		return openSession(rc.hWrites, id);
	}

	public WRSqlSession openReadSession(String id) {
		if(init==false)checkInit();
		
		if(msf!=null && msf.getConfiguration().getMappedStatement(id)!=null){
			return new WRSqlSession(msf.openSession(),false);			 
		}else{		
			return openSession(rc.hReads, id);
		}
	}

	private WRSqlSession openSession(Map<String, SSF> hss, String id) {
		lock.readLock().lock();
		try{
			SSF ssf = hss.get(id);
			if (ssf != null) {
				SelfSessionFactory factory = ssf.next();
				if (factory != null) {
					return new WRSqlSession(factory.openSession(),factory.isWriteable());
				} else {
					logger.error("No factory found with statement id: " + id);
				}
			} else {
				logger.error("Invalid statement id: " + id);
	
			}
			return null;
		}finally{
			lock.readLock().unlock();
		}
	}
	
	/**
	 * 根据路径判定加载db.xml文件,并且解析该文件，为具体的对象
	 * @return
	 */
	private Map<String, XmlConfiguration> loadConfigAndParse() 
	{
		Map<String, XmlConfiguration> xcs = new LinkedHashMap<String, XmlConfiguration>();
		
		try {
			File fcfg = new File(CFG_ROOT_PATH + File.separator + MYBATIS_DATABASE_CONFIG);
			if (fcfg.exists() && fcfg.isFile()) {
				//解析文件
				DBSXMLConfigBuilder dbp = new DBSXMLConfigBuilder(readMybatisXml(fcfg));
				List<SelfDBConfigEntity> list = dbp.parse();
				for(SelfDBConfigEntity e : list)
				{
					XmlConfiguration xmlConfig = new XmlConfiguration(e.getId());
					xmlConfig.setSelfDBConfigEntity(e);
					xcs.put(e.getId(), xmlConfig);
				}
				logger.info("found mybatis configurate count: " + list.size() + " from " + MYBATIS_DATABASE_CONFIG);
			}
		} catch (Exception e) {
			logger.error("read mybatis configurate file db.xml error", e);
		}
		return xcs;
	}

	//通过调用XmlConfiguration的isChangeConfig方法来判定是否改变了
	private Set<String> getChangeNames(Map<String, XmlConfiguration> hOldXmlConfigs,Map<String, XmlConfiguration> hNewXmlConfigs) {
		Set<String> names=new HashSet<String>();

		for (String key : hNewXmlConfigs.keySet()) {
			XmlConfiguration nxc = hNewXmlConfigs.get(key);
			XmlConfiguration olc = hOldXmlConfigs.get(key);

			if (olc == null || olc.isChangeConfig(nxc)) {
				logger.info((olc == null ? "Add: " : "Update: ") + key);

				names.add(key);
			}
		}

		for (String key : hOldXmlConfigs.keySet()) {
			XmlConfiguration nxc = hNewXmlConfigs.get(key);
			XmlConfiguration olc = hOldXmlConfigs.get(key);
			if (nxc == null || olc.isChangeConfig(nxc)) {
				if (nxc == null) {
					logger.info("Remove: " + key);
				}

				names.add(key);
			}
		}

		return names;
	}

	public synchronized boolean reload() {
		File fcfg = new File(CFG_ROOT_PATH + File.separator + MYBATIS_DATABASE_CONFIG);
		if(!fcfg.exists()){
			logger.error(CFG_ROOT_PATH + File.separator + MYBATIS_DATABASE_CONFIG + " Not Exist!");
			return false;
		}
				 
		try{
			if(checkIfCfgChanged()){
				return true;
			}else{ 
				//检查url风格的xmlmap文件的变化
				for(SelfSessionFactory ssf:rc.getAllFactories()){
					XmlConfiguration xcf=(XmlConfiguration)ssf.getConfiguration();
					xcf.checkAndReloadMappers();
				}
				checkAndReloadMemorySessionFactory();
			
				return false;
			}
		}catch(Exception e){
			logger.error("Reload exception: "+e,e);
			
			return false;
		}finally{
			lastLoadTime=System.currentTimeMillis();
		}
	}
	
	/**
	 * 检查db.xml文件, 如果变更, 则重新载入
	 * 
	 * @return
	 */
	private boolean checkIfCfgChanged(){
		File fcfg = new File(CFG_ROOT_PATH + File.separator + MYBATIS_DATABASE_CONFIG);
		
		Long nowDate = fcfg.lastModified();
		boolean changed=true;
		Set<String> changedNames=new HashSet<String>();
		if(configLastModifyDate != null && nowDate.equals(configLastModifyDate)){
			changed=false;
		}else{
			configLastModifyDate = nowDate;
		}		
		
		if(changed){
			Map<String, XmlConfiguration> newXmlConfigs = loadConfigAndParse();
			changedNames=getChangeNames(hXmlConfigs,newXmlConfigs);
			if (changedNames.size()>0) {
				for(XmlConfiguration xc:newXmlConfigs.values()){
					xc.loadConfig();
				}
				
				hXmlConfigs.clear();
				hXmlConfigs = newXmlConfigs;
				
				RuntimeConfig current=loadCurrentConfigs();
				
				lock.writeLock().lock();
				try{
					Set<SelfSessionFactory> expiredFactories=rc.getAllFactories();
					
					rc.clear();
					rc=current;
					
					delayCloseFactories(expiredFactories,cfgDatasouceCloseDelay);
				}finally{
					lock.writeLock().unlock();
				}
	
				checkAndReloadMemorySessionFactory();								 
			}else{
				changed=false;
			}
		}
		
		return changed;
	}
	 
	private RuntimeConfig loadCurrentConfigs(){
		RuntimeConfig current=new RuntimeConfig();
		
		for (XmlConfiguration cfg : hXmlConfigs.values()) {
			if(cfg.isReady()){
				String mode = cfg.getVariables().getProperty("mode", "rw").toLowerCase();
				SelfSessionFactory factory = new SelfSessionFactory(cfg);
				
				boolean w=false,r=false;				
	
				Set<String> ss = new HashSet<String>();
				for (MappedStatement m : cfg.getMappedStatements()) {
					if (!ss.contains(m.getId())) {
						ss.add(m.getId());
						
						current.hStatements.put(m.getId(), m);
						
						if (mode.indexOf("w") >= 0) {
							w=true;
							
							addStatement(current.hWrites, factory, m);
						}
						if (mode.indexOf("r") >= 0) {
							r=true;
							
							addStatement(current.hReads, factory, m);
						}
					}
				}
				
				if(w){
					factory.setWriteable(true);
					current.vWriteFactories.add(factory);
				}else{
					factory.setWriteable(false);
				}
				
				if(r){
					current.vReadFactories.add(factory);
				}	
			}
		}
		
		//logger.info("Init factory connections ...");
		//current.initFactoryConnections();
		logger.info("Loaded mybatis configs: " + hXmlConfigs.size());
		
		
		return current;
	}
	
	

	private static void addStatement(Map<String, SSF> wr, SelfSessionFactory factory, MappedStatement m) {
		String key = m.getId();
		SSF ssf = wr.get(key);
		if (ssf == null) {
			ssf = new SSF();
			wr.put(key, ssf);
		}

		ssf.add(factory);
	}

	

	public void destory() {
		stopBackgroundThread();
		
		delayCloseFactories(rc.getAllFactories(),0);		
	}

	public int getCfgXmlCheckTime() {
		return cfgXmlCheckTime;
	}

	public void setCfgXmlCheckTime(int cfgXmlCheckTime) {
		this.cfgXmlCheckTime = cfgXmlCheckTime;
	}
	
	public long getLastLoadTime() {
		return lastLoadTime;
	}

	public void setLastLoadTime(long lastCheckedTime) {
		this.lastLoadTime = lastCheckedTime;
	}

	public int getCfgDatasouceCloseDelay() {
		return cfgDatasouceCloseDelay;
	}

	public void setCfgDatasouceCloseDelay(int cfgDatasouceCloseDelay) {
		this.cfgDatasouceCloseDelay = cfgDatasouceCloseDelay;
	}

	//公共方法，读取db.xml配置文件
	private InputStream readMybatisXml(File xml)throws Exception{
		try{
			String s=EnvTools.loadEnvContent(xml.getAbsolutePath(),"utf-8");
			
			return new ByteArrayInputStream(s.getBytes("utf-8"));
		}catch(Exception e){
			e.printStackTrace();
			
			throw e;
		}
	}
	
	
	static class RuntimeConfig{
		Map<String, SSF> hWrites= new HashMap<String, SSF>();
		Map<String, SSF> hReads = new HashMap<String, SSF>();

		List<SelfSessionFactory> vWriteFactories = new ArrayList<SelfSessionFactory>();
		List<SelfSessionFactory> vReadFactories  = new ArrayList<SelfSessionFactory>();

		Map<String, MappedStatement> hStatements = new HashMap<String, MappedStatement>();		
		
		public Set<SelfSessionFactory> getAllFactories(){
			Set<SelfSessionFactory> all=new HashSet<SelfSessionFactory>();
			for(SelfSessionFactory ssf:vWriteFactories){
				if(!all.contains(ssf)){
					all.add(ssf);
				}
			}		
			
			for(SelfSessionFactory ssf:vReadFactories){
				if(!all.contains(ssf)){
					all.add(ssf);
				}
			}
			
			return all;
		}
		
		protected MappedStatement checkMissingMappedStatement(String id) {
			MappedStatement ms=null;
			
			SelfSessionFactory ssfr=findMappedStatementFactory(vReadFactories,id);
			if(ssfr!=null){
				ms=ssfr.getConfiguration().getMappedStatement(id);
				addStatement(hReads, ssfr, ms);
			}
			
			SelfSessionFactory ssfw=findMappedStatementFactory(vWriteFactories,id);
			if(ssfw!=null){
				ms=ssfw.getConfiguration().getMappedStatement(id);
				addStatement(hWrites, ssfr, ms);
			}
			
			
			return ms;
		}
		
		private SelfSessionFactory findMappedStatementFactory(List<SelfSessionFactory> ssfs,String id){			 
			for(SelfSessionFactory ssf:ssfs){
				MappedStatement ms=ssf.getConfiguration().getMappedStatement(id);
				if(ms!=null){						 
					return ssf;
				}
			}
			return null;
		}
		
		public void initFactoryConnections(){
			for(SelfSessionFactory ssf:getAllFactories()){
				initFactoryConnection(ssf);
			}
		}
		
		private void initFactoryConnection(SelfSessionFactory factory){
			factory.openSession().close();			
		}
		
		public void clear(){
			hWrites.clear();
			hReads.clear();
			
			vWriteFactories.clear();
			vReadFactories.clear();
			hStatements.clear();
		}		 
	}
	
	public SelfSessionFactory getFactoryByName(String name){
		if(init==false)checkInit();
		
		lock.readLock().lock();
		try{
			SelfSessionFactory ssf=findFactoryByName(this.rc.vWriteFactories,name);
			if(ssf==null){
				ssf=findFactoryByName(this.rc.vReadFactories,name);
			}
			return ssf;
		}finally{
			lock.readLock().unlock();
		}
	}
	
	
	private static SelfSessionFactory findFactoryByName(List<SelfSessionFactory> ssfs,String name){
		for(SelfSessionFactory ssf:ssfs){				 				
			if(name.equals(getFactoryName(ssf))){
				return ssf;
			}
		}
		return null;
	}
	
	private static String getFactoryName(SelfSessionFactory ssf){
		String x=ssf.toString();
		int p=x.indexOf("[");
		if(p>0){
			x=x.substring(0,p);
		}
		
		return x;
	}
	
	static class SSF {
		private AtomicInteger position = new AtomicInteger();
		private List<SelfSessionFactory> ssfs = new ArrayList<SelfSessionFactory>();

		public SSF() {
		}

		public void add(SelfSessionFactory ssf) {
			ssfs.add(ssf);
		}

		public SelfSessionFactory remove(String name){
			SelfSessionFactory sf=findFactoryByName(ssfs,name);
			if(sf!=null){
				ssfs.remove(sf);
			}
			return sf;			 
		}
		
		
		public SelfSessionFactory update(SelfSessionFactory ssf){
			String name=getFactoryName(ssf);
			SelfSessionFactory ssf_old=remove(name);
			
			add(ssf);
			
			return ssf_old;
		}
		
		
		public SelfSessionFactory next() {
			int ix = position.getAndIncrement();

			if (ssfs.size() > 0) {
				ix = ix % ssfs.size();
				return ssfs.get(ix);
			} else {
				return null;
			}
		}							
	}

	
}
