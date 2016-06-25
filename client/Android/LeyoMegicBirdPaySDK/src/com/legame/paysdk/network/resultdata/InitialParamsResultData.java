package com.legame.paysdk.network.resultdata;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

/**
 * 
 * @author Kaiguang
 * @date 2016.3.21
 * @类说明 解析初始化获取相关参数接口下发的参数2
 * 
 */
public class InitialParamsResultData extends BaseResultData {

	private String extrType;
	
	private List<String> payTypeList;
	
	public InitialParamsResultData(String extrType) {
		this.extrType = extrType;
		payTypeList = new ArrayList<String>();
	}

	@Override
	public String getExpectPageType() {
		return extrType;
	}

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
//			 Log.e("AlipayResult", "xml info:"+str);

			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser parser = factory.newPullParser();

			parser.setInput(dateInput, "UTF-8");
			int event = parser.getEventType();
			String name = null;
			Map<String, String> map = null;
			while (event != XmlPullParser.END_DOCUMENT) {
				if (isParseDataCanceled) {
					return false;
				}
				event = parser.next();
				if (event == XmlPullParser.START_TAG) {
					name = parser.getName();
					if("info".equals(name)){
						if (!parseInfoTag(getXmlAttributes(parser))) {
							return false;
						}
					}else if("type".equals(name)){
						map = getXmlAttributes(parser);
						String payType = map.get("gamePaymentName");
						payTypeList.add(payType);
					}
					
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	
	public List<String> getPaytypeInfos(){
		return payTypeList;
	}
	
	/**
	 * 
	 <info type="Get_initial_param" error="0" substatus="0" detail="">
	 * 
	 * <item> <type gamePaymentName="alipay" gamePaymentIdx="1"/>
	 * 
	 * <type gamePaymentName="tenpay" gamePaymentIdx="2"/>
	 * 
	 * <type gamePaymentName="yeepay" gamePaymentIdx="3"/>
	 * 
	 * <type gamePaymentName="mo9" gamePaymentIdx="4"/>
	 * 
	 * <type gamePaymentName="wiipay" gamePaymentIdx="5"/>
	 * 
	 * <type gamePaymentName="unionpay" gamePaymentIdx="6"/>
	 * 
	 * <type gamePaymentName="upay" gamePaymentIdx="7"/>
	 * 
	 * <type gamePaymentName="vpay" gamePaymentIdx="8"/>
	 * 
	 * <type gamePaymentName="mmdo" gamePaymentIdx="9"/> </item> </info>
	 */

}
