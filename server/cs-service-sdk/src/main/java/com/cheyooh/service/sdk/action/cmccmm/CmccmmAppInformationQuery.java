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
import com.cheyooh.service.sdk.idata.cmccmm.XmlCmccmmAppQueryRequest;
import com.cheyooh.service.sdk.idata.cmccmm.XmlCmccmmAppQueryResponse;
import com.cheyooh.service.sdk.tools.GenerateTool;

public class CmccmmAppInformationQuery extends AbstractNotifyService<Cmd> {
	private static final String url = "http://211.139.191.168:48081/oms/AppInformationQuery";

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
//			String APPID = allParamMap.get("APPID");
//			if (StringUtils.isEmpty(APPID)) {
//				APPID = "";
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
			logger.info("the CmccmmAppInformationQuery xml is :" + xmlBody);
			
//			XMap xmapRequest = new XMap();
//			xmapRequest.register(XmlCmccmmAppQueryRequest.class);
//			XmlCmccmmAppQueryRequest request = (XmlCmccmmAppQueryRequest) xmapRequest.load(new ByteArrayInputStream(xmlBody.getBytes("UTF-8")));
			
			XmlCmccmmAppQueryResponse xmlCmccmmAppQueryResponse = getContent(xmlBody,url);
			
			// 操作数据库，入库操作
//			List<XmlCmccmmAppInfoSchema> infos = xmlCmccmmAppQueryResponse.getAppInfo();
//			XmlCmccmmAppInfoSchema info = infos.get(0);
//			SdkCmccmmAppqueryMapper mapper = dal.getMapper(SdkCmccmmAppqueryMapper.class);
//			SdkCmccmmAppquery record = new SdkCmccmmAppquery();
//			
//			record.setApcodeReq(request.getAPCode());
//			record.setAppdescResp(xmlCmccmmAppQueryResponse.getRspDesc());
//			record.setAppescResp(info.getAppDesc());
//			record.setAppidReq(request.getAPPID());
////			record.setAppidResp("");
//			record.setAppstatusResp(info.getStatus());
//			record.setApptypeResp(info.getType());
//			record.setBussdateResp(info.getBussDate());
//			record.setContentfeeResp(Float.valueOf(info.getContentFee()));
//			record.setContenttypeResp(info.getContentType());
////			record.setCopyrightxmlResp("");
//			record.setCreatedTime(new Date());
//			record.setDescResp(info.getDesc());
////			record.setId();
//			record.setKeywordsResp(info.getKeyWords());
//			record.setLanguageResp(info.getLanguage());
//			record.setMmiapxmlResp("");
//			record.setNameResp("");
//			record.setPackagenameResp("");
//			record.setProgramidResp("");
//			record.setProgramResp("");
//			record.setRspcodeResp("");
//			record.setRspdescResp("");
//			record.setStatusResp("");
//			record.setTransactionidReq(xmlCmccmmAppQueryResponse.getTransactionId());
//			record.setTypeResp("");
//			record.setVersioncodeResp("");
//			record.setVersionnameResp("");
//			record.setVersionResp("");
//			mapper.insertSelective(record);
			
			dal.commit();
			XMap xmap = new XMap();
			xmap.register(XmlCmccmmAppQueryResponse.class);
			List<String> filters = new ArrayList<String>();
			String responseXmlBody = xmap.asXmlString(xmlCmccmmAppQueryResponse,
					"UTF-8", filters);
			responseXmlBody=responseXmlBody.replace("&lt;![CDATA[", "<![CDATA[");
			responseXmlBody=responseXmlBody.replace("]]&gt;", "]]>");
			result = responseXmlBody;
			return sendResponse(result);
		} catch (Exception e) {
			logger.error("the CmccmmAppInformationQuery pay , sms error!", e);
			return sendResponse("fail");
		} finally {
			dal.close();
		}

	}

	private Result sendResponse(String result) {
		return new Result(new ResultXJContent(result, result));
	}

	private XmlCmccmmAppQueryResponse getContent(String xmlBody, String url) {
		HttpClient httpclient = new DefaultHttpClient();
		XmlCmccmmAppQueryResponse xmlCmccmmAppQueryResponse = new XmlCmccmmAppQueryResponse();
		try {
			logger.debug("the CmccmmAppInformationQuery send api =" + url);
			HttpPost post = new HttpPost(url);

			logger.debug("the CmccmmAppInformationQuery send xmlBody ="
					+ xmlBody);
			post.setEntity(new ByteArrayEntity(xmlBody.getBytes()));
			HttpResponse response = httpclient.execute(post);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity, "UTF-8");
			logger.debug("the CmccmmAppInformationQuery response =" + content);
			
			XMap xmapresponse = new XMap();
			xmapresponse.register(XmlCmccmmAppQueryResponse.class);
			ByteArrayInputStream in = new ByteArrayInputStream(
					content.getBytes("UTF-8"));
			xmlCmccmmAppQueryResponse = (XmlCmccmmAppQueryResponse) xmapresponse.load(in);
		} catch (Exception e) {
			logger.error("the CmccmmAppInformationQuery request error is :" + e);
			return null;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return xmlCmccmmAppQueryResponse;
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
			String leyoUrl = "http://dev.leyogame.cn/api/m/CmccmmAppInformationQuery";
			logger.debug("leyo the CmccmmAppInformationQuery send api = " + leyoUrl);
			HttpPost post = new HttpPost(leyoUrl);

			post.setEntity(new ByteArrayEntity(xmlBody.getBytes()));
			HttpResponse response = httpclient.execute(post);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity, "UTF-8");
			logger.debug("leyo the CmccmmAppInformationQuery response = " + content);

			result = content;
		} catch (Exception e) {
			logger.error("leyo the CmccmmAppInformationQuery request error is :" + e);
			return null;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return result;
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		try {
			String TransactionId = GenerateTool.createOrderNo();
			String APCode = "444670";
			String APPID = "300005607603";
			
			XmlCmccmmAppQueryRequest xmlCmccmmAppQueryRequest = new XmlCmccmmAppQueryRequest();
			xmlCmccmmAppQueryRequest.setTransactionId(TransactionId);
			xmlCmccmmAppQueryRequest.setAPCode(APCode);
			xmlCmccmmAppQueryRequest.setAPPID(APPID);
			
			XMap xmap = new XMap();
			xmap.register(XmlCmccmmAppQueryRequest.class);
			List<String> filters = new ArrayList<String>();
			String xmlBody = xmap.asXmlString(xmlCmccmmAppQueryRequest, "UTF-8",
					filters,true);
			xmlBody=xmlBody.replace("&lt;![CDATA[", "<![CDATA[");
			xmlBody=xmlBody.replace("]]&gt;", "]]>");
			
			CmccmmAppInformationQuery cmccmmAppInformationQuery = new CmccmmAppInformationQuery();
			String result = cmccmmAppInformationQuery.test(xmlBody);
			
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
