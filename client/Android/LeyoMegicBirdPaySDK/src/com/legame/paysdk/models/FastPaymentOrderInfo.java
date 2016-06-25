package com.legame.paysdk.models;

import java.io.Serializable;

/** 
 * 类说明：   
 * @author  huangliang
 * @date    2014-2-25
 * @version 1.0
 */
public class FastPaymentOrderInfo implements Serializable{
	private static final long serialVersionUID = -3611030887049884415L;

	private String type;
	private String orderNo;
	private String payInfo;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getPayInfo() {
		return payInfo;
	}
	public void setPayInfo(String payInfo) {
		this.payInfo = payInfo;
	}

}
