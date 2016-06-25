package jeecg.ext.online.ftl;

import java.io.IOException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateModel;

@SuppressWarnings("rawtypes")

public class ExTemplateContext {
	public ExTemplateContext() {
	}
 	 
	@PostConstruct
	public void init() {
		if (tags != null){			 
			String key;
			Iterator iterator = tags.keySet().iterator(); 
			while (iterator.hasNext()){
				key = (String) iterator.next();
				exFreemarker.setSharedVariable(key, (TemplateModel) tags.get(key));
			}
		}
	}

	public Locale getLocale() {
		return exFreemarker.getLocale();
	}

	public Template getTemplate(String tableName) {
		Template template = null;
		if (tableName == null)
			return null;

		try {
			template = exFreemarker.getTemplate(tableName, exFreemarker.getLocale(), "UTF-8");
			return template;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Map getTags() {
		return tags;
	}

	public void setTags(Map tags) {
		this.tags = tags;
	}

	@Resource
	private Configuration exFreemarker;
	
	private Map tags;

	public Configuration getExFreemarker() {
		return exFreemarker;
	}

	public void setExFreemarker(Configuration exFreemarker) {
		this.exFreemarker = exFreemarker;
	}
}
