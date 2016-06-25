package com.cheyooh.service.sdk.idata;

import com.cheyooh.service.framework.idata.ResultContent;


public class ResultGetInitalParam extends ResultContent {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7403612658434352107L;
	
	private String gamePaymentName;
	
	private int gamePaymentIdx;
	
	public ResultGetInitalParam() {
		super("type");
	}

	public String getGamePaymentName() {
		return gamePaymentName;
	}

	public void setGamePaymentName(String gamePaymentName) {
		this.gamePaymentName = gamePaymentName;
	}

	public int getGamePaymentIdx() {
		return gamePaymentIdx;
	}

	public void setGamePaymentIdx(int gamePaymentIdx) {
		this.gamePaymentIdx = gamePaymentIdx;
	}
	
}
