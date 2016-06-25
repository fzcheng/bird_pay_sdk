package com.legame.paysdk.network.engine;

import java.util.Map;
import java.util.TreeMap;

import com.legame.paysdk.network.engine.cachestrategy.CacheStrategy;
import com.legame.paysdk.network.resultdata.OnekeyRegisterResultData;

import android.content.Context;


/** 
 * 类说明：   
 * @author  xinhui.cheng
 * @date    2013-10-14
 * @version 1.0
 */
public class OnekeyRegisterNetEngine extends BaseNetEngine {

	private String mDeviceId;
	
	public OnekeyRegisterNetEngine(String deviceId){
		mHttpMethod = HTTP_POST;
		mCacheStrategy = new CacheStrategy(false);
		mResultData = new OnekeyRegisterResultData();
		mDeviceId = deviceId;
	}
	
	@Override
	protected String getCommand() {
		return "one_key_register";
	}
	
	@Override
	protected Map<String, String> getParams(Context c) {
		TreeMap<String, String> map = new TreeMap<String, String>();
		map.put("device_id", mDeviceId);
		return map;
	}

}
