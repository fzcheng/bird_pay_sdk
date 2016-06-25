package jeecg.ext.sdk.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.Advertisers;
import jeecg.ext.tools.DateUtil;
import jeecg.ext.tools.GenerateSequenceUtil;
import jeecg.ext.tools.TagUtilExt;
import jeecg.system.service.SystemService;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.model.json.ValidForm;
import org.jeecgframework.core.util.PasswordUtil;
import org.jeecgframework.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/advertisers")
public class AdvertisersController  extends BaseController{
	private static final Logger logger = Logger.getLogger(AdvertisersController.class);
	@Autowired
	private SystemService systemService;
	
	@RequestMapping(params="list")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response){
		return new ModelAndView("advertisers/list");
	}
	
	@RequestMapping(params="datagrid")
	public void datagrid(Advertisers advertisers,HttpServletRequest request,HttpServletResponse response, DataGrid dataGrid){
		
		CriteriaQuery cq=new CriteriaQuery(Advertisers.class,dataGrid);
		
		String name = request.getParameter("name");
		String contactsName = request.getParameter("contactsName");
		String contactsPhone = request.getParameter("contactsPhone");
	    if (name != null) {
			cq.add(Restrictions.like("name", "%" + name.trim() + "%"));
		}
		if (contactsName != null) {
			if(StringUtils.isNotBlank(contactsName)){
				cq.add(Restrictions.like("contactsName", "%" + contactsName.trim() + "%"));	
	    	}
		}
		if (contactsPhone != null) {
			if(StringUtils.isNotBlank(contactsPhone)){
				cq.add(Restrictions.like("contactsPhone", "%" + contactsPhone.trim() + "%"));
			}
		}
		
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
	 String adverId = GenerateSequenceUtil.generateSequenceNo();
	 if (StringUtil.isNotEmpty(id)) {
	   Advertisers advertisers = systemService.getEntity(Advertisers.class, Integer.valueOf(id));
	   req.setAttribute("advertisers", advertisers);
	   adverId = advertisers.getAdverId();
	 }
	 req.setAttribute("adverId", adverId);
	 return new ModelAndView("advertisers/add");
	}
	
	@RequestMapping(params = "valid")
	public @ResponseBody
	ValidForm valid(Integer id, String name, String param) {
	  logger.debug("id = " + id + ", name = " + name + ", param = " + param);
	  boolean isExisted = false;
		if ("name".equals(name)) {
		  Advertisers advertisers = systemService.findUniqueByProperty(Advertisers.class, "name", param);
		  if (advertisers != null) {
		    if (id == null) {
		      isExisted = true;
		    } else {
		      if (!id.equals(advertisers.getId())) {
		        isExisted = true;
		      }
		    }
		  }
		} else if ("loginEmail".equals(name)) {
		  Advertisers advertisers = systemService.findUniqueByProperty(Advertisers.class, "loginEmail", param);
		  if (advertisers != null) {
		    if (id == null) {
		      isExisted = true;
		    } else {
		      if (!id.equals(advertisers.getId())) {
		        isExisted = true;
		      }
		    }
		  }
		}
	
		ValidForm valid = new ValidForm();
		if (isExisted) {
		  valid.setInfo("登录账号已存在！");
		  valid.setStatus("n");
		} else {
			valid.setInfo("");
			valid.setStatus("y");
	    }
	    return valid;
	  }
	
	  @RequestMapping(params = "save")
	  @ResponseBody
	  public AjaxJson save(Advertisers advertisers) {
	    AjaxJson json = new AjaxJson();

	    ValidForm valid = valid(advertisers.getId(), "name", advertisers.getName());
	    if ("n".equals(valid.getStatus())) {
	      json.setMsg("操作失败，广告主已存在！");
	      json.setSuccess(false);
	      return json;
	    }
	    
	    valid = valid(advertisers.getId(), "loginEmail", advertisers.getLoginEmail());
	    if ("n".equals(valid.getStatus())) {
	      json.setMsg("操作失败，登录账号已存在！");
	      json.setSuccess(false);
	      return json;
	    }

	    Advertisers entity;
	    if (advertisers.getId() == null) {
	      entity = advertisers;
	      String passwd = PasswordUtil.encrypt(entity.getLoginEmail(), entity.getLoginPwd(), PasswordUtil.getStaticSalt());
	      entity.setLoginPwd(passwd);
	      entity.setCreateTime(DateUtil.getCurrentTime());
	      entity.setLastTime(DateUtil.getCurrentTime());
	      systemService.save(advertisers);
	    } else {
	      entity = systemService.getEntity(Advertisers.class, advertisers.getId());
	      if (entity != null) {
	        entity.setContactsName(advertisers.getContactsName());
	        entity.setContactsPhone(advertisers.getContactsPhone());
	        entity.setName(advertisers.getName());
	        entity.setLastTime(DateUtil.getCurrentTime());
	        if (!advertisers.getLoginEmail().equals(entity.getLoginEmail())) {
	          entity.setLoginEmail(advertisers.getLoginEmail());
	        }
	        if (StringUtils.isNotBlank(advertisers.getLoginPwd())) {
	          if (!advertisers.getLoginPwd().equals(entity.getLoginPwd())) {
	            String passwd = PasswordUtil.encrypt(entity.getLoginPwd(), advertisers.getLoginPwd(), PasswordUtil.getStaticSalt());
	            entity.setLoginPwd(passwd);
	          }
	        }
	        
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
	  
}
