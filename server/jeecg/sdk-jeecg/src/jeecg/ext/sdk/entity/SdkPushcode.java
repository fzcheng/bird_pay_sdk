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
 * SdkPushcode entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_pushcode", catalog = "game_sdk")
public class SdkPushcode implements java.io.Serializable {

	// Fields

	private Integer id;
	private SdkGame sdkGame;
	private String pushMasterSecret;
	private String pushAppKey;

	// Constructors

	/** default constructor */
	public SdkPushcode() {
	}

	/** full constructor */
	public SdkPushcode(SdkGame sdkGame, String pushMasterSecret,
			String pushAppKey) {
		this.sdkGame = sdkGame;
		this.pushMasterSecret = pushMasterSecret;
		this.pushAppKey = pushAppKey;
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
	@JoinColumn(name = "game_id", nullable = false)
	public SdkGame getSdkGame() {
		return this.sdkGame;
	}

	public void setSdkGame(SdkGame sdkGame) {
		this.sdkGame = sdkGame;
	}

	@Column(name = "push_masterSecret", nullable = false, length = 30)
	public String getPushMasterSecret() {
		return this.pushMasterSecret;
	}

	public void setPushMasterSecret(String pushMasterSecret) {
		this.pushMasterSecret = pushMasterSecret;
	}

	@Column(name = "push_appKey", nullable = false, length = 30)
	public String getPushAppKey() {
		return this.pushAppKey;
	}

	public void setPushAppKey(String pushAppKey) {
		this.pushAppKey = pushAppKey;
	}

}