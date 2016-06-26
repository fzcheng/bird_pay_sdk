package com.legame.leyo.smspay;

import java.io.InputStream;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

import com.legame.paysdk.network.resultdata.BaseResultData;
import com.legame.paysdk.network.resultdata.FastPaymentResultData;
import com.legame.paysdk.utils.LogUtil;

/** 
 * 类说明：   
 * @author  Shaohui.Yang
 * @date    2014年6月10日
 * @version 1.0
 */
public class MdoPayBackResultData extends BaseResultData{
	private static final String TAG = FastPaymentResultData.class.getSimpleName();
	
	@Override
	public String getExpectPageType() {
		return "payback_mmdo";
	}

	@Override
	public boolean parseXml(InputStream dateInput) {
		try {
			XmlPullParser parser = Xml.newPullParser();
			parser.setInput(dateInput, "UTF-8");
			Map<String, String> map = null;
			int event = parser.getEventType();
			String name = null;

			while (event != XmlPullParser.END_DOCUMENT) {
				if(isParseDataCanceled){
					return false;
				}
				event = parser.next();
				switch (event) {
				case XmlPullParser.START_TAG:
					name = parser.getName();
					if (name.equals("info")) {
						map = getXmlAttributes(parser);
						if (!parseInfoTag(map)) {
							LogUtil.w(TAG, "parseInfoTag error...");
							return false;
						}						
					}				
					break;
				case XmlPullParser.END_TAG:
					break;
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.w(TAG, "parseXml error:" + e.toString());
		}
		return false;
	}

}
