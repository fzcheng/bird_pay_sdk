package com.cheyooh.service.sdk.idata.gameserver;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.nuxeo.common.xmap.XMap;
import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XNodeList;
import org.nuxeo.common.xmap.annotation.XObject;

@XObject("response")
public class EmaySms {
	
	@XNode(value = "error")
	private String error;
	
	@XNodeList(value = "message", type = ArrayList.class, componentType = 
			XmlEmayUpMessage.class)
	private List< XmlEmayUpMessage> xmlEmayUpMessage;

	public EmaySms(){
		this.error=null;
		this.xmlEmayUpMessage=null;
	}
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public List<XmlEmayUpMessage> getXmlEmayUpMessage() {
		return xmlEmayUpMessage;
	}

	public void setXmlEmayUpMessage(List<XmlEmayUpMessage> xmlEmayUpMessage) {
		this.xmlEmayUpMessage = xmlEmayUpMessage;
	}

	@Override
	public String toString() {
		return "EmaySms [error=" + error + ", xmlEmayUpMessage="
				+ xmlEmayUpMessage + "]";
	}

	public static void main(String[] args) {
	    XMap xmap = new XMap();
	    xmap.register(EmaySms.class);
	    
	    String xml1 ="<response><error>0</error><message/></response>";	    
	    String xml2 = "<response><error>0</error><message><srctermid>13570200825</srctermid><sendTime>20150109105610</sendTime><msgcontent>1111</msgcontent><addSerial></addSerial><addSerialRev>168985</addSerialRev></message></response>";
	    String xml3 = "<response><error>0</error><message><srctermid>18602040382</srctermid><sendTime>20150109105423</sendTime><msgcontent>460012040600239,861138028683874</msgcontent><addSerial></addSerial><addSerialRev>168985</addSerialRev></message><message><srctermid>13570200825</srctermid><sendTime>20150109105610</sendTime><msgcontent>1111</msgcontent><addSerial></addSerial><addSerialRev>168985</addSerialRev></message></response>";
	    
	    ByteArrayInputStream in;
	    try {
	      in = new ByteArrayInputStream(xml2.trim().getBytes("utf-8"));
	      EmaySms sms = (EmaySms) xmap.load(in);
	      SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-HH-dd HH:mm:ss");
	      SimpleDateFormat formatter2=new SimpleDateFormat("yyyyHHddHHmmss");
	      //Date date=new Date();
	      //System.out.println(date);
	      List<XmlEmayUpMessage> message = sms.getXmlEmayUpMessage();
	      if(message.get(0).getSrctermid()!=null){
	    	  int i=1;
		      for(XmlEmayUpMessage xmlEmayUpMessage:sms.xmlEmayUpMessage){
		    	  System.out.println("第"+i+"个---the srctermid is :"+xmlEmayUpMessage.getSrctermid());
		    	  //System.out.println("第"+i+"个---the sendTime is :"+xmlEmayUpMessage.getSendTime().trim());
		    	  System.out.println("第"+i+"个---the msgcontent is :"+xmlEmayUpMessage.getMsgcontent());
		    	  i++;
		      }
	      }
	      
	      List<String> filters = new ArrayList<String>();
	      String result=xmap.asXmlString(sms, "UTF-8", filters,true);
	      System.out.println("the xmlString is :"+result);
	      
	    } catch (Exception e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	}
}
