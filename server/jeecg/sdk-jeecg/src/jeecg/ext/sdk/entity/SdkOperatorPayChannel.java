package jeecg.ext.sdk.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SdkOperatorPayChannel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_operator_pay_channel", catalog = "game_sdk")
public class SdkOperatorPayChannel implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2591538550928884182L;
	private Integer id;
	private Short operatorType;
	private String channelCode;
	private String channelName;
	private Short smsType;
	private String sdkMinVer;
	private Integer billingProvinceId;
	private String billingPeriods;
	private String shieldingProvinceIds;
	private String shieldingSegments;
	private Date createdTime;
	private Date updatedTime;
	private Integer smsContentType;
	private String corporation;
	private Float dayLimit;
	private Float monthLimit;
	private Integer timeinterval;
	private Integer reqTimeinterval;
	private String signCorporation;

	// Constructors

	/** default constructor */
	public SdkOperatorPayChannel() {
	}

	/** minimal constructor */
	public SdkOperatorPayChannel(Short operatorType, String channelCode,
			String channelName) {
		this.operatorType = operatorType;
		this.channelCode = channelCode;
		this.channelName = channelName;
	}

	/** full constructor */
	public SdkOperatorPayChannel(Short operatorType, String channelCode,
			String channelName, Short smsType, String sdkMinVer,
			Integer billingProvinceId, String billingPeriods,
			String shieldingProvinceIds, String shieldingSegments,
			Timestamp createdTime, Timestamp updatedTime,
			Integer smsContentType, String corporation, Float dayLimit,
			Float monthLimit, Integer timeinterval, Integer reqTimeinterval,
			String signCorporation) {
		this.operatorType = operatorType;
		this.channelCode = channelCode;
		this.channelName = channelName;
		this.smsType = smsType;
		this.sdkMinVer = sdkMinVer;
		this.billingProvinceId = billingProvinceId;
		this.billingPeriods = billingPeriods;
		this.shieldingProvinceIds = shieldingProvinceIds;
		this.shieldingSegments = shieldingSegments;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.smsContentType = smsContentType;
		this.corporation = corporation;
		this.dayLimit = dayLimit;
		this.monthLimit = monthLimit;
		this.timeinterval = timeinterval;
		this.reqTimeinterval = reqTimeinterval;
		this.signCorporation = signCorporation;
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

	@Column(name = "operator_type", nullable = false)
	public Short getOperatorType() {
		return this.operatorType;
	}

	public void setOperatorType(Short operatorType) {
		this.operatorType = operatorType;
	}

	@Column(name = "channel_code", nullable = false, length = 64)
	public String getChannelCode() {
		return this.channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	@Column(name = "channel_name", nullable = false, length = 64)
	public String getChannelName() {
		return this.channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	@Column(name = "sms_type")
	public Short getSmsType() {
		return this.smsType;
	}

	public void setSmsType(Short smsType) {
		this.smsType = smsType;
	}

	@Column(name = "sdk_min_ver", length = 15)
	public String getSdkMinVer() {
		return this.sdkMinVer;
	}

	public void setSdkMinVer(String sdkMinVer) {
		this.sdkMinVer = sdkMinVer;
	}

	@Column(name = "billing_province_id")
	public Integer getBillingProvinceId() {
		return this.billingProvinceId;
	}

	public void setBillingProvinceId(Integer billingProvinceId) {
		this.billingProvinceId = billingProvinceId;
	}

	@Column(name = "billing_periods", length = 256)
	public String getBillingPeriods() {
		return this.billingPeriods;
	}

	public void setBillingPeriods(String billingPeriods) {
		this.billingPeriods = billingPeriods;
	}

	@Column(name = "shielding_province_ids", length = 256)
	public String getShieldingProvinceIds() {
		return this.shieldingProvinceIds;
	}

	public void setShieldingProvinceIds(String shieldingProvinceIds) {
		this.shieldingProvinceIds = shieldingProvinceIds;
	}

	@Column(name = "shielding_segments", length = 256)
	public String getShieldingSegments() {
		return this.shieldingSegments;
	}

	public void setShieldingSegments(String shieldingSegments) {
		this.shieldingSegments = shieldingSegments;
	}

	@Column(name = "created_time", length = 19)
	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Column(name = "updated_time", length = 19)
	public Date getUpdatedTime() {
		return this.updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	@Column(name = "sms_content_type")
	public Integer getSmsContentType() {
		return this.smsContentType;
	}

	public void setSmsContentType(Integer smsContentType) {
		this.smsContentType = smsContentType;
	}

	@Column(name = "corporation", length = 64)
	public String getCorporation() {
		return this.corporation;
	}

	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}

	@Column(name = "day_limit", precision = 12, scale = 0)
	public Float getDayLimit() {
		return this.dayLimit;
	}

	public void setDayLimit(Float dayLimit) {
		this.dayLimit = dayLimit;
	}

	@Column(name = "month_limit", precision = 12, scale = 0)
	public Float getMonthLimit() {
		return this.monthLimit;
	}

	public void setMonthLimit(Float monthLimit) {
		this.monthLimit = monthLimit;
	}

	@Column(name = "timeinterval")
	public Integer getTimeinterval() {
		return this.timeinterval;
	}

	public void setTimeinterval(Integer timeinterval) {
		this.timeinterval = timeinterval;
	}

	@Column(name = "req_timeinterval")
	public Integer getReqTimeinterval() {
		return this.reqTimeinterval;
	}

	public void setReqTimeinterval(Integer reqTimeinterval) {
		this.reqTimeinterval = reqTimeinterval;
	}

	@Column(name = "sign_corporation", length = 32)
	public String getSignCorporation() {
		return this.signCorporation;
	}

	public void setSignCorporation(String signCorporation) {
		this.signCorporation = signCorporation;
	}

}