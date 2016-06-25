package com.cheyooh.service.sdk.idata.cmccmm;

import java.util.ArrayList;
import java.util.List;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XNodeList;
import org.nuxeo.common.xmap.annotation.XObject;

@XObject("OrdAuthReq")
public class XmlCmccmmOrdAuthReq {
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
	
	@XNode(value = "UserId")
	private String UserId = "";
	
	@XNode(value = "AppID")
	private String AppID = "";
	
	@XNode(value = "PayCode")
	private String PayCode = "";
	
	@XNode(value = "TotalPrice")
	private String TotalPrice = "";
	
	@XNode(value = "OrderID")
	private String OrderID = "";
	
	@XNode(value = "MD5sign")
	private String MD5sign = "";
	
	@XNode(value = "OrderType")
	private String OrderType = "";
	
	@XNode(value = "ChannelID")
	private String ChannelID = "";
	
	@XNode(value = "PorvinceID")
	private String PorvinceID = "";
	
	@XNode(value = "PayerType")
	private String PayerType = "";
	
	@XNode(value = "ExData")
	private String ExData = "";

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

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getAppID() {
		return AppID;
	}

	public void setAppID(String appID) {
		AppID = appID;
	}

	public String getPayCode() {
		return PayCode;
	}

	public void setPayCode(String payCode) {
		PayCode = payCode;
	}

	public String getTotalPrice() {
		return TotalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		TotalPrice = totalPrice;
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

	public String getOrderType() {
		return OrderType;
	}

	public void setOrderType(String orderType) {
		OrderType = orderType;
	}

	public String getChannelID() {
		return ChannelID;
	}

	public void setChannelID(String channelID) {
		ChannelID = channelID;
	}

	public String getPorvinceID() {
		return PorvinceID;
	}

	public void setPorvinceID(String porvinceID) {
		PorvinceID = porvinceID;
	}

	public String getPayerType() {
		return PayerType;
	}

	public void setPayerType(String payerType) {
		PayerType = payerType;
	}

	public String getExData() {
		return ExData;
	}

	public void setExData(String exData) {
		ExData = exData;
	}

	@Override
	public String toString() {
		return "XmlCmccmmOrdAuthReq [MsgType=" + MsgType + ", Version="
				+ Version + ", Send_Address=" + Send_Address
				+ ", Dest_Address=" + Dest_Address + ", OutTradeID="
				+ OutTradeID + ", UserId=" + UserId + ", AppID=" + AppID
				+ ", PayCode=" + PayCode + ", TotalPrice=" + TotalPrice
				+ ", OrderID=" + OrderID + ", MD5sign=" + MD5sign
				+ ", OrderType=" + OrderType + ", ChannelID=" + ChannelID
				+ ", PorvinceID=" + PorvinceID + ", PayerType=" + PayerType
				+ ", ExData=" + ExData + "]";
	}
	
	
}
