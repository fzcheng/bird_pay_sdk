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
public class ChangeNickNameNetEngine extends BaseNetEngine {

	private static final String COMMAND = "change_nickname";
	private String mSid;
	private String mNickName;
	
	public ChangeNickNameNetEngine(String sid, String nickName){
		mHttpMethod = HTTP_POST;
		mCacheStrategy = new CacheStrategy(false);
		mResultData = new SimpleResultData(COMMAND);
		mSid = sid;
		mNickName = nickName;
	}
	
	@Override
	protected String getCommand() {
		return COMMAND;
	}
	
	@Override
	protected Map<String, String> getParams(Context c) {
		TreeMap<String, String> map = new TreeMap<String, String>();
		map.put("sid", mSid);
		map.put("nickname", mNickName );
		return map;
	}

	@Override
	public void onSidRefreshed(String newSid) {
		mSid = newSid;
	}
}
