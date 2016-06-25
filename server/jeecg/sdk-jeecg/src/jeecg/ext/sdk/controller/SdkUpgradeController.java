package jeecg.ext.sdk.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.SdkGame;
import jeecg.ext.sdk.entity.SdkGameChannel;
import jeecg.ext.sdk.entity.SdkGameUpdate;
import jeecg.ext.sdk.entity.SdkGameUpgrade;
import jeecg.ext.sdk.entity.SdkPlan;
import jeecg.ext.sdk.entity.SdkUpgrade;
import jeecg.ext.tools.CryptoUtil;
import jeecg.ext.tools.TagUtilExt;
import jeecg.system.service.SystemService;
import net.erdfelt.android.apk.AndroidApk;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.PinyinUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sdkUpgrade")
public class SdkUpgradeController  extends BaseController{

	static Log logger=LogFactory.getLog(SdkUpgradeController.class);
	
	private static final String CHANNEL_FILE_PATH="/assets/LGSDK_CHANNEL";
	
	private static String UNZIP_CMD="";
	
	public SdkUpgradeController(){
		// 初始化
		UNZIP_CMD=ResourceUtil.getConfigByName("UNZIP_CMD");
	}
	@Autowired
	private SystemService systemService;
	
	@RequestMapping(params="list")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response){
		
		return new ModelAndView("sdkUpgrade/list");
	}
	
	
	@RequestMapping(params="datagrid")
	public void datagrid(SdkGame sdkGame,HttpServletRequest request,HttpServletResponse response, DataGrid dataGrid){
		
		CriteriaQuery cq=new CriteriaQuery(SdkGame.class,dataGrid);
		if(StringUtil.isNotEmpty(sdkGame.getName())){
			sdkGame.setName("*"+sdkGame.getName()+"*");
		}
		HqlGenerateUtil.installHql(cq, sdkGame);

		this.systemService.getDataGridReturn(cq, true);
		//dataGrid.setReaults(SdkGameUpgrade.getSdkGameUpgradeList(systemService, dataGrid.getReaults()));
		TagUtilExt.datagrid(response, dataGrid,"gameId");
	}
	
	@RequestMapping(params="upgratelist")
	public ModelAndView upgratelist(HttpServletRequest request,HttpServletResponse response){
		if(StringUtil.isNotEmpty(request.getParameter("gameId"))){
			request.setAttribute("gameId", request.getParameter("gameId"));
		}
		return new ModelAndView("sdkUpgrade/upgratelist");
	}
	
	@RequestMapping(params="datagrid2")
	public void datagrid2(SdkUpgrade sdkUpgrade,HttpServletRequest request,HttpServletResponse response, DataGrid dataGrid){
		String gameId=request.getParameter("gameId");
		CriteriaQuery cq=new CriteriaQuery(SdkUpgrade.class,dataGrid);
		cq.eq("gameId", Integer.valueOf(gameId));
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		List<SdkGameUpdate> sdkGameUpdates=SdkGameUpdate.getUpdateList(systemService, dataGrid.getReaults());
		dataGrid.setReaults(sdkGameUpdates);
		TagUtilExt.datagrid(response, dataGrid,"upgradeId");
		
	}
	
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(String id,HttpServletRequest request){
		if(StringUtil.isNotEmpty(id)){
			SdkUpgrade sdkUpgrade=this.systemService.getEntity(SdkUpgrade.class,Integer.valueOf(id));
			 request.setAttribute("sdkUpgrade", sdkUpgrade);
		}
		return new ModelAndView("sdkUpgrade/add");
	}
	
	@RequestMapping(params="save")
	@ResponseBody
	public AjaxJson save(HttpServletRequest request,SdkUpgrade sdkUpgrade){
		AjaxJson json=new AjaxJson();
		 
		if(StringUtil.isNotEmpty(sdkUpgrade.getUpgradeId())){
			SdkUpgrade tmp=this.systemService.getEntity(SdkUpgrade.class, sdkUpgrade.getUpgradeId());
			//String dwUrl=sdkUpgrade.getDownUrl();
			//dwUrl=ResourceUtil.getConfigByName("base_down_url")+dwUrl;
			tmp.setDownUrl(sdkUpgrade.getDownUrl());
			tmp.setForceTag(sdkUpgrade.getForceTag());
			tmp.setVersionCode(sdkUpgrade.getVersionCode());
			this.systemService.updateEntitie(tmp);
			json.setMsg("更新成功");
		}else{
			json.setMsg("不存在");
		}
		return json;
	}
	
	@RequestMapping(params = "saveFiles", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson saveFile(HttpServletRequest request,HttpServletResponse response){
		String upgradeId=request.getParameter("id");
		AjaxJson json=new AjaxJson();
		String versionCode="0";
		String rootPath="public/upgrade/";
		Map<String, Object> attributes = new HashMap<String, Object>();
		 
		MultipartHttpServletRequest fq = (MultipartHttpServletRequest) request;
		Iterator<String> it= fq.getFileNames();
		String eid=String.valueOf(System.currentTimeMillis());
		String path =request.getServletContext().getRealPath("/");
		String base_path=ResourceUtil.getConfigByName("apk.basepath",path);
		String filePathString=base_path+rootPath+eid;
		
		logger.info("base_path:"+base_path);
		logger.info("filePathString:"+filePathString);
		System.out.println("base_path:"+base_path);
		System.out.println("filePathString:"+filePathString);
		File evalRoot=new File(filePathString);
		if(!evalRoot.exists()){
			evalRoot.mkdirs();
		}
		
		while(it.hasNext()){
			String name=it.next();
			MultipartFile f=fq.getFile(name);
			File fto=new File(evalRoot,f.getOriginalFilename());
			try{
				FileOutputStream fos=new FileOutputStream(fto);
				fos.write(f.getBytes());
				fos.close();
				
				logger.info("Copied file to: "+fto.getAbsolutePath());
				AndroidApk androidApk=new AndroidApk(fto);
				versionCode=androidApk.getAppVersionCode();
				String filePath=fto.getAbsolutePath();
				String disPath=filePath.substring(0, filePath.lastIndexOf("."));
				if(!unzipFile(filePath,disPath)){
					 json.setMsg("解压失败");
					 json.setSuccess(false);
					 return json;
				}
				String channelString=getChannel(disPath);
				// 反解
				String uncodeChannelString=CryptoUtil.decrypt(channelString);
				if(uncodeChannelString.lastIndexOf("_")>0){
					uncodeChannelString=uncodeChannelString.substring(uncodeChannelString.lastIndexOf("_")+1);
					SdkUpgrade sdkUpgrade=this.systemService.getEntity(SdkUpgrade.class, Integer.valueOf(upgradeId));
					if(sdkUpgrade!=null && StringUtil.isNotEmpty(sdkUpgrade.getChannel()) && sdkUpgrade.getChannel().lastIndexOf("_")>0){
						 String channleSource=sdkUpgrade.getChannel().substring(sdkUpgrade.getChannel().lastIndexOf("_")+1);
						 if(!channleSource.equalsIgnoreCase(uncodeChannelString)){
							 json.setMsg("渠道错误，和数据库的sdkUpgrade记录的channel字段不匹配");
							 json.setSuccess(false);
							 return json;
						 }
					}else{
						 json.setMsg("渠道错误，数据库sdkUpgrade记录不存在，或者channel字段为空，或者channel字段不存在下划线格式");
						 json.setSuccess(false);
						 return json;
					}
				}else if("T001".equals(uncodeChannelString)){
					SdkUpgrade sdkUpgrade=this.systemService.getEntity(SdkUpgrade.class, Integer.valueOf(upgradeId));
					if(sdkUpgrade!=null && StringUtil.isNotEmpty(sdkUpgrade.getChannel())&&sdkUpgrade.getChannel().equals("T001")){
						logger.info("默认渠道T001正常上传");
					}else{
						 json.setMsg("默认渠道T001错误");
						 json.setSuccess(false);
						 return json;
					}
				}else{
					json.setMsg("渠道错误，读取的游戏包渠道号不存在下划线格式");
					 json.setSuccess(false);
					 return json;
				}
				filePathString=rootPath+eid+"/"+f.getOriginalFilename();
			}catch(Exception e){
				logger.error(""+e,e);
			}			
		}
		json.setSuccess(true);
		json.setMsg("文件添加成功");
		attributes.put("versionCode",versionCode);
		attributes.put("apkfile",ResourceUtil.getConfigByName("base_down_url")+filePathString);
		json.setAttributes(attributes);

		return json;
		
	}
	
	public String getChannel(String filePath){
		StringBuilder  channel=new StringBuilder();
		String channelFilePath=filePath+CHANNEL_FILE_PATH;
		FileReader reader=null;
		try {
			File channelFile =new File(channelFilePath);
			if(channelFile.exists()){
				reader=new FileReader(channelFile);
				BufferedReader br = new BufferedReader (reader); 
				String s;
	             while ((s = br.readLine() )!=null) {
	            	 channel.append(s);
	              }
	             reader.close();
			}
		} catch (Exception e) {
			channel.append("");
		}
		return channel.toString();
	}
	
	
	@RequestMapping(params = "downapk")
	public void downapk(HttpServletRequest request,HttpServletResponse response,String filePath){
	/*	SdkUpgrade sdkUpgrade=this.systemService.getEntity(SdkUpgrade.class, Integer.valueOf(id));
		if(sdkUpgrade==null){
			return;
		}*/
		String fileName=filePath;
		String path =request.getServletContext().getRealPath("/");
		String base_path=ResourceUtil.getConfigByName("apk.basepath",path);
		String filePathString=base_path+fileName;
		try {      
	    	 
			 
			File dwFile=new File(filePathString);
	        FileInputStream fis = new FileInputStream(dwFile);  
	        
	        System.out.println("客户端类型：" + request.getHeader("User-Agent"));  
	        response.addHeader("Content-Type", "application/octet-stream");
	        if (request.getHeader("User-Agent").contains("Firefox")) { 
	        	
	        	response.addHeader("content-disposition", "attachment;filename="  
	                    + java.net.URLEncoder.encode(dwFile.getName(), "UTF-8"));  
	        } else if (request.getHeader("User-Agent").contains("MSIE")) {  
	            response.addHeader("content-disposition", "attachment;filename="  
	                    + java.net.URLEncoder.encode(dwFile.getName(), "UTF-8"));  
	        }else {
	        	response.addHeader("content-disposition", "attachment;filename="  
	                    + java.net.URLEncoder.encode(dwFile.getName(), "UTF-8"));  
			} 
	  
	        //相应的逻辑操作  
	          
	        ServletOutputStream sos = response.getOutputStream();  
	  
	        int count = 0;  
	  
	        byte[] bytes = new byte[1024];  
	  
	        while ((count = fis.read(bytes)) != -1) {  
	            sos.write(bytes, 0, count);  
	        }  
	  
	        sos.flush();  
	  
	        sos.close(); 
	        String delFilePath=filePathString.substring(0, filePathString.lastIndexOf("/"));
	         
	        // 删除
	       /* File delFile=new File(delFilePath);
	        if(delFile.exists()){
	        	del(delFilePath);
	        }*/
	        
	    } catch (Exception  e){      
	        e.printStackTrace();      
	    }   
		
		 
	}
	/**
	 * 使用apktool 解压
	 * @param path
	 * @param disPath
	 */
	private boolean unzipFile(String path,String disPath){
		String cmd=UNZIP_CMD;
		cmd=cmd.replace("{filePath}", path).replace("{disPath}", disPath);
		boolean tag=exectuCmd(cmd);
		return tag;
		
	}
	/**
	 * 执行命令
	 * @param cmd
	 * @return
	 */
	private boolean exectuCmd(String cmd){
		Process process=null;
		 //获得命令执行后在控制台的输出信息  
        System.out.println(cmd);// 打印输出信息
        logger.info(cmd);
		try{
			process=Runtime.getRuntime().exec(cmd);
			BufferedInputStream in = new BufferedInputStream(process.getInputStream());  
            BufferedReader inBr = new BufferedReader(new InputStreamReader(in));  
            
            BufferedInputStream errorInputStream=new BufferedInputStream(process.getErrorStream());
            BufferedReader errorReader=new BufferedReader(new InputStreamReader(errorInputStream));
            String lineStr;  
            while ((lineStr = inBr.readLine()) != null){  
                //获得命令执行后在控制台的输出信息  
                System.out.println(lineStr);// 打印输出信息
                logger.info(lineStr);
            }
            System.out.println(lineStr);// 打印输出信息
            logger.info(lineStr);
            //检查命令是否执行失败。  
            while ((lineStr = errorReader.readLine()) != null)  
            {
                //获得命令执行后在控制台的输出信息  
                System.out.println(lineStr);// 打印输出信息  
                logger.info(lineStr);
            }
            
            if (process.waitFor() != 0) {  
                if (process.exitValue() == 1)//p.exitValue()==0表示正常结束，1：非正常结束  
                {
                	System.err.println("命令执行失败!"); 
                	logger.info("命令执行失败!");
                }
                System.err.println("命令执行失败!"); 
            	logger.info("命令执行失败!");    
                return false;
            }  
            inBr.close();  
            in.close();
            process.destroy();
            System.err.println("命令执行chenggong!"); 
        	logger.info("命令执行chenggong!");  
            return true;
		}catch(Exception e){
			 System.err.println("命令执行失败!"+e.toString()); 
         	logger.info("命令执行失败!"+e.toString()); 
			return false;
		}
	}
	
	public static void main(String args[]) throws Exception {
		String s1="T001";
		String s2="kaixinxiaoxiaole2015_3_wenjianjia";
		System.out.println(s2.lastIndexOf("_"));
	}
}


