package com.cheyooh.service.dal.tool;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zzg.zhou@mbook.com.cn {MSN:zzgzhou@hotmail.com, QQ:11039850}
 * 
 */
public class XmlMapperGenerator {
	
	public void write(String xmlmapDirRoot,String toMappersDir)throws Exception{
		Map<String,List<String>> h=new LinkedHashMap<String,List<String>>();
		
		File root=new File(xmlmapDirRoot);
		for(File d1:root.listFiles()){	
			if(d1.isDirectory())for(File d2:d1.listFiles()){
				if(d2.isDirectory()&& d2.getName().equals("xmlmap")){
						String mapfile="mappers_"+d1.getName()+".xml";
						List<String> lfs=new ArrayList<String>();
						h.put(mapfile,lfs);
						for(File xml:d2.listFiles()){
							if(xml.getName().endsWith(".xml")){
								String full_path=xml.getAbsolutePath().replaceAll("\\\\","/");
								int p=full_path.indexOf("/resources/");
								String xml_path=full_path.substring(p+"resources".length()+2);							
								lfs.add(xml_path);
							}
						}
					 
				}
			}
		}
		
		for(String key:h.keySet()){
			List<String> mappers=h.get(key);
			
			System.out.println("Write mapper file("+mappers.size()+"): "+key);
						
			StringBuilder sb=new StringBuilder();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?> \r\n");
			sb.append("\r\n<mappers>	");
			for(String m:mappers){
				sb.append("\r\n  <mapper resource=\"").append(m).append("\"/>");
			}
			sb.append("\r\n</mappers>	");
			File f=new File(toMappersDir+"/"+key);
			FileOutputStream fos=new FileOutputStream(f);
			try{
				fos.write(sb.toString().getBytes("utf-8"));
			}finally{
				fos.close();
			}
		}
	}
	
	public static void main(String[] args)throws Exception{
		String root="src/main/resources//com/cheyooh/service/dal/db";
		String mapper_dir="appcfg/mybatis";
		XmlMapperGenerator xmg=new XmlMapperGenerator();
		xmg.write(root,mapper_dir);
	}
}
