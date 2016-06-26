package com.legame.paysdk.utils;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/** 
 * 类说明：   
 * @author  Terry.Lu
 * @date    2013年11月1日
 * @version 1.0
 */
public class UIUtils {
	private UIUtils() {}
	
	public static void hideSoftInput(View view) {
		final InputMethodManager im = (InputMethodManager) view.getContext()
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		im.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}
	
	public static String normalizationFileSize(long bytes){
		if(bytes <= 0){
			return "0k";
		}
		
		if(bytes > 0 && bytes < 1024 * 1024){
			float kb = bytes / 1024.0f;
			String skb = String.format("%.2f", kb) + "KB";
			return skb;
		}else{
			float mb = bytes / (1024.0f * 1024);
			String smb = String.format("%.2f", mb) + "MB";
			return smb;
		}
	}
	
	public static boolean isGameInstalled(Context context, String packName) {
		try {
			context.getPackageManager().getPackageInfo(packName, 0);
		} catch (NameNotFoundException e) {
			return false;
		}
		return true;
	}
}
