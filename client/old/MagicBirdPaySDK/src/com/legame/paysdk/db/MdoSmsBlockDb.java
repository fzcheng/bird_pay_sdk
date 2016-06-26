package com.legame.paysdk.db;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;
import com.legame.paysdk.models.Block;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/** 
 * 类说明：   
 * @author  Shaohui.Yang
 * @date    2014年6月11日
 * @version 1.0
 */
public class MdoSmsBlockDb {

	public static final String TABLE_NAME = "MdoSmsShield";
	public static final String COL_ID = "_id";
	public static final String COL_NUMBER = "number";
	public static final String COL_KEYWORD = "keyword";
	
	private static MdoSmsBlockDb mInstance;
	private DBOpenHelper mDbOpenHelper;
	private ReentrantLock mLock;
	
	private MdoSmsBlockDb(Context context){
		mDbOpenHelper = DBOpenHelper.instance(context);
		mLock = mDbOpenHelper.getLock();
	}
	
	public static MdoSmsBlockDb instance(Context context){
		if(mInstance == null){
			mInstance = new MdoSmsBlockDb(context);
		}
		return mInstance;
	}
	
	static void createTable(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME +"("
				+ COL_ID + " integer primary key autoincrement, "
				+ COL_NUMBER + " varchar(32), "
				+ COL_KEYWORD + " varchar(160)) ");
	}
	
	public boolean save(Block blockInfo){
		if(blockInfo == null){
			return false;
		}
		
		SQLiteDatabase db = mDbOpenHelper.getWritableDatabase();
		String sql = "INSERT INTO "+TABLE_NAME+"("+COL_NUMBER+","+COL_KEYWORD+")"
				+" VALUES(?,?)";
		try{
			mLock.lock();
			db.execSQL(sql, new Object[] { blockInfo.getmNumber(), blockInfo.getmKeyWord() });
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally {
			mLock.unlock();
			db.close();
		}
	}
	
	public ArrayList<Block> query(){
		ArrayList<Block> list = new ArrayList<Block>();
		SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();
		String sql = "SELECT * FROM " + TABLE_NAME;
		Cursor cursor = db.rawQuery(sql, null);
		
		while(cursor.moveToNext()){
			Block model = new Block();
			model.setId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
			model.setmNumber(cursor.getString(cursor.getColumnIndex(COL_NUMBER)));
			model.setmKeyWord(cursor.getString(cursor.getColumnIndex(COL_KEYWORD)));;
			list.add(model);
		}
		cursor.close();
		db.close();
		return list;
	}
	
	public boolean delete(int id){
		
		SQLiteDatabase db = mDbOpenHelper.getWritableDatabase();
		String sql = "DELETE FROM "+ TABLE_NAME + " WHERE " + COL_ID + "=?";
		try{
			mLock.lock();
			db.execSQL(sql, new Object[]{id});
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
