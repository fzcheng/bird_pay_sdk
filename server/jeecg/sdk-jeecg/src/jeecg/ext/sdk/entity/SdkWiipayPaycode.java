package jeecg.ext.sdk.entity;

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
 * SdkWiipayPaycode entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_wiipay_paycode", catalog = "game_sdk")
public class SdkWiipayPaycode implements java.io.Serializable {

	// Fields

	private Integer id;
	private SdkGame sdkGame;
	private String name;
	private String price;
	private String payCode;
	private String pakageName;

	// Constructors

	/** default constructor */
	public SdkWiipayPaycode() {
	}

	/** minimal constructor */
	public SdkWiipayPaycode(SdkGame sdkGame, String name, String price,
			String payCode) {
		this.sdkGame = sdkGame;
		this.name = name;
		this.price = price;
		this.payCode = payCode;
	}

	/** full constructor */
	public SdkWiipayPaycode(SdkGame sdkGame, String name, String price,
			String payCode, String pakageName) {
		this.sdkGame = sdkGame;
		this.name = name;
		this.price = price;
		this.payCode = payCode;
		this.pakageName = pakageName;
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
	@JoinColumn(name = "game_id", nullable = false)
	public SdkGame getSdkGame() {
		return this.sdkGame;
	}

	public void setSdkGame(SdkGame sdkGame) {
		this.sdkGame = sdkGame;
	}

	@Column(name = "name", nullable = false, length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "price", nullable = false, length = 20)
	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Column(name = "pay_code", nullable = false, length = 20)
	public String getPayCode() {
		return this.payCode;
	}

	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}

	@Column(name = "pakage_name", length = 100)
	public String getPakageName() {
		return this.pakageName;
	}

	public void setPakageName(String pakageName) {
		this.pakageName = pakageName;
	}

}