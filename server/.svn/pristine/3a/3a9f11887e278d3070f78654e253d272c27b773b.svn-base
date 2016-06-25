package com.cheyooh.service.sdk.action.cmccmm;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


import org.nuxeo.common.xmap.XMap;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Cmd;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.ResultXJContent;
import com.cheyooh.service.sdk.action.notify.AbstractNotifyService;
import com.cheyooh.service.sdk.idata.cmccmm.XmlCmccmmOrdAuthReq;
import com.cheyooh.service.sdk.idata.cmccmm.XmlCmccmmOrdAuthResp;

public class CmccmmOrdAuth extends AbstractNotifyService<Cmd> {

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
		String result = "";
		DAL dal = DALFactory.createDAL();
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
			logger.debug("CmccmmOrdAuth pay notify xml : " + xmlBody);

			XMap xmap = new XMap();
			xmap.register(XmlCmccmmOrdAuthReq.class);

			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
					xmlBody.getBytes("UTF-8"));
			XmlCmccmmOrdAuthReq notify = (XmlCmccmmOrdAuthReq) xmap
					.load(byteArrayInputStream);

			// 操作数据库，入库操作

			
			
			// 组装应答xml， XmlCmccmmOrdAuthResp
			XmlCmccmmOrdAuthResp xmlCmccmmOrdAuthResp = new XmlCmccmmOrdAuthResp();
			// set值

			XMap xmapXmlCmccmmOrdAuthResp = new XMap();
			xmapXmlCmccmmOrdAuthResp.register(XmlCmccmmOrdAuthResp.class);
			List<String> filters = new ArrayList<String>();
			result = xmapXmlCmccmmOrdAuthResp.asXmlString(xmlCmccmmOrdAuthResp,
					"UTF-8", filters);
			dal.commit();
			return sendResponse(result);
		} catch (Exception e) {
			logger.error("the CmccmmTestOrder pay, sms error!", e);
			return sendResponse("fail");
		} finally {
			dal.close();
		}

	}

	private Result sendResponse(String result) {
		return new Result(new ResultXJContent(result, result));
	}

	public static void main(String[] args) {

	}
}
