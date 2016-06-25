package jeecg.ext.online.db;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

@SuppressWarnings("unchecked")
public class SpringBean {
	static ApplicationContext context;
	static{
		String path=System.getProperty("catalina.home","appcfg");		
		context=new FileSystemXmlApplicationContext(path+"/oss-jeecg-spring.xml");
	}
	
 	public static <T> T getBean(String id){
		return (T)context.getBean(id);
	}
 	
 	public static JdbcTemplate getJdbcTemplate(String name){
 		JdbcTemplate t=getBean("jdbcTemplate_"+name);
 		return t;
 	}
}
