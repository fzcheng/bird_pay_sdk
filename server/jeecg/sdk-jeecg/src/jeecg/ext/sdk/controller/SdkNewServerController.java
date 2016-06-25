package jeecg.ext.sdk.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.GmiPkg;
import jeecg.ext.sdk.entity.SdkNewServer;
import jeecg.ext.sdk.entity.SdkPlan;
import jeecg.ext.tools.ImageHelper;
import jeecg.ext.tools.TagUtilExt;
import jeecg.system.service.SystemService;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
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
@RequestMapping("/sdkNewServer")
public class SdkNewServerController  extends BaseController{

	@Autowired
	private SystemService systemService;
	
	@RequestMapping(params="list")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response){
		
		return new ModelAndView("sdkNewServer/list");
	}
	
	@RequestMapping(params="datagrid")
	public void datagrid(SdkNewServer	 sdkNewServer,HttpServletRequest request,HttpServletResponse response, DataGrid dataGrid){
		
		CriteriaQuery cq=new CriteriaQuery(SdkNewServer.class,dataGrid);
		String gameName=request.getParameter("gameName");
		if(StringUtils.isNotEmpty(gameName)&&StringUtils.isNotBlank(gameName)){
			cq.add(Restrictions.like("gameName", "%"+gameName+"%"));
		}
		String ctBegin = request.getParameter("startTime_begin");
		String ctEnd = request.getParameter("startTime_end");
		if(StringUtil.isNotEmpty(ctBegin) && StringUtil.isNotEmpty(ctEnd) ){
			try {
			cq.ge("startTime", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(ctBegin));
			cq.le("startTime", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(ctEnd));
			} catch (ParseException e) {
			e.printStackTrace();
			}
			cq.add();
			}
		cq.addOrder("createTime", SortDirection.desc);
		HqlGenerateUtil.installHql(cq, sdkNewServer);
		this.systemService.getDataGridReturn(cq, true);
		TagUtilExt.datagrid(response, dataGrid,"nsId");
	}
	
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(String id,HttpServletRequest request){
		if(StringUtil.isNotEmpty(id)){
			SdkNewServer sdkNewServer=this.systemService.getEntity(SdkNewServer.class, Integer.valueOf(id));
			request.setAttribute("sdkNewServer", sdkNewServer);
		}
		return new ModelAndView("sdkNewServer/add");
	}
	
	@RequestMapping(params="save")
	@ResponseBody
	public AjaxJson save(HttpServletRequest request,SdkNewServer sdkNewServer,String startDate){
		AjaxJson json=new AjaxJson();
		if(StringUtil.isNotEmpty(sdkNewServer.getNsId())){
			this.systemService.saveOrUpdate(sdkNewServer);
			systemService.addLog("新服"+sdkNewServer.getGameName()+"更新完成", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			json.setMsg("新服"+sdkNewServer.getGameName()+"更新完成");
		}else {
			GmiPkg gmiPkg=this.systemService.findUniqueByProperty(GmiPkg.class, "pkg", sdkNewServer.getGamePkg());
			if(gmiPkg==null){
				json.setMsg("此游戏不存在");
			}else{
				sdkNewServer.setCreateTime(new Timestamp(System.currentTimeMillis()));
				sdkNewServer.setGameDl(gmiPkg.getDownUrl());
				
				sdkNewServer.setGameIcon(ImageHelper.getImageUrl(ImageHelper.getIconImagePath(gmiPkg.getPkg())));
				sdkNewServer.setGameName(gmiPkg.getName());
				sdkNewServer.setStartTime(Timestamp.valueOf(startDate));
				this.systemService.save(sdkNewServer);
				systemService.addLog("新服"+sdkNewServer.getGameName()+"添加完成", Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
				json.setMsg("添加完成");
				
			}
		}
		return json;
	}
	@RequestMapping(params = "del", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson del(HttpServletRequest request,String id){
		AjaxJson json=new AjaxJson();
		try{
			SdkNewServer sdkNewServer=this.systemService.getEntity(SdkNewServer.class, Integer.valueOf(id));
			this.systemService.delete(sdkNewServer);
			systemService.addLog("新服"+sdkNewServer.getGameName()+"删除成功", Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			json.setMsg("删除成功");
		}catch(Exception e){
			json.setMsg("无法删除");
		}
		return json;
	}
	
}
