package jeecg.ext.sdk.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SdkBulletin entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_bulletin", catalog = "game_sdk")
public class SdkBulletin implements java.io.Serializable {

	// Fields

	private Integer bulletinId;
	private String detail;
	private Integer type;
	private String gamePkg;
	private String gameName;
	private String gameUrl;
	private String gameIcon;
	private Integer gameRating;
	private String gameCategory;
	private Integer status;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public SdkBulletin() {
	}

	/** minimal constructor */
	public SdkBulletin(String detail, Integer type, Integer status,
			Timestamp createTime) {
		this.detail = detail;
		this.type = type;
		this.status = status;
		this.createTime = createTime;
	}

	/** full constructor */
	public SdkBulletin(String detail, Integer type, String gamePkg,
			String gameName, String gameUrl, String gameIcon,
			Integer gameRating, String gameCategory, Integer status,
			Timestamp createTime) {
		this.detail = detail;
		this.type = type;
		this.gamePkg = gamePkg;
		this.gameName = gameName;
		this.gameUrl = gameUrl;
		this.gameIcon = gameIcon;
		this.gameRating = gameRating;
		this.gameCategory = gameCategory;
		this.status = status;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "bulletin_id", unique = true, nullable = false)
	public Integer getBulletinId() {
		return this.bulletinId;
	}

	public void setBulletinId(Integer bulletinId) {
		this.bulletinId = bulletinId;
	}

	@Column(name = "detail", nullable = false, length = 2048)
	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Column(name = "type", nullable = false)
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "game_pkg", length = 256)
	public String getGamePkg() {
		return this.gamePkg;
	}

	public void setGamePkg(String gamePkg) {
		this.gamePkg = gamePkg;
	}

	@Column(name = "game_name", length = 256)
	public String getGameName() {
		return this.gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	@Column(name = "game_url", length = 256)
	public String getGameUrl() {
		return this.gameUrl;
	}

	public void setGameUrl(String gameUrl) {
		this.gameUrl = gameUrl;
	}

	@Column(name = "game_icon", length = 256)
	public String getGameIcon() {
		return this.gameIcon;
	}

	public void setGameIcon(String gameIcon) {
		this.gameIcon = gameIcon;
	}

	@Column(name = "game_rating")
	public Integer getGameRating() {
		return this.gameRating;
	}

	public void setGameRating(Integer gameRating) {
		this.gameRating = gameRating;
	}

	@Column(name = "game_category", length = 64)
	public String getGameCategory() {
		return this.gameCategory;
	}

	public void setGameCategory(String gameCategory) {
		this.gameCategory = gameCategory;
	}

	@Column(name = "status", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "create_time", nullable = false, length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}