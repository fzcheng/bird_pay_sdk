package jeecg.ext.sdk.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * SdkStatGameCore entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_stat_game_core", catalog = "game_sdk")
public class SdkStatGameCore implements java.io.Serializable {

	// Fields

	private SdkStatGameCoreId id;
	private Integer cntUserReg;
	private Integer cntUserActive;
	private Integer cntUserRecharge;
	private Integer totalAmount;

	// Constructors

	/** default constructor */
	public SdkStatGameCore() {
	}

	/** full constructor */
	public SdkStatGameCore(SdkStatGameCoreId id, Integer cntUserReg,
			Integer cntUserActive, Integer cntUserRecharge, Integer totalAmount) {
		this.id = id;
		this.cntUserReg = cntUserReg;
		this.cntUserActive = cntUserActive;
		this.cntUserRecharge = cntUserRecharge;
		this.totalAmount = totalAmount;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "gameId", column = @Column(name = "game_id", nullable = false)),
			@AttributeOverride(name = "statDay", column = @Column(name = "stat_day", nullable = false, length = 10)) })
	public SdkStatGameCoreId getId() {
		return this.id;
	}

	public void setId(SdkStatGameCoreId id) {
		this.id = id;
	}

	@Column(name = "cnt_user_reg", nullable = false)
	public Integer getCntUserReg() {
		return this.cntUserReg;
	}

	public void setCntUserReg(Integer cntUserReg) {
		this.cntUserReg = cntUserReg;
	}

	@Column(name = "cnt_user_active", nullable = false)
	public Integer getCntUserActive() {
		return this.cntUserActive;
	}

	public void setCntUserActive(Integer cntUserActive) {
		this.cntUserActive = cntUserActive;
	}

	@Column(name = "cnt_user_recharge", nullable = false)
	public Integer getCntUserRecharge() {
		return this.cntUserRecharge;
	}

	public void setCntUserRecharge(Integer cntUserRecharge) {
		this.cntUserRecharge = cntUserRecharge;
	}

	@Column(name = "total_amount", nullable = false)
	public Integer getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

}