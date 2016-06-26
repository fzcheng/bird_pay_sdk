package com.legame.paysdk.network.task;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;

import com.legame.paysdk.ErrorCode;
import com.legame.paysdk.ListenerHolder;
import com.legame.paysdk.network.engine.BaseNetEngine;
import com.legame.paysdk.network.engine.OutUrlInfoNetEngine;
import com.legame.paysdk.network.resultdata.OutUrlInfoResultData;
import com.legame.paysdk.network.task.NetTask.NetTaskListener;
import com.legame.paysdk.utils.LogUtil;

/** 
 * 类说明：   
 * @author  Terry.Lu
 * @date    2013年10月25日
 * @version 1.0
 */
public class ListUrlTask implements NetTaskListener {
	private static final String TAG = ListUrlTask.class.getSimpleName();
	private NetTask mNetTask;
	private Context mContext;
	private ProgressDialog mProgressDialog;
	
	public ListUrlTask(Context context) {
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
		showProgressDlg("正在获取，请稍候...");
//		Log.i(TAG,"请求获取CP方域名列表");
		OutUrlInfoNetEngine engine = new OutUrlInfoNetEngine();
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
		OutUrlInfoResultData result = (OutUrlInfoResultData) engine.getResultData();
		if (result.getErrorCode() != 0) {
			LogUtil.d("LGPaySdk", "联网失败");
			ListenerHolder.sListURLListener.onGameCallback(ErrorCode.ERROR_FAIL, "联网失败");
			return;
		}
		String url= result.getOutUrlInfo().getDomain();
		if (TextUtils.isEmpty(url)) {
			LogUtil.d("LGPaySdk", "url为空");
			ListenerHolder.sListURLListener.onGameCallback(ErrorCode.ERROR_FAIL, "url为空");
			return;
		}
		List<String> list = new ArrayList<String>();
//		Log.i(TAG,"联网获取回来的url列表大小："+result.getOutListUrlInfo().uInfo.size());
		for(int i = 0; i < result.getOutListUrlInfo().uInfo.size();++i){
			list.add(result.getOutListUrlInfo().uInfo.get(i).getDomain());
//			Log.i(TAG,"add的链接："+result.getOutListUrlInfo().uInfo.get(i).getDomain());
		}
//		Log.i(TAG,"list的大小："+list.size());
//		urlList = list;
		ListenerHolder.sListURLListener.onGameCallback2(ErrorCode.ERROR_SUCCESS, list);
		
	}

	@Override
	public void onTaskRunError(int tag) {
		closeProgressDlg();
//		Log.i(TAG,"请求失败");
		ListenerHolder.sListURLListener.onGameCallback(ErrorCode.ERROR_FAIL, "获取url列表失败");	
	}

	@Override
	public void onTaskRunCanceled(int tag) {
		closeProgressDlg();
//		Log.i(TAG,"取消请求");
		ListenerHolder.sListURLListener.onGameCallback(ErrorCode.ERROR_FAIL, "取消获取url列表");
	}
}
