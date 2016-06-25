package jeecg.ext.sdk.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * SdkStatGameRemain entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_stat_game_remain", catalog = "game_sdk")
public class SdkStatGameRemain implements java.io.Serializable {

	// Fields

	private SdkStatGameRemainId id;
	private Integer cntNew;
	private Integer cntRemain2;
	private Integer cntRemain3;
	private Integer cntRemain7;

	// Constructors

	/** default constructor */
	public SdkStatGameRemain() {
	}

	/** minimal constructor */
	public SdkStatGameRemain(SdkStatGameRemainId id) {
		this.id = id;
	}

	/** full constructor */
	public SdkStatGameRemain(SdkStatGameRemainId id, Integer cntNew,
			Integer cntRemain2, Integer cntRemain3, Integer cntRemain7) {
		this.id = id;
		this.cntNew = cntNew;
		this.cntRemain2 = cntRemain2;
		this.cntRemain3 = cntRemain3;
		this.cntRemain7 = cntRemain7;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "gameId", column = @Column(name = "game_id", nullable = false)),
			@AttributeOverride(name = "statDay", column = @Column(name = "stat_day", nullable = false, length = 10)) })
	public SdkStatGameRemainId getId() {
		return this.id;
	}

	public void setId(SdkStatGameRemainId id) {
		this.id = id;
	}

	@Column(name = "cnt_new")
	public Integer getCntNew() {
		return this.cntNew;
	}

	public void setCntNew(Integer cntNew) {
		this.cntNew = cntNew;
	}

	@Column(name = "cnt_remain_2")
	public Integer getCntRemain2() {
		return this.cntRemain2;
	}

	public void setCntRemain2(Integer cntRemain2) {
		this.cntRemain2 = cntRemain2;
	}

	@Column(name = "cnt_remain_3")
	public Integer getCntRemain3() {
		return this.cntRemain3;
	}

	public void setCntRemain3(Integer cntRemain3) {
		this.cntRemain3 = cntRemain3;
	}

	@Column(name = "cnt_remain_7")
	public Integer getCntRemain7() {
		return this.cntRemain7;
	}

	public void setCntRemain7(Integer cntRemain7) {
		this.cntRemain7 = cntRemain7;
	}

}