package com.cheyooh.service.framework.utils.binding;

import java.io.File;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.ResultContent;
import com.cheyooh.service.framework.idata.ResultContentList;
import com.cheyooh.service.framework.idata.StatusCode;
import com.cheyooh.tools.cfg.CfgPath;
import com.cheyooh.tools.log.Logger;

public class RequestDataBinding {
	static Logger logger = new Logger(RequestDataBinding.class);

	public static String uploadRepositoryPath=CfgPath.getLogPathRoot()+"/upload/tmp";
	public static String uploadFilePath=CfgPath.getLogPathRoot()+"/upload/files";
	
	public static int    uploadSizeThreshold=256*1024;
	public static String uploadEncoding="utf-8";
	public static int    uploadMaxSize=10*1024*1024;
	
	static{
		try{
			File[] fs=new File[]{
					new File(uploadRepositoryPath),
					new File(uploadFilePath)
			};
			for(File f:fs){
				if(f.exists()==false){
					logger.info("Create upload dir: "+f.getAbsolutePath());
					f.mkdirs();					
				}else{
					logger.info("Exist upload dir: "+f.getAbsolutePath());
				}
			}
		}catch(Exception e){
			logger.error("Init upload dir exception: "+e,e);
		}
	}
	
	public static void fromRequest(HttpServletRequest request, Object cmd) {		
		fromNormalRequest(request,cmd);
		
		if(ServletFileUpload.isMultipartContent(request)){
			fromMutiFileRequest(request,cmd);
		}
		
		ClassHelper.MetaClass mc = ClassHelper.getMetaClass(cmd);
 
		ClassHelper.FGS fgs = mc.getMapppingField("requestHost");
		if (fgs != null) {
			String host=getRequestRealIp(request);
			ClassHelper.setAttribute(cmd, fgs,host);
		} 
	}
	
	private static String getRequestRealIp(HttpServletRequest request){
		String ip=null;		
		String[] ip_headers=new String[]{
				 "x-forwarded-for"
				,"X-Forwarded-For"
				,"HTTP_X_FORWARDED_FOR"
				,"Proxy-Client-IP"
				,"WL-Proxy-Client-IP"
				,"HTTP_CLIENT_IP"};
		for(String ih:ip_headers){
			String ips=request.getHeader(ih);
			
			if(ips!=null && ips.length()>0 && ("unknown".equalsIgnoreCase(ips)==false)){
				String[] vs=ips.split(",");
				for(String v:vs){
					v=v.trim();
					if("unknown".equalsIgnoreCase(v)==false){
						ip=v;
						break;
					}
				}
				
				if(ip!=null){
					break;
				}
			}
		} 
 
		if(ip==null){
			ip=request.getRemoteAddr();
		}
		
		return ip;
	}
	
	private static void fromNormalRequest(HttpServletRequest request, Object cmd) {
		ClassHelper.MetaClass mc = ClassHelper.getMetaClass(cmd);

		Enumeration<String> names = request.getParameterNames();
		String name = null;
		String[] values = null;	 
		
		while (names.hasMoreElements()) {
			name = (String) names.nextElement();
			values = request.getParameterValues(name);

			ClassHelper.FGS fgs = mc.getMapppingField(name);
			if (fgs != null) {
				if(fgs.getField().getType().isArray()){
					ClassHelper.setObject(cmd, fgs, values);
				}else{
					ClassHelper.setAttribute(cmd, fgs, (values==null || values.length<1)?null:values[0]);
				}
			}
		}	 
		 
	}
	
	private static void fromMutiFileRequest(HttpServletRequest request, Object cmd) {
		//DiskFileItem工厂,主要用来设定上传文件的参数
        DiskFileItemFactory factory = new DiskFileItemFactory();        
        factory.setSizeThreshold(uploadSizeThreshold);    		//设置内存缓冲区的阀值
        factory.setRepository(new File(uploadRepositoryPath));    //临时目录，默认
        
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding(uploadEncoding);        //设置编码格式，推荐 jsp 和 处理类 均为 UTF-8
        upload.setSizeMax(uploadMaxSize);                //设置上传数据的最大数据
        
        ClassHelper.MetaClass mc = ClassHelper.getMetaClass(cmd);
        try{
	        List<FileItem> items = upload.parseRequest(request);
	        for(FileItem item:items){             
	            if (item.isFormField()) {    //如果是表单字段
	                String name = item.getFieldName();
	                String value = item.getString(uploadEncoding);
	                
	                ClassHelper.FGS fgs = mc.getMapppingField(name);
	    			if (fgs != null) {
	    				ClassHelper.setAttribute(cmd, fgs, value);
	    			}    			                
	            } else {    //如果是文件字段
	                String name = item.getFieldName();
	                File f=new File(uploadFilePath+"/"+newUploadFileId());
	                item.write(f);
	                
	                ClassHelper.FGS fgs = mc.getMapppingField(name);
	    			if (fgs != null) {
	    				if(fgs.getField().getType()==String.class){
	    					ClassHelper.setAttribute(cmd, fgs, f.getAbsolutePath());
	    				}else{
	    					ClassHelper.setAttribute(cmd, fgs, f);
	    				}
	    			}
	            }
	        }
        }catch(Exception e){
        	logger.error(""+e,e);
        }
	}
	
	 
	
	private static long FILE_SEQ=1;
	private static long CURRENT_TIME=System.currentTimeMillis();
	
	private synchronized static String newUploadFileId(){
		StringBuilder sb=new StringBuilder();
		
		Calendar ca=Calendar.getInstance();
		
		if(CURRENT_TIME==System.currentTimeMillis()){
			FILE_SEQ++;
		}else{
			FILE_SEQ=1;
			CURRENT_TIME=System.currentTimeMillis();
		}
		
		int m=ca.get(Calendar.MINUTE);
		int s=ca.get(Calendar.SECOND);
		
		if(m<10){
			sb.append("0");
		}
		sb.append(m);
		
		
		if(s<10){
			sb.append("0");
		}
		sb.append("_").append(s);
		
		
		sb.append(".").append(FILE_SEQ);		 
		
		return sb.toString();
	}

	public static void fromJSONObject(JSONObject json, Object cmd) {
		ClassHelper.MetaClass mc = ClassHelper.getMetaClass(cmd);
		if (json != null) {
			JSONArray names = json.names();
			if (names != null) {
				String name = null;
				Object value = null;
				try {
					for (int i = 0; i < names.length(); i++) {
						name = names.getString(i);
						value = json.opt(name);
						if (value != null) {
							ClassHelper.FGS fgs = mc.getMapppingField(name);
							if (fgs != null) {
								ClassHelper.setAttribute(cmd, fgs, value.toString());
							}
						}
					}
				} catch (JSONException je) {
					logger.error(je);
				}
			}
		}
	}

	
	private static Map<String,Class<? extends ResultContent>> hContentClasses=new ConcurrentHashMap<String,Class<? extends ResultContent>>();
	public static void registJSONParseClass(String name,Class<? extends ResultContent> clazz){
		hContentClasses.put(name, clazz);
	}
	
	public static Result parseResultFromJSONString(String jsonString) {
		try {
			Result r = new Result();
			
			JSONObject json = new JSONObject(jsonString);
			
			r.setStatus(json.getInt(Result.TAG_STATUS));
			r.setMessage(json.getString(Result.TAG_MESSAGE));
			r.setType(json.optString(Result.TAG_TYPE));
			r.setSubStatus(json.optInt(Result.TAG_SUBSTATUS));
			
			JSONArray names=json.names();
			for(int i=0;i<names.length();i++){
				String name=names.getString(i);
				Object o=json.opt(name);
				if(o instanceof JSONArray){
					JSONArray array=(JSONArray)o;
					Class<? extends ResultContent> clazz=hContentClasses.get(name);
					if(clazz!=null){					
						ResultContentList content_list = new ResultContentList();
						for (int k = 0; k < array.length(); k++) {
							JSONObject jobj = (JSONObject) array.get(k);
							
							ResultContent rc=clazz.newInstance();
							RequestDataBinding.fromJSONObject(jobj, rc);
	
							content_list.addContent(rc);
						}
						r.setContent(content_list);
					}else{
						logger.error("Invalid json array name: "+name);
					}
				}else if(o instanceof JSONObject){
					JSONObject jobj=(JSONObject)o;
					Class<? extends ResultContent> clazz=hContentClasses.get(name);
					if(clazz!=null){	
						ResultContent rc=clazz.newInstance();
						RequestDataBinding.fromJSONObject(jobj, rc);
						
						r.setContent(rc);
					}else{
						logger.error("Invalid json object name: "+name);
					}
				}
			} 
			return r;
		}catch(Exception e){
			logger.error(e);
			
			return StatusCode.ERR_SYSTEM().newResult("JSONParseException: "+e.getMessage());
		}
	}
	
	/**
	 * 老版本result对象的json解析,与proxy处理的时候调用
	 * @param jsonString
	 * @return
	 */
	public static Result parseOldResultFromJSONString(String jsonString) {
		try {
			Result r = new Result();
			
			JSONObject json = new JSONObject(jsonString);
			
			r.setStatus(json.getInt("status"));
			r.setMessage(json.getString("message"));
			r.setType(json.optString("type"));
			r.setSubStatus(json.optInt("substatus"));
			
			JSONArray names=json.names();
			for(int i=0;i<names.length();i++){
				String name=names.getString(i);
				Object o=json.opt(name);
				if(o instanceof JSONArray){
					JSONArray array=(JSONArray)o;
					Class<? extends ResultContent> clazz=hContentClasses.get(name);
					if(clazz!=null){					
						ResultContentList content_list = new ResultContentList();
						for (int k = 0; k < array.length(); k++) {
							JSONObject jobj = (JSONObject) array.get(k);
							
							ResultContent rc=clazz.newInstance();
							RequestDataBinding.fromJSONObject(jobj, rc);
	
							content_list.addContent(rc);
						}
						r.setContent(content_list);
					}else{
						logger.error("Invalid json array name: "+name);
					}
				}else if(o instanceof JSONObject){
					JSONObject jobj=(JSONObject)o;
					Class<? extends ResultContent> clazz=hContentClasses.get(name);
					if(clazz!=null){	
						ResultContent rc=clazz.newInstance();
						RequestDataBinding.fromJSONObject(jobj, rc);
						
						r.setContent(rc);
					}else{
						logger.error("Invalid json object name: "+name);
					}
				}
			} 
			return r;
		}catch(Exception e){
			return StatusCode.ERR_SYSTEM().newResult("JSONParseException: "+e.getMessage());
		}
	}
}
