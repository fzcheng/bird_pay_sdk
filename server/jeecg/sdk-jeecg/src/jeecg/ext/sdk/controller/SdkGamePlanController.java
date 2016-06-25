package jeecg.ext.sdk.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.GmiPkg;
import jeecg.ext.sdk.entity.SdkGamePlan;
import jeecg.ext.sdk.entity.SdkPlan;
import jeecg.ext.tools.ImageHelper;
import jeecg.system.service.SystemService;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sdkGamePlan")
public class SdkGamePlanController extends BaseController{

	@Autowired
	private SystemService systemService;
	
	@RequestMapping(params="list")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response){
		if(StringUtil.isNotEmpty(request.getParameter("planId"))){
			request.setAttribute("planId", request.getParameter("planId"));
		}
		return new ModelAndView("sdkGamePlan/list");
	}
	
	@RequestMapping(params="datagrid")
	public void datagrid(SdkGamePlan sdkGamePlan,HttpServletRequest request,HttpServletResponse response, DataGrid dataGrid){
		String planId=request.getParameter("planId");
		CriteriaQuery cq=new CriteriaQuery(SdkGamePlan.class,dataGrid);
		cq.eq("sdkPlan.id", Integer.valueOf(planId));
		cq.addOrder("idx",SortDirection.asc);
		cq.add();
		String gameName=request.getParameter("gameName");
		if(StringUtils.isNotEmpty(gameName)&&StringUtils.isNotBlank(gameName)){
			cq.add(Restrictions.like("gameName", "%"+gameName+"%"));
		}
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(String id,HttpServletRequest request){
		if(StringUtil.isNotEmpty(id)){
			SdkGamePlan sdkGamePlan=this.systemService.getEntity(SdkGamePlan.class, Integer.valueOf(id));
			request.setAttribute("sdkGamePlan", sdkGamePlan);
		}
		request.setAttribute("planId", request.getParameter("planId"));
		return new ModelAndView("sdkGamePlan/add");
	}
	@RequestMapping(params="save")
	@ResponseBody
	public AjaxJson save(HttpServletRequest request,SdkGamePlan sdkGamePlan){
		AjaxJson json=new AjaxJson();
		if(StringUtil.isNotEmpty(sdkGamePlan.getId())){
			SdkGamePlan tmp=this.systemService.getEntity(SdkGamePlan.class, Integer.valueOf(sdkGamePlan.getId()));
			GmiPkg gmiPkg=this.systemService.findUniqueByProperty(GmiPkg.class, "pkg", sdkGamePlan.getGamePkg());
			if(gmiPkg==null){
				json.setMsg("添加失败，不存在此游戏！");
				return json;
			}
			tmp.setGamePkg(sdkGamePlan.getGamePkg());
			tmp.setGameCategory(gmiPkg.getCategory());
			tmp.setGameUrl(gmiPkg.getDownUrl());
			
			tmp.setGameIcon(ImageHelper.getImageUrl(ImageHelper.getIconImagePath(gmiPkg.getPkg())));
			tmp.setGameName(gmiPkg.getName());
			tmp.setGameRating(gmiPkg.getStar());
			//tmp.setIdx(gmiPkg.getIdx());
			this.systemService.saveOrUpdate(tmp);
			
			systemService.addLog("游戏"+tmp.getGameName()+"更新成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			json.setMsg("游戏"+tmp.getGameName()+"更新成功");
		}else{
			GmiPkg gmiPkg=this.systemService.findUniqueByProperty(GmiPkg.class, "pkg", sdkGamePlan.getGamePkg());
			if(gmiPkg==null){
				json.setMsg("添加失败，不存在此游戏！");
				return json;
			}
			sdkGamePlan.setGameName(gmiPkg.getName());
			Object maxOrder=this.systemService.singleResult("select min(p.idx) from SdkGamePlan p where p.sdkPlan.id= "+sdkGamePlan.getSdkPlan().getId());
			int maxCount=0;
			if(maxOrder!=null){
				maxCount=Integer.valueOf(maxOrder.toString())-1;
			}else {
				maxCount=999;
			}
			sdkGamePlan.setIdx(maxCount);
			sdkGamePlan.setGameCategory(gmiPkg.getCategory());
			sdkGamePlan.setGameUrl(gmiPkg.getDownUrl());
			sdkGamePlan.setGameIcon(ImageHelper.getImageUrl(ImageHelper.getIconImagePath(gmiPkg.getPkg())));
			sdkGamePlan.setGameName(gmiPkg.getName());
			sdkGamePlan.setGameRating(gmiPkg.getStar());
			this.systemService.save(sdkGamePlan);
			systemService.addLog("游戏"+sdkGamePlan.getGameName()+"添加成功", Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			json.setMsg("添加成功");
		}
		return json;
	}
	
	@RequestMapping(params = "del", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson del(HttpServletRequest request,SdkGamePlan sdkGamePlan){
		AjaxJson json=new AjaxJson();
		try{
			this.systemService.delete(sdkGamePlan);
			systemService.addLog("方案删除成功", Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			json.setMsg("删除成功");
		}catch(Exception e){
			systemService.addLog("方案无法删除", Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			json.setMsg("无法删除");
		}
		return json;
	}
	
	
}
