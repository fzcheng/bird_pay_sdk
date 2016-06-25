package com.cheyooh.service.sdk.idata;

import com.cheyooh.service.framework.utils.annotation.NotNull;

/**
 * 绑定手机
 * 
 * @author zhouzg@cheyooh.com
 *
 */
public class CmdVerifyPhone extends CmdGeneral {
 
	private static final long serialVersionUID = -1172985239449157448L;

	
	/**
	 * 收到的短信验证码
	 */
	@NotNull
	private String vcode;

	private String phone;
	
	private String continue_confirm;

	public String getVcode() {
		return vcode;
	}


	public void setVcode(String vcode) {
		this.vcode = vcode;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getContinue_confirm() {
		return continue_confirm;
	}


	public void setContinue_confirm(String continue_confirm) {
		this.continue_confirm = continue_confirm;
	}
}
