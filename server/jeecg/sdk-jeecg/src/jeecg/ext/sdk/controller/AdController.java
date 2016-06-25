package jeecg.ext.sdk.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.Ad;
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
@RequestMapping("/ad")
public class AdController  extends BaseController{
	static Log logger=LogFactory.getLog(AdController.class);
	
	@Autowired
	private SystemService systemService;
	
	@RequestMapping(params="list")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response){
		if(request.getParameter("adverId")!=null){
			request.setAttribute("adverId", request.getParameter("adverId"));
		}
		return new ModelAndView("ad/list");
	}
	
	@RequestMapping(params="datagrid")
	public void datagrid(Ad ad,HttpServletRequest request,HttpServletResponse response, DataGrid dataGrid){
		
		CriteriaQuery cq=new CriteriaQuery(Ad.class,dataGrid);
		
		//查询条件组装器
		HqlGenerateUtil.installHql(cq, ad);
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
	 String adId = GenerateSequenceUtil.generateSequenceNo();
	 if (StringUtil.isNotEmpty(id)) {
		 Ad ad = systemService.getEntity(Ad.class, Integer.valueOf(id));
	   req.setAttribute("ad", ad);
	   adId = ad.getAdId();
	   SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//小写的mm表示的是分钟  
	   String beginTime = ad.getBeginTime();
	   String endTime = ad.getEndTime();
		try {
			Date dateBegin = sdf.parse(beginTime);
			Date dateEnd = sdf.parse(endTime);
			req.setAttribute("beginTime", dateBegin);
			req.setAttribute("endTime", dateEnd);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	 }
	 req.setAttribute("adId", adId);
	 if(req.getParameter("adverId")!=null){
		 req.setAttribute("adverId", req.getParameter("adverId"));
		}
	 return new ModelAndView("ad/add");
	}
	
	@RequestMapping(params = "save")
	  @ResponseBody
	  public AjaxJson save(Ad ad,HttpServletRequest request) {
	    AjaxJson json = new AjaxJson();
	    
	    String adId = GenerateSequenceUtil.generateSequenceNo();
	    String apiKey = getAppkey();
	    String adverId="";
		if(StringUtil.isNotEmpty(request.getParameter("adverId"))){
			logger.info("the request adverId is :"+request.getParameter("adverId"));
			adverId= request.getParameter("adverId");
		}
		String adname="";
		if(StringUtil.isNotEmpty(request.getParameter("adname"))){
			logger.info("the request adname is :"+request.getParameter("adname"));
			adname= request.getParameter("adname");
		}
		String beginTime="";
		if(StringUtil.isNotEmpty(request.getParameter("beginTime"))){
			logger.info("the request beginTime is :"+request.getParameter("beginTime"));
			beginTime= request.getParameter("beginTime");
		}
		String endTime="";
		if(StringUtil.isNotEmpty(request.getParameter("endTime"))){
			logger.info("the request endTime is :"+request.getParameter("endTime"));
			endTime= request.getParameter("endTime");
		}
		String fund="";
		if(StringUtil.isNotEmpty(request.getParameter("fund"))){
			logger.info("the request fund is :"+request.getParameter("fund"));
			fund= request.getParameter("fund");
		}
		String detail="";
		if(StringUtil.isNotEmpty(request.getParameter("detail"))){
			logger.info("the request detail is :"+request.getParameter("detail"));
			detail= request.getParameter("detail");
		}
		
	    Ad entity;
	    if (ad.getId() == null) {
	      entity = ad;
	      entity.setAdId(adId);
	      entity.setAdverId(adverId);
	      entity.setAdname(adname);
	      entity.setBeginTime(beginTime);
	      entity.setEndTime(endTime);
	      entity.setAdKey(apiKey);
	      entity.setStatus(Globals.Zero_Int);
	      entity.setFund(Integer.parseInt(fund));
	      entity.setFundOutput(Globals.Zero_Int);
	      entity.setDetail(detail);
	      entity.setCreateTime(DateUtil.getCurrentTime());
	      entity.setLastTime(DateUtil.getCurrentTime());
	      systemService.save(ad);
	    } else {
	      entity = systemService.getEntity(Ad.class, ad.getId());
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
