package com.cheyooh.service.dal.mybatis;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.cheyooh.service.dal.DALException;
import com.cheyooh.service.dal.DALMode;
import com.cheyooh.service.dal.dialect.BatisDialect;
import com.cheyooh.tools.log.Logger;

/**
 * @author zzg.zhou@mbook.com.cn {MSN:zzgzhou@hotmail.com, QQ:11039850}
 * 
 */
@SuppressWarnings("rawtypes")
public class SelfSqlSession implements SqlSession {
	static Logger logger = new Logger(SelfSqlSession.class);

	private ConfigurationManager cm = ConfigurationManager.getInstance();

	private DALMode accessMode = DALMode.AUTO;

	private Map<String, WRSqlSession> readSessions = new HashMap<String, WRSqlSession>();
	private Map<String, WRSqlSession> writeSessions = new HashMap<String, WRSqlSession>();

	public SelfSqlSession() {

	}

	public Object selectOne(String statement) {
		if (statement.endsWith("@count")) {
			statement = statement.substring(0, statement.length() - 6);
			SqlSession session = getReadSession(statement);

			return BatisDialect.getCount(session, statement, null);
		} else {
			return selectOne(statement, null);
		}
	}

	public Object selectOne(String statement, Object parameter) {
		if (statement.endsWith("@count")) {
			statement = statement.substring(0, statement.length() - 6);
			SqlSession session = getReadSession(statement);

			return BatisDialect.getCount(session, statement, parameter);
		} else {
			return getReadSession(statement).selectOne(statement, parameter);		     		    
		}
	}

	public List selectList(String statement) {
		return getReadSession(statement).selectList(statement);
	}

	public List selectList(String statement, Object parameter) {
		return getReadSession(statement).selectList(statement, parameter);
	}

	public List selectList(String statement, Object parameter, RowBounds rowBounds) {
		return getReadSession(statement).selectList(statement, parameter, rowBounds);
	}

	public Map selectMap(String statement, String mapKey) {
		return getReadSession(statement).selectMap(statement, mapKey);
	}

	public Map selectMap(String statement, Object parameter, String mapKey) {
		return getReadSession(statement).selectMap(statement, parameter, mapKey);
	}

	public Map selectMap(String statement, Object parameter, String mapKey, RowBounds rowBounds) {
		return getReadSession(statement).selectMap(statement, parameter, mapKey, rowBounds);
	}

	public void select(String statement, ResultHandler handler) {
		getReadSession(statement).select(statement, handler);
	}

	public void select(String statement, Object parameter, ResultHandler handler) {
		getReadSession(statement).select(statement, parameter, handler);
	}

	public void select(String statement, Object parameter, RowBounds rowBounds, ResultHandler handler) {
		getReadSession(statement).select(statement, parameter, rowBounds, handler);

	}

	public int insert(String statement) {
		return getWriteSession(statement).insert(statement);

	}

	public int insert(String statement, Object parameter) {
		return getWriteSession(statement).insert(statement, parameter);
	}

	public int update(String statement) {
		return getWriteSession(statement).update(statement);
	}

	public int update(String statement, Object parameter) {
		return getWriteSession(statement).update(statement, parameter);
	}

	public int delete(String statement) {
		return getWriteSession(statement).delete(statement);
	}

	public int delete(String statement, Object parameter) {
		return getWriteSession(statement).delete(statement, parameter);
	}

	public void commit() {
		for (SqlSession s : writeSessions.values()) {
			s.commit();
		}

	}

	public void commit(boolean force) {
		for (SqlSession s : writeSessions.values()) {
			s.commit(force);
		}

	}

	public void rollback() {
		for (SqlSession s : writeSessions.values()) {
			s.rollback();
		}

	}

	public void rollback(boolean force) {
		for (SqlSession s : writeSessions.values()) {
			s.rollback(force);
		}

	}

	public void close() {
		for (SqlSession s : writeSessions.values()) {
			s.close();
		}
		writeSessions.clear();

		for (SqlSession s : readSessions.values()) {
			s.close();
		}
		readSessions.clear();
	}

	public void clearCache() {
		for (SqlSession s : writeSessions.values()) {
			s.clearCache();
		}

		for (SqlSession s : readSessions.values()) {
			s.clearCache();
		}
	}

	private String extractNamespace(String statementId){
		 int lastPeriod = statementId.lastIndexOf('.');
		 return lastPeriod > 0 ? statementId.substring(0, lastPeriod) : statementId;
	}
	
	
	private WRSqlSession findReadSessionForWrite(String id){
		String ns=extractNamespace(id);
	 	
		WRSqlSession session = readSessions.get(ns);
		if (session == null || session.isWriteable()==false){
			session=null;
			
			for(WRSqlSession s:readSessions.values()){
				if(s.getConfiguration().getMappedStatement(id)!=null){
					if(s.isWriteable()){
						session=s;
						break;
					}
				}			
			}	
		}
		return session;	 
	}
	
	private WRSqlSession findSession( Map<String, WRSqlSession> h,String id){
		String ns=extractNamespace(id);
	 	
		WRSqlSession session = h.get(ns);
		if (session == null) {
			for(WRSqlSession s:h.values()){
				if(s.getConfiguration().getMappedStatement(id)!=null){
					session=s;
					break;
				}
			}	
		}
		return session;
	}
	
	private WRSqlSession findWriteSession(String id){
		return findSession(writeSessions,id);
		 
	}
	
	private WRSqlSession findReadSession(String id){
		return findSession(readSessions,id);		 
	}
	
	
	
	private SqlSession getWriteSession(String id) {
		if (accessMode == DALMode.SLAVE) {
			throw new DALException("Write disabled in slave mode!");
		}
		
		String ns=extractNamespace(id);
	 	
		WRSqlSession session = findWriteSession(id);
		if(session==null){
			session=findReadSessionForWrite(id);
			if(session!=null){
				writeSessions.put(ns, session);
			}
		}
		
		if(session==null){
			session = cm.openWriteSession(id);
			writeSessions.put(ns, session);
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("Write datasouce: " + session.getConfiguration() + ", statement: " + id);
		}
		return session;
	}

	private SqlSession getReadSession(String id) {
		if (accessMode == DALMode.MASTER) {
			return getWriteSession(id);
		}

		MappedStatement statement = cm.getMappedStatement(id);
		if (StatementType.PREPARED != statement.getStatementType()) {
			return getWriteSession(id);
		}

		WRSqlSession session = findWriteSession(id);
		if(session==null){
			String ns=extractNamespace(id);
			session=findReadSession(id);
			 
			if (session == null) {
				session = cm.openReadSession(id);
				readSessions.put(ns, session);
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Read  datasouce: " + session.getConfiguration() + ", statement: " + id);
		}
		return session;
	}

	public Configuration getConfiguration() {
		return cm;
	}

	@SuppressWarnings("unchecked")
	public <T> T getMapper(Class<T> type) {
		ClassLoader classLoader = type.getClassLoader();
		Class<?>[] interfaces = new Class[] { type };
		SelfMapperProxy proxy = new SelfMapperProxy(this);
		return (T) Proxy.newProxyInstance(classLoader, interfaces, proxy);
	}

	public Connection getConnection() {
		throw new RuntimeException("Incorrect invoke!");
	}

	public DALMode getAccessMode() {
		return accessMode;
	}

	public void setAccessMode(DALMode accessMode) {
		this.accessMode = accessMode;
	}

}
