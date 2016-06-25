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
@XObject("callbackReq")
public class XmlWoappstoreCallbackReq {
  @XNode(value = "orderid")
  private String orderid;
  @XNode(value = "ordertime")
  private String ordertime;
  @XNode(value = "cpid")
  private String cpid;
  @XNode(value = "appid")
  private String appid;
  @XNode(value = "fid")
  private String fid;
  @XNode(value = "consumeCode")
  private String consumeCode;
  @XNode(value = "payfee")
  private String payfee;
  @XNode(value = "payType")
  private String payType;
  @XNode(value = "hRet")
  private String hRet;
  @XNode(value = "status")
  private String status;
  @XNode(value = "signMsg")
  private String signMsg;

  public XmlWoappstoreCallbackReq() {
    // TODO Auto-generated constructor stub
  }

  public String getOrderid() {
    return orderid;
  }

  public void setOrderid(String orderid) {
    this.orderid = orderid;
  }

  public String getOrdertime() {
    return ordertime;
  }

  public void setOrdertime(String ordertime) {
    this.ordertime = ordertime;
  }

  public String getCpid() {
    return cpid;
  }

  public void setCpid(String cpid) {
    this.cpid = cpid;
  }

  public String getAppid() {
    return appid;
  }

  public void setAppid(String appid) {
    this.appid = appid;
  }

  public String getFid() {
    return fid;
  }

  public void setFid(String fid) {
    this.fid = fid;
  }

  public String getConsumeCode() {
    return consumeCode;
  }

  public void setConsumeCode(String consumeCode) {
    this.consumeCode = consumeCode;
  }

  public String getPayfee() {
    return payfee;
  }

  public void setPayfee(String payfee) {
    this.payfee = payfee;
  }

  public String getPayType() {
    return payType;
  }

  public void setPayType(String payType) {
    this.payType = payType;
  }

  public String gethRet() {
    return hRet;
  }

  public void sethRet(String hRet) {
    this.hRet = hRet;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getSignMsg() {
    return signMsg;
  }

  public void setSignMsg(String signMsg) {
    this.signMsg = signMsg;
  }

  public String generateSignString() {
    return "orderid=" + orderid + "&ordertime=" + ordertime + "&cpid=" + cpid + "&appid=" + appid + "&fid=" + fid + "&consumeCode=" + consumeCode + "&payfee="
        + payfee + "&payType=" + payType + "&hRet=" + hRet + "&status=" + status;
  }
}
