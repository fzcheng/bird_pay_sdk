package com.cheyooh.service.dal.diagnosis;

import java.io.File;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.cheyooh.tools.cfg.CfgPath;
import com.cheyooh.tools.log.Logger;

public class LogFileSQLWriter implements SQLWriter{
	static Logger logger=new Logger(LogFileSQLWriter.class);
	
	public int write(SqlLog log) {
		String s=log.toString();
		
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<s.length();i++){
			char c=s.charAt(i);
			if(c=='\r' || c=='\n'){
				sb.append(" ");
			}else{
				sb.append(c);
			}
		}
		writeFile(sb.toString());
		
		return 1;
	}
	
	
	
	private synchronized void writeFile(String s){
		try{
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			File logfile=new File(CfgPath.getLogPathRoot()+"/longsql_"+sdf.format(new Date())+".log");
			 
			RandomAccessFile raf=new RandomAccessFile(logfile,"rw");
			try{
				raf.seek(logfile.length());
				raf.write((s+"\r\n").getBytes());			
			}finally{
				raf.close();
			}				
		}catch(Exception e){
			logger.error(e);
		}
		
	}
}
