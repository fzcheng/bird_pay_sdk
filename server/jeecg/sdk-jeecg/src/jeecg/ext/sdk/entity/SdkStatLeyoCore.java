package jeecg.ext.sdk.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SdkStatLeyoCore entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_stat_leyo_core", catalog = "game_sdk")
public class SdkStatLeyoCore implements java.io.Serializable {

	// Fields

	private Date statDay;
	private Integer cntUserNew;
	private Integer cntUserRecharge;
	private Integer totalAmount;
	private Integer cntUserActive;

	// Constructors

	/** default constructor */
	public SdkStatLeyoCore() {
	}

	/** full constructor */
	public SdkStatLeyoCore(Date statDay, Integer cntUserNew,
			Integer cntUserRecharge, Integer totalAmount, Integer cntUserActive) {
		this.statDay = statDay;
		this.cntUserNew = cntUserNew;
		this.cntUserRecharge = cntUserRecharge;
		this.totalAmount = totalAmount;
		this.cntUserActive = cntUserActive;
	}

	// Property accessors
	@Id
	@Temporal(TemporalType.DATE)
	@Column(name = "stat_day", unique = true, nullable = false, length = 10)
	public Date getStatDay() {
		return this.statDay;
	}

	public void setStatDay(Date statDay) {
		this.statDay = statDay;
	}

	@Column(name = "cnt_user_new", nullable = false)
	public Integer getCntUserNew() {
		return this.cntUserNew;
	}

	public void setCntUserNew(Integer cntUserNew) {
		this.cntUserNew = cntUserNew;
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

	@Column(name = "cnt_user_active", nullable = false)
	public Integer getCntUserActive() {
		return this.cntUserActive;
	}

	public void setCntUserActive(Integer cntUserActive) {
		this.cntUserActive = cntUserActive;
	}

}