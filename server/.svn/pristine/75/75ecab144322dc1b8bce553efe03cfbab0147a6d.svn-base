package jeecg.ext.sdk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Ad entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ad", catalog = "bird_plat")
public class Ad implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Fields
	private Integer id;
	private String adId;
	private String adverId;
	private String adname;
	private String beginTime;
	private String endTime;
	private String adKey;
	private Integer status;
	private Integer fund;
	private Integer fundOutput;
	private String detail;
	private String createTime;
	private String lastTime;
	
	// Constructors

	/** default constructor */
	public Ad() {
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

	@Column(name = "adid")
	public String getAdId() {
		return this.adId;
	}

	public void setAdId(String adId) {
		this.adId = adId;
	}

	@Column(name = "adverid", length = 45)
	public String getAdverId() {
		return adverId;
	}

	public void setAdverId(String adverId) {
		this.adverId = adverId;
	}
	
	@Column(name = "adname", length = 45)
	public String getAdname() {
		return adname;
	}

	public void setAdname(String adname) {
		this.adname = adname;
	}

	@Column(name = "begin_time", length = 19)
	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	@Column(name = "end_time", length = 19)
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Column(name = "adkey", length = 45)
	public String getAdKey() {
		return adKey;
	}

	public void setAdKey(String adKey) {
		this.adKey = adKey;
	}
	
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "fund")
	public Integer getFund() {
		return fund;
	}

	public void setFund(Integer fund) {
		this.fund = fund;
	}

	@Column(name = "fund_output")
	public Integer getFundOutput() {
		return fundOutput;
	}

	public void setFundOutput(Integer fundOutput) {
		this.fundOutput = fundOutput;
	}

	@Column(name = "detail", length = 255)
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
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