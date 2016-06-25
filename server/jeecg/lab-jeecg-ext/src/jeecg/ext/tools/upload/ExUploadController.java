package jeecg.ext.tools.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.cgform.entity.upload.CgUploadEntity;
import jeecg.cgform.service.upload.CgUploadServiceI;
import jeecg.ext.online.db.ExDatabaseService;
import jeecg.ext.online.db.entity.ExCgFormFieldEntity;
import jeecg.ext.online.db.entity.ExCgFormHeadEntity;
import jeecg.system.service.SystemService;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.util.FileUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping({ "/exUploadController" })
public class ExUploadController extends BaseController {
	private static final Logger logger = Logger
			.getLogger(ExUploadController.class);

	@Autowired
	private SystemService systemService;

	@Autowired
	private ExDatabaseService exDatabaseService;

	@Autowired
	private CgUploadServiceI cgUploadService;
	private String message;

	public ExDatabaseService getExDatabaseService() {
		return exDatabaseService;
	}

	public void setExDatabaseService(ExDatabaseService exDatabaseService) {
		this.exDatabaseService = exDatabaseService;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * upload the file from request
	 * 
	 * @param request
	 * @return
	 */
	public String uploadFile(HttpServletRequest request, String path,
			String cgField) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		String contextPath = request.getServletContext().getRealPath("/");
		String realPath = "";
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile mf = entity.getValue();// 获取上传文件对象
			String fileName = mf.getOriginalFilename();
			realPath = path.split("\\.")[0];
			realPath = realPath.substring(0, realPath.lastIndexOf("/"));
			File dir = new File(contextPath + "/" + realPath);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			String extend = FileUtils.getExtend(fileName);
			if (extend.equals("")) {
				realPath = path.split("\\.")[0];
			} else {

				realPath = path.split("\\.")[0] + "."
						+ FileUtils.getExtend(fileName);
			}

			File file = new File(contextPath + "/" + realPath);
			FileOutputStream fo = null;
			try {
				fo = new FileOutputStream(file);
				if (file.exists()) {
					// file.delete();
				}
				file.canRead();
				byte[] fileBytes = mf.getBytes();
				fo.write(fileBytes);

			} catch (IllegalStateException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} finally {
				if (fo != null) {
					try {
						fo.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
			break;
		}
		return realPath;

	}

	/**
	 * build the file path for saveing
	 * 
	 * @param basePath
	 * @param index
	 * @return
	 */
	public String buildPath(String fileName, String cgFieldValue) {
		int i = 1;
		if (cgFieldValue == null || cgFieldValue.equals("")) {

		} else {
			String[] list = cgFieldValue.split(",");
			int length = list.length;
			String[] temp = list[length - 1].split("\\.");
			String[] fileNamePrefix = temp[0].split("/");
			String filePrex = fileNamePrefix[fileNamePrefix.length - 1];
			i = Integer.valueOf(filePrex) + 1;
		}
		String extend = FileUtils.getExtend(fileName);
		String datePath = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
		if (extend.equals("")) {
			return datePath + "/"
					+ String.valueOf((int) (Math.random() * 100000)) + "/"
					+ String.valueOf(i);
		}

		return datePath + "/" + String.valueOf((int) (Math.random() * 100000))
				+ "/" + String.valueOf(i) + "." + extend;
	}

	public String addCgFieldValue(String cgFieldValue, String path) {

		if (cgFieldValue == null || cgFieldValue.equals("")) {
			return path;
		} else {
			cgFieldValue = cgFieldValue + "," + path;
		}

		return cgFieldValue;
	}

	@RequestMapping(params = { "saveFiles" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public AjaxJson saveFiles(HttpServletRequest request,
			HttpServletResponse response, CgUploadEntity cgUploadEntity) {
		AjaxJson j = new AjaxJson();
		Map attributes = new HashMap();
		String fileKey = oConvertUtils.getString(request
				.getParameter("fileKey"));
		String id = oConvertUtils.getString(request.getParameter("cgFormId"));
		String tableName = oConvertUtils.getString(request
				.getParameter("cgFormName"));
		String cgField = oConvertUtils.getString(request
				.getParameter("cgFormField"));

		ExCgFormHeadEntity head = exDatabaseService
				.getCgFormHeadByTableName(tableName);
		List list = exDatabaseService.getCgFormFieldByTableName(tableName);
		// ExCgFormFieldEntity keyField=head.getKeyField();
		String uploadBasePath = "default";
		int length = 0;
		if (list != null) {
			length = list.size();
		}
		for (int i = 0; i < length; i++) {
			Map map = (Map) list.get(i);
			if (map.get("field_name").equals(cgField)) {
				uploadBasePath = (String) map.get("file_path");
			}
		}

		String cgFieldValue = (String) request.getSession().getAttribute(
				tableName + "," + "new" + "," + cgField);

		String newOrUpdate = String
				.valueOf(request.getParameter("newOrUpdate"));
		if (newOrUpdate.equals("1")) {
			if (StringUtil.isEmpty(cgFieldValue)) {
				Map data = exDatabaseService.findOneForJdbc(tableName, id);
				if (data != null) {

					cgFieldValue = (String) data.get(cgField);
				}
			}
		}
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();

		String newCgFieldValue = "";
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile mf = entity.getValue();// 获取上传文件对象
			String fileName = mf.getOriginalFilename();
			String path = buildPath(fileName, cgFieldValue);
			String realPath = path.split("\\.")[0];
			realPath = realPath.substring(0, realPath.lastIndexOf("/"));
			File dir = new File(uploadBasePath + "/" + realPath);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			File file = new File(uploadBasePath + "/" + path);
			try {
				mf.transferTo(file);
			} catch (IllegalStateException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			newCgFieldValue = addCgFieldValue(cgFieldValue, path);
			attributes.put("path", path);
		}

		request.getSession().setAttribute(
				tableName + "," + "new" + "," + cgField, newCgFieldValue);
		message = "操作成功";
		j.setMsg(message);
		j.setAttributes(attributes);
		return j;
	}

	@RequestMapping(params = { "modifyUpload" })
	@ResponseBody
	public AjaxJson modifyUpload(HttpServletRequest request) {

		AjaxJson j = new AjaxJson();

		String tableName = request.getParameter("tableName");
		String cgField = request.getParameter("cgField");
		String path = request.getParameter("path");

		String newCgFieldValue = (String) request.getSession().getAttribute(
				tableName + "," + "new" + "," + cgField);
		List list = exDatabaseService.getCgFormFieldByTableName(tableName);
		// ExCgFormFieldEntity keyField=head.getKeyField();
		String uploadBasePath = "default";
		int length = 0;
		if (list != null) {
			length = list.size();
		}
		for (int i = 0; i < length; i++) {
			Map map = (Map) list.get(i);
			if (map.get("field_name").equals(cgField)) {
				uploadBasePath = (String) map.get("file_path");
			}
		}
		String realPath = "";
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile mf = entity.getValue();// 获取上传文件对象
			String fileName = mf.getOriginalFilename();
			realPath = path.split("\\.")[0];
			String extend = FileUtils.getExtend(fileName);
			
			if(!extend.equals("")){
				realPath = realPath+"."+extend;
			}
			String dirPath = realPath.substring(0, realPath.lastIndexOf("/"));
			File dir = new File(uploadBasePath + "/" + dirPath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			

			File file = new File(uploadBasePath + "/" + realPath);
			try {
				mf.transferTo(file);
			} catch (IllegalStateException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}

		String[] newCgFieldValueArray = newCgFieldValue.split(",");
		length = 0;
		if (newCgFieldValueArray != null) {
			length = newCgFieldValueArray.length;
		}
		for (int i = 0; i < length; i++) {
			if (newCgFieldValueArray[i].equals(path)) {
				newCgFieldValueArray[i] = realPath;
				break;
			}
		}
		newCgFieldValue = "";
		boolean flag = true;
		for (int i = 0; i < length; i++) {
			if (!newCgFieldValueArray[i].equals("0")) {
				if (flag) {
					newCgFieldValue = newCgFieldValueArray[i];
					flag = false;
				} else {
					newCgFieldValue =newCgFieldValue +  "," + newCgFieldValueArray[i];
				}
			}
		}
		request.getSession().setAttribute(
				tableName + "," + "new" + "," + cgField, newCgFieldValue);
		if(!realPath.equals(path)){
			
			FileUtils.delete(uploadBasePath + "/" + path);
		}

		j.setSuccess(true);
		j.setMsg("修改成功");
		Map<String,Object> attributes = new HashMap();
		attributes.put("path", realPath);
		attributes.put("isNewOrUpdate", "new");
		j.setAttributes(attributes);
		return j;
	}

	@RequestMapping(params = { "cancleUpload" })
	@ResponseBody
	public AjaxJson cancleUpload(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();

		String tableName = request.getParameter("tableName");
		String cgField = request.getParameter("cgField");
		String path = request.getParameter("path");

		String newCgFieldValue = (String) request.getSession().getAttribute(
				tableName + "," + "new" + "," + cgField);
		List list = exDatabaseService.getCgFormFieldByTableName(tableName);
		// ExCgFormFieldEntity keyField=head.getKeyField();
		String uploadBasePath = "default";
		int length = 0;
		if (list != null) {
			length = list.size();
		}
		for (int i = 0; i < length; i++) {
			Map map = (Map) list.get(i);
			if (map.get("field_name").equals(cgField)) {
				uploadBasePath = (String) map.get("file_path");
			}
		}

		String[] newCgFieldValueArray = newCgFieldValue.split(",");
		length = 0;
		if (newCgFieldValueArray != null) {
			length = newCgFieldValueArray.length;
		}
		for (int i = 0; i < length; i++) {
			if (newCgFieldValueArray[i].equals(path)) {
				newCgFieldValueArray[i] = "0";
				break;
			}
		}
		newCgFieldValue = "";
		boolean flag = true;
		for (int i = 0; i < length; i++) {
			if (!newCgFieldValueArray[i].equals("0")) {
				if (flag) {
					newCgFieldValue = newCgFieldValueArray[i];
					flag = false;
				} else {
					newCgFieldValue = newCgFieldValue + "," + newCgFieldValueArray[i];
				}
			}
		}
		request.getSession().setAttribute(
				tableName + "," + "new" + "," + cgField, newCgFieldValue);

		j.setSuccess(true);
		j.setMsg("删除成功");
		return j;
	}

	@RequestMapping(params = { "delFile" })
	@ResponseBody
	public AjaxJson delFile(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String idkey = request.getParameter("key");
		String id = request.getParameter("id");
		String tableName = request.getParameter("tableName");
		String path = request.getParameter("path");
		String cgField = request.getParameter("cgField");

		String delCgFieldValue = (String) request.getSession().getAttribute(
				tableName + "," + "del" + "," + cgField);

		delCgFieldValue = addCgFieldValue(delCgFieldValue, path);

		request.getSession().setAttribute(
				tableName + "," + "del" + "," + cgField, delCgFieldValue);

		/*
		 * Map map = (Map)exDatabaseService.findOneForJdbc(tableName, id);
		 * 
		 * map.remove(idkey); String cgFieldValue =(String) map.get(cgField);
		 * String cgFieldValueUpdate = "";
		 * 
		 * String[] list = null; List updateList = new ArrayList(0);
		 * if(cgFieldValue != null ) list = cgFieldValue.split(","); if(list !=
		 * null && list.length != 0){ for(int i = 0; i < list.length; i++ ){
		 * if(!list[i].equals(path) && (!list[i].equals(""))){
		 * updateList.add(list[i]); } } for(int i = 0; i < updateList.size();
		 * i++){ if(i == 0){ cgFieldValueUpdate =(String) updateList.get(i);
		 * }else{ cgFieldValueUpdate = "," + (String)updateList.get(i); } }
		 * map.put(cgField, cgFieldValueUpdate); } map = new
		 * HashMap<String,String>(); map.put(cgField, cgFieldValueUpdate);
		 * 
		 * Map attr = new HashMap(0); attr.put("cgField", cgField);
		 * attr.put("cgFieldValue", cgFieldValueUpdate);
		 * 
		 * exDatabaseService.updateTable(tableName, id, map); this.message
		 * ="被删除成功"; this.systemService.addLog(path +this.message,
		 * Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		 */
		j.setSuccess(true);
		// j.setAttributes(attr);
		message = "操作成功";
		j.setMsg(this.message);
		return j;
	}

	@RequestMapping(params = { "modifyFile" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public AjaxJson modifyFile(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		Map attributes = new HashMap();
		String fileKey = oConvertUtils.getString(request
				.getParameter("fileKey"));
		String id = oConvertUtils.getString(request.getParameter("id"));
		String tableName = oConvertUtils.getString(request
				.getParameter("tableName"));
		String cgField = oConvertUtils.getString(request
				.getParameter("cgField"));

		String oldPath = request.getParameter("path");
		ExCgFormHeadEntity head = exDatabaseService
				.getCgFormHeadByTableName(tableName);
		ExCgFormFieldEntity keyField = head.getKeyField();
		String uploadBasePath = keyField.getFile_path();
		List list = (List) request.getSession().getAttribute(
				tableName + "," + "update" + "," + cgField);
		String newPath = uploadFile(request,
				"temp/" + String.valueOf((int) (Math.random() * 100000))
						+ oldPath, cgField);
		Map map = new HashMap(0);
		map.put(oldPath, newPath);
		if (list == null) {
			list = new ArrayList(0);
		}
		list.add(map);
		request.getSession().setAttribute(
				tableName + "," + "update" + "," + cgField, list);
		message = "操作成功";
		j.setMsg(message);
		attributes.put("path", newPath);
		j.setAttributes(attributes);
		return j;
	}

}
