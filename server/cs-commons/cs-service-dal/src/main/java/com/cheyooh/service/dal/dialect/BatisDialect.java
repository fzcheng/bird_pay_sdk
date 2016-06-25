package com.cheyooh.service.dal.dialect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.builder.xml.dynamic.ForEachSqlNode;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import com.cheyooh.service.dal.mybatis.SqlStatementHandler;

/**
 * @author zhouzg@cheyooh.com {MSN:zzgzhou@hotmail.com, QQ:11039850}
 * 
 */
public class BatisDialect {

	public static int getCount(SqlSession session,String sqlTemplateId,Object params){
		Configuration cfg=session.getConfiguration();		
		MappedStatement mst=cfg.getMappedStatement(sqlTemplateId);		
		BoundSql bs=mst.getBoundSql(params);		
		String sql=bs.getSql();
		
		 
		Dialect dialect=BatisDialect.getDialect(cfg);
		String count_sql=dialect.getCountSql(sql);
		
		int cnt=0;
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			Date startTime=new Date();
			
			pstmt=session.getConnection().prepareStatement(count_sql);
			setParameters(pstmt,mst,bs,params);
			
			try{
				rs=pstmt.executeQuery();
			}finally{
				SqlStatementHandler.logSql(startTime,pstmt,null,mst);
			}			
			
			if(rs.next()){
				cnt=rs.getInt(1);
			}
		}catch(SQLException e){
			throw new RuntimeException("Get Count SQL Error, Query-SQL:"+sql+"\r\n\r\n, Count-SQL: "+count_sql,e);
		}finally{
			if(rs!=null)try{rs.close();}catch(Exception e){}
			if(pstmt!=null)try{pstmt.close();}catch(Exception e){}
		}
		return cnt;
	}
	 
	
    /** 
     * 对SQL参数(?)设值,参考org.apache.ibatis.executor.parameter.DefaultParameterHandler 
     * @param ps 
     * @param mappedStatement 
     * @param boundSql 
     * @param parameterObject 
     * @throws SQLException
     */  
    private static void setParameters(PreparedStatement ps,MappedStatement mappedStatement,BoundSql boundSql,Object parameterObject) throws SQLException {  
        ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());  
        
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();  
        if (parameterMappings != null) {
            Configuration configuration = mappedStatement.getConfiguration();  
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();  
            MetaObject metaObject = parameterObject == null ? null: configuration.newMetaObject(parameterObject);  
            for (int i = 0; i < parameterMappings.size(); i++) {  
                ParameterMapping parameterMapping = parameterMappings.get(i);  
                if (parameterMapping.getMode() != ParameterMode.OUT) {  
                    Object value;
                    String propertyName = parameterMapping.getProperty();  
                    PropertyTokenizer prop = new PropertyTokenizer(propertyName);
                    if (parameterObject == null) {  
                        value = null;  
                    } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {  
                        value = parameterObject;  
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {  
                        value = boundSql.getAdditionalParameter(propertyName);  
                    } else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX)&& boundSql.hasAdditionalParameter(prop.getName())) {  
                        value = boundSql.getAdditionalParameter(prop.getName());  
                        if (value != null) {  
                            value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));  
                        }  
                    } else {  
                        value = metaObject == null ? null : metaObject.getValue(propertyName);  
                    }  
                    TypeHandler typeHandler = parameterMapping.getTypeHandler();  
                    if (typeHandler == null) {  
                        throw new ExecutorException("There was no TypeHandler found for parameter "+ propertyName + " of statement "+ mappedStatement.getId());  
                    }
                    typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());  
                }  
            }  
        }  
    }  
     
	public static Dialect getDialect(Configuration configuration) {
		Dialect.Type databaseType = null;
		try {
			databaseType = Dialect.Type.valueOf(configuration.getVariables().getProperty("dialect").toUpperCase());
		} catch (Exception e) {
			// ignore
		}
		if (databaseType == null) {
			throw new RuntimeException("the value of the dialect property in configuration.xml is not defined : "
					+ configuration.getVariables().getProperty("dialect"));
		}
		Dialect dialect = null;
		switch (databaseType) {
		case SQLSERVER2005:
			dialect = new SQLServer2005Dialect();
			break;
		case MYSQL:
			dialect = new MySQLDialect();
			break;

		}
		return dialect;
	}

}
