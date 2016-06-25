package com.cheyooh.service.sdk.tools;


public class StringTool {
	
	
	public static String max(String s,int length){		
		if(s==null || length<0){
			return null;
		}
		
		if(s.length()<=length){
			return s;					
		}else{
			if(length>4){
				return s.substring(0,length-4)+" ...";
			}else{
				return s.substring(0,length);
			}
		}
	}
	

	/**
	 * @param exp 时间(单位: s-秒, m-分, h-时, d-天, 默认值:d)
	 * @return 毫秒值
	 */
	public static long getTimeMillis(String exp) {
		long le = 0;
		char c = exp.charAt(exp.length() - 1);
		switch (c) {
		case 's':
			le = 1000 * Long.parseLong(exp.substring(0, exp.length() - 1));
			break;
		case 'm':
			le = 60 * 1000 * Long.parseLong(exp.substring(0, exp.length() - 1));
			break;
		case 'h':
			le = 60 * 60 * 1000 * Long.parseLong(exp.substring(0, exp.length() - 1));
			break;
		case 'd':
			le = 24 * 60 * 60 * 1000 * Long.parseLong(exp.substring(0, exp.length() - 1));
			break;
		default: // 默认过期时间的单位为天
			le = 24 * 60 * 60 * 1000 * Long.parseLong(exp);
			break;
		}

		return le;
	}
	
	public static void main(String[] args) {
		
	}
}
