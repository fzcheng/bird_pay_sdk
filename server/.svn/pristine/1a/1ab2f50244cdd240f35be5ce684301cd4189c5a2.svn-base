package com.cheyooh.service.sdk.idata.gameserver;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;

/**
 * 
 * @author ljg
 * 
 */
@XObject("xml")
public class XmlWechatNotifyResp {
	
	private static final String XML_RESP = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xml><return_code>%s</return_code><return_msg>%s</return_msg></xml>";
	
	@XNode(value = "return_code")
	private String return_code="";//返回状态码
	
	@XNode(value = "return_msg")
	private String return_msg="";//返回信息
	
	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	public String getReturn_msg() {
		return return_msg;
	}

	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}

	public String toXml() {
		return String.format(XML_RESP, return_code, return_msg);
	}
	
	public static void main(String[] args) {
	    System.out.println(new XmlWechatNotifyResp().toXml());
	  }
}
