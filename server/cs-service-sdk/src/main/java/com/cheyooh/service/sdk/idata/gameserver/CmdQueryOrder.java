package com.cheyooh.service.sdk.idata.gameserver;

import com.cheyooh.service.framework.idata.Cmd;
import com.cheyooh.service.framework.utils.annotation.Match;
import com.cheyooh.service.framework.utils.annotation.NotNull;

public class CmdQueryOrder extends Cmd{
	private static final long serialVersionUID = -7419937945133971343L;
	
	@NotNull
	@Match(regex="[0-9]{16}")
	private String order_no;
	
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

 
}
