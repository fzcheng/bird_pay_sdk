package com.legame.leyo.smspay.thread;

import java.util.LinkedList;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.legame.paysdk.activity.LYGamePayment;
import com.legame.paysdk.models.Order;
import com.legame.paysdk.models.SdkpayData;

/**
 * 类说明：发送信息队列线程
 * 
 * @author Kaiguang
 * @date 2015/4/15
 * 
 */
public class QueueSendSms {
	private static final String TAG = "QueueSendSms";

	private SendMessagesThread thread;

	private Context mContext;

	private QueueSendSmsListener mQueueSendSmsListener;

	private LinkedList<Order> msgQueue;// 信息队列
	
	private LinkedList<SdkpayData> sdkMsgQueue;//SDK数据信息队列
	
	private SdkpayData mSdkpayData;

	private long lastTime = 0;

	private static Handler handler;
	
	private int mIntervalTime;
	
	private Activity activity;
	
	private LYGamePayment mLyGamePayment;
	
	private static final int SMSPAY = 0;
	
	
	private QueueSendSms() {
	}

	private static class SingletonHolder {
		private static final QueueSendSms instance = new QueueSendSms();
	}

	public static final QueueSendSms getInstance() {
		return SingletonHolder.instance;
	}

	public void init(Context context) {
		this.mContext = context;
		if(context instanceof Activity)
			activity = (Activity) context;
		lastTime = System.currentTimeMillis();

		if (msgQueue == null) {
			msgQueue = new LinkedList<Order>();
		}
		if(sdkMsgQueue == null){
			sdkMsgQueue = new LinkedList<SdkpayData>();
		}
		if (handler == null) {
			handler = new Handler() {
				@Override
				public void handleMessage(Message msg) {}
			};
		}
	}

	public synchronized void setQueueSendSmsListener(
			QueueSendSmsListener mQueueSendSmsListener) {
		this.mQueueSendSmsListener = mQueueSendSmsListener;
	}

	public synchronized void resetOnSendSmsResultListener() {
		this.mQueueSendSmsListener = null;
	}
	
	public synchronized void resetCurrentTime(){
		lastTime = System.currentTimeMillis();
	}

	public synchronized void setIntervalTime(String intervalTime) {
		if (TextUtils.isEmpty(intervalTime)
				|| !intervalTime.trim().matches("[0-9]*")) {
			mIntervalTime = 30 ;//+ (int) (Math.random() * 10);
			return;
		}
		int time = Integer.parseInt(intervalTime.trim());
		mIntervalTime = time;

	}
	
	public void setLyGamePayment(LYGamePayment lyGamePayment){
		mLyGamePayment = lyGamePayment;
	}
	

	/**
	 * 加入消息队列
	 * 
	 * @param obj
	 */
	public synchronized void queueMsgPush(Order order) {
		msgQueue.add(order);
	}
	
	public synchronized void queueSdkMsgPush(SdkpayData sdkData){
		sdkMsgQueue.add(sdkData);
	}

	/**
	 * 加入消息到队列头部
	 * 
	 * @param order
	 */
	public synchronized void queueMsgPushHead(Order order) {
		msgQueue.addFirst(order);
	}
	
	public synchronized void queueSdkMsgPushHead(SdkpayData sdkData){
		sdkMsgQueue.addFirst(sdkData);
	}

	/**
	 * 出队列
	 * 
	 * @return
	 */
	private synchronized Order queueMsgPop() {
		return msgQueue.poll();
	}
	
	private synchronized SdkpayData queueSdkMsgPop(){
		return sdkMsgQueue.poll();
	}

	/**
	 * 判断队列是否为空
	 * 
	 * @return
	 */
	private boolean queueIsNull() {
		if (msgQueue.peek() == null) {
			return true;
		}
		return false;
	}
	
	private boolean queueSdkIsNull(){
		if(sdkMsgQueue.peek() == null){
			return true;
		}
		
		return false;
	}

	public void start() {
		if (thread == null) {
			thread = new SendMessagesThread();
			thread.start();
			while (!thread.isAlive()) {
				sleeps(0.1f);
			}
		}

	}

	private static final int NEW_SMS_PAY = 0;
	private static final int OLD_SMS_PAY = 1;
	private class SendMessagesThread extends Thread {

		public SendMessagesThread() {
//			 Looper.prepare();
		}

		@Override
		public void run() {
				
			while (true) {
				if (queueIsNull()&&queueSdkIsNull()) {
					sleeps(1);
				} else {
					sendSms();
					pay();
					sleepSeconds();
				}
			}

		}
		
		private void pay(){
			if(!queueSdkIsNull()){
				mSdkpayData = queueSdkMsgPop();
				String sdkType = mSdkpayData.getOrderInfo().getSms_Type();
				if(mSdkpayData.getOrderInfo().getSms_pay_type() == 1 ||
						mSdkpayData.getOrderInfo().getSms_pay_type() ==2){
					sendsms();
				}else{
					sdkPay(sdkType); 
				}
				
			}
		}
		
		/**
		 * 短信多补点计费
		 */
		private void sendsms(){
			Log.e("SendMessagesThread", "order:"
					+ mSdkpayData.getOrderInfo().getOrderNo() + " Thead ID:"
					+ getId());
			Message msg = handler.obtainMessage();
			msg.arg1 = NEW_SMS_PAY;
			msg.obj = mSdkpayData;
			handler.sendMessage(msg);
			Log.e("SendMessagesThread", "mIntervalTime:"
					+ mIntervalTime);
		}
		
		private void sendSms(){
			if(!queueIsNull()){
				Order order = queueMsgPop();
				Message msg = handler.obtainMessage();
				msg.arg1 = OLD_SMS_PAY;
				msg.obj = order;
				handler.sendMessage(msg);
			}
		}
		
		/**
		 * SDK多补点计费
		 */
		private void sdkPay(String sdkType){
			Message msg = handler.obtainMessage();
			msg.obj = mSdkpayData;
			handler.sendMessage(msg);
		}

	}
	
	
	public static void sleeps(float time) {
		try {
			int t = (int) (time * 1000);
			Thread.sleep(t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void sleepSeconds(){
		for (;;) {
			if (System.currentTimeMillis() - lastTime < mIntervalTime * 1000) {
				QueueSendSms.sleeps(1);
				continue;
			} else {
				lastTime = System.currentTimeMillis();
				break;
			}
		}
	}

	public static final int QUEUEPAY_SUCCESS = 0;
	public static final int QUEUEPAY_FAIL = 1;
	public interface QueueSendSmsListener {
		public void onSendSmsResultListener(int statusResult);
	}

}
