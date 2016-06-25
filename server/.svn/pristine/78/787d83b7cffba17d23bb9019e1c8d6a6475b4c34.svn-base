package jeecg.ext.sdk.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.SdkMmdoSetting;
import jeecg.ext.sdk.entity.SdkOperatorPayChannel;
import jeecg.ext.sdk.entity.SdkProvinceAddr;
import jeecg.ext.sdk.vo.BillingPeriodVo;
import jeecg.ext.sdk.vo.ShieldingSegmentVo;
import jeecg.system.service.SystemService;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
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

/**
 * @author Merlin
 * 
 */
@Controller
@RequestMapping("/optpaychannel")
public class OperatorPayChannelController extends BaseController {
	private static final Logger logger = Logger
			.getLogger(OperatorPayChannelController.class);
	private static final ObjectMapper mapper = new ObjectMapper();
	static {
		// mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		mapper.configure(
				DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	@Autowired
	private SystemService systemService;

	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) {
		return new ModelAndView("optpaychannel/list");
	}

	@RequestMapping(params = "datagrid")
	public void datagrid(HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(SdkOperatorPayChannel.class,
				dataGrid);
		String operatorType = request.getParameter("operatorType");
		if (StringUtils.isNotEmpty(operatorType)&& StringUtils.isNotBlank(operatorType)) {
			short ot = parseInteger(operatorType.trim()).shortValue();
			cq.add(Restrictions.eq("operatorType", ot));
		}
		
		String smsType = request.getParameter("smsType");
		if (StringUtils.isNotEmpty(smsType)&& StringUtils.isNotBlank(smsType)) {
			short st=parseInteger(smsType.trim()).shortValue();
			cq.add(Restrictions.eq("smsType", st));
		}
		
		String corporation = request.getParameter("corporation");
		if (StringUtils.isNotEmpty(corporation)&& StringUtils.isNotBlank(corporation)) {
			cq.add(Restrictions.like("corporation", "%" + corporation.trim() + "%"));
		}
		
		String signCorporation = request.getParameter("signCorporation");
		if (StringUtils.isNotEmpty(signCorporation)&& StringUtils.isNotBlank(signCorporation)) {
			cq.add(Restrictions.like("signCorporation", "%" + signCorporation.trim() + "%"));
		}
		
		String channelName = request.getParameter("channelName");
		if (StringUtils.isNotEmpty(channelName)&& StringUtils.isNotBlank(channelName)) {
			cq.add(Restrictions.like("channelName", "%" + channelName.trim() + "%"));
		}
		
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(String id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("optpaychannel/addorupdate");

		Integer idInt = parseInteger(id);
		if (idInt != null) {
			SdkOperatorPayChannel payChannel = systemService.getEntity(
					SdkOperatorPayChannel.class, idInt);
			mv.addObject("payChannel", payChannel);
		}

		return mv;
	}

	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(SdkOperatorPayChannel payChannel) {
		AjaxJson json = new AjaxJson();
		try {
			SdkOperatorPayChannel entity = null;
			if (payChannel.getId() != null) {
				entity = systemService.getEntity(SdkOperatorPayChannel.class,
						payChannel.getId());
			}
			// SdkOperatorPayChannel entity =
			// systemService.findUniqueByProperty(SdkOperatorPayChannel.class,
			// "channelCode", payChannel.getChannelCode());
			// if (entity != null && (payChannel.getId() == null ||
			// !payChannel.getId().equals(entity.getId()))) {
			// json.setMsg("支付渠道号已存在");
			// return json;
			// }

			Date now = new Date();
			if (entity != null) {
				entity.setSignCorporation(payChannel.getSignCorporation());
				entity.setCorporation(payChannel.getCorporation());
				entity.setChannelCode(payChannel.getChannelCode());
				entity.setChannelName(payChannel.getChannelName());
				entity.setOperatorType(payChannel.getOperatorType());
				entity.setSdkMinVer(payChannel.getSdkMinVer());
				entity.setSmsType(payChannel.getSmsType());
				entity.setSmsContentType(payChannel.getSmsContentType());
				entity.setDayLimit(payChannel.getDayLimit());
				entity.setMonthLimit(payChannel.getMonthLimit());
				entity.setTimeinterval(payChannel.getTimeinterval());
				entity.setReqTimeinterval(payChannel.getReqTimeinterval());
			} else {
				entity = payChannel;
				entity.setCreatedTime(now);
			}
			entity.setUpdatedTime(now);

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

		List<SdkMmdoSetting> settings = systemService.findByProperty(
				SdkMmdoSetting.class, "operatorPayChannelId", idInt);
		if (settings != null && settings.size() > 0) {
			json.setMsg("该支付配置已绑定短信配置，请先解除绑定");
			return json;
		}

		SdkOperatorPayChannel entity = systemService.getEntity(
				SdkOperatorPayChannel.class, idInt);
		if (entity == null) {
			json.setMsg("id=" + id + "，记录不存在");
			return json;
		}

		systemService.delete(entity);
		json.setMsg("删除成功");

		return json;
	}

	@RequestMapping(params = "editPaying")
	public ModelAndView editPaying(Integer id) {
		logger.debug("get the operator pay channel payying setting edit page");
		ModelAndView mv = new ModelAndView("optpaychannel/editPaying");

		List<SdkProvinceAddr> provinces = systemService
				.getList(SdkProvinceAddr.class);
		mv.addObject("provinces", provinces);

		SdkOperatorPayChannel entity;
		if (id != null) {
			entity = systemService.getEntity(SdkOperatorPayChannel.class, id);
		} else {
			entity = null;
		}
		mv.addObject("passage", entity);
		return mv;
	}

	@RequestMapping(params = "periods")
	public @ResponseBody
	List<BillingPeriodVo> getBillingPeriods(Integer id) throws Exception {
		List<BillingPeriodVo> periods = new ArrayList<BillingPeriodVo>(0);
		;
		if (id != null) {
			SdkOperatorPayChannel entity = systemService.getEntity(
					SdkOperatorPayChannel.class, id);
			if (entity != null
					&& StringUtils.isNotBlank(entity.getBillingPeriods())) {
				TypeReference<List<BillingPeriodVo>> typeRef = new TypeReference<List<BillingPeriodVo>>() {
				};
				periods = mapper.readValue(entity.getBillingPeriods(), typeRef);
			}
		}

		return periods;
	}

	@RequestMapping(params = "savePaying")
	@ResponseBody
	public AjaxJson savePaying(SdkOperatorPayChannel payChannel) {
		AjaxJson json = new AjaxJson();
		try {
			if (payChannel.getId() == null) {
				json.setMsg("计费通道不存在");
				return json;
			}

			SdkOperatorPayChannel entity = systemService.getEntity(
					SdkOperatorPayChannel.class, payChannel.getId());
			if (entity == null) {
				json.setMsg("计费通道不存在");
				return json;
			}

			Date now = new Date();
			entity.setBillingPeriods(payChannel.getBillingPeriods());
			entity.setBillingProvinceId(payChannel.getBillingProvinceId());
			entity.setUpdatedTime(now);

			this.systemService.saveOrUpdate(entity);
			json.setMsg("保存成功");
		} catch (Exception e) {
			json.setMsg("保存异常");
		}

		return json;
	}

	@RequestMapping(params = "editShielding")
	public ModelAndView editShielding(Integer id) {
		logger.debug("get the operator pay channel shielding setting edit page");
		ModelAndView mv = new ModelAndView("optpaychannel/editShielding");

		SdkOperatorPayChannel entity = null;
		if (id != null) {
			entity = systemService.getEntity(SdkOperatorPayChannel.class, id);
		}

		List<SdkProvinceAddr> provinces = systemService
				.getList(SdkProvinceAddr.class);
		mv.addObject("provinces", provinces);
		mv.addObject("passage", entity);
		return mv;
	}

	@RequestMapping(params = "segments")
	public @ResponseBody
	List<ShieldingSegmentVo> getShieldingSegments(Integer id) throws Exception {
		List<ShieldingSegmentVo> segments = new ArrayList<ShieldingSegmentVo>();
		;
		if (id != null) {
			SdkOperatorPayChannel entity = systemService.getEntity(
					SdkOperatorPayChannel.class, id);
			if (entity != null
					&& StringUtils.isNotBlank(entity.getShieldingSegments())) {
				String[] segmentsArray = entity.getShieldingSegments().split(
						",");
				for (String seg : segmentsArray) {
					ShieldingSegmentVo vo = new ShieldingSegmentVo();
					vo.setSegment(seg);
					segments.add(vo);
				}
			}
		}

		return segments;
	}

	@RequestMapping(params = "saveShielding")
	@ResponseBody
	public AjaxJson saveShielding(SdkOperatorPayChannel payChannel) {
		AjaxJson json = new AjaxJson();
		try {
			if (payChannel.getId() == null) {
				json.setMsg("计费通道不存在");
				return json;
			}

			SdkOperatorPayChannel entity = systemService.getEntity(
					SdkOperatorPayChannel.class, payChannel.getId());
			if (entity == null) {
				json.setMsg("计费通道不存在");
				return json;
			}

			Date now = new Date();
			entity.setShieldingProvinceIds(payChannel.getShieldingProvinceIds());
			entity.setShieldingSegments(payChannel.getShieldingSegments());
			entity.setUpdatedTime(now);

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
}
