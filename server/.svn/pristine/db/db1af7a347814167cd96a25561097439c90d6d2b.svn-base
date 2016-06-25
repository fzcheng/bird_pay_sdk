/**
 * 
 */
package com.cheyooh.service.sdk.idata.gameserver;

import java.io.ByteArrayInputStream;

import org.nuxeo.common.xmap.XMap;
import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;

/**
 * @author Merlin
 * 
 */
@XObject("sms")
public class WimipaySms {
  @XNode(value = "wipay/smsport")
  private String smsPort;
  @XNode(value = "wipay/smscontent")
  private String smsContent;

  public WimipaySms() {
  }

  public String getSmsPort() {
    return smsPort;
  }

  public void setSmsPort(String smsPort) {
    this.smsPort = smsPort;
  }

  public String getSmsContent() {
    return smsContent;
  }

  public void setSmsContent(String smsContent) {
    this.smsContent = smsContent;
  }

  @Override
  public String toString() {
    return "WimipaySms [smsPort=" + smsPort + ", smsContent=" + smsContent + "]";
  }

  public static void main(String[] args) {
    XMap xmap = new XMap();
    xmap.register(WimipaySms.class);
    String xml = "<sms><wipay><smsport>200</smsport><smscontent>error</smscontent></wipay></sms>";
    ByteArrayInputStream in;
    try {
      in = new ByteArrayInputStream(xml.getBytes("utf-8"));
      WimipaySms sms = (WimipaySms) xmap.load(in);
      System.out.println(sms);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }
}
