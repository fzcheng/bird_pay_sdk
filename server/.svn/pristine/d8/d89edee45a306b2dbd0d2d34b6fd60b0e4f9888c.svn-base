package com.cheyooh.service.framework.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zzg.zhou@mbook.com.cn {MSN:zzgzhou@hotmail.com, QQ:11039850}
 *  
 *  基于模板产生一个Service工程
 */
public class ServiceProjectTemplate { 
	private String projectName;
	private String packageName;
	
	private File source=null;
	private File target=null;
	
	private List<String[][]> cas=new ArrayList<String[][]>();
	 
	public ServiceProjectTemplate(String projectName){
		this.projectName=projectName;
		this.packageName=projectName.replace("-", ".");
		source=new File("template/cs-service-__SERVICE_NAME");
		target=new File("target/projects/cs-service-"+projectName);
		
		target.mkdirs();
	}
	
	public void createProjectFiles(){
		System.out.println("Creating service project: "+projectName);
		
		copyFiles(source,target);
		
		for(String[][] tpls:cas){
			createCmdFiles(tpls);
			createActionFiles(tpls);
		}				
		
		System.out.println("Finished: "+target.getAbsolutePath());
	}
	
	private void createCmdFiles(String[][] tpls){
		String cmdName=null;
		for(String[] tp:tpls){
			if(tp[0].equals("__CMD_NAME")){
				cmdName=tp[1];
				break;
			}
		}
		trans(new File("template/CmdTemplate.java"),new File("target/projects/cs-service-"+projectName+"/src/main/java/com/cheyooh/service/"+packageName+"/idata/"+cmdName+".java"),tpls);
	}
	
	private void createActionFiles(String[][] tpls){
		String actionName=null;
		for(String[] tp:tpls){
			if(tp[0].equals("__ACTION_NAME")){
				actionName=tp[1];
				break;
			}
		}
		trans(new File("template/ActionTemplate.java"),new File("target/projects/cs-service-"+projectName+"/src/main/java/com/cheyooh/service/"+packageName+"/action/"+actionName+".java"),tpls);
	}
	
	private void copyFiles(File from,File to){
		if(from.isDirectory() && from.getName().toLowerCase().equals(".svn")==false){
			for(File f:from.listFiles()){
				if(f.getName().toLowerCase().equals(".svn")){
					continue;
				}
				
				String to_name=f.getName();
				to_name=to_name.replaceAll("__PROJECT_NAME", projectName);
				to_name=to_name.replaceAll("__SERVICE_NAME", packageName.replace(".", "/"));
				
				File t=new File(to,to_name);
				if(f.isDirectory()){						
					t.mkdirs();
					System.out.println("Create dir: "+t.getAbsolutePath());
				}
				copyFiles(f,t);
			}
		}else if(from.isFile()){
			trans(from,to,new String[][]{});
		}
	}
	
	private void trans(File from,File to,String[][] tpls){
		System.out.println("Copy file from: "+from.getAbsolutePath()+" to: "+to.getAbsolutePath());
		try{
			BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(from),"utf-8"));
			BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(to),"utf-8"));
			
			String line=reader.readLine();
			while(line!=null){
				for(String[] tp:tpls){
					line=line.replaceAll(tp[0],tp[1]);
				}
				line=line.replaceAll("__SERVICE_NAME", packageName);
				line=line.replaceAll("__PROJECT_NAME", projectName);
				
				writer.write(line+"\r\n");
				
				line=reader.readLine();
			}
			reader.close();
			writer.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	static void createLoggingProject(){
		//创建一个简单的服务工程
		ServiceProjectTemplate spt=new ServiceProjectTemplate("logging");
		spt.createProjectFiles();
	}
	
	static void createUserProject(){
		String author="zzg.zhou";
		
		//创建一个服务工程,并且产生Cmd和Action模板类
		ServiceProjectTemplate spt=new ServiceProjectTemplate("user");
		spt.cas.add(new String[][]{
				{"__AUTHOR",author},
				{"__CMD_NAME","CmdRegister"},
				{"__CMD_COMMENT","用户注册参数"},
				{"__ACTION_NAME","Register"},
				{"__ACTION_COMMENT","用户注册服务"}				
		});
		
		spt.cas.add(new String[][]{
				{"__AUTHOR",author},
				{"__CMD_NAME","CmdLogin"},
				{"__CMD_COMMENT","用户登录参数"},
				{"__ACTION_NAME","Login"},
				{"__ACTION_COMMENT","用户登录服务"}				
		});
		
		spt.createProjectFiles();
	}
	
	static void createPublicProject(){
		ServiceProjectTemplate spt=new ServiceProjectTemplate("pub");
		
		spt.createProjectFiles();
	}
	
	static void createSmsProject(){
		ServiceProjectTemplate spt=new ServiceProjectTemplate("sms");
		spt.createProjectFiles();
	}
	
	static void createOrderProject(){
		ServiceProjectTemplate spt=new ServiceProjectTemplate("order");
		spt.createProjectFiles();
	}
	
	static void createIpResourceProject(){
		//创建一个简单的IpResources服务工程
		ServiceProjectTemplate spt=new ServiceProjectTemplate("iprs");
		spt.createProjectFiles();
	}
	
	static void createEDrivingProject(){
		//创建一个简单的e代驾服务工程
		ServiceProjectTemplate spt=new ServiceProjectTemplate("edriving");
		spt.createProjectFiles();
	}
	
	public static void main(String[] args) {		
		// createLoggingProject();
		
		// createUserProject();
		
		// createPublicProject();
		
		// createSmsProject();
		
		//createOrderProject();
		
		//createIpResourceProject();
		//当如果你需要添加的项目名称需要两个单词以上如edriving-test，那么两个单词之间请使用中横线分隔，
		createEDrivingProject();
	}

}

