/**
 * 
 */
package com.cheyooh.service.sdk.idata.gameserver;

import org.nuxeo.common.xmap.XMap;
import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;

/**
 * @author Merlin
 *
 */
@XObject("paymessages")
public class XmlWoappstoreVerifyOrderRes {
  @XNode(value = "checkOrderIdRsp")
  private int checkOrderIdRsp;
//  @XNode(value = "appname")
//  private String appname;
//  @XNode(value = "feename")
//  private String feename;
//  @XNode(value = "payfee")
//  private int payfee;
//  @XNode(value = "appdeveloper")
//  private String appdeveloper;
  @XNode(value = "gameaccount")
  private String gameaccount;
  @XNode(value = "macaddress")
  private String macaddress;
//  @XNode(value = "appid")
//  private String appid;
  @XNode(value = "ipaddress")
  private String ipaddress;
  @XNode(value = "serviceid")
  private String serviceid;
  @XNode(value = "channelid")
  private String channelid;
  @XNode(value = "cpid")
  private String cpid;
  @XNode(value = "ordertime")
  private String ordertime;
  @XNode(value = "imei")
  private String imei;
  @XNode(value = "appversion")
  private String appversion;
  
  public XmlWoappstoreVerifyOrderRes() {
    // TODO Auto-generated constructor stub
  }

  public int getCheckOrderIdRsp() {
    return checkOrderIdRsp;
  }

  public void setCheckOrderIdRsp(int checkOrderIdRsp) {
    this.checkOrderIdRsp = checkOrderIdRsp;
  }

//  public String getAppname() {
//    return appname;
//  }
//
//  public void setAppname(String appname) {
//    this.appname = appname;
//  }
//
//  public String getFeename() {
//    return feename;
//  }
//
//  public void setFeename(String feename) {
//    this.feename = feename;
//  }
//
//  public int getPayfee() {
//    return payfee;
//  }
//
//  public void setPayfee(int payfee) {
//    this.payfee = payfee;
//  }
//
//  public String getAppdeveloper() {
//    return appdeveloper;
//  }
//
//  public void setAppdeveloper(String appdeveloper) {
//    this.appdeveloper = appdeveloper;
//  }

  public String getGameaccount() {
    return gameaccount;
  }

  public void setGameaccount(String gameaccount) {
    this.gameaccount = gameaccount;
  }

  public String getMacaddress() {
    return macaddress;
  }

  public void setMacaddress(String macaddress) {
    this.macaddress = macaddress;
  }

//  public String getAppid() {
//    return appid;
//  }
//
//  public void setAppid(String appid) {
//    this.appid = appid;
//  }

  public String getIpaddress() {
    return ipaddress;
  }

  public void setIpaddress(String ipaddress) {
    this.ipaddress = ipaddress;
  }

  public String getServiceid() {
    return serviceid;
  }

  public void setServiceid(String serviceid) {
    this.serviceid = serviceid;
  }

  public String getChannelid() {
    return channelid;
  }

  public void setChannelid(String channelid) {
    this.channelid = channelid;
  }

  public String getCpid() {
    return cpid;
  }

  public void setCpid(String cpid) {
    this.cpid = cpid;
  }

  public String getOrdertime() {
    return ordertime;
  }

  public void setOrdertime(String ordertime) {
    this.ordertime = ordertime;
  }

  public String getImei() {
    return imei;
  }

  public void setImei(String imei) {
    this.imei = imei;
  }

  public String getAppversion() {
    return appversion;
  }

  public void setAppversion(String appversion) {
    this.appversion = appversion;
  }

  public String toXml() throws Exception {
    XMap xmap = new XMap();
    xmap.register(this.getClass());
    String str = xmap.asXmlString(this, "UTF-8", null);
    return str;
  }
  
}
