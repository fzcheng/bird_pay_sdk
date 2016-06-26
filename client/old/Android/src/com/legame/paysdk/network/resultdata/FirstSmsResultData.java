package com.legame.paysdk.network.resultdata;

import java.io.InputStream;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

import com.legame.paysdk.models.FirstSmsInfo;
import com.legame.paysdk.utils.LogUtil;

public class FirstSmsResultData extends BaseResultData {

	private final String TAG = "FirstSmsResultData";
	private FirstSmsInfo mFirstSmsInfo;
	private String mExpectType;
	
	@Override
	public String getExpectPageType() {
		return mExpectType;
	}
	public FirstSmsResultData(String expectType){
		this.mExpectType = expectType;
	}
	public FirstSmsInfo getFirstSmsInfo() {
		return mFirstSmsInfo;
	}

	
	@Override
	public boolean parseXml(InputStream dateInput) {
		try {
//			XmlPullParser parser = Xml.newPullParser();

			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser parser = factory.newPullParser();
			parser.setInput(dateInput, "UTF-8");
			Map<String, String> map = null;
			
			int event = parser.getEventType();
			String name = null;
			
			while (event != XmlPullParser.END_DOCUMENT) {

				if (isParseDataCanceled) { //需要处理取消
					return false;
				}

				switch (event) {
				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:
					name = parser.getName();
					Log.i(TAG, "name-->"+name);
					if ("info".equals(name)) {
						map = getXmlAttributes(parser);
						if (!parseInfoTag(map)) {
							LogUtil.w(TAG, "parseInfoTag error...");
							return false;
						}
					} else if("item".equals(name)) {
						map = getXmlAttributes(parser);
						mFirstSmsInfo = new FirstSmsInfo();
						mFirstSmsInfo.setSdkSmsUpPort(map.get("sdkSmsUpPort"));
						Log.i(TAG, "上行端口："+mFirstSmsInfo.getSdkSmsUpPort());
					}
					break;
				}
				event = parser.next();
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.w(TAG, "parseXml error:" + e.toString());
		}
		return false;
	}

}
