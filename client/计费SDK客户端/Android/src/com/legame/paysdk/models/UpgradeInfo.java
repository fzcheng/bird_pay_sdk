package com.legame.paysdk.models;

public class UpgradeInfo {
	private boolean mForce;
	private boolean mStatus;
	private String mDownloadUrl;
	private String phone;
	private String upport;
	
	private int versionCode;


	public UpgradeInfo() {
		//
	}

	public UpgradeInfo(boolean force, boolean status, String downloadUrl) {
		mForce = force;
		mStatus = status;
		mDownloadUrl = downloadUrl;
	}

	public boolean getForce() {
		return mForce;
	}

	public void setForce(boolean mForce) {
		this.mForce = mForce;
	}

	public boolean getStatus() {
		return mStatus;
	}

	public void setStatus(boolean mStatus) {
		this.mStatus = mStatus;
	}

	public String getDownloadUrl() {
		return mDownloadUrl;
	}

	public void setDownloadUrl(String mDownloadUrl) {
		this.mDownloadUrl = mDownloadUrl;
	}
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUpport() {
		return upport;
	}

	public void setUpport(String upport) {
		this.upport = upport;
	}
	
	public void setVersioncode(int versionCode){
		this.versionCode = versionCode;
	}
	
	public int getVersioncode(){
		return versionCode;
	}
}