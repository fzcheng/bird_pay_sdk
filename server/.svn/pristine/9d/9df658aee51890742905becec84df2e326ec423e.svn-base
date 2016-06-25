package com.cheyooh.tools.cfg;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class CfgWatchdog extends Thread {
    private static Log logger = LogFactory.getLog(CfgWatchdog.class);
    public static Map<String,CfgInfo> watchArray = new java.util.concurrent.ConcurrentHashMap<String,CfgInfo>();
    
    public static final long DEFAULT_DELAY = 10000L;
    protected long delay;
    boolean interrupted;
 
    protected CfgWatchdog() {
        setDaemon(true);
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }
    
    public void setInterrupted(boolean interrupted) {
        this.interrupted = interrupted;
    }

    protected abstract void doOnChange(CfgInfo cfg);

    protected void checkAndConfigure() {
        Iterator<String> keys = watchArray.keySet().iterator();
        while(keys.hasNext()){
            String key = keys.next();
            CfgInfo value = watchArray.get(key);
            check(key,value);
        }
    }
    
    protected void check(String key, CfgInfo cfg) {
        logger.debug("check [ " + key + "] ... ");
        boolean fileExists;
        File file = new File(cfg.getFilepath() + File.separator + cfg.getFilename());
        try {
            fileExists = file.exists();
        } catch (SecurityException e) {
            logger.warn("Was not allowed to read check file existance, file:[" + cfg.getFilename() + "].");
            interrupted = true;
            return;
        }
        logger.debug("CfgWatchdog.checkAndConfigure ......" + fileExists);
        if (fileExists) {
            long l = file.lastModified();
            if (l > cfg.getLastmodified()) {
                logger.debug("change [ " + key + "]  ... ");
                cfg.setLastmodified(l);
                watchArray.put(key, cfg);
                doOnChange(cfg);
            }
        } 
    }

    public void run() {
        while (!interrupted) {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
            }
            logger.debug("CfgWatchdog.running ......");
            checkAndConfigure();
        }
        logger.debug("CfgWatchdog.exit ......");
    }
}
