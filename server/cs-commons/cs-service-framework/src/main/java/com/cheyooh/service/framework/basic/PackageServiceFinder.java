package com.cheyooh.service.framework.basic;

import com.cheyooh.service.framework.idata.Cmd;
import com.cheyooh.tools.log.Logger;
import com.cheyooh.tools.utils.XUtils;
 
@SuppressWarnings("unchecked")
public class PackageServiceFinder implements ServiceFinder{
	static Logger logger = new Logger(PackageServiceFinder.class);  
	private String pkg;
	
	public PackageServiceFinder(String pkg){
		this.pkg=pkg;
	}
	
	public Class<Service<Cmd>> findServiceClass(String name) {
		 Class<Service<Cmd>> csc = null;
		if (name != null && name.length() > 0) {
			String class_name=pkg+"."+name;			
			
			csc = (Class<Service<Cmd>>) XUtils.forName(class_name);
			if(csc!=null){
				logger.info("Loaded service: " + class_name);
			}
			
		}

		return csc;
	}

	

}
