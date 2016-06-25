package jeecg.ext.sdk.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * SdkOutChannelDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_out_channel_detail", catalog = "game_sdk")
public class SdkOutChannelDetail implements java.io.Serializable {

	// Fields

	private Integer id;
	private SdkOutChannel sdkOutChannel;
	private Timestamp inputTime;
	private Integer regNumber;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public SdkOutChannelDetail() {
	}

	/** full constructor */
	public SdkOutChannelDetail(SdkOutChannel sdkOutChannel,
			Timestamp inputTime, Integer regNumber, Timestamp createTime) {
		this.sdkOutChannel = sdkOutChannel;
		this.inputTime = inputTime;
		this.regNumber = regNumber;
		this.createTime = createTime;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "channel_id", nullable = false)
	public SdkOutChannel getSdkOutChannel() {
		return this.sdkOutChannel;
	}

	public void setSdkOutChannel(SdkOutChannel sdkOutChannel) {
		this.sdkOutChannel = sdkOutChannel;
	}

	@Column(name = "input_time", nullable = false, length = 19)
	public Timestamp getInputTime() {
		return this.inputTime;
	}

	public void setInputTime(Timestamp inputTime) {
		this.inputTime = inputTime;
	}

	@Column(name = "reg_number", nullable = false)
	public Integer getRegNumber() {
		return this.regNumber;
	}

	public void setRegNumber(Integer regNumber) {
		this.regNumber = regNumber;
	}

	@Column(name = "create_time", nullable = false, length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}