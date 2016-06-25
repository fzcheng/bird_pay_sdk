package com.cheyooh.tools.cfg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cheyooh.tools.log.Logger;
import com.cheyooh.tools.utils.IOUtils;

public class EnvTools {
	private static Logger logger = new Logger(EnvTools.class);
	
	/**
	 * 字符串中的环境变量替换
	 * @param string_content
	 * @return
	 */
	public static String loadEnvContent(String string_content) {
		return getMatchValueInContent(string_content, "\\$\\{(.*?)\\}") ;
	}
	
	/**
	 * @param srcfile   文件存在的绝对路径
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public static String loadEnvContent(String srcfile, String charset) throws Exception {
		try {
			String content = IOUtils.getFileContent(srcfile, charset);
			return loadEnvContent(content);
		} catch (Exception ex) {
			logger.error("文件【" + srcfile + "】处理失败...", ex);
			throw ex;
		}
	}
	
	/**
	 * 对文档中符合条件的变量进行值替换
	 * 
	 * @param content
	 * @param mstring
	 * @return
	 */
	private static String getMatchValueInContent(String content, String mstring) {
		Pattern pattern = Pattern.compile(mstring);
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			String fg = matcher.group(1);
			content = content.replaceAll("\\$\\{"+fg+"\\}", EnvUtil.getValue(fg));
		}
		return content;
	}
}
