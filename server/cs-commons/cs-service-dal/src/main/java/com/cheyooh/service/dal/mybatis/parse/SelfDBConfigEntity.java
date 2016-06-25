package com.cheyooh.service.dal.mybatis.parse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 定义db.xml对应的实体
 * @author Com
 *
 */
public class SelfDBConfigEntity 
{
	private String id = null;
	private String mode = null;
	private String dialect = null;
    private String transactionManagerType = null;
    private String dataSourceType = null;
    private String driver = null;
    private String url = null;
    private String username = null;
    private String password = null;
    private String poolMaximumActiveConnections = null;
    private String poolMaximumIdleConnections = null;
    private String poolPingEnabled = null;
    private String poolPingQuery = null;
    private String poolPingConnectionsNotUsedFor = null;
	private List<String> urlMappers = new ArrayList<String>();
	private List<String> resourceMappers = new ArrayList<String>();
	
	//键值名称定义
	public static final String KEY_id = "id";
	public static final String KEY_mode = "mode";
	public static final String KEY_dialect = "dialect";
    public static final String KEY_transactionManagerType = "transactionManagerType";
    public static final String KEY_dataSourceType = "dataSourceType";
    public static final String KEY_driver = "driver";
    public static final String KEY_url = "url";
    public static final String KEY_username = "username";
    public static final String KEY_password = "password";
    public static final String KEY_poolMaximumActiveConnections = "poolMaximumActiveConnections";
    public static final String KEY_poolMaximumIdleConnections = "poolMaximumIdleConnections";
    public static final String KEY_poolPingEnabled = "poolPingEnabled";
    public static final String KEY_poolPingQuery = "poolPingQuery";
    public static final String KEY_poolPingConnectionsNotUsedFor = "poolPingConnectionsNotUsedFor";
	
	public void addUrlMapper(String urlMapper)
	{
		this.urlMappers.add(urlMapper);
	}
	public void addResourceMappers(String resourceMappers)
	{
		this.resourceMappers.add(resourceMappers);
	}
	//逐一给各个属性设置值。
	public void setAll(Map<String,String> map)
	{
		if(StringUtils.isNotEmpty(map.get(KEY_id)))
		{
			this.id = map.get(KEY_id);
		}
		if(StringUtils.isNotEmpty(map.get(KEY_mode)))
		{
			this.mode = map.get(KEY_mode);
		}
		if(StringUtils.isNotEmpty(map.get(KEY_dialect)))
		{
			this.dialect = map.get(KEY_dialect);
		}
		if(StringUtils.isNotEmpty(map.get(KEY_transactionManagerType)))
		{
			this.transactionManagerType = map.get(KEY_transactionManagerType);
		}
		if(StringUtils.isNotEmpty(map.get(KEY_dataSourceType)))
		{
			this.dataSourceType = map.get(KEY_dataSourceType);
		}
		if(StringUtils.isNotEmpty(map.get(KEY_driver)))
		{
			this.driver = map.get(KEY_driver);
		}
		if(StringUtils.isNotEmpty(map.get(KEY_url)))
		{
			this.url = map.get(KEY_url);
		}
		if(StringUtils.isNotEmpty(map.get(KEY_username)))
		{
			this.username = map.get(KEY_username);
		}
		if(StringUtils.isNotEmpty(map.get(KEY_password)))
		{
			this.password = map.get(KEY_password);
		}
		if(StringUtils.isNotEmpty(map.get(KEY_poolMaximumActiveConnections)))
		{
			this.poolMaximumActiveConnections = map.get(KEY_poolMaximumActiveConnections);
		}
		if(StringUtils.isNotEmpty(map.get(KEY_poolMaximumIdleConnections)))
		{
			this.poolMaximumIdleConnections = map.get(KEY_poolMaximumIdleConnections);
		}
		if(StringUtils.isNotEmpty(map.get(KEY_poolPingEnabled)))
		{
			this.poolPingEnabled = map.get(KEY_poolPingEnabled);
		}
		if(StringUtils.isNotEmpty(map.get(KEY_poolPingQuery)))
		{
			this.poolPingQuery = map.get(KEY_poolPingQuery);
		}
		if(StringUtils.isNotEmpty(map.get(KEY_poolPingConnectionsNotUsedFor)))
		{
			this.poolPingConnectionsNotUsedFor = map.get(KEY_poolPingConnectionsNotUsedFor);
		}
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getDialect() {
		return dialect;
	}
	public void setDialect(String dialect) {
		this.dialect = dialect;
	}
	public String getTransactionManagerType() {
		return transactionManagerType;
	}
	public void setTransactionManagerType(String transactionManagerType) {
		this.transactionManagerType = transactionManagerType;
	}
	public String getDataSourceType() {
		return dataSourceType;
	}
	public void setDataSourceType(String dataSourceType) {
		this.dataSourceType = dataSourceType;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPoolMaximumActiveConnections() {
		return poolMaximumActiveConnections;
	}
	public void setPoolMaximumActiveConnections(String poolMaximumActiveConnections) {
		this.poolMaximumActiveConnections = poolMaximumActiveConnections;
	}
	public String getPoolMaximumIdleConnections() {
		return poolMaximumIdleConnections;
	}
	public void setPoolMaximumIdleConnections(String poolMaximumIdleConnections) {
		this.poolMaximumIdleConnections = poolMaximumIdleConnections;
	}
	public String getPoolPingEnabled() {
		return poolPingEnabled;
	}
	public void setPoolPingEnabled(String poolPingEnabled) {
		this.poolPingEnabled = poolPingEnabled;
	}
	public String getPoolPingQuery() {
		return poolPingQuery;
	}
	public void setPoolPingQuery(String poolPingQuery) {
		this.poolPingQuery = poolPingQuery;
	}
	public String getPoolPingConnectionsNotUsedFor() {
		return poolPingConnectionsNotUsedFor;
	}
	public void setPoolPingConnectionsNotUsedFor(
			String poolPingConnectionsNotUsedFor) {
		this.poolPingConnectionsNotUsedFor = poolPingConnectionsNotUsedFor;
	}
	public List<String> getUrlMappers() {
		return urlMappers;
	}
	public List<String> getResourceMappers() {
		return resourceMappers;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof SelfDBConfigEntity)
		{
			SelfDBConfigEntity e = (SelfDBConfigEntity) obj;
			if(!this.getId().equals(e.getId()))
			{
				return false;
			}
			if(!this.getDataSourceType().equals(e.getDataSourceType()))
			{
				return false;
			}
			if(!this.getDialect().equals(e.getDialect()))
			{
				return false;
			}
			if(!this.getDriver().equals(e.getDriver()))
			{
				return false;
			}
			if(!this.getMode().equals(e.getMode()))
			{
				return false;
			}
			if(!this.getPassword().equals(e.getPassword()))
			{
				return false;
			}
			if(!this.getPoolMaximumActiveConnections().equals(e.getPoolMaximumActiveConnections()))
			{
				return false;
			}
			if(!this.getPoolMaximumIdleConnections().equals(e.getPoolMaximumIdleConnections()))
			{
				return false;
			}
			if(!this.getPoolPingConnectionsNotUsedFor().equals(e.getPoolPingConnectionsNotUsedFor()))
			{
				return false;
			}
			if(!this.getPoolPingEnabled().equals(e.getPoolPingEnabled()))
			{
				return false;
			}
			if(!this.getPoolPingQuery().equals(e.getPoolPingQuery()))
			{
				return false;
			}
			if(!this.getTransactionManagerType().equals(e.getTransactionManagerType()))
			{
				return false;
			}
			if(!this.getUrl().equals(e.getUrl()))
			{
				return false;
			}
			if(!this.getUsername().equals(e.getUsername()))
			{
				return false;
			}
			//对于mapper的判定
			if(this.getUrlMappers().size() != e.getUrlMappers().size())
			{
				return false;
			}
			if(!this.getUrlMappers().containsAll(e.getUrlMappers()))
			{
				return false;
			}
			if(this.getResourceMappers().size() != e.getResourceMappers().size())
			{
				return false;
			}
			if(!this.getResourceMappers().containsAll(e.getResourceMappers()))
			{
				return false;
			}
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
