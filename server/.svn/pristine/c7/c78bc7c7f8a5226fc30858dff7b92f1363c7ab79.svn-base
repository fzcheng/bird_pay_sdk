package jeecg.ext.sdk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * SdkOperatorPayLimit entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sdk_operator_pay_limit", catalog = "game_sdk", uniqueConstraints = @UniqueConstraint(columnNames = "operator_type"))
public class SdkOperatorPayLimit implements java.io.Serializable {
  private static final long serialVersionUID = 3975138051865462298L;
  private Integer id;
  private Integer operatorType;
  private Double dayLimit;
  private Double monthLimit;

  // Constructors

  /** default constructor */
  public SdkOperatorPayLimit() {
  }

  /** minimal constructor */
  public SdkOperatorPayLimit(Integer operatorType) {
    this.operatorType = operatorType;
  }

  /** full constructor */
  public SdkOperatorPayLimit(Integer operatorType, Double dayLimit, Double monthLimit) {
    this.operatorType = operatorType;
    this.dayLimit = dayLimit;
    this.monthLimit = monthLimit;
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

  @Column(name = "operator_type", unique = true, nullable = false)
  public Integer getOperatorType() {
    return this.operatorType;
  }

  public void setOperatorType(Integer operatorType) {
    this.operatorType = operatorType;
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

}