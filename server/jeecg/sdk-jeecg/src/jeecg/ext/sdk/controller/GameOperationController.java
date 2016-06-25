package jeecg.ext.sdk.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.cgform.engine.FreemarkerHelper;
import jeecg.cgreport.common.CgReportConstant;
import jeecg.cgreport.util.QueryParamUtil;
import jeecg.ext.sdk.service.ExcelService;
import jeecg.system.service.SystemService;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.util.BrowserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/gameOperationController")
public class GameOperationController extends BaseController {

	static Log logger = LogFactory.getLog(GameOperationController.class);
	@Autowired
	private SystemService systemService;

	/**
	 * 运营分析
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "operationAnalyzeList")
	public void operationAnalyzeList(HttpServletRequest request,
			HttpServletResponse response) {
		FreemarkerHelper viewEngine = new FreemarkerHelper();
		String sql = "SELECT game_id, NAME AS game_name FROM game_sdk.sdk_game";
		List game = this.systemService.findForJdbc(sql, null);
		Map map = new HashMap(0);
		map.put("game", game);
		String html = viewEngine.parseTemplate(
				"/jeecg/ext/sdk/ftl/sdk_operation_analyze.ftl", map);
		try {
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter writer = response.getWriter();
			writer.println(html);
			writer.flush();
		} catch (IOException e) {
			logger.error("" + e, e);
		}

	}

	@RequestMapping(params = "exportXls")
	public void operationAnalyzeExcel(HttpServletRequest request,
			HttpServletResponse response) {
		String codedFileName = "游戏运营分析报表";

		List<Map<String, Object>> fieldList = new ArrayList<Map<String, Object>>();
		Map map1 = new HashMap();
		map1.put("field_txt", "指标");
		map1.put("field_name", "apiName");
		fieldList.add(map1);
		Map map2 = new HashMap();
		map2.put("field_txt", "指标值");
		map2.put("field_name", "apiValue");
		fieldList.add(map2);
		List<Map<String, Object>> result = excelData(request);

		response.setContentType("application/vnd.ms-excel");
		OutputStream fOut = null;
		try {
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
			HSSFWorkbook workbook = null;
			ExcelService excelService = new ExcelService();
			workbook = excelService.exportExcel(codedFileName, fieldList,result);
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
	
	public List<Map<String, Object>> excelData(HttpServletRequest request){
		String game_id = (String) request.getParameter("game_id");
		String stat_day_begin = (String) request.getParameter("stat_day_begin");
		String stat_day_end = (String) request.getParameter("stat_day_end");
		boolean dayTips = false;
		if (stat_day_end == null || stat_day_end.equals("")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			stat_day_end = sdf.format(new Date());
			dayTips = true;
		}
		if (stat_day_begin == null || stat_day_begin.equals("")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			stat_day_begin = sdf.format(new Date());
		}
		if (game_id == null || game_id.equals("")) {
			List list = this.systemService.findForJdbc(
					"select game_id from  game_sdk.sdk_operation_data limit 1",
					1, 1);
			game_id = String.valueOf(((Map) list.get(0)).get("game_id"));
		}

		List<Map<String, Object>> apiList = new ArrayList<Map<String, Object>>();
		boolean flag = true;
		String sql = "select sum(cnt_user_reg) as apiName from game_sdk.sdk_operation_data where game_id ="
				+ game_id
				+ " and stat_day >='"
				+ stat_day_begin
				+ "' and stat_day <= '" + stat_day_end + "'";
		addAPI("时间段", stat_day_begin + "~" + (dayTips ? "现在" : stat_day_end),
				apiList);
		/* 注册人数 */
		sql = "select sum(cnt_user_reg) as apiName from game_sdk.sdk_operation_data where game_id ="
				+ game_id
				+ " and stat_day >='"
				+ stat_day_begin
				+ "' and stat_day <= '" + stat_day_end + "'";
		addAPI(flag, "注册人数", sql, apiList);

		/* 总充值人数 */
		sql = "select sum(cnt_user_recharge) as apiName from game_sdk.sdk_operation_data where game_id ="
				+ game_id
				+ " and stat_day >='"
				+ stat_day_begin
				+ "' and stat_day <= '" + stat_day_end + "'";
		addAPI(flag, "总充值人数", sql, apiList);

		/* 总充值金额 */
		sql = "select sum(total_amount) as apiName from game_sdk.sdk_operation_data where game_id ="
				+ game_id
				+ " and stat_day >='"
				+ stat_day_begin
				+ "' and stat_day <= '" + stat_day_end + "'";
		addAPI(flag, "总充值金额", sql, apiList);
		/* 注册ARPU值 */

		sql = "select CONCAT(TRUNCATE((sum(cnt_user_reg)/sum(total_amount))*100,2),'%') as apiName from game_sdk.sdk_operation_data where game_id ="
				+ game_id
				+ " and stat_day >='"
				+ stat_day_begin
				+ "' and stat_day <= '" + stat_day_end + "'";
		addAPI(flag, "注册ARPU值", sql, apiList);

		/* 充值ARPU值 */
		sql = "select CONCAT(TRUNCATE((sum(cnt_user_recharge)/sum(total_amount))*100,2),'%') as apiName from game_sdk.sdk_operation_data where game_id ="
				+ game_id
				+ " and stat_day >='"
				+ stat_day_begin
				+ "' and stat_day <= '" + stat_day_end + "'";
		addAPI(flag, "充值ARPU值", sql, apiList);

		/* 时间段内注册并付费人数 */
		sql = "SELECT ROUND(SUM(b.amount), 2) AS reg_pay_amount,COUNT(a.uid) AS reg_pay_num FROM game_sdk.sdk_user a,game_sdk.sdk_order b WHERE TO_DAYS(a.reg_time) >="+stat_day_begin 
				+" AND TO_DAYS(a.reg_time) <=" + stat_day_end + " AND TO_DAYS(b.complete_time) >="+stat_day_begin+
				" AND TO_DAYS(b.complete_time) <="+stat_day_end+
				" AND a.uid = b.uid AND a.login_game = b.game_id AND b.game_id ="+game_id+" AND b.status = 1;";
		
		  
		  
		  
		addAPI(flag, "时间段内注册并付费人数", sql, apiList);
		/* 时间段内注册并付费金额 */
		addAPI(flag, "时间段内注册并付费金额", sql, apiList);
		
		
		/* 新用户充值人数 */
		addAPI(flag, "新用户充值人数", sql, apiList);
		/* 新用户充值金额 */
		addAPI(flag, "新用户充值金额", sql, apiList);
		
		
		
		/* 第一次充值金额 
		addAPI(flag, "第一次充值金额", sql, apiList);*/
		
		
		/* 老用户充值数 */
		addAPI(flag, "老用户充值数", sql, apiList);
		/* 老用户充值金额 */
		addAPI(flag, "老用户充值金额", sql, apiList);
		
/*		 时间段内充值大于0人数 
		addAPI(flag, "时间段内充值大于0人数", sql, apiList);
		 时间段内充值大于0金额 
		addAPI(flag, "时间段内充值大于0金额", sql, apiList);*/
		return apiList;
	}

	@RequestMapping(params = "datagrid")
	public void operationAnalyze(HttpServletRequest request,
			HttpServletResponse response) {

		String game_id = (String) request.getParameter("game_id");
		String stat_day_begin = (String) request.getParameter("stat_day_begin");
		String stat_day_end = (String) request.getParameter("stat_day_end");
		boolean dayTips = false;
		if (stat_day_end == null || stat_day_end.equals("")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			stat_day_end = sdf.format(new Date());
			dayTips = true;
		}
		if (stat_day_begin == null || stat_day_begin.equals("")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			stat_day_begin = sdf.format(new Date());
		}
		if (game_id == null || game_id.equals("")) {
			List list = this.systemService.findForJdbc(
					"select game_id from  game_sdk.sdk_operation_data limit 1",
					1, 1);
			game_id = String.valueOf(((Map) list.get(0)).get("game_id"));
		}

		List<Map<String, Object>> apiList = new ArrayList<Map<String, Object>>();
		boolean flag = true;
		String sql = "select sum(cnt_user_reg) as apiName from game_sdk.sdk_operation_data where game_id ="
				+ game_id
				+ " and stat_day >='"
				+ stat_day_begin
				+ "' and stat_day <= '" + stat_day_end + "'";
		addAPI("时间段", stat_day_begin + "~" + (dayTips ? "现在" : stat_day_end),
				apiList);
		/* 注册人数 */
		sql = "select sum(cnt_user_reg) as apiName from game_sdk.sdk_operation_data where game_id ="
				+ game_id
				+ " and stat_day >='"
				+ stat_day_begin
				+ "' and stat_day <= '" + stat_day_end + "'";
		addAPI(flag, "注册人数", sql, apiList);

		/* 总充值人数 */
		sql = "select sum(cnt_user_recharge) as apiName from game_sdk.sdk_operation_data where game_id ="
				+ game_id
				+ " and stat_day >='"
				+ stat_day_begin
				+ "' and stat_day <= '" + stat_day_end + "'";
		addAPI(flag, "总充值人数", sql, apiList);

		/* 总充值金额 */
		sql = "select sum(total_amount) as apiName from game_sdk.sdk_operation_data where game_id ="
				+ game_id
				+ " and stat_day >='"
				+ stat_day_begin
				+ "' and stat_day <= '" + stat_day_end + "'";
		addAPI(flag, "总充值金额", sql, apiList);
		/* 注册ARPU值 */

		sql = "select CONCAT(TRUNCATE((sum(cnt_user_reg)/sum(total_amount))*100,2),'%') as apiName from game_sdk.sdk_operation_data where game_id ="
				+ game_id
				+ " and stat_day >='"
				+ stat_day_begin
				+ "' and stat_day <= '" + stat_day_end + "'";
		addAPI(flag, "注册ARPU值", sql, apiList);

		/* 充值ARPU值 */
		sql = "select CONCAT(TRUNCATE((sum(cnt_user_recharge)/sum(total_amount))*100,2),'%') as apiName from game_sdk.sdk_operation_data where game_id ="
				+ game_id
				+ " and stat_day >='"
				+ stat_day_begin
				+ "' and stat_day <= '" + stat_day_end + "'";
		addAPI(flag, "充值ARPU值", sql, apiList);

		/* 时间段内注册并付费人数 */
		sql = "SELECT count(a.uid) AS apiName FROM game_sdk.sdk_user a,game_sdk.sdk_order b WHERE TO_DAYS(a.reg_time) >=TO_DAYS('"+stat_day_begin 
				+"') AND TO_DAYS(a.reg_time) <=TO_DAYS('" + stat_day_end + "') AND TO_DAYS(b.complete_time) >=TO_DAYS('"+stat_day_begin+
				"') AND TO_DAYS(b.complete_time) <=TO_DAYS('"+stat_day_end+
				"') AND a.uid = b.uid AND a.login_game = b.game_id AND b.game_id ="+game_id+" AND b.status = 1";
		
		addAPI(flag, "时间段内注册并付费人数", sql, apiList);
		/* 时间段内注册并付费金额 */
		sql = "SELECT ROUND(SUM(b.amount), 2) AS apiName FROM game_sdk.sdk_user a,game_sdk.sdk_order b WHERE TO_DAYS(a.reg_time) >=TO_DAYS('"+stat_day_begin 
				+"') AND TO_DAYS(a.reg_time) <=TO_DAYS('" + stat_day_end + "') AND TO_DAYS(b.complete_time) >=TO_DAYS('"+stat_day_begin+
				"') AND TO_DAYS(b.complete_time) <=TO_DAYS('"+stat_day_end+
				"') AND a.uid = b.uid AND a.login_game = b.game_id AND b.game_id ="+game_id+" AND b.status = 1";
		addAPI(flag, "时间段内注册并付费金额", sql, apiList);
		
		
		/* 新用户充值人数 */
		
		sql = " SELECT count(b.uid) as apiName FROM game_sdk.sdk_order b WHERE b.game_id ="+game_id+"" +
				" AND b.status = 1 AND TO_DAYS(b.complete_time) >=TO_DAYS('"+stat_day_begin+"') AND TO_DAYS(b.complete_time) <=TO_DAYS('"+ stat_day_end+
				"') AND b.uid NOT IN (SELECT a.uid FROM game_sdk.sdk_order a WHERE a.status = 1 AND a.game_id ="+ game_id+" AND TO_DAYS(a.complete_time) <= TO_DAYS('"+stat_day_begin+"'))";
		addAPI(flag, "新用户充值人数", sql, apiList);
		/* 新用户充值金额 */
		sql = " SELECT ROUND(SUM(b.amount), 2) as apiName FROM game_sdk.sdk_order b WHERE b.game_id ="+game_id+"" +
				" AND b.status = 1 AND TO_DAYS(b.complete_time) >=TO_DAYS('"+stat_day_begin+"') AND TO_DAYS(b.complete_time) <=TO_DAYS('"+ stat_day_end+
				"') AND b.uid NOT IN (SELECT a.uid FROM game_sdk.sdk_order a WHERE a.status = 1 AND a.game_id ="+ game_id+" AND TO_DAYS(a.complete_time) <= TO_DAYS('"+stat_day_begin+"'))";
		
		addAPI(flag, "新用户充值金额", sql, apiList);
		/* 第一次充值金额 
		addAPI(flag, "第一次充值金额", sql, apiList);*/
		
		
		/* 老用户充值数 */
		sql = " SELECT count(b.uid) as apiName FROM game_sdk.sdk_order b, game_sdk.sdk_user a WHERE b.game_id ="+ game_id+" AND b.status = 1 AND TO_DAYS(b.complete_time) >= TO_DAYS('"+stat_day_begin+
				"') AND TO_DAYS(b.complete_time) <=TO_DAYS('"+ stat_day_end +"') AND b.uid = a.uid AND a.login_game="+game_id+" and  TO_DAYS(a.reg_time) < TO_DAYS('"+stat_day_begin+"')";
		addAPI(flag, "老用户充值数", sql, apiList);
		/* 老用户充值金额 */
		sql = " SELECT ROUND(SUM(b.amount), 2) AS apiName  FROM game_sdk.sdk_order b, game_sdk.sdk_user a WHERE b.game_id ="+ game_id+" AND b.status = 1 AND TO_DAYS(b.complete_time) >= TO_DAYS('"+stat_day_begin+
				"') AND TO_DAYS(b.complete_time) <=TO_DAYS('"+ stat_day_end +"') AND b.uid = a.uid AND a.login_game="+game_id+" and  TO_DAYS(a.reg_time) < TO_DAYS('"+stat_day_begin+"')";
		addAPI(flag, "老用户充值金额", sql, apiList);
		
		
		/* 时间段内充值大于0人数 */
/*		addAPI(flag, "时间段内充值大于0人数", sql, apiList);
		 时间段内充值大于0金额 
		addAPI(flag, "时间段内充值大于0金额", sql, apiList);*/
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");

		try {
			PrintWriter writer = response.getWriter();
			String html = QueryParamUtil.getJson(apiList,
					Long.valueOf(apiList.size()));
			JSONObject jsonObj = JSONObject.fromObject(html);
			jsonObj.put("game_id", game_id);
			writer.println(jsonObj.toString());
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addAPI(String apiName, String apiValue, List apiList) {
		Map oAPI = new HashMap(0);
		oAPI.put("apiName", apiName);
		oAPI.put("apiValue", apiValue);
		apiList.add(oAPI);
	}

	public void addAPI(boolean flag, String apiName, String sql, List apiList) {
		Map oAPI = new HashMap(0);
		String apiValue = "-";
		oAPI.put("apiName", apiName);
		List list = this.systemService.findForJdbc(sql, 1, 1);
		int size = 0;
		if (list != null) {
			size = list.size();
			if (size > 0) {
				Map map0 = (Map) list.get(0);
				if (map0.get("apiName") == null) {
					apiValue = "-";
				} else {

					apiValue = String.valueOf(map0.get("apiName"));
				}

			}
		}
		oAPI.put("apiValue", apiValue);
		apiList.add(oAPI);
	}

	/**
	 * 运营报表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "operationDayReportList")
	public ModelAndView operationDayReportList(HttpServletRequest request) {
		return new ModelAndView("gameoperation/operationdayreportlist");
	}

	@RequestMapping(params = "operationDayReportData")
	public void operationDayReportData(HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {

	}

	/**
	 * 市场日报
	 */
	@RequestMapping(params = "marketDayReportList")
	public ModelAndView marketDayReportList() {
		return new ModelAndView("gameoperation/marketdayreportlist");
	}

	@RequestMapping(params = "marketDayReportData")
	public void marketDayReportData() {

	}

}
