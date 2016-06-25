package com.cheyooh.service.sdk.idata;

import com.cheyooh.service.framework.idata.Cmd;

public class CmdWiiPayOrder extends Cmd{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9091884699765681524L;
	
	/**
	 * 运营商类型
	 */
	private String operatorType;
	/**
	 * 运营商名
	 */
	private String operatorTypeTile;
	/**
	 * 渠道号
	 */
	private String channelCode;
	/**
	 * 应用编号
	 */
	private String appCode;
	/**
	 * 计费编号
	 */
	private String payCode;
	/**
	 * 手机卡imsi
	 */
	private String imsi;
	/**
	 * 手机号
	 */
	private String tel;
	/**
	 * success表示成功，其他一切表示失败
	 */
	private String state;
	/**
	 * 订单编号唯一
	 */
	private String bookNo;
	/**
	 * 订单时间
	 */
	private String date;
	/**
	 * 交易金额
	 */
	private String price;
	/**
	 * 自定义参数（为base64密码,反base64密码后是个json字字符串）
	 */
	private String devPrivate;
	/**
	 * 支付类型刑
	 */
	private String synType;
	public String getOperatorType() {
		return operatorType;
	}
	public void setOperatorType(String operatorType) {
		this.operatorType = operatorType;
	}
	public String getOperatorTypeTile() {
		return operatorTypeTile;
	}
	public void setOperatorTypeTile(String operatorTypeTile) {
		this.operatorTypeTile = operatorTypeTile;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	public String getPayCode() {
		return payCode;
	}
	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getBookNo() {
		return bookNo;
	}
	public void setBookNo(String bookNo) {
		this.bookNo = bookNo;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDevPrivate() {
		return devPrivate;
	}
	public void setDevPrivate(String devPrivate) {
		this.devPrivate = devPrivate;
	}
	public String getSynType() {
		return synType;
	}
	public void setSynType(String synType) {
		this.synType = synType;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
