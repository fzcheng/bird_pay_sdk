package jeecg.ext.sdk.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.SdkGame;
import jeecg.ext.sdk.entity.SdkGameCp;
import jeecg.ext.sdk.entity.SdkOperatorPayChannel;
import jeecg.ext.sdk.entity.SdkOrder;
import jeecg.ext.sdk.entity.SdkOrderExcel;
import jeecg.ext.sdk.entity.SdkOrderMmdo;
import jeecg.ext.sdk.entity.SdkPayment;
import jeecg.ext.sdk.entity.SdkProvinceAddr;
import jeecg.system.service.SystemService;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.excel.ExcelExportUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sdkOrderNew")
public class SdkOrderNewController extends BaseController {
	private static final Logger logger = Logger
			.getLogger(SdkOrderNewController.class);
	
	@Autowired
	private SystemService systemService;

	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("sdkOrderNew/list");

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
		
		List<SdkGame> games = this.systemService.getList(SdkGame.class);
	    if (games != null && games.size() > 0) {
	      StringBuffer gameReplace = new StringBuffer();
	      for (SdkGame game : games) {
	        gameReplace.append(game.getName()).append("_").append(game.getGameId()).append(",");
	      }
	      mv.addObject("gameReplace", gameReplace.toString());
	    } else {
	      mv.addObject("gameReplace", "0_0");
	    }
		
	    List<SdkGameCp> sdkGameCps=this.systemService.getList(SdkGameCp.class);
	    if (sdkGameCps != null && sdkGameCps.size() > 0) {
		      StringBuffer sdkGameCpsReplace = new StringBuffer();
		      for (SdkGameCp sdkGameCp : sdkGameCps) {
		    	  sdkGameCpsReplace.append(sdkGameCp.getName()).append("_").append(sdkGameCp.getCpId()).append(",");
		      }
		      mv.addObject("sdkGameCpsReplace", sdkGameCpsReplace.toString());
		    } else {
		      mv.addObject("sdkGameCpsReplace", "0_0");
		    }
	    
	    List<SdkOperatorPayChannel> sdkOperatorPayChannels = this.systemService.getList(SdkOperatorPayChannel.class);
	    if (sdkOperatorPayChannels != null && sdkOperatorPayChannels.size() > 0) {
	      StringBuffer channelsReplace = new StringBuffer();
	      for (SdkOperatorPayChannel sdkOperatorPayChannel : sdkOperatorPayChannels) {
	    	  channelsReplace.append(sdkOperatorPayChannel.getChannelName()).append("_").append(sdkOperatorPayChannel.getChannelCode()).append(",");
	      }
	      mv.addObject("channelsReplace", channelsReplace.toString());
	    } else {
	      mv.addObject("channelsReplace", "0_0");
	    }
	    
	    List<SdkProvinceAddr> sdkProvinceAddrs = this.systemService.getList(SdkProvinceAddr.class);
	    if (sdkProvinceAddrs != null && sdkProvinceAddrs.size() > 0) {
		      StringBuffer sdkProvinceAddrsReplace = new StringBuffer();
		      sdkProvinceAddrsReplace.append(" ").append("_").append("0").append(",");
		      for (SdkProvinceAddr sdkProvinceAddr : sdkProvinceAddrs) {
		    	  sdkProvinceAddrsReplace.append(sdkProvinceAddr.getProvincenm()).append("_").append(sdkProvinceAddr.getId()).append(",");
		      }
		      mv.addObject("sdkProvinceAddrsReplace", sdkProvinceAddrsReplace.toString());
		    } else {
		      mv.addObject("sdkProvinceAddrsReplace", "0_0");
		    }
	    
		return mv;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "datagrid")
	public void datagrid(SdkOrder sdkOrder,HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		/*List params = new ArrayList();
		StringBuffer hqlSb = new StringBuffer();
		StringBuffer countHql = new StringBuffer();
		StringBuffer conditionHql = new StringBuffer();
		
		// 总记录HQL
//		countHql.append(" select count(*) from SdkOrder o, SdkOrderMmdo m where o.payId = m.payId ");
		countHql.append(" select count(*) from SdkOrder o");
		// 查询HQL
//		hqlSb.append(" select new map( o.orderNo as orderNo, o.sdkGame as sdkGame, o.sdkUser as sdkUser, o.sdkPayment as sdkPayment , o.sdkGameCp as sdkGameCp, o.orderName as orderName, o.orderDesc as orderDesc, o.payId as payId, ");
//		hqlSb.append(" o.amount as amount, o.channel as channel, o.status as status, o.statusDetail as statusDetail, o.cpExt as cpExt, o.queryStatus as queryStatus, o.queryTime as queryTime, o.notifyStatus as notifyStatus, o.notifyRequest as notifyRequest, ");
//		hqlSb.append(" o.notifyResponse as notifyResponse, o.notifyTime as notifyTime, o.createTime as createTime, o.completeTime as completeTime, o.originalcode as originalcode, m.payChannelCode as payChannelCode ) ");
//		hqlSb.append(" from SdkOrder o, SdkOrderMmdo m where o.payId = m.payId ");
		hqlSb.append(" select new map( o.orderNo as orderNo, o.sdkGame as sdkGame, o.sdkUser as sdkUser, o.sdkPayment as sdkPayment , o.sdkGameCp as sdkGameCp, o.orderName as orderName, o.orderDesc as orderDesc, o.payId as payId, ");
		hqlSb.append(" o.amount as amount, o.channel as channel, o.status as status, o.statusDetail as statusDetail, o.cpExt as cpExt, o.queryStatus as queryStatus, o.queryTime as queryTime, o.notifyStatus as notifyStatus, o.notifyRequest as notifyRequest, ");
		hqlSb.append(" o.notifyResponse as notifyResponse, o.notifyTime as notifyTime, o.createTime as createTime, o.completeTime as completeTime, o.originalcode as originalcode ) ");
		hqlSb.append(" from SdkOrder o ");
		
		
		// 条件HQL
		String ctBegin = request.getParameter("createTime_begin");
		String ctEnd = request.getParameter("createTime_end");
		if(StringUtil.isNotEmpty(ctBegin) && StringUtil.isNotEmpty(ctEnd) ){
			conditionHql.append(" and o.createTime >= ? and o.createTime <= ? ");
			try {
				params.add(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(ctBegin));
				params.add(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(ctEnd));
			} catch (ParseException e) {
				logger.error("the time error is :"+e);
			}
		}
		String orderNo = request.getParameter("orderNo");
		if (StringUtils.isNotBlank(orderNo) && StringUtils.isNotEmpty(orderNo)) {
			conditionHql.append(" and o.orderNo like ? ");
			params.add("%" + orderNo.trim() + "%");
		}
		String sdkPayment_payType = request.getParameter("sdkPayment_payType");
		if (StringUtils.isNotBlank(sdkPayment_payType) && StringUtils.isNotEmpty(sdkPayment_payType)) {
			conditionHql.append(" and o.sdkPayment.id = ? ");
			Integer t = parseInteger(sdkPayment_payType);
			params.add(t);
		}
		String amount = request.getParameter("amount");
		if (StringUtils.isNotBlank(amount) && StringUtils.isNotEmpty(amount)) {
			conditionHql.append(" and o.amount = ? ");
			float f = Float.parseFloat(amount);
			params.add(f);
		}
		String status = request.getParameter("status");
		if (StringUtils.isNotBlank(status) && StringUtils.isNotEmpty(status)) {
			conditionHql.append(" and o.status = ? ");
			params.add(parseInteger(status));
		}
		String sdkGame_gameId = request.getParameter("sdkGame_gameId");
		if (StringUtils.isNotBlank(sdkGame_gameId) && StringUtils.isNotEmpty(sdkGame_gameId)) {
			conditionHql.append(" and o.sdkGame.gameId = ? ");
			params.add(parseInteger(sdkGame_gameId));
		}
		String notifyStatus = request.getParameter("notifyStatus");
		if (StringUtils.isNotBlank(notifyStatus) && StringUtils.isNotEmpty(notifyStatus)) {
			conditionHql.append(" and o.notifyStatus = ? ");
			params.add(parseInteger(notifyStatus));
		}
		String payId = request.getParameter("payId");
		if (StringUtils.isNotBlank(payId) && StringUtils.isNotEmpty(payId)) {
			conditionHql.append(" and o.payId = ? ");
			params.add(parseInteger(payId));
		}
		String sdkUser_uid = request.getParameter("sdkUser_uid");
		if (StringUtils.isNotBlank(sdkUser_uid) && StringUtils.isNotEmpty(sdkUser_uid)) {
			conditionHql.append(" and o.sdkUser.uid = ? ");
			params.add(parseInteger(sdkUser_uid.trim()));
		}
		String channelCode = request.getParameter("channelCode");
		if (StringUtils.isNotBlank(channelCode) && StringUtils.isNotEmpty(channelCode)) {
			conditionHql.append(" and m.payChannelCode = ? ");
			params.add(channelCode);
		}
		conditionHql.append(" order by o.createTime desc, o.orderNo desc ");
		
		//每行记录
		List<Map> result =  this.systemService.findPageListByHql(hqlSb.append(conditionHql).toString(), dataGrid.getPage(), dataGrid.getRows(), params.toArray());
		List<SdkOrder> sdkOrders = new ArrayList<SdkOrder>();
		for(Map map : result){
			SdkOrder sdkOrderNew = new SdkOrder();
			sdkOrderNew.setOrderNo((String)map.get("orderNo"));
			sdkOrderNew.setSdkGame((SdkGame)map.get("sdkGame"));
			sdkOrderNew.setSdkUser((SdkUser)map.get("sdkUser"));
			sdkOrderNew.setSdkPayment((SdkPayment)map.get("sdkPayment"));
			sdkOrderNew.setSdkGameCp((SdkGameCp)map.get("sdkGameCp"));
			sdkOrderNew.setOrderName((String)map.get("orderName"));
			sdkOrderNew.setOrderDesc((String)map.get("orderDesc"));
			sdkOrderNew.setPayId((Integer)map.get("payId"));
			sdkOrderNew.setAmount((Float)map.get("amount"));
			sdkOrderNew.setChannel((String)map.get("channel"));
			sdkOrderNew.setStatus((Integer)map.get("status"));
			sdkOrderNew.setStatusDetail((String)map.get("statusDetail"));
			sdkOrderNew.setCpExt((String)map.get("cpExt"));
			sdkOrderNew.setQueryStatus((Integer)map.get("queryStatus"));
			sdkOrderNew.setQueryTime((Timestamp)map.get("queryTime"));
			sdkOrderNew.setNotifyStatus((Integer)map.get("notifyStatus"));
			sdkOrderNew.setNotifyRequest((String)map.get("notifyRequest"));
			sdkOrderNew.setNotifyResponse((String)map.get("notifyResponse"));
			sdkOrderNew.setNotifyTime((Timestamp)map.get("notifyTime"));
			sdkOrderNew.setCreateTime((Timestamp)map.get("createTime"));
			sdkOrderNew.setCompleteTime((Timestamp)map.get("completeTime"));
			sdkOrderNew.setOriginalcode((String)map.get("originalcode"));
//			sdkOrderNew.setChannelCode((String)map.get("payChannelCode"));
			sdkOrders.add(sdkOrderNew);
		}
		
		//总记录
		int allcount = ((Long)this.systemService.findHql(countHql.append(conditionHql).toString(), params.toArray()).get(0)).intValue();
		
		dataGrid.setReaults(sdkOrders);
		dataGrid.setTotal(allcount);
		TagUtil.datagrid(response, dataGrid);*/
		
		
		
		CriteriaQuery cq = new CriteriaQuery(SdkOrder.class, dataGrid);
		Map<String, Object> ordermap=new LinkedHashMap<String, Object>();
		ordermap.put("createTime", SortDirection.desc);
		ordermap.put("orderNo", SortDirection.desc);
		cq.setOrder(ordermap);
		
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, sdkOrder);
		
		String ctBegin = request.getParameter("createTime_begin");
		String ctEnd = request.getParameter("createTime_end");
		if(StringUtil.isNotEmpty(ctBegin) && StringUtil.isNotEmpty(ctEnd) ){
			try {
//			logger.info("the ctBegin ="+ctBegin);
//			logger.info("the ctEnd ="+ctEnd);
			cq.ge("createTime", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(ctBegin));
			cq.le("createTime", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(ctEnd));
			} catch (ParseException e) {
				logger.error("the time error is :"+e);
			}
			cq.add();
		}else{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());	
			calendar.add(Calendar.MONTH, -1);
			Date startTime=calendar.getTime();
			cq.ge("createTime", startTime);
			cq.add();
		}
		
		String orderNo = request.getParameter("orderNo");
		if (StringUtils.isNotBlank(orderNo) && StringUtils.isNotEmpty(orderNo)) {
			cq.add(Restrictions.like("orderNo", "%" + orderNo.trim() + "%"));
		}
		
		String sdkPayment_payType = request.getParameter("sdkPayment_payType");
		if (StringUtils.isNotBlank(sdkPayment_payType)
				&& StringUtils.isNotEmpty(sdkPayment_payType)) {
			Integer t=parseInteger(sdkPayment_payType);
			cq.add(Restrictions.eq("sdkPayment.id", t));
		}

		String amount = request.getParameter("amount");
		if (StringUtils.isNotBlank(amount)
				&& StringUtils.isNotEmpty(amount)) {
			float f = Float.parseFloat(amount);
			cq.add(Restrictions.eq("amount", f));
		}
		
		String status = request.getParameter("status");
		if (StringUtils.isNotBlank(status) && StringUtils.isNotEmpty(status)) {
			cq.add(Restrictions.eq("status", parseInteger(status)));
		}
		
		String sdkGame_gameId = request.getParameter("sdkGame_gameId");
		if (StringUtils.isNotBlank(sdkGame_gameId) && StringUtils.isNotEmpty(sdkGame_gameId)) {
			cq.add(Restrictions.eq("sdkGame.gameId", parseInteger(sdkGame_gameId)));
		}
		
		String notifyStatus = request.getParameter("notifyStatus");
		if (StringUtils.isNotBlank(notifyStatus) && StringUtils.isNotEmpty(notifyStatus)) {
			cq.add(Restrictions.eq("notifyStatus", parseInteger(notifyStatus)));
		}
		
		String payId = request.getParameter("payId");
		if (StringUtils.isNotBlank(payId) && StringUtils.isNotEmpty(payId)) {
			cq.add(Restrictions.eq("payId", parseInteger(payId)));
		}
		
		String sdkUser_uid = request.getParameter("sdkUser_uid");
		if (StringUtils.isNotBlank(sdkUser_uid) && StringUtils.isNotEmpty(sdkUser_uid)) {
			cq.add(Restrictions.eq("sdkUser.uid", parseInteger(sdkUser_uid.trim())));
		}
		
		// cq.add(Restrictions.sqlRestriction("CAST({alias}.mobilephone AS CHAR) like ?",
		// "%"+mobilephone.trim()+"%", StringType.INSTANCE));
		this.systemService.getDataGridReturn(cq, true);
		List<SdkOrder> list = dataGrid.getReaults();
		String sdkOrderMmdoHql = " from SdkOrderMmdo where payId = ? ";
		String sdkOperatorPayChannelHql=" from SdkOperatorPayChannel where channelCode = ? and operatorType = ? ";
		for (SdkOrder order : list) {
			List<SdkOrderMmdo> sdkOrderMmdos = this.systemService.findHql(sdkOrderMmdoHql, order.getPayId());
			if(sdkOrderMmdos != null && sdkOrderMmdos.size() > 0){
				SdkOrderMmdo sdkOrderMmdo = sdkOrderMmdos.get(0);
				List<SdkOperatorPayChannel> sdkOperatorPayChannels = this.systemService.findHql(sdkOperatorPayChannelHql,sdkOrderMmdo.getPayChannelCode(),sdkOrderMmdo.getOperationType().shortValue());
				if(sdkOperatorPayChannels != null && sdkOperatorPayChannels.size() > 0){
					SdkOperatorPayChannel sdkOperatorPayChannel=sdkOperatorPayChannels.get(0);
					order.setChannelCode(sdkOperatorPayChannel.getChannelName());
				}
				
				if(order.getSdkPayment().getPayType()!=9){
					
				}else {
					order.setOperationType(sdkOrderMmdo.getOperationType());
					order.setProvinceno(String.valueOf(sdkOrderMmdo.getProvinceNo()));
					order.setMobilephoneNumber(sdkOrderMmdo.getMobilephone());
				}
			}
		}
		
		dataGrid.setReaults(list);
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
	
	/**
	 * 导出Excel
	 * 
	 * @param request
	 * @param response
	 */

	@SuppressWarnings("unused")
	@RequestMapping(params = "outputExcel")
	public void outputExcel(HttpServletRequest request,
			HttpServletResponse response) {

		// 生成提示信息，
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "订单数据";
			// 根据浏览器进行转码，使其支持中文文件名
			String browse = BrowserUtils.checkBrowse(request);
			if ("MSIE".equalsIgnoreCase(browse.substring(0, 4))) {
				response.setHeader(
						"content-disposition",
						"attachment;filename="
								+ java.net.URLEncoder.encode(codedFileName,
										"UTF-8") + ".xls");
			} else {
				String newtitle = new String(codedFileName.getBytes("UTF-8"),
						"ISO8859-1");
				response.setHeader("content-disposition",
						"attachment;filename=" + newtitle + ".xls");
			}
			
			CriteriaQuery cq = new CriteriaQuery(SdkOrder.class);
			Map<String, Object> ordermap=new LinkedHashMap<String, Object>();
			ordermap.put("createTime", SortDirection.desc);
			ordermap.put("orderNo", SortDirection.desc);
			cq.setOrder(ordermap);
			
			String ctBegin = request.getParameter("createTime_begin");
			String ctEnd = request.getParameter("createTime_end");
			if(StringUtil.isNotEmpty(ctBegin) && StringUtil.isNotEmpty(ctEnd) ){
				try {
				cq.ge("createTime", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(ctBegin));
				cq.le("createTime", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(ctEnd));
				} catch (ParseException e) {
					logger.error("the time error is :"+e);
				}
				cq.add();
			}else{
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());	
				calendar.add(Calendar.MONTH, -1);
				Date startTime=calendar.getTime();
				cq.ge("createTime", startTime);
				cq.add();
			}
			
			String orderNo = request.getParameter("orderNo");
			if (StringUtils.isNotBlank(orderNo) && StringUtils.isNotEmpty(orderNo)) {
				cq.add(Restrictions.like("orderNo", "%" + orderNo.trim() + "%"));
			}
			
			String sdkPayment_payType = request.getParameter("sdkPayment_payType");
			if (StringUtils.isNotBlank(sdkPayment_payType)
					&& StringUtils.isNotEmpty(sdkPayment_payType)) {
				Integer t=parseInteger(sdkPayment_payType);
				cq.add(Restrictions.eq("sdkPayment.id", t));
			}

			String amount = request.getParameter("amount");
			if (StringUtils.isNotBlank(amount)
					&& StringUtils.isNotEmpty(amount)) {
				float f = Float.parseFloat(amount);
				cq.add(Restrictions.eq("amount", f));
			}
			
			String status = request.getParameter("status");
			if (StringUtils.isNotBlank(status) && StringUtils.isNotEmpty(status)) {
				cq.add(Restrictions.eq("status", parseInteger(status)));
			}
			
			String sdkGame_gameId = request.getParameter("sdkGame_gameId");
			if (StringUtils.isNotBlank(sdkGame_gameId) && StringUtils.isNotEmpty(sdkGame_gameId)) {
				cq.add(Restrictions.eq("sdkGame.gameId", parseInteger(sdkGame_gameId)));
			}
			
			String notifyStatus = request.getParameter("notifyStatus");
			if (StringUtils.isNotBlank(notifyStatus) && StringUtils.isNotEmpty(notifyStatus)) {
				cq.add(Restrictions.eq("notifyStatus", parseInteger(notifyStatus)));
			}
			
			String payId = request.getParameter("payId");
			if (StringUtils.isNotBlank(payId) && StringUtils.isNotEmpty(payId)) {
				cq.add(Restrictions.eq("payId", parseInteger(payId)));
			}
			
			String sdkUser_uid = request.getParameter("sdkUser_uid");
			if (StringUtils.isNotBlank(sdkUser_uid) && StringUtils.isNotEmpty(sdkUser_uid)) {
				cq.add(Restrictions.eq("sdkUser.uid", parseInteger(sdkUser_uid.trim())));
			}

			List<SdkOrder> list = this.systemService.getListByCriteriaQuery(cq,
					false);
			
			String sdkOrderMmdoHql = " from SdkOrderMmdo where payId = ? ";
			String sdkOperatorPayChannelHql=" from SdkOperatorPayChannel where channelCode = ? and operatorType = ? ";
			for (SdkOrder order : list) {
				List<SdkOrderMmdo> sdkOrderMmdos = this.systemService.findHql(sdkOrderMmdoHql, order.getPayId());
				if(sdkOrderMmdos != null && sdkOrderMmdos.size() > 0){
					SdkOrderMmdo sdkOrderMmdo = sdkOrderMmdos.get(0);
					List<SdkOperatorPayChannel> sdkOperatorPayChannels = this.systemService.findHql(sdkOperatorPayChannelHql,sdkOrderMmdo.getPayChannelCode(),sdkOrderMmdo.getOperationType().shortValue());
					if(sdkOperatorPayChannels != null && sdkOperatorPayChannels.size() > 0){
						SdkOperatorPayChannel sdkOperatorPayChannel=sdkOperatorPayChannels.get(0);
						order.setChannelCode(sdkOperatorPayChannel.getChannelName());
					}
					
					if(order.getSdkPayment().getPayType()!=9){
						
					}else {
						order.setOperationType(sdkOrderMmdo.getOperationType());
						order.setProvinceno(String.valueOf(sdkOrderMmdo.getProvinceNo()));
						order.setMobilephoneNumber(sdkOrderMmdo.getMobilephone());
					}
				}
			}
			
//			CriteriaQuery cq_sdkOperatorPayChannels = new CriteriaQuery(SdkOperatorPayChannel.class);
//			List<SdkOperatorPayChannel> sdkOperatorPayChannels = this.systemService
//					.getListByCriteriaQuery(cq_sdkOperatorPayChannels, false);
//			Map<String, String> map_sdkOperatorPayChannels = new HashMap<String, String>();
//			if (sdkOperatorPayChannels != null && sdkOperatorPayChannels.size() > 0) {
//				for (SdkOperatorPayChannel sdkOperatorPayChannel : sdkOperatorPayChannels) {
//					map_sdkOperatorPayChannels.put(sdkOperatorPayChannel.getChannelCode(), sdkOperatorPayChannel.getChannelName());
//				}
//			}
			CriteriaQuery cq_sdkGames = new CriteriaQuery(SdkGame.class);
			List<SdkGame> sdkGames = this.systemService
					.getListByCriteriaQuery(cq_sdkGames, false);
			Map<Integer, String> map_sdkGames = new HashMap<Integer, String>();
			if (sdkGames != null && sdkGames.size() > 0) {
				for (SdkGame sdkGame : sdkGames) {
					map_sdkGames.put(sdkGame.getGameId(), sdkGame.getName());
				}
			}
			
			CriteriaQuery cq_sdkProvinceAddrs = new CriteriaQuery(SdkProvinceAddr.class);
			List<SdkProvinceAddr> sdkProvinceAddrs = this.systemService
					.getListByCriteriaQuery(cq_sdkProvinceAddrs, false);
			Map<Integer, String> map_sdkProvinceAddrs = new HashMap<Integer, String>();
			if (sdkProvinceAddrs != null && sdkProvinceAddrs.size() > 0) {
				for (SdkProvinceAddr sdkProvinceAddr : sdkProvinceAddrs) {
					map_sdkProvinceAddrs.put(sdkProvinceAddr.getId(), sdkProvinceAddr.getProvincenm());
				}
			}
			
			List<SdkOrderExcel> resultList = new ArrayList<SdkOrderExcel>();
			for (SdkOrder order : list) {
				String orderName="";
				String operationType="";
				String payChannelCodeChineseName="";
				String provincename="";
				String statusName="";
				String statusdetail="";
				String originalcode="";
				String channel="";
				String gameIdChineseName="";
				String cpExt="";
				String notifyStatusName="";
				String notifyTime="";
				String sdkPaymentName="";
				
				SdkOrderExcel excel = new SdkOrderExcel();
				excel.setCreateTime(order.getCreateTime());
				excel.setOrderNo(order.getOrderNo());
				if(StringUtils.isNotEmpty(order.getChannelCode())&&StringUtils.isNotBlank(order.getChannelCode())){
					payChannelCodeChineseName=order.getChannelCode();
				}
				excel.setChannelCode(payChannelCodeChineseName);
				if(StringUtils.isNotEmpty(order.getOrderName())&&StringUtils.isNotBlank(order.getOrderName())){
					orderName=order.getOrderName();
				}
				excel.setOrderName(orderName);
				if(order.getOperationType()!=null){
					if(order.getOperationType()==1){
						operationType="移动";
					}else if(order.getOperationType()==2){
						operationType="联通";
					}else if(order.getOperationType()==3){
						operationType="电信";
					}
				}
				excel.setOperationType(operationType);
				if(order.getProvinceno()!=null){
					if(!"0".equals(order.getProvinceno())){
						provincename=map_sdkProvinceAddrs.get(Integer.valueOf(order.getProvinceno()));
					}
				}
				excel.setProvinceno(provincename);
				if(StringUtils.isNotEmpty(order.getMobilephoneNumber())&&StringUtils.isNotBlank(order.getMobilephoneNumber())){
					excel.setMobilephoneNumber(order.getMobilephoneNumber());;
				}
				sdkPaymentName=order.getSdkPayment().getPayCnName();
				excel.setSdkPayment_payType(sdkPaymentName);
				excel.setAmount(order.getAmount());
				/*订单创建_0,支付成功_1,等待结果_2,支付失败_3,订单异常_4,省份屏蔽_5,余额不足_6,网络异常_7
				  ,计费点无效_8,签名鉴权无效_9,请求频繁_10,黑名单_11,日上限_12,月上限_13,未知原因错误_14
				  ,飞行模式_15,长短信发送解析失败_16,不在服务区_17,超时_18,订单取消_19,获取短信内容端口异常_20
				  ,获取验证码超时_21*/
				if(order.getStatus()==0){
					statusName="订单创建";
				}else if(order.getStatus()==1){
					statusName="支付成功";
				}else if(order.getStatus()==2){
					statusName="等待结果";
				}else if(order.getStatus()==3){
					statusName="支付失败";
				}else if(order.getStatus()==4){
					statusName="订单异常";
				}else if(order.getStatus()==5){
					statusName="省份屏蔽";
				}else if(order.getStatus()==6){
					statusName="余额不足";
				}else if(order.getStatus()==7){
					statusName="网络异常";
				}else if(order.getStatus()==8){
					statusName="计费点无效";
				}else if(order.getStatus()==9){
					statusName="签名鉴权无效";
				}else if(order.getStatus()==10){
					statusName="请求频繁";
				}else if(order.getStatus()==11){
					statusName="黑名单";
				}else if(order.getStatus()==12){
					statusName="日上限";
				}else if(order.getStatus()==13){
					statusName="月上限";
				}else if(order.getStatus()==14){
					statusName="未知原因错误";
				}else if(order.getStatus()==15){
					statusName="飞行模式";
				}else if(order.getStatus()==16){
					statusName="长短信发送解析失败";
				}else if(order.getStatus()==17){
					statusName="不在服务区";
				}else if(order.getStatus()==18){
					statusName="超时";
				}else if(order.getStatus()==19){
					statusName="订单取消";
				}else if(order.getStatus()==20){
					statusName="获取短信内容端口异常";
				}else if(order.getStatus()==21){
					statusName="获取验证码超时";
				}
				
				excel.setStatus(statusName);
				if(StringUtils.isNotEmpty(order.getStatusDetail())&&StringUtils.isNotBlank(order.getStatusDetail())){
					statusdetail=order.getStatusDetail();
				}
				excel.setStatusDetail(statusdetail);
				if(StringUtils.isNotEmpty(order.getOriginalcode())&&StringUtils.isNotBlank(order.getOriginalcode())){
					originalcode=order.getOriginalcode();
				}
				excel.setOriginalcode(originalcode);
				if(StringUtils.isNotEmpty(order.getChannel())&&StringUtils.isNotBlank(order.getChannel())){
					channel=order.getChannel();
				}
				excel.setChannel(channel);;
				excel.setSdkUser_uid(String.valueOf(order.getSdkUser().getUid()));
				gameIdChineseName=map_sdkGames.get(order.getSdkGame().getGameId());
				excel.setSdkGame_gameId(gameIdChineseName);
				excel.setSdkGameCp_cpId(order.getSdkGameCp().getName());
				if(StringUtils.isNotEmpty(order.getCpExt())&&StringUtils.isNotBlank(order.getCpExt())){
					cpExt=order.getCpExt();
				}
				excel.setCpExt(cpExt);
				//尚未通知_0,通知成功_1,通知失败_2,无需通知_3
				if(order.getNotifyStatus()==0){
					notifyStatusName="尚未通知";
				}else if(order.getNotifyStatus()==1){
					notifyStatusName="通知成功";
				}else if(order.getNotifyStatus()==2){
					notifyStatusName="通知失败";
				}else if(order.getNotifyStatus()==3){
					notifyStatusName="无需通知";
				}
				if(order.getNotifyTime()!=null){
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					notifyTime=formatter.format(order.getNotifyTime());
				}
				excel.setNotifyTime(notifyTime);
				excel.setPayId(String.valueOf(order.getPayId()));
				resultList.add(excel);
			}

			// 进行转码，使其支持中文文件名
			// 产生工作簿对象
			HSSFWorkbook workbook = null;

			workbook = ExcelExportUtil.exportExcel("导出信息", SdkOrderExcel.class,
					resultList);
			fOut = response.getOutputStream();
			workbook.write(fOut);
		} catch (UnsupportedEncodingException e1) {

		} catch (Exception e) {

		} finally {
			try {
				fOut.flush();
				fOut.close();
			} catch (IOException e) {

			}
		}
	}
}
