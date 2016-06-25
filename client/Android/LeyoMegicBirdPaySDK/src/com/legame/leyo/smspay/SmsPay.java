package com.legame.leyo.smspay;

import java.util.List;

import android.app.Activity;
import android.content.Context;

import com.legame.leyo.smspay.thread.QueueSendSms;
import com.legame.leyo.smspay.thread.QueueSendSms.QueueSendSmsListener;
import com.legame.paysdk.LeGamePayMent;
import com.legame.paysdk.activity.LYGamePayment;
import com.legame.paysdk.models.OrderInfo;
import com.legame.paysdk.models.SdkpayData;
import com.legame.paysdk.network.resultdata.FastPaymentResultData;


public class SmsPay extends LeGamePayMent{
	private static final String TAG = "LeGamePayMent";
	
	private FastPaymentResultData mResultData ;
	
	public SmsPay(){
		
	}
	
	private LYGamePayment lyGamePayment;
	public void setLYGamePayment(LYGamePayment lyGamePayment){
		this.lyGamePayment = lyGamePayment; 
	}
	
	public void leyoPay(final Context context,  final OrderInfo orderInfo, 
			final FastPaymentResultData resultData, final MbsPayCallback lypaycallback){
		final String strChargeSuceessstip = resultData.getTipInfo().getChargesuceesstip();				
		mResultData = resultData;
		
		String smsType = orderInfo.getSms_Type();
		
		QueueSendSmsListener queueListener = new QueueSendSmsListener() {
			
			@Override
			public void onSendSmsResultListener(int statusResult) {
				
				
			}
		};
		final List<SdkpayData> sdkDataList = mResultData.getSdkMapList();
		setQueueSendSms(context, sdkDataList, queueListener);
	}
	
	private void setQueueSendSms(Context context,List<SdkpayData> sdkDataList,QueueSendSmsListener listener){
		if(!GlobalVal.sIsQueueSendSmsInit){
			QueueSendSms.getInstance().init(context);
			GlobalVal.sIsQueueSendSmsInit = true;
		}
		
		QueueSendSms.getInstance().queueSdkMsgPushHead(sdkDataList.get(0));
		QueueSendSms.getInstance().setIntervalTime(mResultData.getIntervaTime());
		QueueSendSms.getInstance().setQueueSendSmsListener(listener);
		QueueSendSms.getInstance().setLyGamePayment(lyGamePayment);
		QueueSendSms.getInstance().start();
	}
	
	
	
	
}
