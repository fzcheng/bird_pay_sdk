package com.legame.paysdk.network.resultdata;

import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

/**
 * 
 * @author Kaiguang
 * @date 2016.3.25
 * @类说明 解析XML数据的基类
 */
public abstract class BaseXmlResultData extends BaseResultData{


	@Override
	public boolean parseXml(InputStream dateInput) {
		
		try {
//			 int count = 0;
//			 while(count == 0){
//			 count = dateInput.available();
//			 }
//			 byte buffer[] = new byte[count];
//			 dateInput.read(buffer);
//			 String str = new String(buffer);
//			 Log.e("BaseRayResult", "xml info:"+str);
			
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser parser = factory.newPullParser();
			parser.setInput(dateInput, "UTF-8");
			
			int event = parser.getEventType();
			String name = null;
			while(XmlPullParser.END_DOCUMENT != event){
				if(isParseDataCanceled){
					return false;
				}
				
				event = parser.next();
				if(XmlPullParser.START_TAG == event){
					name = parser.getName();
					if("info".equals(name)){
						if (!parseInfoTag(getXmlAttributes(parser))) {
							return false;
						}
					}else{
						getParams(name,parser);
					}
				}
				
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	protected abstract void getParams(String name,XmlPullParser parser);

}
