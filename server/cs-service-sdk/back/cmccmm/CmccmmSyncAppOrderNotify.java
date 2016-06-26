package com.cheyooh.service.sdk.action.cmccmm;

import java.net.URLDecoder;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Cmd;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.ResultXJContent;
import com.cheyooh.service.sdk.action.notify.AbstractNotifyService;
import com.cheyooh.service.sdk.db.dao.SdkGameEmbedsdkMapper;
import com.cheyooh.service.sdk.db.entity.SdkGameEmbedsdk;
import com.cheyooh.service.sdk.db.entity.SdkGameEmbedsdkExample;
import com.cheyooh.service.sdk.tools.HmacSHA1Encryption;


public class CmccmmSyncAppOrderNotify extends AbstractNotifyService<Cmd> {
	private static final String payChannelCode = "sdkxiaomipay";
	private static final String RESPONSE_BODY = "{\"errcode\":\"%d\"}";
//	private String encryptKey = "UOxuzLoJij7HHQjP3TFyrQ==";
	private String encryptKey="";
	private int gameid = 0;
	// 0表示异常
	private int errcode = 0;

	@Override
	protected Result verify(Cmd cmd) {
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
	protected Result execute(Cmd cmd) {
		DAL dal = DALFactory.createDAL();
		try {

			HttpServletRequest httpServletRequest = cmd.getServiceContext()
					.getRequest();
			Map<String, String> map = new HashMap<String, String>();
			Map<String, String> xiaomiMap = new HashMap<String, String>();
			Enumeration<String> paramNames = httpServletRequest
					.getParameterNames();
			// 获取所有的参数名
			while (paramNames.hasMoreElements()) {
				String name = paramNames.nextElement();
				// 得到参数名
				String value = httpServletRequest.getParameter(name);
				// 通过参数名获取对应的值
				logger.debug("小米回调信息获取的参数值: "
						+ MessageFormat.format("{0}={1}", name, value));
				map.put(name, value);
				if ("m".equals(name)) {

				} else if ("signature".equals(name)) {

				} else {
					@SuppressWarnings("deprecation")
					String decodeValue = URLDecoder.decode(value);
					xiaomiMap.put(name, decodeValue);
				}
			}

			String appId = map.get("appId");
			if (StringUtils.isEmpty(appId)) {
				appId = "";
			}

			String cpOrderId = map.get("cpOrderId");
			if (StringUtils.isEmpty(cpOrderId)) {
				cpOrderId = "";
			}

			String cpUserInfo = map.get("cpUserInfo");
			if (StringUtils.isEmpty(cpUserInfo)) {
				cpUserInfo = "";
			}

			String uid = map.get("uid");
			if (StringUtils.isEmpty(uid)) {
				uid = "";
			}

			String orderId = map.get("orderId");
			if (StringUtils.isEmpty(orderId)) {
				orderId = "";
			}

			String orderStatus = map.get("orderStatus");
			if (StringUtils.isEmpty(orderStatus)) {
				orderStatus = "";
			}

			String payFee = map.get("payFee");
			if (StringUtils.isEmpty(payFee)) {
				payFee = "";
			}

			String productCode = map.get("productCode");
			if (StringUtils.isEmpty(productCode)) {
				productCode = "";
			}

			String productName = map.get("productName");
			if (StringUtils.isEmpty(productName)) {
				productName = "";
			}

			String productCount = map.get("productCount");
			if (StringUtils.isEmpty(productCount)) {
				productCount = "";
			}

			String payTime = map.get("payTime");
			if (StringUtils.isEmpty(payTime)) {
				payTime = "";
			}

			String orderConsumeType = map.get("orderConsumeType");
			if (StringUtils.isEmpty(orderConsumeType)) {
				orderConsumeType = "";
			}

			String partnerGiftConsume = map.get("partnerGiftConsume");
			if (StringUtils.isEmpty(partnerGiftConsume)) {
				partnerGiftConsume = "";
			}

			String signature = map.get("signature");
			if (StringUtils.isEmpty(signature)) {
				signature = "";
			}
			
			SdkGameEmbedsdkMapper sdkGameEmbedsdkMapper = dal
					.getMapper(SdkGameEmbedsdkMapper.class);
			SdkGameEmbedsdkExample sdkGameEmbedsdkExample = new SdkGameEmbedsdkExample();
			sdkGameEmbedsdkExample.createCriteria()
					.andPayChannelCodeEqualTo(payChannelCode)
					.andAppidEqualTo(appId);
			SdkGameEmbedsdk sdkGameEmbedsdk = sdkGameEmbedsdkMapper
					.selectOne(sdkGameEmbedsdkExample);
			if (sdkGameEmbedsdk != null) { 
				encryptKey = sdkGameEmbedsdk.getAppsecret();
				gameid = sdkGameEmbedsdk.getGameId();
			}

			String signature1 = getSignature(xiaomiMap);
			if (signature.equals(signature1)) {
				// 验证成功
					errcode=200;
			} else {
				// 验证失败
				errcode=1525;
			}
			dal.commit();
			return response(errcode);
		} catch (Exception e) {
			logger.error("the xiaomi pay notify error!", e);
			return response(errcode);
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

	private Result response(int status) {
		String res = String.format(RESPONSE_BODY, status);
		return new Result(new ResultXJContent(res, res));
	}

	private String getSignature(Map<String, String> map) {
		String result = "";
		try {
			SortedMap<String, String> sortedMap = new TreeMap<String, String>();
			sortedMap.putAll(map);
			String s1 = "";
			int i = 0;
			int count = map.size();
			for (Map.Entry<String, String> me : sortedMap.entrySet()) {
				s1 = s1 + me.getKey() + "=" + me.getValue();
				i++;
				if (i < count) {
					s1 = s1 + "&";
				}
			}
			logger.debug("the xiaomi pay notify plaintext is :" + s1);
			result = HmacSHA1Encryption.HmacSHA1Encrypt(s1, encryptKey);
			return result;
		} catch (Exception e) {
			logger.error("the xiaomi pay notify getSignature error =" + e);
			return "";
		}
	}

	/**
	 * 测试方法
	 * 
	 * @param appId
	 * @param cpOrderId
	 * @param cpUserInfo
	 * @param uid
	 * @param orderId
	 * @param orderStatus
	 * @param payFee
	 * @param productCode
	 * @param productName
	 * @param productCount
	 * @param payTime
	 * @param orderConsumeType
	 * @param partnerGiftConsume
	 * @return
	 */
	private String getContent(Map<String, String> map) {
		HttpClient httpclient = new DefaultHttpClient();
		String result = "";
		try {
			String sendUrl = "http://dev.leyogame.cn/api/m/XiaomiSDKNotify?";
			StringBuffer query = new StringBuffer(sendUrl);
			int i = 0;
			for (String key : map.keySet()) {
				if (i != 0) {
					query.append("&");
				}
				switch (key) {
				case "appId": {
					query.append("appId=").append(map.get(key));
					break;
				}
				case "cpOrderId": {
					query.append("cpOrderId=").append(map.get(key));
					break;
				}
				case "cpUserInfo": {
					query.append("cpUserInfo=").append(map.get(key));
					break;
				}
				case "uid": {
					query.append("uid=").append(map.get(key));
					break;
				}
				case "orderId": {
					query.append("orderId=").append(map.get(key));
					break;
				}
				case "orderStatus": {
					query.append("orderStatus=").append(map.get(key));
					break;
				}
				case "payFee": {
					query.append("payFee=").append(map.get(key));
					break;
				}
				case "productCode": {
					query.append("productCode=").append(map.get(key));
					break;
				}
				case "productName": {
					query.append("productName=").append(map.get(key));
					break;
				}
				case "productCount": {
					query.append("productCount=").append(map.get(key));
					break;
				}
				case "payTime": {
					query.append("payTime=").append(map.get(key));
					break;
				}
				case "orderConsumeType": {
					query.append("orderConsumeType=").append(map.get(key));
					break;
				}
				case "partnerGiftConsume": {
					query.append("partnerGiftConsume=").append(map.get(key));
					break;
				}
				case "signature": {
					query.append("signature=").append(map.get(key));
					break;
				}
				default:
					break;
				}
				i++;
			}
			HttpGet httpget = new HttpGet(query.toString());
			logger.info("the sdkxiaomipay send url =" + query.toString());
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity, "UTF-8");
			logger.info("the sdkxiaomipay send response content - content = "
					+ content);
			result = content;
			return result;
		} catch (Exception e) {
			logger.error("the sdkxiaomipay request error is :" + e, e);
			return "";
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
	}

	/**
	 * 日期转换成字符串
	 * 
	 * @param date
	 * @return str
	 */
	public static String DateToStr(Date date) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = format.format(date);
		return str;
	}

	/**
	 * 字符串转换成日期
	 * 
	 * @param str
	 * @return date
	 */
	public static Date StrToDate(String str) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static void main(String args[]) throws Exception {
//		XiaomiSDKNotify x = new XiaomiSDKNotify();
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("appId", "2882303761517423951");
//		map.put("cpOrderId", "92596a4b-d879-4f55-a0d7-584c46dc9b2e");
//		map.put("orderId", "21145213703289635713");
//		map.put("orderStatus", "TRADE_SUCCESS");
//		map.put("payFee", "100");
//		map.put("payTime", URLEncoder.encode("2016-01-07 11:27:37"));
//		map.put("productCode", "D1013");
//		map.put("productCount", "1");
//		map.put("productName", URLEncoder.encode("超值礼包"));
//		map.put("uid", "105089788");
//		map.put("signature", "adf1f72cdd8fbc398cc65c5cd1391d89c9614e02");
//		String result = x.getContent(map);
//		System.out.println(result);
	}
}
