package com.cheyooh.tools.log;

import java.io.File;

import com.cheyooh.tools.cfg.CfgPath;

public class Logger implements org.apache.commons.logging.Log { 	
	private org.apache.log4j.Logger Log;
	
	static{		 
		String log_dir=CfgPath.getLogPathRoot();
		System.setProperty("log4j.dir",log_dir);
		
		File   flog=new File(log_dir);
		if(flog.exists()==false){
			flog.mkdir();	
			
			Logger _logger=new Logger(Logger.class);
			_logger.info("Create log dir: "+flog.getAbsolutePath());
		}				 
	}
	
	@SuppressWarnings("rawtypes")
	public Logger(Class clazz){		 
		this.Log=org.apache.log4j.Logger.getLogger(clazz);
	}
	 
	public boolean isDebugEnabled() {
		return Log.isDebugEnabled();		
	}

	public boolean isErrorEnabled() {
		return Log.isDebugEnabled();
	}

	public boolean isFatalEnabled() {
		return true;
	}

	public boolean isInfoEnabled() {
		return Log.isInfoEnabled();
	}

	public boolean isTraceEnabled() {
		return Log.isDebugEnabled();
	}

	public boolean isWarnEnabled() {
		return Log.isDebugEnabled();
	}
 
	public void trace(Object message) {
		Log.debug(message);
	}

	public void trace(Object message, Throwable t) {
		Log.debug(message,t);
	}

	public void debug(Object message) {
		Log.debug(message);
	}

	public void debug(Object message, Throwable t) {
		Log.debug(message,t);
	}

	public void info(Object message) {
		Log.info(message);
	}

	public void info(Object message, Throwable t) {
		Log.info(message,t);
	}

	public void warn(Object message) {
		Log.warn(message);
	}

	public void warn(Object message, Throwable t) {
		Log.warn(message,t);
	}

	public void error(Object message) {
		if(message instanceof Throwable){
			Throwable t=(Throwable)message;
			Log.error(t.getMessage(),t);
		}else{
			Log.error(message);
		}
	}

	public void error(Object message, Throwable t) {
		Log.error(message,t);
	}

	public void fatal(Object message) {
		if(message instanceof Throwable){
			Throwable t=(Throwable)message;
			Log.fatal(t.getMessage(),t);
		}else{
			Log.fatal(message);
		}
	}

	public void fatal(Object message, Throwable t) {
		Log.fatal(message,t);		
	}
	 
}
