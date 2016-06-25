package org.jeecgframework.core.interceptors;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

public class CusLocalSessionFactoryBean extends LocalSessionFactoryBean{

	private final String packageSql="select * from ext_packages_to_scan  where enable=1";
	private final String columSql="package_name";
	
	private DataSource dataSourceCus;
	
	private static  List<String> packList;
	
	public DataSource getDataSourceCus() {
		return dataSourceCus;
	}

	public void setDataSourceCus(DataSource dataSourceCus) {
		this.dataSourceCus = dataSourceCus;
	}
	
	@Override
	public void afterPropertiesSet() throws IOException {
		if(packList==null){
			JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSourceCus);
			packList=getPackageList(jdbcTemplate,packageSql,new Object[0]);
		}
		/*String[] packagesToScan={"jeecg.system.pojo.*","jeecg.demo.entity.*","jeecg.test.entity.*","jeecg.cgform.entity.*",
				"jeecg.ext.online.db.*","jeecg.ext.table.*","jeecg.ext.sdk.*","jeecg.ext.online.*","jeecg.ext.wangcai.*"};*/
		
		super.setPackagesToScan(packList.toArray(new String[0]));
		super.afterPropertiesSet();
	}
	
	private List<String> getPackageList(JdbcTemplate jdbcTemplate,String sql,Object[] params){
		final List<String> list=new ArrayList<String>();
		
		jdbcTemplate.query(sql,params, new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet paramResultSet)
					throws SQLException {
				list.add(paramResultSet.getString(columSql));
			}
			
		});
		return list;
	}

	

}
