package com.cheyooh.service.sdk.idata.notify;

import com.cheyooh.service.framework.idata.Cmd;

public class CmdMmdoNotify extends Cmd{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6917481208444441206L;

	private String linkid;
	
	private String spid;
	
	private String cmd;
	
	private String mobile;
	
	private String spnum;
	
	private String key;

	public String getLinkid() {
		return linkid;
	}

	public void setLinkid(String linkid) {
		this.linkid = linkid;
	}

	public String getSpid() {
		return spid;
	}

	public void setSpid(String spid) {
		this.spid = spid;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSpnum() {
		return spnum;
	}

	public void setSpnum(String spnum) {
		this.spnum = spnum;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	
}
