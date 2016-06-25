package jeecg.ext.sdk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AdMission entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ad_missionsub", catalog = "bird_plat")
public class AdMissionSub implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Integer id;
	private String missionId;
	private String missionSubId;
	private Integer no;
	private Integer type;
	private Integer typeValue;
	private Integer status;
	private Integer birdMoney;
	private String missionDetail;
	private String createTime;
	private String lastTime;
	
	// Constructors

	/** default constructor */
	public AdMissionSub() {
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

	@Column(name = "missionid", length = 45)
	public String getMissionId() {
		return this.missionId;
	}

	public void setMissionId(String missionId) {
		this.missionId = missionId;
	}

	@Column(name = "missionsubid", length = 45)
	public String getAdMissionSub() {
		return this.missionSubId;
	}

	public void setAdMissionSub(String missionSubId) {
		this.missionSubId = missionSubId;
	}

	@Column(name = "no")
	public Integer getNo() {
		return this.no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	@Column(name = "type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "type_value")
	public Integer getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(Integer typeValue) {
		this.typeValue = typeValue;
	}
	
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "bird_money")
	public Integer getBirdMoney() {
		return birdMoney;
	}

	public void setBirdMoney(Integer birdMoney) {
		this.birdMoney = birdMoney;
	}

	@Column(name = "mission_detail", length = 255)
	public String getMissionDetail() {
		return missionDetail;
	}

	public void setMissionDetail(String missionDetail) {
		this.missionDetail = missionDetail;
	}

	@Column(name = "create_time", length = 19)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "last_time", length = 19)
	public String getLastTime() {
		return this.lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	
}