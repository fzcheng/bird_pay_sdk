package jeecg.ext.sdk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SdkCityAddr entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_city_addr", catalog = "game_sdk")
public class SdkCityAddr implements java.io.Serializable {

	// Fields

	private String provinceId;
	private Integer id;
	private String citynm;

	// Constructors

	/** default constructor */
	public SdkCityAddr() {
	}

	/** minimal constructor */
	public SdkCityAddr(String provinceId) {
		this.provinceId = provinceId;
	}

	/** full constructor */
	public SdkCityAddr(String provinceId, Integer id, String citynm) {
		this.provinceId = provinceId;
		this.id = id;
		this.citynm = citynm;
	}

	// Property accessors
	@Id
	@Column(name = "province_id", unique = true, nullable = false, length = 15)
	public String getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	@Column(name = "id")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "citynm", length = 240)
	public String getCitynm() {
		return this.citynm;
	}

	public void setCitynm(String citynm) {
		this.citynm = citynm;
	}

}