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
 * SdkIpaddr entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_ipaddr", catalog = "game_sdk")
public class SdkIpaddr implements java.io.Serializable {

	// Fields

	private Integer id;
	private String faddrno;
	private String faddrnm;
	private String fgb;
	private String fpostcode;
	private String fabbr;
	private Integer forder;
	private String fabbren;
	private Date createdTime;
	private Date updatedTime;

	// Constructors

	/** default constructor */
	public SdkIpaddr() {
	}

	/** full constructor */
	public SdkIpaddr(String faddrno, String faddrnm, String fgb,
			String fpostcode, String fabbr, Integer forder, String fabbren,
			Timestamp createdTime, Timestamp updatedTime) {
		this.faddrno = faddrno;
		this.faddrnm = faddrnm;
		this.fgb = fgb;
		this.fpostcode = fpostcode;
		this.fabbr = fabbr;
		this.forder = forder;
		this.fabbren = fabbren;
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

	@Column(name = "faddrno", length = 12)
	public String getFaddrno() {
		return this.faddrno;
	}

	public void setFaddrno(String faddrno) {
		this.faddrno = faddrno;
	}

	@Column(name = "faddrnm", length = 80)
	public String getFaddrnm() {
		return this.faddrnm;
	}

	public void setFaddrnm(String faddrnm) {
		this.faddrnm = faddrnm;
	}

	@Column(name = "fgb", length = 20)
	public String getFgb() {
		return this.fgb;
	}

	public void setFgb(String fgb) {
		this.fgb = fgb;
	}

	@Column(name = "fpostcode", length = 10)
	public String getFpostcode() {
		return this.fpostcode;
	}

	public void setFpostcode(String fpostcode) {
		this.fpostcode = fpostcode;
	}

	@Column(name = "fabbr", length = 50)
	public String getFabbr() {
		return this.fabbr;
	}

	public void setFabbr(String fabbr) {
		this.fabbr = fabbr;
	}

	@Column(name = "forder")
	public Integer getForder() {
		return this.forder;
	}

	public void setForder(Integer forder) {
		this.forder = forder;
	}

	@Column(name = "fabbren", length = 20)
	public String getFabbren() {
		return this.fabbren;
	}

	public void setFabbren(String fabbren) {
		this.fabbren = fabbren;
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