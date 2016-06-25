/**
 * 
 */
package com.cheyooh.service.sdk.idata.gameserver;


/**
 * @author Merlin
 * 
 */
public class WoplusSmsReq {
  private String outTradeNo;
  private String timeStamp;
  private String subject;
  private String description;
  private float price;
  private int quantity;
  private float totalFee;
  private String developerId;
  private String developerName;
  private String callbackUrl;
  private String callbackData;
  private String appKey;
  private String appName;
  private String iapId;
  private String imsi;
  private String imei;
  private String signType;
  private String signature;

  public WoplusSmsReq() {
  }
  
  public String getOutTradeNo() {
    return outTradeNo;
  }

  public void setOutTradeNo(String outTradeNo) {
    this.outTradeNo = outTradeNo;
  }

  public String getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(String timeStamp) {
    this.timeStamp = timeStamp;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public float getTotalFee() {
    return totalFee;
  }

  public void setTotalFee(float totalFee) {
    this.totalFee = totalFee;
  }

  public String getDeveloperId() {
    return developerId;
  }

  public void setDeveloperId(String developerId) {
    this.developerId = developerId;
  }

  public String getDeveloperName() {
    return developerName;
  }

  public void setDeveloperName(String developerName) {
    this.developerName = developerName;
  }

  public String getCallbackUrl() {
    return callbackUrl;
  }

  public void setCallbackUrl(String callbackUrl) {
    this.callbackUrl = callbackUrl;
  }

  public String getCallbackData() {
    return callbackData;
  }

  public void setCallbackData(String callbackData) {
    this.callbackData = callbackData;
  }

  public String getAppKey() {
    return appKey;
  }

  public void setAppKey(String appKey) {
    this.appKey = appKey;
  }

  public String getAppName() {
    return appName;
  }

  public void setAppName(String appName) {
    this.appName = appName;
  }

  public String getIapId() {
    return iapId;
  }

  public void setIapId(String iapId) {
    this.iapId = iapId;
  }

  public String getImsi() {
    return imsi;
  }

  public void setImsi(String imsi) {
    this.imsi = imsi;
  }

  public String getImei() {
    return imei;
  }

  public void setImei(String imei) {
    this.imei = imei;
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
