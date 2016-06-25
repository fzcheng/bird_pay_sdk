package jeecg.ext.sdk.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SdkStatGameCoreId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class SdkStatGameCoreId implements java.io.Serializable {

	// Fields

	private Integer gameId;
	private Date statDay;

	// Constructors

	/** default constructor */
	public SdkStatGameCoreId() {
	}

	/** full constructor */
	public SdkStatGameCoreId(Integer gameId, Date statDay) {
		this.gameId = gameId;
		this.statDay = statDay;
	}

	// Property accessors

	@Column(name = "game_id", nullable = false)
	public Integer getGameId() {
		return this.gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "stat_day", nullable = false, length = 10)
	public Date getStatDay() {
		return this.statDay;
	}

	public void setStatDay(Date statDay) {
		this.statDay = statDay;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SdkStatGameCoreId))
			return false;
		SdkStatGameCoreId castOther = (SdkStatGameCoreId) other;

		return ((this.getGameId() == castOther.getGameId()) || (this
				.getGameId() != null && castOther.getGameId() != null && this
				.getGameId().equals(castOther.getGameId())))
				&& ((this.getStatDay() == castOther.getStatDay()) || (this
						.getStatDay() != null && castOther.getStatDay() != null && this
						.getStatDay().equals(castOther.getStatDay())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getGameId() == null ? 0 : this.getGameId().hashCode());
		result = 37 * result
				+ (getStatDay() == null ? 0 : this.getStatDay().hashCode());
		return result;
	}

}