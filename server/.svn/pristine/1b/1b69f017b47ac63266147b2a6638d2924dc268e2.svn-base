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
 * SdkUpgradeJar entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_upgrade_jar", catalog = "game_sdk")
public class SdkUpgradeJar implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2112718265691976060L;
	private Integer id;
	private String versionCode;
	private String downUrl;
	private Integer statusTag;
	private String memo;
	private Date createTime;
	private Date updatedTime;

	// Constructors

	/** default constructor */
	public SdkUpgradeJar() {
	}

	/** minimal constructor */
	public SdkUpgradeJar(String downUrl, Integer statusTag) {
		this.downUrl = downUrl;
		this.statusTag = statusTag;
	}

	/** full constructor */
	public SdkUpgradeJar(String versionCode, String downUrl, Integer statusTag,
			String memo, Timestamp createTime, Timestamp updatedTime) {
		this.versionCode = versionCode;
		this.downUrl = downUrl;
		this.statusTag = statusTag;
		this.memo = memo;
		this.createTime = createTime;
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

	@Column(name = "version_code", length = 64)
	public String getVersionCode() {
		return this.versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	@Column(name = "down_url", nullable = false, length = 256)
	public String getDownUrl() {
		return this.downUrl;
	}

	public void setDownUrl(String downUrl) {
		this.downUrl = downUrl;
	}

	@Column(name = "status_tag", nullable = false)
	public Integer getStatusTag() {
		return this.statusTag;
	}

	public void setStatusTag(Integer statusTag) {
		this.statusTag = statusTag;
	}

	@Column(name = "memo", length = 256)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "updated_time", length = 19)
	public Date getUpdatedTime() {
		return this.updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

}