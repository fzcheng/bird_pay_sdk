package jeecg.ext.tools;

import org.jeecgframework.core.util.ResourceUtil;



public class ImageHelper {

	public static String getImageUrl(String path){	 
		String base_url=ResourceUtil.getConfigByName("image.base_url");
		
		String url=path;
		if(!base_url.endsWith("/")){
			base_url+="/";
		}
		
		if(url.startsWith("/")){
			url=url.substring(1);
		}
		url=base_url+url;
			 
		return url;
	}
	
	public static String getIconImagePath(String pkg){
		return getImagePath(pkg,"icon.png");
	}
	private static String getImagePath(String pkg,String name){
		StringBuilder sb=new StringBuilder();
		String[] sv=pkg.split("\\.");
		for(String s:sv){
			if(sb.length()>0){
				sb.append("/");
			}
			sb.append(s);
		}
		sb.append("/").append(name);
		
		return sb.toString();
	}
}
