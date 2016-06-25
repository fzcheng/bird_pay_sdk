package jeecg.ext.sdk.entity;

import java.sql.Timestamp;
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
 * SdkGamePush entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_game_push", catalog = "game_sdk")
public class SdkGamePush implements java.io.Serializable {

	// Fields

	private Integer id;
	private SdkGame sdkGame;
	private String contentType;
	private String pushTag;
	private String title;
	private String message;
	private String msgId;
	private Timestamp msgTime;
	private String url;

	// Constructors

	/** default constructor */
	public SdkGamePush() {
	}

	/** minimal constructor */
	public SdkGamePush(SdkGame sdkGame, String contentType, String title,
			String message) {
		this.sdkGame = sdkGame;
		this.contentType = contentType;
		this.title = title;
		this.message = message;
	}

	/** full constructor */
	public SdkGamePush(SdkGame sdkGame, String contentType, String pushTag,
			String title, String message, String msgId, Timestamp msgTime,
			String url) {
		this.sdkGame = sdkGame;
		this.contentType = contentType;
		this.pushTag = pushTag;
		this.title = title;
		this.message = message;
		this.msgId = msgId;
		this.msgTime = msgTime;
		this.url = url;
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
	@JoinColumn(name = "game_id", nullable = false)
	public SdkGame getSdkGame() {
		return this.sdkGame;
	}

	public void setSdkGame(SdkGame sdkGame) {
		this.sdkGame = sdkGame;
	}

	@Column(name = "content_type", nullable = false, length = 10)
	public String getContentType() {
		return this.contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Column(name = "push_tag")
	public String getPushTag() {
		return this.pushTag;
	}

	public void setPushTag(String pushTag) {
		this.pushTag = pushTag;
	}

	@Column(name = "title", nullable = false, length = 100)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "message", nullable = false, length = 65535)
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Column(name = "msg_id", length = 20)
	public String getMsgId() {
		return this.msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	@Column(name = "msg_time", length = 19)
	public Timestamp getMsgTime() {
		return this.msgTime;
	}

	public void setMsgTime(Timestamp msgTime) {
		this.msgTime = msgTime;
	}

	@Column(name = "url", length = 100)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}