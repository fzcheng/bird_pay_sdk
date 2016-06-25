package com.cheyooh.service.sdk.idata.gameserver;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;

@XObject("response")
public class XmlCmcc49youVerifycodeRes {
	
	@XNode(value = "resultCode")
	private String resultCode;

	@XNode(value = "resultMsg")
	private String resultMsg;

	@XNode(value = "orderid")
	private String orderid;

	public XmlCmcc49youVerifycodeRes() {
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	@Override
	public String toString() {
		return "XmlCmcc49youVerifycodeRes [resultCode=" + resultCode
				+ ", resultMsg=" + resultMsg + ", orderid=" + orderid + "]";
	}

}
