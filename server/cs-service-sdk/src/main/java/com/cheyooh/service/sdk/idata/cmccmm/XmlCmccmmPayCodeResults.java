package com.cheyooh.service.sdk.idata.cmccmm;

import java.util.ArrayList;
import java.util.List;

import org.nuxeo.common.xmap.annotation.XNodeList;
import org.nuxeo.common.xmap.annotation.XObject;

@XObject
public class XmlCmccmmPayCodeResults {
	@XNodeList(componentType = XmlCmccmmPayCodeResultSchema.class, type = ArrayList.class, value = "PayCodeResult")
	private List<XmlCmccmmPayCodeResultSchema> PayCodeResult ;

	public List<XmlCmccmmPayCodeResultSchema> getPayCodeResult() {
		return PayCodeResult;
	}

	public void setPayCodeResult(List<XmlCmccmmPayCodeResultSchema> payCodeResult) {
		PayCodeResult = payCodeResult;
	}

	@Override
	public String toString() {
		return "XmlCmccmmPayCodeResults [PayCodeResult=" + PayCodeResult + "]";
	}
	
}
