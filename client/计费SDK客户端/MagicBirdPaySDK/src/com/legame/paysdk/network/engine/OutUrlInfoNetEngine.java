package com.legame.paysdk.network.engine;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;

import com.legame.paysdk.network.resultdata.OutUrlInfoResultData;
import com.legame.paysdk.network.utils.NetTools;

/** 
 * 类说明：   
 * @author  Terry.Lu
 * @date    2013年10月15日
 * @version 1.0
 */
public class OutUrlInfoNetEngine extends BaseNetEngine {
	private static final String METHOD = "get_domainlist_forcp";
	
	public OutUrlInfoNetEngine() {

		mResultData = new OutUrlInfoResultData(METHOD);
	}

	@Override
	protected String getCommand() {
		return METHOD;
	}

	@Override
	protected Map<String, String> getParams(Context c) {
		// TODO Auto-generated method stub
		Map<String, String> params = new HashMap<String, String>();
		params.put("version_code", String.valueOf(NetTools.getVersionCode(c)));
		return params;
	}
}
