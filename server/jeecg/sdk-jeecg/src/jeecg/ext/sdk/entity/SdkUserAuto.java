package jeecg.ext.sdk.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SdkUserAuto entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_user_auto", catalog = "game_sdk")
public class SdkUserAuto implements java.io.Serializable {

	// Fields

	private String deviceId;
	private String imei;
	private Timestamp regTime;
	private Integer regGame;
	private String regChannel;
	private String regVersion;
	private String regIp;

	// Constructors

	/** default constructor */
	public SdkUserAuto() {
	}

	/** full constructor */
	public SdkUserAuto(String deviceId, String imei, Timestamp regTime,
			Integer regGame, String regChannel, String regVersion, String regIp) {
		this.deviceId = deviceId;
		this.imei = imei;
		this.regTime = regTime;
		this.regGame = regGame;
		this.regChannel = regChannel;
		this.regVersion = regVersion;
		this.regIp = regIp;
	}

	// Property accessors
	@Id
	@Column(name = "device_id", unique = true, nullable = false, length = 50)
	public String getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@Column(name = "imei", nullable = false, length = 64)
	public String getImei() {
		return this.imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
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

	@Column(name = "reg_ip", nullable = false, length = 64)
	public String getRegIp() {
		return this.regIp;
	}

	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}

}