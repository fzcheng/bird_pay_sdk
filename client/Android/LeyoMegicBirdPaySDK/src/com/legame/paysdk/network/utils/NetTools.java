package com.legame.paysdk.network.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.legame.paysdk.GlobalVal;
import com.legame.paysdk.models.ListUrlInfo;
import com.legame.paysdk.models.UrlInfo;
import com.legame.paysdk.utils.CryptoUtil;
import com.legame.paysdk.utils.LogUtil;

/** 
 * 类说明：   
 * @author  Terry.Lu
 * @date    2013-5-15
 * @version 1.0
 */
public class NetTools {
	/**
	 * 是否联网
	 * 
	 * @param context
	 * @return true为有联网反之没有联网
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager mgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (mgr != null) {
			NetworkInfo[] info = mgr.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		} else {
			return false;
		}
		return false;
	}

	public static final int NET_TYPE_NULL = 0;
	public static final int NET_TYPE_WIFI = 1;
	public static final int NET_TYPE_GPRS = 2;

	public static ListUrlInfo gListUrl = null;
	public static boolean gIsDomainFailure = false;
	public static List<UrlInfo> gListURLinfo = null;
	public static int gTryIndex = 0;
	public static boolean gIsNetFailure = false;
	public static boolean gIsFirstStart = false;
	public static boolean gIsNetConnect = false;

	public static int isWifiAvailable(Context context) {
		ConnectivityManager mgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = mgr.getActiveNetworkInfo();

		if(info == null){
			return NET_TYPE_NULL;
		}

		if (info.getType()== ConnectivityManager.TYPE_WIFI) {
			return NET_TYPE_WIFI;
		} else {
			return NET_TYPE_GPRS;
		}
	}	

	/**
	 * 判断当前网络连接是否为cmwap
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isCmwap(Context activity) {
		ConnectivityManager manager = (ConnectivityManager) activity
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netWrokInfo = manager.getActiveNetworkInfo();

		if (netWrokInfo == null) {
			return false;
		}

		String typeName = netWrokInfo.getTypeName();
		String extraInfo = netWrokInfo.getExtraInfo();

		if ("MOBILE".equalsIgnoreCase(typeName)
				&& ("cmwap".equalsIgnoreCase(extraInfo)
						|| "3gwap".equalsIgnoreCase(extraInfo)
						|| "uniwap".equalsIgnoreCase(extraInfo) 
						|| "ctwap".equalsIgnoreCase(extraInfo))) {
			return true;
		} else {

			return false;
		}
	}

	public static HttpURLConnection getCmwapConnect(String requestUrl)
			throws IOException {
		URL url = new URL(requestUrl);
		HttpURLConnection con;

		LogUtil.i("Tools", "use cmwap...");

		String host = url.getHost();
		int port = url.getPort();
		if (port == -1) {
			requestUrl = requestUrl.replaceAll(host, "10.0.0.172:80");
		} else {
			requestUrl = requestUrl.replaceAll(host + ":" + port,
					"10.0.0.172:80");
		}

		url = new URL(requestUrl);
		con = (HttpURLConnection) url.openConnection();

		String xOnlineHost = null;
		if (port == -1) {
			xOnlineHost = host;
		} else {
			xOnlineHost = host + ":" + port;
		}
		con.setRequestProperty("Host", "10.0.0.172");
		con.setRequestProperty("X-Online-Host", xOnlineHost);
		return con;
	}

	public static void setCommonHttpHeader(HttpURLConnection con) {
		con.setRequestProperty("Accept", "*/*");
		con.setRequestProperty("Accept-Language", "zh-CN, zh");
		con.setRequestProperty("Charset",
				"UTF-8,ISO-8859-1,US-ASCII,ISO-10646-UCS-2;q=0.6");
		con.setRequestProperty(
				"User-Agent",
				"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
		con.setRequestProperty("Connection", "Keep-Alive");
	}

	public static Map<String, String> getHttpResponseHeader(
			HttpURLConnection http) {
		Map<String, String> header = new LinkedHashMap<String, String>();
		for (int i = 0;; i++) {
			String mine = http.getHeaderField(i);
			if (mine == null)
				break;

			String str = http.getHeaderFieldKey(i);
			if (str != null) {
				header.put(str.toLowerCase(Locale.CHINA), mine);
			}
		}
		return header;
	}

	public static String getServerUrl(){
		if(GlobalVal.sDebug) 
		{
			return "http://lg.yeelo.cn/api";
		} else {
			return "http://lg.yeelo.cn/api";
//			return NetLib.getServerUrl(); //正式服务器，后期需要使用lib形式进行封装	
		}
	}

	public static String getSecrectKey(){
//		return "zu4bfqhrpxidybsbh5yflswm2svsdd01";
		return NetLib.getSecrectKey();
	}

	/**
	 * 获取机器的IMEI
	 * 
	 * @return
	 */
	public static String getIMEI(Context context) {
		String IMEI = "";
		try {
			TelephonyManager tm = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			if(tm != null)
				IMEI = tm.getDeviceId();

			if (IMEI != null
					&& (IMEI.length() < 14 //电信cdma2000的设备序列号meid长度是14位 
							|| IMEI.equals("004999010640000")
							|| IMEI.equals("000000000000000") 
							|| IMEI.equals("null"))) {
				return null;
			}

			return IMEI;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getVersionName(Context cx) {
		String packName = cx.getPackageName();
		PackageInfo pinfo = null;
		try {
			pinfo = cx.getPackageManager().getPackageInfo(packName, 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		if (pinfo != null)
		{
			if(TextUtils.isEmpty(pinfo.versionName))
			{
				return "empty";
			}
			return pinfo.versionName;
		}
		else
		{
			return "empty";
		}
	}
	
	public static int getVersionCode(Context cx) {
		String packName = cx.getPackageName();
		PackageInfo pinfo = null;
		try {
			pinfo = cx.getPackageManager().getPackageInfo(packName, 0);
		} catch (NameNotFoundException e) {

			e.printStackTrace();
		}
		if (pinfo != null)
			return pinfo.versionCode;
		else
			return 1;
	}

	public static String getChannelNumber(Context c) {
//		return "T001";
		try {
			AssetManager am = c.getAssets();
			InputStream is = am.open("LGSDK_CHANNEL");
			byte[] buf = new byte[is.available()];
			is.read(buf);
			String cipher = new String(buf, "UTF-8");
			
			return CryptoUtil.decrypt(cipher, CryptoUtil.PRIVATE_KEY);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取Android本机MAC
	 * @return
	 */
	public static String getLocalMacAddress(Context context) {
		//在wifi未开启状态下，仍然可以获取MAC地址，但是IP地址必须在已连接状态下否则为0
		String macAddress = null, ip = null;
		WifiManager wifi = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
//		WifiInfo info = wifi.getConnectionInfo();
		if((Build.MODEL.equals("sdk")) || (Build.MODEL.equals("google_sdk")))
		{
			return "android_emulator_mac_addr";
		}
		
		WifiInfo info = (null == wifi ? null : wifi.getConnectionInfo());
		if (null != info) {
		    macAddress = info.getMacAddress();
		    try {
				ip = getLocalIpAddress();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		System.out.println("mac:" + macAddress + ",ip:" + ip);
		return info.getMacAddress();
	}

	/**
	 * 获取本地IP
	 * @return
	 * @throws Exception
	 */
	public static String getLocalIpAddress() throws Exception{
		
		for(Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();en.hasMoreElements();){
			NetworkInterface intf = en.nextElement();
			for(Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();enumIpAddr.hasMoreElements();){
				InetAddress inetAddress = enumIpAddr.nextElement();
				if(!inetAddress.isLinkLocalAddress() && !inetAddress.isLinkLocalAddress()){
					return inetAddress.getHostAddress().toString();
				}
			}
		}
		
		return null;
	}
	
	public static boolean isUpdatedSid(Context ctx) {
		SharedPreferences sp = ctx.getSharedPreferences("sid_info",
				Context.MODE_PRIVATE);
		boolean updated = sp.getBoolean("updated", false);
		return updated;
	}
	
	public static void setUpdatedSid(Context ctx, boolean updated) {
		SharedPreferences sp = ctx.getSharedPreferences("sid_info",
				Context.MODE_PRIVATE);
		sp.edit().putBoolean("updated", updated).commit();
	}
	
	public static void saveSid(Context ctx, String sid) {
		SharedPreferences sp = ctx.getSharedPreferences("sid_info",
				Context.MODE_PRIVATE);
		sp.edit().putString("sid", sid).commit();
	}
	
	public static String getSid(Context ctx) {
		SharedPreferences sp = ctx.getSharedPreferences("sid_info",
				Context.MODE_PRIVATE);
		String sid = sp.getString("sid", "");
		return sid;
	}
	
	public static void setSidExpired(Context ctx, boolean isExpired) {
		SharedPreferences sp = ctx.getSharedPreferences("sid_info",
				Context.MODE_PRIVATE);
		sp.edit().putBoolean("expired", isExpired).commit();
	}
	
	public static boolean isSidExpired(Context ctx) {
		SharedPreferences sp = ctx.getSharedPreferences("sid_info",
				Context.MODE_PRIVATE);
		boolean expired = sp.getBoolean("expired", true);
		return expired;
	}
	
	public static int getOrientation(Context ctx) {
		SharedPreferences sp = ctx.getSharedPreferences("orientation",
				Context.MODE_PRIVATE);
		int orientation = sp.getInt("orientation", 0);
		return orientation;
	}
	
	public static void setOrientation(Context ctx, int orientation) {
		SharedPreferences sp = ctx.getSharedPreferences("orientation",
				Context.MODE_PRIVATE);
		sp.edit().putInt("orientation", orientation).commit();
	}
}
