package com.legame.paysdk.network.engine;

import java.util.HashMap;
import java.util.Map;

import com.legame.paysdk.network.resultdata.BaseResultData;
import com.legame.paysdk.network.resultdata.GameBaseNewResultData;
import com.legame.paysdk.network.utils.NetTools;

import android.content.Context;

/**
 * 说明:游戏基地二次访问服务器
 * @author KaiGuang
 * @date 2015.04.03
 *
 */
public class GameBaseNewEngine extends BaseNetEngine{
	
	private String sid;
	private String amount;
	private String cpext;
	private String imsi;
	private String imei;
	
	
	private static final String CMD = "Pay_yxjdnsms";
	
	public GameBaseNewEngine(){
		mHttpMethod = HTTP_POST;
		mResultData = new GameBaseNewResultData(CMD);
	}
	
	public void setData(Context context,String amount,String cpext,String imsi){
		this.sid = NetTools.getSid(context);
		this.amount = amount;
		this.cpext = cpext;
		this.imsi = imsi;
	}
	
	@Override
	protected String getCommand() {
		// TODO Auto-generated method stub
		return CMD;
	}

	@Override
	protected Map<String, String> getParams(Context context) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("sid", sid);
		map.put("amount", amount);
		map.put("cp_ext", cpext);
		map.put("imsi", imsi);
		
		imei = NetTools.getIMEI(context);
		if(imei == null){
			imei = NetTools.getLocalMacAddress(context);
		}
		map.put("imei", imei);
		return map;
	}

}
