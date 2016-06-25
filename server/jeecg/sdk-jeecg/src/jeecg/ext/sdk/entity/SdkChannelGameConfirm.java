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
 * SdkChannelGameConfirm entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_channel_game_confirm", catalog = "game_sdk")
public class SdkChannelGameConfirm implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6226325926185691936L;
	private Integer id;
	private String channelCode;
	private String channelName;
	private Integer gameId;
	private String gameName;
	private Integer chargetip;
	private Integer chargesuceesstip;
	private Date createdTime;
	private Date updatedTime;
	private Integer originalGameId;
	private String originalGameName;

	// Constructors

	/** default constructor */
	public SdkChannelGameConfirm() {
	}

	/** full constructor */
	public SdkChannelGameConfirm(String channelCode, String channelName,
			Integer gameId, String gameName, Integer chargetip,
			Integer chargesuceesstip, Timestamp createdTime,
			Timestamp updatedTime, Integer originalGameId,
			String originalGameName) {
		this.channelCode = channelCode;
		this.channelName = channelName;
		this.gameId = gameId;
		this.gameName = gameName;
		this.chargetip = chargetip;
		this.chargesuceesstip = chargesuceesstip;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.originalGameId = originalGameId;
		this.originalGameName = originalGameName;
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

	@Column(name = "channel_code", length = 64)
	public String getChannelCode() {
		return this.channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	@Column(name = "channel_name", length = 64)
	public String getChannelName() {
		return this.channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	@Column(name = "game_id")
	public Integer getGameId() {
		return this.gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	@Column(name = "game_name", length = 64)
	public String getGameName() {
		return this.gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	@Column(name = "chargetip")
	public Integer getChargetip() {
		return this.chargetip;
	}

	public void setChargetip(Integer chargetip) {
		this.chargetip = chargetip;
	}

	@Column(name = "chargesuceesstip")
	public Integer getChargesuceesstip() {
		return this.chargesuceesstip;
	}

	public void setChargesuceesstip(Integer chargesuceesstip) {
		this.chargesuceesstip = chargesuceesstip;
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

	@Column(name = "original_game_id")
	public Integer getOriginalGameId() {
		return this.originalGameId;
	}

	public void setOriginalGameId(Integer originalGameId) {
		this.originalGameId = originalGameId;
	}

	@Column(name = "original_game_name", length = 64)
	public String getOriginalGameName() {
		return this.originalGameName;
	}

	public void setOriginalGameName(String originalGameName) {
		this.originalGameName = originalGameName;
	}

}