package jeecg.ext.sdk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SdkProvinceAddr entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_province_addr", catalog = "game_sdk")
public class SdkProvinceAddr implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1630417093157933395L;
	private Integer id;
	private String provincenm;

	// Constructors

	/** default constructor */
	public SdkProvinceAddr() {
	}

	/** minimal constructor */
	public SdkProvinceAddr(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public SdkProvinceAddr(Integer id, String provincenm) {
		this.id = id;
		this.provincenm = provincenm;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "provincenm", length = 240)
	public String getProvincenm() {
		return this.provincenm;
	}

	public void setProvincenm(String provincenm) {
		this.provincenm = provincenm;
	}

}