package com.cheyooh.service.sdk.idata;

import com.cheyooh.service.sdk.cfg.Cfg;

public class CmdPay extends CmdGeneral {

	private static final long serialVersionUID = -1951052784254347901L;

	/**
	 * : 支付金额(单位:元)
	 */
	private float amount;

	/**
	 * : 游戏服务商扩展信息(通知游
	 */
	private String cp_ext;

	/**
	 * 订单说明
	 */
	private String order_desc = Cfg.cfg.getString("sdk.pay.order.desc", "游戏支付");

	/**
	 * 订单名称
	 */
	private String order_name = Cfg.cfg.getString("sdk.pay.order.name", "游戏");

	/**
	 * sdk版本
	 */
	private String sdkver;

	/**
	 * imsi
	 */
	private String imsi;

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getCp_ext() {
		return cp_ext;
	}

	public void setCp_ext(String cp_ext) {
		this.cp_ext = cp_ext;
	}

	public String getOrder_desc() {
		return order_desc;
	}

	public void setOrder_desc(String order_desc) {
		this.order_desc = order_desc;
	}

	public String getOrder_name() {
		return order_name;
	}

	public void setOrder_name(String order_name) {
		this.order_name = order_name;
	}

	public String getSdkver() {
		return sdkver;
	}

	public void setSdkver(String sdkver) {
		this.sdkver = sdkver;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

}
