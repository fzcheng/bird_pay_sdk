package com.cheyooh.service.sdk.idata;

import com.cheyooh.service.framework.idata.ResultContent;

public class ResultSmsUpPort extends ResultContent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7070911816692315039L;
	private String sdkSmsUpPort;
	private String sdkSmsContent;
	
	public ResultSmsUpPort() {
		super("item");
	}

	public String getSdkSmsUpPort() {
		return sdkSmsUpPort;
	}

	public void setSdkSmsUpPort(String sdkSmsUpPort) {
		this.sdkSmsUpPort = sdkSmsUpPort;
	}

	public String getSdkSmsContent() {
		return sdkSmsContent;
	}

	public void setSdkSmsContent(String sdkSmsContent) {
		this.sdkSmsContent = sdkSmsContent;
	}

	@Override
	public String toString() {
		return "ResultSmsUpPort [sdkSmsUpPort=" + sdkSmsUpPort
				+ ", sdkSmsContent=" + sdkSmsContent + "]";
	}

	
	
}
