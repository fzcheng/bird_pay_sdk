package jeecg.ext.sdk.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.Ad;
import jeecg.ext.sdk.entity.App;
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
@RequestMapping("/app")
public class AppController  extends BaseController{
	static Log logger=LogFactory.getLog(AppController.class);
	
	@Autowired
	private SystemService systemService;
	
	@RequestMapping(params="list")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response){
		return new ModelAndView("app/list");
	}
	
	@RequestMapping(params="datagrid")
	public void datagrid(App app,HttpServletRequest request,HttpServletResponse response, DataGrid dataGrid){
		
		CriteriaQuery cq=new CriteriaQuery(App.class,dataGrid);
		
		//查询条件组装器
		HqlGenerateUtil.installHql(cq, app);
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
		 App app = systemService.getEntity(App.class, Integer.valueOf(id));
	   req.setAttribute("app", app);
	   appid = app.getAppid();
	 }
	 req.setAttribute("appid", appid);
	 return new ModelAndView("app/add");
	}
	
	@RequestMapping(params = "save")
	  @ResponseBody
	  public AjaxJson save(App app,HttpServletRequest request) {
	    AjaxJson json = new AjaxJson();
	    
	    String appId = GenerateSequenceUtil.generateSequenceNo();
	    String appKey = getAppkey();
		
	    App entity;
	    if (app.getId() == null) {
	      entity = app;
	      entity.setAppid(appId);
	      entity.setAppKey(appKey);
	      entity.setStatus(Globals.Zero_Int);
	      entity.setCreateTime(DateUtil.getCurrentTime());
	      entity.setLastTime(DateUtil.getCurrentTime());
	      systemService.save(app);
	    } else {
	      entity = systemService.getEntity(App.class, app.getId());
	      if (entity != null) {
	    	  entity.setAppName(app.getAppName());
	    	  entity.setAppDetail(app.getAppDetail());
	    	  entity.setType(app.getType());
	    	  entity.setCallbackUrl(app.getCallbackUrl());
	    	  entity.setDeviceToken(app.getDeviceToken());
	    	  entity.setIcon(app.getIcon());
	    	  entity.setImg1(app.getImg1());
	    	  entity.setImg2(app.getImg2());
	    	  entity.setImg3(app.getImg3());
	    	  entity.setImg4(app.getImg4());
	    	  entity.setImg5(app.getImg5());
	    	  entity.setPackagename(app.getPackagename());
	    	  entity.setPackageName(app.getPackageName());
	    	  entity.setPackageUrl(app.getPackageUrl());
	    	  entity.setRate(app.getRate());
	    	  entity.setRateUnit(app.getRateUnit());
	    	  entity.setSize(app.getSize());
	    	  entity.setVersion(app.getVersion());
	    	  entity.setVersionCode(app.getVersionCode());
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
