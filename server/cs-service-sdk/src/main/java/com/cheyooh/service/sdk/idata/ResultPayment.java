package com.cheyooh.service.sdk.idata;

import com.cheyooh.service.framework.idata.ResultContent;

public class ResultPayment extends ResultContent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6701443534614793904L;
	
	
	private String name;
	private int show;
	
	public ResultPayment() {
		super("type");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getShow() {
		return show;
	}

	public void setShow(int show) {
		this.show = show;
	}

	
}
