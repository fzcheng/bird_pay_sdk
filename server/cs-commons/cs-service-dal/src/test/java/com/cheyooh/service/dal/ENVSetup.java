package com.cheyooh.service.dal;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.math.RandomUtils;

import com.cheyooh.service.dal.mybatis.ConfigurationManager;
import com.cheyooh.tools.log.Logger;

public class ENVSetup {
	static Logger logger=new Logger(ENVSetup.class);
	
	//定义延迟描述，主要解决问题是，java对文件读取修改时间（getLastModified()）在不同的操作系统下存在差异
	//1.在windows下，返回值是毫秒级别，不存在问题
	//2.在Linux下，返回的值是毫秒值，但是会舍掉毫秒数字，变成三个零，如1403690647983 在LInux下会是1403690647000
	private static AtomicInteger delaySecond = new AtomicInteger(1);

	
	static
	{
		String env_path="env.cfg";
		String path = TestMapperReload.class.getResource("/").getPath();
		System.setProperty("CS_PATH_ENV_CFG",path + File.separator + env_path);
		
		//为了加快测试速度, 设置每秒检测一次配置文件的变更
		ConfigurationManager.getInstance().setCfgXmlCheckTime(1);
	}	
	
	public static void setup(){
		
	}
	
	public static void copyToXmlmap(String fromXmlFile,String toXmlFile)throws Exception{	
		logger.info("Copy xmlmap2/"+fromXmlFile+" -> xmlmap/"+toXmlFile);
		
		String src =ENVSetup.class.getResource("/mybatis/xmlmap2/"+fromXmlFile).getPath();
		String dest=ENVSetup.class.getResource("/mybatis/xmlmap/" +toXmlFile).getPath();
		FileUtils.copyFile(new File(src), new File(dest));
		
		int s = delaySecond.getAndIncrement() * 1000;
		new File(dest).setLastModified(System.currentTimeMillis() +  s);
		
		ConfigurationManager.getInstance().reload();		 
	}
}
