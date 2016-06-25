package com.legame.paysdk.network.resultdata;

import java.util.Map;

import org.xmlpull.v1.XmlPullParser;

public class WxpayResultData extends BaseXmlResultData {

	private String mExpectType;
	private Map<String,String> wxpayInfoMap;
	
	public WxpayResultData(String extrType){
		this.mExpectType = extrType;
	}

	@Override
	public String getExpectPageType() {
		return mExpectType;
	}

	public Map<String,String> getWxpayInfoMap(){
		return wxpayInfoMap;
	}

	@Override
	protected void getParams(String name, XmlPullParser parser) {
		if("order".equals(name)){
			wxpayInfoMap = getXmlAttributes(parser);
		}
	}
	
}
