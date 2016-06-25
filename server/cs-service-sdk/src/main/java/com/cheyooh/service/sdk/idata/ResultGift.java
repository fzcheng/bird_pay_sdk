package com.cheyooh.service.sdk.idata;

import com.cheyooh.service.framework.idata.ResultContent;

public class ResultGift extends ResultContent{
 
	private static final long serialVersionUID = -5249283847931090076L;

	public ResultGift(){
		super("gift");
	}
	
	/**
	 * 礼包id
	 */
	private int id;
	
	private String icon_url;
	
	/**
	 * 礼包名称
	 */
	private String name;
	
	/**
	 * 百分比的数字，0~100, 0-表示抢光了
	 */
	private String remain;
		
	/**
	 * 过期时间
	 */
	private String expiration;
	
	/**
	 * 是否已领取. 1-已领取, 0-未领取
	 */
	private int	accept;
	
	/**
	 * 礼包详细内容
	 */
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIcon_url() {
		return icon_url;
	}

	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemain() {
		return remain;
	}

	public void setRemain(String remain) {
		this.remain = remain;
	}

	public String getExpiration() {
		return expiration;
	}

	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}

	public int getAccept() {
		return accept;
	}

	public void setAccept(int accept) {
		this.accept = accept;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
