package jeecg.ext.sdk.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.SdkChannel;
import jeecg.ext.sdk.entity.SdkGame;
import jeecg.ext.sdk.entity.SdkUpgrade;
import jeecg.ext.tools.CryptoUtil;
import jeecg.ext.tools.XmlHelper;
import jeecg.ext.tools.ZipCompressor;
import jeecg.system.service.SystemService;
import net.erdfelt.android.apk.AndroidApk;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.util.PinyinUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/makeApk")
public class ApkMakerController extends BaseController{
	
	private static final String APKTOOL_PATH_STRING="";
	
	private static String UNZIP_CMD="";
	
	private static  String COMPRESS_CMD="";
	
	private static  String SIGN_APK_CMD="";
	
	private static String OUT_FILE="/out/";

	private static final String CHANNEL_FILE_PATH="/assets/LGSDK_CHANNEL";

	private static final String APKCONFIGFILE="/AndroidManifest.xml";
	
	static Log logger=LogFactory.getLog(ApkMakerController.class);
	
	public ApkMakerController(){
		// 初始化
		UNZIP_CMD=ResourceUtil.getConfigByName("UNZIP_CMD");
		COMPRESS_CMD=ResourceUtil.getConfigByName("COMPRESS_CMD");
		SIGN_APK_CMD=ResourceUtil.getConfigByName("SIGN_APK_CMD");
	}
	
	@Autowired
	private SystemService systemService;
	
	
	@RequestMapping(params="list")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response){
		return new ModelAndView("compresapk/list");
	}
	
	
	@RequestMapping(params="first")
	public ModelAndView first(HttpServletRequest request,HttpServletResponse response){
 		return new ModelAndView("compresapk/first");
	}
	
	@RequestMapping(params="second")
	public ModelAndView second(HttpServletRequest request,HttpServletResponse response){
		// 获取渠道列表
		String fileName=request.getParameter("fileName");
		request.setAttribute("fileName", fileName);
		List<SdkChannel> sdkChannels=this.systemService.getList(SdkChannel.class);
		request.setAttribute("sdkChannels", sdkChannels);
		//return request.getRequestDispatcher("compresapk/second").forward(request, response);
		return new ModelAndView("compresapk/second");
	}
	
	@RequestMapping(params="three")
	public ModelAndView three(HttpServletRequest request,HttpServletResponse response) throws ZipException, IOException{
		String channelValue=request.getParameter("channelValues");
		String fileName=request.getParameter("fileName");
		String gameNameString=request.getParameter("name");
		Date nowTime=new Date(System.currentTimeMillis());
		SimpleDateFormat sdfDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
		OUT_FILE="/"+gameNameString+"_"+sdfDateFormat.format(nowTime)+"/";
		String versionString="1";
		int apkCount=0;
		// 解压
		String path =request.getServletContext().getRealPath("/");
		String base_path=ResourceUtil.getConfigByName("apk.basepath",path);
		String filePathString=base_path+fileName;
		File file=new File(filePathString);
		String disPath="";
		SdkGame sdkGame=null;
		if(file.exists()){
			disPath=filePathString.substring(0, filePathString.lastIndexOf("."));
			AndroidApk androidApk=new AndroidApk(file);
			versionString=androidApk.getAppVersionCode();
			if(unzipFile(filePathString,disPath)){
				// 解压成功 读取xml
				String appKey=XmlHelper.getAppKey(disPath+APKCONFIGFILE);
				sdkGame=this.systemService.findUniqueByProperty(SdkGame.class, "appKey", appKey);
				if(sdkGame!=null){
					
				}else{
					// 未找到游戏
					request.setAttribute("errorMessage", "未找到游戏");
					return new ModelAndView("compresapk/three");
				}
			}else {
				// 解压失败
				request.setAttribute("errorMessage", "解压失败");
				return new ModelAndView("compresapk/three");
			}
		}
		
		String[] channels=channelValue.split(",");
		if(channels.length>0){
			for(int i=0;i<channels.length;i++){
				if(!StringUtil.isNotEmpty(channels[i])) continue;
				SdkChannel sdkChannel=this.systemService.getEntity(SdkChannel.class,Integer.valueOf(channels[i]));
				if(sdkChannel!=null){
				try {
					// 游戏名称＋游戏版本号＋渠道编号
					
					String gameName=PinyinUtil.getPinYin(sdkGame.getName());
					String signString=gameName+"_"+versionString+"_"+sdkChannel.getChannelNum();
					logger.info("the apkMaker gameName ="+gameName+", appVersionCode ="+versionString+", channelNum ="+sdkChannel.getChannelNum());
					logger.info("the apkMaker before encrypt the plaintext ="+signString);
						if(!modifyChannel(disPath,signString)){
							request.setAttribute("errorMessage", "修改渠道失败");
							return new ModelAndView("compresapk/three");
						}
						// 打包
						String newFileString=filePathString.substring(0, filePathString.lastIndexOf("."))+"_new.apk";
						
						if(!compress(disPath.replace("\\", "/"),newFileString.replace("\\", "/"))){
							request.setAttribute("errorMessage", "重启创建apk失败");
							return new ModelAndView("compresapk/three");
						}
						// 签名
						String baseSignpATHString=filePathString.substring(0, filePathString.lastIndexOf("/"))+OUT_FILE;
						String signFileString=baseSignpATHString+gameNameString+"_"
								+versionString+"_"+sdkChannel.getChannelName()+".apk";
						File signPathFile=new File(baseSignpATHString);
						if(!signPathFile.exists()){
							signPathFile.mkdirs();
						}
						if(!signApk(newFileString,signFileString)){
							request.setAttribute("errorMessage", "签名失败");
							return new ModelAndView("compresapk/three");
						}
						apkCount++;
						boolean isExsit=false;
						// 记录
						if(sdkGame!=null){
							List<SdkUpgrade> tmps=this.systemService.findByProperty(SdkUpgrade.class, "gameId", sdkGame.getGameId());
							if(tmps!=null && tmps.size()>0){
								for(SdkUpgrade tmp:tmps){
									String channelTmp=tmp.getChannel();
									if(channelTmp.lastIndexOf("_")>0){
										channelTmp=channelTmp.substring(channelTmp.lastIndexOf("_")+1);
									}else{
										request.setAttribute("errorMessage", sdkChannel.getChannelName()+" 渠道错误");
										return new ModelAndView("compresapk/three");
									}
									if(channelTmp.equalsIgnoreCase(sdkChannel.getChannelNum())){
										isExsit=true;
										break;
										// 渠道存在不用添加
										//打包
										/*String zipFileString=filePathString.substring(0, filePathString.lastIndexOf("/"))+OUT_FILE;
										String zipoutString=zipFileString.substring(0,zipFileString.lastIndexOf("/"))+".zip";
										ZipCompressor zc = new ZipCompressor(zipoutString.replace("\\", "/"));   
								        zc.compress(zipFileString.replace("\\", "/")); 
										String outFile=fileName.substring(0,fileName.lastIndexOf("/"))+OUT_FILE.substring(0,OUT_FILE.lastIndexOf("/"))+".zip";;
										request.setAttribute("fileName", outFile);
										request.setAttribute("akpCount", apkCount);
										return new ModelAndView("compresapk/three");*/
										//request.setAttribute("errorMessage", sdkChannel.getChannelName()+" 渠道已经存在");
										//return new ModelAndView("compresapk/three");
									}
								}
							}
							if(!isExsit){
								SdkUpgrade sdkUpgrade=new SdkUpgrade();
								sdkUpgrade.setChannel(signString);
								sdkUpgrade.setCreateTime(new Timestamp(System.currentTimeMillis()));
								sdkUpgrade.setDownUrl("");
								sdkUpgrade.setForceTag(0);
								sdkUpgrade.setGameId(sdkGame.getGameId());
								sdkUpgrade.setMemo("");
								sdkUpgrade.setRegex(0);
								sdkUpgrade.setStatusTag(1);
								sdkUpgrade.setVersion("");
								sdkUpgrade.setVersionCode(0);
								this.systemService.save(sdkUpgrade);
							}
							
						}
					} catch (Exception e) {
						request.setAttribute("errorMessage", "未知错误");
						this.logger.debug("打包错误 -------------"+e);
						return new ModelAndView("compresapk/three");
					}
					
				}
			}
		}
		
		//打包
		String zipFileString=filePathString.substring(0, filePathString.lastIndexOf("/"))+OUT_FILE;
		String zipoutString=zipFileString.substring(0,zipFileString.lastIndexOf("/"))+".zip";
		ZipCompressor zc = new ZipCompressor(zipoutString.replace("\\", "/"));   
        zc.compress(zipFileString.replace("\\", "/")); 
		String outFile=fileName.substring(0,fileName.lastIndexOf("/"))+OUT_FILE.substring(0,OUT_FILE.lastIndexOf("/"))+".zip";;
		request.setAttribute("fileName", outFile);
		request.setAttribute("akpCount", apkCount);
		return new ModelAndView("compresapk/three");
	}
	
	@RequestMapping(params = "finish")
	public AjaxJson finishApk(HttpServletRequest request,HttpServletResponse response){
		AjaxJson j=new AjaxJson();
		j.setMsg("完成!");
		return j;
	}
	
	@RequestMapping(params = "zipApk")
	public void packageApk(HttpServletRequest request,HttpServletResponse response){
		
		String fileName=request.getParameter("fileName");
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
	        File delFile=new File(delFilePath);
	        if(delFile.exists()){
	        	del(delFilePath);
	        }
	        
	    } catch (Exception  e){      
	        e.printStackTrace();      
	    }   
		
		 
	}
	
	private void del(String filePath) throws IOException{
		File f = new File(filePath);//定义文件路径   
		if(f.exists() && f.isDirectory()){
			if(f.listFiles().length==0){//若目录下没有文件则直接删除  
		        f.delete();  
		    }else{
		    	File delFile[]=f.listFiles();  
		        int i =f.listFiles().length;  
		        for(int j=0;j<i;j++){  
		            if(delFile[j].isDirectory()){  
		               del(delFile[j].getAbsolutePath());
		            }
;		            delFile[j].delete();
		        }
		    }
		}
		   
	}
	
	@RequestMapping(params = "datagrid")
	public void datagrid(SdkGame gameData,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		 
	} 
	 
	@RequestMapping(params = "saveFiles", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson saveFile(HttpServletRequest request,HttpServletResponse response){
		AjaxJson json=new AjaxJson();
		Map<String, Object> attributes = new HashMap<String, Object>();
		 
		MultipartHttpServletRequest fq = (MultipartHttpServletRequest) request;
		Iterator<String> it= fq.getFileNames();
		String eid=String.valueOf(System.currentTimeMillis());
		String path =request.getServletContext().getRealPath("/");
		String base_path=ResourceUtil.getConfigByName("apk.basepath",path);
		String filePathString=base_path+"public/apk/"+eid;
		
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
				filePathString="public/apk/"+eid+"/"+f.getOriginalFilename();
			}catch(Exception e){
				logger.error(""+e,e);
			}			
		}
		
		json.setMsg("文件添加成功");
		attributes.put("apkfile",filePathString);
		json.setAttributes(attributes);

		return json;
		
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
	 * 写入渠道号文件
	 * @param basePath 解压后apk目录
	 * @param channelNum 渠道号
	 * @throws IOException
	 */
	private boolean modifyChannel(String basePath,String channelNum){
		String filePath=basePath+CHANNEL_FILE_PATH;
		FileWriter writer=null;
		try {
			File channelFile=new File(filePath);
			if(!channelFile.exists()){
				channelFile.createNewFile();
			}
			writer=new FileWriter(channelFile);
			String inputString=CryptoUtil.encrypt(channelNum);
			writer.write(inputString);
			writer.flush();
			writer.close();
			return true;
		} catch (Exception e) {
			System.out.println(e);
			 return false;
		} 
 
	}
	
	/**
	 * 打包
	 * @param sourcePath
	 * @param newapkFile
	 */
	private boolean compress(String sourcePath,String newapkFile){
		String cmd=COMPRESS_CMD.replace("{sourcePath}", sourcePath).replace("{newunapkFile}", newapkFile);
		boolean tag=exectuCmd(cmd);
		return tag;
	}
	
	/**
	 * 签名
	 * @param unsignApkFile
	 * @param signApkFile
	 */
	private boolean signApk(String unsignApkFile,String signApkFile){
		String cmd=SIGN_APK_CMD;
		cmd=cmd.replace("{signFile}", signApkFile).replace("{unsignFile}", unsignApkFile);
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
//                	System.err.println("命令执行失败!"); 
                	logger.info("非正常结束,命令执行失败!"+", the cmd ="+cmd);
                }
//                System.err.println("命令执行失败!"); 
            	logger.info("命令执行失败!"+", the cmd ="+cmd);    
                return false;
            }  
            inBr.close();  
            in.close();
            process.destroy();
            System.err.println("命令执行chenggong!"); 
        	logger.info("命令执行chenggong!");  
            return true;
		}catch(Exception e){
//			 System.err.println("命令执行失败!"+e.toString()); 
         	logger.info("命令执行失败!"+e.toString()); 
			return false;
		}
	}
	
	
	public static void main(String args[]) throws Exception {
		// 游戏名称＋游戏版本号＋渠道编号
		String gameName=PinyinUtil.getPinYin("内测游戏");
		String appVersionCode="1";
		String channelNum="suixingbao1";
		String signString=gameName+"_"+appVersionCode+"_"+channelNum;
		String encryptString=CryptoUtil.encrypt(signString);
		String decryptString=CryptoUtil.decrypt(encryptString);
//		String decryptString=CryptoUtil.decrypt("483C4B38812F9B8C042402B6AA78194C");
//		System.out.println("the encryptString ="+encryptString);
		System.out.println(encryptString);
		System.out.println(decryptString);
	}
}
