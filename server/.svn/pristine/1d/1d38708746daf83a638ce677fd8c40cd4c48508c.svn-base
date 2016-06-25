package com.cheyooh.rpc.dal;

import java.util.LinkedHashMap;
import java.util.Map;

import com.cheyooh.tools.log.Logger;

#{imports}

public class RpcMapping {
	static Logger logger=new Logger(RpcMapping.class);
	
	public static Map<String,Class<?>> mappers=new LinkedHashMap<String,Class<?>>();
	
	static{
		#{mappings}
	}
	
	public static void loadMappers(){
		if(mappers.size()>0){			 
			logger.info("Loaded mappers: "+mappers.size());
		}else{
			throw new RuntimeException("No ***MapperImpl found in RpcMapping.java!");
		}
	}
}