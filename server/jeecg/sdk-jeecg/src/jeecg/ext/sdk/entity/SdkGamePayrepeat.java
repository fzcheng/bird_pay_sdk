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
 * SdkGamePayrepeat entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_game_payrepeat", catalog = "game_sdk")
public class SdkGamePayrepeat implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 211036281970350407L;
	private Integer id;
	private Integer gameId;
	private Integer usestate;
	private Date createdTime;
	private Date updatedTime;

	// Constructors

	/** default constructor */
	public SdkGamePayrepeat() {
	}

	/** full constructor */
	public SdkGamePayrepeat(Integer gameId, Integer usestate,
			Timestamp createdTime, Timestamp updatedTime) {
		this.gameId = gameId;
		this.usestate = usestate;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
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

	@Column(name = "usestate")
	public Integer getUsestate() {
		return this.usestate;
	}

	public void setUsestate(Integer usestate) {
		this.usestate = usestate;
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

}