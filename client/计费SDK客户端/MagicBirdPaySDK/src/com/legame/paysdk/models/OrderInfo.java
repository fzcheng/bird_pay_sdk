package com.legame.paysdk.models;

import java.io.Serializable;

/** 
 * 类说明：   
 * @author  Terry.Lu
 * @date    2013年10月15日
 * @version 1.0
 */
public class OrderInfo implements Serializable {
	private static final long serialVersionUID = -2494513253437854694L;
	
	private String orderNo;
	private String payInfo;
    private String sms_type = "";//woappstore:沃商店
    private int  sms_pay_type = 1;// 短信方式，1：短信指令，2：网络获取，3：SDK接入
    private String sms_content_type;//发送短信的方式：1.字符串发送，2.二进制方式发送，3.多条短信
    
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
	public String getSms_Type() {
		return sms_type;
	}
	public void setSms_Type(String sms_type) {
		this.sms_type = sms_type;
	}
	public int getSms_pay_type() {
		return sms_pay_type;
	}
	public void setSms_pay_type(int sms_pay_type) {
		this.sms_pay_type = sms_pay_type;
	}
	public String getSms_content_type() {
		return sms_content_type;
	}
	public void setSms_content_type(String sms_content_type) {
		this.sms_content_type = sms_content_type;
	}
}
