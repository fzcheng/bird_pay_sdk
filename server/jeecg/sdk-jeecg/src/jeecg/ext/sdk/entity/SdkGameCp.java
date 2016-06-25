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

/**
 * SdkGameCp entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_game_cp", catalog = "game_sdk")
public class SdkGameCp implements java.io.Serializable {
  private static final long serialVersionUID = -8685671903553770440L;

  private Integer cpId;
  private String name;
  private String apiKey;
  private String loginName;
  private String pwd;
  private String email;
  private Date createTime;
  private String memo;
  private Set<SdkOrder> sdkOrders = new HashSet<SdkOrder>(0);
  private Set<SdkGame> sdkGames = new HashSet<SdkGame>(0);

  // Constructors

  /** default constructor */
  public SdkGameCp() {
  }

  /** minimal constructor */
  public SdkGameCp(String name, String apiKey, Date createTime) {
    this.name = name;
    this.apiKey = apiKey;
    this.createTime = createTime;
  }

  /** full constructor */
  public SdkGameCp(String name, String apiKey, String loginName, String pwd, String email, Date createTime, String memo, 
      Set<SdkOrder> sdkOrders, Set<SdkGame> sdkGames) {
    this.name = name;
    this.apiKey = apiKey;
    this.loginName = loginName;
    this.pwd = pwd;
    this.email = email;
    this.createTime = createTime;
    this.memo = memo;
    this.sdkOrders = sdkOrders;
    this.sdkGames = sdkGames;
  }

  // Property accessors
  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "cp_id", unique = true, nullable = false)
  public Integer getCpId() {
    return this.cpId;
  }

  public void setCpId(Integer cpId) {
    this.cpId = cpId;
  }

  @Column(name = "name", nullable = false, length = 128)
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(name = "api_key", nullable = false, length = 64)
  public String getApiKey() {
    return this.apiKey;
  }

  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }

  @Column(name = "login_name", length = 64)
  public String getLoginName() {
    return this.loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  @Column(name = "pwd", length = 64)
  public String getPwd() {
    return this.pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }

  @Column(name = "email", length = 64)
  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Column(name = "create_time", nullable = false, length = 19)
  public Date getCreateTime() {
    return this.createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  @Column(name = "memo", length = 1024)
  public String getMemo() {
    return this.memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sdkGameCp")
  public Set<SdkOrder> getSdkOrders() {
    return this.sdkOrders;
  }

  public void setSdkOrders(Set<SdkOrder> sdkOrders) {
    this.sdkOrders = sdkOrders;
  }

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sdkGameCp")
  public Set<SdkGame> getSdkGames() {
    return this.sdkGames;
  }

  public void setSdkGames(Set<SdkGame> sdkGames) {
    this.sdkGames = sdkGames;
  }

}