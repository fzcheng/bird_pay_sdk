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
 * SdkGameAdvertisementList entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_game_advertisement_list", catalog = "game_sdk")
public class SdkGameAdvertisementList implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6213254034993274266L;
	// Fields

	private Integer id;
	private String advertisementUrl;
	private String iconUrl;
	private Date createdTime;
	private Date updatedTime;
	private Integer ifshow;

	// Constructors

	/** default constructor */
	public SdkGameAdvertisementList() {
	}

	/** full constructor */
	public SdkGameAdvertisementList(String advertisementUrl, String iconUrl,
			Timestamp createdTime, Timestamp updatedTime, Integer ifshow) {
		this.advertisementUrl = advertisementUrl;
		this.iconUrl = iconUrl;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.ifshow = ifshow;
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

	@Column(name = "advertisement_url", length = 400)
	public String getAdvertisementUrl() {
		return this.advertisementUrl;
	}

	public void setAdvertisementUrl(String advertisementUrl) {
		this.advertisementUrl = advertisementUrl;
	}

	@Column(name = "icon_url", length = 400)
	public String getIconUrl() {
		return this.iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
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

	@Column(name = "ifshow")
	public Integer getIfshow() {
		return this.ifshow;
	}

	public void setIfshow(Integer ifshow) {
		this.ifshow = ifshow;
	}

}