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
import jeecg.ext.sdk.entity.SdkNotifyMmdo;
import jeecg.ext.sdk.entity.SdkNotifyMmdoExcel;
import jeecg.ext.sdk.entity.SdkOperatorPayChannel;
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
@RequestMapping("/sdkNotifyMmdo")
public class SdkNotifyMmdoController extends BaseController {
	private static final Logger logger = Logger
			.getLogger(SdkNotifyMmdoController.class);
	
	@Autowired
	private SystemService systemService;

	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("sdkNotifyMmdo/list");
		
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
	    
		return mv;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "datagrid")
	public void datagrid(HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(SdkNotifyMmdo.class, dataGrid);
		Map<String, Object> sdkNotifyMmdomap=new LinkedHashMap<String, Object>();
		sdkNotifyMmdomap.put("createTime", SortDirection.desc);
		sdkNotifyMmdomap.put("orderNo", SortDirection.desc);
		cq.setOrder(sdkNotifyMmdomap);
		
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
		
		String notifyStatus = request.getParameter("notifyStatus");
		if (StringUtils.isNotBlank(notifyStatus) && StringUtils.isNotEmpty(notifyStatus)) {
			cq.add(Restrictions.eq("notifyStatus", parseInteger(notifyStatus)));
		}
		
		String operationType = request.getParameter("operationType");
		if (StringUtils.isNotBlank(operationType)
				&& StringUtils.isNotEmpty(operationType)) {
			Integer t = parseInteger(operationType);
			cq.add(Restrictions.eq("operationType", t));
		}
		
//		String payChannelCode = request.getParameter("payChannelCode");
//		if (StringUtils.isNotBlank(payChannelCode) && StringUtils.isNotEmpty(payChannelCode)) {
//			cq.add(Restrictions.eq("payChannelCode", payChannelCode.trim()));
//		}
		
		String gameId = request.getParameter("gameId");
		if (StringUtils.isNotBlank(gameId)
				&& StringUtils.isNotEmpty(gameId)) {
			cq.add(Restrictions.eq("gameId",
					parseInteger(gameId)));
		}

		String amount = request.getParameter("amount");
		if (StringUtils.isNotBlank(amount)
				&& StringUtils.isNotEmpty(amount)) {
			float f = Float.parseFloat(amount);
			cq.add(Restrictions.eq("amount", f));
		}
		
		String additionalStatus = request.getParameter("additionalStatus");
		if (StringUtils.isNotBlank(additionalStatus) && StringUtils.isNotEmpty(additionalStatus)) {
			cq.add(Restrictions.eq("additionalStatus", parseInteger(additionalStatus)));
		}

		String mobile = request.getParameter("mobile");
		if (StringUtils.isNotBlank(mobile) && StringUtils.isNotEmpty(mobile)) {
			cq.add(Restrictions.like("mobile", "%" + mobile.trim() + "%"));
		}
		
		String linkid = request.getParameter("linkid");
		if (StringUtils.isNotBlank(linkid) && StringUtils.isNotEmpty(linkid)) {
			cq.add(Restrictions.like("linkid", "%" + linkid.trim() + "%"));
		}
		
		// cq.add(Restrictions.sqlRestriction("CAST({alias}.mobilephone AS CHAR) like ?",
		// "%"+mobilephone.trim()+"%", StringType.INSTANCE));
		this.systemService.getDataGridReturn(cq, true);
		List<SdkNotifyMmdo> list = dataGrid.getReaults();
		String sdkOperatorPayChannelHql=" from SdkOperatorPayChannel where channelCode = ? and operatorType = ? ";
		for (SdkNotifyMmdo sdkNotifyMmdo : list) {
			if(sdkNotifyMmdo.getOperationType()!=null&&StringUtils.isNotEmpty(sdkNotifyMmdo.getPayChannelCode())&&StringUtils.isNotBlank(sdkNotifyMmdo.getPayChannelCode())){
				List<SdkOperatorPayChannel> sdkOperatorPayChannels = this.systemService.findHql(sdkOperatorPayChannelHql,sdkNotifyMmdo.getPayChannelCode(),sdkNotifyMmdo.getOperationType().shortValue());
				if(sdkOperatorPayChannels != null && sdkOperatorPayChannels.size() > 0){
					SdkOperatorPayChannel sdkOperatorPayChannel=sdkOperatorPayChannels.get(0);
					sdkNotifyMmdo.setPayChannelName(sdkOperatorPayChannel.getChannelName());
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

	@RequestMapping(params = "outputExcel")
	public void outputExcel(HttpServletRequest request,
			HttpServletResponse response) {

		// 生成提示信息，
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "回调数据";
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

//			Map map = new HashMap();  
//	        Enumeration paramNames = request.getParameterNames();  
//	        while (paramNames.hasMoreElements()) {  
//	            String paramName = (String) paramNames.nextElement();  
//	  
//	            String[] paramValues = request.getParameterValues(paramName);  
//	            if (paramValues.length == 1) {  
//	                String paramValue = paramValues[0];  
//	                if (paramValue.length() != 0) {  
//	                    map.put(paramName, paramValue);  
//	                }  
//	            }  
//	        }  
//	  
//	        Set<Map.Entry<String, String>> set = map.entrySet();  
//	        logger.info("------------------------------");  
//	        for (Map.Entry entry : set) {  
//	        	logger.info("the result is :"+entry.getKey() + ":" + entry.getValue());  
//	        }  
//	        logger.info("------------------------------"); 
			
			CriteriaQuery cq = new CriteriaQuery(SdkNotifyMmdo.class);
			Map<String, Object> sdkNotifyMmdomap=new LinkedHashMap<String, Object>();
			sdkNotifyMmdomap.put("createTime", SortDirection.desc);
			sdkNotifyMmdomap.put("orderNo", SortDirection.desc);
			cq.setOrder(sdkNotifyMmdomap);
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
			
			String notifyStatus = request.getParameter("notifyStatus");
			if (StringUtils.isNotBlank(notifyStatus) && StringUtils.isNotEmpty(notifyStatus)) {
				cq.add(Restrictions.eq("notifyStatus", parseInteger(notifyStatus)));
			}
			
			String operationType = request.getParameter("operationType");
			if (StringUtils.isNotBlank(operationType)
					&& StringUtils.isNotEmpty(operationType)) {
				Integer t = parseInteger(operationType);
				cq.add(Restrictions.eq("operationType", t));
			}
			
//			String payChannelCode = request.getParameter("payChannelCode");
//			if (StringUtils.isNotBlank(payChannelCode) && StringUtils.isNotEmpty(payChannelCode)) {
//				cq.add(Restrictions.eq("payChannelCode", payChannelCode.trim()));
//			}
			
			String gameId = request.getParameter("gameId");
			if (StringUtils.isNotBlank(gameId)
					&& StringUtils.isNotEmpty(gameId)) {
				cq.add(Restrictions.eq("gameId",
						parseInteger(gameId)));
			}

			String amount = request.getParameter("amount");
			if (StringUtils.isNotBlank(amount)
					&& StringUtils.isNotEmpty(amount)) {
				float f = Float.parseFloat(amount);
				cq.add(Restrictions.eq("amount", f));
			}
			
			String additionalStatus = request.getParameter("additionalStatus");
			if (StringUtils.isNotBlank(additionalStatus) && StringUtils.isNotEmpty(additionalStatus)) {
				cq.add(Restrictions.eq("additionalStatus", parseInteger(additionalStatus)));
			}

			String mobile = request.getParameter("mobile");
			if (StringUtils.isNotBlank(mobile) && StringUtils.isNotEmpty(mobile)) {
				cq.add(Restrictions.like("mobile", "%" + mobile.trim() + "%"));
			}
			
			String linkid = request.getParameter("linkid");
			if (StringUtils.isNotBlank(linkid) && StringUtils.isNotEmpty(linkid)) {
				cq.add(Restrictions.like("linkid", "%" + linkid.trim() + "%"));
			}
			List<SdkNotifyMmdo> list = this.systemService.getListByCriteriaQuery(cq,
					false);
			String sdkOperatorPayChannelHql=" from SdkOperatorPayChannel where channelCode = ? and operatorType = ? ";
			
			for (SdkNotifyMmdo sdkNotifyMmdo : list) {
				List<SdkOperatorPayChannel> sdkOperatorPayChannels = this.systemService.findHql(sdkOperatorPayChannelHql,sdkNotifyMmdo.getPayChannelCode(),sdkNotifyMmdo.getOperationType().shortValue());
				if(sdkOperatorPayChannels != null && sdkOperatorPayChannels.size() > 0){
					SdkOperatorPayChannel sdkOperatorPayChannel=sdkOperatorPayChannels.get(0);
					sdkNotifyMmdo.setPayChannelName(sdkOperatorPayChannel.getChannelName());
				}
			}
			
			CriteriaQuery cq_sdkGames = new CriteriaQuery(SdkGame.class);
			List<SdkGame> sdkGames = this.systemService
					.getListByCriteriaQuery(cq_sdkGames, false);
			Map<Integer, String> map_sdkGames = new HashMap<Integer, String>();
			if (sdkGames != null && sdkGames.size() > 0) {
				for (SdkGame sdkGame : sdkGames) {
					map_sdkGames.put(sdkGame.getGameId(), sdkGame.getName());
				}
			}
			
			List<SdkNotifyMmdoExcel> resultList = new ArrayList<SdkNotifyMmdoExcel>();
			for (SdkNotifyMmdo sdkNotifyMmdo : list) {
				String operationTypeChineseName="";
				String payChannelCodeChineseName="";
				String gameIdChineseName="";
				String additionalStatusChineseName="";
				String notifyStatusName="";
				String statusDetail="";
				String originalcode="";
				
				SdkNotifyMmdoExcel excel = new SdkNotifyMmdoExcel();
				excel.setCreateTime(sdkNotifyMmdo.getCreateTime());
				if(StringUtils.isEmpty(sdkNotifyMmdo.getOrderNo())||StringUtils.isBlank(sdkNotifyMmdo.getOrderNo())){
					excel.setOrderNo("");
				}else{
					excel.setOrderNo(sdkNotifyMmdo.getOrderNo());
				}
				if(sdkNotifyMmdo.getNotifyStatus()!=null){
					if(sdkNotifyMmdo.getNotifyStatus()==0){
						notifyStatusName="失败";
					}else if(sdkNotifyMmdo.getNotifyStatus()==1){
						notifyStatusName="成功";
					}
				}
				excel.setNotifyStatus(notifyStatusName);
				
				if(sdkNotifyMmdo.getOperationType()==1){
					operationTypeChineseName="移动";
				}else if(sdkNotifyMmdo.getOperationType()==2){
					operationTypeChineseName="联通";
				}else if(sdkNotifyMmdo.getOperationType()==3){
					operationTypeChineseName="电信";
				}
				excel.setOperationType(operationTypeChineseName);
				payChannelCodeChineseName=sdkNotifyMmdo.getPayChannelName();
				gameIdChineseName=map_sdkGames.get(sdkNotifyMmdo.getGameId());
				excel.setPayChannelCode(payChannelCodeChineseName);
				excel.setGameId(gameIdChineseName);
				if(sdkNotifyMmdo.getAmount()==null){
					excel.setAmount(0f);
				}else{
					excel.setAmount(sdkNotifyMmdo.getAmount());
				}
				if(sdkNotifyMmdo.getAdditionalStatus()!=null){
					if(sdkNotifyMmdo.getAdditionalStatus()==0){
						additionalStatusChineseName="否";
					}else if(sdkNotifyMmdo.getAdditionalStatus()==1){
						additionalStatusChineseName="是";
					}
				}
				excel.setAdditionalStatus(additionalStatusChineseName);
				if(StringUtils.isEmpty(sdkNotifyMmdo.getMobile())||StringUtils.isBlank(sdkNotifyMmdo.getMobile())){
					excel.setMobile("");
				}else{
					excel.setMobile(sdkNotifyMmdo.getMobile());
				}
				if(StringUtils.isEmpty(sdkNotifyMmdo.getLinkid())||StringUtils.isBlank(sdkNotifyMmdo.getLinkid())){
					excel.setLinkid("");
				}else{
					excel.setLinkid(sdkNotifyMmdo.getLinkid());
				}
				if(StringUtils.isEmpty(sdkNotifyMmdo.getSpid())||StringUtils.isBlank(sdkNotifyMmdo.getSpid())){
					excel.setSpid("");
				}else{
					excel.setSpid(sdkNotifyMmdo.getSpid());
				}
				if(StringUtils.isEmpty(sdkNotifyMmdo.getCmd())||StringUtils.isBlank(sdkNotifyMmdo.getCmd())){
					excel.setCmd("");
				}else{
					excel.setCmd(sdkNotifyMmdo.getCmd());
				}
				if(StringUtils.isEmpty(sdkNotifyMmdo.getSpnum())||StringUtils.isBlank(sdkNotifyMmdo.getSpnum())){
					excel.setSpnum("");
				}else{
					excel.setSpnum(sdkNotifyMmdo.getSpnum());
				}
				if(StringUtils.isEmpty(sdkNotifyMmdo.getKey())||StringUtils.isBlank(sdkNotifyMmdo.getKey())){
					excel.setKey("");
				}else{
					excel.setKey(sdkNotifyMmdo.getKey());
				}
				if(StringUtils.isNotEmpty(sdkNotifyMmdo.getStatusDetail())&&StringUtils.isNotBlank(sdkNotifyMmdo.getStatusDetail())){
					statusDetail=sdkNotifyMmdo.getStatusDetail();
				}
				excel.setStatusDetail(statusDetail);
				if(StringUtils.isNotEmpty(sdkNotifyMmdo.getOriginalcode())&&StringUtils.isNotBlank(sdkNotifyMmdo.getOriginalcode())){
					originalcode=sdkNotifyMmdo.getOriginalcode();
				}
				excel.setOriginalcode(originalcode);
				resultList.add(excel);
			}
			// 进行转码，使其支持中文文件名
			// 产生工作簿对象
			HSSFWorkbook workbook = null;

			workbook = ExcelExportUtil.exportExcel("导出信息", SdkNotifyMmdoExcel.class,
					resultList);
			fOut = response.getOutputStream();
			workbook.write(fOut);
		} catch (UnsupportedEncodingException e1) {
			logger.error("the outputExcel UnsupportedEncodingException error is :"+e1);
		} catch (Exception e) {
			logger.error("the outputExcel Exception error is :"+e);
		} finally {
			try {
				fOut.flush();
				fOut.close();
			} catch (IOException e) {

			}
		}
	}
}
