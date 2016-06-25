package com.cheyooh.service.sdk.idata;

import com.cheyooh.service.framework.idata.ResultContent;
import com.cheyooh.service.framework.utils.annotation.NullNone;

public class ResultPayinfo extends ResultContent {

  private static final long serialVersionUID = 8934579767900699500L;

  private String order_no;

  private String sms_type = "";

  private int sms_pay_type;

  @NullNone
  private String pay_info;

  public ResultPayinfo(String order_no) {
    this(order_no, null);
  }

  public ResultPayinfo(String order_no, String pay_info) {
    super("order");

    this.order_no = order_no;
    this.pay_info = pay_info;
  }

  public String getOrder_no() {
    return order_no;
  }

  public void setOrder_no(String order_no) {
    this.order_no = order_no;
  }

  public String getPay_info() {
    return pay_info;
  }

  public void setPay_info(String pay_info) {
    this.pay_info = pay_info;
  }

  public String getSms_type() {
    return sms_type;
  }

  public void setSms_type(String sms_type) {
    this.sms_type = sms_type;
  }

  public int getSms_pay_type() {
    return sms_pay_type;
  }

  public void setSms_pay_type(int sms_pay_type) {
    this.sms_pay_type = sms_pay_type;
  }

}
