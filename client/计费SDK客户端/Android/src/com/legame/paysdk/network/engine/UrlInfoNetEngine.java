package com.legame.paysdk.network.engine;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;

import com.legame.paysdk.network.resultdata.UrlInfoResultData;
import com.legame.paysdk.network.utils.NetTools;

/** 
 * 类说明：   
 * @author  Terry.Lu
 * @date    2013年10月15日
 * @version 1.0
 */
public class UrlInfoNetEngine extends BaseNetEngine {
	private static final String METHOD = "get_domainlist";
	
	public UrlInfoNetEngine() {

		mResultData = new UrlInfoResultData(METHOD);
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
