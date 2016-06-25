package jeecg.ext.online.ftl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jeecg.cgform.common.FormHtmlUtil;
import jeecg.cgform.entity.config.CgFormFieldEntity;
import jeecg.cgform.entity.config.CgFormHeadEntity;
import jeecg.cgform.service.cgformftl.CgformFtlServiceI;
import jeecg.cgform.service.config.CgFormFieldServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import freemarker.cache.TemplateLoader;

public class ExTemplateLoader implements TemplateLoader {
	public ExTemplateLoader() {
	}

	public Object findTemplateSource(String name) throws IOException {

		name = name.toLowerCase();
		name = name.replace("_zh_cn", "");
		name = name.replace("_en_us", "");
		 
		PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();

		CgFormHeadEntity head = cgFormFieldService.getCgFormHeadByTableName(name);
		if (head.getJformType().intValue() == 2) { 
			Resource resources[] = patternResolver.getResources("jeecg/ext/online/ftl/jformonetomany.ftl");
			InputStreamReader inputStreamReader = null;
			if (resources != null && resources.length > 0)
				inputStreamReader = new InputStreamReader(resources[0].getInputStream(), "UTF-8");

			return inputStreamReader;
		}

		Map<String,Object> cgformFtlEntity = cgformFtlService.getCgformFtlByTableName(name);
		if (cgformFtlEntity != null) { 
			String content = cgformFtlEntity.get("ftl_content") != null ? new String((byte[]) cgformFtlEntity.get("ftl_content")) : "";
			content = initFormHtml(content, name);
			 
			return new StringBuilder(content);
		}
		Resource resources[] = patternResolver.getResources("jeecg/ext/online/ftl/jform.ftl");
		InputStreamReader inputStreamReader = null;
		if (resources != null && resources.length > 0)
			inputStreamReader = new InputStreamReader(resources[0].getInputStream(), "UTF-8");

		return inputStreamReader;
	}

	public long getLastModified(Object templateSource) {

		return 0L;
	}

	public Reader getReader(Object templateSource, String encoding) throws IOException {
		if (templateSource instanceof InputStreamReader) {
			BufferedReader br = new BufferedReader((InputStreamReader) templateSource);
			return br;
		} else {
			StringBuilder str = (StringBuilder) templateSource;
			return new StringReader(str.toString());
		}
	}

	public void closeTemplateSource(Object obj) throws IOException {
	}

	private String initFormHtml(String htmlStr, String talbeName) {

		try {
			Map<String,CgFormFieldEntity> fieldMap = cgFormFieldService.getAllCgFormFieldByTableName(talbeName);
			Pattern pattern = Pattern.compile("\\#\\{([a-zA-Z_0-9]+)\\}", 2);
			Matcher matcher = pattern.matcher(htmlStr);

			StringBuffer sb = new StringBuffer();
			String thStr = "";
			String inputStr = "";

			for (boolean result = matcher.find(); result; result = matcher.find()) {
				thStr = matcher.group(1);

				inputStr = "";
				if (fieldMap.get(thStr) != null) {
					inputStr = FormHtmlUtil.getFormHTML(fieldMap.get(thStr));
					inputStr = (new StringBuilder(String.valueOf(inputStr))).append("<span class=\"Validform_checktip\">&nbsp;</span>").toString();
				}
				matcher.appendReplacement(sb, inputStr);
			}

			matcher.appendTail(sb);
			htmlStr = sb.toString();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return htmlStr;
	}
  
	@Autowired
	private CgformFtlServiceI cgformFtlService;

	@Autowired
	private CgFormFieldServiceI cgFormFieldService;

	public CgformFtlServiceI getCgformFtlService() {
		return cgformFtlService;
	}

	public void setCgformFtlService(CgformFtlServiceI cgformFtlService) {
		this.cgformFtlService = cgformFtlService;
	}

	public CgFormFieldServiceI getCgFormFieldService() {
		return cgFormFieldService;
	}

	public void setCgFormFieldService(CgFormFieldServiceI cgFormFieldService) {
		this.cgFormFieldService = cgFormFieldService;
	}
}
