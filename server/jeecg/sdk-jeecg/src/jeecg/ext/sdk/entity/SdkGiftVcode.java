package jeecg.ext.sdk.entity;

import java.sql.Timestamp;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * SdkGiftVcode entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_gift_vcode", catalog = "game_sdk")
public class SdkGiftVcode implements java.io.Serializable {

	// Fields

	private SdkGiftVcodeId id;
	private Integer uid;
	private Timestamp usedTime;

	// Constructors

	/** default constructor */
	public SdkGiftVcode() {
	}

	/** minimal constructor */
	public SdkGiftVcode(SdkGiftVcodeId id) {
		this.id = id;
	}

	/** full constructor */
	public SdkGiftVcode(SdkGiftVcodeId id, Integer uid, Timestamp usedTime) {
		this.id = id;
		this.uid = uid;
		this.usedTime = usedTime;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "giftId", column = @Column(name = "gift_id", nullable = false)),
			@AttributeOverride(name = "vcode", column = @Column(name = "vcode", nullable = false, length = 64)) })
	public SdkGiftVcodeId getId() {
		return this.id;
	}

	public void setId(SdkGiftVcodeId id) {
		this.id = id;
	}

	@Column(name = "uid")
	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	@Column(name = "used_time", length = 19)
	public Timestamp getUsedTime() {
		return this.usedTime;
	}

	public void setUsedTime(Timestamp usedTime) {
		this.usedTime = usedTime;
	}

}