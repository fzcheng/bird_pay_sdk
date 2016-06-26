package com.cheyooh.service.dal;

import junit.framework.Assert;

import org.apache.commons.beanutils.BeanUtils;
import org.testng.annotations.Test;

//import com.cheyooh.service.dal.db.misc.dao.MiscBannerMapper;
//import com.cheyooh.service.dal.db.misc.entity.MiscBanner;
//import com.cheyooh.service.dal.db.misc.entity.MiscBannerExample;
import com.cheyooh.tools.log.Logger;

/**
 * 测试读取jar中xmlmap文件
 * 
 * 
 * @author zhouzg@cheyooh.com
 *
 */
@Test
public class TestJarXmlMapper {
	static Logger logger=new Logger(TestJarXmlMapper.class);	
	static{ ENVSetup.setup();}
	
		 
	public void testXmlMapper()throws Exception{
//		DAL dal=DALFactory.createDAL();
//		try{
//			MiscBannerMapper mapper=dal.getMapper(MiscBannerMapper.class);
//			MiscBanner banner=mapper.selectOne(new MiscBannerExample());			
//			logger.info(BeanUtils.describe(banner));
//			Assert.assertNotNull(banner);			 
//		}finally{
//			dal.close();
//		}
	}
	
}
