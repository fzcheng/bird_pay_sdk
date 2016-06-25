package jeecg.ext.sdk.entity;

import java.util.Date;

import org.jeecgframework.core.annotation.Excel;

public class SdkNotifyMmdoExcel {
	
	@Excel(exportName="创建时间", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	private Date createTime;	
	@Excel(exportName="订单号", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	private String orderNo;
	@Excel(exportName="订单状态", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	private String notifyStatus;
	@Excel(exportName="运营商类型", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	private String operationType;
	@Excel(exportName="支付渠道名称", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	private String payChannelCode;
	@Excel(exportName="游戏名称", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	private String gameId;
	@Excel(exportName="单价金额", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	private Float amount;
	@Excel(exportName="是否补点", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	private String additionalStatus;
	@Excel(exportName="手机号码", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	private String mobile;
	@Excel(exportName="外部交易编号", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	private String linkid;
	@Excel(exportName="状态描述", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	private String statusDetail;
	@Excel(exportName="原始状态码", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	private String originalcode;
	@Excel(exportName="业务代码", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	private String spid;
	@Excel(exportName="上行指令", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	private String cmd;
	@Excel(exportName="端口", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	private String spnum;
	@Excel(exportName="省份", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	private String key;
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public String getPayChannelCode() {
		return payChannelCode;
	}
	public void setPayChannelCode(String payChannelCode) {
		this.payChannelCode = payChannelCode;
	}
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public String getAdditionalStatus() {
		return additionalStatus;
	}
	public void setAdditionalStatus(String additionalStatus) {
		this.additionalStatus = additionalStatus;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getLinkid() {
		return linkid;
	}
	public void setLinkid(String linkid) {
		this.linkid = linkid;
	}
	public String getSpid() {
		return spid;
	}
	public void setSpid(String spid) {
		this.spid = spid;
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public String getSpnum() {
		return spnum;
	}
	public void setSpnum(String spnum) {
		this.spnum = spnum;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getNotifyStatus() {
		return notifyStatus;
	}
	public void setNotifyStatus(String notifyStatus) {
		this.notifyStatus = notifyStatus;
	}
	public String getStatusDetail() {
		return statusDetail;
	}
	public void setStatusDetail(String statusDetail) {
		this.statusDetail = statusDetail;
	}
	public String getOriginalcode() {
		return originalcode;
	}
	public void setOriginalcode(String originalcode) {
		this.originalcode = originalcode;
	}
	
}
