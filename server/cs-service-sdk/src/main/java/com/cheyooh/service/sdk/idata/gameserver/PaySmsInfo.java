/**
 * 
 */
package com.cheyooh.service.sdk.idata.gameserver;

/**
 * @author Merlin
 * 
 */
public class PaySmsInfo {
  private boolean isRight;
  private String upPort;
  private String upContent;

  public PaySmsInfo() {
  }

  public boolean isRight() {
    return isRight;
  }

  public void setRight(boolean isRight) {
    this.isRight = isRight;
  }

  public String getUpPort() {
    return upPort;
  }

  public void setUpPort(String upPort) {
    this.upPort = upPort;
  }

  public String getUpContent() {
    return upContent;
  }

  public void setUpContent(String upContent) {
    this.upContent = upContent;
  }

  @Override
  public String toString() {
    return "PaySmsInfo [isRight=" + isRight + ", upPort=" + upPort + ", upContent=" + upContent + "]";
  }

}
