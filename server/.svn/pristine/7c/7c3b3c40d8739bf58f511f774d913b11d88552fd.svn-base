package jeecg.ext.sdk.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SdkChannelPartner entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_channel_partner", catalog = "game_sdk")
public class SdkChannelPartner implements java.io.Serializable {
  private static final long serialVersionUID = -8677645284900135402L;
  private Integer id;
  private String partnerName;
  private String loginName;
  private String loginPasswd;
  private Date updateTime;
  private Date createTime;

  // Constructors

  /** default constructor */
  public SdkChannelPartner() {
  }

  /** minimal constructor */
  public SdkChannelPartner(String partnerName, Date createTime) {
    this.partnerName = partnerName;
    this.createTime = createTime;
  }

  /** full constructor */
  public SdkChannelPartner(String partnerName, String loginName, String loginPasswd, Date updateTime, Date createTime) {
    this.partnerName = partnerName;
    this.loginName = loginName;
    this.loginPasswd = loginPasswd;
    this.updateTime = updateTime;
    this.createTime = createTime;
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

  @Column(name = "partner_name", nullable = false)
  public String getPartnerName() {
    return this.partnerName;
  }

  public void setPartnerName(String partnerName) {
    this.partnerName = partnerName;
  }

  @Column(name = "login_name", length = 64)
  public String getLoginName() {
    return this.loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  @Column(name = "login_passwd", length = 64)
  public String getLoginPasswd() {
    return this.loginPasswd;
  }

  public void setLoginPasswd(String loginPasswd) {
    this.loginPasswd = loginPasswd;
  }

  @Column(name = "update_time", length = 19)
  public Date getUpdateTime() {
    return this.updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  @Column(name = "create_time", nullable = false, length = 19)
  public Date getCreateTime() {
    return this.createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  @Override
  public String toString() {
    return "SdkChannelPartner [id=" + id + ", partnerName=" + partnerName + ", loginName=" + loginName + ", loginPasswd=" + loginPasswd + ", updateTime="
        + updateTime + ", createTime=" + createTime + "]";
  }
}