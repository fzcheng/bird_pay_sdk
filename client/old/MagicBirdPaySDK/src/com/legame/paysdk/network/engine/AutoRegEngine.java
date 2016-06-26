package com.legame.paysdk.network.engine;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;

import com.legame.paysdk.network.resultdata.AutoRegResultData;
import com.legame.paysdk.network.utils.NetTools;

public class AutoRegEngine extends BaseNetEngine {
	private static final String TAG = "AutoRegEngine";
	private static final String CMD = "auto_register";
	private static final String PARAM_IMEI = "imei";

	public AutoRegEngine() {
		mHttpMethod = HTTP_GET;
		mResultData = new AutoRegResultData();
	}

	@Override
	protected Map<String, String> getParams(Context c) {
		String imei = NetTools.getIMEI(c);
		if (imei == null) {
			imei = NetTools.getLocalMacAddress(c);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put(PARAM_IMEI, imei);
		return map;
	}

	@Override
	protected String getCommand() {
		return CMD;
	}

}
