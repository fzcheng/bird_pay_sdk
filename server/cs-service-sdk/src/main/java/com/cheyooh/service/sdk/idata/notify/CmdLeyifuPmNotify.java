/**
 * 
 */
package com.cheyooh.service.sdk.idata.notify;

import com.cheyooh.service.framework.idata.Cmd;

/**
 * @author Merlin
 *
 */
public class CmdLeyifuPmNotify extends Cmd {
  private static final long serialVersionUID = -8360737755556757916L;
  private String linkid;
  private String mobile;
  private String content;
  private String longcode;
  private int feecode;
  private String flag;
  private String province;

  /**
   * 
   */
  public CmdLeyifuPmNotify() {
    // TODO Auto-generated constructor stub
  }

  public String getLinkid() {
    return linkid;
  }

  public void setLinkid(String linkid) {
    this.linkid = linkid;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getLongcode() {
    return longcode;
  }

  public void setLongcode(String longcode) {
    this.longcode = longcode;
  }

  public int getFeecode() {
    return feecode;
  }

  public void setFeecode(int feecode) {
    this.feecode = feecode;
  }

  public String getFlag() {
    return flag;
  }

  public void setFlag(String flag) {
    this.flag = flag;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  @Override
  public String toString() {
    return "CmdLeyifuPmNotify [linkid=" + linkid + ", mobile=" + mobile + ", content=" + content + ", longcode=" + longcode + ", feecode=" + feecode
        + ", flag=" + flag + ", province=" + province + "]";
  }
}
