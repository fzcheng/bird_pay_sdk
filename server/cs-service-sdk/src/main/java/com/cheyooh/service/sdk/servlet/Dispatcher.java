package com.cheyooh.service.sdk.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cheyooh.service.framework.servlet.ServletDispatcher;
import com.cheyooh.tools.log.Logger;

/**
 * @author zzg.zhou@mbook.com.cn {MSN:zzgzhou@hotmail.com, QQ:11039850}
 *  
 */
@WebServlet("/service/*")
public class Dispatcher extends ServletDispatcher {
	private static final long serialVersionUID = 1L;
	static Logger logger = new Logger(Dispatcher.class);	
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri=request.getRequestURI();
		String mflag="/service/m/";
		int p=uri.lastIndexOf(mflag);
		if(p>0){
			String m=uri.substring(p+mflag.length());
			request.getRequestDispatcher("/service?m="+m).forward(request, response);
		}else{
			super.doService(request, response);
		}
		 
		  
	} 
	
	
}
