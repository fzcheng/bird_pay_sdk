package com.cheyooh.service.sdk.idata.gameserver;


import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import org.nuxeo.common.xmap.XMap;
import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;

@XObject("message")
public class XmlEmayUpMessage {
	@XNode(value = "srctermid")
	private String srctermid;
	
	@XNode(value = "sendTime")
	private String sendTime;
	
	@XNode(value = "msgcontent")
	private String msgcontent;

	public XmlEmayUpMessage(){
		
	}

	public String getSrctermid() {
		return srctermid;
	}

	public void setSrctermid(String srctermid) {
		this.srctermid = srctermid;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getMsgcontent() {
		return msgcontent;
	}

	public void setMsgcontent(String msgcontent) {
		this.msgcontent = msgcontent;
	}

	@Override
	public String toString() {
		return "XmlEmayUpMessage [srctermid=" + srctermid + ", sendTime="
				+ sendTime + ", msgcontent=" + msgcontent + "]";
	}
	
	public static void main(String[] args) {
		XMap xmap = new XMap();
	    xmap.register(XmlEmayUpMessage.class);
	    String xml = "<message><srctermid>18602040382</srctermid><sendTime>20150109105423</sendTime><msgcontent>460012040600239,861138028683874</msgcontent><addSerial></addSerial><addSerialRev>168985</addSerialRev></message>";
	    ByteArrayInputStream in;
	    try {
	      in = new ByteArrayInputStream(xml.getBytes("utf-8"));
	      XmlEmayUpMessage sms = (XmlEmayUpMessage) xmap.load(in);
	      System.out.println(sms);
	      List<String> filters = new ArrayList<String>();
	      String result=xmap.asXmlString(sms, "UTF-8", filters,true);
	      System.out.println("the xmlString is :"+result);
	      
	    } catch (Exception e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	}
}
