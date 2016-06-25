package jeecg.ext.sdk.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SdkMobileVcode entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_mobile_vcode", catalog = "game_sdk")
public class SdkMobileVcode implements java.io.Serializable {

	// Fields

	private Integer vid;
	private String vcode;
	private String mobile;
	private Integer uid;
	private Integer type;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public SdkMobileVcode() {
	}

	/** minimal constructor */
	public SdkMobileVcode(String vcode, String mobile, Integer type,
			Timestamp createTime) {
		this.vcode = vcode;
		this.mobile = mobile;
		this.type = type;
		this.createTime = createTime;
	}

	/** full constructor */
	public SdkMobileVcode(String vcode, String mobile, Integer uid,
			Integer type, Timestamp createTime) {
		this.vcode = vcode;
		this.mobile = mobile;
		this.uid = uid;
		this.type = type;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "vid", unique = true, nullable = false)
	public Integer getVid() {
		return this.vid;
	}

	public void setVid(Integer vid) {
		this.vid = vid;
	}

	@Column(name = "vcode", nullable = false, length = 8)
	public String getVcode() {
		return this.vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	@Column(name = "mobile", nullable = false, length = 16)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "uid")
	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	@Column(name = "type", nullable = false)
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "create_time", nullable = false, length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}