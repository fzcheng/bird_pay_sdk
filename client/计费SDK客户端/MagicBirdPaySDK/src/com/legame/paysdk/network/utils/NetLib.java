package com.legame.paysdk.network.utils;


public class NetLib{
	
	static {
		System.loadLibrary("PaySDK");
	}

	public static native String getServerUrl();
	public static native String getSecrectKey();
}