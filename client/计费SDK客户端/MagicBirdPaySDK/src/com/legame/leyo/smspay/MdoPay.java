package com.legame.leyo.smspay;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;

import com.legame.leyo.smspay.extend.PayErrorCode;
import com.legame.paysdk.GlobalVal;
import com.legame.paysdk.db.MdoPayBackDb;
import com.legame.paysdk.db.MdoSmsBlockDb;
import com.legame.paysdk.models.Block;
import com.legame.paysdk.models.Command;
import com.legame.paysdk.models.Commands;
import com.legame.paysdk.models.OrderInfo;
import com.legame.paysdk.network.engine.BaseNetEngine;
import com.legame.paysdk.network.task.NetTask;
import com.legame.paysdk.network.task.NetTask.NetTaskListener;
import com.legame.paysdk.network.utils.NetTools;
import com.legame.paysdk.utils.LogUtil;

/**
 * 类说明： 短信支付
 * 
 * @author Shaohui.Yang/Xiaodm
 * @date 2014年6月10日/2015.01.30
 * @version 1.0
 */
public class MdoPay implements NetTaskListener {

	private final String TAG = MdoPay.class.getSimpleName();
	private final String MDO_SMS_SEND_ACTION = "com.leyo.pay.MDO_SMS_SEND_ACTION";
	private String MDO_ACTION_SMS_DELIVERY = "com.leyo.pay.MDO_ACTION_SMS_DELIVERY";
	private final int TIME_OUT = 10000;
	private boolean isSendSucess = false;
	private NetTask mNetTask;
	private Context mContext;
	private OrderInfo mOrderInfo;
	private Commands mCommands;
	private MdoPayCallBack mMdoPayCallBack;
	private HashMap<String, String> mapMdoPayBack;
	private BroadcastReceiver receiver;
	private int time;
	private int count = 0;
	private String states = "";
	private boolean isSending = false;
	private boolean isSendAgain = false;
	private int index = 0;
	private String orignalCode ;

	public void pay(Context context, OrderInfo orderInfo, Commands commands,
			MdoPayCallBack mdoPayCallBack) {
		mContext = context;
		mOrderInfo = orderInfo;
		mCommands = commands;
		mMdoPayCallBack = mdoPayCallBack;
		time = Integer.parseInt(mCommands.getmTime());
		isSendAgain = false;
		count = 0;
		index = 0;
		states = "";
		registerBroadcastReceiver();
		new Thread(new Runnable() {

			@Override
			public void run() {
				 Looper.prepare();
				List<Command> list = mCommands.mCommandList;
				sendSms(list.get(count));
			}
		}).start();
	}

	private CountDownTimer timer = new CountDownTimer(TIME_OUT, 1000) {

		@Override
		public void onTick(long arg0) {

		}

		@Override
		public void onFinish() {
			if (isSending) {
				// 发送超时
				LogUtil.d(TAG, "发送超时");
				smsTimeOut();
			}
		}
	};

	public void sendSms(Command comm) {

		if (comm != null) {
			
			isSending = true;
			LogUtil.d(TAG, comm.getmNumber() + "," + comm.getmContent());
			Intent intent = new Intent(MDO_SMS_SEND_ACTION);
			int phoneType = -1;
			String content = comm.getmContent();
			PendingIntent sentIntent = PendingIntent.getBroadcast(mContext, 0,
					intent, PendingIntent.FLAG_UPDATE_CURRENT);

			
			PendingIntent deliveredPI = PendingIntent.getBroadcast(mContext, 0,
					new Intent(MDO_ACTION_SMS_DELIVERY),
					PendingIntent.FLAG_CANCEL_CURRENT);
			if (GlobalVal.isDoubleSimCard(mContext)) {
				if (content == null || content.length() < 1) {
					// throw new
					// IllegalArgumentException("Invalid message body");
					onCallbackResult(1);
					MdoPayBackCheck.instance(mContext).UploadMdoPayBackInfo(mCommands, mOrderInfo, PayErrorCode.PAY_FAILE,PayErrorCode.SMS_SEND_RESULT_CONTENT_NULL);
					return;
				}
				timer.start();
				send(content, comm, sentIntent, deliveredPI);
			} else {
				phoneType = GlobalVal.selectSimSendSMS(mContext,
						mCommands.getmIMSI());
				if (phoneType == GlobalVal.SIM1_TYPE) {
					sendMultipartTextMessage(comm.getmNumber(), "",
							comm.getmContent(), sentIntent, deliveredPI,
							GlobalVal.SIM1_TYPE);
				} else if (phoneType == GlobalVal.SIM2_TYPE) {
					sendMultipartTextMessage(comm.getmNumber(), "",
							comm.getmContent(), sentIntent, deliveredPI,
							GlobalVal.SIM2_TYPE);
				}
			}
			LogUtil.d(TAG, "消息发送-->" + new Date());
		}
	}

	public void sendAgainSms(Command comm) {
		if (comm != null) {
			isSending = true;
			LogUtil.d(TAG,
					"再发送一次短信：" + comm.getmNumber() + "," + comm.getmContent());
			Intent intent = new Intent(MDO_SMS_SEND_ACTION);
			int phoneType = -1;
			// String content = comm.getmContent();

			PendingIntent sentIntent = PendingIntent.getBroadcast(mContext, 0,
					intent, 134217728);

			PendingIntent deliveredPI = PendingIntent.getBroadcast(mContext, 0,
					new Intent(MDO_ACTION_SMS_DELIVERY),
					PendingIntent.FLAG_CANCEL_CURRENT);
			if (GlobalVal.isDoubleSimCard(mContext)) {
				if (comm.getmContent() == null
						|| comm.getmContent().length() < 1) {
					onCallbackResult(1);
					MdoPayBackCheck.instance(mContext).UploadMdoPayBackInfo(mCommands, mOrderInfo,PayErrorCode.PAY_FAILE, PayErrorCode.SMS_SEND_RESULT_CONTENT_NULL);
					return;
				}
				timer.start();
				send(comm.getmContent(), comm, sentIntent, deliveredPI);
			} else {

				phoneType = GlobalVal.selectSimSendSMS(mContext,
						mCommands.getmIMSI());
				if (phoneType == GlobalVal.SIM1_TYPE) {
					sendMultipartTextMessage(comm.getmNumber(), "",
							comm.getmContent(), sentIntent, deliveredPI,
							GlobalVal.SIM1_TYPE);
				} else if (phoneType == GlobalVal.SIM2_TYPE) {
					sendMultipartTextMessage(comm.getmNumber(), "",
							comm.getmContent(), sentIntent, deliveredPI,
							GlobalVal.SIM2_TYPE);
				}
			}
			LogUtil.d(TAG, "再次进行消息发送-->" + new Date());
		}
	}

	private void send(String content, Command comm, PendingIntent sentIntent,
			PendingIntent deliveredPI) {
		// 这是旧版的sms支付
		if ("2".equals(mOrderInfo.getSms_content_type())) {
			byte[] data = null;
			try {
				data = content.getBytes("ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			SmsManager.getDefault().sendDataMessage(comm.getmNumber(), null,
					(short) 0, data, sentIntent, deliveredPI);
			return;
		}
		Log.i(TAG, "before send");
		SmsManager.getDefault().sendTextMessage(comm.getmNumber(), null,
				content, sentIntent, deliveredPI);
		Log.i(TAG, "after send");
		// 这是新版的sms支付
		// SmsManager smsManager = SmsManager.getDefault();
		// List<String> divideContents = smsManager.divideMessage(content);
		// for (String msg : divideContents) {
		// smsManager.sendTextMessage(comm.getmNumber(), null, msg, sentIntent,
		// deliveredPI);
		// }
	}

	/*
	 * number:所要发送短信的号码 content：发送的内容 pendingIntent:短信发送之后的应答：成功或失败;
	 * deliveredPI:对方收到短信之后的应答：成功或失败；
	 * phoneType:针对双卡双待手机选择哪张卡发送短信参数。0表示sim1;1表示sim2
	 */
	public void sendMultipartTextMessage(String number, String paramString2,
			String content, PendingIntent pendingIntent,
			PendingIntent paramArrayList2, int phoneType) {
		LogUtil.d(TAG, "双卡双待");
		try {
			boolean isMtk = false;
			Class<?> localClass = null;
			Method localMethod = null;
			// 高通芯片
			try {
				localClass = Class.forName("android.telephony.MSimSmsManager");
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (localClass == null) {
				// mtk芯片
				try {
					localClass = Class
							.forName("android.telephony.gemini.GeminiSmsManager");
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (localClass != null) {
					isMtk = true;
				}
			}
			if (localClass == null) {
				// 三星
				localClass = Class.forName("android.telephony.SmsManager");
			}

			if (isMtk) {
				LogUtil.d(TAG, "mtk: smsType:" + mOrderInfo.getSms_Type());
				if ("2".equals(mOrderInfo.getSms_content_type())) {
					localMethod = localClass.getDeclaredMethod(
							"sendDataMessageGemini", new Class[] {
									String.class, String.class, Short.class,
									byte[].class, Integer.class,
									PendingIntent.class, PendingIntent.class });

					byte[] data = null;
					try {
						data = content.getBytes("ISO-8859-1");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}

					localMethod.invoke(number, new Object[] { number, null,
							(short) 0, data, phoneType, pendingIntent,
							paramArrayList2 });
					return;
				}

				Class<?>[] arrayOfClass = new Class[6];
				arrayOfClass[0] = String.class;
				arrayOfClass[1] = String.class;
				arrayOfClass[2] = String.class;
				arrayOfClass[3] = Integer.TYPE;
				arrayOfClass[4] = PendingIntent.class;
				arrayOfClass[5] = PendingIntent.class;

				localMethod = localClass.getDeclaredMethod(
						"sendTextMessageGemini", arrayOfClass);
				Object[] arrayOfObject = new Object[6];
				arrayOfObject[0] = number;
				arrayOfObject[1] = paramString2;
				arrayOfObject[2] = content;
				arrayOfObject[3] = Integer.valueOf(phoneType);
				arrayOfObject[4] = pendingIntent;
				arrayOfObject[5] = paramArrayList2;
				localMethod.invoke(null, arrayOfObject);
			} else {
				LogUtil.d(TAG, "非mtk: smsType:" + mOrderInfo.getSms_Type());
				Object localObject = localClass.getDeclaredMethod("getDefault",
						null).invoke(null, null);
				if ("2".equals(mOrderInfo.getSms_content_type())) {

					localMethod = localClass.getDeclaredMethod(
							"sendDataMessage", new Class<?>[] { String.class,
									String.class, Short.class, byte[].class,
									PendingIntent.class, PendingIntent.class,
									Integer.class });
					byte[] data = null;
					try {
						data = content.getBytes("ISO-8859-1");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					localMethod.invoke(localObject, new Object[] { number,
							null, (short) 0, data, pendingIntent,
							paramArrayList2, phoneType });
					return;
				}
				Class<?>[] arrayOfClass = new Class[6];
				arrayOfClass[0] = String.class;
				arrayOfClass[1] = String.class;
				arrayOfClass[2] = String.class;
				arrayOfClass[3] = PendingIntent.class;
				arrayOfClass[4] = PendingIntent.class;
				arrayOfClass[5] = Integer.TYPE;
				localMethod = localClass.getDeclaredMethod("sendTextMessage",
						arrayOfClass);
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
			SmsManager.getDefault().sendTextMessage(number, null, content,
					pendingIntent, paramArrayList2);
			localException.printStackTrace();
		}
	}

	public void UploadMdoPayBackInfo() {
		mapMdoPayBack = getMdoPayBackInfo();

		MdoPayBackNetEngine engine = new MdoPayBackNetEngine(
				NetTools.getSid(mContext), mapMdoPayBack);
		mNetTask = new NetTask(mContext, engine, 0);
		mNetTask.setListener(this);
		new Thread(mNetTask).start();
	}

	public HashMap<String, String> getMdoPayBackInfo() {
		String numbers = mCommands.mCommandList.get(0).getmNumber();
		String contents = mCommands.mCommandList.get(0).getmContent();

		for (int i = 1; i < mCommands.mCommandList.size(); i++) {
			numbers = numbers + ","
					+ mCommands.mCommandList.get(i).getmNumber();
			contents = contents + ","
					+ mCommands.mCommandList.get(i).getmContent();
		}
		if (states.endsWith(",")) {
			states = states.substring(0, states.length() - 1);
		}

		HashMap<String, String> mdoPayBackInfo = new HashMap<String, String>();
		mdoPayBackInfo.put("imsi", mCommands.getmIMSI());
		mdoPayBackInfo.put("orderNo", mOrderInfo.getOrderNo());
		mdoPayBackInfo.put("number", numbers);
		mdoPayBackInfo.put("content", contents);
		mdoPayBackInfo.put("state", states);
		mdoPayBackInfo.put("sms_type", mOrderInfo.getSms_Type());
		mdoPayBackInfo.put("imei", NetTools.getIMEI(mContext));
		mdoPayBackInfo.put("originalcode", orignalCode);
		return mdoPayBackInfo;
	}

	private class MdoPaySMSReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(MDO_SMS_SEND_ACTION)) {
				index++;
				isSending = false;
				timer.cancel();
				int resultCode = getResultCode();
				Log.i(TAG, "resultCode:"+resultCode);
				orignalCode = PayErrorCode.SMS_SEND_RESULT_PREFIX+resultCode;
				switch (resultCode) {
				case Activity.RESULT_OK:
					isSendSucess = true;
					states += "1,";
					if (index == 2) {
						states = "1,";
					}
					saveMdoSmsBlock(mCommands.mCommandList.get(count).blockList);
					LogUtil.d(TAG, "发送成功");
					break;
				default:
					states += "0,";
					LogUtil.d(TAG, "发送失败");
					if (index == 1) {
						isSendAgain = true;
					}
					break;
				}
				
				if (!isSendAgain) {
					sendNextSms();
				}
				if (isSendAgain) {
					isSendAgain = false;
					sendAgainSms();
				}
			} else if (intent.getAction().equals(MDO_ACTION_SMS_DELIVERY)) {
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					LogUtil.d(TAG, "SMS Delivery:Success!");
					break;
				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
					LogUtil.d(TAG, "SMS Delivery:RESULT_ERROR_GENERIC_FAILURE!");
					break;
				case SmsManager.RESULT_ERROR_NO_SERVICE:
					LogUtil.d(TAG, "SMS Delivery:RESULT_ERROR_NO_SERVICE!");
					break;
				case SmsManager.RESULT_ERROR_NULL_PDU:
					LogUtil.d(TAG, "SMS Delivery:RESULT_ERROR_NULL_PDU!");
					break;
				case SmsManager.RESULT_ERROR_RADIO_OFF:
					LogUtil.d(TAG, "SMS Delivery:RESULT_ERROR_RADIO_OFF!");
					break;
				}
			}
		}
	}

	private void sendAgainSms() {
		
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				sendAgainSms(mCommands.mCommandList.get(count));
			}
		}, time * 1000);
	}

	private void sendNextSms() {
		count++;
		if (count == mCommands.mCommandList.size()) {
			count = 0;
			int resultState;
			if (isSendSucess) {
				resultState = 0;
			} else {
				resultState = 1;
			}
			onCallbackResult(resultState);
			UploadMdoPayBackInfo();
			// if(receiver != null){
			// mContext.unregisterReceiver(receiver);
			// }
		} else {
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() {
					sendSms(mCommands.mCommandList.get(count));
				}
			}, time * 1000);
		}
	}

	private void smsTimeOut() {
		for (int i = states.length(); i < mCommands.mCommandList.size() * 2; i += 2) {
			states += "0,";
		}
		int resultState;
		if (isSendSucess) {
			resultState = 0;
		} else {
			resultState = 1;
		}
		orignalCode = PayErrorCode.SMS_SEND_RESULT_TIME_OUT;//超时
		onCallbackResult(resultState);
		UploadMdoPayBackInfo();
	}

	private void onCallbackResult(int resultState) {
		if (mMdoPayCallBack != null)
			mMdoPayCallBack.onMdoPayResult(resultState);
		else
			unRegisterReceiver(mContext);
	}

	private void saveMdoSmsBlock(ArrayList<Block> blocklist) {
		MdoSmsBlockDb db = MdoSmsBlockDb.instance(mContext);
		for (int i = 0; i < blocklist.size(); i++) {
			db.save(blocklist.get(i));
		}
	}

	private void registerBroadcastReceiver() {
		receiver = new MdoPaySMSReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(MDO_SMS_SEND_ACTION);
		filter.addAction(MDO_ACTION_SMS_DELIVERY);
		mContext.registerReceiver(receiver, filter);
	}

	public interface MdoPayCallBack {
		public void onMdoPayResult(int status);
	}

	@Override
	public void onTaskRunSuccessful(int tag, BaseNetEngine engine) {
		MdoPayBackResultData resultData = (MdoPayBackResultData) engine
				.getResultData();
		if (resultData.getErrorCode() != 0) {
			if (resultData.getErrorCode() != 0) {
				String errorTip = resultData.getErrorTip();

				if (TextUtils.isEmpty(errorTip)) {
					LogUtil.d(TAG, "mdo上传支付结果失败");
				} else {
					LogUtil.d(TAG, errorTip);
				}
			}
			MdoPayBackDb db = MdoPayBackDb.instance(mContext);
			db.save(mapMdoPayBack);
		}
	}

	@Override
	public void onTaskRunError(int tag) {
		MdoPayBackDb db = MdoPayBackDb.instance(mContext);
		db.save(mapMdoPayBack);
	}

	@Override
	public void onTaskRunCanceled(int tag) {
		MdoPayBackDb db = MdoPayBackDb.instance(mContext);
		db.save(mapMdoPayBack);
	}

	public void unRegisterReceiver(Context context) {
		try {
			if (receiver != null) {
				context.unregisterReceiver(receiver);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

	}
}
