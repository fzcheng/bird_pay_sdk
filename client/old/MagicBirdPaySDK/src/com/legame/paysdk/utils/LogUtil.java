package com.legame.paysdk.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.legame.paysdk.GlobalVal;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;


/** 
 * 类说明：   
 * @author  xinhui.cheng
 * @date    2012-6-4
 * @version 1.0
 */
public class LogUtil {
	private static final boolean DEBUG = GlobalVal.sDebug; 
	private static final boolean WRITE_FILE_LOG = GlobalVal.sDebug;
	
	public static void i(String tag , String msg){
		if (DEBUG) {
			Log.i(tag, msg);
			
			if (WRITE_FILE_LOG) {
				writeToLog("I/" + tag + ": " + msg);
			}
		}
	}
	public static void e(String tag , String msg){
		if (DEBUG) {
			Log.e(tag, msg);
			
			if (WRITE_FILE_LOG) {
				writeToLog("E/" + tag + ": " + msg);
			}
		}
	}
	
	public static void e(String tag, String msg, Throwable e) {
		if (DEBUG) {
			Log.e(tag, msg, e);
			
			if (WRITE_FILE_LOG) {
				writeToLog("E/" + tag + ": " + msg);
			}
		}
	}
	
	public static void d(String tag , String msg){
		if (DEBUG) {
			Log.d(tag, msg);
			
			if (WRITE_FILE_LOG) {
				writeToLog("D/" + tag + ": " + msg);
			}
		}
	}
	public static void w(String tag , String msg){
		if (DEBUG) {
			Log.w(tag, msg);
			
			if (WRITE_FILE_LOG) {
				writeToLog("W/" + tag + ": " + msg);
			}
		}
	}
	
	public static void v(String tag , String msg){
		if (DEBUG) {
			Log.v(tag, msg);
			
			if (WRITE_FILE_LOG) {
				writeToLog("W/" + tag + ": " + msg);
			}
		}
	}
	
	private static Handler mLogHdlr = new FileLogHandler(Looper.getMainLooper());
	
	private static void writeToLog(String log) {
		Message msg = mLogHdlr.obtainMessage();
		msg.obj = log;
		mLogHdlr.sendMessage(msg);
	}
	
	static class FileLogHandler extends Handler {
		private boolean				mHasSDCard	= true;
		private FileOutputStream	mLogOutput;
		private File				mLogFile;
		
		FileLogHandler() {
			init();
		}
		
		FileLogHandler(Looper looper) {
			super(looper);
			init();
		}
		
		void init() {
			mHasSDCard = hasExternalStorage();
			
			if (mHasSDCard) {
				try {
					mLogFile = new File(Config.ROOT_DIR, "log.txt");
					
					if (!mLogFile.exists()) {
						mLogFile.createNewFile();
					}
				} catch (IOException e) {}
			}
		}
		
		boolean hasExternalStorage(){
			return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
		}
		
		public void handleMessage(Message msg) {
			if (!mHasSDCard) {
				return;
			}

			try {
				String log = (String) msg.obj + "\n";
				if (log != null) {
					byte[] logData = log.getBytes();
					getLogOutput().write(logData, 0, logData.length);
				}
			} catch (Exception e) {
			}
		}

		FileOutputStream getLogOutput() throws Exception {
			if (mLogOutput == null) {
				mLogOutput = new FileOutputStream(mLogFile, true);
			}
			
			return mLogOutput;
		}
	}
}
