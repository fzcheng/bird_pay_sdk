package com.cheyooh.service.sdk.idata.cmccmm;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;

@XObject("PayCodeResInfo")
public class XmlCmccmmPayCodeResInfoSchema {
	@XNode(value = "PayCodeName")
	private String PayCodeName = "";
	
	@XNode(value = "PayCode")
	private String PayCode = "";
	
	@XNode(value = "PayCodeIndex")
	private String PayCodeIndex = "";

	public String getPayCodeName() {
		return PayCodeName;
	}

	public void setPayCodeName(String payCodeName) {
		PayCodeName = payCodeName;
	}

	public String getPayCode() {
		return PayCode;
	}

	public void setPayCode(String payCode) {
		PayCode = payCode;
	}

	public String getPayCodeIndex() {
		return PayCodeIndex;
	}

	public void setPayCodeIndex(String payCodeIndex) {
		PayCodeIndex = payCodeIndex;
	}

	@Override
	public String toString() {
		return "XmlCmccmmPayCodeResInfoSchema [PayCodeName=" + PayCodeName
				+ ", PayCode=" + PayCode + ", PayCodeIndex=" + PayCodeIndex
				+ "]";
	}
	
	
}
