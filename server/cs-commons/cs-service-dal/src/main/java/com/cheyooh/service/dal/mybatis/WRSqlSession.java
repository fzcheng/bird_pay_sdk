package com.cheyooh.service.dal.mybatis;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

@SuppressWarnings("rawtypes")
public class WRSqlSession implements SqlSession{
	private boolean writeable=true;
	private SqlSession session;
	
	public WRSqlSession(SqlSession session){
		this(session,true);
	}
	public WRSqlSession(SqlSession session,boolean writeable) {
		super();
		this.session = session;
		this.writeable=writeable;
	}
 
	public SqlSession getSession() {
		return session;
	}
	
	public boolean isWriteable(){
		return writeable; 
	}

	public void setSession(SqlSession session) {
		this.session = session;
	}
	public Object selectOne(String statement) {
		return session.selectOne(statement);
	}
	public Object selectOne(String statement, Object parameter) {
		return session.selectOne(statement, parameter);
	}
	
	public List selectList(String statement) {
		return session.selectList(statement);
	}
	public List selectList(String statement, Object parameter) {
		return session.selectList(statement, parameter);
	}
	public List selectList(String statement, Object parameter, RowBounds rowBounds) {
		return session.selectList(statement, parameter, rowBounds);
	}
	public Map selectMap(String statement, String mapKey) {
		return session.selectMap(statement, mapKey);
	}
	public Map selectMap(String statement, Object parameter, String mapKey) {
		return session.selectMap(statement, parameter, mapKey);
	}
	public Map selectMap(String statement, Object parameter, String mapKey, RowBounds rowBounds) {
		return session.selectMap(statement, parameter, mapKey, rowBounds);
	}
	public void select(String statement, Object parameter, ResultHandler handler) {
		session.select(statement, parameter, handler);
	}
	public void select(String statement, ResultHandler handler) {
		session.select(statement, handler);
	}
	public void select(String statement, Object parameter, RowBounds rowBounds, ResultHandler handler) {
		session.select(statement, parameter, rowBounds, handler);
	}
	public int insert(String statement) {
		return session.insert(statement);
	}
	public int insert(String statement, Object parameter) {
		return session.insert(statement, parameter);
	}
	public int update(String statement) {
		return session.update(statement);
	}
	public int update(String statement, Object parameter) {
		return session.update(statement, parameter);
	}
	public int delete(String statement) {
		return session.delete(statement);
	}
	public int delete(String statement, Object parameter) {
		return session.delete(statement, parameter);
	}
	public void commit() {
		session.commit();
	}
	public void commit(boolean force) {
		session.commit(force);
	}
	public void rollback() {
		session.rollback();
	}
	public void rollback(boolean force) {
		session.rollback(force);
	}
	public void close() {
		session.close();
	}
	public void clearCache() {
		session.clearCache();
	}
	public Configuration getConfiguration() {
		return session.getConfiguration();
	}
	public <T> T getMapper(Class<T> type) {
		return session.getMapper(type);
	}
	public Connection getConnection() {
		return session.getConnection();
	}
	
}
