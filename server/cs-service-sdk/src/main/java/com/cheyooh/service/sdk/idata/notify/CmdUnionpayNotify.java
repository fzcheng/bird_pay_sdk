package com.cheyooh.service.sdk.idata.notify;

import com.cheyooh.service.framework.idata.Cmd;


/**
 * 银联通知参数
 * @author Jaly
 *
 */
public class CmdUnionpayNotify extends Cmd{

	/**
	 * 
	 */
	private static final long serialVersionUID = 775978054895142402L;

	/**
	 * 版本号
	 */
	private String version;
	/**
	 * 字符编码
	 */
	private String charset;
	/**
	 * 签名方法
	 */
	private String signMethod;
	/**
	 * 签名信息
	 */
	private String signature;
	/**
	 * 交易类型
	 */
	private String transType;
	/**
	 * 商户ID
	 */
	private String merId;
	/**
	 * 交易状态
	 */
	private String transStatus;
	/**
	 * 响应码
	 */
	private String respCode;
	/**
	 * 响应消息
	 */
	private String respMsg;
	/**
	 * 查询流水号
	 */
	private String qn;
	/**
	 * 商户订单号
	 */
	private String orderNumber;
	/**
	 * 交易开始日期时间
	 */
	private String orderTime;
	/**
	 * 清算金额
	 */
	private Float settleAmount;
	/**
	 * 清算币种
	 */
	private String settleCurrency;
	/**
	 * 清算日期
	 */
	private String settleDate;
	/**
	 * 清算汇率
	 */
	private String exchangeRate;
	/**
	 * 兑换日期
	 */
	private String exchangeDate;
	/**
	 * 商户保留域
	 */
	private String merReserved;
	/**
	 * 请求方保留域
	 */
	private String reqReserved;
	/**
	 * 系统保留域
	 */
	private String sysReserved;
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public String getSignMethod() {
		return signMethod;
	}
	public void setSignMethod(String signMethod) {
		this.signMethod = signMethod;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	public String getTransStatus() {
		return transStatus;
	}
	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getRespMsg() {
		return respMsg;
	}
	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}
	public String getQn() {
		return qn;
	}
	public void setQn(String qn) {
		this.qn = qn;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public Float getSettleAmount() {
		return settleAmount;
	}
	public void setSettleAmount(Float settleAmount) {
		this.settleAmount = settleAmount;
	}
	public String getSettleCurrency() {
		return settleCurrency;
	}
	public void setSettleCurrency(String settleCurrency) {
		this.settleCurrency = settleCurrency;
	}
	public String getSettleDate() {
		return settleDate;
	}
	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}
	public String getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public String getExchangeDate() {
		return exchangeDate;
	}
	public void setExchangeDate(String exchangeDate) {
		this.exchangeDate = exchangeDate;
	}
	public String getMerReserved() {
		return merReserved;
	}
	public void setMerReserved(String merReserved) {
		this.merReserved = merReserved;
	}
	public String getReqReserved() {
		return reqReserved;
	}
	public void setReqReserved(String reqReserved) {
		this.reqReserved = reqReserved;
	}
	public String getSysReserved() {
		return sysReserved;
	}
	public void setSysReserved(String sysReserved) {
		this.sysReserved = sysReserved;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
