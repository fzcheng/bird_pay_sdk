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
 * SdkPhoneSegment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_phone_segment", catalog = "game_sdk")
public class SdkPhoneSegment implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8575703316392218891L;
	private Integer id;
	private Integer provinceNo;
	private Integer areaCode;
	private Integer phoneSegmentCode;
	private Date createTime;
	private Date updatedTime;
	private Integer operationType;

	// Constructors

	/** default constructor */
	public SdkPhoneSegment() {
	}

	/** full constructor */
	public SdkPhoneSegment(Integer provinceNo, Integer areaCode,
			Integer phoneSegmentCode, Timestamp createTime,
			Timestamp updatedTime, Integer operationType) {
		this.provinceNo = provinceNo;
		this.areaCode = areaCode;
		this.phoneSegmentCode = phoneSegmentCode;
		this.createTime = createTime;
		this.updatedTime = updatedTime;
		this.operationType = operationType;
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

	@Column(name = "phone_segment_code")
	public Integer getPhoneSegmentCode() {
		return this.phoneSegmentCode;
	}

	public void setPhoneSegmentCode(Integer phoneSegmentCode) {
		this.phoneSegmentCode = phoneSegmentCode;
	}

	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "updated_time", length = 19)
	public Date getUpdatedTime() {
		return this.updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	@Column(name = "operation_type")
	public Integer getOperationType() {
		return this.operationType;
	}

	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}

}