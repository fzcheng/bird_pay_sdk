package jeecg.ext.sdk.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.SdkPlan;
import jeecg.ext.tools.TagUtilExt;
import jeecg.system.service.SystemService;
import net.sf.ehcache.search.expression.Criteria;

import org.apache.bcel.generic.NEW;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sdkPlan")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class SdkPlanController  extends BaseController{

	static Log logger=LogFactory.getLog(SdkPlanController.class);
	
	@Autowired
	private SystemService systemService;
	
	@RequestMapping(params="list")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response){
		
		return new ModelAndView("sdkPlan/list");
	}
	
	@RequestMapping(params="datagrid")
	public void datagrid(SdkPlan sdkPlan,HttpServletRequest request,HttpServletResponse response, DataGrid dataGrid){
		
		CriteriaQuery cq=new CriteriaQuery(SdkPlan.class,dataGrid);
		String name=request.getParameter("name");
		if(StringUtils.isNotEmpty(name)&&StringUtils.isNotBlank(name)){
			cq.add(Restrictions.like("name", "%"+name+"%"));
		}
//		HqlGenerateUtil.installHql(cq, sdkPlan);
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(String id,HttpServletRequest request){
		if(StringUtil.isNotEmpty(id)){
			SdkPlan sdkPlan=this.systemService.getEntity(SdkPlan.class, Integer.valueOf(id));
			request.setAttribute("sdkPlan", sdkPlan);
		}
		return new ModelAndView("sdkPlan/add");
	}
	@RequestMapping(params="save")
	@ResponseBody
	public AjaxJson save(HttpServletRequest request,SdkPlan sdkPlan){
		AjaxJson json=new AjaxJson();
		if(StringUtil.isNotEmpty(sdkPlan.getId())){
			this.systemService.saveOrUpdate(sdkPlan);
			systemService.addLog("方案"+sdkPlan.getName()+"更新成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			json.setMsg("方案"+sdkPlan.getName()+"更新成功");
		}else {
			this.systemService.save(sdkPlan);
			systemService.addLog("方案"+sdkPlan.getName()+"添加完成", Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			json.setMsg("方案"+sdkPlan.getName()+"添加完成");
		}
		return json;
	}
	@RequestMapping(params = "del", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson del(HttpServletRequest request,SdkPlan sdkPlan){
		AjaxJson json=new AjaxJson();
		try{
			this.systemService.delete(sdkPlan);
			systemService.addLog("方案删除成功", Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			json.setMsg("删除成功");
		}catch(Exception e){
			json.setMsg("无法删除");
		}
		return json;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
