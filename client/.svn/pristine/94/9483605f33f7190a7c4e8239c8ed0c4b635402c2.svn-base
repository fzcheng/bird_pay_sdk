package com.legame.paysdk.network.engine;

import java.util.HashMap;
import java.util.Map;

import com.legame.paysdk.network.engine.cachestrategy.CacheStrategy;
import com.legame.paysdk.network.resultdata.SimpleResultData;

import android.content.Context;

/** 
 * 类说明：   
 * @author  Terry.Lu
 * @date    2013年10月25日
 * @version 1.0
 */
public class LogoutNetEngine extends BaseNetEngine {
	private static final String METHOD = "user_logout";
	private String mSid;
	private String mUsername;
	
	public LogoutNetEngine(String username, String sid) {
		mUsername = username;
		mSid = sid;
		mHttpMethod = HTTP_POST;
		mCacheStrategy = new CacheStrategy(false);
		mResultData = new SimpleResultData(METHOD);
	}
	
	@Override
	protected Map<String, String> getParams(Context c) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("user_name", mUsername);
		map.put("sid", mSid);
		return map;
	}
	
	@Override
	protected String getCommand() {
		return METHOD;
	}
}
