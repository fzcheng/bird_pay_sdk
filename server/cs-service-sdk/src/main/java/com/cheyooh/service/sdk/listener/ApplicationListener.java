package com.cheyooh.service.sdk.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.basic.PackageServiceFinder;
import com.cheyooh.service.framework.basic.ServiceManager;
import com.cheyooh.service.framework.idata.ResultBusy;
import com.cheyooh.service.framework.utils.binding.RequestDataBinding;
import com.cheyooh.service.sdk.notify.OrderNotify;
import com.cheyooh.service.sdk.notify.UnionpayQueryOrder;
import com.cheyooh.service.sdk.thread.LogWriteThread;
import com.cheyooh.tools.cfg.CfgUtil;
import com.cheyooh.tools.log.Logger;

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
		
		LogWriteThread.startBgWrite();
		
		if(isRunningNotify()){
			OrderNotify.getInstance().start();
			// 主动查单
			UnionpayQueryOrder.getInstance().start();
		}
	}
	
	private boolean isRunningNotify(){
		String running_notify="true";
		logger.info("Application running_notify boolean ="+running_notify);
		if("true".equalsIgnoreCase(running_notify)){
			return true;
		}else{
			return false;
		}
	}
	 
	private void setupPaths(){
		//这里设置文件上传的临时存储目录, 缺省为: ./upload/tmp 和 ./upload/bufdir
		//RequestDataBinding.uploadRepositoryPath=cfg.getString("upload.bufdir");
		//RequestDataBinding.uploadFilePath      =cfg.getString("upload.tmpdir");
		
		//这里设置Mybatis配置文件目录,缺省为: appcfg/mybatis
		//DALFactory.setXmlPath(cfg.getString("mybatis.configuration.dir"));
		
		//字段校验错误消息配置文件
		com.cheyooh.service.framework.cfg.Cfg.msg=com.cheyooh.service.sdk.cfg.Cfg.msg;
	}
 
	
	private void setupServiceFinder() {
		//注册服务方法所在的包名
		ServiceManager sm = ServiceManager.getInstance();
		
		sm.registServiceFinder(new PackageServiceFinder("com.cheyooh.service.sdk.action.client"));
		sm.registServiceFinder(new PackageServiceFinder("com.cheyooh.service.sdk.action.gameserver"));
		sm.registServiceFinder(new PackageServiceFinder("com.cheyooh.service.sdk.action.notify"));
		sm.registServiceFinder(new PackageServiceFinder("com.cheyooh.service.sdk.action.external"));
		sm.registServiceFinder(new PackageServiceFinder("com.cheyooh.service.sdk.action"));
	}

	private void setupHttpClient() {
		 
	}

	private void setupJSONParseClass() {		 
		RequestDataBinding.registJSONParseClass("ResultBusy", ResultBusy.class);		 
	}
 
	public void contextDestroyed(ServletContextEvent ce) {		
		if(isRunningNotify()){
			OrderNotify.getInstance().shutdown();
		}
		LogWriteThread.stopBgWrite();
		
		//关闭配置文件修改监视线程
		CfgUtil.interruptMonitor();				
		
		//关闭数据源
		DALFactory.destory();
		
		
		logger.info("Application destroyed.");
	}

}
