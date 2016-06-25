package jeecg.ext.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.system.pojo.ExTSRole;
import jeecg.system.controller.core.RoleController;
import jeecg.system.pojo.base.TSRoleUser;
import jeecg.system.pojo.base.TSUser;
import jeecg.system.service.SystemService;
import jeecg.system.service.UserService;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/exRoleController")
public class ExRoleController extends RoleController{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(RoleController.class);
	private UserService userService;
	private SystemService systemService;
	private String message = null;

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	public UserService getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
	@Override
	@RequestMapping(params = "role")
	public ModelAndView role() {
		return new ModelAndView("ext/system/role/roleList");
	}
	
	/**
	 * easyuiAJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "exRoleGrid")
	public void roleGrid(ExTSRole role, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ExTSRole.class,dataGrid);
		TSUser tsUser = ResourceUtil.getSessionUserName();

		List<TSRoleUser> rUsers = systemService.findByProperty(TSRoleUser.class, "TSUser.id", tsUser.getId());
		boolean isAdmin = false;
		for(TSRoleUser tsRoleUser: rUsers){
			if(tsRoleUser.getTSRole().getRoleCode().equals("admin")){
				isAdmin = true;
			}
		}
		if(!isAdmin){			
			role.setCreatedby(tsUser.getId());
		}
		
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, role);
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
		;
	}
	
	@RequestMapping(params = "exSaveRole")
	@ResponseBody
	public AjaxJson saveRole(ExTSRole role, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		TSUser tsUser = ResourceUtil.getSessionUserName();
		role.setCreatedby(tsUser.getId());
		if (StringUtil.isNotEmpty(role.getId())) {
			message = "角色: " + role.getRoleName() + "被更新成功";
			userService.saveOrUpdate(role);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			message = "角色: " + role.getRoleName() + "被添加成功";
			userService.save(role);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		request.getServletContext().setAttribute("functionReload", "1");
		
		String functionid = request.getParameter("defaultTab");
		if(functionid == null || functionid.equals("")){
	
		}else{
			String updateSql = "replace t_s_sys_conf_role set value='" + functionid + "' ,role_id ='" + role.getId() + "' , keyss = 'home.tab'";
			systemService.executeSql(updateSql);
		}

		return j;
	}

}
