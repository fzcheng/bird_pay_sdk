package jeecg.ext.report.service;

import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface ExCgReportServiceI  extends CommonService
{
	  public abstract Map<String, Object> queryCgReportConfig(String paramString);
	  
	  public abstract Map<String, Object> queryCgReportMainConfig(String paramString);
	  
	  public abstract List<Map<String, Object>> queryCgReportItems(String paramString);
	  
	  public abstract List<Map<String, Object>> queryByCgReportSql(String paramString, Map paramMap, int paramInt1, int paramInt2);
	  
	  public abstract long countQueryByCgReportSql(String paramString, Map paramMap);
	  
	  public abstract List<String> getSqlFields(String paramString);
	  
	  public abstract List<Map<String, Object>> queryByCgReportSql(String configId,String sql, Map params, int page, int rows);
	  
	  public abstract long countQueryByCgReportSql(String configId,String sql,Map params);
	  
	  public static final String DATASOURCE_KEY="datasource";
}
