/**
 * 
 */
package com.cheyooh.service.sdk.idata.notify;

import com.cheyooh.service.framework.idata.Cmd;

/**
 * @author Merlin
 * 
 */
public class CmdSinaMonthlyPmNotify extends Cmd {
  private static final long serialVersionUID = -9212612829033784928L;
  private String businesslinkid;
  private String mobile;
  private String longphone;
  private String state;
  private String linkid;

  /**
   * 
   */
  public CmdSinaMonthlyPmNotify() {
  }

  public String getBusinesslinkid() {
    return businesslinkid;
  }

  public void setBusinesslinkid(String businesslinkid) {
    this.businesslinkid = businesslinkid;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getLongphone() {
    return longphone;
  }

  public void setLongphone(String longphone) {
    this.longphone = longphone;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getLinkid() {
    return linkid;
  }

  public void setLinkid(String linkid) {
    this.linkid = linkid;
  }

  @Override
  public String toString() {
    return "CmdSinaMonthlyPmNotify [businesslinkid=" + businesslinkid + ", mobile=" + mobile + ", longphone=" + longphone + ", state=" + state + ", linkid="
        + linkid + "]";
  }

}
