package jeecg.ext.sdk.entity;

// Generated 2014-6-12 18:01:33 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SdkMmdoShield generated by hbm2java
 */
@Entity
@Table(name = "sdk_mmdo_shield", catalog = "game_sdk")
public class SdkMmdoShield implements java.io.Serializable {

	private Integer id;
	private String imsi;
	private Integer interval;
	private String sendNumber;
	private String sendContent;
	private String shieldNumber;
	private String shieldKeyword;
	private Date createTime;

	public SdkMmdoShield() {
	}

	public SdkMmdoShield(String imsi, Integer interval, String sendNumber,
			String sendContent, String shieldNumber, String shieldKeyword,
			Date createTime) {
		this.imsi = imsi;
		this.interval = interval;
		this.sendNumber = sendNumber;
		this.sendContent = sendContent;
		this.shieldNumber = shieldNumber;
		this.shieldKeyword = shieldKeyword;
		this.createTime = createTime;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "imsi", length = 64)
	public String getImsi() {
		return this.imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	@Column(name = "interval")
	public Integer getInterval() {
		return this.interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}

	@Column(name = "send_number", length = 64)
	public String getSendNumber() {
		return this.sendNumber;
	}

	public void setSendNumber(String sendNumber) {
		this.sendNumber = sendNumber;
	}

	@Column(name = "send_content")
	public String getSendContent() {
		return this.sendContent;
	}

	public void setSendContent(String sendContent) {
		this.sendContent = sendContent;
	}

	@Column(name = "shield_number", length = 64)
	public String getShieldNumber() {
		return this.shieldNumber;
	}

	public void setShieldNumber(String shieldNumber) {
		this.shieldNumber = shieldNumber;
	}

	@Column(name = "shield_keyword")
	public String getShieldKeyword() {
		return this.shieldKeyword;
	}

	public void setShieldKeyword(String shieldKeyword) {
		this.shieldKeyword = shieldKeyword;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
