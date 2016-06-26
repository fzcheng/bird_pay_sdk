package com.legame.paysdk.network.task;

import java.util.concurrent.Semaphore;

import android.content.Context;

import com.legame.paysdk.ErrorCode;
import com.legame.paysdk.models.UserInfo;
import com.legame.paysdk.network.engine.SidUpdateNetEngine;
import com.legame.paysdk.network.resultdata.SidUpdateResultData;
import com.legame.paysdk.network.utils.NetTools;
import com.legame.paysdk.utils.LogUtil;

/** 
 * 类说明：   
 * @author  Terry.Lu
 * @date    2013年10月14日
 * @version 1.0
 */
public class SidUpdateTask {
	private static final String TAG = SidUpdateTask.class.getSimpleName();
	private SidUpdateNetEngine mNetEngine;
	private Semaphore mSemaphore = new Semaphore(1);
	
	private static class SidUpdateTaskInstance {
		private static SidUpdateTask sInstance = new SidUpdateTask();  
	}
	
	private SidUpdateTask() {}
	
	public static SidUpdateTask getInstance() {
		return SidUpdateTaskInstance.sInstance;
	}
	
	public void cancel(){
		if(mNetEngine != null){
			mNetEngine.cancelGetNetData();
		}
	}
	
	public boolean doUpdateSid(Context ctx) {
		LogUtil.e(TAG, Thread.currentThread().getName() + ":update sid..");
		NetTools.setSidExpired(ctx, true);
		
		try {
			mSemaphore.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if (NetTools.isUpdatedSid(ctx) && !NetTools.isSidExpired(ctx)) {
			mSemaphore.release();
			LogUtil.e(TAG, Thread.currentThread().getName() + ":no need to update sid..");
			return true;
		}
		
		NetTools.setUpdatedSid(ctx, false);
		String sid = NetTools.getSid(ctx);
		mNetEngine = new SidUpdateNetEngine(sid);
		boolean res = false;
		LogUtil.e(TAG, Thread.currentThread().getName() + ":start to udpate sid..");
		
		int retry = 2;
		int error = ErrorCode.ERROR_FAIL;
		
		while (retry > 0) {
			if ((error = mNetEngine.getNetData(ctx)) == ErrorCode.ERROR_SUCCESS) {
				break;
			} 
			
			--retry;
		}
		
		res = error == ErrorCode.ERROR_SUCCESS ? true : false;
		
		if (res) {
			SidUpdateResultData resData = (SidUpdateResultData)mNetEngine.getResultData();
			UserInfo user = resData.getUserInfo();
			
			if (user != null) {
				NetTools.saveSid(ctx, user.getSid());
				NetTools.setUpdatedSid(ctx, true);
				NetTools.setSidExpired(ctx, false);
			}
			
		}
		
		mSemaphore.release();
		
		return res;
	}
}
