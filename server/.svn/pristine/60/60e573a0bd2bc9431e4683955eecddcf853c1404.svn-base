package com.cheyooh.service.framework.utils;

import java.io.File;
import java.io.FileFilter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JspServiceScanner {
	/**
	 * 获取指定路径下的jsp文件服务
	 * @param path           根路径
	 * @param subpkg      子包路径
	 * @return  Map           <文件名,相对subpkg的路径>
	 */
	public static Map<String,String> getAllJspServices(String root,String subpkg) {
		Map<String,String> jspMap = new ConcurrentHashMap<String, String>();
		Map<String,String> jspMapTmp = new HashMap<String, String>();
		getFileMapInPath(root+File.separator+subpkg,subpkg,true,jspMapTmp);	
		jspMap = jspMapTmp;
		return jspMap;
	}
	
	/**
	 * 查找指定目录下的文件信息
	 * @param path	        	文件根路径
	 * @param recursive       是否递归查找
	 * @param storeMap	   存储对象<name-path>
	 */
	private static void getFileMapInPath(String path,String pkgName, final boolean recursive,Map<String, String> storeMap) {
		File dir = new File(path);
		if (!dir.exists() || !dir.isDirectory()) {
			return;
		}

		File[] dirfiles = dir.listFiles(new FileFilter() {
			public boolean accept(File file) {
				return (recursive && file.isDirectory()) || (file.getName().endsWith(".jsp"));
			}
		});

		for (File file : dirfiles) {
			if (file.isDirectory()) {
				getFileMapInPath(file.getAbsolutePath(), pkgName+File.separator+file.getName(),recursive, storeMap);
			} else {
				String className = file.getName().substring(0,file.getName().length() - 4);
				storeMap.put(className, pkgName+File.separator+file.getName());
			}
		}
	}
	
}
