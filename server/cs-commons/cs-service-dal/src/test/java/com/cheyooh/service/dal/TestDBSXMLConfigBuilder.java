package com.cheyooh.service.dal;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.cheyooh.service.dal.mybatis.parse.DBSXMLConfigBuilder;
import com.cheyooh.service.dal.mybatis.parse.SelfDBConfigEntity;



/**
 * 测试配置文件
 * @author Com
 *
 */
public class TestDBSXMLConfigBuilder 
{
	@Test
	public void testParse()
	{
		String dal_path="db.xml";
		String path = TestDBSXMLConfigBuilder.class.getResource("/mybatis/").getPath();
		DBSXMLConfigBuilder dbp = new DBSXMLConfigBuilder(readMybatisXml(new File(path + dal_path)));
		List<SelfDBConfigEntity> list = dbp.parse();
		Assert.assertEquals(list.size(), 12);
		List<String> findDbs = new ArrayList<String>();
		for(SelfDBConfigEntity entity : list)
		{
			findDbs.add(entity.getId());
		}
		
		
		List<String> dbs = new ArrayList<String>();
	    dbs.add("advnews");
	    dbs.add("bbs");
	    dbs.add("cms08");
	    dbs.add("log");
	    dbs.add("message");
	    dbs.add("misc");
	    dbs.add("pub");
	    dbs.add("usedcar");
	    dbs.add("user");
	    dbs.add("wz");
	    dbs.add("message_salve");
	    dbs.add("memory_db");

		Assert.assertEquals(findDbs, dbs);
	}
	//公共方法，读取db.xml配置文件
	public static InputStream readMybatisXml(File xml){
		try{
			//String s=EnvTools.loadEnvContent(xml.getAbsolutePath(),"utf-8");
			return new FileInputStream(xml);
			//return new ByteArrayInputStream(s.getBytes("utf-8"));
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}