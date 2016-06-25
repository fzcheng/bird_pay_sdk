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
 * SdkPropsSetting entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_props_setting", catalog = "game_sdk")
public class SdkPropsSetting implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -729703101718024225L;
	private Integer id;
	private String propsName;
	private Float amount;
	private String propsDesc;
	private String propsAlias;
	private Date createdTime;
	private Date updatedTime;
	private Integer gameId;
	private Integer operatorPayChannelId;
	private Integer useStatus;

	// Constructors

	/** default constructor */
	public SdkPropsSetting() {
	}

	/** full constructor */
	public SdkPropsSetting(String propsName, Float amount, String propsDesc,
			String propsAlias, Timestamp createdTime, Timestamp updatedTime,
			Integer gameId, Integer operatorPayChannelId, Integer useStatus) {
		this.propsName = propsName;
		this.amount = amount;
		this.propsDesc = propsDesc;
		this.propsAlias = propsAlias;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.gameId = gameId;
		this.operatorPayChannelId = operatorPayChannelId;
		this.useStatus = useStatus;
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

	@Column(name = "props_name", length = 64)
	public String getPropsName() {
		return this.propsName;
	}

	public void setPropsName(String propsName) {
		this.propsName = propsName;
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

	@Column(name = "props_alias", length = 64)
	public String getPropsAlias() {
		return this.propsAlias;
	}

	public void setPropsAlias(String propsAlias) {
		this.propsAlias = propsAlias;
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

	@Column(name = "game_id")
	public Integer getGameId() {
		return this.gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	@Column(name = "operator_pay_channel_id")
	public Integer getOperatorPayChannelId() {
		return this.operatorPayChannelId;
	}

	public void setOperatorPayChannelId(Integer operatorPayChannelId) {
		this.operatorPayChannelId = operatorPayChannelId;
	}

	@Column(name = "use_status")
	public Integer getUseStatus() {
		return this.useStatus;
	}

	public void setUseStatus(Integer useStatus) {
		this.useStatus = useStatus;
	}

}