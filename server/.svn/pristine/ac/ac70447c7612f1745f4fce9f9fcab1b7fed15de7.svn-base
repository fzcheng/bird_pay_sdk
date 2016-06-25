package com.cheyooh.service.framework.idata;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import com.cheyooh.tools.utils.Pagination;


/**
 * @author zzg.zhou@mbook.com.cn {MSN:zzgzhou@hotmail.com, QQ:11039850}
 *  
 */
public class ResultContentPage extends ResultContent{	 
	private static final long serialVersionUID = -6001615828059328991L;
	
	private int total;
	
	private int currentPage;
	
	private int totalPage;
	
	private List<ResultContent> contents=new ArrayList<ResultContent>();
	 
	public ResultContentPage(){		
		
	}
 	
	public ResultContentPage(Pagination<?> p){
		setPagination(p);
	}
	
	public ResultContentPage(String tagName){
		super(tagName);
	}
	
	public ResultContentPage(String tagName,Pagination<?> p){
		super(tagName);
		setPagination(p);
	}
	
	@SuppressWarnings("unchecked")
	private void setPagination(Pagination<?> p){
		this.total=p.getTotal();
		this.currentPage=p.getPageIndex();
		this.totalPage=p.getTotalPages();
	 
		List<?> list=p.getList();
		if(list!=null && list.size()>0){
			Object o=list.get(0);
			if(o instanceof ResultContent){
				addContents((List<ResultContent>)list);
			}
		}
	}
	
	public List<ResultContent> getContents(){
		return contents;
	}
	
	public void clear(){
		contents.clear();
	 
	}
	
	public void addContents(List<ResultContent> cs){	 	
		contents.addAll(cs);
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
		sb.append("<").append(name());
		sb.append(" total=\"").append(getTotal()).append("\"");
		sb.append(" currentPage=\"").append(getCurrentPage()).append("\"");
		sb.append(" totalPage=\"").append(getTotalPage()).append("\"");
		sb.append(">\r\n");
		for(ResultContent c:contents){
			if(sb.length()>0){
				sb.append("\r\n");
			}
			sb.append(c.getXml());
		}
		sb.append("</").append(name()).append(">\r\n");
		return sb.toString();		
	} 

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}


	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}
	
}
