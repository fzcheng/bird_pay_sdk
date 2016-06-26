package com.legame.paysdk.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
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
import com.legame.paysdk.utils.ResourceUtil;

/**
 * 类说明：
 * 
 * @author xinhui.cheng
 * @date 2013-10-22
 * @version 1.0
 */
public class LGServiceTermsActivity extends Activity {

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
		setContentView(ResourceUtil.getLayout(this, "lgsdk_service_terms_layout"));
		initViews();
	}

	private void initViews() {
		TextView titleTv = (TextView) findViewById(ResourceUtil.getId(this, "lgsdk_title_layout_title_content"));
		Button btn = (Button) findViewById(ResourceUtil.getId(this, "lgsdk_title_layout_back_btn"));
		titleTv.setText(ResourceUtil.getString(this, "lgsdk_service_terms"));
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		initWebView();
	}

	private void initWebView() {
		final ProgressBar progressbar = (ProgressBar) findViewById(ResourceUtil.getId(this, "lgsdk_service_terms_layout_progress_small"));
		WebView webivew = (WebView) findViewById(ResourceUtil.getId(this, "lgsdk_service_terms_layout_webview"));
		webivew.getSettings().setJavaScriptEnabled(true);
		webivew.getSettings().setBuiltInZoomControls(false);
		webivew.getSettings().setSaveFormData(false);
		webivew.setFocusableInTouchMode(true);
		webivew.requestFocus();
		webivew.requestFocusFromTouch();
		webivew.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
		webivew.setWebViewClient(new WebViewClient() {

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

		});

		webivew.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				progressbar.setProgress(newProgress);
				super.onProgressChanged(view, newProgress);
			}
		});

		webivew.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// restartTimer();
				return false;
			}
		});

		webivew.loadUrl("http://sdk.leyogame.cn/show/agreement.html");
	}
}
