package com.cheyooh.service.sdk.idata;

import com.cheyooh.service.framework.idata.ResultContent;

public class ResultOneKeyRegister extends ResultContent{
 
	private static final long serialVersionUID = -2592466526984032034L;

	
	private int name;
	
	private String password;
 	 	
	public ResultOneKeyRegister(){
		super("user");
	}
	 
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
 
	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}
	
}
