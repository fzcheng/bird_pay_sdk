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
 * SdkSwbInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_swb_info", catalog = "game_sdk")
public class SdkSwbInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4998048110957933299L;
	private Integer id;
	private Integer gameId;
	private Integer operatorType;
	private String appId;
	private String appKey;
	private String cpId;
	private String cpCode;
	private String company;
	private String phone;
	private String channel;
	private Integer useStatus;
	private Date createdTime;
	private Date updatedTime;

	// Constructors

	/** default constructor */
	public SdkSwbInfo() {
	}

	/** minimal constructor */
	public SdkSwbInfo(Integer gameId, Integer operatorType) {
		this.gameId = gameId;
		this.operatorType = operatorType;
	}

	/** full constructor */
	public SdkSwbInfo(Integer gameId, Integer operatorType, String appId,
			String appKey, String cpId, String cpCode, String company,
			String phone, String channel, Integer useStatus,
			Timestamp createdTime, Timestamp updatedTime) {
		this.gameId = gameId;
		this.operatorType = operatorType;
		this.appId = appId;
		this.appKey = appKey;
		this.cpId = cpId;
		this.cpCode = cpCode;
		this.company = company;
		this.phone = phone;
		this.channel = channel;
		this.useStatus = useStatus;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
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

	@Column(name = "game_id", nullable = false)
	public Integer getGameId() {
		return this.gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	@Column(name = "operator_type", nullable = false)
	public Integer getOperatorType() {
		return this.operatorType;
	}

	public void setOperatorType(Integer operatorType) {
		this.operatorType = operatorType;
	}

	@Column(name = "app_id", length = 256)
	public String getAppId() {
		return this.appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	@Column(name = "app_key", length = 256)
	public String getAppKey() {
		return this.appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	@Column(name = "cp_id", length = 256)
	public String getCpId() {
		return this.cpId;
	}

	public void setCpId(String cpId) {
		this.cpId = cpId;
	}

	@Column(name = "cp_code", length = 256)
	public String getCpCode() {
		return this.cpCode;
	}

	public void setCpCode(String cpCode) {
		this.cpCode = cpCode;
	}

	@Column(name = "company", length = 256)
	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(name = "phone", length = 256)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "channel", length = 256)
	public String getChannel() {
		return this.channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	@Column(name = "use_status")
	public Integer getUseStatus() {
		return this.useStatus;
	}

	public void setUseStatus(Integer useStatus) {
		this.useStatus = useStatus;
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

}