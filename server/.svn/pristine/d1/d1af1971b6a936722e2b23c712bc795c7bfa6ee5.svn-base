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
import javax.persistence.UniqueConstraint;

/**
 * SdkPayment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_payment", catalog = "game_sdk", uniqueConstraints = @UniqueConstraint(columnNames = "pay_type"))
public class SdkPayment implements java.io.Serializable {
  private static final long serialVersionUID = 2754758045669853947L;
  private Integer id;
  private String payName;
  private Integer payType;
  private String payCnName;
  private Set<SdkOrderCopy> sdkOrderCopies = new HashSet<SdkOrderCopy>(0);
  private Set<SdkOrder> sdkOrders = new HashSet<SdkOrder>(0);
  private Set<SdkGamePayment> sdkGamePayments = new HashSet<SdkGamePayment>(0);
  private Set<SdkPayPassage> sdkPayPassages = new HashSet<SdkPayPassage>(0);

  // Constructors

  /** default constructor */
  public SdkPayment() {
  }

  /** minimal constructor */
  public SdkPayment(String payName, Integer payType, String payCnName) {
    this.payName = payName;
    this.payType = payType;
    this.payCnName = payCnName;
  }

  /** full constructor */
  public SdkPayment(String payName, Integer payType, String payCnName, Set<SdkOrderCopy> sdkOrderCopies, Set<SdkOrder> sdkOrders,
      Set<SdkGamePayment> sdkGamePayments, Set<SdkPayPassage> sdkPayPassages) {
    this.payName = payName;
    this.payType = payType;
    this.payCnName = payCnName;
    this.sdkOrderCopies = sdkOrderCopies;
    this.sdkOrders = sdkOrders;
    this.sdkGamePayments = sdkGamePayments;
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

  @Column(name = "pay_name", nullable = false, length = 20)
  public String getPayName() {
    return this.payName;
  }

  public void setPayName(String payName) {
    this.payName = payName;
  }

  @Column(name = "pay_type", unique = true, nullable = false)
  public Integer getPayType() {
    return this.payType;
  }

  public void setPayType(Integer payType) {
    this.payType = payType;
  }

  @Column(name = "pay_cn_name", nullable = false, length = 50)
  public String getPayCnName() {
    return this.payCnName;
  }

  public void setPayCnName(String payCnName) {
    this.payCnName = payCnName;
  }

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sdkPayment")
  public Set<SdkOrderCopy> getSdkOrderCopies() {
    return this.sdkOrderCopies;
  }

  public void setSdkOrderCopies(Set<SdkOrderCopy> sdkOrderCopies) {
    this.sdkOrderCopies = sdkOrderCopies;
  }

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sdkPayment")
  public Set<SdkOrder> getSdkOrders() {
    return this.sdkOrders;
  }

  public void setSdkOrders(Set<SdkOrder> sdkOrders) {
    this.sdkOrders = sdkOrders;
  }

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sdkPayment")
  public Set<SdkGamePayment> getSdkGamePayments() {
    return this.sdkGamePayments;
  }

  public void setSdkGamePayments(Set<SdkGamePayment> sdkGamePayments) {
    this.sdkGamePayments = sdkGamePayments;
  }

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sdkPayment")
  public Set<SdkPayPassage> getSdkPayPassages() {
    return this.sdkPayPassages;
  }

  public void setSdkPayPassages(Set<SdkPayPassage> sdkPayPassages) {
    this.sdkPayPassages = sdkPayPassages;
  }

}