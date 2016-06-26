package com.legame.paysdk.models;

import java.io.Serializable;
/**
 * 类说明：订单类
 * @author Kaiguang
 * @date 2015/4/15
 *
 */
public class Order implements Serializable{

	private static final long serialVersionUID = 6243500184351721461L;
	
	private OrderInfo mOrderInfo;
	
	private Commands mCommands;

	public OrderInfo getOrderInfo() {
		return mOrderInfo;
	}

	public void setOrderInfo(OrderInfo mOrderInfo) {
		this.mOrderInfo = mOrderInfo;
	}

	public Commands getCommands() {
		return mCommands;
	}

	public void setCommands(Commands mCommands) {
		this.mCommands = mCommands;
	}
}
