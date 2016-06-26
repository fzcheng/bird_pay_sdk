package com.legame.paysdk.network.task;

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
import com.legame.paysdk.network.engine.ChangeNickNameNetEngine;
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
public class ChangeNickNameTask implements NetTaskListener {
	private static final String TAG = ChangeNickNameTask.class.getSimpleName();
	private NetTask mNetTask;
	private Context mContext;
	private ProgressDialog mProgressDialog;
	private String mNewNickName;
	private UserInfo mUserInfo;
	
	public ChangeNickNameTask(Context context) {
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
	
	public void execute(String newNickName) {
		showProgressDlg("请稍候...");
		if (TextUtils.equals(newNickName.trim(), "")) {
			closeProgressDlg();
			Toast.makeText(mContext,
					ResourceUtil.getString(mContext, "lgsdk_user_center_nickname_hint"),
					Toast.LENGTH_SHORT).show();
			return;
		}
		mNewNickName = newNickName;
		ChangeNickNameNetEngine engine = new ChangeNickNameNetEngine(NetTools
				.getSid(mContext), newNickName);
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
			LogUtil.d(TAG, "change nickname fail");
			String tip = resultData.getErrorTip();
			
			if (TextUtils.isEmpty(tip)) {
				tip = "修改呢称失败！";
			}
			Toast.makeText(mContext, engine.getResultData().getErrorTip(),
					Toast.LENGTH_SHORT).show();
			ListenerHolder.sChangeNickNameListener.onGameCallback(ErrorCode.ERROR_SUCCESS, tip);

		} else {
//			Log.i(TAG,"修改呢称成功");
			if(mUserInfo != null){
				UserInfoDb.getInstance().updateUserNickname(
						mUserInfo.getUserName(), mNewNickName);
				mUserInfo = UserInfoDb.getInstance().getUserInfoBySid(
						NetTools.getSid(mContext));// refresh
			}
			Toast.makeText(mContext,
					ResourceUtil.getString(mContext, "lgsdk_change_nickname_success_toast"),
					Toast.LENGTH_SHORT).show();
			ListenerHolder.sChangeNickNameListener.onGameCallback(ErrorCode.ERROR_SUCCESS, mNewNickName);
			mNewNickName = null;
		}
	}

	@Override
	public void onTaskRunError(int tag) {
		closeProgressDlg();
		ListenerHolder.sChangeNickNameListener.onGameCallback(ErrorCode.ERROR_SUCCESS,"修改呢称失败！");
	}

	@Override
	public void onTaskRunCanceled(int tag) {
		closeProgressDlg();	
	}
}
