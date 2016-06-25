package com.cheyooh.tools.http;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzg {MSN:pingzzg@hotmail.com, QQ:11039850}
 *  
 */
public class HttpResult {
	private String requestUrl;
	
	private int status;
	private String body;
	private Map<String,String[]> headers=new HashMap<String,String[]>();
	
	private String cookie;
	
	/**
	 * 响应时间,单位:毫秒
	 */
	private int usetime;
	
	public HttpResult(int status){
		this.status=status;
	}
	public HttpResult(int status,String body){
		this.status=status;
		this.body=body;
	}
	public HttpResult(int status,String body,String requestUrl){
		this.status=status;
		this.body=body;
		this.requestUrl=requestUrl;
	}
	
	public HttpResult setUsetime(int usetime){
		this.usetime=usetime;
		return this;
	}
	
	public int getUsetime(){
		return this.usetime;
	}
	
	public String getHeader(String name){
		String[] vs=headers.get(name);
		if(vs!=null && vs.length>0){
			return vs[0];
		}
		return null;
	}
	public String[] getHeaders(String name){
		String[] vs=headers.get(name);
		 
		return vs;
	}
	public void addHeader(String name,String value){		 		
		String[] vs=headers.get(name);
		if(vs==null){
			vs=new String[]{value};
			
			headers.put(name,vs);
		}else{
			String[] ss=new String[vs.length+1];
			System.arraycopy(vs,0,ss,0,vs.length);
			ss[vs.length]=value;
			
			headers.put(name,ss);
		}				
	}
	
	public boolean isOk(){
		return this.status==200;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getCookie() {
		return cookie;
	}
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	public String getRequestUrl() {
		return requestUrl;
	}
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}
}
