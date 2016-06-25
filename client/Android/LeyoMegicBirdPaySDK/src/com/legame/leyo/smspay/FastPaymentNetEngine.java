package com.legame.leyo.smspay;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.text.TextUtils;

import com.legame.leyo.smspay.extend.SmsGetPhoneNumber;
import com.legame.leyo.smspay.util.ReflectUtil;
import com.legame.paysdk.network.engine.BaseNetEngine;
import com.legame.paysdk.network.resultdata.FastPaymentResultData;
import com.legame.paysdk.network.utils.NetTools;

/** 
 * 类说明：   
 * @author  huangliang
 * @date    2014-2-25
 * @version 1.0
 */
public class FastPaymentNetEngine extends BaseNetEngine{
	private static final String METHOD = "pay_fast";
	private String mSid;
	private String mAmount;
	private String mCpText;
	private String mIMSI;
	private String mPropsid = "";
	public FastPaymentNetEngine(){
		mHttpMethod = HTTP_POST;
		mResultData = new FastPaymentResultData(METHOD);
	}

   public void setData(String sid,String amount, String cpText, String propsid, String imsi){
		mSid = sid;
		mAmount = amount;
		mCpText = cpText;
		mIMSI = imsi;
		mPropsid = propsid;
   }
	
	@Override
	protected Map<String, String> getParams(Context c) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("sid", mSid);
		map.put("amount", mAmount);
		
		if (!TextUtils.isEmpty(mCpText)) {
			map.put("cp_ext", mCpText);	
		}
		
		map.put("propsid", mPropsid);	
		
		map.put("imsi", mIMSI);
		String imei = NetTools.getIMEI(c);
		if (imei == null) {
			imei = NetTools.getLocalMacAddress(c);
		}
		map.put("imei", imei);
		map.put("iccid", SmsGetPhoneNumber.getIccid(c));
		
		map.put("dbiccid", SmsGetPhoneNumber.getHasInsertIccidServerDb(c));
		
		String mac = NetTools.getLocalMacAddress(c);
		map.put("mac", mac);
		ReflectUtil reflectUtil = new ReflectUtil("com.legame.paysdk.network.resultdata.FastPaymentResultData");
		if(reflectUtil.hasField("mOrderList")){
			map.put("leyoadditional", "add");//新的多补点计费功能，区分新旧接口
		}
		return map;
	}
	
	@Override
	protected String getCommand() {
		return METHOD;
	}

}
