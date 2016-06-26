package com.legame.paysdk.models;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

/**
 * 类说明：
 * 
 * @author xinhui.cheng
 * @date 2013-7-31
 * @version 1.0
 */
public class PushModel implements Serializable{

	private static final long serialVersionUID = -80926799077602470L;
	public static final int TYPE_TEXT_MSG = 1;
	//public static final int TYPE_PHONE_MSG = 2;
	public static final int TYPE_URL_MSG = 2;
	@SerializedName("title")
	private String title;
	@SerializedName("message")
	private String message;
	@SerializedName("contentType")
	private int contentType;
	@SerializedName("extras")
	private PushExtrasModel extras;
	private boolean isRead;

	private String url;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getContentType() {
		return contentType;
	}

	public void setContentType(int contentType) {
		this.contentType = contentType;
	}

	public PushExtrasModel getExtras() {
		return extras;
	}

	public void setExtras(PushExtrasModel extras) {
		this.extras = extras;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

}
