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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * SdkPayPassage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_pay_passage", catalog = "game_sdk", uniqueConstraints = @UniqueConstraint(columnNames = "code"))
public class SdkPayPassage implements java.io.Serializable {
  private static final long serialVersionUID = 283021537714946267L;
  private Integer id;
  private SdkPayment sdkPayment;
  private SdkOperatorPayment sdkOperatorPayment;
  private String name;
  private String code;
  private String sdkMinVer;
  private Integer billingProvinceId;
  private String shieldingProvinceIds;
  private String shieldingSegments;
  private Integer smsType;
  private String smsFetchUrl;
  private Date createdTime;
  private Date updatedTime;
  private Set<SdkPayPassageParam> sdkPayPassageParams = new HashSet<SdkPayPassageParam>(0);

  // Constructors

  /** default constructor */
  public SdkPayPassage() {
  }

  /** minimal constructor */
  public SdkPayPassage(SdkPayment sdkPayment, String name, String code) {
    this.sdkPayment = sdkPayment;
    this.name = name;
    this.code = code;
  }

  /** full constructor */
  public SdkPayPassage(SdkPayment sdkPayment, SdkOperatorPayment sdkOperatorPayment, String name, String code, String sdkMinVer, Integer billingProvinceId,
      String shieldingProvinceIds, String shieldingSegments, Integer smsType, String smsFetchUrl, Date createdTime, Date updatedTime,
      Set<SdkPayPassageParam> sdkPayPassageParams) {
    this.sdkPayment = sdkPayment;
    this.sdkOperatorPayment = sdkOperatorPayment;
    this.name = name;
    this.code = code;
    this.sdkMinVer = sdkMinVer;
    this.billingProvinceId = billingProvinceId;
    this.shieldingProvinceIds = shieldingProvinceIds;
    this.shieldingSegments = shieldingSegments;
    this.smsType = smsType;
    this.smsFetchUrl = smsFetchUrl;
    this.createdTime = createdTime;
    this.updatedTime = updatedTime;
    this.sdkPayPassageParams = sdkPayPassageParams;
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
  @JoinColumn(name = "payment_id", nullable = false)
  public SdkPayment getSdkPayment() {
    return this.sdkPayment;
  }

  public void setSdkPayment(SdkPayment sdkPayment) {
    this.sdkPayment = sdkPayment;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "operator_payment_id")
  public SdkOperatorPayment getSdkOperatorPayment() {
    return this.sdkOperatorPayment;
  }

  public void setSdkOperatorPayment(SdkOperatorPayment sdkOperatorPayment) {
    this.sdkOperatorPayment = sdkOperatorPayment;
  }

  @Column(name = "name", nullable = false, length = 64)
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(name = "code", unique = true, nullable = false, length = 64)
  public String getCode() {
    return this.code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  @Column(name = "sdk_min_ver", length = 32)
  public String getSdkMinVer() {
    return this.sdkMinVer;
  }

  public void setSdkMinVer(String sdkMinVer) {
    this.sdkMinVer = sdkMinVer;
  }

  @Column(name = "billing_province_id")
  public Integer getBillingProvinceId() {
    return this.billingProvinceId;
  }

  public void setBillingProvinceId(Integer billingProvinceId) {
    this.billingProvinceId = billingProvinceId;
  }

  @Column(name = "shielding_province_ids", length = 256)
  public String getShieldingProvinceIds() {
    return this.shieldingProvinceIds;
  }

  public void setShieldingProvinceIds(String shieldingProvinceIds) {
    this.shieldingProvinceIds = shieldingProvinceIds;
  }

  @Column(name = "shielding_segments", length = 256)
  public String getShieldingSegments() {
    return this.shieldingSegments;
  }

  public void setShieldingSegments(String shieldingSegments) {
    this.shieldingSegments = shieldingSegments;
  }

  @Column(name = "sms_type")
  public Integer getSmsType() {
    return this.smsType;
  }

  public void setSmsType(Integer smsType) {
    this.smsType = smsType;
  }

  @Column(name = "sms_fetch_url", length = 256)
  public String getSmsFetchUrl() {
    return this.smsFetchUrl;
  }

  public void setSmsFetchUrl(String smsFetchUrl) {
    this.smsFetchUrl = smsFetchUrl;
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

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sdkPayPassage")
  public Set<SdkPayPassageParam> getSdkPayPassageParams() {
    return this.sdkPayPassageParams;
  }

  public void setSdkPayPassageParams(Set<SdkPayPassageParam> sdkPayPassageParams) {
    this.sdkPayPassageParams = sdkPayPassageParams;
  }

}