package com.cheyooh.service.sdk.idata;

import com.cheyooh.service.framework.idata.ResultContent;
import com.cheyooh.service.framework.utils.annotation.NullNone;

public class ResultBulletin extends ResultContent{
 
	private static final long serialVersionUID = 1145968729681889395L;

	public ResultBulletin(){
		super("bulletin");
	}
	
	/**
	 * 公告内容
	 */
	private String content;
	
	/**
	 * 公告类型: 0-无,1-网页链接,2-游戏下载
	 */
	private int type;
	         
	/**
	 * web页面链接
	 */
	@NullNone
	private String	url;
	
	private ResultGameInfo game;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ResultGameInfo getGame() {
		return game;
	}

	public void setGame(ResultGameInfo game) {
		this.game = game;
	}
}
