package com.cheyooh.service.framework.cfg;

import com.cheyooh.tools.cfg.CfgData;

public class Cfg {
	/**
	 * 消息的属性命名规则:
	 * 
	 * 空字段提示语:  m+"."+字段名+".null"=错误提示语句
	 * 字段格式错误:  m+"."+字段名+".notmatch"=错误提示语句
	 */
	public static CfgData msg;
}
