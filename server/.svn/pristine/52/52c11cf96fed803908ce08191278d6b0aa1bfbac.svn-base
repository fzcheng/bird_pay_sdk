package com.cheyooh.service.sdk.thread;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.cheyooh.service.sdk.db.entity.SdkLogAccess;
import com.cheyooh.tools.log.Logger;

/**
 * @author zzg.zhou@mbook.com.cn {MSN:zzgzhou@hotmail.com, QQ:11039850}
 *  
 */

@SuppressWarnings("unchecked")
public class LogError {
	static Logger failLogger=new Logger(LogError.class);
	
	
	public static void logError(SdkLogAccess log){
		try{
			StringBuilder sb=new StringBuilder();
			Map<String,String> m=BeanUtils.describe(log);
			for(String name:m.keySet()){
				if(sb.length()>0){
					sb.append("\t");
				}
				sb.append(name).append("=").append(m.get(name)==null?"":m.get(name));
			}
			failLogger.error(sb.toString());
		}catch(Exception e){
			LogWriteThread.logger.error(""+e,e);
		}
	}
	
	public static void logDebug(SdkLogAccess log){
		try{
			StringBuilder sb=new StringBuilder();
			Map<String,String> m=BeanUtils.describe(log);
			for(String name:m.keySet()){
				if(sb.length()>0){
					sb.append("\t");
				}
				sb.append(name).append("=").append(m.get(name)==null?"":m.get(name));
			}
			failLogger.info(sb.toString());
		}catch(Exception e){
			LogWriteThread.logger.error(""+e,e);
		}
	}	
}
