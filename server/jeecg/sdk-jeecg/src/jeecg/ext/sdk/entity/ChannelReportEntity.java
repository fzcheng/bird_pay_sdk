package jeecg.ext.sdk.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.jeecgframework.core.annotation.Excel;
import org.jeecgframework.core.util.oConvertUtils;

import jeecg.system.service.SystemService;

public class ChannelReportEntity {

	@Excel(exportName="日期", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	private String  statDay;
	@Excel(exportName="渠道名称", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	private String channel;
	@Excel(exportName="游戏名称", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	private String gameId;
	@Excel(exportName="收入", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	private Float income;
	@Excel(exportName="充值人数", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	private Integer rechargeNum;
	@Excel(exportName="注册人数", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	private Integer regNum;
	@Excel(exportName="渠道商ID", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	private Integer channelPartnetId;
	@Excel(exportName="渠道商名称", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	private String channelPartnerName; 

	
	public static List<ChannelReportEntity> getChannelReportEntityList(SystemService systemService,List list) {
		
		for(Object reportEntity:list){
			 ChannelReportEntity reportEntitytmp=(ChannelReportEntity)reportEntity;
			 SdkGame sdkGame=systemService.getEntity(SdkGame.class, oConvertUtils.getInt(reportEntitytmp.getGameId()));
			 if(sdkGame!=null){
				 reportEntitytmp.setGameId(sdkGame.getName());
			 }
		}
		return list;
	}
	
	public Integer getChannelPartnetId() {
		return channelPartnetId;
	}
	public void setChannelPartnetId(Integer channelPartnetId) {
		this.channelPartnetId = channelPartnetId;
	}
	public String getChannelPartnerName() {
		return channelPartnerName;
	}
	public void setChannelPartnerName(String channelPartnerName) {
		this.channelPartnerName = channelPartnerName;
	}
	public String getStatDay() {
		return statDay;
	}
	public void setStatDay(String statDay) {
		this.statDay = statDay;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	public Float getIncome() {
		return income;
	}
	public void setIncome(Float income) {
		this.income = income;
	}
	public Integer getRechargeNum() {
		return rechargeNum;
	}
	public void setRechargeNum(Integer rechargeNum) {
		this.rechargeNum = rechargeNum;
	}
	public Integer getRegNum() {
		return regNum;
	}
	public void setRegNum(Integer regNum) {
		this.regNum = regNum;
	}
}
