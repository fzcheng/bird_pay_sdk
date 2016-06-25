package com.legame.paysdk.download;

/** 
 * 类说明：   
 * @author  Toby.chen
 * @date    2013-7-11
 * @version 1.0
 */

public class FileDownloadModel {
	
	public static final int CANCEL_BY_USER = 0;
	public static final int CANCEL_BY_ERROR = 1;
	
	public static final int DOWNLOADING = 0;
	public static final int PREPARE_DOWNLOADING = 1;
	public static final int DOWNLOAD_ERROR = 2;
	public static final int PREPARE_CANCEL_DOWNLOAD = 3;
	public static final int DOWNLOAD_CANCELED = 4;
	public static final int DOWNLOAD_OVER_AND_INSTALLED = 5;
	public static final int DOWNLOAD_OVER_AND_UNINSTALL = 6;
	
	private String mIndentfier = null;
	private String mCreateTime = null;
	private String mPackageName = null;
	private String mGameName = null;
	private String mDownloadUrl = null;
	private String mPicUrl = null;
	private long mTotalSize = -1; 
	private int mCancelStatus = CANCEL_BY_USER;
	
	private int mDownloadStatus = 0;
	
	public FileDownloadModel(){}
	
	public FileDownloadModel(String ct, String pkgName, String gn, String dlUrl, String picUrl, long size, int status){
		mCreateTime = ct;
		mPackageName = pkgName;
		mIndentfier = "" + mPackageName.hashCode();
		mGameName = gn;
		mDownloadUrl = dlUrl;
		mPicUrl = picUrl;
		mTotalSize = size;
		mCancelStatus = status;
	}
	
	public int getDownloadStatus(){
		return mDownloadStatus;
	}
	
	public void setDownloadStatus(int status){
		mDownloadStatus = status;
	}
	
	public String getIndentfier() {
		return mIndentfier;
	}
	
	public String getCreateTime() {
		return mCreateTime;
	}
	
	public void setCreateTime(String createTime) {
		mCreateTime = createTime;
	}
	
	public String getPackageName() {
		return mPackageName;
	}
	
	public void setPackageName(String packageName) {
		mPackageName = packageName;
		mIndentfier = "" + mPackageName.hashCode();
	}
	
	public String getGameName() {
		return mGameName;
	}
	
	public void setGameName(String gameName) {
		mGameName = gameName;
	}
	
	public String getDownloadUrl() {
		return mDownloadUrl;
	}
	
	public void setDownloadUrl(String downloadUrl) {
		mDownloadUrl = downloadUrl;
	}
	
	public String getPicUrl() {
		return mPicUrl;
	}
	
	public void setPicUrl(String picUrl) {
		mPicUrl = picUrl;
	}
	
	public long getTotalSize() {
		return mTotalSize;
	}
	
	public void setTotalSize(long totalSize) {
		mTotalSize = totalSize;
	}
	
	public int getCancelStatus() {
		return mCancelStatus;
	}
	
	public void setCancelStatus(int cancelStatus) {
		mCancelStatus = cancelStatus;
	}
}
