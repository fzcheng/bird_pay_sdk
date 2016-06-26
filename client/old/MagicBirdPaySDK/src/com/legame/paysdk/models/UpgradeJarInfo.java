package com.legame.paysdk.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class UpgradeJarInfo {
	private boolean ifdownload;
	private String down_url;
	private String version_code_jar;

	public UpgradeJarInfo() {
		//
	}

	public UpgradeJarInfo(boolean mIfdownload, String mVersion_code_jar, String downloadUrl) {
		ifdownload = mIfdownload;
		version_code_jar = mVersion_code_jar;
		down_url = downloadUrl;
	}

	public boolean isIfdownload() {
		return ifdownload;
	}

	public void setIfdownload(boolean ifdownload) {
		this.ifdownload = ifdownload;
	}

	public String getDown_url() {
		return down_url;
	}

	public void setDown_url(String down_url) {
		this.down_url = down_url;
	}

	public String getVersion_code_jar() {
		return version_code_jar;
	}

	public void setVersion_code_jar(String version_code_jar) {
		this.version_code_jar = version_code_jar;
	}
	
	
	private static final String upgradeJar_INFO = "upgradeJar_info";
	private static final String upgradeJar = "upgradeJar";

	public static String getUpgradeJar(Context context){
		SharedPreferences sp = context.getSharedPreferences(upgradeJar_INFO, Context.MODE_PRIVATE);
		String deviceId = sp.getString(upgradeJar, "");
		return deviceId;
	}


	public static void saveUpgradeJar(Context context, String ver){
		SharedPreferences sp = context.getSharedPreferences(upgradeJar_INFO, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString(upgradeJar, ver);
		editor.commit();
	}
}