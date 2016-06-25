package jeecg.ext.sdk.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.SdkTelephoneCenternumber;
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
@RequestMapping("/sdkTelephoneCenternumber")
public class SdkTelephoneCenternumberController extends BaseController {
	private static final Logger logger = Logger.getLogger(SdkTelephoneCenternumberController.class);

	@Autowired
	private SystemService systemService;

	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sdkTelephoneCenternumber/list");
		return mv;
	}

	@RequestMapping(params = "datagrid")
	public void datagrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(SdkTelephoneCenternumber.class, dataGrid);
		String imsi = request.getParameter("imsi");
		String centernumber = request.getParameter("centernumber");
		if (StringUtils.isNotBlank(imsi)) {
			cq.add(Restrictions.like("imsi", "%" + imsi + "%"));
		}
		if (StringUtils.isNotBlank(centernumber)) {
			cq.add(Restrictions.like("centernumber", "%" + centernumber + "%"));
		}
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(String id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sdkTelephoneCenternumber/addorupdate");
		Integer idInt = parseInteger(id);
		if (idInt != null) {
			SdkTelephoneCenternumber sdkTelephoneCenternumber = systemService.getEntity(SdkTelephoneCenternumber.class, idInt);
			mv.addObject("sdkTelephoneCenternumber", sdkTelephoneCenternumber);
		}
		return mv;
	}
	
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(SdkTelephoneCenternumber sdkTelephoneCenternumber) {
		AjaxJson json = new AjaxJson();
		try {
			SdkTelephoneCenternumber entity = null;
			if (sdkTelephoneCenternumber.getId() != null) {
				entity = systemService.getEntity(SdkTelephoneCenternumber.class, sdkTelephoneCenternumber.getId());
			}

			Date now = new Date();
			if (entity != null) {
				entity.setImsi(sdkTelephoneCenternumber.getImsi());
				entity.setCenternumber(sdkTelephoneCenternumber.getCenternumber());
				entity.setUpdatedTime(now);
			} else {
				entity = sdkTelephoneCenternumber;
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

		SdkTelephoneCenternumber entity = systemService.getEntity(SdkTelephoneCenternumber.class, idInt);
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
