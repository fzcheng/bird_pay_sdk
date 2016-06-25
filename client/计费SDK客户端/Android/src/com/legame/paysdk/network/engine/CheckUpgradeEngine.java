package com.legame.paysdk.network.engine;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.text.TextUtils;

import com.legame.paysdk.GlobalVal;
import com.legame.paysdk.network.resultdata.CheckUpgradeResultData;
import com.legame.paysdk.network.utils.NetTools;

public class CheckUpgradeEngine extends BaseNetEngine {
	private static final String CMD = "upgrade";
	
	private String imsi;

	public CheckUpgradeEngine(String imsi) {
		mHttpMethod = HTTP_GET;
		mResultData = new CheckUpgradeResultData();
		this.imsi = imsi;
	}

	@Override
	protected Map<String, String> getParams(Context c) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("version_code", String.valueOf(NetTools.getVersionCode(c)));
		if(TextUtils.isEmpty(imsi)){
			params.put("imsi", "");
		}else {
			params.put("imsi", imsi);
		}
		String imei = NetTools.getIMEI(c);
		if (imei == null) {
			imei = NetTools.getLocalMacAddress(c);
		}
		params.put("imei", imei);
		return params;
	}

	@Override
	protected String getCommand() {
		return CMD;
	}

}
