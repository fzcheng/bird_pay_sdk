package com.cheyooh.tools.utils;

import java.net.URLEncoder;

public class XUtils {
	public static String encodeUrl(String v) {
		if (v != null) {
			try {
				return URLEncoder.encode(v, "utf-8");
			} catch (Exception e) {
				return v;
			}
		} else {
			return "";
		}
	}
	
	public static Class<?> forName(String name) {
		try {
			return Class.forName(name);
		} catch (ClassNotFoundException e) {
			try{
				return Class.forName(name, true,Thread.currentThread().getContextClassLoader());
			}catch(ClassNotFoundException e2){
				return null;
			}
		}
		 
	}
	

	public static String escapeSqlValue(String v){
		if(v==null){
			return null;
		}
		
		StringBuffer r=new StringBuffer();
		r.append("'");
		for(int i=0;i<v.length();i++){
			char c=v.charAt(i);
			switch(c){
				case '\'': r.append("''");   break;
				case '"' : r.append("\\\""); break;
				case '\\': r.append("\\\\"); break;
				default  : r.append(c);
			}
		}
		r.append("'");
		return r.toString();		 
	}

	private static final String LT = "&lt;";
	private static final String GT = "&gt;";
	private static final String AMP = "&amp;";
	private static final String QUOT = "&quot;";
	private static final String APOS = "&apos;";

	
	public static String escapeXml(String s) {
		if (s == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case '<':
				sb.append(LT);
				break;
			case '>':
				sb.append(GT);
				break;
			case '&':
				sb.append(AMP);
				break;
			case '"':
				sb.append(QUOT);
				break;
			case '\'':
				sb.append(APOS);
				break;
			default:
				sb.append(c);
			}

		}
		return sb.toString();
	}

	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception e) {
		}
	}
}
