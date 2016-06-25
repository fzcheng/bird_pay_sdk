package com.cheyooh.service.sdk.idata;

import com.cheyooh.service.framework.idata.ResultContent;

public class ResultInformation extends ResultContent{
 
	private static final long serialVersionUID = 6305010912056401673L;

	public ResultInformation(){
		super("info");
	}
	
	/**
	 * 标题
	 */
	private String title;
			
	/**
	 * 简介
	 */
	private String summary;
	
	/**
	 * 资讯类型
	 */
	private String type;
			 
	/**
	 * 资讯链接
	 */
	private String url;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
			 
}
