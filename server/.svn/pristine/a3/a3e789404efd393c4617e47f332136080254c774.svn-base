package jeecg.ext.sdk.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.Ad;
import jeecg.ext.sdk.entity.AdApp;
import jeecg.ext.sdk.entity.Push;
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

import cn.birdplat.service.DeviceToken;
import cn.birdplat.service.MqttBroker;

@Controller
@RequestMapping("/push")
public class PushController  extends BaseController{
	static Log logger=LogFactory.getLog(PushController.class);
	
	MqttBroker mqttBroker;
	DeviceToken deviceToken;
	public void setMqttBroker(MqttBroker mqttBroker) {
		this.mqttBroker = mqttBroker;
	}
	public void setDeviceToken(DeviceToken deviceToken) {
		this.deviceToken = deviceToken;
	}
	
	@Autowired
	private SystemService systemService;
	
	@RequestMapping(params="android")
	public ModelAndView android(HttpServletRequest request,HttpServletResponse response){
		if(request.getParameter("adverId")!=null){
			request.setAttribute("adverId", request.getParameter("adverId"));
		}
		return new ModelAndView("push/android");
	}
	
	@RequestMapping(params="list")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response){
		return new ModelAndView("push/list");
	}
	
	@RequestMapping(params="datagrid")
	public void datagrid(Push push,HttpServletRequest request,HttpServletResponse response, DataGrid dataGrid){
		CriteriaQuery cq=new CriteriaQuery(Push.class,dataGrid);
		
		//查询条件组装器
		HqlGenerateUtil.installHql(cq, push);
		this.systemService.getDataGridReturn(cq, true);
		TagUtilExt.datagrid(response, dataGrid,"id");
	}
	
	/**
	* 列表页面跳转
	* 
	* @return
	*/
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(HttpServletRequest req) {
	 String url = "";
	 if(req.getParameter("type")!=null){
		 String type = req.getParameter("type");
		if(type.equals("0")){
			url = "push/android";
		}else if(type.equals("1")){
			url = "push/ios";
		}
	 }
	 return new ModelAndView(url);
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(params = "save")
	  @ResponseBody
	  public AjaxJson save(Push push,HttpServletRequest request) {
	    AjaxJson json = new AjaxJson();
	    
	    String pushid = GenerateSequenceUtil.generateSequenceNo();
	    String type="";
		if(StringUtil.isNotEmpty(request.getParameter("type"))){
			logger.info("the request type is :"+request.getParameter("type"));
			type= request.getParameter("type");
		}
		String title="";
		if(StringUtil.isNotEmpty(request.getParameter("title"))){
			logger.info("the request title is :"+request.getParameter("title"));
			title= request.getParameter("title");
		}
		String content="";
		if(StringUtil.isNotEmpty(request.getParameter("content"))){
			logger.info("the request content is :"+request.getParameter("content"));
			content= request.getParameter("content");
		}
		String token="";
		if(StringUtil.isNotEmpty(request.getParameter("token"))){
			logger.info("the request token is :"+request.getParameter("token"));
			token= request.getParameter("token");
		}
		
		Push entity;
	    if (push.getId() == null) {
	      entity = push;
	      entity.setPushid(pushid);
	      entity.setType(Integer.parseInt(type));
	      entity.setTitle(title);
	      entity.setContent(content);
	      entity.setCreateTime(DateUtil.getCurrentTime());
	      entity.setLastTime(DateUtil.getCurrentTime());
	      entity.setDeviceToken(token);
	      systemService.save(push);
	      
	      String[] args = new String[] {token,title,content};
	      if(type.equals("0")){
				mqttBroker.main(args);
	      }else if(type.equals("1")){
				try {
					deviceToken.main(args);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	      }
	    } else {
	      entity = systemService.getEntity(Push.class, push.getId());
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
	
}
