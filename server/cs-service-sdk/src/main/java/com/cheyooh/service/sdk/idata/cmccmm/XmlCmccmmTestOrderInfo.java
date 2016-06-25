package com.cheyooh.service.sdk.idata.cmccmm;

import java.util.ArrayList;
import java.util.List;

import org.nuxeo.common.xmap.annotation.XNodeList;
import org.nuxeo.common.xmap.annotation.XObject;

@XObject
public class XmlCmccmmTestOrderInfo {
	@XNodeList(componentType = XmlCmccmmTestOrderInfoSchema.class, type = ArrayList.class, value = "TestOrderInfo")
	  private List<XmlCmccmmTestOrderInfoSchema> TestOrderInfo;

	public List<XmlCmccmmTestOrderInfoSchema> getTestOrderInfo() {
		return TestOrderInfo;
	}

	public void setTestOrderInfo(List<XmlCmccmmTestOrderInfoSchema> testOrderInfo) {
		TestOrderInfo = testOrderInfo;
	}

	@Override
	public String toString() {
		return "XmlCmccmmTestOrderInfo [TestOrderInfo=" + TestOrderInfo + "]";
	}
	
	
}
