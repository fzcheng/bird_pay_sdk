package com.legame.paysdk.network.resultdata;

import java.io.InputStream;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Xml;

import com.legame.paysdk.models.UserInfo;
import com.legame.paysdk.utils.LogUtil;


/** 
 * 类说明：   
 * @author  xinhui.cheng
 * @date    2013-10-14
 * @version 1.0
 */
public class UserLoginResultData extends BaseResultData {
	
	private static final String TAG = UserLoginResultData.class.getSimpleName();
	
	private UserInfo mUserInfo;
	
	@Override
	public String getExpectPageType() {
		return "user_login";
	}
	
	public UserInfo getUserInfo(){
		return mUserInfo;
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
						
					} else if (name.equals("user_login")) {
						map = getXmlAttributes(parser);
						mUserInfo = new UserInfo();
						mUserInfo.setSid(map.get("sid"));
						mUserInfo.setValidTime(Long.valueOf(map.get("valid_time")));
						mUserInfo.setBindPhone(Boolean.valueOf(map.get("bind_phone")));
						mUserInfo.setNickName(map.get("nick_name"));
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
