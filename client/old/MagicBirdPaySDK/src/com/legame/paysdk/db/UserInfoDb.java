package com.legame.paysdk.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.legame.paysdk.ErrorCode;
import com.legame.paysdk.ListenerHolder;
import com.legame.paysdk.models.UserInfo;
import com.legame.paysdk.utils.CryptoUtil;

/** 
 * 类说明：   
 * @author  Terry.Lu
 * @date    2013年10月14日
 * @version 1.0
 * @modify Terry.Lu
 * @modifyDate 2013年11月5日
 */
public class UserInfoDb {
	static final String TABLE_NAME = "UserInfo";
	static final String COL_ID = "_id";
	static final String COL_GAMEPKGNAME = "gamePkgName";
	static final String COL_USERNAME = "un";
	static final String COL_PWD = "pwd";
	static final String COL_SID = "sid";
	static final String COL_LAST_TIME = "lastTime";
	static final String COL_IS_BINDED = "isBinded";
	static final String COL_NICK_NAME = "nickName";
	static final String COL_LOGIN_NUM = "loginNum";
	static final String COL_PHONE_NUM = "phoneNum";
	static final String COL_RESERVED1 = "reserved1";
	static final String COL_RESERVED2 = "reserved2";
	private PaySdkDBHelper mDbOpenHelper;
	
	private static class UserInfoDbInstance {
		private static UserInfoDb sInstance = new UserInfoDb();
	}
	
	private UserInfoDb() {
		mDbOpenHelper = PaySdkDBHelper.getHelper();
	}
	
	public static UserInfoDb getInstance() {
		return UserInfoDbInstance.sInstance;
	}
	
	static void createTable(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME +"("
				+ COL_ID + " integer primary key autoincrement, "
				+ COL_GAMEPKGNAME + " varchar(128), "
				+ COL_USERNAME + " varchar(128), "
				+ COL_PWD + " varchar(128), "
				+ COL_SID + " varchar(1024), "
				+ COL_LAST_TIME + " integer, "
				+ COL_IS_BINDED + " integer, "
				+ COL_NICK_NAME + " varchar(128), "
				+ COL_LOGIN_NUM + " integer, "
				+ COL_PHONE_NUM + " varchar(32), "
				+ COL_RESERVED1 + " varchar(1024), "
				+ COL_RESERVED2 + " varchar(1024))");
	}
	
//	public List<UserInfo> getUserList() {
//		SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();
//		Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, COL_LAST_TIME + " desc");
//		List<UserInfo> users = new ArrayList<UserInfo>();
//		
//		while (cursor != null && cursor.moveToNext()) {
//			UserInfo history = UserInfoBuilder.build(cursor);
//			users.add(history);
//		}
//		
//		if (cursor != null) {
//			cursor.close();
//		}
//		
//		db.close();
//		return users;
//	}
	
	public void updateSidByUserName(String gamePkgName ,String username, String sid) {
		SQLiteDatabase db = mDbOpenHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COL_SID, sid);
		db.update(TABLE_NAME, values, COL_USERNAME + "=? and " + COL_GAMEPKGNAME + "=?",
				new String[] {username,gamePkgName});
		db.close();
	}
	
	/**
	 * 获取指定条件的用户账号列表
	 * @param gamePkgName 指定的游戏包名
	 * @param isCurrentGame 如果为true,则表示获取包名为gamePkgName游戏下的所有账号，如果为false则获取除了
	 * 						包名为gamePkgName游戏以外的所有账号。
	 * @return 用户信息列表，该列表中的用户账号数据可能重复
	 */
	private ArrayList<UserInfo> getUserListForGame(String gamePkgName, boolean isCurrentGame){
		ArrayList<UserInfo> list = new ArrayList<UserInfo>();
		String sql = "SELECT * FROM "+TABLE_NAME+" WHERE "+COL_GAMEPKGNAME+"=? ORDER BY "+COL_LAST_TIME+" DESC";
		if(!isCurrentGame){
			sql = "SELECT * FROM "+TABLE_NAME+" WHERE "+COL_GAMEPKGNAME+"!=? ORDER BY "+COL_LAST_TIME+" DESC";
		}
		SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery(sql, new String[]{gamePkgName});
		while(cursor.moveToNext()){
			UserInfo info = UserInfoBuilder.build(cursor);
			list.add(info);
		}
		if (cursor != null) {
			cursor.close();
		}
		
		db.close();
		return list;
	}
	
	public UserInfo getLastLoginUser() {
		SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, COL_LAST_TIME + " desc");
		UserInfo userInfo = null;
		
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				userInfo = UserInfoBuilder.build(cursor);	
			}
			cursor.close();
		}
		
		db.close();
		
		return userInfo;
	}
	
	public UserInfo getNotLoginUserInfo() {
		SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, null, COL_LOGIN_NUM + "=?", new String[] {String.valueOf(0)}, null, null, COL_LAST_TIME + " desc");
		UserInfo userInfo = null;
		
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				userInfo = UserInfoBuilder.build(cursor);	
			}
			cursor.close();
		}
		
		db.close();
		
		return userInfo;
	}
	
	/**
	 * 根据给定的游戏包名获取一个指定游戏下的账号在最上面的用户账号列表，且该列表账号不会重复。
	 * @param gamePkgName
	 * @return
	 */
	public ArrayList<UserInfo> getNoRepeatUserListOrderByGame(String gamePkgName){
		ArrayList<UserInfo> recordList = new ArrayList<UserInfo>();
		recordList.addAll(getUserListForGame(gamePkgName, true));
		recordList.addAll(getUserListForGame(gamePkgName, false));
		
		ArrayList<UserInfo> resultList = new ArrayList<UserInfo>();
		for (int i = 0; i < recordList.size(); i++) {
			UserInfo info = recordList.get(i);
			if(resultList.indexOf(info) == -1){
				resultList.add(info);
			}
		}
		return resultList;
	}
	
	public boolean deleteUser(String userName){
		String sql = "DELETE FROM "+TABLE_NAME+" WHERE "+COL_USERNAME+"=?";
		SQLiteDatabase db = mDbOpenHelper.getWritableDatabase();
		try{
			db.execSQL(sql, new Object[]{userName});
			return true;
		} catch (Exception e){
			e.printStackTrace();
			return false;
		} finally {
			db.close();
		}
	}
	public boolean deleteUserAll(){
		String sql = "DELETE FROM "+TABLE_NAME;
		SQLiteDatabase db = mDbOpenHelper.getWritableDatabase();
		try{
			db.execSQL(sql);
			ListenerHolder.sDeleteAllUserListener.onGameCallback(ErrorCode.ERROR_SUCCESS, "清理本地缓存成功");
			return true;
		} catch (Exception e){
			e.printStackTrace();
			ListenerHolder.sDeleteAllUserListener.onGameCallback(ErrorCode.ERROR_FAIL, "删除本地缓存失败");
			return false;
		} finally {
			db.close();
		}
	}
	/**
	 * 该方法用于保存用户信息，当指定游戏下账号存在时，更新部分当前数据。
	 * @param info
	 * @param gamePkgName
	 * @return
	 */
	public boolean saveOrUpdateUser(UserInfo info ,String gamePkgName){
		if(info == null || TextUtils.isEmpty(info.getUserName()) || TextUtils.isEmpty(gamePkgName)){
			return false;
		}
	
		String sql = "SELECT * FROM "+TABLE_NAME+" WHERE "+COL_USERNAME+"=? AND "+COL_GAMEPKGNAME+"=?";
		SQLiteDatabase db = mDbOpenHelper.getWritableDatabase();
		Cursor cursor = null;
		try{
			cursor = db.rawQuery(sql, new String[]{info.getUserName(),gamePkgName});
			if(cursor.getCount() != 0){
				String updateSql = "UPDATE "+TABLE_NAME+" SET "+COL_IS_BINDED+"=?,"+COL_NICK_NAME+"=? " +
						" WHERE "+COL_USERNAME+"=?";
				String updateSql2 = "UPDATE "+TABLE_NAME+" SET "+COL_SID+"=?,"+COL_LAST_TIME+"=? " +
						" WHERE "+COL_USERNAME+"=? AND "+COL_GAMEPKGNAME+"=?";
				db.execSQL(updateSql, new Object[]{info.isBindPhone()?1:0,info.getNickName(),
						info.getUserName()});
				db.execSQL(updateSql2, new Object[]{info.getSid(),System.currentTimeMillis(),
						info.getUserName(),gamePkgName});
			} else {
				sql = "INSERT INTO "+TABLE_NAME+"("+COL_SID+","+COL_USERNAME+","+COL_PWD+
						","+COL_IS_BINDED+","+COL_NICK_NAME+","+COL_GAMEPKGNAME+","+COL_LOGIN_NUM+")" +
								" VALUES(?,?,?,?,?,?,?)";
				db.execSQL(sql, new Object[]{info.getSid(),info.getUserName(),CryptoUtil.encrypt(info.getPassword(),CryptoUtil.PRIVATE_KEY)
						,info.isBindPhone(),info.getNickName(),gamePkgName,0});
			}
			return true;
		} catch (Exception e){
			e.printStackTrace();
			return false;
		} finally {
			if(cursor != null){
				cursor.close();
			}
			db.close();
		}
	}
	
	public boolean isExist(String username , String gamePkgName){
		if(TextUtils.isEmpty(username) || TextUtils.isEmpty(gamePkgName)){
			return false;
		}
		String sql = "SELECT COUNT(*) FROM "+TABLE_NAME+" WHERE "+COL_USERNAME+"=? AND "+COL_GAMEPKGNAME+"=?";
		SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();
		Cursor c = db.rawQuery(sql, new String[]{username});
		boolean ret = false;
		if(c.moveToFirst()){
			int count = c.getInt(0);
			if(count==0){
				ret = false;
			} else {
				ret = true;
			}
		}
		
		if (c != null) {
			c.close();
		}
		db.close();
		return ret;
	}
	
	public boolean updateUserPassword(String username , String password){
		if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
			return false;
		}
		String sql = "UPDATE "+TABLE_NAME+" SET "+COL_PWD+"=? WHERE "+COL_USERNAME+"=?";
		SQLiteDatabase db = mDbOpenHelper.getWritableDatabase();
		try{
			db.execSQL(sql, new Object[]{CryptoUtil.encrypt(password,CryptoUtil.PRIVATE_KEY),username});
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		} finally{
			db.close();
		}
	}
	
	public boolean updateUserNickname(String username , String nickname){
		if(TextUtils.isEmpty(username) || TextUtils.isEmpty(nickname)){
			return false;
		}
		String sql = "UPDATE "+TABLE_NAME+" SET "+COL_NICK_NAME+"=? WHERE "+COL_USERNAME+"=?";
		SQLiteDatabase db = mDbOpenHelper.getWritableDatabase();
		try{
			db.execSQL(sql, new Object[]{nickname,username});
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		} finally{
			db.close();
		}
	}
	
	public boolean updateUserBindPhoneState(String username , boolean isBindPhone, String phone){
		if(TextUtils.isEmpty(username)){
			return false;
		}
		String sql = "UPDATE "+TABLE_NAME+" SET "+COL_IS_BINDED + "=?" + phone +"=? WHERE "+COL_USERNAME+"=?";
		SQLiteDatabase db = mDbOpenHelper.getWritableDatabase();
		try{
			db.execSQL(sql, new Object[]{isBindPhone?1:0, phone, username});
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		} finally{
			db.close();
		}
	}
	
	public boolean updateUserLastLoginTime(String username , long lastTime,String gamePkgName){
		if(TextUtils.isEmpty(username) || TextUtils.isEmpty(gamePkgName)){
			return false;
		}
		String selectSql = "SELECT * FROM "+TABLE_NAME+" WHERE "+COL_USERNAME+"=? AND "+COL_GAMEPKGNAME+"=?";
		String sql = "UPDATE "+TABLE_NAME+" SET "+COL_LAST_TIME+"=? WHERE "+COL_USERNAME+"=? AND "+COL_GAMEPKGNAME+"=?";
		SQLiteDatabase db = mDbOpenHelper.getWritableDatabase();
		Cursor cursor = null;
		try{
			cursor = db.rawQuery(selectSql, new String[]{username,gamePkgName});
			if(cursor.moveToNext()){
				db.execSQL(sql, new Object[]{lastTime,username});
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		} finally{
			if(cursor != null){
				cursor.close();
			}
			db.close();
		}
	}
	
	/**
	 * 更新登录次数，每次加1
	 * @param userName
	 */
	public void updateLoginNumByUsername(String userName){
		if(TextUtils.isEmpty(userName)){
			return;
		}
		String sql = "UPDATE "+TABLE_NAME+" SET "+COL_LOGIN_NUM+"="+COL_LOGIN_NUM+"+1 WHERE "+COL_USERNAME+"=?";
		SQLiteDatabase db = mDbOpenHelper.getWritableDatabase();
		db.execSQL(sql, new Object[]{userName});
		db.close();
	}
	
	public int getLoginNumByUsername(String userName){
		int num = 0;
		String sql = "SELECT * FROM "+TABLE_NAME+" WHERE "+COL_USERNAME+"=?";
		SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();
		Cursor c = db.rawQuery(sql, new String[]{userName});
		if(c.moveToFirst()){
			num = c.getInt(c.getColumnIndex(COL_LOGIN_NUM));
		}
		c.close();
		db.close();
		return num;
	}
	
	public UserInfo getUserInfoByUsername(String username){
		String sql = "SELECT * FROM " + TABLE_NAME +" WHERE "+COL_USERNAME+"=? ORDER BY " + COL_LAST_TIME + " DESC";
		UserInfo info = null;
		SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery(sql, new String[]{username});
		if(cursor.moveToNext()){
			info = UserInfoBuilder.build(cursor);
		}
		cursor.close();
		db.close();
		return info;
	}
	
	public UserInfo getUserInfoBySid(String sid){
		String sql = "SELECT * FROM " + TABLE_NAME +" WHERE "+COL_SID+"=?";
		UserInfo info = null;
		SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery(sql, new String[]{sid});
		if(cursor.moveToNext()){
			info = UserInfoBuilder.build(cursor);
		}
		cursor.close();
		db.close();
		return info;
	}
	
	private static class UserInfoBuilder {
		public static UserInfo build(Cursor cursor) {
			int colUn = cursor.getColumnIndex(COL_USERNAME);
			int colPwd = cursor.getColumnIndex(COL_PWD);
			int colSid = cursor.getColumnIndex(COL_SID);
			int colLastTime = cursor.getColumnIndex(COL_LAST_TIME);
			int colIsBinded = cursor.getColumnIndex(COL_IS_BINDED);
			int colNickName = cursor.getColumnIndex(COL_NICK_NAME);
			int colGamePkgName = cursor.getColumnIndex(COL_GAMEPKGNAME);
			int colPhoneNum = cursor.getColumnIndex(COL_PHONE_NUM);
					
			UserInfo user = new UserInfo();
			if (colUn != -1) {
				user.setUserName(cursor.getString(colUn));	
			}
			
			if (colPwd != -1) {
				user.setPassword(CryptoUtil.decrypt(cursor.getString(colPwd),CryptoUtil.PRIVATE_KEY));
			}
			
			if (colSid != -1) {
				user.setSid(cursor.getString(colSid));	
			}
			
			if (colLastTime != -1) {
				user.setLastTime(cursor.getLong(colLastTime));	
			}
			
			if (colNickName != -1) {
				user.setNickName(cursor.getString(colNickName));	
			}
			
			if (colPhoneNum != -1) {
				user.setPhoneNum(cursor.getString(colPhoneNum));	
			}
			
			if (colIsBinded != -1) {
				boolean isBinded = (cursor.getInt(colIsBinded) == 1) ? true : false;
				user.setBindPhone(isBinded);	
			}
			
			if (colGamePkgName != -1) {
				user.setGamePkgName(cursor.getString(colGamePkgName));	
			}
			
			
			return user;
		}
		
		public static ContentValues deconstruct(UserInfo user) {
			ContentValues values = new ContentValues();
			values.put(COL_USERNAME, user.getUserName());
			values.put(COL_PWD, user.getPassword());
			values.put(COL_SID, user.getSid());
			values.put(COL_LAST_TIME, user.getValidTime());
			values.put(COL_IS_BINDED, user.isBindPhone() ? 1 : 0);
			values.put(COL_NICK_NAME, user.getNickName());
			values.put(COL_GAMEPKGNAME, user.getGamePkgName());
			values.put(COL_PHONE_NUM, user.getPhoneNum());
			return values;
		}

	}
}
