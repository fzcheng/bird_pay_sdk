package jeecg.ext.sdk.entity;

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

/**
 * SdkPlan entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_plan", catalog = "game_sdk")
public class SdkPlan implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Set<SdkGamePlan> sdkGamePlans = new HashSet<SdkGamePlan>(0);

	// Constructors

	/** default constructor */
	public SdkPlan() {
	}

	/** minimal constructor */
	public SdkPlan(String name) {
		this.name = name;
	}

	/** full constructor */
	public SdkPlan(String name, Set<SdkGamePlan> sdkGamePlans) {
		this.name = name;
		this.sdkGamePlans = sdkGamePlans;
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

	@Column(name = "name", nullable = false, length = 30)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sdkPlan")
	public Set<SdkGamePlan> getSdkGamePlans() {
		return this.sdkGamePlans;
	}

	public void setSdkGamePlans(Set<SdkGamePlan> sdkGamePlans) {
		this.sdkGamePlans = sdkGamePlans;
	}

}