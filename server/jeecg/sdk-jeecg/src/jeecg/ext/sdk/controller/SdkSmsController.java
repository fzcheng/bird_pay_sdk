package jeecg.ext.sdk.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.SdkSms;
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
@RequestMapping("/sdkSms")
public class SdkSmsController extends BaseController {
	private static final Logger logger = Logger
			.getLogger(SdkSmsController.class);

	@Autowired
	private SystemService systemService;

	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) {
		return new ModelAndView("sdkSms/list");
	}	
	
	@RequestMapping(params = "datagrid")
	public void datagrid(HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(SdkSms.class,
				dataGrid);
		String upPort=request.getParameter("upPort");
		String useState=request.getParameter("useState");
		if(upPort!=null){
			cq.add(Restrictions.like("upPort", "%"+upPort+"%"));
		}
		if(useState!=null){
			if(StringUtils.isNotBlank(useState)){
				cq.add(Restrictions.eq("useState", parseInteger(useState)));
			}			
		}
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	@RequestMapping(params = "addorupdate")
	  public ModelAndView addorupdate(String id) {
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("sdkSms/addorupdate");
	    Integer idInt = parseInteger(id);
	    if (idInt != null) {
	    	SdkSms sdkSms=systemService.getEntity(SdkSms.class, idInt);
	      mv.addObject("sdkSms", sdkSms);
	    }
	    return mv;
	  }
	
	 @RequestMapping(params = "save")
	  @ResponseBody
	  public AjaxJson save(SdkSms sdkSms){
	    AjaxJson json=new AjaxJson();
	    try {
	    	SdkSms entity=null;
	      if (sdkSms.getId() != null) {
	        entity = systemService.getEntity(SdkSms.class, sdkSms.getId());
	      }
	     
	      Date now = new Date();
	      if (entity != null) {
	    	entity.setOperationType(sdkSms.getOperationType());
	    	entity.setUpPort(sdkSms.getUpPort());
		    entity.setUseState(sdkSms.getUseState());
	    	entity.setUpdatedTime(now);
	      } else {
	        entity = sdkSms;
	        entity.setCreatedTime(now);
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
	    	   
	    SdkSms entity=systemService.getEntity(SdkSms.class, idInt);
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
