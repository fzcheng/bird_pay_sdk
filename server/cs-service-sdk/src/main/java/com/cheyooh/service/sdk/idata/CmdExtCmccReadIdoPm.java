/**
 * 
 */
package com.cheyooh.service.sdk.idata;

import com.cheyooh.service.framework.idata.Cmd;

/**
 * @author Merlin
 * 
 */
public class CmdExtCmccReadIdoPm extends Cmd {
  private static final long serialVersionUID = 2887761261969014949L;
  private String game_id;
  private String read_ido_code;
  private String imsi;
  private String imei;
  private String out_trade_no;
  private String ext_data;

  public String getGame_id() {
    return game_id;
  }

  public void setGame_id(String game_id) {
    this.game_id = game_id;
  }

  public String getRead_ido_code() {
    return read_ido_code;
  }

  public void setRead_ido_code(String read_ido_code) {
    this.read_ido_code = read_ido_code;
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

  public String getExt_data() {
    return ext_data;
  }

  public void setExt_data(String ext_data) {
    this.ext_data = ext_data;
  }

  @Override
  public String toString() {
    return "CmdExtCmccReadIdoPm [game_id=" + game_id + ", read_ido_code=" + read_ido_code + ", imsi=" + imsi + ", imei=" + imei + ", out_trade_no="
        + out_trade_no + ", ext_data=" + ext_data + "]";
  }
}
