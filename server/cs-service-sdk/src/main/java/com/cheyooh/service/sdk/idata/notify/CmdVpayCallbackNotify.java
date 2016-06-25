package com.cheyooh.service.sdk.idata.notify;

import com.cheyooh.service.framework.idata.Cmd;

public class CmdVpayCallbackNotify extends Cmd{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4051371928157192848L;

	private String uid;
	
	private String mob;
	
	private String oid;
	
	private String md5;
	
	private Integer mz;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getMob() {
		return mob;
	}

	public void setMob(String mob) {
		this.mob = mob;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public Integer getMz() {
		return mz;
	}

	public void setMz(Integer mz) {
		this.mz = mz;
	}
	
	
}
