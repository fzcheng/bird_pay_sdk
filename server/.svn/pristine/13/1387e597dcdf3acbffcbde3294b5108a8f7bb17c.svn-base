package com.cheyooh.service.sdk.tools;

import java.io.File;

import com.alisoft.xplatform.asf.cache.ICacheManager;
import com.alisoft.xplatform.asf.cache.IMemcachedCache;
import com.alisoft.xplatform.asf.cache.memcached.CacheUtil;
import com.alisoft.xplatform.asf.cache.memcached.MemcachedCacheManager;
import com.cheyooh.tools.cfg.CfgPath;
import com.cheyooh.tools.log.Logger;
import com.cheyooh.tools.xml.XmlTools;

/**
 * 缓存管理
 * 
 * @author Com
 * 
 */
public class CacheManager {
	static Logger logger = new Logger(CacheManager.class);

	private static CacheManager cm = new CacheManager();

	private ICacheManager<IMemcachedCache> manager;
	private IMemcachedCache cacheSession;

	private CacheManager() {
		manager = CacheUtil.getCacheManager(IMemcachedCache.class, MemcachedCacheManager.class.getName());
		String xml = CfgPath.getCfgPath("sdk-memcached.xml");
		if(new File(xml).exists()){
			try {
				manager.setLocalConfigFile(xml);
				manager.setLocalConfigDocument(XmlTools.loadXmlFile(xml));
				logger.info("Loaded memcached xml: " + xml);
				
				manager.start();
				cacheSession = manager.getCache("gamesdk_session");
				
			} catch (Exception ex) {
				logger.error("加载memcached配置文件异常: " + xml + ", Exception: " + ex, ex);
			}
		}else{
			throw new RuntimeException("Memcached config file not found: "+xml);
		}
	}

	public static void shutdown() {
		cm.manager.stop();
	}

	/**
	 * 获取值
	 * 
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		return (String) cm.cacheSession.get(key);
	}

	/**
	 * 设置值
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean setValue(String key, String value) {
		cm.cacheSession.put(key, value);
		return true;
	}

	/**
	 * 缓存是否存在
	 * 
	 * @param key
	 * @return
	 */
	public static boolean hasCache(String key) {
		return cm.cacheSession.containsKey(key);
	}

	/**
	 * 移除值
	 * 
	 * @param key
	 */
	public static void removeKey(String key) {
		cm.cacheSession.remove(key);
	}
  
}
