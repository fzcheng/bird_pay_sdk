package com.legame.paysdk.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** 
 * 类说明：   
 * @author  huangliang
 * @date    2014-6-10
 * @version 1.0
 */
public class Command implements Serializable{

	private static final long serialVersionUID = -3836555321946226132L;
	
	private String mNumber;
	private String mContent;
	public ArrayList<Block> blockList;
	private String wapUrl;//wap方式付费所需要而下发的URL
	public String getmNumber() {
		return mNumber;
	}
	public void setmNumber(String mNumber) {
		this.mNumber = mNumber;
	}
	public String getmContent() {
		return mContent;
	}
	public void setmContent(String mContent) {
		this.mContent = mContent;
	}
	public String getWapUrl() {
		return wapUrl;
	}
	public void setWapUrl(String wapUrl) {
		this.wapUrl = wapUrl;
	}
}
