package dalgenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JavaRpcMappingGenerator {
	private String rootJavaTo;
	private String javaServiceMapping;
	
	public JavaRpcMappingGenerator(String rootJavaTo, String javaServiceMapping) {
		this.rootJavaTo=rootJavaTo;
		this.javaServiceMapping=javaServiceMapping;
	}

	public void autoComplete()throws Exception {
		File rootDir=new File(rootJavaTo);
	 	
		Map<String,String> mappers=new LinkedHashMap<String,String>();
		List<String> imports=new ArrayList<String>();
		for(File fdb:rootDir.listFiles()){
			if(fdb.isDirectory()){
				String db=fdb.getName();				
				for(File f:fdb.listFiles()){
					String n=f.getName();
					if(n.endsWith("Impl.java")){
						imports.add("com.cheyooh.rpc.dal."+db+"."+n.substring(0,n.length()-5));						
						mappers.put(db+":"+n.substring(0,n.length()-9),n.substring(0,n.length()-5)+".class");
					}
				}
				
			}
		}	
		
		Map<String,Object> vars=new LinkedHashMap<String,Object>();
		StringBuilder sbimport=new StringBuilder();
		for(String i:imports){
			sbimport.append("import ").append(i).append(";\r\n");
		}
		vars.put("imports", sbimport);
		
		StringBuilder sbmappings=new StringBuilder();
		for(String i:mappers.keySet()){
			sbmappings.append("\r\n		mappers.put(\""+i+"\", "+mappers.get(i)).append(");");
		}
		vars.put("mappings", sbmappings);
		
		String tpl=PhpGenerator.getTemplate("/dalgenerator/mapping.tpl");
		for(String v:vars.keySet()){
			String key="#\\{"+v+"\\}";
			tpl=tpl.replaceAll(key,vars.get(v).toString());
		}
		
		System.out.println("Write file:  "+javaServiceMapping);
		FileOutputStream fos=new FileOutputStream(javaServiceMapping);
		fos.write(tpl.getBytes("utf-8"));
		fos.close();
		
	}

}
