package com.legame.paysdk.db;

import java.io.File;
import java.lang.reflect.Method;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;

import com.legame.paysdk.utils.LogUtil;

/** 
 * 类说明：   
 * @author  Terry.Lu
 * @date    2013年10月14日
 * @version 1.0
 */
public abstract class ExtenalStorageDBHelper {
	private static final String TAG = ExtenalStorageDBHelper.class.getSimpleName();
	private final Context mContext;
	private final String mName;
	private String mDirectory;
	private final CursorFactory mFactory;
	private final int mNewVersion;

	protected SQLiteDatabase mDatabase = null;
//	protected SQLiteDatabase newDatabase = null;
//	protected SQLiteDatabase oldDatabase = null;
	
	private boolean mIsInitializing = false;
	
	public ExtenalStorageDBHelper(Context context, String dir, String name, CursorFactory factory, int version) {
		if (version < 1) throw new IllegalArgumentException("Version must be >= 1, was " + version);
		mDirectory = dir;
		mContext = context;
		mName = name;
		mFactory = factory;
		mNewVersion = version;
	}
	
	public synchronized SQLiteDatabase getWritableDatabase() {
		if (mDatabase != null && mDatabase.isOpen() && !mDatabase.isReadOnly()) {
			return mDatabase;  // The database is already open for business
		}

		if (mIsInitializing) {
			throw new IllegalStateException("getWritableDatabase called recursively");
		}

		// If we have a read-only database open, someone could be using it
		// (though they shouldn't), which would cause a lock to be held on
		// the file, and our attempts to open the database read-write would
		// fail waiting for the file lock.  To prevent that, we acquire the
		// lock on the read-only database, which shuts out other users.

		boolean success = false;
		SQLiteDatabase db = null;
		if (mDatabase != null) lockDb(mDatabase);
		try {
			mIsInitializing = true;
			if (mName == null) {
				db = SQLiteDatabase.create(null);
			} else {
				File dbDir = new File(mDirectory);
				
				if (!dbDir.exists()) {
					dbDir.mkdirs();
				}
				
				File dbFile = new File(mDirectory, mName);
				db = SQLiteDatabase.openOrCreateDatabase(dbFile, mFactory);
			}

			int version = db.getVersion();
			if (version != mNewVersion) {
				db.beginTransaction();
				try {
					if (version == 0) {
						onCreate(db);
					} else {
						onUpgrade(db, version, mNewVersion);
					}
					db.setVersion(mNewVersion);
					db.setTransactionSuccessful();
				} finally {
					db.endTransaction();
				}
			}

			onOpen(db);
			success = true;
			return db;
		} finally {
			mIsInitializing = false;
			if (success) {
				if (mDatabase != null) {
					try { mDatabase.close(); } catch (Exception e) { }
					unlockDb(mDatabase);
				}
				mDatabase = db;
			} else {
				if (mDatabase != null) unlockDb(mDatabase);
				if (db != null) db.close();
			}
		}
	}
	
	public synchronized SQLiteDatabase getReadableDatabase() {
		if (mDatabase != null && mDatabase.isOpen()) {
			return mDatabase;  // The database is already open for business
		}

		if (mIsInitializing) {
			throw new IllegalStateException("getReadableDatabase called recursively");
		}

		try {
			return getWritableDatabase();
		} catch (SQLiteException e) {
			if (mName == null) throw e;  // Can't open a temp database read-only!
			LogUtil.e(TAG, "Couldn't open " + mName + " for writing (will try read-only):", e);
		}

		SQLiteDatabase db = null;
		try {
			mIsInitializing = true;
			File dbFile = new File(mDirectory, mName);
			try {
				db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(), mFactory, SQLiteDatabase.OPEN_READONLY);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (db.getVersion() != mNewVersion) {
				throw new SQLiteException(
						"Can't upgrade read-only database from version "
								+ db.getVersion() + " to " + mNewVersion + ": "
								+ dbFile.getAbsolutePath());
			}
			onOpen(db);
			LogUtil.w(TAG, "Opened " + mName + " in read-only mode");
			mDatabase = db;
			
			return mDatabase;
		} finally {
			mIsInitializing = false;
			if (db != null && db != mDatabase) db.close();
		}
	}
	
	public synchronized void close() {
		if (mIsInitializing) throw new IllegalStateException("Closed during initialization");

		if (mDatabase != null && mDatabase.isOpen()) {
			mDatabase.close();
			mDatabase = null;
		}
	}
	
	public abstract void onCreate(SQLiteDatabase db);
	
	public abstract void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);
	
	public void onOpen(SQLiteDatabase db) {
		//TODO
	}
	
	private void lockDb(SQLiteDatabase db) {
		Class<?> sqliteCls = db.getClass();
		Class<?>[] paramTypes = new Class<?>[0];
		Object[] params = new Object[0];
		
		try {
			Method lockMD = sqliteCls.getDeclaredMethod("lock", paramTypes);
			lockMD.setAccessible(true);
			lockMD.invoke(db, params);
		}  catch (Exception e) {
//			e.printStackTrace();
		}
	}
	
	private void unlockDb(SQLiteDatabase db) {
		Class<?> sqliteCls = db.getClass();
		Class<?>[] paramTypes = new Class<?>[0];
		Object[] params = new Object[0];
		
		try {
			Method unlockMD = sqliteCls.getDeclaredMethod("unlock", paramTypes);
			unlockMD.setAccessible(true);
			unlockMD.invoke(db, params);
		} catch (Exception e) {
//			e.printStackTrace();
		}
	}
	
	/**
	 * 改变数据库路径的方法
	 * @param dir
	 */
	protected void changeDir(String dir){
		mDirectory = dir;
	}
}
