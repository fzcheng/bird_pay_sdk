package com.cheyooh.service.sdk.idata.gameserver;

import com.cheyooh.service.framework.idata.Cmd;
import com.cheyooh.service.framework.utils.annotation.NotNull;

public class CmdQueryUser extends Cmd{
	private static final long serialVersionUID = -7419937945133971343L;
	@NotNull
	private String sid;

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}
}
