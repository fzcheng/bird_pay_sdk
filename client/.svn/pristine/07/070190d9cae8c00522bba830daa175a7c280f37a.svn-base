package com.legame.leyo.smspay;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.legame.leyo.smspay.extend.PayErrorCode;
import com.legame.leyo.smspay.extend.PayResult;
import com.legame.paysdk.ErrorCode;
import com.legame.paysdk.LeGamePayMent;
import com.legame.paysdk.models.OrderInfo;
import com.legame.paysdk.network.resultdata.FastPaymentResultData;

public class AliPay extends LeGamePayMent {
	
	private static final int SDK_PAY_FLAG = 1;

	private static final int SDK_CHECK_FLAG = 2;
	
	@Override
	public void leyoPay(Context context, OrderInfo orderInfo,
			final FastPaymentResultData resultData, MbsPayCallback lypaycallback) {
		String payInfo = orderInfo.getPayInfo();
		
		pay(context, payInfo, lypaycallback);
	}
	
	public void pay(final Context context,String payInfo,final MbsPayCallback lypaycallback){
		Handler handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				
				switch(msg.what){
				case SDK_CHECK_FLAG:
					
					break;
				case SDK_PAY_FLAG:
					PayResult payResult = new PayResult((String) msg.obj);
					
					String resultStatus = payResult.getResultStatus();

					if (TextUtils.equals(resultStatus, "9000")) {
						lypaycallback.onLeYoPayResult(ErrorCode.ERROR_SUCCESS, resultStatus);
					} else {
						if (TextUtils.equals(resultStatus, "8000")) {
							Toast.makeText(context, "支付结果确认中",
									Toast.LENGTH_SHORT).show();
							lypaycallback.onLeYoPayResult(ErrorCode.ERROR_FAIL, "alipay:"+resultStatus);
						} else {
							// 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
							lypaycallback.onLeYoPayResult(ErrorCode.ERROR_FAIL, "alipay:"+resultStatus);
						}
					}
					break;
					
				}
			}
		};
		if(!(context instanceof Activity)){
			lypaycallback.onLeYoPayResult(ErrorCode.ERROR_FAIL, PayErrorCode.PAY_NO_ACTIVITY);
			return;
		}
		Activity activity = (Activity) context;
		onPay(payInfo, handler, 0, activity);
	}
	
	@Override
	public void onPay(final String payInfo,final Handler handler, int myWhat,
			final Activity activity) {
		Runnable payRunnable = new Runnable() {

			@Override
			public void run() {
				PayTask alipay = new PayTask(activity);
				String result = alipay.pay(payInfo,true);

				Message msg = new Message();
				msg.what = SDK_PAY_FLAG;
				msg.obj = result;
				handler.sendMessage(msg);
			}
		};

		Thread payThread = new Thread(payRunnable);
		payThread.start();
	}
}
