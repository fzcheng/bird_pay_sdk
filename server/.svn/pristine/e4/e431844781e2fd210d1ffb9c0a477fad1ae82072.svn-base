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
 * SdkSms entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_sms", catalog = "game_sdk")
public class SdkSms implements java.io.Serializable {

	// Fields

	private Integer id;
	private String upPort;
	private Integer useState;
	private Date createdTime;
	private Date updatedTime;
	private Integer operationType;

	// Constructors

	/** default constructor */
	public SdkSms() {
	}

	/** full constructor */
	public SdkSms(String upPort, Integer useState, Timestamp createdTime,
			Timestamp updatedTime, Integer operationType) {
		this.upPort = upPort;
		this.useState = useState;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.operationType = operationType;
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

	@Column(name = "up_port", length = 64)
	public String getUpPort() {
		return this.upPort;
	}

	public void setUpPort(String upPort) {
		this.upPort = upPort;
	}

	@Column(name = "use_state")
	public Integer getUseState() {
		return this.useState;
	}

	public void setUseState(Integer useState) {
		this.useState = useState;
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

	@Column(name = "operation_type")
	public Integer getOperationType() {
		return this.operationType;
	}

	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}

}