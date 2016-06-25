package jeecg.system.listener;

import java.util.List;

import javax.servlet.ServletContextEvent;

import jeecg.system.pojo.base.TSIcon;
import jeecg.system.service.SystemService;

import org.jeecgframework.core.common.model.common.DBTable;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


/**
 * 系统初始化监听器,在系统启动时运行,进行一些初始化工作
 * @author laien
 *
 */
public class InitListener  implements javax.servlet.ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		SystemService systemService = (SystemService) webApplicationContext.getBean("systemService");
		//对数据字典进行缓存
		systemService.initAllTypeGroups();
		
		List<DBTable> dbTableList = systemService.getAllDbTableName();
		for(int i=0;i<dbTableList.size();i++){
			DBTable dbTable = dbTableList.get(i);
			String tableName = dbTable.getTableName();
			String entityName = dbTable.getEntityName();
			System.out.println("+++++++++第"+i+"个+++++++++");
			System.out.println("的tableName是:"+tableName);
			System.out.println("的entityName是:"+entityName);
			if(tableName.equals("cgform_datasource")){
//				systemService.loadAll(TSIcon.class);
				List<Object> list = systemService.findByQueryString("SELECT jdbcUrl FROM ExCgFormDataSource");
				int size = list.size();
				for(int j=0;j<list.size();j++){
					Object object = list.get(j);
					System.out.println("第"+j+"个jdbc_url是:"+object);
				}
//				List<Object> List = systemService.findByQueryString("SELECT * FROM game_sdk_jeecg.cgform_datasource;");
			}
		}
	}

}
