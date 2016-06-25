/**
 * 
 */
package com.cheyooh.service.sdk.action.client;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.nuxeo.common.xmap.XMap;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.StatusCode;
import com.cheyooh.service.sdk.cfg.Cfg;
import com.cheyooh.service.sdk.db.dao.SdkCmcc49youVerifyMapper;
import com.cheyooh.service.sdk.db.dao.SdkCuccZhangyunzyVerifyMapper;
import com.cheyooh.service.sdk.db.dao.SdkMobileGameBaseVerifyMapper;
import com.cheyooh.service.sdk.db.dao.SdkOrderMapper;
import com.cheyooh.service.sdk.db.dao.SdkOrderMmdoMapper;
import com.cheyooh.service.sdk.db.dao.SdkOrderMmdoVerifycodeMapper;
import com.cheyooh.service.sdk.db.entity.SdkCmcc49youVerify;
import com.cheyooh.service.sdk.db.entity.SdkCmcc49youVerifyExample;
import com.cheyooh.service.sdk.db.entity.SdkCuccZhangyunzyVerify;
import com.cheyooh.service.sdk.db.entity.SdkCuccZhangyunzyVerifyExample;
import com.cheyooh.service.sdk.db.entity.SdkMobileGameBaseVerify;
import com.cheyooh.service.sdk.db.entity.SdkMobileGameBaseVerifyExample;
import com.cheyooh.service.sdk.db.entity.SdkOrder;
import com.cheyooh.service.sdk.db.entity.SdkOrderMmdo;
import com.cheyooh.service.sdk.db.entity.SdkOrderMmdoVerifycode;
import com.cheyooh.service.sdk.db.entity.SdkOrderMmdoVerifycodeExample;
import com.cheyooh.service.sdk.idata.CmdPayVerifyCodeMmdo;
import com.cheyooh.service.sdk.idata.gameserver.JsonCmccYongzhengReqOrder;
import com.cheyooh.service.sdk.idata.gameserver.JsonCuccYijianWomusicReqParam;
import com.cheyooh.service.sdk.idata.gameserver.JsonCuccYijianWomusicRes;
import com.cheyooh.service.sdk.idata.gameserver.JsonCuccYijianWomusicSyncOrder;
import com.cheyooh.service.sdk.idata.gameserver.XmlCmcc49youVerifycodeRes;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Merlin
 * 
 */
public class Pay_verifycode extends
		AbstractSdkClientService<CmdPayVerifyCodeMmdo> {
	private static final String CUCCZHANGYUNZY = "cucczhangyunzy";
	private static final String CUCCYIJIANWOMUSIC = "cuccyijianwomusic";
	private static final String CMCCYONGZHENG = "cmccyongzheng";
	private static final ObjectMapper mapper = new ObjectMapper();
	static {
		// mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
	}
	
	private String faileString3="-1,301,302";
	private String faileString4="104,105,106,107,108,300,317,318";
	private String faileString5="304,305,331,338,345,352,253,254,355,356,357,358,359,360";
//	private String faileString6="";
	private String faileString7="350,351";
	private String faileString8="102,319";
	private String faileString9="101,103,109,324";
	private String faileString10="314,315,361";
	private String faileString11="303";
	private String faileString12="306,309,311,320,325,327,329,332,334,336,339,341,343,346,348";
	private String faileString13="307,310,316,321,326,328,330,333,335,337,340,342,344,347,349";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cheyooh.service.sdk.action.client.AbstractSdkClientService#
	 * isLoginRequired()
	 */
	@Override
	protected boolean isLoginRequired() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cheyooh.service.framework.basic.Service#execute(com.cheyooh.service
	 * .framework.idata.Cmd)
	 */
	@Override
	protected Result execute(CmdPayVerifyCodeMmdo cmd) {
		DAL dal = DALFactory.createDAL();
		try {
			String orderNo= cmd.getOrder_no();
			logger.info("the pay_verifycode confirm pay , the orderNo ="+orderNo);
			SdkOrderMapper orderMapper = dal.getMapper(SdkOrderMapper.class);
			SdkOrder order = orderMapper.selectByPrimaryKey(orderNo);
			if(order!=null){
				SdkOrderMmdoMapper sdkOrderMmdoMapper = dal
						.getMapper(SdkOrderMmdoMapper.class);
				SdkOrderMmdo sdkOrderMmdo = sdkOrderMmdoMapper
						.selectByPrimaryKey(order.getPayId());
				if(sdkOrderMmdo!=null){
					if("mobilegamebasepm".equals(sdkOrderMmdo.getPayChannelCode())){
						logger.info("enter the  mobile game base confirm pay "+orderNo);
						SdkMobileGameBaseVerifyMapper verifyMapper = dal
								.getMapper(SdkMobileGameBaseVerifyMapper.class);
						SdkMobileGameBaseVerifyExample example = new SdkMobileGameBaseVerifyExample();
						example.createCriteria().andOrderNoEqualTo(cmd.getOrder_no());
						SdkMobileGameBaseVerify verify = verifyMapper.selectOne(example);
						if (verify == null) {
							return StatusCode.ERR_NOTFOUND();
						}

						verify.setVerifyCode(cmd.getVerifycode());
						verify.setUpdatedTime(new Date());

						try {
							Map<String, String> res=confirmPayMobilegamebasepm(verify);
							logger.info("the mobile game base confirm and the orderNo and CD is :"+orderNo+","+res.get("CD"));
							if("1000".equals(res.get("CD"))){
								order.setStatus(1);
							}else if(faileString3.contains(res.get("CD"))){
								order.setStatus(3);
								order.setStatusDetail(res.get("MSG"));
							}else if(faileString5.contains(res.get("CD"))){
								order.setStatus(5);
								order.setStatusDetail(res.get("MSG"));
							}else if(faileString10.contains(res.get("CD"))){
								order.setStatus(10);
								order.setStatusDetail(res.get("MSG"));
							}else if(faileString4.contains(res.get("CD"))){
								order.setStatus(4);
								order.setStatusDetail(res.get("MSG"));
							}else if(faileString7.contains(res.get("CD"))){
								order.setStatus(7);
								order.setStatusDetail(res.get("MSG"));
							}else if(faileString8.contains(res.get("CD"))){
								order.setStatus(8);
								order.setStatusDetail(res.get("MSG"));
							}else if(faileString9.contains(res.get("CD"))){
								order.setStatus(9);
								order.setStatusDetail(res.get("MSG"));
							}else if(faileString11.contains(res.get("CD"))){
								order.setStatus(11);
								order.setStatusDetail(res.get("MSG"));
							}else if(faileString12.contains(res.get("CD"))){
								order.setStatus(12);
								order.setStatusDetail(res.get("MSG"));
							}else if(faileString13.contains(res.get("CD"))){
								order.setStatus(13);
								order.setStatusDetail(res.get("MSG"));
							}
							SdkOrderMapper sdkOrderMapper = dal
									.getMapper(SdkOrderMapper.class);
							sdkOrderMapper.updateByPrimaryKeySelective(order);
						} catch (Exception e) {
							logger.error("the mobile game base confirm pay error", e);
						}

						verifyMapper.updateByPrimaryKeySelective(verify);
						dal.commit();
					}else if("cmcc49you".equals(sdkOrderMmdo.getPayChannelCode())){
						logger.info("enter the  cmcc49you confirm pay "+orderNo);
						SdkCmcc49youVerifyMapper sdkCmcc49youVerifyMapper = dal
								.getMapper(SdkCmcc49youVerifyMapper.class);
						SdkCmcc49youVerifyExample sdkCmcc49youVerifyExample = new SdkCmcc49youVerifyExample();
						sdkCmcc49youVerifyExample.createCriteria().andOrderNoEqualTo(
								cmd.getOrder_no());
						SdkCmcc49youVerify sdkCmcc49youVerify = sdkCmcc49youVerifyMapper
								.selectOne(sdkCmcc49youVerifyExample);
						if (sdkCmcc49youVerify == null) {
							return StatusCode.ERR_NOTFOUND();
						}

						sdkCmcc49youVerify.setVerifyCode(cmd.getVerifycode());
						sdkCmcc49youVerify.setUpdatedTime(new Date());

						try {
							XmlCmcc49youVerifycodeRes res=confirmPayCmcc49you(sdkCmcc49youVerify);
							logger.info("the cmcc49you confirm resultcode is :"+res.getResultCode());
							if("200000".equals(res.getResultCode())){
								order.setStatus(1);
							}else if("200001".equals(res.getResultCode())||"200068".equals(res.getResultCode())){
								order.setStatus(4);
								order.setStatusDetail("订单异常");
							}else if("201006".equals(res.getResultCode())){
								order.setStatus(6);
								order.setStatusDetail("余额不足");
							}else if("201268".equals(res.getResultCode())){
								order.setStatus(8);
								order.setStatusDetail("计费点无效");
							}else{
								order.setStatus(3);
								order.setStatusDetail("支付失败");
							}
							SdkOrderMapper sdkOrderMapper = dal
									.getMapper(SdkOrderMapper.class);
							sdkOrderMapper.updateByPrimaryKeySelective(order);
						} catch (Exception e) {
							logger.error("the cmcc49you confirm pay error", e);
						}

						sdkCmcc49youVerifyMapper
								.updateByPrimaryKeySelective(sdkCmcc49youVerify);
						dal.commit();
					}else if(CUCCZHANGYUNZY.equalsIgnoreCase(sdkOrderMmdo.getPayChannelCode())){
						logger.info("the cucczhangyunzy confirm pay "+orderNo);
						SdkCuccZhangyunzyVerifyMapper sdkCuccZhangyunzyVerifyMapper=dal.getMapper(SdkCuccZhangyunzyVerifyMapper.class);
						SdkCuccZhangyunzyVerifyExample sdkCuccZhangyunzyVerifyExample=new SdkCuccZhangyunzyVerifyExample();
						sdkCuccZhangyunzyVerifyExample.createCriteria().andOrderNoEqualTo(cmd.getOrder_no());
						SdkCuccZhangyunzyVerify sdkCuccZhangyunzyVerify=sdkCuccZhangyunzyVerifyMapper.selectOne(sdkCuccZhangyunzyVerifyExample);
						if(sdkCuccZhangyunzyVerify==null){
							return StatusCode.ERR_NOTFOUND();
						}
						sdkCuccZhangyunzyVerify.setVerifyCode(cmd.getVerifycode());
						sdkCuccZhangyunzyVerify.setUpdatedTime(new Date());
						try {
							String unikey=sdkCuccZhangyunzyVerify.getOutTradeNo();
							String phone=sdkCuccZhangyunzyVerify.getMobile();
							String result=confirmPayCuccZhangyunZy(unikey,phone,cmd.getVerifycode());
							String[] r=result.split("<:>");
							if(r[0].equals("SUCCESS")){
								order.setStatus(1);
								order.setOriginalcode(r[1]);
								sdkOrderMmdo.setRespStatus(1);
							}else{
								order.setStatus(3);
								order.setOriginalcode(r[1]);
								order.setStatusDetail(r[2]);
							}
							sdkCuccZhangyunzyVerify.setConfirmResultcode(r[1]);
							sdkCuccZhangyunzyVerify.setConfirmResultmsg(result);
						}catch (Exception e) {
							logger.error("the cucczhangyunzy confirm pay error", e);
						}
						SdkOrderMapper sdkOrderMapper = dal
								.getMapper(SdkOrderMapper.class);
						sdkOrderMapper.updateByPrimaryKeySelective(order);
						sdkCuccZhangyunzyVerifyMapper.updateByPrimaryKeySelective(sdkCuccZhangyunzyVerify);
						dal.commit();
					}else if(CUCCYIJIANWOMUSIC.equalsIgnoreCase(sdkOrderMmdo.getPayChannelCode())){
						logger.info("the cuccyijianwomusic confirm pay orderNo ="+orderNo);
						SdkOrderMmdoVerifycodeMapper sdkOrderMmdoVerifycodeMapper=dal.getMapper(SdkOrderMmdoVerifycodeMapper.class);
						SdkOrderMmdoVerifycodeExample sdkOrderMmdoVerifycodeExample=new SdkOrderMmdoVerifycodeExample();
						sdkOrderMmdoVerifycodeExample.createCriteria().andOrderNoEqualTo(cmd.getOrder_no());
						SdkOrderMmdoVerifycode sdkOrderMmdoVerifycode=sdkOrderMmdoVerifycodeMapper.selectOne(sdkOrderMmdoVerifycodeExample);
						if(sdkOrderMmdoVerifycode==null){
							return StatusCode.ERR_NOTFOUND();
						}
						sdkOrderMmdoVerifycode.setVerifyCode(cmd.getVerifycode());
						sdkOrderMmdoVerifycode.setUpdatedTime(new Date());
						try {
							JsonCuccYijianWomusicRes jsonCuccYijianWomusicRes=confirmPayCuccYijianWomusic(cmd.getOrder_no()
									,sdkOrderMmdoVerifycode.getOutTradeNo(),String.valueOf(sdkOrderMmdoVerifycode.getPrice())
									,sdkOrderMmdo.getReqImsi(),sdkOrderMmdo.getImei()
									,sdkOrderMmdo.getIpAddr(),cmd.getVerifycode(),"0");
							if("0".equals(jsonCuccYijianWomusicRes.getStatus())){
								order.setStatus(1);
								sdkOrderMmdo.setRespStatus(1);
								order.setStatusDetail("");
								order.setOriginalcode("leyosms-1");
							}else{
								order.setStatus(3);
							}
							sdkOrderMmdoVerifycode.setConfirmResultcode(jsonCuccYijianWomusicRes.getStatus());
							String confirmResultmsg=mapper.writeValueAsString(jsonCuccYijianWomusicRes);
							sdkOrderMmdoVerifycode.setConfirmResultmsg(confirmResultmsg);
						}catch (Exception e) {
							logger.error("the cuccyijianwomusic confirm pay error", e);
						}
						SdkOrderMapper sdkOrderMapper = dal
								.getMapper(SdkOrderMapper.class);
						sdkOrderMapper.updateByPrimaryKeySelective(order);
						sdkOrderMmdoVerifycodeMapper.updateByPrimaryKeySelective(sdkOrderMmdoVerifycode);
						dal.commit();
					}else if(CMCCYONGZHENG.equalsIgnoreCase(sdkOrderMmdo.getPayChannelCode())){
						logger.info("the cmccyongzheng confirm pay orderNo ="+orderNo);
						SdkOrderMmdoVerifycodeMapper sdkOrderMmdoVerifycodeMapper=dal.getMapper(SdkOrderMmdoVerifycodeMapper.class);
						SdkOrderMmdoVerifycodeExample sdkOrderMmdoVerifycodeExample=new SdkOrderMmdoVerifycodeExample();
						sdkOrderMmdoVerifycodeExample.createCriteria().andOrderNoEqualTo(cmd.getOrder_no());
						SdkOrderMmdoVerifycode sdkOrderMmdoVerifycode=sdkOrderMmdoVerifycodeMapper.selectOne(sdkOrderMmdoVerifycodeExample);
						if(sdkOrderMmdoVerifycode==null){
							return StatusCode.ERR_NOTFOUND();
						}
						sdkOrderMmdoVerifycode.setVerifyCode(cmd.getVerifycode());
						sdkOrderMmdoVerifycode.setUpdatedTime(new Date());
						try {
							JsonCmccYongzhengReqOrder confirmres=confirmPayCmccYongzheng(sdkOrderMmdoVerifycode.getOutTradeNo(),cmd.getVerifycode());
							if("200".equals(confirmres.getState())){
								order.setStatus(1);
								sdkOrderMmdo.setRespStatus(1);
								order.setStatusDetail(confirmres.getMessage());
								order.setOriginalcode("leyosms-1");
							}else{
								order.setStatus(3);
								order.setStatusDetail(confirmres.getMessage());
							}
							sdkOrderMmdoVerifycode.setConfirmResultcode(confirmres.getState());
							String confirmResultmsg=mapper.writeValueAsString(confirmres);
							sdkOrderMmdoVerifycode.setConfirmResultmsg(confirmResultmsg);
						}catch (Exception e) {
							logger.error("the cmccyongzheng confirm pay error", e);
						}
						sdkOrderMmdoVerifycodeMapper.updateByPrimaryKeySelective(sdkOrderMmdoVerifycode);
					}
				}
				sdkOrderMmdoMapper.updateByPrimaryKeySelective(sdkOrderMmdo);
			}
			SdkOrderMapper sdkOrderMapper = dal
					.getMapper(SdkOrderMapper.class);
			sdkOrderMapper.updateByPrimaryKeySelective(order);
			dal.commit();
		}catch(Exception e){
			logger.error("the yeyou error ="+e);
			return null;
		}finally {
			dal.close();
		}
		return StatusCode.SUCCESS();
		
	}

	/**
	 * 移动原宿页游
	 * @param verify
	 * @return
	 */
	private Map<String, String> confirmPayMobilegamebasepm(final SdkMobileGameBaseVerify verify){
		HttpClient httpclient = new DefaultHttpClient();
		Map<String, String> res=new HashMap<String, String>();
		try {
			String smsUrl = Cfg.cfg.getString("sdk.mobilegamebase.url");
			logger.debug("the mobile game base confirm pay api = " + smsUrl);

			HttpPost httpost = new HttpPost(smsUrl);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("method", "cp"));
			nvps.add(new BasicNameValuePair("partner", Cfg.cfg
					.getString("sdk.mobilegamebase.partner")));
			nvps.add(new BasicNameValuePair("order", verify.getOutTradeNo()));
			nvps.add(new BasicNameValuePair("verifycode", verify
					.getVerifyCode()));
			nvps.add(new BasicNameValuePair("key", Cfg.cfg
					.getString("sdk.mobilegamebase.confirm.key")));
			logger.debug("the mobile game base confirm parameter : " + nvps);

			httpost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));

			HttpResponse response = httpclient.execute(httpost);
			HttpEntity entity = response.getEntity();
			logger.debug("the mobile game base confirm response: " + response);
			String content = EntityUtils.toString(entity, "UTF-8");
			logger.debug("the mobile game base confirm response content - content = "
					+ content);
			verify.setConfirmRawData(content);
			res = mapper.readValue(content,
					new TypeReference<HashMap<String, String>>() {
					});
			return res;
		} catch (Exception e) {
			logger.error("the mobile game base confirm error is :" + e);
			return null;
		}finally {
			httpclient.getConnectionManager().shutdown();
		}
	}

	
	/**
	 * 移动页游49游
	 * @param sdkCmcc49youVerify
	 * @return
	 * @throws Exception
	 */
	private XmlCmcc49youVerifycodeRes confirmPayCmcc49you(
			final SdkCmcc49youVerify sdkCmcc49youVerify) throws Exception {
		HttpClient httpclient = new DefaultHttpClient();
		XmlCmcc49youVerifycodeRes res = new XmlCmcc49youVerifycodeRes();
		try {
			String sendUrl = Cfg.cfg.getString("sdk.cmcc49you.urlconfirm");
			logger.debug("the cmcc49you confirm api = " + sendUrl);
			StringBuffer query = new StringBuffer(sendUrl);
			query.append("verifycode=").append(
					sdkCmcc49youVerify.getVerifyCode());
			query.append("&orderid=")
					.append(sdkCmcc49youVerify.getOutTradeNo());
			HttpGet httpget = new HttpGet(query.toString());
			logger.debug("the cmcc49you confirm send url = " + query.toString());
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			logger.debug("the cmcc49you confirm send response: " + response);
			String content = EntityUtils.toString(entity, "UTF-8");
			logger.debug("the cmcc49you send response content - content = "
					+ content);
			XMap xmap = new XMap();
			xmap.register(XmlCmcc49youVerifycodeRes.class);
			ByteArrayInputStream in = new ByteArrayInputStream(
					content.getBytes("utf-8"));
			res = (XmlCmcc49youVerifycodeRes) xmap.load(in);
			sdkCmcc49youVerify.setConfirmResultcode(res.getResultCode());
			sdkCmcc49youVerify.setConfirmResultmsg(res.getResultMsg());
			return res;
		} catch (Exception e) {
			logger.error("the wnmms request error is :" + e, e);
			return null;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
	}

	/**
	 * 联通掌云中音通道
	 * @param unikey
	 * @param phone
	 * @param validcode
	 * @return
	 */
	private String confirmPayCuccZhangyunZy(String unikey,String phone,String validcode){
		HttpClient httpclient = new DefaultHttpClient();
		String result = "";
		try {
			String sendUrl = Cfg.cfg.getString("sdk.cucczhangyunzy.confirmurl");
			StringBuffer query = new StringBuffer(sendUrl);
			// pid使用同一个
			String pid = Cfg.cfg.getString("sdk.zmzf.pid");
			query.append("pid=").append(pid);
			query.append("&unikey=").append(unikey);
			query.append("&phone=").append(phone);
			query.append("&validcode=").append(validcode);
			HttpGet httpget = new HttpGet(query.toString());
			logger.debug("the cucczhangyunzy confirm url = " + query.toString());
			logger.info("the cucczhangyunzy confirm url = " + query.toString());
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity, "UTF-8");
			logger.debug("the cucczhangyunzy response of confirm url , the content =" + content);
//			logger.info("the cucczhangyunzy response of confirm url , the content =" + content);
			result = content;
		} catch (Exception e) {
			logger.error("the cucczhangyunzy confirm request error =" + e, e);
			return null;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return result;
	}

	/**
	 * 联通易简沃音乐验证码通道
	 * @param orderId
	 * @param sequence
	 * @param money
	 * @param imsi
	 * @param imei
	 * @param ipAddr
	 * @param VCode
	 * @param sendStatus
	 * @return
	 */
	private JsonCuccYijianWomusicRes confirmPayCuccYijianWomusic(String orderId
			,String sequence,String money,String imsi,String imei
			,String ipAddr,String VCode,String sendStatus){
		HttpClient httpclient = new DefaultHttpClient();
		JsonCuccYijianWomusicRes jsonCuccYijianWomusicRes = new JsonCuccYijianWomusicRes();
		try {
			String sendUrl = Cfg.cfg.getString("sdk.cuccyijian.url");
			logger.debug("the cuccyijianwomusic payconfirm send api = " + sendUrl);
			// logger.info("the cuccyijianwomusic send api = " + sendUrl);
			HttpPost post = new HttpPost(sendUrl);
			JsonCuccYijianWomusicSyncOrder jsonCuccYijianWomusicSyncOrder = new JsonCuccYijianWomusicSyncOrder();
			String appId = Cfg.cfg.getString("sdk.cuccyijianwomusic.appId");
			jsonCuccYijianWomusicSyncOrder.setAppId(appId);
			jsonCuccYijianWomusicSyncOrder.setOrderId(orderId);
			jsonCuccYijianWomusicSyncOrder.setSequence(sequence);
			jsonCuccYijianWomusicSyncOrder.setMoney(money);
			jsonCuccYijianWomusicSyncOrder.setImsi(imsi);
			jsonCuccYijianWomusicSyncOrder.setImei(imei);
			jsonCuccYijianWomusicSyncOrder.setIpAddr(ipAddr);
			jsonCuccYijianWomusicSyncOrder.setVCode(VCode);
			jsonCuccYijianWomusicSyncOrder.setSendStatus(sendStatus);
			JsonCuccYijianWomusicReqParam jsonCuccYijianWomusicReqParam=new JsonCuccYijianWomusicReqParam();
			String version = Cfg.cfg.getString("sdk.cuccyijian.version");
			jsonCuccYijianWomusicReqParam.setVersion(version);
			String operation = Cfg.cfg.getString("sdk.cuccyijianwomusic.confirm.operation");
			jsonCuccYijianWomusicReqParam.setOperation(operation);
			jsonCuccYijianWomusicReqParam.setSyncOrder(jsonCuccYijianWomusicSyncOrder);
			String jsonBody = mapper.writeValueAsString(jsonCuccYijianWomusicReqParam);
			logger.debug("the cuccyijianwomusic payconfirm send jsonBody = " + jsonBody);
			logger.info("the cuccyijianwomusic payconfirm send jsonBody = " + jsonBody);
			post.setEntity(new ByteArrayEntity(jsonBody.getBytes()));
			HttpResponse response = httpclient.execute(post);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity, "UTF-8");
			logger.debug("the cuccyijianwomusic payconfirm response =" + content);
			logger.info("the cuccyijianwomusic payconfirm response =" + content);
			jsonCuccYijianWomusicRes = mapper.readValue(content,
					JsonCuccYijianWomusicRes.class);
			logger.debug("the cuccyijianwomusic payconfirm jsonCuccYijianRes ="
					+ jsonCuccYijianWomusicRes.toString());
//			logger.debug("the cuccyijianwomusic payconfirm jsonCuccYijianRes ="
//					+ jsonCuccYijianWomusicRes.toString());
		} catch (Exception e) {
			logger.error("the cuccyijianwomusic request error =" + e, e);
			return null;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return jsonCuccYijianWomusicRes;
	}

	/**
	 * (验证码模式)移动永正电子提交验证码
	 * @param outTradeNo
	 * @param verifycode
	 * @return
	 */
	private JsonCmccYongzhengReqOrder confirmPayCmccYongzheng(String outTradeNo,String verifycode){
		HttpClient httpclient = new DefaultHttpClient();
		JsonCmccYongzhengReqOrder res = new JsonCmccYongzhengReqOrder();
		try {
			String sendUrl = Cfg.cfg.getString("sdk.cmccyongzheng.confirmurl");
			StringBuffer query = new StringBuffer(sendUrl);
			query.append("orderNo=").append(outTradeNo);
			query.append("&code=").append(verifycode);
			HttpGet httpget = new HttpGet(query.toString());
			logger.debug("the cmccyongzheng confirm send url = " + query.toString());
//			logger.info("the cmccyongzheng confirm send url = " + query.toString());
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity, "UTF-8");
			logger.debug("the cmccyongzheng confirm send response content - content = "
					+ content);
			logger.info("the cmccyongzheng confirm send response content - content = "
					+ content);
			res = mapper.readValue(content, JsonCmccYongzhengReqOrder.class);
		} catch (Exception e) {
			logger.error("the cmccyongzheng confirm request error = " + e, e);
			return null;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return res;
	}
}