package com.cheyooh.service.sdk.idata.gameserver;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonCuccYijianWomusicSyncOrder {
	
	private String appId;
	
	private String orderId;
	
	private String sequence;
	
	private String money;
	
	private String imsi;
	
	private String imei;
	
	private String ipAddr;
	
	@JsonProperty
	private String VCode;
	
	private String sendStatus;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

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

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	@JsonProperty
	public String getVCode() {
		return VCode;
	}
	@JsonProperty
	public void setVCode(String vCode) {
		VCode = vCode;
	}

	public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	@Override
	public String toString() {
		return "JsonCuccYijianWomusicSyncOrder [appId=" + appId + ", orderId="
				+ orderId + ", sequence=" + sequence + ", money=" + money
				+ ", imsi=" + imsi + ", imei=" + imei + ", ipAddr=" + ipAddr
				+ ", VCode=" + VCode + ", sendStatus=" + sendStatus + "]";
	}

}
