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
 * SdkGameUsedPlan entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_game_used_plan", catalog = "game_sdk")
public class SdkGameUsedPlan implements java.io.Serializable {

	// Fields

	private Integer id;
	private SdkPlan sdkPlan;
	private SdkGame sdkGame;

	// Constructors

	/** default constructor */
	public SdkGameUsedPlan() {
	}

	/** full constructor */
	public SdkGameUsedPlan(SdkPlan sdkPlan, SdkGame sdkGame) {
		this.sdkPlan = sdkPlan;
		this.sdkGame = sdkGame;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "game_id", nullable = false)
	public SdkGame getSdkGame() {
		return this.sdkGame;
	}

	public void setSdkGame(SdkGame sdkGame) {
		this.sdkGame = sdkGame;
	}

}