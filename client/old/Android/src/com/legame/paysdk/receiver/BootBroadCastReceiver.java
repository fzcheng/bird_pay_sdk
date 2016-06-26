package com.legame.paysdk.receiver;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;

/** 
 * 类说明：   
 * @author  huangliang
 * @date    2014-6-16
 * @version 1.0
 */
public class BootBroadCastReceiver extends BroadcastReceiver {
	static final String ACTION_BOOT="android.intent.action.BOOT_COMPLETED"; 

	@Override
	public void onReceive(Context context, Intent intent) {
		if(intent.getAction().equals(ACTION_BOOT)){
			ObserverSMS(context);
		}
	}
	
	public void ObserverSMS(Context context){
		ContentResolver contentResolver = context.getContentResolver();
		Handler handler = new Handler();
		ContentObserver m_SMSObserver = new SMSObserver(handler, context);
		contentResolver.registerContentObserver(Uri.parse("content://sms/inbox"), true, m_SMSObserver);
	}

}
