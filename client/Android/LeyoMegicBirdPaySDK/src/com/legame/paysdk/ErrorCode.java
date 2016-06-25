package com.legame.paysdk;

/**
 * 类说明： 错误码
 * 
 * @author Terry.Lu
 * @date 2013年10月15日
 * @version 1.0
 */
public final class ErrorCode {
	private ErrorCode() {
	}

	public static final int ERROR_UPDATE_SID_SUCCESS = 1001;
	public static final int ERROR_SENDED_RECHARGE_REQUEST = 1002;
	public static final int ERROR_SUCCESS = 0;
	public static final int ERROR_FAIL = -1001;
	public static final int ERROR_NO_INIT = -1002;
	public static final int ERROR_INIT_FAIL = -1003;
	public static final int ERROR_NO_LOGIN = -1004;
	public static final int ERROR_SID_EXPIRED = -1005;
	public static final int ERROR_NETWORK_FAILED = -1007;
	public static final int ERROR_USER_CANCELED = -1008;
	public static final int ERROR_NO_NETWORK = -1009;
	public static final int ERROR_LOGIN_CANCELED = -1010;
	public static final int ERROR_NO_SID = -1011;
	public static final int ERROR_CANCEL_DIALOG = 1012;
	public static final int ERROR_NO_DIALOG = -1012;
	public static final int ERROR_NO_SUPPORT = -1013;
	public static final int ERROR_NO_SIMCARD = -1014;
	public static final int ERROR_JAR_EXCEPTION = -1015;
}
