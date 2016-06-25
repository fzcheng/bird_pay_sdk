package com.legame.paysdk.network.engine;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;

import com.legame.paysdk.GlobalVal;
import com.legame.paysdk.network.engine.BaseNetEngine;
import com.legame.paysdk.network.resultdata.VirtualCodeResultData;
/**
 * 
 * @author Kaiguang
 * @date 2016.3.7
 * @类说明 上传虚拟码到后台
 *
 */
public class VirtualCodeNetEngine extends BaseNetEngine{
	
	private static final String METHOD = "Pay_zwjfVirtualcode";
	
	private String sid;
	private String virtualCode;
	private String orderId;
	
	
	public VirtualCodeNetEngine(String sid,String virtualCode,String orderId){
		mHttpMethod = HTTP_POST;
		this.sid = sid;
		this.virtualCode = virtualCode;
		this.orderId = orderId;
		mResultData = new VirtualCodeResultData(METHOD);
	}
	
	@Override
	protected String getCommand() {
		return METHOD;
	}

	@Override
	protected Map<String, String> getParams(Context c) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("sid", sid);
		params.put("order_no", orderId);
		params.put("virtualcode", virtualCode);
		return params;
	}

}
