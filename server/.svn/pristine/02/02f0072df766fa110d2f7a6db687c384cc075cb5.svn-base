package com.cheyooh.service.dal.mybatis;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMap;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.AutoMappingBehavior;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.type.TypeAliasRegistry;
import org.apache.ibatis.type.TypeHandlerRegistry;

import com.cheyooh.service.dal.mybatis.parse.DBSXMLConfigBuilder;
import com.cheyooh.service.dal.mybatis.parse.SelfDBConfigEntity;
import com.cheyooh.tools.cfg.EnvTools;
import com.cheyooh.tools.log.Logger;

/**
 * @author zzg.zhou@mbook.com.cn {MSN:zzgzhou@hotmail.com, QQ:11039850}
 * 
 */
public class XmlConfiguration extends Configuration{
	static Logger logger = new Logger(XmlConfiguration.class);
	
	private String name;

	private SelfConfiguration config;
	
	private boolean readable =false;
	private boolean writeable=false;
	 
	private boolean ready=false;
	
	private SelfDBConfigEntity selfDBConfigEntity;
	
	public XmlConfiguration(String name) {
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public boolean isReadable() {
		return readable;
	}

	public boolean isWriteable() {
		return writeable;
	}
 
	public Configuration getConfig() {
		return config;
	}
	
	public boolean checkAndReloadMappers(){
		return config.checkAndReloadMappers();
	}
	
	/**
	 * 加载新的配置文件
	 * @return
	 */
	public boolean loadConfig() 
	{		 
		SelfXMLConfigBuilder parser = new SelfXMLConfigBuilder(selfDBConfigEntity, null, null);
		config = parser.parse();
		
		String mode = config.getVariables().getProperty("mode", "wr").toLowerCase();
		if (mode.indexOf("w") >= 0) {
			writeable=true;
		}
		if (mode.indexOf("r") >= 0) {
			readable=true;
		}			
		ready=true;
		return true;
	}

	//通过计算相关信息的hashcode，来判定是否对应的值改变了。
	public boolean isChangeConfig(XmlConfiguration old)
	{
		return !this.selfDBConfigEntity.equals(old.getSelfDBConfigEntity());
	}
	
	public String toString() {
		StringBuilder sb=new StringBuilder(name);
		sb.append("[");
		if(writeable){
			sb.append("w");
		}
		if(readable){
			sb.append("r");
		}
		sb.append("]");
		return sb.toString();
	}

	public int hashCode() {
		return config.hashCode();
	}

	public boolean equals(Object obj) {
		return config.equals(obj);
	}

	public void addLoadedResource(String resource) {
		config.addLoadedResource(resource);
	}

	public boolean isResourceLoaded(String resource) {
		return config.isResourceLoaded(resource);
	}

	public Environment getEnvironment() {
		return config.getEnvironment();
	}

	public void setEnvironment(Environment environment) {
		config.setEnvironment(environment);
	}

	public AutoMappingBehavior getAutoMappingBehavior() {
		return config.getAutoMappingBehavior();
	}

	public void setAutoMappingBehavior(AutoMappingBehavior autoMappingBehavior) {
		config.setAutoMappingBehavior(autoMappingBehavior);
	}

	public boolean isLazyLoadingEnabled() {
		return config.isLazyLoadingEnabled();
	}

	public void setLazyLoadingEnabled(boolean lazyLoadingEnabled) {
		config.setLazyLoadingEnabled(lazyLoadingEnabled);
	}

	public boolean isAggressiveLazyLoading() {
		return config.isAggressiveLazyLoading();
	}

	public void setAggressiveLazyLoading(boolean aggressiveLazyLoading) {
		config.setAggressiveLazyLoading(aggressiveLazyLoading);
	}

	public boolean isMultipleResultSetsEnabled() {
		return config.isMultipleResultSetsEnabled();
	}

	public void setMultipleResultSetsEnabled(boolean multipleResultSetsEnabled) {
		config.setMultipleResultSetsEnabled(multipleResultSetsEnabled);
	}

	public boolean isUseGeneratedKeys() {
		return config.isUseGeneratedKeys();
	}

	public void setUseGeneratedKeys(boolean useGeneratedKeys) {
		config.setUseGeneratedKeys(useGeneratedKeys);
	}

	public ExecutorType getDefaultExecutorType() {
		return config.getDefaultExecutorType();
	}

	public void setDefaultExecutorType(ExecutorType defaultExecutorType) {
		config.setDefaultExecutorType(defaultExecutorType);
	}

	public boolean isCacheEnabled() {
		return config.isCacheEnabled();
	}

	public void setCacheEnabled(boolean cacheEnabled) {
		config.setCacheEnabled(cacheEnabled);
	}

	public Integer getDefaultStatementTimeout() {
		return config.getDefaultStatementTimeout();
	}

	public void setDefaultStatementTimeout(Integer defaultStatementTimeout) {
		config.setDefaultStatementTimeout(defaultStatementTimeout);
	}

	public boolean isUseColumnLabel() {
		return config.isUseColumnLabel();
	}

	public void setUseColumnLabel(boolean useColumnLabel) {
		config.setUseColumnLabel(useColumnLabel);
	}

	public Properties getVariables() {
		return config.getVariables();
	}

	public void setVariables(Properties variables) {
		config.setVariables(variables);
	}

	public TypeHandlerRegistry getTypeHandlerRegistry() {
		return config.getTypeHandlerRegistry();
	}

	public TypeAliasRegistry getTypeAliasRegistry() {
		return config.getTypeAliasRegistry();
	}

	public ObjectFactory getObjectFactory() {
		return config.getObjectFactory();
	}

	public void setObjectFactory(ObjectFactory objectFactory) {
		config.setObjectFactory(objectFactory);
	}

	public ObjectWrapperFactory getObjectWrapperFactory() {
		return config.getObjectWrapperFactory();
	}

	public void setObjectWrapperFactory(ObjectWrapperFactory objectWrapperFactory) {
		config.setObjectWrapperFactory(objectWrapperFactory);
	}

	public MetaObject newMetaObject(Object object) {
		return config.newMetaObject(object);
	}

	public ParameterHandler newParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) {
		return config.newParameterHandler(mappedStatement, parameterObject, boundSql);
	}

	public ResultSetHandler newResultSetHandler(Executor executor, MappedStatement mappedStatement, RowBounds rowBounds, ParameterHandler parameterHandler,
			ResultHandler resultHandler, BoundSql boundSql) {
		return config.newResultSetHandler(executor, mappedStatement, rowBounds, parameterHandler, resultHandler, boundSql);
	}

	public StatementHandler newStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameterObject, RowBounds rowBounds,
			ResultHandler resultHandler) {
		return config.newStatementHandler(executor, mappedStatement, parameterObject, rowBounds, resultHandler);
	}

	public Executor newExecutor(Transaction transaction) {
		return config.newExecutor(transaction);
	}

	public Executor newExecutor(Transaction transaction, ExecutorType executorType) {
		return config.newExecutor(transaction, executorType);
	}

	public void addKeyGenerator(String id, KeyGenerator keyGenerator) {
		config.addKeyGenerator(id, keyGenerator);
	}

	public Collection<String> getKeyGeneratorNames() {
		return config.getKeyGeneratorNames();
	}

	public Collection<KeyGenerator> getKeyGenerators() {
		return config.getKeyGenerators();
	}

	public KeyGenerator getKeyGenerator(String id) {
		return config.getKeyGenerator(id);
	}

	public boolean hasKeyGenerator(String id) {
		return config.hasKeyGenerator(id);
	}

	public void addCache(Cache cache) {
		config.addCache(cache);
	}

	public Collection<String> getCacheNames() {
		return config.getCacheNames();
	}

	public Collection<Cache> getCaches() {
		return config.getCaches();
	}

	public Cache getCache(String id) {
		return config.getCache(id);
	}

	public boolean hasCache(String id) {
		return config.hasCache(id);
	}

	public void addResultMap(ResultMap rm) {
		config.addResultMap(rm);
	}

	public Collection<String> getResultMapNames() {
		return config.getResultMapNames();
	}

	public Collection<ResultMap> getResultMaps() {
		return config.getResultMaps();
	}

	public ResultMap getResultMap(String id) {
		return config.getResultMap(id);
	}

	public boolean hasResultMap(String id) {
		return config.hasResultMap(id);
	}

	public void addParameterMap(ParameterMap pm) {
		config.addParameterMap(pm);
	}

	public Collection<String> getParameterMapNames() {
		return config.getParameterMapNames();
	}

	public Collection<ParameterMap> getParameterMaps() {
		return config.getParameterMaps();
	}

	public ParameterMap getParameterMap(String id) {
		return config.getParameterMap(id);
	}

	public boolean hasParameterMap(String id) {
		return config.hasParameterMap(id);
	}

	public void addMappedStatement(MappedStatement ms) {
		config.addMappedStatement(ms);
	}

	public Collection<String> getMappedStatementNames() {
		return config.getMappedStatementNames();
	}

	public Collection<MappedStatement> getMappedStatements() {
		return config.getMappedStatements();
	}

	public MappedStatement getMappedStatement(String id) {
		return config.getMappedStatement(id);
	}

	public Map<String, XNode> getSqlFragments() {
		return config.getSqlFragments();
	}

	public void addInterceptor(Interceptor interceptor) {
		config.addInterceptor(interceptor);
	}

	public void addMappers(String packageName, @SuppressWarnings("rawtypes") Class superType) {
		config.addMappers(packageName, superType);
	}

	public void addMappers(String packageName) {
		config.addMappers(packageName);
	}

	public <T> void addMapper(Class<T> type) {
		config.addMapper(type);
	}

	public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
		return config.getMapper(type, sqlSession);
	}

	public boolean hasMapper(@SuppressWarnings("rawtypes") Class type) {
		return config.hasMapper(type);
	}

	public boolean hasStatement(String statementName) {
		return config.hasStatement(statementName);
	}

	public void addStatementNodes(String namespace, List<XNode> nodes) {
		config.addStatementNodes(namespace, nodes);
	}

	public void addResource(String namespace, String resource) {
		config.addResource(namespace, resource);
	}

	public void addCacheRef(String namespace, String referencedNamespace) {
		config.addCacheRef(namespace, referencedNamespace);
	}

	public void buildAllStatements() {
		config.buildAllStatements();
	}

	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}

	public SelfDBConfigEntity getSelfDBConfigEntity() {
		return selfDBConfigEntity;
	}

	public void setSelfDBConfigEntity(SelfDBConfigEntity selfDBConfigEntity) {
		this.selfDBConfigEntity = selfDBConfigEntity;
	}

	
}
