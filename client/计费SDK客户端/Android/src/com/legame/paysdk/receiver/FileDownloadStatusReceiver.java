package com.legame.paysdk.receiver;

import java.util.ArrayList;
import java.util.List;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.widget.RemoteViews;

import com.legame.paysdk.download.FileDownloadDatabase;
import com.legame.paysdk.download.FileDownloadModel;
import com.legame.paysdk.download.FileDownloadService;
import com.legame.paysdk.utils.AsyncImageLoader;
import com.legame.paysdk.utils.ResourceUtil;

public class FileDownloadStatusReceiver extends BroadcastReceiver {

	class NotificationInfo{
		public Context context;
		public String indentifier;
		public int eventCode;
		public long size;
	}
	
	private List<NotificationInfo> mNotifyList = null;
	
	final Handler handler=new Handler();
	final Runnable runnable=new Runnable(){
	@Override
	public void run() {
		sendNotification();
		}
	};
	
	
	private void sendNotification()
	{
		for(int i = 0; i < mNotifyList.size(); i++){
			NotificationInfo info = mNotifyList.get(i);
			switch (info.eventCode) {
			case FileDownloadService.DOWNLOAD_ERROR:
				showNotificationError(info.context, info.indentifier, "下载失败啦，点击重试", i);
				mNotifyList.remove(info);
				break;
			case FileDownloadService.DOWNLOAD_FINISH:
				showNotificationDownloadFinish(info.context, info.indentifier, "下载完成", i);
				mNotifyList.remove(info);
				break;
			case FileDownloadService.DOWNLOAD_UPDATEBUFFER:
				showNotificationWhileDownloading(info.context, info.indentifier, info.size, i);
				break;
			case FileDownloadService.DOWNLOAD_CONTENTLENGTH:
				break;
			case FileDownloadService.DOWNLOAD_USERCANCEL:
				showNotificationError(info.context,info.indentifier, "用户取消下载，点击继续下载", i);
				mNotifyList.remove(info);
				break;
			default:
				break;
			}	
		}
		if (mNotifyList.size() == 0) {
			mNotifyList = null;
		}else {
			handler.postDelayed(runnable, 1000);
		}
	}
	
	
	private void updateNotifyList(NotificationInfo info)
	{
		if( mNotifyList== null){
			mNotifyList = new ArrayList<NotificationInfo>();
			mNotifyList.add(info);
			sendNotification();
		}else {
			for (NotificationInfo tInfo : mNotifyList) {
				if(tInfo.indentifier.equals(info.indentifier)){
					tInfo.context = info.context;
					tInfo.eventCode = info.eventCode;
					tInfo.size = info.size;
					return;
				}
			}
			mNotifyList.add(info);
		}
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		if(intent != null && intent.getAction().equals(FileDownloadService.ACTION_FILEDOWNLOAD_NOTIFICATION)){
			NotificationInfo info = new NotificationInfo();
			info.context = context;
			info.indentifier = intent.getStringExtra(FileDownloadService.DOWNLOAD_FILE_INDENTIFIER);
			info.eventCode = intent.getIntExtra(FileDownloadService.DOWNLOAD_EVENT_CODE, 0);
			info.size = intent.getLongExtra(FileDownloadService.DOWNLOAD_PARAM_SIZE, 0);
			updateNotifyList(info);
		}	
	}
	
	private void showNotificationError(Context context, String indentifier, String msg, long when){
		FileDownloadModel f = null;
		f = FileDownloadDatabase.instance(context).getFileDonwloadModeByIndentifier(indentifier);
		if(f != null)
		{
			Intent intent = new Intent(context, FileDownloadService.class)
			.setAction(FileDownloadService.START_SERVICE_CMD)
			.putExtra(FileDownloadService.EXTRA_DL_URL, f.getDownloadUrl())
			.putExtra(FileDownloadService.EXTRA_GAME_NAME,f.getGameName())
			.putExtra(FileDownloadService.EXTRA_PIC_URL, f.getPicUrl())
			.putExtra(FileDownloadService.EXTRA_PKG_NAME, f.getPackageName());
			//PendingIntent pi = PendingIntent.getService(context, f.getPackageName().hashCode(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
			PendingIntent pi = PendingIntent.getService(context,f.getPackageName().hashCode(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
			 
			RemoteViews rv = new RemoteViews(context.getPackageName(), ResourceUtil.getLayout(context, "lgsdk_notification_layout"));
			rv.setTextViewText(ResourceUtil.getId(context, "lgsdk_notification_title"), f.getGameName());
			rv.setTextViewText(ResourceUtil.getId(context, "lgsdk_notification_content"), msg);
			
			Bitmap logo = AsyncImageLoader.getInstance(context).getLocalImage(f.getPicUrl());
			if(logo != null){
				rv.setImageViewBitmap(ResourceUtil.getId(context, "lgsdk_notification_icon"), logo);
			}else {
				rv.setImageViewResource(ResourceUtil.getId(context, "lgsdk_notification_icon"), ResourceUtil.getDrawable(context, "lgsdk_ic_launcher"));
			}
			NotificationManager mNotiManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
			Notification mNotification = new Notification();
			mNotification.contentView = rv;
			mNotification.contentIntent = pi;
			mNotification.icon = ResourceUtil.getDrawable(context, "lgsdk_ic_launcher");
			mNotification.flags = Notification.FLAG_AUTO_CANCEL;
			mNotification.when = when;
			
			mNotiManager.notify(Integer.valueOf(indentifier), mNotification);
		}
	}
	
	
	
	public void showNotificationWhileDownloading(Context context, String indentifier, long downloadedSize, long when){
		FileDownloadModel f = null;
		f = FileDownloadDatabase.instance(context).getFileDonwloadModeByIndentifier(indentifier);
//		if(f != null)
//		{
//			Intent intent = new Intent(context, LGGameDetailActivity.class);
//			Bundle b = new Bundle();
//			b.putSerializable(LGGameDetailActivity.KEY_PACKAGE_NAME, f.getPackageName());
//			b.putString(LGGameDetailActivity.KEY_FROM_STR, "notification");
//			intent.putExtras(b);
//			intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
//			PendingIntent pi = PendingIntent.getActivity(context,f.getPackageName().hashCode(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//			RemoteViews rv = new RemoteViews(context.getPackageName(), ResourceUtil.getLayout(context, "lgsdk_download_notification_layout"));
//			rv.setTextViewText(ResourceUtil.getId(context, "lgsdk_download_notification_title"), f.getGameName());
//			Bitmap logo = AsyncImageLoader.getInstance(context).getLocalImage(f.getPicUrl());
//			if(logo != null){
//				rv.setImageViewBitmap(ResourceUtil.getId(context, "lgsdk_download_notification_icon"), logo);
//			}else {
//				rv.setImageViewResource(ResourceUtil.getId(context, "lgsdk_download_notification_icon"), ResourceUtil.getDrawable(context, "lgsdk_ic_launcher"));
//			}
//			
//			rv.setTextViewText(ResourceUtil.getId(context, "lgsdk_download_notification_content"), "下载中" + (int)(downloadedSize * 100 / f.getTotalSize()) + "%");
//			rv.setProgressBar(ResourceUtil.getId(context, "lgsdk_download_notification_progressbar"), (int)f.getTotalSize(), (int)downloadedSize, false);
//			
//			NotificationManager mNotiManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//			Notification mNotification = new Notification();
//			mNotification.contentView = rv;
//			mNotification.contentIntent = pi; 
//			mNotification.icon = ResourceUtil.getDrawable(context, "lgsdk_ic_launcher");
//			mNotification.flags = Notification.FLAG_NO_CLEAR;
//			mNotification.when = when;
//			mNotiManager.notify(Integer.valueOf(indentifier), mNotification);
//		}
	}

	private void showNotificationDownloadFinish(Context context, String indentifier, String msg, int when){
		FileDownloadModel f = null;
		f = FileDownloadDatabase.instance(context).getFileDonwloadModeByIndentifier(indentifier);
		if(f != null)
		{
			RemoteViews rv = new RemoteViews(context.getPackageName(), ResourceUtil.getLayout(context, "lgsdk_notification_layout"));
			rv.setTextViewText(ResourceUtil.getId(context, "lgsdk_notification_title"), f.getGameName());
			rv.setTextViewText(ResourceUtil.getId(context, "lgsdk_notification_content"), msg);
			
			Bitmap logo = AsyncImageLoader.getInstance(context).getLocalImage(f.getPicUrl());
			if(logo != null){
				rv.setImageViewBitmap(ResourceUtil.getId(context, "lgsdk_notification_icon"), logo);
			}else {
				rv.setImageViewResource(ResourceUtil.getId(context, "lgsdk_notification_icon"), ResourceUtil.getDrawable(context, "lgsdk_ic_launcher"));
			}
			NotificationManager mNotiManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
			Notification mNotification = new Notification();
			mNotification.contentView = rv;
			mNotification.contentIntent = PendingIntent.getService(context, 0, new Intent(), PendingIntent.FLAG_CANCEL_CURRENT);
			mNotification.icon = ResourceUtil.getDrawable(context, "lgsdk_ic_launcher");
			mNotification.flags = Notification.FLAG_AUTO_CANCEL;
			mNotification.when = when;
			mNotiManager.notify(Integer.valueOf(indentifier), mNotification);
		}
	}
}
