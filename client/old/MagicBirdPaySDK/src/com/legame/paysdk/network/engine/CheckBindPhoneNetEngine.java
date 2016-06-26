package com.legame.paysdk.network.engine;

import java.util.Map;
import java.util.TreeMap;

import com.legame.paysdk.network.engine.cachestrategy.CacheStrategy;
import com.legame.paysdk.network.resultdata.CheckBindPhoneResultData;

import android.content.Context;


/** 
 * 类说明：   
 * @author  xinhui.cheng
 * @date    2013-10-14
 * @version 1.0
 */
public class CheckBindPhoneNetEngine extends BaseNetEngine {

	private String mSid;
	
	public CheckBindPhoneNetEngine(String sid){
		mHttpMethod = HTTP_POST;
		mCacheStrategy = new CacheStrategy(false);
		mResultData = new CheckBindPhoneResultData();
		mSid = sid;
	}
	
	@Override
	protected Map<String, String> getParams(Context c) {
		TreeMap<String, String> map = new TreeMap<String, String>();
		map.put("sid", mSid);
		return map;
	}

	@Override
	public void onSidRefreshed(String newSid) {
		mSid = newSid;
	}
}
