package com.cheyooh.service.sdk.idata.notify;

import com.cheyooh.service.framework.idata.Cmd;

public class CmdMmdoSinaNotify extends Cmd {

  /**
	 * 
	 */
  private static final long serialVersionUID = 7500467576566161943L;

  /**
   * 业务处理ID
   */
  private String transactionId;
  /**
   * 加密的手机号
   */
  private String mobile;
  /**
   * 预定资费
   */
  private String fee;
  /**
   * 透传的指令
   */
  private String command;
  /**
   * 订单状态 1为成功 -1为用户消费超过每日限额 -2为其他校验非法 其他
   */
  private String orderState;
  /**
   * 省份
   */
  private String province;
  /**
   * 计费长号
   */
  private String src;
  /**
   * 订单时间
   */
  private String orderTime;

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getFee() {
    return fee;
  }

  public void setFee(String fee) {
    this.fee = fee;
  }

  public String getCommand() {
    return command;
  }

  public void setCommand(String command) {
    this.command = command;
  }

  public String getOrderState() {
    return orderState;
  }

  public void setOrderState(String orderState) {
    this.orderState = orderState;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getSrc() {
    return src;
  }

  public void setSrc(String src) {
    this.src = src;
  }

  public String getOrderTime() {
    return orderTime;
  }

  public void setOrderTime(String orderTime) {
    this.orderTime = orderTime;
  }

  @Override
  public String toString() {
    return "CmdMmdoSinaNotify [transactionId=" + transactionId + ", mobile=" + mobile + ", fee=" + fee + ", command=" + command + ", orderState=" + orderState
        + ", province=" + province + ", src=" + src + ", orderTime=" + orderTime + "]";
  }

}
