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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * SdkGame entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_game", catalog = "game_sdk", uniqueConstraints = @UniqueConstraint(columnNames = "app_key"))
public class SdkGame implements java.io.Serializable {

	// Fields

	private Integer gameId;
	private SdkGameCp sdkGameCp;
	private String name;
	private String appKey;
	private Timestamp createTime;
	private String callbackRecharge;
	private String gameUrl;
	private Integer status;
	private String memo;
	private String weipaykey;
	private String packageName;
	private Integer forceTag;
	private String version;
	private Integer ifPush;
	private Set<SdkGameUsedPlan> sdkGameUsedPlans = new HashSet<SdkGameUsedPlan>(
			0);
	private Set<SdkOrderCopy> sdkOrderCopies = new HashSet<SdkOrderCopy>(0);
	private Set<SdkWiipayPaycode> sdkWiipayPaycodes = new HashSet<SdkWiipayPaycode>(
			0);
	private Set<SdkGamePush> sdkGamePushs = new HashSet<SdkGamePush>(0);
	private Set<SdkGamePayment> sdkGamePayments = new HashSet<SdkGamePayment>(0);
	private Set<SdkPushcode> sdkPushcodes = new HashSet<SdkPushcode>(0);
	private Set<SdkOrder> sdkOrders = new HashSet<SdkOrder>(0);

	// Constructors

	/** default constructor */
	public SdkGame() {
	}

	/** minimal constructor */
	public SdkGame(SdkGameCp sdkGameCp, String name, String appKey,
			Timestamp createTime, String callbackRecharge, Integer status) {
		this.sdkGameCp = sdkGameCp;
		this.name = name;
		this.appKey = appKey;
		this.createTime = createTime;
		this.callbackRecharge = callbackRecharge;
		this.status = status;
	}

	/** full constructor */
	public SdkGame(SdkGameCp sdkGameCp, String name, String appKey,
			Timestamp createTime, String callbackRecharge, String gameUrl,
			Integer status, String memo, String weipaykey, String packageName,
			Integer forceTag, String version, Integer ifPush,
			Set<SdkGameUsedPlan> sdkGameUsedPlans,
			Set<SdkOrderCopy> sdkOrderCopies,
			Set<SdkWiipayPaycode> sdkWiipayPaycodes,
			Set<SdkGamePush> sdkGamePushs, Set<SdkGamePayment> sdkGamePayments,
			Set<SdkPushcode> sdkPushcodes,
			Set<SdkOrder> sdkOrders) {
		this.sdkGameCp = sdkGameCp;
		this.name = name;
		this.appKey = appKey;
		this.createTime = createTime;
		this.callbackRecharge = callbackRecharge;
		this.gameUrl = gameUrl;
		this.status = status;
		this.memo = memo;
		this.weipaykey = weipaykey;
		this.packageName = packageName;
		this.forceTag = forceTag;
		this.version = version;
		this.ifPush = ifPush;
		this.sdkGameUsedPlans = sdkGameUsedPlans;
		this.sdkOrderCopies = sdkOrderCopies;
		this.sdkWiipayPaycodes = sdkWiipayPaycodes;
		this.sdkGamePushs = sdkGamePushs;
		this.sdkGamePayments = sdkGamePayments;
		this.sdkPushcodes = sdkPushcodes;
		this.sdkOrders = sdkOrders;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "game_id", unique = true, nullable = false)
	public Integer getGameId() {
		return this.gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cp_id", nullable = false)
	public SdkGameCp getSdkGameCp() {
		return this.sdkGameCp;
	}

	public void setSdkGameCp(SdkGameCp sdkGameCp) {
		this.sdkGameCp = sdkGameCp;
	}

	@Column(name = "name", nullable = false, length = 64)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "app_key", unique = true, nullable = false, length = 64)
	public String getAppKey() {
		return this.appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	@Column(name = "create_time", nullable = false, length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "callback_recharge", nullable = false, length = 256)
	public String getCallbackRecharge() {
		return this.callbackRecharge;
	}

	public void setCallbackRecharge(String callbackRecharge) {
		this.callbackRecharge = callbackRecharge;
	}

	@Column(name = "game_url", length = 256)
	public String getGameUrl() {
		return this.gameUrl;
	}

	public void setGameUrl(String gameUrl) {
		this.gameUrl = gameUrl;
	}

	@Column(name = "status", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "memo", length = 124)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "weipaykey", length = 50)
	public String getWeipaykey() {
		return this.weipaykey;
	}

	public void setWeipaykey(String weipaykey) {
		this.weipaykey = weipaykey;
	}

	@Column(name = "package_name", length = 50)
	public String getPackageName() {
		return this.packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	@Column(name = "force_tag")
	public Integer getForceTag() {
		return this.forceTag;
	}

	public void setForceTag(Integer forceTag) {
		this.forceTag = forceTag;
	}

	@Column(name = "version", length = 15)
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Column(name = "if_push")
	public Integer getIfPush() {
		return this.ifPush;
	}

	public void setIfPush(Integer ifPush) {
		this.ifPush = ifPush;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sdkGame")
	public Set<SdkGameUsedPlan> getSdkGameUsedPlans() {
		return this.sdkGameUsedPlans;
	}

	public void setSdkGameUsedPlans(Set<SdkGameUsedPlan> sdkGameUsedPlans) {
		this.sdkGameUsedPlans = sdkGameUsedPlans;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sdkGame")
	public Set<SdkOrderCopy> getSdkOrderCopies() {
		return this.sdkOrderCopies;
	}

	public void setSdkOrderCopies(Set<SdkOrderCopy> sdkOrderCopies) {
		this.sdkOrderCopies = sdkOrderCopies;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sdkGame")
	public Set<SdkWiipayPaycode> getSdkWiipayPaycodes() {
		return this.sdkWiipayPaycodes;
	}

	public void setSdkWiipayPaycodes(Set<SdkWiipayPaycode> sdkWiipayPaycodes) {
		this.sdkWiipayPaycodes = sdkWiipayPaycodes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sdkGame")
	public Set<SdkGamePush> getSdkGamePushs() {
		return this.sdkGamePushs;
	}

	public void setSdkGamePushs(Set<SdkGamePush> sdkGamePushs) {
		this.sdkGamePushs = sdkGamePushs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sdkGame")
	public Set<SdkGamePayment> getSdkGamePayments() {
		return this.sdkGamePayments;
	}

	public void setSdkGamePayments(Set<SdkGamePayment> sdkGamePayments) {
		this.sdkGamePayments = sdkGamePayments;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sdkGame")
	public Set<SdkPushcode> getSdkPushcodes() {
		return this.sdkPushcodes;
	}

	public void setSdkPushcodes(Set<SdkPushcode> sdkPushcodes) {
		this.sdkPushcodes = sdkPushcodes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sdkGame")
	public Set<SdkOrder> getSdkOrders() {
		return this.sdkOrders;
	}

	public void setSdkOrders(Set<SdkOrder> sdkOrders) {
		this.sdkOrders = sdkOrders;
	}

}