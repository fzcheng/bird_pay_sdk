package com.cheyooh.service.dal;

import com.cheyooh.service.dal.diagnosis.Diagnosis;
import com.cheyooh.service.dal.mybatis.ConfigurationManager;
import com.cheyooh.service.dal.mybatis.DefaultDALImpl;

/**
 * @author zzg.zhou@mbook.com.cn {MSN:zzgzhou@hotmail.com, QQ:11039850}
 *  
 */
public class DALFactory {	 	
	static{
		ConfigurationManager.getInstance().checkInit();
		Diagnosis.start();
	}
	/**
	 * 创建DAL对象. 使用完后应调用DAL.close()函数释放资源.
	 * 
	 * @return
	 */
	public static DAL createDAL(){
		return new DefaultDALImpl();
	}
	
	/**
	 *  设置MyBatis配置文件的根目录. 默认值为: appcfg/mybatis
	 * @param path: 配置文件根路径
	 */
	public static void setXmlPath(String path){
		ConfigurationManager.CFG_ROOT_PATH=path;
	}
	
	 
	/**
	 * 系统退出时, 释放所有资源,关闭后台线程
	 */
	public static void destory(){
		Diagnosis.stop();
		ConfigurationManager.getInstance().destory();
	}
	
	 
}
