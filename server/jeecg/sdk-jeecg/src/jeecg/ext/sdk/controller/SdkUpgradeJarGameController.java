package jeecg.ext.sdk.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.SdkChannelGameConfirm;
import jeecg.ext.sdk.entity.SdkGame;
import jeecg.ext.sdk.entity.SdkGameBlacklist;
import jeecg.ext.sdk.entity.SdkOperatorPayChannel;
import jeecg.ext.sdk.entity.SdkUpgradeJar;
import jeecg.ext.sdk.entity.SdkUpgradeJarGame;
import jeecg.system.service.SystemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.tag.core.easyui.TagUtil;

@Controller
@RequestMapping("/sdkUpgradeJarGame")
public class SdkUpgradeJarGameController extends BaseController {
	private static final Logger logger = Logger
			.getLogger(SdkUpgradeJarGameController.class);

	@Autowired
	private SystemService systemService;

	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("sdkUpgradeJarGame/list");

		List<SdkGame> games = this.systemService.getList(SdkGame.class);

		if (games != null && games.size() > 0) {
			StringBuffer gameReplace = new StringBuffer();
			for (SdkGame game : games) {
				gameReplace.append(game.getName()).append("_")
						.append(game.getGameId()).append(",");
			}
			mv.addObject("gameReplace", gameReplace.toString());
		} else {
			mv.addObject("gameReplace", "0_0");
		}
		return mv;
	}

	@RequestMapping(params = "datagrid")
	public void datagrid(HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(SdkUpgradeJarGame.class, dataGrid);
		String versionCode = request.getParameter("versionCode");
		if (StringUtils.isNotEmpty(versionCode)
				&& StringUtils.isNotBlank(versionCode)) {
			cq.add(Restrictions.like("versionCode", "%" + versionCode + "%"));
		}
		String gameId = request.getParameter("gameId");
		if (StringUtils.isNotEmpty(gameId) && StringUtils.isNotBlank(gameId)) {
			Integer i = parseInteger(gameId);
			cq.add(Restrictions.eq("gameId", i));
		}
		String statusTag = request.getParameter("statusTag");
		if (StringUtils.isNotEmpty(statusTag) && StringUtils.isNotBlank(statusTag)) {
			cq.add(Restrictions.eq("statusTag", parseInteger(statusTag)));
		}
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
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

	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(String id, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sdkUpgradeJarGame/addorupdate");
		Integer idInt = parseInteger(id);
		if (idInt != null) {
			SdkUpgradeJarGame sdkUpgradeJarGame = systemService
					.getEntity(SdkUpgradeJarGame.class, idInt);
			mv.addObject("sdkUpgradeJarGame", sdkUpgradeJarGame);
		}

		List<SdkGame> games = this.systemService.getList(SdkGame.class);
		mv.addObject("games", games);
		
		List<SdkUpgradeJar> sdkUpgradeJars=this.systemService.getList(SdkUpgradeJar.class);
		mv.addObject("sdkUpgradeJars",sdkUpgradeJars);
		return mv;
	}

	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(SdkUpgradeJarGame sdkUpgradeJarGame) {
		AjaxJson json = new AjaxJson();
		try {
			SdkUpgradeJarGame entity=null;
			if (sdkUpgradeJarGame.getId() != null) {
				entity = systemService.getEntity(SdkUpgradeJarGame.class,
						sdkUpgradeJarGame.getId());
			}
			Date now = new Date();
			if (entity != null) {
				entity.setGameId(sdkUpgradeJarGame.getGameId());
				entity.setVersionCode(sdkUpgradeJarGame.getVersionCode());
				entity.setStatusTag(sdkUpgradeJarGame.getStatusTag());
				entity.setUpdatedTime(now);
			} else {
				entity = sdkUpgradeJarGame;
				entity.setCreateTime(now);
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

		SdkUpgradeJarGame entity = systemService.getEntity(
				SdkUpgradeJarGame.class, idInt);
		if (entity == null) {
			json.setMsg("id=" + id + "，记录不存在");
			return json;
		}

		systemService.delete(entity);
		json.setMsg("删除成功");

		return json;
	}
}
