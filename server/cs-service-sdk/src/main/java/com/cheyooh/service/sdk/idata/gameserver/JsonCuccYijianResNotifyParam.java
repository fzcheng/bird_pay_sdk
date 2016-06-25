package com.cheyooh.service.sdk.idata.gameserver;

public class JsonCuccYijianResNotifyParam {
	
	private String orderId;
	
	private String sequence;
	
	private String appId;
	
	private String imsi;
	
	private String provinceId;
	
	private String channelId;
	
	private String serviceType;
	
	private String money;
	
	private String status;
	
	private String extData;
	
	private String resultMsg;
	
	private String feetime;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getExtData() {
		return extData;
	}

	public void setExtData(String extData) {
		this.extData = extData;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public String getFeetime() {
		return feetime;
	}

	public void setFeetime(String feetime) {
		this.feetime = feetime;
	}

	@Override
	public String toString() {
		return "JsonCuccYijianResNotifyParam [orderId=" + orderId
				+ ", sequence=" + sequence + ", appId=" + appId + ", imsi="
				+ imsi + ", provinceId=" + provinceId + ", channelId="
				+ channelId + ", serviceType=" + serviceType + ", money="
				+ money + ", status=" + status + ", extData=" + extData
				+ ", resultMsg=" + resultMsg + ", feetime=" + feetime + "]";
	}
	
}
