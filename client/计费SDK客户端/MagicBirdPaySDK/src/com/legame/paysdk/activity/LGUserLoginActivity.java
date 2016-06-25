package com.legame.paysdk.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.legame.paysdk.ErrorCode;
import com.legame.paysdk.GlobalVal;
import com.legame.paysdk.ListenerHolder;
import com.legame.paysdk.db.UserInfoDb;
import com.legame.paysdk.dialog.RegisterDialog;
import com.legame.paysdk.dialog.RegisterDialog.RegisterDialogListener;
import com.legame.paysdk.dialog.UserLoginDialog;
import com.legame.paysdk.dialog.UserLoginDialog.UserLoginDialogListener;
import com.legame.paysdk.download.FileDownloadService;
import com.legame.paysdk.models.Bulletin;
import com.legame.paysdk.models.LGGameInfo;
import com.legame.paysdk.models.PushModel;
import com.legame.paysdk.models.UserInfo;
import com.legame.paysdk.network.engine.BaseNetEngine;
import com.legame.paysdk.network.engine.OnekeyRegisterNetEngine;
import com.legame.paysdk.network.engine.UserLoginNetEngine;
import com.legame.paysdk.network.resultdata.OnekeyRegisterResultData;
import com.legame.paysdk.network.resultdata.UserLoginResultData;
import com.legame.paysdk.network.task.NetTask;
import com.legame.paysdk.network.task.NetTask.NetTaskListener;
import com.legame.paysdk.network.utils.NetTools;
import com.legame.paysdk.utils.DataUtils;
import com.legame.paysdk.utils.LogUtil;
import com.legame.paysdk.utils.ResourceUtil;


/** 
 * 类说明：   
 * @author  xinhui.cheng
 * @date    2013-10-16
 * @version 1.0
 */
public class LGUserLoginActivity extends Activity implements NetTaskListener,UserLoginDialogListener,RegisterDialogListener{
	
	private static final String TAG = LGUserLoginActivity.class.getSimpleName();
	
	private static final int TAG_DEFAULT = 1;
	private static final int TAG_LOGIN = 2;
	private static final int TAG_ONEKEYLOGIN = 3;
	private static final int TAG_REGISTER = 4;
	
	private static final int REQUEST_CODE_RETRIVE_ACCOUNT = 1;
	private static final int REQUEST_CODE_BIND_PHONE = 2;

	private UserLoginDialog mLoginDialog;
	private RegisterDialog mRegisterDialog;
	
	private UserInfo mUserInfo = new UserInfo();
	private String mTempUserName;
	private String mTempPwd;
	private NetTask mNetTask;
	private ProgressDialog mLoginProgressDialog;
	private ProgressDialog mRegisterProgressDialog;
	
	private WindowManager mWindowManager;
	private WindowManager.LayoutParams mWindowLayoutParams;
	private View mBulletinView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		if(!GlobalVal.sInitFinished){
			throw new IllegalAccessError("have not init");
		}
		switch (GlobalVal.getOrientation(this)) {
		case ORIENTATION_LANDSCAPE:
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
			break;
		case ORIENTATION_PORTRAIT:
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			break;
		}
		
		String currentUserName = UserInfo.getCurrentLoginUserName(this);
		
		if (!TextUtils.isEmpty(currentUserName)) {
			mUserInfo = UserInfoDb.getInstance().getUserInfoByUsername(currentUserName);	
		}
		
		if(UserInfo.getAutoLoginState(this) && !TextUtils.isEmpty(currentUserName) && mUserInfo != null){
			login(mUserInfo.getUserName(), mUserInfo.getPassword(), null , TAG_DEFAULT);
		} else {
			mLoginDialog = new UserLoginDialog(this);
			mLoginDialog.setUserLoginDialogListener(this);
			mLoginDialog.show();
			createBulletinView();
		}
		
		if (mUserInfo == null) {
			mUserInfo = new UserInfo();
		}

	}
	
	@Override
	public void onBackPressed() {
		System.out.println("窗体对话框->用户按下返回键");
	}
	
	private void showLoginProgressDialog(){
		if(mLoginProgressDialog == null){
			mLoginProgressDialog = new ProgressDialog(this);
			mLoginProgressDialog.setMessage(getString(ResourceUtil.getString(getApplicationContext(), "lgsdk_login_please_wait")));
			mLoginProgressDialog.setCanceledOnTouchOutside(false);
			mLoginProgressDialog.setCancelable(false);
		}
		mLoginProgressDialog.show();
	}
	
	private void showRegisterProgressDialog(){
		if(mRegisterProgressDialog == null){
			mRegisterProgressDialog = new ProgressDialog(this);
			mRegisterProgressDialog.setMessage(getString(ResourceUtil.getString(getApplicationContext(), "lgsdk_register_please_wait")));
			mRegisterProgressDialog.setCancelable(false);
			mRegisterProgressDialog.setCanceledOnTouchOutside(false);
		}
		mRegisterProgressDialog.show();
	}
	
	private void login(String userName, String pwd , String newPwd,int tag){
		showLoginProgressDialog();
		mTempUserName = userName;
		if(!TextUtils.isEmpty(newPwd)){
			mTempPwd = newPwd;
		} else {
			mTempPwd = pwd;
		}
		UserLoginNetEngine engine = new UserLoginNetEngine(userName, pwd, newPwd);
		mNetTask = new NetTask(this, engine, tag);
		mNetTask.setListener(this);
		new Thread(mNetTask).start();
	}

	private void register(){
		showRegisterProgressDialog();
		OnekeyRegisterNetEngine engine = new OnekeyRegisterNetEngine(DataUtils.getDeviceID(this));
		mNetTask = new NetTask(this, engine, TAG_REGISTER);
		mNetTask.setListener(this);
		new Thread(mNetTask).start();
	}
	
	private void closeAndCallBack(int errorCode , String data){
		if(mRegisterDialog != null && mRegisterDialog.isShowing()){
			mRegisterDialog.dismiss();
			mRegisterDialog = null;
		}
		if(mLoginDialog != null && mLoginDialog.isShowing()){
			mLoginDialog.dismiss();
			mLoginDialog = null;
		}
		
		finish();
		
		if(ListenerHolder.sLoginListener != null){
			ListenerHolder.sLoginListener.onGameCallback(errorCode, data);
		}
	}
	
	/**
	 * 自动登陆失败，弹出出登陆框，让用户登陆
	 */
	private void processAutoLoginError()
	{
		mLoginDialog = new UserLoginDialog(this);
		mLoginDialog.setUserLoginDialogListener(this);
		mLoginDialog.show();
	}
	
	@Override
	public void onTaskRunSuccessful(int tag, BaseNetEngine engine) {
		LogUtil.i(TAG, "onTaskRunSuccessful");
		if(tag == TAG_REGISTER){
			if (mRegisterProgressDialog != null && mRegisterProgressDialog.isShowing()) {
				mRegisterProgressDialog.dismiss();
				mRegisterProgressDialog = null;
			}
			OnekeyRegisterResultData resultData = (OnekeyRegisterResultData) engine.getResultData();
			if(resultData.getErrorCode() != 0){
				String errorTip = resultData.getErrorTip();
				if (TextUtils.isEmpty(errorTip)) {
					Toast.makeText(this, ResourceUtil.getString(getApplicationContext(), "lgsdk_register_failed_please_retry"),
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(this, errorTip, Toast.LENGTH_SHORT).show();
				}
			} else {
				UserInfo info = resultData.getUserInfo();
				UserInfoDb.getInstance().saveOrUpdateUser(info, getPackageName());
				mRegisterDialog = new RegisterDialog(this, info.getUserName(), info.getPassword() ,RegisterDialog.PAGE_TYPE_DEFAULT);
				mRegisterDialog.setRegisterDialogListener(this);
				mRegisterDialog.show();
				mLoginDialog.updateUserList();
				createBulletinView();
			}
			
			return;
		}
		
		UserLoginResultData resultData = (UserLoginResultData) engine
				.getResultData();
		if (resultData.getErrorCode() != 0) {
			String errorTip = resultData.getErrorTip();
			if (TextUtils.isEmpty(errorTip)) {
				Toast.makeText(this, ResourceUtil.getString(getApplicationContext(), "lgsdk_login_failed_please_retry"),
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, errorTip, Toast.LENGTH_SHORT).show();
			}
			
			if(tag == TAG_DEFAULT)
			{
				processAutoLoginError();
			}

		} else {
			UserInfo info = resultData.getUserInfo();
			if (TextUtils.isEmpty(info.getSid())) {
				Toast.makeText(this, ResourceUtil.getString(getApplicationContext(), "lgsdk_login_failed_please_retry"),
						Toast.LENGTH_SHORT).show();
				LogUtil.e(TAG, "get sid failed.....");
			
				if(tag == TAG_DEFAULT)
				{
					processAutoLoginError();
				}
			} else {
				GlobalVal.sIsAnonymous = false;
				NetTools.saveSid(this, info.getSid());
				mUserInfo.setUserName(mTempUserName);
				mUserInfo.setPassword(mTempPwd);
				mUserInfo.setSid(info.getSid());
				mUserInfo.setValidTime(info.getValidTime());
				mUserInfo.setBindPhone(info.isBindPhone());
				mUserInfo.setNickName(info.getNickName());
				UserInfoDb.getInstance().saveOrUpdateUser(mUserInfo, getPackageName());
				UserInfoDb.getInstance().updateUserLastLoginTime(mUserInfo.getUserName()
						, System.currentTimeMillis(), getPackageName());
				UserInfo.updateCurrentLoginUserName(this, mUserInfo.getUserName());
				UserInfoDb.getInstance().updateLoginNumByUsername(mUserInfo.getUserName());
				GlobalVal.sIsLogin = true;
				if(tag == TAG_DEFAULT || tag == TAG_LOGIN){
					if(tag == TAG_LOGIN){
						UserInfo.updateAutoLoginState(this, mLoginDialog.getAutoLoginState());
					}
					int loginNum = UserInfoDb.getInstance().getLoginNumByUsername(mUserInfo.getUserName());
					if(!mUserInfo.isBindPhone()){
						if(loginNum%3 == 0 ){
//							LGUserCenterActivity.jumptoSecuritySetting(this, REQUEST_CODE_BIND_PHONE);
							closeAndCallBack(ErrorCode.ERROR_SUCCESS, mUserInfo.getSid());
						} else {
							closeAndCallBack(ErrorCode.ERROR_SUCCESS, mUserInfo.getSid());
						}
					} else {
						closeAndCallBack(ErrorCode.ERROR_SUCCESS, mUserInfo.getSid());
					}
					
				} else if(tag == TAG_ONEKEYLOGIN){
					UserInfoDb.getInstance().updateUserPassword(mUserInfo.getUserName(), mUserInfo.getPassword());
					closeAndCallBack(ErrorCode.ERROR_SUCCESS, mUserInfo.getSid());
				}
			}
		}

		if (mLoginProgressDialog != null && mLoginProgressDialog.isShowing()) {
			mLoginProgressDialog.dismiss();
			mLoginProgressDialog = null;
		}

	}

	@Override
	public void onTaskRunError(int tag) {
		LogUtil.i(TAG, "onTaskRunError");
		if(tag == TAG_REGISTER){
			Toast.makeText(this, ResourceUtil.getString(getApplicationContext(), "lgsdk_register_failed_please_retry"), Toast.LENGTH_SHORT).show();
			if (mRegisterProgressDialog != null && mRegisterProgressDialog.isShowing()) {
				mRegisterProgressDialog.dismiss();
				mRegisterProgressDialog = null;
			}
			return;
		}
		Toast.makeText(this, ResourceUtil.getString(getApplicationContext(), "lgsdk_login_failed_please_retry"),
				Toast.LENGTH_SHORT).show();
		if (mLoginProgressDialog != null && mLoginProgressDialog.isShowing()) {
			mLoginProgressDialog.dismiss();
			mLoginProgressDialog = null;
		}
		
		if(tag == TAG_DEFAULT)
		{
			processAutoLoginError();
		}
	}

	@Override
	public void onTaskRunCanceled(int tag) {
	}

	
	@Override
	public void onOneKeyRegister() {
		register();
	}

	@Override
	public void onLogin(String userName, String password) {
		login(userName, password, null, TAG_LOGIN);
	}
	
	@Override
	public void onRetriveAccount() {
		Intent intent = new Intent(this,LGRetriveAccountActivity.class);
		startActivityForResult(intent, REQUEST_CODE_RETRIVE_ACCOUNT);
	}

	@Override
	public void onRegisterLogin(String userName, String pwd, String newPwd) {
		login(userName, pwd, newPwd, TAG_ONEKEYLOGIN);
	}

	@Override
	public void onChangeAccount(String userName, String pwd) {
		mLoginDialog.setAccountInfo(userName, pwd);
//		mWindowManager.removeView(mBulletinView);
//		mWindowManager.addView(mBulletinView, mWindowLayoutParams);
	}
	
	@Override
	public void onClickServiceTerms() {
		Intent intent = new Intent(this,LGServiceTermsActivity.class);
		startActivity(intent);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		LogUtil.i(TAG, "resultCode:"+resultCode);
		if(requestCode == REQUEST_CODE_BIND_PHONE){
			closeAndCallBack(ErrorCode.ERROR_SUCCESS, mUserInfo.getSid());
		} else if(requestCode == REQUEST_CODE_RETRIVE_ACCOUNT){
			if(resultCode == RESULT_OK){
				String userName = data.getStringExtra("username");
				String password = data.getStringExtra("password");
				mRegisterDialog = new RegisterDialog(this, userName, password , RegisterDialog.PAGE_TYPE_RETRIVE_ACCOUNT);
				mRegisterDialog.setRegisterDialogListener(this);
				mRegisterDialog.show();
			}
		}
	}
	
	private void createBulletinView() {
		if (mBulletinView != null) {
			mWindowManager.removeView(mBulletinView);
			mBulletinView = null;
		}
		
		final Bulletin bulletin = DataUtils.getBulletin(this);
		String content = bulletin.getContent();
		
		if (!TextUtils.isEmpty(content)) {
			mBulletinView = View.inflate(this, ResourceUtil.getLayout(this, "lgsdk_bulletin_layout"), null);
			TextView textView = (TextView) mBulletinView.findViewById(ResourceUtil.getId(this, "lgsdk_bulletin_layout_content"));
			textView.setText(bulletin.getContent());

			textView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					LogUtil.d(TAG, "click bulletin!!");
					switch (bulletin.getType()) {
					case Bulletin.BULLETIN_TYPE_GAME:
						startDownload(bulletin);
						break;

					case Bulletin.BULLETIN_TYPE_WEB_LINK:
						Intent i = new Intent(LGUserLoginActivity.this, LGMessageContentActivity.class);
						i.putExtra(LGMessageContentActivity.KEY_CONTENT_TYPE, PushModel.TYPE_URL_MSG);
						i.putExtra(LGMessageContentActivity.KEY_TITLE, "公告内容");
						i.putExtra(LGMessageContentActivity.KEY_CONTENT, bulletin.getUrl());
						//i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						startActivity(i);
						break;
					}
				}
			});
			mWindowManager = getWindowManager();
			mWindowLayoutParams = new WindowManager.LayoutParams();
			mWindowLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION;
			mWindowLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
					| WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
			mWindowLayoutParams.format = PixelFormat.TRANSPARENT;
			mWindowLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
			DisplayMetrics dm = new DisplayMetrics();
			mWindowManager.getDefaultDisplay().getMetrics(dm);
			float bulletinViewWidth = TypedValue.applyDimension(
					TypedValue.COMPLEX_UNIT_DIP, 312, dm);
			mWindowLayoutParams.x = (int) ((dm.widthPixels - bulletinViewWidth) / 2);
			mWindowLayoutParams.width = (int) bulletinViewWidth;
			mWindowLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
			mWindowManager.addView(mBulletinView, mWindowLayoutParams);
		}
	}

	private void startDownload(Bulletin bulletin) {
		LGGameInfo game = bulletin.getGameInfo();
		
		if (game != null) {
			Intent it = new Intent(this, FileDownloadService.class);
			it.putExtra(FileDownloadService.EXTRA_DL_URL, game.getDl_url());
			it.putExtra(FileDownloadService.EXTRA_PIC_URL, game.getPicUrl());
			it.putExtra(FileDownloadService.EXTRA_PKG_NAME, game.getPackageName());
			it.putExtra(FileDownloadService.EXTRA_GAME_NAME, game.getName());
			startService(it);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mWindowManager != null && mBulletinView != null) {
			mWindowManager.removeView(mBulletinView);
		}
	}
}
