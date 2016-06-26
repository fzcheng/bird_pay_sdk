package com.legame.paysdk.models;

import com.legame.paysdk.ErrorCode;
import com.legame.paysdk.ListenerHolder;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * 类说明：
 * 
 * @author xinhui.cheng
 * @date 2013-10-14
 * @version 1.0
 */
public class UserInfo {

	private static final String FILE_NAME = "userinfo";
	
	private String sid;
	private long validTime;
	private boolean bindPhone;
	private String nickName;
	private String userName;
	private String password;
	private long lastTime;
	private String gamePkgName;
	private String phoneNum;

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public long getValidTime() {
		return validTime;
	}

	public void setValidTime(long validTime) {
		this.validTime = validTime;
	}

	public boolean isBindPhone() {
		return bindPhone;
	}

	public void setBindPhone(boolean bindPhone) {
		this.bindPhone = bindPhone;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public long getLastTime() {
		return lastTime;
	}

	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}
	
	public String getGamePkgName() {
		return gamePkgName;
	}

	public void setGamePkgName(String gamePkgName) {
		this.gamePkgName = gamePkgName;
	}

	@Override
	public boolean equals(Object o) {
		UserInfo info = (UserInfo) o;
		if(userName == null){
			return super.equals(o);
		}
		if(userName.equals(info.getUserName())){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		if(userName == null){
			return super.hashCode();
		} else {
			return userName.hashCode();
		}
	}
	
	/**
	 * 更新是否自动登录状态
	 * @param context
	 * @param autoLogin
	 */
	public static void updateAutoLoginState(Context context ,boolean autoLogin){
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);
		sp.edit().putBoolean("autoLogin", autoLogin).commit();
		if(!autoLogin){
			if(ListenerHolder.sCancelAutoLoginListener != null){

				ListenerHolder.sCancelAutoLoginListener.onGameCallback(ErrorCode.ERROR_SUCCESS, "取消自动登录");
			}
		}
	}
	
	/**
	 * 获取是否自动登录
	 * @param context
	 * @return
	 */
	public static boolean getAutoLoginState(Context context){
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);
		return sp.getBoolean("autoLogin", true);
	}
	
	/**
	 * 更新当前登录过的用户名
	 * @param context
	 * @param username
	 */
	public static void updateCurrentLoginUserName(Context context , String username){
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);
		sp.edit().putString("loginUsername", username).commit();
	}
	
	/**
	 * 获取当前登录过的用户名
	 * @param context
	 * @return
	 */
	public static String getCurrentLoginUserName(Context context){
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);
		return sp.getString("loginUsername", null);
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
}
