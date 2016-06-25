package com.legame.paysdk.network.engine;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;

import com.legame.paysdk.network.resultdata.SidUpdateResultData;

/** 
 * 类说明：   
 * @author  Terry.Lu
 * @date    2013年10月14日
 * @version 1.0
 */
public class SidUpdateNetEngine extends BaseNetEngine {
	private static final String METHOD = "refresh_sid";
	private String mSid;
	
	public SidUpdateNetEngine(String sid) {
		mSid = sid;
		mResultData = new SidUpdateResultData();
	}
	
	@Override
	protected Map<String, String> getParams(Context c) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("sid", mSid);
		return map;
	}

	@Override
	protected String getCommand() {
		return METHOD;
	}
}
