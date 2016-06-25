package jeecg.ext.online.db;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;
import javax.sql.DataSource;

import jeecg.cgform.common.CommUtils;
import jeecg.cgform.exception.BusinessException;
import jeecg.cgform.util.QueryParamUtil;
import jeecg.ext.online.db.entity.ExCgFormDataSource;
import jeecg.ext.online.db.entity.ExCgFormFieldEntity;
import jeecg.ext.online.db.entity.ExCgFormHeadEntity;
import jeecg.ext.tools.ExTools;
import jeecg.ext.tools.OnlineHeadFields;
import jeecg.system.pojo.base.TSUser;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.dao.jdbc.JdbcDao;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service("exDatabaseService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ExDatabaseService {
	private static final Logger logger = Logger.getLogger(ExDatabaseService.class);

	@Resource(name = "commonService")
	private CommonService commonService;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private ExDataSource exDataSource;

	@Autowired
	private OnlineHeadFields onlineHeadFields;
	
	public int insertTable(String tableName, Map data) {
		ExCgFormHeadEntity head = getCgFormHeadByTableName(tableName);
		ExCgFormFieldEntity keyfield = head.getKeyField();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		TSUser currentUser = ResourceUtil.getSessionUserName();
		for (ExCgFormFieldEntity e : head.getCgformFields()) {
			if (valueNotExists(data, e.getFieldName())) {
				if ("create_time".equalsIgnoreCase(e.getFieldName()) || "create_date".equalsIgnoreCase(e.getFieldName())) {
					data.put(e.getFieldName(), sdf.format(new Date()));
				} else if ("create_by".equalsIgnoreCase(e.getFieldName())) {
					data.put(e.getFieldName(), currentUser.getUserName());
				} else if ("create_name".equalsIgnoreCase(e.getFieldName())) {
					data.put(e.getFieldName(), currentUser.getRealName());
				}
			}
		}

		String keyid = keyfield.getFieldName();
		String defvalue = keyfield.getFieldValue();
		if ("uuid".equalsIgnoreCase(defvalue)) {
			data.put(keyid, UUID.randomUUID().toString().replaceAll("-", ""));
		}

		Map<String,ExCgFormFieldEntity> fields=new HashMap<String,ExCgFormFieldEntity>();
		for(ExCgFormFieldEntity f:head.getCgformFields()){
			fields.put(f.getFieldName().toLowerCase(), f);
		}
		
		StringBuffer insertKey = new StringBuffer();
		StringBuffer insertValue = new StringBuffer();
		for (Iterator iterator = data.entrySet().iterator(); iterator.hasNext();) {
			java.util.Map.Entry entry = (java.util.Map.Entry) iterator.next();
			String key = "`" + (String) entry.getKey() + "`";
			String value = (String) entry.getValue();

			if (value != null && value.length() > 0) {
				if (insertKey.length() > 0) {
					insertKey.append(", ");
					insertValue.append(", ");
				}
				insertKey.append(key);
				
				ExCgFormFieldEntity f=fields.get(entry.getKey().toString().toLowerCase());
				if("md5".equalsIgnoreCase(f.getType())){
					if(value.trim().length()!=32){
						value=DigestUtils.md5Hex(value.trim());
					}
				}
				
				insertValue.append(ExTools.escapeSqlValue(value));
			}

		}
		String sql = (new StringBuilder("INSERT INTO ")).append(tableName).append(" (").append(insertKey).append(") VALUES (").append(insertValue).append(")")
				.toString();
		int num = getJdbcTemplate(tableName).update(sql, new Object[0]);
		extMethod(tableName, data);
	 	
		if(tableName.equalsIgnoreCase("cgform_head") || tableName.equalsIgnoreCase("jeecg.cgform_head")){
			String targetTable=(String)data.get("table_name");			
			ExCgFormHeadEntity head_head=getCgFormHeadByTableName(targetTable);
			onlineHeadFields.addTableFields(head_head);
			
			this.clearCache();
		}else if(tableName.equalsIgnoreCase("cgform_field") || tableName.equalsIgnoreCase("jeecg.cgform_field")){
			this.clearCache();
		}
		
		return num;
	}

	public int updateTable(String tableName, String id, Map data) {
		ExCgFormHeadEntity head = getCgFormHeadByTableName(tableName);
		String keyid = head.getKeyField().getFieldName();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		TSUser currentUser = ResourceUtil.getSessionUserName();
		for (ExCgFormFieldEntity e : head.getCgformFields()) {
			if (valueNotExists(data, e.getFieldName())) {
				if ("update_time".equalsIgnoreCase(e.getFieldName()) || "update_date".equalsIgnoreCase(e.getFieldName())) {
					data.put(e.getFieldName(), sdf.format(new Date()));
				} else if ("update_by".equalsIgnoreCase(e.getFieldName())) {
					data.put(e.getFieldName(), currentUser.getUserName());
				} else if ("update_name".equalsIgnoreCase(e.getFieldName())) {
					data.put(e.getFieldName(), currentUser.getRealName());
				}
			}
		}
		
		Map<String,ExCgFormFieldEntity> fields=new HashMap<String,ExCgFormFieldEntity>();
		for(ExCgFormFieldEntity f:head.getCgformFields()){
			fields.put(f.getFieldName().toLowerCase(), f);
		}

		String comma = "";
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("update ").append(tableName).append(" set ");
		for (Iterator iterator = data.entrySet().iterator(); iterator.hasNext();) {
			java.util.Map.Entry entry = (java.util.Map.Entry) iterator.next();
			String key = "`" + (String) entry.getKey() + "`";
			String value = (String) entry.getValue();
			if (value == null || value.length() == 0) {
				sqlBuffer.append(comma).append(key).append("=null");
			} else {
				
				ExCgFormFieldEntity f=fields.get(entry.getKey().toString().toLowerCase());
				if("md5".equalsIgnoreCase(f.getType())){
					if(value.trim().length()!=32){
						value=DigestUtils.md5Hex(value.trim());
					}
				}
				
				sqlBuffer.append(comma).append(key).append("=").append(ExTools.escapeSqlValue(value));
			}

			comma = ", ";
		}
		sqlBuffer.append(" where ").append(keyid).append("='").append(id).append("'");
		int num = getJdbcTemplate(tableName).update(sqlBuffer.toString(), new Object[0]);
		extMethod(tableName, data);
		
		if(tableName.equalsIgnoreCase("cgform_head") || tableName.equalsIgnoreCase("jeecg.cgform_head")){
			String targetTable=(String)data.get("table_name");			
			ExCgFormHeadEntity head_head=getCgFormHeadByTableName(targetTable);
			onlineHeadFields.updateTableFields(head_head);		
			
			this.clearCache();
		}else if(tableName.equalsIgnoreCase("cgform_field") || tableName.equalsIgnoreCase("jeecg.cgform_field")){
			this.clearCache();
		}
		
		return num;
	}

	public boolean deleteTable(String tableName, String id) {
		ExCgFormHeadEntity head = getCgFormHeadByTableName(tableName);
		
		if("Y".equalsIgnoreCase(head.getIsSystable())){
			return false;
		}else{
			String keyid = head.getKeyField().getFieldName();
			try {
				StringBuilder deleteSql = new StringBuilder();
				deleteSql.append((new StringBuilder("DELETE FROM ")).append(tableName).append(" WHERE " + keyid + " = ?").toString());
				if (!QueryParamUtil.sql_inj(id)) {
					getJdbcTemplate(tableName).update(deleteSql.toString(), new Object[] { id });
				}
				this.clearCache();
				
				return true;
			} catch (Exception e) {
				logger.error("" + e, e);
				return false;
			}
		}		
	}

	private boolean valueNotExists(Map data, String key) {
		String v = (String) data.get(key);
		return v != null && v.length() > 0;
	}

	public Map findOneForJdbc(String tableName, String id) {
		ExCgFormHeadEntity head = getCgFormHeadByTableName(tableName);
		String keyid = head.getKeyField().getFieldName();

		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from ").append(tableName);
		sqlBuffer.append(" where ").append(keyid).append("='").append(id).append("'");

		Map map = getJdbcTemplate(tableName).queryForMap(sqlBuffer.toString(), new Object[0]);

		return map;
	}

	private void extMethod(String tableName, Map data) {
		ExCgFormHeadEntity cgFormHeadEntity = getCgFormHeadByTableName(tableName);
		if (cgFormHeadEntity != null) {
			String sqlPlugin = cgFormHeadEntity.getSqlPlugIn();
			if (StringUtils.isNotEmpty(sqlPlugin)) {
				String sqls[] = sqlPlugin.split(";");
				String as[] = sqls;
				int i = 0;
				for (int j = as.length; i < j; i++) {
					String sql = as[i];
					if (sql.toLowerCase().indexOf("insert") != -1 || sql.toLowerCase().indexOf("update") != -1) {
						logger.info((new StringBuilder("sql plugin -------->")).append(sql).toString());
						sql = formateSQl(sql, data);
						logger.info((new StringBuilder("sql plugin -------->")).append(sql).toString());
						int num = getJdbcTemplate(tableName).update(sql, new Object[0]);
						if (num > 0)
							logger.info((new StringBuilder("sql plugin --execute success------>")).append(sql).toString());

						else
							logger.info((new StringBuilder("sql plugin --execute fail------>")).append(sql).toString());
					}
				}
			}
		}
	}

	private Map<String, ExCgFormHeadEntity> headTables = new ConcurrentHashMap<String, ExCgFormHeadEntity>();

	public void clearCache(){
		headTables.clear();
	}
	
	public ExCgFormHeadEntity getCgFormHeadByTableName(String tableName) {
		ExCgFormHeadEntity head = headTables.get(tableName.toLowerCase());
		if (head == null) {
			StringBuilder hql = new StringBuilder();
			hql.append("from ExCgFormHeadEntity f");
			hql.append(" where f.tableName=? ");

			List list = commonService.findHql(hql.toString(), new Object[] { tableName });
			if (list != null && list.size() > 0) {
				head = (ExCgFormHeadEntity) list.get(0);

				headTables.put(tableName.toLowerCase(), head);
			}
		}
		return head;
	}
	
	public JdbcTemplate getJdbcTemplate(ExCgFormDataSource ds){
		if (ds != null) {
			if ("jeecg".equalsIgnoreCase(ds.getDatasource())) {
				return jdbcTemplate;
			}

			DataSource datasource = exDataSource.getDataSource(ds);
			return new JdbcTemplate(datasource);
		} else {
			return jdbcTemplate;
		}
	}

	public JdbcTemplate getJdbcTemplate(String tableName) {
		if (StringUtils.isEmpty(tableName)) {
			return jdbcTemplate;
		}

		ExCgFormHeadEntity head = getCgFormHeadByTableName(tableName);
		ExCgFormDataSource ds = head.getDatasource();
		if (ds != null) {
			if ("jeecg".equalsIgnoreCase(ds.getDatasource())) {
				return jdbcTemplate;
			}

			DataSource datasource = exDataSource.getDataSource(ds);
			return new JdbcTemplate(datasource);
		} else {
			return jdbcTemplate;
		}
	}

	public DataSource getDatasource(ExCgFormHeadEntity head) {
		ExCgFormDataSource ds = head.getDatasource();
		if (ds != null) {
			if ("jeecg".equalsIgnoreCase(ds.getDatasource())) {
				return jdbcTemplate.getDataSource();
			}

			DataSource datasource = exDataSource.getDataSource(ds);
			return datasource;
		} else {
			return jdbcTemplate.getDataSource();
		}
	}

	public List getCgFormFieldByTableName(String tableName) {

		StringBuilder sql = new StringBuilder("");
		sql.append("select f.* from cgform_field f,cgform_head h");
		sql.append(" where f.table_id = h.id and f.is_show='Y'");
		sql.append(" and h.table_name=? and f.is_key='N' order by f.order_num");
		List list = commonService.findForJdbc(sql.toString(), new Object[] { tableName });
		return list;
	}

	/**
	 * 按属性查找对象列表.
	 */
	public <T> List<T> findByProperty(Class<T> entityClass, String propertyName, Object value) {

		return commonService.findByProperty(entityClass, propertyName, value);
	}

	public Map getCgFormFieldByFormId(String formid) {

		StringBuilder hql = new StringBuilder("");
		hql.append("from ExCgFormFieldEntity f");
		hql.append(" where f.table.id=? ");
		List list = commonService.findHql(hql.toString(), new Object[] { formid });
		Map map = new HashMap();
		if (list != null && list.size() > 0) {
			ExCgFormFieldEntity po;
			for (Iterator iterator = list.iterator(); iterator.hasNext(); map.put(po.getFieldName(), po))
				po = (ExCgFormFieldEntity) iterator.next();
		}
		return map;
	}

	public Map getAllCgFormFieldByTableName(String tableName) {

		StringBuilder hql = new StringBuilder("");
		hql.append("from ExCgFormFieldEntity f");
		hql.append(" where f.table.tableName=? ");
		List list = commonService.findHql(hql.toString(), new Object[] { tableName });
		Map map = new HashMap();
		if (list != null && list.size() > 0) {
			ExCgFormFieldEntity po;
			for (Iterator iterator = list.iterator(); iterator.hasNext(); map.put(po.getFieldName(), po))
				po = (ExCgFormFieldEntity) iterator.next();
		}
		return map;
	}

	public List getSubTableData(String mainTableName, String subTableName, String mainTableId) {

		StringBuilder sql1 = new StringBuilder("");
		sql1.append("select f.* from cgform_field f ,cgform_head h");
		sql1.append(" where f.table_id = h.id ");
		sql1.append(" and h.table_name=? ");
		sql1.append(" and f.main_table=? ");
		List list = commonService.findForJdbc(sql1.toString(), new Object[] { subTableName, mainTableName });
		StringBuilder sql2 = new StringBuilder("");
		sql2.append("select sub.* from ").append(subTableName).append(" sub ");
		sql2.append(", ").append(mainTableName).append(" main ");
		sql2.append("where 1=1 ");
		if (list != null && list.size() > 0) {
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map map = (Map) iterator.next();
				if (map.get("main_field") != null)
					sql2.append(" and sub.").append((String) map.get("field_name")).append("=").append("main.").append((String) map.get("main_field"));
			}
		}
		sql2.append(" and main.id= ? ");
		List subTableDataList = commonService.findForJdbc(sql2.toString(), new Object[] { mainTableId });
		return subTableDataList;
	}

	private String formateSQl(String sql, Map params) {

		if (params == null)
			return sql;

		if (sql.toLowerCase().indexOf("insert") != -1)
			sql = sql.replace("#{UUID}", UUID.randomUUID().toString());

		for (Iterator iterator = params.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			sql = sql.replace((new StringBuilder("#{")).append(key).append("}").toString(),
					(new StringBuilder("'")).append(((String) params.get(key)).toString()).append("'").toString());
		}
		return sql;
	}

	public Map insertTableMore(Map mapMore, String mainTableName) throws BusinessException {
		Map mainMap = (Map) ((List) mapMore.get(mainTableName)).get(0);

		String filterName[] = { "tableName", "saveOrUpdateMore" };
		mainMap = CommUtils.attributeMapFilter(mainMap, filterName);
		mainMap.put("id", UUID.randomUUID().toString());
		insertTable(mainTableName, mainMap);

		String filterMainTable[] = { mainTableName };
		mapMore = CommUtils.attributeMapFilter(mapMore, filterMainTable);
		for (Iterator it = mapMore.entrySet().iterator(); it.hasNext();) {
			java.util.Map.Entry entry = (java.util.Map.Entry) (java.util.Map.Entry) it.next();
			String ok = (String) entry.getKey();
			List ov = (List) (List) entry.getValue();
			Map fieldMap;
			for (Iterator iterator = ov.iterator(); iterator.hasNext(); insertTable(ok, fieldMap)) {
				fieldMap = (Map) iterator.next();
				List fkFieldList = getFKField(mainTableName, ok);
				fieldMap.put("id", UUID.randomUUID().toString());
				fieldMap = CommUtils.convertFKMap(fieldMap, mainMap, fkFieldList);
			}
		}

		return mainMap;
	}

	public boolean updateTableMore(Map mapMore, String mainTableName) throws BusinessException {

		Map mainMap = (Map) ((List) mapMore.get(mainTableName)).get(0);
		String mainTableId = (String) mainMap.get("id");

		String filterName[] = { "tableName", "saveOrUpdateMore", "id" };
		mainMap = CommUtils.attributeMapFilter(mainMap, filterName);
		updateTable(mainTableName, mainTableId, mainMap);
		mainMap.put("id", mainTableId);

		String filterMainTable[] = { mainTableName };
		mapMore = CommUtils.attributeMapFilter(mapMore, filterMainTable);
		for (Iterator it = mapMore.entrySet().iterator(); it.hasNext();) {
			java.util.Map.Entry entry = (java.util.Map.Entry) (java.util.Map.Entry) it.next();
			String ok = (String) entry.getKey();
			List ov = (List) (List) entry.getValue();

			List fkFieldList = getFKField(mainTableName, ok);

			Map subTableDateMap = getSubTableData(fkFieldList, mainTableName, ok, mainTableId);
			for (Iterator iterator = ov.iterator(); iterator.hasNext();) {
				Map fieldMap = (Map) iterator.next();
				String subId = fieldMap.get("id") != null ? (String) fieldMap.get("id") : "";
				if (StringUtils.isEmpty(subId)) {
					fieldMap = CommUtils.convertFKMap(fieldMap, mainMap, fkFieldList);
					fieldMap.put("id", UUID.randomUUID().toString());
					insertTable(ok, fieldMap);
				} else {
					fieldMap = CommUtils.convertFKMap(fieldMap, mainMap, fkFieldList);
					String subFilterName[] = { "id" };
					fieldMap = CommUtils.attributeMapFilter(fieldMap, subFilterName);
					updateTable(ok, subId, fieldMap);
					if (subTableDateMap.containsKey(subId))
						subTableDateMap.remove(subId);
				}
			}
			if (subTableDateMap.size() > 0) {
				String subId;
				for (Iterator itSub = subTableDateMap.entrySet().iterator(); itSub.hasNext(); deleteSubTableDataById(subId, ok)) {
					java.util.Map.Entry entrySub = (java.util.Map.Entry) (java.util.Map.Entry) itSub.next();
					subId = (String) entrySub.getKey();
				}
			}
		}
		return true;
	}

	private List getFKField(String mainTableName, String subTableName) {

		StringBuilder sql1 = new StringBuilder("");
		sql1.append("select f.* from cgform_field f ,cgform_head h");
		sql1.append(" where f.table_id = h.id ");
		sql1.append(" and h.table_name=? ");
		sql1.append(" and f.main_table=? ");
		List list = commonService.findForJdbc(sql1.toString(), new Object[] { subTableName, mainTableName });
		return list;
	}

	private Map getSubTableData(List fkFieldList, String mainTableName, String subTableName, String mainTableId) {

		StringBuilder sql2 = new StringBuilder("");
		sql2.append("select sub.* from ").append(subTableName).append(" sub ");
		sql2.append(", ").append(mainTableName).append(" main ");
		sql2.append("where 1=1 ");
		if (fkFieldList != null && fkFieldList.size() > 0) {
			for (Iterator iterator = fkFieldList.iterator(); iterator.hasNext();) {
				Map map = (Map) iterator.next();
				if (map.get("main_field") != null)
					sql2.append(" and sub.").append((String) map.get("field_name")).append("=").append("main.").append((String) map.get("main_field"));
			}
		}
		sql2.append(" and main.id= ? ");
		List subTableDataList = commonService.findForJdbc(sql2.toString(), new Object[] { mainTableId });
		Map dataMap = new HashMap();
		if (subTableDataList != null) {
			Map map;
			for (Iterator iterator1 = subTableDataList.iterator(); iterator1.hasNext(); dataMap.put((String) map.get("id"), map))
				map = (Map) iterator1.next();
		}
		return dataMap;
	}

	private void deleteSubTableDataById(String subId, String subTableName) {

		StringBuilder sql = new StringBuilder("");
		sql.append(" delete from ").append(subTableName).append(" where id = ? ");

		commonService.executeSql(sql.toString(), new Object[] { subId });
	}

	public Map getCgformFtlByTableName(String tableName) {

		StringBuilder sql = new StringBuilder("");
		sql.append("select ftl.* from cgform_ftl ftl,cgform_head head");
		sql.append(" where ftl.cgform_id=head.id");
		sql.append(" and ftl.ftl_status='1'");
		sql.append(" and head.table_name=? ");
		List list = commonService.findForJdbc(sql.toString(), new Object[] { tableName });
		if (list != null && list.size() > 0)
			return (Map) list.get(0);

		else
			return null;
	}

	public int getNextVarsion(String cgformId) {

		StringBuilder sql = new StringBuilder("");
		sql.append("select (max(ftl_version)+1) as varsion from cgform_ftl");
		sql.append(" where cgform_id = ? ");
		Map map = commonService.findOneForJdbc(sql.toString(), new Object[] { cgformId });
		if (map != null) {
			int varsion = map.get("varsion") != null ? ((Double) map.get("varsion")).intValue() : 0;
			return varsion;
		} else {
			return 0;
		}
	}

	public boolean hasActive(String cgformId) {
		StringBuilder sql = new StringBuilder("");
		sql.append("select * from cgform_ftl");
		sql.append(" where ftl_status = '1' ");
		sql.append(" and cgform_id = ? ");
		Map map = commonService.findOneForJdbc(sql.toString(), new Object[] { cgformId });
		return map != null;
	}

	public Map queryConfigs(String tableName) {

		Map configs = new HashMap();

		ExCgFormHeadEntity head = getCgFormHeadByTableName(tableName);
		TreeSet fields = new TreeSet(new Comparator<ExCgFormFieldEntity>() {
			public int compare(ExCgFormFieldEntity o1, ExCgFormFieldEntity o2) {
				int i1 = o1.getOrderNum() == null ? 0 : o1.getOrderNum();
				int i2 = o2.getOrderNum() == null ? 0 : o2.getOrderNum();
				if (i1 != i2) {
					return i1 - i2;
				} else {
					return o1.getFieldName().hashCode() > o2.getFieldName().hashCode() ? 1 : -1;
				}

			}
		});
		fields.addAll(head.getCgformFields());

		configs.put("page_size", head.getPagesize() < 1 ? 20 : head.getPagesize());
		configs.put("config_id", head.getTableName());
		configs.put("config_name", head.getContent());
		configs.put("tableName", head.getTableName());
		configs.put("config_ischeckbox", head.getIsCheckbox());
		configs.put("config_ispagination", head.getIsPagination());
		configs.put("config_istree", head.getIsTree());
		configs.put("config_querymode", head.getQuerymode());
		configs.put("fields", fields);
		configs.put("jformVersion", head.getJformVersion());
		configs.put("style_width", head.getStyleWidth()<100?600:head.getStyleWidth());
		configs.put("style_height", head.getStyleHeight()<50?400:head.getStyleHeight());		 
		return configs;
	}

	public List querySingle(String tableName, String field, Map params, String sort, String order, int page, int rows) {

		StringBuilder sqlB = new StringBuilder();

		dealQuerySql(tableName, field, params, sqlB);

		ExCgFormHeadEntity head = getCgFormHeadByTableName(tableName);

		if (!StringUtil.isEmpty(sort) && !StringUtil.isEmpty(order)) {
			sqlB.append((new StringBuilder(" ORDER BY ")).append(sort).append(" ").append(order).toString());
		} else {
			if (StringUtils.isNotEmpty(head.getSqlOrder())) {
				sqlB.append("  ORDER BY ").append(head.getSqlOrder());
			}
		}

		String sql = JdbcDao.jeecgCreatePageSql(sqlB.toString(), page, rows);
		return this.getJdbcTemplate(tableName).queryForList(sql, new Object[0]);

	}

	public Long getQuerySingleSize(String tableName, String field, Map params) {
		StringBuilder sqlB = new StringBuilder();
		dealQuerySql(tableName, "count(*) as size,", params, sqlB);

		return this.getJdbcTemplate(tableName).queryForLong(sqlB.toString(), new Object[0]);
	}

	private void dealQuerySql(String tableName, String field, Map params, StringBuilder sqlB) {
		ExCgFormHeadEntity head = getCgFormHeadByTableName(tableName);

		sqlB.append(" SELECT ");
		String as[] = field.split(",");
		int i = 0;
		for (int j = as.length; i < j; i++) {
			String f = as[i];
			sqlB.append(f);

			sqlB.append(",");

		}
		sqlB.deleteCharAt(sqlB.length() - 1);
		sqlB.append((new StringBuilder(" FROM ")).append(tableName).toString());
		if (params.size() >= 1) {
			sqlB.append(" WHERE 1=1 ");
			for (Iterator it = params.keySet().iterator(); it.hasNext();) {
				String key = String.valueOf(it.next());
				String value = String.valueOf(params.get(key));
				if (!StringUtil.isEmpty(value) && !"null".equals(value)) {
					sqlB.append(" AND ");
					sqlB.append((new StringBuilder(" ")).append(key).append(value).toString());
				}
			}
			if (StringUtils.isNotEmpty(head.getSqlWhere())) {
				sqlB.append(" AND ").append(head.getSqlWhere());
			}
		} else {
			if (StringUtils.isNotEmpty(head.getSqlWhere())) {
				sqlB.append(" WHERE ").append(head.getSqlWhere());
			}
		}
	}

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

	public OnlineHeadFields getOnlineHeadFields() {
		return onlineHeadFields;
	}

	public void setOnlineHeadFields(OnlineHeadFields onlineHeadFields) {
		this.onlineHeadFields = onlineHeadFields;
	}

}
