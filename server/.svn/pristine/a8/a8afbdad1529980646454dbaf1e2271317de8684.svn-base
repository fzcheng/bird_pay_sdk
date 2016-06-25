package com.cheyooh.service.sdk.action.notify;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.cheyooh.service.framework.basic.Service;
import com.cheyooh.service.framework.basic.ServiceContext;
import com.cheyooh.service.framework.idata.Cmd;
import com.cheyooh.service.framework.idata.Result;

public abstract class AbstractNotifyService <C extends Cmd> extends Service<C>{
	 
	public Result doService(ServiceContext rc)throws ServletException, IOException{
		rc.setFmt("txt");
		
		return super.doService(rc);
	}
	protected void log(C cmd, Result r){		 
		String info=getRequestInfo(cmd.getServiceContext().getRequest());
		
		logger.info(info+", Response("+r.getStatus()+"): "+r.getMessage());
		
	}
	
	protected void logException(ServiceContext rc,Throwable e){
		String info=getRequestInfo(rc.getRequest());
		logger.error(info+", Exception: "+e.getClass()+", detail: "+e.getMessage());
	}
	
	private String getRequestInfo(HttpServletRequest request){
		String uri=request.getRequestURI();
		
		StringBuilder sb=new StringBuilder();
		Enumeration<String> e=request.getParameterNames();
		while(e.hasMoreElements()){
			String name=e.nextElement();
			if(sb.length()>0){
				sb.append("&");
			}
			
			String[] vs=request.getParameterValues(name);
			if(vs!=null){
				sb.append(name).append("=");
				for(int i=0;i<vs.length;i++){
					if(i>0){
						sb.append("::");
					}
					sb.append(vs[i]);
				}
			}else{
				sb.append(name);
			}
		}		
		
		
		if(sb.length()>0){
			return uri+"?"+sb.toString();
		}else{
			return uri;
		}		 
	}
}
