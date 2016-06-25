package jeecg.ext.sdk.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.SdkGameBlacklist;
import jeecg.system.service.SystemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.tag.core.easyui.TagUtil;

@Controller
@RequestMapping("/sdkGameBlacklist")
public class SdkGameBlacklistController extends BaseController {
	private static final Logger logger = Logger.getLogger(SdkGameBlacklistController.class);
	  
	  @Autowired
	  private SystemService systemService;
	  
	  @RequestMapping(params = "list")
	  public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
	    return new ModelAndView("sdkGameBlacklist/list");
	  }

	  @RequestMapping(params = "datagrid")
	  public void datagrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
	    CriteriaQuery cq = new CriteriaQuery(SdkGameBlacklist.class, dataGrid);
	    String mobile=request.getParameter("mobile");
		if(StringUtils.isNotEmpty(mobile)&&StringUtils.isNotBlank(mobile)){
			cq.add(Restrictions.like("mobile", "%"+mobile+"%"));
		}
		String operatorType=request.getParameter("operatorType");
		if(StringUtils.isNotEmpty(operatorType)&&StringUtils.isNotBlank(operatorType)){
			Integer i=parseInteger(operatorType);
			cq.add(Restrictions.eq("operatorType", i.shortValue()));
		}
	    this.systemService.getDataGridReturn(cq, true);
	    TagUtil.datagrid(response, dataGrid);
	  }
	  
	  @RequestMapping(params = "addorupdate")
	  public ModelAndView addorupdate(String id) {
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("sdkGameBlacklist/addorupdate");
	    logger.info("the sdkGameBlacklist id is :"+id);
	    Integer idInt = parseInteger(id);
	    logger.info("the idInt is :"+idInt);
	    if (idInt != null) {
	    	SdkGameBlacklist sdkGameBlacklist = systemService.getEntity(SdkGameBlacklist.class, idInt);
	      mv.addObject("sdkGameBlacklist", sdkGameBlacklist);
	    }
	    
	    return mv;
	  }
	  
	  @RequestMapping(params = "save")
	  @ResponseBody
	  public AjaxJson save(SdkGameBlacklist sdkGameBlacklist){
	    AjaxJson json=new AjaxJson();
	    try {
	    	SdkGameBlacklist entity = null;
	      if (sdkGameBlacklist.getId() != null) {
	        entity = systemService.getEntity(SdkGameBlacklist.class, sdkGameBlacklist.getId());
	      }
	      //SdkOperatorPayChannel entity = systemService.findUniqueByProperty(SdkOperatorPayChannel.class, "channelCode", payChannel.getChannelCode());
	      //if (entity != null && (payChannel.getId() == null || !payChannel.getId().equals(entity.getId()))) {
	      //  json.setMsg("支付渠道号已存在");
	      //  return json;
	      //}
	      Date now = new Date();
	      if (entity != null) {
	    	entity.setImsi(sdkGameBlacklist.getImsi());
	    	entity.setMobile(sdkGameBlacklist.getMobile());
	    	entity.setOperatorType(sdkGameBlacklist.getOperatorType());
	    	entity.setCreatedTime(now);	    	  	
	      } else {
	        entity = sdkGameBlacklist;
	      }
	      
	      this.systemService.saveOrUpdate(entity);
	      json.setMsg("保存成功");
	    } catch (Exception e) {
	      json.setMsg("保存异常");
	    }
	    
	    return json;
	  }
	  
	  @RequestMapping(params = "del", method = RequestMethod.POST)
	  @ResponseBody
	  public AjaxJson del(String id) {
	    AjaxJson json = new AjaxJson();
	    Integer idInt = parseInteger(id);
	    if (idInt == null) {
	      json.setMsg("请选择要删除的项目");
	      return json;
	    }
	    	   
	    
	    SdkGameBlacklist entity = systemService.getEntity(SdkGameBlacklist.class, idInt);
	    if (entity == null) {
	      json.setMsg("id=" + id + "，记录不存在");
	      return json;
	    }
	    
	    systemService.delete(entity);
	    json.setMsg("删除成功");
	    
	    return json;
	  }
	  
	  private Integer parseInteger(String str) {
	    Integer num = null;
	    try {
	      if (StringUtils.isNotBlank(str)) {
	        num = Integer.valueOf(str);
	      }
	    } catch (NumberFormatException e) {
	      logger.warn("parse number string error! str = " + str);
	    }
	    return num;
	  }
}
