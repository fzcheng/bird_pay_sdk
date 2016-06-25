package jeecg.ext.sdk.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.SdkGame;
import jeecg.ext.sdk.entity.SdkOperatorPayChannel;
import jeecg.ext.sdk.entity.SdkOrderMmdo;
import jeecg.system.service.SystemService;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sdkOrderMmdo")
public class SdkOrderMmdoController extends BaseController {
	private static final Logger logger = Logger
			.getLogger(SdkOrderMmdoController.class);

	@Autowired
	private SystemService systemService;

	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("sdkOrderMmdo/list");

		List<SdkOperatorPayChannel> sdkOperatorPayChannels = this.systemService
				.getList(SdkOperatorPayChannel.class);
		if (sdkOperatorPayChannels != null && sdkOperatorPayChannels.size() > 0) {
			StringBuffer sdkOperatorPayChannelReplace = new StringBuffer();
			for (SdkOperatorPayChannel sdkOperatorPayChannel : sdkOperatorPayChannels) {
				sdkOperatorPayChannelReplace.append(sdkOperatorPayChannel.getChannelName()).append("_")
						.append(sdkOperatorPayChannel.getChannelCode()).append(",");
			}
			mv.addObject("sdkOperatorPayChannelReplace", sdkOperatorPayChannelReplace.toString());
		}

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
		
		StringBuffer statusReplace=new StringBuffer();
		statusReplace.append("失败").append("_").append("0")
		.append(",").append("成功").append("_").append("1")
		;
		mv.addObject("statusReplace", statusReplace.toString());
		return mv;
	}

	@RequestMapping(params = "datagrid")
	public void datagrid(HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(SdkOrderMmdo.class, dataGrid);
		Map<String, Object> sdkOrderMmdomap = new LinkedHashMap<String, Object>();
		sdkOrderMmdomap.put("reqTime", SortDirection.desc);
		sdkOrderMmdomap.put("payId", SortDirection.desc);
		cq.setOrder(sdkOrderMmdomap);

		String ctBegin = request.getParameter("reqTime_begin");
		String ctEnd = request.getParameter("reqTime_end");
		if (StringUtil.isNotEmpty(ctBegin) && StringUtil.isNotEmpty(ctEnd)) {
			try {
				cq.ge("reqTime", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
						.parse(ctBegin));
				cq.le("reqTime", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
						.parse(ctEnd));
			} catch (ParseException e) {
				logger.error("the time error is :" + e);
			}
			cq.add();
		}else {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());	
			calendar.add(Calendar.MONTH, -1);
			Date startTime=calendar.getTime();
			cq.ge("reqTime", startTime);
			cq.add();
		}

		String payId = request.getParameter("payId");
		if (StringUtils.isNotBlank(payId) && StringUtils.isNotEmpty(payId)) {
			Integer t = parseInteger(payId);
			cq.add(Restrictions.eq("payId",t));
		}

		String operationType = request.getParameter("operationType");
		if (StringUtils.isNotBlank(operationType)
				&& StringUtils.isNotEmpty(operationType)) {
			Integer t = parseInteger(operationType);
			cq.add(Restrictions.eq("operationType", t));
		}
		
		String payChannelCode = request.getParameter("payChannelCode");
		if (StringUtils.isNotBlank(payChannelCode) && StringUtils.isNotEmpty(payChannelCode)) {
			cq.add(Restrictions.eq("payChannelCode", payChannelCode.trim()));
		}
		
		String gameId = request.getParameter("gameId");
		if (StringUtils.isNotBlank(gameId)
				&& StringUtils.isNotEmpty(gameId)) {
			cq.add(Restrictions.eq("gameId",
					parseInteger(gameId)));
		}
		
		String uid = request.getParameter("uid");
		if (StringUtils.isNotBlank(uid)
				&& StringUtils.isNotEmpty(uid)) {
			cq.add(Restrictions.eq("uid",
					parseInteger(uid)));
		}

		String reqOrderAmount = request.getParameter("reqOrderAmount");
		if (StringUtils.isNotBlank(reqOrderAmount) && StringUtils.isNotEmpty(reqOrderAmount)) {
			float f = Float.parseFloat(reqOrderAmount);
			cq.add(Restrictions.eq("reqOrderAmount", f));
		}

		String respStatus = request.getParameter("respStatus");
		if (StringUtils.isNotBlank(respStatus) && StringUtils.isNotEmpty(respStatus)) {
			cq.add(Restrictions.eq("respStatus", parseInteger(respStatus)));
		}

		String reqImsi = request.getParameter("reqImsi");
		if (StringUtils.isNotBlank(reqImsi)
				&& StringUtils.isNotEmpty(reqImsi)) {
			cq.add(Restrictions.like("reqImsi", "%"+reqImsi.trim()+"%"));
		}
		
		String imei = request.getParameter("imei");
		if (StringUtils.isNotBlank(imei)
				&& StringUtils.isNotEmpty(imei)) {
			cq.add(Restrictions.like("imei", "%"+imei.trim()+"%"));
		}

		String additionalStatus = request.getParameter("additionalStatus");
		if (StringUtils.isNotBlank(additionalStatus) && StringUtils.isNotEmpty(additionalStatus)) {
			cq.add(Restrictions.eq("additionalStatus", parseInteger(additionalStatus)));
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
}
