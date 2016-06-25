package jeecg.ext.sdk.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SdkOrderImsiInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_order_imsi_info", catalog = "game_sdk")
public class SdkOrderImsiInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4260363497806936617L;
	private Integer id;
	private String orderNo;
	private String imsi;
	private String mobilephone;
	private String iccid;
	private String centernumber;
	private Integer provinceNo;
	private Integer areaCode;
	private Integer shieldStatus;
	private Timestamp createdTime;
	private Timestamp updatedTime;

	// Constructors

	/** default constructor */
	public SdkOrderImsiInfo() {
	}

	/** minimal constructor */
	public SdkOrderImsiInfo(String orderNo) {
		this.orderNo = orderNo;
	}

	/** full constructor */
	public SdkOrderImsiInfo(String orderNo, String imsi, String mobilephone,
			String iccid, String centernumber, Integer provinceNo,
			Integer areaCode, Integer shieldStatus, Timestamp createdTime,
			Timestamp updatedTime) {
		this.orderNo = orderNo;
		this.imsi = imsi;
		this.mobilephone = mobilephone;
		this.iccid = iccid;
		this.centernumber = centernumber;
		this.provinceNo = provinceNo;
		this.areaCode = areaCode;
		this.shieldStatus = shieldStatus;
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

	@Column(name = "order_no", nullable = false, length = 64)
	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name = "imsi", length = 128)
	public String getImsi() {
		return this.imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	@Column(name = "mobilephone", length = 11)
	public String getMobilephone() {
		return this.mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	@Column(name = "iccid", length = 30)
	public String getIccid() {
		return this.iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	@Column(name = "centernumber", length = 30)
	public String getCenternumber() {
		return this.centernumber;
	}

	public void setCenternumber(String centernumber) {
		this.centernumber = centernumber;
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

	@Column(name = "shield_status")
	public Integer getShieldStatus() {
		return this.shieldStatus;
	}

	public void setShieldStatus(Integer shieldStatus) {
		this.shieldStatus = shieldStatus;
	}

	@Column(name = "created_time", length = 19)
	public Timestamp getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	@Column(name = "updated_time", length = 19)
	public Timestamp getUpdatedTime() {
		return this.updatedTime;
	}

	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}

}