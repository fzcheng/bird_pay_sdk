package com.cheyooh.service.sdk.idata.cmccmm;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;

@XObject("AppResponse")
public class XmlCmccmmAppResponse {
	
	private static final String XML_RESP = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
			+ "<AppResponse>\n"
			+ "   <TransactionId>%s</TransactionId>\n"
			+ "   <RspCode>%s</RspCode>\n"
			+ "   <RspDesc>%s</RspDesc>\n"
			+ "   <APPID>%s</APPID>\n" 
			+ "   <APPKey>%s</APPKey>\n"
			+ "</AppResponse>";
	
	@XNode(value = "TransactionId")
	private String TransactionId="";
	
	@XNode(value = "RspCode")
	private String RspCode="";
	
	@XNode(value = "RspDesc")
	private String RspDesc="";
	
	@XNode(value = "APPID")
	private String APPID="";
	
	@XNode(value = "APPKey")
	private String APPKey="";

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

	public String getAPPID() {
		return APPID;
	}

	public void setAPPID(String aPPID) {
		APPID = aPPID;
	}

	public String getAPPKey() {
		return APPKey;
	}

	public void setAPPKey(String aPPKey) {
		APPKey = aPPKey;
	}

	@Override
	public String toString() {
		return "XmlCmccmmAppResponse [TransactionId=" + TransactionId
				+ ", RspCode=" + RspCode + ", RspDesc=" + RspDesc + ", APPID="
				+ APPID + ", APPKey=" + APPKey + "]";
	}
	
	public String toXml() {
	    return String.format(XML_RESP, TransactionId, RspCode, RspDesc,APPID);
	}
	
	public static void main(String[] args) {
		 System.out.println(new XmlCmccmmAppResponse().toXml());
	}
}
