package com.cheyooh.service.sdk.idata.cmccmm;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;

@XObject("ProgramResponse")
public class XmlCmccmmProgramResponse {
	private static final String XML_RESP = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
			+ "<ProgramResponse>\n"
			+ "   <TransactionId>%s</TransactionId>\n"
			+ "   <RspCode>%s</RspCode>\n"
			+ "   <RspDesc>%s</RspDesc>\n"
			+ "   <PID>%s</PID>\n" 
			+ "</ProgramResponse>";
	
	@XNode(value = "TransactionId")
	private String TransactionId="";
	
	@XNode(value = "RspCode")
	private String RspCode="";
	
	@XNode(value = "RspDesc")
	private String RspDesc="";
	
	@XNode(value = "PID")
	private String PID="";

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

	public String getPID() {
		return PID;
	}

	public void setPID(String pID) {
		PID = pID;
	}

	@Override
	public String toString() {
		return "XmlCmccmmProgramResponse [TransactionId=" + TransactionId
				+ ", RspCode=" + RspCode + ", RspDesc=" + RspDesc + ", PID="
				+ PID + "]";
	}
	
	public String toXml() {
	    return String.format(XML_RESP, TransactionId, RspCode, RspDesc,PID);
	}
	
	public static void main(String[] args) {
		 System.out.println(new XmlCmccmmProgramResponse().toXml());
	}
}
