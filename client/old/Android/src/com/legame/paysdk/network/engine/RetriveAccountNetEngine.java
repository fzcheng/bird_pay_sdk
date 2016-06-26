package com.legame.paysdk.network.engine;

import java.util.Map;
import java.util.TreeMap;

import android.content.Context;

import com.legame.paysdk.network.engine.cachestrategy.CacheStrategy;
import com.legame.paysdk.network.resultdata.RetriveAccountResultData;


/** 
 * 类说明：   
 * @author  xinhui.cheng
 * @date    2013-10-15
 * @version 1.0
 */
public class RetriveAccountNetEngine extends BaseNetEngine {

	private static final String COMMAND = "retrive_account";
	private String mPhone;
	private String mVcode;
	
	public RetriveAccountNetEngine(String phone , String vcode){
		mHttpMethod = HTTP_POST;
		mCacheStrategy = new CacheStrategy(false);
		mResultData = new RetriveAccountResultData();
		mPhone = phone;
		mVcode = vcode;
	}
	
	@Override
	protected String getCommand() {
		return COMMAND;
	}
	
	@Override
	protected Map<String, String> getParams(Context c) {
		TreeMap<String, String> map = new TreeMap<String, String>();
		map.put("phone", mPhone);
		map.put("vcode", mVcode);
		return map;
	}

}
