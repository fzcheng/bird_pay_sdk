package jeecg.ext.sdk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * SdkGamePlan entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_game_plan", catalog = "game_sdk")
public class SdkGamePlan implements java.io.Serializable {

	// Fields

	private Integer id;
	private SdkPlan sdkPlan;
	private String gamePkg;
	private String gameName;
	private String gameUrl;
	private String gameIcon;
	private Integer gameRating;
	private String gameCategory;
	private Integer idx;

	// Constructors

	/** default constructor */
	public SdkGamePlan() {
	}

	/** minimal constructor */
	public SdkGamePlan(SdkPlan sdkPlan, String gamePkg, String gameName,
			Integer idx) {
		this.sdkPlan = sdkPlan;
		this.gamePkg = gamePkg;
		this.gameName = gameName;
		this.idx = idx;
	}

	/** full constructor */
	public SdkGamePlan(SdkPlan sdkPlan, String gamePkg, String gameName,
			String gameUrl, String gameIcon, Integer gameRating,
			String gameCategory, Integer idx) {
		this.sdkPlan = sdkPlan;
		this.gamePkg = gamePkg;
		this.gameName = gameName;
		this.gameUrl = gameUrl;
		this.gameIcon = gameIcon;
		this.gameRating = gameRating;
		this.gameCategory = gameCategory;
		this.idx = idx;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "plan_id", nullable = false)
	public SdkPlan getSdkPlan() {
		return this.sdkPlan;
	}

	public void setSdkPlan(SdkPlan sdkPlan) {
		this.sdkPlan = sdkPlan;
	}

	@Column(name = "game_pkg", nullable = false, length = 256)
	public String getGamePkg() {
		return this.gamePkg;
	}

	public void setGamePkg(String gamePkg) {
		this.gamePkg = gamePkg;
	}

	@Column(name = "game_name", nullable = false, length = 256)
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

	@Column(name = "game_category", length = 50)
	public String getGameCategory() {
		return this.gameCategory;
	}

	public void setGameCategory(String gameCategory) {
		this.gameCategory = gameCategory;
	}

	@Column(name = "idx", nullable = false)
	public Integer getIdx() {
		return this.idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
	}

}