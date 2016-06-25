package com.legame.paysdk.network.resultdata;

import java.util.Map;
import org.xmlpull.v1.XmlPullParser;

public class AlipayResultData extends BaseXmlResultData{
	
	private String mExtrType;
	
	private String orderNo;
	
	private String payInfo;
	
	public AlipayResultData(String extrType){
		this.mExtrType = extrType;
	}

	@Override
	public String getExpectPageType() {
		return mExtrType;
	}
	
	@Override
	protected void getParams(String name, XmlPullParser parser) {
		if("order".equals(name)){
			Map<String, String>map = getXmlAttributes(parser);
			orderNo = map.get("order_no");
			payInfo = map.get("pay_info");
		}

	}

	public String getOrderNo(){
		return orderNo;
	}
	
	public String getPayInfo(){
		return payInfo;
	}

}
