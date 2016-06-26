package com.cheyooh.service.dal;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import org.testng.Assert;
import org.testng.annotations.Test;

//import com.cheyooh.service.dal.db.misc.dao.MiscDDQMapper;
import com.cheyooh.tools.log.Logger;

/**
 * 测试在并发情况下: 修改xml配置文件, 不会导致sql查询失败
 * 
 * 
 * @author zhouzg@cheyooh.com
 *
 */
@Test
public class TestConcurrentMapperReload {
	static Logger logger=new Logger(TestConcurrentMapperReload.class);	
	static{ ENVSetup.setup();}
	
		 
	public void testReload()throws Exception{
		//初始化
		executeQuery(new CountDownLatch(1),new AtomicInteger());
		
		
		//测试线程数
		final int threads=50;
		//每线程查询次数
		final int querys_per_thread=100;
		
		//xml文件变更5次
		final int xml_changes=5;
		
		final CountDownLatch cdl=new  CountDownLatch(threads+1);
		final AtomicInteger success=new AtomicInteger(0);
		
		for(int i=0;i<threads;i++){
			new Thread(){
				public void run(){
					executeQuery(cdl,success);
				}
			}.start();
		}
		
		new Thread(){
			public void run(){
				for(int i=0;i<xml_changes;i++){
					try{
						ENVSetup.copyToXmlmap("MiscDDQMapper_v"+(i%3)+".xml", "MiscDDQMapper.xml");
						if(i<xml_changes-1){
							Thread.sleep(1000);
						}
						
						success.incrementAndGet();
					}catch(Exception e){
						logger.error(e);
					}
				}
				
				cdl.countDown();
			}
		}.start();
		
		logger.info("Waiting for all thread exit ...");
		cdl.await();
		
		Assert.assertEquals(success.get(), threads*querys_per_thread+xml_changes);
		
		logger.info("All thread test ok!");
	}
	
	
	private void executeQuery(CountDownLatch cdl,AtomicInteger success){
//		logger.info("Run thread: "+Thread.currentThread().getName());
//		
//		DAL dal=DALFactory.createDAL();
//		try{			 
//			Random rm=new Random();
//			
//			MiscDDQMapper mapper=dal.getMapper(MiscDDQMapper.class);
//			for(int i=0;i<100;i++){	
//				try{ Thread.sleep(1+rm.nextInt(30)); }catch(Exception e){}
//				
//				List<Map<String,Object>> r=mapper.DDQuery("selectTest", null);
//				
//				Assert.assertTrue(r!=null && r.size()==1);								
//				Assert.assertTrue(r.get(0).containsKey("1") || r.get(0).containsKey("a"));
//			 	
//				success.incrementAndGet();
//			}
//		}catch(Exception e){
//			logger.error(e);
//		}finally{
//			dal.close();
//			
//			cdl.countDown();
//			logger.info("Finished thread: "+Thread.currentThread().getName());
//		}
	}
	 
}
