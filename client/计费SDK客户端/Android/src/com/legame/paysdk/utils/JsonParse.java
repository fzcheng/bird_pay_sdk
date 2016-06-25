package com.legame.paysdk.utils;


import java.util.Map;

import com.google.gson.Gson;


/** 
 * 类说明：   
 * @author  xinhui.cheng
 * @date    2013-7-31
 * @version 1.0
 */
public class JsonParse {

	private static JsonParse instance;
	
	private JsonParse(){
		
	}
	
	public static JsonParse getJsonParse(){
		if(instance == null){
			instance = new JsonParse();
		}
		return instance;
	}
	
	/**
	 * 根据给定的json字符串解析成给定的class
	 * @param json	json字符串
	 * @param classOfT 要返回的类
	 * @return
	 */
	public synchronized <T> T parseJson(String json, Class<T> classOfT) throws Exception{
		
		T target = null;
		Gson gson = new Gson();
		target = gson.fromJson(json, classOfT);
		return target;
	}
	/**
	 * 将一个Map对象转换成Json字符串
	 * @param object
	 * @return
	 */
	public String mapToJson(Map<String,Object> object){
		String jsonStr = "";
		Gson gson = new Gson();
		jsonStr = gson.toJson(object);
		return jsonStr;
	}
	
	
}
