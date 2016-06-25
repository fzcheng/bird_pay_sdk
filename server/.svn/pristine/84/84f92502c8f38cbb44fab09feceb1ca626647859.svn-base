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
@Table(name = "ad_mission", catalog = "bird_plat")
public class AdMission implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Fields

	private Integer id;
	private String missionId;
	private String missionStrategyId;
	private Integer adId;
	private String createTime;
	private String lastTime;
	private Integer type;
	private Integer status;
	private String beginTime;
	private String endTime;
	private Integer showType;
	private String showImg;
	private String showImgSrc;
	private Integer birdMoney;
	private String missionDetail;
	private String packageName;
	private Integer versionCode;
	private String version;
	private String packageNameDownload;
	private String appId;
	
	// Constructors

	/** default constructor */
	public AdMission() {
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

	@Column(name = "mission_strategy_id", length = 45)
	public String getMissionStrategyId() {
		return this.missionStrategyId;
	}

	public void setMissionStrategyId(String missionStrategyId) {
		this.missionStrategyId = missionStrategyId;
	}

	@Column(name = "adid")
	public Integer getAdId() {
		return this.adId;
	}

	public void setAdId(Integer adId) {
		this.adId = adId;
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

	@Column(name = "type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "begin_time", length = 19)
	public String getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	@Column(name = "end_time", length = 19)
	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	@Column(name = "show_type")
	public Integer getShowType() {
		return showType;
	}

	public void setShowType(Integer showType) {
		this.showType = showType;
	}

	@Column(name = "show_img", length = 255)
	public String getShowImg() {
		return showImg;
	}

	public void setShowImg(String showImg) {
		this.showImg = showImg;
	}

	@Column(name = "show_img_src", length = 255)
	public String getShowImgSrc() {
		return showImgSrc;
	}

	public void setShowImgSrc(String showImgSrc) {
		this.showImgSrc = showImgSrc;
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

	@Column(name = "package_name", length = 255)
	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	@Column(name = "version_code")
	public Integer getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(Integer versionCode) {
		this.versionCode = versionCode;
	}

	@Column(name = "version", length = 45)
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Column(name = "packageName", length = 255)
	public String getPackageNameDownload() {
		return packageNameDownload;
	}

	public void setPackageNameDownload(String packageNameDownload) {
		this.packageNameDownload = packageNameDownload;
	}

	@Column(name = "appid", length = 45)
	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
	
}