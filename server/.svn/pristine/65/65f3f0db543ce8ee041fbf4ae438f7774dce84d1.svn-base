package jeecg.ext.sdk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * SdkChannel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_channel", catalog = "game_sdk", uniqueConstraints = @UniqueConstraint(columnNames = "channel_num"))
public class SdkChannel implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2292969502690985700L;
	private Integer id;
	private Integer pid;
	private String channelName;
	private String channelNum;
	private String memo;
	private String loginName;
	private String loginPasswd;

	// Constructors

	/** default constructor */
	public SdkChannel() {
	}

	/** minimal constructor */
	public SdkChannel(Integer pid, String channelName, String channelNum) {
		this.pid = pid;
		this.channelName = channelName;
		this.channelNum = channelNum;
	}

	/** full constructor */
	public SdkChannel(Integer pid, String channelName, String channelNum,
			String memo, String loginName, String loginPasswd) {
		this.pid = pid;
		this.channelName = channelName;
		this.channelNum = channelNum;
		this.memo = memo;
		this.loginName = loginName;
		this.loginPasswd = loginPasswd;
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

	@Column(name = "pid", nullable = false)
	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	@Column(name = "channel_name", nullable = false, length = 20)
	public String getChannelName() {
		return this.channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	@Column(name = "channel_num", unique = true, nullable = false, length = 100)
	public String getChannelNum() {
		return this.channelNum;
	}

	public void setChannelNum(String channelNum) {
		this.channelNum = channelNum;
	}

	@Column(name = "memo", length = 100)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "login_name", length = 64)
	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "login_passwd", length = 64)
	public String getLoginPasswd() {
		return this.loginPasswd;
	}

	public void setLoginPasswd(String loginPasswd) {
		this.loginPasswd = loginPasswd;
	}

}