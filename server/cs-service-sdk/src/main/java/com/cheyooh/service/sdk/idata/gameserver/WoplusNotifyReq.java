/**
 * 
 */
package com.cheyooh.service.sdk.idata.gameserver;

/**
 * @author Merlin
 *
 */
public class WoplusNotifyReq {
  private String outTradeNo;
  private String transactionId;
  private String callbackData;
  private Float totalFee;
  private String userNumberPrev;
  private String status;
  private String chargeResultCode;
  private String paymentTime;
  private String signType;
  private String signature;

  public WoplusNotifyReq() {
    // TODO Auto-generated constructor stub
  }

  public String getOutTradeNo() {
    return outTradeNo;
  }

  public void setOutTradeNo(String outTradeNo) {
    this.outTradeNo = outTradeNo;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public String getCallbackData() {
    return callbackData;
  }

  public void setCallbackData(String callbackData) {
    this.callbackData = callbackData;
  }

  public Float getTotalFee() {
    return totalFee;
  }

  public void setTotalFee(Float totalFee) {
    this.totalFee = totalFee;
  }

  public String getUserNumberPrev() {
    return userNumberPrev;
  }

  public void setUserNumberPrev(String userNumberPrev) {
    this.userNumberPrev = userNumberPrev;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getChargeResultCode() {
    return chargeResultCode;
  }

  public void setChargeResultCode(String chargeResultCode) {
    this.chargeResultCode = chargeResultCode;
  }

  public String getPaymentTime() {
    return paymentTime;
  }

  public void setPaymentTime(String paymentTime) {
    this.paymentTime = paymentTime;
  }

  public String getSignType() {
    return signType;
  }

  public void setSignType(String signType) {
    this.signType = signType;
  }

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }

}
