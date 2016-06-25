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
 * SdkCenternumber entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_centernumber", catalog = "game_sdk")
public class SdkCenternumber implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6676171630514200650L;
	private Integer id;
	private Integer provinceNo;
	private Integer areaCode;
	private String centernumber;
	private Date createdTime;
	private Date updatedTime;

	// Constructors

	/** default constructor */
	public SdkCenternumber() {
	}

	/** full constructor */
	public SdkCenternumber(Integer provinceNo, Integer areaCode,
			String centernumber, Timestamp createdTime, Timestamp updatedTime) {
		this.provinceNo = provinceNo;
		this.areaCode = areaCode;
		this.centernumber = centernumber;
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

	@Column(name = "centernumber", length = 30)
	public String getCenternumber() {
		return this.centernumber;
	}

	public void setCenternumber(String centernumber) {
		this.centernumber = centernumber;
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