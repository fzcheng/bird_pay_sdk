package com.legame.paysdk;

import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;

import com.legame.paysdk.models.Commands;
import com.legame.paysdk.models.OrderInfo;
import com.legame.paysdk.network.resultdata.FastPaymentResultData;

public class LeGamePayMent {
	private static LeGamePayMent sInstance = null;
	public LeGamePayMent(){
		
	}
	public static synchronized LeGamePayMent defaultPay() {
		if (sInstance == null) {
			sInstance = new LeGamePayMent();
		}
		return sInstance;
	}
	
	/**
	 * 说明:增加此接口为了适配只传入context的支付处理流程
	 * date: 2015/9/7
	 * @param context
	 * @param orderInfo
	 * @param resultData
	 * @param lypaycallback
	 */
	public void leyoPay(final Context context,  OrderInfo orderInfo, final FastPaymentResultData resultData, final MbsPayCallback lypaycallback){
		
	}
	/**
	 * 支付宝,财付通
	 * @param strOrderInfo
	 * @param callback
	 * @param myWhat
	 * @param activity
	 */
	public void  onPay(final String strOrderInfo, final Handler callback,
			final int myWhat, final Activity activity) {
	}
	
	
	
	
	
	
	
	public interface LeYoPayCallBack{
		public void onLeYoPayResult(int status);
	}
	public interface MbsPayCallback{
		public void onLeYoPayResult(int status,String msg);
	}
	public interface LeYoPayMobileMMInitListener{
		public void onInitFinish();
	}
}
