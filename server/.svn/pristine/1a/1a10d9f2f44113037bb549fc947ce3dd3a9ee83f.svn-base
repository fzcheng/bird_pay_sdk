package com.cheyooh.service.sdk.action.external;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
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
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.ResultXJContent;
import com.cheyooh.service.sdk.action.notify.AbstractNotifyService;
import com.cheyooh.service.sdk.db.dao.SdkOrderMapper;
import com.cheyooh.service.sdk.db.dao.SdkOrderMmdoMapper;
import com.cheyooh.service.sdk.db.entity.SdkOrder;
import com.cheyooh.service.sdk.db.entity.SdkOrderMmdo;
import com.cheyooh.service.sdk.idata.CmdConsumeCmccZwjfExt;
import com.cheyooh.service.sdk.idata.gameserver.JsonCmccZwjfConsumeVcodeReq;
import com.cheyooh.service.sdk.idata.gameserver.JsonCmccZwjfValidateVcodeReq;
import com.cheyooh.service.sdk.idata.gameserver.JsonCmccZwjfVirtualcodeRes;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConsumeCmccZwjfVirtualCode extends
		AbstractNotifyService<CmdConsumeCmccZwjfExt> {
	private String secretKey = "ee970c86054cb39e203024e7e74934e178d75142a9e8bf245e83ae00cef836330e022eb7ee4c2283ff2d294c6f493321c7c3bc11c7f5ab03180280b7396b730a";
	private String qudaoshangcode = "1006";
	private static final ObjectMapper mapper = new ObjectMapper();
	private final String XML_SMS = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
			+ "<leyo>\n"
			+ "   <orderNo>%s</orderNo>\n"
			+ "   <resultCode>%s</resultCode>\n"
			+ "   <resultMsg>%s</resultMsg>\n" 
			+ "   <dealid>%s</dealid>\n"
			+ "</leyo>";

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cheyooh.service.framework.basic.Service#verify(com.cheyooh.service
	 * .framework.idata.Cmd)
	 */
	@Override
	protected Result verify(CmdConsumeCmccZwjfExt cmd) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cheyooh.service.framework.basic.Service#execute(com.cheyooh.service
	 * .framework.idata.Cmd)
	 */
	@Override
	protected Result execute(CmdConsumeCmccZwjfExt cmd) {
		String orderNo = cmd.getOrderNo();
		String virtualcode = cmd.getVirtualCode();
		String dealid="";
		logger.info("the ConsumeCmccZwjfVirtualCode , the orderNo =" + orderNo
				+ ", the virtualcode =" + virtualcode);

		DAL dal = DALFactory.createDAL();
		Result result = null;
		String resultCode = "fail";
		String resultMsg = "";
		try {
			SdkOrderMapper sdkOrderMapper = dal.getMapper(SdkOrderMapper.class);
			SdkOrder sdkOrder = sdkOrderMapper.selectByPrimaryKey(orderNo);
			if (sdkOrder != null) {
				SdkOrderMmdoMapper sdkOrderMmdoMapper = dal
						.getMapper(SdkOrderMmdoMapper.class);
				SdkOrderMmdo sdkOrderMmdo = sdkOrderMmdoMapper
						.selectByPrimaryKey(sdkOrder.getPayId());

				int orderStatus = 3;
				String orderStatusDetail = sdkOrder.getStatusDetail();
				String originalcode = sdkOrder.getOriginalcode();

				if (sdkOrderMmdo != null) {
					//防止重复提交成功订单
					if(sdkOrder.getStatus()==1
							&&StringUtils.isNotBlank(sdkOrderMmdo.getTradeid())
							&&StringUtils.isNotEmpty(sdkOrderMmdo.getTradeid())
							&&StringUtils.isNotBlank(sdkOrderMmdo.getRespSendContent())
							&&StringUtils.isNotEmpty(sdkOrderMmdo.getRespSendContent())
							){
						resultCode = "success";
						resultMsg = "";
						dealid=sdkOrderMmdo.getTradeid();
						result = sendResponse(orderNo, resultCode, resultMsg,dealid);
						return result;
					}
					
					JsonCmccZwjfValidateVcodeReq jsonCmccZwjfValidateVcodeReq = new JsonCmccZwjfValidateVcodeReq();
					jsonCmccZwjfValidateVcodeReq.setVcode(virtualcode);
					Date validateVcodedate = new Date();
					String validatetime = DateToStr(validateVcodedate);
					jsonCmccZwjfValidateVcodeReq.setValidatetime(validatetime);
					String jsonOrderbeforeString = mapper
							.writeValueAsString(jsonCmccZwjfValidateVcodeReq);
					logger.debug("the ConsumeCmccZwjfVirtualCode ,  jsonOrderbeforeString ="
							+ jsonOrderbeforeString);
					String validateVcodeReq = getReq(jsonOrderbeforeString);
					JsonCmccZwjfVirtualcodeRes jsonCmccZwjfVirtualcodeRes = validateVcode(validateVcodeReq);
					logger.debug("the ConsumeCmccZwjfVirtualCode ,jsonCmccZwjfVirtualcodeRes is:"+jsonCmccZwjfVirtualcodeRes.toString());

					String mobilephone = sdkOrderMmdo.getMobilephone();
					
					if ("0".equals(jsonCmccZwjfVirtualcodeRes.getCode())) {
						JsonCmccZwjfConsumeVcodeReq jsonCmccZwjfConsumeVcodeReq = new JsonCmccZwjfConsumeVcodeReq();
						jsonCmccZwjfConsumeVcodeReq.setVcode(virtualcode);
						Date consumedate = new Date();
						String consumetime = DateToStr(consumedate);
						jsonCmccZwjfConsumeVcodeReq.setConsumetime(consumetime);
						jsonCmccZwjfConsumeVcodeReq.setMerchantcode("");
						String jsonOrderbeforeString2 = mapper
								.writeValueAsString(jsonCmccZwjfConsumeVcodeReq);
						String consumecodeReq = getReq(jsonOrderbeforeString2);
						JsonCmccZwjfVirtualcodeRes jsonCmccZwjfVirtualcodeRes2 = consumeVcode(consumecodeReq);
						logger.debug("the ConsumeCmccZwjfVirtualCode ,jsonCmccZwjfVirtualcodeRes2 is:"+jsonCmccZwjfVirtualcodeRes2.toString());
						
						if ("0".equals(jsonCmccZwjfVirtualcodeRes2.getCode())) {
							orderStatus = 1;
							resultCode = "success";
							resultMsg = "";
							originalcode=jsonCmccZwjfVirtualcodeRes2.getCode();
						} else {
							orderStatus = 3;
							orderStatusDetail = jsonCmccZwjfVirtualcodeRes2
									.getMsg();
							originalcode=jsonCmccZwjfVirtualcodeRes2.getCode();
							resultCode = "fail";
							resultMsg = orderStatusDetail;
						}
						mobilephone = jsonCmccZwjfVirtualcodeRes.getPhone();
						dealid=jsonCmccZwjfVirtualcodeRes.getDealid();
						sdkOrderMmdo.setRespSendContent(virtualcode);
						sdkOrderMmdo.setRespStatus(1);
						sdkOrderMmdo.setMobilephone(mobilephone);
						sdkOrderMmdo.setTradeid(dealid);
					} else {
						orderStatusDetail = jsonCmccZwjfVirtualcodeRes.getMsg();
						originalcode=jsonCmccZwjfVirtualcodeRes.getCode();
						resultCode = "fail";
						resultMsg = orderStatusDetail;
						sdkOrderMmdo.setRespStatus(0);
					}
					sdkOrderMmdoMapper
							.updateByPrimaryKeySelective(sdkOrderMmdo);
				} else {
					resultCode = "fail";
					resultMsg = "sdkOrderMmdo无此订单号";
				}

				sdkOrder.setStatus(orderStatus);
				sdkOrder.setStatusDetail(orderStatusDetail);
				sdkOrder.setOriginalcode(originalcode);
				sdkOrderMapper.updateByPrimaryKeySelective(sdkOrder);
			} else {
				resultCode = "fail";
				resultMsg = "sdkOrder无此订单号";
			}

			dal.commit();
		} catch (Exception e) {
			logger.error("the ConsumeCmccZwjfVirtualCode , error =" + e);
			resultCode = "fail";
			resultMsg = "程序运行抛出异常";
		} finally {
			dal.close();
		}
		result = sendResponse(orderNo, resultCode, resultMsg,dealid);
		return result;

	}

	private Result sendResponse(String orderNo, String resultcode,
			String resultmsg,String dealid) {
		String xml = String.format(XML_SMS, orderNo, resultcode, resultmsg,dealid);
		return new Result(new ResultXJContent(xml, xml));
	}

	private JsonCmccZwjfVirtualcodeRes validateVcode(String req) {
		HttpClient httpclient = new DefaultHttpClient();
		JsonCmccZwjfVirtualcodeRes jsonCmccZwjfVirtualcodeRes = new JsonCmccZwjfVirtualcodeRes();
		try {
			//卓望正式url
			String sendUrl = "http://jfj.i139.cn/aspire_itfc/validateVcode";
			//卓望测试url
//			String sendUrl = "http://cacti.yunbobao.cn/validateVcode";
			logger.debug("the ConsumeCmccZwjfVirtualCode validateVcode send api = "
					+ sendUrl);
			HttpPost httpost = new HttpPost(sendUrl);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("req", req));
			httpost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			HttpResponse response = httpclient.execute(httpost);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity, "UTF-8");
			jsonCmccZwjfVirtualcodeRes = mapper.readValue(content,
					JsonCmccZwjfVirtualcodeRes.class);
			logger.debug("the ConsumeCmccZwjfVirtualCode validateVcode ,"
					+ jsonCmccZwjfVirtualcodeRes.toString());
		} catch (Exception e) {
			logger.error(
					"the ConsumeCmccZwjfVirtualCode validateVcode request error ="
							+ e, e);
			return null;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return jsonCmccZwjfVirtualcodeRes;
	}

	/**
	 * 日期转换成字符串
	 * 
	 * @param date
	 * @return str
	 */
	public static String DateToStr(Date date) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
		String str = format.format(date);
		return str;
	}

	/**
	 * 对字符串按升序排序
	 * 
	 * @param date
	 * @return str
	 */
	public static String orderStrWithASC(String param) {
		String result = "";
		if (StringUtils.isNotEmpty(param) && StringUtils.isNotBlank(param)) {
			char[] c = param.toCharArray();
			Arrays.sort(c);
			result = String.valueOf(c);
		} else {
			return null;
		}
		return result;
	}

	public String getReq(String jsonString) {
		String result = "";
		String jsonOrderAfterString = orderStrWithASC(jsonString);
		String sign = DigestUtils.md5Hex(secretKey + jsonOrderAfterString)
				.toLowerCase();
		String beforBase64String = sign + jsonString + qudaoshangcode;
		result = Base64.encodeBase64String(beforBase64String.getBytes());
		return result;
	}

	private JsonCmccZwjfVirtualcodeRes consumeVcode(String req) {
		HttpClient httpclient = new DefaultHttpClient();
		JsonCmccZwjfVirtualcodeRes jsonCmccZwjfVirtualcodeRes = new JsonCmccZwjfVirtualcodeRes();
		try {
			//卓望正式url
			String sendUrl = "http://jfj.i139.cn/aspire_itfc/consumeVCode";
			//卓望测试url
//			String sendUrl = "http://cacti.yunbobao.cn/consumeVCode";
			logger.debug("the ConsumeCmccZwjfVirtualCode consumeVcode send api = "
					+ sendUrl);
			HttpPost httpost = new HttpPost(sendUrl);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("req", req));
			httpost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			HttpResponse response = httpclient.execute(httpost);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity, "UTF-8");
			jsonCmccZwjfVirtualcodeRes = mapper.readValue(content,
					JsonCmccZwjfVirtualcodeRes.class);
			logger.debug("the ConsumeCmccZwjfVirtualCode consumeVcode ,"+jsonCmccZwjfVirtualcodeRes.toString());
		} catch (Exception e) {
			logger.error(
					"the ConsumeCmccZwjfVirtualCode consumeVcode request error ="
							+ e, e);
			return null;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return jsonCmccZwjfVirtualcodeRes;
	}

	/**
	 * 测试
	 * 
	 * @param map
	 * @return
	 */
	private String consumeCmccZwjfVirtualCode(Map<String, String> map) {
		String result = "";
		HttpClient httpclient = new DefaultHttpClient();
		try {
			String url = "http://dev.leyogame.cn/api/m/ConsumeCmccZwjfVirtualCode?";
			StringBuffer query = new StringBuffer(url);
			query.append("orderNo=").append(map.get("orderNo"));
			query.append("&virtualCode=").append(map.get("virtualCode"));
			HttpGet httpGet = new HttpGet(query.toString());
			HttpResponse response = httpclient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity, "UTF-8");
			logger.debug("the ConsumeCmccZwjfVirtualCode consume ,content = "
					+ content);
			result = content;
		} catch (Exception e) {
			logger.error("the ConsumeCmccZwjfVirtualCode consume ,error = " + e);
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return result;
	}

	public static void main(String[] args) {
		ConsumeCmccZwjfVirtualCode t = new ConsumeCmccZwjfVirtualCode();
		Map<String, String> map = new HashMap<String, String>();
		map.put("orderNo", "1603251519290101");
		map.put("virtualCode", "5874661534");
		String result = t.consumeCmccZwjfVirtualCode(map);
		System.out.println(result);
	}
}
