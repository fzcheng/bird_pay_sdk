//package com.cheyooh.tools.cfg;
//
//import org.apache.commons.configuration.PropertiesConfiguration;
//import com.cheyooh.tools.log.Logger;
//
//public class CfgMonitor extends CfgWatchdog {    
//    private static Logger logger = new Logger(CfgMonitor.class);
//    static String FLAG_WATCH = "IsWatch";
//      
//    public CfgMonitor() {
//        super();
//    }
//    
//    @Override
//    protected void doOnChange(CfgInfo cfg) {
//        logger.debug("CfgMonitor.doOnChange ......");
//        //重新读取一次配置文件(无监听的时候无法了解文件变更)
//        PropertiesConfiguration realcfg = CfgUtil.getCfgFile(cfg.getClazz(), cfg.getFilename());
//        
//        //默认监控配置文件,主动设置IsWatch=0不监控
//        int isWatch = realcfg.getInt(FLAG_WATCH,1);
//             
//        if(isWatch ==1){//文件有变化并且需要监控时刷新
//            logger.debug("real modify [ "+cfg.getFilename()+"  ] ,loading..");
//            cfg.getCfgdata().setCfg(realcfg);
//        }else{
//            logger.debug("file modify [ "+cfg.getFilename()+"  ],no load  ..");
//        }
//    }
//}
