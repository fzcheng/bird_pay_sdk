package jeecg.ext.sdk.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Transient;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SdkGameChannel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_game_channel", catalog = "game_sdk")
public class SdkGameChannel implements java.io.Serializable {
  private static final long serialVersionUID = 8405434278621170567L;
  private Integer id;
  private Integer gameId;
  private Integer channelId;
  private String channelCode;
  private Double payDeductPct;
  private Double payDividePct;
  private Double regDeductPct;
  private Double regDividePct;
  private Date updatedTime;
  private Date createdTime;
  
  private String payDeductPctStr;
  private String regDeductPctStr;

  // Constructors

  /** default constructor */
  public SdkGameChannel() {
  }

  /** full constructor */
  public SdkGameChannel(Integer gameId, Integer channelId, String channelCode, Double payDeductPct, Double payDividePct, Double regDeductPct,
      Double regDividePct, Date updatedTime, Date createdTime) {
    this.gameId = gameId;
    this.channelId = channelId;
    this.channelCode = channelCode;
    this.payDeductPct = payDeductPct;
    this.payDividePct = payDividePct;
    this.regDeductPct = regDeductPct;
    this.regDividePct = regDividePct;
    this.updatedTime = updatedTime;
    this.createdTime = createdTime;
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

  @Column(name = "game_id")
  public Integer getGameId() {
    return this.gameId;
  }

  public void setGameId(Integer gameId) {
    this.gameId = gameId;
  }

  @Column(name = "channel_id")
  public Integer getChannelId() {
    return this.channelId;
  }

  public void setChannelId(Integer channelId) {
    this.channelId = channelId;
  }

  @Column(name = "channel_code", length = 128)
  public String getChannelCode() {
    return this.channelCode;
  }

  public void setChannelCode(String channelCode) {
    this.channelCode = channelCode;
  }

  @Column(name = "pay_deduct_pct", precision = 10, scale = 4)
  public Double getPayDeductPct() {
    return this.payDeductPct;
  }

  public void setPayDeductPct(Double payDeductPct) {
    this.payDeductPct = payDeductPct;
  }

  @Column(name = "pay_divide_pct", precision = 10, scale = 4)
  public Double getPayDividePct() {
    return this.payDividePct;
  }

  public void setPayDividePct(Double payDividePct) {
    this.payDividePct = payDividePct;
  }

  @Column(name = "reg_deduct_pct", precision = 10, scale = 4)
  public Double getRegDeductPct() {
    return this.regDeductPct;
  }

  public void setRegDeductPct(Double regDeductPct) {
    this.regDeductPct = regDeductPct;
  }

  @Column(name = "reg_divide_pct", precision = 10, scale = 4)
  public Double getRegDividePct() {
    return this.regDividePct;
  }

  public void setRegDividePct(Double regDividePct) {
    this.regDividePct = regDividePct;
  }

  @Column(name = "updated_time", length = 19)
  public Date getUpdatedTime() {
    return this.updatedTime;
  }

  public void setUpdatedTime(Date updatedTime) {
    this.updatedTime = updatedTime;
  }

  @Column(name = "created_time", length = 19)
  public Date getCreatedTime() {
    return this.createdTime;
  }

  public void setCreatedTime(Date createdTime) {
    this.createdTime = createdTime;
  }

  @Transient
  public String getPayDeductPctStr() {
    return payDeductPctStr;
  }

  public void setPayDeductPctStr(String payDeductPctStr) {
    this.payDeductPctStr = payDeductPctStr;
  }

  @Transient
  public String getRegDeductPctStr() {
    return regDeductPctStr;
  }

  public void setRegDeductPctStr(String regDeductPctStr) {
    this.regDeductPctStr = regDeductPctStr;
  }

  @Override
  public String toString() {
    return "SdkGameChannel [id=" + id + ", gameId=" + gameId + ", channelId=" + channelId + ", channelCode=" + channelCode + ", payDeductPct=" + payDeductPct
        + ", payDividePct=" + payDividePct + ", regDeductPct=" + regDeductPct + ", regDividePct=" + regDividePct + ", updatedTime=" + updatedTime
        + ", createdTime=" + createdTime + "]";
  }
}