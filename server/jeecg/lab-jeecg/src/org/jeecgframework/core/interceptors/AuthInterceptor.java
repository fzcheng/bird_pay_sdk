package org.jeecgframework.core.interceptors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jeecg.system.pojo.base.ExtExcludeUrls;
import jeecg.system.service.SystemService;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.common.SessionInfo;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


/**
 * 权限拦截器
 * 
 * @author
 * 
 */
public class AuthInterceptor implements HandlerInterceptor {
	 
	private static final Logger logger = Logger.getLogger(AuthInterceptor.class);
	private SystemService systemService;
	//private List<String> excludeUrls;
	
	private static List<String> staticExcludeUrls;

	/*public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}
*/
	public SystemService getSystemService() {
		return systemService;
	}

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	/**
	 * 在controller后拦截
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) throws Exception {
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {

	}
	
	private void initExcludeUrls(){
		if(staticExcludeUrls==null){
			staticExcludeUrls=new ArrayList<String>();
			CriteriaQuery cq=new CriteriaQuery(ExtExcludeUrls.class);
			cq.eq("enable", 1);
			cq.add();
			List<ExtExcludeUrls> list= systemService.getListByCriteriaQuery(cq, false);
			if(list!=null && list.size()>0){
				for(ExtExcludeUrls url : list){
					staticExcludeUrls.add(url.getExcludeUrls());
				}
			}
		}
	}

	/**
	 * 在controller前拦截
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		String requestPath = ResourceUtil.getRequestPath(request);// 用户访问的资源地址
		HttpSession session = ContextHolderUtils.getSession();
		SessionInfo sessioninfo = (SessionInfo) session.getAttribute(Globals.USER_SESSION);
		initExcludeUrls();
		 
		if (staticExcludeUrls.contains(requestPath)) {
			return true;
		} else {
			if (sessioninfo != null && sessioninfo.getUser()!=null ) {
				
																
				 
				 if(!hasMenuAuth(request)){
					 response.sendRedirect("loginController.do?noAuth");
					//request.getRequestDispatcher("webpage/common/noAuth.jsp").forward(request, response);
					return false;
				} 
																
				
								//update-start--Author:anchao  Date:20130415 for：按钮权限控制
								String functionId=oConvertUtils.getString(request.getParameter("clickFunctionId"));
				if(!oConvertUtils.isEmpty(functionId)){
					Set<String> operationCodes = systemService.getOperationCodesByUserIdAndFunctionId(sessioninfo.getUser().getId(), functionId);
					request.setAttribute("operationCodes", operationCodes);
				 
				}
												
															 
				if(!oConvertUtils.isEmpty(functionId)){
					List<String> allOperation=this.systemService.findListbySql("SELECT operationcode FROM t_s_operation  WHERE functionid='"+functionId+"'"); 
					  
					List<String> newall = new ArrayList<String>();
					if(allOperation.size()>0){
						for(String s:allOperation){ 
						    s=s.replaceAll(" ", "");
							newall.add(s); 
						}						 
						String hasOperSql="SELECT operation FROM t_s_role_function fun, t_s_role_user role WHERE  " +
							"fun.functionid='"+functionId+"' AND fun.operation!=''  AND fun.roleid=role.roleid AND role.userid='"+sessioninfo.getUser().getId()+"' ";
						List<String> hasOperList = this.systemService.findListbySql(hasOperSql); 
					    for(String strs:hasOperList){ 
							    for(String s:strs.split(",")){
							        s=s.replaceAll(" ", "");
							    	newall.remove(s);
							    } 
								
							 
						} 
					}
					 
					 request.setAttribute("noauto_operationCodes", newall);
				 
				}
																
				
				
				
				return true;
			} else {
								//forword(request);
												forward(request, response);
								
				return false;
			}

		}
		// String requestPath = ContextHolderUtils.getRequestPath(request);// 用户访问的资源地址
		/*
		 * if (sessionInfo == null) {// 没有登录系统，或登录超时 forward("您没有登录或登录超时，请重新登录！", request, response); return false; }
		 */
		/*
		 * String requestPath = ContextHolderUtils.getRequestPath(request);// 用户访问的资源地址
		 * 
		 * List<TFunction> functions=systemService.findByProperty(TFunction.class,"functionurl",requestPath); if ( functions.size()<1) { forward("请修复数据库！数据库缺失【" + requestPath + "】资源！", request, response); return false; } TUser user = sessionInfo.getUser();
		 * 
		 * // 验证当前用户是否有权限访问此资源 List<TFunction> loginActionlist = new ArrayList();//已有权限菜单 List<TRoleUser> TRoleUsers=user.getTRoleUsers(); for (TRoleUser ru:TRoleUsers) { TRole role =ru.getTRole(); Set<TRoleFunction> roleFunctionList=role.getTRoleFunctions(); if(roleFunctionList.size()>0){ for(TRoleFunction roleFunction :roleFunctionList) { TFunction function=(TFunction)roleFunction.getTFunction(); loginActionlist.add(function); } } } boolean b=false; if (loginActionlist.size()>0) { for (TFunction f:loginActionlist) { if(f.getFunctionurl().equals(requestPath)){ b = true; break; } } if (b) { return true;// 当前访问资源地址是不需要验证的资源 } }else{ forward("您没有【" + requestPath + "】权限，请联系管理员给您赋予相应权限！", request, response); return false; }
		 */
	}
		private boolean hasMenuAuth(HttpServletRequest request){
		 
		String requestPath = ResourceUtil.getRequestPath(request);// 用户访问的资源地址
				String funcid=oConvertUtils.getString(request.getParameter("clickFunctionId"));
		 
		if(requestPath.indexOf("loginController.do")!=-1||funcid.length()==0){
			return true;
		} 
				SessionInfo sessioninfo = (SessionInfo) ContextHolderUtils.getSession().getAttribute(Globals.USER_SESSION);
		String userid = sessioninfo.getUser().getId();
		//requestPath=requestPath.substring(0, requestPath.indexOf("?")+1);
		String sql = "SELECT DISTINCT f.id FROM t_s_function f,t_s_role_function  rf,t_s_role_user ru " +
				" WHERE f.id=rf.functionid AND rf.roleid=ru.roleid AND " +
				"ru.userid='"+userid+"' AND f.functionurl like '"+requestPath+"%'"; 
		List list = this.systemService.findListbySql(sql);
		if(list.size()==0){
			return false;
		}else{
			return true;
		}
		 
	}
		/**
	 * 转发
	 * 
	 * @param user
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "forword")
	public ModelAndView forword(HttpServletRequest request) {

		return new ModelAndView(new RedirectView("loginController.do?login"));
	}

	private void forward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				request.getRequestDispatcher("webpage/login/timeout.jsp").forward(request, response);
			}

}
