package com.cheyooh.service.sdk.idata.cmccmm;

import java.util.ArrayList;
import java.util.List;

import org.nuxeo.common.xmap.annotation.XNodeList;
import org.nuxeo.common.xmap.annotation.XObject;

@XObject
public class XmlCmccmmPayCodeReqInfos {
	 @XNodeList(componentType = XmlCmccmmPayCodeReqInfoSchema.class, type = ArrayList.class, value = "PayCodeReqInfo")
	  private List<XmlCmccmmPayCodeReqInfoSchema> PayCodeReqInfo;

	public List<XmlCmccmmPayCodeReqInfoSchema> getPayCodeReqInfo() {
		return PayCodeReqInfo;
	}

	public void setPayCodeReqInfo(List<XmlCmccmmPayCodeReqInfoSchema> payCodeReqInfo) {
		PayCodeReqInfo = payCodeReqInfo;
	}

	@Override
	public String toString() {
		return "XmlCmccmmPayCodeReqInfos [PayCodeReqInfo=" + PayCodeReqInfo
				+ "]";
	}
	 
	 
}
