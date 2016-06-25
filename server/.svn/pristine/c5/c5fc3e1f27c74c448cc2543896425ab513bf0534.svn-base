package com.cheyooh.service.sdk.action.client;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.sdk.db.dao.SdkPhoneSegmentMapper;
import com.cheyooh.service.sdk.db.dao.SdkTelephoneMapper;
import com.cheyooh.service.sdk.db.dao.SdkXuanwuMapper;
import com.cheyooh.service.sdk.db.entity.SdkPhoneSegment;
import com.cheyooh.service.sdk.db.entity.SdkPhoneSegmentExample;
import com.cheyooh.service.sdk.db.entity.SdkTelephone;
import com.cheyooh.service.sdk.db.entity.SdkTelephoneExample;
import com.cheyooh.service.sdk.db.entity.SdkXuanwu;
import com.cheyooh.tools.log.Logger;
import com.esms.MOMsg;
import com.esms.PostMsg;
import com.esms.common.entity.Account;

public class XuanWuMmsGetPhoneThread extends Thread {
	private Logger logger = new Logger(this.getClass());
	
	public XuanWuMmsGetPhoneThread() {

	}
	
	@Override
	public void run() {
		DAL dal = DALFactory.createDAL();
		try {
			for (int i = 0; i < 4; i++) {
				logger.debug("the XuanWuGetPhone loop read, i="+i);
				
				try {
					Thread.sleep(20000);
				} catch (InterruptedException e) {
					logger.warn("the XuanWuGetPhone sleep error!", e);
				}
				
				try {
					Account ac = new Account("lzjj@lzjj", "lzjj111@");//
					PostMsg pm = new PostMsg();
					pm.getCmHost().setHost("211.147.239.62", 9080);//设置网关的IP和port，用于发送信息
					pm.getWsHost().setHost("211.147.239.62", 9070);//设置网关的 IP和port，用于获取账号信息、上行、状态报告等等
					MOMsg[] mos = pm.getMOMsgs(ac, 10);
					if(mos != null){
						for(MOMsg mo : mos){
							logger.info("the XuanWuGetPhone info is:"+mo.toString());
							String mobilePhone=mo.getPhone();
							String content=mo.getContent();
							String specnumber=mo.getSepcNumber();
							
							if(content.contains(",")&&content.length()>16){
								//此短信内容格式是：单卡格式是"imsi,imei"，双卡格式是"imsi1|imsi2,imei",
								//此部分是亿美平台使用的短信内容格式，此平台已不试用，更改为使用玄武短信平台，为了兼容已经推出去的旧游戏包，需解析此种短信内容格式。
								SdkTelephoneMapper sdkTelephoneMapper = dal
										.getMapper(SdkTelephoneMapper.class);
								SdkTelephone sdkTelephone = new SdkTelephone();
								sdkTelephone.setMobilephone(mobilePhone);
								// 获取表sdk_telephone的province_no和area_code信息
								String phoneSegmentCode =mobilePhone.substring(0, 7); // 获取手机号段
								logger.debug("the XuanWuGetPhone phone_segment_code ="+phoneSegmentCode);
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
								String imsis="";
								String imei="";
								if(content.trim().contains(",")){
									String[] imsisandimei = content.trim().split(",");
									imsis = imsisandimei[0].trim();
									imei = imsisandimei[1].trim();
								}else
								{
									imsis=content.trim();
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
								Date d = new Date();
								sdkTelephone.setCreatedTime(d);
								sdkTelephoneMapper.insertSelective(sdkTelephone);
								
							}else {
								//此种短信内容为玄武平台使用，为新模式
								SdkXuanwuMapper sdkXuanwuMapper=dal.getMapper(SdkXuanwuMapper.class);
								SdkXuanwu sdkXuanwu=sdkXuanwuMapper.selectByPrimaryKey(content);
								if(sdkXuanwu!=null){
									sdkXuanwu.setMobilephone(mobilePhone);
									sdkXuanwu.setSpecnumber(specnumber);
									
									SdkTelephoneMapper sdkTelephoneMapper = dal
											.getMapper(SdkTelephoneMapper.class);
									SdkTelephoneExample sdkTelephoneExample=new SdkTelephoneExample();
									sdkTelephoneExample.createCriteria().andMobilephoneEqualTo(mobilePhone);
									SdkTelephone sdkTelephoneDb=sdkTelephoneMapper.selectOne(sdkTelephoneExample);
									if(sdkTelephoneDb!=null){
										//已有手机号，只更新手机内容
										sdkTelephoneDb.setImsi(sdkXuanwu.getImsi());
										sdkTelephoneDb.setImei(sdkXuanwu.getImei());
										Date ud=new Date();
										sdkTelephoneDb.setUpdatedTime(ud);
										sdkTelephoneMapper.updateByPrimaryKeySelective(sdkTelephoneDb);
									}else{
										SdkTelephone sdkTelephone = new SdkTelephone();
										sdkTelephone.setMobilephone(mobilePhone);
										String phoneSegmentCode = mobilePhone.substring(0, 7); // 获取手机号段
										logger.debug("the XuanWuGetPhone phone_segment_code ="+phoneSegmentCode);
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
										sdkTelephone.setImsi(sdkXuanwu.getImsi());
										sdkTelephone.setImei(sdkXuanwu.getImei());
										Date d = new Date();
										sdkTelephone.setCreatedTime(d);
										sdkTelephoneMapper.insertSelective(sdkTelephone);
									}
								}
								sdkXuanwuMapper.updateByPrimaryKeySelective(sdkXuanwu);
							}
						}
					}
				} catch (Exception e) {
					logger.warn("the XuanWuGetPhone get mobile phone error!", e);
				}
			}

			dal.commit();
		} catch (Exception e) {
			logger.error("the XuanWuGetPhone run error is : " + e);
			return;
		} finally {
			dal.close();
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
	
	public static void main(String[] args) {
		XuanWuMmsGetPhoneThread xuanWuMmsGetPhoneThread=new XuanWuMmsGetPhoneThread();
		xuanWuMmsGetPhoneThread.start();
	}
}
