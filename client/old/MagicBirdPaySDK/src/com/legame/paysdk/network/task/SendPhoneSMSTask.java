package com.legame.paysdk.network.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.legame.paysdk.ErrorCode;
import com.legame.paysdk.ListenerHolder;
import com.legame.paysdk.network.engine.BaseNetEngine;
import com.legame.paysdk.network.engine.BindPhoneSMSNetEngine;
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
public class SendPhoneSMSTask implements NetTaskListener {
	private static final String TAG = SendPhoneSMSTask.class.getSimpleName();
	private NetTask mNetTask;
	private Context mContext;
	private ProgressDialog mProgressDialog;
	private String mPhoneNum;
	
	public SendPhoneSMSTask(Context context) {
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
	
	public void execute(String phoneNum) {
		showProgressDlg("请稍候...");
		if (TextUtils.equals(phoneNum.trim(), "") || phoneNum.length() != 11) {
			closeProgressDlg();
			Toast.makeText(mContext, ResourceUtil.getString(mContext, "lgsdk_bind_phone_error_num_toast"),
					Toast.LENGTH_SHORT).show();
			return;
		}
		// set the phone number here
		mPhoneNum = phoneNum;

		mNetTask = new NetTask(mContext, new BindPhoneSMSNetEngine(
				NetTools.getSid(mContext), mPhoneNum), 0);
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
			LogUtil.d(TAG, "send sms phone fail");
			String tip = resultData.getErrorTip();
			
			if (TextUtils.isEmpty(tip)) {
				tip = "发送绑定电话号码短信失败！";
			}
			String msg = engine.getResultData().getErrorTip();
			Log.d(TAG, "error msg : " + msg);
			
		
			Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();

			ListenerHolder.sBindPhoneSMSListener.onGameCallback(ErrorCode.ERROR_SUCCESS, msg);
		} else {
//			Log.i(TAG,"请求绑定电话号码短信成功");
			Toast.makeText(mContext,
					ResourceUtil.getString(mContext, "lgsdk_bind_phone_success_toast"),
					Toast.LENGTH_SHORT).show();
			ListenerHolder.sBindPhoneSMSListener.onGameCallback(ErrorCode.ERROR_SUCCESS, mPhoneNum);
			mPhoneNum = null;
		}
	}

	@Override
	public void onTaskRunError(int tag) {
		closeProgressDlg();
		ListenerHolder.sBindPhoneSMSListener.onGameCallback(ErrorCode.ERROR_SUCCESS,"绑定电话号码失败！");
	}

	@Override
	public void onTaskRunCanceled(int tag) {
		closeProgressDlg();	
	}
}
