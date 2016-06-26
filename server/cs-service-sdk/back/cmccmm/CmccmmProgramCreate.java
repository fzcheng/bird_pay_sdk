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
import com.cheyooh.service.sdk.db.dao.SdkCmccmmApkcreateMapper;
import com.cheyooh.service.sdk.db.entity.SdkCmccmmApkcreate;
import com.cheyooh.service.sdk.idata.cmccmm.XmlCmccmmProgramRequest;
import com.cheyooh.service.sdk.idata.cmccmm.XmlCmccmmProgramResponse;
import com.cheyooh.service.sdk.tools.GenerateTool;

public class CmccmmProgramCreate extends AbstractNotifyService<Cmd> {
	private static final String url = "http://211.139.191.168:48081/oms/programAndSynchronizeMd5";

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
			logger.info("the CmccmmProgramCreate xml is :" + xmlBody);
			
			XMap xmapProgramRequest = new XMap();
			xmapProgramRequest.register(XmlCmccmmProgramRequest.class);
			ByteArrayInputStream in = new ByteArrayInputStream(
					xmlBody.getBytes("utf-8"));
			XmlCmccmmProgramRequest xmlRequest = (XmlCmccmmProgramRequest) xmapProgramRequest.load(in);

			XmlCmccmmProgramResponse xmlCmccmmProgramResponse = getContent(
					xmlBody, url);
			// 操作数据库，入库操作
			SdkCmccmmApkcreate sdkCmccmmApkcreate=new SdkCmccmmApkcreate();
			sdkCmccmmApkcreate.setTransactionidReq(xmlRequest.getTransactionId());
			sdkCmccmmApkcreate.setAppidReq(xmlRequest.getAPPID());
			sdkCmccmmApkcreate.setPltidReq(xmlRequest.getPltID());
			sdkCmccmmApkcreate.setVersioncodeReq(xmlRequest.getVersionCode());
			sdkCmccmmApkcreate.setVersionnameReq(xmlRequest.getVersionName());
			sdkCmccmmApkcreate.setStatusReq(xmlRequest.getStatus());
			sdkCmccmmApkcreate.setPidReq(xmlRequest.getPID());
			sdkCmccmmApkcreate.setMd5Req(xmlRequest.getMD5());
			sdkCmccmmApkcreate.setRspcodeResp(xmlCmccmmProgramResponse.getRspCode());
			sdkCmccmmApkcreate.setRspdescResp(xmlCmccmmProgramResponse.getRspDesc());
			sdkCmccmmApkcreate.setCreatedTime(new Date());
			SdkCmccmmApkcreateMapper sdkCmccmmApkcreateMapper=dal.getMapper(SdkCmccmmApkcreateMapper.class);
			sdkCmccmmApkcreateMapper.insert(sdkCmccmmApkcreate);
			
			dal.commit();
			XMap xmap = new XMap();
			xmap.register(XmlCmccmmProgramResponse.class);
			List<String> filters = new ArrayList<String>();
			String responseXmlBody = xmap.asXmlString(xmlCmccmmProgramResponse,
					"UTF-8", filters);
			responseXmlBody=responseXmlBody.replace("&lt;![CDATA[", "<![CDATA[");
			responseXmlBody=responseXmlBody.replace("]]&gt;", "]]>");
			
			result = responseXmlBody;
			return sendResponse(result);
		} catch (Exception e) {
			logger.error("the CmccmmProgramCreate pay appear error is :", e);
			return sendResponse(result);
		} finally {
			dal.close();
		}

	}

	private Result sendResponse(String result) {
		return new Result(new ResultXJContent(result, result));
	}

	private XmlCmccmmProgramResponse getContent(String xmlBody, String url) {
		HttpClient httpclient = new DefaultHttpClient();
		XmlCmccmmProgramResponse xmlCmccmmProgramResponse = new XmlCmccmmProgramResponse();
		try {
			logger.debug("the CmccmmProgramCreate send api = " + url);
			HttpPost post = new HttpPost(url);

			logger.debug("the CmccmmProgramCreate send xmlBody = " + xmlBody);
			post.setEntity(new ByteArrayEntity(xmlBody.getBytes()));
			HttpResponse response = httpclient.execute(post);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity, "UTF-8");
			logger.debug("the CmccmmProgramCreate response = " + content);

			XMap xmapresponse = new XMap();
			xmapresponse.register(XmlCmccmmProgramResponse.class);
			ByteArrayInputStream in = new ByteArrayInputStream(
					content.getBytes("utf-8"));
			xmlCmccmmProgramResponse = (XmlCmccmmProgramResponse) xmapresponse
					.load(in);
		} catch (Exception e) {
			logger.error("the CmccmmProgramCreate request error is :" + e);
			return null;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return xmlCmccmmProgramResponse;
	}

	/**
	 * 访问我们的服务器测试方法接口
	 * 
	 * @param xmlCmccmmAppRequest
	 * @return
	 */
	private String test(XmlCmccmmProgramRequest xmlCmccmmProgramRequest) {
		String result = "";
		HttpClient httpclient = new DefaultHttpClient();
		try {
			String leyoUrl = "http://dev.leyogame.cn/api/m/CmccmmProgramCreate";
			logger.debug("leyo the CmccmmProgramCreate send api = " + leyoUrl);
			HttpPost post = new HttpPost(leyoUrl);

			XMap xmap = new XMap();
			xmap.register(XmlCmccmmProgramRequest.class);
			List<String> filters = new ArrayList<String>();
			String xmlBody = xmap.asXmlString(xmlCmccmmProgramRequest, "utf-8",
					filters);
			logger.debug("leyo the CmccmmProgramCreate send xmlBody = "
					+ xmlBody);
			post.setEntity(new ByteArrayEntity(xmlBody.getBytes()));
			HttpResponse response = httpclient.execute(post);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity, "UTF-8");
			logger.debug("leyo the CmccmmProgramCreate response = " + content);

			result = content;
		} catch (Exception e) {
			logger.error("leyo the CmccmmProgramCreate request error is :" + e);
			return null;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return result;
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		/*参数值实例
		 * <?xml version="1.0" encoding="utf-8" standalone="no"?>
		 * <ProgramRequest> <TransactionId>1605051128090101</TransactionId>
		 * <PltID>2012</PltID>
		 * <MD5sign>D79469AD1ED6617A532236BF17BC52A4</MD5sign>
		 * <APPID>300005607603</APPID> <VersionCode>2</VersionCode>
		 * <VersionName>2.0</VersionName> <Status>0</Status> <PID> </PID> <MD5>
		 * </MD5> </ProgramRequest>
		 * 
		 * 
		 * <?xml version="1.0" encoding="UTF-8" standalone="no"?>
		 * <ProgramResponse> <TransactionId>1605051128090101</TransactionId>
		 * <RspCode>0</RspCode> <RspDesc>success</RspDesc> <PID>3069096</PID>
		 * </ProgramResponse>
		 */

		try {

			String TransactionId = GenerateTool.createOrderNo();
			String PltID = "2012";
			String PltKey = "moli123";
			String MD5sign = DigestUtils.md5Hex(
					TransactionId + "#" + PltID + "#" + PltKey).toUpperCase();
			String APPID = "300005607603";
			String VersionCode = "2";
			String VersionName = "2.0";
			String Status = "0";
			XmlCmccmmProgramRequest xmlCmccmmProgramRequest = new XmlCmccmmProgramRequest();
			xmlCmccmmProgramRequest.setTransactionId(TransactionId);
			xmlCmccmmProgramRequest.setPltID(PltID);
			xmlCmccmmProgramRequest.setMD5sign(MD5sign);
			xmlCmccmmProgramRequest.setAPPID(APPID);
			xmlCmccmmProgramRequest.setVersionCode(VersionCode);
			xmlCmccmmProgramRequest.setVersionName(VersionName);
			xmlCmccmmProgramRequest.setStatus(Status);
			xmlCmccmmProgramRequest.setPID(" ");
			xmlCmccmmProgramRequest.setMD5(" ");

			XMap xmap = new XMap();
			xmap.register(XmlCmccmmProgramRequest.class);
			List<String> filters = new ArrayList<String>();
			String xmlBody = xmap.asXmlString(xmlCmccmmProgramRequest, "UTF-8",
					filters);
			xmlBody=xmlBody.replace("&lt;![CDATA[", "<![CDATA[");
			xmlBody=xmlBody.replace("]]&gt;", "]]>");
			
			CmccmmProgramCreate cmccmmProgramCreate = new CmccmmProgramCreate();
			String result = cmccmmProgramCreate.test(xmlCmccmmProgramRequest);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}
}
