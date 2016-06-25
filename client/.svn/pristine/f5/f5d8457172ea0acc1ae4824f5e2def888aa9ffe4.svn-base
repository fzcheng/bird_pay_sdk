package com.legame.paysdk.network.engine;

import java.util.Map;
import java.util.TreeMap;

import android.content.Context;

import com.legame.paysdk.network.engine.cachestrategy.CacheStrategy;
import com.legame.paysdk.network.resultdata.SimpleResultData;


/** 
 * 类说明：   
 * @author  xinhui.cheng
 * @date    2013-10-15
 * @version 1.0
 */
public class BindPhoneNetEngine extends BaseNetEngine {

	private static final String COMMAND = "bind_phone";
	
	private String mSid;
	private String mVcode;
	private boolean mbContinue = false;
	
	public BindPhoneNetEngine(String sid, String vcode){
		mHttpMethod = HTTP_POST;
		mCacheStrategy = new CacheStrategy(false);
		mResultData = new SimpleResultData(COMMAND);
		mSid = sid;
		mVcode = vcode;
	}
	
	public BindPhoneNetEngine(String sid, String vcode, boolean cont) {
		this(sid, vcode);
		mbContinue = cont;
	}
	
	@Override
	protected String getCommand() {
		return COMMAND;
	}
	
	@Override
	protected Map<String, String> getParams(Context c) {
		TreeMap<String, String> map = new TreeMap<String, String>();
		map.put("sid", mSid);
		map.put("vcode", mVcode);
		if (mbContinue) {
			map.put("continue_confirm", "1");
		}
		return map;
	}

	@Override
	public void onSidRefreshed(String newSid) {
		mSid = newSid;
	}
	
	public void setContinue(boolean isContinue) {
		mbContinue = isContinue;
	}
}
