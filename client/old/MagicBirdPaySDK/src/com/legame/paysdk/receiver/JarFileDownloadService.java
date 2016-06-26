package com.legame.paysdk.receiver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.legame.paysdk.utils.ResourceUtil;

public class JarFileDownloadService extends Service {
	private static final String TAG = "JarFileDownloadService";
	//标题
	private String titleId = "";
	//下载地址
	private String url = "";
	 
	//文件存储
	private File updateDir = null;
	public static File updateFile = null;
	 
	//通知栏
	private NotificationManager updateNotificationManager = null;
	private Notification updateNotification = null;
	//通知栏跳转Intent
	private PendingIntent updatePendingIntent = null;
	
	//下载状态
	private final static int DOWNLOAD_COMPLETE = 0;
	private final static int DOWNLOAD_FAIL = 1;
	
	private Context mContext;
	private static JarFileDownloadService mInstance;
	
	public JarFileDownloadService(){
		
	}
	private JarFileDownloadService(Context context){
		mContext = context;
	}
	public static JarFileDownloadService instance(Context context){
		if(mInstance == null){
			mInstance = new JarFileDownloadService(context);
		}
		return mInstance;
	}
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
//		服务器通知消息
		super.onCreate();
		cheanUpdateFile();

	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		//获取传值
	    titleId = intent.getStringExtra("titleId");
	    url = intent.getStringExtra("url");
	    //创建文件
	    if(android.os.Environment.MEDIA_MOUNTED.equals(android.os.Environment.getExternalStorageState())){
	        updateDir = new File(Environment.getExternalStorageDirectory(),"/LyPaySDK/download/"+getPackageName());
	        updateFile = new File(updateDir.getPath(),titleId+".jar");
	    }
	 
	    this.updateNotificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
	    this.updateNotification = new Notification();
	 
	    //设置通知栏显示内容
	     
//	    updateNotification.icon = ResourceUtil.getDrawable(mContext, "lgsdk_ic_launcher");
	    updateNotification.tickerText = "下载";
//	    updateNotification.when = System.currentTimeMillis();
//	    updateNotification.defaults = Notification.DEFAULT_ALL;
//	    updateNotification.flags = Notification.FLAG_AUTO_CANCEL;
	    updateNotification.setLatestEventInfo(this,"","0%",updatePendingIntent);
	    //发出通知
	    updateNotificationManager.notify(0,updateNotification);
	 
	    //开启一个新的线程下载，如果使用Service同步下载，会导致ANR问题，Service本身也会阻塞
	    new Thread(new updateRunnable()).start();//这个是下载的重点，是下载的过程
		return super.onStartCommand(intent, flags, startId);
	}

	class updateRunnable implements Runnable {
        Message message = updateHandler.obtainMessage();
        public void run() {
            message.what = DOWNLOAD_COMPLETE;
            try{
                if(!updateDir.exists()){
                    updateDir.mkdirs();
                }
                if(!updateFile.exists()){
                    updateFile.createNewFile();
                }
                long downloadSize = downloadUpdateFile(url,updateFile);
                if(downloadSize>0){
                    //下载成功
                    updateHandler.sendMessage(message);
                }
            }catch(Exception ex){
                ex.printStackTrace();
                message.what = DOWNLOAD_FAIL;
                //下载失败
                updateHandler.sendMessage(message);
            }
        }
    }
	public long downloadUpdateFile(String downloadUrl, File saveFile) throws Exception {
        int downloadCount = 0;
        int currentSize = 0;
        long totalSize = 0;
        int updateTotalSize = 0;
         
        HttpURLConnection httpConnection = null;
        InputStream is = null;
        FileOutputStream fos = null;
         
        try {
            URL url = new URL(downloadUrl);
            httpConnection = (HttpURLConnection)url.openConnection();
            httpConnection.setRequestProperty("User-Agent", "PacificHttpClient");
            if(currentSize > 0) {
                httpConnection.setRequestProperty("RANGE", "bytes=" + currentSize + "-");
            }
            httpConnection.setConnectTimeout(10000);
            httpConnection.setReadTimeout(20000);
            updateTotalSize = httpConnection.getContentLength();
            if (httpConnection.getResponseCode() == 404) {
            	Log.i(TAG,"404出错");
            }
            is = httpConnection.getInputStream();                   
            fos = new FileOutputStream(saveFile, false);
            byte buffer[] = new byte[4096];
            int readsize = 0;
            while((readsize = is.read(buffer)) > 0){
                fos.write(buffer, 0, readsize);
                totalSize += readsize;
                //为了防止频繁的通知导致应用吃紧，百分比增加10才通知一次
                if((downloadCount == 0)||(int) (totalSize*100/updateTotalSize)-10 > downloadCount){ 
                    downloadCount += 10;
                    updateNotification.setLatestEventInfo(JarFileDownloadService.this, "正在下载", (int)totalSize*100/updateTotalSize+"%", updatePendingIntent);
                    updateNotificationManager.notify(0, updateNotification);
                }
            }
        } finally {
            if(httpConnection != null) {
                httpConnection.disconnect();
            }
            if(is != null) {
                is.close();
            }
            if(fos != null) {
                fos.close();
            }
        }
        return totalSize;
    }
	private Handler updateHandler = new  Handler(){
	    @Override
	    public void handleMessage(Message msg) {
	        switch(msg.what){
	            case DOWNLOAD_COMPLETE:
	                //点击安装PendingIntent
	                Uri uri = Uri.fromFile(updateFile);
	                Intent installIntent = new Intent(Intent.ACTION_VIEW);
	                installIntent.setDataAndType(uri, "application/vnd.android.package-archive");
	                updatePendingIntent = PendingIntent.getActivity(JarFileDownloadService.this, 0, installIntent, 0);
	                 
	                updateNotification.defaults = Notification.DEFAULT_SOUND;//铃声提醒 
	                updateNotification.setLatestEventInfo(JarFileDownloadService.this, "", "下载完成,点击安装", updatePendingIntent);
	                updateNotificationManager.notify(0, updateNotification);
	                 
//	                //安装完成广播
//					Intent intentC = new Intent();
//					intentC.setAction(Property.ACTION_FILEDOWN_COMPLETE);
//					intentC.putExtra("filepath", updateFile.getAbsolutePath().toString());
//					sendBroadcast(intentC);
					
	            case DOWNLOAD_FAIL:
	                //下载失败
	                updateNotification.setLatestEventInfo(JarFileDownloadService.this, "", "下载完成,点击安装", updatePendingIntent);
	                updateNotificationManager.notify(0, updateNotification);
	            default:
	        }
	    }
	};
	
	public void cheanUpdateFile(){
		File updateFile = new File("/LeYoDownlaod",titleId+".jar");
		if(updateFile.exists()){
		   //当不需要的时候，清除之前的下载文件，避免浪费用户空间
		   updateFile.delete(); 
		}
	}
}
