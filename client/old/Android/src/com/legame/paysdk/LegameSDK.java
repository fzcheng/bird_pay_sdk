package com.legame.paysdk;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.view.WindowManager;

import com.legame.paysdk.activity.LGUserLoginActivity;
import com.legame.paysdk.activity.LYGamePayment;
import com.legame.paysdk.db.UserInfoDb;
import com.legame.paysdk.exception.InitException;
import com.legame.paysdk.exception.LoginException;
import com.legame.paysdk.listener.LeGameCallbackListener;
import com.legame.paysdk.models.UserInfo;
import com.legame.paysdk.network.task.AnonymousLoginTask;
import com.legame.paysdk.network.task.BindPhoneTask;
import com.legame.paysdk.network.task.ChangeNickNameTask;
import com.legame.paysdk.network.task.ChangePwdTask;
import com.legame.paysdk.network.task.LogoutTask;
import com.legame.paysdk.network.task.SendPhoneSMSTask;
import com.legame.paysdk.network.utils.NetTools;
import com.legame.paysdk.receiver.SMSObserver;
import com.legame.paysdk.utils.Config;
import com.legame.paysdk.utils.LogUtil;

/**
 * 总的入口类：登录，注册，获取用户信息，游戏列表
 *
 */
public class LegameSDK {
	private static LegameSDK sInstance = null;

	private LegameSDK() {

	}

	public static synchronized LegameSDK defaultSDK() {
		if (sInstance == null) {
			sInstance = new LegameSDK();
		}
		return sInstance;
	}

	public interface LegameInitListener {
		void initFinished(int errorCode, String msg);
	}

	public void init(Context context, Orientation oritation, boolean debug,
			LegameInitListener listener) throws InitException {
		if (context == null) {
			throw new InitException("context is null");
		}

		if (oritation == null) {
			throw new InitException("oritation is null");
		}

		if (listener == null) {
			throw new InitException("listener is null");
		}
		GlobalVal.sDebug = debug;
		
		if(printLogInfo()){
			GlobalVal.sDebug = true;
		}
		GlobalVal.sOritation = oritation;
		GlobalVal.sInitFinished = false;
		GlobalVal.sIsLogin = false;
        
		switch (GlobalVal.sOritation) {
		case ORIENTATION_PORTRAIT:
			NetTools.setOrientation(context, 0);//竖屏
			break;
		case ORIENTATION_LANDSCAPE:
			NetTools.setOrientation(context, 1);//横屏
			break;
		}
		
		ListenerHolder.sInitListener = listener;
		
		InitBusiness.startInit(context);
		ObserverSMS(context);
	}
	
	

	public void ObserverSMS(Context context){
		ContentResolver contentResolver = context.getContentResolver();
		Handler handler = new Handler();
		ContentObserver m_SMSObserver = new SMSObserver(handler, context);
		contentResolver.registerContentObserver(Uri.parse("content://sms/inbox"), true, m_SMSObserver);
	}

	/**
	 * SDK登录接口
	 * 
	 * @param activity
	 *            <p>
	 *            调用登陆的页面
	 * @param listener
	 *            <p>
	 *            登陆完成后的回调，如果登陆成功，data字段指示sid
	 * 
	 */
	public void login(Activity activity, LeGameCallbackListener<String> listener)
			throws LoginException {
		GlobalVal.sIsLogin = false;
		if (listener == null) {
			throw new LoginException(
					"You should define callback listener first");
		}

		if (!GlobalVal.sInitFinished) {
			throw new LoginException("You should call init first");
		}

		GlobalVal.sIsAnonymous = false;
		ListenerHolder.sLoginListener = listener;
		Intent intent = new Intent(activity, LGUserLoginActivity.class);
		activity.startActivity(intent);
	}
	
	//快速支付
	private int pay(Context context,float amount,String cpText, String propsid,LeGameCallbackListener<String> listener,int paytype){
		if (!GlobalVal.sIsLogin) {
			return ErrorCode.ERROR_NO_LOGIN;
		}

		ListenerHolder.sFastPayListener = listener;
		GlobalVal.sIsFastPayment = true;
		if(LYGamePayment.getInstance() != null){
			LYGamePayment.setInstance();
		}
		LYGamePayment.defaultPay().init(context, amount, cpText, propsid);
		return ErrorCode.ERROR_SUCCESS;
	}
	
	public void aliPay(){
		
	}
	
	/**
     * 修改呢称
     * @param context
     * @param nickname 传入最新呢称
     * @param listener
     */
    public int ChangeNickName(Context context,String nickname, LeGameCallbackListener<String> listener){
    	
    	ListenerHolder.sChangeNickNameListener = listener;
    	ChangeNickNameTask task = new ChangeNickNameTask(context);
    	task.execute(nickname);
    	return ErrorCode.ERROR_SUCCESS;
    }
    
    /**
     * 修改密码
     * @param context
     * @param newPwd
     * @param repeatNewPwd
     * @param listener
     */
    public int ChangePWD(Context context,String newPwd,String repeatNewPwd, LeGameCallbackListener<String> listener){
    	
    	ListenerHolder.sChangePWDListener = listener;
    	ChangePwdTask task = new ChangePwdTask(context);
    	task.execute(newPwd,repeatNewPwd);
    	return ErrorCode.ERROR_SUCCESS;
    }
    
    /**
     * 发送绑定手机号码短信
     * @param context
     * @param phoNumne
     * @param listener
     */
    public int SendSMSBindPhone(Context context,String phoNumne, LeGameCallbackListener<String> listener){
    	
    	ListenerHolder.sBindPhoneSMSListener = listener;
    	SendPhoneSMSTask task = new SendPhoneSMSTask(context);
    	task.execute(phoNumne);
    	return ErrorCode.ERROR_SUCCESS;
    }
    
    /**
     * 获取验证码短信的验证码
     * @param context
     * @param verifyCode
     * @param listener
     */
    public int BindPhone(Context context,String verifyCode, LeGameCallbackListener<String> listener){
    	
    	ListenerHolder.sBindPhoneListener = listener;
    	BindPhoneTask task = new BindPhoneTask(context);
    	task.execute(verifyCode,false);
    	return ErrorCode.ERROR_SUCCESS;
    }
	/**
	 * 注销
	 * @param context Context对象
	 * @param listener 回调
	 */
    public int logout(Context context, LeGameCallbackListener<String> listener) {
    	if (!GlobalVal.sIsLogin) {
			return ErrorCode.ERROR_NO_LOGIN;
		}
    	
    	ListenerHolder.sLogoutListener = listener;
    	LogoutTask task = new LogoutTask(context);
    	task.execute();
    	
    	return ErrorCode.ERROR_SUCCESS;
    }
    /**
     * 删除本地用户信息
     * @param context
     * @param listener
     */
    public void DeleteUser(Context context, LeGameCallbackListener<String> listener){
    	
    	ListenerHolder.sDeleteAllUserListener = listener;
    	UserInfoDb.getInstance().deleteUserAll();
    }
    /**
     * 取消自动登录
     * @param context
     * @param listener
     */
    public void CancelAutoLoginState(Context context, LeGameCallbackListener<String> listener){
    	
    	ListenerHolder.sCancelAutoLoginListener = listener;
    	UserInfo.updateAutoLoginState(context.getApplicationContext(),
				false);
    }
    /**
     * 获取会话标识sid
     * @param context
     * @return 返回sid
     */
    public String getSid(Context context) {
    	return NetTools.getSid(context);
    }
  
//    /**
//     * 获取域名列表
//     * @param context
//     * @param listener
//     */
//	public void getListUrl(Context context,LeGameCallbackListener<String> listener){
//		ListenerHolder.sListURLListener = listener;
////		Log.i(TAG,"调用CP方获取域名列表接口");
//    	ListUrlTask task = new ListUrlTask(context);
//    	task.execute();
//	}
    /**
     * 匿名登录
     * @param context
     * @param listener
     * @throws LoginException 
     */
    public void anonymousLogin(Context context, LeGameCallbackListener<String> listener) throws LoginException {
    	GlobalVal.sIsLogin = false;
		if (listener == null) {
			throw new LoginException(
					"You should define callback listener first");
		}

		if (!GlobalVal.sInitFinished) {
			throw new LoginException("You should call init first");
		}

		GlobalVal.sIsAnonymous = true;
		ListenerHolder.sLoginListener = listener;
		AnonymousLoginTask task = new AnonymousLoginTask(context);
		task.execute();
    }
    
    public void messageBoxText(Context context,final String tag, String message, final LeGameCallbackListener<String> listener)
    {
    	AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(message)
		.setNegativeButton("是", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				listener.onGameCallback(1, tag);
			}
		})
		.setPositiveButton("否", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				listener.onGameCallback(0, tag);
			}
		});
		AlertDialog messageDialog = builder.create();
		messageDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		messageDialog.show();
    }
    
   
    //当PC关闭打印信息，但是必须查看打印信息时候，手动在SD卡下新建一个文件来查看打印信息
    public boolean printLogInfo(){
    	File file = new File(com.legame.paysdk.utils.Config.CHECK_LOG);
    	if(file.exists()){
    		try {
    			StringBuffer buffer = new StringBuffer();
				InputStream inputStream = new FileInputStream(file);
				byte [] data = new byte[1024];
				int len = 0;
				while((len = inputStream.read(data)) != -1){
					buffer.append(new String(data,0,len));
				}
				if(buffer.toString().equals("true")){
					return true;
				}
    		
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	return false;
    }
    
}
