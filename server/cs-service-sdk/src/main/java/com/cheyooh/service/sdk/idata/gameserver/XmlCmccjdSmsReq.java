package com.cheyooh.service.sdk.idata.gameserver;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;

@XObject("request")
public class XmlCmccjdSmsReq {
	@XNode(value = "imsi")
	private String imsi;
	
	@XNode(value = "imei")
	private String imei;
	
	@XNode(value = "price")
	private String price;
	
	@XNode(value = "cpparam")
	private String cpparam;

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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCpparam() {
		return cpparam;
	}

	public void setCpparam(String cpparam) {
		this.cpparam = cpparam;
	}

	@Override
	public String toString() {
		return "XmlCmccjdSmsReq [imsi=" + imsi + ", imei=" + imei + ", price="
				+ price + ", cpparam=" + cpparam + "]";
	}
	
	
}
