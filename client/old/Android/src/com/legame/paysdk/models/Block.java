package com.legame.paysdk.models;

import java.io.Serializable;

/**
 * 类说明：
 * 
 * @author huangliang
 * @date 2014-6-10
 * @version 1.0
 */
public class Block implements Serializable {
	private static final long serialVersionUID = -8806109482882218805L;

	private int id;
	private String mNumber;
	private String mKeyWord;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getmNumber() {
		return mNumber;
	}

	public void setmNumber(String mNumber) {
		this.mNumber = mNumber;
	}

	public String getmKeyWord() {
		return mKeyWord;
	}

	public void setmKeyWord(String mKeyWord) {
		this.mKeyWord = mKeyWord;
	}

}
