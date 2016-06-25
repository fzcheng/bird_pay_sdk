package jeecg.ext.sdk.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.SdkUpgradeJar;
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
@RequestMapping("/sdkUpgradeJar")
public class SdkUpgradeJarController extends BaseController {
	private static final Logger logger = Logger
			.getLogger(SdkUpgradeJarController.class);

	@Autowired
	private SystemService systemService;

	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) {
		return new ModelAndView("sdkUpgradeJar/list");
	}

	@RequestMapping(params = "datagrid")
	public void datagrid(HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(SdkUpgradeJar.class,
				dataGrid);
		String versionCode=request.getParameter("versionCode");
		if(StringUtils.isNotEmpty(versionCode)&&StringUtils.isNotBlank(versionCode)){
			cq.add(Restrictions.like("versionCode", "%"+versionCode+"%"));
		}
		String statusTag=request.getParameter("statusTag");
		if(StringUtils.isNotEmpty(statusTag)&&StringUtils.isNotBlank(statusTag)){
			cq.add(Restrictions.eq("statusTag", parseInteger(statusTag)));
		}
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
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
	
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(String id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sdkUpgradeJar/addorupdate");
		Integer idInt = parseInteger(id);
		logger.info("the sdkUpgradeJar idInt is :" + idInt);
		if (idInt != null) {
			SdkUpgradeJar sdkUpgradeJar = systemService
					.getEntity(SdkUpgradeJar.class, idInt);
			mv.addObject("sdkUpgradeJar", sdkUpgradeJar);
		}
		return mv;
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
		String base_path = ResourceUtil.getConfigByName("jar.basepath", path);
		String filePathString = base_path + eid;

		logger.info("base_path:" + base_path);
		logger.info("filePathString:" + filePathString);
		File evalRoot = new File(filePathString);
		if (!evalRoot.exists()) {
			evalRoot.mkdirs();
		}

		while (it.hasNext()) {
			String name = it.next();
			MultipartFile f = fq.getFile(name);
//			String houzuiming = f.getOriginalFilename().substring(
//					f.getOriginalFilename().lastIndexOf("."));
			logger.info("jar name is :" + f.getOriginalFilename());
			File fto = new File(evalRoot, f.getOriginalFilename());

			try {
				FileOutputStream fos = new FileOutputStream(fto);
				fos.write(f.getBytes());
				fos.close();

				logger.info("Copied file to: " + fto.getAbsolutePath());
				filePathString = eid + "/" + f.getOriginalFilename();

				SdkUpgradeJar entity = null;
				if (request.getParameter("id") != null) {
					entity = systemService.getEntity(
							SdkUpgradeJar.class,
							parseInteger(request.getParameter("id")));
				}
				Date now = new Date();
				if (entity != null) {
					String basepath = ResourceUtil.getConfigByName("jar.basepath");
//					logger.info("the jar.basepath is :"+basepath);
					String dbfilepath = entity.getDownUrl();
//					logger.info("the dbfilepath is :"+dbfilepath);
					String temfilepath = "";
					if (dbfilepath.contains("/")) {
						temfilepath = basepath
								+ dbfilepath.substring(0, dbfilepath.indexOf("/"));// linux下的删除路径
//						logger.info("the temfilepth is:" + temfilepath);
//						String lastfilepath = temfilepath.replace("/", "\\\\");// windows下的删除路径
						try {
							DeleteFolder(temfilepath);
						} catch (Exception e) {
							logger.error("" + e, e);
						}
					}
					entity.setVersionCode(request.getParameter("versionCode"));
					entity.setDownUrl(eid + "/"+f.getOriginalFilename());
					entity.setStatusTag(Integer.valueOf(request
							.getParameter("statusTag")));
					entity.setMemo(request.getParameter("memo"));
					entity.setUpdatedTime(now);
				} else {
					entity = new SdkUpgradeJar();
					entity.setVersionCode(request.getParameter("versionCode"));
					entity.setDownUrl(eid + "/"+f.getOriginalFilename());
					entity.setMemo(request.getParameter("memo"));
					entity.setCreateTime(now);
					entity.setStatusTag(Integer.valueOf(request
							.getParameter("statusTag")));
				}

				this.systemService.saveOrUpdate(entity);

			} catch (Exception e) {
				logger.error("" + e, e);
				json.setMsg("保存异常");
			}
		}
		attributes.put("jarfile", filePathString);
		json.setAttributes(attributes);
		json.setMsg("文件添加成功");
		return json;

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

		SdkUpgradeJar entity = systemService.getEntity(
				SdkUpgradeJar.class, idInt);
		if (entity == null) {
			json.setMsg("id=" + id + "，记录不存在");
			return json;
		}

		String base_path = ResourceUtil.getConfigByName("jar.basepath");
		String dbfilepath = entity.getDownUrl();
		String temfilepath = "";
		if (dbfilepath.contains("/")) {
			temfilepath = base_path
					+ dbfilepath.substring(0, dbfilepath.indexOf("/"));// linux下的删除路径
			logger.info("the temfilepth is:" + temfilepath);
//			String lastfilepath = temfilepath.replace("/", "\\\\");// windows下的删除路径
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
	public static void main(String[] args) {
		String eid = String.valueOf(System.currentTimeMillis());
		System.out.println(eid);
	}
}
