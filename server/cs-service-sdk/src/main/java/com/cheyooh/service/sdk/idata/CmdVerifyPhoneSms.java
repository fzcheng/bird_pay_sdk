package com.cheyooh.service.sdk.idata;

import com.cheyooh.service.framework.utils.annotation.Match;
import com.cheyooh.service.framework.utils.annotation.NotNull;

/**
 * 绑定手机-发送短信
 * @author zhouzg@cheyooh.com
 *
 */
public class CmdVerifyPhoneSms extends CmdGeneral {
 
	private static final long serialVersionUID = 1314910920820392293L;

	@NotNull
	@Match(type="mobile")
	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
