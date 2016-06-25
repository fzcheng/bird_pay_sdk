package jeecg.ext.sdk.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * SdkUpgrade entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_upgrade", catalog = "game_sdk", uniqueConstraints = @UniqueConstraint(columnNames = {
		"game_id", "channel" }))
public class SdkUpgrade implements java.io.Serializable {

	// Fields

	private Integer upgradeId;
	private Integer gameId;
	private String channel;
	private Integer regex;
	private String version;
	private Integer versionCode;
	private Integer forceTag;
	private String downUrl;
	private Integer statusTag;
	private String memo;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public SdkUpgrade() {
	}

	/** minimal constructor */
	public SdkUpgrade(Integer gameId, String channel, Integer regex,
			String version, Integer forceTag, String downUrl,
			Integer statusTag, Timestamp createTime) {
		this.gameId = gameId;
		this.channel = channel;
		this.regex = regex;
		this.version = version;
		this.forceTag = forceTag;
		this.downUrl = downUrl;
		this.statusTag = statusTag;
		this.createTime = createTime;
	}

	/** full constructor */
	public SdkUpgrade(Integer gameId, String channel, Integer regex,
			String version, Integer versionCode, Integer forceTag,
			String downUrl, Integer statusTag, String memo, Timestamp createTime) {
		this.gameId = gameId;
		this.channel = channel;
		this.regex = regex;
		this.version = version;
		this.versionCode = versionCode;
		this.forceTag = forceTag;
		this.downUrl = downUrl;
		this.statusTag = statusTag;
		this.memo = memo;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "upgrade_id", unique = true, nullable = false)
	public Integer getUpgradeId() {
		return this.upgradeId;
	}

	public void setUpgradeId(Integer upgradeId) {
		this.upgradeId = upgradeId;
	}

	@Column(name = "game_id", nullable = false)
	public Integer getGameId() {
		return this.gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	@Column(name = "channel", nullable = false, length = 64)
	public String getChannel() {
		return this.channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	@Column(name = "regex", nullable = false)
	public Integer getRegex() {
		return this.regex;
	}

	public void setRegex(Integer regex) {
		this.regex = regex;
	}

	@Column(name = "version", nullable = false, length = 64)
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Column(name = "version_code")
	public Integer getVersionCode() {
		return this.versionCode;
	}

	public void setVersionCode(Integer versionCode) {
		this.versionCode = versionCode;
	}

	@Column(name = "force_tag", nullable = false)
	public Integer getForceTag() {
		return this.forceTag;
	}

	public void setForceTag(Integer forceTag) {
		this.forceTag = forceTag;
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

	@Column(name = "create_time", nullable = false, length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}