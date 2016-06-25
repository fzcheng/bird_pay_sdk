package jeecg.ext.sdk.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * SdkUpgradeJarGame entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_upgrade_jar_game", catalog = "game_sdk", uniqueConstraints = @UniqueConstraint(columnNames = {
		"version_code", "game_id" }))
public class SdkUpgradeJarGame implements java.io.Serializable {

	// Fields

	private Integer id;
	private String versionCode;
	private Integer gameId;
	private Integer statusTag;
	private Date createTime;
	private Date updatedTime;

	// Constructors

	/** default constructor */
	public SdkUpgradeJarGame() {
	}

	/** minimal constructor */
	public SdkUpgradeJarGame(String versionCode, Integer gameId,
			Integer statusTag) {
		this.versionCode = versionCode;
		this.gameId = gameId;
		this.statusTag = statusTag;
	}

	/** full constructor */
	public SdkUpgradeJarGame(String versionCode, Integer gameId,
			Integer statusTag, Timestamp createTime, Timestamp updatedTime) {
		this.versionCode = versionCode;
		this.gameId = gameId;
		this.statusTag = statusTag;
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

	@Column(name = "version_code", nullable = false, length = 64)
	public String getVersionCode() {
		return this.versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	@Column(name = "game_id", nullable = false)
	public Integer getGameId() {
		return this.gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	@Column(name = "status_tag", nullable = false)
	public Integer getStatusTag() {
		return this.statusTag;
	}

	public void setStatusTag(Integer statusTag) {
		this.statusTag = statusTag;
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