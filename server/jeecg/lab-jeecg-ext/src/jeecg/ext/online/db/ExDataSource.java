package jeecg.ext.online.db;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import jeecg.ext.online.db.entity.ExCgFormDataSource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

@Scope("singleton")
@Service("exDataSource")
public class ExDataSource {
	private static final Logger logger = Logger.getLogger(ExDataSource.class);
	
	private Map<String,CachedDataSource> hDatasource=new HashMap<String,CachedDataSource>();
	
	public ExDataSource(){
		
	}
	
  
	public  DataSource getDataSource(ExCgFormDataSource dscfg){
		String name=dscfg.getDatasource();
		CachedDataSource cds=hDatasource.get(name);
		if(cds==null){
			return createDataSource(dscfg).getDataSource();
		}else {
			String cstring=getConnectionString(dscfg);
			if(cds.getConnectionString().equals(cstring)==false){
				return createDataSource(dscfg).getDataSource();
			}else{
				return cds.getDataSource();
			}
		}
		 
	}
	
	private synchronized CachedDataSource createDataSource(ExCgFormDataSource dscfg){
		String name=dscfg.getDatasource();
		String cstring=getConnectionString(dscfg);
		logger.info("Create datasource: "+name+", "+dscfg.getJdbcUrl()+"|"+dscfg.getJdbcUsername());
		
		DriverManagerDataSource datasource = new DriverManagerDataSource(dscfg.getJdbcUrl(), dscfg.getJdbcUsername(), dscfg.getJdbcPassword());
		datasource.setDriverClassName(dscfg.getJdbcDriver());
		CachedDataSource cds=new CachedDataSource(cstring,datasource);
		
		hDatasource.put(name, cds);
		 
		return cds;
	}
	
	

	private String getConnectionString(ExCgFormDataSource dscfg){
		StringBuilder sb=new StringBuilder();
		sb.append(dscfg.getJdbcDriver());
		sb.append("|").append(dscfg.getJdbcUrl());
		sb.append("|").append(dscfg.getJdbcUsername());
		sb.append("|").append(dscfg.getJdbcPassword());
		
		return sb.toString();
	}
	
	
	
	class CachedDataSource{
		public CachedDataSource(String connectionString, DataSource dataSource) {
			super();
			this.connectionString = connectionString;
			this.dataSource = dataSource;
			this.lastTime=System.currentTimeMillis();
		}
		private long   lastTime;
		private String connectionString;
		private DataSource dataSource;
		
		public long getLastTime() {
			return lastTime;
		}
		public void setLastTime(long lastTime) {
			this.lastTime = lastTime;
		}
		public String getConnectionString() {
			return connectionString;
		}
		public void setConnectionString(String connectionString) {
			this.connectionString = connectionString;
		}
		public DataSource getDataSource() {
			return dataSource;
		}
		public void setDataSource(DataSource dataSource) {
			this.dataSource = dataSource;
		}
	}
	
}
