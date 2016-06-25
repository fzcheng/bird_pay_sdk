package jeecg.ext.sdk.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * SdkOrderCopy entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_order_copy", catalog = "game_sdk")
public class SdkOrderCopy implements java.io.Serializable {

	// Fields

	private String orderNo;
	private SdkGame sdkGame;
	private SdkUser sdkUser;
	private SdkPayment sdkPayment;
	private SdkGameCp sdkGameCp;
	private String orderName;
	private String orderDesc;
	private Integer payId;
	private Float amount;
	private String channel;
	private Integer status;
	private String statusDetail;
	private String cpExt;
	private Integer notifyStatus;
	private String notifyRequest;
	private String notifyResponse;
	private Timestamp notifyTime;
	private Timestamp createTime;
	private Timestamp completeTime;

	// Constructors

	/** default constructor */
	public SdkOrderCopy() {
	}

	/** minimal constructor */
	public SdkOrderCopy(String orderNo, SdkGame sdkGame, SdkUser sdkUser,
			SdkPayment sdkPayment, SdkGameCp sdkGameCp, String orderName,
			String orderDesc, Integer payId, Float amount, String channel,
			Integer status, Integer notifyStatus, Timestamp createTime) {
		this.orderNo = orderNo;
		this.sdkGame = sdkGame;
		this.sdkUser = sdkUser;
		this.sdkPayment = sdkPayment;
		this.sdkGameCp = sdkGameCp;
		this.orderName = orderName;
		this.orderDesc = orderDesc;
		this.payId = payId;
		this.amount = amount;
		this.channel = channel;
		this.status = status;
		this.notifyStatus = notifyStatus;
		this.createTime = createTime;
	}

	/** full constructor */
	public SdkOrderCopy(String orderNo, SdkGame sdkGame, SdkUser sdkUser,
			SdkPayment sdkPayment, SdkGameCp sdkGameCp, String orderName,
			String orderDesc, Integer payId, Float amount, String channel,
			Integer status, String statusDetail, String cpExt,
			Integer notifyStatus, String notifyRequest, String notifyResponse,
			Timestamp notifyTime, Timestamp createTime, Timestamp completeTime) {
		this.orderNo = orderNo;
		this.sdkGame = sdkGame;
		this.sdkUser = sdkUser;
		this.sdkPayment = sdkPayment;
		this.sdkGameCp = sdkGameCp;
		this.orderName = orderName;
		this.orderDesc = orderDesc;
		this.payId = payId;
		this.amount = amount;
		this.channel = channel;
		this.status = status;
		this.statusDetail = statusDetail;
		this.cpExt = cpExt;
		this.notifyStatus = notifyStatus;
		this.notifyRequest = notifyRequest;
		this.notifyResponse = notifyResponse;
		this.notifyTime = notifyTime;
		this.createTime = createTime;
		this.completeTime = completeTime;
	}

	// Property accessors
	@Id
	@Column(name = "order_no", unique = true, nullable = false, length = 64)
	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "game_id", nullable = false)
	public SdkGame getSdkGame() {
		return this.sdkGame;
	}

	public void setSdkGame(SdkGame sdkGame) {
		this.sdkGame = sdkGame;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uid", nullable = false)
	public SdkUser getSdkUser() {
		return this.sdkUser;
	}

	public void setSdkUser(SdkUser sdkUser) {
		this.sdkUser = sdkUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type", nullable = false)
	public SdkPayment getSdkPayment() {
		return this.sdkPayment;
	}

	public void setSdkPayment(SdkPayment sdkPayment) {
		this.sdkPayment = sdkPayment;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cp_id", nullable = false)
	public SdkGameCp getSdkGameCp() {
		return this.sdkGameCp;
	}

	public void setSdkGameCp(SdkGameCp sdkGameCp) {
		this.sdkGameCp = sdkGameCp;
	}

	@Column(name = "order_name", nullable = false, length = 64)
	public String getOrderName() {
		return this.orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	@Column(name = "order_desc", nullable = false, length = 32)
	public String getOrderDesc() {
		return this.orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	@Column(name = "pay_id", nullable = false)
	public Integer getPayId() {
		return this.payId;
	}

	public void setPayId(Integer payId) {
		this.payId = payId;
	}

	@Column(name = "amount", nullable = false, precision = 12, scale = 0)
	public Float getAmount() {
		return this.amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	@Column(name = "channel", nullable = false, length = 64)
	public String getChannel() {
		return this.channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	@Column(name = "status", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "status_detail", length = 256)
	public String getStatusDetail() {
		return this.statusDetail;
	}

	public void setStatusDetail(String statusDetail) {
		this.statusDetail = statusDetail;
	}

	@Column(name = "cp_ext", length = 512)
	public String getCpExt() {
		return this.cpExt;
	}

	public void setCpExt(String cpExt) {
		this.cpExt = cpExt;
	}

	@Column(name = "notify_status", nullable = false)
	public Integer getNotifyStatus() {
		return this.notifyStatus;
	}

	public void setNotifyStatus(Integer notifyStatus) {
		this.notifyStatus = notifyStatus;
	}

	@Column(name = "notify_request", length = 2048)
	public String getNotifyRequest() {
		return this.notifyRequest;
	}

	public void setNotifyRequest(String notifyRequest) {
		this.notifyRequest = notifyRequest;
	}

	@Column(name = "notify_response", length = 256)
	public String getNotifyResponse() {
		return this.notifyResponse;
	}

	public void setNotifyResponse(String notifyResponse) {
		this.notifyResponse = notifyResponse;
	}

	@Column(name = "notify_time", length = 19)
	public Timestamp getNotifyTime() {
		return this.notifyTime;
	}

	public void setNotifyTime(Timestamp notifyTime) {
		this.notifyTime = notifyTime;
	}

	@Column(name = "create_time", nullable = false, length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "complete_time", length = 19)
	public Timestamp getCompleteTime() {
		return this.completeTime;
	}

	public void setCompleteTime(Timestamp completeTime) {
		this.completeTime = completeTime;
	}

}