package jeecg.ext.sdk.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.SdkMmdoSetting;
import jeecg.ext.sdk.entity.SdkOperatorPayChannel;
import jeecg.ext.sdk.entity.SdkGameDomain;
import jeecg.system.service.SystemService;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.jeecgframework.core.common.controller.BaseController;

@Controller
@RequestMapping("/sdkGameDomain")
public class SdkGameDomainController extends BaseController {
	private static final Logger logger = Logger.getLogger(SdkGameDomainController.class);
	  
	  @Autowired
	  private SystemService systemService;
	  
	  @RequestMapping(params = "list")
	  public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
	    return new ModelAndView("sdkGameDomain/list");
	  }

	  @RequestMapping(params = "datagrid")
	  public void datagrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
	    CriteriaQuery cq = new CriteriaQuery(SdkGameDomain.class, dataGrid);
	    String domain=request.getParameter("domain");
		if(StringUtils.isNotEmpty(domain)&&StringUtils.isNotBlank(domain)){
			cq.add(Restrictions.like("domain", "%"+domain+"%"));
		}
	    String status=request.getParameter("status");
		if(StringUtils.isNotEmpty(status)&&StringUtils.isNotBlank(status)){
			cq.add(Restrictions.eq("status", parseInteger(status)));
		}
	    this.systemService.getDataGridReturn(cq, true);
	    TagUtil.datagrid(response, dataGrid);
	  }
	  
	  @RequestMapping(params = "addorupdate")
	  public ModelAndView addorupdate(String id) {
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("sdkGameDomain/addorupdate");
	    
	    Integer idInt = parseInteger(id);
	    if (idInt != null) {
	    	SdkGameDomain sdkGameDomain = systemService.getEntity(SdkGameDomain.class, idInt);
	      mv.addObject("sdkGameDomain", sdkGameDomain);
	    }
	    
	    return mv;
	  }
	  
	  @RequestMapping(params = "save")
	  @ResponseBody
	  public AjaxJson save(SdkGameDomain sdkGameDomain){
	    AjaxJson json=new AjaxJson();
	    try {
	    	SdkGameDomain entity = null;
	      if (sdkGameDomain.getId() != null) {
	        entity = systemService.getEntity(SdkGameDomain.class, sdkGameDomain.getId());
	      }
	      //SdkOperatorPayChannel entity = systemService.findUniqueByProperty(SdkOperatorPayChannel.class, "channelCode", payChannel.getChannelCode());
	      //if (entity != null && (payChannel.getId() == null || !payChannel.getId().equals(entity.getId()))) {
	      //  json.setMsg("支付渠道号已存在");
	      //  return json;
	      //}
	      
	      if (entity != null) {
	    	entity.setDomain(sdkGameDomain.getDomain());
	    	entity.setStatus(sdkGameDomain.getStatus());	    	
	      } else {
	        entity = sdkGameDomain;
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
	    	   
	    
	    SdkGameDomain entity = systemService.getEntity(SdkGameDomain.class, idInt);
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
