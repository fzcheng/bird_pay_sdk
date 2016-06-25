package jeecg.ext.sdk.entity;

import javax.persistence.Column;

/**
 * SdkGameChannelId entity. @author MyEclipse Persistence Tools
 */

public class SdkGameChannelId implements java.io.Serializable {

	// Fields

	private Integer gameId;
	private String channel;

	// Constructors

	/** default constructor */
	public SdkGameChannelId() {
	}

	/** full constructor */
	public SdkGameChannelId(Integer gameId, String channel) {
		this.gameId = gameId;
		this.channel = channel;
	}

	// Property accessors
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SdkGameChannelId))
			return false;
		SdkGameChannelId castOther = (SdkGameChannelId) other;

		return ((this.getGameId() == castOther.getGameId()) || (this
				.getGameId() != null && castOther.getGameId() != null && this
				.getGameId().equals(castOther.getGameId())))
				&& ((this.getChannel() == castOther.getChannel()) || (this
						.getChannel() != null && castOther.getChannel() != null && this
						.getChannel().equals(castOther.getChannel())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getGameId() == null ? 0 : this.getGameId().hashCode());
		result = 37 * result
				+ (getChannel() == null ? 0 : this.getChannel().hashCode());
		return result;
	}

}