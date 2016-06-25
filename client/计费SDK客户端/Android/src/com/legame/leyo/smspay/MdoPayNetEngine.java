package com.legame.leyo.smspay;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.text.TextUtils;

import com.legame.paysdk.GlobalVal;
import com.legame.paysdk.network.engine.BaseNetEngine;
import com.legame.paysdk.network.resultdata.FastPaymentResultData;
import com.legame.paysdk.network.utils.NetTools;

/** 
 * 类说明：   
 * @author  huangliang
 * @date    2014-6-10
 * @version 1.0
 */
public class MdoPayNetEngine extends BaseNetEngine{
	private static final String METHOD = "pay_mmdo";
	private String mSid;
	private String mAmount;
	private String mCpText;
	private String imsi;
	
	public MdoPayNetEngine(String sid, String amount, String cpText,String imsi){
		mHttpMethod = HTTP_POST;
		mSid = sid;
		mAmount = amount;
		mCpText = cpText;
		this.imsi = imsi;

		mResultData = new FastPaymentResultData(METHOD);
	}
	
	@Override
	protected String getCommand() {
		return METHOD;
	}

	@Override
	protected Map<String, String> getParams(Context c) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("sid", mSid);
		map.put("amount", mAmount);
		if(TextUtils.isEmpty(imsi)){
			map.put("imsi", "");
		}else {
			map.put("imsi", imsi);
		}
		if (!TextUtils.isEmpty(mCpText)) {
			map.put("cp_ext", mCpText);
		}
		String imei = NetTools.getIMEI(c);
		if (imei == null) {
			imei = NetTools.getLocalMacAddress(c);
		}
		map.put("imei", imei);
		
		String mac = NetTools.getLocalMacAddress(c);
		map.put("mac", mac);
		
		return map;
	}
	
	@Override
	public void onSidRefreshed(String newSid) {
		mSid = newSid;
	}

	protected final String getAmount() {
		return mAmount;
	}

}
