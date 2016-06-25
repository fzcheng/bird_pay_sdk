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
import com.cheyooh.service.sdk.db.dao.SdkCmccmmAppcreateMapper;
import com.cheyooh.service.sdk.db.entity.SdkCmccmmAppcreate;
import com.cheyooh.service.sdk.idata.cmccmm.XmlCmccmmAppRequest;
import com.cheyooh.service.sdk.idata.cmccmm.XmlCmccmmAppResponse;
import com.cheyooh.service.sdk.idata.cmccmm.XmlcmccmmAppExtSchema;
import com.cheyooh.service.sdk.tools.GenerateTool;

public class CmccmmAppCreateOrUpdate extends AbstractNotifyService<Cmd> {
//	private static final String url = "http://211.139.191.168:48081/oms/appContentAddOrUpdate";
	private static final String url = "http://dev.10086.cn/oms/appContentAddOrUpdate";
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
		String result="";
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
			logger.info("the CmccmmAppCreateOrUpdate xml is : " + xmlBody);
			
			XMap xmapAppRequest = new XMap();
			xmapAppRequest.register(XmlCmccmmAppRequest.class);
			ByteArrayInputStream in = new ByteArrayInputStream(
					xmlBody.getBytes("utf-8"));
			XmlCmccmmAppRequest xmlRequest = (XmlCmccmmAppRequest) xmapAppRequest.load(in);

			XmlCmccmmAppResponse xmlCmccmmAppResponse = getContent(
					xmlBody, url);
			// 操作数据库，入库操作
			
			List<XmlcmccmmAppExtSchema> AppExtInfos = xmlRequest.getAppExtInfo();
			XmlcmccmmAppExtSchema AppExtInfo = AppExtInfos.get(0);
			AppExtInfo.getChargePic();
			
			SdkCmccmmAppcreateMapper mapper = dal.getMapper(SdkCmccmmAppcreateMapper.class);
			SdkCmccmmAppcreate appCreate = new SdkCmccmmAppcreate();
			appCreate.setAppdescReq(xmlRequest.getAppDesc());
			appCreate.setAppidReq(xmlRequest.getAPPID());
			appCreate.setAppkeyResp(xmlCmccmmAppResponse.getAPPKey());
			appCreate.setAppnameReq(xmlRequest.getAppName());
			appCreate.setChargetimeReq(AppExtInfo.getChargeTime());
			appCreate.setChargetypeReq(AppExtInfo.getChargeType());
			appCreate.setContentfeeReq(Float.valueOf(AppExtInfo.getContentFee()));
			appCreate.setContenttypeReq(AppExtInfo.getContentType());
			appCreate.setCopyrightattachReq(AppExtInfo.getCopyRightAttach());
			appCreate.setCreatedTime(new Date());
			appCreate.setDevnameReq(xmlRequest.getDevName());
			appCreate.setFreeretestReq(xmlRequest.getFreeRetest());
			appCreate.setOnlinetypeReq(AppExtInfo.getOnlineType());
			appCreate.setPltidReq(xmlRequest.getPltID());
			appCreate.setRspcodeResp(xmlCmccmmAppResponse.getRspCode());
			appCreate.setRspdescResp(xmlCmccmmAppResponse.getRspDesc());
			appCreate.setServicesphoneReq(xmlRequest.getServicesPhone());
			appCreate.setStatusReq(xmlRequest.getStatus());
			appCreate.setTransactionidReq(xmlRequest.getTransactionId());
			appCreate.setTypeReq(AppExtInfo.getType());
//			appCreate.setUpdatedTime(updatedTime);
			
			mapper.insertSelective(appCreate);
			
			dal.commit();
			XMap xmap = new XMap();
			xmap.register(XmlCmccmmAppResponse.class);
			List<String> filters = new ArrayList<String>();
			String responseXmlBody = xmap.asXmlString(xmlCmccmmAppResponse, "UTF-8",
					filters);
			responseXmlBody=responseXmlBody.replace("&lt;![CDATA[", "<![CDATA[");
			responseXmlBody=responseXmlBody.replace("]]&gt;", "]]>");
			result =responseXmlBody;
			logger.info("the CmccmmAppCreateOrUpdate result is :"+result);
			return sendResponse(result);
		} catch (Exception e) {
			logger.error("the CmccmmAppCreateOrUpdate pay , sms error!", e);
			return sendResponse("Exception");
		} finally {
			dal.close();
		}

	}

	private Result sendResponse(String result) {
		return new Result(new ResultXJContent(result, result));
	}

	/**
	 * 访问移动MM平台的方法接口
	 * @param xmlCmccmmAppRequest
	 * @param url
	 * @return
	 */
	private XmlCmccmmAppResponse getContent(
			String xmlBody, String url) {
		HttpClient httpclient = new DefaultHttpClient();
		XmlCmccmmAppResponse xmlCmccmmAppResponse = new XmlCmccmmAppResponse();
		try {
			logger.debug("the CmccmmAppCreateOrUpdate send api = " + url);
			HttpPost post = new HttpPost(url);

			logger.debug("the CmccmmAppCreateOrUpdate send xmlBody = "
					+ xmlBody);
			post.setEntity(new ByteArrayEntity(xmlBody.getBytes()));
			HttpResponse response = httpclient.execute(post);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity, "UTF-8");
			logger.debug("the CmccmmAppCreateOrUpdate response = " + content);

			XMap xmapresponse = new XMap();
			xmapresponse.register(XmlCmccmmAppResponse.class);
			ByteArrayInputStream in = new ByteArrayInputStream(
					content.getBytes("utf-8"));
			xmlCmccmmAppResponse = (XmlCmccmmAppResponse) xmapresponse.load(in);
		} catch (Exception e) {
			logger.error("the CmccmmAppCreateOrUpdate request error is :" + e);
			return null;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return xmlCmccmmAppResponse;
	}

	/**
	 * 访问我们的服务器测试方法接口
	 * @param xmlCmccmmAppRequest
	 * @return
	 */
	private String test(XmlCmccmmAppRequest xmlCmccmmAppRequest) {
		String result = "";
		HttpClient httpclient = new DefaultHttpClient();
		try {
			String leyoUrl = "http://dev.leyogame.cn/api/m/CmccmmAppCreateOrUpdate";
//			String leyoUrl = "http://leyo.magicbirds.cn/api/m/CmccmmAppCreateOrUpdate";
			logger.debug("leyo the CmccmmAppCreateOrUpdate send api = "
					+ leyoUrl);
			HttpPost post = new HttpPost(leyoUrl);

			XMap xmap = new XMap();
			xmap.register(XmlCmccmmAppRequest.class);
			List<String> filters = new ArrayList<String>();
			String xmlBody = xmap.asXmlString(xmlCmccmmAppRequest, "utf-8",
					filters);
			logger.debug("leyo the leyoCmccmmAppCreateOrUpdate send xmlBody = "
					+ xmlBody);
			post.setEntity(new ByteArrayEntity(xmlBody.getBytes()));
			HttpResponse response = httpclient.execute(post);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity, "UTF-8");
			logger.debug("leyo the CmccmmAppCreateOrUpdate response = "
					+ content);

			result = content;
		} catch (Exception e) {
			logger.error("leyo the CmccmmAppCreateOrUpdate request error is :"
					+ e);
			return null;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return result;
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		/*  参数值实例
		 * <?xml version="1.0" encoding="UTF-8"?> <AppRequest>
		 * <TransactionId>1605041532440101</TransactionId> <PltID>2012</PltID>
		 * <MD5sign>09EC67A731C138A9D7004AF0AC5DBCD5</MD5sign>
		 * <Status>0</Status> <APPID></APPID>
		 * <AppName><![CDATA[测试应用]]></AppName>
		 * <AppDesc><![CDATA[应用介绍]]></AppDesc>
		 * <DevName><![CDATA[乐付]]></DevName>
		 * <ServicesPhone>12345678901</ServicesPhone> <FreeRetest>0</FreeRetest>
		 * <AppExtInfo> <ContentFee>0</ContentFee>
		 * <ContentType>1003</ContentType> <Type>10</Type>
		 * <OnlineType>2</OnlineType> <ChargeTime>2</ChargeTime>
		 * <ChargeType>02</ChargeType>
		 * <CopyRightAttach><![CDATA[版权承诺书.zip]]></CopyRightAttach>
		 * </AppExtInfo> </AppRequest>
		 * 
		 * 
		 * <?xml version="1.0" encoding="utf-8" standalone="no"?>
		 * <AppRequest>
		 * <TransactionId>1605050959520101</TransactionId>
		 * <PltID>2012</PltID>
		 * <MD5sign>32D58471C9C6295B3F3CC41A737F327F</MD5sign>
		 * <Status>0</Status>
		 * <APPID> </APPID>
		 * <AppName>测试应用</AppName>
		 * <AppDesc>应用介绍</AppDesc>
		 * <DevName>乐付</DevName>
		 * <ServicesPhone>12345678901</ServicesPhone>
		 * <FreeRetest>0</FreeRetest>
		 * <AppExtInfo>
		 * <ContentFee>0</ContentFee>
		 * <ContentType>1003</ContentType>
		 * <Type>10</Type>
		 * <OnlineType>2</OnlineType>
		 * <ChargeTime>2</ChargeTime>
		 * <ChargeType>02</ChargeType>
		 * <ChargePic> </ChargePic>
		 * <CopyRightAttach>版权承诺书.zip</CopyRightAttach>
		 * </AppExtInfo>
		 * </AppRequest>
		 * 
		 * 
		 * <?xml version="1.0" encoding="UTF-8" standalone="no"?>
		 *<AppResponse>
		 *<TransactionId>1605050959520101</TransactionId>
		 *<RspCode>0</RspCode>
		 *<RspDesc>success</RspDesc>
		 *<APPID>300005607603</APPID>
		 *<APPKey>91fce60790828f8e9904cc427127de7989ac422d862acf542c70c49a56fc326583c5e75b1aa8df7a2c5d623de0bdd367</APPKey>
		 *</AppResponse>
		 * 
		 * 
		 */

		try {

			XmlCmccmmAppRequest xmlCmccmmAppRequest = new XmlCmccmmAppRequest();
			
			String TransactionId = GenerateTool.createOrderNo();
			String PltID="2007";
			String PltKey="moli1613";
			String MD5sign = DigestUtils.md5Hex(TransactionId+"#" + PltID +"#" + PltKey).toUpperCase();
			xmlCmccmmAppRequest.setTransactionId(TransactionId);
			xmlCmccmmAppRequest.setPltID(PltID);
			xmlCmccmmAppRequest.setMD5sign(MD5sign);
			
//			xmlCmccmmAppRequest.setTransactionId("1605050959520101");
//			xmlCmccmmAppRequest.setPltID("2012");
//			xmlCmccmmAppRequest.setMD5sign("32D58471C9C6295B3F3CC41A737F327F");
			xmlCmccmmAppRequest.setStatus("0");
			xmlCmccmmAppRequest.setAPPID("");
			xmlCmccmmAppRequest.setAppName("桃色恋人2HD版");
			xmlCmccmmAppRequest.setAppDesc("<![CDATA[桃色恋人HD版]]>");
			xmlCmccmmAppRequest.setDevName("<![CDATA[乐付信息技术有限公司]]>");
			xmlCmccmmAppRequest.setServicesPhone("4000968662");
			xmlCmccmmAppRequest.setFreeRetest("0");

			XmlcmccmmAppExtSchema xmlcmccmmAppExtSchema = new XmlcmccmmAppExtSchema();
			xmlcmccmmAppExtSchema.setContentFee("0");
			xmlcmccmmAppExtSchema.setContentType("1003");
			xmlcmccmmAppExtSchema.setType("6");
			xmlcmccmmAppExtSchema.setOnlineType("2");
			xmlcmccmmAppExtSchema.setChargeTime("2");
			xmlcmccmmAppExtSchema.setChargeType("02");
			xmlcmccmmAppExtSchema.setChargePic(" ");
			xmlcmccmmAppExtSchema.setCopyRightAttach("<![CDATA[版权承诺书.zip]]>");
			// xmlCmccmmAppRequest.addAppExtInfo(xmlcmccmmAppExtSchema);
			List<XmlcmccmmAppExtSchema> xmlcmccmmAppExtSchemaList = new ArrayList<XmlcmccmmAppExtSchema>();
			xmlcmccmmAppExtSchemaList.add(xmlcmccmmAppExtSchema);
			xmlCmccmmAppRequest.setAppExtInfo(xmlcmccmmAppExtSchemaList);

			XMap xmap = new XMap();
			xmap.register(XmlCmccmmAppRequest.class);
			List<String> filters = new ArrayList<String>();
			String xmlBody = xmap.asXmlString(xmlCmccmmAppRequest, "UTF-8",
					filters);
			xmlBody=xmlBody.replace("&lt;![CDATA[", "<![CDATA[");
			xmlBody=xmlBody.replace("]]&gt;", "]]>");
			
			CmccmmAppCreateOrUpdate cmccmmAppCreateOrUpdate = new CmccmmAppCreateOrUpdate();
			String result = cmccmmAppCreateOrUpdate.test(xmlCmccmmAppRequest);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
