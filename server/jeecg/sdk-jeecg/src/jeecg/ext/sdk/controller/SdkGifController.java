package jeecg.ext.sdk.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.SdkGame;
import jeecg.ext.sdk.entity.SdkPlan;
import jeecg.ext.tools.TagUtilExt;
import jeecg.system.service.SystemService;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sdkGif")
public class SdkGifController  extends BaseController{

	@Autowired
	private SystemService systemService;
	
	@RequestMapping(params="list")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response){
		
		return new ModelAndView("sdkGif/list");
	}
	
	@RequestMapping(params="datagrid")
	public void datagrid(SdkGame sdkGame,HttpServletRequest request,HttpServletResponse response, DataGrid dataGrid){
		
		CriteriaQuery cq=new CriteriaQuery(SdkGame.class,dataGrid);
		if(StringUtil.isNotEmpty(sdkGame.getName())){
			sdkGame.setName("*"+sdkGame.getName()+"*");
		}
		//
		HqlGenerateUtil.installHql(cq, sdkGame);
		this.systemService.getDataGridReturn(cq, true);
		TagUtilExt.datagrid(response, dataGrid,"gameId");
	}
	
	
	
	
	
}
