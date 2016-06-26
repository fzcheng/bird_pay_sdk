package com.cheyooh.service.sdk.action.cmccmm;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.nuxeo.common.xmap.XMap;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Cmd;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.ResultXJContent;
import com.cheyooh.service.sdk.action.notify.AbstractNotifyService;
import com.cheyooh.service.sdk.idata.cmccmm.XmlCmccmmTestOrderRequest;
import com.cheyooh.service.sdk.idata.cmccmm.XmlCmccmmTestOrderResponse;
import com.cheyooh.service.sdk.tools.GenerateTool;


public class CmccmmTestOrder extends AbstractNotifyService<Cmd> {
	private static final String url = "http://211.139.191.168:48081/oms/TestOrder";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cheyooh.service.framework.basic.Service#verify(com.cheyooh.service
	 * .framework.idata.Cmd)
	 */
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
		String result = "";
		try {
//			HttpServletRequest httpServletRequest = cmd.getServiceContext()
//					.getRequest();
//			Map<String, String> allParamMap = new HashMap<String, String>();
//			// Map<String, String> postMap = new HashMap<String, String>();
//			Enumeration<String> paramNames = httpServletRequest
//					.getParameterNames();
//			// 获取所有的参数名
//			while (paramNames.hasMoreElements()) {
//				String name = paramNames.nextElement();// 得到参数名
//				String value = httpServletRequest.getParameter(name);// 通过参数名获取对应的值
//				logger.debug("cmccmm创建应用信息获取的参数值: "
//						+ MessageFormat.format("{0}={1}", name, value));
//				allParamMap.put(name, value);
//			}
//
//			String TransactionId = GenerateTool.createOrderNo();
//			allParamMap.put("TransactionId", TransactionId);
//
//			String APCode = allParamMap.get("APCode");
//			if (StringUtils.isEmpty(APCode)) {
//				APCode = "";
//			}
//
//			String Status = allParamMap.get("Status");
//			if (StringUtils.isEmpty(Status)) {
//				Status = "";
//			}
//
//			String APPID = allParamMap.get("APPID");
//			if (StringUtils.isEmpty(APPID)) {
//				APPID = "";
//			}
//
//			String PayCode = allParamMap.get("PayCode");
//			if (StringUtils.isEmpty(PayCode)) {
//				PayCode = "";
//			}
			InputStream inputStream = cmd.getServiceContext().getRequest()
					.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream, "UTF-8"));
			StringBuffer xmlBuff = new StringBuffer();
			String line;
			while ((line = reader.readLine()) != null) {
				xmlBuff.append(line);
			}
			String xmlBody = xmlBuff.toString();
			logger.info("the CmccmmTestOrder xml is :" + xmlBody);

			XmlCmccmmTestOrderResponse xmlCmccmmTestOrderResponse = getContent(xmlBody,url);
			// 操作数据库，入库操作

			dal.commit();
			XMap xmap = new XMap();
			xmap.register(XmlCmccmmTestOrderResponse.class);
			List<String> filters = new ArrayList<String>();
			String responseXmlBody = xmap.asXmlString(xmlCmccmmTestOrderResponse,
					"UTF-8", filters);
			responseXmlBody=responseXmlBody.replace("&lt;![CDATA[", "<![CDATA[");
			responseXmlBody=responseXmlBody.replace("]]&gt;", "]]>");
			result = responseXmlBody;
			return sendResponse(result);
		} catch (Exception e) {
			logger.error("the CmccmmTestOrder pay , sms error!", e);
			return sendResponse("fail");
		} finally {
			dal.close();
		}

	}

	private Result sendResponse(String result) {
		return new Result(new ResultXJContent(result, result));
	}

	private XmlCmccmmTestOrderResponse getContent(String xmlBody, String url) {
		HttpClient httpclient = new DefaultHttpClient();
		XmlCmccmmTestOrderResponse xmlCmccmmTestOrderResponse = new XmlCmccmmTestOrderResponse();
		try {
			logger.debug("the CmccmmTestOrder send api =" + url);
			HttpPost post = new HttpPost(url);

			logger.debug("the CmccmmTestOrder send xmlBody ="
					+ xmlBody);
			post.setEntity(new ByteArrayEntity(xmlBody.getBytes()));
			HttpResponse response = httpclient.execute(post);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity, "UTF-8");
			logger.debug("the CmccmmTestOrder response =" + content);

			XMap xmapresponse = new XMap();
			xmapresponse.register(XmlCmccmmTestOrderResponse.class);
			ByteArrayInputStream in = new ByteArrayInputStream(
					content.getBytes("utf-8"));
			xmlCmccmmTestOrderResponse = (XmlCmccmmTestOrderResponse) xmapresponse.load(in);
		} catch (Exception e) {
			logger.error("the CmccmmTestOrder request error is :" + e);
			return null;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return xmlCmccmmTestOrderResponse;
	}
	
	/**
	 * 访问我们的服务器测试方法接口
	 * 
	 * @param xmlCmccmmAppRequest
	 * @return
	 */
	private String test(String xmlBody) {
		String result = "";
		HttpClient httpclient = new DefaultHttpClient();
		try {
			String leyoUrl = "http://dev.leyogame.cn/api/m/CmccmmTestOrder";
			logger.debug("leyo the CmccmmTestOrder send api = " + leyoUrl);
			HttpPost post = new HttpPost(leyoUrl);

			post.setEntity(new ByteArrayEntity(xmlBody.getBytes()));
			HttpResponse response = httpclient.execute(post);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity, "UTF-8");
			logger.debug("leyo the CmccmmTestOrder response = " + content);

			result = content;
		} catch (Exception e) {
			logger.error("leyo the CmccmmTestOrder request error is :" + e);
			return null;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return result;
	}

	public static void main(String[] args) {
		
//		<?xml version="1.0" encoding="UTF-8" standalone="no"?>
//		<TestOrderRequest>
//		<TransactionId>1605061657430101</TransactionId>
//		<APCode>444670</APCode>
//		<Status>01</Status>
//		<APPID>300005607603</APPID>
//		<PayCode>30000560760310</PayCode>
//		</TestOrderRequest>
		
//		<?xml version="1.0" encoding="UTF-8"?>
//		<TestOrderResponse>
//		  <TransactionId>1605061709080101</TransactionId>
//		  <RspCode>0</RspCode>
//		  <RspDesc>QueryTestOrderReq success companyCode=444670,appId=300005607603,payCode=30000560760310</RspDesc>
//		  <TestOrderInfos>
//		    <TestOrderInfo>
//		      <PayCode>30000560760310</PayCode>
//		      <PayCodeName><![CDATA[测试应用-20金币]]></PayCodeName>
//		      <PayCodeType>购买类-可重复购买</PayCodeType>
//		      <Mobile>13425134343</Mobile>
//		      <TestOrderDate>2016-05-06 15:59:24</TestOrderDate>
//		      <TestOrderResult><![CDATA[通过]]></TestOrderResult>
//		      <TestOrderSyncStatus><![CDATA[同步成功]]></TestOrderSyncStatus>
//		    </TestOrderInfo>
//		  </TestOrderInfos>
//		</TestOrderResponse>
		      
		try {
			String TransactionId = GenerateTool.createOrderNo();
			String APCode = "444670";
			String APPID = "300005607603";
			
			XmlCmccmmTestOrderRequest xmlCmccmmTestOrderRequest = new XmlCmccmmTestOrderRequest();
			xmlCmccmmTestOrderRequest.setTransactionId(TransactionId);
			xmlCmccmmTestOrderRequest.setAPCode(APCode);
			xmlCmccmmTestOrderRequest.setStatus("01");
			xmlCmccmmTestOrderRequest.setAPPID(APPID);
			xmlCmccmmTestOrderRequest.setPayCode("30000560760310");
			
			XMap xmap = new XMap();
			xmap.register(XmlCmccmmTestOrderRequest.class);
			List<String> filters = new ArrayList<String>();
			String xmlBody = xmap.asXmlString(xmlCmccmmTestOrderRequest, "UTF-8",
					filters,true);
			xmlBody=xmlBody.replace("&lt;![CDATA[", "<![CDATA[");
			xmlBody=xmlBody.replace("]]&gt;", "]]>");
			
			CmccmmTestOrder cmccmmTestOrder = new CmccmmTestOrder();
			String result = cmccmmTestOrder.test(xmlBody);
			
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
}
