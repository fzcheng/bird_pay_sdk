package com.cheyooh.service.sdk.action.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.sdk.cfg.Cfg;
import com.cheyooh.service.sdk.db.dao.SdkNotifySinaMonthlyMapper;
import com.cheyooh.service.sdk.db.entity.SdkNotifySinaMonthly;
import com.cheyooh.service.sdk.db.entity.SdkNotifySinaMonthlyExample;
import com.cheyooh.service.sdk.db.entity.SdkOrder;
import com.cheyooh.service.sdk.idata.gameserver.SinaMonthPaySms;
import com.cheyooh.service.sdk.idata.gameserver.SinaMonthPaySmsReq;
import com.cheyooh.tools.log.Logger;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SinaMonthSms extends Thread {
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
	private Logger logger = new Logger(this.getClass());
	private SdkOrder order;

	public SinaMonthSms(SdkOrder order) {
		this.order = order;
	}

	@Override
	public void run() {
		DAL dal = DALFactory.createDAL();
		try {
			SdkNotifySinaMonthlyMapper monthlyMapper = dal
					.getMapper(SdkNotifySinaMonthlyMapper.class);
			SdkNotifySinaMonthlyExample example = new SdkNotifySinaMonthlyExample();
			example.createCriteria().andOrderNoEqualTo(order.getOrderNo());
			SdkNotifySinaMonthly monthly = monthlyMapper.selectOne(example);

			String phone = "";
			for (int i = 0; i < 5; i++) {
				try {
					phone = getMobileSinaPhoneNumber(monthly.getSmsPort(),
							monthly.getSmsCmd());
				} catch (Exception e) {
					logger.warn("get mobile phone error!", e);
				}

				if (StringUtils.isNotBlank(phone)) {
					monthly.setSmsState("4");
					monthly.setMobile(phone);
					break;
				}

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					logger.warn("sleep error!", e);
				}
			}

			if (StringUtils.isNotBlank(phone)) {
				boolean isPay = false;
				SinaMonthPaySms sinaMonthPaySms = null;
				try {
					logger.debug("申请计费请求时的电话号码是：" + monthly.getMobile());
					sinaMonthPaySms = sendsmstosina(order, monthly.getMobile());
					if ("0000".equals(sinaMonthPaySms.getRespCode())) {
						// TODO
						monthly.setSmsState("6");
						isPay = true;
					} else {
						logger.error("apply pay error, respCode = "
								+ sinaMonthPaySms.getRespCode());
						monthly.setSmsState("7");
					}
				} catch (Exception e) {
					logger.error("apply pay error!", e);
					monthly.setSmsState("7");
				}
				if (isPay) {
					try {

						logger.debug("申请支付地址是：" + sinaMonthPaySms.getPayURL());
						logger.debug("申请游戏名字是：" + sinaMonthPaySms.getAppName());
						SinaMonthPaySmsReq sinaMonthPaySmsReq = getpaysmssinaurl(sinaMonthPaySms
								.getPayURL());
						logger.debug("the sina SinaMonthPaySmsReq = "
								+ sinaMonthPaySmsReq);

						if (StringUtils
								.isBlank(sinaMonthPaySmsReq.getResCode())
								|| "null".equals(sinaMonthPaySmsReq
										.getResCode())) {
							monthly.setSmsState("8");
							monthly.setSmsKeynum(sinaMonthPaySmsReq.getKeyNum());
							monthly.setSmsKeywords(sinaMonthPaySmsReq
									.getKeyWords());
							monthly.setUpCmd(sinaMonthPaySmsReq.getSmsCode());
							monthly.setUpPort(sinaMonthPaySmsReq.getSmsNum());
						} else {
							logger.error("申请支付地址后get pay sms error, resCode = "
									+ sinaMonthPaySmsReq.getResCode());
							monthly.setSmsState("9");
						}
					} catch (Exception e) {
						logger.error("申请支付地址后get pay sms error!", e);
						monthly.setSmsState("9");
					}
				}
			} else {
				monthly.setSmsState("5");
				logger.error("cannot get mobile phone error!");
			}
			monthly.setUpdatedTime(new Date());
			monthlyMapper.updateByPrimaryKeySelective(monthly);
			logger.debug("the sina monthly pay status = "
					+ monthly.getSmsState());
			dal.commit();
		} catch(Exception e){
			logger.error("the run error is : "+e);
			return ;
		} finally {
			dal.close();
		}

	}

	public String getMobileSinaPhoneNumber(String longphone, String code)
			throws Exception {
		HttpClient httpclient = new DefaultHttpClient();
		try {
			// 获取用新浪发送短信时的手机号码
			String sendPhonenumberUrl = Cfg.cfg
					.getString("sdk.sina.month.getPhonenumber.url");
			StringBuffer query = new StringBuffer(sendPhonenumberUrl);
			logger.debug("the sendPhonenumberUrl api = " + sendPhonenumberUrl);
			query.append("code=").append(code);
			query.append("&longphone=").append(longphone);
			HttpGet httpget = new HttpGet(query.toString());
			HttpResponse responsegetphonenumber = httpclient.execute(httpget);
			logger.debug("第二次请求内容是：" + httpget.getURI());
			HttpEntity entitygetphonenumber = responsegetphonenumber
					.getEntity();
			logger.debug("the sendPhonenumberUrl response: "
					+ responsegetphonenumber);
			String phonenumbercontent = EntityUtils.toString(
					entitygetphonenumber, "UTF-8");
			logger.debug("the sendPhonenumberUrl content - content = "
					+ phonenumbercontent);
			logger.debug("!!!!!!!!!!!!!!!!!!!!!!!!手机号码是：" + phonenumbercontent);
			if (phonenumbercontent == null) {
				logger.debug("新浪短信发送获取手机号码失败");
			}

			return phonenumbercontent;
		} catch(Exception e){
			logger.error("the getMobileSinaPhoneNumber error is : "+e);
			return null;
		}finally {
			httpclient.getConnectionManager().shutdown();
		}
	}

	public SinaMonthPaySms sendsmstosina(SdkOrder order, String phonenumber)
			throws Exception {
		HttpClient httpclient = new DefaultHttpClient();
		SinaMonthPaySms res = new SinaMonthPaySms();
		try {
			String sendUrl = Cfg.cfg.getString("sdk.sina.month.sendsms.url");
			logger.debug("the mobile send sms sms to sina api = " + sendUrl);
			HttpPost httpost = new HttpPost(sendUrl);

			StringBuffer query = new StringBuffer();
			query.append("corpType=").append("11");
			query.append("&businessId=").append("562");
			query.append("&appName=").append(order.getOrderName());
			query.append("&appType=").append("game");

			logger.debug("--------------订单号码：" + order.getOrderNo());
			query.append("&orderNum=").append(order.getOrderNo());
			logger.debug("申请支付地址的金额参数是："
					+ Float.toString(order.getAmount() * 100) + "分");
			query.append("&orderAmt=").append(
					Float.toString(order.getAmount() * 100));
			query.append("&vt=").append("6");
			query.append("&paymentTag=").append("smsSP");
			query.append("&phoneNum=").append(phonenumber);
			String appkey = Cfg.cfg.getString("sdk.sina.month.sendsms.appkey");
			query.append("&key=").append(appkey);
			String plaintext = query.toString();
			logger.debug("！！！！！！！！！！！    md5加密前的明文是：" + plaintext);
			String md5string = DigestUtils.md5Hex(plaintext);

			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("corpType", "11"));
			nvps.add(new BasicNameValuePair("businessId", "562"));
			nvps.add(new BasicNameValuePair("appName", order.getOrderName()));
			nvps.add(new BasicNameValuePair("appType", "game"));
			nvps.add(new BasicNameValuePair("orderNum", order.getOrderNo()));
			nvps.add(new BasicNameValuePair("orderAmt", Float.toString(order
					.getAmount() * 100)));
			nvps.add(new BasicNameValuePair("vt", "6"));
			nvps.add(new BasicNameValuePair("paymentTag", "smsSP"));
			nvps.add(new BasicNameValuePair("phoneNum", phonenumber));
			nvps.add(new BasicNameValuePair("sign", md5string));
			httpost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			logger.debug("新浪无线支付申请请求是："
					+ EntityUtils.toString(httpost.getEntity(), "UTF-8"));
			logger.debug("excuting request(请求是):" + httpost.getURI());
			HttpResponse response = httpclient.execute(httpost);
			HttpEntity entity = response.getEntity();
			logger.debug("the mobile send response: " + response);
			String content = EntityUtils.toString(entity, "UTF-8");
			logger.debug("the mobile send response content - content = "
					+ content);
			res = mapper.readValue(content, SinaMonthPaySms.class);
		} catch(Exception e){
			logger.error("the sendsmstosina error is : "+e);
			return null;
		}finally {
			httpclient.getConnectionManager().shutdown();
		}
		return res;
	}

	private SinaMonthPaySmsReq getpaysmssinaurl(String url) throws Exception {
		HttpClient httpclient = new DefaultHttpClient();
		String content = "";
		try {
			// 获取用新浪发送短信时的指令和长号码
			logger.debug("the mobile send api = " + url);
			HttpPost httpget = new HttpPost(url);
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			logger.debug("the mobile send response: " + response);
			content = EntityUtils.toString(entity, "UTF-8");
			logger.debug("the mobile send response content - content = "
					+ content);
			return mapper.readValue(content, SinaMonthPaySmsReq.class);

		}catch(Exception e){
			logger.error("the getpaysmssinaurl error is : "+e);
			return null;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
	}
}
