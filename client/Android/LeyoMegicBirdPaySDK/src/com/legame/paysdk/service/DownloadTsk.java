package com.legame.paysdk.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Map;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.legame.paysdk.network.utils.NetTools;
import com.legame.paysdk.utils.BuildConfig;
import com.legame.paysdk.utils.Config;

public class DownloadTsk extends AsyncTask<Void, Long, Void> {
	private static final String TAG = "DownloadTsk";

	public static final int TSK_CODE_START = 0x01;// start
	public static final int TSK_CODE_PAUSE = 0x02;// pause
	public static final int TSK_CODE_SUCCESS = 0x03;// success
	public static final int TSK_CODE_FAIL = 0x04;// fail
	public static final int TSK_CODE_PARAM_ERROR = 0x05;// param error
	public static final int TSK_CODE_LEN_ERROR = 0x06;// content length error
	public static final int TSK_CODE_TIMEOUT_ERROR = 0x07;// timeout error
	public static final int TSK_CODE_NET_ERROR = 0x08;// net error
	
	public static final int TSK_CODE_PROGRESS = 0x09;// net error

	public static final String TEMP_FILE_SUFFIX = ".tmp";

	/**
	 * 超时时间 30秒
	 * */
	private final static int TIME_OUT = 30 * 1000;

	/**
	 * 每次接受256K
	 * */
	private final static int RECV_BUF_SIZE = 256 * 1024;

	public interface onDownloadListener {
		public void onDownloadResult(Long... tskResult);
	}

	private Context mContext;
	String mDownloadUrl;
	String mSaveFileName;
	private onDownloadListener mDownloadCallback;

	// for call back
	private long mFileDownloadedSize;
	private boolean mPause;

	// temp file
	private File mTmpSaveFile;

	public DownloadTsk(Context context, onDownloadListener callback,
			String downloadUrl, String saveFileName) {
		mContext = context;
		mDownloadCallback = callback;
		mDownloadUrl = downloadUrl;
		mSaveFileName = saveFileName;
		mPause = false;
	}

	@Override
	protected Void doInBackground(Void... params) {
		if (BuildConfig.DEBUG) {
			Log.d(TAG, "doInBackground,mDownloadUrl : " + mDownloadUrl
					+ ",mSaveFileName : " + mSaveFileName);
		}
		// first,check request params
		if (mDownloadUrl == null || TextUtils.equals(mDownloadUrl.trim(), "")
				|| mSaveFileName == null
				|| TextUtils.equals(mSaveFileName.trim(), "")) {
			publishProgress((long) TSK_CODE_PARAM_ERROR);
			return null;
		}
		// create temp file
		createTempSaveFile();
		// do http request, fetch file size
		doHttpRequest();
		return null;
	}

	@Override
	protected void onProgressUpdate(Long... values) {
		super.onProgressUpdate(values);
		if (mDownloadCallback != null) {
			mDownloadCallback.onDownloadResult(values);
		}
	}

	public void pause() {
		mPause = true;
	}

	private void createTempSaveFile() {
		File dir = new File(Config.DOWNLOAD_DIR);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File temp = new File(dir, mSaveFileName + TEMP_FILE_SUFFIX);
		/**
		 * because it support break-point resume
		 * <p>
		 * so, every new version upgrade should have unique file name
		 * <p>
		 * since it does not pull down md5 value
		 */

		if (temp.exists()) {
			mFileDownloadedSize = temp.length();
		} else {
			try {
				temp.createNewFile();
			} catch (IOException e) {
				if (BuildConfig.DEBUG) {
					Log.e(TAG, "IOException", e);
				}
			}
			mFileDownloadedSize = 0;
		}
		mTmpSaveFile = temp;
	}

	private void deleteTmpSaveFileIfNeeded() {
		File dir = new File(Config.DOWNLOAD_DIR);
		if (!dir.exists()) {
			// not exist
			return;
		}
		File temp = new File(dir, mSaveFileName + TEMP_FILE_SUFFIX);
		if (temp.exists()) {
			temp.delete();
		}
	}

	private File getSaveFile() {
		File dir = new File(Config.DOWNLOAD_DIR);
		if (!dir.exists()) {
			// not exist
			return null;
		}
		File saveFile = new File(dir, mSaveFileName);
		if (saveFile.exists()) {
			return saveFile;
		}
		return null;
	}

	private boolean checkUserCancel(HttpURLConnection conn) {
		if (mPause) {
			// check if user canceled
			conn.disconnect();
			publishProgress((long) TSK_CODE_PAUSE);// pause
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @see{project LeGame}
	 */
	private void doHttpRequest() {
		HttpURLConnection conn = null;
		try {
			URL url = new URL(mDownloadUrl);
			if (NetTools.isCmwap(mContext)) {
				conn = NetTools.getCmwapConnect(mDownloadUrl);
			} else {
				conn = (HttpURLConnection) url.openConnection();
			}
		} catch (Exception e) {
			if (BuildConfig.DEBUG) {
				Log.e(TAG, "Exception", e);
			}
			publishProgress((long) TSK_CODE_FAIL);
			return;// break following code
		}

		try {
			// 设置http请求
			conn.setConnectTimeout(TIME_OUT);
			conn.setReadTimeout(TIME_OUT);
			conn.setRequestMethod("GET");
			NetTools.setCommonHttpHeader(conn);
			if (mFileDownloadedSize > 0) { // 支持断点续传
				conn.setRequestProperty("Range", "bytes=" + mFileDownloadedSize
						+ "-");
			}
			conn.connect(); // 发起链接
		} catch (SocketTimeoutException e) {
			if (BuildConfig.DEBUG) {
				Log.e(TAG, "SocketTimeoutException", e);
			}
			conn.disconnect();
			publishProgress((long) TSK_CODE_TIMEOUT_ERROR);
			return;
		} catch (Exception e) {
			if (BuildConfig.DEBUG) {
				Log.e(TAG, "Exception", e);
			}
			conn.disconnect();
			publishProgress((long) TSK_CODE_FAIL);
		}
		if (checkUserCancel(conn)) {
			return;
		}
		try {
			int statusCode = conn.getResponseCode();
			if (statusCode != 200 && statusCode != 206) {
				if (BuildConfig.DEBUG) {
					Log.e(TAG, "http status code:" + statusCode);
				}
				conn.disconnect();
				publishProgress((long) TSK_CODE_FAIL);
				return;
			} else {
				// 有可能服务器不支持断点续传，则需要从头开始下载
				if (statusCode == 200) {
					mFileDownloadedSize = 0;
					deleteTmpSaveFileIfNeeded();
				}
				Map<String, String> header = NetTools
						.getHttpResponseHeader(conn);
				String contentLengthStr = header.get("content-length");
				long contentLen = 0;

				if (contentLengthStr == null || contentLengthStr.equals("")) {
					contentLen = 0;
				} else {
					contentLen = Long.parseLong(contentLengthStr);
				}
				if (contentLen < 0) {
					conn.disconnect();
					publishProgress((long) TSK_CODE_LEN_ERROR);
					return;
				}
				File savedFile = getSaveFile();
				if (savedFile != null) {
					if (savedFile.length() == contentLen) {
						/**
						 * it has been previously downloaded at some time
						 * <p>
						 * in this situation, mFileDownloadedSize must be 0
						 */
						publishProgress((long) TSK_CODE_SUCCESS);
						return;
					} else {
						savedFile.delete();
					}
				}
				publishProgress((long) TSK_CODE_START, contentLen
						+ mFileDownloadedSize);
			}
		} catch (SocketTimeoutException e) {
			if (BuildConfig.DEBUG) {
				Log.e(TAG, "SocketTimeoutException", e);
			}
			conn.disconnect();
			publishProgress((long) TSK_CODE_TIMEOUT_ERROR);
			return;
		} catch (IOException e) {
			if (BuildConfig.DEBUG) {
				Log.e(TAG, "IOException", e);
			}
			conn.disconnect();
			publishProgress((long) TSK_CODE_NET_ERROR);
			return;
		}
		if (checkUserCancel(conn)) {
			return;
		}
		InputStream is = null;
		RandomAccessFile rafileHandle = null;
		try {
			int readed = 0;
			is = conn.getInputStream();
			byte[] buffer = new byte[RECV_BUF_SIZE];
			rafileHandle = new RandomAccessFile(mTmpSaveFile, "rw");
			while (!mPause
					&& (readed = is.read(buffer, 0, RECV_BUF_SIZE)) != -1) {
				rafileHandle.seek(mFileDownloadedSize);
				rafileHandle.write(buffer, 0, readed);
				mFileDownloadedSize += readed;
				// if report progress needed, send callback here
				//TODO
				publishProgress((long) TSK_CODE_PROGRESS, mFileDownloadedSize);
			}
			if (checkUserCancel(conn)) {
				return;
			}
		} catch (IOException e) {
			if (BuildConfig.DEBUG) {
				Log.e(TAG, "IOException", e);
			}
			conn.disconnect();
			publishProgress((long) TSK_CODE_NET_ERROR);
			return;
		} finally {
			try {
				if (rafileHandle != null) {
					rafileHandle.close();
				}

				if (is != null) {
					is.close();
				}
				conn.disconnect();
			} catch (IOException e) {
				// nothing
			}
		}
		// rename
		String destFilePath = mTmpSaveFile.getAbsolutePath();
		int index = destFilePath.indexOf(TEMP_FILE_SUFFIX);
		String newFileName = destFilePath.substring(0, index);
		mTmpSaveFile.renameTo(new File(newFileName));
		publishProgress((long) TSK_CODE_SUCCESS);
	}

}
