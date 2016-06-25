package com.cheyooh.service.sdk.idata.gameserver;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;

@XObject("response")
public class XmlCmccjdSmsRes {
	@XNode(value = "content_sid")
	private String content_sid;
	
	@XNode(value = "price")
	private String price;
	
	@XNode(value = "sms_1_num")
	private String sms_1_num;
	
	@XNode(value = "sms_1")
	private String sms_1;

	public String getContent_sid() {
		return content_sid;
	}

	public void setContent_sid(String content_sid) {
		this.content_sid = content_sid;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getSms_1_num() {
		return sms_1_num;
	}

	public void setSms_1_num(String sms_1_num) {
		this.sms_1_num = sms_1_num;
	}

	public String getSms_1() {
		return sms_1;
	}

	public void setSms_1(String sms_1) {
		this.sms_1 = sms_1;
	}

	@Override
	public String toString() {
		return "XmlCmccjdSmsReq [content_sid=" + content_sid + ", price="
				+ price + ", sms_1_num=" + sms_1_num + ", sms_1=" + sms_1 + "]";
	}
	
	
}
