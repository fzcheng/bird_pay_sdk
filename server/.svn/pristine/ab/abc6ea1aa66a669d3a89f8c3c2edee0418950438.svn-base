package jeecg.ext.sdk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SdkGameDomain entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_game_domain", catalog = "game_sdk")
public class SdkGameDomain implements java.io.Serializable {

	// Fields

	private Integer id;
	private String domain;
	private Integer status;

	// Constructors

	/** default constructor */
	public SdkGameDomain() {
	}

	/** full constructor */
	public SdkGameDomain(String domain, Integer status) {
		this.domain = domain;
		this.status = status;
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

	@Column(name = "domain", nullable = false, length = 400)
	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Column(name = "status", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}