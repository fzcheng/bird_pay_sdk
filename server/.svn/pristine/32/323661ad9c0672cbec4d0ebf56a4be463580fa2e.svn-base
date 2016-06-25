package com.cheyooh.service.dal.mybatis;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.tools.log.Logger;

/**
 * @author zzg.zhou@mbook.com.cn {MSN:zzgzhou@hotmail.com, QQ:11039850}
 *  
 */
public class LocalSession{
	static Logger logger = new Logger(LocalSession.class);
		 
	static ThreadLocal<DAL> local=new ThreadLocal<DAL>();
	
	private LocalSession(){}
	
	public static DAL createDAL(){
		DAL dal=local.get();
		if(dal==null){
			dal=new DefaultDALImpl();
			local.set(dal); 
		}
		return dal;
	}
	 
	public static void closeDAL(){
		DAL dal=local.get();
		if(dal!=null){
			dal.close();
		}
		local.remove();
	}	 
}
