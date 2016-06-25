package jeecg.ext.sdk.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SdkOrderAlipay entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_order_alipay", catalog = "game_sdk")
public class SdkOrderAlipay implements java.io.Serializable {

	// Fields

	private Integer payId;
	private Timestamp reqTime;
	private String reqPartner;
	private String reqSeller;
	private String reqOutTradeNo;
	private String reqSubject;
	private String reqBody;
	private Float reqTotalFee;
	private String reqNotifyUrl;
	private Timestamp notifyTime;
	private String notifyTradeStatus;
	private Float notifyTotalFee;
	private String notifySubject;
	private String notifyOutTradeNo;
	private String notifyNotifyRegTime;
	private String notifyTradeNo;

	// Constructors

	/** default constructor */
	public SdkOrderAlipay() {
	}

	/** minimal constructor */
	public SdkOrderAlipay(Timestamp reqTime, String reqPartner,
			String reqSeller, String reqOutTradeNo, String reqSubject,
			String reqBody, Float reqTotalFee, String reqNotifyUrl) {
		this.reqTime = reqTime;
		this.reqPartner = reqPartner;
		this.reqSeller = reqSeller;
		this.reqOutTradeNo = reqOutTradeNo;
		this.reqSubject = reqSubject;
		this.reqBody = reqBody;
		this.reqTotalFee = reqTotalFee;
		this.reqNotifyUrl = reqNotifyUrl;
	}

	/** full constructor */
	public SdkOrderAlipay(Timestamp reqTime, String reqPartner,
			String reqSeller, String reqOutTradeNo, String reqSubject,
			String reqBody, Float reqTotalFee, String reqNotifyUrl,
			Timestamp notifyTime, String notifyTradeStatus,
			Float notifyTotalFee, String notifySubject,
			String notifyOutTradeNo, String notifyNotifyRegTime,
			String notifyTradeNo) {
		this.reqTime = reqTime;
		this.reqPartner = reqPartner;
		this.reqSeller = reqSeller;
		this.reqOutTradeNo = reqOutTradeNo;
		this.reqSubject = reqSubject;
		this.reqBody = reqBody;
		this.reqTotalFee = reqTotalFee;
		this.reqNotifyUrl = reqNotifyUrl;
		this.notifyTime = notifyTime;
		this.notifyTradeStatus = notifyTradeStatus;
		this.notifyTotalFee = notifyTotalFee;
		this.notifySubject = notifySubject;
		this.notifyOutTradeNo = notifyOutTradeNo;
		this.notifyNotifyRegTime = notifyNotifyRegTime;
		this.notifyTradeNo = notifyTradeNo;
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

	@Column(name = "req_partner", nullable = false, length = 64)
	public String getReqPartner() {
		return this.reqPartner;
	}

	public void setReqPartner(String reqPartner) {
		this.reqPartner = reqPartner;
	}

	@Column(name = "req_seller", nullable = false, length = 64)
	public String getReqSeller() {
		return this.reqSeller;
	}

	public void setReqSeller(String reqSeller) {
		this.reqSeller = reqSeller;
	}

	@Column(name = "req_out_trade_no", nullable = false, length = 64)
	public String getReqOutTradeNo() {
		return this.reqOutTradeNo;
	}

	public void setReqOutTradeNo(String reqOutTradeNo) {
		this.reqOutTradeNo = reqOutTradeNo;
	}

	@Column(name = "req_subject", nullable = false, length = 64)
	public String getReqSubject() {
		return this.reqSubject;
	}

	public void setReqSubject(String reqSubject) {
		this.reqSubject = reqSubject;
	}

	@Column(name = "req_body", nullable = false, length = 64)
	public String getReqBody() {
		return this.reqBody;
	}

	public void setReqBody(String reqBody) {
		this.reqBody = reqBody;
	}

	@Column(name = "req_total_fee", nullable = false, precision = 12, scale = 0)
	public Float getReqTotalFee() {
		return this.reqTotalFee;
	}

	public void setReqTotalFee(Float reqTotalFee) {
		this.reqTotalFee = reqTotalFee;
	}

	@Column(name = "req_notify_url", nullable = false, length = 256)
	public String getReqNotifyUrl() {
		return this.reqNotifyUrl;
	}

	public void setReqNotifyUrl(String reqNotifyUrl) {
		this.reqNotifyUrl = reqNotifyUrl;
	}

	@Column(name = "notify_time", length = 19)
	public Timestamp getNotifyTime() {
		return this.notifyTime;
	}

	public void setNotifyTime(Timestamp notifyTime) {
		this.notifyTime = notifyTime;
	}

	@Column(name = "notify_trade_status")
	public String getNotifyTradeStatus() {
		return this.notifyTradeStatus;
	}

	public void setNotifyTradeStatus(String notifyTradeStatus) {
		this.notifyTradeStatus = notifyTradeStatus;
	}

	@Column(name = "notify_total_fee", precision = 12, scale = 0)
	public Float getNotifyTotalFee() {
		return this.notifyTotalFee;
	}

	public void setNotifyTotalFee(Float notifyTotalFee) {
		this.notifyTotalFee = notifyTotalFee;
	}

	@Column(name = "notify_subject", length = 64)
	public String getNotifySubject() {
		return this.notifySubject;
	}

	public void setNotifySubject(String notifySubject) {
		this.notifySubject = notifySubject;
	}

	@Column(name = "notify_out_trade_no", length = 64)
	public String getNotifyOutTradeNo() {
		return this.notifyOutTradeNo;
	}

	public void setNotifyOutTradeNo(String notifyOutTradeNo) {
		this.notifyOutTradeNo = notifyOutTradeNo;
	}

	@Column(name = "notify_notify_reg_time", length = 64)
	public String getNotifyNotifyRegTime() {
		return this.notifyNotifyRegTime;
	}

	public void setNotifyNotifyRegTime(String notifyNotifyRegTime) {
		this.notifyNotifyRegTime = notifyNotifyRegTime;
	}

	@Column(name = "notify_trade_no", length = 64)
	public String getNotifyTradeNo() {
		return this.notifyTradeNo;
	}

	public void setNotifyTradeNo(String notifyTradeNo) {
		this.notifyTradeNo = notifyTradeNo;
	}

}