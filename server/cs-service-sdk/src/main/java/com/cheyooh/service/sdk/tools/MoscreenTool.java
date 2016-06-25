package com.cheyooh.service.sdk.tools;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 移动动漫通道（魔屏）工具类
 * @author ljg
 *
 */

public class MoscreenTool {

	private String appKey;
	private String appSecret;
	public MoscreenTool(String appKey,String appSecret){
		this.appKey=appKey;
		this.appSecret=appSecret;
	}
	
	/**
	 * 通用的url生成方法
	 * 如果下面的方法不能满足，可以使用此方法进行生成
	 * @param url
	 * @param params
	 * @return
	 */
	public String buildUrlWithSign(String url,Map<String, String> params){
		Map<String, String> newparams=new HashMap<String, String>(params);
		newparams.put("appKey", appKey);
		newparams.put("appSecret", appSecret);
		if(newparams.get("timestamp")==null){
			newparams.put("timestamp", System.currentTimeMillis()+"");
		}
		String sign=SignTool.sign(newparams);
		newparams.put("sign", sign);
		
		newparams.remove("appSecret");
		
		return assembleUrl(url, newparams);
	}
	
	
	public static String assembleUrl(String url,Map<String, String> params){
		if(!url.endsWith("?")){
			url+="?";
		}
		for(String key:params.keySet()){
			try {
				if(params.get(key)==null || params.get(key).length()==0){
					url+=key+"="+params.get(key)+"&";
				}else{
					url+=key+"="+URLEncoder.encode(params.get(key), "utf-8")+"&";
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return url;
	}
}
