package com.legame.paysdk.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 类说明：
 * 
 * @author huangliang
 * @date 2014-6-10
 * @version 1.0
 */
public class Commands implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5906364879100341444L;

	private String mIMSI;
	private String mTime;

	private String mStatus;//0：等待短信；1：获取短信成功；2：获取短信失败
	private String flagSend;//发送短信的条件。01:发送短信，10:不发送短信接收验证码，11:发送短信接收验证码。
							//10和11是以接收验证码为回调。01只是发送短信而没下发验证码
	public ArrayList<Command> mCommandList;
	
	public String getmIMSI() {
		return mIMSI;
	}

	public void setmIMSI(String mIMSI) {
		this.mIMSI = mIMSI;
	}

	public String getmTime() {
		return mTime;
	}

	public void setmTime(String mTime) {
		this.mTime = mTime;
	}
	public String getmStatus() {
		return mStatus;
	}

	public void setmStatus(String mStatus) {
		this.mStatus = mStatus;
	}

	public String getFlagSend() {
		return flagSend;
	}

	public void setFlagSend(String flagSend) {
		this.flagSend = flagSend;
	}
}
