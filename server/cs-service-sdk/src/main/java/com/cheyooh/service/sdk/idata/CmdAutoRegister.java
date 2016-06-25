package com.cheyooh.service.sdk.idata;

import com.cheyooh.service.framework.utils.annotation.NotNull;


public class CmdAutoRegister extends CmdGeneral{
	 
	private static final long serialVersionUID = 3395690621141183096L;
	
	@NotNull
	private String imei;

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}
 
	 
}
