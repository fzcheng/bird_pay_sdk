package com.legame.leyo.smspay;

import java.util.Map;

import com.legame.paysdk.ErrorCode;
import com.legame.paysdk.ListenerHolder;
import com.legame.paysdk.activity.WapPayActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;

public class ShenzhouPay {
	
	private Context context;
	
	private Map<String,String> payInfo;
	
	public ShenzhouPay(Context context,Map<String,String> payInfo){
		this.context = context;
		this.payInfo = payInfo;
	}
	
	/**
	 * 微信支付
	 */
	public void payByWxpay(){
		if(!isWXInstall()){
			ListenerHolder.sMbsPayCallback.onLeYoPayResult(ErrorCode.ERROR_FAIL, "未安装微信");
			return;
		}
		pay();
	}
	
	public void payByAlipay(){
		pay();
//		String orderNo = payInfo.get("order_no");
//		String requestUrl = payInfo.get("requestUrl");
//		String backUpUrl = payInfo.get("requestUrl_backup");
//		String successPageUrl = payInfo.get("successPage_Url");
//		String failPageUrl = payInfo.get("failPage_Url");
//		String productUrl = payInfo.get("productUrl");
//		
//		Intent intent = new Intent(/*(Activity)context,WapPayActivity.class*/);
//		intent.setAction(Intent.ACTION_VIEW);
//		intent.setData(Uri.parse(requestUrl));
//		intent.putExtra("order_no", orderNo);
//		intent.putExtra("requestUrl", requestUrl);
//		intent.putExtra("backUpUrl", backUpUrl);
//		intent.putExtra("successPage_Url", successPageUrl);
//		intent.putExtra("failPage_Url", failPageUrl);
//		intent.putExtra("productUrl", productUrl);
//		context.startActivity(intent);
	}
	
	/**
	 * 神州付
	 */
	private void pay(){
		String orderNo = payInfo.get("order_no");
		String requestUrl = payInfo.get("requestUrl");
		String backUpUrl = payInfo.get("requestUrl_backup");
		String successPageUrl = payInfo.get("successPage_Url");
		String failPageUrl = payInfo.get("failPage_Url");
		String productUrl = payInfo.get("productUrl");
		
//		Log.e("shenzhoupay", "request:"+requestUrl);
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
	
	private boolean isAliInstall(){
		try
	    {
	      if ((this.context.getPackageManager().getPackageInfo("com.eg.android.AlipayGphone", 64)) == null)
	        return false;
	      return true;
	    }
	    catch (PackageManager.NameNotFoundException localNameNotFoundException)
	    {
	    }
		return false;
	}
	
}
