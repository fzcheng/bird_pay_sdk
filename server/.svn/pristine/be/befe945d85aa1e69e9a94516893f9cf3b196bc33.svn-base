package jeecg.ext.sdk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SdkGameDomainForcp entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_game_domain_forcp", catalog = "game_sdk")
public class SdkGameDomainForcp implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer gameId;
	private String domain;
	private Integer status;

	// Constructors

	/** default constructor */
	public SdkGameDomainForcp() {
	}

	/** full constructor */
	public SdkGameDomainForcp(Integer gameId, String domain, Integer status) {
		this.gameId = gameId;
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

	@Column(name = "game_id", nullable = false)
	public Integer getGameId() {
		return this.gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	@Column(name = "domain", nullable = false, length = 400)
	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Column(name = "STATUS", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}