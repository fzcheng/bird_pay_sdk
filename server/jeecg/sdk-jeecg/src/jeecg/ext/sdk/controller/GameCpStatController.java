/**
 * 
 */
package jeecg.ext.sdk.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jeecg.cgreport.common.CgReportConstant;
import jeecg.cgreport.exception.CgReportNotFoundException;
import jeecg.ext.online.db.entity.ExCgFormDataSource;
import jeecg.ext.online.service.DataPrevilegeUtil;
import jeecg.ext.report.service.ExCgReportExcelServiceI;
import jeecg.ext.report.service.ExCgReportServiceI;
import jeecg.ext.report.util.QueryParamUtil;
import jeecg.ext.sdk.entity.SdkGameCp;
import jeecg.ext.service.impl.JdbcServiceImpl;
import jeecg.system.pojo.base.TSUser;
import jeecg.system.service.SystemService;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Merlin
 * 
 */
@Controller
@RequestMapping("/gamecpstat")
public class GameCpStatController extends BaseController {
  @Autowired
  private SystemService systemService;
  @Autowired
  private ExCgReportServiceI cgReportService;
  @Autowired
  private ExCgReportExcelServiceI cgReportExcelService;

  @RequestMapping(params = "list")
  public ModelAndView list(String id, HttpServletRequest request, HttpServletResponse response) {
    Map<String, Object> cgReportMap = null;
    
    Integer cpId = getCpId();
    
    try {
      cgReportMap = cgReportService.queryCgReportConfig(id);
    } catch (Exception e) {
      throw new CgReportNotFoundException("报表不存在: " + id);
    }
    
    loadVars(cgReportMap);

    StringBuilder def = new StringBuilder("");
    Enumeration<String> names = request.getParameterNames();
    while (names.hasMoreElements()) {
      String name = names.nextElement();
      if (name.startsWith("_q_")) {
        String v = request.getParameter(name);
        if (StringUtils.isNotEmpty(v)) {
          def.append("&");
          def.append(name.substring(3)).append("=").append(v);
        }
      }
    }
    def.append("&");
    def.append("cp_id=").append(cpId);
    cgReportMap.put("default_query_param",def.toString());
    ModelAndView mv = new ModelAndView();
    mv.addAllObjects(cgReportMap);
    mv.setViewName("gamecpstat/cgreportlist");
    return mv;
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @RequestMapping(params = "datagrid")
  public void datagrid(String configId, String page, String field, String rows, String sort, String order, HttpServletRequest request,
      HttpServletResponse response) {

    Map cgReportMap = null;
    Integer cpId = getCpId();
    
    try {
      cgReportMap = cgReportService.queryCgReportConfig(configId);
      if (cgReportMap.size() <= 0)
        throw new CgReportNotFoundException("报表不存在: " + configId);
    } catch (Exception e) {
      throw new CgReportNotFoundException("报表异常: " + configId + e.getMessage());
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
    queryparams.put("cp_id", " = " + cpId);
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
    // exCgReportService.queryByCgReportSql(qs, queryparams, p, r);

    querySql = DataPrevilegeUtil.replaceSQL(querySql);

    long size = cgReportService.countQueryByCgReportSql(configId, querySql.replace("''", "'"), queryparams);
    // cgReportService.countQueryByCgReportSql(querySql.replace("''", "'"),
    // queryparams);
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

  @SuppressWarnings({ "unused", "unchecked", "rawtypes" })
  @RequestMapping(params = "exportXls")
  public void exportXls(HttpServletRequest request,
      HttpServletResponse response) {
    //step.1 设置，获取配置信息
    String codedFileName = "报表";
    String sheetName="导出信息";
    
    Integer cpId = getCpId();
    
    if (StringUtil.isNotEmpty(request.getParameter("configId"))) {
      String configId = request.getParameter("configId");
      Map<String, Object> cgReportMap = null;
      try{
        cgReportMap = cgReportService.queryCgReportConfig(configId);
      }catch (Exception e) {
        throw new CgReportNotFoundException("动态报表配置不存在!");
      }
      List<Map<String,Object>> fieldList = (List<Map<String, Object>>) cgReportMap.get(CgReportConstant.ITEMS);
      Map configM = (Map) cgReportMap.get(CgReportConstant.MAIN);
      codedFileName = configM.get("name")+codedFileName;
      String querySql = (String) configM.get(CgReportConstant.CONFIG_SQL);
      List<Map<String,Object>> items = (List<Map<String, Object>>) cgReportMap.get(CgReportConstant.ITEMS);
      Map queryparams =  new LinkedHashMap<String,Object>();
      for(Map<String,Object> item:items){
        String isQuery = (String) item.get(CgReportConstant.ITEM_ISQUERY);
        if(CgReportConstant.BOOL_TRUE.equalsIgnoreCase(isQuery)){
          //step.2 装载查询条件
          QueryParamUtil.loadQueryParams(request, item, queryparams);
        }
      }
      queryparams.put("cp_id", " = " + cpId);
      //step.3 进行查询返回结果
      List<Map<String, Object>> result= cgReportService.queryByCgReportSql(configId,querySql, queryparams, -1, -1);
      dealDic(result, items);
      dealReplace(result, items);
      response.setContentType("application/vnd.ms-excel");
      OutputStream fOut = null;
      try {

        //step.4 根据浏览器进行转码，使其支持中文文件名
        String browse = BrowserUtils.checkBrowse(request);
        if ("MSIE".equalsIgnoreCase(browse.substring(0, 4))) {
          response.setHeader("content-disposition",
              "attachment;filename="
                  + java.net.URLEncoder.encode(codedFileName,
                      "UTF-8") + ".xls");
        } else {
          String newtitle = new String(codedFileName.getBytes("UTF-8"),
              "ISO8859-1");
          response.setHeader("content-disposition",
              "attachment;filename=" + newtitle + ".xls");
        }
        //step.5 产生工作簿对象
        HSSFWorkbook workbook = null;
        workbook = cgReportExcelService.exportExcel(codedFileName, fieldList, result);
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
    } else {
      throw new BusinessException("参数错误");
    }
  }
  
  @SuppressWarnings({ "rawtypes", "unchecked" })
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

  @SuppressWarnings({ "rawtypes", "unchecked" })
  private void dealDic(List result, List beans) {

    for (Iterator iterator = beans.iterator(); iterator.hasNext();) {
      Map bean = (Map) iterator.next();
      String dict_code = (String) bean.get("dict_code");
      if (!StringUtil.isEmpty(dict_code)) {
        List dicDatas = queryDic(dict_code);
        for (Iterator iterator1 = result.iterator(); iterator1.hasNext();) {
          Map r = (Map) iterator1.next();
          String value = String.valueOf(r.get(bean.get("field_name")));
          for (Iterator iterator2 = dicDatas.iterator(); iterator2.hasNext();) {
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

  @SuppressWarnings({ "rawtypes", "unchecked" })
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
            for (Iterator iterator1 = result.iterator(); iterator1.hasNext();) {
              Map r = (Map) iterator1.next();
              String value = String.valueOf(r.get(bean.get("field_name")));
              if (value.equalsIgnoreCase(v))
                r.put(bean.get("field_name"), txt);
            }
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
    }
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public List<String> getMetaField(String sql) {
    JdbcServiceImpl jdbcService = new JdbcServiceImpl();
    List list = jdbcService.getQuerySqlMeta(sql);
    jdbcService.close();
    return list;
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public List<String> getMetaField(String sql, ExCgFormDataSource ds) {
    JdbcServiceImpl jdbcService = new JdbcServiceImpl(ds.getJdbcDriver(), ds.getJdbcUrl(), ds.getJdbcUsername(), ds.getJdbcPassword());
    List list = jdbcService.getQuerySqlMeta(sql);
    jdbcService.close();
    return list;
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @RequestMapping(params = { "getFields" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
  @ResponseBody
  public Object getSqlFields(HttpServletRequest request, HttpServletResponse response, String sql) {
    String datasource = request.getParameter(ExCgReportServiceI.DATASOURCE_KEY);
    sql = DataPrevilegeUtil.replaceSQL(sql);
    sql = unConvertSQL(sql);
    List result = null;
    Map reJson = new HashMap();
    try {
      if (!StringUtils.isNotEmpty(datasource)) {
        result = getMetaField(sql);
      } else {
        ExCgFormDataSource ds = this.systemService.getEntity(ExCgFormDataSource.class, datasource);
        if (ds != null) {
          result = getMetaField(sql, ds);
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
      reJson.put("sql", sql);// StringEscapeUtils.escapeSql(sql));
      return reJson;
    }
  }

  public String convertSQL(String sql) {
    return sql.replace("'", "''");
  }

  public String unConvertSQL(String sql) {
    return sql.replace("''", "'");
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
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

  @SuppressWarnings("rawtypes")
  private List queryDic(String diccode) {
    boolean isSQL = false;
    String flag = "select";
    isSQL = diccode.toLowerCase().contains(flag);
    StringBuilder dicSql = new StringBuilder();
    if (isSQL) {
      dicSql.append(diccode);
    } else {
      dicSql.append(" SELECT TYPECODE,TYPENAME FROM");
      dicSql.append(" t_s_type");
      dicSql.append(" WHERE TYPEGROUPID = ");
      dicSql.append((new StringBuilder(" (SELECT ID FROM t_s_typegroup WHERE TYPEGROUPCODE = '")).append(diccode).append("' )").toString());
    }
    String sql = DataPrevilegeUtil.replaceSQL(dicSql.toString());
    List dicDatas = cgReportService.findForJdbc(sql, new Object[0]);
    return dicDatas;
  }

  private Integer getCpId() {
    HttpSession session = ContextHolderUtils.getSession();
    Integer cpId = (Integer) session.getAttribute("cpId");
    if (cpId == null) {
      TSUser user=ResourceUtil.getSessionUserName();
      if (StringUtils.isNumeric(user.getUserName())) {
        cpId = Integer.valueOf(user.getUserName());
        session.setAttribute("cpId", cpId);
      } else {
        SdkGameCp gameCp = systemService.findUniqueByProperty(SdkGameCp.class, "loginName", user.getUserName());
        if (gameCp != null) {
          cpId = gameCp.getCpId();
          session.setAttribute("cpId", cpId);
        }
      }
    }
    
    if (cpId == null) {
      throw new CgReportNotFoundException("游戏商不存在: " + cpId);
    }
    
    return cpId;
  }
}
