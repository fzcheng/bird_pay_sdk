package com.legame.paysdk.dialog;

import java.io.File;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Process;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.legame.paysdk.ErrorCode;
import com.legame.paysdk.GlobalVal;
import com.legame.paysdk.ListenerHolder;
import com.legame.paysdk.models.UpgradeInfo;
import com.legame.paysdk.service.DownloadService;
import com.legame.paysdk.service.DownloadTsk;
import com.legame.paysdk.utils.Config;
import com.legame.paysdk.utils.LogUtil;
import com.legame.paysdk.utils.ResourceUtil;

public class UpgradeDialog extends Dialog implements DialogInterface.OnCancelListener{
	protected static final String TAG = "UpgradeDialog";

	public interface BackgroundDownloadCallback{
		void downloadInBackground(String url, String fileName);
	}

	private TextView mContent;
	private ProgressBar mProgressBar;
	private Button mButton1;
	private Button mButton2;

	private UpgradeInfo mUpgradeInfo;

	private boolean mDownloading;

	public UpgradeDialog(final Context context, UpgradeInfo info) {
		super(context, ResourceUtil.getStyle(context, "lgsdk_custom_dialog_theme"));
		mUpgradeInfo = info;
		init();
	}

	private void init(){
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setCanceledOnTouchOutside(false);
		setContentView(ResourceUtil.getLayout(getContext(), "lgsdk_upgrade_dialog"));
		getWindow().getAttributes().gravity = Gravity.CENTER;
		TextView title = (TextView) findViewById(ResourceUtil.getId(getContext(), "lgsdk_upgrade_dialog_title"));
		title.setText("更新");
		
		mContent = (TextView) findViewById(ResourceUtil.getId(getContext(), "lgsdk_upgrade_dialog_content"));
		mProgressBar = (ProgressBar) findViewById(ResourceUtil.getId(getContext(), "lgsdk_upgrade_dialog_progress"));
		mButton1 = (Button) findViewById(ResourceUtil.getId(getContext(), "lgsdk_upgrade_dialog_button1"));
		mButton2 = (Button) findViewById(ResourceUtil.getId(getContext(), "lgsdk_upgrade_dialog_button2"));
		setOnCancelListener(this);

		mContent.setText("检测到新版本，建议立即更新");


		mProgressBar.setVisibility(View.INVISIBLE);

		if (mUpgradeInfo.getForce()) {
			mButton1.setText("更新");
			mButton2.setVisibility(View.GONE);
			mButton1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {

					mProgressBar.setVisibility(View.VISIBLE);
					mProgressBar.setIndeterminate(true);
					mButton1.setEnabled(false);

					String fileName = getFileName(mUpgradeInfo.getDownloadUrl());

					if (TextUtils.isEmpty(fileName)) {
						Toast.makeText(getContext(), "获取文件名失败", Toast.LENGTH_LONG).show();
						return;
					}

					DownloadService.start(getContext(), mUpgradeInfo.getDownloadUrl(), fileName);
				}
			});
		}
		IntentFilter filter = new IntentFilter(DownloadService.ACTION_REPORT_DOWNLOAD_EVENT);
		getContext().registerReceiver(mDownloadReceiver, filter);
	}

	private static String getFileName(String url){
		int lastSprit = url.lastIndexOf("/");
		int quesMark = url.indexOf("?");

		if (quesMark == -1) {
			return url.substring(lastSprit + 1);
		}else{
			return url.substring(lastSprit + 1, quesMark);
		}
	}

	private BroadcastReceiver mDownloadReceiver = new BroadcastReceiver() {
		private long mFileLen;
		private boolean mRunning;
		@Override
		public void onReceive(Context context, Intent intent) {
			LogUtil.d(TAG, "onReceive");

			int event = intent.getIntExtra(DownloadService.EXTRA_EVENT_CODE, 0);
			if (event == DownloadTsk.TSK_CODE_START) {
				mRunning = true;
				mFileLen = intent.getLongExtra(DownloadService.EXTRA_EVENT_FILESIZE, -1);
				mButton1.setEnabled(false);
				mProgressBar.setIndeterminate(false);
				mProgressBar.setMax((int) mFileLen);
				mProgressBar.setProgress(0);

				final File file = new File(Config.DOWNLOAD_DIR + "/" + getFileName(mUpgradeInfo.getDownloadUrl()) + DownloadTsk.TEMP_FILE_SUFFIX);
				new Thread(new Runnable() {
					@Override
					public void run() {
						while(mRunning){
							final long len = file.length();
							GlobalVal.sHandler.post(new Runnable() {
								@Override
								public void run() {
									LogUtil.d(TAG, "filelen:" + mFileLen + " current:" + len);
									mProgressBar.setProgress((int) len);
								}
							});
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}

					}
				}).start();

			}else if (event == DownloadTsk.TSK_CODE_SUCCESS) {
				mRunning = false;
				Uri uri = Uri.fromFile(new File(Config.DOWNLOAD_DIR + "/" + getFileName(mUpgradeInfo.getDownloadUrl())));
				Intent it = new Intent(Intent.ACTION_VIEW);
				it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				it.setDataAndType(uri, "application/vnd.android.package-archive");
				context.startActivity(it);
				cancel();
			}else if (event == DownloadTsk.TSK_CODE_FAIL) {
				mRunning = false;
				mButton1.setText("重试");
				mButton1.setEnabled(true);
				mButton1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						String fileName = getFileName(mUpgradeInfo.getDownloadUrl());

						if (TextUtils.isEmpty(fileName)) {
							Toast.makeText(getContext(), "获取文件名失败", Toast.LENGTH_LONG).show();
							return;
						}
						DownloadService.start(getContext(), mUpgradeInfo.getDownloadUrl(), fileName);
					}
				});

			}
		}
	};


	@Override
	public void onCancel(DialogInterface dialog) {
		getContext().unregisterReceiver(mDownloadReceiver);
		if (mUpgradeInfo.getForce()) {
			GlobalVal.sHandler.post(new Runnable() {
				@Override
				public void run() {
					ListenerHolder.sInitListener.initFinished(ErrorCode.ERROR_INIT_FAIL, "升级取消");
					System.exit(0);
					Process.killProcess(Process.myPid());
				}
			});

		}
	}
}
