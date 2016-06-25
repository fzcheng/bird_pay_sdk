/**
 * 
 */
package com.cheyooh.service.sdk.idata;

import com.cheyooh.service.framework.idata.Cmd;

/**
 * @author Merlin
 * 
 */
public class CmdUnicomwopm extends Cmd {
  private static final long serialVersionUID = 2285417038582706501L;
  private String game_id;
  private String money;
  private String imsi;
  private String imei;
  private String out_trade_no;
  private String cp_ext;

  /**
   * 
   */
  public CmdUnicomwopm() {
    // TODO Auto-generated constructor stub
  }

  public String getGame_id() {
    return game_id;
  }

  public void setGame_id(String game_id) {
    this.game_id = game_id;
  }

  public String getMoney() {
    return money;
  }

  public void setMoney(String money) {
    this.money = money;
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

  public String getOut_trade_no() {
    return out_trade_no;
  }

  public void setOut_trade_no(String out_trade_no) {
    this.out_trade_no = out_trade_no;
  }

  public String getCp_ext() {
    return cp_ext;
  }

  public void setCp_ext(String cp_ext) {
    this.cp_ext = cp_ext;
  }

  @Override
  public String toString() {
    return "CmdUnicomwopm [game_id=" + game_id + ", money=" + money + ", imsi=" + imsi + ", imei=" + imei + ", out_trade_no=" + out_trade_no + ", cp_ext="
        + cp_ext + "]";
  }

}
