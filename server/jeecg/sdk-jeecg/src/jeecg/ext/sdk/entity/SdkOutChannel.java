package jeecg.ext.sdk.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * SdkOutChannel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_out_channel", catalog = "game_sdk", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class SdkOutChannel implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String gameName;
	private Timestamp createTime;
	private Set<SdkOutChannelDetail> sdkOutChannelDetails = new HashSet<SdkOutChannelDetail>(
			0);

	// Constructors

	/** default constructor */
	public SdkOutChannel() {
	}

	/** minimal constructor */
	public SdkOutChannel(String name, String gameName, Timestamp createTime) {
		this.name = name;
		this.gameName = gameName;
		this.createTime = createTime;
	}

	/** full constructor */
	public SdkOutChannel(String name, String gameName, Timestamp createTime,
			Set<SdkOutChannelDetail> sdkOutChannelDetails) {
		this.name = name;
		this.gameName = gameName;
		this.createTime = createTime;
		this.sdkOutChannelDetails = sdkOutChannelDetails;
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

	@Column(name = "name", unique = true, nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "game_name", nullable = false, length = 50)
	public String getGameName() {
		return this.gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	@Column(name = "create_time", nullable = false, length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sdkOutChannel")
	public Set<SdkOutChannelDetail> getSdkOutChannelDetails() {
		return this.sdkOutChannelDetails;
	}

	public void setSdkOutChannelDetails(
			Set<SdkOutChannelDetail> sdkOutChannelDetails) {
		this.sdkOutChannelDetails = sdkOutChannelDetails;
	}

}