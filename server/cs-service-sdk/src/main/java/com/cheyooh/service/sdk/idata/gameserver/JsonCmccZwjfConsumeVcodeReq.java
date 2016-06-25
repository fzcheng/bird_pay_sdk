package com.cheyooh.service.sdk.idata.gameserver;

public class JsonCmccZwjfConsumeVcodeReq {
	private String vcode;
	
	private String merchantcode;
	
	private String consumetime;

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	public String getMerchantcode() {
		return merchantcode;
	}

	public void setMerchantcode(String merchantcode) {
		this.merchantcode = merchantcode;
	}

	public String getConsumetime() {
		return consumetime;
	}

	public void setConsumetime(String consumetime) {
		this.consumetime = consumetime;
	}

	@Override
	public String toString() {
		return "JsonCmccZwjfConsumeVcodeReq [vcode=" + vcode
				+ ", merchantcode=" + merchantcode + ", consumetime="
				+ consumetime + "]";
	}
	
	
}
