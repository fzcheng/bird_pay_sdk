package com.cheyooh.service.sdk.idata;

import com.cheyooh.service.framework.idata.ResultContent;

public class ResultSdkXqtpayParam extends ResultContent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3847067528308770889L;
	private String mhtOrderNo;
	private String payChannelType;
	private String consumerId;
	private String mhtOrderName;
	private String mhtOrderDetail;
	private String mhtOrderAmt;
	private String notifyUrl;
	private String superid;
	private String sign;
	
	public ResultSdkXqtpayParam() {
		setTagName("sdkxqtpay");
	}
	
	public String getMhtOrderNo() {
		return mhtOrderNo;
	}
	public void setMhtOrderNo(String mhtOrderNo) {
		this.mhtOrderNo = mhtOrderNo;
	}
	public String getPayChannelType() {
		return payChannelType;
	}
	public void setPayChannelType(String payChannelType) {
		this.payChannelType = payChannelType;
	}
	public String getConsumerId() {
		return consumerId;
	}
	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}
	public String getMhtOrderName() {
		return mhtOrderName;
	}
	public void setMhtOrderName(String mhtOrderName) {
		this.mhtOrderName = mhtOrderName;
	}
	public String getMhtOrderDetail() {
		return mhtOrderDetail;
	}
	public void setMhtOrderDetail(String mhtOrderDetail) {
		this.mhtOrderDetail = mhtOrderDetail;
	}
	public String getMhtOrderAmt() {
		return mhtOrderAmt;
	}
	public void setMhtOrderAmt(String mhtOrderAmt) {
		this.mhtOrderAmt = mhtOrderAmt;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getSuperid() {
		return superid;
	}
	public void setSuperid(String superid) {
		this.superid = superid;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
}
