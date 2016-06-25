package com.cheyooh.service.sdk.idata.cmccmm;

import java.util.ArrayList;
import java.util.List;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XNodeList;
import org.nuxeo.common.xmap.annotation.XObject;

@XObject("PaycodeStatusRequest")
public class XmlCmccmmPaycodeStatusRequest {

	@XNode(value = "TransactionId")
	private String TransactionId = "";

	@XNode(value = "PltID")
	private String PltID = "";

	@XNode(value = "MD5sign")
	private String MD5sign = "";

	@XNode(value = "APPID")
	private String APPID = "";
	
	@XNode(value = "PayCodeStatusReqInfos")
	private XmlCmccmmPayCodeStatusReqInfos PayCodeStatusReqInfos;

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

	public XmlCmccmmPayCodeStatusReqInfos getPayCodeStatusReqInfos() {
		return PayCodeStatusReqInfos;
	}

	public void setPayCodeStatusReqInfos(
			XmlCmccmmPayCodeStatusReqInfos payCodeStatusReqInfos) {
		PayCodeStatusReqInfos = payCodeStatusReqInfos;
	}

	@Override
	public String toString() {
		return "XmlCmccmmPaycodeStatusRequest [TransactionId=" + TransactionId
				+ ", PltID=" + PltID + ", MD5sign=" + MD5sign + ", APPID="
				+ APPID + ", PayCodeStatusReqInfos=" + PayCodeStatusReqInfos
				+ "]";
	}

}
