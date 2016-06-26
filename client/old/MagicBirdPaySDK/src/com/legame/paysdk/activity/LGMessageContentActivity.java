package com.legame.paysdk.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.legame.paysdk.GlobalVal;
import com.legame.paysdk.models.PushModel;
import com.legame.paysdk.utils.ResourceUtil;

/**
 * 
 * @author leyogame
 * 网页下载
 */
public class LGMessageContentActivity extends Activity {
	
	public static String KEY_TITLE = "title";
	public static String KEY_CONTENT = "content";
	public static String KEY_CONTENT_TYPE = "content_type";
	public static String KEY_FROM = "from";
	
	private String mTitle;
	private String mContent;
	private int mContentType;
	private WebView mWebView;
	private String mFrom;
	private View mBack;
	private View mGo;
	private View mRefresh;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		switch (GlobalVal.getOrientation(this)) {
		case ORIENTATION_LANDSCAPE:
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
			break;
		case ORIENTATION_PORTRAIT:
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			break;
		}

		initData();
		setContentView(ResourceUtil.getLayout(this, "lgsdk_msg_content_layout"));
		initViews();
	}

	private void initData()
	{
		Intent intent = getIntent();
		mContentType = intent.getIntExtra(KEY_CONTENT_TYPE, 0);
		mTitle = intent.getStringExtra(KEY_TITLE);
		mContent = intent.getStringExtra(KEY_CONTENT);
		mFrom = intent.getStringExtra(KEY_FROM);
		
		if (TextUtils.isEmpty(mTitle)) {
			mTitle = "消息内容";
		}
		
		if(TextUtils.isEmpty(mContent)){
			mContent = "内容为空！";
		}
	}
	
	@Override
	public void onBackPressed() {
		goback();
		super.onBackPressed();
	}
	
	private void goback() {
		finish();
		overridePendingTransition(ResourceUtil.getAnim(getApplicationContext(), "lgsdk_left_in"), ResourceUtil.getAnim(getApplicationContext(), "lgsdk_right_out"));
	}
	
	private void initViews() {
		TextView titleTv = (TextView) findViewById(ResourceUtil.getId(this, "lgsdk_title_layout_title_content"));
		Button btn = (Button) findViewById(ResourceUtil.getId(this, "lgsdk_title_layout_back_btn"));
		titleTv.setText(mTitle);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				goback();
			}
		});
		
		if(!TextUtils.isEmpty(mFrom)/* && mFrom.equals(LGInfoListActivity.class.getSimpleName())*/)
		{
			//显示webview下面导航栏
			View navigationBar = findViewById(ResourceUtil.getId(this, "lgsdk_msg_content_layout_navigationbar"));
			navigationBar.setVisibility(View.VISIBLE);
		}
		mGo = findViewById(ResourceUtil.getId(this, "lgsdk_msg_content_layout_navigationbar_go"));
		mBack = findViewById(ResourceUtil.getId(this, "lgsdk_msg_content_layout_navigationbar_back"));
		mRefresh = findViewById(ResourceUtil.getId(this, "lgsdk_msg_content_layout_navigationbar_refresh"));
		
		mGo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mWebView.goForward();
			}
		});
		
		mBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mWebView.goBack();
			}
		});
		
		mRefresh.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mWebView.reload();
			}
		});
		
		initWebView();
	}

	@SuppressLint("SetJavaScriptEnabled")
	private void initWebView() {
		final ProgressBar progressbar = (ProgressBar) findViewById(ResourceUtil.getId(this, "lgsdk_msg_content_layout_progress_small"));
		mWebView = (WebView) findViewById(ResourceUtil.getId(this, "lgsdk_msg_content_layout_webview"));
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.getSettings().setBuiltInZoomControls(false);
		mWebView.getSettings().setSaveFormData(false);
		mWebView.setFocusableInTouchMode(true);
		mWebView.requestFocus();
		mWebView.requestFocusFromTouch();
		mWebView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
		mWebView.setWebViewClient(new WebViewClient() {

			@Override
			public void onPageFinished(WebView view, String url) {
				progressbar.setVisibility(View.GONE);
				super.onPageFinished(view, url);
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				progressbar.setVisibility(View.VISIBLE);
				super.onPageStarted(view, url, favicon);
			}

			@SuppressLint("NewApi")
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				System.out.println("调用网页下载");
				if(url.endsWith(".apk"))
				{
					System.out.println("确定调用网页下载");
//					DownloadManager downloadManager = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);
//					DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
//					long downloadId = downloadManager.enqueue(request);
					Intent it = new Intent(Intent.ACTION_VIEW);
					it.setData(Uri.parse(url));
					it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(it);
					return true;
				}
				return super.shouldOverrideUrlLoading(view, url);
			}
		});

		mWebView.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				progressbar.setProgress(newProgress);
				super.onProgressChanged(view, newProgress);
			}
		});

		
		mWebView.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// restartTimer();
				return false;
			}
		});
		
		if(mContentType == PushModel.TYPE_TEXT_MSG)
		{
			mWebView.loadDataWithBaseURL(null, mContent, "text/html",  "utf-8", null);
		}
		else if(mContentType == PushModel.TYPE_URL_MSG)
		{
			mWebView.loadUrl(mContent);
			//webView.loadUrl("http://www.apk8.com/game/game_13686.html");
		}
		
	}
}

