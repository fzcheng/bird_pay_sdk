/**
 * 
 */
package com.cheyooh.service.sdk.idata.gameserver;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;

/**
 * @author Merlin
 * 
 */
@XObject("SyncAppOrderReq")
public class XmlWimipayMmNotify {
  @XNode(value="OrderID")
  private String orderId;
  @XNode(value="Price")
  private float price;
  @XNode(value="ExData")
  private String exData;
  @XNode(value="ActionTime")
  private String actionTime;
  @XNode(value="TradeID")
  private String tradeId;
  @XNode(value="FeeMSISDN")
  private String feeMsisdn;
  @XNode(value="PayCode")
  private String payCode;

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public String getExData() {
    return exData;
  }

  public void setExData(String exData) {
    this.exData = exData;
  }

  public String getActionTime() {
    return actionTime;
  }

  public void setActionTime(String actionTime) {
    this.actionTime = actionTime;
  }

  public String getTradeId() {
    return tradeId;
  }

  public void setTradeId(String tradeId) {
    this.tradeId = tradeId;
  }

  public String getFeeMsisdn() {
    return feeMsisdn;
  }

  public void setFeeMsisdn(String feeMsisdn) {
    this.feeMsisdn = feeMsisdn;
  }

  public String getPayCode() {
    return payCode;
  }

  public void setPayCode(String payCode) {
    this.payCode = payCode;
  }
  
}
