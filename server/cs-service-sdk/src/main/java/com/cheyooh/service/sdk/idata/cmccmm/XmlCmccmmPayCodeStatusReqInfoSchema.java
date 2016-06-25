package com.cheyooh.service.sdk.idata.cmccmm;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;

@XObject
public class XmlCmccmmPayCodeStatusReqInfoSchema {
	
	@XNode(value = "PayCode")
	private String PayCode = "";
	
	@XNode(value = "Status")
	private String Status = "";

	public String getPayCode() {
		return PayCode;
	}

	public void setPayCode(String payCode) {
		PayCode = payCode;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	@Override
	public String toString() {
		return "XmlCmccmmPayCodeStatusReqInfoSchema [PayCode=" + PayCode
				+ ", Status=" + Status + "]";
	}
	
	
}
