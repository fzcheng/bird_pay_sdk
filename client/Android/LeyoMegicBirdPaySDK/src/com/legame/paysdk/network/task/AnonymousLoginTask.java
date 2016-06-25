package com.legame.paysdk.network.task;

import java.io.File;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.legame.paysdk.ErrorCode;
import com.legame.paysdk.GlobalVal;
import com.legame.paysdk.ListenerHolder;
import com.legame.paysdk.activity.LGUserLoginActivity;
import com.legame.paysdk.db.AnonymousLoginDb;
import com.legame.paysdk.db.PaySdkDBHelper;
import com.legame.paysdk.db.UserInfoDb;
import com.legame.paysdk.dialog.TextDialog;
import com.legame.paysdk.models.UserInfo;
import com.legame.paysdk.network.engine.BaseNetEngine;
import com.legame.paysdk.network.engine.OnekeyRegisterNetEngine;
import com.legame.paysdk.network.engine.UserLoginNetEngine;
import com.legame.paysdk.network.resultdata.BaseResultData;
import com.legame.paysdk.network.resultdata.OnekeyRegisterResultData;
import com.legame.paysdk.network.resultdata.UserLoginResultData;
import com.legame.paysdk.network.task.NetTask.NetTaskListener;
import com.legame.paysdk.network.utils.NetTools;
import com.legame.paysdk.utils.Config;
import com.legame.paysdk.utils.DataUtils;
import com.legame.paysdk.utils.LogUtil;
import com.legame.paysdk.utils.ResourceUtil;

public class AnonymousLoginTask implements NetTaskListener {
	private static final String TAG = AnonymousLoginTask.class.getSimpleName();
	private NetTask mNetTask;
	
	private Context mContext;
	private ProgressDialog mProgressDialog;
	
	private static final int TAG_LOGIN = 1;
	private static final int TAG_FAST_REGISTER = 2;
	private UserInfo mUserInfo;
	private String mTempUserName;
	private String mTempPwd;
	
	public AnonymousLoginTask(Context context) {
		mContext = context;
		mUserInfo = new UserInfo();
	}
	
	public void execute() {
		String un = AnonymousLoginDb.getInstance().getUsername(mContext.getPackageName());
		if (!TextUtils.isEmpty(un)) {
			UserInfoDb db = UserInfoDb.getInstance();
			UserInfo info = db.getUserInfoByUsername(un);
			if (info != null) {
				if(UserInfo.getAutoLoginState(mContext)){
					if(!GlobalVal.getMetadataBoolean(mContext, "LEYO_NO_SHOW_DIALOG"))//meta-data控制不弹loading框
						showProgressDlg(mContext.getString(ResourceUtil.getString(mContext, "lgsdk_login_please_wait")));
					login(info.getUserName(), info.getPassword());
					return;
				}
				Intent intent = new Intent(mContext, LGUserLoginActivity.class);
				mContext.startActivity(intent);
			} else {
				AnonymousLoginDb.getInstance().deleteUsername(un);
				register();
			}
		} else {
//			register();
			UserInfoDb db = UserInfoDb.getInstance();
			UserInfo info = db.getLastLoginUser();
			
			if (info == null) {
				info = db.getNotLoginUserInfo();
			}
			
			if (info != null) {
				if(UserInfo.getAutoLoginState(mContext)){ 
					if(!GlobalVal.getMetadataBoolean(mContext, "LEYO_NO_SHOW_DIALOG"))//meta-data控制不弹loading框
						showProgressDlg(mContext.getString(ResourceUtil.getString(mContext, "lgsdk_login_please_wait")));
					login(info.getUserName(), info.getPassword());
					return;
				}
				Intent intent = new Intent(mContext, LGUserLoginActivity.class);
				mContext.startActivity(intent);
			} else {
				//兼容旧包，修复旧包更新新包找不到游戏ID的bug
				if(!hasOldDB()){
					register();
				}else{
					PaySdkDBHelper dbHelper = AnonymousLoginDb.getInstance().getPaySdkDBHelper();
					if(dbHelper.isNewDir()){
						dbHelper.changeDirectory();
						execute();
					}else{
						dbHelper.changeDirectory();
						register();
					}
					
				}
			}
		}
	}
	
	private boolean hasOldDB(){
		File file = new File(Config.OLD_DB_DIR,"PaySDK");
		return file.exists();
	}
	
	private void login(String userName, String pwd) {
		mTempUserName = userName;
		mTempPwd = pwd;
		UserLoginNetEngine engine = new UserLoginNetEngine(userName, pwd, null);
		mNetTask = new NetTask(mContext, engine, TAG_LOGIN);
		mNetTask.setListener(this);
		new Thread(mNetTask).start();
	}
	
	private void register() {
		OnekeyRegisterNetEngine engine = new OnekeyRegisterNetEngine(DataUtils.getDeviceID(mContext));
		mNetTask = new NetTask(mContext, engine, TAG_FAST_REGISTER);
		mNetTask.setListener(this);
		new Thread(mNetTask).start();
	}
	
	private void showProgressDlg(String msg) {
		mProgressDialog = new ProgressDialog(mContext);
		mProgressDialog.setCancelable(false);
		mProgressDialog.setCanceledOnTouchOutside(false);
		mProgressDialog.setMessage(msg);
		mProgressDialog.show();
	}
	
	private void closeProgressDlg() {
		if (mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
		}
	}
	
	private  void sendCallback(final int errorCode , final String data){
		closeProgressDlg();
		if (errorCode == ErrorCode.ERROR_SUCCESS) {
			GlobalVal.sIsLogin = true;
		}else {
			GlobalVal.sIsLogin = false;
		}
		GlobalVal.sHandler.post(new Runnable() {
			@Override
			public void run() {
				ListenerHolder.sLoginListener.onGameCallback(errorCode, data);
			}
		});
	}

	@Override
	public void onTaskRunSuccessful(int tag, BaseNetEngine engine) {
		BaseResultData resultData = engine.getResultData();
		int errorCode = resultData.getErrorCode();
		
		if(errorCode != 0){
			String errorTip = resultData.getErrorTip();
			int status = ErrorCode.ERROR_FAIL;
			
			if (TextUtils.isEmpty(errorTip)) {
				if (resultData instanceof OnekeyRegisterResultData) {
					errorTip = mContext.getString(ResourceUtil.getString(mContext, "lgsdk_register_failed_please_retry"));
				} else {
					errorTip = mContext.getString(ResourceUtil.getString(mContext, "lgsdk_login_failed_please_retry"));
				}
			}
			
			Toast.makeText(mContext, errorTip,Toast.LENGTH_SHORT).show();
			sendCallback(status, errorTip);
		} else {
			if (tag == TAG_LOGIN) {
				UserInfo info = ((UserLoginResultData)resultData).getUserInfo();
				if (TextUtils.isEmpty(info.getSid())) {
					Toast.makeText(mContext, ResourceUtil.getString(mContext, "lgsdk_login_failed_please_retry"),
							Toast.LENGTH_SHORT).show();
					LogUtil.e(TAG, "get sid failed.....");
					sendCallback(ErrorCode.ERROR_FAIL, null);
				} else {
					NetTools.saveSid(mContext, info.getSid());
					mUserInfo.setUserName(mTempUserName);
					mUserInfo.setPassword(mTempPwd);
					mUserInfo.setSid(info.getSid());
					mUserInfo.setValidTime(info.getValidTime());
					mUserInfo.setBindPhone(info.isBindPhone());
					mUserInfo.setNickName(info.getNickName());
					UserInfoDb.getInstance().saveOrUpdateUser(mUserInfo, mContext.getPackageName());
					long lastTime = System.currentTimeMillis();
					UserInfoDb.getInstance().updateUserLastLoginTime(mUserInfo.getUserName(), lastTime, mContext.getPackageName());
					UserInfo.updateCurrentLoginUserName(mContext, mUserInfo.getUserName());
					UserInfoDb.getInstance().updateLoginNumByUsername(mUserInfo.getUserName());
					AnonymousLoginDb.getInstance().save(mUserInfo.getUserName(), mContext.getPackageName(), lastTime);
					GlobalVal.sIsLogin = true;
					GlobalVal.sIsAnonymous = true;
					sendCallback(ErrorCode.ERROR_SUCCESS, mUserInfo.getSid());
				}
			} else if (TAG_FAST_REGISTER == tag){
				UserInfo info = ((OnekeyRegisterResultData)resultData).getUserInfo();
				UserInfoDb.getInstance().saveOrUpdateUser(info, mContext.getPackageName());
				login(info.getUserName(), info.getPassword());
			}
		}
		
	}

	@Override
	public void onTaskRunError(int tag) {
		closeProgressDlg();
		showNetWorkErrorDialog();
	}

	private void showNetWorkErrorDialog(){
		final TextDialog dialog = new TextDialog(mContext);
		dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		dialog.showTitle("错误")
		.setContent("网络错误，登录失败。")
		.showButton1("重试", new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				execute();
			}
		})
		.showButton2("关闭",	new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.cancel();
//				System.exit(0);
				Process.killProcess(Process.myPid());
			}
		});
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
	}
	
	@Override
	public void onTaskRunCanceled(int tag) {
		// TODO Auto-generated method stub
		
	}
}
