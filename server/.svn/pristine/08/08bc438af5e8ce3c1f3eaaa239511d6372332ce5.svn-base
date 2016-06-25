package com.yeepay;

import com.cheyooh.service.sdk.cfg.Cfg;

 
/**
 * @author lu.li
 *
 */
public class Configuration {
	private static Configuration configuration=new Configuration();
	
	public static Configuration getInstance(){
		return configuration;
	}
	
	private Configuration(){}
	
	public String getValue(String key) {
		return Cfg.cfg.getString("sdk.pay.yeepay."+key);
	}
}
