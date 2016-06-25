package com.legame.paysdk.utils;

import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.legame.paysdk.models.Bulletin;
import com.legame.paysdk.models.LGGameInfo;

/** 
 * 类说明：   
 * @author  Terry Lu
 * @date    2013年12月17日
 * @version 1.0
 */
public final class DataUtils {
	private DataUtils() {}
	
	public static void saveBulletin(Context context, Bulletin bulletin) {
		if (bulletin != null) {
			SharedPreferences sp = context.getSharedPreferences("bulletin", Context.MODE_PRIVATE);
			Editor editor = sp.edit();
			editor.putString("content", bulletin.getContent());
			editor.putInt("type", bulletin.getType());
			editor.putString("url", bulletin.getUrl());
			LGGameInfo game = bulletin.getGameInfo();
			
			if (game != null) {
				editor.putString("package_name", game.getPackageName());
				editor.putString("name", game.getName());
				editor.putString("dl_url", game.getDl_url());
				editor.putString("icon_url", game.getIconUrl());
				editor.putFloat("rating", game.getRating());
				editor.putString("category", game.getCategory());
			}
			
			editor.commit();
		}
	}
	
	public static Bulletin getBulletin(Context context) {
		SharedPreferences sp = context.getSharedPreferences("bulletin", Context.MODE_PRIVATE);
		Bulletin bulletin = new Bulletin();
		bulletin.setContent(sp.getString("content", null));
		bulletin.setType(sp.getInt("type", Bulletin.BULLETIN_TYPE_WEB_LINK));
		bulletin.setUrl(sp.getString("url", ""));
		
		LGGameInfo game = new LGGameInfo();
		game.setPackageName(sp.getString("package_name", ""));
		game.setName(sp.getString("name", ""));
		game.setDl_url(sp.getString("dl_url", ""));
		game.setIconUrl(sp.getString("icon_url", ""));
		game.setRating(sp.getFloat("rating", 3));
		game.setCategory(sp.getString("category", ""));
		bulletin.setGameInfo(game);
		return bulletin;
	}
	
	public static void saveDeviceID(Context context,String deviceID){
		SharedPreferences sp = context.getSharedPreferences(Constants.DEVICE_INFO, 0);
		Editor editor = sp.edit();
		editor.putString(Constants.DEVICE_ID, deviceID);
		editor.commit();
	}
	
	public static String getDeviceID(Context context){
		SharedPreferences sp = context.getSharedPreferences(Constants.DEVICE_INFO, 0);
		String deviceId = sp.getString(Constants.DEVICE_ID, "");
		return deviceId;
	}
	
	public static void saveSDKVersion(Context context,String sdkVersion){
		saveStringPre(context, Constants.SDK_VERSION_INFO, sdkVersion);
	}
	
	public static String getSDKVersion(Context context){
		return getValuePre(context, Constants.SDK_VERSION_INFO);
	}
	
	private static Editor getEditor(Context context){
		SharedPreferences sp = context.getSharedPreferences(Constants.LEYO_DATA, Context.MODE_PRIVATE);
		return sp.edit();
	}
	
	public static void saveStringPre(Context context ,String key ,String value){
		Editor editor = getEditor(context);
		editor.putString(key, value);
		editor.commit();
	}
	
	public static String getValuePre(Context context,String key){
		SharedPreferences sp = context.getSharedPreferences(Constants.LEYO_DATA, Context.MODE_PRIVATE);
		return sp.getString(key, "");
	}
	
	public static void saveLongPre(Context context,String key,long value){
		Editor editor = getEditor(context);
		editor.putLong(key, value);
		editor.commit();
	}
	
	public static Long getLongPre(Context context,String key){
		return context.getSharedPreferences(Constants.LEYO_DATA, Context.MODE_PRIVATE).getLong(key, 0);
	}
	
}
