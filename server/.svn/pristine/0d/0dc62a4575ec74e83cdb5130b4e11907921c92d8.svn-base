package com.cheyooh.service.sdk.action.cmccmm;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
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
import com.cheyooh.service.sdk.db.dao.SdkCmccmmPaycodestatusMapper;
import com.cheyooh.service.sdk.db.entity.SdkCmccmmPaycodestatus;
import com.cheyooh.service.sdk.idata.cmccmm.XmlCmccmmPayCodeStatusReqInfoSchema;
import com.cheyooh.service.sdk.idata.cmccmm.XmlCmccmmPayCodeStatusReqInfos;
import com.cheyooh.service.sdk.idata.cmccmm.XmlCmccmmPayCodeStatusResponse;
import com.cheyooh.service.sdk.idata.cmccmm.XmlCmccmmPaycodeStatusRequest;
import com.cheyooh.service.sdk.tools.GenerateTool;


public class CmccmmPayCodeStatus extends AbstractNotifyService<Cmd> {
	private static final String url = "http://211.139.191.168:48081/oms/paycodeStatus";

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
			logger.info("the CmccmmPayCodeStatus xml is :" + xmlBody);
			
			XMap xmlCmccmmPaycodeStatusRequest = new XMap();
			xmlCmccmmPaycodeStatusRequest.register(XmlCmccmmPaycodeStatusRequest.class);
			ByteArrayInputStream in = new ByteArrayInputStream(
					xmlBody.getBytes("utf-8"));
			XmlCmccmmPaycodeStatusRequest xmlRequest = (XmlCmccmmPaycodeStatusRequest) xmlCmccmmPaycodeStatusRequest.load(in);
			
			XmlCmccmmPayCodeStatusResponse xmlCmccmmPayCodeStatusResponse = getContent(xmlBody,url);
			// 操作数据库，入库操作
			SdkCmccmmPaycodestatusMapper sdkCmccmmPaycodestatusMapper=dal.getMapper(SdkCmccmmPaycodestatusMapper.class);
			SdkCmccmmPaycodestatus sdkCmccmmPaycodestatus=new SdkCmccmmPaycodestatus();
			sdkCmccmmPaycodestatus.setTransactionidReq(xmlRequest.getTransactionId());
			sdkCmccmmPaycodestatus.setPltidReq(xmlRequest.getPltID());
			sdkCmccmmPaycodestatus.setAppidReq(xmlRequest.getAPPID());
			sdkCmccmmPaycodestatus.setPaycodeReq(xmlRequest.getPayCodeStatusReqInfos().getPayCodeStatusReqInfo().get(0).getPayCode());
			sdkCmccmmPaycodestatus.setStatusReq(xmlRequest.getPayCodeStatusReqInfos().getPayCodeStatusReqInfo().get(0).getStatus());
			sdkCmccmmPaycodestatus.setRspcodeResp(xmlCmccmmPayCodeStatusResponse.getRspCode());
			sdkCmccmmPaycodestatus.setRspdescResp(xmlCmccmmPayCodeStatusResponse.getRspDesc());
			sdkCmccmmPaycodestatus.setCreatedTime(new Date());
			sdkCmccmmPaycodestatusMapper.insertSelective(sdkCmccmmPaycodestatus);
			
			dal.commit();
			XMap xmap = new XMap();
			xmap.register(XmlCmccmmPayCodeStatusResponse.class);
			List<String> filters = new ArrayList<String>();
			String responseXmlBody = xmap.asXmlString(xmlCmccmmPayCodeStatusResponse,
					"UTF-8", filters);
			result = responseXmlBody;
			return sendResponse(result);
		} catch (Exception e) {
			logger.error("the CmccmmPayCodeStatus pay appear error is :", e);
			return sendResponse("fail");
		} finally {
			dal.close();
		}

	}

	private Result sendResponse(String result) {
		return new Result(new ResultXJContent(result, result));
	}

	private XmlCmccmmPayCodeStatusResponse getContent(String xmlBody, String url) {
		HttpClient httpclient = new DefaultHttpClient();
		XmlCmccmmPayCodeStatusResponse xmlCmccmmPayCodeStatusResponse = new XmlCmccmmPayCodeStatusResponse();
		try {
			logger.debug("the CmccmmPayCodeStatus send api =" + url);
			HttpPost post = new HttpPost(url);

			logger.debug("the CmccmmPayCodeStatus send xmlBody ="
					+ xmlBody);
			post.setEntity(new ByteArrayEntity(xmlBody.getBytes()));
			HttpResponse response = httpclient.execute(post);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity, "UTF-8");
			logger.debug("the CmccmmPayCodeStatus response =" + content);

			XMap xmapresponse = new XMap();
			xmapresponse.register(XmlCmccmmPayCodeStatusResponse.class);
			ByteArrayInputStream in = new ByteArrayInputStream(
					content.getBytes("utf-8"));
			xmlCmccmmPayCodeStatusResponse = (XmlCmccmmPayCodeStatusResponse) xmapresponse.load(in);
		} catch (Exception e) {
			logger.error("the CmccmmPayCodeStatus request error is :" + e);
			return null;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return xmlCmccmmPayCodeStatusResponse;
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
			String leyoUrl = "http://dev.leyogame.cn/api/m/CmccmmPayCodeStatus";
			logger.debug("leyo the CmccmmPayCodeStatus send api = " + leyoUrl);
			HttpPost post = new HttpPost(leyoUrl);

			post.setEntity(new ByteArrayEntity(xmlBody.getBytes()));
			HttpResponse response = httpclient.execute(post);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity, "UTF-8");
			logger.debug("leyo the CmccmmPayCodeStatus response = " + content);

			result = content;
		} catch (Exception e) {
			logger.error("leyo the CmccmmPayCodeStatus request error is :" + e);
			return null;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return result;
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
//		<?xml version="1.0" encoding="UTF-8" standalone="no"?>
//		<PaycodeStatusRequest>
//		<TransactionId>1605131753470101</TransactionId>
//		<PltID>2012</PltID>
//		<MD5sign>1132752422D283E865B77322AA40587C</MD5sign>
//		<APPID>300005607603</APPID>
//		<PayCodeStatusReqInfos>
//			<PayCodeStatusReqInfo>
//			<PayCode>30000560760314</PayCode>
//			<Status>2</Status>
//			</PayCodeStatusReqInfo>
//		</PayCodeStatusReqInfos>
//		</PaycodeStatusRequest>
		
//		<?xml version="1.0" encoding="UTF-8"?>
//		<PaycodeStatusResponse>
//		  <TransactionId>1605131753470101</TransactionId>
//		  <RspCode>0</RspCode>
//		  <RspDesc>success</RspDesc>
//		</PaycodeStatusResponse>
		
		try {
			String TransactionId = GenerateTool.createOrderNo();
			String PltID = "2012";
			String PltKey = "moli123";
			String MD5sign = DigestUtils.md5Hex(
					TransactionId + "#" + PltID + "#" + PltKey).toUpperCase();
			String APPID = "300005607603";
			
			XmlCmccmmPaycodeStatusRequest xmlCmccmmPaycodeStatusRequest=new XmlCmccmmPaycodeStatusRequest();
			xmlCmccmmPaycodeStatusRequest.setTransactionId(TransactionId);
			xmlCmccmmPaycodeStatusRequest.setPltID(PltID);
			xmlCmccmmPaycodeStatusRequest.setMD5sign(MD5sign);
			xmlCmccmmPaycodeStatusRequest.setAPPID(APPID);
			
			XmlCmccmmPayCodeStatusReqInfoSchema xmlCmccmmPayCodeStatusReqInfoSchema1=new XmlCmccmmPayCodeStatusReqInfoSchema();
			xmlCmccmmPayCodeStatusReqInfoSchema1.setPayCode("30000560760314");
			xmlCmccmmPayCodeStatusReqInfoSchema1.setStatus("2");
			
			XmlCmccmmPayCodeStatusReqInfos xmlCmccmmPayCodeStatusReqInfos=new XmlCmccmmPayCodeStatusReqInfos();
			List<XmlCmccmmPayCodeStatusReqInfoSchema> payCodeStatusReqInfo = new ArrayList<XmlCmccmmPayCodeStatusReqInfoSchema>();
			payCodeStatusReqInfo.add(xmlCmccmmPayCodeStatusReqInfoSchema1);
			xmlCmccmmPayCodeStatusReqInfos.setPayCodeStatusReqInfo(payCodeStatusReqInfo);
			xmlCmccmmPaycodeStatusRequest.setPayCodeStatusReqInfos(xmlCmccmmPayCodeStatusReqInfos);
			
			XMap xmap = new XMap();
			xmap.register(XmlCmccmmPaycodeStatusRequest.class);
			List<String> filters = new ArrayList<String>();
			String xmlBody = xmap.asXmlString(xmlCmccmmPaycodeStatusRequest, "UTF-8",
					filters,true);
			xmlBody=xmlBody.replace("&lt;![CDATA[", "<![CDATA[");
			xmlBody=xmlBody.replace("]]&gt;", "]]>");
			
			CmccmmPayCodeStatus cmccmmPayCodeStatus = new CmccmmPayCodeStatus();
			String result = cmccmmPayCodeStatus.test(xmlBody);
			
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
