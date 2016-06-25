package com.cheyooh.service.sdk.idata;

import com.cheyooh.service.framework.idata.ResultContent;

public class ResultGiftVCode extends ResultContent{ 
	private static final long serialVersionUID = -1854124894757325618L;
  
	public ResultGiftVCode(String vcode){
		super("accept_gift");
		
		this.vcode=vcode;
	}
	
	/**
	 * 验证码
	 */
	private String vcode;

	public String getVcode() {
		return vcode;
	}
	public void setVcode(String vcode) {
		this.vcode = vcode;
	}
}
