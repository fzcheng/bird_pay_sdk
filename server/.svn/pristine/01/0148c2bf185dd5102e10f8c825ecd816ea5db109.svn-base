package com.cheyooh.tools.watch;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.cheyooh.tools.log.Logger;

/**
 * 监控文件存储队列
 */
public class FileWatchQuence {
	private Logger logger = new Logger(FileWatchQuence.class);
	private ConcurrentHashMap<String, FileWatchObject> watchMap = new ConcurrentHashMap<String, FileWatchObject>();
	private Map<String, Thread> watchThreadMap = new HashMap<String, Thread>();

	public void putQuenceWatch(FileWatchObject wobj) {
		String key = wobj.getFileName();
		watchMap.put(key, wobj);
		
		if (watchThreadMap.containsKey(key)) {
			logger.info("【" + key + "】 is watched ...");
		} else {
			logger.info("【" + key + "】 isnot watched ,new thread watch  ...");
			FileWatchThread watch = wobj.getWatchThread();
			watch.setWatchObject(wobj);
			watch.setDelay(wobj.getCheckInterval());
			watch.start();
			watchThreadMap.put(key, watch);
		}
	}

	public void removeQuenceWatch(String key) {
		watchMap.remove(key);
	}

	public FileWatchObject getQuenceWatch(String key) {
		return watchMap.get(key);
	}

}
