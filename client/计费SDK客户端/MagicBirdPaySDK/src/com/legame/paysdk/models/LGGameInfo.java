package com.legame.paysdk.models;

import java.io.Serializable;
import java.util.ArrayList;

/** 
 * 类说明：   
 * @author  Baker.li
 * @date    2013年7月10日
 * @version 1.0
 */

public class LGGameInfo implements Serializable {
	
	private static final long serialVersionUID = 7219723561651035976L;
	
	private String name;
	private String category;
	private String iconUrl;
	private String packageName;
	private float rating;
	private String introImg;
	private String PicUrl;
	private String fileSize;
	private String possibility = "100";
	private int appStatus = 0; //0:未下载 1：下载中 2，下载完成 3.已安装
	private String dl_url;    //下载地址
	
	public static final int APP_STATUS_DOWNLOAD = 0X00;
	public static final int APP_STATUS_DOWNLOADING = 0X01;
	public static final int APP_STATUS_DOWNLOADED = 0X02;
	public static final int APP_STATUS_INSTALLED = 0X03;
	/**描述*/
	private String desc;
	/**预览图片，最多3个，Pair[0]保存图片尺寸,Pair[1]保存图片URL*/
	private ArrayList<ImageInfo> previews;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public String getIntroImg() {
		return introImg;
	}
	public void setIntroImg(String introImg) {
		this.introImg = introImg;
	}
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public ArrayList<ImageInfo> getPreviews() {
		return previews;
	}
	public void setPreviews(ArrayList<ImageInfo> previews) {
		this.previews = previews;
	}
	public String getPossibility() {
		return possibility;
	}
	public void setPossibility(String possibility) {
		this.possibility = possibility;
	}
	public int getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(int appStatus) {
		this.appStatus = appStatus;
	}
	public String getDl_url() {
		return dl_url;
	}
	public void setDl_url(String dl_url) {
		this.dl_url = dl_url;
	}
}
