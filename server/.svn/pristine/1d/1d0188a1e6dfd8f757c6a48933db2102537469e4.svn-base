package jeecg.ext.sdk.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SdkOrderWiipay entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_order_wiipay", catalog = "game_sdk")
public class SdkOrderWiipay implements java.io.Serializable {

	// Fields

	private Integer payId;
	private Timestamp reqTime;
	private String reqOrder;
	private Float reqAmt;
	private String reqPayCode;
	private Timestamp notifyDate;
	private String notifyOperatorType;
	private String notifyOperatorTypeTile;
	private String notifyChannelCode;
	private String notifyAppCode;
	private String notifyPayCode;
	private String notifyImsi;
	private String notifyTel;
	private String notifyState;
	private String notifyBookNo;
	private Float notifyPrice;
	private String notifySynType;

	// Constructors

	/** default constructor */
	public SdkOrderWiipay() {
	}

	/** minimal constructor */
	public SdkOrderWiipay(Timestamp reqTime, String reqOrder, Float reqAmt) {
		this.reqTime = reqTime;
		this.reqOrder = reqOrder;
		this.reqAmt = reqAmt;
	}

	/** full constructor */
	public SdkOrderWiipay(Timestamp reqTime, String reqOrder, Float reqAmt,
			String reqPayCode, Timestamp notifyDate, String notifyOperatorType,
			String notifyOperatorTypeTile, String notifyChannelCode,
			String notifyAppCode, String notifyPayCode, String notifyImsi,
			String notifyTel, String notifyState, String notifyBookNo,
			Float notifyPrice, String notifySynType) {
		this.reqTime = reqTime;
		this.reqOrder = reqOrder;
		this.reqAmt = reqAmt;
		this.reqPayCode = reqPayCode;
		this.notifyDate = notifyDate;
		this.notifyOperatorType = notifyOperatorType;
		this.notifyOperatorTypeTile = notifyOperatorTypeTile;
		this.notifyChannelCode = notifyChannelCode;
		this.notifyAppCode = notifyAppCode;
		this.notifyPayCode = notifyPayCode;
		this.notifyImsi = notifyImsi;
		this.notifyTel = notifyTel;
		this.notifyState = notifyState;
		this.notifyBookNo = notifyBookNo;
		this.notifyPrice = notifyPrice;
		this.notifySynType = notifySynType;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "pay_id", unique = true, nullable = false)
	public Integer getPayId() {
		return this.payId;
	}

	public void setPayId(Integer payId) {
		this.payId = payId;
	}

	@Column(name = "req_time", nullable = false, length = 19)
	public Timestamp getReqTime() {
		return this.reqTime;
	}

	public void setReqTime(Timestamp reqTime) {
		this.reqTime = reqTime;
	}

	@Column(name = "req_Order", nullable = false, length = 50)
	public String getReqOrder() {
		return this.reqOrder;
	}

	public void setReqOrder(String reqOrder) {
		this.reqOrder = reqOrder;
	}

	@Column(name = "req_Amt", nullable = false, precision = 12, scale = 0)
	public Float getReqAmt() {
		return this.reqAmt;
	}

	public void setReqAmt(Float reqAmt) {
		this.reqAmt = reqAmt;
	}

	@Column(name = "req_payCode", length = 10)
	public String getReqPayCode() {
		return this.reqPayCode;
	}

	public void setReqPayCode(String reqPayCode) {
		this.reqPayCode = reqPayCode;
	}

	@Column(name = "notify_date", length = 19)
	public Timestamp getNotifyDate() {
		return this.notifyDate;
	}

	public void setNotifyDate(Timestamp notifyDate) {
		this.notifyDate = notifyDate;
	}

	@Column(name = "notify_operatorType", length = 20)
	public String getNotifyOperatorType() {
		return this.notifyOperatorType;
	}

	public void setNotifyOperatorType(String notifyOperatorType) {
		this.notifyOperatorType = notifyOperatorType;
	}

	@Column(name = "notify_operatorTypeTile", length = 20)
	public String getNotifyOperatorTypeTile() {
		return this.notifyOperatorTypeTile;
	}

	public void setNotifyOperatorTypeTile(String notifyOperatorTypeTile) {
		this.notifyOperatorTypeTile = notifyOperatorTypeTile;
	}

	@Column(name = "notify_channelCode", length = 10)
	public String getNotifyChannelCode() {
		return this.notifyChannelCode;
	}

	public void setNotifyChannelCode(String notifyChannelCode) {
		this.notifyChannelCode = notifyChannelCode;
	}

	@Column(name = "notify_appCode", length = 10)
	public String getNotifyAppCode() {
		return this.notifyAppCode;
	}

	public void setNotifyAppCode(String notifyAppCode) {
		this.notifyAppCode = notifyAppCode;
	}

	@Column(name = "notify_payCode", length = 10)
	public String getNotifyPayCode() {
		return this.notifyPayCode;
	}

	public void setNotifyPayCode(String notifyPayCode) {
		this.notifyPayCode = notifyPayCode;
	}

	@Column(name = "notify_imsi", length = 100)
	public String getNotifyImsi() {
		return this.notifyImsi;
	}

	public void setNotifyImsi(String notifyImsi) {
		this.notifyImsi = notifyImsi;
	}

	@Column(name = "notify_tel", length = 100)
	public String getNotifyTel() {
		return this.notifyTel;
	}

	public void setNotifyTel(String notifyTel) {
		this.notifyTel = notifyTel;
	}

	@Column(name = "notify_state", length = 10)
	public String getNotifyState() {
		return this.notifyState;
	}

	public void setNotifyState(String notifyState) {
		this.notifyState = notifyState;
	}

	@Column(name = "notify_bookNo", length = 100)
	public String getNotifyBookNo() {
		return this.notifyBookNo;
	}

	public void setNotifyBookNo(String notifyBookNo) {
		this.notifyBookNo = notifyBookNo;
	}

	@Column(name = "notify_price", precision = 12, scale = 0)
	public Float getNotifyPrice() {
		return this.notifyPrice;
	}

	public void setNotifyPrice(Float notifyPrice) {
		this.notifyPrice = notifyPrice;
	}

	@Column(name = "notify_synType", length = 30)
	public String getNotifySynType() {
		return this.notifySynType;
	}

	public void setNotifySynType(String notifySynType) {
		this.notifySynType = notifySynType;
	}

}