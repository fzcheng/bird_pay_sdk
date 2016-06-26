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
import com.cheyooh.service.sdk.db.dao.SdkCmccmmUploadpgMapper;
import com.cheyooh.service.sdk.db.entity.SdkCmccmmUploadpg;
import com.cheyooh.service.sdk.idata.cmccmm.XmlCmccmmPgExtSchema;
import com.cheyooh.service.sdk.idata.cmccmm.XmlCmccmmUploadPgRequest;
import com.cheyooh.service.sdk.idata.cmccmm.XmlCmccmmUploadPgResponse;
import com.cheyooh.service.sdk.tools.GenerateTool;

public class CmccmmUploadProgramPackage extends AbstractNotifyService<Cmd> {
	private static final String url = "http://211.139.191.168:48081/oms/uploadProgramPackage";

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
			logger.info("the CmccmmUploadProgramPackage xml is :" + xmlBody);
			
			XMap xmlCmccmmUploadPgRequest = new XMap();
			xmlCmccmmUploadPgRequest.register(XmlCmccmmUploadPgRequest.class);
			ByteArrayInputStream in = new ByteArrayInputStream(
					xmlBody.getBytes("utf-8"));
			XmlCmccmmUploadPgRequest xmlRequest = (XmlCmccmmUploadPgRequest) xmlCmccmmUploadPgRequest.load(in);
			
			XmlCmccmmUploadPgResponse xmlCmccmmUploadPgResponse = getContent(xmlBody,url);
			// 操作数据库，入库操作
			SdkCmccmmUploadpgMapper sdkCmccmmUploadpgMapper=dal.getMapper(SdkCmccmmUploadpgMapper.class);
			SdkCmccmmUploadpg sdkCmccmmUploadpg=new SdkCmccmmUploadpg();
			sdkCmccmmUploadpg.setTransactionidReq(xmlRequest.getTransactionId());
			sdkCmccmmUploadpg.setPltidReq(xmlRequest.getPltID());
			sdkCmccmmUploadpg.setAppidReq(xmlRequest.getAPPID());
			sdkCmccmmUploadpg.setPidReq(xmlRequest.getPID());
			sdkCmccmmUploadpg.setProgramurlReq(xmlRequest.getProgramUrl());
			sdkCmccmmUploadpg.setFilesizeReq(xmlRequest.getFileSize());
			sdkCmccmmUploadpg.setFilecrc32Req(xmlRequest.getFileCRC32());
			sdkCmccmmUploadpg.setPublishtommReq(xmlRequest.getPublishToMM());
			sdkCmccmmUploadpg.setLogoReq(xmlRequest.getPgExtInfo().get(0).getLOGO());
			sdkCmccmmUploadpg.setPicture1Req(xmlRequest.getPgExtInfo().get(0).getPicture1());
			sdkCmccmmUploadpg.setPicture2Req(xmlRequest.getPgExtInfo().get(0).getPicture2());
//			sdkCmccmmUploadpg.setChargepicReq(xmlRequest.getPgExtInfo().get(0).getc);
			sdkCmccmmUploadpg.setSelfsignReq(xmlRequest.getPgExtInfo().get(0).getSelfSign());
			sdkCmccmmUploadpg.setKeystoreReq(xmlRequest.getPgExtInfo().get(0).getKeyStore());
			sdkCmccmmUploadpg.setStorepassReq(xmlRequest.getPgExtInfo().get(0).getStorePass());
			sdkCmccmmUploadpg.setKeypassReq(xmlRequest.getPgExtInfo().get(0).getKeyPass());
			sdkCmccmmUploadpg.setAliasReq(xmlRequest.getPgExtInfo().get(0).getAlias());
			sdkCmccmmUploadpg.setVersiondescReq(xmlRequest.getPgExtInfo().get(0).getVersionDesc());
			sdkCmccmmUploadpg.setRspcodeResp(xmlCmccmmUploadPgResponse.getRspCode());
			sdkCmccmmUploadpg.setRspdescResp(xmlCmccmmUploadPgResponse.getRspDesc());
			sdkCmccmmUploadpg.setCreatedTime(new Date());
			sdkCmccmmUploadpgMapper.insertSelective(sdkCmccmmUploadpg);
			
			dal.commit();
			XMap xmap = new XMap();
			xmap.register(XmlCmccmmUploadPgResponse.class);
			List<String> filters = new ArrayList<String>();
			String responseXmlBody = xmap.asXmlString(xmlCmccmmUploadPgResponse,
					"UTF-8", filters);
			result = responseXmlBody;
			return sendResponse(result);
		} catch (Exception e) {
			logger.error("the CmccmmUploadProgramPackage pay appear error is :", e);
			return sendResponse("fail");
		} finally {
			dal.close();
		}

	}

	private Result sendResponse(String result) {
		return new Result(new ResultXJContent(result, result));
	}

	private XmlCmccmmUploadPgResponse getContent(String xmlBody, String url) {
		HttpClient httpclient = new DefaultHttpClient();
		XmlCmccmmUploadPgResponse xmlCmccmmUploadPgResponse = new XmlCmccmmUploadPgResponse();
		try {
			logger.debug("the CmccmmUploadProgramPackage send api =" + url);
			HttpPost post = new HttpPost(url);

			logger.debug("the CmccmmUploadProgramPackage send xmlBody ="
					+ xmlBody);
			post.setEntity(new ByteArrayEntity(xmlBody.getBytes()));
			HttpResponse response = httpclient.execute(post);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity, "UTF-8");
			logger.debug("the CmccmmUploadProgramPackage response =" + content);

			XMap xmapresponse = new XMap();
			xmapresponse.register(XmlCmccmmUploadPgResponse.class);
			ByteArrayInputStream in = new ByteArrayInputStream(
					content.getBytes("utf-8"));
			xmlCmccmmUploadPgResponse = (XmlCmccmmUploadPgResponse) xmapresponse.load(in);
		} catch (Exception e) {
			logger.error("the CmccmmUploadProgramPackage request error is :" + e);
			return null;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return xmlCmccmmUploadPgResponse;
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
			String leyoUrl = "http://dev.leyogame.cn/api/m/CmccmmUploadProgramPackage";
			logger.debug("leyo the CmccmmUploadProgramPackage send api = " + leyoUrl);
			HttpPost post = new HttpPost(leyoUrl);

			post.setEntity(new ByteArrayEntity(xmlBody.getBytes()));
			HttpResponse response = httpclient.execute(post);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity, "UTF-8");
			logger.debug("leyo the CmccmmUploadProgramPackage response = " + content);

			result = content;
		} catch (Exception e) {
			logger.error("leyo the CmccmmUploadProgramPackage request error is :" + e);
			return null;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return result;
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
//		<?xml version="1.0" encoding="UTF-8" standalone="no"?>
//		<UploadPgRequest>
//		<TransactionId>1605061609100101</TransactionId>
//		<PltID>2012</PltID>
//		<MD5sign>1711ADF70AD5F946AF3DC54B5853F646</MD5sign>
//		<APPID>300005607603</APPID>
//		<PID>3069096</PID>
//		<ProgramUrl><![CDATA[rongda_Program1.apk]]></ProgramUrl>
//		<FileSize>123423</FileSize>
//		<FileCRC32>b292637f</FileCRC32>
//		<PublishToMM>1</PublishToMM>
//		<PgExtInfo>
//		<LOGO><![CDATA[123u2.png]]></LOGO>
//		<Picture1><![CDATA[1431677576425.png]]></Picture1>
//		<Picture2><![CDATA[1431677578917.jpg]]></Picture2>
//		<SelfSign>1</SelfSign>
//		<KeyStore><![CDATA[img200910172057151.keystore]]></KeyStore>
//		<StorePass>1</StorePass>
//		<KeyPass>2</KeyPass>
//		<Alias>1</Alias>
//		<VersionDesc>2</VersionDesc>
//		</PgExtInfo>
//		</UploadPgRequest>
		
//		<?xml version="1.0" encoding="UTF-8"?>
//		<UploadPgResponse>
//		  <TransactionId>1605061609100101</TransactionId>
//		  <RspCode>0</RspCode>
//		  <RspDesc>success</RspDesc>
//		</UploadPgResponse>
		
		try {
			String TransactionId = GenerateTool.createOrderNo();
			String PltID = "2012";
			String PltKey = "moli123";
			String MD5sign = DigestUtils.md5Hex(
					TransactionId + "#" + PltID + "#" + PltKey).toUpperCase();
			String APPID = "300005607603";
			String PID="3069096";
			String ProgramUrl="<![CDATA[rongda_Program1.apk]]>";
			String FileSize="123423"; 
			String FileCRC32="b292637f";
			String PublishToMM="1";
			
			XmlCmccmmUploadPgRequest xmlCmccmmUploadPgRequest=new XmlCmccmmUploadPgRequest();
			xmlCmccmmUploadPgRequest.setTransactionId(TransactionId);
			xmlCmccmmUploadPgRequest.setPltID(PltID);
			xmlCmccmmUploadPgRequest.setMD5sign(MD5sign);
			xmlCmccmmUploadPgRequest.setAPPID(APPID);
			xmlCmccmmUploadPgRequest.setPID(PID);
			xmlCmccmmUploadPgRequest.setProgramUrl(ProgramUrl);
			xmlCmccmmUploadPgRequest.setFileSize(FileSize);
			xmlCmccmmUploadPgRequest.setFileCRC32(FileCRC32);
			xmlCmccmmUploadPgRequest.setPublishToMM(PublishToMM);
			
			XmlCmccmmPgExtSchema xmlCmccmmPgExtSchema=new XmlCmccmmPgExtSchema();
			xmlCmccmmPgExtSchema.setLOGO("<![CDATA[123u2.png]]>"); 
			xmlCmccmmPgExtSchema.setPicture1("<![CDATA[1431677576425.png]]>");
			xmlCmccmmPgExtSchema.setPicture2("<![CDATA[1431677578917.jpg]]>");
			xmlCmccmmPgExtSchema.setSelfSign("1");
			xmlCmccmmPgExtSchema.setKeyStore("<![CDATA[img200910172057151.keystore]]>");
			xmlCmccmmPgExtSchema.setStorePass("1");
			xmlCmccmmPgExtSchema.setKeyPass("2");
			xmlCmccmmPgExtSchema.setAlias("1");
			xmlCmccmmPgExtSchema.setVersionDesc("2");
			
			List<XmlCmccmmPgExtSchema> pgExtInfo=new ArrayList<XmlCmccmmPgExtSchema>();
			pgExtInfo.add(xmlCmccmmPgExtSchema);
			xmlCmccmmUploadPgRequest.setPgExtInfo(pgExtInfo);
			
			XMap xmap = new XMap();
			xmap.register(XmlCmccmmUploadPgRequest.class);
			List<String> filters = new ArrayList<String>();
			String xmlBody = xmap.asXmlString(xmlCmccmmUploadPgRequest, "UTF-8",
					filters,true);
			xmlBody=xmlBody.replace("&lt;![CDATA[", "<![CDATA[");
			xmlBody=xmlBody.replace("]]&gt;", "]]>");
			
			CmccmmUploadProgramPackage cmccmmUploadProgramPackage = new CmccmmUploadProgramPackage();
			String result = cmccmmUploadProgramPackage.test(xmlBody);
			
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
