package com.cheyooh.service.sdk.idata;



public class CmdFastPay extends CmdPay{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8393106496717486813L;

	private String imsi;
	private String sdkver;

	
	public String getSdkver() {
		return sdkver;
	}

	public void setSdkver(String sdkver) {
		this.sdkver = sdkver;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

}
