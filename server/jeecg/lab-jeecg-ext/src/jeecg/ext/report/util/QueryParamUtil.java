package jeecg.ext.report.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jeecg.ext.online.db.entity.ExCgFormFieldEntity;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jeecgframework.core.util.StringUtil;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class QueryParamUtil extends jeecg.cgform.util.QueryParamUtil {
	static Log logger = LogFactory.getLog(QueryParamUtil.class);
	
	public static void loadQueryParams(HttpServletRequest request, Map item, Map params) {
		String filedName = (String) item.get("field_name");
		String queryMode = (String) item.get("search_mode");
		String filedType = (String) item.get("field_type");

		if ("single".equals(queryMode)) {
			String value = request.getParameter(filedName);
			if (!StringUtils.isEmpty(value)) {
				sql_inj_throw(value);
				value = applyType(filedType, value);

				if (value.contains("*")) {
					value = value.replaceAll("\\*", "%");
					params.put(filedName, (new StringBuilder(" LIKE ")).append(value).toString());
				} else {
					params.put(filedName, (new StringBuilder(" = ")).append(value).toString());
				}
			}
		} else if ("group".equals(queryMode)) {
			String begin = request.getParameter((new StringBuilder(String.valueOf(filedName))).append("_begin").toString());
			String end = request.getParameter((new StringBuilder(String.valueOf(filedName))).append("_end").toString());

			sql_inj_throw(begin);
			sql_inj_throw(end);

			begin = applyType(filedType, begin);
			end = applyType(filedType, end);

			if (!StringUtil.isEmpty(begin)) {
				String re = (new StringBuilder(" >= ")).append(begin).toString();
				if (!StringUtil.isEmpty(end))
					re = (new StringBuilder(String.valueOf(re))).append(" AND ").append(filedName).append(" <= ").append(end).toString();

				params.put(filedName, re);
			} else if (!StringUtil.isEmpty(end)) {
				String re = (new StringBuilder(" <= ")).append(end).toString();
				params.put(filedName, re);
			}
		}
	}

	private final static long ONE_DAY=24*3600*1000L;
	
	public static void loadQueryParams(HttpServletRequest request, ExCgFormFieldEntity b, Map params) {
		if ("single".equals(b.getQueryMode())) {
			String searchfield=request.getParameter("searchfield");
			if(StringUtils.isEmpty(searchfield) || b.getFieldName().equalsIgnoreCase(searchfield)){
				String value = request.getParameter(b.getFieldName());
				sql_inj_throw(value);
				value = applyType(b.getType(), value);
				String queryLike = b.getQueryLike();
				if (!StringUtil.isEmpty(value)){
					if (value.contains("*")) {
						value = value.replaceAll("\\*", "%");
						params.put(b.getFieldName(), (new StringBuilder(" LIKE ")).append(value).toString());
					} else {
						if("date".equalsIgnoreCase(b.getType())){
							try{
								SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
								Date df=sdf.parse(value.substring(1,value.length()-1));
								Date dt=new Date(df.getTime()+ONE_DAY);
										
								StringBuilder sb=new StringBuilder();
								sb.append(" >= ").append(value);
								sb.append(" AND ").append(b.getFieldName());
								sb.append(" <'").append(sdf.format(dt)).append("'");
								
								params.put(b.getFieldName(), sb.toString());								 
							}catch(Exception e){
								logger.error(e);
							}
						}else{
							if(value.startsWith("'")){//字符串like查询								
								if("right".equalsIgnoreCase(b.getQueryLike())){
									params.put(b.getFieldName(), (new StringBuilder(" LIKE ")).append(value.substring(0,value.length()-1)).append("%'").toString());
								}if("all".equalsIgnoreCase(b.getQueryLike())){
									params.put(b.getFieldName(), (new StringBuilder(" LIKE '%")).append(value.substring(1,value.length()-2)).append("%'").toString());
								}if("left".equalsIgnoreCase(b.getQueryLike())){
									params.put(b.getFieldName(), (new StringBuilder(" LIKE '%")).append(value.substring(1,value.length())).toString());
								}else{
									params.put(b.getFieldName(), (new StringBuilder(" = ")).append(value).toString());
								}
							}else{
								params.put(b.getFieldName(), (new StringBuilder(" = ")).append(value).toString());
							}
						}
					}
				}
			}else if(StringUtils.isEmpty(searchfield) || searchfield.equalsIgnoreCase((new StringBuilder(String.valueOf(b.getFieldName()))).append("_end").toString())){
				String begin = request.getParameter((new StringBuilder(String.valueOf(b.getFieldName()))).append("_begin").toString());
				sql_inj_throw(begin);
				String fieldType = b.getType();
				if(fieldType.equalsIgnoreCase("DateTime")){
					fieldType = "Date";
				}
				begin = applyType(fieldType, begin);
				String end = request.getParameter((new StringBuilder(String.valueOf(b.getFieldName()))).append("_end").toString());
				sql_inj_throw(end);
				if(fieldType.equalsIgnoreCase("Date") && !StringUtil.isEmpty(end)){
					end = end + " 23:59:59";
				}
				end = applyType(fieldType, end);
				if (!StringUtil.isEmpty(begin)) {
					String re = (new StringBuilder(" >= ")).append(begin).toString();
					if (!StringUtil.isEmpty(end)){
						re = (new StringBuilder(String.valueOf(re))).append(" AND ").append(b.getFieldName()).append(" <= ").append(end).toString();
					}
					params.put(b.getFieldName(), re);
				} else if (!StringUtil.isEmpty(end)) {
					String re = (new StringBuilder(" <= ")).append(end).toString();
					params.put(b.getFieldName(), re);
				}
			}
		} else if ("group".equals(b.getQueryMode())) {
			String begin = request.getParameter((new StringBuilder(String.valueOf(b.getFieldName()))).append("_begin").toString());
			sql_inj_throw(begin);
			begin = applyType(b.getType(), begin);
			String end = request.getParameter((new StringBuilder(String.valueOf(b.getFieldName()))).append("_end").toString());
			sql_inj_throw(end);
			end = applyType(b.getType(), end);
			if (!StringUtil.isEmpty(begin)) {
				String re = (new StringBuilder(" >= ")).append(begin).toString();
				if (!StringUtil.isEmpty(end)){
					re = (new StringBuilder(String.valueOf(re))).append(" AND ").append(b.getFieldName()).append(" <= ").append(end).toString();
				}
				params.put(b.getFieldName(), re);
			} else if (!StringUtil.isEmpty(end)) {
				String re = (new StringBuilder(" <= ")).append(end).toString();
				params.put(b.getFieldName(), re);
			}
		}
	}
}
