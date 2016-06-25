package jeecg.ext.sdk.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.SdkGame;
import jeecg.ext.sdk.entity.SdkGamePayrepeat;
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
@RequestMapping("/sdkGamePayrepeat")
public class SdkGamePayrepeatController extends BaseController {
	private static final Logger logger = Logger.getLogger(SdkGamePayrepeatController.class);

	@Autowired
	private SystemService systemService;

	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sdkGamePayrepeat/list");

		List<SdkGame> games = this.systemService.getList(SdkGame.class);
		
		// gameReplace
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
		CriteriaQuery cq = new CriteriaQuery(SdkGamePayrepeat.class, dataGrid);
		String gameId = request.getParameter("gameId");
		String usestate = request.getParameter("usestate");
		
		if (StringUtils.isNotBlank(gameId) && StringUtils.isNotEmpty(gameId)) {
			cq.add(Restrictions.eq("gameId", parseInteger(gameId)));
		}
		if (StringUtils.isNotBlank(usestate) && StringUtils.isNotEmpty(usestate)) {
			cq.add(Restrictions.eq("usestate", parseInteger(usestate)));
		}

		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(String id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sdkGamePayrepeat/addorupdate");
		Integer idInt = parseInteger(id);
		if (idInt != null) {
			SdkGamePayrepeat sdkGamePayrepeat = systemService.getEntity(SdkGamePayrepeat.class, idInt);
			mv.addObject("sdkGamePayrepeat", sdkGamePayrepeat);
		}
		
		List<SdkGame> games = this.systemService.getList(SdkGame.class);
		mv.addObject("games", games);
		
		return mv;
	}

	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(SdkGamePayrepeat sdkGamePayrepeat) {
		AjaxJson json = new AjaxJson();
		try {
			SdkGamePayrepeat entity = null;
			if (sdkGamePayrepeat.getId() != null) {
				entity = systemService.getEntity(SdkGamePayrepeat.class, sdkGamePayrepeat.getId());
			}

			Date now = new Date();
			if (entity != null) {
				entity.setGameId(sdkGamePayrepeat.getGameId());
				entity.setUsestate(sdkGamePayrepeat.getUsestate());
				entity.setUpdatedTime(now);
			} else {
				entity = sdkGamePayrepeat;
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

		SdkGamePayrepeat entity = systemService.getEntity(SdkGamePayrepeat.class, idInt);
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
