package com.cheyooh.service.framework.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.cheyooh.service.framework.basic.JspService;
import com.cheyooh.service.framework.basic.Service;
import com.cheyooh.service.framework.basic.ServiceContext;
import com.cheyooh.service.framework.basic.ServiceListener;
//import com.cheyooh.service.framework.basic.ServiceManager;
import com.cheyooh.service.framework.basic.ServiceManagerCenter;
import com.cheyooh.service.framework.idata.Cmd;
import com.cheyooh.service.framework.idata.StatusCode;
import com.cheyooh.tools.cfg.EnvUtil;
import com.cheyooh.tools.log.Logger;

public abstract class ServletDispatcher extends HttpServlet {
	private static final long serialVersionUID = -4805739240172064516L;
	static Logger logger = new Logger(ServletDispatcher.class);	
	  
	public ServletDispatcher() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request, response);
	}

	protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");		
				
		if("xxxx".equals(request.getParameter("m")) || "nagios".equals(request.getParameter("channel"))){
			//隐藏ping日志消息
		}else{
			logger.info(""+request.getMethod()+" "+request.getRequestURL()+"?"+request.getQueryString());
		}
		
		ServiceContext rc = getServiceContext(request, response);
		executeService(rc);		
		// setupServicePage(rc);
		rc.buildResponse();		
	}

	protected ServiceContext getServiceContext(HttpServletRequest request, HttpServletResponse response){
		return new ServiceContext(request, response);
	}
	
	private void executeService(ServiceContext rc) throws ServletException {	
		//TODO 修订支持集中部署模式 服务查找
		ServiceManagerCenter sm = ServiceManagerCenter.getInstance();
		//String servicepath = rc.getRequest().getRequestURL().toString();
		//修订根据contextpath处理
		String servicepath = rc.getRequest().getRequestURI().toString();
		
		String rootpath = rc.getRequest().getServletContext().getRealPath("/");
		Service<Cmd> srv = sm.findService(rc.getCmd(), servicepath, rootpath, "service");
		
		if (srv != null) {
			try {
				ServiceListener sl = sm.getServiceListener();
				if (sl != null) {	sl.onBeginCall(srv);}
				
				if(srv instanceof JspService){
					JspService jsp = (JspService)srv;
					rc.setOutputJsp(true);
					rc.setJsp(jsp.getJspServicePath());
				}
				
				srv.doService(rc);
				
				
				if (sl != null) {sl.onEndCall(srv);}
			} catch (Exception e) {
				logger.error(e);
				
				String msg=EnvUtil.getValue("msg.service.exception");
				if(msg==null || msg.trim().length()==0){
					msg="服务临时不可用, 请稍候再试!";
				}
				
				rc.setResult(StatusCode.ERR_SYSTEM().setMessage(msg));
			}
		} else {
			rc.setResult(StatusCode.NON_SERVICE());
		}	
 
	}

	@SuppressWarnings("unused")
	private void setupServicePage(ServiceContext rc) {
		if(rc.isOutputJsp()){
			String cmd = rc.getCmd();
			if (StringUtils.isNotEmpty(cmd)) {
				String page = getPage(cmd);	
				String fpath = rc.getRequest().getServletContext().getRealPath(page);
				if (new File(fpath).exists()) {
					rc.setJsp(page);	
					if (rc.getResult().getStatus() == StatusCode.NON_SERVICE().getStatus()) {
						rc.setResult(StatusCode.SUCCESS());
					}
				}
			} else {
				rc.setResult(StatusCode.ERR_PARAMETER().newResult("缺少命令参数: cmd"));
			}
		}
	}

	private String getPage(String cmd) {
		return "/service/" + cmd + ".jsp";
	}
}
