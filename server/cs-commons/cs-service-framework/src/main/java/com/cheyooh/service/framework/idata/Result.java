package com.cheyooh.service.framework.idata;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.cheyooh.tools.log.Logger;
import com.cheyooh.tools.utils.XUtils;

public class Result implements Serializable{
	private static final long serialVersionUID = -8561849026748417299L;
	private static Logger logger=new Logger(Result.class);		 
	public static String TAG_MESSAGE="detail";
	public static String TAG_RESULT="info";
	public static String TAG_SUBSTATUS="substatus";
	public static String TAG_STATUS="error";
	public static String TAG_TYPE="type";
	public static String TAG_TIMESPEND="timeservice";
	public static String TAG_TIMEAPI="timeapi";
	
	public static boolean IS_INCLUDE_SUBCODE=true;	
	public static boolean IS_INCLUEDE_TIMESERVICE=false;
	public static boolean IS_INCLUEDE_TIMEAPI=true;
	
	private String fmt;	
	private String type;		 
	private int status=0;
	private int subStatus=0;
	private String message="OK";
	private ResultContent content;
	private boolean finalStatus=false; 
	
	/**请求结果计时值，以ms为单位*/
	private long timeService = 0 ;
	private long timeApi=0;
	public long getTimeService() {
		return timeService;
	}

	public void setTimeService(long timeService) {
		this.timeService = timeService;
	}

	public long getTimeApi() {
		return timeApi;
	}

	public void setTimeApi(long timeApi) {
		this.timeApi = timeApi;
	}

	/**
	 * 扩展属性
	 */
	private Map<String,String> attribute=new HashMap<String,String>();
	
	public Result(){}
	
	public Result(ResultContent content){
		this.content=content;
		
		if(content instanceof ResultXJContent){
			String xml=((ResultXJContent)content).getXml();
			if(xml!=null && xml.startsWith("<")==false){
				this.message=xml;
			}
		}
	}
	
	public Result(int status,String message){
		this.status=status;
		this.message=message;
	}
	
	public Result(int status,int subStatus,String message){
		this.status=status;
		this.subStatus=subStatus;
		this.message=message;
	}
	
	public Result newResult(int subCode,String message){
		Result r=new Result();
		r.setStatus(getStatus());
		r.setSubStatus(subCode);
		r.setType(getType());		
		r.setMessage(message);
		return r;
	}
	public Result newResult(String message){
		Result r=new Result();
		r.setStatus(getStatus());
		r.setType(getType());
		r.setSubStatus(getSubStatus());
		r.setMessage(message);
		r.attribute=attribute;
		return r;
	}
	
	public Result setContent(ResultContent c){
		this.content=c;
		return this;
	}
	
	public ResultContent getContent(){
		return this.content;
	}
	
	public String getMessage(){
		return message;
	}
	
	public Result setMessage(String message){
		this.message=message;
		return this;
	}
	
	public int getStatus(){
		return status;
	}
	
	public Result setStatus(int status){
		if(finalStatus){
			throw new RuntimeException("Final status error!");
		}else{
			this.status=status;
			return this;
		}
	}
  
	public Result addAttribute(String name,String value){
		if(value!=null){
			attribute.put(name, value);
		}
		return this;
	}
	
	public Result clearAttribute(){
		attribute.clear();
		return this;
	}
	
	public String getResult(String fmt,String type){
		if("json".equalsIgnoreCase(fmt)){
			return getJson(type);
		}else{
			return getXml(type);
		}
	}
	
	public String getJson(String type){
		if(content instanceof ResultXJContent){
			return ((ResultXJContent)content).getJson();
		}
		
		try{
			JSONObject json=new JSONObject();
			json.put(TAG_TYPE,type);
			json.put(TAG_STATUS, status);
			if(IS_INCLUDE_SUBCODE){
				json.put(TAG_SUBSTATUS,subStatus);
			}
			json.put(TAG_MESSAGE,message);
			
			if(IS_INCLUEDE_TIMEAPI && getTimeApi() > 0){
				json.put(TAG_TIMEAPI, getTimeApi() +"ms");
			}
			
			if(IS_INCLUEDE_TIMESERVICE && getTimeService() > 0){
				json.put(TAG_TIMESPEND, getTimeService() +"ms");
			}
			
			if(attribute!=null && attribute.size()>0){
				for(String key:attribute.keySet()){
					json.put(key, attribute.get(key));
				}
			}
			
			if(content!=null){
				json.put(content.name(),content.toJson());
			}
			return json.toString();
		}catch(Exception e){
			logger.error(e);
			return "{"+TAG_STATUS+":-3, "+TAG_MESSAGE+":\"JSON转换错误.\"}";
		}
	}
		 
	public String getXml(String type){
		if(content instanceof ResultXJContent){
			return ((ResultXJContent)content).getXml();
		}
		
		
		StringBuilder sb=new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("\r\n\r\n");
		
		if(IS_INCLUEDE_TIMEAPI && getTimeApi() > 0){
			sb.append("<!-- "+ TAG_TIMEAPI +" : "+getTimeApi()+"ms --> ");
			sb.append("\r\n\r\n");
		}
		
		sb.append("<"+TAG_RESULT);
		sb.append(" ").append(TAG_TYPE).append("=\"").append(type).append("\"");
		sb.append(" ").append(TAG_STATUS).append("=\"").append(status).append("\"");
		
		if(IS_INCLUDE_SUBCODE){
			sb.append(" ").append(TAG_SUBSTATUS).append("=\"").append(subStatus).append("\"");
		}
		
		if(IS_INCLUEDE_TIMESERVICE ){
			sb.append(" ").append(TAG_TIMESPEND).append("=\"").append(getTimeService()+"ms").append("\"");
		}
		
		sb.append(" ").append(TAG_MESSAGE).append("=\"").append(message==null?"":XUtils.escapeXml(message)).append("\"");
		
		if(attribute!=null && attribute.size()>0){
			for(String key:attribute.keySet()){
				sb.append(" ").append(key).append("=\"").append( XUtils.escapeXml(attribute.get(key)) ).append("\"");
			}
		}
		sb.append(">\r\n\r\n");
		
		if(content!=null){
			sb.append(content.getXml());
		}
		 
		sb.append("\r\n</"+TAG_RESULT+">");
		return sb.toString();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;		
	}
	
	public String toString(){
		StringBuilder sb=new StringBuilder();
		sb.append(""+status+" ["+type+"] "+message);
		return sb.toString();
	}

	public String getFmt() {
		return fmt;
	}

	public void setFmt(String fmt) {
		this.fmt = fmt;		
	}

	public int getSubStatus() {
		return subStatus;
	}

	public void setSubStatus(int subStatus) {
		if(finalStatus){
			throw new RuntimeException("Final status error!");
		}else{
			this.subStatus = subStatus;
		}
	}

	public boolean isFinalStatus() {
		return finalStatus;
	}

	public Result setFinalStatus(boolean finalStatus) {
		this.finalStatus = finalStatus;
		return this;
	}
	 
}
