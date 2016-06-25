/**
 * 
 */
package com.cheyooh.service.sdk.idata;

/**
 * @author Merlin
 *
 */
public class CmdPayVerifyCodeMmdo extends CmdGeneral {
  private static final long serialVersionUID = 7546383858668728846L;
  
  private String order_no;
  private String verifycode;
  
  /**
   * 
   */
  public CmdPayVerifyCodeMmdo() {
    // TODO Auto-generated constructor stub
  }

  public String getOrder_no() {
    return order_no;
  }

  public void setOrder_no(String order_no) {
    this.order_no = order_no;
  }

  public String getVerifycode() {
    return verifycode;
  }

  public void setVerifycode(String verifycode) {
    this.verifycode = verifycode;
  }
}
