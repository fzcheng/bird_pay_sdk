package jeecg.ext.sdk.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SdkOrderMmdo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_order_mmdo", catalog = "game_sdk")
public class SdkOrderMmdo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6697971249753053537L;
	private Integer payId;
	private Timestamp reqTime;
	private Float reqOrderAmount;
	private String reqImsi;
	private String reqSendNumber;
	private String reqSendContent;
	private String respImsi;
	private String respSendNumber;
	private String respSendContent;
	private Integer respStatus;
	private Timestamp respTime;
	private Integer gameId;
	private Integer uid;
	private Integer operationType;
	private String imei;
	private String macAddr;
	private String ipAddr;
	private String rawData;
	private String payChannelCode;
	private Integer additionalStatus;
	private String tjpropsname;
	private String tradeid;
	private Integer overThirtym;
	private String mobilephone;
	private Integer provinceNo;
	private String iccid;
	private String centernumber;
	private String paytactics;
	private Integer nextPaytactics;

	// Constructors

	/** default constructor */
	public SdkOrderMmdo() {
	}

	/** full constructor */
	public SdkOrderMmdo(Timestamp reqTime, Float reqOrderAmount,
			String reqImsi, String reqSendNumber, String reqSendContent,
			String respImsi, String respSendNumber, String respSendContent,
			Integer respStatus, Timestamp respTime, Integer gameId,
			Integer uid, Integer operationType, String imei, String macAddr,
			String ipAddr, String rawData, String payChannelCode,
			Integer additionalStatus, String tjpropsname, String tradeid,
			Integer overThirtym, String mobilephone, Integer provinceNo,
			String iccid, String centernumber, String paytactics,
			Integer nextPaytactics) {
		this.reqTime = reqTime;
		this.reqOrderAmount = reqOrderAmount;
		this.reqImsi = reqImsi;
		this.reqSendNumber = reqSendNumber;
		this.reqSendContent = reqSendContent;
		this.respImsi = respImsi;
		this.respSendNumber = respSendNumber;
		this.respSendContent = respSendContent;
		this.respStatus = respStatus;
		this.respTime = respTime;
		this.gameId = gameId;
		this.uid = uid;
		this.operationType = operationType;
		this.imei = imei;
		this.macAddr = macAddr;
		this.ipAddr = ipAddr;
		this.rawData = rawData;
		this.payChannelCode = payChannelCode;
		this.additionalStatus = additionalStatus;
		this.tjpropsname = tjpropsname;
		this.tradeid = tradeid;
		this.overThirtym = overThirtym;
		this.mobilephone = mobilephone;
		this.provinceNo = provinceNo;
		this.iccid = iccid;
		this.centernumber = centernumber;
		this.paytactics = paytactics;
		this.nextPaytactics = nextPaytactics;
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

	@Column(name = "req_time", length = 19)
	public Timestamp getReqTime() {
		return this.reqTime;
	}

	public void setReqTime(Timestamp reqTime) {
		this.reqTime = reqTime;
	}

	@Column(name = "req_order_amount", precision = 12, scale = 0)
	public Float getReqOrderAmount() {
		return this.reqOrderAmount;
	}

	public void setReqOrderAmount(Float reqOrderAmount) {
		this.reqOrderAmount = reqOrderAmount;
	}

	@Column(name = "req_imsi", length = 128)
	public String getReqImsi() {
		return this.reqImsi;
	}

	public void setReqImsi(String reqImsi) {
		this.reqImsi = reqImsi;
	}

	@Column(name = "req_send_number", length = 64)
	public String getReqSendNumber() {
		return this.reqSendNumber;
	}

	public void setReqSendNumber(String reqSendNumber) {
		this.reqSendNumber = reqSendNumber;
	}

	@Column(name = "req_send_content")
	public String getReqSendContent() {
		return this.reqSendContent;
	}

	public void setReqSendContent(String reqSendContent) {
		this.reqSendContent = reqSendContent;
	}

	@Column(name = "resp_imsi", length = 128)
	public String getRespImsi() {
		return this.respImsi;
	}

	public void setRespImsi(String respImsi) {
		this.respImsi = respImsi;
	}

	@Column(name = "resp_send_number", length = 64)
	public String getRespSendNumber() {
		return this.respSendNumber;
	}

	public void setRespSendNumber(String respSendNumber) {
		this.respSendNumber = respSendNumber;
	}

	@Column(name = "resp_send_content")
	public String getRespSendContent() {
		return this.respSendContent;
	}

	public void setRespSendContent(String respSendContent) {
		this.respSendContent = respSendContent;
	}

	@Column(name = "resp_status")
	public Integer getRespStatus() {
		return this.respStatus;
	}

	public void setRespStatus(Integer respStatus) {
		this.respStatus = respStatus;
	}

	@Column(name = "resp_time", length = 19)
	public Timestamp getRespTime() {
		return this.respTime;
	}

	public void setRespTime(Timestamp respTime) {
		this.respTime = respTime;
	}

	@Column(name = "game_id")
	public Integer getGameId() {
		return this.gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	@Column(name = "uid")
	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	@Column(name = "operation_type")
	public Integer getOperationType() {
		return this.operationType;
	}

	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}

	@Column(name = "imei", length = 64)
	public String getImei() {
		return this.imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	@Column(name = "mac_addr", length = 64)
	public String getMacAddr() {
		return this.macAddr;
	}

	public void setMacAddr(String macAddr) {
		this.macAddr = macAddr;
	}

	@Column(name = "ip_addr", length = 128)
	public String getIpAddr() {
		return this.ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	@Column(name = "raw_data", length = 2048)
	public String getRawData() {
		return this.rawData;
	}

	public void setRawData(String rawData) {
		this.rawData = rawData;
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

	@Column(name = "tjpropsname", length = 32)
	public String getTjpropsname() {
		return this.tjpropsname;
	}

	public void setTjpropsname(String tjpropsname) {
		this.tjpropsname = tjpropsname;
	}

	@Column(name = "tradeid", length = 32)
	public String getTradeid() {
		return this.tradeid;
	}

	public void setTradeid(String tradeid) {
		this.tradeid = tradeid;
	}

	@Column(name = "over_thirtym")
	public Integer getOverThirtym() {
		return this.overThirtym;
	}

	public void setOverThirtym(Integer overThirtym) {
		this.overThirtym = overThirtym;
	}

	@Column(name = "mobilephone", length = 11)
	public String getMobilephone() {
		return this.mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	@Column(name = "province_no")
	public Integer getProvinceNo() {
		return this.provinceNo;
	}

	public void setProvinceNo(Integer provinceNo) {
		this.provinceNo = provinceNo;
	}

	@Column(name = "iccid", length = 30)
	public String getIccid() {
		return this.iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	@Column(name = "centernumber", length = 30)
	public String getCenternumber() {
		return this.centernumber;
	}

	public void setCenternumber(String centernumber) {
		this.centernumber = centernumber;
	}

	@Column(name = "paytactics", length = 800)
	public String getPaytactics() {
		return this.paytactics;
	}

	public void setPaytactics(String paytactics) {
		this.paytactics = paytactics;
	}

	@Column(name = "next_paytactics")
	public Integer getNextPaytactics() {
		return this.nextPaytactics;
	}

	public void setNextPaytactics(Integer nextPaytactics) {
		this.nextPaytactics = nextPaytactics;
	}

}