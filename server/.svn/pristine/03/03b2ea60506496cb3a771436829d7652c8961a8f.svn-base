package jeecg.ext.online.service;

import jeecg.system.pojo.base.TSUser;

import org.jeecgframework.core.util.ResourceUtil;

public class DataPrevilegeUtil {
	
	static public String replaceSQL(String sql) {
		TSUser tsUser = ResourceUtil.getSessionUserName();
		String uid = tsUser.getId();
		//return sql.replaceAll("${uid}", uid);
		return sql;
	}
}
