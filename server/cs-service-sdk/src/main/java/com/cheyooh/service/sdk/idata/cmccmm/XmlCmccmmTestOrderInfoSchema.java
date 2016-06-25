package com.cheyooh.service.sdk.idata.cmccmm;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;

@XObject
public class XmlCmccmmTestOrderInfoSchema {
	@XNode(value = "PayCode")
	private String PayCode = "";
	
	@XNode(value = "PayCodeName")
	private String PayCodeName = "";
	
	@XNode(value = "PayCodeType")
	private String PayCodeType = "";
	
	@XNode(value = "Mobile")
	private String Mobile = "";
	
	@XNode(value = "TestOrderDate")
	private String TestOrderDate = "";
	
	@XNode(value = "TestOrderResult")
	private String TestOrderResult = "";
	
	@XNode(value = "TestOrderSyncStatus")
	private String TestOrderSyncStatus = "";

	public String getPayCode() {
		return PayCode;
	}

	public void setPayCode(String payCode) {
		PayCode = payCode;
	}

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

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getTestOrderDate() {
		return TestOrderDate;
	}

	public void setTestOrderDate(String testOrderDate) {
		TestOrderDate = testOrderDate;
	}

	public String getTestOrderResult() {
		return TestOrderResult;
	}

	public void setTestOrderResult(String testOrderResult) {
		TestOrderResult = testOrderResult;
	}

	public String getTestOrderSyncStatus() {
		return TestOrderSyncStatus;
	}

	public void setTestOrderSyncStatus(String testOrderSyncStatus) {
		TestOrderSyncStatus = testOrderSyncStatus;
	}

	@Override
	public String toString() {
		return "XmlCmccmmTestOrderInfoSchema [PayCode=" + PayCode
				+ ", PayCodeName=" + PayCodeName + ", PayCodeType="
				+ PayCodeType + ", Mobile=" + Mobile + ", TestOrderDate="
				+ TestOrderDate + ", TestOrderResult=" + TestOrderResult
				+ ", TestOrderSyncStatus=" + TestOrderSyncStatus + "]";
	}
	
}
