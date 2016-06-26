package com.legame.paysdk.db;

import java.io.File;

import android.database.sqlite.SQLiteDatabase;

import com.legame.paysdk.utils.Config;

/** 
 * 类说明：   
 * @author  Terry.Lu
 * @date    2013年10月15日
 * @version 1.0
 */
public class PaySdkDBHelper extends ExtenalStorageDBHelper {
	/**
	 * 数据库名
	 */
	protected static final String DATABASE_NAME = "PaySDK";

	/**
	 * 数据库版本
	 */
	private static final int DATABASE_VERSION = 1;
	
	private static final String DIR = Config.DB_DIR;
	
	private static final String OLD_DIR = Config.OLD_DB_DIR;
	
	private boolean isNewDir = true;
	
	private static class PaySdkDBHelperInstance {
		private static PaySdkDBHelper sInstance = new PaySdkDBHelper();	
	}
	
	private PaySdkDBHelper() {
		super(null, DIR, DATABASE_NAME, null, DATABASE_VERSION);
		File file = new File(DIR);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	protected  static PaySdkDBHelper getHelper() {
		return PaySdkDBHelperInstance.sInstance;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		UserInfoDb.createTable(db);
		AnonymousLoginDb.createTable(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 切换数据库路径
	 */
	public void changeDirectory(){
		if(isNewDir){
			isNewDir = false;
			changeDir(OLD_DIR);
		}else{
			isNewDir = true;
			changeDir(DIR);
		}
		mDatabase = null;
	}
	
	/**
	 * 判断是否是新包的数据库路径
	 * @return
	 */
	public boolean isNewDir(){
		return isNewDir;
	}

}
