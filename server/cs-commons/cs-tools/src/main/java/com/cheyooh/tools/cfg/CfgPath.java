package com.cheyooh.tools.cfg;

public class CfgPath {
	private static String cfgPath=_getCfgPath("cfg.path","appcfg");
	private static String logPath=_getLogPath("log4j.dir");
	
	public static String getCfgPathRoot(){
		return cfgPath;
	}
	
	public static String getLogPathRoot(){
		return logPath;
	}
	
	public static String getCfgPath(String name){
		if(name.indexOf(":")>0 || name.startsWith("/")){
			return name;
		}else{
			String home=getCfgPathRoot();
			return home+"/"+name;
		}
	}
	
	private static String _getCfgPath(String env,String p){
		String path=System.getProperty(env);		 
		if(path==null){
			path=getJBossPathRoot();
			
			if(path==null){
				path=getTomcatPathRoot();
			}	
			
			if(path!=null){
				if(path.endsWith("/")){
					path=path+p;
				}else{
					path=path+"/"+p;
				}
			}
		}
		if(path==null){
			path=p;
		}			
		return path;	 
							 		 
	}
	
	private static String _getLogPath(String env){
		String p="logs";
		
		String path=System.getProperty(env);
		if(path==null){
			path=getJBossPathRoot();
			
			if(path==null){
				path=getTomcatPathRoot();
			}else{
				p="log"; //JBOSS 的log目录
			}
			
			if(path!=null){
				if(path.endsWith("/")){
					path=path+p;
				}else{
					path=path+"/"+p;
				}
			}
		}
		if(path==null){
			path=p;
		}			
		return path;	 
							 		 
	}
	
	private static String getJBossPathRoot(){ 
		String path=System.getProperty("jboss.server.base.dir"); //JBOSS-AS-7
		if(path==null){
			path=System.getProperty("jboss.server.home.dir");
		}
		 
		return path;
	}
	
	private static String getTomcatPathRoot(){
		String path=System.getProperty("catalina.home");
		 
		return path;
	}
}
