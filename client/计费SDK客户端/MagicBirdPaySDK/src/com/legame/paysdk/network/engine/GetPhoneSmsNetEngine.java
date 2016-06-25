package com.legame.paysdk.network.engine;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.text.TextUtils;

import com.legame.paysdk.GlobalVal;
import com.legame.paysdk.network.resultdata.FirstSmsResultData;
import com.legame.paysdk.network.utils.NetTools;

/** 
 * 类说明：   
 * @author  Terry.Lu
 * @date    2013年10月15日
 * @version 1.0
 */
public class GetPhoneSmsNetEngine extends BaseNetEngine {
	private static final String METHOD = "Fetch_phone";
	private String imsi ;
	
	public GetPhoneSmsNetEngine(String imsi) {
//		Log.i(TAG,"发送短信获取手机号码");
		mResultData = new FirstSmsResultData(METHOD);
		mHttpMethod = HTTP_POST;
		this.imsi = imsi;
	}

	@Override
	protected String getCommand() {
		return METHOD;
	}

	@Override
	protected Map<String, String> getParams(Context c) {
		Map<String, String> params = new HashMap<String, String>();
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
}
