package com.legame.paysdk.network.engine;

import java.util.Map;

import android.content.Context;
import android.util.Log;

import com.legame.leyo.smspay.FastPaymentNetEngine;
import com.legame.paysdk.network.resultdata.AlipayResultData;
import com.legame.paysdk.network.resultdata.FastPaymentResultData;
import com.legame.paysdk.network.resultdata.WxpayResultData;
import com.legame.paysdk.network.resultdata.ZwjfpayResultData;
import com.legame.paysdk.utils.Constants;

/**
 * 
 * @author Kaiguang
 * @date 2016.3.9
 * 
 */
public class ThirdpayNetEngine extends FastPaymentNetEngine {

	private static final String[] METHOD = new String[] {
			Constants.ALIPAY_TYPE, Constants.SHENZHOUWXPAY_TYPE, 
			Constants.ZWJFPAY_TYPE,Constants.SMSPAY_TYPE_MMDO,
			Constants.YSDKPAY_TYPE};

	private static String mPayType;

	public ThirdpayNetEngine(int payType) {
		mPayType = METHOD[payType];
//		if(payType == 0){
//			mPayType = Constants.SHENZHOUALIPAY_TYPE;
//		}
//		if(payType == 1){
//			mPayType = Constants.SHENZHOUWXPAY_TYPE;
//		}
		init();
	}

	public ThirdpayNetEngine(String payType) {
		mPayType = payType;
//		if(Constants.SHENZHOUWXPAY_TYPE.equals(payType)){
//			mPayType = Constants.SHENZHOUWXPAY_TYPE;
//		}
		init();
	}

	private void init() {
		mHttpMethod = HTTP_POST;

		if (Constants.ALIPAY_TYPE.equals(mPayType)) {
			mResultData = new AlipayResultData(mPayType);
		} else if (Constants.WXPAY_TYPE.equals(mPayType)
				||Constants.SHENZHOUWXPAY_TYPE.equals(mPayType)
				||Constants.SHENZHOUALIPAY_TYPE.equals(mPayType)) {
			mResultData = new WxpayResultData(mPayType);
		} else if (Constants.ZWJFPAY_TYPE.equals(mPayType)) {
			mResultData = new ZwjfpayResultData(mPayType);
		} else if(Constants.SMSPAY_TYPE_MMDO.equals(mPayType)){
			mResultData = new FastPaymentResultData(mPayType);
		}else if(Constants.YSDKPAY_TYPE.equals(mPayType)){
//			mResultData = new AlipayResultData(mPayType);
		}else {
			
		}
	}

	@Override
	protected Map<String, String> getParams(Context c) {
		return super.getParams(c);
	}

	public void setData(String sid, String amount, String cpText,
			String propsid, String imsi) {
		super.setData(sid, amount, cpText, propsid, imsi);
	}

	@Override
	protected String getCommand() {
		return mPayType;
	}

}
