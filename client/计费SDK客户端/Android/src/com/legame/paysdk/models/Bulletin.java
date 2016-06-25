package com.legame.paysdk.models;

import java.io.Serializable;

/** 
 * 类说明：   
 * @author  Terry Lu
 * @date    2013年12月17日
 * @version 1.0
 */
public class Bulletin implements Serializable {
	public static final int BULLETIN_TYPE_WEB_LINK = 1;
	public static final int BULLETIN_TYPE_GAME = 2;
	private static final long serialVersionUID = 1L;
	private String content;
	private int type;
	private String url;
	private LGGameInfo gameInfo;
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
	public LGGameInfo getGameInfo() {
		return gameInfo;
	}
	public void setGameInfo(LGGameInfo gameInfo) {
		this.gameInfo = gameInfo;
	}
}
