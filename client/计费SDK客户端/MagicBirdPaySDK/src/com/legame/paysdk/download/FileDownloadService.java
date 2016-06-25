package com.legame.paysdk.download;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.legame.paysdk.download.FileDownloadTask.DownloadTaskListener;
import com.legame.paysdk.receiver.FileDownloadStatusReceiver;
import com.legame.paysdk.utils.Config;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.text.TextUtils;


/**
 * 类说明：
 * 
 * @author Toby.chen
 * @date 2013-7-11
 * @version 1.0
 */

public class FileDownloadService extends Service implements
		DownloadTaskListener {

	public static final String START_SERVICE_CMD = "com.legame.paysdk.download.startDownloadFileService";
	public static final String STOP_SERVICE_CMD = "com.legame.paysdk.download.stopDownloadFileService";

	public static final int DONWLOADING = 0;
	public static final int DONWLOAD_ERROR = 1;
	public static final int DONWLOAD_CANCEL = 2;
	public static final int DONWLOAD_OVER = 3;
	public static final int DONWLOAD_NOT_EXISIT = 4;

	//startservice 下载参数
	public static final String EXTRA_DL_URL = "com.legame.paysdk.filedownloadservice.extra.DL_URL";
	public static final String EXTRA_PIC_URL = "com.legame.paysdk.filedownloadservice.extra.PIC_URL";
	public static final String EXTRA_PKG_NAME = "com.legame.paysdk.filedownloadservice.extra.PKG_NAME";
	public static final String EXTRA_GAME_NAME = "com.legame.paysdk.filedownloadservice.extra.GAME_NAME";

	// broadcast 下载通知
	public static final String ACTION_FILEDOWNLOAD_NOTIFICATION = "com.legame.paysdk.intent.ACTION_FILEDOWNLOAD_NOTIFICATION";
	public static final String DOWNLOAD_FILE_INDENTIFIER = "com.legame.paysdk.intent.ACTION_FILEDOWNLOAD_NOTIFICATION.FILE_INDENTIFIER";
	public static final String DOWNLOAD_EVENT_CODE = "com.legame.paysdk.intent.ACTION_FILEDOWNLOAD_NOTIFICATION.EVENT_CODE";
	public static final String DOWNLOAD_PARAM_SIZE = "com.legame.paysdk.intent.ACTION_FILEDOWNLOAD_NOTIFICATION.PARAM_SIZE";
	
	//下载EVENT_CODE对应状态
	public static final int DOWNLOAD_ERROR = 0x01;
	public static final int DOWNLOAD_FINISH = 0x02;
	public static final int DOWNLOAD_UPDATEBUFFER = 0x03;
	public static final int DOWNLOAD_CONTENTLENGTH = 0x04;
	public static final int DOWNLOAD_USERCANCEL = 0x05;

	private final FileDownloadServiceBinder mServiceBinder = new FileDownloadServiceBinder();
	private Map<String, FileDownloadTask> mDownloadingTasks = null;
	private RemoteCallbackList<IDownloadFileCallback> mCallbacks = null;

	private FileDownloadStatusReceiver mReceiver = null;
	
	@Override
	public void onCreate() {
		super.onCreate();
		mDownloadingTasks = new ConcurrentHashMap<String, FileDownloadTask>();
		mCallbacks = new RemoteCallbackList<IDownloadFileCallback>();
		registerBroadcastReceiver();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (intent == null) {
			return super.onStartCommand(intent, flags, startId);
		}
		String action = intent.getAction();
		if (action == null) {
			return super.onStartCommand(intent, flags, startId);
		}
		if (TextUtils.equals(action, START_SERVICE_CMD)) {
			String downloadUrl = intent.getStringExtra(EXTRA_DL_URL);
			String picUrl = intent.getStringExtra(EXTRA_PIC_URL);
			String pkgName = intent.getStringExtra(EXTRA_PKG_NAME);
			String gameName = intent.getStringExtra(EXTRA_GAME_NAME);
			startDownloadTask(downloadUrl, picUrl, pkgName, gameName);
		}
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return mServiceBinder;
	}

	@Override
	public boolean onUnbind(Intent intent) {
		super.onUnbind(intent);
		return true;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Set<String> keySet = mDownloadingTasks.keySet();
		for (String key : keySet) {
			SendBroadCast(key, DOWNLOAD_ERROR);
		}
		mCallbacks.kill();
		unregisterBroadcastReceiver();
	}
	
	private void registerBroadcastReceiver() {
		IntentFilter filter = new IntentFilter();

		filter.addAction(ACTION_FILEDOWNLOAD_NOTIFICATION);
		mReceiver = new FileDownloadStatusReceiver();
		registerReceiver(mReceiver, filter);
	}
	
	private void unregisterBroadcastReceiver() {
		if (mReceiver != null) {
			unregisterReceiver(mReceiver);
			mReceiver = null;
		}
	}

	public void registerCallback(IDownloadFileCallback cb)
			throws RemoteException {
		if (cb != null) {
			mCallbacks.register(cb);
		}
	}

	public void unregisterCallback(IDownloadFileCallback cb)
			throws RemoteException {
		if (cb != null) {
			mCallbacks.unregister(cb);
		}
	}

	public boolean startDownloadTask(String dlUrl, String picUrl,
			String pkgName, String gameName) {

		FileDownloadModel f = null;

		String indentifier = "" + pkgName.hashCode();
		if ((f = FileDownloadDatabase.instance(this)
				.getFileDonwloadModeByIndentifier(indentifier)) == null) {
			FileDownloadModel m = new FileDownloadModel();
			m.setPackageName(pkgName);
			m.setGameName(gameName);
			m.setDownloadUrl(dlUrl);
			m.setPicUrl(picUrl);

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
					Locale.CHINA);
			m.setCreateTime(df.format(new Date()));
			FileDownloadDatabase.instance(this).insert(m);

		} else {
			if (f.getCancelStatus() == FileDownloadModel.CANCEL_BY_ERROR) { // 恢复初始状态
				f.setCancelStatus(FileDownloadModel.CANCEL_BY_USER);
				FileDownloadDatabase.instance(this).update(f);
			}
		}

		FileDownloadTask t = mDownloadingTasks.get(indentifier);
		if (t != null) {
			return false;
		}

		t = new FileDownloadTask(this, indentifier, dlUrl, this);
		mDownloadingTasks.put(indentifier, t);

		t.startDownload();
		return true;
	}

	public void stopDownload(String pkgName) {
		String indentifier = "" + pkgName.hashCode();

		FileDownloadTask t = mDownloadingTasks.get(indentifier);
		if (t != null && t.isDownloading()) {
			t.stopDownload();
		}
	}

	public void deleteDownloadTask(String pkgName) {
		String indentifier = "" + pkgName.hashCode();
		FileDownloadTask t = mDownloadingTasks.get(indentifier);
		if (t != null && t.isDownloading()) {
			t.stopDownload();
		}

		FileDownloadDatabase.instance(this).deleteByIndentifier(indentifier);
	}

	public void clearDownloadTask() {
		Collection<FileDownloadTask> values = mDownloadingTasks.values();
		for (FileDownloadTask t : values) {
			if (t.isDownloading()) {
				t.stopDownload();
			}
		}

		FileDownloadDatabase.instance(this).clearAllDatas();
	}

	public int getDownloadTaskStatus(String pkgName) {
		String indentifier = "" + pkgName.hashCode();
		FileDownloadTask t = mDownloadingTasks.get(indentifier);
		if (t != null && t.isDownloading()) {
			return DONWLOADING;
		}

		String savePath = Config.DOWNLOAD_DIR + File.separator + indentifier + ".apk";
		File saveFile = new File(savePath);
		long fileSize = -9999;
		if (saveFile.exists()) {
			fileSize = saveFile.length();
		}

		FileDownloadModel m = FileDownloadDatabase.instance(this)
				.getFileDonwloadModeByIndentifier(indentifier);
		if (m == null) {
			return DONWLOAD_NOT_EXISIT;
		}

		if (m.getTotalSize() == fileSize) {
			return DONWLOAD_OVER;
		}

		if (m.getCancelStatus() == FileDownloadModel.CANCEL_BY_ERROR) {
			return DONWLOAD_ERROR;
		} else {
			return DONWLOAD_CANCEL;
		}
	}

	public long getDownloadTaskFileSize(String pkgName) {

		String indentifier = "" + pkgName.hashCode();

		FileDownloadModel m = FileDownloadDatabase.instance(this)
				.getFileDonwloadModeByIndentifier(indentifier);
		if (m == null) {
			return 0;
		}

		return m.getTotalSize();
	}

	public String getSaveFilePath(String pkgName) {
		String indentifier = "" + pkgName.hashCode();
		String savePath = Config.DOWNLOAD_DIR + File.separator + indentifier + ".apk";
		return savePath;
	}
	
	public long getTmpFilePath(String pkgName) {
		String indentifier = "" + pkgName.hashCode();
		String path = Config.DOWNLOAD_DIR + File.separator + indentifier + ".apk" + ".dl";
		
		File f = new File(path);
		if(f.exists()){
			return f.length();
		}
		
		return 0;
	}
	
	public List<FileDownloadModel> getAllDownloadDatas(){
		return FileDownloadDatabase.instance(this).getAllDownloadModel();
	}

	@Override
	public void onError(String indentifier, int msg) {
		FileDownloadModel m = FileDownloadDatabase.instance(this)
				.getFileDonwloadModeByIndentifier(indentifier);
		if (m != null
				&& m.getCancelStatus() != FileDownloadModel.CANCEL_BY_ERROR) {
			m.setCancelStatus(FileDownloadModel.CANCEL_BY_ERROR);
			FileDownloadDatabase.instance(this).update(m);
		}
		mDownloadingTasks.remove(indentifier);

		int n = mCallbacks.beginBroadcast();
		try {
			for (int i = 0; i < n; i++) {
				mCallbacks.getBroadcastItem(i).onError(indentifier, msg);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		mCallbacks.finishBroadcast();
		SendBroadCast(indentifier, DOWNLOAD_ERROR);
	}

	@Override
	public void OnGetContentLength(String indentifier, long size) {
		FileDownloadModel m = FileDownloadDatabase.instance(this)
				.getFileDonwloadModeByIndentifier(indentifier);
		if (m != null && m.getTotalSize() != size) {
			m.setTotalSize(size);
			FileDownloadDatabase.instance(this).update(m);
		}

		int n = mCallbacks.beginBroadcast();
		try {
			for (int i = 0; i < n; i++) {
				mCallbacks.getBroadcastItem(i).OnGetContentLength(indentifier,
						size);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		mCallbacks.finishBroadcast();
		SendBroadCast(indentifier, DOWNLOAD_CONTENTLENGTH, size);
	}

	@Override
	public void onDownloadFinish(String indentifier) {
		mDownloadingTasks.remove(indentifier);

		int n = mCallbacks.beginBroadcast();
		try {
			for (int i = 0; i < n; i++) {
				mCallbacks.getBroadcastItem(i).onDownloadFinish(indentifier);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		mCallbacks.finishBroadcast();
		SendBroadCast(indentifier, DOWNLOAD_FINISH);
	
		// 安装Apk
		String path = Config.DOWNLOAD_DIR + File.separator + indentifier + ".apk";
		Uri uri = Uri.fromFile(new File(path));
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setDataAndType(uri, "application/vnd.android.package-archive");
		startActivity(intent);
	}

	@Override
	public void OnUserCanceled(String indentifier) {
		FileDownloadModel m = FileDownloadDatabase.instance(this)
				.getFileDonwloadModeByIndentifier(indentifier);
		if (m != null
				&& m.getCancelStatus() != FileDownloadModel.CANCEL_BY_USER) {
			m.setCancelStatus(FileDownloadModel.CANCEL_BY_USER);
			FileDownloadDatabase.instance(this).update(m);
		}
		mDownloadingTasks.remove(indentifier);

		int n = mCallbacks.beginBroadcast();
		try {
			for (int i = 0; i < n; i++) {
				mCallbacks.getBroadcastItem(i).OnUserCanceled(indentifier);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		mCallbacks.finishBroadcast();
		SendBroadCast(indentifier, DOWNLOAD_USERCANCEL);
	}

	@Override
	public void OnBufferUpdate(String indentifier, long downloadedSize) {

		int n = mCallbacks.beginBroadcast();
		try {
			for (int i = 0; i < n; i++) {
				mCallbacks.getBroadcastItem(i).OnBufferUpdate(indentifier,
						downloadedSize);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		mCallbacks.finishBroadcast();
		SendBroadCast(indentifier, DOWNLOAD_UPDATEBUFFER, downloadedSize);
	}

	public class FileDownloadServiceBinder extends Binder {
		public FileDownloadService getMyService() {
			return FileDownloadService.this;
		}
	}
	
	private void SendBroadCast(String indentifier, int eventCode)
	{
		Intent i = new Intent(ACTION_FILEDOWNLOAD_NOTIFICATION);
		i.putExtra(DOWNLOAD_FILE_INDENTIFIER, indentifier);
		i.putExtra(DOWNLOAD_EVENT_CODE, eventCode);
		sendBroadcast(i);
	}
	
	private void SendBroadCast(String indentifier, int eventCode, long size)
	{
		Intent i = new Intent(ACTION_FILEDOWNLOAD_NOTIFICATION);
		i.putExtra(DOWNLOAD_FILE_INDENTIFIER, indentifier);
		i.putExtra(DOWNLOAD_EVENT_CODE, eventCode);
		i.putExtra(DOWNLOAD_PARAM_SIZE, size);
		sendBroadcast(i);
	}

}
