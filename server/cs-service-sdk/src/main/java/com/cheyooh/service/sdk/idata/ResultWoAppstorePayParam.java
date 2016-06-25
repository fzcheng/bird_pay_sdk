/**
 * 
 */
package com.cheyooh.service.sdk.idata;

import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.ResultContent;

/**
 * @author Merlin
 * 
 */
public class ResultWoAppstorePayParam extends ResultContent {
  private static final long serialVersionUID = -8761475257618226784L;
  private String cpid;
  private String cpcode;
  private String appid;
  private String company;
  private String phone;
  private String game;
  private String url;
  private boolean vac;
  private String vaccode;
  private boolean otherpay;
  private String customcode;
  private String props;
  private float money;
  private String vacmode;

  public ResultWoAppstorePayParam() {
    setTagName("woappstore");
  }

  public String getCpid() {
    return cpid;
  }

  public void setCpid(String cpid) {
    this.cpid = cpid;
  }

  public String getCpcode() {
    return cpcode;
  }

  public void setCpcode(String cpcode) {
    this.cpcode = cpcode;
  }

  public String getAppid() {
    return appid;
  }

  public void setAppid(String appid) {
    this.appid = appid;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getGame() {
    return game;
  }

  public void setGame(String game) {
    this.game = game;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public boolean isVac() {
    return vac;
  }

  public void setVac(boolean vac) {
    this.vac = vac;
  }

  public String getVaccode() {
    return vaccode;
  }

  public void setVaccode(String vaccode) {
    this.vaccode = vaccode;
  }

  public boolean isOtherpay() {
    return otherpay;
  }

  public void setOtherpay(boolean otherpay) {
    this.otherpay = otherpay;
  }

  public String getCustomcode() {
    return customcode;
  }

  public void setCustomcode(String customcode) {
    this.customcode = customcode;
  }

  public String getProps() {
    return props;
  }

  public void setProps(String props) {
    this.props = props;
  }

  public float getMoney() {
    return money;
  }

  public void setMoney(float money) {
    this.money = money;
  }

  public String getVacmode() {
    return vacmode;
  }

  public void setVacmode(String vacmode) {
    this.vacmode = vacmode;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }
  public static void main(String[] args) {
    ResultWoAppstorePayParam p = new ResultWoAppstorePayParam();
    Result r = new Result(p);
    System.out.println(r.getXml("payinfo"));
  }
}
