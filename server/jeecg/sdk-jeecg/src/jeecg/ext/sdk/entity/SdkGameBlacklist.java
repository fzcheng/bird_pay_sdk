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
 * SdkGameBlacklist entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_game_blacklist", catalog = "game_sdk")
public class SdkGameBlacklist implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1730129121644430402L;
	private Integer id;
	private String imsi;
	private String mobile;
	private Short operatorType;
	private Date createdTime;
	private Date updatedTime;

	// Constructors

	/** default constructor */
	public SdkGameBlacklist() {
	}

	/** full constructor */
	public SdkGameBlacklist(String imsi, String mobile, Short operatorType,
			Date createdTime, Date updatedTime) {
		this.imsi = imsi;
		this.mobile = mobile;
		this.operatorType = operatorType;
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

	@Column(name = "imsi", length = 400)
	public String getImsi() {
		return this.imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	@Column(name = "mobile", length = 11)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "operator_type")
	public Short getOperatorType() {
		return this.operatorType;
	}

	public void setOperatorType(Short operatorType) {
		this.operatorType = operatorType;
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