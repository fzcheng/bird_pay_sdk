package jeecg.ext.sdk.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.SdkGameAdvertisementList;
import jeecg.system.service.SystemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;

@Controller
@RequestMapping("/sdkGameAdvertisementList")
public class SdkGameAdvertisementListController extends BaseController {
	private static final Logger logger = Logger
			.getLogger(SdkGameAdvertisementListController.class);

	@Autowired
	private SystemService systemService;

	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) {
		return new ModelAndView("sdkGameAdvertisementList/list");
	}

	@RequestMapping(params = "datagrid")
	public void datagrid(HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(SdkGameAdvertisementList.class,
				dataGrid);
		String advertisementUrl=request.getParameter("advertisementUrl");
		if(StringUtils.isNotEmpty(advertisementUrl)&&StringUtils.isNotBlank(advertisementUrl)){
			cq.add(Restrictions.like("advertisementUrl", "%"+advertisementUrl+"%"));
		}
		String ifshow=request.getParameter("ifshow");
		if(StringUtils.isNotEmpty(ifshow)&&StringUtils.isNotBlank(ifshow)){
			cq.add(Restrictions.eq("ifshow", parseInteger(ifshow)));
		}
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(String id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sdkGameAdvertisementList/addorupdate");
		logger.info("the sdkGameAdvertisementList id is :" + id);
		Integer idInt = parseInteger(id);
		logger.info("the sdkGameAdvertisementList idInt is :" + idInt);
		if (idInt != null) {
			SdkGameAdvertisementList sdkGameAdvertisementList = systemService
					.getEntity(SdkGameAdvertisementList.class, idInt);
			mv.addObject("sdkGameAdvertisementList", sdkGameAdvertisementList);
		}
		return mv;
	}

	@RequestMapping(params = "del", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson del(String id) {
		AjaxJson json = new AjaxJson();
		Integer idInt = parseInteger(id);
		if (idInt == null) {
			json.setMsg("请选择要删除的项目");
			return json;
		}

		SdkGameAdvertisementList entity = systemService.getEntity(
				SdkGameAdvertisementList.class, idInt);
		if (entity == null) {
			json.setMsg("id=" + id + "，记录不存在");
			return json;
		}

		String base_path = ResourceUtil.getConfigByName("pic.basepath");
		String dbfilepath = entity.getIconUrl();
		String temfilepath = "";
		if (dbfilepath.contains("/")) {
			temfilepath = base_path
					+ dbfilepath.substring(0, dbfilepath.indexOf("/"));// linux下的删除路径
			logger.info("the temfilepth is:" + temfilepath);
			String lastfilepath = temfilepath.replace("/", "\\\\");// windows下的删除路径
			logger.info("the delete path is:" + temfilepath);
			try {
				DeleteFolder(temfilepath);
				systemService.delete(entity);
				json.setMsg("删除成功");
			} catch (Exception e) {
				logger.error("" + e, e);
				json.setMsg("删除异常");
			}
		} else {
			json.setMsg("删除错误！");
		}
		return json;
	}

	private Integer parseInteger(String str) {
		Integer num = null;
		try {
			if (StringUtils.isNotBlank(str)) {
				num = Integer.valueOf(str);
			}
		} catch (NumberFormatException e) {
			logger.warn("parse number string error! str = " + str);
		}
		return num;
	}

	@RequestMapping(params = "saveFile", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson saveFile(HttpServletRequest request,
			HttpServletResponse response) {
		AjaxJson json = new AjaxJson();
		Map<String, Object> attributes = new HashMap<String, Object>();

		MultipartHttpServletRequest fq = (MultipartHttpServletRequest) request;
		Iterator<String> it = fq.getFileNames();
		String eid = String.valueOf(System.currentTimeMillis());
		String path = request.getServletContext().getRealPath("/");
		String base_path = ResourceUtil.getConfigByName("pic.basepath", path);
		String filePathString = base_path + eid;

		logger.info("base_path:" + base_path);
		logger.info("filePathString:" + filePathString);
		System.out.println("base_path:" + base_path);
		System.out.println("filePathString:" + filePathString);
		File evalRoot = new File(filePathString);
		if (!evalRoot.exists()) {
			evalRoot.mkdirs();
		}

		while (it.hasNext()) {
			String name = it.next();
			logger.info("the file name is :" + name);
			MultipartFile f = fq.getFile(name);
			String houzuiming = f.getOriginalFilename().substring(
					f.getOriginalFilename().lastIndexOf("."));
			logger.info("the eid+houzuiming is :" + eid + houzuiming);
			File fto = new File(evalRoot, eid + houzuiming);

			try {
				FileOutputStream fos = new FileOutputStream(fto);
				fos.write(f.getBytes());
				fos.close();

				logger.info("Copied file to: " + fto.getAbsolutePath());
				filePathString = eid + "/" + f.getOriginalFilename();

				SdkGameAdvertisementList entity = null;
				if (request.getParameter("id") != null) {
					entity = systemService.getEntity(
							SdkGameAdvertisementList.class,
							request.getParameter("id"));
				}
				Date now = new Date();
				if (entity != null) {
					entity.setAdvertisementUrl(request.getParameter("adurl"));
					entity.setIconUrl(eid + "/" + eid + houzuiming);
					entity.setIfshow(Integer.valueOf(request
							.getParameter("ifpush")));
					entity.setUpdatedTime(now);
				} else {
					entity = new SdkGameAdvertisementList();
					entity.setAdvertisementUrl(request.getParameter("adurl"));
					entity.setIconUrl(eid + "/" + eid + houzuiming);
					entity.setCreatedTime(now);
					entity.setIfshow(Integer.valueOf(request
							.getParameter("ifpush")));
				}

				this.systemService.saveOrUpdate(entity);

			} catch (Exception e) {
				logger.error("" + e, e);
				json.setMsg("保存异常");
			}
		}

		logger.info("the laststep is:");

		attributes.put("picfile", filePathString);
		json.setAttributes(attributes);
		json.setMsg("文件添加成功");
		return json;

	}

	/**
	 * 根据路径删除指定的目录或文件，无论存在与否
	 * 
	 * @param sPath
	 *            要删除的目录或文件
	 * @return 删除成功返回 true，否则返回 false。
	 */
	public boolean DeleteFolder(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 判断目录或文件是否存在
		if (!file.exists()) { // 不存在返回 false
			return flag;
		} else {
			// 判断是否为文件
			if (file.isFile()) { // 为文件时调用删除文件方法
				return deleteFile(sPath);
			} else { // 为目录时调用删除目录方法
				return deleteDirectory(sPath);
			}
		}
	}

	/**
	 * 删除单个文件
	 * 
	 * @param sPath
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	/**
	 * 删除目录（文件夹）以及目录下的文件
	 * 
	 * @param sPath
	 *            被删除目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public boolean deleteDirectory(String sPath) {
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		boolean flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} // 删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag)
			return false;
		// 删除当前目录
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public boolean creatTxtFile() throws IOException {
		boolean flag = false;
		String pathbase = "D:\\ljg\\需求文档\\号段需求\\移动\\log\\";
		String eid = String.valueOf(System.currentTimeMillis());
		String filenameTemp = pathbase + eid + ".txt";
		File filename = new File(filenameTemp);
		if (!filename.exists()) {
			filename.createNewFile();
			flag = true;
		}
		return flag;
	}

	public void writerText(String path, String content,String error) {

        File dirFile = new File(path);

        if (!dirFile.exists()) {
            dirFile.mkdir();
            System.out.println("创建文件!");
        }

        try {
            //new FileWriter(path + "t.txt", true)  这里加入true 可以不覆盖原有TXT文件内容 续写
            BufferedWriter bw1 = new BufferedWriter(new FileWriter(path + "11到20.txt", true));
            bw1.write(content);
            bw1.newLine();
            bw1.flush();
            bw1.close();
        } catch (IOException e) {
        	BufferedWriter bw1;
			try {
				bw1 = new BufferedWriter(new FileWriter(path + "11到20.txt", true));
				bw1.write(error);
				bw1.newLine();
	            bw1.flush();
	            bw1.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
	}
	
	public void readTxtFile(String filePath) {
		/** 
	     * @param args 
	     */  
	    //驱动程序就是之前在classpath中配置的JDBC的驱动程序的JAR 包中  
	    String DBDRIVER = "com.mysql.jdbc.Driver";  
	    //连接地址是由各个数据库生产商单独提供的，所以需要单独记住  
	    String DBURL = "jdbc:mysql://192.168.1.99:3306/game_sdk";  
	    //连接数据库的用户名  
	    String DBUSER = "root";  
	    //连接数据库的密码  
	    String DBPASS = "leyogame";
		try {
			Connection con = null; //表示数据库的连接对象 
			PreparedStatement pstmt = null; //表示数据库更新操作
	        Class.forName(DBDRIVER); //1、使用CLASS 类加载驱动程序  
	        con = DriverManager.getConnection(DBURL,DBUSER,DBPASS); //2、连接数据库  
	        System.out.println(con);  
	        
			String encoding = "GBK";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				Date begin=new Date();
				
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				String[] content=new String[]{};
				int num=1;
				logger.info("开始写入数据库!");
				while ((lineTxt = bufferedReader.readLine()) != null) {
					content=lineTxt.split(",");
					if(StringUtils.isNumeric(content[0])){
						Date now = new Date();       
				        String sql = "insert into sdk_phone(H_code,area_code,city_name,create_time,operation_type) values(?,?,?,?,?)";
				        int H_code=parseInteger(content[0]);
				        pstmt = con.prepareStatement(sql); //使用预处理的方式创建对象  
				        pstmt.setInt(1, H_code); //第一个？号的内容 
				        String area_code="0"+content[1];
				        pstmt.setString(2, area_code); //第二个？号的内容  
				        pstmt.setString(3, content[2]);
				        pstmt.setDate(4, new java.sql.Date(now.getTime()));
				        int type=1;
				        pstmt.setInt(5, type);
				        pstmt.executeUpdate(); //执行SQL 语句，更新数据库  
				        String suceess="第"+num+"条内容写入成功!\n";
				        //logger.info(suceess);
				        System.out.println(" 号段："+H_code+", 区号："+area_code+", 类型："+type+", 时间："+now.getTime()+"---"+suceess);
						String faile="-----第"+num+"条内容写入失败!-----\n";
						writerText("D:\\ljg\\需求文档\\号段需求\\移动\\log\\",suceess,faile);
						num++;
					}															
				}
				Date end=new Date();
				long l=end.getTime()-begin.getTime();  
				long day=l/(24*60*60*1000);  
				long hour=(l/(60*60*1000)-day*24);  
				long min=((l/(60*1000))-day*24*60-hour*60);  
				long s=(l/1000-day*24*60*60-hour*60*60-min*60);
				String time="共用时： "+day+"天"+hour+"小时"+min+"分"+s+"秒";
				//System.out.println(time);
				logger.info(time);
				writerText("D:\\ljg\\需求文档\\号段需求\\移动\\log\\",time,"");
				String d="总共有"+(num-1)+"条数据写入数据库!";
				//System.out.println(d);
				logger.info(d);
				writerText("D:\\ljg\\需求文档\\号段需求\\移动\\log\\",d,"");
				read.close();
				//System.out.println("成功写入数据库！");
				logger.info("成功写入数据库！");
				pstmt.close();
				con.close(); // 3、关闭数据库  
			} else {
				System.out.println("找不到指定的文件");								
			}
		} catch (Exception e) {			
			System.out.println("读取文件内容出错");			
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		 SdkGameAdvertisementListController p =new
		 SdkGameAdvertisementListController();
		// String path = "D:\\ljg\\tmp\\141578668489";
		// boolean result = p.DeleteFolder(path);
		// logger.debug("结果是："+result);
		// System.out.println(result);
//		 p.readTxtFile("D:\\ljg\\需求文档\\号段需求\\移动\\");
		 String string="11.jar";
		 String s=string.substring(string.lastIndexOf("."));
		 System.out.println(s);
	}

}
