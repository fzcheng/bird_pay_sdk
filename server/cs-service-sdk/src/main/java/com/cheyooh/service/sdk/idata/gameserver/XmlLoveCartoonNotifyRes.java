/**
 * 
 */
package com.cheyooh.service.sdk.idata.gameserver;


/**
 * @author Merlin
 * 
 */
public class XmlLoveCartoonNotifyRes {
  private static final String NOTIFY_RES = "<ResponseBody><Status>%d</Status><Trade_no>%s</Trade_no></ResponseBody>";
  private int status = 1;
  private String tradeNo = "";

  public XmlLoveCartoonNotifyRes() {
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getTradeNo() {
    return tradeNo;
  }

  public void setTradeNo(String tradeNo) {
    this.tradeNo = tradeNo;
  }

  public String toXml() {
    return String.format(NOTIFY_RES, status, tradeNo);
  }
}
