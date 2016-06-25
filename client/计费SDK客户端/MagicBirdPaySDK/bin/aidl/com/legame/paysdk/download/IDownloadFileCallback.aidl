package com.legame.paysdk.download;

interface IDownloadFileCallback {

	/**
	 * 回调函数，在http下载过程中发生错误后会回调该函数
	 * 
	 * @param indentifier
	 *            下载任务标识
	 * 
	 * @param msg
	 *            错误原因
	 * */
	void onError(String indentifier, int msg);
	
	
	/**
	 * 回调函数，通知下载的文件总大小
	 * 
	 * @param size
	 *            文件大小，单位bytes
	 * */
	void OnGetContentLength(String indentifier, long size);
	
	
	/**
	 * 回调函数，通知下载已经完成
	 * */
	void onDownloadFinish(String indentifier);
	
	
	/**
	 * 回调函数，通知用户的取消操作已经完成
	 * */
	void OnUserCanceled(String indentifier);
	
	
	/**
	 * 回调函数，通知用户当前缓冲的情况
	 * */
	void OnBufferUpdate(String indentifier, long downloadedSize);
	
}