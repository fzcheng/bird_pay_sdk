package com.cheyooh.service.framework.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.StatusCode;
import com.cheyooh.service.framework.utils.binding.RequestDataBinding;
import com.cheyooh.tools.log.Logger;

public class ServiceContext {
	static Logger logger=new Logger(ServiceContext.class);
	  
	private HttpServletRequest  request;
	private HttpServletResponse response;
	private String cmd;
	private String fmt;
	
	private Result result=StatusCode.NON_SERVICE();	
	private String defaultPage="/service/default.jsp";	
	private boolean outputJsp=false;
	
	public ServiceContext(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		this.cmd=request.getParameter("cmd");
		if(this.cmd==null){
			this.cmd=request.getParameter("m");
		}
		this.fmt=request.getParameter("fmt");
	}
	
	private String jsp;
	 
	public <T> T getCmd(T cmd){
		RequestDataBinding.fromRequest(request, cmd);
		return cmd;
	}
	
	public void buildResponse()throws IOException,ServletException{
		if(result.getType()==null){
			if(cmd==null){
				result.setType("error");
			}else{
				result.setType(cmd);
			}
		}
		
		result.setFmt(fmt);
		
		if(outputJsp){
			buildResponseUseJsp();
		}else{
			buildResponseUseServlet();
		}
	}
	
	protected void buildResponseUseServlet()throws IOException,ServletException{
		if("json".equalsIgnoreCase( fmt )){
			response.setContentType("application/json");
		}else if("txt".equalsIgnoreCase(fmt)){
			response.setContentType("text/plain");
		}else{
			response.setContentType("text/xml");
		}
		
		response.setCharacterEncoding("utf-8");
		
		String type = result.getType();
		if(StringUtils.isEmpty(type))type=cmd;
		String r=result.getResult(fmt,type);
		
		if(request.getParameter("debug")!=null){
			StringBuffer sb=new StringBuffer();
			sb.append(request.getMethod()+": "+request.getRequestURL());
			if(request.getQueryString()!=null){
				sb.append("?").append(request.getQueryString());
			}
			sb.append(", Response: ").append(r);
			logger.info(sb);
		}
		
		response.getWriter().print(r);
	}
	  
	
	protected void buildResponseUseJsp()throws IOException,ServletException{
		request.setAttribute("result",result); 
		
		if(jsp==null){
			jsp=getDefaultPage();
		}
		
		request.getRequestDispatcher(jsp).forward(request, response);
	}
	  
	public String getJsp() {
		return jsp;
	}

	public String setJsp(String jsp) {
		this.jsp = jsp;
		return this.jsp;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public String getCmd() {
		return cmd;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public String getDefaultPage() {
		return defaultPage;
	}

	public void setDefaultPage(String defaultPage) {
		this.defaultPage = defaultPage;
	}

	public boolean isOutputJsp() {
		return outputJsp;
	}

	public void setOutputJsp(boolean outputJsp) {
		this.outputJsp = outputJsp;
	}

	public String getFmt() {
		return fmt;
	}

	public void setFmt(String fmt) {
		this.fmt = fmt;
	}
  
}
