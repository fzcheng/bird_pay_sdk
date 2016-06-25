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
@XObject("checkOrderIdReq")
public class XmlWoappstoreVerifyOrderReq {
  @XNode(value = "orderid")
  private String orderid;
  @XNode(value = "signMsg")
  private String signMsg;

  public XmlWoappstoreVerifyOrderReq() {

  }

  public String getOrderid() {
    return orderid;
  }

  public void setOrderid(String orderid) {
    this.orderid = orderid;
  }

  public String getSignMsg() {
    return signMsg;
  }

  public void setSignMsg(String signMsg) {
    this.signMsg = signMsg;
  }

}
