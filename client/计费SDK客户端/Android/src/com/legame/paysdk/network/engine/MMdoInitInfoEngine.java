package com.legame.paysdk.network.engine;

import java.util.HashMap;
import java.util.Map;

import com.legame.paysdk.network.resultdata.MMdoInitInfoResultData;

import android.content.Context;

public class MMdoInitInfoEngine extends BaseNetEngine{
	
	private static final String METHOD = "Get_swb_cmcc";
	
	
	public MMdoInitInfoEngine() {
		mHttpMethod = HTTP_GET;
		mResultData = new MMdoInitInfoResultData(METHOD);
	}
	
	@Override
	protected String getCommand() {
		// TODO Auto-generated method stub
		return METHOD;
	}

	@Override
	protected Map<String, String> getParams(Context c) {
		// TODO Auto-generated method stub
		return new HashMap<String, String>();
	}

}
