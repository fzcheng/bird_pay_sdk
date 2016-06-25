package com.cheyooh.service.sdk.idata;

import com.cheyooh.service.framework.idata.ResultContent;

public class Resultcmccgamebase extends ResultContent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6062343879995148393L;
	
	private String isRepeated;
	
	private String billingIndex;
	
	private String cpParam;
	
	public Resultcmccgamebase() {
		setTagName("cmccgamebase");
	}

	public String getIsRepeated() {
		return isRepeated;
	}

	public void setIsRepeated(String isRepeated) {
		this.isRepeated = isRepeated;
	}

	public String getBillingIndex() {
		return billingIndex;
	}

	public void setBillingIndex(String billingIndex) {
		this.billingIndex = billingIndex;
	}

	public String getCpParam() {
		return cpParam;
	}

	public void setCpParam(String cpParam) {
		this.cpParam = cpParam;
	}
	
}
