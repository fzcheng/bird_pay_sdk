package dalgenerator;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PhpMain {
	public static void main(String[] args)throws Exception {
		String root="../../cs-dal-mappers/trunk";
		String rootFrom   =root+"/src/main/java/com/cheyooh/service/dal/db";
		String rootXml    =root+"/src/main/resources/com/cheyooh/service/dal/db";
		String rootMybatis=root+"/appcfg/mybatis";
		
		String rootPhpTo  ="../../cs-service-08cms/trunk/webroot";
		String[] phpDbs   =new String[]{"cms08","bbs","user","misc","pub"};
		
		String rootJavaTo =root+"/src/main/java/com/cheyooh/rpc/dal";
		String javaServiceMapping=root+"/src/main/java/com/cheyooh/rpc/dal/RpcMapping.java";
		 
		File rootDir=new File(rootFrom);
		
		createJavaFiles(rootDir,rootJavaTo,rootXml,rootMybatis);
		 
		createPhpFiles(rootDir,rootPhpTo,phpDbs);		
			
		createPhpShortMappers(rootPhpTo);
		
		new JavaRpcMappingGenerator(rootJavaTo,javaServiceMapping).autoComplete();
	}
	
	
	 
	private static void createJavaFiles(File rootDir,String rootJavaTo,String rootXml,String rootMybatis)throws Exception {
		for(File fdb:rootDir.listFiles()){
			if(fdb.isDirectory()){
				_createJavaFiles(fdb,rootJavaTo,rootXml,rootMybatis);
			}
		}	
	}	
	
	private static void _createJavaFiles(File fdb, String rootTo,String rootXml,String rootMybatis)throws Exception {
		info("Create java files for db: "+fdb.getName()+", path: "+fdb.getAbsolutePath());
		for(File d:fdb.listFiles()){
			String db=d.getParentFile().getName();
			String dbUpper=db.substring(0,1).toUpperCase()+db.substring(1);
			if(d.isDirectory()){
				if(d.getName().equals("dao")){
					//创建DDQ接口
					Map<String,Object> vars=new HashMap<String,Object>();
					vars.put("db",db);
					vars.put("dbUpper",dbUpper);					 				
					String tpl=PhpGenerator.getTemplate("/dalgenerator/ddq_dao.tpl");
					for(String v:vars.keySet()){
						String key="#\\{"+v+"\\}";			
						tpl=tpl.replaceAll(key,vars.get(v).toString());
					}
					String ddq_file=d.getAbsolutePath()+"/"+dbUpper+"DDQMapper.java";
					PhpGenerator.write(ddq_file, tpl);
					 
					String ddq_xml=rootMybatis+"/xmlmap/"+dbUpper+"DDQMapper.xml";
					if(new File(ddq_xml).exists()==false){
						tpl=PhpGenerator.getTemplate("/dalgenerator/ddq_xml.tpl");
						for(String v:vars.keySet()){
							String key="#\\{"+v+"\\}";			
							tpl=tpl.replaceAll(key,vars.get(v).toString());
						}
						PhpGenerator.write(ddq_xml, tpl);
					}
					for(File f:d.listFiles()){
						if(f.getName().endsWith(".java")){
							info("Process dao "+f.getName()+" to: ");
							PhpGenerator php=new JavaMapperImplGenerator();
							php.createPhpFiles(f.getAbsolutePath(),rootTo);
						}
					}
				}
			}
		}
	}
	
	private static void createPhpFiles(File rootDir,String rootPhpTo,String[] dbs)throws Exception {
		for(File fdb:rootDir.listFiles()){
			if(fdb.isDirectory()){
				_createPhpFiles(fdb,rootPhpTo,dbs);
			}
		}	
	}
	
	private static void createPhpShortMappers(String rootPhp)throws Exception {
		Set<String> mappers=new HashSet<String>();
		
		File rootDir=new File(rootPhp+"/dal");
		for(File fdb:rootDir.listFiles()){
			String db=fdb.getName();
			if(fdb.isDirectory() 
					&& db.equalsIgnoreCase("common")==false 
					&& db.equalsIgnoreCase("mappers")==false){
				File daoDir=new File(fdb.getAbsolutePath()+"/dao");
				
				File entityDir=new File(fdb.getAbsolutePath()+"/entity");
				for(File df:daoDir.listFiles()){
					String fname=df.getName();
					String short_class_name=fname.substring(0,fname.length()-4); //remove .php
					String full_dao_prefix   ="dal_"+db+"_dao_";
					String full_entity_prefix="dal_"+db+"_entity_";
					
					String entityName=short_class_name.substring(0,short_class_name.length()-6); //remove Mapper
					
					File phpEntity=new File(entityDir.getAbsolutePath()+"/"+entityName+".php");
					File phpEntityKey=new File(entityDir.getAbsolutePath()+"/"+entityName+"Key.php");
					File phpEntityExample=new File(entityDir.getAbsolutePath()+"/"+entityName+"Example.php");
					File phpEntityWithBLOBS=new File(entityDir.getAbsolutePath()+"/"+entityName+"WithBLOBs.php");
					
					String targetfile=rootDir.getAbsolutePath()+"/mappers/"+fname;
					if(!mappers.contains(targetfile)){
						StringBuffer body=new StringBuffer();
						body.append("<?php \r\n");
						//body.append("require_once dirname(__FILE__).'/../../dal/"+db+"/dao/"+fname+"'; \r\n\r\n");
						
						body.append("if(!class_exists('"+short_class_name+"')){ \r\n");
						body.append("  class "+short_class_name).append(" extends ").append(full_dao_prefix+short_class_name).append("{} \r\n\r\n");
						
						if(phpEntity.exists()){
							body.append("  class ").append(entityName).append(" extends ").append(full_entity_prefix+entityName).append("{} \r\n\r\n");
						}
						if(phpEntityKey.exists()){
							body.append("  class ").append(entityName+"Key").append(" extends ").append(full_entity_prefix+entityName+"Key").append("{} \r\n\r\n");
						}
						if(phpEntityExample.exists()){
							body.append("  class ").append(entityName+"Example").append(" extends ").append(full_entity_prefix+entityName+"Example").append("{} \r\n\r\n");
						}
						if(phpEntityWithBLOBS.exists()){
							body.append("  class ").append(entityName+"WithBLOBs").append(" extends ").append(full_entity_prefix+entityName+"WithBLOBs").append("{} \r\n\r\n");
						}
						 
						
						body.append("}");	
						
						PhpGenerator.write(targetfile, body.toString());
					}else{
						throw new RuntimeException("Mapper file exists: "+targetfile);
					}
				}
			}
		}	
	}
	
	 
	
	private static void _createPhpFiles(File fdb, String rootTo,String[] dbs)throws Exception {
		info("Create php files for db: "+fdb.getName()+", path: "+fdb.getAbsolutePath());
		for(File d:fdb.listFiles()){
			boolean dbg=false;
			if(dbs==null){
				dbg=true;
			}else{
				String db=d.getParentFile().getName();
				for(String s:dbs){
					if(db.equalsIgnoreCase(s)){
						dbg=true;
					}
				}
			}
			if(dbg && d.isDirectory()){
				if(d.getName().equals("dao")){
					for(File f:d.listFiles()){
						if(f.getName().endsWith(".java")){
							info("Process dao "+f.getName()+" to: ");
							PhpGenerator php=new PhpMapperGenerator();
							php.createPhpFiles(f.getAbsolutePath(),rootTo);
						}
					}
				}else if(d.getName().equals("entity")){
					for(File f:d.listFiles()){
						if(f.getName().endsWith("Example.java")){						
							PhpGenerator php=new PhpEntityExampleGenerator();
							php.createPhpFiles(f.getAbsolutePath(),rootTo);
						}else if(f.getName().endsWith(".java")){
							PhpGenerator php=new PhpEntityGenerator();
							php.createPhpFiles(f.getAbsolutePath(),rootTo);
						}
					}
				}else if(d.getName().equals("qform")){
					for(File f:d.listFiles()){
						if(f.getName().endsWith(".java")){
							PhpGenerator php=new PhpEntityGenerator();
							php.createPhpFiles(f.getAbsolutePath(),rootTo);
						}
					}
				}
			}
		}
	}
	
	private static void info(String s){
		
	}
}

