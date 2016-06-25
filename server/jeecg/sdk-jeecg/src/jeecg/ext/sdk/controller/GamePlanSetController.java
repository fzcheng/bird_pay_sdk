package jeecg.ext.sdk.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.SdkGame;
import jeecg.ext.sdk.entity.SdkGamePlanSet;
import jeecg.ext.sdk.entity.SdkGameUsedPlan;
import jeecg.ext.sdk.entity.SdkPlan;
import jeecg.system.service.SystemService;

import org.apache.bcel.generic.IF_ACMPEQ;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.sql.ast.expr.SQLAggregateExpr;

@Controller
@RequestMapping("/gamePlanSet")
public class GamePlanSetController extends BaseController {

	static Log logger=LogFactory.getLog(GamePlanSetController.class);
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@Autowired
	private SystemService systemService;
	
	@RequestMapping(params="list")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response){
		
		return new ModelAndView("gamePlanSet/list");
	}
	
	@RequestMapping(params="datagrid")
	public void datagrid(SdkGamePlanSet sdkGamePlanSet,HttpServletRequest request,HttpServletResponse response, DataGrid dataGrid){
		SdkGame sdkGame=new SdkGame();
		if(sdkGamePlanSet!=null && StringUtil.isNotEmpty(sdkGamePlanSet.getGameName())){
			sdkGame.setName("*"+sdkGamePlanSet.getGameName()+"*");
		}
		if(StringUtil.isNotEmpty(dataGrid.getSort())){
			String orderName=dataGrid.getSort();
			if(orderName.equals("gameName")){
				orderName="name"; 
			}else if(orderName.equals("gamePackageName")){
				orderName="packageName";
			}else if (orderName.equals("cpName")) {
				orderName=null;
			}
			else if (orderName.equals("usedPlan")) {
				orderName=null;
			}
			dataGrid.setSort(orderName);
		}
		CriteriaQuery cq=new CriteriaQuery(SdkGame.class,dataGrid);
		String gameName=request.getParameter("gameName");
		HqlGenerateUtil.installHql(cq, sdkGame);
		this.systemService.getDataGridReturn(cq, true);
		List<SdkGamePlanSet> sdkGamePlanSets=SdkGamePlanSet.getUsedList(dataGrid.getReaults());
		dataGrid.setReaults(sdkGamePlanSets);
		TagUtil.datagrid(response, dataGrid);
	}
	@RequestMapping(params="setPlan")
	public ModelAndView setPlan(String id,HttpServletRequest request){
		if(StringUtil.isNotEmpty(id)){
			request.setAttribute("gameId", id);
			SdkGame sdkGame=this.systemService.getEntity(SdkGame.class,Integer.valueOf(id));
			if(sdkGame!=null && sdkGame.getSdkGameUsedPlans()!=null && sdkGame.getSdkGameUsedPlans().size()>0){
				List<SdkGameUsedPlan> sdkGameUsedPlans=new ArrayList<SdkGameUsedPlan>(sdkGame.getSdkGameUsedPlans());
				Integer selectPlanId=sdkGameUsedPlans.get(0).getSdkPlan().getId();
				request.setAttribute("selectPlanId", selectPlanId);
			}
		}
		List<SdkPlan> sdkPlans=this.systemService.getList(SdkPlan.class);
		request.setAttribute("sdkPlans", sdkPlans);
		return new ModelAndView("gamePlanSet/set");
	}
	@RequestMapping(params="save")
	@ResponseBody
	public AjaxJson save(String gameId,String selectPlan,HttpServletRequest request){
		AjaxJson json=new AjaxJson();
		SdkGameUsedPlan sdkGameUsedPlan=this.systemService.findUniqueByProperty(SdkGameUsedPlan.class, "sdkGame.gameId", Integer.valueOf(gameId));
		SdkPlan sdkPlan=this.systemService.get(SdkPlan.class, Integer.valueOf(selectPlan));
		if(sdkGameUsedPlan!=null){
			 sdkGameUsedPlan.setSdkPlan(sdkPlan);
			 this.systemService.saveOrUpdate(sdkGameUsedPlan);
			 message="游戏"+sdkGameUsedPlan.getSdkGame().getName()+"使用"+sdkGameUsedPlan.getSdkPlan().getName()+"方案更新成功";
			 systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			 json.setMsg(message);
		}else{
			sdkGameUsedPlan=new SdkGameUsedPlan();
			sdkGameUsedPlan.setSdkPlan(sdkPlan);
			SdkGame sdkGame=this.systemService.getEntity(SdkGame.class, Integer.valueOf(gameId));
			sdkGameUsedPlan.setSdkGame(sdkGame);
			this.systemService.save(sdkGameUsedPlan);
			message="游戏"+sdkGameUsedPlan.getSdkGame().getName()+"使用"+sdkGameUsedPlan.getSdkPlan().getName()+"方案保存成功";
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			json.setMsg(message);
		}
		
		
		return json;
	}
	
	
	
	
	
}
