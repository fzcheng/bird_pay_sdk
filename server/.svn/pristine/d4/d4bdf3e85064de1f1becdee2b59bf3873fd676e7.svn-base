package jeecg.ext.sdk.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SdkGift entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_gift", catalog = "game_sdk")
public class SdkGift implements java.io.Serializable {

	// Fields

	private Integer giftId;
	private Integer gameId;
	private String title;
	private String image;
	private String detail;
	private String vcodes;
	private Integer remain;
	private Integer total;
	private Timestamp beginTime;
	private Timestamp endTime;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public SdkGift() {
	}

	/** minimal constructor */
	public SdkGift(Integer gameId, String title, Integer remain, Integer total,
			Timestamp endTime, Timestamp createTime) {
		this.gameId = gameId;
		this.title = title;
		this.remain = remain;
		this.total = total;
		this.endTime = endTime;
		this.createTime = createTime;
	}

	/** full constructor */
	public SdkGift(Integer gameId, String title, String image, String detail,
			String vcodes, Integer remain, Integer total, Timestamp beginTime,
			Timestamp endTime, Timestamp createTime) {
		this.gameId = gameId;
		this.title = title;
		this.image = image;
		this.detail = detail;
		this.vcodes = vcodes;
		this.remain = remain;
		this.total = total;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "gift_id", unique = true, nullable = false)
	public Integer getGiftId() {
		return this.giftId;
	}

	public void setGiftId(Integer giftId) {
		this.giftId = giftId;
	}

	@Column(name = "game_id", nullable = false)
	public Integer getGameId() {
		return this.gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	@Column(name = "title", nullable = false, length = 256)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "image", length = 256)
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name = "detail", length = 2048)
	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Column(name = "vcodes", length = 65535)
	public String getVcodes() {
		return this.vcodes;
	}

	public void setVcodes(String vcodes) {
		this.vcodes = vcodes;
	}

	@Column(name = "remain", nullable = false)
	public Integer getRemain() {
		return this.remain;
	}

	public void setRemain(Integer remain) {
		this.remain = remain;
	}

	@Column(name = "total", nullable = false)
	public Integer getTotal() {
		return this.total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	@Column(name = "begin_time", length = 19)
	public Timestamp getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Timestamp beginTime) {
		this.beginTime = beginTime;
	}

	@Column(name = "end_time", nullable = false, length = 19)
	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	@Column(name = "create_time", nullable = false, length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}