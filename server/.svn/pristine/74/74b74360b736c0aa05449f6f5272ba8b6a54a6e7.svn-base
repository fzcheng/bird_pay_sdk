package jeecg.ext.sdk.entity;

import java.util.Date;
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
 * SdkOperatorPayment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_operator_payment", catalog = "game_sdk", uniqueConstraints = @UniqueConstraint(columnNames = "type"))
public class SdkOperatorPayment implements java.io.Serializable {
  private static final long serialVersionUID = -2043101594914418464L;
  private Integer id;
  private Short type;
  private String name;
  private Double dayLimit;
  private Double monthLimit;
  private Date createdTime;
  private Date updatedTime;
  private Set<SdkPayPassage> sdkPayPassages = new HashSet<SdkPayPassage>(0);

  // Constructors

  /** default constructor */
  public SdkOperatorPayment() {
  }

  /** full constructor */
  public SdkOperatorPayment(Short type, String name, Double dayLimit, Double monthLimit, Date createdTime, Date updatedTime,
      Set<SdkPayPassage> sdkPayPassages) {
    this.type = type;
    this.name = name;
    this.dayLimit = dayLimit;
    this.monthLimit = monthLimit;
    this.createdTime = createdTime;
    this.updatedTime = updatedTime;
    this.sdkPayPassages = sdkPayPassages;
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

  @Column(name = "type", unique = true)
  public Short getType() {
    return this.type;
  }

  public void setType(Short type) {
    this.type = type;
  }

  @Column(name = "name", length = 32)
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(name = "day_limit", precision = 10)
  public Double getDayLimit() {
    return this.dayLimit;
  }

  public void setDayLimit(Double dayLimit) {
    this.dayLimit = dayLimit;
  }

  @Column(name = "month_limit", precision = 10)
  public Double getMonthLimit() {
    return this.monthLimit;
  }

  public void setMonthLimit(Double monthLimit) {
    this.monthLimit = monthLimit;
  }

  @Column(name = "created_time", length = 19)
  public Date getCreatedTime() {
    return this.createdTime;
  }

  public void setCreatedTime(Date createdTime) {
    this.createdTime = createdTime;
  }

  @Column(name = "updated_time", length = 19)
  public Date getUpdatedTime() {
    return this.updatedTime;
  }

  public void setUpdatedTime(Date updatedTime) {
    this.updatedTime = updatedTime;
  }

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sdkOperatorPayment")
  public Set<SdkPayPassage> getSdkPayPassages() {
    return this.sdkPayPassages;
  }

  public void setSdkPayPassages(Set<SdkPayPassage> sdkPayPassages) {
    this.sdkPayPassages = sdkPayPassages;
  }

}