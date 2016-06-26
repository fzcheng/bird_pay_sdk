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
public class ListUrlInfo {
	
    public List<UrlInfo> uInfo = new ArrayList<UrlInfo>();

	private static final String ListUrl_INFO = "listUrl_info";
	private static final String ListUrl = "listUrl";

	public static String getListUrl(Context context){
		SharedPreferences sp = context.getSharedPreferences(ListUrl_INFO, 0);
		String deviceId = sp.getString(ListUrl, "");
		return deviceId;
	}


	public static void saveListUrl(Context context, String listUrl){
		SharedPreferences sp = context.getSharedPreferences(ListUrl_INFO, 0);
		Editor editor = sp.edit();
		editor.putString(ListUrl, listUrl);
		editor.commit();
	}
}
