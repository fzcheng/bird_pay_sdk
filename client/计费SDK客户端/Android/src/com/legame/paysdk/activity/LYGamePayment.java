package com.legame.paysdk.activity;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Properties;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.legame.paysdk.ErrorCode;
import com.legame.paysdk.GlobalVal;
import com.legame.paysdk.LeGamePayMent;
import com.legame.paysdk.LeGamePayMent.LeYoPayCallBack2;
import com.legame.paysdk.ListenerHolder;
import com.legame.paysdk.dialog.FailDialog;
import com.legame.paysdk.dialog.MoreConfirmDialog;
import com.legame.paysdk.dialog.SecondConfirmDialog;
import com.legame.paysdk.dialog.SuccessDialog;
import com.legame.paysdk.dialog.TextDialog;
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
 * 类说明：
 * 
 * @author Terry.Lu/Xiaodm/KaiGuang
 * @date 2013年10月16日/2015年01月21日/2015-5-15
 * @version 1.0
 */
@SuppressLint("NewApi")
public class LYGamePayment implements NetTaskListener {
	private static final String TAG = "LYGamePayment";
	private float mAmount;
	private String mCpText;
	private TextDialog mDialog;
	private ProgressDialog mProgressDialog;
	private NetTask mNetTask;
	private String mIMSI;
	private String mPropsid;
	private LeGamePayMent[] lpm;
	private Context mContext;
	public static Context gContext;

	private String errorTip = "";
	private int errorCode = ErrorCode.ERROR_NO_NETWORK;
	private boolean bSina;
	// private int sinamonthsmsIndex = 0;

	private DexClassLoader mDexClassLoader;

	private static LYGamePayment sInstance = null;

	public LYGamePayment() {

	}

	public static LYGamePayment getInstance() {
		return sInstance;
	}

	public static void setInstance() {
		sInstance = null;
	}

	public static synchronized LYGamePayment defaultPay() {
		if (sInstance == null) {
			sInstance = new LYGamePayment();
		}
		return sInstance;
	}

	public void init(Context context, float amount, String cpText,
			String propsid) {
		mContext = context;
		mAmount = amount;
		mCpText = cpText;
		mPropsid = propsid;
		mIMSI = GlobalVal.getIMSI(context);
		bSina = false;
		
		mDexClassLoader = GlobalVal.classRef(mContext,
				Config.LEYOPAY_SMS);
		if (GlobalVal.sIsFastPayment && !TextUtils.isEmpty(mIMSI)) {
			if(!GlobalVal.getMetadataBoolean(mContext, "LEYO_NO_SHOW_DIALOG"))
				showWaitingDlg("请求服务器中，请稍候...");
			getFastPaymentType();
		} else {
			if (GlobalVal.sIsMonthly) {
				GlobalVal.sIsMonthly = false;
				// Toast.makeText(this, "不支持该业务", Toast.LENGTH_SHORT).show();
				final FailDialog mDialog = new FailDialog(mContext);
				mDialog.showTitle("支付提示");
				mDialog.setContent1("不支持该业务");
				mDialog.setContent2(ErrorCode.ERROR_NO_SUPPORT + "");
				mDialog.setContent3("您的电话号码不支持该业务，如有疑问，请牢记返回码致电客服："
						+ResourceUtil.getStringExt(mContext, "lgsdk_service_tel"));
				mDialog.show();
				mDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
				mDialog.showButton1("确定", new View.OnClickListener() {

					@Override
					public void onClick(View v) {

						ListenerHolder.sFastPayListener.onGameCallback(
								ErrorCode.ERROR_NO_SUPPORT, "不支持该业务");
						mDialog.dismiss();
						// ((Activity)mContext).finish();
					}
				});
			} else {
				final FailDialog mDialog = new FailDialog(mContext);
				mDialog.showTitle("支付提示");
				mDialog.setContent1("检测不到电话卡");
				mDialog.setContent2(ErrorCode.ERROR_NO_SIMCARD + "");
				mDialog.setContent3("没有电话卡无法进行话费支付，请插上电话卡，如有疑问，请牢记返回码致电客服："
						+ResourceUtil.getStringExt(mContext, "lgsdk_service_tel"));
				mDialog.show();
				mDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
				mDialog.showButton1("确定", new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						ListenerHolder.sFastPayListener.onGameCallback(
								ErrorCode.ERROR_NO_SIMCARD, "检测不到sim卡");
						mDialog.dismiss();
						// ((Activity)mContext).finish();
					}
				});
			}
		}

		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			mDialog = new TextDialog(mContext);
			mDialog.getWindow().setType(
					WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
			mDialog.showTitle("").setContent("SD卡不可用")
					.showButton1("确定", new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							mDialog.dismiss();
							// ((Activity)mContext).finish();
						}
					});
			mDialog.setCanceledOnTouchOutside(false);
			mDialog.setCancelable(false);
			mDialog.show();
		}
	}
	
	public void showWaitingDlg(String message) {
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

	private void getFastPaymentType() {
		String ver = UpgradeJarInfo.getUpgradeJar(mContext);
		GlobalVal.CopyAssertJarToFile(mContext, Config.LEYOPAY_SMS,
				Config.LEYOPAY_SMS);
		if (!Constants.SMS_SDK_VERSION.equals(ver)) {
			
		}
		instanceFastPay();
	}
	
	private void instanceFastPay(){
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

	private TipsInfo tipsInfo ;
	@Override
	public void onTaskRunSuccessful(int tag, final BaseNetEngine engine) {
		cancelWaitingDlg();
		final FastPaymentResultData resultData = (FastPaymentResultData) engine
				.getResultData();

		if (resultData.getOrderInfo() != null) {
			if (resultData.getOrderInfo().getSms_Type() != null) {
				bSina = resultData.getOrderInfo().getSms_Type()
						.equals("sinamonthpm");
			}
		}
		errorCode = resultData.getErrorCode();
		errorTip = resultData.getErrorTip();
		tipsInfo = resultData.getTipInfo();
//		 Log.i(TAG,"错误代码："+errorCode+"错误提示:"+errorTip);
		if (errorCode != 0) {// 快速支付失败

			fastPaymentFail(bSina, errorCode, errorTip);
		} else {// 选择快速支付
			String type = resultData.getType();
			if (!TextUtils.isEmpty(type)) {
				final int typeIndex = isExistFastPayment(type);
				String strScb = resultData.getTipInfo().getChargetip();
				if(typeIndex == -2){
					fastPaymentFail(false, ErrorCode.ERROR_JAR_EXCEPTION, errorTip);
					return;
				}
				if (typeIndex != -1) {
					if (strScb != null && !strScb.equals("")) {// 不为空
						if (strScb.equals("1")) {
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
											selectPayment(typeIndex, engine);
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
																	bSina,
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
						} else {
							// 没有配置二次确认框弹出
							// Log.i(TAG,"没有配置二次确认框");
							selectPayment(typeIndex, engine);
						}
					} else {
						selectPayment(typeIndex, engine);
					}
				} else {
					fastPaymentFail(bSina, ErrorCode.ERROR_FAIL, errorTip);
				}
			} else {
				fastPaymentFail(bSina, ErrorCode.ERROR_FAIL, errorTip);
			}
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

	}

	public void fastPaymentFail(boolean isSina, final int errorCode,
			String errorTip) {
		if (!isSina) {
			if (ListenerHolder.sFastPayListener != null) {
				// 快速支付失败
				ListenerHolder.sFastPayListener.onGameCallback(errorCode,
						errorTip);
			}
			// 失败
			String errotTip = "";
			if (errorCode == -1009) {
				errotTip = "联网失败";
			} else {
				errotTip = "很抱歉，支付失败";
			}
			boolean notShow = tipsInfo.getChargefailtip() != null 
					? tipsInfo.getChargefailtip().equals(TipsInfo.NO_SHOW)
					: GlobalVal.getMetadataBoolean(mContext, "LEYO_NO_SHOW_DIALOG");
			if(notShow){
				if (ListenerHolder.sFastPayListener != null) {
					
					ListenerHolder.sFastPayListener.onGameCallback(errorCode,
							"支付失败");
				}
				return;
			}
			final FailDialog mDialog = new FailDialog(mContext);
			mDialog.showTitle("支付提示");
			mDialog.setContent1(errotTip);
			mDialog.setContent2(errorCode + " " + errorTip);
			mDialog.setContent3("支付失败，如有疑问，请牢记返回码致电客服："
					+ResourceUtil.getStringExt(mContext, "lgsdk_service_tel"));
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
		} else {
			bSina = false;
			Toast.makeText(mContext, "支付失败", Toast.LENGTH_SHORT).show();
			// ((Activity)mContext).finish();
		}
	}

	// 快速支付type
	public int isExistFastPayment(String type) {
		lpm = new LeGamePayMent[10];
		int typeIndex = -1;
		if (type.equals("mmdo")||type.equals("alipay")||type.equals("unionpay")) {
			typeIndex = PaymentType.PAYMTENT_TYPE_MDO;
			String ver = UpgradeJarInfo.getUpgradeJar(mContext);
			GlobalVal.CopyAssertJarToFile(mContext, Config.LEYOPAY_SMS, Config.LEYOPAY_SMS);
			if (!Constants.SMS_SDK_VERSION.equals(ver)) {
				
			} 
			// Log.i(TAG,"进入反射初始化");
			Class smsClass = null;
			Class mdoPayBackClass = null;
			try {
				// Load the library class from the class loader.
				// 载入从网络上下载的类
				smsClass = mDexClassLoader.loadClass("com.legame.leyo.smspay.SmsPay");
				if(type.equals("alipay")){
					smsClass = mDexClassLoader.loadClass("com.legame.leyo.smspay.AliPay");
					typeIndex = PaymentType.PAYMTENT_TYPE_ALIPAY;
				}
				
				if(type.equals("unionpay")){
					smsClass = mDexClassLoader.loadClass("com.legame.leyo.smspay.UnionPay");
					typeIndex = PaymentType.PAYMTENT_TYPE_UNION_PAY;
				}
					
				Object payObj = smsClass.newInstance();

				if(type.equals("mmdo")){
					Method smsObjSetLYGamePayment = payObj.getClass().getMethod(
							"setLYGamePayment", LYGamePayment.class);
					smsObjSetLYGamePayment.invoke(payObj, LYGamePayment.this);
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
			}catch(Exception e){
				e.printStackTrace();
			}

		}
		return typeIndex;
	}

	public void selectPayment(final int typeIndex, BaseNetEngine engine) {
		switch (typeIndex) {
		case PaymentType.PAYMTENT_TYPE_ALIPAY:
		case PaymentType.PAYMTENT_TYPE_TENPAY:
		case PaymentType.PAYMTENT_TYPE_MO9:
		case PaymentType.PAYMTENT_TYPE_WEIPAI:
		case PaymentType.PAYMTENT_TYPE_UNION_PAY:
		case PaymentType.PAYMTENT_TYPE_UPAY:
		case PaymentType.PAYMTENT_TYPE_VPAY:
		case PaymentType.PAYMTENT_TYPE_MDO:
			if (isPluginInstalled(typeIndex)) {
				// Log.i(TAG,"进入支付");
				startPayment(typeIndex, engine);
			}
			break;
		case PaymentType.PAYMTENT_TYPE_GAME_RECHARGABLE_CARD:
		case PaymentType.PAYMTENT_TYPE_PHONE_RECHARGABLE_CARD:
			break;
		}
	}

	private boolean isPluginInstalled(int type) {
		switch (type) {
		case PaymentType.PAYMTENT_TYPE_ALIPAY:
			return true;
		case PaymentType.PAYMTENT_TYPE_UNION_PAY:
			return true;
		case PaymentType.PAYMTENT_TYPE_TENPAY:
		case PaymentType.PAYMTENT_TYPE_MO9:
		case PaymentType.PAYMTENT_TYPE_WEIPAI:
		case PaymentType.PAYMTENT_TYPE_UPAY:
		case PaymentType.PAYMTENT_TYPE_VPAY:
			return false;
		case PaymentType.PAYMTENT_TYPE_MDO:
			return true;
		}

		return false;
	}

	public void startPayment(int typeIndex, BaseNetEngine engine) {
		
		final FastPaymentResultData resultData = (FastPaymentResultData) engine
				.getResultData();
		OrderInfo orderInfo = resultData.getOrderInfo();
		switch (typeIndex) {
		case PaymentType.PAYMTENT_TYPE_ALIPAY:
			 
			break;
		case PaymentType.PAYMTENT_TYPE_TENPAY:
			break;
		case PaymentType.PAYMTENT_TYPE_MO9:
			break;
		case PaymentType.PAYMTENT_TYPE_WEIPAI:
			break;
		case PaymentType.PAYMTENT_TYPE_UNION_PAY:
			break;
		case PaymentType.PAYMTENT_TYPE_UPAY:
			break;
		case PaymentType.PAYMTENT_TYPE_VPAY:
			break;
		case PaymentType.PAYMTENT_TYPE_MDO:
			showWaitingDlg(resultData.getPayLoadingshowMsg());
			break;
			// 短信支付
//			if (Constants.SMS_SDK_VERSION.equals(UpgradeJarInfo
//					.getUpgradeJar(mContext))) {
		default:
			break;
		}
		lpm[typeIndex].leyoPay(/*(Activity)*/ mContext,
				orderInfo, resultData, new LeYoPayCallBack2() {

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
			if (tipsInfo.getChargesuceesstip().equals(TipsInfo.SHOW)) {// 后台配置需要弹出成功dialog
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
			Log.e("LYGamePayment", "fail tip:"+tipsInfo.getChargefailtip());
			if(tipsInfo.getChargefailtip().equals(TipsInfo.NO_SHOW)){
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

	public String getResource(String payType) throws IOException {

		Properties props = new Properties();
		InputStream inputStream = this.getClass().getClassLoader()
				.getResourceAsStream("payment.properties");
		props.load(inputStream);
		String temp = props.getProperty(payType);
		return temp;
	}

	private void showDialog(Context context, final int errorCode) {
		final FailDialog mDialog = new FailDialog(context);
		mDialog.showTitle("联网提示");
		mDialog.setContent1("很抱歉，服务器繁忙");
		mDialog.setContent2(errorCode + "");
		mDialog.setContent3("服务器繁忙，导致暂时无法连接服务器，如有疑问，请牢记返回码致电客服："
				+ResourceUtil.getStringExt(mContext, "lgsdk_service_tel"));
		mDialog.show();
		mDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
		mDialog.showButton1("确定", new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ListenerHolder.sFastPayListener.onGameCallback(errorCode,
						"支付失败");
				mDialog.dismiss();
			}
		});
	}
	
}
