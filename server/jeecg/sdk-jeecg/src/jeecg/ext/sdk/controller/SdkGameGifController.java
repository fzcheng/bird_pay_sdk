package jeecg.ext.sdk.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.SdkGame;
import jeecg.ext.sdk.entity.SdkGameGif;
import jeecg.ext.sdk.entity.SdkGift;
import jeecg.ext.sdk.entity.SdkGiftVcode;
import jeecg.ext.sdk.entity.SdkGiftVcodeId;
import jeecg.ext.sdk.entity.SdkPlan;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sdkGameGif")
public class SdkGameGifController extends BaseController {

	@Autowired
	private SystemService systemService;
	
	@RequestMapping(params="list")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response){
		if(StringUtil.isNotEmpty(request.getParameter("gameId"))){
			request.setAttribute("gameId", request.getParameter("gameId"));
		}
		return new ModelAndView("sdkGameGif/list");
	}
	
	@RequestMapping(params="datagrid")
	public void datagrid(SdkGift sdkGift,HttpServletRequest request,HttpServletResponse response, DataGrid dataGrid){
		String gameId=request.getParameter("gameId");
		CriteriaQuery cq=new CriteriaQuery(SdkGift.class,dataGrid);
		cq.eq("gameId", Integer.valueOf(gameId));
		cq.add();
		String title=request.getParameter("title");
		if(StringUtils.isNotEmpty(title)&&StringUtils.isNotBlank(title)){
			cq.add(Restrictions.like("title", "%"+title+"%"));
		}	
		HqlGenerateUtil.installHql(cq, sdkGift);
		this.systemService.getDataGridReturn(cq, true);
		SdkGameGif sdkGameGif=new SdkGameGif();
		dataGrid.setReaults(sdkGameGif.getSdkGameGifList(systemService,dataGrid.getReaults()));
		TagUtil.datagrid(response, dataGrid);
	}
	
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(String id,HttpServletRequest request){
		String gameId=request.getParameter("gameId");
		if(StringUtil.isNotEmpty(id)){
			SdkGift sdkGift=this.systemService.getEntity(SdkGift.class, Integer.valueOf(id));
			request.setAttribute("sdkGift", sdkGift);
		}
		request.setAttribute("gameId", gameId);
		return new ModelAndView("sdkGameGif/add");
	}
	
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(HttpServletRequest request,SdkGift sdkGift,String endDate){
		AjaxJson json=new AjaxJson();
		if(StringUtil.isNotEmpty(sdkGift.getGiftId())){
			
		}else {
			try {
				String vcodes=sdkGift.getVcodes();
				List<String> codeList=new ArrayList<String>();
				int count=1;
				if(vcodes.split(",").length>0){
					count=vcodes.split(",").length;
					codeList=Arrays.asList(vcodes.split(","));
				}
				sdkGift.setBeginTime(new Timestamp(System.currentTimeMillis()));
				sdkGift.setCreateTime(new Timestamp(System.currentTimeMillis()));
	 
				sdkGift.setEndTime(Timestamp.valueOf(endDate));
				sdkGift.setTotal(count);
				sdkGift.setRemain(count);
				// 保存
				this.systemService.save(sdkGift);
				int totalGift=0;
				for(String vcode:codeList){
					if(StringUtil.isNotEmpty(vcode)){
						SdkGiftVcodeId sdkGiftVcodeId=new SdkGiftVcodeId();
						sdkGiftVcodeId.setGiftId(sdkGift.getGiftId());
						sdkGiftVcodeId.setVcode(vcode);
						SdkGiftVcode sdkGiftVcode=new SdkGiftVcode();
						sdkGiftVcode.setId(sdkGiftVcodeId);
						try {
							this.systemService.save(sdkGiftVcode);
							totalGift++;
						} catch (Exception e) {
							 
						}
						
					}
				}
				sdkGift.setTotal(totalGift);
				sdkGift.setRemain(totalGift);
				// 更新
				this.systemService.saveOrUpdate(sdkGift);
				
				systemService.addLog("礼包"+sdkGift.getTitle()+"添加成功", Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
				json.setMsg("礼包"+sdkGift.getTitle()+"添加成功");
			} catch (Exception e) {
				systemService.addLog("礼包添加失败", Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
				json.setMsg("添加失败");
			}
			
		}
		return json;
	}
	
	@RequestMapping(params = "del", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson del(HttpServletRequest request,String gameId, String id){
		AjaxJson json=new AjaxJson();
		if(StringUtil.isNotEmpty(id)){
			SdkGift sdkGift=this.systemService.getEntity(SdkGift.class, Integer.valueOf(id));
			if(sdkGift!=null){
				// 删除之前
				try {
					CriteriaQuery cq=new CriteriaQuery(SdkGiftVcode.class);
					cq.eq("id.giftId", sdkGift.getGiftId());
					cq.add();
					List<SdkGiftVcode> sdkGiftVcodes=this.systemService.getListByCriteriaQuery(cq, false);
					// 删除
					this.systemService.deleteAllEntitie(sdkGiftVcodes);
					this.systemService.delete(sdkGift);
					systemService.addLog("礼包"+sdkGift.getTitle()+"删除成功", Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
					json.setMsg("删除成功");
				} catch (Exception e) {
					systemService.addLog("礼包删除失败", Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
					json.setMsg("删除失败");
				}
				
			}
		}
		
		return json;
	}
	
}
