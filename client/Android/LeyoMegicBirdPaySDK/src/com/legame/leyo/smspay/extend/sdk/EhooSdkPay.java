package com.legame.leyo.smspay.extend.sdk;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import android.content.Context;
import android.util.Log;

import com.ehoo.app.OnPayListener;
import com.ehoo.app.Pay;
import com.ehoo.app.PayOption;
import com.ehoo.app.PaySDK;
import com.ehoo.app.ResultBean;
import com.legame.leyo.smspay.MdoPayBackCheck;
import com.legame.leyo.smspay.extend.PayErrorCode;
import com.legame.paysdk.ErrorCode;
import com.legame.paysdk.GlobalVal;
import com.legame.paysdk.LeGamePayMent.MbsPayCallback;
import com.legame.paysdk.network.resultdata.FastPaymentResultData;

/**
 * 
 * @author Kaiguang
 * @date 2016.4.28
 * @类说明 彩客易付
 * 
 */
public class EhooSdkPay {
	

	public static void init(Context context, String merID, String ehooAppid) {
		PaySDK.setMerID(merID);
		PaySDK.setOpenAppID(ehooAppid);
		PaySDK.init(context);
	}

	public static boolean hasEhooSdkPay() {
		if (GlobalVal.isClassnameExist("com.ehoo.app.PaySDK")) {
			return true;
		}
		return false;
	}

	public static void pay(final Context context, final FastPaymentResultData fastResult,
			final MbsPayCallback callback) {
		Map<String,String> payData = fastResult.getMapEhoo();
		
		Pay pay = new Pay(context);
		PayOption payOption = new PayOption();
		String chargePoint = payData.get("chargePoint");
		payOption.setOpenChargePoint(chargePoint);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd",
				Locale.getDefault());
		payOption.setOrderDate(format.format(new Date()));
		payOption.setOrderID(payData.get("orderNo"));//
		
		pay.setPayOptions(payOption);

		pay.setOnPayListener(new OnPayListener() {

			@Override
			public boolean onPostPayResult(ResultBean result) {
				if (result != null && result.isSuccess()) {
					// 支付成功（联网游戏不以此作为判断依据，请以服务器回调为准）
					MdoPayBackCheck.instance(context).
					UploadMdoPayBackInfo(fastResult.getCommands(), fastResult.getOrderInfo(), PayErrorCode.PAY_SUCCESS, result.getDetailCode()+"");
					callback.onLeYoPayResult(ErrorCode.ERROR_SUCCESS, "");
				} else {
					// 支付失败
//					Log.e("EhooSDKPAY", result.getMessage());
					MdoPayBackCheck.instance(context).
					UploadMdoPayBackInfo(fastResult.getCommands(), fastResult.getOrderInfo(), PayErrorCode.PAY_FAILE, result.getDetailCode()+"");
					callback.onLeYoPayResult(ErrorCode.ERROR_FAIL, result.getMessage()+":"+result.getDetailCode());
				}
				/*
				 * 返回 true 拦截 sdk 内部结果提示， 返回 false 显示 sdk 内部结果提示，建议返回 false
				 */
				return false;
			}
		});

		pay.start();
	}

}
