package jeecg.ext.sdk.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.SdkChannelGameConfirm;
import jeecg.ext.sdk.entity.SdkGame;
import jeecg.ext.sdk.entity.SdkOperatorPayChannel;
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
@RequestMapping("/channelGameConfirm")
public class ChannelGameConfirmController extends BaseController {
	private static final Logger logger = Logger
			.getLogger(ChannelGameConfirmController.class);

	@Autowired
	private SystemService systemService;

	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) {
		return new ModelAndView("channelGameConfirm/list");
	}

	@RequestMapping(params = "datagrid")
	public void datagrid(HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(SdkChannelGameConfirm.class,
				dataGrid);
		String channelName = request.getParameter("channelName");
		String gameName = request.getParameter("gameName");
		String originalGameName = request.getParameter("originalGameName");
		String chargetip = request.getParameter("chargetip");
		String chargesuceesstip = request.getParameter("chargesuceesstip");
		if (channelName != null) {
			cq.add(Restrictions.like("channelName", "%" + channelName.trim() + "%"));
		}
		if (originalGameName != null) {
			cq.add(Restrictions.like("originalGameName", "%" + originalGameName.trim() + "%"));
		}
		if (gameName != null) {
			cq.add(Restrictions.like("gameName", "%" + gameName.trim() + "%"));
		}
		if (chargetip != null) {
			if(StringUtils.isNotBlank(chargetip)){
				cq.add(Restrictions.eq("chargetip", parseInteger(chargetip)));
			}
		}
		if (chargesuceesstip != null) {
			if(StringUtils.isNotBlank(chargesuceesstip)){
				cq.add(Restrictions.eq("chargesuceesstip", parseInteger(chargesuceesstip)));
			}
//			cq.add(Restrictions.sqlRestriction("CAST({alias}.mobilephone AS CHAR) like ?", "%"+mobilephone.trim()+"%", StringType.INSTANCE));
		}
		
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(String id, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("channelGameConfirm/addorupdate");
		Integer idInt = parseInteger(id);
		if (idInt != null) {
			SdkChannelGameConfirm sdkChannelGameConfirm = systemService
					.getEntity(SdkChannelGameConfirm.class, idInt);
			mv.addObject("sdkChannelGameConfirm", sdkChannelGameConfirm);
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
	public AjaxJson save(SdkChannelGameConfirm sdkChannelGameConfirm) {
		AjaxJson json = new AjaxJson();
		try {
			SdkChannelGameConfirm entity=null;
			if (sdkChannelGameConfirm.getId() != null) {
				entity = systemService.getEntity(SdkChannelGameConfirm.class,
						sdkChannelGameConfirm.getId());
			}
			Date now = new Date();
			if (entity != null) {
				SdkOperatorPayChannel sdkOperatorPayChannel=systemService.findUniqueByProperty(SdkOperatorPayChannel.class, "channelCode", sdkChannelGameConfirm.getChannelCode());
				entity.setChannelName(sdkOperatorPayChannel.getChannelName());
				SdkGame sdkGame=systemService.findUniqueByProperty(SdkGame.class, "gameId", sdkChannelGameConfirm.getGameId());
				entity.setGameName(sdkGame.getName());
				SdkGame originalGame=systemService.findUniqueByProperty(SdkGame.class, "gameId", sdkChannelGameConfirm.getOriginalGameId());
				entity.setOriginalGameName(originalGame.getName());
				entity.setChannelCode(sdkChannelGameConfirm.getChannelCode());
				entity.setGameId(sdkChannelGameConfirm.getGameId());
				entity.setChargetip(sdkChannelGameConfirm.getChargetip());
				entity.setChargesuceesstip(sdkChannelGameConfirm.getChargesuceesstip());
				entity.setUpdatedTime(now);
			} else {
				entity = sdkChannelGameConfirm;
				SdkOperatorPayChannel sdkOperatorPayChannel=systemService.findUniqueByProperty(SdkOperatorPayChannel.class, "channelCode", sdkChannelGameConfirm.getChannelCode());
				entity.setChannelName(sdkOperatorPayChannel.getChannelName());
				SdkGame sdkGame=systemService.findUniqueByProperty(SdkGame.class, "gameId", sdkChannelGameConfirm.getGameId());
				entity.setGameName(sdkGame.getName());
				SdkGame originalGame=systemService.findUniqueByProperty(SdkGame.class, "gameId", sdkChannelGameConfirm.getOriginalGameId());
				entity.setOriginalGameName(originalGame.getName());
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

		SdkChannelGameConfirm entity = systemService.getEntity(
				SdkChannelGameConfirm.class, idInt);
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
