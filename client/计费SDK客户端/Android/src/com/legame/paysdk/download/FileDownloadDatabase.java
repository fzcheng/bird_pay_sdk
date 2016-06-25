package com.legame.paysdk.download;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.legame.paysdk.db.DBOpenHelper;
import com.legame.paysdk.utils.Config;


/**
 * 类说明：
 * 
 * @author Toby.chen
 * @date 2013-7-11
 * @version 1.0
 */

public class FileDownloadDatabase {

	public static final String TABLE_NAME = "FileDownloadTB";
	public static final String COL_INDENTIFIER = "indentifier";
	public static final String COL_CREATE_TIME = "CreateTime";
	public static final String COL_PACKAGE_NAME = "packageName";
	public static final String COL_GAME_NAME = "game_name";
	public static final String COL_DOWNLOAD_URL = "downloadUrl";
	public static final String COL_PIC_URL = "picUrl";
	public static final String COL_FILE_TOTAL_SIZE = "totalSize";
	public static final String COL_CANCEL_STATUS = "cancelStatus";

	private DBOpenHelper mDbOpenHelper = null;
	private static FileDownloadDatabase sInstance = null;
	private ReentrantLock mLock = null;

	private FileDownloadDatabase(Context context) {
		mDbOpenHelper = DBOpenHelper.instance(context);
		mLock = mDbOpenHelper.getLock();
	}

	public synchronized static FileDownloadDatabase instance(Context context) {
		if (sInstance == null) {
			sInstance = new FileDownloadDatabase(context);
		}

		return sInstance;
	}

	@SuppressLint("NewApi")
	public void insert(FileDownloadModel m) {
		mLock.lock();
		SQLiteDatabase db = mDbOpenHelper.getWritableDatabase();
		ContentValues values = FileDownloadModelBuilder.deconstruct(m);
		db.insertWithOnConflict(TABLE_NAME, null, values,
				SQLiteDatabase.CONFLICT_IGNORE);
		mLock.unlock();
	}

	public void deleteByIndentifier(String indentifier) {
		mLock.lock();
		SQLiteDatabase db = mDbOpenHelper.getWritableDatabase();
		db.delete(TABLE_NAME, COL_INDENTIFIER + "=?",
				new String[] { indentifier });
		db.close();
		mLock.unlock();
	}

	public void clearAllDatas() {
		mLock.lock();
		SQLiteDatabase db = mDbOpenHelper.getWritableDatabase();
		db.delete(TABLE_NAME, null, null);
		db.close();
		mLock.unlock();
	}

	@SuppressLint("NewApi")
	public void update(FileDownloadModel m) {
		mLock.lock();
		SQLiteDatabase db = mDbOpenHelper.getWritableDatabase();
		ContentValues values = FileDownloadModelBuilder.deconstruct(m);
		db.updateWithOnConflict(TABLE_NAME, values, COL_INDENTIFIER + "=?",
				new String[] { m.getIndentfier() },
				SQLiteDatabase.CONFLICT_REPLACE);
		mLock.unlock();
	}

	public FileDownloadModel getFileDonwloadModeByIndentifier(String indentifier) {
		mLock.lock();
		SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, null, COL_INDENTIFIER + "=?",
				new String[] { indentifier }, null, null, null);

		FileDownloadModel m = null;

		if (cursor != null && cursor.moveToNext()) {
			m = FileDownloadModelBuilder.build(cursor);
		}

		if (cursor != null) {
			cursor.close();
		}

		db.close();
		mLock.unlock();
		return m;
	}

	public int getFileDownloadStatusByPackageName(String pkgName)
	{
		String indentifier = "" + pkgName.hashCode();
		String savePath = Config.DOWNLOAD_DIR + File.separator + indentifier + ".apk";
		File saveFile = new File(savePath);
		long fileSize = -9999;
		if (saveFile.exists()) {
			fileSize = saveFile.length();
		}
		
		FileDownloadModel m = getFileDonwloadModeByIndentifier(indentifier);
		
		if (m == null) {
			return FileDownloadService.DONWLOAD_NOT_EXISIT;
		}

		if (m.getTotalSize() == fileSize) {
			return FileDownloadService.DONWLOAD_OVER;
		}

		if (m.getCancelStatus() == FileDownloadModel.CANCEL_BY_ERROR) {
			return FileDownloadService.DONWLOAD_ERROR;
		} else {
			return FileDownloadService.DONWLOAD_CANCEL;
		}
	}
	
	public List<FileDownloadModel> getAllDownloadModel() {

		mLock.lock();
		SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null,
				COL_CREATE_TIME + " desc");
		List<FileDownloadModel> datas = new ArrayList<FileDownloadModel>();

		while (cursor != null && cursor.moveToNext()) {
			FileDownloadModel m = FileDownloadModelBuilder.build(cursor);
			datas.add(m);
		}

		if (cursor != null) {
			cursor.close();
		}

		db.close();
		mLock.unlock();
		return datas;
	}

	private static class FileDownloadModelBuilder {

		public static FileDownloadModel build(Cursor cursor) {

			int colCreateTime = cursor.getColumnIndex(COL_CREATE_TIME);
			int colPkgName = cursor.getColumnIndex(COL_PACKAGE_NAME);
			int colGn = cursor.getColumnIndex(COL_GAME_NAME);
			int colDlUrl = cursor.getColumnIndex(COL_DOWNLOAD_URL);
			int colPic = cursor.getColumnIndex(COL_PIC_URL);
			int colFileSize = cursor.getColumnIndex(COL_FILE_TOTAL_SIZE);
			int colCancelStatus = cursor.getColumnIndex(COL_CANCEL_STATUS);

			FileDownloadModel m = new FileDownloadModel(
					cursor.getString(colCreateTime),
					cursor.getString(colPkgName), cursor.getString(colGn),
					cursor.getString(colDlUrl), cursor.getString(colPic),
					cursor.getLong(colFileSize), cursor.getInt(colCancelStatus));
			return m;
		}

		public static ContentValues deconstruct(FileDownloadModel m) {
			ContentValues values = new ContentValues();
			values.put(COL_INDENTIFIER, m.getIndentfier());
			values.put(COL_CREATE_TIME, m.getCreateTime());
			values.put(COL_PACKAGE_NAME, m.getPackageName());
			values.put(COL_GAME_NAME, m.getGameName());
			values.put(COL_DOWNLOAD_URL, m.getDownloadUrl());
			values.put(COL_PIC_URL, m.getPicUrl());
			values.put(COL_FILE_TOTAL_SIZE, m.getTotalSize());
			values.put(COL_CANCEL_STATUS, m.getCancelStatus());

			return values;
		}
	}

}
