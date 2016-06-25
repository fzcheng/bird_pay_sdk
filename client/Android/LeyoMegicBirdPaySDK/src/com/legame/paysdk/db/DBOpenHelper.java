package com.legame.paysdk.db;

import java.util.concurrent.locks.ReentrantLock;

import com.legame.paysdk.download.FileDownloadDatabase;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/** 
 * 类说明：   
 * @author  Terry.Lu
 * @date    2013-5-15
 * @version 1.0
 */
public class DBOpenHelper extends SQLiteOpenHelper {
	/**
	 * 数据库名
	 */
	protected static final String DATABASE_NAME = "LeGameSdkDatabase";

	/**
	 * 数据库版本
	 */
	private static final int DATABASE_VERSION = 1;
	private static DBOpenHelper sInstance;
	private final ReentrantLock mReentrantLock = new ReentrantLock(true);

	public ReentrantLock getLock() {
		return mReentrantLock;
	}

	public static synchronized DBOpenHelper instance(Context context) {
		if (sInstance == null) {
			sInstance = new DBOpenHelper(context.getApplicationContext());
		}

		return sInstance;
	}

	private DBOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		createTable(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
	
	private void createTable(SQLiteDatabase db){
		createFileDownloadTB(db);
		MdoPayBackDb.createTable(db);
		MdoSmsBlockDb.createTable(db);
	}
		
	
	private void createFileDownloadTB(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS " + FileDownloadDatabase.TABLE_NAME+"("
				+ FileDownloadDatabase.COL_INDENTIFIER + " varchar(64) primary key, "
				+ FileDownloadDatabase.COL_CREATE_TIME + " varchar(128), "
				+ FileDownloadDatabase.COL_PACKAGE_NAME + " varchar(256), "
				+ FileDownloadDatabase.COL_GAME_NAME + " varchar(256), "
				+ FileDownloadDatabase.COL_DOWNLOAD_URL + " varchar(1024), "
				+ FileDownloadDatabase.COL_PIC_URL + " varchar(1024), "
				+ FileDownloadDatabase.COL_FILE_TOTAL_SIZE + " integer, "
				+ FileDownloadDatabase.COL_CANCEL_STATUS + " integer)");
	}
	
}
