package com.legame.paysdk.models;

import java.io.Serializable;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/** 
 * 类说明：   
 * @author  xiaodm
 * @date    2014年12月23日
 * @version 1.0
 */
public class FirstSmsInfo implements Serializable {
	private static final long serialVersionUID = -2494513253437854694L;
	
	private String sdkSmsUpPort;
	
	public String getSdkSmsUpPort() {
		return sdkSmsUpPort;
	}
	public void setSdkSmsUpPort(String phoneNumber) {
		this.sdkSmsUpPort = phoneNumber;
	}
    
	
	private static final String SDKSMSUPPORT_INFO = "sdksmsupport_info";
	private static final String SDKSMSUPPORT = "sdksmsupport";

	public static String getSdkSmsUpPort(Context context){
		SharedPreferences sp = context.getSharedPreferences(SDKSMSUPPORT_INFO, 0);
		String deviceId = sp.getString(SDKSMSUPPORT, "");
		return deviceId;
	}


	public static void saveSdkSmsUpPort(Context context, String sdksmsupport){
		SharedPreferences sp = context.getSharedPreferences(SDKSMSUPPORT_INFO, 0);
		Editor editor = sp.edit();
		editor.putString(SDKSMSUPPORT, sdksmsupport);
		editor.commit();
	}
}
