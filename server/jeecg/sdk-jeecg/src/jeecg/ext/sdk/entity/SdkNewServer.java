package jeecg.ext.sdk.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SdkNewServer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_new_server", catalog = "game_sdk")
public class SdkNewServer implements java.io.Serializable {

	// Fields

	private Integer nsId;
	private String type;
	private Timestamp startTime;
	private String gamePkg;
	private String gameName;
	private String gameDl;
	private String gameIcon;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public SdkNewServer() {
	}

	/** minimal constructor */
	public SdkNewServer(String type, Timestamp startTime, Timestamp createTime) {
		this.type = type;
		this.startTime = startTime;
		this.createTime = createTime;
	}

	/** full constructor */
	public SdkNewServer(String type, Timestamp startTime, String gamePkg,
			String gameName, String gameDl, String gameIcon,
			Timestamp createTime) {
		this.type = type;
		this.startTime = startTime;
		this.gamePkg = gamePkg;
		this.gameName = gameName;
		this.gameDl = gameDl;
		this.gameIcon = gameIcon;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ns_id", unique = true, nullable = false)
	public Integer getNsId() {
		return this.nsId;
	}

	public void setNsId(Integer nsId) {
		this.nsId = nsId;
	}

	@Column(name = "type", nullable = false, length = 16)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "start_time", nullable = false, length = 19)
	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	@Column(name = "game_pkg", length = 256)
	public String getGamePkg() {
		return this.gamePkg;
	}

	public void setGamePkg(String gamePkg) {
		this.gamePkg = gamePkg;
	}

	@Column(name = "game_name", length = 256)
	public String getGameName() {
		return this.gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	@Column(name = "game_dl", length = 256)
	public String getGameDl() {
		return this.gameDl;
	}

	public void setGameDl(String gameDl) {
		this.gameDl = gameDl;
	}

	@Column(name = "game_icon", length = 256)
	public String getGameIcon() {
		return this.gameIcon;
	}

	public void setGameIcon(String gameIcon) {
		this.gameIcon = gameIcon;
	}

	@Column(name = "create_time", nullable = false, length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}