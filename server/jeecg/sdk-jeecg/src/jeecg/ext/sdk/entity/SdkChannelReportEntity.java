package jeecg.ext.sdk.entity;

import java.awt.print.Printable;
import java.sql.Timestamp;



public class SdkChannelReportEntity {

	private Integer id;
	private  String channelPartnerName;
	private String gameName;
	private String channelName;
	private Integer income;
	private Integer regUserNum;
	private Integer rechargeNum;
	private Timestamp creatTime;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getChannelPartnerName() {
		return channelPartnerName;
	}
	public void setChannelPartnerName(String channelPartnerName) {
		this.channelPartnerName = channelPartnerName;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public Integer getIncome() {
		return income;
	}
	public void setIncome(Integer income) {
		this.income = income;
	}
	public Integer getRegUserNum() {
		return regUserNum;
	}
	public void setRegUserNum(Integer regUserNum) {
		this.regUserNum = regUserNum;
	}
	public Integer getRechargeNum() {
		return rechargeNum;
	}
	public void setRechargeNum(Integer rechargeNum) {
		this.rechargeNum = rechargeNum;
	}
	public Timestamp getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Timestamp creatTime) {
		this.creatTime = creatTime;
	}
}
