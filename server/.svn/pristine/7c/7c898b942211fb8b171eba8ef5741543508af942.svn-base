package jeecg.ext.sdk.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import jeecg.ext.sdk.entity.Advertisers;
import jeecg.ext.sdk.entity.Ap;
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
@RequestMapping("/ap")
public class ApController  extends BaseController{
	private static final Logger logger = Logger.getLogger(ApController.class);
	@Autowired
	private SystemService systemService;
	
	@RequestMapping(params="list")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response){
		return new ModelAndView("ap/list");
	}
	
	@RequestMapping(params="datagrid")
	public void datagrid(Ap ap,HttpServletRequest request,HttpServletResponse response, DataGrid dataGrid){
		
		CriteriaQuery cq=new CriteriaQuery(Ap.class,dataGrid);
		
		String name = request.getParameter("apName");
		String contactsName = request.getParameter("contactsName");
		String contactsPhone = request.getParameter("contactsPhone");
	    if (name != null) {
			cq.add(Restrictions.like("apName", "%" + name.trim() + "%"));
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
	 String apid = GenerateSequenceUtil.generateSequenceNo();
	 if (StringUtil.isNotEmpty(id)) {
	   Ap ap = systemService.getEntity(Ap.class, Integer.valueOf(id));
	   req.setAttribute("ap", ap);
	   apid = ap.getApid();
	 }
	 req.setAttribute("apid", apid);
	 return new ModelAndView("ap/add");
	}
	
	@RequestMapping(params = "valid")
	public @ResponseBody
	ValidForm valid(Integer id, String name, String param) {
	  logger.debug("id = " + id + ", apName = " + name + ", param = " + param);
	  boolean isExisted = false;
		if ("apName".equals(name)) {
		  Ap ap = systemService.findUniqueByProperty(Ap.class, "apName", param);
		  if (ap != null) {
		    if (id == null) {
		      isExisted = true;
		    } else {
		      if (!id.equals(ap.getId())) {
		        isExisted = true;
		      }
		    }
		  }
		} else if ("loginEmail".equals(name)) {
			Ap ap = systemService.findUniqueByProperty(Ap.class, "loginEmail", param);
		  if (ap != null) {
		    if (id == null) {
		      isExisted = true;
		    } else {
		      if (!id.equals(ap.getId())) {
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
	  public AjaxJson save(Ap ap) {
	    AjaxJson json = new AjaxJson();

	    ValidForm valid = valid(ap.getId(), "apName", ap.getApName());
	    if ("n".equals(valid.getStatus())) {
	      json.setMsg("操作失败，开发者已存在！");
	      json.setSuccess(false);
	      return json;
	    }
	    
	    valid = valid(ap.getId(), "loginEmail", ap.getLoginEmail());
	    if ("n".equals(valid.getStatus())) {
	      json.setMsg("操作失败，登录账号已存在！");
	      json.setSuccess(false);
	      return json;
	    }

	    Ap entity;
	    if (ap.getId() == null) {
	      entity = ap;
	      String passwd = PasswordUtil.encrypt(entity.getLoginEmail(), entity.getLoginPwd(), PasswordUtil.getStaticSalt());
	      entity.setLoginPwd(passwd);
	      entity.setCreateTime(DateUtil.getCurrentTime());
	      entity.setLastTime(DateUtil.getCurrentTime());
	      systemService.save(ap);
	    } else {
	      entity = systemService.getEntity(Ap.class, ap.getId());
	      if (entity != null) {
	        entity.setContactsName(ap.getContactsName());
	        entity.setContactsPhone(ap.getContactsPhone());
	        entity.setApName(ap.getApName());
	        entity.setLastTime(DateUtil.getCurrentTime());
	        if (!ap.getLoginEmail().equals(entity.getLoginEmail())) {
	          entity.setLoginEmail(ap.getLoginEmail());
	        }
	        if (StringUtils.isNotBlank(ap.getLoginPwd())) {
	          if (!ap.getLoginPwd().equals(entity.getLoginPwd())) {
	            String passwd = PasswordUtil.encrypt(entity.getLoginPwd(), ap.getLoginPwd(), PasswordUtil.getStaticSalt());
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
