package com.cheyooh.service.framework.idata;


public class ResultXJContent extends ResultContent {
 
	private static final long serialVersionUID = 7127838020931015335L;
	
	private String xml;
	private String json;
	 
	public ResultXJContent(String xml,String json){
		this.xml=xml;
		this.json=json;
	} 
		
	public String getXml(){
		return xml;
	}
	
	public String getJson(){
		return json;
	}
	 
	
}
