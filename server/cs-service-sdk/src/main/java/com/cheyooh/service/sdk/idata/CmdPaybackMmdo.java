package com.cheyooh.service.sdk.idata;

public class CmdPaybackMmdo extends CmdGeneral{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8514508672089451316L;

	private String imsi;
	
	private String order_no;
	
	private String number;
	
	private String content;
	
	private String state;
	
	private String sms_type;

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSms_type() {
		return sms_type;
	}

	public void setSms_type(String sms_type) {
		this.sms_type = sms_type;
	}
	
	
}
