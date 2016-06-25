package com.cheyooh.service.sdk.thread;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.sdk.cfg.Cfg;
import com.cheyooh.service.sdk.db.dao.SdkDDQMapper;
import com.cheyooh.service.sdk.db.dao.SdkLogAccessMapper;
import com.cheyooh.service.sdk.db.entity.SdkLogAccess;
import com.cheyooh.service.sdk.db.entity.SdkUserLoginGame;
import com.cheyooh.tools.log.Logger;
 

/**
 * @author zzg.zhou@mbook.com.cn {MSN:zzgzhou@hotmail.com, QQ:11039850}
 * 
 *  后台写入线程
 */
public class LogWriteThread extends Thread{
	public static Logger logger=new Logger(LogWriteThread.class);
	
	
	private static ArrayBlockingQueue<SdkLogAccess> vcs=new ArrayBlockingQueue<SdkLogAccess>(Cfg.cfg.getInt("logging.logwrite.queue",500000));
	 
	
	public static void addLog(SdkLogAccess log){
		vcs.add(log);		 
	}
	
	public static int getQueueLength(){
		return vcs.size();
	}
  
	
	private static LogWriteThread task;
	private static boolean running=true;
		
	public synchronized static void startBgWrite(){
		if(task==null){
			task=new LogWriteThread();
			task.setDaemon(true);
			task.start();
		}else{
			logger.error("BgWriteTask thread running.");
		}
	}		
	
	public synchronized static void stopBgWrite(){
		if(task!=null){
			running=false; 
			
			try{ Thread.sleep(300); }catch(Exception e){}
			
			if(task!=null){
				task.interrupt();
			}
		} 
	}
  
	
	private LogWriteThread(){
		
	}
	
	public void run(){
		running=true;
		try{
			logger.info("LogWriteTask running ...");
			while(running){								
				try{
					List<SdkLogAccess> list=new ArrayList<SdkLogAccess>();
					int batch_size=Cfg.cfg.getInt("logging.logwrite.size",20);
					for(int i=0;i<batch_size;i++){						
						SdkLogAccess vc=vcs.poll();						
						if(vc!=null){
							list.add(vc);
						}else{
							break;
						}
					}
					if(list.size()>0){
						save(list);
					}else{
						Thread.sleep(1000);
					}					
				}catch(Throwable e){
					logger.error(e);
					
					if(running){
						try{ Thread.sleep(5*1000); }catch(Exception ex){}
					}
				}
			}
		}finally{			
			task=null;
			logger.info("LogWriteTask stopped.");
		}
	}
	
	private void save(List<SdkLogAccess> logs){
		DAL dal=DALFactory.createDAL();
		try{
			SdkLogAccessMapper mapper=dal.getMapper(SdkLogAccessMapper.class);
			
			for(SdkLogAccess log:logs){
				insert(dal,mapper,log);
			}
			dal.commit();			 						 
		}catch(Exception e){
			logger.error("Write log exception: "+e,e);
			try{dal.rollback();}catch(Exception ex){}
			
			for(SdkLogAccess log:logs){
				LogError.logError(log);
			}			 			
		}finally{
			dal.close();
		}		 
	}
	
	
	private void insert(DAL dal,SdkLogAccessMapper mapper,SdkLogAccess log){
		String log_action_table_prefix=Cfg.cfg.getString("logging.action.table.prefix", "sdk_log_access_");
		String log_action_table_format=Cfg.cfg.getString("logging.action.table.format", "yyyyMM");
		 
		String tableName=log_action_table_prefix+new SimpleDateFormat(log_action_table_format).format(log.getLogTime());
		log.setTableName(tableName);
		
		createLogAccessTableIfNotExists(dal,mapper,log);
		
		mapper.insertLogAccess(log);			
		
		
		if("user_login".equalsIgnoreCase(log.getAction()) && log.getUid()!=null && log.getStatus()==0){
			SdkDDQMapper ddq=dal.getMapper(SdkDDQMapper.class);
			SdkUserLoginGame loginGame=new SdkUserLoginGame();
			loginGame.setFirstLoginChannel(log.getChannel());
			loginGame.setFirstLoginTime(log.getLogTime());
			loginGame.setFirstLoginVersion(log.getVersion());
			loginGame.setGameId(log.getGameId());
			loginGame.setLastLoginChannel(log.getChannel());
			loginGame.setLastLoginTime(log.getLogTime());
			loginGame.setLastLoginVersion(log.getVersion());
			loginGame.setUid(log.getUid());
			ddq.insertSdkUserLoginGameOnDuplicateUpdate(loginGame);
		}
	} 
	 
	
	private static Set<String> hLogAccessTables=new HashSet<String>(); 
	private static synchronized void createLogAccessTableIfNotExists(DAL dal,SdkLogAccessMapper mapper,SdkLogAccess log){
		String tableName=log.getTableName();
		
		if(!hLogAccessTables.contains(tableName)){
			dal.commit();
			
			int r=mapper.createLogAccessIfNotExists(log);
			
			hLogAccessTables.add(tableName);
			
			logger.info("Create log access table: "+tableName+", return "+r);
		}
	}  
}
