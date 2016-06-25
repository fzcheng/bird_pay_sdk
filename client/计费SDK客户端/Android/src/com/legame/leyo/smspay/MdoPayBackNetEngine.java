package com.legame.leyo.smspay;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;

import com.legame.paysdk.network.engine.BaseNetEngine;
import com.legame.paysdk.network.utils.NetTools;

/** 
 * 类说明：   mdo支付上传支付结果
 * @author  Shaohui.Yang
 * @date    2014年6月10日
 * @version 1.0
 */
public class MdoPayBackNetEngine extends BaseNetEngine{

	private static final String METHOD = "payback_mmdo";
	private String mSid;
	private String mImsi;
	private String mOrderNo;
	private String mNumber;
	private String mContent;
	private String mState;
	private String mSms_type;
	private String mIMEI;
	private String originalcode;
	
	public MdoPayBackNetEngine(String sid, HashMap<String,String> mapMdoPayBack){
		mHttpMethod = HTTP_POST;
		mSid = sid;
		mImsi = mapMdoPayBack.get("imsi");
		mOrderNo = mapMdoPayBack.get("orderNo");
		mNumber = mapMdoPayBack.get("number");
		mContent = mapMdoPayBack.get("content");
		mState = mapMdoPayBack.get("state");
		mSms_type = mapMdoPayBack.get("sms_type");
		mIMEI = mapMdoPayBack.get("imei");
		originalcode = mapMdoPayBack.get("originalcode");
		if(originalcode == null){
			originalcode = "";
		}
		mResultData = new MdoPayBackResultData();
	}
	
	@Override
	protected String getCommand() {
		return METHOD;
	}
	
	@Override
	protected Map<String, String> getParams(Context c) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("sid", mSid);
		map.put("imsi", mImsi);
		map.put("order_no", mOrderNo);
		map.put("number", mNumber);
		map.put("content", mContent);
		map.put("state", mState);
		map.put("sms_type", mSms_type);
		map.put("originalcode", originalcode);
		if(mIMEI == null){
			mIMEI = NetTools.getLocalMacAddress(c);
		}
		map.put("imei", mIMEI);
		return map;
	}

}
