package jeecg.ext.sdk.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.SdkGame;
import jeecg.ext.sdk.entity.SdkSwbInfo;
import jeecg.system.service.SystemService;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sdkSwbInfo")
public class SdkSwbInfoController extends BaseController {
	private static final Logger logger = Logger.getLogger(SdkSwbInfoController.class);

	@Autowired
	private SystemService systemService;

	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sdkSwbInfo/list");

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
		return mv;
	}

	@RequestMapping(params = "datagrid")
	public void datagrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(SdkSwbInfo.class, dataGrid);
		String operatorType = request.getParameter("operatorType");

		if (StringUtils.isNotBlank(operatorType) && StringUtils.isNotEmpty(operatorType)) {
			cq.add(Restrictions.eq("operatorType", parseInteger(operatorType)));
		}

		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(String id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sdkSwbInfo/addorupdate");
		Integer idInt = parseInteger(id);
		if (idInt != null) {
			SdkSwbInfo sdkSwbInfo = systemService.getEntity(SdkSwbInfo.class, idInt);
			mv.addObject("sdkSwbInfo", sdkSwbInfo);
		}
		
		List<SdkGame> games = this.systemService.getList(SdkGame.class);
		mv.addObject("games", games);
		
		return mv;
	}

	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(SdkSwbInfo sdkSwbInfo) {
		AjaxJson json = new AjaxJson();
		try {
			SdkSwbInfo entity = null;
			if (sdkSwbInfo.getId() != null) {
				entity = systemService.getEntity(SdkSwbInfo.class, sdkSwbInfo.getId());
			}

			Date now = new Date();
			if (entity != null) {
				entity.setGameId(sdkSwbInfo.getGameId());
				entity.setAppId(sdkSwbInfo.getAppId());
				entity.setAppKey(sdkSwbInfo.getAppKey());
				entity.setOperatorType(sdkSwbInfo.getOperatorType());
				entity.setUseStatus(sdkSwbInfo.getUseStatus());
				entity.setChannel(sdkSwbInfo.getChannel());
				entity.setCompany(sdkSwbInfo.getCompany());
				entity.setCpCode(sdkSwbInfo.getCpCode());
				entity.setCpId(sdkSwbInfo.getCpId());
				entity.setPhone(sdkSwbInfo.getPhone());
				entity.setUpdatedTime(now);
			} else {
				entity = sdkSwbInfo;
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

		SdkSwbInfo entity = systemService.getEntity(SdkSwbInfo.class, idInt);
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
