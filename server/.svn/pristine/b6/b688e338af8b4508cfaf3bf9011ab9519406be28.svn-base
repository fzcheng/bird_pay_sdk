package com.cheyooh.service.sdk.idata.cmccmm;

import java.util.ArrayList;
import java.util.List;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XNodeList;
import org.nuxeo.common.xmap.annotation.XObject;

@XObject("AppRequest")
public class XmlCmccmmAppRequest {
//	private static final String XML_RESP = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
//			+ "<AppRequest>\n"
//			+ "   <TransactionId>%s</TransactionId>\n"
//			+ "   <PltID>%s</PltID>\n"
//			+ "   <MD5sign>%s</MD5sign>\n"
//			+ "   <Status>%s</Status>\n" 
//			+ "   <APPID>%s</APPID>\n"
//			+ "   <AppName>%s</AppName>\n"
//			+ "   <AppDesc>%s</AppDesc>\n"
//			+ "   <DevName>%s</DevName>\n"
//			+ "   <ServicesPhone>%s</ServicesPhone>\n"
//			+ "   <FreeRetest>%s</FreeRetest>\n"
//			+ "   <AppExtInfo>%s</AppExtInfo>\n"
//			+ "</AppRequest>";
	
	@XNode(value = "TransactionId")
	private String TransactionId = "";
	  
	  @XNode(value = "PltID")
	  private String PltID="";
	  
	  @XNode(value = "MD5sign")
	  private String MD5sign="";
	  
	  @XNode(value = "Status")
	  private String Status="";
	  
	  @XNode(value = "APPID")
	  private String APPID="";
	  
	  @XNode(value = "AppName")
	  private String AppName="";
	  
	  @XNode(value = "AppDesc")
	  private String AppDesc="";
	  
	  @XNode(value = "DevName")
	  private String DevName="";
	  
	  @XNode(value = "ServicesPhone")
	  private String ServicesPhone="";
	  
	  @XNode(value = "FreeRetest")
	  private String FreeRetest="";
	  
	  @XNodeList(componentType = XmlcmccmmAppExtSchema.class, type = ArrayList.class, value = "AppExtInfo")
	  private List<XmlcmccmmAppExtSchema> AppExtInfo;

	public String getTransactionId() {
		return TransactionId;
	}

	public void setTransactionId(String transactionId) {
		TransactionId = transactionId;
	}

	public String getPltID() {
		return PltID;
	}

	public void setPltID(String pltID) {
		PltID = pltID;
	}

	public String getMD5sign() {
		return MD5sign;
	}

	public void setMD5sign(String mD5sign) {
		MD5sign = mD5sign;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getAPPID() {
		return APPID;
	}

	public void setAPPID(String aPPID) {
		APPID = aPPID;
	}

	public String getAppName() {
		return AppName;
	}

	public void setAppName(String appName) {
		AppName = appName;
	}

	public String getAppDesc() {
		return AppDesc;
	}

	public void setAppDesc(String appDesc) {
		AppDesc = appDesc;
	}

	public String getDevName() {
		return DevName;
	}

	public void setDevName(String devName) {
		DevName = devName;
	}

	public String getServicesPhone() {
		return ServicesPhone;
	}

	public void setServicesPhone(String servicesPhone) {
		ServicesPhone = servicesPhone;
	}

	public String getFreeRetest() {
		return FreeRetest;
	}

	public void setFreeRetest(String freeRetest) {
		FreeRetest = freeRetest;
	}

	public List<XmlcmccmmAppExtSchema> getAppExtInfo() {
		return AppExtInfo;
	}

	public void setAppExtInfo(List<XmlcmccmmAppExtSchema> appExtInfo) {
		AppExtInfo = appExtInfo;
	}

//	public void addAppExtInfo(XmlcmccmmAppExtSchema xmlcmccmmAppExtSchema){
//		if(AppExtInfo==null){
//			AppExtInfo=new ArrayList<XmlcmccmmAppExtSchema>();
//		}
//		AppExtInfo.add(xmlcmccmmAppExtSchema);
//	}
	  
	@Override
	public String toString() {
		return "XmlCmccmmAppRequest [TransactionId=" + TransactionId
				+ ", PltID=" + PltID + ", MD5sign=" + MD5sign + ", Status="
				+ Status + ", APPID=" + APPID + ", AppName=" + AppName
				+ ", AppDesc=" + AppDesc + ", DevName=" + DevName
				+ ", ServicesPhone=" + ServicesPhone + ", FreeRetest="
				+ FreeRetest + ", AppExtInfo=" + AppExtInfo + "]";
	}

//	public String toXml() {
//	    return String.format(XML_RESP, TransactionId, PltID, MD5sign,Status,APPID,AppName,AppDesc,DevName,ServicesPhone,FreeRetest,AppExtInfo);
//	}
	
	public static void main(String[] args) {
//		 System.out.println(xmlCmccmmAppRequest.toXml());
	}
}
