package com.cheyooh.tools.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.cheyooh.tools.log.Logger;


public class PropertyUtils {
	static Logger logger=new Logger(PropertyUtils.class);
	
	private PropertyUtils() {
	}
 
	public static Properties readToProperties(File f){
		InputStream in=null;
		try{
			Properties p=new Properties();
			
			if(f.exists()){
				try{
				in=new FileInputStream(f);
				p.load(in);
				}catch(Exception e){
					logger.error(""+e,e);
				}
			}
			return p;
		}finally{
			IOUtils.close(in);
		}
	}
	
	public static String readToString(String file,String charset) { 
		FileInputStream fin=null;
		try{
			fin=new FileInputStream(file);
			return readToString(fin,charset);
		} catch(IOException e) {            
			logger.error("文件读取失败: "+e,e);
			return null;
		}
	}
	public static String readToString(InputStream inputStream) {         
		return readToString(inputStream,null);
	}
	public static String readToString(InputStream inputStream,String charset) {         
		try{            
			ByteArrayOutputStream oStream = new ByteArrayOutputStream();            
			int length;    
			byte[] buf=new byte[1024];
			while((length = inputStream.read(buf)) != -1) {                
				oStream.write(buf,0,length);            
			}           
			if(charset!=null){
				return new String(oStream.toByteArray(),charset);
			}else{
				return new String(oStream.toByteArray());
			}
		} catch(IOException e) {            
			logger.error("文件读取失败: "+e,e);   
			return null;
		}finally{
			try{inputStream.close();}catch(Exception e){}
		}
	}
  
}
