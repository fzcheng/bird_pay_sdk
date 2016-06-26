package com.cheyooh.service.dal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.testng.annotations.Test;

//import com.cheyooh.service.dal.db.misc.dao.MiscDDQMapper;
import com.cheyooh.tools.log.Logger;

/**
 * 
 * 测试 db.xml配置中<mapper url="xxxxxxxx.xml"/> xml文件的动态加载
 * 
 * 
 * @author zhouzg@cheyooh.com
 *
 */
@Test
public class TestMapperReload {
	static Logger logger=new Logger(TestMapperReload.class);	
	static{ ENVSetup.setup();}
	
	public void testDDQXmlSelectTest()throws Exception{
//		DAL dal=DALFactory.createDAL();
//		try{
//			ENVSetup.copyToXmlmap("MiscDDQMapper_v0.xml", "MiscDDQMapper.xml");	
//			
//			MiscDDQMapper mapper=dal.getMapper(MiscDDQMapper.class);
//			List<Map<String,Object>> r=mapper.DDQuery("selectTest", null);
//			
//			Assert.assertTrue(r!=null && r.size()==1);
//			Assert.assertEquals("1",r.get(0).get("1").toString());
//		}finally{
//			dal.close();
//		}
	}
	
	/**
	 * 测试动态修改xmlmap中的sql语句: selectTest
	 * @throws Exception
	 */
	@Test(dependsOnMethods={"testDDQXmlSelectTest"})
	public void testReloadDDQXmlselectTest()throws Exception{
//		logger.info("start Test : testReloadDDQXmlselectTest....");
//		DAL dal=DALFactory.createDAL();
//		try{
//			MiscDDQMapper mapper=dal.getMapper(MiscDDQMapper.class);
//			 
//			//test
//			String src =ENVSetup.class.getResource("/mybatis/xmlmap2/MiscDDQMapper_v1.xml").getPath();
//			String dest=ENVSetup.class.getResource("/mybatis/xmlmap/MiscDDQMapper.xml").getPath();
//			logger.info("Src : " + src);
//			logger.info("dest : " + dest);
//			ENVSetup.copyToXmlmap("MiscDDQMapper_v1.xml", "MiscDDQMapper.xml");		   
//			//修改后
//			List<Map<String,Object>> r=mapper.DDQuery("selectTest", null);
//			logger.info("Size : " + r.size() + " List" + r.get(0) + "  value:" + r.get(0).get("a"));
//			Assert.assertTrue(r!=null && r.size()==1);
//			Assert.assertEquals("100",r.get(0).get("a").toString());
//			
//		}finally{
//			dal.close();
//		}
	}
	
	/**
	 * 测试动态增加一条sql语句: selectTestV1
	 * 
	 * @throws Exception
	 */
	@Test(dependsOnMethods={"testReloadDDQXmlselectTest"})
	public void testReloadDDQXmlselectTestV2()throws Exception{
//		DAL dal=DALFactory.createDAL();
//		try{
//			MiscDDQMapper mapper=dal.getMapper(MiscDDQMapper.class);
//			
//			ENVSetup.copyToXmlmap("MiscDDQMapper_v0.xml", "MiscDDQMapper.xml");
//			
//			//添加前, 语句应该不存在
//			try{
//				mapper.DDQuery("selectTestV2", null);
//				Assert.fail("selectTestV2 语句已经存在于xml文件中, 需要先清理掉, 再测试.");
//			}catch(Exception e){				 
//				Assert.assertTrue(e.getMessage().indexOf("Command not found: com.cheyooh.service.dal.db.misc.dao.MiscDDQMapper.selectTestV2")>=0);
//			}
//
//			ENVSetup.copyToXmlmap("MiscDDQMapper_v2.xml", "MiscDDQMapper.xml"); 
//			
//			
//			//确认查询新的语句成功
//			List<Map<String,Object>> r=mapper.DDQuery("selectTestV2", null);
//			Assert.assertTrue(r!=null && r.size()==1);
//			Assert.assertEquals("value",r.get(0).get("aaa").toString());
//			
//		}finally{
//			dal.close();
//		}
	}
	
	/**
	 * 测试动态删除刚才增加一条sql语句, 此测试依赖于上一个增加sql语句的测试 
	 * 
	 * @throws Exception
	 */
	@Test(dependsOnMethods={"testReloadDDQXmlselectTestV2"})
	public void testReloadDDQXmlselectTestV2Del()throws Exception{
//		DAL dal=DALFactory.createDAL();
//		try{
//			MiscDDQMapper mapper=dal.getMapper(MiscDDQMapper.class);
//			
//			//删除语句
//			ENVSetup.copyToXmlmap("MiscDDQMapper_v0.xml", "MiscDDQMapper.xml"); 
//			
//			
//			//确认语句查询错误
//			try{
//				mapper.DDQuery("selectTestV2", null);
//				
//				//TODO: 目前还不支持动态载入删除的语句.				
//				//Assert.fail("selectTestV2 语句删除失败!");
//				
//			}catch(Exception e){
//				Assert.assertTrue(e.getMessage().indexOf("Command not found: com.cheyooh.service.dal.db.misc.dao.MiscDDQMapper.selectTestV2")>=0);
//			}
//			
//			 
//		}finally{
//			dal.close();
//		}
	}
		
	
}
