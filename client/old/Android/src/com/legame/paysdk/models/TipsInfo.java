package com.legame.paysdk.models;

import java.io.Serializable;

/** 
 * 类说明：   
 * @author  xiaodm
 * @date    2014年12月29日
 * @version 1.0
 */
public class TipsInfo implements Serializable {
	private static final long serialVersionUID = -2494513253437854694L;
	public static final String NO_SHOW = "0";
	public static final String SHOW = "1";
	
	private String chargetip;//是否弹出二次确认框，0：不弹，1：弹
	private String chargesuceesstip;//控制成功支付框是否弹出
	private String gamename;
	private String loadingtip;//支付loading时间限制
	private String chargefailtip;//支付结果确认框，0：不谈，1：弹
	
	public String getGamename() {
		return gamename;
	}
	public void setGamename(String gamename) {
		this.gamename = gamename;
	}
	public String getChargetip() {
		return chargetip;
	}
	public void setChargetip(String chargetip) {
		this.chargetip = chargetip;
	}
	public String getChargesuceesstip() {
		return chargesuceesstip;
	}
	public void setChargesuceesstip(String chargesuceesstip) {
		this.chargesuceesstip = chargesuceesstip;
	}
	public String getLoadingtip() {
		return loadingtip;
	}
	public void setLoadingtip(String loadingtip) {
		this.loadingtip = loadingtip;
	}
	public String getChargefailtip() {
		return chargefailtip;
	}
	public void setChargefailtip(String chargefailtip) {
		this.chargefailtip = chargefailtip;
	}
}
