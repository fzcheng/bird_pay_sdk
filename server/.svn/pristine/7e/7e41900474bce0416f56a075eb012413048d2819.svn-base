package jeecg.ext.sdk.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.SdkChannelGameConfirm;
import jeecg.ext.sdk.entity.SdkCityAddr;
import jeecg.ext.sdk.entity.SdkGame;
import jeecg.ext.sdk.entity.SdkOperatorPayChannel;
import jeecg.ext.sdk.entity.SdkPropsSetting;
import jeecg.ext.sdk.entity.SdkProvinceAddr;
import jeecg.system.service.SystemService;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;

@Controller
@RequestMapping("/sdkPropsSetting")
public class SdkPropsSettingController extends BaseController {
	
	private static final Logger logger = Logger
			.getLogger(SdkPropsSettingController.class);
	
	@Autowired
	private SystemService systemService;

	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sdkPropsSetting/list");
		
		List<SdkGame> games = this.systemService.getList(SdkGame.class);
	    
	    if (games != null && games.size() > 0) {
	      StringBuffer gameReplace = new StringBuffer();
	      for (SdkGame game : games) {
	        gameReplace.append(game.getName()).append("_").append(game.getGameId()).append(",");
	      }
	      mv.addObject("gameReplace", gameReplace.toString());
	    } else {
	      mv.addObject("gameReplace", "0_0");
	    }

	    List<SdkOperatorPayChannel> payChannels = this.systemService.getList(SdkOperatorPayChannel.class);
	    if (payChannels != null && payChannels.size() > 0) {
	      StringBuffer channelReplace = new StringBuffer();
	      for (SdkOperatorPayChannel pc : payChannels) {
	        channelReplace.append(pc.getChannelName()).append("_").append(pc.getId()).append(",");
	      }
	      mv.addObject("channelReplace", channelReplace.toString());
	    } else {
	      mv.addObject("channelReplace", "0_0");
	    }
		return mv;
	}
	
	@RequestMapping(params = "datagrid")
	public void datagrid(HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(SdkPropsSetting.class,
				dataGrid);
		String operatorPayChannelId = request.getParameter("operatorPayChannelId");
		String gameId = request.getParameter("gameId");
		String propsName = request.getParameter("propsName");
		String propsAlias = request.getParameter("propsAlias");
		String amount = request.getParameter("amount");
		String useStatus = request.getParameter("useStatus");
		String propsDesc = request.getParameter("propsDesc");
		
		if (operatorPayChannelId != null) {
			if (StringUtils.isNotBlank(operatorPayChannelId)) {
				cq.add(Restrictions.eq("operatorPayChannelId", parseInteger(operatorPayChannelId)));
			}
		}
		if (gameId != null) {
			if (StringUtils.isNotBlank(gameId)) {
				cq.add(Restrictions.like("gameId", gameId));
			}
		}
		if (propsName != null) {
			cq.add(Restrictions.like("propsName", "%" + propsName.trim() + "%"));
		}
		if (propsAlias != null) {
			cq.add(Restrictions.like("propsAlias", "%" + propsAlias.trim() + "%"));
		}
		if (amount != null) {
			if(StringUtils.isNotBlank(amount)){
				float f=Float.parseFloat(amount);
				cq.add(Restrictions.eq("amount", f));
			}
		}
		if (useStatus != null) {
			if(StringUtils.isNotBlank(useStatus)){
				cq.add(Restrictions.eq("useStatus", parseInteger(useStatus)));
			}
//			cq.add(Restrictions.sqlRestriction("CAST({alias}.mobilephone AS CHAR) like ?", "%"+mobilephone.trim()+"%", StringType.INSTANCE));
		}
		if (propsDesc != null) {
			cq.add(Restrictions.like("propsDesc", "%" + propsDesc.trim() + "%"));
		}
		
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(String id, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sdkPropsSetting/addorupdate");
		Integer idInt = parseInteger(id);
		if (idInt != null) {
			SdkPropsSetting sdkPropsSetting=systemService
					.getEntity(SdkPropsSetting.class, idInt);
			mv.addObject("sdkPropsSetting", sdkPropsSetting);
		}

		List<SdkGame> games = this.systemService.getList(SdkGame.class);
		mv.addObject("games", games);

		List<SdkOperatorPayChannel> payChannels = this.systemService
				.getList(SdkOperatorPayChannel.class);
		mv.addObject("payChannels", payChannels);
		return mv;
	}
	
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(SdkPropsSetting sdkPropsSetting) {
		AjaxJson json = new AjaxJson();
		try {
			SdkPropsSetting entity=null;
			if (sdkPropsSetting.getId() != null) {
				entity = systemService.getEntity(SdkPropsSetting.class,
						sdkPropsSetting.getId());
			}
			Date now = new Date();
			if (entity != null) {
				entity.setGameId(sdkPropsSetting.getGameId());
				entity.setOperatorPayChannelId(sdkPropsSetting.getOperatorPayChannelId());
				entity.setPropsName(sdkPropsSetting.getPropsName());
				entity.setPropsAlias(sdkPropsSetting.getPropsAlias());
				entity.setAmount(sdkPropsSetting.getAmount());
				entity.setUseStatus(sdkPropsSetting.getUseStatus());
				entity.setUpdatedTime(now);
			} else {
				entity = sdkPropsSetting;
				entity.setCreatedTime(now);
			}

			this.systemService.saveOrUpdate(entity);
			json.setMsg("保存成功");
		} catch (Exception e) {
			json.setMsg("保存异常");
		}

		return json;
	}
	
	@RequestMapping(params = "del", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson del(String id) {
		AjaxJson json = new AjaxJson();
		Integer idInt = parseInteger(id);
		if (idInt == null) {
			json.setMsg("请选择要删除的项目");
			return json;
		}

		SdkPropsSetting entity=systemService.getEntity(
				SdkPropsSetting.class, idInt);
		if (entity == null) {
			json.setMsg("id=" + id + "，记录不存在");
			return json;
		}

		systemService.delete(entity);
		json.setMsg("删除成功");

		return json;
	}
	
	private Integer parseInteger(String str) {
		Integer num = null;
		try {
			if (StringUtils.isNotBlank(str)) {
				num = Integer.valueOf(str);
			}
		} catch (NumberFormatException e) {
			logger.warn("parse number string error! str = " + str);
		}
		return num;
	}
}
