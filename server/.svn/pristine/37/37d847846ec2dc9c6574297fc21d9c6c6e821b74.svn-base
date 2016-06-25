package jeecg.ext.sdk.entity;

import java.util.Date;
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
 * SdkPayPassageParam entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_pay_passage_param", catalog = "game_sdk")
public class SdkPayPassageParam implements java.io.Serializable {
  private static final long serialVersionUID = 3527819287522502892L;
  private Integer id;
  private SdkPayPassage sdkPayPassage;
  private String name;
  private String param;
  private String val;
  private Date createdTime;
  private Date updatedTime;

  // Constructors

  /** default constructor */
  public SdkPayPassageParam() {
  }

  /** minimal constructor */
  public SdkPayPassageParam(SdkPayPassage sdkPayPassage) {
    this.sdkPayPassage = sdkPayPassage;
  }
  
  public SdkPayPassageParam(Integer id, String name, String param, String val) {
    this.id = id;
    this.name = name;
    this.param = param;
    this.val = val;
  }

  /** full constructor */
  public SdkPayPassageParam(SdkPayPassage sdkPayPassage, String name, String param, String val, Date createdTime, Date updatedTime) {
    this.sdkPayPassage = sdkPayPassage;
    this.name = name;
    this.param = param;
    this.val = val;
    this.createdTime = createdTime;
    this.updatedTime = updatedTime;
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
  @JoinColumn(name = "sdk_pay_passage_id", nullable = false)
  public SdkPayPassage getSdkPayPassage() {
    return this.sdkPayPassage;
  }

  public void setSdkPayPassage(SdkPayPassage sdkPayPassage) {
    this.sdkPayPassage = sdkPayPassage;
  }

  @Column(name = "name", length = 64)
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(name = "param", length = 64)
  public String getParam() {
    return this.param;
  }

  public void setParam(String param) {
    this.param = param;
  }

  @Column(name = "val", length = 256)
  public String getVal() {
    return this.val;
  }

  public void setVal(String val) {
    this.val = val;
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

}