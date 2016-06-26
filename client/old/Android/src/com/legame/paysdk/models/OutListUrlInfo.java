package com.legame.paysdk.models;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


/**
 * 类说明：
 * 
 * @author DingMing.Xiao
 * @date 2014-10-13
 * @version 1.0
 */
public class OutListUrlInfo {
	
    public List<OutUrlInfo> uInfo = new ArrayList<OutUrlInfo>();

	private static final String outListUrl_INFO = "outlistUrl_info";
	private static final String outListUrl = "outlistUrl";

	public static String getOutListUrl(Context context){
		SharedPreferences sp = context.getSharedPreferences(outListUrl_INFO, 0);
		String deviceId = sp.getString(outListUrl, "");
		return deviceId;
	}


	public static void saveOutListUrl(Context context, String listUrl){
		SharedPreferences sp = context.getSharedPreferences(outListUrl_INFO, 0);
		Editor editor = sp.edit();
		editor.putString(outListUrl, listUrl);
		editor.commit();
	}
}
