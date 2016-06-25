package jeecg.ext.sdk.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.AdApp;
import jeecg.ext.sdk.entity.AdMission;
import jeecg.ext.sdk.entity.AdMissionSub;
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
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/adMissionSub")
public class AdMissionSubController  extends BaseController{
	static Log logger=LogFactory.getLog(AdMissionSubController.class);
	
	@Autowired
	private SystemService systemService;
	
	@RequestMapping(params="list")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response){
		if(request.getParameter("missionId")!=null){
			request.setAttribute("missionId", request.getParameter("missionId"));
		}
		return new ModelAndView("adMissionSub/list");
	}
	
	@RequestMapping(params="datagrid")
	public void datagrid(AdMissionSub adMissionSub,HttpServletRequest request,HttpServletResponse response, DataGrid dataGrid){
		
		CriteriaQuery cq=new CriteriaQuery(AdMissionSub.class,dataGrid);
		
		//查询条件组装器
		HqlGenerateUtil.installHql(cq, adMissionSub);
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
	 String missionSubId = GenerateSequenceUtil.generateSequenceNo();
	 AdMissionSub adMissionSub = new AdMissionSub();
	 if (StringUtil.isNotEmpty(id)) {
		 adMissionSub = systemService.getEntity(AdMissionSub.class, Integer.valueOf(id));
	   req.setAttribute("adMissionSub", adMissionSub);
	   missionSubId = adMissionSub.getAdMissionSub();
	 }
	 req.setAttribute("missionSubId", missionSubId);
	 if(req.getParameter("missionId")!=null){
		 req.setAttribute("missionId", req.getParameter("missionId"));
		}
	 return new ModelAndView("adMissionSub/add");
	}
	
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(AdMissionSub adMissionSub) {
		AjaxJson json = new AjaxJson();
		
		String missionSubId = GenerateSequenceUtil.generateSequenceNo();
		
		AdMissionSub entity;
		if (adMissionSub.getId() == null) {
			entity = adMissionSub;
			entity.setMissionId(missionSubId);
			entity.setCreateTime(DateUtil.getCurrentTime());
			entity.setLastTime(DateUtil.getCurrentTime());
			systemService.save(adMissionSub);
		} else {
			entity = systemService.getEntity(AdMissionSub.class, adMissionSub.getId());
			if (entity != null) {
				entity.setBirdMoney(adMissionSub.getBirdMoney());
				entity.setMissionDetail(adMissionSub.getMissionDetail());
				entity.setNo(adMissionSub.getNo());
				entity.setType(adMissionSub.getType());
				entity.setTypeValue(adMissionSub.getTypeValue());
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
