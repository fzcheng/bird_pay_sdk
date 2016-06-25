package com.legame.paysdk.models;

import java.io.Serializable;

/** 
 * 类说明：   
 * @author  Terry.Lu
 * @date    2013年10月17日
 * @version 1.0
 */
public class PaymentType implements Serializable {
	private static final long serialVersionUID = 2866403110147980333L;
	public static final int PAYMTENT_TYPE_ALIPAY = 0;
	public static final int PAYMTENT_TYPE_TENPAY = 1;
	public static final int PAYMTENT_TYPE_PHONE_RECHARGABLE_CARD = 2;
	public static final int PAYMTENT_TYPE_GAME_RECHARGABLE_CARD = 3;
	public static final int PAYMTENT_TYPE_MO9 = 4;
	public static final int PAYMTENT_TYPE_WEIPAI = 5;
	public static final int PAYMTENT_TYPE_UNION_PAY = 6;
	public static final int PAYMTENT_TYPE_UPAY = 7;
	public static final int PAYMTENT_TYPE_VPAY = 8;
	public static final int PAYMTENT_TYPE_MDO = 9;
	
	private String paymentName;
	private int drawableId;
	private int type;
	private boolean isShow;
	private String paymentType; //值为alipay, tenpay, yeepay, mo9, wiipay, unionpay, upay, vpay
	
	public PaymentType(String paymentType, boolean isShow) {
		this.paymentType = paymentType;
		this.isShow = isShow;
	}
	
	public PaymentType(String paymentName, int drawableId, int type) {
		this.paymentName = paymentName;
		this.drawableId = drawableId;
		this.type = type;
	}
	
	public int getType() {
		return this.type;
	}
	
	public String getPaymentName() {
		return paymentName;
	}
	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}
	public int getDrawableId() {
		return drawableId;
	}
	public void setDrawableId(int drawableId) {
		this.drawableId = drawableId;
	}

	public boolean isShow() {
		return isShow;
	}

	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public PaymentType setPaymentType(String paymentType) {
		this.paymentType = paymentType;
		return this;
	}
}
