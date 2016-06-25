package com.cheyooh.service.sdk.idata.gameserver;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;

/**
 * 
 * @author ljg
 * 
 */
@XObject("SyncAppOrderReq")
public class XmlMmNotifyReq {

	@XNode(value = "TransactionID")
	private String TransactionID="";//

	@XNode(value = "MsgType")
	private String msgType;// 消息类型

	@XNode(value = "Version")
	private String version;// 版本号

	// private Device send_Address;//发送方地址

	// private Device dest_Address;//接收方地址
	@XNode(value = "OrderID")
	private String orderId;// 订单编号

	@XNode(value = "CheckID")
	private Integer checkId;// 开放平台订购，鉴权接口中的CheckID

	@XNode(value = "TradeID")
	private String tradeId;// 外部交易ID

	@XNode(value = "Price")
	private Integer price;// 业务资费(分)

	@XNode(value = "ActionTime")
	private String actionTime;// 操作时间

	@XNode(value = "ActionID")
	private Integer actionId;// 操作代码

	@XNode(value = "MSISDN")
	private String msisdn;// 目标用户手机号码

	@XNode(value = "FeeMSISDN")
	private String feeMsisdn;// 计费手机号码

	@XNode(value = "AppID")
	private String appId;// 应用ID

	@XNode(value = "ProgramID")
	private String programId;// 应用程序包ID

	@XNode(value = "PayCode")
	private String payCode;// 应用计费点编码

	@XNode(value = "TotalPrice")
	private Integer totalPrice;// 订购总价(分)

	@XNode(value = "SubsNumb")
	private Integer subsNumb;// 订购关系个数

	@XNode(value = "SubsSeq")
	private Integer subsSeq;// 档次同步的序号

	@XNode(value = "ChannelID")
	private String channelId;// 渠道ID

	@XNode(value = "ExData")
	private String exData;// 应用自定义参数

	@XNode(value = "OrderType")
	private String OrderType;// 订单类型（默认为0） 0：测试订单 1：正式订单

	@XNode(value = "OrderPayment")
	private Integer OrderPayment;// 订单支付方类型 1：移动 2：联通 3：电信 4：第三方支付：采用第三方支付方式

	@XNode(value = "MD5Sign")
	private String MD5Sign;// 32位大写MD5(OrderID# ChannelID#PayCode#AppKey)
							// 不允许跳字段，空字段保留

	public XmlMmNotifyReq(){
		
	}
	
	public String getTransactionID() {
		return TransactionID;
	}

	public void setTransactionID(String transactionID) {
		TransactionID = transactionID;
	}
	
	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getCheckId() {
		return checkId;
	}

	public void setCheckId(Integer checkId) {
		this.checkId = checkId;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getActionTime() {
		return actionTime;
	}

	public void setActionTime(String actionTime) {
		this.actionTime = actionTime;
	}

	public Integer getActionId() {
		return actionId;
	}

	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getFeeMsisdn() {
		return feeMsisdn;
	}

	public void setFeeMsisdn(String feeMsisdn) {
		this.feeMsisdn = feeMsisdn;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

	public String getPayCode() {
		return payCode;
	}

	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getSubsNumb() {
		return subsNumb;
	}

	public void setSubsNumb(Integer subsNumb) {
		this.subsNumb = subsNumb;
	}

	public Integer getSubsSeq() {
		return subsSeq;
	}

	public void setSubsSeq(Integer subsSeq) {
		this.subsSeq = subsSeq;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getExData() {
		return exData;
	}

	public void setExData(String exData) {
		this.exData = exData;
	}

	public String getOrderType() {
		return OrderType;
	}

	public void setOrderType(String orderType) {
		OrderType = orderType;
	}

	public Integer getOrderPayment() {
		return OrderPayment;
	}

	public void setOrderPayment(Integer orderPayment) {
		OrderPayment = orderPayment;
	}

	public String getMD5Sign() {
		return MD5Sign;
	}

	public void setMD5Sign(String mD5Sign) {
		MD5Sign = mD5Sign;
	}

}
