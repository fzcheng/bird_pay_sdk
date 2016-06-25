package com.cheyooh.service.framework.basic;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cheyooh.service.framework.idata.Cmd;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.ResultContent;
import com.cheyooh.service.framework.idata.ResultContentList;

public class _services extends Service<Cmd> {
	private ServiceManagerCenter center = ServiceManagerCenter.getInstance();
	final static Set<String> innerServices=new HashSet<String>(){		 
		private static final long serialVersionUID = 4700697268702629804L;

		{
			add("_services");
			add("noneservice");
		}
	};
	
	protected Result execute(Cmd cmd) {				
		ResultContentList rcl=new ResultContentList();
		rcl.setTagName("services");
		
		/**扫描时刻检查可应用的服务*/
		//String servicepath = cmd.getServiceContext().getRequest().getRequestURL().toString();
		
		//修订根据contextpath处理
		String servicepath = cmd.getServiceContext().getRequest().getRequestURI().toString();
		
		String rootpath = cmd.getServiceContext().getRequest().getServletContext().getRealPath("/");
		// logger.info("扫描请求 :  " + servicepath+", jsp服务根目录 : " +rootpath);	
		center.doScanServices(servicepath,rootpath,"service");	
		
		List<String> names = center.getServieNames(servicepath);
		for(String name:names){
			if(innerServices.contains(name.toLowerCase())==false){
				rcl.addContent(new NamedService(name));
			}
		}
		
		return new Result(rcl);
	}

	@Override
	protected Result verify(Cmd cmd) {		
		return null;
	}
 
	
	public static class NamedService extends ResultContent{ 
		private static final long serialVersionUID = 1L;
		
		private String name;
		
		public NamedService(String name){
			super("service");
			this.name=name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
  
}
