package jeecg.ext.sdk.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.SdkGame;
import jeecg.ext.sdk.entity.SdkGameDomainForcp;
import jeecg.ext.sdk.entity.SdkMmdoSetting;
import jeecg.ext.sdk.entity.SdkOperatorPayChannel;
import jeecg.ext.sdk.entity.SdkGameDomain;
import jeecg.system.service.SystemService;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
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
import org.jeecgframework.core.common.controller.BaseController;

@Controller
@RequestMapping("/sdkGameDomainForCp")
public class SdkGameDomainForCPController extends BaseController {
	private static final Logger logger = Logger
			.getLogger(SdkGameDomainForCPController.class);

	@Autowired
	private SystemService systemService;

	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sdkGameDomainForCp/list");
		
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
		CriteriaQuery cq = new CriteriaQuery(SdkGameDomainForcp.class, dataGrid);
		String gameId = request.getParameter("gameId");
		if (StringUtils.isNotEmpty(gameId) && StringUtils.isNotBlank(gameId)) {
			cq.add(Restrictions.eq("gameId", parseInteger(gameId)));
		} 
		String domain = request.getParameter("domain");
		if (StringUtils.isNotEmpty(domain) && StringUtils.isNotBlank(domain)) {
			cq.add(Restrictions.like("domain", "%" + domain + "%"));
		}
		String status = request.getParameter("status");
		if (StringUtils.isNotEmpty(status) && StringUtils.isNotBlank(status)) {
			cq.add(Restrictions.eq("status", parseInteger(status)));
		}
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(String id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sdkGameDomainForCp/addorupdate");

		Integer idInt = parseInteger(id);
		if (idInt != null) {
			SdkGameDomainForcp sdkGameDomainForcp = systemService.getEntity(
					SdkGameDomainForcp.class, idInt);
			mv.addObject("sdkGameDomainForcp", sdkGameDomainForcp);
		}

		return mv;
	}

	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(SdkGameDomainForcp sdkGameDomainForcp) {
		AjaxJson json = new AjaxJson();
		try {
			SdkGameDomainForcp entity = null;
			if (sdkGameDomainForcp.getId() != null) {
				entity = systemService.getEntity(SdkGameDomainForcp.class,
						sdkGameDomainForcp.getId());
			}
			// SdkOperatorPayChannel entity =
			// systemService.findUniqueByProperty(SdkOperatorPayChannel.class,
			// "channelCode", payChannel.getChannelCode());
			// if (entity != null && (payChannel.getId() == null ||
			// !payChannel.getId().equals(entity.getId()))) {
			// json.setMsg("支付渠道号已存在");
			// return json;
			// }

			if (entity != null) {
				entity.setGameId(sdkGameDomainForcp.getGameId());
				entity.setDomain(sdkGameDomainForcp.getDomain());
				entity.setStatus(sdkGameDomainForcp.getStatus());
			} else {
				entity = sdkGameDomainForcp;
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

		SdkGameDomainForcp entity = systemService.getEntity(
				SdkGameDomainForcp.class, idInt);
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
