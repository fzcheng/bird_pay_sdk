package com.cheyooh.service.dal.diagnosis;

import java.util.concurrent.ArrayBlockingQueue;

import com.cheyooh.tools.cfg.EnvUtil;
import com.cheyooh.tools.log.Logger;

public class Diagnosis implements Runnable{
	static Logger logger=new Logger(Diagnosis.class);
	
	private final static int MAX_CAPACITY=50000;
	private static ArrayBlockingQueue<SqlLog> vcs=new ArrayBlockingQueue<SqlLog>(MAX_CAPACITY);	
	private static SQLWriter writer=null;	
	private static boolean writerLoaded=false;
	
	
	private static Diagnosis instance=new Diagnosis();
	public static Diagnosis getInstance(){
		return instance;
	}
	
	public synchronized static void start(){
		if(instance.running==false){
			instance.running=true;
			instance.thread.start();
		}
	}
	
	public synchronized static void stop(){
		if(instance.running){
			instance.running=false;
			
			for(int i=0;i<10;i++){
				if(instance.thread.isAlive()==false){
					break;
				}else{
					try{ Thread.sleep(30); }catch(Exception e){}
				}
			}
		}
	}
	
	public static void addLog(SqlLog log){
		if(vcs.size()>=MAX_CAPACITY-1){
			logger.error("SQL log-max-capacity: "+MAX_CAPACITY+", Ingore record: "+log.toString());
		}else{
			vcs.add(log);
		}
	}
	
	public static String getWriterClass(){
		String clazzName=EnvUtil.env.getString("dal.diagnosis.sqlwriter.class","com.cheyooh.service.dal.diagnosis.LogFileSQLWriter");
		return clazzName;
	}
		
	
	private static void write(SqlLog log){
		SQLWriter w=getSQLWriter();
		if(w!=null){
			w.write(log);
		}else{
			logger.warn(log.toString());
		}
	}
	
	private synchronized static SQLWriter getSQLWriter(){
		if(writerLoaded==false){
			writerLoaded=true;
			
			String clazzName=getWriterClass();
			try{
				Class<?> clazz=Class.forName(clazzName);
				Object o=clazz.newInstance();
				if(o instanceof SQLWriter){
					logger.info("Found SqlDiagnosis: "+clazzName);
					writer=(SQLWriter)o;
				}	
			}catch(ClassNotFoundException e){
				logger.error("Diagnosis class not found: "+clazzName+", use log debug long sql.");
			}catch(Exception e){
				logger.error("Exception load SqlDiagnosis: "+clazzName+", "+e,e);
			}
		}
		return writer;
	}
	
	
	
	
	private Thread thread;
	private boolean running=false;
	
	private Diagnosis(){
		thread=new Thread(this);
		thread.setDaemon(true);
		thread.setName("DiagnosisThread");
	}	
	
	public void run(){
		running=true;
		try{
			while(running){
				SqlLog log=vcs.poll();						
				if(log!=null){
					try{
						write(log);
					}catch(Exception e){
						logger.error("Write sql-log: "+log.toString()+" exception: "+e,e);
					}
				}else{
					try{ Thread.sleep(200); }catch(Exception e){}
				}
			}
		}finally{
			running=false;
			logger.info("DiagnosisThread exit!");
		}
	}				 
	
}
