package com.legame.paysdk.utils;

import java.lang.reflect.Method;

import com.legame.paysdk.utils.LogUtil;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.CountDownTimer;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.widget.Toast;
/**
 * 
 * @author codeDing
 *
 */

public class SmsBasePay{
	private String SENT_SMS_ACTION = "SENT_SMS_ACTION";
	private static final int TIME_OUT = 30000;
	private boolean isSendSucess = false;
	private Context mContext;
	public static final int SIM1_TYPE = 0;
	public static final int SIM2_TYPE = 1;
	private MdoPayCallBack mMdoPayCallBack;
	private BroadcastReceiver receiver;
	private boolean isSending = false;
	private String mNumber = "";
	private String mContent = "";
	
	public void pay(Context context, String number ,String content, MdoPayCallBack mdoPayCallBack){
		mContext = context;
		mNumber = number;
		mContent = content;
		mMdoPayCallBack = mdoPayCallBack;
		
		registerBroadcastReceiver();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				sendSms(mNumber,mContent);
			}
		}).start();
	}
	private CountDownTimer timer = new CountDownTimer(TIME_OUT, 1000) {

		@Override
		public void onTick(long arg0) {
			
		}

		@Override
		public void onFinish() {
			if(isSending){
				//发送超时
				Toast.makeText(mContext, "发送超时", Toast.LENGTH_SHORT).show();
				smsTimeOut();
			}
		}
	};
	public void sendSms(String mNumber, String mContent){
		
		if(mNumber != null){
			isSending = true;
//			System.out.println(mNumber+","+mContent);
			Intent intent = new Intent(SENT_SMS_ACTION);
			int phoneType = -1;
			PendingIntent sentIntent = PendingIntent.getBroadcast(mContext, 0, intent, 0);
			if(isDoubleSimCard(mContext)){
			    if (mContent == null || mContent.length() < 1) {
			    	throw new IllegalArgumentException("Invalid message body");
				}
				SmsManager.getDefault().sendTextMessage(mNumber, null, mContent, sentIntent, null);

			}else{

				phoneType = selectSimSendSMS(mContext, getPhoneIMSI(mContext));
				if (phoneType == SIM1_TYPE) {

					sendMultipartTextMessage(mNumber, "", mContent, sentIntent, null, SIM1_TYPE);
				}else if(phoneType == SIM2_TYPE){

					sendMultipartTextMessage(mNumber, "", mContent, sentIntent, null, SIM2_TYPE);
				}
			}
		}
	}
	
	public void sendSms(Context context, String number, String content,
			MdoPayCallBack mdoPayCallBack) {
		mContext = context;
		mNumber = number;
		mContent = content;
		mMdoPayCallBack = mdoPayCallBack;

		registerBroadcastReceiver();
		new Thread(new Runnable() {

			@Override
			public void run() {
				Intent intent = new Intent(SENT_SMS_ACTION);
				PendingIntent sentIntent = PendingIntent.getBroadcast(mContext, 0,
						intent, 0);
				SmsManager.getDefault().sendTextMessage(mNumber, null,
						mContent, sentIntent, null);
			}
		}).start();
	}
	
	public void sendMultipartTextMessage(String number,String paramString2, String content,
			PendingIntent pendingIntent, PendingIntent paramArrayList2,int phoneType) {
		try {
			boolean isMtk = false;
			Class localClass = null;
			Method localMethod = null;
			try {
				localClass = Class.forName("android.telephony.MSimSmsManager");
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (localClass == null) {
				// mtkоƬ
				try {
					localClass = Class.forName("android.telephony.gemini.GeminiSmsManager");
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (localClass != null) {
					isMtk = true;
				}
			}
			if (localClass == null) {
				localClass = Class.forName("android.telephony.SmsManager");
			}

			if (isMtk) {
				Class[] arrayOfClass = new Class[6];
				arrayOfClass[0] = String.class;
				arrayOfClass[1] = String.class;
				arrayOfClass[2] = String.class;
				arrayOfClass[3] = Integer.TYPE;
				arrayOfClass[4] = PendingIntent.class;
				arrayOfClass[5] = PendingIntent.class;

				localMethod = localClass.getDeclaredMethod("sendTextMessageGemini", arrayOfClass);
				Object[] arrayOfObject = new Object[6];
				arrayOfObject[0] = number;
				arrayOfObject[1] = paramString2;
				arrayOfObject[2] = content;
				arrayOfObject[3] = Integer.valueOf(phoneType);
				arrayOfObject[4] = pendingIntent;
				arrayOfObject[5] = paramArrayList2;
				localMethod.invoke(null, arrayOfObject);
			} else {
				Object localObject = localClass.getDeclaredMethod("getDefault",null).invoke(null, null);
				Class[] arrayOfClass = new Class[6];
				arrayOfClass[0] = String.class;
				arrayOfClass[1] = String.class;
				arrayOfClass[2] = String.class;
				arrayOfClass[3] = PendingIntent.class;
				arrayOfClass[4] = PendingIntent.class;
				arrayOfClass[5] = Integer.TYPE;
				localMethod = localClass.getDeclaredMethod("sendTextMessage",arrayOfClass);
				Object[] arrayOfObject = new Object[6];
				arrayOfObject[0] = number;
				arrayOfObject[1] = paramString2;
				arrayOfObject[2] = content;
				arrayOfObject[3] = pendingIntent;
				arrayOfObject[4] = paramArrayList2;
				arrayOfObject[5] = Integer.valueOf(phoneType);
				localMethod.invoke(localObject, arrayOfObject);
			}
			return;
		} catch (Exception localException) {
			SmsManager.getDefault().sendTextMessage(number, null,
					content, pendingIntent, null);
			localException.printStackTrace();
		}
	}

	
	public class MdoPaySMSReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(SENT_SMS_ACTION)) {
				isSending = false;
				timer.cancel();
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					isSendSucess = true;
					mMdoPayCallBack.onMdoPayResult(0);
//					Toast.makeText(mContext, "发送成功", Toast.LENGTH_SHORT).show();
					LogUtil.d("SMS", "发送成功");
					break;
				default:
					mMdoPayCallBack.onMdoPayResult(1);
					break;
				}
			}
		}
	}
	
	
	private void smsTimeOut(){

		if (isSendSucess) {
			mMdoPayCallBack.onMdoPayResult(0);
		} else {
			mMdoPayCallBack.onMdoPayResult(1);
		}
		if(receiver != null){
			mContext.unregisterReceiver(receiver);
		}
	}
	
	private void registerBroadcastReceiver(){
		receiver = new MdoPaySMSReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(SENT_SMS_ACTION);
        mContext.registerReceiver(receiver, filter);
	}
	
	public interface MdoPayCallBack{
		public void onMdoPayResult(int status);
	}

	public void unRegisterReceiver(Context context){
		if(receiver != null){
			context.unregisterReceiver(receiver);
		}
		
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
			Integer resourcesId = new Integer(1);
			if (imsi == null || "".equals(imsi)) {
				try { // ���÷����ȡ MTK�ֻ�
					Method addMethod = tm.getClass().getDeclaredMethod(
							"getSubscriberIdGemini", resources);
					addMethod.setAccessible(true);
					imsi = (String) addMethod.invoke(tm, resourcesId);
				} catch (Exception e) {
					imsi = null;
				}
			}
			if (imsi == null || "".equals(imsi)) {
				try { // ���÷����ȡ չѶ�ֻ�
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
				try { 
					Method addMethod2 = tm.getClass().getDeclaredMethod(
							"getSubscriberId", resources);
					addMethod2.setAccessible(true);
					imsi = (String) addMethod2.invoke(tm, resourcesId);
				} catch (Exception e) {
					imsi = "";
				}
			}
			if (imsi == null || "".equals(imsi)) {
				try { 
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
}
