package com.legame.paysdk.network.resultdata;

import java.io.InputStream;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class MMdoInitInfoResultData extends BaseResultData{
	
	private String mExpectType;
	
	private String mmAppId = "";
	private String mmAppKey = "";
	
	public MMdoInitInfoResultData(String expectType){
		this.mExpectType = expectType;
	}

	@Override
	public String getExpectPageType() {
		// TODO Auto-generated method stub
		return mExpectType;
	}

	@Override
	public boolean parseXml(InputStream dateInput) {
		try {
			
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = factory.newPullParser();
			parser.setInput(dateInput,"utf-8");
			
			Map<String, String> map = null;
			int event = parser.getEventType();
			String name = null;
			while(event != XmlPullParser.END_DOCUMENT){
				event = parser.next();
				switch (event) {
				
				case XmlPullParser.START_TAG:
					name = parser.getName();
					if("ResultGetSwbCmcc".equals(name)){
						map = getXmlAttributes(parser);
						mmAppId = map.get("appId");
						mmAppKey = map.get("appKey");
					}
					break;

				default:
					break;
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return false;
	}

	public String getMmAppId() {
		return mmAppId;
	}

	public void setMmAppId(String mmAppId) {
		this.mmAppId = mmAppId;
	}

	public String getMmAppKey() {
		return mmAppKey;
	}

	public void setMmAppKey(String mmAppKey) {
		this.mmAppKey = mmAppKey;
	}
}
