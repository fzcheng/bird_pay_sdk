package com.legame.paysdk.activity;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.legame.paysdk.ErrorCode;
import com.legame.paysdk.GlobalVal;
import com.legame.paysdk.LeGamePayMent;
import com.legame.paysdk.ListenerHolder;
import com.legame.paysdk.LeGamePayMent.MbsPayCallback;
import com.legame.paysdk.dialog.FailDialog;
import com.legame.paysdk.dialog.MoreConfirmDialog;
import com.legame.paysdk.dialog.SecondConfirmDialog;
import com.legame.paysdk.dialog.SuccessDialog;
import com.legame.paysdk.models.Commands;
import com.legame.paysdk.models.ErrorCodelnfo;
import com.legame.paysdk.models.OrderInfo;
import com.legame.paysdk.models.PaymentType;
import com.legame.paysdk.models.TipsInfo;
import com.legame.paysdk.models.UpgradeJarInfo;
import com.legame.paysdk.network.engine.BaseNetEngine;
import com.legame.paysdk.network.resultdata.FastPaymentResultData;
import com.legame.paysdk.network.task.NetTask;
import com.legame.paysdk.network.task.NetTask.NetTaskListener;
import com.legame.paysdk.network.utils.NetTools;
import com.legame.paysdk.utils.Config;
import com.legame.paysdk.utils.Constants;
import com.legame.paysdk.utils.ResourceUtil;

import dalvik.system.DexClassLoader;

/**
 * 
 * @author Kaiguang
 * @date 2016.2.26
 * @类说明 支付模块流程处理
 * 
 */
@SuppressLint("NewApi")
public class MbsGamePayment implements NetTaskListener {

	private static MbsGamePayment instance;
	private Context mContext;
	private float mAmount;
	private String mCpText;
	private String mPropsid;
	private String mIMSI;

	private int errorCode;
	private String errorTip = "";

	private DexClassLoader mDexClassLoader;
	private ProgressDialog mProgressDialog;
	private NetTask mNetTask;
	private LeGamePayMent[] lpm;

	public static MbsGamePayment getInstance() {
		if (instance == null) {
			instance = new MbsGamePayment();
		}
		return instance;
	}

	public void init() {
	}

	public void pay(Context context, float amount, String cpText, String propsid) {
		mContext = context;
		this.mAmount = amount;
		this.mCpText = cpText;
		this.mPropsid = propsid;
		mIMSI = GlobalVal.getIMSI(context);
		
		mDexClassLoader = GlobalVal.classRef(mContext, Config.LEYOPAY_SMS);
		

		if (!TextUtils.isEmpty(mIMSI)) {
			createWaitingDlg("请求服务器中，请稍候...");
			requestPayment();
		}else{//获取不到IMSI
			Toast.makeText(mContext, "获取IMSI为空", Toast.LENGTH_SHORT).show();
		}

	}

	private void requestPayment() {
		String ver = UpgradeJarInfo.getUpgradeJar(mContext);
		GlobalVal.CopyAssertJarToFile(mContext, Config.LEYOPAY_SMS,
				Config.LEYOPAY_SMS);
		if (!Constants.SMS_SDK_VERSION.equals(ver)) {

		}
		instanceFastPay();
	}

	private void instanceFastPay() {
		Class fastPaymentNetEngine = null;

		try {
			fastPaymentNetEngine = mDexClassLoader
					.loadClass("com.legame.leyo.smspay.FastPaymentNetEngine");
			Object payObj = fastPaymentNetEngine.newInstance();

			Method m1 = payObj.getClass().getMethod("setData", String.class,
					String.class, String.class, String.class, String.class);
			m1.invoke(payObj, NetTools.getSid(mContext), mAmount + "", mCpText,
					mPropsid, mIMSI);
			mNetTask = new NetTask(mContext, (BaseNetEngine) payObj, 0);
			mNetTask.setListener(this);
			new Thread(mNetTask).start();

		} catch (Exception exception) {
			exception.printStackTrace();
			cancelWaitingDlg();
			showDialog(mContext, ErrorCode.ERROR_JAR_EXCEPTION);
		}
	}

	public void createWaitingDlg(String message) {
		mProgressDialog = new ProgressDialog(mContext);
		mProgressDialog.setCancelable(false);
		mProgressDialog.setMessage(message);
		mProgressDialog.getWindow().setType(
				WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		 mProgressDialog.show();
	}

	public void cancelWaitingDlg() {
		if (mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.cancel();
		}
	}

	private void showDialog(Context context, int msg) {

	}

	public void fastPaymentFail(final int errorCode, String errorTip) {
		// 失败
		String errotTip = "";
		if (errorCode == -1009) {
			errotTip = "联网失败";
		} else {
			errotTip = "很抱歉，支付失败";
		}
		final FailDialog mDialog = new FailDialog(mContext);
		mDialog.showTitle("支付提示");
		mDialog.setContent1(errotTip);
		mDialog.setContent2(errorCode + " " + errorTip);
		mDialog.setContent3("支付失败，如有疑问，请牢记返回码致电客服："
				+ ResourceUtil.getStringExt(mContext, "lgsdk_service_tel"));
		mDialog.show();
		mDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
		mDialog.showButton1("确定", new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ListenerHolder.sFastPayListener.onGameCallback(errorCode,
						"支付失败");
				mDialog.dismiss();
				// ((Activity)mContext).finish();
			}
		});
	}

	// 快速支付type
	public int isExistFastPayment(String type) {
		lpm = new LeGamePayMent[10];
		int typeIndex = -1;
		if (type.equals("mmdo") || type.equals("alipay")
				|| type.equals("unionpay")) {
			typeIndex = PaymentType.PAYMTENT_TYPE_MDO;
			String ver = UpgradeJarInfo.getUpgradeJar(mContext);
			GlobalVal.CopyAssertJarToFile(mContext, Config.LEYOPAY_SMS,
					Config.LEYOPAY_SMS);
			if (!Constants.SMS_SDK_VERSION.equals(ver)) {

			}
			// Log.i(TAG,"进入反射初始化");
			Class smsClass = null;
			Class mdoPayBackClass = null;
			try {
				// Load the library class from the class loader.
				// 载入从网络上下载的类
				smsClass = mDexClassLoader
						.loadClass("com.legame.leyo.smspay.SmsPay");
				if (type.equals("alipay")) {
					smsClass = mDexClassLoader
							.loadClass("com.legame.leyo.smspay.AliPay");
					typeIndex = PaymentType.PAYMTENT_TYPE_ALIPAY;
				}


				Object payObj = smsClass.newInstance();

				if (type.equals("mmdo")) {
					Method smsObjSetLYGamePayment = payObj.getClass()
							.getMethod("setLYGamePayment", LYGamePayment.class);
					smsObjSetLYGamePayment.invoke(payObj, MbsGamePayment.this);
				}

				lpm[typeIndex] = (LeGamePayMent) payObj;
				mdoPayBackClass = mDexClassLoader
						.loadClass("com.legame.leyo.smspay.MdoPayBackCheck");
				Object mdoPayBackObj = mdoPayBackClass.newInstance();
				Method m1 = mdoPayBackObj.getClass().getMethod("instance",
						Context.class);
				m1.invoke(null, mContext);
				Method m2 = mdoPayBackObj.getClass().getMethod(
						"UploadMdoPayBackInfo");
				m2.invoke(mdoPayBackObj);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return typeIndex;
	}
	
	private void startPayment(int typeIndex, BaseNetEngine engine){
		final FastPaymentResultData resultData = (FastPaymentResultData) engine
				.getResultData();
		if(PaymentType.PAYMTENT_TYPE_MDO == typeIndex){
			mProgressDialog.setMessage(resultData.getPayLoadingshowMsg());
			mProgressDialog.show();
		}
		lpm[typeIndex].leyoPay( mContext,
				resultData.getOrderInfo(), resultData, new MbsPayCallback() {

					@Override
					public void onLeYoPayResult(int status, String msg) {
						cancelWaitingDlg();

						if (0 == status) {
							showPaymentResult(ErrorCode.ERROR_SUCCESS,
									msg);
						} else {
							showPaymentResult(ErrorCode.ERROR_FAIL, msg);
						}
					}
				});
	}
	
	public void showPaymentResult(int error, String errorTip) {
		if (ErrorCode.ERROR_SUCCESS == error) {
			// 成功
			if (tipsInfo!=null&&TipsInfo.SHOW.equals(tipsInfo.getChargesuceesstip())) {// 后台配置需要弹出成功dialog
				final SuccessDialog mDialog = new SuccessDialog(mContext);
				mDialog.showTitle("购买提示");
				mDialog.setContent1("恭喜您，支付成功！");
				mDialog.setContent2("说明：如有问题请致电客服电话："
						+ResourceUtil.getStringExt(mContext, "lgsdk_service_tel"));
				mDialog.show();
				mDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
				mDialog.showButton1("确定", new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						if (ListenerHolder.sFastPayListener != null) {
							ListenerHolder.sFastPayListener.onGameCallback(
									ErrorCode.ERROR_SUCCESS,
									ResourceUtil.getStringExt(mContext,
											"lgsdk_mdo_operation_success"));
						}
						mDialog.dismiss();
						// ((Activity)mContext).finish();
					}
				});
			} else {
				if (ListenerHolder.sFastPayListener != null) {
					ListenerHolder.sFastPayListener.onGameCallback(
							ErrorCode.ERROR_SUCCESS, ResourceUtil.getStringExt(
									mContext, "lgsdk_mdo_operation_success"));
				}
				// ((Activity)mContext).finish();
			}
		} else {
			// 失败
			if(tipsInfo!=null&&TipsInfo.NO_SHOW.equals(tipsInfo.getChargefailtip())){
				if (ListenerHolder.sFastPayListener != null) {
					ListenerHolder.sFastPayListener.onGameCallback(
							ErrorCode.ERROR_FAIL, ResourceUtil
									.getStringExt(mContext,
											"lgsdk_mdo_operation_failed"));
				}
				return;
			}
			final FailDialog mDialog = new FailDialog(mContext);
			mDialog.showTitle("购买提示");
			mDialog.setContent1("很抱歉，支付失败！");
			mDialog.setContent2(error + "");
			mDialog.setContent3("支付失败，如有疑问，请牢记返回码致电客服："
								+ResourceUtil.getStringExt(mContext, "lgsdk_service_tel"));
			mDialog.show();
			mDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
			mDialog.showButton1("确定", new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					if (ListenerHolder.sFastPayListener != null) {
						ListenerHolder.sFastPayListener.onGameCallback(
								ErrorCode.ERROR_FAIL, ResourceUtil
										.getStringExt(mContext,
												"lgsdk_mdo_operation_failed"));
					}
					mDialog.dismiss();
					// ((Activity)mContext).finish();
				}
			});
		}
	}
	
	private void payBack(FastPaymentResultData resultData,int typeIndex){
		Commands commands = resultData.getCommands();
		OrderInfo orderInfo = resultData.getOrderInfo();
		DexClassLoader cl = GlobalVal.classRef(mContext,
				Config.LEYOPAY_SMS);
		
		try{
			String state = "3";
			HashMap<String,String> mapMdoPayBack = new HashMap<String, String>();
			String imsi = "";
			if(commands != null){
				imsi = commands.getmIMSI();
			}
			mapMdoPayBack.put("imsi",imsi);
			mapMdoPayBack.put("orderNo",orderInfo.getOrderNo());
			mapMdoPayBack.put("number","");
			mapMdoPayBack.put("content","");
			mapMdoPayBack.put("state",state);
			mapMdoPayBack.put("sms_type",orderInfo.getSms_Type());
			mapMdoPayBack.put("originalcode", "leyoordercancel");
			Class<?> mdoPayBackCheckClass = cl.loadClass("com.legame.leyo.smspay.MdoPayBackNetEngine");
			Constructor<?> constructor = mdoPayBackCheckClass
					.getConstructor(new Class[]{String.class,HashMap.class});
			Object mdoPayBack = constructor.newInstance(new Object[]{NetTools.getSid(mContext), mapMdoPayBack});
			BaseNetEngine engine = (BaseNetEngine) mdoPayBack;
			NetTask netTask = new NetTask(mContext, engine, 0);
			new Thread(netTask).start();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private TipsInfo tipsInfo;
	@Override
	public void onTaskRunSuccessful(int tag, final BaseNetEngine engine) {
		cancelWaitingDlg();
		final FastPaymentResultData resultData = (FastPaymentResultData) engine
				.getResultData();

		errorCode = resultData.getErrorCode();
		errorTip = resultData.getErrorTip();
		tipsInfo = resultData.getTipInfo();
		// Log.i(TAG,"错误代码："+errorCode+"错误提示:"+errorTip);
		if (errorCode != 0) {// 快速支付失败
			fastPaymentFail(errorCode, errorTip);
		} else {
			String type = resultData.getType();
			if (!TextUtils.isEmpty(type)) {
				final int typeIndex = isExistFastPayment(type);
				if(typeIndex == -2){
					fastPaymentFail(ErrorCode.ERROR_JAR_EXCEPTION, errorTip);
					return;
				}
				if(typeIndex != -1){
					String commitDialog = resultData.getTipInfo().getChargetip();
					if("1".equals(commitDialog)){

						// 配置二次确认框弹出
						String amount = "￥" + mAmount;
						String text = "支付金额：【" + amount + "元】";
						String gameName = "";
						if (resultData.getTipInfo().getGamename() != null) {
							gameName = "产品名称：【"
									+ GlobalVal.getAppName(mContext) + "】";
						}
						String strContet = "";
						if (GlobalVal.getAppName(mContext).equals(
								resultData.getTipInfo().getGamename())) {

							strContet = "";
						} else { 
							strContet = "特别说明：\n为良好计费的体验，本次计费将使用"
									+ resultData.getTipInfo().getGamename()
									+ "产品来计费.";
						}
						final SecondConfirmDialog mSecondConfirmDialog = new SecondConfirmDialog(
								mContext);
						mSecondConfirmDialog.setMTitle("购买提示：");
						mSecondConfirmDialog.setContent1(text);
						mSecondConfirmDialog.setContent2(gameName);
						mSecondConfirmDialog.setContent3(strContet);
						mSecondConfirmDialog.show();
						mSecondConfirmDialog.setCancelable(false);
						mSecondConfirmDialog.showButton1("确定",
								new View.OnClickListener() {
									@Override
									public void onClick(View v) {
										// 进入快速支付
										startPayment(typeIndex, engine);
										mSecondConfirmDialog.dismiss();
									}
								});
						mSecondConfirmDialog.showButton2("拒绝",
								new View.OnClickListener() {
									@Override
									public void onClick(View v) {
										final MoreConfirmDialog mDialog = new MoreConfirmDialog(
												mContext);
										mDialog.setContent1("若放弃购买,您将无法获得最佳体验,是否返回");
										mDialog.show();
										mDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
										mDialog.showButton1("确定",
												new View.OnClickListener() {
													@Override
													public void onClick(
															View v) {
														fastPaymentFail(
																ErrorCode.ERROR_FAIL,
																errorTip);
														payBack(resultData,typeIndex);
														mSecondConfirmDialog
																.dismiss();
														mDialog.dismiss();
														// ((Activity)mContext).finish();
													}
												});
										mDialog.showButton2("返回",
												new View.OnClickListener() {
													@Override
													public void onClick(
															View v) {
														mDialog.dismiss();
													}
												});
									}
								});
					
					}else{
						startPayment(typeIndex, engine);
					}
				}
			} else {
				fastPaymentFail(ErrorCode.ERROR_FAIL, "支付类型不能为空");
			}
		}

	}

	@Override
	public void onTaskRunError(int tag) {
		cancelWaitingDlg();
		int errocode = 0;
		if (ErrorCodelnfo.getErrorCode() != 200) {
			if (ErrorCodelnfo.getErrorCode() == 0) {
				errocode = ErrorCode.ERROR_FAIL;
			} else {
				errocode = ErrorCodelnfo.getErrorCode();
			}
			showDialog(mContext, errocode);
		}
	}

	@Override
	public void onTaskRunCanceled(int tag) {
		// TODO Auto-generated method stub

	}

}
