package com.cheyooh.service.sdk.idata.cmccmm;

import java.util.ArrayList;
import java.util.List;

import org.nuxeo.common.xmap.annotation.XNodeList;
import org.nuxeo.common.xmap.annotation.XObject;

@XObject
public class XmlCmccmmPayCodeResInfos {
	@XNodeList(componentType = XmlCmccmmPayCodeResInfoSchema.class, type = ArrayList.class, value = "PayCodeResInfo")
	  private List<XmlCmccmmPayCodeResInfoSchema> PayCodeResInfo;

	public List<XmlCmccmmPayCodeResInfoSchema> getPayCodeResInfo() {
		return PayCodeResInfo;
	}

	public void setPayCodeResInfo(List<XmlCmccmmPayCodeResInfoSchema> payCodeResInfo) {
		PayCodeResInfo = payCodeResInfo;
	}

	@Override
	public String toString() {
		return "XmlCmccmmPayCodeResInfos [PayCodeResInfo=" + PayCodeResInfo
				+ "]";
	}
	
}
