package com.legame.paysdk;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.legame.paysdk.network.utils.NetTools;
import com.legame.paysdk.utils.Config;

import dalvik.system.DexClassLoader;

public class GlobalVal {
	public static String TAG = "GlobalVal";

	public static boolean sDebug = true;
	public static boolean sInitFinished = false;
	public static Orientation sOritation;
	
	public static boolean sIsLogin = false;
	public static boolean sIsAnonymous = false;
	public static boolean sIsFastPayment = false;
	public static boolean sIsMonthly = false;
	public static boolean sIsEgameInit = false;
	public static String sDeviceId;

	public static Handler sHandler = new Handler(Looper.getMainLooper());
	private static final String APP_ID = "LEGAME_APPID";
	public static final int SIM1_TYPE = 0;
	public static final int SIM2_TYPE = 1;
	
    public static Orientation getOrientation(Context context){
    	if(NetTools.getOrientation(context) == 0){
    		return Orientation.ORIENTATION_PORTRAIT;
    	}else {
			return Orientation.ORIENTATION_LANDSCAPE;
		}
    }

	public static String getAppName(Context context) {
		try {
			ApplicationInfo ai = context.getPackageManager().getApplicationInfo(
					context.getPackageName(), 0);
			return ai.loadLabel(context.getPackageManager()).toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	public static String getAppId(Context context) {
		try {
			ApplicationInfo ai = context.getPackageManager().getApplicationInfo(
					context.getPackageName(), PackageManager.GET_META_DATA);
			Bundle bundle = ai.metaData;
			return bundle.getString(APP_ID);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static String getAppNameByPackage(Context context , String pkgName) {
		try {
			ApplicationInfo ai = context.getPackageManager().getApplicationInfo(
					pkgName, 0);
			return ai.loadLabel(context.getPackageManager()).toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static int getVersioncode(Context context){
		PackageInfo pi;
		try {
			pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			return pi.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public static String getVersionname(Context context){
		PackageInfo pi;
		try {
			pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			return pi.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static int getMetadataInt(Context context,String key){
		ApplicationInfo appInfo;
		int value;
		try {
			appInfo = context.getPackageManager()
					.getApplicationInfo(context.getPackageName(),
							PackageManager.GET_META_DATA);
			value = appInfo.metaData.getInt(key);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			value = -1;
		}
		return value;
	}
	
	public static String getMetadateString(Context context,String key){
		ApplicationInfo appInfo;
		String value;
		try {
			appInfo = context.getPackageManager()
					.getApplicationInfo(context.getPackageName(),
							PackageManager.GET_META_DATA);
			value = appInfo.metaData.getString(key);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			value = null;
		}
		return value;
	}
	
	
	public static boolean getMetadataBoolean(Context context,String key){
		ApplicationInfo appInfo;
		boolean value;
		try {
			appInfo = context.getPackageManager()
					.getApplicationInfo(context.getPackageName(),
							PackageManager.GET_META_DATA);
			value = appInfo.metaData.getBoolean(key);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			value = false;
		}
		return value;
	}
	
	public static String getIMSI(Context context){

		String sim1IMSI = getPhoneIMSI(context);
		String sim2IMSI = getSim2IMSI(context);
		if(TextUtils.isEmpty(sim1IMSI) && TextUtils.isEmpty(sim2IMSI)){
			return null;
		}else if(!TextUtils.isEmpty(sim1IMSI) && TextUtils.isEmpty(sim2IMSI)){
			return sim1IMSI;
		}else if(!TextUtils.isEmpty(sim1IMSI) && !TextUtils.isEmpty(sim2IMSI)){
			return sim1IMSI + "|" + sim2IMSI;
		}else if(TextUtils.isEmpty(sim1IMSI) && !TextUtils.isEmpty(sim2IMSI)){
			return sim2IMSI;
		}

		return null;
	}
	
	public static boolean isDoubleSimCard(Context context){
		String sim1IMSI = getPhoneIMSI(context);
		String sim2IMSI = getSim2IMSI(context);
		if(!TextUtils.isEmpty(sim1IMSI) && TextUtils.isEmpty(sim2IMSI)){
			return true;
		}
		return false;
	}
	
	public static String getPhoneIMSI(Context context){
		 try {
			TelephonyManager mTelephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			 return mTelephonyManager.getSubscriberId();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getSim2IMSI(Context context) {
		String imsi = "";
		try {
			TelephonyManager tm = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			// imsi = tm.getSubscriberId();
			// if (imsi == null || "".equals(imsi))
			// imsi = tm.getSimOperator();
			Class<?>[] resources = new Class<?>[] { int.class };
			Integer resourcesId = Integer.valueOf(1);
			if (imsi == null || "".equals(imsi)) {
				try { // 利用反射获取 MTK手机
					Method addMethod = tm.getClass().getDeclaredMethod(
							"getSubscriberIdGemini", resources);
					addMethod.setAccessible(true);
					imsi = (String) addMethod.invoke(tm, resourcesId);
				} catch (Exception e) {
					imsi = null;
				}
			}
			if (imsi == null || "".equals(imsi)) {
				try { // 利用反射获取 展讯手机
					Class<?> c = Class
							.forName("com.android.internal.telephony.PhoneFactory");
					Method m = c.getMethod("getServiceName", String.class,
							int.class);
					String spreadTmService = (String) m.invoke(c,
							Context.TELEPHONY_SERVICE, 1);
					TelephonyManager tm1 = (TelephonyManager) context
							.getSystemService(spreadTmService);
					imsi = tm1.getSubscriberId();
				} catch (Exception e) {
					imsi = null;
				}
			}
			if (imsi == null || "".equals(imsi)) {
				try { // 利用反射获取 高通手机
					Method addMethod2 = tm.getClass().getDeclaredMethod(
							"getSubscriberId", resources);
					addMethod2.setAccessible(true);
					imsi = (String) addMethod2.invoke(tm, resourcesId);
				} catch (Exception e) {
					imsi = "";
				}
			}
			if (imsi == null || "".equals(imsi)) {
				try { // 利用反射获取 三星手机
					Class<?> c = Class
							.forName("android.telephony.TelephonyManager");
					Object defaultObject = c.getDeclaredMethod("getDefault", null).invoke(null, null);
					Method m = c.getMethod("getSubscriberIdDs", Integer.TYPE);
					Object o = m.invoke(defaultObject, resourcesId);
					if(o instanceof String){
						imsi = (String)o;
					}
				} catch (Exception e) {
					imsi = null;
				}
			}
			if (imsi == null || "".equals(imsi)) {
				imsi = "";
			}
			return imsi;
		} catch (Exception e) {
			return "";
		}
	}
	
	public static int selectSimSendSMS(Context context,String content){
		if(content.equals(getPhoneIMSI(context))){
			return SIM1_TYPE;
		}else if(content.equals(getSim2IMSI(context))){
			return SIM2_TYPE;
		}
		return -1;
	}

	public static int getSimArea(Context context){
	    int i = -1;
	    TelephonyManager localTelephonyManager;
	    if ((localTelephonyManager = (TelephonyManager)context.getSystemService("phone")) != null)
	    {
	      String str = null;
	      try
	      {
	        str = localTelephonyManager.getSubscriberId();
	      }
	      catch (Exception localException)
	      {
	        localException.printStackTrace();
	      }
	      if (str != null)
	        if ((str.startsWith("46000")) || (str.startsWith("46002")) || (str.startsWith("46007")) || (str.equals("898600")))
	          i = 0;//移动
	        else if ((str.startsWith("46001")) || (str.startsWith("46006")) || (str.startsWith("46010")))
	          i = 1;//联通
	        else if ((str.startsWith("46003")) || (str.startsWith("46005")) || (str.startsWith("46011")))
	          i = 3;//电信
	    }
	    return i;
	 }
	
	/**
	 * 读取SD卡的jar里的类
	 * @param actvity
	 * @param filename
	 * @return
	 */
	public static DexClassLoader classRef(Context actvity, String filename){
		 
		 final File optimizedDexOutputPath = actvity.getDir("dex", Context.MODE_PRIVATE);
         File dbDir = new File(Environment.getExternalStorageDirectory().toString()
					+ "/MbsPaySDK/download");
         if (!dbDir.exists()) {
				dbDir.mkdirs();
			}
         File dirfile = new File(Environment.getExternalStorageDirectory().toString()
					+ "/MbsPaySDK/download/"+actvity.getPackageName(),filename);
     	Log.i(TAG,"jar本地路径："+dirfile.getAbsolutePath());
     	Log.i(TAG,"jar2本地路径："+optimizedDexOutputPath.getAbsolutePath());
     	DexClassLoader cl = new DexClassLoader(dirfile.getAbsolutePath(),
     			optimizedDexOutputPath.getAbsolutePath(), null, actvity.getClassLoader());
         return cl;
	}
	
	/**
	 * 把assets目录下的文件拷贝到指定目录下
	 * @param context
	 * @param filename assets目录下的文件名，包括后缀
	 * @param destination 要拷贝到的目标文件名
	 */
	public static void CopyAssertJarToFile(Context context, String filename,
            String destination) {
        try {
 
            File file = new File(Environment.getExternalStorageDirectory()
                    .toString() + "/MbsPaySDK/download/"+context.getPackageName()
                    +File.separator + destination);
            if (file.exists()) {
                return;
            }
 
            InputStream inputStream = context.getAssets().open(filename);
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte buffer[] = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buffer)) != 0) {
                fileOutputStream.write(buffer, 0, len);
            }
            fileOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	
	/**
	* 写文件到sd卡上
	* 
	* @param context
	*/
	public static void writeFileToSD(String context) {
		//使用RandomAccessFile 写文件 还是蛮好用的..推荐给大家使用...
		String sdStatus = Environment.getExternalStorageState();
		if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
			Log.d("TestFile", "SD card is not avaiable/writeable right now.");
			return;
		}
		try {
			String pathName = "/sdcard/";
			String fileName = "log.txt";
			File path = new File(pathName);
			File file = new File(pathName + fileName);
			if (!path.exists()) {
				Log.d("TestFile", "Create the path:" + pathName);
				path.mkdir();
			}
		if (!file.exists()) {
			Log.d("TestFile", "Create the file:" + fileName);
			file.createNewFile();
		}
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		raf.seek(file.length());
	    raf.write(context.getBytes());
		raf.close();
		//注释的也是写文件..但是每次写入都会把之前的覆盖..
		/*String pathName = "/sdcard/";
		　 String fileName = "log.txt";
		　 File path = new File(pathName);
		　 File file = new File(pathName + fileName);
		　 if (!path.exists()) {
			　　Log.d("TestFile", "Create the path:" + pathName);
			　　path.mkdir();
		　 }
		　 if (!file.exists()) {
			　　Log.d("TestFile", "Create the file:" + fileName);
			　　file.createNewFile();
		　 }
		　 FileOutputStream stream = new FileOutputStream(file);
		　 String s = context;
		　 byte[] buf = s.getBytes();
		　 stream.write(buf);
		　 stream.close();*/
		} catch (Exception e) {
			Log.e("TestFile", "Error on writeFilToSD.");
		}
	}
	
	public static boolean isMMJarExit(){
		try {
			Class.forName("mm.purchasesdk.Purchase");
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean isSnowfoxExist(){
		try {
			Class.forName("com.fqhx.sdk.hub.FqhxSDKHub");
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean isClassnameExist(String className){
			try {
				Class.forName(className);
				return true;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
	}
	
	/**
	 * 删除download文件里的文件
	 */
	public static void deleteDownloadFile(){
		
		String sdpath = Config.DOWNLOAD_DIR;
	    for (int i = 0; i <= 1; i++){
	        File file = new File(sdpath+"/LeyoPay_sms.jar");
	        if(file.exists()){

		        file.delete();
	        }
	    }
	}
}
