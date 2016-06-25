package com.cheyooh.service.sdk.idata;

import com.cheyooh.service.framework.utils.annotation.NotNull;


public class CmdUserLogin extends CmdGeneral{
	 
	private static final long serialVersionUID = 3395690621141183096L;
	
	@NotNull
	private String user_name;

	/**
	 *  密码
	 */
	@NotNull
	private String pwd;
	
	
	/**
	 * 新密码，如果该参数不为空，则表示该操作需要修改密码并登录。
	 */
	private String new_pwd;
	
	/**
	 * 系统版本
	 */
	private String osversion;
	
	/**
	 * 机型
	 */
	private String model;


	public String getUser_name() {
		return user_name;
	}


	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public String getNew_pwd() {
		return new_pwd;
	}


	public void setNew_pwd(String new_pwd) {
		this.new_pwd = new_pwd;
	}
 
	public String getOsversion() {
		return osversion;
	}
	
	public void setOsversion(String osversion) {
		this.osversion = osversion;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
}
