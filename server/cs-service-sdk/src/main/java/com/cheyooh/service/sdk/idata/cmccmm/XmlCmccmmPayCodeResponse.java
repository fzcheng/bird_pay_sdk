package com.cheyooh.service.sdk.idata.cmccmm;

import java.io.ByteArrayInputStream;

import org.nuxeo.common.xmap.XMap;
import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;

@XObject("PaycodeResponse")
public class XmlCmccmmPayCodeResponse {

	// <?xml version="1.0" encoding="UTF-8"?>
	// <PaycodeResponse>
	// <RspCode><![CDATA[-0001]]></RspCode>
	// <RspDesc><![CDATA[请求消息体格式有错]]></RspDesc>
	// <PayCodeResInfos/>
	// </PaycodeResponse>

	private static final String XML_RESP = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
			+ "<PaycodeResponse>\n"
			+ "   <RspCode>%s</RspCode>\n"
			+ "   <RspDesc>%s</RspDesc>\n"
			+ "   <PayCodeResInfos/>\n"
			+ "</PaycodeResponse>";

	@XNode(value = "TransactionId")
	private String TransactionId = "";

	@XNode(value = "RspCode")
	private String RspCode = "";

	@XNode(value = "RspDesc")
	private String RspDesc = "";

	@XNode(value = "PayCodeResInfos")
	private XmlCmccmmPayCodeResInfos PayCodeResInfos;

	public String getTransactionId() {
		return TransactionId;
	}

	public void setTransactionId(String transactionId) {
		TransactionId = transactionId;
	}

	public String getRspCode() {
		return RspCode;
	}

	public void setRspCode(String rspCode) {
		RspCode = rspCode;
	}

	public String getRspDesc() {
		return RspDesc;
	}

	public void setRspDesc(String rspDesc) {
		RspDesc = rspDesc;
	}

	public XmlCmccmmPayCodeResInfos getPayCodeResInfos() {
		return PayCodeResInfos;
	}

	public void setPayCodeResInfos(XmlCmccmmPayCodeResInfos payCodeResInfos) {
		PayCodeResInfos = payCodeResInfos;
	}

	@Override
	public String toString() {
		return "XmlCmccmmPayCodeResponse [TransactionId=" + TransactionId
				+ ", RspCode=" + RspCode + ", RspDesc=" + RspDesc
				+ ", PayCodeResInfos=" + PayCodeResInfos + "]";
	}

	public String toXml() {
		return String.format(XML_RESP, TransactionId, RspCode, RspDesc,
				PayCodeResInfos);
	}

	public static void main(String[] args) {
		try {
			XmlCmccmmPayCodeResponse xmlCmccmmPayCodeResponse = new XmlCmccmmPayCodeResponse();
			XMap xmapresponse = new XMap();
			xmapresponse.register(XmlCmccmmPayCodeResponse.class);
			String content = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
					+ "<PaycodeResponse>\n"
					+ "   <RspCode><![CDATA[-0001]]></RspCode>\n"
					+ "   <RspDesc><![CDATA[请求消息体格式有错]]></RspDesc>\n"
					+ "   <PayCodeResInfos/>\n" 
					+ "</PaycodeResponse>";
			ByteArrayInputStream in;
			in = new ByteArrayInputStream(content.getBytes("UTF-8"));
			xmlCmccmmPayCodeResponse = (XmlCmccmmPayCodeResponse) xmapresponse
					.load(in);
			System.out.println(xmlCmccmmPayCodeResponse);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}
}
