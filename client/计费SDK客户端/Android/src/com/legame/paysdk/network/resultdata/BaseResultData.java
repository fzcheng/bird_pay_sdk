package com.legame.paysdk.network.resultdata;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;

import com.legame.paysdk.ErrorCode;
import com.legame.paysdk.utils.LogUtil;

/** 
 * 类说明：   
 * @author  Terry.Lu
 * @date    2013-5-15
 * @version 1.0
 */
public abstract class BaseResultData {
	private static final String TAG = "BaseResultData";
	
	//以下三个变量，故意定义为private，不让子类直接使用，子类应该使用parseInfoTag方法来设置这三个值
	private String mErrorTip = null;
	private int mErrorCode = -1; //错误码，0表示正确，其他表示错误
	private String mPageType = null;
	
	protected boolean isParseDataCanceled = false; //解析过程中需要对该标记为进行处理
	
	public String getErrorTip() {
		return mErrorTip;
	}
	
	public String getPageType() {
		return mPageType;
	}
	
	public int getErrorCode() {
		return mErrorCode;
	}
	
	public abstract String getExpectPageType();
	
	public void setParseCanceled(boolean isCanceled){
		isParseDataCanceled = isCanceled;
	}
	
	/**
	 * 封装解析info tag的通用方法，供子类使用
	 * */
	protected boolean parseInfoTag(final Map<String, String> xmlMap) {
		
		if(xmlMap == null){
			return false;
		}
		
		mPageType = xmlMap.get("type");
		
		if (mPageType == null || !mPageType.equals(getExpectPageType())) {
			LogUtil.w(TAG, "xml type error:" + mPageType);
			return false;
		}
		
		String error = xmlMap.get("error");
		if (error == null) {
			LogUtil.w(TAG, "xml error code wrong");
			return false;
		}
		
		mErrorCode = Integer.valueOf(error);
		mErrorTip = xmlMap.get("detail");
		
		return true;
	}
	
	public static Map<String, String> getXmlAttributes(XmlPullParser parser) {
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < parser.getAttributeCount(); i++) {
			map.put(parser.getAttributeName(i), parser.getAttributeValue(i));
		}
		return map;
	}
	
	public int parseData(final InputStream dateInput){
		if(dateInput == null){
			return ErrorCode.ERROR_FAIL;
		}
		
		if(getExpectPageType() == null){
			throw new RuntimeException("mExpectPageType is not initialized, you should init it first");
		}
		
		boolean parseRessult = parseXml(dateInput);
		
		//-6表示服务器返回的sid过期
		if (mErrorCode == -6) {
			return ErrorCode.ERROR_SID_EXPIRED;
		} else {
			return parseRessult ? ErrorCode.ERROR_SUCCESS : ErrorCode.ERROR_FAIL;
		}
	}
	
	/**
	 * 子类需要重载实现该函数
	 * */
	public abstract boolean parseXml(final InputStream dateInput);
	
	protected static int parseInt(String text, int defVal){
		if(text == null){
			return defVal;
		}
		int val = defVal;
		try{
			val = Integer.parseInt(text);
		}catch (NumberFormatException e) {
			// nothing to do
		}
		return val;
	}
	
	protected static float parseFloat(String text, float defVal){
		if(text == null){
			return defVal;
		}
		float val = defVal;
		try{
			val = Float.parseFloat(text);
		}catch (NumberFormatException e) {
			// nothing to do
		}
		return val;
	}
}
