package com.cheyooh.service.sdk.cfg;

import com.cheyooh.tools.cfg.CfgData;
import com.cheyooh.tools.cfg.CfgUtil;

/**
 * @author zzg.zhou@mbook.com.cn {MSN:zzgzhou@hotmail.com, QQ:11039850}
 *  
 */
public class Cfg {
	public static CfgData cfg=CfgUtil.getCfg(Cfg.class,"service-sdk.cfg");
	
	public static CfgData msg=CfgUtil.getCfg(Cfg.class,"service-sdk.msg");

}
