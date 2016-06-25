package org.jeecgframework.core.extend.hqlsearch;

import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.extend.hqlsearch.util.StringUtil;


public class HqlGenerateUtil {
	
	private static final String SUFFIX_COMMA = ",";
	private static final String SUFFIX_KG = " ";
	/**模糊查询符号*/
	private static final String SUFFIX_ASTERISK = "*";
		private static final String SUFFIX_ASTERISK_VAGUE = "%";
		/**不等于查询符号*/
	private static final String SUFFIX_NOT_EQUAL = "!";
	private static final String SUFFIX_NOT_EQUAL_NULL = "!NULL";
	
	/**时间查询符号*/
	private static final String END = "end";
	private static final String BEGIN = "begin";
	private static final Logger logger = Logger.getLogger(HqlGenerateUtil.class);
	

	
	/**
  	 * 自动生成查询条件HQL
  	 * 模糊查询
  	 * 【只对Integer类型和String类型的字段自动生成查询条件】
  	 * @param hql
  	 * @param values
  	 * @param searchObj
  	 * @throws Exception
  	 */
  	public static void installHql(CriteriaQuery cq,Object searchObj){
  		PropertyDescriptor origDescriptors[] = PropertyUtils.getPropertyDescriptors(searchObj);
  		// 获得对象属性中的name 
  		String descriptorsNames = getDescriptorsNames(origDescriptors);
  		
        for (int i = 0; i < origDescriptors.length; i++) {
            String name = origDescriptors[i].getName();
            String type = origDescriptors[i].getPropertyType().toString();
            
            if ("class".equals(name)||"ids".equals(name)||"page".equals(name)
            		||"rows".equals(name)||"sort".equals(name)||"order".equals(name)) {
                continue; // No point in trying to set an object's class
            }
            try {
            if (PropertyUtils.isReadable(searchObj, name)) {
               if("class java.lang.String".equals(type)){
            	   Object value = PropertyUtils.getSimpleProperty(searchObj, name);
            	   String searchValue = null;
            	   if(value!=null){
            		    searchValue = value.toString().trim();
            	   }else{
            		   continue;
            	   }
            	   if(searchValue!=null&&!"".equals(searchValue)){
            		   //[1].In 多个条件查询{逗号隔开参数}
            		   if(searchValue.indexOf(SUFFIX_COMMA)>=0){
            			   //页面输入查询条件，情况（取消字段的默认条件）
            			   if(searchValue.indexOf(SUFFIX_KG)>=0){
            				   String val = searchValue.substring(searchValue.indexOf(SUFFIX_KG));
            				   cq.eq(name, val);
            			   }else{
            				   String[] vs = searchValue.split(SUFFIX_COMMA);
                			   cq.in(name, vs);
            			   }
            		   }
            		   //[2].模糊查询{带有* 星号的参数}
            		   else if(searchValue.indexOf(SUFFIX_ASTERISK)>=0){
            			   cq.like(name, searchValue.replace(SUFFIX_ASTERISK, SUFFIX_ASTERISK_VAGUE));
            		   }
            		   //[3].不匹配查询{等于！叹号}
            		   //(1).不为空字符串
            		   else if(searchValue.equals(SUFFIX_NOT_EQUAL)){
            			   cq.isNotNull(name);
            		   }
            		   //(2).不为NULL
            		   else if(searchValue.toUpperCase().equals(SUFFIX_NOT_EQUAL_NULL)){
            			   cq.isNotNull(name);
            		   }
            		   //(3).正常不匹配
            		   else if(searchValue.indexOf(SUFFIX_NOT_EQUAL)>=0){
            			   cq.notEq(name, searchValue.replace(SUFFIX_NOT_EQUAL, ""));
            		   }
            		   //[4].全匹配查询{没有特殊符号的参数}
            		   else{
            			   cq.eq(name, searchValue);
            		   }
            	   }
               }else if("class java.lang.Integer".equals(type)){
            	   Object value = PropertyUtils.getSimpleProperty(searchObj, name);
            	   if(value!=null&&!"".equals(value)){
            		   cq.eq(name, value);
            	   }
               } else if("class java.math.BigDecimal".equals(type)){
            	               	   Object value = PropertyUtils.getSimpleProperty(searchObj, name);
            	   if(value!=null&&!"".equals(value)){
            		   cq.eq(name, value);
            	   }
            	                  }else if("class java.lang.Short".equals(type)){
            	               	   Object value = PropertyUtils.getSimpleProperty(searchObj, name);
            	   if(value!=null&&!"".equals(value)){
            		   cq.eq(name, value);
            	   }
            	                  }else if("class java.lang.Long".equals(type)){
            	               	   Object value = PropertyUtils.getSimpleProperty(searchObj, name);
            	   if(value!=null&&!"".equals(value)){
            		   cq.eq(name, value);
            	   }
            	                  }else if ("class java.util.Date".equals(type)) {
					Date value = (Date) PropertyUtils.getSimpleProperty(searchObj, name);
					if (null != value) {
						// 判断开始时间
						if (name.contains(BEGIN)) {
							String realName = StringUtil.firstLowerCase(name.substring(5, name.length()));
							if (!BEGIN.equals(name.substring(0, 5)) || !descriptorsNames.contains(realName)) {
								logger.error("该查询属性 [" + name + "] 命名不规则");
							} else {
								cq.ge(realName, value);
							}
						}
						// 判断结束时间
						else if (name.contains(END)) {
							String realName = StringUtil.firstLowerCase(name.substring(3, name.length()));
							if (!END.equals(name.substring(0, 3)) || !descriptorsNames.contains(realName)) {
								logger.error("该查询属性 [" + name + "] 命名不规则");
							} else {
								cq.le(name, value);
							}
						}
						else {
							  cq.eq(name, value);
						}
					 }
                  }
               }
            } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        //添加选择条件
        cq.add();
  	}
  	
  	  	  	/*
  	 * 下面的方法是重载 org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(CriteriaQuery, Object)
  	 * 其目的是拓展区间查询功能
  	 * 注：
  	 * 	重载此方法后修改了模版 controllerTemplate.ftl#datagrid()
  	 * 	将第二行<br/>
  	 * 		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, ${entityName?uncap_first});
  	 * 	改为<br/>
  	 * 		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, ${entityName?uncap_first}, request.getParameterMap());
  	 * 	因模版里不方便注释，故在此处说明
  	 */
		  	/**
  	 * 自动生成查询条件HQL（扩展区间查询功能）
  	 * @param cq
  	 * @param searchObj
  	 * @param parameterMap 	request参数集合，封装了所有查询信息
  	 */
  	public static void installHql(CriteriaQuery cq,Object searchObj, Map<String, String[]> parameterMap){
  		PropertyDescriptor origDescriptors[] = PropertyUtils.getPropertyDescriptors(searchObj);
  		// 获得对象属性中的name 
  		String descriptorsNames = getDescriptorsNames(origDescriptors);
  		
  		for (int i = 0; i < origDescriptors.length; i++) {
  			String name = origDescriptors[i].getName();
  			String type = origDescriptors[i].getPropertyType().toString();
  			
  			if ("class".equals(name)||"ids".equals(name)||"page".equals(name)
  					||"rows".equals(name)||"sort".equals(name)||"order".equals(name)) {
  				continue; // No point in trying to set an object's class
  			}
  			try {
  				if (PropertyUtils.isReadable(searchObj, name)) {
  					
  					// 添加 判断是否有区间值
  					String beginValue = null;
  					String endValue = null;
  					if (parameterMap.containsKey(name+"_begin")) {
						beginValue = parameterMap.get(name+"_begin")[0].trim();
					}
  					if (parameterMap.containsKey(name+"_end")) {
  						endValue = parameterMap.get(name+"_end")[0].trim();
  					}
  					
  					// 根据类型分类处理
  					if("class java.lang.String".equals(type)){
  						Object value = PropertyUtils.getSimpleProperty(searchObj, name);
  						String searchValue = null;
  						if(value!=null){
  							searchValue = value.toString().trim();
  						}else{
  							continue;
  						}
  						if(searchValue!=null&&!"".equals(searchValue)){
  							//[1].In 多个条件查询{逗号隔开参数}
  							if(searchValue.indexOf(SUFFIX_COMMA)>=0){
  								//页面输入查询条件，情况（取消字段的默认条件）
  								if(searchValue.indexOf(SUFFIX_KG)>=0){
  									String val = searchValue.substring(searchValue.indexOf(SUFFIX_KG));
  									cq.eq(name, val);
  								}else{
  									String[] vs = searchValue.split(SUFFIX_COMMA);
  									cq.in(name, vs);
  								}
  							}
  							//[2].模糊查询{带有* 星号的参数}
  							else if(searchValue.indexOf(SUFFIX_ASTERISK)>=0){
  								cq.like(name, searchValue.replace(SUFFIX_ASTERISK, SUFFIX_ASTERISK_VAGUE));
  							}
  							//[3].不匹配查询{等于！叹号}
  							//(1).不为空字符串
  							else if(searchValue.equals(SUFFIX_NOT_EQUAL)){
  								cq.isNotNull(name);
  							}
  							//(2).不为NULL
  							else if(searchValue.toUpperCase().equals(SUFFIX_NOT_EQUAL_NULL)){
  								cq.isNotNull(name);
  							}
  							//(3).正常不匹配
  							else if(searchValue.indexOf(SUFFIX_NOT_EQUAL)>=0){
  								cq.notEq(name, searchValue.replace(SUFFIX_NOT_EQUAL, ""));
  							}
  	            		   //[4].全匹配查询{没有特殊符号的参数}
  	            		   else{
  	            			   cq.eq(name, searchValue);
  	            		   }
  						}
  					}else if("class java.lang.Integer".equals(type)){
  						if (beginValue != null && !"".equals(beginValue)) {
	  						cq.ge(name, Integer.parseInt(beginValue));
	  					}
	  					if (endValue != null && !"".equals(endValue)) {
	  						cq.le(name, Integer.parseInt(endValue));
	  					}
	  					Object value = PropertyUtils.getSimpleProperty(searchObj, name);
	  					if(value!=null&&!"".equals(value)){
	  						cq.eq(name, value);
	  					}
  					} else if("class java.math.BigDecimal".equals(type)){
  						if (beginValue != null && !"".equals(beginValue)) {
  							if (beginValue.contains(".")) {
  								cq.ge(name, BigDecimal.valueOf(Double.parseDouble(beginValue)));
							} else {
								cq.ge(name, BigDecimal.valueOf(Long.parseLong(beginValue)));
							}
	  					}
	  					if (endValue != null && !"".equals(endValue)) {
	  						if (beginValue.contains(".")) {
  								cq.le(name, BigDecimal.valueOf(Double.parseDouble(endValue)));
							} else {
								cq.le(name, BigDecimal.valueOf(Long.parseLong(endValue)));
							}
	  					}
	  					Object value = PropertyUtils.getSimpleProperty(searchObj, name);
	  					if(value!=null&&!"".equals(value)){
	  						cq.eq(name, value);
	  					}
  					}else if("class java.lang.Short".equals(type)){
  						if (beginValue != null && !"".equals(beginValue)) {
	  						cq.ge(name, Short.parseShort(beginValue));
	  					}
	  					if (endValue != null && !"".equals(endValue)) {
	  						cq.le(name, Short.parseShort(endValue));
	  					}
	  					Object value = PropertyUtils.getSimpleProperty(searchObj, name);
	  					if(value!=null&&!"".equals(value)){
	  						cq.eq(name, value);
	  					}
  					}else if("class java.lang.Long".equals(type)){
  						if (beginValue != null && !"".equals(beginValue)) {
	  						cq.ge(name, Long.parseLong(beginValue));
	  					}
	  					if (endValue != null && !"".equals(endValue)) {
	  						cq.le(name, Long.parseLong(endValue));
	  					}
	  					Object value = PropertyUtils.getSimpleProperty(searchObj, name);
	  					if(value!=null&&!"".equals(value)){
	  						cq.eq(name, value);
	  					}
  					}else if ("class java.util.Date".equals(type)) {
  						// 由于前台使用时间控件，目前仅提供下面两种格式支持。
  						SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
  						SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
  						if (StringUtils.isNotBlank(beginValue)) {
  							if (beginValue.length() == 19) {
  								cq.ge(name, sdf1.parse(beginValue));
							} else if (beginValue.length() == 10) {
								cq.ge(name, sdf2.parse(beginValue));
							}
	  					}
  						if (StringUtils.isNotBlank(endValue)) {
  							if (endValue.length() == 19) {
  								cq.le(name, sdf1.parse(endValue));
  							} else if (endValue.length() == 10) {
  								// 对于"yyyy-MM-dd"格式日期，因时间默认为0，故此添加" 23:23:59"并使用sdf1解析，以方便查询日期时间数据
  								cq.le(name, sdf1.parse(endValue + " 23:23:59"));
  							}
  						}
	  					Object value = PropertyUtils.getSimpleProperty(searchObj, name);
	  					if(value!=null&&!"".equals(value)){
	  						cq.eq(name, value);
	  					}
  						if (null != value) {
  							// 判断开始时间
  							if (name.contains(BEGIN)) {
  								String realName = StringUtil.firstLowerCase(name.substring(5, name.length()));
  								if (!BEGIN.equals(name.substring(0, 5)) || !descriptorsNames.contains(realName)) {
  									logger.error("该查询属性 [" + name + "] 命名不规则");
  								} else {
  									cq.ge(realName, value);
  								}
  							}
  							// 判断结束时间
  							else if (name.contains(END)) {
  								String realName = StringUtil.firstLowerCase(name.substring(3, name.length()));
  								if (!END.equals(name.substring(0, 3)) || !descriptorsNames.contains(realName)) {
  									logger.error("该查询属性 [" + name + "] 命名不规则");
  								} else {
  									cq.le(name, value);
  								}
  							}
  							else {
  								cq.eq(name, value);
  							}
  						}
  					}
  				}
  			} catch (Exception e) {
  				e.printStackTrace();
  			}
  		}
  		//添加选择条件
  		cq.add();
  	}
  	
  	
	/**
	 * 得到对象属性中所有name
	 * @param origDescriptors
	 * @return
	 */
  	private static String getDescriptorsNames(PropertyDescriptor origDescriptors[]) {
  		StringBuilder sb = new StringBuilder();
  		for (int i = 0; i < origDescriptors.length; i++) {
  			sb.append(origDescriptors[i].getName() + ",");
  		}
  		return sb.toString();
  	}
  	
  	public static void main(String[] args) {
  		
	}
  
}


