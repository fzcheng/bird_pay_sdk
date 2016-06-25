package jeecg.ext.sdk.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SdkGiftVcodeId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class SdkGiftVcodeId implements java.io.Serializable {

	// Fields

	private Integer giftId;
	private String vcode;

	// Constructors

	/** default constructor */
	public SdkGiftVcodeId() {
	}

	/** full constructor */
	public SdkGiftVcodeId(Integer giftId, String vcode) {
		this.giftId = giftId;
		this.vcode = vcode;
	}

	// Property accessors

	@Column(name = "gift_id", nullable = false)
	public Integer getGiftId() {
		return this.giftId;
	}

	public void setGiftId(Integer giftId) {
		this.giftId = giftId;
	}

	@Column(name = "vcode", nullable = false, length = 64)
	public String getVcode() {
		return this.vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SdkGiftVcodeId))
			return false;
		SdkGiftVcodeId castOther = (SdkGiftVcodeId) other;

		return ((this.getGiftId() == castOther.getGiftId()) || (this
				.getGiftId() != null && castOther.getGiftId() != null && this
				.getGiftId().equals(castOther.getGiftId())))
				&& ((this.getVcode() == castOther.getVcode()) || (this
						.getVcode() != null && castOther.getVcode() != null && this
						.getVcode().equals(castOther.getVcode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getGiftId() == null ? 0 : this.getGiftId().hashCode());
		result = 37 * result
				+ (getVcode() == null ? 0 : this.getVcode().hashCode());
		return result;
	}

}