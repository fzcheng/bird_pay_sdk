package com.cheyooh.service.framework.idata;

import java.io.Serializable;
import java.util.Collection;

import org.json.JSONException;
import org.json.JSONObject;

import com.cheyooh.tools.utils.XUtils;
import com.cheyooh.service.framework.utils.annotation.Transfer;
import com.cheyooh.service.framework.utils.annotation.Transient;
import com.cheyooh.service.framework.utils.binding.ClassHelper;

public abstract class ResultContent implements Serializable{ 
	private static final long serialVersionUID = -2413681867619976468L;

	@Transient
	private String tagName;	
	public ResultContent(){}
	
	public ResultContent(String tagName){
		this.tagName=tagName;
	}
	
	public String name(){
		if(this.tagName==null){
			return name(this);
		}else{
			return this.tagName;
		}
	}
	
	private static String name(Object o){
		Transfer t=o.getClass().getAnnotation(Transfer.class);
		if(t!=null){
			return t.name();
		}else{		
			String nm=o.getClass().getName();
			int p=nm.lastIndexOf(".");
			nm=nm.substring(p+1);
			
			p=nm.lastIndexOf("$");
			if(p>0){
				nm=nm.substring(p+1);
			}
			
			return nm;
		}
	}
	
	public Object toJson() throws JSONException{		  
		JSONObject json=new JSONObject();		
		ClassHelper.MetaClass mc=ClassHelper.getMetaClass(this);
		for(String name:mc.getNames()){
			ClassHelper.FGS fgs=mc.getMapppingField(name);
			String value=ClassHelper.getAttribute(this, fgs);
			if(value==null){
				value="";
			}
			json.put(name, value);			 
		}				 
		return json;
	}
	
	public String getXml(){
		return getXml(name());
	}
	@SuppressWarnings("unchecked")
	public String getXml(String nodeName){
		StringBuilder sb=new StringBuilder();
		
		if(nodeName!=null){
			sb.append("<").append(nodeName);
		}
		
		StringBuilder subnodes=new StringBuilder();		
		ClassHelper.MetaClass mc=ClassHelper.getMetaClass(this);
		for(String name:mc.getNames()){
			ClassHelper.FGS fgs=mc.getMapppingField(name);
			 
			Class<?> type=fgs.getField().getType();
			  
			if(ResultContent.class.isAssignableFrom(type)){
				ResultContent rc=(ResultContent)ClassHelper.getObject(this,fgs);
				if(rc!=null){
					subnodes.append("  ").append(rc.getXml(name));
				}
			}else if(Collection.class.isAssignableFrom(type)){
				subnodes.append("  <").append(name).append(">\r\n");
				Collection<Object> cs=(Collection<Object>)ClassHelper.getObject(this,fgs);
				if(cs!=null){
					for(Object c:cs){
						if(c!=null){
							String cn=name(c);
							String v=c.toString();
							if(c instanceof ResultContent){
								v=((ResultContent) c).getXml(null);
							}
							subnodes.append("    <").append(cn).append(v).append(" />\r\n");
						}
					}
				}
				subnodes.append("  </").append(name).append(">\r\n");
			}else{
				String value=ClassHelper.getAttribute(this, fgs);
				if(value==null){					
					if(!fgs.isNullNone()){
						//空值输出空字符串
						value="";
					}
				}else{					
					value=XUtils.escapeXml(value);
				}
				
				if(value!=null){
					sb.append(" ").append(name).append("=\"").append(value).append("\"");
				}
			}							 						
		}
		if(subnodes.length()>0){
			if(nodeName!=null){
				sb.append(">\r\n");
			}			
			sb.append(subnodes.toString());			
			if(nodeName!=null){
				sb.append("</").append(nodeName).append(">\r\n");
			}
		}else{
			if(nodeName!=null){
				sb.append("/>\r\n");
			}
		}
		return sb.toString();
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
}
