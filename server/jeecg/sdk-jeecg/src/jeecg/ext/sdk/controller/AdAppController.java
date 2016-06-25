package jeecg.ext.sdk.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import jeecg.ext.sdk.entity.Ad;
import jeecg.ext.sdk.entity.AdApp;
//import jeecg.ext.sdk.entity.App;
import jeecg.ext.tools.DateUtil;
import jeecg.ext.tools.GenerateSequenceUtil;
import jeecg.ext.tools.TagUtilExt;
import jeecg.system.service.SystemService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/adApp")
public class AdAppController  extends BaseController{
	static Log logger=LogFactory.getLog(AdAppController.class);
	
	@Autowired
	private SystemService systemService;
	
	@RequestMapping(params="list")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response){
		return new ModelAndView("adApp/list");
	}
	
	@RequestMapping(params="datagrid")
	public void datagrid(AdApp adApp,HttpServletRequest request,HttpServletResponse response, DataGrid dataGrid){
		
		CriteriaQuery cq=new CriteriaQuery(AdApp.class,dataGrid);
		
		//查询条件组装器
		HqlGenerateUtil.installHql(cq, adApp);
		this.systemService.getDataGridReturn(cq, true);
		TagUtilExt.datagrid(response, dataGrid,"id");
	}
	
	/**
	* 列表页面跳转
	* 
	* @return
	*/
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(String id, HttpServletRequest req) {
	 String appid = GenerateSequenceUtil.generateSequenceNo();
	 if (StringUtil.isNotEmpty(id)) {
		 AdApp adApp = systemService.getEntity(AdApp.class, Integer.valueOf(id));
	   req.setAttribute("app", adApp);
	   appid = adApp.getAdAppid();
	 }
	 req.setAttribute("appid", appid);
	 return new ModelAndView("adApp/add");
	}
	
	@RequestMapping(params = "save")
	  @ResponseBody
	  public AjaxJson save(AdApp adApp,HttpServletRequest request) {
	    AjaxJson json = new AjaxJson();
	    
	    String appId = GenerateSequenceUtil.generateSequenceNo();
	    String appKey = getAppkey();
		
	    AdApp entity;
	    if (adApp.getId() == null) {
	      entity = adApp;
	      entity.setAdAppid(appId);
	      entity.setAppKey(appKey);
	      entity.setStatus(Globals.Zero_Int);
	      entity.setCreateTime(DateUtil.getCurrentTime());
	      entity.setLastTime(DateUtil.getCurrentTime());
	      systemService.save(adApp);
	    } else {
	      entity = systemService.getEntity(AdApp.class, adApp.getId());
	      if (entity != null) {
	        entity.setLastTime(DateUtil.getCurrentTime());
	        
	        json.setMsg("修改成功");
	      }
	    }
	    
	    if (entity == null) {
	      json.setMsg("操作失败");
	      json.setSuccess(false);
	      return json;
	    }
	    
	    systemService.saveOrUpdate(entity);
	    return json;
	  }
	
	private String getAppkey() {
	  UUID uuid = UUID.randomUUID();
	  return uuid.toString().replace("-", "");
	}
}
