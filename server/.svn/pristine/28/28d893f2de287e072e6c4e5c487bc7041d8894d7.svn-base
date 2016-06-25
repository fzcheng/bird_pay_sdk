package com.cheyooh.service.framework.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

import com.cheyooh.service.framework.idata.Cmd;
import com.cheyooh.service.framework.utils.ServiceScanner;

public class ServiceManager {
	 //TODO 避免引用工程大量修订，直接在此应用修改
	 private ServiceManagerCenter center = ServiceManagerCenter.getInstance();
	 private static ServiceManager sm=new ServiceManager();
	 public static ServiceManager getInstance(){
		 return sm;
	 }
	 
	 private Map<String, Class<Service<Cmd>>> hServices = new ConcurrentHashMap<String, Class<Service<Cmd>>>();	 	 
	 private List<ServiceFinder> sfs=new ArrayList<ServiceFinder>();	 
	 private ServiceListener serviceListener;
	 
	 private boolean autoLoaded=false;
	 private ServiceManager(){		
	 }
	 
	 public void registServiceFinder(ServiceFinder sf){
		 sfs.add(sf);
		 center.registServiceFinder(sf);
	 }
	 
	 public void clearServiceFinders(){
		 sfs.clear();
	 }
	 
	 private synchronized void innerScanServices(){
		 if(!autoLoaded){
			 doScanServices();
		 }
	 }
	 
	 public synchronized void doScanServices() {
		 Map<String,Class<Service<Cmd>>> ss=ServiceScanner.getAllServices();	
		 hServices.putAll(ss);
		 autoLoaded=true;
	 }
	 
	 public List<String> getServieNames(){
		 List<String> names=new ArrayList<String>();
		 names.addAll(hServices.keySet());
		 Collections.sort(names);
		 return names;
	 }
	 
	 public Service<Cmd> findService(String name){
		 if(!autoLoaded){
			 innerScanServices();
		 }
		 
		 Class<Service<Cmd>> csc=null;
		 if(StringUtils.isNotEmpty(name)){
			 name=name.substring(0, 1).toUpperCase() + name.substring(1);
			
			 csc=name==null?null:hServices.get(name);		 
			 if(csc==null){
				 for(ServiceFinder sf:sfs){
					 csc=sf.findServiceClass(name);
					 if(csc!=null){
						 hServices.put(name, csc);
						 break;
					 }
				 }	
			 }
		 }
		 if(csc==null){
			 return new NoneService();
		 }	 
		 
		 try {
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
		center.setServiceListener(serviceListener);
	}	  
}
