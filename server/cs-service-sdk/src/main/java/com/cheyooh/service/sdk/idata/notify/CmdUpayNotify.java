package com.cheyooh.service.sdk.idata.notify;

import com.cheyooh.service.framework.idata.Cmd;

public class CmdUpayNotify  extends Cmd{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3807435587638161451L;

	/**
	 * 交易序号
	 */
	private String tradeId;
	/**
	 * 账单点数
	 */
	private Integer point;
	/**
	 * 请求成功点数
	 */
	private Integer amount;
	/**
	 * 开发商自订参数
	 */
	private String extraInfo;
	/**
	 * 时间戳
	 */
	private String ts;
	/**
	 * 验证值
	 */
	private String hash;
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getExtraInfo() {
		return extraInfo;
	}
	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}
	public String getTs() {
		return ts;
	}
	public void setTs(String ts) {
		this.ts = ts;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	
	
}
