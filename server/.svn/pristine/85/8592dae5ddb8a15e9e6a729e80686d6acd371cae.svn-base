package jeecg.ext.sdk.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.GmiPkg;
import jeecg.ext.sdk.entity.SdkBulletin;
import jeecg.ext.sdk.entity.SdkNewServer;
import jeecg.ext.sdk.entity.SdkPlan;
import jeecg.ext.tools.ImageHelper;
import jeecg.ext.tools.TagUtilExt;
import jeecg.system.service.SystemService;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/sdkBulletin")
public class SdkBulletinController  extends BaseController{

	@Autowired
	private SystemService systemService;
	
	@RequestMapping(params="list")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response){
		List<SdkBulletin> sdkBulletins=this.systemService.getList(SdkBulletin.class);
		if(sdkBulletins!=null && sdkBulletins.size()>0){
			request.setAttribute("checkCount", sdkBulletins.size());
		}
		return new ModelAndView("sdkBulletin/list");
	}
	
	
	@RequestMapping(params="datagrid")
	public void datagrid(SdkBulletin	 sdkBulletin,HttpServletRequest request,HttpServletResponse response, DataGrid dataGrid){
		
		CriteriaQuery cq=new CriteriaQuery(SdkBulletin.class,dataGrid);
		HqlGenerateUtil.installHql(cq, sdkBulletin);
		this.systemService.getDataGridReturn(cq, true);
		TagUtilExt.datagrid(response, dataGrid,"bulletinId");
	}
	
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(String id,HttpServletRequest request){
		if(StringUtil.isNotEmpty(id)){
			SdkBulletin sdkBulletin=this.systemService.getEntity(SdkBulletin.class, Integer.valueOf(id));
			request.setAttribute("sdkBulletin", sdkBulletin);
		}
		return new ModelAndView("sdkBulletin/add");
	}
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(HttpServletRequest request,SdkBulletin	 sdkBulletin){
		AjaxJson json=new AjaxJson();
		if(sdkBulletin.getType()==2 && !StringUtil.isNotEmpty(sdkBulletin.getGamePkg())){
			json.setMsg("请输入游戏包名");
			return json;
		}else if (sdkBulletin.getType()==1 && !StringUtil.isNotEmpty(sdkBulletin.getGameUrl())) {
			json.setMsg("请输入Url");
			return json;
		}
		if(sdkBulletin.getGameUrl().indexOf("http")<0){
			sdkBulletin.setGameUrl("http://"+sdkBulletin.getGameUrl());
		}
		sdkBulletin.setCreateTime(new Timestamp(System.currentTimeMillis()));
		if(StringUtil.isNotEmpty(sdkBulletin.getBulletinId())){
			if(StringUtil.isNotEmpty(sdkBulletin.getGamePkg()) && sdkBulletin.getType()==2){
				// 
				GmiPkg gmiPkg=this.systemService.findUniqueByProperty(GmiPkg.class, "pkg", sdkBulletin.getGamePkg());
				sdkBulletin.setGameUrl("");
				if(gmiPkg!=null){
					sdkBulletin.setGameCategory(gmiPkg.getCategory());
					sdkBulletin.setGameIcon(ImageHelper.getImageUrl(ImageHelper.getIconImagePath(gmiPkg.getPkg())));
					sdkBulletin.setGameName(gmiPkg.getName());
					sdkBulletin.setGameRating(gmiPkg.getStar());
					sdkBulletin.setStatus(1);
					this.systemService.saveOrUpdate(sdkBulletin);
					systemService.addLog("公告"+sdkBulletin.getDetail()+"添加成功", Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
					json.setMsg("公告"+sdkBulletin.getDetail()+"添加成功");
				}else{
					json.setMsg("不存在此游戏");
				}
				 
			}else{
				if(!StringUtil.isNotEmpty(sdkBulletin.getGameUrl()) && sdkBulletin.getType()==1){
					json.setMsg("请输入url");
				}else{
					sdkBulletin.setGamePkg("");
					sdkBulletin.setStatus(1);
					this.systemService.saveOrUpdate(sdkBulletin);
					systemService.addLog("公告"+sdkBulletin.getDetail()+"添加成功", Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
					json.setMsg("公告"+sdkBulletin.getDetail()+"添加成功");
				}
			}
		}else{
			if(StringUtil.isNotEmpty(sdkBulletin.getGamePkg())){
				GmiPkg gmiPkg=this.systemService.findUniqueByProperty(GmiPkg.class, "pkg", sdkBulletin.getGamePkg());
				sdkBulletin.setGameUrl("");
				if(gmiPkg!=null){
					sdkBulletin.setGameCategory(gmiPkg.getCategory());
					sdkBulletin.setGameIcon(ImageHelper.getImageUrl(ImageHelper.getIconImagePath(gmiPkg.getPkg())));
					sdkBulletin.setGameName(gmiPkg.getName());
					sdkBulletin.setGameRating(gmiPkg.getStar());
					sdkBulletin.setStatus(1);
					this.systemService.save(sdkBulletin);
					systemService.addLog("公告"+sdkBulletin.getDetail()+"添加成功", Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
					json.setMsg("公告"+sdkBulletin.getDetail()+"添加成功");
				}else{
					json.setMsg("不存在此游戏");
				}
				 
			}else{
				if(!StringUtil.isNotEmpty(sdkBulletin.getGameUrl()) && sdkBulletin.getType()==1){
					json.setMsg("请输入url");
				}else{
					sdkBulletin.setGamePkg("");
					sdkBulletin.setStatus(1);
					this.systemService.save(sdkBulletin);
					systemService.addLog("公告"+sdkBulletin.getDetail()+"添加成功", Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
					json.setMsg("公告"+sdkBulletin.getDetail()+"添加成功");
				}
			}
		}
		 
		
		return json;
	}
	
	@RequestMapping(params = "start", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson start(HttpServletRequest request,String  id){
		AjaxJson json=new AjaxJson();
		try{
			SdkBulletin sdkBulletin=this.systemService.getEntity(SdkBulletin.class, Integer.valueOf(id));
			if(sdkBulletin!=null){
				sdkBulletin.setStatus(1);
				this.systemService.saveOrUpdate(sdkBulletin);
				systemService.addLog("公告"+sdkBulletin.getDetail()+"启动成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
				json.setMsg("公告"+sdkBulletin.getDetail()+"启动成功");
			}
			
			json.setMsg("启动成功");
		}catch(Exception e){
			systemService.addLog("公告无法启动", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			json.setMsg("无法启动");
		}
		return json;
	}
	@RequestMapping(params = "stop", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson stop(HttpServletRequest request,String  id){
		AjaxJson json=new AjaxJson();
		try{
			SdkBulletin sdkBulletin=this.systemService.getEntity(SdkBulletin.class, Integer.valueOf(id));
			if(sdkBulletin!=null){
				sdkBulletin.setStatus(0);
				this.systemService.saveOrUpdate(sdkBulletin);
				systemService.addLog("公告"+sdkBulletin.getDetail()+"停止成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			}
			json.setMsg("停止成功");
		}catch(Exception e){
			systemService.addLog("公告无法停止", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			json.setMsg("无法停止");
		}
		return json;
	}
	
}
