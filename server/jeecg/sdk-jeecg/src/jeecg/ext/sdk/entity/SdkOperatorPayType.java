package jeecg.ext.sdk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SdkOperatorPayType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_operator_pay_type", catalog = "game_sdk")
public class SdkOperatorPayType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4621515238167563686L;
	private Integer id;
	private Integer type;
	private Integer operator;
	private Float minPrice;
	private Float maxPrice;
	private Integer idx;
	private String ver;

	// Constructors

	/** default constructor */
	public SdkOperatorPayType() {
	}

	/** minimal constructor */
	public SdkOperatorPayType(Integer type, Integer operator, Float minPrice,
			Float maxPrice, Integer idx) {
		this.type = type;
		this.operator = operator;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.idx = idx;
	}

	/** full constructor */
	public SdkOperatorPayType(Integer type, Integer operator, Float minPrice,
			Float maxPrice, Integer idx, String ver) {
		this.type = type;
		this.operator = operator;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.idx = idx;
		this.ver = ver;
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

	@Column(name = "type", nullable = false)
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "operator", nullable = false)
	public Integer getOperator() {
		return this.operator;
	}

	public void setOperator(Integer operator) {
		this.operator = operator;
	}

	@Column(name = "min_price", nullable = false, precision = 12, scale = 0)
	public Float getMinPrice() {
		return this.minPrice;
	}

	public void setMinPrice(Float minPrice) {
		this.minPrice = minPrice;
	}

	@Column(name = "max_price", nullable = false, precision = 12, scale = 0)
	public Float getMaxPrice() {
		return this.maxPrice;
	}

	public void setMaxPrice(Float maxPrice) {
		this.maxPrice = maxPrice;
	}

	@Column(name = "idx", nullable = false)
	public Integer getIdx() {
		return this.idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
	}

	@Column(name = "ver", length = 15)
	public String getVer() {
		return this.ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

}