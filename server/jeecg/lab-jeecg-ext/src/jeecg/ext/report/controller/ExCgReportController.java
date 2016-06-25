package jeecg.ext.report.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.cgform.engine.FreemarkerHelper;
import jeecg.cgreport.exception.CgReportNotFoundException;
import jeecg.ext.online.db.entity.ExCgFormDataSource;
import jeecg.ext.report.service.ExCgReportServiceI;
import jeecg.ext.online.service.DataPrevilegeUtil;
import jeecg.ext.report.util.QueryParamUtil;
import jeecg.ext.service.impl.JdbcServiceImpl;
import jeecg.system.service.SystemService;

import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/exCgReportController")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ExCgReportController extends BaseController {
	
	@Autowired
	private ExCgReportServiceI cgReportService;
	
	
	public SystemService getSystemService() {
		return systemService;
	}

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	@RequestMapping(params = "list")
	public void list(String id, HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> cgReportMap = null;
		try {
			cgReportMap = cgReportService.queryCgReportConfig(id);
		} catch (Exception e) {
			throw new CgReportNotFoundException("报表不存在: " + id);
		}
		FreemarkerHelper viewEngine = new FreemarkerHelper();
		loadVars(cgReportMap);
		
		StringBuilder def=new StringBuilder("");
		Enumeration<String> names=request.getParameterNames();
		while(names.hasMoreElements()){
			String name=names.nextElement();
			if(name.startsWith("_q_")){
				String v=request.getParameter(name);
				if(StringUtils.isNotEmpty(v)){
					def.append("&");					
					def.append(name.substring(3)).append("=").append(v);
				}
			}
		}	
		cgReportMap.put("default_query_param",def.toString());
		String html = viewEngine.parseTemplate(
				"jeecg/ext/report/tpl/cgreportlist.ftl", cgReportMap);
		try {
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter writer = response.getWriter();
			writer.println(html);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(params = "datagrid")
	public void datagrid(String configId, String page, String field,
			String rows, String sort, String order, HttpServletRequest request,
			HttpServletResponse response) {

		Map cgReportMap = null;

		try {
			cgReportMap = cgReportService.queryCgReportConfig(configId);
			if (cgReportMap.size() <= 0)
				throw new CgReportNotFoundException("报表不存在: " + configId);
		} catch (Exception e) {
			throw new CgReportNotFoundException("报表异常: " + configId
					+ e.getMessage());

		}
		Map configM = (Map) (Map) cgReportMap.get("main");
		String querySql = (String) configM.get("cgreport_sql");
		List items = (List) (List) cgReportMap.get("items");
		Map queryparams = new LinkedHashMap();
		for (Iterator iterator = items.iterator(); iterator.hasNext();) {
			Map item = (Map) iterator.next();
			String isQuery = (String) item.get("search_flag");
			if ("Y".equalsIgnoreCase(isQuery)) {
				QueryParamUtil.loadQueryParams(request, item, queryparams);
			}
		}
		int p = page != null ? Integer.parseInt(page) : 1;
		int r = rows != null ? Integer.parseInt(rows) : 99999;

		String qs = querySql;
		qs = qs.replace("''", "'");
		if (sort != null && sort.length() > 0) {
			qs += " order by " + sort;
			if (order != null) {
				qs += " " + order;
			}
		}
		List result = cgReportService.queryByCgReportSql(configId, qs, queryparams, p, r);
				//exCgReportService.queryByCgReportSql(qs, queryparams, p, r);
		
		querySql = DataPrevilegeUtil.replaceSQL(querySql);

		long size = cgReportService.countQueryByCgReportSql(configId,querySql.replace("''", "'"),
				queryparams);
				//cgReportService.countQueryByCgReportSql(querySql.replace("''", "'"),
				//queryparams);
		dealDic(result, items);
		dealReplace(result, items);
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");

		try {
			PrintWriter writer = response.getWriter();
			writer.println(QueryParamUtil.getJson(result, Long.valueOf(size)));
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadVars(Map cgReportMap) {

		Map mainM = (Map) (Map) cgReportMap.get("main");
		List fieldList = (List) (List) cgReportMap.get("items");
		List queryList = new ArrayList(0);
		for (Iterator iterator = fieldList.iterator(); iterator.hasNext();) {
			Map fl = (Map) iterator.next();
			String isQuery = (String) fl.get("search_flag");
			if ("Y".equalsIgnoreCase(isQuery)) {
				loadDic(fl, fl);
				queryList.add(fl);
			}
		}
		cgReportMap.put("config_id", mainM.get("code"));
		cgReportMap.put("config_name", mainM.get("name"));
		cgReportMap.put("config_fieldList", fieldList);
		cgReportMap.put("config_queryList", queryList);
	}

	private void dealDic(List result, List beans) {

		for (Iterator iterator = beans.iterator(); iterator.hasNext();) {
			Map bean = (Map) iterator.next();
			String dict_code = (String) bean.get("dict_code");
			if (!StringUtil.isEmpty(dict_code)) {
				List dicDatas = queryDic(dict_code);
				for (Iterator iterator1 = result.iterator(); iterator1
						.hasNext();) {
					Map r = (Map) iterator1.next();
					String value = String
							.valueOf(r.get(bean.get("field_name")));
					for (Iterator iterator2 = dicDatas.iterator(); iterator2
							.hasNext();) {
						Map m = (Map) iterator2.next();
						String typecode = String.valueOf(m.get("typecode"));
						String typename = String.valueOf(m.get("typename"));
						if (value.equalsIgnoreCase(typecode)) {
							r.put(bean.get("field_name"), typename);
						}
					}
				}
			}
		}
	}

	private void dealReplace(List result, List beans) {

		Iterator iterator = beans.iterator();
		while (iterator.hasNext()) {
			Map bean = (Map) iterator.next();
			String replace = (String) bean.get("replace_value");
			if (!StringUtil.isEmpty(replace))

				try {
					String groups[] = replace.split(",");
					String as[] = groups;
					int i = 0;
					for (int j = as.length; i < j; i++) {
						String g = as[i];
						String items[] = g.split("_");
						String v = items[0];
						String txt = items[1];
						for (Iterator iterator1 = result.iterator(); iterator1
								.hasNext();) {
							Map r = (Map) iterator1.next();
							String value = String.valueOf(r.get(bean
									.get("field_name")));
							if (value.equalsIgnoreCase(v))
								r.put(bean.get("field_name"), txt);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	@Autowired
	private SystemService systemService;

	

	public List<String> getMetaField(String sql) {
		JdbcServiceImpl jdbcService =new  JdbcServiceImpl();
		List list = jdbcService.getQuerySqlMeta(sql);
		jdbcService.close();
		return list;	
	}
	
	public List<String> getMetaField(String sql,ExCgFormDataSource ds) {
		JdbcServiceImpl jdbcService =new  JdbcServiceImpl(ds.getJdbcDriver(),ds.getJdbcUrl(),ds.getJdbcUsername(),ds.getJdbcPassword());
		List list = jdbcService.getQuerySqlMeta(sql);
		jdbcService.close();
		return list;	
	}

	@RequestMapping(params = { "getFields" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public Object getSqlFields(HttpServletRequest request, HttpServletResponse response,String sql) {
		String datasource=request.getParameter(ExCgReportServiceI.DATASOURCE_KEY);
		sql = DataPrevilegeUtil.replaceSQL(sql);
		sql = unConvertSQL(sql);
		List result = null;
		Map reJson = new HashMap();
		try {
			if(!StringUtils.isNotEmpty(datasource)){
				result = getMetaField(sql);
			}else{
				ExCgFormDataSource ds=this.systemService.getEntity(ExCgFormDataSource.class, datasource);
				if(ds!=null){
					result = getMetaField(sql,ds);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (result == null) {
			reJson.put("status", "error");
			return reJson;
		} else {
			reJson.put("status", "success");
			reJson.put("datas", result);
			reJson.put("sql",sql);//StringEscapeUtils.escapeSql(sql));
			return reJson;
		}
	}

	public String convertSQL(String sql) {
		return sql.replace("'", "''");
	}
	public String unConvertSQL(String sql) {
		return sql.replace("''", "'");
	}

	private void loadDic(Map m, Map cgReportMap) {
		String dict_code = (String) cgReportMap.get("dict_code");
		if (StringUtil.isEmpty(dict_code)) {
			m.put("field_dictlist", new ArrayList(0));
			return;
		} else {
			List dicDatas = queryDic(dict_code);
			m.put("field_dictlist", dicDatas);
			return;
		}
	}

	private List queryDic(String diccode) {
		boolean  isSQL = false;
		String flag = "select";
		isSQL = diccode.toLowerCase().contains(flag);
		StringBuilder dicSql = new StringBuilder();
		if(isSQL){
			dicSql.append(diccode);
		}else{
			dicSql.append(" SELECT TYPECODE,TYPENAME FROM");
			dicSql.append(" t_s_type");
			dicSql.append(" WHERE TYPEGROUPID = ");
			dicSql.append((new StringBuilder(
					" (SELECT ID FROM t_s_typegroup WHERE TYPEGROUPCODE = '"))
					.append(diccode).append("' )").toString());
		}
		String sql  = DataPrevilegeUtil.replaceSQL(dicSql.toString());
		List dicDatas = cgReportService.findForJdbc(sql,new Object[0]);
		return dicDatas;
	}

	

}
