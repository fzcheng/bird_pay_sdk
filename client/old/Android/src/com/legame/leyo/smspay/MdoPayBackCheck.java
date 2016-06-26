package com.legame.leyo.smspay;

import java.util.HashMap;
import java.util.LinkedList;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.legame.paysdk.db.MdoPayBackDb;
import com.legame.paysdk.models.Commands;
import com.legame.paysdk.models.OrderInfo;
import com.legame.paysdk.network.engine.BaseNetEngine;
import com.legame.paysdk.network.task.NetTask;
import com.legame.paysdk.network.task.NetTask.NetTaskListener;
import com.legame.paysdk.network.utils.NetTools;
import com.legame.paysdk.utils.LogUtil;

/** 
 * 类说明：   检测是否需要重新上传mdo支付结果
 * @author  Shaohui.Yang
 * @date    2014年6月10日
 * @version 1.0
 */
public class MdoPayBackCheck implements NetTaskListener{
	
	private static final String TAG = MdoPayBackCheck.class.getSimpleName();
	private Context mContext;
	private static MdoPayBackCheck mInstance;
	private HashMap<String,String> mapMdoPayBack;
	private LinkedList<HashMap<String,String>> mdoPayBackList;
	private MdoPayBackDb db;
	private NetTask mNetTask;
	
	public MdoPayBackCheck(){
		
	}
	private MdoPayBackCheck(Context context){
		mContext = context;
	}
	
	public static MdoPayBackCheck instance(Context context){
		if(mInstance == null){
			mInstance = new MdoPayBackCheck(context);
		}
		return mInstance;
	}
	
	public void UploadMdoPayBackInfo(){
		db = MdoPayBackDb.instance(mContext);
		
		if(db == null){
			Log.e(TAG, "db is null");
			return;
		}
		mdoPayBackList= db.query();
		if(mdoPayBackList == null || mdoPayBackList.size() == 0){
			return;
		}
		
		UploadMdoPayResult();	
	}
	private HashMap<String,String> mMdoPayBackInfo = null;
	private String states = "";
	public void UploadMdoPayBackInfo(Commands commands,
			OrderInfo orderInfo,String state){
		UploadMdoPayBackInfo(commands, orderInfo, state, "");
	}
	
	public void UploadMdoPayBackInfo(Commands commands, 
			OrderInfo orderInfo,String state,String sdkResultCode){
		mMdoPayBackInfo = getMdoPayBackInfo(commands,orderInfo,state,sdkResultCode);
		MdoPayBackNetEngine engine = new MdoPayBackNetEngine(NetTools.getSid(mContext), mMdoPayBackInfo);
		mNetTask = new NetTask(mContext, engine, 0);
//		Log.i(TAG,"回调给服务器");
		mNetTask.setListener(this);
		new Thread(mNetTask).start();
	}
	
	public void UploadMdoPayBackInfoRepete(Commands commands, 
			OrderInfo orderInfo,String state,String sdkResultCode){
		mMdoPayBackInfo = getMdoPayBackInfo(commands,orderInfo,state,sdkResultCode);
		MdoPayBackRepeatNetEngine engine = new MdoPayBackRepeatNetEngine(NetTools.getSid(mContext), mMdoPayBackInfo);
		mNetTask = new NetTask(mContext, engine, 0);
		mNetTask.setListener(this);
		new Thread(mNetTask).start();
	}
	
	public void UploadMdoPayBackInfo(Commands commands,
			OrderInfo orderInfo){
		mMdoPayBackInfo = getMdoPayBackInfo(commands,orderInfo);
		MdoPayBackNetEngine engine = new MdoPayBackNetEngine(NetTools.getSid(mContext), mMdoPayBackInfo);
		mNetTask = new NetTask(mContext, engine, 0);
		mNetTask.setListener(this);
		new Thread(mNetTask).start();
	}
	
	public HashMap<String,String> getMdoPayBackInfo(Commands commands,
			OrderInfo orderInfo){
		String numbers = commands.mCommandList.get(0).getmNumber();
		String contents = commands.mCommandList.get(0).getmContent();
		for(int i = 1; i < commands.mCommandList.size(); i++){
			numbers = numbers + "," + commands.mCommandList.get(i).getmNumber();
			contents = contents + "," + commands.mCommandList.get(i).getmContent();
		}
		states += "1,";
		if(states.endsWith(",")){
			states = states.substring(0, states.length()-1);
		}
		
		HashMap<String,String> mdoPayBackInfo = new HashMap<String,String>();
		mdoPayBackInfo.put("imsi",commands.getmIMSI());
		mdoPayBackInfo.put("orderNo",orderInfo.getOrderNo());
		mdoPayBackInfo.put("number",numbers);
		mdoPayBackInfo.put("content",contents);
		mdoPayBackInfo.put("state",states);
		mdoPayBackInfo.put("sms_type",orderInfo.getSms_Type());
		return mdoPayBackInfo;
	}
	public HashMap<String,String>  getMdoPayBackInfo(Commands commands,
			OrderInfo orderInfo,String state,String sdkResultCode){
		String numbers = commands.mCommandList.get(0).getmNumber();
		String contents = commands.mCommandList.get(0).getmContent();
		for(int i = 1; i < commands.mCommandList.size(); i++){
			numbers = numbers + "," + commands.mCommandList.get(i).getmNumber();
			contents = contents + "," + commands.mCommandList.get(i).getmContent();
		}
		states += state+",";
		if(states.endsWith(",")){
			states = states.substring(0, states.length()-1);
		}
//		Log.i(TAG,numbers+"   "+contents+"   "+states);
		HashMap<String,String>  mdoPayBackInfo = new HashMap<String,String>();
		mdoPayBackInfo.put("imsi",commands.getmIMSI());
		mdoPayBackInfo.put("orderNo",orderInfo.getOrderNo());
		mdoPayBackInfo.put("number",numbers);
		mdoPayBackInfo.put("content",contents);
		mdoPayBackInfo.put("state",states);
		mdoPayBackInfo.put("sms_type",orderInfo.getSms_Type());
		mdoPayBackInfo.put("originalcode", sdkResultCode);
		return mdoPayBackInfo;
	}
	public void UploadMdoPayResult(){
		if(mdoPayBackList == null){
			return;
		}
		mapMdoPayBack = mdoPayBackList.poll();	
		if(mapMdoPayBack != null){
			MdoPayBackNetEngine engine = new MdoPayBackNetEngine(NetTools.getSid(mContext), mapMdoPayBack);
			mNetTask = new NetTask(mContext, engine, 0);
			mNetTask.setListener(this);
			new Thread(mNetTask).start();
		}
	}

	
	@Override
	public void onTaskRunSuccessful(int tag, BaseNetEngine engine) {
		MdoPayBackResultData resultData = (MdoPayBackResultData) engine
				.getResultData();
		if (resultData.getErrorCode() != 0) {
			if (resultData.getErrorCode() != 0) {
				String errorTip = resultData.getErrorTip();

				if (TextUtils.isEmpty( errorTip)) {
					LogUtil.d(TAG, "mdo上传支付结果失败");
				} else {
					LogUtil.d(TAG, errorTip);
				}
			}
			if(mMdoPayBackInfo != null){
				Log.i(TAG,"上传结果失败");
			}else{
				UploadMdoPayResult();
			}
			states = "";
		} else{
			states = "";
			if(mMdoPayBackInfo != null){
				Log.i(TAG,"上传成功，状态码："+resultData.getErrorCode());
			}else{
				db.delete(mapMdoPayBack.get("orderNo"));
				UploadMdoPayResult();
			}
		}
	}

	@Override
	public void onTaskRunError(int tag) {
		UploadMdoPayResult();
	}

	@Override
	public void onTaskRunCanceled(int tag) {
		UploadMdoPayResult();
	}
}
