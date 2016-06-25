package jeecg.ext.sdk.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SdkOrderTenpay entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_order_tenpay", catalog = "game_sdk")
public class SdkOrderTenpay implements java.io.Serializable {

	// Fields

	private Integer payId;
	private Timestamp reqTime;
	private String reqVer;
	private String reqSalePlat;
	private String reqCharset;
	private Integer reqBankType;
	private String reqDesc;
	private String reqPurchaserId;
	private String reqBargainorId;
	private String reqSpBillno;
	private Float reqTotalFee;
	private Integer reqFeeType;
	private String reqNotifyUrl;
	private String reqAttach;
	private String reqTimeStart;
	private String reqTimeExpire;
	private Timestamp respTime;
	private String respErrInfo;
	private String respTokenId;
	private Timestamp notifyTime;
	private String notifyVer;
	private String notifyCharset;
	private Integer notifyBankType;
	private String notifyBankBillno;
	private Integer notifyPayResult;
	private String notifyPayInfo;
	private String notifyPurchaseAlias;
	private String notifyBargainorId;
	private String notifyTransactionId;
	private String notifySpBillno;
	private Float notifyTotalFee;
	private String notifyFeeType;
	private String notifyAttach;
	private String notifyTimeEnd;

	// Constructors

	/** default constructor */
	public SdkOrderTenpay() {
	}

	/** minimal constructor */
	public SdkOrderTenpay(Timestamp reqTime, String reqVer, String reqSalePlat,
			Integer reqBankType, String reqDesc, String reqBargainorId,
			String reqSpBillno, Float reqTotalFee, String reqNotifyUrl,
			String reqAttach) {
		this.reqTime = reqTime;
		this.reqVer = reqVer;
		this.reqSalePlat = reqSalePlat;
		this.reqBankType = reqBankType;
		this.reqDesc = reqDesc;
		this.reqBargainorId = reqBargainorId;
		this.reqSpBillno = reqSpBillno;
		this.reqTotalFee = reqTotalFee;
		this.reqNotifyUrl = reqNotifyUrl;
		this.reqAttach = reqAttach;
	}

	/** full constructor */
	public SdkOrderTenpay(Timestamp reqTime, String reqVer, String reqSalePlat,
			String reqCharset, Integer reqBankType, String reqDesc,
			String reqPurchaserId, String reqBargainorId, String reqSpBillno,
			Float reqTotalFee, Integer reqFeeType, String reqNotifyUrl,
			String reqAttach, String reqTimeStart, String reqTimeExpire,
			Timestamp respTime, String respErrInfo, String respTokenId,
			Timestamp notifyTime, String notifyVer, String notifyCharset,
			Integer notifyBankType, String notifyBankBillno,
			Integer notifyPayResult, String notifyPayInfo,
			String notifyPurchaseAlias, String notifyBargainorId,
			String notifyTransactionId, String notifySpBillno,
			Float notifyTotalFee, String notifyFeeType, String notifyAttach,
			String notifyTimeEnd) {
		this.reqTime = reqTime;
		this.reqVer = reqVer;
		this.reqSalePlat = reqSalePlat;
		this.reqCharset = reqCharset;
		this.reqBankType = reqBankType;
		this.reqDesc = reqDesc;
		this.reqPurchaserId = reqPurchaserId;
		this.reqBargainorId = reqBargainorId;
		this.reqSpBillno = reqSpBillno;
		this.reqTotalFee = reqTotalFee;
		this.reqFeeType = reqFeeType;
		this.reqNotifyUrl = reqNotifyUrl;
		this.reqAttach = reqAttach;
		this.reqTimeStart = reqTimeStart;
		this.reqTimeExpire = reqTimeExpire;
		this.respTime = respTime;
		this.respErrInfo = respErrInfo;
		this.respTokenId = respTokenId;
		this.notifyTime = notifyTime;
		this.notifyVer = notifyVer;
		this.notifyCharset = notifyCharset;
		this.notifyBankType = notifyBankType;
		this.notifyBankBillno = notifyBankBillno;
		this.notifyPayResult = notifyPayResult;
		this.notifyPayInfo = notifyPayInfo;
		this.notifyPurchaseAlias = notifyPurchaseAlias;
		this.notifyBargainorId = notifyBargainorId;
		this.notifyTransactionId = notifyTransactionId;
		this.notifySpBillno = notifySpBillno;
		this.notifyTotalFee = notifyTotalFee;
		this.notifyFeeType = notifyFeeType;
		this.notifyAttach = notifyAttach;
		this.notifyTimeEnd = notifyTimeEnd;
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

	@Column(name = "req_ver", nullable = false, length = 10)
	public String getReqVer() {
		return this.reqVer;
	}

	public void setReqVer(String reqVer) {
		this.reqVer = reqVer;
	}

	@Column(name = "req_sale_plat", nullable = false, length = 10)
	public String getReqSalePlat() {
		return this.reqSalePlat;
	}

	public void setReqSalePlat(String reqSalePlat) {
		this.reqSalePlat = reqSalePlat;
	}

	@Column(name = "req_charset", length = 10)
	public String getReqCharset() {
		return this.reqCharset;
	}

	public void setReqCharset(String reqCharset) {
		this.reqCharset = reqCharset;
	}

	@Column(name = "req_bank_type", nullable = false)
	public Integer getReqBankType() {
		return this.reqBankType;
	}

	public void setReqBankType(Integer reqBankType) {
		this.reqBankType = reqBankType;
	}

	@Column(name = "req_desc", nullable = false, length = 32)
	public String getReqDesc() {
		return this.reqDesc;
	}

	public void setReqDesc(String reqDesc) {
		this.reqDesc = reqDesc;
	}

	@Column(name = "req_purchaser_id", length = 32)
	public String getReqPurchaserId() {
		return this.reqPurchaserId;
	}

	public void setReqPurchaserId(String reqPurchaserId) {
		this.reqPurchaserId = reqPurchaserId;
	}

	@Column(name = "req_bargainor_id", nullable = false, length = 16)
	public String getReqBargainorId() {
		return this.reqBargainorId;
	}

	public void setReqBargainorId(String reqBargainorId) {
		this.reqBargainorId = reqBargainorId;
	}

	@Column(name = "req_sp_billno", nullable = false, length = 32)
	public String getReqSpBillno() {
		return this.reqSpBillno;
	}

	public void setReqSpBillno(String reqSpBillno) {
		this.reqSpBillno = reqSpBillno;
	}

	@Column(name = "req_total_fee", nullable = false, precision = 12, scale = 0)
	public Float getReqTotalFee() {
		return this.reqTotalFee;
	}

	public void setReqTotalFee(Float reqTotalFee) {
		this.reqTotalFee = reqTotalFee;
	}

	@Column(name = "req_fee_type")
	public Integer getReqFeeType() {
		return this.reqFeeType;
	}

	public void setReqFeeType(Integer reqFeeType) {
		this.reqFeeType = reqFeeType;
	}

	@Column(name = "req_notify_url", nullable = false)
	public String getReqNotifyUrl() {
		return this.reqNotifyUrl;
	}

	public void setReqNotifyUrl(String reqNotifyUrl) {
		this.reqNotifyUrl = reqNotifyUrl;
	}

	@Column(name = "req_attach", nullable = false)
	public String getReqAttach() {
		return this.reqAttach;
	}

	public void setReqAttach(String reqAttach) {
		this.reqAttach = reqAttach;
	}

	@Column(name = "req_time_start", length = 32)
	public String getReqTimeStart() {
		return this.reqTimeStart;
	}

	public void setReqTimeStart(String reqTimeStart) {
		this.reqTimeStart = reqTimeStart;
	}

	@Column(name = "req_time_expire", length = 32)
	public String getReqTimeExpire() {
		return this.reqTimeExpire;
	}

	public void setReqTimeExpire(String reqTimeExpire) {
		this.reqTimeExpire = reqTimeExpire;
	}

	@Column(name = "resp_time", length = 19)
	public Timestamp getRespTime() {
		return this.respTime;
	}

	public void setRespTime(Timestamp respTime) {
		this.respTime = respTime;
	}

	@Column(name = "resp_err_info", length = 256)
	public String getRespErrInfo() {
		return this.respErrInfo;
	}

	public void setRespErrInfo(String respErrInfo) {
		this.respErrInfo = respErrInfo;
	}

	@Column(name = "resp_token_id", length = 64)
	public String getRespTokenId() {
		return this.respTokenId;
	}

	public void setRespTokenId(String respTokenId) {
		this.respTokenId = respTokenId;
	}

	@Column(name = "notify_time", length = 19)
	public Timestamp getNotifyTime() {
		return this.notifyTime;
	}

	public void setNotifyTime(Timestamp notifyTime) {
		this.notifyTime = notifyTime;
	}

	@Column(name = "notify_ver", length = 10)
	public String getNotifyVer() {
		return this.notifyVer;
	}

	public void setNotifyVer(String notifyVer) {
		this.notifyVer = notifyVer;
	}

	@Column(name = "notify_charset", length = 10)
	public String getNotifyCharset() {
		return this.notifyCharset;
	}

	public void setNotifyCharset(String notifyCharset) {
		this.notifyCharset = notifyCharset;
	}

	@Column(name = "notify_bank_type")
	public Integer getNotifyBankType() {
		return this.notifyBankType;
	}

	public void setNotifyBankType(Integer notifyBankType) {
		this.notifyBankType = notifyBankType;
	}

	@Column(name = "notify_bank_billno", length = 64)
	public String getNotifyBankBillno() {
		return this.notifyBankBillno;
	}

	public void setNotifyBankBillno(String notifyBankBillno) {
		this.notifyBankBillno = notifyBankBillno;
	}

	@Column(name = "notify_pay_result")
	public Integer getNotifyPayResult() {
		return this.notifyPayResult;
	}

	public void setNotifyPayResult(Integer notifyPayResult) {
		this.notifyPayResult = notifyPayResult;
	}

	@Column(name = "notify_pay_info", length = 256)
	public String getNotifyPayInfo() {
		return this.notifyPayInfo;
	}

	public void setNotifyPayInfo(String notifyPayInfo) {
		this.notifyPayInfo = notifyPayInfo;
	}

	@Column(name = "notify_purchase_alias", length = 64)
	public String getNotifyPurchaseAlias() {
		return this.notifyPurchaseAlias;
	}

	public void setNotifyPurchaseAlias(String notifyPurchaseAlias) {
		this.notifyPurchaseAlias = notifyPurchaseAlias;
	}

	@Column(name = "notify_bargainor_id", length = 64)
	public String getNotifyBargainorId() {
		return this.notifyBargainorId;
	}

	public void setNotifyBargainorId(String notifyBargainorId) {
		this.notifyBargainorId = notifyBargainorId;
	}

	@Column(name = "notify_transaction_id", length = 64)
	public String getNotifyTransactionId() {
		return this.notifyTransactionId;
	}

	public void setNotifyTransactionId(String notifyTransactionId) {
		this.notifyTransactionId = notifyTransactionId;
	}

	@Column(name = "notify_sp_billno", length = 32)
	public String getNotifySpBillno() {
		return this.notifySpBillno;
	}

	public void setNotifySpBillno(String notifySpBillno) {
		this.notifySpBillno = notifySpBillno;
	}

	@Column(name = "notify_total_fee", precision = 12, scale = 0)
	public Float getNotifyTotalFee() {
		return this.notifyTotalFee;
	}

	public void setNotifyTotalFee(Float notifyTotalFee) {
		this.notifyTotalFee = notifyTotalFee;
	}

	@Column(name = "notify_fee_type", length = 10)
	public String getNotifyFeeType() {
		return this.notifyFeeType;
	}

	public void setNotifyFeeType(String notifyFeeType) {
		this.notifyFeeType = notifyFeeType;
	}

	@Column(name = "notify_attach")
	public String getNotifyAttach() {
		return this.notifyAttach;
	}

	public void setNotifyAttach(String notifyAttach) {
		this.notifyAttach = notifyAttach;
	}

	@Column(name = "notify_time_end", length = 32)
	public String getNotifyTimeEnd() {
		return this.notifyTimeEnd;
	}

	public void setNotifyTimeEnd(String notifyTimeEnd) {
		this.notifyTimeEnd = notifyTimeEnd;
	}

}