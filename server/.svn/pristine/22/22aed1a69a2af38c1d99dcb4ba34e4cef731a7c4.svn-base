package com.cheyooh.tools.watch;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.cheyooh.tools.utils.XUtils;

public abstract class FileWatchThread extends Thread {
	public static final long DEFAULT_DELAY = 60000L;
	protected long delay = DEFAULT_DELAY;
	boolean interrupted;

	private FileWatchObject watchObject;
	public FileWatchObject getWatchObject() {
		return watchObject;
	}

	public void setWatchObject(FileWatchObject watchObject) {
		this.watchObject = watchObject;
	}

	protected FileWatchThread() {
		setDaemon(true);
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

	public void setInterrupted(boolean interrupted) {
		this.interrupted = interrupted;
	}

	protected abstract void doOnChange(FileWatchObject storeObj);

	protected void checkAndConfigure() {
		check(watchObject.getFileName(), watchObject);
	}

	protected void check(String key, FileWatchObject storeObj) {
		boolean fileExists;
		File file = new File(key);
		try {
			fileExists = file.exists();
		} catch (SecurityException e) {
			interrupted = true;
			return;
		}
		if (fileExists) {
			long l = file.lastModified();
			// 主文件有更新后检测改变，如果主监控文件为变化才检测相关文件
			if (l > storeObj.getFileLastModified()) {
				storeObj.setFileLastModified(l);
				doOnChange(storeObj);
			}else{ //检测辅助文件
				RelativeCheckResult result = checkUpdateMonitorFiles(key, storeObj.getRelativeFileWatchList());
				if(result.isExistChanged()){ //存在变化，触发更新
					storeObj.setRelativeFileWatchList(result.getFilterFileWatchList());
					doOnChange(storeObj);
				}
			}
		}
	}
	
	private  RelativeCheckResult checkUpdateMonitorFiles(String mainFileName,List<FileWatchObject> flist){
		boolean isExistedChanged = false;
		List<FileWatchObject> destList = new ArrayList<FileWatchObject>();
		for(FileWatchObject fobj : flist){
			File xfile = new File(fobj.getFileName());
			if(xfile.exists()){
				long ftime = xfile.lastModified();
				if(ftime > fobj.getFileLastModified()){
					fobj.setFileLastModified(ftime);
					isExistedChanged = true;
				}
			}
			destList.add(fobj);
		}
		
		RelativeCheckResult xresult = new RelativeCheckResult();
		xresult.setExistChanged(isExistedChanged);
		xresult.setFilterFileWatchList(destList);
		
		return xresult;
	}

	public void run() {
		while (!interrupted) {
			XUtils.sleep(delay);
			checkAndConfigure();
		}
	}
}

class RelativeCheckResult {
	private boolean isExistChanged = false;
	private List<FileWatchObject> filterFileWatchList = new ArrayList<FileWatchObject>();
	public boolean isExistChanged() {
		return isExistChanged;
	}
	public void setExistChanged(boolean isExistChanged) {
		this.isExistChanged = isExistChanged;
	}
	public List<FileWatchObject> getFilterFileWatchList() {
		return filterFileWatchList;
	}
	public void setFilterFileWatchList(List<FileWatchObject> filterFileWatchList) {
		this.filterFileWatchList = filterFileWatchList;
	}
}
