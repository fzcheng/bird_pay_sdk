package com.legame.paysdk.network.task;

import android.content.Context;
import android.os.Handler;

import com.legame.paysdk.ErrorCode;
import com.legame.paysdk.network.engine.BaseNetEngine;
import com.legame.paysdk.network.utils.NetTools;
import com.legame.paysdk.utils.LogUtil;

/** 
 * 类说明：   
 * @author  Terry.Lu
 * @date    2013-5-15
 * @version 1.0
 */
public class NetTask implements Runnable {
	private static final String TAG = "NetTask";

	private BaseNetEngine mEngine = null;
	private int mTag = 0;
	private Handler mHandler;
	private Context mContext = null;
	private int mStatus = -1; // 0 means ok, -1 means error
	private NetTaskListener mListener = null;
	private boolean mIsCancel = false;
	
	private boolean mIsTaskRunning = false;
	private SidUpdateTask mUpdateSidTask;

	public NetTask(Context c, BaseNetEngine engine, int tag) {
		mHandler = new Handler(c.getMainLooper());
		mContext = c;
		mEngine = engine;
		mTag = tag;
	}
	
	public void setListener(NetTaskListener l){
		mListener = l;
	}
	
	public void cancel(){
		mIsCancel = true;
		mEngine.cancelGetNetData();
		
		if (mUpdateSidTask != null) {
			mUpdateSidTask.cancel();
		}
	}
	
	public boolean isTaskRunning(){
		return mIsTaskRunning;
	}

	@Override
	public void run() {
		mIsTaskRunning = true;
		mIsCancel = false;
		int error = mEngine.getNetData(mContext);
		
		switch (error) {
		case ErrorCode.ERROR_SUCCESS:
			mStatus = 0;
			break;
		case ErrorCode.ERROR_FAIL:
			mStatus = -1;
			break;
			
		case ErrorCode.ERROR_SID_EXPIRED:
			mUpdateSidTask = SidUpdateTask.getInstance();
			mStatus = 0;
			
			if (!mUpdateSidTask.doUpdateSid(mContext)) {
				LogUtil.e(TAG, "udpate sid failed..");
				mUpdateSidTask = null;
				mHandler.post(mCallbackRunnable);
				return;
			}
			
			mUpdateSidTask = null;
			mEngine.onSidRefreshed(NetTools.getSid(mContext));
			error = mEngine.getNetData(mContext);
			break;
		default:
			break;
		}

		LogUtil.i(TAG, "engine work doing over..");
		mHandler.post(mCallbackRunnable);
	}

	private Runnable mCallbackRunnable = new Runnable() {

		@Override
		public void run() {
			mIsTaskRunning = false;
			if(mListener == null){
				return;
			}
			
			if(mIsCancel){
				mListener.onTaskRunCanceled(mTag);
				return;
			}
			
			if(mStatus == 0){
				mListener.onTaskRunSuccessful(mTag, mEngine);
			}else if(mStatus == -1){
				mListener.onTaskRunError(mTag);
			}

		}
	};

	public interface NetTaskListener {

		public void onTaskRunSuccessful(int tag, BaseNetEngine engine);
		public void onTaskRunError(int tag);
		public void onTaskRunCanceled(int tag);
	}
}
