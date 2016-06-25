package com.cheyooh.service.sdk.idata.cmccmm;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;

@XObject
public class XmlCmccmmPayCodeResultSchema {
	@XNode(value = "PayCodeName")
	private String PayCodeName = "";
	
	@XNode(value = "PayCodeType")
	private String PayCodeType = "";
	
	@XNode(value = "Days")
	private String Days = "";
	
	@XNode(value = "Fee")
	private String Fee = "";
	
	@XNode(value = "PayCode")
	private String PayCode = "";
	
	@XNode(value = "SyncsubsURL")
	private String SyncsubsURL = "";

	public String getPayCodeName() {
		return PayCodeName;
	}

	public void setPayCodeName(String payCodeName) {
		PayCodeName = payCodeName;
	}

	public String getPayCodeType() {
		return PayCodeType;
	}

	public void setPayCodeType(String payCodeType) {
		PayCodeType = payCodeType;
	}

	public String getDays() {
		return Days;
	}

	public void setDays(String days) {
		Days = days;
	}

	public String getFee() {
		return Fee;
	}

	public void setFee(String fee) {
		Fee = fee;
	}

	public String getPayCode() {
		return PayCode;
	}

	public void setPayCode(String payCode) {
		PayCode = payCode;
	}

	public String getSyncsubsURL() {
		return SyncsubsURL;
	}

	public void setSyncsubsURL(String syncsubsURL) {
		SyncsubsURL = syncsubsURL;
	}

	@Override
	public String toString() {
		return "XmlCmccmmPayCodeResultSchema [PayCodeName=" + PayCodeName
				+ ", PayCodeType=" + PayCodeType + ", Days=" + Days + ", Fee="
				+ Fee + ", PayCode=" + PayCode + ", SyncsubsURL=" + SyncsubsURL
				+ "]";
	}
	
}
