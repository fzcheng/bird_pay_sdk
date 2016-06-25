package com.cheyooh.service.sdk.idata.cmccmm;

import java.util.ArrayList;
import java.util.List;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XNodeList;
import org.nuxeo.common.xmap.annotation.XObject;

@XObject("OrdAuthReq")
public class XmlCmccmmOrdAuthResp {
	
	@XNode(value = "MsgType")
	private String MsgType = "";
	
	@XNode(value = "Version")
	private String Version = "";
	
	@XNodeList(componentType = XmlCmccmmAddressInfoSchema.class, type = ArrayList.class, value = "Send_Address")
	private List<XmlCmccmmAddressInfoSchema> Send_Address;

	@XNodeList(componentType = XmlCmccmmAddressInfoSchema.class, type = ArrayList.class, value = "Dest_Address")
	private List<XmlCmccmmAddressInfoSchema> Dest_Address;
	
	@XNode(value = "OutTradeID")
	private String OutTradeID = "";
	
	@XNode(value = "OrderID")
	private String OrderID = "";
	
	@XNode(value = "MD5sign")
	private String MD5sign = "";
	
	@XNode(value = "Result")
	private String Result = "";
	
	@XNode(value = "ApName")
	private String ApName = "";
	
	@XNode(value = "AppName")
	private String AppName = "";
	
	@XNode(value = "AppId")
	private String AppId = "";
	
	@XNode(value = "ItemName")
	private String ItemName = "";
	
	@XNode(value = "PayCode")
	private String PayCode = "";
	
	@XNode(value = "Price")
	private String Price = "";
	
	@XNode(value = "Desc")
	private String Desc = "";

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public String getVersion() {
		return Version;
	}

	public void setVersion(String version) {
		Version = version;
	}

	public List<XmlCmccmmAddressInfoSchema> getSend_Address() {
		return Send_Address;
	}

	public void setSend_Address(List<XmlCmccmmAddressInfoSchema> send_Address) {
		Send_Address = send_Address;
	}

	public List<XmlCmccmmAddressInfoSchema> getDest_Address() {
		return Dest_Address;
	}

	public void setDest_Address(List<XmlCmccmmAddressInfoSchema> dest_Address) {
		Dest_Address = dest_Address;
	}

	public String getOutTradeID() {
		return OutTradeID;
	}

	public void setOutTradeID(String outTradeID) {
		OutTradeID = outTradeID;
	}

	public String getOrderID() {
		return OrderID;
	}

	public void setOrderID(String orderID) {
		OrderID = orderID;
	}

	public String getMD5sign() {
		return MD5sign;
	}

	public void setMD5sign(String mD5sign) {
		MD5sign = mD5sign;
	}

	public String getResult() {
		return Result;
	}

	public void setResult(String result) {
		Result = result;
	}

	public String getApName() {
		return ApName;
	}

	public void setApName(String apName) {
		ApName = apName;
	}

	public String getAppName() {
		return AppName;
	}

	public void setAppName(String appName) {
		AppName = appName;
	}

	public String getAppId() {
		return AppId;
	}

	public void setAppId(String appId) {
		AppId = appId;
	}

	public String getItemName() {
		return ItemName;
	}

	public void setItemName(String itemName) {
		ItemName = itemName;
	}

	public String getPayCode() {
		return PayCode;
	}

	public void setPayCode(String payCode) {
		PayCode = payCode;
	}

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		Price = price;
	}

	public String getDesc() {
		return Desc;
	}

	public void setDesc(String desc) {
		Desc = desc;
	}

	@Override
	public String toString() {
		return "XmlCmccmmOrdAuthResp [MsgType=" + MsgType + ", Version="
				+ Version + ", Send_Address=" + Send_Address
				+ ", Dest_Address=" + Dest_Address + ", OutTradeID="
				+ OutTradeID + ", OrderID=" + OrderID + ", MD5sign=" + MD5sign
				+ ", Result=" + Result + ", ApName=" + ApName + ", AppName="
				+ AppName + ", AppId=" + AppId + ", ItemName=" + ItemName
				+ ", PayCode=" + PayCode + ", Price=" + Price + ", Desc="
				+ Desc + "]";
	}
	
	
}
