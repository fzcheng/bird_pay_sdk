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
 * SdkTelephoneCenternumber entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_telephone_centernumber", catalog = "game_sdk")
public class SdkTelephoneCenternumber implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4477071007321348230L;
	private Integer id;
	private String imsi;
	private String centernumber;
	private Date createdTime;
	private Date updatedTime;

	// Constructors

	/** default constructor */
	public SdkTelephoneCenternumber() {
	}

	/** full constructor */
	public SdkTelephoneCenternumber(String imsi, String centernumber,
			Timestamp createdTime, Timestamp updatedTime) {
		this.imsi = imsi;
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

	@Column(name = "imsi", length = 400)
	public String getImsi() {
		return this.imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
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