package com.legame.paysdk.network.resultdata;

import java.io.InputStream;

import org.apache.http.protocol.HTTP;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.text.TextUtils;

public class AutoRegResultData extends BaseResultData {
	private static final String CMD = "auto_register";

	private static final String NS = "";
	private static final String RESP_INFO_TAG = "info";
	private static final String RESP_REGISTER_TAG = "register";
	private static final String RESP_DEVICE_ID_TAG = "device_id";

	private String mDevId;
	private String smsTip;//控制第一次启动是否弹框

	public String getDevId() {
		return mDevId;
	}
	
	public String getSmsTip(){
		return smsTip;
	}

	@Override
	public String getExpectPageType() {
		return CMD;
	}

	@Override
	public boolean parseXml(InputStream dateInput) {
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser parser = factory.newPullParser();
			parser.setInput(dateInput, HTTP.UTF_8);
			int eventType = parser.getEventType();
			String tag = "";
			while (eventType != XmlPullParser.END_DOCUMENT) {
				// every time, check if user canceled this op
				if (isParseDataCanceled) {
					return false;
				}

				if (eventType == XmlPullParser.START_TAG) {
					tag = parser.getName();
					if (TextUtils.equals(tag, RESP_INFO_TAG)) {
						if (!parseInfoTag(getXmlAttributes(parser))) {
							return false;
						}
					} else if (TextUtils.equals(tag, RESP_REGISTER_TAG)) {
						mDevId = parser.getAttributeValue(NS,
								RESP_DEVICE_ID_TAG);
						smsTip = parser.getAttributeValue(NS,"smstip");
						break;
					}
				}
				eventType = parser.next();
			}// ?end while
			return true;
		} catch (Exception e) {

		}
		return false;
	}

}
