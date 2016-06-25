package com.cheyooh.service.sdk.idata.gameserver;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;


@XObject("notify")
public class XmlAlipayNotify {
	/*
	<notify>
		<trade_status>TRADE_FINISHED</trade_status>
		<total_fee>0.90</total_fee>
		<subject>123456</subject>
		<out_trade_no>1118060201-7555</out_trade_no>
		<notify_reg_time>2013-11-1112:33:33.000</notify_reg_time>
		<trade_no>1122334455</trade_no>
	</notify>		  
	 */
	
	@XNode(value="trade_status")
	private String trade_status;
	
	@XNode(value="total_fee")
	private float total_fee;
	
	@XNode(value="subject")
	private String subject;
	
	@XNode(value="out_trade_no")
	private String out_trade_no;
	
	@XNode(value="notify_reg_time")
	private String notify_reg_time;
	
	@XNode(value="trade_no")
	private String trade_no;

	public String getTrade_status() {
		return trade_status;
	}

	public void setTrade_status(String trade_status) {
		this.trade_status = trade_status;
	}

	public float getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(float total_fee) {
		this.total_fee = total_fee;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getNotify_reg_time() {
		return notify_reg_time;
	}

	public void setNotify_reg_time(String notify_reg_time) {
		this.notify_reg_time = notify_reg_time;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
}
