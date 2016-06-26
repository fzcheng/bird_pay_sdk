package com.legame.leyo.smspay;


/**
 * 初始化回调处理
 * 
 * @author dingming xiao
 */
public abstract interface OnInitCallBackListener {



	/***
	 * 成功返回的消息体
	 * 
	 * @param bundle
	 */
	public abstract void onSuccess();

	/**
	 * 失败返回的状态值
	 * 
	 * @param code
	 * @param Meg
	 */
	public abstract void onFailture(int returncode);
}
