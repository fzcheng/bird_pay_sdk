package jeecg.ext.sdk.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.SdkCityAddr;
import jeecg.ext.sdk.entity.SdkPhoneSegment;
import jeecg.ext.sdk.entity.SdkProvinceAddr;
import jeecg.system.service.SystemService;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
//import org.activiti.engine.impl.util.json.JSONArray;
//import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StringType;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;

@Controller
@RequestMapping("/sdkPhoneSegment")
public class SdkPhoneSegmentController extends BaseController {
	private static final Logger logger = Logger
			.getLogger(SdkPhoneSegmentController.class);

	@Autowired
	private SystemService systemService;

	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sdkPhoneSegment/list");

		List<SdkProvinceAddr> sdkProvinceAddrs = this.systemService
				.getList(SdkProvinceAddr.class);
		if (sdkProvinceAddrs != null && sdkProvinceAddrs.size() > 0) {
			StringBuffer provinceReplace = new StringBuffer();
			for (SdkProvinceAddr sdkProvinceAddr : sdkProvinceAddrs) {
				provinceReplace.append(sdkProvinceAddr.getProvincenm())
						.append("_").append(sdkProvinceAddr.getId())
						.append(",");
			}
			mv.addObject("provinceReplace", provinceReplace.toString());
		} else {
			mv.addObject("provinceReplace", "0_0");
		}

		List<SdkCityAddr> sdkCityAddrs = this.systemService
				.getList(SdkCityAddr.class);
		if (sdkCityAddrs != null && sdkCityAddrs.size() > 0) {
			StringBuffer cityReplace = new StringBuffer();
			for (SdkCityAddr sdkCityAddr : sdkCityAddrs) {
				cityReplace.append(sdkCityAddr.getCitynm()).append("_")
						.append(sdkCityAddr.getId()).append(",");
			}
			mv.addObject("cityReplace", cityReplace.toString());
		} else {
			mv.addObject("cityReplace", "0_0");
		}
		return mv;
	}

	@RequestMapping(params = "datagrid")
	public void datagrid(HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(SdkPhoneSegment.class, dataGrid);
		String provinceNo = request.getParameter("provinceNo");
		String areaCode = request.getParameter("areaCode");
		String phoneSegmentCode = request.getParameter("phoneSegmentCode");
		if (provinceNo != null) {
			if (StringUtils.isNotBlank(provinceNo)) {
				cq.add(Restrictions.eq("provinceNo", parseInteger(provinceNo)));
			}
		}
		if (areaCode != null) {
			if (StringUtils.isNotBlank(areaCode)) {
				cq.add(Restrictions.eq("areaCode", parseInteger(areaCode)));
			}			
		}
		if (phoneSegmentCode != null) {
			if (StringUtils.isNotBlank(phoneSegmentCode)) {
				cq.add(Restrictions.sqlRestriction(
						"CAST({alias}.phone_segment_code AS CHAR) like ?", "%"
								+ phoneSegmentCode.trim() + "%",
						StringType.INSTANCE));
			}
		}
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(String id, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sdkPhoneSegment/addorupdate");
		Integer idInt = parseInteger(id);
		if (idInt != null) {
			SdkPhoneSegment sdkPhoneSegment = systemService.getEntity(
					SdkPhoneSegment.class, idInt);
			mv.addObject("sdkPhoneSegment", sdkPhoneSegment);
		}
		CriteriaQuery cq = new CriteriaQuery(SdkProvinceAddr.class);
		cq.addOrder("id", SortDirection.asc);
		List<SdkProvinceAddr> sdkProvinceAddrs = this.systemService
				.getListByCriteriaQuery(cq, false);
		// request.setAttribute("sdkIpaddrprovinces", sdkIpaddrprovinces);
		mv.addObject("sdkProvinceAddrs", sdkProvinceAddrs);
		return mv;
	}

	@RequestMapping(params = "getCity", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getCity(String provinceNo) {
		CriteriaQuery cq = new CriteriaQuery(SdkCityAddr.class);
		cq.addOrder("provinceId", SortDirection.asc);
		cq.add(Restrictions.ilike("provinceId", provinceNo, MatchMode.START));
		List<SdkCityAddr> sdkCityAddrs = this.systemService
				.getListByCriteriaQuery(cq, false);
		Map<Integer, String> map = new HashMap<Integer, String>();
		if (sdkCityAddrs != null && sdkCityAddrs.size() > 0) {
			for (SdkCityAddr sdkCityAddr : sdkCityAddrs) {
				map.put(sdkCityAddr.getId(), sdkCityAddr.getCitynm());
			}
		}
		JSONObject j = JSONObject.fromObject(map);
		return j;
	}

	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(SdkPhoneSegment sdkPhoneSegment) {
		AjaxJson json = new AjaxJson();
		try {
			SdkPhoneSegment entity = null;
			if (sdkPhoneSegment.getId() != null) {
				entity = systemService.getEntity(SdkPhoneSegment.class,
						sdkPhoneSegment.getId());
			}
			Date now = new Date();
			if (entity != null) {
				entity.setProvinceNo(sdkPhoneSegment.getProvinceNo());
				entity.setAreaCode(sdkPhoneSegment.getAreaCode());
				entity.setPhoneSegmentCode(sdkPhoneSegment
						.getPhoneSegmentCode());
				entity.setOperationType(sdkPhoneSegment.getOperationType());
				entity.setUpdatedTime(now);
			} else {
				entity = sdkPhoneSegment;
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

		SdkPhoneSegment entity = systemService.getEntity(SdkPhoneSegment.class,
				idInt);
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
