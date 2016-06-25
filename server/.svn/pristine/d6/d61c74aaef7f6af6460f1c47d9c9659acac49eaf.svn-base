 package jeecg.ext.report.service.impl;
 
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.sql.DataSource;

import jeecg.cgreport.service.core.CgReportServiceI;
import jeecg.ext.online.db.ExDataSource;
import jeecg.ext.online.db.entity.ExCgFormDataSource;
import jeecg.ext.report.service.ExCgReportServiceI;

import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.common.dao.jdbc.JdbcDao;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.JeecgSqlUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
 @Service("exCgReportService")
 @Transactional
 public class ExCgReportServiceImpl extends CommonServiceImpl implements ExCgReportServiceI
 {
   @Autowired
   private JdbcDao jdbcDao;
   
   public ExCgReportServiceImpl() {}
   
   public Map<String, Object> queryCgReportConfig(String reportId)
   {
/*  32 */     Map<String, Object> cgReportM = new HashMap(0);
/*  33 */     Map<String, Object> mainM = queryCgReportMainConfig(reportId);
/*  34 */     List<Map<String, Object>> itemsM = queryCgReportItems(reportId);
/*  35 */     cgReportM.put("main", mainM);
/*  36 */     cgReportM.put("items", itemsM);
/*  37 */     return cgReportM;
   }
   
   public Map<String, Object> queryCgReportMainConfig(String reportId) {
/*  41 */     String sql = JeecgSqlUtil.getMethodSql(JeecgSqlUtil.getMethodUrl());
/*  42 */     Map<String, Object> parameters = new LinkedHashMap();
/*  43 */     parameters.put("id", reportId);
/*  44 */     Map mainM = this.jdbcDao.findForMap(sql, parameters);
/*  45 */     return mainM;
   }
   
   public List<Map<String, Object>> queryCgReportItems(String reportId) {
/*  49 */     String sql = JeecgSqlUtil.getMethodSql(JeecgSqlUtil.getMethodUrl());
/*  50 */     Map<String, Object> parameters = new LinkedHashMap();
/*  51 */     parameters.put("configId", reportId);
/*  52 */     List<Map<String, Object>> items = this.jdbcDao.findForListMap(sql, parameters);
/*  53 */     return items;
   }
   
 
   public List<Map<String, Object>> queryByCgReportSql(String sql, Map params, int page, int rows)
   {
/*  59 */     String querySql = getFullSql(sql, params);
/*  60 */     List<Map<String, Object>> result = null;
/*  61 */     if ((page == -1) && (rows == -1)) {
/*  62 */       result = this.jdbcDao.findForJdbc(querySql, new Object[0]);
     } else {
/*  64 */       result = this.jdbcDao.findForJdbc(querySql, page, rows);
     }
/*  66 */     return result;
   }
   /**
    * 根据数据源获取数据
    * @param configId
    * @param sql
    * @param params
    * @param page
    * @param rows
    * @return
    */
   public List<Map<String, Object>> queryByCgReportSql(String configId, String sql, Map params, int page, int rows){
	   String querySql = getFullSql(sql, params);
	   List<Map<String, Object>> result = null;
	   String exeSql="";
	   if ((page == -1) && (rows == -1)){
		   exeSql=querySql;
	   }else{
		   exeSql=JdbcDao.jeecgCreatePageSql(querySql.toString(), page, rows);
	   }
	   
	   Map<String, Object> mainMap=queryCgReportMainConfig(configId);
	   if(mainMap.containsKey(DATASOURCE_KEY)){
		   return this.getJdbcTemplate(mainMap.get(DATASOURCE_KEY).toString()).queryForList(exeSql, new Object[0]);
	   }else{
		   result = this.jdbcDao.findForJdbc(exeSql, new Object[0]);
	   }
	   return result;
   }
   
 public JdbcTemplate getJdbcTemplate(String dataSource){
	 if(StringUtils.isEmpty(dataSource)){
		 return jdbcTemplate;
	 }
	 ExCgFormDataSource ds=this.commonService.getEntity(ExCgFormDataSource.class, dataSource);
	 if(ds!=null){
		 if("jeecg".equals(ds.getDatasource())){
			 return jdbcTemplate;
		 }
		 DataSource datasource = exDataSource.getDataSource(ds);
		return new JdbcTemplate(datasource);
	 }else{
		 return jdbcTemplate;
	 }
 }
 
 
 
 
   private String getFullSql(String sql, Map params)
   {
/*  76 */     StringBuilder sqlB = new StringBuilder();
/*  77 */     sqlB.append("SELECT t.* FROM ( ");
/*  78 */     sqlB.append(sql + " ");
/*  79 */     sqlB.append(") t ");
/*  80 */     if (params.size() >= 1) {
/*  81 */       sqlB.append("WHERE 1=1  ");
/*  82 */       Iterator it = params.keySet().iterator();
/*  83 */       while (it.hasNext()) {
/*  84 */         String key = String.valueOf(it.next());
/*  85 */         String value = String.valueOf(params.get(key));
/*  86 */         if ((!StringUtil.isEmpty(value)) && (!"null".equals(value))) {
/*  87 */           sqlB.append(" AND ");
/*  88 */           sqlB.append(" " + key + value);
         }
       }
     }
/*  92 */     return sqlB.toString();
   }
   
   public long countQueryByCgReportSql(String sql, Map params)
   {
/*  97 */     String querySql = getFullSql(sql, params);
/*  98 */     querySql = "SELECT COUNT(*) FROM (" + querySql + ") t2";
/*  99 */     long result = this.jdbcDao.findForLong(querySql, new HashMap(0));
/* 100 */     return result;
   }
   
   public long countQueryByCgReportSql(String configId,String sql,Map params){
	   Map<String, Object> mainMap=queryCgReportMainConfig(configId);
	   String querySql = getFullSql(sql, params);
	   querySql = "SELECT COUNT(*) FROM (" + querySql + ") t2";
	   
	   if(mainMap.containsKey(DATASOURCE_KEY)){
		   return this.getJdbcTemplate(mainMap.get(DATASOURCE_KEY).toString()).queryForLong(querySql,new Object[0]);
	   }else{
		   return this.jdbcDao.findForLong(querySql, new HashMap(0));
	   }
	  
   }
   
   public List<String> getSqlFields(String sql)
   {
/* 105 */     if (oConvertUtils.isEmpty(sql)) {
/* 106 */       return null;
     }
/* 108 */     List<Map<String, Object>> result = this.jdbcDao.findForJdbc(sql, 1, 1);
/* 109 */     if (result.size() < 1) {
/* 110 */       throw new BusinessException("???sql???????????");
     }
/* 112 */     Set fieldsSet = ((Map)result.get(0)).keySet();
/* 113 */     List<String> fileds = new ArrayList(fieldsSet);
/* 114 */     return fileds;
   }
   
   
   @Resource(name = "commonService")
	private CommonService commonService;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ExDataSource exDataSource;
	
	
	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	
	public ExDataSource getExDataSource() {
		return exDataSource;
	}

	public void setExDataSource(ExDataSource exDataSource) {
		this.exDataSource = exDataSource;
	}
	
	
	
 }

