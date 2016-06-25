package com.legame.paysdk.utils;
/** 
 * 类说明：   
 * @author  Kaiguang
 * @date    2016.2.24
 * @version 1.0
 */
public class Constants {
	private Constants() {}
	
	public static final String DEVICE_INFO = "device_info";
	public static final String DEVICE_ID = "device_id";
	
	public static class AliErrorCode {
		public static final int ALI_SUCCESS = 9000;
		public static final int ALI_SYS_EXCEPTION = 4000;
		public static final int ALI_DATA_FORMAT_INCORRECT = 4001;
		public static final int ALI_PAY_NOT_ALLOW = 4003;
		public static final int ALI_UNBIND_USER = 4004;
		public static final int ALI_FAILED_TO_BIND = 4005;
		public static final int ALI_FAILED_TO_PAY = 4006;
		public static final int ALI_REBIND_USER = 4010;
		public static final int ALI_SERVICE_UPGRADING = 6000;
		public static final int ALI_USER_CANEL_PAYING = 6001;
		public static final int ALI_CONNECTION_FAILED = 6002;
	}
	
	public static final String SDK_VERSION = "0.0.1";
	public static final String SMS_SDK_VERSION = "0.0.1";
	
	public static final String LEYO_DATA = "leyoDataInfo";
	public static final String SDK_VERSION_INFO = "sdkVersion";
	
	public static final String SHOW_UPDATE_DIALOG_TIME = "updateDialogTime";
	
	public static final String PAY_IDENTIFY = "payIdentify";
}
