package com.cheyooh.service.sdk.idata.gameserver;

import org.nuxeo.common.xmap.annotation.XNode;

public class XmlCmccGamebaseNotifyResp {
	private static final String XML_RESP = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><hRet>%s</hRet><message>%s</message>";
	@XNode(value = "hRet")
	private String hRet = "";
	
	@XNode(value = "message")
	private String message = "";

	public String gethRet() {
		return hRet;
	}

	public void sethRet(String hRet) {
		this.hRet = hRet;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String toXml() {
	    return String.format(XML_RESP, hRet,message);
	  }
	
	@Override
	public String toString() {
		return "XmlCmccGamebaseNotifyResp [hRet=" + hRet + ", message="
				+ message + "]";
	}
	
	public static void main(String[] args) {
	    System.out.println(new XmlCmccGamebaseNotifyResp().toXml());
	  }
}
