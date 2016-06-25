package jeecg.ext.sdk.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SdkInformation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_information", catalog = "game_sdk")
public class SdkInformation implements java.io.Serializable {

	// Fields

	private Integer infoId;
	private Integer gameId;
	private String title;
	private String type;
	private String detail;
	private String weburl;
	private Timestamp createTime;
	private Integer status;

	// Constructors

	/** default constructor */
	public SdkInformation() {
	}

	/** minimal constructor */
	public SdkInformation(Integer gameId, String title, String type,
			Timestamp createTime, Integer status) {
		this.gameId = gameId;
		this.title = title;
		this.type = type;
		this.createTime = createTime;
		this.status = status;
	}

	/** full constructor */
	public SdkInformation(Integer gameId, String title, String type,
			String detail, String weburl, Timestamp createTime, Integer status) {
		this.gameId = gameId;
		this.title = title;
		this.type = type;
		this.detail = detail;
		this.weburl = weburl;
		this.createTime = createTime;
		this.status = status;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "info_id", unique = true, nullable = false)
	public Integer getInfoId() {
		return this.infoId;
	}

	public void setInfoId(Integer infoId) {
		this.infoId = infoId;
	}

	@Column(name = "game_id", nullable = false)
	public Integer getGameId() {
		return this.gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	@Column(name = "title", nullable = false, length = 256)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "type", nullable = false, length = 64)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "detail", length = 2048)
	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Column(name = "weburl", length = 1024)
	public String getWeburl() {
		return this.weburl;
	}

	public void setWeburl(String weburl) {
		this.weburl = weburl;
	}

	@Column(name = "create_time", nullable = false, length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "status", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}