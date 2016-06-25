package com.legame.paysdk.network.engine;

import java.util.Map;
import java.util.TreeMap;

import com.legame.paysdk.network.engine.cachestrategy.CacheStrategy;
import com.legame.paysdk.network.resultdata.UserLoginResultData;
import com.legame.paysdk.utils.CryptoUtil;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;


/** 
 * 类说明：   
 * @author  xinhui.cheng
 * @date    2013-10-14
 * @version 1.0
 */
public class UserLoginNetEngine extends BaseNetEngine {

	private static final String TAG = "UserLoginNetEngine";
	
	private String mUserName;
	private String mPwd;
	private String mNewPwd;
	
	/**
	 * @param userName 用户名
	 * @param pwd	密码
	 * @param newPwd 	当需要修改密码时才传入该参数。否则传入null即可。
	 */
	public UserLoginNetEngine(String userName,String pwd, String newPwd){
		mHttpMethod = HTTP_POST;
		mCacheStrategy = new CacheStrategy(false);
		mResultData = new UserLoginResultData();
		mUserName = userName;
		mPwd = pwd;
		mNewPwd = newPwd;
	}
	
	@Override
	protected String getCommand() {
		return "user_login";
	}
	
	@Override
	protected Map<String, String> getParams(Context c) {
		TreeMap<String, String> map = new TreeMap<String, String>();
		map.put("user_name", mUserName);
		map.put("pwd", CryptoUtil.encrypt(mPwd,CryptoUtil.SERVICE_KEY));
		if(!TextUtils.isEmpty(mNewPwd)){
			map.put("new_pwd",  CryptoUtil.encrypt(mNewPwd,CryptoUtil.SERVICE_KEY));
		}
		String model = android.os.Build.MODEL;
		map.put("model", model);
		String  release = android.os.Build.VERSION.RELEASE;
		map.put("osversion", release);

//        int currentapiVersion=android.os.Build.VERSION.SDK_INT;
		Log.i(TAG,"Product Model: " + android.os.Build.MODEL + ","
                + android.os.Build.VERSION.SDK_INT + ","
                + android.os.Build.VERSION.RELEASE);
		return map;
	}

}
