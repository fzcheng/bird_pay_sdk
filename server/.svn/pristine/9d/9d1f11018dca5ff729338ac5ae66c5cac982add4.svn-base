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
 * SdkGamePayment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_game_payment", catalog = "game_sdk")
public class SdkGamePayment implements java.io.Serializable {

	// Fields

	private Integer id;
	private SdkPayment sdkPayment;
	private SdkGame sdkGame;
	private Integer payShow;
	private Integer idx;

	// Constructors

	/** default constructor */
	public SdkGamePayment() {
	}

	/** full constructor */
	public SdkGamePayment(SdkPayment sdkPayment, SdkGame sdkGame,
			Integer payShow, Integer idx) {
		this.sdkPayment = sdkPayment;
		this.sdkGame = sdkGame;
		this.payShow = payShow;
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
	@JoinColumn(name = "payment_id", nullable = false)
	public SdkPayment getSdkPayment() {
		return this.sdkPayment;
	}

	public void setSdkPayment(SdkPayment sdkPayment) {
		this.sdkPayment = sdkPayment;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "game_id", nullable = false)
	public SdkGame getSdkGame() {
		return this.sdkGame;
	}

	public void setSdkGame(SdkGame sdkGame) {
		this.sdkGame = sdkGame;
	}

	@Column(name = "pay_show", nullable = false)
	public Integer getPayShow() {
		return this.payShow;
	}

	public void setPayShow(Integer payShow) {
		this.payShow = payShow;
	}

	@Column(name = "idx", nullable = false)
	public Integer getIdx() {
		return this.idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
	}

}