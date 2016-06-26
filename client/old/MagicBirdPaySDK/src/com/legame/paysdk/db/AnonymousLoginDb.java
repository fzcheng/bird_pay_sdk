package com.legame.paysdk.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AnonymousLoginDb {
	static final String TABLE_NAME = "AnonymousLogin";
	static final String COL_ID = "_id";
	static final String COL_GAMEPKGNAME = "gamePkgName";
	static final String COL_USERNAME = "un";
	static final String COL_LAST_TIME = "lastTime";
	static final String COL_RESERVED1 = "reserved1";
	static final String COL_RESERVED2 = "reserved2";
	
	private PaySdkDBHelper mDbOpenHelper;
	
	private static class AnonymousLoginDbInstance {
		private static AnonymousLoginDb sInstance = new AnonymousLoginDb();
	}
	
	private AnonymousLoginDb() {
		mDbOpenHelper = PaySdkDBHelper.getHelper();
	}
	
	public static AnonymousLoginDb getInstance() {
		return AnonymousLoginDbInstance.sInstance;
	}
	
	static void createTable(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME +"("
				+ COL_ID + " integer primary key autoincrement, "
				+ COL_GAMEPKGNAME + " varchar(128), "
				+ COL_USERNAME + " varchar(128), "
				+ COL_LAST_TIME + " integer, "
				+ COL_RESERVED1 + " varchar(1024), "
				+ COL_RESERVED2 + " varchar(1024))");
	}
	
	public String getUsername(String packageName) {
		String un = null;
		SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, new String[] {COL_USERNAME}, COL_GAMEPKGNAME + "=?", new String[] {packageName}, null, null, null);
		if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
			int index = cursor.getColumnIndex(COL_USERNAME);
			if (index >= 0) {
				un = cursor.getString(index);
			}
			cursor.close();
		}
		db.close();
		return un;
	}
	
	public void deleteUsername(String userName){
		String sql = "DELETE FROM "+TABLE_NAME+" WHERE "+COL_USERNAME+"=?";
		SQLiteDatabase db = mDbOpenHelper.getWritableDatabase();
		try{
			db.execSQL(sql, new Object[]{userName});
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			db.close();
		}
	}
	
	public void save(String un, String packageName, long lastTime) {
		SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();
		ContentValues values = new ContentValues();
		values.put(COL_LAST_TIME, lastTime);
		int count = db.update(TABLE_NAME, values, COL_GAMEPKGNAME + "=?", new String[] {String.valueOf(lastTime)});
		
		if (count <=0) {
			values = new ContentValues();
			values.put(COL_USERNAME, un);
			values.put(COL_GAMEPKGNAME, packageName);
			values.put(COL_LAST_TIME, lastTime);
			db.insert(TABLE_NAME, null, values);
		}
		
		db.close();
	}
	
	public PaySdkDBHelper getPaySdkDBHelper(){
		return mDbOpenHelper;
	}
}
