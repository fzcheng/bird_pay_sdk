package com.legame.paysdk.network.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.widget.Toast;

import com.legame.paysdk.ErrorCode;
import com.legame.paysdk.ListenerHolder;
import com.legame.paysdk.db.UserInfoDb;
import com.legame.paysdk.models.UserInfo;
import com.legame.paysdk.network.engine.BaseNetEngine;
import com.legame.paysdk.network.engine.ModifyPwdNetEngine;
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
public class ChangePwdTask implements NetTaskListener {
	private static final String TAG = ChangePwdTask.class.getSimpleName();
	private NetTask mNetTask;
	private Context mContext;
	private ProgressDialog mProgressDialog;
	private UserInfo mUserInfo;
	private String newPasswd;
	
	public ChangePwdTask(Context context) {
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
	
	public void execute(String newPwd,String repeatNewPwd) {
		showProgressDlg("请稍候...");
		String oldPwd = "";
		if(mUserInfo != null){
			oldPwd = mUserInfo.getPassword();
//			Log.i(TAG,"oldPwd-->"+oldPwd);
			if (!TextUtils.equals(oldPwd, mUserInfo.getPassword())) {
				closeProgressDlg();
				Toast.makeText(mContext,
						ResourceUtil.getString(mContext, "lgsdk_change_pwd_oldpwd_mismatch_toast"),
						Toast.LENGTH_SHORT).show();
				return;
			}
		}
		
		Pattern pattern = Pattern.compile("^[A-Za-z0-9]{6,20}$");
		Matcher matcher = pattern.matcher(newPwd);
		boolean match = matcher.matches();
		if (!match) {
			closeProgressDlg();
			Toast.makeText(mContext, ResourceUtil.getString(mContext, "lgsdk_new_pwd_hint"),
					Toast.LENGTH_SHORT).show();
			return;
		}
		if (!TextUtils.equals(newPwd, repeatNewPwd)) {
			closeProgressDlg();
			Toast.makeText(mContext,
					ResourceUtil.getString(mContext, "lgsdk_change_pwd_newpwd_mismatch_toast"),
					Toast.LENGTH_SHORT).show();
			return;
		}
		newPasswd = newPwd;
		ModifyPwdNetEngine engine = new ModifyPwdNetEngine(NetTools
				.getSid(mContext),oldPwd, newPwd);
		mNetTask = new NetTask(mContext, engine, 0);
		mNetTask.setListener(this);
		new Thread(mNetTask).start();;
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
			LogUtil.d(TAG, "change pwd fail");
			String tip = resultData.getErrorTip();
			
			if (TextUtils.isEmpty(tip)) {
				tip = "修改密码失败！";
			}
			Toast.makeText(mContext, engine.getResultData().getErrorTip(),
					Toast.LENGTH_SHORT).show();
			ListenerHolder.sChangePWDListener.onGameCallback(ErrorCode.ERROR_SUCCESS, tip);

		} else {
//			Log.i(TAG,"修改密码成功");
			if(mUserInfo != null){
				UserInfoDb.getInstance().updateUserPassword(
						mUserInfo.getUserName(), newPasswd);
				mUserInfo = UserInfoDb.getInstance().getUserInfoBySid(
						NetTools.getSid(mContext));// refresh
			}
//			Log.i(TAG,"newPasswd-->"+newPasswd);
			Toast.makeText(mContext,
					ResourceUtil.getString(mContext, "lgsdk_change_pwd_success_toast"),
					Toast.LENGTH_SHORT).show();
			ListenerHolder.sChangePWDListener.onGameCallback(ErrorCode.ERROR_SUCCESS, "修改密码成功");
			newPasswd = null;
		}
	}

	@Override
	public void onTaskRunError(int tag) {
		closeProgressDlg();
		ListenerHolder.sChangePWDListener.onGameCallback(ErrorCode.ERROR_SUCCESS,"修改密码失败！");
	}

	@Override
	public void onTaskRunCanceled(int tag) {
		closeProgressDlg();	
	}
}
