package com.cheyooh.service.sdk.idata;

import com.cheyooh.service.framework.idata.ResultContent;


public class ResultPayWechatInfo extends ResultContent {
	
	private static final long serialVersionUID = 4481444857911540673L;

	private String appid;

	private String partnerid;

	private String prepayid;
	
	private String extra;
	
	private String noncestr;
	
	private String timestamp;
	
	private String sign;
	
	private String order_no;
	
	public ResultPayWechatInfo(String appid, String partnerid, String prepayid,
			String extra, String noncestr, String timestamp, String sign,
			String order_no) {
		super("order");
		this.appid = appid;
		this.partnerid = partnerid;
		this.prepayid = prepayid;
		this.extra = extra;
		this.noncestr = noncestr;
		this.timestamp = timestamp;
		this.sign = sign;
		this.order_no = order_no;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getPartnerid() {
		return partnerid;
	}

	public void setPartnerid(String partnerid) {
		this.partnerid = partnerid;
	}

	public String getPrepayid() {
		return prepayid;
	}

	public void setPrepayid(String prepayid) {
		this.prepayid = prepayid;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public String getNoncestr() {
		return noncestr;
	}

	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
	
}
