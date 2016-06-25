package com.cheyooh.service.sdk.idata;

import com.cheyooh.service.framework.idata.ResultContentList;

public class ResultPayMmdoChargeTip extends ResultContentList {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3761213622118695614L;

	private String gamename;
		
	private String chargetip;
	
	private String chargesuceesstip;
	
	private String sendingtip;
	
	private String loadingtipmin;
	
	private String chargefailtip;
	
	private String currentifpay="0";
	
	private String nextifpay="0";
	
	public ResultPayMmdoChargeTip(){
		setTagName("tip");
	}

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

	public String getSendingtip() {
		return sendingtip;
	}

	public void setSendingtip(String sendingtip) {
		this.sendingtip = sendingtip;
	}

	public String getLoadingtipmin() {
		return loadingtipmin;
	}

	public void setLoadingtipmin(String loadingtipmin) {
		this.loadingtipmin = loadingtipmin;
	}

	public String getChargefailtip() {
		return chargefailtip;
	}

	public void setChargefailtip(String chargefailtip) {
		this.chargefailtip = chargefailtip;
	}

	public String getCurrentifpay() {
		return currentifpay;
	}

	public void setCurrentifpay(String currentifpay) {
		this.currentifpay = currentifpay;
	}

	public String getNextifpay() {
		return nextifpay;
	}

	public void setNextifpay(String nextifpay) {
		this.nextifpay = nextifpay;
	}

	
}
