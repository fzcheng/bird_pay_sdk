/**
 * 
 */
package com.cheyooh.service.sdk.idata.notify;

import com.cheyooh.service.framework.idata.Cmd;

/**
 * @author Merlin
 * 
 */
public class CmdMobileGameBasePmNotify extends Cmd {
  private static final long serialVersionUID = -8213428818299094319L;
  /*
     参数名  重要性 类型  参数含义  备注
     orderid 必须  String  订单id  
     partner 必须  Int 合作方标识 
     subcid  可选  String  子渠道id 
     number  必须  Long  计费手机号 
     consume 必须  String  道具  
     price 必须  Int 计费价格  
     gateway 可选  String  用户访问ip  
     province  可选  String  省份，utf-8编码  
     city  可选  Sting 城市，utf-8编码  
     savetime  必须  Long  记录保存时间,毫秒数  
     exdata  可选  String  扩展数据  用于合作方透传数据，不能包含&
     Key MD5摘要 String  Md5(orderid=%s&partner=%s&number=%s&consume=%s&price=%s&savetime=%s&exdata=%s&key=%s) 使用双方约定的key对数据做校验
   */
  private String orderId;
  private String partner;
  private String subId;
  private String number;
  private String consume;
  private int price;
  private String gateway;
  private String province;
  private String city;
  private long savetime;
  private String exdata;
  private String key;

  /**
   * 
   */
  public CmdMobileGameBasePmNotify() {
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getPartner() {
    return partner;
  }

  public void setPartner(String partner) {
    this.partner = partner;
  }

  public String getSubId() {
    return subId;
  }

  public void setSubId(String subId) {
    this.subId = subId;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getConsume() {
    return consume;
  }

  public void setConsume(String consume) {
    this.consume = consume;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public String getGateway() {
    return gateway;
  }

  public void setGateway(String gateway) {
    this.gateway = gateway;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public long getSavetime() {
    return savetime;
  }

  public void setSavetime(long savetime) {
    this.savetime = savetime;
  }

  public String getExdata() {
    return exdata;
  }

  public void setExdata(String exdata) {
    this.exdata = exdata;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

}
