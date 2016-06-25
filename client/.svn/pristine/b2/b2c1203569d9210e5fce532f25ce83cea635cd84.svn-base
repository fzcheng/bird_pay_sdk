package com.legame.paysdk.network.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.legame.paysdk.ErrorCode;
import com.legame.paysdk.ListenerHolder;
import com.legame.paysdk.db.UserInfoDb;
import com.legame.paysdk.dialog.TextDialog;
import com.legame.paysdk.models.UserInfo;
import com.legame.paysdk.network.engine.BaseNetEngine;
import com.legame.paysdk.network.engine.BindPhoneNetEngine;
import com.legame.paysdk.network.resultdata.SimpleResultData;
import com.legame.paysdk.network.task.NetTask.NetTaskListener;
import com.legame.paysdk.network.utils.NetTools;
import com.legame.paysdk.utils.LogUtil;
import com.legame.paysdk.utils.ResourceUtil;

/** 
 * 类说明：   
 * @author  xiaodm
 * @date    2015年02月09日
 * @version 1.0
 */
public class BindPhoneTask implements NetTaskListener {
	private static final String TAG = BindPhoneTask.class.getSimpleName();
	private NetTask mNetTask;
	private Context mContext;
	private ProgressDialog mProgressDialog;
	private UserInfo mUserInfo;
	private String mVerifyCode;
	
	public BindPhoneTask(Context context) {
		mContext = context;
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			mUserInfo = UserInfoDb.getInstance().getUserInfoBySid(
					NetTools.getSid(mContext));
		}
	}
	
	private void showProgressDlg(String msg) {
		mProgressDialog = new ProgressDialog(mContext);
		mProgressDialog.setCancelable(false);
		mProgressDialog.setMessage(msg);
		mProgressDialog.show();
	}
	
	private void closeProgressDlg() {
		if (mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
		}
	}
	
	public void execute(String verifyCode, boolean isContinue) {
		showProgressDlg("请稍候...");
		if (TextUtils.equals(verifyCode, "")) {
			closeProgressDlg();
			Toast.makeText(mContext,
					ResourceUtil.getString(mContext, "lgsdk_verify_binding_no_code_toast"),
					Toast.LENGTH_SHORT).show();
			return;
		}
		mVerifyCode = verifyCode;
		BindPhoneNetEngine engine = new BindPhoneNetEngine(NetTools.getSid(mContext), 
				mVerifyCode);
		engine.setContinue(isContinue);
		mNetTask = new NetTask(mContext, engine, 0);
		mNetTask.setListener(this);
		new Thread(mNetTask).start();
	}
	
	public void cancel() {
		if (mNetTask != null && mNetTask.isTaskRunning()) {
			mNetTask.cancel();
		}
	}
	
	@Override
	public void onTaskRunSuccessful(int tag, BaseNetEngine engine) {
		closeProgressDlg();
		SimpleResultData resultData = (SimpleResultData) engine.getResultData();
		
		if (resultData.getErrorCode() != 0) {
			LogUtil.d(TAG, "bind phone fail");
			String tip = resultData.getErrorTip();
			
			if (TextUtils.isEmpty(tip)) {
				tip = "绑定电话号码失败！";
			}
			String msg = engine.getResultData().getErrorTip();
			Log.d(TAG, "error msg : " + msg);
			
			if (resultData.getErrorCode() == 101 || resultData.getErrorCode() == 102 || resultData.getErrorCode() == 103) {
				final TextDialog dialog = new TextDialog(mContext);
				dialog.showTitle("警告")
				.setContent(msg)
				.showButton1("否", new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				})
				.showButton2("是",	new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						dialog.dismiss();
						execute(mVerifyCode,true);
					}
				});
				
				dialog.setCanceledOnTouchOutside(false);
				dialog.show();
			} else {
				Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
			}

			ListenerHolder.sBindPhoneListener.onGameCallback(ErrorCode.ERROR_SUCCESS, tip);
		} else {
//			Log.i(TAG,"绑定电话号码成功");
			if(mUserInfo != null){
				UserInfoDb.getInstance().updateUserBindPhoneState(
						mUserInfo.getUserName(), true, mVerifyCode);
				mUserInfo.setPhoneNum(mVerifyCode);
				mUserInfo.setBindPhone(true);
			}
			Toast.makeText(mContext,
					ResourceUtil.getString(mContext, "lgsdk_bind_phone_success_toast"),
					Toast.LENGTH_SHORT).show();
			ListenerHolder.sBindPhoneListener.onGameCallback(ErrorCode.ERROR_SUCCESS, "绑定成功");
			mVerifyCode = null;
		}
	}

	@Override
	public void onTaskRunError(int tag) {
		closeProgressDlg();
		ListenerHolder.sBindPhoneListener.onGameCallback(ErrorCode.ERROR_SUCCESS,"绑定电话号码失败！");
	}

	@Override
	public void onTaskRunCanceled(int tag) {
		closeProgressDlg();	
	}
}
