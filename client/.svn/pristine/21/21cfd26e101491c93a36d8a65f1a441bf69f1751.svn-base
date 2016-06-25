package com.legame.paysdk.service;

import java.io.File;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.legame.paysdk.GlobalVal;
import com.legame.paysdk.service.DownloadTsk.onDownloadListener;
import com.legame.paysdk.utils.BuildConfig;
import com.legame.paysdk.utils.Config;
import com.legame.paysdk.utils.LogUtil;
import com.legame.paysdk.utils.ResourceUtil;

public class DownloadService extends Service implements onDownloadListener {
	private static final String TAG = "DownloadService";

	public static final String ACTION_START = "com.legame.paysdk.intent.ACTIION_START";
	private static final String ACTION_STOP = "com.legame.paysdk.intent.ACTION_STOP";
	private static final String ACTION_INSTALL = "com.legame.paysdk.intent.ACTION_INSTALL";

	// broadcast
	public static final String ACTION_REPORT_DOWNLOAD_EVENT = "com.legame.paysdk.intent.ACTION_REPORT_DOWNLOAD_EVENT";
	public static final String EXTRA_EVENT_CODE = "com.legame.paysdk.intent.extra_EVENT_CODE";
	public static final String EXTRA_EVENT_FILESIZE = "com.legame.paysdk.intent.extra_EVENT_FILESIZE";

	// download url
	public static final String EXTRA_DL_URL = "com.legame.paysdk.extra.DL_URL";
	// local save location
	public static final String EXTRA_SAVE_FILE_NAME = "com.legame.paysdk.extra_SAVE_FILE_NAME";
	public static final String EXTRA_SHOW_NOTIFICATION = "com.legame.paysdk.extra_SHOW_NOTIFICATION";

	private boolean mDownloading;
	private DownloadTsk mDownloadTsk;
	private int mMaxStartId;
	
	private NotificationManager mNotiManager;
	private Notification mNotification;
	private boolean mShowNotification;
	private static final int NOTIFICATION_ID = 0;

	
	public static void start(Context context, String downloadUrl,
			String saveFileName) {
		start(context, downloadUrl, saveFileName, false);
	}
	
	public static void start(Context context, String downloadUrl,
			String saveFileName, boolean showNotification) {
		Intent i = new Intent(context, DownloadService.class)
				.setAction(ACTION_START).putExtra(EXTRA_DL_URL, downloadUrl)
				.putExtra(EXTRA_SAVE_FILE_NAME, saveFileName)
				.putExtra(EXTRA_SHOW_NOTIFICATION, showNotification);
		context.startService(i);
	}

	public static void stop(Context context) {
		Intent i = new Intent(context, DownloadService.class)
				.setAction(ACTION_STOP);
		context.startService(i);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		initNotification();
		mDownloading = false;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		mMaxStartId = startId;
		if (intent == null) {
			return super.onStartCommand(intent, flags, startId);
		}
		String action = intent.getAction();
		if (action == null) {
			return super.onStartCommand(intent, flags, startId);
		}
		if (TextUtils.equals(action, ACTION_START)) {// fire start
			if (!mDownloading) {
				mDownloading = !mDownloading;
				fireDownload(intent);
			} else {
				if (BuildConfig.DEBUG) {
					Log.d(TAG, "download ongoing ,ignore this request");
				}
			}
		} else if (TextUtils.equals(action, ACTION_STOP)) {// fire stop
			stopDownload(startId);
		} else if (TextUtils.equals(action, ACTION_INSTALL)) {// fire stop
			Uri uri = Uri.fromFile(new File(Config.DOWNLOAD_DIR + "/" + intent.getStringExtra(EXTRA_SAVE_FILE_NAME)));
			Intent insIntent = new Intent(Intent.ACTION_VIEW);
			insIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			insIntent.setDataAndType(uri, "application/vnd.android.package-archive");
			startActivity(insIntent);
			mNotiManager.cancel(NOTIFICATION_ID);
		}

		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mDownloading = false;// reset
	}

	private void fireDownload(Intent intent) {
		String downloadUrl = intent.getStringExtra(EXTRA_DL_URL);
		String saveFileFullPath = intent.getStringExtra(EXTRA_SAVE_FILE_NAME);
		mDownloadTsk = new DownloadTsk(this, this,
				downloadUrl, saveFileFullPath);
		mDownloadTsk.execute();
		
		mShowNotification = intent.getBooleanExtra(EXTRA_SHOW_NOTIFICATION, false);
//		mShowNotification = true;
		if (mShowNotification) {
			showNotificationStart();
		}else {
			mNotiManager.cancel(NOTIFICATION_ID);
		}
	}

	private void stopDownload(int startId) {
		if (mDownloading && mDownloadTsk != null) {
			mDownloadTsk.pause();
			mDownloading = false;
		}
	}

	/**
	 * =========================================================================
	 * start Download callback
	 * =========================================================================
	 */
	long mContentLen = -1;
	long mCurrentTime;
	
	@Override
	public void onDownloadResult(Long... tskResult) {
		// broadcast result to receiver
		int tskCode = (int) ((long) tskResult[0]);
		if (BuildConfig.DEBUG) {
			Log.d(TAG, "onDownloadResult,tskCode : " + tskCode);
		}
		switch (tskCode) {
		case DownloadTsk.TSK_CODE_START:
			Log.i(TAG,"TSK_CODE_START");
			sendBroadcast(tskCode, tskResult[1]);
			mContentLen = tskResult[1];
			break;
		case DownloadTsk.TSK_CODE_SUCCESS:
			Log.i(TAG,"TSK_CODE_SUCCESS"+mShowNotification);
			sendBroadcast(tskCode, -1);
			if (mShowNotification) {
				
				Toast.makeText(this, "下载完成，请点击通知栏安装", Toast.LENGTH_SHORT).show();
				showNotificationSuccess();
			}
			stopSelf(mMaxStartId);

			break;
		case DownloadTsk.TSK_CODE_PAUSE:
			Log.i(TAG,"TSK_CODE_PAUSE");
			sendBroadcast(tskCode, -1);
			stopSelf(mMaxStartId);
			break;
			
		case DownloadTsk.TSK_CODE_PROGRESS:
			Log.i(TAG,"TSK_CODE_PROGRESS");
			long len = tskResult[1];
			long now = System.currentTimeMillis();
			
			if (mShowNotification && now - mCurrentTime > 100) {
				showNotificationWhileDownloading(mContentLen, len);
				mCurrentTime = System.currentTimeMillis();
			}
			
			break;
		// fail
		case DownloadTsk.TSK_CODE_FAIL:
		case DownloadTsk.TSK_CODE_LEN_ERROR:
		case DownloadTsk.TSK_CODE_NET_ERROR:
		case DownloadTsk.TSK_CODE_PARAM_ERROR:
		case DownloadTsk.TSK_CODE_TIMEOUT_ERROR:
			sendBroadcast(DownloadTsk.TSK_CODE_FAIL, -1);
			stopSelf(mMaxStartId);
			if (mShowNotification) {
				showNotificationError();
			}
			break;
		}
	}
	
	
	private void initNotification(){
		mNotiManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		
		mRemoteView = new RemoteViews(this.getPackageName(), ResourceUtil.getLayout(this, "lgsdk_download_notification_layout"));
		mRemoteView.setTextViewText(ResourceUtil.getId(this, "lgsdk_download_notification_title"), GlobalVal.getAppName(this));
		mRemoteView.setImageViewResource(ResourceUtil.getId(this, "lgsdk_download_notification_icon"), ResourceUtil.getDrawable(this, "lgsdk_ic_launcher"));
		mNotification = new Notification();
		mNotification.contentView = mRemoteView;
		mNotification.icon = ResourceUtil.getDrawable(this, "lgsdk_ic_launcher");
		mNotification.flags = Notification.FLAG_NO_CLEAR;
//		mNotification = new NotificationCompat.Builder(this)
//		.setContent(rv)
//		.setSmallIcon(ResourceUtil.getDrawable(this, "ic_launcher"))
//		.setAutoCancel(false)
//		.setOngoing(true)
//		.setDefaults(Notification.DEFAULT_SOUND)
//		.build();
	}

	private RemoteViews mRemoteView;
	
	private void showNotificationStart(){
		RemoteViews rv = new RemoteViews(getPackageName(), ResourceUtil.getLayout(this, "lgsdk_notification_layout"));
		rv.setTextViewText(ResourceUtil.getId(this, "lgsdk_notification_title"), GlobalVal.getAppName(this));
		rv.setTextViewText(ResourceUtil.getId(this, "lgsdk_notification_content"), "升级即将开始");
		rv.setImageViewResource(ResourceUtil.getId(this, "lgsdk_notification_icon"), ResourceUtil.getDrawable(this, "lgsdk_ic_launcher"));
		mNotification.contentView = rv;
		mNotification.contentIntent = PendingIntent.getService(this, 0, new Intent(), PendingIntent.FLAG_CANCEL_CURRENT);
		mNotiManager.notify(NOTIFICATION_ID, mNotification);
	}

	private void showNotificationWhileDownloading(long max, long progress){
//		RemoteViews rv = mNotification.contentView;
		mRemoteView.setTextViewText(ResourceUtil.getId(this, "lgsdk_download_notification_content"), "升级下载中" + (int)(progress * 100 / max) + "%");
		mRemoteView.setProgressBar(ResourceUtil.getId(this, "lgsdk_download_notification_progressbar"), (int)max, (int)progress, false);
		mNotification.contentView = mRemoteView;
		mNotification.contentIntent = PendingIntent.getService(this, 0, new Intent(), PendingIntent.FLAG_CANCEL_CURRENT);
		mNotiManager.notify(NOTIFICATION_ID, mNotification);
	}

	private void showNotificationError(){

		Intent intent = new Intent(this, DownloadService.class)
		.setAction(DownloadService.ACTION_START).putExtra(DownloadService.EXTRA_DL_URL, mDownloadTsk.mDownloadUrl)
		.putExtra(DownloadService.EXTRA_SAVE_FILE_NAME, mDownloadTsk.mSaveFileName)
		.putExtra(EXTRA_SHOW_NOTIFICATION, mShowNotification);

		PendingIntent pi = PendingIntent.getService(this, 0, intent, 0);
		
		RemoteViews rv = new RemoteViews(getPackageName(), ResourceUtil.getLayout(this, "lgsdk_notification_layout"));
		rv.setTextViewText(ResourceUtil.getId(this, "lgsdk_notification_title"), GlobalVal.getAppName(this));
		rv.setTextViewText(ResourceUtil.getId(this, "lgsdk_notification_content"), "升级出错");
		rv.setImageViewResource(ResourceUtil.getId(this, "lgsdk_notification_icon"), ResourceUtil.getDrawable(this, "lgsdk_ic_launcher"));
		mNotification.contentView = rv;
		mNotification.contentIntent = pi;
		mNotiManager.notify(NOTIFICATION_ID, mNotification);
	}

	private void showNotificationSuccess(){
		Intent intent = new Intent(this, DownloadService.class)
		.setAction(DownloadService.ACTION_INSTALL)
		.putExtra(EXTRA_SAVE_FILE_NAME, mDownloadTsk.mSaveFileName);
		PendingIntent pi = PendingIntent.getService(this, 0, intent, 0);
		
		RemoteViews rv = new RemoteViews(getPackageName(), ResourceUtil.getLayout(this, "lgsdk_notification_layout"));
		rv.setTextViewText(ResourceUtil.getId(this, "lgsdk_notification_title"), GlobalVal.getAppName(this));
		rv.setTextViewText(ResourceUtil.getId(this, "lgsdk_notification_content"), "点击安装");
		rv.setImageViewResource(ResourceUtil.getId(this, "lgsdk_notification_icon"), ResourceUtil.getDrawable(this, "lgsdk_ic_launcher"));
		mNotification.contentView = rv;
		mNotification.contentIntent = pi;
		mNotification.flags = mNotification.flags & Notification.FLAG_AUTO_CANCEL;
		mNotification.flags = mNotification.flags & ~Notification.FLAG_NO_CLEAR;
		
		mNotiManager.notify(NOTIFICATION_ID, mNotification);
	}
	

	/**
	 * =========================================================================
	 * end Download callback
	 * =========================================================================
	 */

	private void sendBroadcast(int code, long fileSize) {
		LogUtil.d(TAG, "code:"  + code + " fileSize:" + fileSize);
		Intent i = new Intent(ACTION_REPORT_DOWNLOAD_EVENT);
		i.putExtra(EXTRA_EVENT_CODE, code);
		i.putExtra(EXTRA_EVENT_FILESIZE, fileSize);
		sendBroadcast(i);
	}
}
