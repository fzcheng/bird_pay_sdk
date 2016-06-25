package jeecg.ext.sdk.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SdkUpgradeJarGameId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class SdkUpgradeJarGameId implements java.io.Serializable {

	// Fields

	private String versionCode;
	private Integer gameId;

	// Constructors

	/** default constructor */
	public SdkUpgradeJarGameId() {
	}

	/** full constructor */
	public SdkUpgradeJarGameId(String versionCode, Integer gameId) {
		this.versionCode = versionCode;
		this.gameId = gameId;
	}

	// Property accessors

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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SdkUpgradeJarGameId))
			return false;
		SdkUpgradeJarGameId castOther = (SdkUpgradeJarGameId) other;

		return ((this.getVersionCode() == castOther.getVersionCode()) || (this
				.getVersionCode() != null && castOther.getVersionCode() != null && this
				.getVersionCode().equals(castOther.getVersionCode())))
				&& ((this.getGameId() == castOther.getGameId()) || (this
						.getGameId() != null && castOther.getGameId() != null && this
						.getGameId().equals(castOther.getGameId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getVersionCode() == null ? 0 : this.getVersionCode()
						.hashCode());
		result = 37 * result
				+ (getGameId() == null ? 0 : this.getGameId().hashCode());
		return result;
	}

}