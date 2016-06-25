package com.yeelo.master.wxapi;

import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.legame.paysdk.ErrorCode;
import com.legame.paysdk.ListenerHolder;
import com.legame.paysdk.activity.WapPayActivity;
//import com.tencent.mm.sdk.constants.Build;
//import com.tencent.mm.sdk.modelpay.PayReq;
//import com.tencent.mm.sdk.openapi.IWXAPI;
//import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class Wxpay {
	
//	private IWXAPI api;
	
	protected static String APPID ;
	
	private Map<String,String> wxPayInfo;
	
	private Context context;
	
	public Wxpay(Context context,Map<String,String> wxPayInfo){
		this.context = context;
		this.wxPayInfo = wxPayInfo;
	}
	
	/**
	 * 神州微信支付
	 */
	public void payBySzWx(){
		if(!isWXInstall()){
			ListenerHolder.sMbsPayCallback.onLeYoPayResult(ErrorCode.ERROR_FAIL, "未安装微信");
			return;
		}
		
		String orderNo = wxPayInfo.get("order_no");
		String requestUrl = wxPayInfo.get("requestUrl");
		String backUpUrl = wxPayInfo.get("requestUrl_backup");
		String successPageUrl = wxPayInfo.get("successPage_Url");
		String failPageUrl = wxPayInfo.get("failPage_Url");
		String productUrl = wxPayInfo.get("productUrl");
		
//		Log.e("WxPay", successPageUrl);
		Intent intent = new Intent((Activity)context,WapPayActivity.class);
//		intent.setAction(Intent.ACTION_VIEW);
		intent.setData(Uri.parse(requestUrl));
		intent.putExtra("order_no", orderNo);
		intent.putExtra("requestUrl", requestUrl);
		intent.putExtra("backUpUrl", backUpUrl);
		intent.putExtra("successPage_Url", successPageUrl);
		intent.putExtra("failPage_Url", failPageUrl);
		intent.putExtra("productUrl", productUrl);
		context.startActivity(intent);
	}
	
	private boolean isWXInstall(){
		try
	    {
	      if ((this.context.getPackageManager().getPackageInfo("com.tencent.mm", 64)) == null)
	        return false;
	      return true;
	    }
	    catch (PackageManager.NameNotFoundException localNameNotFoundException)
	    {
	    }
	    return false;
	}

//	/**
//	 * 微信SDK官方支付
//	 */
//	public void pay(){
//		api = WXAPIFactory.createWXAPI(context, APPID,false);
//		api.registerApp(APPID);
//		
//		if(!api.isWXAppInstalled()){
//			ListenerHolder.sMbsPayCallback.onLeYoPayResult(ErrorCode.ERROR_FAIL, "未安装微信");
//			return;
//		}
//		
//		if(!api.isWXAppSupportAPI()){
//			ListenerHolder.sMbsPayCallback.onLeYoPayResult(ErrorCode.ERROR_FAIL, "微信当前版本不支持!");
//			return;
//		}
//		
//		final PayReq req = new PayReq();
//		req.appId = wxPayInfo.get("appid");
//		req.partnerId = wxPayInfo.get("partnerid");
//		req.prepayId = wxPayInfo.get("prepayid");
//		req.nonceStr = wxPayInfo.get("noncestr");
//		req.timeStamp = wxPayInfo.get("timestamp");
//		req.packageValue = wxPayInfo.get("extra");
//		req.sign = wxPayInfo.get("sign");
//		req.extData = "app data";
//		api.sendReq(req);
//	}
	
}
