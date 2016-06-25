package jeecg.ext.sdk.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * GmiPkg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gmi_pkg", catalog = "game")
public class GmiPkg implements java.io.Serializable {

	// Fields

	private String pkg;
	private String name;
	private String ename;
	private String version;
	private String summary;
	private String comments;
	private Date publishDate;
	private String publisher;
	private String language;
	private Float fee;
	private String size;
	private String category;
	private String subCategory;
	private String fromUrl;
	private String source;
	private String icon;
	private String minSdk;
	private String screen;
	private Integer star;
	private Integer hot;
	private String downUrl;
	private Integer idx;
	private String imgSmallWh;
	private String imgLargeWh;
	private Timestamp createTime;
	private Integer status;


	// Constructors

	/** default constructor */
	public GmiPkg() {
	}

	/** minimal constructor */
	public GmiPkg(String pkg, String name, Integer idx, Timestamp createTime,
			Integer status) {
		this.pkg = pkg;
		this.name = name;
		this.idx = idx;
		this.createTime = createTime;
		this.status = status;
	}

	/** full constructor */
	public GmiPkg(String pkg, String name, String ename, String version,
			String summary, String comments, Date publishDate,
			String publisher, String language, Float fee, String size,
			String category, String subCategory, String fromUrl, String source,
			String icon, String minSdk, String screen, Integer star,
			Integer hot, String downUrl, Integer idx, String imgSmallWh,
			String imgLargeWh, Timestamp createTime, Integer status
			) {
		this.pkg = pkg;
		this.name = name;
		this.ename = ename;
		this.version = version;
		this.summary = summary;
		this.comments = comments;
		this.publishDate = publishDate;
		this.publisher = publisher;
		this.language = language;
		this.fee = fee;
		this.size = size;
		this.category = category;
		this.subCategory = subCategory;
		this.fromUrl = fromUrl;
		this.source = source;
		this.icon = icon;
		this.minSdk = minSdk;
		this.screen = screen;
		this.star = star;
		this.hot = hot;
		this.downUrl = downUrl;
		this.idx = idx;
		this.imgSmallWh = imgSmallWh;
		this.imgLargeWh = imgLargeWh;
		this.createTime = createTime;
		this.status = status;
	
	}

	// Property accessors
	@Id
	@Column(name = "pkg", unique = true, nullable = false, length = 100)
	public String getPkg() {
		return this.pkg;
	}

	public void setPkg(String pkg) {
		this.pkg = pkg;
	}

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ename", length = 100)
	public String getEname() {
		return this.ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	@Column(name = "version", length = 64)
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Column(name = "summary", length = 2048)
	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Column(name = "comments", length = 256)
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "publish_date", length = 10)
	public Date getPublishDate() {
		return this.publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	@Column(name = "publisher", length = 100)
	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@Column(name = "language", length = 50)
	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Column(name = "fee", precision = 12, scale = 0)
	public Float getFee() {
		return this.fee;
	}

	public void setFee(Float fee) {
		this.fee = fee;
	}

	@Column(name = "size")
	public String getSize() {
		return this.size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Column(name = "category", length = 50)
	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Column(name = "sub_category", length = 128)
	public String getSubCategory() {
		return this.subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	@Column(name = "from_url", length = 512)
	public String getFromUrl() {
		return this.fromUrl;
	}

	public void setFromUrl(String fromUrl) {
		this.fromUrl = fromUrl;
	}

	@Column(name = "source", length = 256)
	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Column(name = "icon")
	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Column(name = "min_sdk", length = 16)
	public String getMinSdk() {
		return this.minSdk;
	}

	public void setMinSdk(String minSdk) {
		this.minSdk = minSdk;
	}

	@Column(name = "screen", length = 50)
	public String getScreen() {
		return this.screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	@Column(name = "star")
	public Integer getStar() {
		return this.star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	@Column(name = "hot")
	public Integer getHot() {
		return this.hot;
	}

	public void setHot(Integer hot) {
		this.hot = hot;
	}

	@Column(name = "down_url", length = 256)
	public String getDownUrl() {
		return this.downUrl;
	}

	public void setDownUrl(String downUrl) {
		this.downUrl = downUrl;
	}

	@Column(name = "idx", nullable = false)
	public Integer getIdx() {
		return this.idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
	}

	@Column(name = "img_small_wh", length = 16)
	public String getImgSmallWh() {
		return this.imgSmallWh;
	}

	public void setImgSmallWh(String imgSmallWh) {
		this.imgSmallWh = imgSmallWh;
	}

	@Column(name = "img_large_wh", length = 16)
	public String getImgLargeWh() {
		return this.imgLargeWh;
	}

	public void setImgLargeWh(String imgLargeWh) {
		this.imgLargeWh = imgLargeWh;
	}

	@Column(name = "create_time", nullable = false, length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "status", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	 

}