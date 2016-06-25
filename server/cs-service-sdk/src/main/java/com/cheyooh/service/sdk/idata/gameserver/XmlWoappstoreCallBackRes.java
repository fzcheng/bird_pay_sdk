/**
 * 
 */
package com.cheyooh.service.sdk.idata.gameserver;


/**
 * @author Merlin
 * 
 */
public class XmlWoappstoreCallBackRes {
  private int callbackRsp;

  public XmlWoappstoreCallBackRes() {
    // TODO Auto-generated constructor stub
  }

  public int getCallbackRsp() {
    return callbackRsp;
  }

  public void setCallbackRsp(int callbackRsp) {
    this.callbackRsp = callbackRsp;
  }

  public String toXml() {
    return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><callbackRsp>" + callbackRsp + "</callbackRsp>";
  }
}
