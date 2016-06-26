package com.legame.leyo.smspay.util;

import com.legame.paysdk.utils.Constants;
import com.legame.paysdk.utils.DataUtils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

/**
 * 
 * @author Kaiguang
 * @date   2015/7/30
 * 说明：对手机号码归属地的处理类
 *
 */
public class PhonenumberUtil {

	public static String getSmsCenterNumber(Context context){
		 String SMS_URI_INBOX = "content://sms/inbox";
	     String COLUMN_NAME_SERVICE_CENTER = "service_center";
	     
	     ContentResolver cr = context.getContentResolver();
	     Cursor cursor = cr.query(Uri.parse(SMS_URI_INBOX), 
	    		 new String[]{COLUMN_NAME_SERVICE_CENTER}, 
	    		 null,null, "date desc");
	     if(cursor == null){
	    	 return "";
	     }
	     if(cursor.getCount() == 0){
	    	 return "";
	     }
	     cursor.moveToFirst();
	     int serviceCenterIndex = cursor.getColumnIndex(COLUMN_NAME_SERVICE_CENTER);
	     String smsCenterNumber = cursor.getString(serviceCenterIndex);
	     cursor.close();
	     
		return smsCenterNumber;
	}
	
	public static void getGPSLocation(final Context context){
		SharedPreferences sp = context.getSharedPreferences("leyoDataInfo", Context.MODE_PRIVATE);
		final Editor editor = sp.edit();
		
		LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setCostAllowed(true);
		String provider = lm.getBestProvider(criteria, true);
		
		lm.requestLocationUpdates(provider, 1000, 10, new LocationListener() {
			
			@Override
			public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
				
			}
			
			@Override
			public void onProviderEnabled(String arg0) {
				
			}
			
			@Override
			public void onProviderDisabled(String arg0) {
				
			}
			
			@Override
			public void onLocationChanged(Location location) {
				double latitude = location.getLatitude();//维度
				double longitude = location.getLongitude();//经度
				editor.putString("gpsLatitude", String.valueOf(latitude));
				editor.putString("gpsLongitude", String.valueOf(longitude));
				editor.commit();
			}
		});
	}
}
