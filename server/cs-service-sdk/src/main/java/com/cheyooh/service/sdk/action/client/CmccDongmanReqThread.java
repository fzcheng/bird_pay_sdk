package com.cheyooh.service.sdk.action.client;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.sdk.cfg.Cfg;
import com.cheyooh.service.sdk.db.dao.SdkOrderMapper;
import com.cheyooh.service.sdk.db.entity.SdkOrder;
import com.cheyooh.service.sdk.db.entity.SdkOrderExample;
import com.cheyooh.service.sdk.idata.gameserver.JsonCmccDongmanConfirmRes;
import com.cheyooh.tools.log.Logger;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CmccDongmanReqThread extends Thread {
	private Logger logger = new Logger(this.getClass());
	
	private static final ObjectMapper mapper = new ObjectMapper();
	static {
		// mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		// or jackson 2.0
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		mapper.setSerializationInclusion(Include.NON_NULL);
		// jackson 1.9 and before
		// mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
		// false);
	}
	
	private static final String key=Cfg.cfg.getString("sdk.cmccdongman.key");
	
	private String orderNo;
	
	private String msgId;
	
	public CmccDongmanReqThread(String orderNo,String msgId){
		this.orderNo=orderNo;
		this.msgId=msgId;
	}
	
	@Override
	public void run() {
		DAL dal = DALFactory.createDAL();
		try {
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				logger.error("sleep1 error!", e);
			}
			
			for (int i = 0,j=0; i < 3; i++) {
				try {
					
					try {
						if(i==0){
							j=i+1;
						}
						if(i==1){
							j=i+2;
						}
						if(i==2){
							j=i+2;
						}
						Thread.sleep(j*1000);
					} catch (InterruptedException e) {
						logger.error("sleep2 error!", e);
					}
					
					logger.debug("the cmccdongman confirm request "+i );
					JsonCmccDongmanConfirmRes res=confirmCmccDongman(key,msgId);
//					if("0".equals(res.getStatus())){
//						logger.debug("the cmccdongman confirm success");
//						SdkOrderMapper sdkOrderMapper=dal.getMapper(SdkOrderMapper.class);
//						SdkOrderExample sdkOrderExample=new SdkOrderExample();
//						sdkOrderExample.createCriteria().andOrderNoEqualTo(orderNo);
//						SdkOrder sdkOrder=sdkOrderMapper.selectOne(sdkOrderExample);
//						if(sdkOrder!=null){
//							logger.debug("the cmccdongman confirm update order status");
//							sdkOrder.setStatus(1);
//						}
//						sdkOrderMapper.updateByPrimaryKey(sdkOrder);
//					}
				} catch (Exception e) {
					logger.warn("the cmccdongman confirm error!", e);
				}
			}
		} catch (Exception e) {
			logger.error("the cmccdongman confirm run error =" + e);
			return;
		} finally {
			dal.commit();
			dal.close();
		}
		
	}
	
	private JsonCmccDongmanConfirmRes confirmCmccDongman(String key,String msgId){
		HttpClient httpclient = new DefaultHttpClient();
		JsonCmccDongmanConfirmRes res=new JsonCmccDongmanConfirmRes();
		try {
			String sendUrl = Cfg.cfg.getString("sdk.cmccdongman.callbackurl");
			StringBuffer query = new StringBuffer(sendUrl);
			query.append("key=").append(key);
			query.append("&msgId=").append(msgId);
			HttpGet httpget = new HttpGet(query.toString());
//			logger.debug("the cmccdongman confirm send url = " + query.toString());
			logger.info("the cmccdongman confirm send url = " + query.toString());
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity, "UTF-8");
//			logger.debug("the cmccdongman confirm send response content - content = "
//					+ content);
			logger.info("the cmccdongman confirm send response content - content = "
					+ content);
			res = mapper.readValue(content, JsonCmccDongmanConfirmRes.class);
		} catch (Exception e) {
			logger.error("the cmccdongman confirm request error =" + e, e);
			return null;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return res;
	}
	
	public static void main(String[] args) {
//		String msgId="ffe32eda-37f3-44cf-8827-f6789970891c";
//		CmccDongmanReqThread test=new CmccDongmanReqThread(msgId);
//		test.start();
	}
}
