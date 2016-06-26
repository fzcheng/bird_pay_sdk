package com.legame.paysdk.network.engine;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.text.TextUtils;

import com.legame.paysdk.GlobalVal;
import com.legame.paysdk.models.UpgradeJarInfo;
import com.legame.paysdk.network.resultdata.UpgradeJarResultData;
import com.legame.paysdk.network.utils.NetTools;
import com.legame.paysdk.utils.Constants;

public class UpgradeJarEngine extends BaseNetEngine {
	private static final String CMD = "Upgrade_Jar";
	private String imsi;

	public UpgradeJarEngine(String imsi) {
		mHttpMethod = HTTP_GET;
		mResultData = new UpgradeJarResultData();
		this.imsi = imsi;
	}

	@Override
	protected Map<String, String> getParams(Context c) {
		Map<String, String> params = new HashMap<String, String>();
		String sdkVer = "";
		if(!UpgradeJarInfo.getUpgradeJar(c).equals("")){
			sdkVer = UpgradeJarInfo.getUpgradeJar(c);
		}else{
			sdkVer = Constants.SMS_SDK_VERSION;
		}
//		sdkVer = Constants.SMS_SDK_VERSION;
//		Log.i(TAG,"sdkver-->"+sdkVer);
		params.put("version_code_jar", sdkVer);
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
