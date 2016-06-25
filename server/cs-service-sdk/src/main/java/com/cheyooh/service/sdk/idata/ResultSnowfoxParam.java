package com.cheyooh.service.sdk.idata;

import com.cheyooh.service.framework.idata.ResultContent;


public class ResultSnowfoxParam extends ResultContent {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8527126592167580646L;
	String orderNo;
	String itemId;
	String name;
	String nameDesc;
	String price;
	String priceDesc;
	
	public ResultSnowfoxParam() {
		setTagName("snowfox");
	}

	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameDesc() {
		return nameDesc;
	}
	public void setNameDesc(String nameDesc) {
		this.nameDesc = nameDesc;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPriceDesc() {
		return priceDesc;
	}
	public void setPriceDesc(String priceDesc) {
		this.priceDesc = priceDesc;
	}
	
	
	
}
