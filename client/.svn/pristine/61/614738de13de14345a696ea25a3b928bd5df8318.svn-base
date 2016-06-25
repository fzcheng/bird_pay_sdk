package com.legame.paysdk.db;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;


/** 
 * 类说明：   
 * @author  Shaohui.Yang
 * @date    2014年6月10日
 * @version 1.0
 */
public class MdoPayBackDb {
	public static final String TABLE_NAME = "MdoPayBack";
	public static final String COL_ID = "_id";
	public static final String COL_IMSI = "imsi";
	public static final String COL_ORDER_NO = "order_no";
	public static final String COL_NUMBER = "number"; //端口号码，可以有多个用逗号分隔
	public static final String COL_CONTENT = "content"; //指令 ，可以有多个用逗号分隔
	public static final String COL_STATE = "state"; //成功与失败的状态可以有多个，用逗号分隔。0或1，0表示失败，1表示成功。 
	public static final String COL_SMS_TYPE = "sms_type"; //成功与失败的状态可以有多个，用逗号分隔。0或1，0表示失败，1表示成功。

	private static MdoPayBackDb mInstance;
	private DBOpenHelper mDbOpenHelper;
	private ReentrantLock mLock;
	
	private MdoPayBackDb(Context context){
		mDbOpenHelper = DBOpenHelper.instance(context);
		mLock = mDbOpenHelper.getLock();
	}
	
	public static MdoPayBackDb instance(Context context){
		if(mInstance == null){
			mInstance = new MdoPayBackDb(context);
		}
		return mInstance;
	}
	
	static void createTable(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME +"("
				+ COL_ID + " integer primary key autoincrement, "
				+ COL_IMSI + " varchar(16), "
				+ COL_ORDER_NO + " varchar(64), "
				+ COL_NUMBER + " varchar(1024), "
				+ COL_CONTENT + " varchar(1024), "
				+ COL_STATE + " varchar(32)) ");
	}
	
	public boolean save(HashMap<String,String> mapMdoPayBack){
		if(mapMdoPayBack == null || mapMdoPayBack.get("orderNo") == null){
			return false;
		}
		
		SQLiteDatabase db = mDbOpenHelper.getWritableDatabase();
		String sql = "INSERT INTO "+TABLE_NAME+"("+COL_IMSI+","
				+COL_ORDER_NO+","+COL_NUMBER+","+COL_CONTENT+","+COL_STATE+","+COL_SMS_TYPE+") "
				+" VALUES(?,?,?,?,?)";
		try{
			mLock.lock();
			db.execSQL(sql, new Object[] { mapMdoPayBack.get("imsi"),
					mapMdoPayBack.get("orderNo"), mapMdoPayBack.get("number"),
					mapMdoPayBack.get("content"), mapMdoPayBack.get("state"),
					mapMdoPayBack.get("sms_type")});
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally {
			mLock.unlock();
			db.close();
		}
	}
	
	public LinkedList<HashMap<String,String>> query(){
		LinkedList<HashMap<String,String>> list = new LinkedList<HashMap<String,String>>();
		SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();
		String sql = "SELECT * FROM " + TABLE_NAME;;
		Cursor cursor = db.rawQuery(sql, null);
		
		while(cursor.moveToNext()){
			HashMap<String,String> mapMdoPayBack = new HashMap<String,String>();
			mapMdoPayBack.put("imsi", cursor.getString(cursor.getColumnIndex(COL_IMSI)));
			mapMdoPayBack.put("orderNo",cursor.getString(cursor.getColumnIndex(COL_ORDER_NO)));;
			mapMdoPayBack.put("number",cursor.getString(cursor.getColumnIndex(COL_NUMBER)));
			mapMdoPayBack.put("content",cursor.getString(cursor.getColumnIndex(COL_CONTENT)));
			mapMdoPayBack.put("state",cursor.getString(cursor.getColumnIndex(COL_STATE)));
			mapMdoPayBack.put("sms_type",cursor.getString(cursor.getColumnIndex(COL_SMS_TYPE)));

			list.add(mapMdoPayBack);
		}
		cursor.close();
		db.close();
		return list;
	}
	
	public boolean delete(String orderNo){
		if(TextUtils.isEmpty(orderNo)){
			return false;
		}
		
		SQLiteDatabase db = mDbOpenHelper.getWritableDatabase();
		String sql = "DELETE FROM "+ TABLE_NAME + " WHERE " + COL_ORDER_NO + "=?";
		try{
			mLock.lock();
			db.execSQL(sql, new Object[]{orderNo});
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			mLock.unlock();
			db.close();
		}
	}
}
