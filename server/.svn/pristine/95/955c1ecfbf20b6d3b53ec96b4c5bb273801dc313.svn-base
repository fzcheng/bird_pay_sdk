package com.cheyooh.service.framework.idata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import com.cheyooh.service.framework.utils.binding.ClassHelper;
import com.cheyooh.tools.utils.XUtils;

public class ResultContentList extends ResultContent{	 
	private static final long serialVersionUID = -6001615828059328991L;
	
	private List<ResultContent> contents=new ArrayList<ResultContent>();
	  
	
	public List<ResultContent> getContents(){
		return contents;
	}
	
	public void clear(){
		contents.clear();				 
	}
	
	public void addContent(ResultContent c){		 	
		contents.add(c);
	}
	
	public Object toJson() throws JSONException{		  
		JSONArray list=new JSONArray();
		for(ResultContent c:contents){
			list.put(c.toJson());
		}				
		return list;
	}
	
	public String getXml(){
		StringBuilder sb=new StringBuilder();
		String tag=this.getTagName();
		if(tag!=null){
			sb.append("<").append(tag);
		 		
			ClassHelper.MetaClass mc=ClassHelper.getMetaClass(this);
			for(String name:mc.getNames()){
				ClassHelper.FGS fgs=mc.getMapppingField(name);
				Class<?> type=fgs.getField().getType();	
				
				//ResultContent 和 Collection 在节点中输出
				if(ResultContent.class.isAssignableFrom(type)){
					 
				}else if(Collection.class.isAssignableFrom(type)){
					
				}else{
					String value=ClassHelper.getAttribute(this, fgs);
					sb.append(" ").append(name).append("=\"").append(XUtils.escapeXml(value)).append("\"");
				}				  
			}
			 
			sb.append(">");
		}
		
		for(ResultContent c:contents){
			if(sb.length()>0){
				sb.append("\r\n");
			}
			sb.append(c.getXml());
		}	
		if(tag!=null){
			sb.append("</").append(tag).append(">");
		}
		return sb.toString();
	}

	public void setContents(List<ResultContent> contents) {
		this.contents = contents;
	}
	
}
