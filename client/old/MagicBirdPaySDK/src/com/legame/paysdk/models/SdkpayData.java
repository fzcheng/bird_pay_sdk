package com.legame.paysdk.models;

import java.util.Map;

import com.legame.paysdk.models.Commands;
import com.legame.paysdk.models.OrderInfo;
/**
 * 
 * @author KaiGuang
 * @date 2015.6.30
 * 说明：用于保存SDK多补点计费数据
 *
 */
public class SdkpayData {
	private Map<String,String> dataMap;
	private Commands commands;
	private OrderInfo orderInfo;
	public Map<String, String> getDataMap() {
		return dataMap;
	}
	
	public void setDataMap(Map<String, String> dataMap) {
		this.dataMap = dataMap;
	}
	public Commands getCommands() {
		return commands;
	}
	public void setCommands(Commands commands) {
		this.commands = commands;
	}
	public OrderInfo getOrderInfo() {
		return orderInfo;
	}
	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}
}
