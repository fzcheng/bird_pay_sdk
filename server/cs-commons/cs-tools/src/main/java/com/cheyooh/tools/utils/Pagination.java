package com.cheyooh.tools.utils;

import java.util.Collections;
import java.util.List;

/**
 * @author zzg.zhou@mbook.com.cn {MSN:zzgzhou@hotmail.com, QQ:11039850}
 *  
 */
public class Pagination<T> {
	/*
	 * 总记录数
	 */
	private int total;
	
	/**
	 * 结果列表
	 */
	private List<T> list = Collections.emptyList();
	
	/**
	 * 偏移量
	 */
	private int offset;
	
	/**
	 * 需获取记录的条数(等于页面大小)
	 */
	private int limit;
	
	/**
	 * 页面号(0表示第1页)
	 */
	private int pageIndex;
	
	public Pagination() {
		this(0, 20);
	}
	
	public Pagination(int pageIndex) {
		this(pageIndex, 20);
	}
	
	public Pagination(int pageIndex, int limit) {
		setPageIndex(pageIndex);
		setLimit(limit);
	}	 

	public void setPageIndex(int page) {
		if (page < 0) {
			page = 0;
		}
		this.pageIndex = page;
		onInit();
	}
	
	private void setLimit(int limit) {
		if (limit < 1) {
			limit = 20;
		}

/** 放宽限制		
		if(limit>1000){
			limit=1000;
		}
*/		
		this.limit = limit;
		
		onInit();
	}
	
	private void onInit() {
		offset = pageIndex * limit;
	}
	 
 	
	public int getTotalPages(){
		int d=getTotal();
		int ret=(int)(d/limit);
		if (d % limit > 0) {
			ret ++;
		}
		return ret;
	}
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;		 
	}

	public int getPagesize() {
		return limit;
	}

	public List<T> getList() {
		return list;
	}
	
	public void setList(List<T> list) {
		this.list = list;		 
	}

	public int getOffset() {
		return offset;
	}

	public int getLimit() {
		return limit;
	}

	public int getPageIndex() {
		return pageIndex;
	}
		 
}
