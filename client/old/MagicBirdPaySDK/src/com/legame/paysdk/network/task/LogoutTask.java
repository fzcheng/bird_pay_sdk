package com.legame.paysdk.network.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;

import com.legame.paysdk.ErrorCode;
import com.legame.paysdk.GlobalVal;
import com.legame.paysdk.ListenerHolder;
import com.legame.paysdk.db.UserInfoDb;
import com.legame.paysdk.models.UserInfo;
import com.legame.paysdk.network.engine.BaseNetEngine;
import com.legame.paysdk.network.engine.LogoutNetEngine;
import com.legame.paysdk.network.resultdata.SimpleResultData;
import com.legame.paysdk.network.task.NetTask.NetTaskListener;
import com.legame.paysdk.network.utils.NetTools;
import com.legame.paysdk.utils.LogUtil;
import com.legame.paysdk.utils.ResourceUtil;

/** 
 * 类说明：   
 * @author  Terry.Lu
 * @date    2013年10月25日
 * @version 1.0
 */
public class LogoutTask implements NetTaskListener {
	private static final String TAG = LogoutTask.class.getSimpleName();
	private NetTask mNetTask;
	private Context mContext;
	private ProgressDialog mProgressDialog;
	
	public LogoutTask(Context context) {
		mContext = context;
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
	
	public void execute() {
		showProgressDlg("正在退出登录，请稍候...");
		String sid = NetTools.getSid(mContext);
		UserInfo user = UserInfoDb.getInstance().getUserInfoBySid(sid);
		LogoutNetEngine engine = new LogoutNetEngine(user.getUserName(), sid);
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
			LogUtil.d(TAG, "logout fail");
			String tip = resultData.getErrorTip();
			
			if (TextUtils.isEmpty(tip)) {
				tip = mContext.getString(ResourceUtil.getString(mContext, "lgsdk_logout_failed"));
			}
			
			ListenerHolder.sLogoutListener.onGameCallback(ErrorCode.ERROR_SUCCESS, tip);
		} else {
			ListenerHolder.sLogoutListener.onGameCallback(ErrorCode.ERROR_SUCCESS, mContext.getString(ResourceUtil.getString(mContext, "lgsdk_logout_success")));
			GlobalVal.sIsLogin = false;
		}
		
	}

	@Override
	public void onTaskRunError(int tag) {
		closeProgressDlg();
		ListenerHolder.sLogoutListener.onGameCallback(ErrorCode.ERROR_SUCCESS, mContext.getString(ResourceUtil.getString(mContext, "lgsdk_logout_failed")));
	}

	@Override
	public void onTaskRunCanceled(int tag) {
		closeProgressDlg();
		
	}
}
