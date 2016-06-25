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
	public void leyoPay(final Activity activity,  OrderInfo orderInfo, final FastPaymentResultData resultData, final LeYoPayCallBack2 lypaycallback){
		
	}
	
	/**
	 * 说明:增加此接口为了适配只传入context的支付处理流程
	 * date: 2015/9/7
	 * @param context
	 * @param orderInfo
	 * @param resultData
	 * @param lypaycallback
	 */
	public void leyoPay(final Context context,  OrderInfo orderInfo, final FastPaymentResultData resultData, final LeYoPayCallBack2 lypaycallback){
		
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
	/**
	 * Mo9,upay
	 * @param strOrderInfo
	 * @param activity
	 * @param lypaycallback
	 */
	public void  onPay(final String strOrderInfo, final Activity activity, final LeYoPayCallBack lypaycallback) {
	}
	/**
	 * weipai vpay
	 * @param orderNo / wzm
	 * @param strOrderInfo
	 * @param activity
	 * @param lypaycallback
	 */
	public void  onPay(final String orderNo, final String strOrderInfo, final Activity activity, final LeYoPayCallBack2 lypaycallback) {
	}
	/**
	 * 短信支付1
	 * @param context
	 * @param orderInfo
	 * @param commands
	 * @param lypaycallback
	 */
	public void  onPay(Context context, OrderInfo orderInfo ,Commands commands, LeYoPayCallBack lypaycallback){
	}
	/**
	 * 短信支付：沃商店
	 * @param context
	 * @param orderInfo
	 * @param commands
	 * @param woStoreInfo
	 * @param lypaycallback
	 */
	public void  onPay(Context context, OrderInfo orderInfo ,Commands commands, Map<String, String> mMap, LeYoPayCallBack lypaycallback){
	}
	/**
	 * 短信支付：移动MM
	 * @param context
	 * @param orderInfo
	 * @param commands
	 * @param mMInfo
	 * @param lypaycallback
	 */
	public void  onPay(Context context, OrderInfo orderInfo ,Commands commands, Map<String, String> mMInfo,int type, LeYoPayCallBack lypaycallback){
	}
	/**
	 * 短信支付：电信爱游戏
	 * @param context
	 * @param mLoveGame
	 * @param lypaycallback
	 */
	public void  onPay(Context context, Map<String, String> mLoveGame, LeYoPayCallBack lypaycallback){
	}
	
	/**
	 * 短信支付：新浪包月
	 * @param mActivity
	 * @param CustomId
	 * @param CustomType
	 * @param count
	 * @param AppName
	 * @param AppType
	 * @param OrdNo
	 * @param lypaycallback
	 */
	public void  onPay(Activity mActivity, String CustomId, String CustomType, String count, 
			String AppName, String AppType, String OrdNo, final LeYoPayCallBack lypaycallback){
	}
	public interface LeYoPayCallBack{
		public void onLeYoPayResult(int status);
	}
	public interface LeYoPayCallBack2{
		public void onLeYoPayResult(int status,String msg);
	}
	public interface LeYoPayMobileMMInitListener{
		public void onInitFinish();
	}
}
