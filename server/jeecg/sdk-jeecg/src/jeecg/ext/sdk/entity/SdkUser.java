package jeecg.ext.sdk.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * SdkUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_user", catalog = "game_sdk")
public class SdkUser implements java.io.Serializable {

	// Fields

	private Integer uid;
	private String pwd;
	private String deviceId;
	private String mobile;
	private Integer bind;
	private String nickName;
	private Timestamp regTime;
	private Integer regGame;
	private String regChannel;
	private String regVersion;
	private String regIp;
	private Timestamp firstLoginTime;
	private Timestamp loginTime;
	private Integer loginGame;
	private String loginChannel;
	private String loginVersion;
	private Integer loginTimes;
	private String loginIp;
	private Integer status;
	private Set<SdkOrder> sdkOrders = new HashSet<SdkOrder>(0);

	// Constructors

	/** default constructor */
	public SdkUser() {
	}

	/** minimal constructor */
	public SdkUser(String pwd, Timestamp regTime, Integer regGame,
			String regChannel, String regVersion, Integer loginTimes,
			Integer status) {
		this.pwd = pwd;
		this.regTime = regTime;
		this.regGame = regGame;
		this.regChannel = regChannel;
		this.regVersion = regVersion;
		this.loginTimes = loginTimes;
		this.status = status;
	}

	/** full constructor */
	public SdkUser(String pwd, String deviceId, String mobile, Integer bind,
			String nickName, Timestamp regTime, Integer regGame,
			String regChannel, String regVersion, String regIp,
			Timestamp firstLoginTime, Timestamp loginTime, Integer loginGame,
			String loginChannel, String loginVersion, Integer loginTimes,
			String loginIp, Integer status, Set<SdkOrder> sdkOrders) {
		this.pwd = pwd;
		this.deviceId = deviceId;
		this.mobile = mobile;
		this.bind = bind;
		this.nickName = nickName;
		this.regTime = regTime;
		this.regGame = regGame;
		this.regChannel = regChannel;
		this.regVersion = regVersion;
		this.regIp = regIp;
		this.firstLoginTime = firstLoginTime;
		this.loginTime = loginTime;
		this.loginGame = loginGame;
		this.loginChannel = loginChannel;
		this.loginVersion = loginVersion;
		this.loginTimes = loginTimes;
		this.loginIp = loginIp;
		this.status = status;
		this.sdkOrders = sdkOrders;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "uid", unique = true, nullable = false)
	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	@Column(name = "pwd", nullable = false, length = 64)
	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Column(name = "device_id", length = 50)
	public String getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@Column(name = "mobile", length = 11)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "bind")
	public Integer getBind() {
		return this.bind;
	}

	public void setBind(Integer bind) {
		this.bind = bind;
	}

	@Column(name = "nick_name", length = 64)
	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Column(name = "reg_time", nullable = false, length = 19)
	public Timestamp getRegTime() {
		return this.regTime;
	}

	public void setRegTime(Timestamp regTime) {
		this.regTime = regTime;
	}

	@Column(name = "reg_game", nullable = false)
	public Integer getRegGame() {
		return this.regGame;
	}

	public void setRegGame(Integer regGame) {
		this.regGame = regGame;
	}

	@Column(name = "reg_channel", nullable = false, length = 64)
	public String getRegChannel() {
		return this.regChannel;
	}

	public void setRegChannel(String regChannel) {
		this.regChannel = regChannel;
	}

	@Column(name = "reg_version", nullable = false, length = 64)
	public String getRegVersion() {
		return this.regVersion;
	}

	public void setRegVersion(String regVersion) {
		this.regVersion = regVersion;
	}

	@Column(name = "reg_ip", length = 64)
	public String getRegIp() {
		return this.regIp;
	}

	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}

	@Column(name = "first_login_time", length = 19)
	public Timestamp getFirstLoginTime() {
		return this.firstLoginTime;
	}

	public void setFirstLoginTime(Timestamp firstLoginTime) {
		this.firstLoginTime = firstLoginTime;
	}

	@Column(name = "login_time", length = 19)
	public Timestamp getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}

	@Column(name = "login_game")
	public Integer getLoginGame() {
		return this.loginGame;
	}

	public void setLoginGame(Integer loginGame) {
		this.loginGame = loginGame;
	}

	@Column(name = "login_channel", length = 64)
	public String getLoginChannel() {
		return this.loginChannel;
	}

	public void setLoginChannel(String loginChannel) {
		this.loginChannel = loginChannel;
	}

	@Column(name = "login_version", length = 64)
	public String getLoginVersion() {
		return this.loginVersion;
	}

	public void setLoginVersion(String loginVersion) {
		this.loginVersion = loginVersion;
	}

	@Column(name = "login_times", nullable = false)
	public Integer getLoginTimes() {
		return this.loginTimes;
	}

	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}

	@Column(name = "login_ip", length = 64)
	public String getLoginIp() {
		return this.loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	@Column(name = "status", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sdkUser")
	public Set<SdkOrder> getSdkOrders() {
		return this.sdkOrders;
	}

	public void setSdkOrders(Set<SdkOrder> sdkOrders) {
		this.sdkOrders = sdkOrders;
	}

}