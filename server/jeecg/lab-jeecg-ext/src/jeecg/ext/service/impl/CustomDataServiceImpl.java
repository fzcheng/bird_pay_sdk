package jeecg.ext.service.impl;

import org.jeecgframework.core.common.dao.impl.CommonDao;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import jeecg.ext.online.db.entity.ExCgFormDataSource;
import jeecg.ext.service.CustomDataService;
import jeecg.system.service.impl.SystemServiceImpl;

public class CustomDataServiceImpl extends SystemServiceImpl implements CustomDataService{

	public void setDataSource(ExCgFormDataSource exCgFormDataSource){
		DriverManagerDataSource datasource=new DriverManagerDataSource(exCgFormDataSource.getJdbcUrl(), exCgFormDataSource.getJdbcUsername(), exCgFormDataSource.getJdbcPassword());
		datasource.setDriverClassName(exCgFormDataSource.getJdbcDriver());
		CommonDao dao=new CommonDao();
		dao.setSession(datasource);
		super.commonDao=dao;
	}
}
