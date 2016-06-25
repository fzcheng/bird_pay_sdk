package com.cheyooh.service.sdk.idata;

import com.cheyooh.service.framework.idata.ResultContentList;

public class ResultGroup extends ResultContentList{
 
	private static final long serialVersionUID = -8657181942612040220L;

	private String name;
		 
	public ResultGroup(String name){
		this.setTagName("group");
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
