package com.legame.paysdk.network.engine;

import java.util.HashMap;
import java.util.Map;

import com.legame.paysdk.network.resultdata.InitialParamsResultData;

import android.content.Context;

/**
 * 
 * @author Kaiguang
 * @date 2016.3.21
 * @类说明 初始化获取相关参数接口
 *
 */
public class GetInitialParamEngine extends BaseNetEngine{
	
	private static final String METHOD = "Get_initial_param";
	
	public GetInitialParamEngine(){
		mHttpMethod = HTTP_POST;
		mResultData = new InitialParamsResultData(METHOD);
	}

	@Override
	protected Map<String, String> getParams(Context c) {
		return new HashMap<String, String>();
	}
	
	@Override
	protected String getCommand() {
		return METHOD;
	}

}
