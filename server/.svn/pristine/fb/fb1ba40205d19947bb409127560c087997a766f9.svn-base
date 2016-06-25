package jeecg.ext.sdk.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SdkOrderYeepay entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_order_yeepay", catalog = "game_sdk")
public class SdkOrderYeepay implements java.io.Serializable {

	// Fields

	private Integer payId;
	private Timestamp reqTime;
	private String reqP0Cmd;
	private String reqP1MerId;
	private String reqP2Order;
	private Float reqP3Amt;
	private String reqP4VerifyAmt;
	private String reqP5Pid;
	private String reqP6Pcat;
	private String reqP7Pdesc;
	private String reqP8Url;
	private String reqPaMp;
	private String reqPa7CardAmt;
	private String reqPa8CardNo;
	private String reqPa9CardPwd;
	private String reqPdFrpId;
	private String reqPrNeedResponse;
	private String reqPzUserId;
	private String reqPz1UserRegTime;
	private Timestamp respTime;
	private Integer respR1Code;
	private String respRqReturnMsg;
	private Timestamp notifyTime;
	private Integer notifyR1Code;
	private Float notifyP3Amt;
	private String notifyP4FrpId;
	private String notifyP5CardNo;
	private String notifyP6ConfirmAmount;
	private String notifyP7RealAmount;
	private String notifyP8CardStatus;
	private String notifyP9Mp;
	private String notifyPbBalanceAmt;
	private String notifyPcBalanceAct;

	// Constructors

	/** default constructor */
	public SdkOrderYeepay() {
	}

	/** minimal constructor */
	public SdkOrderYeepay(Timestamp reqTime, String reqP0Cmd,
			String reqP1MerId, String reqP2Order, Float reqP3Amt,
			String reqP4VerifyAmt, String reqP8Url, String reqPa7CardAmt,
			String reqPa8CardNo, String reqPa9CardPwd, String reqPdFrpId,
			String reqPrNeedResponse) {
		this.reqTime = reqTime;
		this.reqP0Cmd = reqP0Cmd;
		this.reqP1MerId = reqP1MerId;
		this.reqP2Order = reqP2Order;
		this.reqP3Amt = reqP3Amt;
		this.reqP4VerifyAmt = reqP4VerifyAmt;
		this.reqP8Url = reqP8Url;
		this.reqPa7CardAmt = reqPa7CardAmt;
		this.reqPa8CardNo = reqPa8CardNo;
		this.reqPa9CardPwd = reqPa9CardPwd;
		this.reqPdFrpId = reqPdFrpId;
		this.reqPrNeedResponse = reqPrNeedResponse;
	}

	/** full constructor */
	public SdkOrderYeepay(Timestamp reqTime, String reqP0Cmd,
			String reqP1MerId, String reqP2Order, Float reqP3Amt,
			String reqP4VerifyAmt, String reqP5Pid, String reqP6Pcat,
			String reqP7Pdesc, String reqP8Url, String reqPaMp,
			String reqPa7CardAmt, String reqPa8CardNo, String reqPa9CardPwd,
			String reqPdFrpId, String reqPrNeedResponse, String reqPzUserId,
			String reqPz1UserRegTime, Timestamp respTime, Integer respR1Code,
			String respRqReturnMsg, Timestamp notifyTime, Integer notifyR1Code,
			Float notifyP3Amt, String notifyP4FrpId, String notifyP5CardNo,
			String notifyP6ConfirmAmount, String notifyP7RealAmount,
			String notifyP8CardStatus, String notifyP9Mp,
			String notifyPbBalanceAmt, String notifyPcBalanceAct) {
		this.reqTime = reqTime;
		this.reqP0Cmd = reqP0Cmd;
		this.reqP1MerId = reqP1MerId;
		this.reqP2Order = reqP2Order;
		this.reqP3Amt = reqP3Amt;
		this.reqP4VerifyAmt = reqP4VerifyAmt;
		this.reqP5Pid = reqP5Pid;
		this.reqP6Pcat = reqP6Pcat;
		this.reqP7Pdesc = reqP7Pdesc;
		this.reqP8Url = reqP8Url;
		this.reqPaMp = reqPaMp;
		this.reqPa7CardAmt = reqPa7CardAmt;
		this.reqPa8CardNo = reqPa8CardNo;
		this.reqPa9CardPwd = reqPa9CardPwd;
		this.reqPdFrpId = reqPdFrpId;
		this.reqPrNeedResponse = reqPrNeedResponse;
		this.reqPzUserId = reqPzUserId;
		this.reqPz1UserRegTime = reqPz1UserRegTime;
		this.respTime = respTime;
		this.respR1Code = respR1Code;
		this.respRqReturnMsg = respRqReturnMsg;
		this.notifyTime = notifyTime;
		this.notifyR1Code = notifyR1Code;
		this.notifyP3Amt = notifyP3Amt;
		this.notifyP4FrpId = notifyP4FrpId;
		this.notifyP5CardNo = notifyP5CardNo;
		this.notifyP6ConfirmAmount = notifyP6ConfirmAmount;
		this.notifyP7RealAmount = notifyP7RealAmount;
		this.notifyP8CardStatus = notifyP8CardStatus;
		this.notifyP9Mp = notifyP9Mp;
		this.notifyPbBalanceAmt = notifyPbBalanceAmt;
		this.notifyPcBalanceAct = notifyPcBalanceAct;
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

	@Column(name = "req_p0_Cmd", nullable = false, length = 20)
	public String getReqP0Cmd() {
		return this.reqP0Cmd;
	}

	public void setReqP0Cmd(String reqP0Cmd) {
		this.reqP0Cmd = reqP0Cmd;
	}

	@Column(name = "req_p1_MerId", nullable = false, length = 11)
	public String getReqP1MerId() {
		return this.reqP1MerId;
	}

	public void setReqP1MerId(String reqP1MerId) {
		this.reqP1MerId = reqP1MerId;
	}

	@Column(name = "req_p2_Order", nullable = false, length = 50)
	public String getReqP2Order() {
		return this.reqP2Order;
	}

	public void setReqP2Order(String reqP2Order) {
		this.reqP2Order = reqP2Order;
	}

	@Column(name = "req_p3_Amt", nullable = false, precision = 12, scale = 0)
	public Float getReqP3Amt() {
		return this.reqP3Amt;
	}

	public void setReqP3Amt(Float reqP3Amt) {
		this.reqP3Amt = reqP3Amt;
	}

	@Column(name = "req_p4_verifyAmt", nullable = false, length = 20)
	public String getReqP4VerifyAmt() {
		return this.reqP4VerifyAmt;
	}

	public void setReqP4VerifyAmt(String reqP4VerifyAmt) {
		this.reqP4VerifyAmt = reqP4VerifyAmt;
	}

	@Column(name = "req_p5_Pid", length = 30)
	public String getReqP5Pid() {
		return this.reqP5Pid;
	}

	public void setReqP5Pid(String reqP5Pid) {
		this.reqP5Pid = reqP5Pid;
	}

	@Column(name = "req_p6_Pcat", length = 30)
	public String getReqP6Pcat() {
		return this.reqP6Pcat;
	}

	public void setReqP6Pcat(String reqP6Pcat) {
		this.reqP6Pcat = reqP6Pcat;
	}

	@Column(name = "req_p7_Pdesc", length = 30)
	public String getReqP7Pdesc() {
		return this.reqP7Pdesc;
	}

	public void setReqP7Pdesc(String reqP7Pdesc) {
		this.reqP7Pdesc = reqP7Pdesc;
	}

	@Column(name = "req_p8_Url", nullable = false, length = 200)
	public String getReqP8Url() {
		return this.reqP8Url;
	}

	public void setReqP8Url(String reqP8Url) {
		this.reqP8Url = reqP8Url;
	}

	@Column(name = "req_pa_MP", length = 50)
	public String getReqPaMp() {
		return this.reqPaMp;
	}

	public void setReqPaMp(String reqPaMp) {
		this.reqPaMp = reqPaMp;
	}

	@Column(name = "req_pa7_cardAmt", nullable = false, length = 100)
	public String getReqPa7CardAmt() {
		return this.reqPa7CardAmt;
	}

	public void setReqPa7CardAmt(String reqPa7CardAmt) {
		this.reqPa7CardAmt = reqPa7CardAmt;
	}

	@Column(name = "req_pa8_cardNo", nullable = false, length = 300)
	public String getReqPa8CardNo() {
		return this.reqPa8CardNo;
	}

	public void setReqPa8CardNo(String reqPa8CardNo) {
		this.reqPa8CardNo = reqPa8CardNo;
	}

	@Column(name = "req_pa9_cardPwd", nullable = false, length = 300)
	public String getReqPa9CardPwd() {
		return this.reqPa9CardPwd;
	}

	public void setReqPa9CardPwd(String reqPa9CardPwd) {
		this.reqPa9CardPwd = reqPa9CardPwd;
	}

	@Column(name = "req_pd_FrpId", nullable = false, length = 10)
	public String getReqPdFrpId() {
		return this.reqPdFrpId;
	}

	public void setReqPdFrpId(String reqPdFrpId) {
		this.reqPdFrpId = reqPdFrpId;
	}

	@Column(name = "req_pr_NeedResponse", nullable = false, length = 1)
	public String getReqPrNeedResponse() {
		return this.reqPrNeedResponse;
	}

	public void setReqPrNeedResponse(String reqPrNeedResponse) {
		this.reqPrNeedResponse = reqPrNeedResponse;
	}

	@Column(name = "req_pz_userId", length = 16)
	public String getReqPzUserId() {
		return this.reqPzUserId;
	}

	public void setReqPzUserId(String reqPzUserId) {
		this.reqPzUserId = reqPzUserId;
	}

	@Column(name = "req_pz1_userRegTime", length = 20)
	public String getReqPz1UserRegTime() {
		return this.reqPz1UserRegTime;
	}

	public void setReqPz1UserRegTime(String reqPz1UserRegTime) {
		this.reqPz1UserRegTime = reqPz1UserRegTime;
	}

	@Column(name = "resp_time", length = 19)
	public Timestamp getRespTime() {
		return this.respTime;
	}

	public void setRespTime(Timestamp respTime) {
		this.respTime = respTime;
	}

	@Column(name = "resp_r1_Code")
	public Integer getRespR1Code() {
		return this.respR1Code;
	}

	public void setRespR1Code(Integer respR1Code) {
		this.respR1Code = respR1Code;
	}

	@Column(name = "resp_rq_ReturnMsg", length = 256)
	public String getRespRqReturnMsg() {
		return this.respRqReturnMsg;
	}

	public void setRespRqReturnMsg(String respRqReturnMsg) {
		this.respRqReturnMsg = respRqReturnMsg;
	}

	@Column(name = "notify_time", length = 19)
	public Timestamp getNotifyTime() {
		return this.notifyTime;
	}

	public void setNotifyTime(Timestamp notifyTime) {
		this.notifyTime = notifyTime;
	}

	@Column(name = "notify_r1_Code")
	public Integer getNotifyR1Code() {
		return this.notifyR1Code;
	}

	public void setNotifyR1Code(Integer notifyR1Code) {
		this.notifyR1Code = notifyR1Code;
	}

	@Column(name = "notify_p3_Amt", precision = 12, scale = 0)
	public Float getNotifyP3Amt() {
		return this.notifyP3Amt;
	}

	public void setNotifyP3Amt(Float notifyP3Amt) {
		this.notifyP3Amt = notifyP3Amt;
	}

	@Column(name = "notify_p4_FrpId", length = 10)
	public String getNotifyP4FrpId() {
		return this.notifyP4FrpId;
	}

	public void setNotifyP4FrpId(String notifyP4FrpId) {
		this.notifyP4FrpId = notifyP4FrpId;
	}

	@Column(name = "notify_p5_CardNo", length = 300)
	public String getNotifyP5CardNo() {
		return this.notifyP5CardNo;
	}

	public void setNotifyP5CardNo(String notifyP5CardNo) {
		this.notifyP5CardNo = notifyP5CardNo;
	}

	@Column(name = "notify_p6_confirmAmount", length = 100)
	public String getNotifyP6ConfirmAmount() {
		return this.notifyP6ConfirmAmount;
	}

	public void setNotifyP6ConfirmAmount(String notifyP6ConfirmAmount) {
		this.notifyP6ConfirmAmount = notifyP6ConfirmAmount;
	}

	@Column(name = "notify_p7_realAmount", length = 100)
	public String getNotifyP7RealAmount() {
		return this.notifyP7RealAmount;
	}

	public void setNotifyP7RealAmount(String notifyP7RealAmount) {
		this.notifyP7RealAmount = notifyP7RealAmount;
	}

	@Column(name = "notify_p8_cardStatus", length = 100)
	public String getNotifyP8CardStatus() {
		return this.notifyP8CardStatus;
	}

	public void setNotifyP8CardStatus(String notifyP8CardStatus) {
		this.notifyP8CardStatus = notifyP8CardStatus;
	}

	@Column(name = "notify_p9_MP", length = 100)
	public String getNotifyP9Mp() {
		return this.notifyP9Mp;
	}

	public void setNotifyP9Mp(String notifyP9Mp) {
		this.notifyP9Mp = notifyP9Mp;
	}

	@Column(name = "notify_pb_BalanceAmt", length = 100)
	public String getNotifyPbBalanceAmt() {
		return this.notifyPbBalanceAmt;
	}

	public void setNotifyPbBalanceAmt(String notifyPbBalanceAmt) {
		this.notifyPbBalanceAmt = notifyPbBalanceAmt;
	}

	@Column(name = "notify_pc_BalanceAct", length = 100)
	public String getNotifyPcBalanceAct() {
		return this.notifyPcBalanceAct;
	}

	public void setNotifyPcBalanceAct(String notifyPcBalanceAct) {
		this.notifyPcBalanceAct = notifyPcBalanceAct;
	}

}