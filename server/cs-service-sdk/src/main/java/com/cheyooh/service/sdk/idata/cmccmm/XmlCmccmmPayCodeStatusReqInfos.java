package com.cheyooh.service.sdk.idata.cmccmm;

import java.util.ArrayList;
import java.util.List;

import org.nuxeo.common.xmap.annotation.XNodeList;
import org.nuxeo.common.xmap.annotation.XObject;

@XObject
public class XmlCmccmmPayCodeStatusReqInfos {

	@XNodeList(componentType = XmlCmccmmPayCodeStatusReqInfoSchema.class, type = ArrayList.class, value = "PayCodeStatusReqInfo")
	private List<XmlCmccmmPayCodeStatusReqInfoSchema> PayCodeStatusReqInfo;

	public List<XmlCmccmmPayCodeStatusReqInfoSchema> getPayCodeStatusReqInfo() {
		return PayCodeStatusReqInfo;
	}

	public void setPayCodeStatusReqInfo(
			List<XmlCmccmmPayCodeStatusReqInfoSchema> payCodeStatusReqInfo) {
		PayCodeStatusReqInfo = payCodeStatusReqInfo;
	}

	@Override
	public String toString() {
		return "XmlCmccmmPayCodeStatusReqInfos [PayCodeStatusReqInfo="
				+ PayCodeStatusReqInfo + "]";
	}

}
