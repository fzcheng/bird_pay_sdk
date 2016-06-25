package com.cheyooh.service.framework.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

import com.cheyooh.service.framework.idata.Cmd;
import com.cheyooh.service.framework.utils.JspServiceScanner;
import com.cheyooh.service.framework.utils.ServiceScanner;

public class ServiceManagerCenter {
	 private static ServiceManagerCenter center=new ServiceManagerCenter();
	 public static ServiceManagerCenter getInstance(){
		 return center;
	 }
	 
	 /** 基于contextpath管理的服务中心 */
	 /** class服务集合 */
	 private Map<String,Map<String, Class<Service<Cmd>>>> serviceCenter = new ConcurrentHashMap<String, Map<String,Class<Service<Cmd>>>>(); 
	 
	 /**jsp服务集合*/
	 private Map<String,Map<String, Class<Service<Cmd>>>> jspServiceCenter = new ConcurrentHashMap<String, Map<String, Class<Service<Cmd>>>>();
	 private Map<String,Map<String, String>> jspServicePathCenter = new ConcurrentHashMap<String, Map<String,String>>();
	 
	 private List<ServiceFinder> sfs=new ArrayList<ServiceFinder>();	 
	 private ServiceListener serviceListener;

	 private ServiceManagerCenter(){		
	 }
	 
	 public void registServiceFinder(ServiceFinder sf){
		 sfs.add(sf);
	 }
	 
	 public void clearServiceFinders(){
		 sfs.clear();
	 }
	 
	 @SuppressWarnings("unchecked")
	public void doScanJspServices(String servicepath,String rootpath,String servicepackage){
		 // jsp服务每次扫描(实现服务动态加载)
		 Map<String,String> jspServicesMap = JspServiceScanner.getAllJspServices(rootpath, servicepackage);		 
		 Map<String, Class<Service<Cmd>>> tmpMap = new ConcurrentHashMap<String, Class<Service<Cmd>>>();
		 for(Entry<String, String> entry :  jspServicesMap.entrySet()){
			 JspService service = new JspService();
			 service.setJspServicePath(entry.getValue());	 
			 Class<?> cls = service.getClass();
			 Class<Service<Cmd>> csc=(Class<Service<Cmd>>)cls;
			 tmpMap.put(entry.getKey(), csc);
		 }
		 jspServiceCenter.put(servicepath, tmpMap);
		 jspServicePathCenter.put(servicepath, jspServicesMap);
	 }
	 
	public void doScanServices(String servicepath, String rootpath,String servicepackage) {
		// class类服务只扫描一次
		Map<String, Class<Service<Cmd>>> classServiceMap = serviceCenter
				.get(servicepath);
		if (classServiceMap == null || classServiceMap.size() == 0) {
			//设定一个同步分析对象指向servicepath值
			Object synObj = servicepath;
			synchronized (synObj) {
				if (serviceCenter.size() == 0) {
					Map<String, Class<Service<Cmd>>> ss = ServiceScanner.getAllServices();
					serviceCenter.put(servicepath, ss);
				}
			}
		}
		// jsp页面服务每次扫描
		doScanJspServices(servicepath, rootpath, servicepackage);
	}
	 
	 public List<String> getServieNames(String servicepath){
		 List<String> names=new ArrayList<String>();
		 if(serviceCenter.containsKey(servicepath)) {
			 Map<String,Class<Service<Cmd>>> ss  = new ConcurrentHashMap<String, Class<Service<Cmd>>>();
			 Map<String,Class<Service<Cmd>>> classServices = serviceCenter.get(servicepath);
			 Map<String,Class<Service<Cmd>>> jspServices = jspServiceCenter.get(servicepath); 
			 ss.putAll(jspServices);
			 ss.putAll(classServices);
			 
			 if(ss != null && ss.size() > 0) {
				 names.addAll(ss.keySet());
				 Collections.sort(names);
			 }
		 }
		 return names;
	 }
	 
	public Service<Cmd> findService(String name, String servicepath,String rootpath, String servicepackage) {
		//服务查找前刷新信息
		doScanServices(servicepath, rootpath, servicepackage);

		Class<Service<Cmd>> csc = null;
		// 优先选择jsp服务
		Map<String, Class<Service<Cmd>>> jspServices = jspServiceCenter.get(servicepath);
		Map<String, Class<Service<Cmd>>> hServices = serviceCenter.get(servicepath);

		// jsp服务不要求首字母大写
		try {
			if (StringUtils.isNotEmpty(name)) {
				String srcName = name;
				String bigName = name.substring(0, 1).toUpperCase()+ name.substring(1);

				// 优先jsp服务
				if (jspServices.containsKey(srcName)|| jspServices.containsKey(bigName)) {
					Class<Service<Cmd>> jspCsc = jspServices.containsKey(srcName) ? jspServices	.get(srcName) : jspServices.get(bigName);
					if (jspCsc != null){
						Service<Cmd> jspService = jspCsc.newInstance();
						if(jspService instanceof JspService) {
							Map<String,String> sMap = jspServicePathCenter.get(servicepath);
							if(sMap != null && sMap.size() >0){
								String jspPath = sMap.get(srcName) ;
								if(StringUtils.isNotEmpty(jspPath)) {
									((JspService) jspService).setJspServicePath(jspPath);
									return jspService;
								}
							}
						}
					}
				}

				csc = bigName == null ? null : hServices.get(bigName);
				if (csc == null) {
					for (ServiceFinder sf : sfs) {
						csc = sf.findServiceClass(name);
						if (csc != null) {
							hServices.put(name, csc);
							serviceCenter.put(servicepath, hServices);
							break;
						}
					}
				}
			}
			if (csc == null) {
				return new NoneService();
			}

			return csc.newInstance();
		} catch (Exception e) {
			throw new ServiceNotFoundException(name, e);
		}
	}

	public ServiceListener getServiceListener() {
		return serviceListener;
	}

	public void setServiceListener(ServiceListener serviceListener) {
		this.serviceListener = serviceListener;
	}	  
}
