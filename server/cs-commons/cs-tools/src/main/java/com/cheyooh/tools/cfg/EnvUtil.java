package com.cheyooh.tools.cfg;

import java.io.File;
import java.util.Map;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.lang.StringUtils;

import com.cheyooh.tools.log.Logger;

/**
 * 加载环境配置文件信息
 */
public class EnvUtil {
	private static Logger logger = new Logger(EnvUtil.class);
	public static PropertiesConfiguration env = new PropertiesConfiguration();
	static {
		try {
			// //必须确认设定的全局配置文件
			// Map<String,String> em=System.getenv();
			// String envpath = em.get("CS_PATH_ENV_CFG");
			// if(envpath!=null){
			// logger.info("Load env.cfg from system env: "+envpath);
			// }else{
			// envpath=CfgPath.getCfgPathRoot()+ File.separator + "env.cfg";
			// if(new File(envpath).exists()){
			// logger.info("Load env.cfg from app path: "+envpath);
			// }else{
			// envpath="../../cs-config/trunk/development/env.cfg";
			//
			// logger.info("Load env.cfg from dev path: "+envpath);
			// }
			// }
			File envFile = new File(getEnvFilePath());
			if (envFile.exists() && envFile.isFile()) {
				env = new PropertiesConfiguration();
				env.setEncoding("utf-8");
				env.load(envFile);
				
				env.setReloadingStrategy(new FileChangedReloadingStrategy());
			} else {
				logger.error("env.cfg not found: " + envFile.getAbsolutePath());
			}
		} catch (Exception ex) {
			logger.error("Exception load env.cfg: " + ex, ex);
			env = new PropertiesConfiguration();
		}
	}

	public static String getEnvFilePath() {
		// 必须确认设定的全局配置文件
		
		String envpath = CfgPath.getCfgPathRoot() + File.separator + "env.cfg";
		if (new File(envpath).exists()) {
			logger.info("Load env.cfg from app path: " + envpath);
		} else {
			envpath=System.getProperty("CS_PATH_ENV_CFG");			
			if(envpath==null){
				Map<String, String> em = System.getenv();
				envpath = em.get("CS_PATH_ENV_CFG");
				if(envpath!=null){
					logger.info("Load env.cfg from system env: " + envpath);
				}
			}else{
				logger.info("Load env.cfg from system property: " + envpath);
			}
			
			if (envpath == null) {
				envpath = "../../cs-config/trunk/development/env.cfg";			
				logger.info("Load env.cfg from dev path: " + envpath);
			}
			if(new File(envpath).exists()==false){
				throw new RuntimeException("File env.cfg not found!");
			}
		}
		return envpath;
	}

	public static String getValue(String key) {
		String value = StringUtils.join(env.getList(key).iterator(), ",");
		return value;
	}
}
