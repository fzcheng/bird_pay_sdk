package com.cheyooh.service.sdk.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class GenerateTool {
	/**
	 * 服务器的唯一编号(1~99), 默认值为: 1
	 */
	public final static int WEB_SERVER_ID;
	  
	static{
		String sid=System.getProperty("WEB_SERVER_ID");		
		WEB_SERVER_ID=sid==null?1:Integer.parseInt(sid.trim());	
		
		if(WEB_SERVER_ID>=100){
			throw new RuntimeException("Invalid system property WEB_SERVER_ID: "+sid);
		}
	}	
	 
	/**
	 * @return  产生16位数字的订单号
	 */
	public static String createOrderNo(){
		Date time=new Date();
		StringBuilder sb=new StringBuilder();
		SimpleDateFormat sdf=new SimpleDateFormat("yyMMddHHmmss");
		sb.append(sdf.format(time));
		if(WEB_SERVER_ID<10){
			sb.append("0");
		}
		sb.append(WEB_SERVER_ID);
		
		int l=getLoop();
		if(l<10){
			sb.append("0");
		}
		sb.append(l);
		
		return sb.toString();
	}
	
	public static String createPassword(){
		Random random=new Random();
		int r=1000000+random.nextInt(1000000);
		String password=(""+r).substring(1);
		return password;
	}
	
	
	/**
	 * @param length
	 *            随机生成字符串的长度
	 * @return
	 */
	public static String getRandomString(int length) {
		String base = "0123456789"; // 生成字符串从此序列中取
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	
	/**
	 * @return 产生一个唯一标识串
	 */
	public static String createUuid(){
		String auid = UUID.randomUUID().toString();
		auid = auid.replaceAll("-","");
		return auid.toLowerCase();
	}
	
	private static int loop=0;
	private static long loopSec=System.currentTimeMillis()/1000;
	private synchronized static int getLoop(){
		long time=System.currentTimeMillis()/1000;		
		if( loopSec != time){
			loopSec= time;
			loop=0;
		}
		
		loop++;
		if(loop>=100){
			throw new RuntimeException("Too many order no!");
		}else{		
			return loop;
		}
	}
}
