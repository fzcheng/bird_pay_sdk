package jeecg.ext.sdk.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.SdkGame;
import jeecg.ext.sdk.entity.SdkGameAlipay;
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
@RequestMapping("/sdkGameAlipay")
public class SdkGameAlipayController extends BaseController {
	private static final Logger logger = Logger
			.getLogger(SdkGameAlipayController.class);

	@Autowired
	private SystemService systemService;

	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sdkGameAlipay/list");

		List<SdkGame> games = this.systemService.getList(SdkGame.class);
		if (games != null && games.size() > 0) {
			StringBuffer gameReplace = new StringBuffer();
			for (SdkGame game : games) {
				gameReplace.append(game.getName()).append("_")
						.append(game.getGameId()).append(",");
			}
			mv.addObject("gameReplace", gameReplace.toString());
		}
		return mv;
	}

	@RequestMapping(params = "datagrid")
	public void datagrid(HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(SdkGameAlipay.class, dataGrid);

		String sdkGame_gameId = request.getParameter("sdkGame_gameId");
		if (StringUtils.isNotBlank(sdkGame_gameId)
				&& StringUtils.isNotEmpty(sdkGame_gameId)) {
			cq.add(Restrictions.eq("sdkGame.gameId",
					parseInteger(sdkGame_gameId)));
		}

		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(String id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sdkGameAlipay/addorupdate");
		Integer idInt = parseInteger(id);
		if (idInt != null) {
			SdkGameAlipay sdkGameAlipay = systemService.getEntity(
					SdkGameAlipay.class, idInt);
			mv.addObject("sdkGameAlipay", sdkGameAlipay);
		}

		List<SdkGame> games = this.systemService.getList(SdkGame.class);
		mv.addObject("games", games);

		return mv;
	}

	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(SdkGameAlipay sdkGameAlipay, HttpServletRequest request) {
		AjaxJson json = new AjaxJson();
		try {
			SdkGameAlipay entity = null;
			if (sdkGameAlipay.getId() != null) {
				entity = systemService.getEntity(SdkGameAlipay.class,
						sdkGameAlipay.getId());
			}

			Date now = new Date();
			if (entity != null) {
				String gameId = request.getParameter("gameId");
				int gameIdInt = parseInteger(gameId);
				SdkGame sdkGame = this.systemService.getEntity(SdkGame.class,
						gameIdInt);
				entity.setSdkGame(sdkGame);
				entity.setPartner(sdkGameAlipay.getPartner());
				entity.setSeller(sdkGameAlipay.getSeller());
				entity.setNotifyUrl(sdkGameAlipay.getNotifyUrl());
				entity.setRsaPrivate(sdkGameAlipay.getRsaPrivate());
				entity.setRsaAlipayPublic(sdkGameAlipay.getRsaAlipayPublic());
				entity.setUpdatedTime(now);
			} else {
				String gameId = request.getParameter("gameId");
				int gameIdInt = parseInteger(gameId);
				SdkGame sdkGame = this.systemService.getEntity(SdkGame.class,
						gameIdInt);
				entity=new SdkGameAlipay();
				entity.setSdkGame(sdkGame);
				String partner = request.getParameter("partner");
				entity.setPartner(partner);
				String seller = request.getParameter("seller");
				entity.setSeller(seller);
				String notifyUrl = request.getParameter("notifyUrl");
				entity.setNotifyUrl(notifyUrl);
				String rsaPrivate = request.getParameter("rsaPrivate");
				entity.setRsaPrivate(rsaPrivate);
				String rsaAlipayPublic = request.getParameter("rsaAlipayPublic");
				entity.setRsaAlipayPublic(rsaAlipayPublic);
				entity.setCreatedTime(now);
			}

			this.systemService.saveOrUpdate(entity);
			json.setMsg("保存成功");
		} catch (Exception e) {
			json.setMsg("保存异常");
		}

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

	@RequestMapping(params = "del", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson del(String id) {
		AjaxJson json = new AjaxJson();
		Integer idInt = parseInteger(id);
		if (idInt == null) {
			json.setMsg("请选择要删除的项目");
			return json;
		}

		SdkGameAlipay entity = systemService.getEntity(SdkGameAlipay.class,
				idInt);
		if (entity == null) {
			json.setMsg("id=" + id + "，记录不存在");
			return json;
		}

		systemService.delete(entity);
		json.setMsg("删除成功");

		return json;
	}

}
