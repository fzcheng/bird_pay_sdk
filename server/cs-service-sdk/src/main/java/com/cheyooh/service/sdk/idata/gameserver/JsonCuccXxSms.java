package com.cheyooh.service.sdk.idata.gameserver;

public class JsonCuccXxSms {
	
	private  String code;
	
	private  String innercode;
	
	private  String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getInnercode() {
		return innercode;
	}

	public void setInnercode(String innercode) {
		this.innercode = innercode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "JsonCuccXxSms [code=" + code + ", innercode=" + innercode
				+ ", message=" + message + "]";
	}
	
	
}
