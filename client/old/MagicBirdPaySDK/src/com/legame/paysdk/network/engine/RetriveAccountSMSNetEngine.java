package com.legame.paysdk.network.engine;

import java.util.Map;
import java.util.TreeMap;

import com.legame.paysdk.network.engine.cachestrategy.CacheStrategy;
import com.legame.paysdk.network.resultdata.SimpleResultData;

import android.content.Context;


/** 
 * 类说明：   
 * @author  xinhui.cheng
 * @date    2013-10-15
 * @version 1.0
 */
public class RetriveAccountSMSNetEngine extends BaseNetEngine {

	private static final String COMMAND = "retrive_account_sms";
	private String mPhone;
	
	public RetriveAccountSMSNetEngine(String phone){
		mHttpMethod = HTTP_POST;
		mCacheStrategy = new CacheStrategy(false);
		mResultData = new SimpleResultData(COMMAND);
		mPhone = phone;
	}
	
	@Override
	protected String getCommand() {
		return COMMAND;
	}
	
	@Override
	protected Map<String, String> getParams(Context c) {
		TreeMap<String, String> map = new TreeMap<String, String>();
		map.put("phone", mPhone);
		return map;
	}

}
