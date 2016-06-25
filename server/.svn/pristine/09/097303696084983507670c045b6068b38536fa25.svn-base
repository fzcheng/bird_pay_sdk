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
@XObject("RequestBody")
public class XmlLoveCartoonNotifyReq {
  /**
   * 签名，用于鉴别发送者。取值=3DES(hehavior + trade_status + tradeno + buyer_id + extension) ，
   * 3DES 采用营销支撑平台颁发给调用方系统的密钥进行加密
   */
  @XNode(value = "Sign")
  private String sign;
  /**
   * 行为 (订购 order 退订 unsubscribe)
   */
  @XNode(value = "Behavior")
  private String behavior;
  /**
   * 交易状态。(0 成功)
   */
  @XNode(value = "Trade_status")
  private String tradeStatus;
  /**
   * 交易流水号
   */
  @XNode(value = "Trade_no")
  private String tradeNo;
  /**
   * 用户号码
   */
  @XNode(value = "Buyer_id")
  private String buyerId;
  /**
   * 产品ID
   */
  @XNode(value = "Product_id")
  private String productId;
  /**
   * 产品名称
   */
  @XNode(value = "Product_name")
  private String productName;
  /**
   * 产品价格
   */
  @XNode(value = "Price")
  private String price;
  /**
   * 产品价格
   */
  @XNode(value = "App_id")
  private String appId;
  /**
   * 拓展字段,上行的短信内容
   */
  @XNode(value = "Extension")
  private String extension;

  public XmlLoveCartoonNotifyReq() {
  }

  public String getSign() {
    return sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }

  public String getBehavior() {
    return behavior;
  }

  public void setBehavior(String behavior) {
    this.behavior = behavior;
  }

  public String getTradeStatus() {
    return tradeStatus;
  }

  public void setTradeStatus(String tradeStatus) {
    this.tradeStatus = tradeStatus;
  }

  public String getTradeNo() {
    return tradeNo;
  }

  public void setTradeNo(String tradeNo) {
    this.tradeNo = tradeNo;
  }

  public String getBuyerId() {
    return buyerId;
  }

  public void setBuyerId(String buyerId) {
    this.buyerId = buyerId;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public String getExtension() {
    return extension;
  }

  public void setExtension(String extension) {
    this.extension = extension;
  }

}
