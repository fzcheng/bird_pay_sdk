package com.cheyooh.tools.watch;

import java.util.ArrayList;
import java.util.List;

public class FileWatchObject {
	//监控的文件名称
	private String fileName;
	
	//监控文件的最后更新时间
	private long fileLastModified;
	
	//读取监控文件后存储的对象
	private Object fileStoreObject;
	
	//监控文件关联的文件信息(如果关联文件有变化,主监控对象也需要更新)
	private List<FileWatchObject> relativeFileWatchList = new ArrayList<FileWatchObject>();
	
	//监控对象检查间隔
	private long checkInterval = 10*1000L;
	public long getCheckInterval() {
		return checkInterval;
	}

	public void setCheckInterval(long checkInterval) {
		this.checkInterval = checkInterval;
	}

	//变更后处理的线程类
	private FileWatchThread watchThread;
	public FileWatchThread getWatchThread() {
		return watchThread;
	}

	public void setWatchThread(FileWatchThread watchThread) {
		this.watchThread = watchThread;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getFileLastModified() {
		return fileLastModified;
	}

	public void setFileLastModified(long fileLastModified) {
		this.fileLastModified = fileLastModified;
	}

	public Object getFileStoreObject() {
		return fileStoreObject;
	}

	public void setFileStoreObject(Object fileStoreObject) {
		this.fileStoreObject = fileStoreObject;
	}

	public List<FileWatchObject> getRelativeFileWatchList() {
		return relativeFileWatchList;
	}

	public void setRelativeFileWatchList(List<FileWatchObject> relativeFileWatchList) {
		this.relativeFileWatchList = relativeFileWatchList;
	}
}
