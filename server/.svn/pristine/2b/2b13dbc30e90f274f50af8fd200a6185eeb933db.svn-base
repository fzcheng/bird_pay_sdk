package jeecg.ext.online.ftl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jeecg.ext.online.controller.ExCgAutoListController;
import jeecg.ext.online.db.ExDatabaseService;
import jeecg.system.pojo.base.TSTypegroup;
import jeecg.system.service.SystemService;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import freemarker.core.Environment;
import freemarker.template.SimpleCollection;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateScalarModel;

@Service("exDictDataTag")
@SuppressWarnings("rawtypes")
public class ExDictDataTag implements TemplateDirectiveModel {
	static Log logger = LogFactory.getLog(ExCgAutoListController.class);

	public ExDictDataTag() {
		
	}

	public void execute(Environment env, Map params, TemplateModel loopVars[], TemplateDirectiveBody body) throws TemplateException, IOException {
		String name = getAttribute(params, "name");
		String label = getAttribute(params, "label");
		String formTable=getAttribute(params,"formTable");
		
		if (name == null){
			throw new TemplateException("Can not find attribute 'name' in data tag", env);
		}
		
		String tablename = getAttribute(params, "tablename");
		
		String var = getAttribute(params, "var");

		var = var == null ? name : var;

		if (tablename == null || tablename.trim().length() <= 0) {
			List dataList = (List) TSTypegroup.allTypes.get(name.toLowerCase());
			if (dataList == null){
				dataList = new ArrayList();
			}
			env.setGlobalVariable(var, new SimpleCollection(dataList));
		} else {
			if(StringUtils.isEmpty(label)){
				label=name;
			}
			
			StringBuilder sql = new StringBuilder("");
			sql.append("select distinct concat(").append(name).append(") as typecode, ");
			sql.append(label).append(" as typename ");
			sql.append(" from ").append(tablename);
			sql.append(" order by 2");
			
			List dataList=exDatabaseService.getJdbcTemplate(formTable).queryForList(sql.toString(), new Object[0]);
			
			env.setGlobalVariable(var, new SimpleCollection(dataList));
		}
		body.render(env.getOut());
	}

	private String getAttribute(Map params, String name) {

		String value = null;
		if (params.containsKey(name)) {
			TemplateModel paramValue = (TemplateModel) params.get(name);

			try {
				value = ((TemplateScalarModel) paramValue).getAsString();
			} catch (TemplateModelException e) {
				logger.error("get attribute " + name + " error", e);
			}
		}
		return value;
	}

	@Autowired
	private SystemService systemService;

	@Autowired
	private ExDatabaseService exDatabaseService;
	
	public SystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	public ExDatabaseService getExDatabaseService() {
		return exDatabaseService;
	}

	public void setExDatabaseService(ExDatabaseService exDatabaseService) {
		this.exDatabaseService = exDatabaseService;
	}
}
