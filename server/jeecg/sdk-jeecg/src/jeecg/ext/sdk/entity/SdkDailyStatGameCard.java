package jeecg.ext.sdk.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * SdkDailyStatGameCard entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_daily_stat_game_card", catalog = "game_sdk", uniqueConstraints = @UniqueConstraint(columnNames = {
		"daily", "game_id", "operator_type", "operator_pay_channel_id",
		"channel_num", "amount" }))
public class SdkDailyStatGameCard implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2564123621752438084L;
	private Integer id;
	private Date daily;
	private Integer gameId;
	private Integer operatorType;
	private Integer operatorPayChannelId;
	private String channelNum;
	private Float amount;
	private Float totalAmount;
	private Date updatedTime;
	private Date createdTime;

	// Constructors

	/** default constructor */
	public SdkDailyStatGameCard() {
	}

	/** minimal constructor */
	public SdkDailyStatGameCard(Timestamp daily, Integer gameId,
			Integer operatorType, String channelNum) {
		this.daily = daily;
		this.gameId = gameId;
		this.operatorType = operatorType;
		this.channelNum = channelNum;
	}

	/** full constructor */
	public SdkDailyStatGameCard(Timestamp daily, Integer gameId,
			Integer operatorType, Integer operatorPayChannelId,
			String channelNum, Float amount, Float totalAmount,
			Timestamp updatedTime, Timestamp createdTime) {
		this.daily = daily;
		this.gameId = gameId;
		this.operatorType = operatorType;
		this.operatorPayChannelId = operatorPayChannelId;
		this.channelNum = channelNum;
		this.amount = amount;
		this.totalAmount = totalAmount;
		this.updatedTime = updatedTime;
		this.createdTime = createdTime;
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

	@Column(name = "daily", nullable = false, length = 19)
	public Date getDaily() {
		return this.daily;
	}

	public void setDaily(Date daily) {
		this.daily = daily;
	}

	@Column(name = "game_id", nullable = false)
	public Integer getGameId() {
		return this.gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	@Column(name = "operator_type", nullable = false)
	public Integer getOperatorType() {
		return this.operatorType;
	}

	public void setOperatorType(Integer operatorType) {
		this.operatorType = operatorType;
	}

	@Column(name = "operator_pay_channel_id")
	public Integer getOperatorPayChannelId() {
		return this.operatorPayChannelId;
	}

	public void setOperatorPayChannelId(Integer operatorPayChannelId) {
		this.operatorPayChannelId = operatorPayChannelId;
	}

	@Column(name = "channel_num", nullable = false, length = 64)
	public String getChannelNum() {
		return this.channelNum;
	}

	public void setChannelNum(String channelNum) {
		this.channelNum = channelNum;
	}

	@Column(name = "amount", precision = 12, scale = 0)
	public Float getAmount() {
		return this.amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	@Column(name = "total_amount", precision = 12, scale = 0)
	public Float getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Column(name = "updated_time", length = 19)
	public Date getUpdatedTime() {
		return this.updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	@Column(name = "created_time", length = 19)
	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

}