package jeecg.ext.sdk.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Transient;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SdkNotifyMmdo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_notify_mmdo", catalog = "game_sdk")
public class SdkNotifyMmdo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7302331434392058753L;
	private Integer id;
	private String linkid;
	private String spid;
	private String cmd;
	private String mobile;
	private String spnum;
	private String key;
	private Integer gameId;
	private Float amount;
	private Date createTime;
	private Integer operationType;
	private String payChannelCode;
	private Integer additionalStatus;
	private String orderNo;
	private String statusDetail;
	private String originalcode;
	private Integer notifyStatus;
	private String payChannelName;

	// Constructors

	/** default constructor */
	public SdkNotifyMmdo() {
	}

	/** minimal constructor */
	public SdkNotifyMmdo(Integer notifyStatus) {
		this.notifyStatus = notifyStatus;
	}

	/** full constructor */
	public SdkNotifyMmdo(String linkid, String spid, String cmd, String mobile,
			String spnum, String key, Integer gameId, Float amount,
			Timestamp createTime, Integer operationType, String payChannelCode,
			Integer additionalStatus, String orderNo, String statusDetail,
			String originalcode, Integer notifyStatus) {
		this.linkid = linkid;
		this.spid = spid;
		this.cmd = cmd;
		this.mobile = mobile;
		this.spnum = spnum;
		this.key = key;
		this.gameId = gameId;
		this.amount = amount;
		this.createTime = createTime;
		this.operationType = operationType;
		this.payChannelCode = payChannelCode;
		this.additionalStatus = additionalStatus;
		this.orderNo = orderNo;
		this.statusDetail = statusDetail;
		this.originalcode = originalcode;
		this.notifyStatus = notifyStatus;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "linkid", length = 64)
	public String getLinkid() {
		return this.linkid;
	}

	public void setLinkid(String linkid) {
		this.linkid = linkid;
	}

	@Column(name = "spid", length = 25)
	public String getSpid() {
		return this.spid;
	}

	public void setSpid(String spid) {
		this.spid = spid;
	}

	@Column(name = "cmd", length = 256)
	public String getCmd() {
		return this.cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	@Column(name = "mobile", length = 64)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "spnum", length = 25)
	public String getSpnum() {
		return this.spnum;
	}

	public void setSpnum(String spnum) {
		this.spnum = spnum;
	}

	@Column(name = "key", length = 20)
	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Column(name = "game_id")
	public Integer getGameId() {
		return this.gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	@Column(name = "amount", precision = 12, scale = 0)
	public Float getAmount() {
		return this.amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "operation_type")
	public Integer getOperationType() {
		return this.operationType;
	}

	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}

	@Column(name = "pay_channel_code", length = 32)
	public String getPayChannelCode() {
		return this.payChannelCode;
	}

	public void setPayChannelCode(String payChannelCode) {
		this.payChannelCode = payChannelCode;
	}

	@Column(name = "additional_status")
	public Integer getAdditionalStatus() {
		return this.additionalStatus;
	}

	public void setAdditionalStatus(Integer additionalStatus) {
		this.additionalStatus = additionalStatus;
	}

	@Column(name = "order_no", length = 64)
	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name = "status_detail", length = 32)
	public String getStatusDetail() {
		return this.statusDetail;
	}

	public void setStatusDetail(String statusDetail) {
		this.statusDetail = statusDetail;
	}

	@Column(name = "originalcode", length = 32)
	public String getOriginalcode() {
		return this.originalcode;
	}

	public void setOriginalcode(String originalcode) {
		this.originalcode = originalcode;
	}

	@Column(name = "notify_status", nullable = false)
	public Integer getNotifyStatus() {
		return this.notifyStatus;
	}

	public void setNotifyStatus(Integer notifyStatus) {
		this.notifyStatus = notifyStatus;
	}

	@Transient
	public String getPayChannelName() {
		return payChannelName;
	}

	public void setPayChannelName(String payChannelName) {
		this.payChannelName = payChannelName;
	}

	
}