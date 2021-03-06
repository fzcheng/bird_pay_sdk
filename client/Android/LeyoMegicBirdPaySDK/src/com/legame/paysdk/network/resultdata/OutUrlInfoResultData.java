package com.legame.paysdk.network.resultdata;

import java.io.InputStream;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Xml;

import com.legame.paysdk.models.OutListUrlInfo;
import com.legame.paysdk.models.OutUrlInfo;
import com.legame.paysdk.utils.LogUtil;

public class OutUrlInfoResultData extends BaseResultData {
	private static final String TAG = OutUrlInfoResultData.class.getSimpleName();
	private String mExpectType;
	private OutUrlInfo mUrlInfo;
    private OutListUrlInfo mListInfo;
	
	public OutUrlInfo getOutUrlInfo(){
		return mUrlInfo;
	}
	
	public OutUrlInfoResultData(String expectType){
		this.mExpectType = expectType;
	}
	public OutListUrlInfo getOutListUrlInfo(){
		return mListInfo;
	}

	@Override
	public String getExpectPageType() {
		return mExpectType;
	}

	@Override
	public boolean parseXml(InputStream dateInput) {
//		XmlPullParser parser = Xml.newPullParser();
		try {

			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser parser = factory.newPullParser();
			parser.setInput(dateInput, "UTF-8");
		    Map<String, String> map = null;
		    int event = parser.getEventType();
			String name = null;
			mListInfo = new OutListUrlInfo();
			while (event != XmlPullParser.END_DOCUMENT) {
				if(isParseDataCanceled){
					return false;
				}
				event = parser.next();
				switch (event) {
				case XmlPullParser.START_TAG:
					name = parser.getName();
//					Log.i(TAG,"CP方使用动态域名名称："+name);
					if (name.equals("info")) {
						map = getXmlAttributes(parser);
						if (!parseInfoTag(map)) {
							LogUtil.w(TAG, "parseInfoTag error...");
							return false;
						}
					}else if (name.equals("item")) {
						map = getXmlAttributes(parser);
						mUrlInfo = new OutUrlInfo();
						mUrlInfo.setDomain(map.get("domain"));
						int gameid = Integer.parseInt(map.get("gameid"));
						mUrlInfo.setGameid(gameid);
//						Log.i(TAG,"domain:"+map.get("domain")+" gameid:"+gameid);
						
						mListInfo.uInfo.add(mUrlInfo);
//						Log.i(TAG,"列表大小"+mListInfo.uInfo.size());
					}
					
					break;
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
