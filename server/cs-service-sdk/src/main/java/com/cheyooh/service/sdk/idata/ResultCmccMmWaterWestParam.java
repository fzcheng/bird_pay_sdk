package com.cheyooh.service.sdk.idata;

import com.cheyooh.service.framework.idata.ResultContent;


public class ResultCmccMmWaterWestParam extends ResultContent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3530282074319827871L;

	private String appId;
	
	private String apiKey;
	
	private String orderNo;
	
	private String channel;
	
	private String amount;
	
	private String clientIp;
	
	public ResultCmccMmWaterWestParam() {
		setTagName("cmccmmwaterwest");
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	
}
