package com.legame.paysdk.network.engine;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.text.TextUtils;

import com.legame.paysdk.GlobalVal;
import com.legame.paysdk.network.resultdata.FastPaymentResultData;
import com.legame.paysdk.network.utils.NetTools;

/** 
 * 类说明：  移动页游： 发送截取的验证码到服务端
 * @author  xiaodingming
 * @date    2014-9-16
 * @version 1.0
 */
public class PageWebPayNetEngine extends BaseNetEngine{
	private static final String METHOD = "pay_verifycode";
	private String mSid;
	private String mCpText;
	private String mOrder_no;
	private String mVerifycode;
	private String imsi;
	
	public PageWebPayNetEngine(String sid, String order_no, String verifycode,String imsi){
		mHttpMethod = HTTP_POST;
		mSid = sid;
//		mCpText = cpText;
		mOrder_no = order_no;
		mVerifycode = verifycode;
		mResultData = new FastPaymentResultData(METHOD);
		this.imsi = imsi;
	}
	
	@Override
	protected String getCommand() {
		return METHOD;
	}
	
	@Override
	protected Map<String, String> getParams(Context c) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("sid", mSid);
		map.put("order_no", mOrder_no);
		map.put("verifycode", mVerifycode);
		if(TextUtils.isEmpty(imsi)){
			map.put("imsi", "");
		}else {
			map.put("imsi", imsi);
		}
//		if (!TextUtils.isEmpty(mCpText)) {
//			map.put("cp_ext", mCpText);
//		}
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

}
