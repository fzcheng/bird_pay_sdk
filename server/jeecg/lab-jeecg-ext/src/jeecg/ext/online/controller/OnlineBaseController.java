package jeecg.ext.online.controller;

import jeecg.ext.online.db.entity.ExCgFormDataSource;
import jeecg.ext.service.CustomDataService;
import jeecg.ext.service.impl.CustomDataServiceImpl;
import jeecg.system.service.SystemService;

import org.jeecgframework.core.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

public abstract class OnlineBaseController extends BaseController {

	@Autowired
	private SystemService systemService;

	protected CustomDataService cuservice;

	public abstract String getDataSourceString();

	/**
	 * 将前台传递过来的日期格式的字符串，自动转化为Date类型
	 * 
	 * @param binder
	 */
	@ModelAttribute
	public void initServer() {
		if(cuservice!=null && cuservice.getSession()!=null){
			cuservice.getSession().close();
		}
		
		ExCgFormDataSource gameCgFormDataSource = this.systemService.getEntity(
				ExCgFormDataSource.class, getDataSourceString());
		cuservice = new CustomDataServiceImpl();
		cuservice.setDataSource(gameCgFormDataSource);

	}

}
