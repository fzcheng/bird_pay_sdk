package com.cheyooh.service.sdk.idata.cmccmm;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;

@XObject
public class XmlcmccmmAppExtSchema {
	private static final String XML_RESP = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
			+ "<AppExtInfo>\n"
			+ "   <ContentFee>%s</ContentFee>\n"
			+ "   <ContentType>%s</ContentType>\n"
			+ "   <Type>%s</Type>\n"
			+ "   <OnlineType>%s</OnlineType>\n" 
			+ "   <ChargeTime>%s</ChargeTime>\n"
			+ "   <ChargeType>%s</ChargeType>\n"
			+ "   <ChargePic>%s</ChargePic>\n"
			+ "   <CopyRightAttach>%s</CopyRightAttach>\n"
			+ "</AppExtInfo>";
	
	@XNode(value = "ContentFee")
	private String ContentFee="";
	
	@XNode(value = "ContentType")
	private String ContentType="";
	
	@XNode(value = "Type")
	private String Type="";
	
	@XNode(value = "OnlineType")
	private String OnlineType="";
	
	@XNode(value = "ChargeTime")
	private String ChargeTime="";
	
	@XNode(value = "ChargeType")
	private String ChargeType="";
	
	@XNode(value = "ChargePic")
	private String ChargePic="";
	
	@XNode(value = "CopyRightAttach")
	private String CopyRightAttach="";

	public String getContentFee() {
		return ContentFee;
	}

	public void setContentFee(String contentFee) {
		ContentFee = contentFee;
	}

	public String getContentType() {
		return ContentType;
	}

	public void setContentType(String contentType) {
		ContentType = contentType;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getOnlineType() {
		return OnlineType;
	}

	public void setOnlineType(String onlineType) {
		OnlineType = onlineType;
	}

	public String getChargeTime() {
		return ChargeTime;
	}

	public void setChargeTime(String chargeTime) {
		ChargeTime = chargeTime;
	}

	public String getChargeType() {
		return ChargeType;
	}

	public void setChargeType(String chargeType) {
		ChargeType = chargeType;
	}

	public String getChargePic() {
		return ChargePic;
	}

	public void setChargePic(String chargePic) {
		ChargePic = chargePic;
	}

	public String getCopyRightAttach() {
		return CopyRightAttach;
	}

	public void setCopyRightAttach(String copyRightAttach) {
		CopyRightAttach = copyRightAttach;
	}

	@Override
	public String toString() {
		return "XmlcmccmmAppExtSchema [ContentFee=" + ContentFee
				+ ", ContentType=" + ContentType + ", Type=" + Type
				+ ", OnlineType=" + OnlineType + ", ChargeTime=" + ChargeTime
				+ ", ChargeType=" + ChargeType + ", ChargePic=" + ChargePic
				+ ", CopyRightAttach=" + CopyRightAttach + "]";
	}

	public String toXml() {
	    return String.format(XML_RESP, ContentFee, ContentType, Type,OnlineType,ChargeTime,ChargeType,ChargePic,CopyRightAttach);
	}
	
	public static void main(String[] args) {
		 System.out.println(new XmlcmccmmAppExtSchema().toXml());
	}
}
