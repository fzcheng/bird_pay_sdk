
package com.legame.paysdk.models;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class OutUrlInfoParse {

	
	//进行解析
	//解析单个
	public static OutUrlInfo parse(String strJson) throws Exception{
		//解析了 1 个属性
		OutUrlInfo temp = new OutUrlInfo();
		JSONObject jsonObject = new JSONObject(strJson);
		
		if(!jsonObject.isNull("domain")){
			temp.setDomain(jsonObject.getString("domain"));
		}
		if(!jsonObject.isNull("gameid")){
			temp.setGameid(jsonObject.getInt("gameid"));
		}
//		System.out.println("temp:"+temp);
		return temp;
	}
	//解析列表
	public static List<OutUrlInfo> parseArray(String strJson) throws Exception{
		List<OutUrlInfo> listM = new ArrayList<OutUrlInfo>();
//		System.out.println("1"+strJson);
		JSONArray listData = new JSONArray(strJson);
//		System.out.println("2");
		for(int i = 0; i < listData.length(); i++){
			listM.add(parse(listData.getJSONObject(i).toString()));
		}
		return listM;
	}
}
