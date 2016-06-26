package com.legame.paysdk.network.engine;

import java.util.Map;
import java.util.TreeMap;

import android.content.Context;

import com.legame.paysdk.network.engine.cachestrategy.CacheStrategy;
import com.legame.paysdk.network.resultdata.SimpleResultData;
import com.legame.paysdk.utils.CryptoUtil;


/** 
 * 类说明：   
 * @author  xinhui.cheng
 * @date    2013-10-14
 * @version 1.0
 */
public class ModifyPwdNetEngine extends BaseNetEngine {

	private static final String COMMAND = "modify_pwd";
	
	private String mSid;
	private String mOldPwd;
	private String mNewPwd;
	
	public ModifyPwdNetEngine(String sid,String oldPwd,String newPwd){
		mHttpMethod = HTTP_POST;
		mCacheStrategy = new CacheStrategy(false);
		mResultData  = new SimpleResultData(COMMAND);
		mSid = sid;
		mOldPwd = oldPwd;
		mNewPwd = newPwd;
	}
	
	@Override
	protected String getCommand() {
		return COMMAND;
	}
	
	@Override
	protected Map<String, String> getParams(Context c) {
		TreeMap<String, String> map = new TreeMap<String, String>();
		map.put("sid", mSid);
		map.put("old_pwd", CryptoUtil.encrypt(mOldPwd, CryptoUtil.SERVICE_KEY));
		map.put("new_pwd", CryptoUtil.encrypt(mNewPwd, CryptoUtil.SERVICE_KEY));
		return map;
	}

	@Override
	public void onSidRefreshed(String newSid) {
		mSid = newSid;
	}
}
