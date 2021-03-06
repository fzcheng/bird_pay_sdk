package com.cheyooh.service.sdk.action.cmccmm;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
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
import com.cheyooh.service.sdk.db.dao.SdkCmccmmPaycodeMapper;
import com.cheyooh.service.sdk.db.entity.SdkCmccmmPaycode;
import com.cheyooh.service.sdk.idata.cmccmm.XmlCmccmmPayCodeReqInfoSchema;
import com.cheyooh.service.sdk.idata.cmccmm.XmlCmccmmPayCodeReqInfos;
import com.cheyooh.service.sdk.idata.cmccmm.XmlCmccmmPayCodeResponse;
import com.cheyooh.service.sdk.idata.cmccmm.XmlCmccmmPaycodeRequest;
import com.cheyooh.service.sdk.tools.GenerateTool;


public class CmccmmPaycodeCreate extends AbstractNotifyService<Cmd> {
	private static final String url = "http://211.139.191.168:48081/oms/appPayCodeApply";

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
			logger.info("the CmccmmPaycodeCreate xml is :" + xmlBody);
			
			XMap xmlCmccmmPaycodeRequest = new XMap();
			xmlCmccmmPaycodeRequest.register(XmlCmccmmPaycodeRequest.class);
			ByteArrayInputStream in = new ByteArrayInputStream(
					xmlBody.getBytes("utf-8"));
			XmlCmccmmPaycodeRequest xmlRequest = (XmlCmccmmPaycodeRequest) xmlCmccmmPaycodeRequest.load(in);
			
			XmlCmccmmPayCodeResponse xmlCmccmmPayCodeResponse = getContent(xmlBody,url);
			// 操作数据库，入库操作
			Date time=new Date();
			int count=xmlRequest.getPayCodeReqInfos().getPayCodeReqInfo().size();
			SdkCmccmmPaycodeMapper sdkCmccmmPaycodeMapper=dal.getMapper(SdkCmccmmPaycodeMapper.class);
			for(int i=0;i<count;i++){
				SdkCmccmmPaycode sdkCmccmmPaycode=new SdkCmccmmPaycode();
				sdkCmccmmPaycode.setTransactionidReq(xmlRequest.getTransactionId());
				sdkCmccmmPaycode.setPltidReq(xmlRequest.getPltID());
				sdkCmccmmPaycode.setAppidReq(xmlRequest.getAPPID());
				sdkCmccmmPaycode.setPaycodename(xmlRequest.getPayCodeReqInfos().getPayCodeReqInfo().get(i).getPayCodeName());
				sdkCmccmmPaycode.setPaycodetypeReq(xmlRequest.getPayCodeReqInfos().getPayCodeReqInfo().get(i).getPayCodeType());
				String days=xmlRequest.getPayCodeReqInfos().getPayCodeReqInfo().get(i).getDays();
				logger.debug("the CmccmmPaycodeCreate days is :"+days);
				int daysInt=0;
				if(StringUtils.isNotBlank(days)){
					daysInt=parseInteger(days);
				}
				sdkCmccmmPaycode.setDaysReq(daysInt);
				String fee=xmlRequest.getPayCodeReqInfos().getPayCodeReqInfo().get(i).getFee();
				float feeFloat=(Float.valueOf(fee))/100;
				sdkCmccmmPaycode.setFeeReq(feeFloat);
				sdkCmccmmPaycode.setAuthflagReq(xmlRequest.getPayCodeReqInfos().getPayCodeReqInfo().get(i).getAuthFlag());
				sdkCmccmmPaycode.setAuthurlReq(xmlRequest.getPayCodeReqInfos().getPayCodeReqInfo().get(i).getAuthURL());
				sdkCmccmmPaycode.setSyncsubsurlReq(xmlRequest.getPayCodeReqInfos().getPayCodeReqInfo().get(i).getSyncsubsURL());
				sdkCmccmmPaycode.setPaycodeindex(xmlRequest.getPayCodeReqInfos().getPayCodeReqInfo().get(i).getPayCodeIndex());
				sdkCmccmmPaycode.setCreatedTime(time);
				
				int c=xmlCmccmmPayCodeResponse.getPayCodeResInfos().getPayCodeResInfo().size();
				for(int j=0;j<c;j++){
					String index =xmlCmccmmPayCodeResponse.getPayCodeResInfos().getPayCodeResInfo().get(j).getPayCodeIndex();
					if(index.equals(xmlRequest.getPayCodeReqInfos().getPayCodeReqInfo().get(i).getPayCodeIndex())){
						sdkCmccmmPaycode.setRspcodeResp(xmlCmccmmPayCodeResponse.getRspCode());
						sdkCmccmmPaycode.setRspdescResp(xmlCmccmmPayCodeResponse.getRspDesc());
						sdkCmccmmPaycode.setPaycodeResp(xmlCmccmmPayCodeResponse.getPayCodeResInfos().getPayCodeResInfo().get(j).getPayCode());
					}
				}
				sdkCmccmmPaycodeMapper.insert(sdkCmccmmPaycode);
			}
			
			dal.commit();
			XMap xmap = new XMap();
			xmap.register(XmlCmccmmPayCodeResponse.class);
			List<String> filters = new ArrayList<String>();
			String responseXmlBody = xmap.asXmlString(xmlCmccmmPayCodeResponse,
					"UTF-8", filters);
			result = responseXmlBody;
			return sendResponse(result);
		} catch (Exception e) {
			logger.error("the CmccmmPaycodeCreate pay appear error is :", e);
			return sendResponse("fail");
		} finally {
			dal.close();
		}

	}

	private Result sendResponse(String result) {
		return new Result(new ResultXJContent(result, result));
	}

	private XmlCmccmmPayCodeResponse getContent(String xmlBody, String url) {
		HttpClient httpclient = new DefaultHttpClient();
		XmlCmccmmPayCodeResponse xmlCmccmmPayCodeResponse = new XmlCmccmmPayCodeResponse();
		try {
			logger.debug("the CmccmmPaycodeCreate send api = " + url);
			HttpPost post = new HttpPost(url);

			logger.debug("the CmccmmPaycodeCreate send xmlBody = "
					+ xmlBody);
			post.setEntity(new ByteArrayEntity(xmlBody.getBytes()));
			HttpResponse response = httpclient.execute(post);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity, "UTF-8");
			logger.debug("the CmccmmPaycodeCreate response = " + content);

			XMap xmapresponse = new XMap();
			xmapresponse.register(XmlCmccmmPayCodeResponse.class);
			ByteArrayInputStream in = new ByteArrayInputStream(
					content.getBytes("UTF-8"));
			xmlCmccmmPayCodeResponse = (XmlCmccmmPayCodeResponse) xmapresponse.load(in);
		} catch (Exception e) {
			logger.error("the CmccmmPaycodeCreate request error is :" + e);
			return null;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return xmlCmccmmPayCodeResponse;
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
//			String leyoUrl = "http://dev.leyogame.cn/api/m/CmccmmPaycodeCreate";
			String leyoUrl = "http://leyo.magicbirds.cn/api/m/CmccmmPaycodeCreate";
			logger.debug("leyo the CmccmmPaycodeCreate send api = " + leyoUrl);
			HttpPost post = new HttpPost(leyoUrl);

			post.setEntity(new ByteArrayEntity(xmlBody.getBytes()));
			HttpResponse response = httpclient.execute(post);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity, "UTF-8");
			logger.debug("leyo the CmccmmPaycodeCreate response = " + content);

			result = content;
		} catch (Exception e) {
			logger.error("leyo the CmccmmPaycodeCreate request error is :" + e);
			return null;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return result;
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

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
//		参考值实例
//		<?xml version="1.0" encoding="UTF-8" standalone="no"?>
//		<PayCodeRequest>
//		<TransactionId>1605061019100101</TransactionId>
//		<PltID>2012</PltID>
//		<MD5sign>64DA31F2D7A227E1878599B595A362B6</MD5sign>
//		<APPID>300005607603</APPID>
//		<PayCodeReqInfos>
//			<PayCodeReqInfo>
//				<PayCodeName><![CDATA[1yuandaoju]]></PayCodeName>
//				<PayCodeType>02</PayCodeType>
//				<Days> </Days>
//				<Fee>100</Fee>
//				<AuthFlag>1</AuthFlag>
//				<AuthURL><![CDATA[http://dev.leyogame.cn/api/m/CmccmmOrdAuth]]></AuthURL>
//				<SyncsubsURL><![CDATA[http://dev.leyogame.cn/api/m/CmccmmSyncAppOrderNotify]]></SyncsubsURL>
//				<PayCodeIndex>1</PayCodeIndex>
//			</PayCodeReqInfo>
//			<PayCodeReqInfo>
//				<PayCodeName><![CDATA[2yuandaoju]]></PayCodeName>
//				<PayCodeType>02</PayCodeType>
//				<Days> </Days>
//				<Fee>200</Fee>
//				<AuthFlag>1</AuthFlag
//				><AuthURL><![CDATA[http://dev.leyogame.cn/api/m/CmccmmOrdAuth]]></AuthURL>
//				<SyncsubsURL><![CDATA[http://dev.leyogame.cn/api/m/CmccmmSyncAppOrderNotify]]></SyncsubsURL>
//				<PayCodeIndex>2</PayCodeIndex>
//			</PayCodeReqInfo>
//		</PayCodeReqInfos>
//		</PayCodeRequest>
		
		
//		<?xml version="1.0" encoding="UTF-8"?>
//		<PaycodeResponse>
//		  <TransactionId>1605061423200101</TransactionId>
//		  <RspCode>0</RspCode>
//		  <RspDesc>success</RspDesc>
//		  <PayCodeResInfos>
//		    <PayCodeResInfo>
//		      <PayCodeName><![CDATA[1金币]]></PayCodeName>
//		      <PayCode>30000560760313</PayCode>
//		      <PayCodeIndex>1</PayCodeIndex>
//		    </PayCodeResInfo>
//		    <PayCodeResInfo>
//		      <PayCodeName><![CDATA[2金币]]></PayCodeName>
//		      <PayCode>30000560760314</PayCode>
//		      <PayCodeIndex>2</PayCodeIndex>
//		    </PayCodeResInfo>
//		  </PayCodeResInfos>
//		</PaycodeResponse>
		      
		try {
			String TransactionId = GenerateTool.createOrderNo();
			String PltID = "2012";
			String PltKey = "moli123";
			String MD5sign = DigestUtils.md5Hex(
					TransactionId + "#" + PltID + "#" + PltKey).toUpperCase();
			String APPID = "300005607603";
			String AuthURL="<![CDATA[http://dev.leyogame.cn/api/m/CmccmmOrdAuth]]>";
			String SyncsubsURL="<![CDATA[http://dev.leyogame.cn/api/m/CmccmmSyncAppOrderNotify]]>";
			XmlCmccmmPaycodeRequest xmlCmccmmPaycodeRequest=new XmlCmccmmPaycodeRequest();
			xmlCmccmmPaycodeRequest.setTransactionId(TransactionId);
			xmlCmccmmPaycodeRequest.setPltID(PltID);
			xmlCmccmmPaycodeRequest.setMD5sign(MD5sign);
			xmlCmccmmPaycodeRequest.setAPPID(APPID);
			
			XmlCmccmmPayCodeReqInfoSchema xmlCmccmmPayCodeReqInfoSchema1=new XmlCmccmmPayCodeReqInfoSchema();
			xmlCmccmmPayCodeReqInfoSchema1.setPayCodeName("<![CDATA[1金币]]>");
			xmlCmccmmPayCodeReqInfoSchema1.setPayCodeType("02");
			xmlCmccmmPayCodeReqInfoSchema1.setDays("");
			xmlCmccmmPayCodeReqInfoSchema1.setFee("100");
			xmlCmccmmPayCodeReqInfoSchema1.setAuthFlag("1");
			xmlCmccmmPayCodeReqInfoSchema1.setAuthURL(AuthURL);
			xmlCmccmmPayCodeReqInfoSchema1.setSyncsubsURL(SyncsubsURL);
			xmlCmccmmPayCodeReqInfoSchema1.setPayCodeIndex("1");
			
			XmlCmccmmPayCodeReqInfoSchema xmlCmccmmPayCodeReqInfoSchema2=new XmlCmccmmPayCodeReqInfoSchema();
			xmlCmccmmPayCodeReqInfoSchema2.setPayCodeName("<![CDATA[2金币]]>");
			xmlCmccmmPayCodeReqInfoSchema2.setPayCodeType("02");
			xmlCmccmmPayCodeReqInfoSchema2.setDays("");
			xmlCmccmmPayCodeReqInfoSchema2.setFee("200");
			xmlCmccmmPayCodeReqInfoSchema2.setAuthFlag("1");
			xmlCmccmmPayCodeReqInfoSchema2.setAuthURL(AuthURL);
			xmlCmccmmPayCodeReqInfoSchema2.setSyncsubsURL(SyncsubsURL);
			xmlCmccmmPayCodeReqInfoSchema2.setPayCodeIndex("2");
			
			XmlCmccmmPayCodeReqInfos xmlCmccmmPayCodeReqInfos=new XmlCmccmmPayCodeReqInfos();
			List<XmlCmccmmPayCodeReqInfoSchema> PayCodeReqInfo=new ArrayList<XmlCmccmmPayCodeReqInfoSchema>();
			PayCodeReqInfo.add(xmlCmccmmPayCodeReqInfoSchema1);
			PayCodeReqInfo.add(xmlCmccmmPayCodeReqInfoSchema2);
			xmlCmccmmPayCodeReqInfos.setPayCodeReqInfo(PayCodeReqInfo);
			xmlCmccmmPaycodeRequest.setPayCodeReqInfos(xmlCmccmmPayCodeReqInfos);
			
			XMap xmap = new XMap();
			xmap.register(XmlCmccmmPaycodeRequest.class);
			List<String> filters = new ArrayList<String>();
			String xmlBody = xmap.asXmlString(xmlCmccmmPaycodeRequest, "UTF-8",
					filters,true);
			xmlBody=xmlBody.replace("&lt;![CDATA[", "<![CDATA[");
			xmlBody=xmlBody.replace("]]&gt;", "]]>");
			
			CmccmmPaycodeCreate cmccmmPaycodeCreate = new CmccmmPaycodeCreate();
			String result = cmccmmPaycodeCreate.test(xmlBody);
			
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
}
