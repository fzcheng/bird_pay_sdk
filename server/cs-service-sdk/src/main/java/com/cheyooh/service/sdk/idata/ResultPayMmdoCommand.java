package com.cheyooh.service.sdk.idata;

import com.cheyooh.service.framework.idata.ResultContentList;

public class ResultPayMmdoCommand extends ResultContentList{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6797664778309803663L;

	
	private String number;
	
	private String content;
	
	private String wapurl="";
	
	private String zwjf="";
	
	public ResultPayMmdoCommand(){
		setTagName("command");
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWapurl() {
		return wapurl;
	}

	public void setWapurl(String wapurl) {
		this.wapurl = wapurl;
	}

	public String getZwjf() {
		return zwjf;
	}

	public void setZwjf(String zwjf) {
		this.zwjf = zwjf;
	}
	
	
}
