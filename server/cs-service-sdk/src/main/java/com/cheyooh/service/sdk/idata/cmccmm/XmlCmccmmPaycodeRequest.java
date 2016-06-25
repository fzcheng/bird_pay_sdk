package com.cheyooh.service.sdk.idata.cmccmm;


import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;

@XObject("PayCodeRequest")
public class XmlCmccmmPaycodeRequest {
	
	@XNode(value = "TransactionId")
	private String TransactionId = "";
	  
	@XNode(value = "PltID")
	private String PltID="";
	  
	@XNode(value = "MD5sign")
	private String MD5sign="";
	
	@XNode(value = "APPID")
	private String APPID="";
	
	@XNode(value = "PayCodeReqInfos")
	private XmlCmccmmPayCodeReqInfos PayCodeReqInfos;

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

	public XmlCmccmmPayCodeReqInfos getPayCodeReqInfos() {
		return PayCodeReqInfos;
	}

	public void setPayCodeReqInfos(XmlCmccmmPayCodeReqInfos payCodeReqInfos) {
		PayCodeReqInfos = payCodeReqInfos;
	}

	@Override
	public String toString() {
		return "XmlCmccmmPaycodeRequest [TransactionId=" + TransactionId
				+ ", PltID=" + PltID + ", MD5sign=" + MD5sign + ", APPID="
				+ APPID + ", PayCodeReqInfos=" + PayCodeReqInfos + "]";
	}

}
