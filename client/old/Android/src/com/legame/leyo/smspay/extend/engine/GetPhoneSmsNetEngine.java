package com.legame.leyo.smspay.extend.engine;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.legame.leyo.smspay.util.ReflectUtil;
import com.legame.paysdk.GlobalVal;
import com.legame.paysdk.network.engine.BaseNetEngine;
import com.legame.paysdk.network.resultdata.FirstSmsResultData;
import com.legame.paysdk.network.utils.NetTools;
import com.legame.paysdk.utils.Constants;
import com.legame.paysdk.utils.DataUtils;

/** 
 * 类说明：   
 * @author  Kaiguang
 * @date    2015/7/30
 * 说明：		主要是可以进行在线jar包更新，不需要整包
 * 			更新。把base包上的类移动到更新jar包
 *	      	类上。并且增加了短信中心号的字段
 */
public class GetPhoneSmsNetEngine extends BaseNetEngine {
	private static final String METHOD = "Fetch_phone";
	
	private String smscenterNumber;
	
	private String iccid;
	
	private String imsi;
	
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
		
		if(smscenterNumber != null){
			params.put("smscenternumber", smscenterNumber);
		}
		SharedPreferences sp = c.getSharedPreferences("leyoDataInfo", Context.MODE_PRIVATE);
		params.put("latitude", sp.getString("gpsLatitude", ""));
		params.put("longitude", sp.getString("gpsLongitude", ""));	
		
		
		params.put("iccid", iccid);
		return params;
	}

	public void setSmscenterNumber(String smscenterNumber) {
		this.smscenterNumber = smscenterNumber;
	}
	
	public void setIccid(String iccid){
		if(iccid == null){
			iccid = "";
		}
		this.iccid = iccid;
	}

}
