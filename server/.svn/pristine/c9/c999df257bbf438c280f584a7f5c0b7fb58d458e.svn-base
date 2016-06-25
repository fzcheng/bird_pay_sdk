package jeecg.ext.sdk.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.SdkOperatorPayType;
import jeecg.ext.sdk.entity.SdkPayment;
import jeecg.system.service.SystemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;

@Controller
@RequestMapping("/sdkOperatorPayType")
public class SdkOperatorPayTypeController extends BaseController {
	private static final Logger logger = Logger
			.getLogger(SdkOperatorPayTypeController.class);

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

		ModelAndView mv = new ModelAndView();
		mv.setViewName("sdkOperatorPayType/list");

		List<SdkPayment> sdkPayments = this.systemService
				.getList(SdkPayment.class);

		if (sdkPayments != null && sdkPayments.size() > 0) {
			StringBuffer sdkPaymentReplace = new StringBuffer();
			for (SdkPayment sdkPayment : sdkPayments) {
				sdkPaymentReplace.append(sdkPayment.getPayCnName()).append("_")
						.append(sdkPayment.getPayType()).append(",");
			}
			mv.addObject("sdkPaymentReplace", sdkPaymentReplace.toString());
		}
		
		return mv;
	}

	@RequestMapping(params = "datagrid")
	public void datagrid(HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(SdkOperatorPayType.class, dataGrid);
//		cq.addOrder("idx", SortDirection.asc);
//		cq.addOrder("maxPrice", SortDirection.asc);
//		cq.addOrder("minPrice", SortDirection.asc);
//		cq.addOrder("operator", SortDirection.asc);
//		cq.addOrder("type", SortDirection.desc);
		Map<String, Object> ordermap=new LinkedHashMap<String, Object>();
		ordermap.put("type", SortDirection.desc);
		ordermap.put("operator", SortDirection.asc);
		ordermap.put("minPrice", SortDirection.asc);
		ordermap.put("maxPrice", SortDirection.asc);
		ordermap.put("idx", SortDirection.asc);
		cq.setOrder(ordermap);
		String type = request.getParameter("type");
		if (StringUtils.isNotBlank(type) && StringUtils.isNotEmpty(type)) {
			cq.add(Restrictions.eq("type", Integer.valueOf(type)));
		}

		String operator = request.getParameter("operator");
		if (StringUtils.isNotBlank(operator)
				&& StringUtils.isNotEmpty(operator)) {
			cq.add(Restrictions.eq("operator", parseInteger(operator)));
		}

		String minPrice = request.getParameter("minPrice");
		if (StringUtils.isNotBlank(minPrice)
				&& StringUtils.isNotEmpty(minPrice)) {
			float f = Float.parseFloat(minPrice);
			cq.add(Restrictions.eq("minPrice", f));
		}

		String maxPrice = request.getParameter("maxPrice");
		if (StringUtils.isNotBlank(maxPrice)
				&& StringUtils.isNotEmpty(maxPrice)) {
			float f = Float.parseFloat(maxPrice);
			cq.add(Restrictions.eq("maxPrice", f));
		}

		String idx = request.getParameter("idx");
		if (StringUtils.isNotBlank(idx) && StringUtils.isNotEmpty(idx)) {
			cq.add(Restrictions.eq("idx", parseInteger(idx)));
		}
		String ver = request.getParameter("ver");
		if (StringUtils.isNotBlank(ver) && StringUtils.isNotEmpty(ver)) {
			cq.add(Restrictions.like("ver", "%" + ver.trim() + "%"));
		}
		// cq.add(Restrictions.sqlRestriction("CAST({alias}.mobilephone AS CHAR) like ?",
		// "%"+mobilephone.trim()+"%", StringType.INSTANCE));
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
		mv.setViewName("sdkOperatorPayType/addorupdate");
		Integer idInt = parseInteger(id);
		if (idInt != null) {
			SdkOperatorPayType sdkOperatorPayType=systemService
					.getEntity(SdkOperatorPayType.class, idInt);
			mv.addObject("sdkOperatorPayType", sdkOperatorPayType);
		}
		List<SdkPayment> sdkPayments = this.systemService.getList(SdkPayment.class);
		mv.addObject("sdkPayments", sdkPayments);
		return mv;
	}
	
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(SdkOperatorPayType sdkOperatorPayType) {
		AjaxJson json = new AjaxJson();
		try {
			SdkOperatorPayType entity=null;
			if (sdkOperatorPayType.getId() != null) {
				entity = systemService.getEntity(SdkOperatorPayType.class,
						sdkOperatorPayType.getId());
			}
			
			if (entity != null) {
				entity.setType(sdkOperatorPayType.getType());
				entity.setOperator(sdkOperatorPayType.getOperator());
				entity.setMinPrice(sdkOperatorPayType.getMinPrice());
				entity.setMaxPrice(sdkOperatorPayType.getMaxPrice());
				entity.setIdx(sdkOperatorPayType.getIdx());
				entity.setVer(sdkOperatorPayType.getVer());
			} else {
				entity = sdkOperatorPayType;
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

		SdkOperatorPayType entity=systemService.getEntity(
				SdkOperatorPayType.class, idInt);
		if (entity == null) {
			json.setMsg("id=" + id + "，记录不存在");
			return json;
		}

		systemService.delete(entity);
		json.setMsg("删除成功");

		return json;
	}
}
