package com.legame.paysdk.models;

import java.io.Serializable;

public class ImageInfo implements Serializable {
	private static final long serialVersionUID = 7380478006317096562L;
	private String intro;
	private String url;

	public static ImageInfo create(String intro, String url) {
		ImageInfo img = new ImageInfo();
		img.setIntro(intro);
		img.setUrl(url);
		return img;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
