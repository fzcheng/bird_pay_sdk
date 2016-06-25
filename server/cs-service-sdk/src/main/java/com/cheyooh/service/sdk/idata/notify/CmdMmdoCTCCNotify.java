package com.cheyooh.service.sdk.idata.notify;

import com.cheyooh.service.framework.idata.Cmd;

public class CmdMmdoCTCCNotify extends Cmd{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8673609159605015509L;

	private String mobile;
	
	private String passwd;
	
	private String type;
	
	private String status;
	
	private String msg;
	
	private String LinkID;
	
	private String MsgID;
	
	private String DestAddr;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getLinkID() {
		return LinkID;
	}

	public void setLinkID(String linkID) {
		LinkID = linkID;
	}

	public String getMsgID() {
		return MsgID;
	}

	public void setMsgID(String msgID) {
		MsgID = msgID;
	}

	public String getDestAddr() {
		return DestAddr;
	}

	public void setDestAddr(String destAddr) {
		DestAddr = destAddr;
	}
	
	
	
	
}
