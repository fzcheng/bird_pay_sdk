package jeecg.ext.sdk.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * SdkProps entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_props", catalog = "game_sdk", uniqueConstraints = @UniqueConstraint(columnNames = "propsid"))
public class SdkProps implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7396803726439661511L;
	private Integer id;
	private String propsid;
	private String tradeid;
	private String tradexdata;
	private Integer gameId;
	private String propsName;
	private String propsAlias;
	private Float amount;
	private String propsDesc;
	private Integer useStatus;
	private String periods;
	private Date createdTime;
	private Date updatedTime;

	// Constructors

	/** default constructor */
	public SdkProps() {
	}

	/** minimal constructor */
	public SdkProps(String propsid) {
		this.propsid = propsid;
	}

	/** full constructor */
	public SdkProps(String propsid, String tradeid, String tradexdata,
			Integer gameId, String propsName, String propsAlias, Float amount,
			String propsDesc, Integer useStatus, String periods,
			Timestamp createdTime, Timestamp updatedTime) {
		this.propsid = propsid;
		this.tradeid = tradeid;
		this.tradexdata = tradexdata;
		this.gameId = gameId;
		this.propsName = propsName;
		this.propsAlias = propsAlias;
		this.amount = amount;
		this.propsDesc = propsDesc;
		this.useStatus = useStatus;
		this.periods = periods;
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

	@Column(name = "propsid", unique = true, nullable = false, length = 10)
	public String getPropsid() {
		return this.propsid;
	}

	public void setPropsid(String propsid) {
		this.propsid = propsid;
	}

	@Column(name = "tradeid", length = 256)
	public String getTradeid() {
		return this.tradeid;
	}

	public void setTradeid(String tradeid) {
		this.tradeid = tradeid;
	}

	@Column(name = "tradexdata", length = 256)
	public String getTradexdata() {
		return this.tradexdata;
	}

	public void setTradexdata(String tradexdata) {
		this.tradexdata = tradexdata;
	}

	@Column(name = "game_id")
	public Integer getGameId() {
		return this.gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	@Column(name = "props_name", length = 64)
	public String getPropsName() {
		return this.propsName;
	}

	public void setPropsName(String propsName) {
		this.propsName = propsName;
	}

	@Column(name = "props_alias", length = 64)
	public String getPropsAlias() {
		return this.propsAlias;
	}

	public void setPropsAlias(String propsAlias) {
		this.propsAlias = propsAlias;
	}

	@Column(name = "amount", precision = 12, scale = 0)
	public Float getAmount() {
		return this.amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	@Column(name = "props_desc", length = 64)
	public String getPropsDesc() {
		return this.propsDesc;
	}

	public void setPropsDesc(String propsDesc) {
		this.propsDesc = propsDesc;
	}

	@Column(name = "use_status")
	public Integer getUseStatus() {
		return this.useStatus;
	}

	public void setUseStatus(Integer useStatus) {
		this.useStatus = useStatus;
	}

	@Column(name = "periods", length = 256)
	public String getPeriods() {
		return this.periods;
	}

	public void setPeriods(String periods) {
		this.periods = periods;
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