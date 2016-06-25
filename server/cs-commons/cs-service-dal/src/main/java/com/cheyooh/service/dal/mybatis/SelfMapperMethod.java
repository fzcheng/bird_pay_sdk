package com.cheyooh.service.dal.mybatis;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.cheyooh.tools.log.Logger;
import com.cheyooh.tools.utils.Pagination;

@SuppressWarnings({"rawtypes","unchecked"})
public class SelfMapperMethod {
	static Logger logger = new Logger(SelfMapperMethod.class);
	
	private SqlSession sqlSession;
	private Configuration config;

	private SqlCommandType type;
	private String commandName;

	private Class<?> declaringInterface;
	private Method method;

	private boolean returnsList;
	private boolean returnsMap;
	private String mapKey;

	private Integer rowBoundsIndex;
	private List<String> paramNames;
	private List<Integer> paramPositions;

	private boolean hasNamedParameters;

	private boolean returnsPage;
	private Integer paginationIndex;
	
	private boolean dynamicQuery;
	public SelfMapperMethod(Class<?> declaringInterface, Method method, SqlSession sqlSession) {
		paramNames = new ArrayList<String>();
		paramPositions = new ArrayList<Integer>();
		this.sqlSession = sqlSession;
		this.method = method;
		this.config = sqlSession.getConfiguration();
		this.hasNamedParameters = false;
		this.declaringInterface = declaringInterface;
		
		if(method.getName().startsWith("DDQ")){
			dynamicQuery=true;
		}
	 
	}

	public Object execute(Object[] args) {
		if(dynamicQuery){	
			commandName = declaringInterface.getName() + "." + args[0];
			
			Object[] qa=null;
			if(args.length>1){
				qa=new Object[args.length-1];
				for(int i=0;i<qa.length;i++){
					qa[i]=args[i+1];					
				}
			}
			args=qa;
		}else{
			commandName = declaringInterface.getName() + "." + method.getName();
		}
		setupMethodSignature();
		setupCommandType();	
		
		Object result;
		if (SqlCommandType.INSERT == type) {
			Object param = getParam(args);
			result = sqlSession.insert(commandName, param);
		} else if (SqlCommandType.UPDATE == type) {
			Object param = getParam(args);
			result = sqlSession.update(commandName, param);
		} else if (SqlCommandType.DELETE == type) {
			Object param = getParam(args);
			result = sqlSession.delete(commandName, param);
		} else if (SqlCommandType.SELECT == type) {		
			if (returnsList) {
				result = executeForList(args);
			} else if (returnsMap) {
				result = executeForMap(args);
			} else if(returnsPage){
				result = executeForPage(args);
			}else {
				Object param = getParam(args);
				result = sqlSession.selectOne(commandName, param);
			}			
		} else {
			throw new BindingException("Unkown execution method for: " + commandName);
		}
		return result;
	}
	 
	private Object executeForPage(Object[] args){
		final Object param = getParam(args);
		Pagination<Object> page;
		RowBounds rowBounds;
		if (paginationIndex != null) {
			page = (Pagination)args[paginationIndex];
			rowBounds = new RowBounds(page.getOffset(), page.getLimit());
		}
		else if (rowBoundsIndex != null) {
			rowBounds = (RowBounds) args[rowBoundsIndex];
			page = new Pagination<Object>(rowBounds.getOffset()/rowBounds.getLimit(),rowBounds.getLimit());
		}
		else {
			throw new BindingException("Invalid bound statement (not found rowBounds or pagination in paramenters)");
		}
		page.setTotal(executeForCount(param));
		page.setList(executeForList(param, rowBounds));
		return page;
	}
	
	private int executeForCount(Object param) {
		return (Integer)sqlSession.selectOne(commandName+"@count", param);		  
	}
	 
	private List executeForList(Object param, RowBounds rowBounds) {
		
		return sqlSession.selectList(commandName, param, rowBounds);
	}
	
	private List executeForList(Object[] args) {
		List result;
		Object param = getParam(args);
		
		if (rowBoundsIndex != null) {
			RowBounds rowBounds = (RowBounds) args[rowBoundsIndex];
			result = sqlSession.selectList(commandName, param, rowBounds);
		} else {
			result = sqlSession.selectList(commandName, param);
		}
		return result;
	}

	private Map executeForMap(Object[] args) {
		Map result;
		Object param = getParam(args);
		if (rowBoundsIndex != null) {
			RowBounds rowBounds = (RowBounds) args[rowBoundsIndex];
			result = sqlSession.selectMap(commandName, param, mapKey, rowBounds);
		} else {
			result = sqlSession.selectMap(commandName, param, mapKey);
		}
		return result;
	}

	private Object getParam(Object[] args) {
		final int paramCount = paramPositions.size();
		if (args == null || paramCount == 0) {
			return null;
		} else if (!hasNamedParameters && paramCount == 1) {
			return checkExampleArg(args[paramPositions.get(0)]);
		} else {
			Map<String, Object> param = new HashMap<String, Object>();
			for (int i = 0; i < paramCount; i++) {
				param.put(paramNames.get(i), checkExampleArg(args[paramPositions.get(i)]));
			}
			return param;
		}
	}
    
	/**
	 * 检查Example查询中 Order by子句可能出现的SQL注入问题
	 * @param arg
	 * @return
	 */
	private Object checkExampleArg(Object arg){
		if(arg==null){
			return null;
		}
		
		
		String invalid_order_by=null;
		
		Class<?> clazz=arg.getClass();
		
		if(clazz.getName().endsWith("Example")){
			try{
				Method m=clazz.getMethod("getOrderByClause");
				String v=(String)m.invoke(arg);
				if(v!=null){					
					for(int i=0;i<v.length();i++){
						char c=v.charAt(i);
						if(c==';' || c=='\\' || c=='\'' || c=='"' || c=='(' || c=='@'){
							invalid_order_by=v;
							break;
						}
					}										
				}
			}catch(NoSuchMethodException ne){
			}catch(Exception e){
				logger.error("Class: "+clazz.getName()+"  check exception: "+e,e);
			}
		}
		
		if(invalid_order_by!=null){
			throw new RuntimeException("无效的Order by子句: "+invalid_order_by);
		}else{
			return arg;
		}
	}
	 

	private void setupMethodSignature() {
		if (List.class.isAssignableFrom(method.getReturnType())) {
			returnsList = true;
		}
		
		if (Pagination.class.isAssignableFrom(method.getReturnType())) {
			returnsPage = true;
		}
		
		if (Map.class.isAssignableFrom(method.getReturnType())) {
			final MapKey mapKeyAnnotation = method.getAnnotation(MapKey.class);
			if (mapKeyAnnotation != null) {
				mapKey = mapKeyAnnotation.value();
				returnsMap = true;
			}
		}
		
		int baseIndex=dynamicQuery?1:0;		

		final Class<?>[] argTypes = method.getParameterTypes();
		for (int i = baseIndex; i < argTypes.length; i++) {
			if (Pagination.class.isAssignableFrom(argTypes[i])) {
				paginationIndex = i-baseIndex;
			}else if (RowBounds.class.isAssignableFrom(argTypes[i])) {
				rowBoundsIndex = i-baseIndex;
			} else {
				String paramName = String.valueOf(paramPositions.size());
				paramName = getParamNameFromAnnotation(i, paramName);
				paramNames.add(paramName);
				paramPositions.add(i-baseIndex);
			}
		}
	}

	private String getParamNameFromAnnotation(int i, String paramName) {
		Object[] paramAnnos = method.getParameterAnnotations()[i];
		for (int j = 0; j < paramAnnos.length; j++) {
			if (paramAnnos[j] instanceof Param) {
				hasNamedParameters = true;
				paramName = ((Param) paramAnnos[j]).value();
			}
		}
		return paramName;
	}

	private void setupCommandType() {
		MappedStatement ms = config.getMappedStatement(commandName);
		if(ms==null){
			throw new BindingException("Command not found: " + commandName);
		}
		
		type = ms.getSqlCommandType();
		if (type == SqlCommandType.UNKNOWN) {
			throw new BindingException("Unknown execution method for: " + commandName);
		}
	}

 

}
