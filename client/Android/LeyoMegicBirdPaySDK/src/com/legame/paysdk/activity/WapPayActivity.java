package com.legame.paysdk.activity;

import com.legame.paysdk.ErrorCode;
import com.legame.paysdk.ListenerHolder;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.FrameLayout.LayoutParams;

/**
 * 
 * @author Kaiguang
 * @date 2015.12.18
 * @类说明 实现wap支付方式
 *
 */
public class WapPayActivity extends Activity{
	
	private static final int WHAT_START = 0;
	private static final int WHAT_FINISH = 1;
	private static final int WHAT_GET_URL = 2;
	
	private ProgressBar loadingBar ;
	
	private WebView webView ;
	
	private String url;
	
	private static String successPageUrl;
	
	private static String productUrl;
	
	private static String failPageUrl;
	
	private WapPayHandler handler ;
	
	private static boolean isLoading = true;
	
	private static boolean paySuccess = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		
		url = getIntent().getStringExtra("requestUrl");
		
		successPageUrl = intent.getStringExtra("successPage_Url");
		
		productUrl = intent.getStringExtra("productUrl");
		
		failPageUrl = intent.getStringExtra("failPage_Url");
		
		initView();
		
		initHandler();
		
		setWebView();
		
		handler.sendMessage(handler.obtainMessage(WHAT_GET_URL, url));
	}
	
	private void initView(){
		FrameLayout layout = new FrameLayout(getApplicationContext());
		layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		setContentView(layout);
		
		loadingBar = new ProgressBar(getApplicationContext());
		loadingBar.setVisibility(View.GONE);
		LayoutParams loadingBarLayout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		loadingBarLayout.gravity = Gravity.CENTER;
		loadingBar.setLayoutParams(loadingBarLayout);		
		webView = new WebView(getApplicationContext());
		LayoutParams webViewLayout = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		webViewLayout.gravity = Gravity.CENTER;
		webView.setLayoutParams(webViewLayout);
		
		layout.addView(webView);
		layout.addView(loadingBar);
	}
	
	private void setWebView(){ 
		webView.getSettings().setJavaScriptEnabled(true);
		webView.requestFocusFromTouch();
		CookieManager.getInstance().setAcceptCookie(true);
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				if(url.startsWith("weixin")||url.startsWith("alipay")){
					Intent intent = new Intent();
					intent.setAction(Intent.ACTION_VIEW);
					intent.setData(Uri.parse(url));
					WapPayActivity.this.startActivity(intent);
				}else{
					view.loadUrl(url);
				}
				
				return true;
			}
			
			@Override
			public void doUpdateVisitedHistory(WebView view, String url,
					boolean isReload) {
				super.doUpdateVisitedHistory(view, url, isReload);
			}
			
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				handler.sendEmptyMessage(WHAT_START);
			}
			
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				Message msg = handler.obtainMessage(WHAT_FINISH,url);
				handler.sendMessage(msg);
			}
		});
	}
	
	private void initHandler(){
		handler = new WapPayHandler();
		handler.init(this,loadingBar, webView);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		if(isLoading){
			return false;
		}
		
		if(keyCode == KeyEvent.KEYCODE_BACK){
			if(paySuccess){
				ListenerHolder.sMbsPayCallback.onLeYoPayResult(ErrorCode.ERROR_SUCCESS, "支付成功");
				paySuccess = false;
			}else{
				ListenerHolder.sMbsPayCallback.onLeYoPayResult(ErrorCode.ERROR_FAIL, "支付失败");
			}
			
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
	
	
	public static class WapPayHandler extends Handler{
		
		private Activity activity;
		
		private ProgressBar loadingBar;
		
		private WebView webView;
		
//		private String successPageUrl;
		
//		private String productUrl;
		
		public WapPayHandler(){
			
		}
		
		public void init(Activity activity,ProgressBar loadingBar,WebView webView){
			this.activity = activity;
			this.loadingBar = loadingBar;
			this.webView = webView;
		}

		
		public void handleMessage(android.os.Message msg) {
			if(msg.what == WHAT_START){
				isLoading = true; 
				loadingBar.setVisibility(View.VISIBLE);
			}else if(msg.what == WHAT_FINISH){
				Log.e("WapPayActivity", "success-"+successPageUrl);
				Log.e("WapPayActivity", "produceURL-"+productUrl);
				Log.e("WapPayActivity", "finishURL-"+String.valueOf(msg.obj));
				if(successPageUrl.equals(String.valueOf(msg.obj))){
					paySuccess = true;
					activity.finish();
					ListenerHolder.sMbsPayCallback.onLeYoPayResult(ErrorCode.ERROR_SUCCESS, "支付成功");
					paySuccess = false;
				}else if(productUrl.equals(String.valueOf(msg.obj)) 
						|| failPageUrl.equals(String.valueOf(msg.obj))){
					activity.finish();
					ListenerHolder.sMbsPayCallback.onLeYoPayResult(ErrorCode.ERROR_FAIL, "支付失败");
				}
				loadingBar.setVisibility(View.GONE);
				isLoading = false;
			}else if(msg.what == WHAT_GET_URL){
				webView.loadUrl(String.valueOf(msg.obj));
			}
			
		}

	}
}
