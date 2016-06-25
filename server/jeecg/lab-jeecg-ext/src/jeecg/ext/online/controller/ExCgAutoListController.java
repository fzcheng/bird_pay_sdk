package jeecg.ext.online.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.cgform.engine.FreemarkerHelper;
import jeecg.ext.online.db.ExDatabaseService;
import jeecg.ext.online.db.entity.ExCgFormFieldEntity;
import jeecg.ext.online.db.entity.ExCgFormHeadEntity;
import jeecg.ext.report.util.QueryParamUtil;
import jeecg.system.pojo.base.TSOperation;
import jeecg.system.service.SystemService;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/exCgAutoListController")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ExCgAutoListController extends BaseController {
	static Log logger = LogFactory.getLog(ExCgAutoListController.class);

	@RequestMapping(params = "list")
	public void list(String id, HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> configs = exDatabaseService.queryConfigs(id);

		FreemarkerHelper viewEngine = new FreemarkerHelper();
		Map<String, Object> paras = new HashMap<String, Object>();

		loadVars(configs, paras);
		
		StringBuilder def=new StringBuilder("");
		Map<String,String> q=new HashMap<String,String>();
		Enumeration<String> names=request.getParameterNames();
		while(names.hasMoreElements()){
			String name=names.nextElement();
			if(name.startsWith("_q_")){
				String v=request.getParameter(name);
				if(StringUtils.isNotEmpty(v)){
					def.append("&");					
					def.append(name.substring(3)).append("=").append(v);
					q.put(name.substring(3), v);
				}
			}
		}
		//List<TSOperation> operationList =  getButtonsByFunctionId(functionId, roleId);
		//paras.put("config_buttons", operationList);
		paras.put("q", q);
		paras.put("default_query_param",def.toString());

		String html = viewEngine.parseTemplate("/jeecg/ext/online/ftl/autolist.ftl", paras);

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
	
	public List<TSOperation> getButtonsByFunctionId(String functionId, String roleId){
		List<TSOperation> operationList = null;
		
		return operationList;
	}
	@RequestMapping(params ="rowEdit")
	public void rowEdit(HttpServletRequest request){
		System.out.println("haha");
		int i = 0;
		
	}

	@RequestMapping(params = "datagrid")
	public void datagrid(String configId, String page, String field, String rows, String sort, String order, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {

		Map configs = exDatabaseService.queryConfigs(configId);
		String table = (String) configs.get("tableName");
		Map params = new HashMap();

		 
		Set beans = (Set) configs.get("fields");
		for (Iterator iterator = beans.iterator(); iterator.hasNext();) {
			ExCgFormFieldEntity b = (ExCgFormFieldEntity) iterator.next();
			 
			if ("Y".equals(b.getIsQuery())) {
				QueryParamUtil.loadQueryParams(request, b, params);
			}
		}
		 
		
		
		int p = page != null ? Integer.parseInt(page) : 1;
		int r = rows != null ? Integer.parseInt(rows) : 99999;

		List result = exDatabaseService.querySingle(table, field.toString(), params, sort, order, p, r);
		Long size = exDatabaseService.getQuerySingleSize(table, field, params);
		dealDic(result, beans);
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");

		try {
			PrintWriter writer = response.getWriter();
			writer.println(QueryParamUtil.getJson(result, size));
			writer.flush();
		} catch (IOException e) {
			logger.error("" + e, e);
		}
	}

	private void dealDic(List result, Set beans) {		 
		for (Iterator iterator = beans.iterator(); iterator.hasNext();) {
			ExCgFormFieldEntity bean = (ExCgFormFieldEntity) iterator.next();
			String dicT = bean.getDictTable();
			String dicF = bean.getDictField();
			if (!StringUtil.isEmpty(dicT) || !StringUtil.isEmpty(dicF)) {
				List dicDatas = queryDic(bean.getCgformHead().getTableName(),dicT, dicF, bean.getDictFieldLabel());
				for (Iterator iterator1 = result.iterator(); iterator1.hasNext();) {
					Map r = (Map) iterator1.next();
					String value = String.valueOf(r.get(bean.getFieldName()));
					for (Iterator iterator2 = dicDatas.iterator(); iterator2.hasNext();) {
						Map m = (Map) iterator2.next();
						String typecode = String.valueOf(m.get("typecode"));
						String typename = String.valueOf(m.get("typename"));
						if (value.equalsIgnoreCase(typecode)) {
							r.put(bean.getFieldName(), typename);
							
							r.put("_"+bean.getFieldName()+"_", typecode);
						}
					}
				}
			}
		}
	}

	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(String configId, String id, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String table = (String) exDatabaseService.queryConfigs(configId).get("tableName");
		
		if(exDatabaseService.deleteTable(table, id)){
			
			
			String message = "删除成功";
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);

			j.setMsg(message);
		}else{
			String message = "无法删除Online表: "+table;
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_ERROR);
			j.setMsg(message);
		}
		return j;
	}

	private Map loadVars(Map configs, Map paras) {
		paras.putAll(configs);
		List fieldList = new ArrayList();
		List queryList = new ArrayList();
		StringBuilder fields = new StringBuilder();
		ExCgFormFieldEntity bean;
		String id_field="id";
		for (Iterator iterator = ((Set) configs.get("fields")).iterator(); iterator.hasNext(); fields.append(bean.getFieldName()).append(",")) {
			bean = (ExCgFormFieldEntity) iterator.next();
			if("Y".equals(bean.getIsKey())){
				id_field=bean.getFieldName();
			}
			Map fm = new HashMap();
			fm.put("field_id", bean.getFieldName());
			fm.put("field_title", bean.getContent());
			fm.put("field_isShow", bean.getIsShow());
			fm.put("field_queryMode", bean.getQueryMode());
			fm.put("field_showType", bean.getShowType());
			fm.put("field_type", bean.getType());
			fm.put("field_href", bean.getFieldHref() != null ? ((Object) (bean.getFieldHref())) : "");
			fm.put("field_formatter", StringUtils.isEmpty(bean.getFormatter())?"":bean.getFormatter());
			fm.put("field_keyType", StringUtils.isEmpty(bean.getFieldValue())?"":bean.getFieldValue());
			
			String field_href_title="字段链接";
			String href=(String)fm.get("field_href");
			String autolist="exCgAutoListController.do?";
			if(href.startsWith(autolist)){
				Map<String,String> hpv=new HashMap<String,String>();
				for(String pv:href.substring(autolist.length()).split("\\&")){
					int x=pv.indexOf("=");
					if(x>0){
						hpv.put(pv.substring(0,x),pv.substring(x+1));
					}else{
						hpv.put(pv, "");
					}
				}
				String tabname=hpv.get("id");
				ExCgFormHeadEntity tabhead=exDatabaseService.getCgFormHeadByTableName(tabname);
				if(tabhead!=null){
					field_href_title=tabhead.getContent();
				}
			}
			fm.put("field_href_title", field_href_title);
			
			loadDic(fm, bean);
			fieldList.add(fm);
			if ("Y".equals(bean.getIsQuery())) {
				Map fmq = new HashMap();
				fmq.put("field_id", bean.getFieldName());
				fmq.put("field_title", bean.getContent());
				fmq.put("field_queryMode", bean.getQueryMode());
				fmq.put("field_type", bean.getType());
				fmq.put("field_showType", bean.getShowType());
				loadDic(fmq, bean);
				queryList.add(fmq);
			}
		}
		
		paras.put("id_field", id_field);
		paras.put("config_fieldList", fieldList);
		paras.put("config_queryList", queryList);
		paras.put("fields", fields);
		return paras;
	}

	private void loadDic(Map m, ExCgFormFieldEntity bean) {

		String dicT = bean.getDictTable();
		String dicF = bean.getDictField();
		if (StringUtil.isEmpty(dicT) && StringUtil.isEmpty(dicF)) {
			m.put("field_dictlist", new ArrayList(0));
			return;
		} else {
			List dicDatas = queryDic(bean.getCgformHead().getTableName(),dicT, dicF, bean.getDictFieldLabel());
			m.put("field_dictlist", dicDatas);
			return;
		}
	}

	private List queryDic(String tableName,String dicT, String dicF, String dicText) {

		StringBuilder dicSql = new StringBuilder();
		if (StringUtil.isEmpty(dicT)) {
			dicT = "t_s_type";
			dicSql.append(" SELECT TYPECODE,TYPENAME FROM");
			dicSql.append((new StringBuilder(" ")).append(dicT).toString());
			dicSql.append(" WHERE TYPEGROUPID = ");
			dicSql.append((new StringBuilder(" (SELECT ID FROM t_s_typegroup WHERE TYPEGROUPCODE = '")).append(dicF).append("' )").toString());
			
			return systemService.findForJdbc(dicSql.toString(), new Object[0]);
		} else {
			if(StringUtils.isEmpty(dicText)){
				dicText = dicF;
			}
			dicSql.append("select distinct ").append(dicF).append(" as typecode, ");
			dicSql.append(dicText).append(" as typename ");
			dicSql.append(" from ").append(dicT);
			dicSql.append(" order by 2");
			
			return exDatabaseService.getJdbcTemplate(tableName).queryForList(dicSql.toString(), new Object[0]);
		}

		  
		 
	}

	private SystemService systemService;

	@Autowired
	private ExDatabaseService exDatabaseService;

	public SystemService getSystemService() {
		return systemService;
	}

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	public ExDatabaseService getExDatabaseService() {
		return exDatabaseService;
	}

	public void setExDatabaseService(ExDatabaseService exDatabaseService) {
		this.exDatabaseService = exDatabaseService;
	}
}
