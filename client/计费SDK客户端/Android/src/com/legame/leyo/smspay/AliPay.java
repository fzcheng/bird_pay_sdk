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
import com.legame.paysdk.LeGamePayMent;
import com.legame.paysdk.models.OrderInfo;
import com.legame.paysdk.network.resultdata.FastPaymentResultData;

public class AliPay extends LeGamePayMent {
	
	private static final int SDK_PAY_FLAG = 1;

	private static final int SDK_CHECK_FLAG = 2;
	
	@Override
	public void leyoPay(final Context context, OrderInfo orderInfo,
			final FastPaymentResultData resultData, final LeYoPayCallBack2 lypaycallback) {
		super.leyoPay(context, orderInfo, resultData, lypaycallback);
		String payInfo = orderInfo.getPayInfo();
		
		Handler handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				
				switch(msg.what){
				case SDK_CHECK_FLAG:
					
					break;
				case SDK_PAY_FLAG:
					PayResult payResult = new PayResult((String) msg.obj);
					
					// 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
					String resultInfo = payResult.getResult();
					
					String resultStatus = payResult.getResultStatus();

					// 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
					if (TextUtils.equals(resultStatus, "9000")) {
//						Toast.makeText(activity, "支付成功",
//								Toast.LENGTH_SHORT).show();
						lypaycallback.onLeYoPayResult(0, resultStatus);
					} else {
						// 判断resultStatus 为非“9000”则代表可能支付失败
						// “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
						if (TextUtils.equals(resultStatus, "8000")) {
							Toast.makeText(context, "支付结果确认中",
									Toast.LENGTH_SHORT).show();

						} else {
							// 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
//							Toast.makeText(activity, "支付失败"+resultStatus,
//									Toast.LENGTH_SHORT).show();
							lypaycallback.onLeYoPayResult(1, resultStatus);
						}
					}
					break;
					
				}
			}
		};
		if(!(context instanceof Activity)){
			lypaycallback.onLeYoPayResult(1, PayErrorCode.PAY_NO_ACTIVITY);
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
				// 构造PayTask 对象
				PayTask alipay = new PayTask(activity);
				// 调用支付接口，获取支付结果
				String result = alipay.pay(payInfo,true);

				Message msg = new Message();
				msg.what = SDK_PAY_FLAG;
				msg.obj = result;
				handler.sendMessage(msg);
			}
		};

		// 必须异步调用
		Thread payThread = new Thread(payRunnable);
		payThread.start();
	}
}
