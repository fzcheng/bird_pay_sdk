package jeecg.ext.service;

import jeecg.ext.online.db.entity.ExCgFormDataSource;
import jeecg.system.service.SystemService;

public interface CustomDataService extends SystemService{
	void setDataSource(ExCgFormDataSource exCgFormDataSource);
}
