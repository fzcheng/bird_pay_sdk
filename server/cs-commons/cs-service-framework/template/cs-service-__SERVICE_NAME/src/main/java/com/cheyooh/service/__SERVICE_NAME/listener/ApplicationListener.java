package com.cheyooh.service.__SERVICE_NAME.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.basic.PackageServiceFinder;
import com.cheyooh.service.framework.basic.ServiceManager;
import com.cheyooh.service.framework.idata.ResultBusy;
import com.cheyooh.service.framework.utils.binding.RequestDataBinding;

import com.cheyooh.tools.log.Logger;
import com.cheyooh.tools.cfg.CfgUtil;

/**
 * @author zhouzg@cheyooh.com {MSN:zzgzhou@hotmail.com, QQ:11039850}
 * 
 */
public class ApplicationListener implements ServletContextListener {
	static Logger logger=new Logger(ApplicationListener.class);
	
	public void contextInitialized(ServletContextEvent ce) {
		logger.info("Application initialized.");
		
		setupPaths();
	 	
		setupServiceFinder();

		setupHttpClient();

		setupJSONParseClass();		  
	}
	
	 
	private void setupPaths(){
		//这里设置文件上传的临时存储目录, 缺省为: ./upload/tmp 和 ./upload/bufdir
		//RequestDataBinding.uploadRepositoryPath=cfg.getString("upload.bufdir");
		//RequestDataBinding.uploadFilePath      =cfg.getString("upload.tmpdir");
		
		//这里设置Mybatis配置文件目录,缺省为: appcfg/mybatis
		//DALFactory.setXmlPath(cfg.getString("mybatis.configuration.dir"));
		
		//字段校验错误消息配置文件
		com.cheyooh.service.framework.cfg.Cfg.msg=com.cheyooh.service.__SERVICE_NAME.cfg.Cfg.msg;
	}
 
	
	private void setupServiceFinder() {
		//注册服务方法所在的包名
		ServiceManager sm = ServiceManager.getInstance();
		sm.registServiceFinder(new PackageServiceFinder("com.cheyooh.service.__SERVICE_NAME.action"));		 
	}

	private void setupHttpClient() {
		 
	}

	private void setupJSONParseClass() {		 
		RequestDataBinding.registJSONParseClass("ResultBusy", ResultBusy.class);		 
	}
 
	public void contextDestroyed(ServletContextEvent ce) {
		//关闭配置文件修改监视线程
		CfgUtil.interruptMonitor();
		
		//关闭数据源
		DALFactory.destory();
		
		logger.info("Application destroyed.");
	}

}
