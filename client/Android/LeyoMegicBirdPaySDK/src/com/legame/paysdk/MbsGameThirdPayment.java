package com.legame.paysdk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;

import com.legame.leyo.smspay.AliPay;
import com.legame.leyo.smspay.VirtualCodePay;
import com.legame.leyo.smspay.extend.sdk.EhooSdkPay;
import com.legame.paysdk.ErrorCode;
import com.legame.paysdk.GlobalVal;
import com.legame.paysdk.LeGamePayMent.MbsPayCallback;
import com.legame.paysdk.adapter.PaytypeListAdapter.PayTypeListener;
import com.legame.paysdk.dialog.PaytypeSlectDialog;
import com.legame.paysdk.network.engine.GetInitialParamEngine;
import com.legame.paysdk.network.engine.ThirdpayNetEngine;
import com.legame.paysdk.network.engine.BaseNetEngine;
import com.legame.paysdk.network.resultdata.AlipayResultData;
import com.legame.paysdk.network.resultdata.BaseResultData;
import com.legame.paysdk.network.resultdata.FastPaymentResultData;
import com.legame.paysdk.network.resultdata.InitialParamsResultData;
import com.legame.paysdk.network.resultdata.WxpayResultData;
import com.legame.paysdk.network.resultdata.ZwjfpayResultData;
import com.legame.paysdk.network.task.NetTask;
import com.legame.paysdk.network.task.NetTask.NetTaskListener;
import com.legame.paysdk.network.utils.NetTools;
import com.legame.paysdk.utils.Constants;
import com.legame.paysdk.utils.DataUtils;
import com.yeelo.master.wxapi.Wxpay;

/**
 * 
 * @author Kaiguang
 * @date 2016.3.10
 * @类说明 第三方支付
 * 
 */
class MbsGameThirdPayment implements NetTaskListener {

	private float amount;
	private String cpText;
	private String propsid;
	private String imsi;

	private Context mContext;
	private ProgressDialog mProgressDialog;

	private MbsPayCallback payCallback;
	
	private Integer payType;

	public void pay(Context context, float amount, String cpText,
			String propsid, MbsPayCallback payCallback, Integer paytype) {
		mContext = context;
		this.amount = amount;
		this.cpText = cpText;
		this.propsid = propsid;
		this.payType = paytype;
		this.imsi = GlobalVal.getIMSI(mContext);
		this.payCallback = payCallback;

		if (TextUtils.isEmpty(imsi)) {

		}
		
		getInitialParams(context);

//		if (paytype == null) {
//			selectPaytypeDialog();
//		} else {
//			waitingDlg("请求服务器中，请稍等...");
//			thirdPay(paytype);
//		}
	}
	
	private void selectPay(){
		if (payType == null) {
			selectPaytypeDialog();
		} else {
			waitingDlg("请求服务器中，请稍等...");
			thirdPay(payType);
		}
	}

	private void getInitialParams(final Context context) {
		waitingDlg("请稍等......");
		GetInitialParamEngine engine = new GetInitialParamEngine();
		NetTask netTask = new NetTask(context, engine, 0);
		netTask.setListener(new GetInitialParamListener(context));
		new Thread(netTask).start();
	}

	private class GetInitialParamListener implements NetTaskListener {

		private Context context;

		GetInitialParamListener(Context context) {
			this.context = context;
		}

		@Override
		public void onTaskRunSuccessful(int tag, BaseNetEngine engine) {
			cancelWaitingDlg();
			InitialParamsResultData resultData = (InitialParamsResultData) engine
					.getResultData();
			int errorCode = resultData.getErrorCode();
			if (errorCode != 0) {
//				showNetWorkErrorDialog(context, "获取支付类型失败");
				selectPay();
				return;
			}

			List<String> paytypeList = resultData.getPaytypeInfos();
			DataUtils.saveStringPre(context, Constants.PAY_TYPE,
					paytypeList.toString());
			selectPay();
		}

		@Override
		public void onTaskRunError(int tag) {
			cancelWaitingDlg();
		}

		@Override
		public void onTaskRunCanceled(int tag) {
			// TODO Auto-generated method stub
			cancelWaitingDlg();
		}

	}

	private void thirdPay(int paytype) {
		ThirdpayNetEngine engine = new ThirdpayNetEngine(paytype);
		engine.setData(NetTools.getSid(mContext), String.valueOf(amount),
				cpText, propsid, imsi);
		NetTask task = new NetTask(mContext, engine, paytype);
		task.setListener(this);
		waitingDlg("请稍等...");
		new Thread(task).start();
	}

	private void waitingDlg(String message) {
		mProgressDialog = new ProgressDialog(mContext);
		mProgressDialog.setCancelable(false);
		mProgressDialog.setMessage(message);
		// mProgressDialog.getWindow().setType(
		// WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		mProgressDialog.show();
	}

	private void cancelWaitingDlg() {
		if (mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.cancel();
		}
	}

	@Override
	public void onTaskRunSuccessful(int tag, BaseNetEngine engine) {
		// TODO Auto-generated method stub
		cancelWaitingDlg();
		BaseResultData baseResult = engine.getResultData();
		int errorCode = baseResult.getErrorCode();
		if (errorCode != 0) {
			payCallback.onLeYoPayResult(ErrorCode.ERROR_FAIL, "支付失败，error:"
					+ errorCode);
			return;
		}
		switch (tag) {
		case Constants.ALIPAY_TYPE_INT:
			AlipayResultData aliResult = (AlipayResultData) engine
					.getResultData();
			AliPay alipay = new AliPay();
			alipay.pay(mContext, aliResult.getPayInfo(), payCallback);

			// WxpayResultData aliResult = (WxpayResultData)
			// engine.getResultData();
			// ShenzhouPay szPay = new ShenzhouPay(mContext,
			// aliResult.getWxpayInfoMap());
			// szPay.payByAlipay();
			break;

		case Constants.WXPAY_TYPE_INT:
			WxpayResultData wxResult = (WxpayResultData) engine.getResultData();
			Wxpay wxpay = new Wxpay(mContext, wxResult.getWxpayInfoMap());
			// wxpay.pay();
			wxpay.payBySzWx();
			break;

		case Constants.ZWJFPAY_TYPE_INT:
			ZwjfpayResultData zwjfResult = (ZwjfpayResultData) engine
					.getResultData();
			VirtualCodePay virtualCodePay = new VirtualCodePay(mContext,
					zwjfResult.getOrderNo(), zwjfResult.getCommand(),
					zwjfResult.getBlock());
			virtualCodePay.setListener(payCallback);
			virtualCodePay.pay();
			break;

		case Constants.SMSPAY_TYPE_INT:
			FastPaymentResultData fastResult = (FastPaymentResultData) engine
					.getResultData();
			// Map<String,String> mapEhoo = fastResult.getMapEhoo();
			EhooSdkPay.pay(mContext, fastResult, payCallback);
			break;

		default:
			payCallback.onLeYoPayResult(ErrorCode.ERROR_FAIL, "支付类型未知，tag:"
					+ tag);
			break;
		}

	}

	@Override
	public void onTaskRunError(int tag) {
		// TODO Auto-generated method stub
		cancelWaitingDlg();
		payCallback.onLeYoPayResult(ErrorCode.ERROR_FAIL, "tag:" + tag);
	}

	@Override
	public void onTaskRunCanceled(int tag) {
		// TODO Auto-generated method stub
		cancelWaitingDlg();

	}

	private void selectPaytypeDialog() {
		// Builder dialog = new AlertDialog.Builder(mContext);
		// dialog.setSingleChoiceItems(new String[]{"支付宝支付","微信支付"},
		// selectPaytype, new OnClickListener() {
		//
		// @Override
		// public void onClick(DialogInterface dialog, int which) {
		// selectPaytype = which;
		// }
		// });
		// dialog.setPositiveButton("确定", new OnClickListener() {
		//
		// @Override
		// public void onClick(DialogInterface dialog, int which) {
		// if(which >=0){
		// selectPaytype = which;
		// }else{
		// waitingDlg("请求服务器中，请稍等...");
		// thirdPay(selectPaytype);
		// dialog.dismiss();
		// }
		// }
		// });
		// dialog.show();
		final PaytypeSlectDialog dialog = new PaytypeSlectDialog(mContext);
		String payTypes = DataUtils.getValuePre(mContext, Constants.PAY_TYPE);// [pay1,pay2,pay3,...]
		String[] payTypeArry = payTypes.replace("[", "").replace("]", "")
				.split(", ");
		
		//米大师支付，和其他支付方式互斥
		if(Constants.YSDKPAY_TYPE.equals(payTypeArry[0])){
			thirdPay(Constants.YSDKPAY_TYPE_INT);
			return;
		}

		if (Constants.YSDKPAY_TYPE.equals(payTypeArry[0])) {
//			Ysdk.recharge();
			return;
		}

		dialog.setCancelable(false);
		dialog.setCanceledOnTouchOutside(false);
		dialog.setPayMoney(String.valueOf(amount) + "元");
		List<String> payTypeList = new ArrayList<String>(
				Arrays.asList(payTypeArry));
		String firstPaytype = payTypeList.remove(0);

		MPayTypeListener listener = new MPayTypeListener();
		listener.setDialog(dialog);
		dialog.setPaytypeListener(listener);
		dialog.setFirstPaytype(firstPaytype);
		dialog.setAdapter(payTypeList);

		dialog.show();
	}

	private class MPayTypeListener implements PayTypeListener {

		private Dialog dialog;

		void setDialog(Dialog dialog) {
			this.dialog = dialog;
		}

		@Override
		public void onSlect(int position, String payType) {

			int payTypeInt = Constants.ALIPAY_TYPE_INT;

			if (Constants.ALIPAY_TYPE.equals(payType)) {

				payTypeInt = Constants.ALIPAY_TYPE_INT;

			} else if (Constants.WXPAY_TYPE.equals(payType)) {

				payTypeInt = Constants.WXPAY_TYPE_INT;

			} else if (Constants.ZWJFPAY_TYPE.equals(payType)) {

				payTypeInt = Constants.ZWJFPAY_TYPE_INT;

			} else if (Constants.SMSPAY_TYPE.equals(payType)) {

				payTypeInt = Constants.SMSPAY_TYPE_INT;

			}

			thirdPay(payTypeInt);
			dialog.dismiss();
		}

		@Override
		public void onCancel() {
			payCallback
					.onLeYoPayResult(ErrorCode.ERROR_CANCEL_DIALOG, "用户取消支付");
			dialog.dismiss();
		}

	}

}
