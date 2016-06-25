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
 * SdkTelephone entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_telephone", catalog = "game_sdk")
public class SdkTelephone implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer provinceNo;
	private Integer areaCode;
	private String mobilephone;
	private String imsi;
	private Date createdTime;
	private Date updatedTime;
	private String imei;

	// Constructors

	/** default constructor */
	public SdkTelephone() {
	}

	/** full constructor */
	public SdkTelephone(Integer provinceNo, Integer areaCode,
			String mobilephone, String imsi, Timestamp createdTime,
			Timestamp updatedTime, String imei) {
		this.provinceNo = provinceNo;
		this.areaCode = areaCode;
		this.mobilephone = mobilephone;
		this.imsi = imsi;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.imei = imei;
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

	@Column(name = "province_no")
	public Integer getProvinceNo() {
		return this.provinceNo;
	}

	public void setProvinceNo(Integer provinceNo) {
		this.provinceNo = provinceNo;
	}

	@Column(name = "area_code")
	public Integer getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(Integer areaCode) {
		this.areaCode = areaCode;
	}

	@Column(name = "mobilephone", length = 11)
	public String getMobilephone() {
		return this.mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	@Column(name = "imsi", length = 400)
	public String getImsi() {
		return this.imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
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

	@Column(name = "imei", length = 400)
	public String getImei() {
		return this.imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

}