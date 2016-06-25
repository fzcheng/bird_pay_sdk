package com.legame.paysdk.db;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

import com.legame.paysdk.models.PushExtrasModel;
import com.legame.paysdk.models.PushModel;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

/** 
 * 类说明：   
 * @author  xinhui.cheng
 * @date    2013-7-31
 * @version 1.0
 */
public class PushDatabase {

	public static final String TABLE_NAME = "push";
	public static final String COL_ID = "_id";
	public static final String COL_MSG_ID = "msgId";
	public static final String COL_TITLE = "title";
	public static final String COL_MESSAGE = "message";
	public static final String COL_TYPE = "type";
	public static final String COL_MSG_TIME = "time";
	//public static final String COL_PHONE_ID = "phoneId";
	public static final String COL_IS_READ = "isRead";	//0表示未读，非0表示已读
	public static final String COL_RESERVER = "reserver";
	public static final String COL_URL = "url";
	private static PushDatabase mInstance;
	
	private DBOpenHelper mDbOpenHelper;
	private ReentrantLock mLock;
	
	private PushDatabase(Context context){
		mDbOpenHelper = DBOpenHelper.instance(context);
		mLock = mDbOpenHelper.getLock();
	}
	
	public static PushDatabase instance(Context context){
		if(mInstance == null){
			mInstance = new PushDatabase(context);
		}
		return mInstance;
	}
	
	public boolean save(PushModel model){
		if(model == null || model.getExtras() == null){
			return false;
		}
		SQLiteDatabase db = mDbOpenHelper.getWritableDatabase();
		String sql = "INSERT INTO "+TABLE_NAME+"("+COL_TITLE+","+COL_MESSAGE+","
				+COL_TYPE+","+COL_MSG_ID+","+COL_MSG_TIME+","+COL_URL+","+COL_IS_READ+") "
				+" VALUES(?,?,?,?,?,?,?)";
		try{
			mLock.lock();
			db.execSQL(sql, new Object[] { model.getTitle(),
					model.getMessage(), model.getContentType(),
					model.getExtras().getMsgId(),
					model.getExtras().getMsgTime(),
					model.getExtras().getMsgUrl(), 0 });
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally {
			mLock.unlock();
			db.close();
		}
	}
	
	public int getUnreadCount(){
		int unreadCount = 0;
		SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();
		String sql = "SELECT COUNT(*) FROM "+TABLE_NAME+ " WHERE "+COL_IS_READ+"=0";
		Cursor cursor = db.rawQuery(sql , null);
		if(cursor.moveToFirst()){
			unreadCount = cursor.getInt(0);
		}
		cursor.close();
		db.close();
		return unreadCount;
	}
	
	public ArrayList<PushModel> findAll(){
		ArrayList<PushModel> list = new ArrayList<PushModel>();
		SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();
		String sql = "SELECT * FROM "+ TABLE_NAME + "  ORDER BY " + COL_MSG_TIME + " DESC";
		Cursor cursor = db.rawQuery(sql, null);
		while(cursor.moveToNext()){
			PushModel model = new PushModel();
			PushExtrasModel extras = new PushExtrasModel();
			model.setTitle(cursor.getString(cursor.getColumnIndex(COL_TITLE)));
			model.setMessage(cursor.getString(cursor.getColumnIndex(COL_MESSAGE)));
			model.setContentType(cursor.getInt(cursor.getColumnIndex(COL_TYPE)));
			model.setRead(cursor.getInt(cursor.getColumnIndex(COL_IS_READ))==0?false:true);
			extras.setMsgId(cursor.getString(cursor.getColumnIndex(COL_MSG_ID)));
			extras.setMsgTime(cursor.getString(cursor.getColumnIndex(COL_MSG_TIME)));
			extras.setMsgUrl(cursor.getString(cursor.getColumnIndex(COL_URL)));
			model.setExtras(extras);
			list.add(model);
		}
		cursor.close();
		db.close();
		return list;
	}
	
	public void updateState(String msgId , boolean isRead){
		if(TextUtils.isEmpty(msgId)){
			return ;
		}
		SQLiteDatabase db = mDbOpenHelper.getWritableDatabase();
		String sql = "UPDATE "+TABLE_NAME +" SET "+COL_IS_READ +"=? WHERE "+COL_MSG_ID+"=?";
		try{
			mLock.lock();
			db.execSQL(sql,new Object[]{isRead?1:0,msgId});
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			mLock.unlock();
			db.close();
		}
	}
	
	public boolean deleteMessage(String msgId){
		if(TextUtils.isEmpty(msgId)){
			return false;
		}
		SQLiteDatabase db = mDbOpenHelper.getWritableDatabase();
		String sql = "DELETE FROM "+TABLE_NAME+" WHERE "+COL_MSG_ID+"=?";
		try{
			mLock.lock();
			db.execSQL(sql, new Object[]{msgId});
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			mLock.unlock();
			db.close();
		}
	}
	
	public boolean deleteAll(){
		SQLiteDatabase db = mDbOpenHelper.getWritableDatabase();
		String sql = "DELETE FROM " +TABLE_NAME;
		try{
			mLock.lock();
			db.execSQL(sql);
			return true;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		} finally{
			mLock.unlock();
			db.close();
		}
	}
	
}
