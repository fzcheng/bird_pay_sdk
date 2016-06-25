package com.cheyooh.service.sdk.idata;

import com.cheyooh.service.framework.idata.ResultContent;

public class ResultPayMmdoBlock extends ResultContent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2685107170899191620L;
	
	private String number;
	
	private String keyword;
	
	public ResultPayMmdoBlock(){
		super("block");
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	

}
