package com.cheyooh.service.sdk.action.client;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.nuxeo.common.xmap.XMap;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.sdk.cfg.Cfg;
import com.cheyooh.service.sdk.db.dao.SdkPhoneSegmentMapper;
import com.cheyooh.service.sdk.db.dao.SdkTelephoneMapper;
import com.cheyooh.service.sdk.db.entity.SdkPhoneSegment;
import com.cheyooh.service.sdk.db.entity.SdkPhoneSegmentExample;
import com.cheyooh.service.sdk.db.entity.SdkTelephone;
import com.cheyooh.service.sdk.idata.gameserver.EmaySms;
import com.cheyooh.service.sdk.idata.gameserver.XmlEmayUpMessage;
import com.cheyooh.tools.log.Logger;

public class EmayUpMessageReceive extends Thread {

	private Logger logger = new Logger(this.getClass());

	public EmayUpMessageReceive() {

	}

	@Override
	public void run() {
		DAL dal = DALFactory.createDAL();
		try {
//			SimpleDateFormat formatter = new SimpleDateFormat("yyyyHHddHHmmss");
			for (int i = 0; i < 6; i++) {

				try {
					EmaySms sms = getEmayUpMessege();
					logger.debug("the Emay sms is :" + sms);
					List<XmlEmayUpMessage> message = sms.getXmlEmayUpMessage();
					logger.debug("the List size is :"+message.size());
					while (true) {
						if (message.get(0).getSrctermid() != null) {
							for (XmlEmayUpMessage xmlEmayUpMessage : sms
									.getXmlEmayUpMessage()) {
								SdkTelephoneMapper sdkTelephoneMapper = dal
										.getMapper(SdkTelephoneMapper.class);
								SdkTelephone sdkTelephone = new SdkTelephone();
								sdkTelephone.setMobilephone(xmlEmayUpMessage.getSrctermid().trim());
								// 获取表sdk_telephone的province_no和area_code信息
								String phoneSegmentCode = xmlEmayUpMessage
										.getSrctermid().trim().substring(0, 7); // 获取手机号段
								logger.debug("the phone_segment_code is :"+phoneSegmentCode);
								SdkPhoneSegmentMapper sdkPhoneSegmentMapper = dal
										.getMapper(SdkPhoneSegmentMapper.class);
								SdkPhoneSegmentExample sdkPhoneSegmentExample = new SdkPhoneSegmentExample();
								sdkPhoneSegmentExample.createCriteria().andPhoneSegmentCodeEqualTo(parseInteger(phoneSegmentCode.trim()));
								SdkPhoneSegment sdkPhoneSegment = sdkPhoneSegmentMapper.selectOne(sdkPhoneSegmentExample);
								if (sdkPhoneSegment != null) {
									sdkTelephone.setProvinceNo(sdkPhoneSegment
											.getProvinceNo());
									sdkTelephone.setAreaCode(sdkPhoneSegment
											.getAreaCode());
								} else {
									sdkTelephone.setProvinceNo(null);
									sdkTelephone.setAreaCode(null);
								}
								logger.debug("the Emay Msgcontent is :"+xmlEmayUpMessage.getMsgcontent().trim());
								logger.info("the telephone is :"+xmlEmayUpMessage.getSrctermid().trim());
								logger.info("the Emay Msgcontent is :"+xmlEmayUpMessage.getMsgcontent().trim());
								String imsis="";
								String imei="";
								if(xmlEmayUpMessage.getMsgcontent().trim().contains(",")){
									String[] imsisandimei = xmlEmayUpMessage
											.getMsgcontent().trim().split(",");
									imsis = imsisandimei[0].trim();
									imei = imsisandimei[1].trim();
								}else
								{
									imsis=xmlEmayUpMessage.getMsgcontent().trim();
								}
								
								if(imsis.contains("|")){
									// 如果存在两张卡，取第一张卡的imsi
									String[] imsi = imsis.trim().split("\\|");
									String imsi1 = imsi[0].trim();
									String imsi2 = imsi[1].trim();
									
									if(sdkPhoneSegment != null){
										int operation=sdkPhoneSegment.getOperationType();
										if(operation==1){
											if(imsi1.startsWith("46000") || imsi1.startsWith("46002")
													|| imsi1.startsWith("46007") || imsi1.startsWith("898600")){
												sdkTelephone.setImsi(imsi1);
											}else {
												sdkTelephone.setImsi(imsi2);
											}
										}
										if(operation==2){
											if(imsi1.startsWith("46001")|| imsi1.startsWith("46006")
													|| imsi1.startsWith("46010")){
												sdkTelephone.setImsi(imsi1);
											}else{
												sdkTelephone.setImsi(imsi2);
											}
										}
										if(operation==3){
											if(imsi1.startsWith("46003")|| imsi1.startsWith("46005")
													|| imsi1.startsWith("46011")){
												sdkTelephone.setImsi(imsi1);
											}else{
												sdkTelephone.setImsi(imsi2);
											}
										}
									}else{
										sdkTelephone.setImsi(imsi1);
									}
								}else if(imsis.contains(" ")){
									// 如果存在两张卡，取第一张卡的imsi
									String[] imsi = imsis.trim().split(" ");
									String imsi1 = imsi[0].trim();
									String imsi2 = imsi[1].trim();
									
									if(sdkPhoneSegment != null){
										int operation=sdkPhoneSegment.getOperationType();
										if(operation==1){
											if(imsi1.startsWith("46000") || imsi1.startsWith("46002")
													|| imsi1.startsWith("46007") || imsi1.startsWith("898600")){
												sdkTelephone.setImsi(imsi1);
											}else {
												sdkTelephone.setImsi(imsi2);
											}
										}
										if(operation==2){
											if(imsi1.startsWith("46001")|| imsi1.startsWith("46006")
													|| imsi1.startsWith("46010")){
												sdkTelephone.setImsi(imsi1);
											}else{
												sdkTelephone.setImsi(imsi2);
											}
										}
										if(operation==3){
											if(imsi1.startsWith("46003")|| imsi1.startsWith("46005")
													|| imsi1.startsWith("46011")){
												sdkTelephone.setImsi(imsi1);
											}else{
												sdkTelephone.setImsi(imsi2);
											}
										}
									}else{
										sdkTelephone.setImsi(imsi1);
									}
								}else{
									sdkTelephone.setImsi(imsis);
								}
								sdkTelephone.setImei(imei);
								// sdkTelephone.setCreatedTime(formatter.parse(xmlEmayUpMessage.getSendTime().trim()));
								Date d = new Date();
								sdkTelephone.setCreatedTime(d);
								
								sdkTelephoneMapper
										.insertSelective(sdkTelephone);
							}
						} else {
							logger.debug("Break out!");
							break;
						}

						sms = getEmayUpMessege();
						message.clear();
						message = sms.getXmlEmayUpMessage();
					}

				} catch (Exception e) {
					logger.warn("get mobile phone error!", e);
				}

				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					logger.warn("sleep error!", e);
				}
			}

			dal.commit();
		} catch (Exception e) {
			logger.error("the run error is : " + e);
			return;
		} finally {
			dal.close();
		}
	}

	private EmaySms getEmayUpMessege() {
		HttpClient httpclient = new DefaultHttpClient();
		try {
			String url = Cfg.cfg.getString("sdk.emay.smscontent.url");
			String cdkey = Cfg.cfg.getString("sdk.emay.cdkey");
			String password = Cfg.cfg.getString("sdk.emay.key");

			StringBuffer query = new StringBuffer(url);
			if (url.contains("?")) {
				query.append("&");
			} else {
				query.append("?");
			}
			query.append("cdkey=").append(cdkey);
			query.append("&password=").append(password);

			logger.debug("the Emay request is :"+query.toString());
			HttpGet httpGet = new HttpGet(query.toString());
			HttpResponse response = httpclient.execute(httpGet);
			HttpEntity entity = response.getEntity();

			logger.debug("yimei response: " + response);

			String content = EntityUtils.toString(entity, "UTF-8");

			logger.debug("yimei response content - content = " + content);
			XMap xmap = new XMap();
			xmap.register(EmaySms.class);
			ByteArrayInputStream in = new ByteArrayInputStream(content.trim()
					.getBytes("utf-8"));
			EmaySms sms = (EmaySms) xmap.load(in);
			return sms;
		} catch (Exception e) {

			e.printStackTrace();
			logger.info("the emay error is :" + e);
			return null;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
	}

	private Integer parseInteger(String str) {
		Integer num = null;
		try {
			if (StringUtils.isNotBlank(str)) {
				num = Integer.valueOf(str);
			}
		} catch (NumberFormatException e) {
			logger.warn("parse number string error! str = " + str);
		}
		return num;
	}
}
