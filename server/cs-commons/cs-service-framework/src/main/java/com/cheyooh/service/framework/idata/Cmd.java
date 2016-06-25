package com.cheyooh.service.framework.idata;

import java.io.Serializable;

import com.cheyooh.service.framework.basic.ServiceContext;
import com.cheyooh.service.framework.utils.annotation.Mapping;

public class Cmd implements Serializable {	 
	private static final long serialVersionUID = -8356606819189437585L;

	@Mapping(name="cmd",alias="m")
	private String cmd;
	
	/**
	 * 页面编号,从0开始
	 */
	private int pageIndex;
	
	/**
	 * 页面尺寸,0 使用缺省值
	 */
	private int pageSize=20;
	
	/**
	 * 输出格式, json或xml
	 */
	private String fmt;
	
	private String requestHost;	
	
	private transient ServiceContext serviceContext;
	public ServiceContext getServiceContext() {
		return serviceContext;
	}
	public void setServiceContext(ServiceContext serviceContext) {
		this.serviceContext = serviceContext;
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public String getFmt() {
		return fmt;
	}
	public void setFmt(String fmt) {
		this.fmt = fmt;
	}
	public String getRequestHost() {
		return requestHost;
	}
	public void setRequestHost(String requestHost) {
		this.requestHost = requestHost;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}	 
}
