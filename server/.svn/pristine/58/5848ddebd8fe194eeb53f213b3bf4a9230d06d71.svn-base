package jeecg.ext.sdk.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.SdkGame;
import jeecg.ext.sdk.entity.SdkWiipayPaycode;
import jeecg.system.service.SystemService;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/wiicode")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class WiipayCodeController  extends BaseController {
	static Log logger=LogFactory.getLog(WiipayCodeController.class);
	
	@Autowired
	private SystemService systemService;
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response){
		//return new ModelAndView("gameInfo/gameinfo_list");
		if(StringUtil.isNotEmpty(request.getParameter("gameId"))){
			request.setAttribute("gameId", request.getParameter("gameId"));
		}
		return new ModelAndView("wiicode/list");
	}
	
	
	@RequestMapping(params = "datagrid")
	public void datagrid(SdkWiipayPaycode wiiCodeData,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(SdkWiipayPaycode.class, dataGrid);
		String gameId=request.getParameter("gameId");
		cq.eq("sdkGame.gameId", Integer.valueOf(gameId));
		cq.add();
		String name=request.getParameter("name");
		if(StringUtils.isNotEmpty(name)&&StringUtils.isNotBlank(name)){
			logger.info("the name is :"+name);
			cq.add(Restrictions.ilike("name", "%"+name.trim()+"%"));
		}
		//查询条件组装器
		//org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, wiiCodeData);
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(String gameId,String id, HttpServletRequest req) {
		if(StringUtil.isNotEmpty(id)){
			// 编辑
			SdkWiipayPaycode sdkWiipayPaycode=this.systemService.getEntity(SdkWiipayPaycode.class, Integer.valueOf(id));
			if(sdkWiipayPaycode!=null){
				req.setAttribute("sdkWiipayPaycode", sdkWiipayPaycode);
			}
		} 
		req.setAttribute("gameId", gameId);
		return new ModelAndView("wiicode/add");
	}
	
	@RequestMapping(params="save")
	@ResponseBody
	public AjaxJson save(HttpServletRequest req, SdkWiipayPaycode wiiData){
		AjaxJson json=new AjaxJson();
		if(StringUtil.isNotEmpty(wiiData.getId())){
			this.systemService.saveOrUpdate(wiiData);
			systemService.addLog("微派计费"+wiiData.getName()+"更新成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			json.setMsg("微派计费"+wiiData.getName()+"更新成功");
		}else{
			this.systemService.save(wiiData);
			systemService.addLog("微派计费"+wiiData.getName()+"添加成功", Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			json.setMsg("添加成功");
		}
		
		return json;
	}
	
	@RequestMapping(params = "del", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson del(HttpServletRequest request, SdkWiipayPaycode wiiCodeData) {
		AjaxJson j = new AjaxJson();
		systemService.delete(wiiCodeData);	
		systemService.addLog("删除计费: "+wiiCodeData.getName(), Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg("删除计费: "+wiiCodeData.getName()+"成功");
		
		return j;
	}
}
