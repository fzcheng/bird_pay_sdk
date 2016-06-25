package jeecg.ext.sdk.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SdkSmsGame entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_sms_game", catalog = "game_sdk")
public class SdkSmsGame implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7102973823580090378L;
	private Integer id;
	private Integer gameId;
	private Integer sendState;
	private Date createdTime;
	private Date updatedTime;
	private Integer smstip;

	// Constructors

	/** default constructor */
	public SdkSmsGame() {
	}

	/** full constructor */
	public SdkSmsGame(Integer gameId, Integer sendState, Timestamp createdTime,
			Timestamp updatedTime, Integer smstip) {
		this.gameId = gameId;
		this.sendState = sendState;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.smstip = smstip;
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

	@Column(name = "game_id")
	public Integer getGameId() {
		return this.gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	@Column(name = "send_state")
	public Integer getSendState() {
		return this.sendState;
	}

	public void setSendState(Integer sendState) {
		this.sendState = sendState;
	}

	@Column(name = "created_time", length = 19)
	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Column(name = "updated_time", length = 19)
	public Date getUpdatedTime() {
		return this.updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	@Column(name = "smstip")
	public Integer getSmstip() {
		return this.smstip;
	}

	public void setSmstip(Integer smstip) {
		this.smstip = smstip;
	}

}