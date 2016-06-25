package com.cheyooh.service.sdk.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SignParameter {
	private static Set<String> ignores=new HashSet<String>();
	static{
		ignores.add("checksign");
		ignores.add("fmt");
		ignores.add("debug");
	}
	
	private Map<String,String[]> hData=new HashMap<String,String[]>();
	
	public void addParameters(List<String[]> nvs){
		for(String[] nv:nvs){
			addParameter(nv[0],nv[1]);
		}
	}
	
	public void addParameters(Map<String,String[]> map){
		for(String name:map.keySet()){
			String[] vs=map.get(name);
			for(String v:vs){
				addParameter(name,v);
			}
		}
	}
	
	public void removeParameter(String name){
		hData.remove(name.toLowerCase());
	}
	
	public void addParameter(String name,String value){
		if(value!=null){
			name=name.toLowerCase();
			
			String[] vs=hData.get(name);
			if(vs==null){
				vs=new String[]{value};
				
				hData.put(name,vs);
			}else{
				String[] nvs=new String[vs.length+1];
				System.arraycopy(vs, 0,nvs,0,vs.length);
				nvs[vs.length]=value;
				
				hData.put(name,nvs);
			}
		}
	}
	public String getSignString(){
		return getSignString("&");
	}
	public String getSignString(String split){
		StringBuilder sb=new StringBuilder();
		
		List<String> names=new ArrayList<String>();
		names.addAll(hData.keySet());
		Collections.sort(names);
		
		for(String name:names){
			if(!ignores.contains(name.toLowerCase())){
				String[] vs=hData.get(name);
				Arrays.sort(vs);
				for(String v:vs){
					if(sb.length()>0){
						sb.append(split);
					}
					sb.append(v);
				}
			}
		}
		
		return sb.toString();
	}
	
}
