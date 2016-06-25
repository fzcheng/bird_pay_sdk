package com.cheyooh.service.sdk.idata.gameserver;

import java.util.ArrayList;
import java.util.List;

public class JsonCtccXinwangRes {
	
	private String result;
	
	private String sms;
	
	private List<JsonCtccXinwangOrderNoRes> detail=new ArrayList<>();

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getSms() {
		return sms;
	}

	public void setSms(String sms) {
		this.sms = sms;
	}

	public List<JsonCtccXinwangOrderNoRes> getDetail() {
		return detail;
	}

	public void setDetail(List<JsonCtccXinwangOrderNoRes> detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "JsonCtccXinwangRes [result=" + result + ", sms=" + sms
				+ ", detail=" + detail + "]";
	}

}
