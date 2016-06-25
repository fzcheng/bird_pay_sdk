package com.cheyooh.service.sdk.idata;

import com.cheyooh.service.framework.idata.ResultContentList;

public class ResultPayMmdoCommandList extends ResultContentList {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1329122544361275331L;

	private String imsi;

	private Integer time = 5;

	private String status;
	
	/*flagSend 01-无验证码需要发短信，10-有验证码不需要发短信，11-有验证码需要发短信。
	默认值01，前面一个是有无验证码的标志位，后面一位是是否发短信的标志位。*/
	private String flagSend="01";

	public ResultPayMmdoCommandList() {
		super.setTagName("commands");
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFlagSend() {
		return flagSend;
	}

	public void setFlagSend(String flagSend) {
		this.flagSend = flagSend;
	}

}
