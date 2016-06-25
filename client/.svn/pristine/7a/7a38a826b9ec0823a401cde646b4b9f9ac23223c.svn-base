package com.legame.paysdk.download;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Map;

import com.legame.paysdk.network.utils.NetTools;
import com.legame.paysdk.utils.Config;
import com.legame.paysdk.utils.LogUtil;

import android.content.Context;
import android.os.Handler;
import android.util.Log;


/**
 * 类说明：
 * 
 * @author Toby.chen
 * @date 2013-7-11
 * @version 1.0
 */

public class FileDownloadTask {

	public static final String TAG = "FileDownloadTask";

	public static final int USER_CANCELED = 0;
	public static final int NET_CONNECTED = 1;
	public static final int BUF_UPDATE = 2;
	public static final int DOWNLOAD_OVER = 3;

	public static final int UNKONW_NET_ERROR = -1;
	public static final int CREATE_URL_CONN_ERROR = -2;
	public static final int CONNECT_TIME_OUT = -3;
	public static final int READ_HEADER_TIME_OUT = -4;
	public static final int READ_BODY_TIME_OUT = -5;
	public static final int HTTP_STATE_CODE_ERROR = -6;
	public static final int CONTENT_LEN_ERROR = -7;
	public static final int SAVE_BODY_IO_ERROR = -8;

	/**
	 * 超时时间 30秒
	 * */
	private final static int TIME_OUT = 30 * 1000;

	/**
	 * 每次接受256K
	 * */
	private final static int RECV_BUF_SIZE = 256 * 1024;

	private Context mContext = null;
	private String mDownloadUlr = null;
	private DownloadTaskListener mListener = null;
	private String mTaskIndentifier = null;
	private String mTmpFilePath = null;
	private String mSaveFilePath = null;
	private long mDownLoadedSize = 0;
	private boolean mStopDownload = false;
	private boolean mIsDownloading = false;
	private static Handler mHandler = new Handler();

	public FileDownloadTask(Context c, String indentifier, String downloadUrl,
			DownloadTaskListener l) {
		mContext = c;
		mDownloadUlr = downloadUrl;
		mListener = l;
		mTaskIndentifier = indentifier;

		initFileSys();
	}

	public String getDownloadedFilePath() {
		return mSaveFilePath;
	}

	public String getTempFilePath() {
		return mTmpFilePath;
	}

	public boolean isDownloading() {
		return mIsDownloading;
	}

	public void stopDownload() {
		mStopDownload = true;
	}

	public void startDownload() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				doDownLoadTask();
			}
		}).start();
	}

	// public static String getSaveFilePath(String indentifier){
	// return Config.DOWNLOAD_DIR + File.separator + indentifier
	// + ".apk";
	// }

	private void initFileSys() {
		File fdir = new File(Config.DOWNLOAD_DIR);
		if (!fdir.exists()) {
			fdir.mkdir(); // 如果路径不存在，则先建立一个
		}

		mSaveFilePath = Config.DOWNLOAD_DIR + File.separator + mTaskIndentifier
				+ ".apk";
		mTmpFilePath = mSaveFilePath + ".dl";

		File fh = new File(mTmpFilePath);
		if (fh.exists()) {
			mDownLoadedSize = fh.length();
		} else {
			mDownLoadedSize = 0;
		}
	}

	private void doDownLoadTask() {
		HttpURLConnection conn = null;
		mStopDownload = false;
		mIsDownloading = true;

		try {
			URL url = new URL(mDownloadUlr);
			if (NetTools.isCmwap(mContext)) {
				conn = NetTools.getCmwapConnect(mDownloadUlr);
			} else {
				conn = (HttpURLConnection) url.openConnection();
			}
		} catch (Exception e) {
			e.printStackTrace();
			sendMessage(CREATE_URL_CONN_ERROR, null);
			mIsDownloading = false;
			return;
		}

		try {
			// 设置http请求
			conn.setConnectTimeout(TIME_OUT);
			conn.setReadTimeout(TIME_OUT);
			conn.setRequestMethod("GET");
			NetTools.setCommonHttpHeader(conn);
			if (mDownLoadedSize > 0) { // 支持断点续传
				conn.setRequestProperty("Range", "bytes=" + mDownLoadedSize
						+ "-");
			}

			conn.connect(); // 发起链接
		} catch (SocketTimeoutException ste) {
			ste.printStackTrace();

			sendMessage(CONNECT_TIME_OUT, null);
			mIsDownloading = false;
			conn.disconnect();
			return;
		} catch (Exception e) {
			e.printStackTrace();

			sendMessage(UNKONW_NET_ERROR, null);
			mIsDownloading = false;
			conn.disconnect();
			return;
		}

		if (mStopDownload) {
			conn.disconnect();
			mIsDownloading = false;
			sendMessage(USER_CANCELED, null);
			return;
		}

		try {
			int statusCode = conn.getResponseCode();
			if (statusCode != 200 && statusCode != 206) {

				LogUtil.e(TAG, "http status code:" + statusCode);

				sendMessage(HTTP_STATE_CODE_ERROR, null);
				mIsDownloading = false;
				conn.disconnect();
				return;

			} else {
				// 有可能服务器不支持断点续传，则需要从头开始下载
				if (statusCode == 200) {
					mDownLoadedSize = 0;
					File tmp = new File(mTmpFilePath);
					tmp.delete();
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

				if (contentLen <= 0) {
					mIsDownloading = false;
					conn.disconnect();

					sendMessage(CONTENT_LEN_ERROR, 0);
					return;
				}

				sendMessage(NET_CONNECTED,
						Long.valueOf(contentLen + mDownLoadedSize));
			}
		} catch (SocketTimeoutException ste) {
			ste.printStackTrace();

			mIsDownloading = false;
			conn.disconnect();
			sendMessage(READ_HEADER_TIME_OUT, 0);
			return;
		} catch (IOException e) {
			e.printStackTrace();

			mIsDownloading = false;
			conn.disconnect();
			sendMessage(UNKONW_NET_ERROR, 0);
			return;
		}

		if (mStopDownload) {
			mIsDownloading = false;
			conn.disconnect();
			sendMessage(USER_CANCELED, null);
			return;
		}

		InputStream is = null;
		RandomAccessFile rafileHandle = null;
		try {
			int readed = 0;
			is = conn.getInputStream();
			byte[] buffer = new byte[RECV_BUF_SIZE];
			rafileHandle = new RandomAccessFile(new File(mTmpFilePath), "rw");

			while (!mStopDownload
					&& ((readed = is.read(buffer, 0, RECV_BUF_SIZE)) != -1)) {
				rafileHandle.seek(mDownLoadedSize);
				rafileHandle.write(buffer, 0, readed);
				mDownLoadedSize += readed;

				Thread.sleep(50);
				sendMessage(BUF_UPDATE, Long.valueOf(mDownLoadedSize));
			}

			if (mStopDownload) {
				mIsDownloading = false;
				sendMessage(USER_CANCELED, 0);
				return;
			}

		} catch (SocketTimeoutException ste) {
			LogUtil.e(TAG, "!!!!!!write time out ERROR!!!!!");
			ste.printStackTrace();

			mIsDownloading = false;
			sendMessage(READ_BODY_TIME_OUT, 0);
			return;
		} catch (IOException ioe) {
			LogUtil.e(TAG, "!!!!!!write ERROR!!!!!");
			ioe.printStackTrace();

			mIsDownloading = false;
			sendMessage(SAVE_BODY_IO_ERROR, 0);
			return;
		} catch (Exception e) {
			Log.i(TAG, "!!!!!!write UNKNOW!!!!ERROR!!!!!");
			e.printStackTrace();

			mIsDownloading = false;
			sendMessage(UNKONW_NET_ERROR, 0);
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
			} catch (Exception e) {
				Log.i(TAG, "!!!!!!is.close() ERROR!!!!!");

				mIsDownloading = false;
				sendMessage(UNKONW_NET_ERROR, 0);
				return;
			}
		}

		File save = new File(mSaveFilePath);
		File tmp = new File(mTmpFilePath);

		if (!save.exists()) {
			// 把临时文件名重命名为正式文件名
			tmp.renameTo(save);
		} else {
			save.delete();
			tmp.renameTo(save);
		}

		mIsDownloading = false;
		sendMessage(DOWNLOAD_OVER, 0);
	}

	private void sendMessage(int msg, Object o) {

		if (msg < 0) {
			mHandler.post(new ErrorRunnable(msg));

		} else if (USER_CANCELED == msg) {
			mHandler.post(new UserCancelRunnable());

		} else if (NET_CONNECTED == msg) {
			long len = ((Long) o).longValue();
			mHandler.post(new NetConnectedRunnable(len));

		} else if (BUF_UPDATE == msg) {
			long downloaded = ((Long) o).longValue();
			mHandler.post(new BufUpdateRunnable(downloaded));

		} else if (DOWNLOAD_OVER == msg) {
			mHandler.post(new DownloadOverRunnable());

		}

	}

	private class ErrorRunnable implements Runnable {
		private int mErrType = 0;

		public ErrorRunnable(int t) {
			mErrType = t;
		}

		@Override
		public void run() {
			if (mListener != null) {
				mListener.onError(mTaskIndentifier, mErrType);
			}
		}
	}

	private class UserCancelRunnable implements Runnable {
		@Override
		public void run() {
			if (mListener != null) {
				mListener.OnUserCanceled(mTaskIndentifier);
			}
		}
	}

	private class NetConnectedRunnable implements Runnable {

		private long mFileLen = 0;

		public NetConnectedRunnable(long fileLen) {
			mFileLen = fileLen;
		}

		@Override
		public void run() {
			if (mListener != null) {
				mListener.OnGetContentLength(mTaskIndentifier, mFileLen);
			}
		}
	}

	private class BufUpdateRunnable implements Runnable {
		private long mDownloadedLen = 0;

		public BufUpdateRunnable(long dlSize) {
			mDownloadedLen = dlSize;
		}

		@Override
		public void run() {
			if (mListener != null) {
				mListener.OnBufferUpdate(mTaskIndentifier, mDownloadedLen);
			}
		}
	}

	private class DownloadOverRunnable implements Runnable {
		@Override
		public void run() {
			if (mListener != null) {
				mListener.onDownloadFinish(mTaskIndentifier);
			}
		}
	}

	public interface DownloadTaskListener {

		/**
		 * 回调函数，在http下载过程中发生错误后会回调该函数
		 * 
		 * @param indentifier
		 *            下载任务标识
		 * 
		 * @param msg
		 *            错误原因
		 * */
		public void onError(String indentifier, int msg);

		/**
		 * 回调函数，通知下载的文件总大小
		 * 
		 * @param size
		 *            文件大小，单位bytes
		 * */
		public void OnGetContentLength(String indentifier, long size);

		/**
		 * 回调函数，通知下载已经完成
		 * */
		public void onDownloadFinish(String indentifier);

		/**
		 * 回调函数，通知用户的取消操作已经完成
		 * */
		public void OnUserCanceled(String indentifier);

		/**
		 * 回调函数，通知用户当前缓冲的情况
		 * */
		public void OnBufferUpdate(String indentifier, long downloadedSize);
	}
}
