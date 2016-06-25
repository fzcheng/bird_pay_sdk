package com.cheyooh.service.sdk.idata;

import com.cheyooh.service.framework.utils.annotation.NotNull;

public class CmdPayYeepay extends CmdPay{
 
	private static final long serialVersionUID = -1951052784254347901L;

	 
	
	/**
	 * : 支付渠道编码(参考易宝支付的"支付通道编码列表")
	 */
	@NotNull
	private String frpid;
	
	/**
	 * : 卡号
	 */
	@NotNull
	private String card_no;
	
	/**
	 * 卡面额
	 */
	@NotNull
	private String card_amt;
	
	/**
	 * : 密码
	 */
	@NotNull
	private String card_pwd;
 

	public String getFrpid() {
		return frpid;
	}

	public void setFrpid(String frpid) {
		this.frpid = frpid;
	}

	public String getCard_no() {
		return card_no;
	}

	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}

	public String getCard_pwd() {
		return card_pwd;
	}

	public void setCard_pwd(String card_pwd) {
		this.card_pwd = card_pwd;
	}

	public String getCard_amt() {
		return card_amt;
	}

	public void setCard_amt(String card_amt) {
		this.card_amt = card_amt;
	}
 
}
