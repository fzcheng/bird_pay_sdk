package jeecg.ext.sdk.controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.SdkGame;
import jeecg.ext.sdk.entity.SdkInformation;
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
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sdkInformation")
public class SdkInformationController   extends BaseController{

	@Autowired
	private SystemService systemService;
	
	static Log logger=LogFactory.getLog(SdkInformationController.class);
	
	
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response){
		//return new ModelAndView("gameInfo/gameinfo_list");
		if(request.getParameter("gameId")!=null){
			request.setAttribute("gameId", request.getParameter("gameId"));
		}
		return new ModelAndView("sdkInformation/list");
	}
	
	@RequestMapping(params = "datagrid")
	public void datagrid(SdkGame sdkGame,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid){
			CriteriaQuery cq=new CriteriaQuery(SdkGame.class,dataGrid);
			if(StringUtil.isNotEmpty(sdkGame.getName())){
				sdkGame.setName("*"+sdkGame.getName()+"*");
			}
			HqlGenerateUtil.installHql(cq, sdkGame);
			this.systemService.getDataGridReturn(cq, true);
			TagUtilExt.datagrid(response, dataGrid, "gameId");
	}
	
	@RequestMapping(params = "list2")
	public ModelAndView list2(HttpServletRequest request,HttpServletResponse response){
		//return new ModelAndView("gameInfo/gameinfo_list");
		if(request.getParameter("gameId")!=null){
			request.setAttribute("gameId", request.getParameter("gameId"));
		}
		return new ModelAndView("sdkInformation/list2");
	}
	
	@RequestMapping(params = "datagrid2")
	public void datagrid2(SdkInformation sdkInformation,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid){
			CriteriaQuery cq=new CriteriaQuery(SdkInformation.class,dataGrid);
			String gameId=request.getParameter("gameId");
			cq.eq("gameId", oConvertUtils.getInt(gameId));
			cq.add();
			cq.addOrder("createTime", SortDirection.desc);
			HqlGenerateUtil.installHql(cq, sdkInformation);
			this.systemService.getDataGridReturn(cq, true);
			TagUtilExt.datagrid(response, dataGrid, "infoId");
	}
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(String id,HttpServletRequest req){
		if(StringUtil.isNotEmpty(id)){
			SdkInformation sdkInformation=this.systemService.getEntity(SdkInformation.class, oConvertUtils.getInt(id));
			if(sdkInformation!=null){
				req.setAttribute("sdkInformation", sdkInformation);
			}
		}
		req.setAttribute("gameId", req.getParameter("gameId"));
		return new ModelAndView("sdkInformation/add");
	}
	
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(HttpServletRequest req,SdkInformation sdkInformation){
		AjaxJson json=new AjaxJson();
		sdkInformation.setCreateTime(new Timestamp(System.currentTimeMillis()));
		sdkInformation.setStatus(1);
		if(StringUtil.isNotEmpty(sdkInformation.getInfoId())){
			this.systemService.saveOrUpdate(sdkInformation);
			systemService.addLog(sdkInformation.getTitle()+"资讯更新成功 ", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			json.setMsg("更新成功");
		}else {
			this.systemService.save(sdkInformation);
			systemService.addLog(sdkInformation.getTitle()+"资讯保存成功 ", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			json.setMsg("保存成功");
		}
		return json;
	}
	@RequestMapping(params = "del", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson del(HttpServletRequest request,SdkInformation sdkInformation){
		AjaxJson json=new AjaxJson();
		if(StringUtil.isNotEmpty(sdkInformation.getInfoId())){
			this.systemService.delete(sdkInformation);
			systemService.addLog("资讯删除成功 ", Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			json.setMsg("资讯删除成功 ");
		}
		return json;
	}
	
}
