package com.cheyooh.service.dal.mybatis;

import java.util.HashMap;
import java.util.Map;

public class MMM {
	private static MMM mmm=new MMM();
	
	public static MMM getInstance(){
		return mmm;
	}
	
	
	private Map<String,MapperMethodInterceptor> hInterceptors=new HashMap<String,MapperMethodInterceptor>();
	private MMM(){		 
	}
	
	public void registerMapperMethodInterceptor(String statement,MapperMethodInterceptor mmi){
		hInterceptors.put(statement, mmi);
	}
	
	public void unregisterMapperMethodInterceptor(String statement){
		hInterceptors.remove(statement);
	}
	
	public MapperMethodInterceptor getMapperMethodInterceptor(String statement){		
		MapperMethodInterceptor m=hInterceptors.get(statement);
		 
		return m;
	}
		
}
