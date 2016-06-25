package com.cheyooh.service.sdk.idata;

import com.cheyooh.service.framework.utils.annotation.NotNull;

public class CmdUserLogout  extends CmdGeneral{

	private static final long serialVersionUID = 3395690621141181566L;
	
	@NotNull 
	private String user_name;
	



	public String getUser_name() {
		return user_name;
	}


	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	

	
}
