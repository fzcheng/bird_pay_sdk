package com.cheyooh.service.sdk.idata.cmccmm;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;

@XObject("ProgramRequest")
public class XmlCmccmmProgramRequest {
	
	private static final String XML_RESP = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
			+ "<ProgramRequest>\n"
			+ "   <TransactionId>%s</TransactionId>\n"
			+ "   <PltID>%s</PltID>\n"
			+ "   <MD5sign>%s</MD5sign>\n"
			+ "   <APPID>%s</APPID>\n" 
			+ "   <VersionCode>%s</VersionCode>\n"
			+ "   <VersionName>%s</VersionName>\n"
			+ "   <Status>%s</Status>\n"
			+ "   <PID>%s</PID>\n"
			+ "   <MD5>%s</MD5>\n"
			+ "</ProgramRequest>";
	
	@XNode(value = "TransactionId")
	  private String TransactionId="";
	
	@XNode(value = "PltID")
	  private String PltID="";
	
	@XNode(value = "MD5sign")
	  private String MD5sign="";
	
	@XNode(value = "APPID")
	  private String APPID="";
	
	@XNode(value = "VersionCode")
	  private String VersionCode="";
	
	@XNode(value = "VersionName")
	  private String VersionName="";
	
	@XNode(value = "Status")
	  private String Status="";
	
	@XNode(value = "PID")
	  private String PID="";
	
	@XNode(value = "MD5")
	  private String MD5="";
	
//	@XNode(value = "MD5publickey")
//	  private String MD5publickey;
//	
//	@XNode(value = "MD5classdex")
//	  private String MD5classdex;

	public String getTransactionId() {
		return TransactionId;
	}

	public void setTransactionId(String transactionId) {
		TransactionId = transactionId;
	}

	public String getPltID() {
		return PltID;
	}

	public void setPltID(String pltID) {
		PltID = pltID;
	}

	public String getMD5sign() {
		return MD5sign;
	}

	public void setMD5sign(String mD5sign) {
		MD5sign = mD5sign;
	}

	public String getAPPID() {
		return APPID;
	}

	public void setAPPID(String aPPID) {
		APPID = aPPID;
	}

	public String getVersionCode() {
		return VersionCode;
	}

	public void setVersionCode(String versionCode) {
		VersionCode = versionCode;
	}

	public String getVersionName() {
		return VersionName;
	}

	public void setVersionName(String versionName) {
		VersionName = versionName;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getPID() {
		return PID;
	}

	public void setPID(String pID) {
		PID = pID;
	}

	public String getMD5() {
		return MD5;
	}

	public void setMD5(String mD5) {
		MD5 = mD5;
	}

//	public String getMD5publickey() {
//		return MD5publickey;
//	}
//
//	public void setMD5publickey(String mD5publickey) {
//		MD5publickey = mD5publickey;
//	}
//
//	public String getMD5classdex() {
//		return MD5classdex;
//	}
//
//	public void setMD5classdex(String mD5classdex) {
//		MD5classdex = mD5classdex;
//	}

	
	
	public String toXml() {
	    return String.format(XML_RESP, TransactionId, PltID, MD5sign,APPID,VersionCode,VersionName,Status,PID,MD5);
	}
	
	@Override
	public String toString() {
		return "XmlCmccmmProgramRequest [TransactionId=" + TransactionId
				+ ", PltID=" + PltID + ", MD5sign=" + MD5sign + ", APPID="
				+ APPID + ", VersionCode=" + VersionCode + ", VersionName="
				+ VersionName + ", Status=" + Status + ", PID=" + PID
				+ ", MD5=" + MD5 + "]";
	}

	public static void main(String[] args) {
		 System.out.println(new XmlCmccmmProgramRequest().toXml());
	}
}
