package com.cheyooh.service.sdk.idata.gameserver;


import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;

@XObject("request")
public class XmlCmccyxjdnSmsReq {
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
	
	
//	String imei;
//	String imsi;
//	String price;
//	String cpparam;
//
//	public String toXml() {
//
//		XmlSerializer serializer = Xml.newSerializer();
//		
//		StringWriter writer = new StringWriter();
//		try {
//			serializer.setOutput(writer);
//			serializer.startDocument("utf-8", true);
//
//			serializer.startTag(null, "request");
//
//			serializer.startTag(null, "imsi");
//			serializer.text(imsi);
//			serializer.endTag(null, "imsi");
//
//			serializer.startTag(null, "imei");
//			serializer.text(imei);
//			serializer.endTag(null, "imei");
//
//			serializer.startTag(null, "price");
//			serializer.text(price);
//			serializer.endTag(null, "price");
//
//			serializer.startTag(null, "cpparam");
//			serializer.text(cpparam);
//			serializer.endTag(null, "cpparam");
//			
//			serializer.endTag(null, "request");
//
//			serializer.endDocument();
//			return writer.toString();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//
//	}
	
}
