package jeecg.ext.online.controller;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jeecg.cgform.common.CommUtils;
import jeecg.cgform.entity.upload.CgUploadEntity;
import jeecg.cgform.exception.BusinessException;
import jeecg.ext.online.db.ExDatabaseService;
import jeecg.ext.online.db.entity.ExCgFormFieldEntity;
import jeecg.ext.online.db.entity.ExCgFormHeadEntity;
import jeecg.ext.online.db.entity.ExCgSubTableVO;
import jeecg.ext.online.ftl.ExTemplateContext;
import jeecg.ext.tools.OnlineHeadFields;
import jeecg.system.pojo.base.TSFunction;
import jeecg.system.pojo.base.TSIcon;
import jeecg.system.pojo.base.TSRole;
import jeecg.system.pojo.base.TSRoleFunction;
import jeecg.system.service.SystemService;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import freemarker.template.Template;

@Controller
@RequestMapping("/exCgFormBuildController")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ExCgFormBuildController extends BaseController {
	static Log logger = LogFactory.getLog(ExCgFormBuildController.class);

	@RequestMapping(params = "ftlForm")
	public void ftlForm(HttpServletRequest request, HttpServletResponse response) {
		try {
			String tableName = request.getParameter("tableName");

			ExCgFormHeadEntity head = exDatabaseService
					.getCgFormHeadByTableName(tableName);
			ExCgFormFieldEntity keyField = head.getKeyField();
			String keyid = keyField.getFieldName();
			clearSessionBeforeFormer(request, head);
			Template template = exTemplateContext.getTemplate(tableName);
			StringWriter stringWriter = new StringWriter();
			BufferedWriter writer = new BufferedWriter(stringWriter);

			Map data = new HashMap();
			String id = request.getParameter(keyid);
			if (StringUtils.isNotEmpty(id)) {
				data = exDatabaseService.findOneForJdbc(tableName, id);
				data.remove(keyid);
			} else {
				initFormData(data, head);
			}
			Map tableData = new HashMap();
			tableData.put(tableName, data);

			data.put("idKey", keyid);
			int newOrUpdateFlag = 0; // 0 :new 1: update
			if (StringUtils.isNotEmpty(id)) {
				newOrUpdateFlag = 1;
				String idHidden = "<input type='hidden' id='row_id' name='"
						+ keyid + "' value='" + id + "'/>";

				data.put("idHidden", idHidden);
				data.put("newOrUpdate", newOrUpdateFlag);
			} else {
				newOrUpdateFlag = 0;
				data.put("idHidden", "");
				data.put("newOrUpdate", newOrUpdateFlag);
			}

			if ("Y".equalsIgnoreCase(keyField.getIsShow())) {
				data.put("idLabel", keyField.getContent());

				String idInput = "<font color='gray'>(自动产生)</font>";
				if (StringUtils.isNotEmpty(id)) {
					idInput = id;
				} else if (StringUtils.isEmpty(keyField.getFieldValue())
						|| "manual".equalsIgnoreCase(keyField.getFieldValue())) {
					// 手动输入主键
					idInput = "<input name='" + keyid + "' style='width: "
							+ keyField.getFieldLength()
							+ "px' class='inputxt' value=''/>";
				}
				data.put("idInput", idInput);
			} else {
				data.put("idLabel", "");
			}

			data.put("head", head);
			Map field = new HashMap();
			if (head.getJformType() == 2) {
				List fieldList = exDatabaseService
						.getCgFormFieldByTableName(tableName);
				data.put("columns", fieldList);

				ExCgSubTableVO subtableVo = new ExCgSubTableVO();
				String subTableStr = head.getSubTableStr();
				if (StringUtils.isNotEmpty(subTableStr)) {
					String subTables[] = subTableStr.split(",");
					List subTableData = new ArrayList();
					List subTalbeFieldList = new ArrayList();
					String as[] = subTables;
					int i = 0;
					for (int j = as.length; i < j; i++) {
						String subTable = as[i];
						if (StringUtils.isNotEmpty(id)) {
							subTableData = exDatabaseService.getSubTableData(
									tableName, subTable, id);
							tableData.put(subTable, subTableData);
						}
						subTalbeFieldList = exDatabaseService
								.getCgFormFieldByTableName(subTable);
						ExCgFormHeadEntity subhead = exDatabaseService
								.getCgFormHeadByTableName(subTable);
						subtableVo = new ExCgSubTableVO();
						subtableVo.setHead(subhead);
						subtableVo.setFieldList(subTalbeFieldList);
						field.put(subTable, subtableVo);
					}
				}
			} else {
				Map cgformFtlEntity = exDatabaseService
						.getCgformFtlByTableName(tableName);
				if (cgformFtlEntity == null) {
					List fieldList = exDatabaseService
							.getCgFormFieldByTableName(tableName);
					data.put("columns", fieldList);
				}
			}
			data.put("data", tableData);
			data.put("field", field);
			data.put("tableName", tableName);
			pushFiles(data, id);
			ExCgFormHeadEntity cgFormHeadEntity = exDatabaseService
					.getCgFormHeadByTableName(tableName);

			if (StringUtils.isNotEmpty(cgFormHeadEntity.getJsPlugIn())) {
				data.put("js_plug_in", cgFormHeadEntity.getJsPlugIn());
			}

			template.process(data, writer);
			String content = stringWriter.toString();
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(content);
		} catch (Exception e) {
			logger.error("" + e, e);
		}
	}

	public void clearSessionBeforeFormer(HttpServletRequest request,
			ExCgFormHeadEntity head) {
		String tableName = head.getTableName();
		for (ExCgFormFieldEntity f : head.getCgformFields()) {
			String cgField = f.getFieldName();
			request.getSession().removeAttribute(
					tableName + "," + "del" + "," + cgField);
			request.getSession().removeAttribute(
					tableName + "," + "update" + "," + cgField);
			request.getSession().removeAttribute(
					tableName + "," + "new" + "," + cgField);
		}
	}

	private void initFormData(Map data, ExCgFormHeadEntity head) {
		ExCgFormFieldEntity keyField = head.getKeyField();
		for (ExCgFormFieldEntity f : head.getCgformFields()) {
			if (f != keyField) {
				String v = f.getFieldValue();
				if (StringUtils.isNotEmpty(v)) {
					v = v.toLowerCase().trim();
					if (v.startsWith("uuid")) {
						v = UUID.randomUUID().toString().replaceAll("-", "");
					} else if (v.startsWith("now")) {
						v = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
								.format(new Date());
					} else if (v.startsWith("rand(")) {
						int p = v.indexOf(")");
						if (p > 0) {
							int n = Integer.parseInt(v.substring(5, p));
							v = getRandomString(n, 0);
						}
					} else if (v.startsWith("rands(")) {
						int p = v.indexOf(")");
						if (p > 0) {
							int n = Integer.parseInt(v.substring(6, p));
							v = getRandomString(n, 1);
						}
					} else if (v.startsWith("randn(")) {
						int p = v.indexOf(")");
						if (p > 0) {
							int n = Integer.parseInt(v.substring(6, p));
							v = getRandomString(n, 2);
						}
					} else {
						v = f.getFieldValue();
					}
					data.put(f.getFieldName(), v);
				}
			}
		}
	}

	private String getRandomString(int length, int type) {
		String[] bases = { "abcdefghijklmnopqrstuvwxyz0123456789",
				"abcdefghijklmnopqrstuvwxyz", "0123456789" };
		String base = bases[type]; // 生成字符串从此序列中取

		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	private void pushFiles(Map data, String id) {
		List uploadBeans = exDatabaseService
				.findByProperty(
						jeecg.cgform.entity.upload.CgUploadEntity.class,
						"cgformId", id);
		List files = new ArrayList(0);
		Map file;
		for (Iterator iterator = uploadBeans.iterator(); iterator.hasNext(); files
				.add(file)) {
			CgUploadEntity b = (CgUploadEntity) iterator.next();
			String title = b.getAttachmenttitle();
			String fileKey = b.getId();
			String path = b.getRealpath();
			String field = b.getCgformField();
			file = new HashMap();
			file.put("title", title);
			file.put("fileKey", fileKey);
			file.put("path", path);
			file.put("field", field != null ? ((Object) (field)) : "");
		}
		data.put("filesList", files);
	}

	public String addNewCgFieldValue(String cgFieldValue, String newCgFieldValue) {
		if (cgFieldValue == null || cgFieldValue.equals("")) {
			return newCgFieldValue;
		} else {
			cgFieldValue = cgFieldValue + "," + newCgFieldValue;
		}
		return cgFieldValue;
	}

	public String delCgFieldValue(String cgFieldValue, String delCgFieldValue) {
		int cgLength = 0;
		int delLength = 0;
		String[] cgList = null;
		String[] delList = null;
		if (cgFieldValue != null && (!cgFieldValue.equals(""))) {
			cgList = cgFieldValue.split(",");
			cgLength = cgList.length;
		}
		if (delCgFieldValue != null && (!delCgFieldValue.equals(""))) {
			delList = delCgFieldValue.split(",");
			delLength = delList.length;
		}

		for (int i = 0; i < delLength; i++) {
			String temp = delList[i];
			for (int j = 0; j < cgLength; j++) {
				if (temp.equals(cgList[j])) {
					cgList[j] = "no";
					break;
				}
			}
		}
		String returnValue = "";
		boolean flag = false;
		for (int i = 0; i < cgLength; i++) {
			if (!cgList[i].equals("no")) {
				if (!flag) {
					returnValue = cgList[i];
					flag = true;
				} else {
					returnValue = returnValue + "," + cgList[i];
				}
			}
		}

		return returnValue;
	}

	public void updateFile(String tableName, String contextPath,
			Map<String, List> map, Map data) {
		int length = 0;

		for (Map.Entry<String, List> entity : map.entrySet()) {
			String cgField = entity.getKey();
			List updateList = entity.getValue();
			int listLength = updateList.size();
			List list = exDatabaseService.getCgFormFieldByTableName(tableName);
			// ExCgFormFieldEntity keyField=head.getKeyField();
			String uploadBasePath = "default";
			int tmplength = 0;
			if (list != null) {
				tmplength = list.size();
			}
			for (int i = 0; i < tmplength; i++) {
				Map tmpMap = (Map) list.get(i);
				if (tmpMap.get("field_name").equals(cgField)) {
					uploadBasePath = (String) tmpMap.get("file_path");
				}
			}
			String[] cgFieldValue = ((String) data.get(cgField)).split(",");
			for (int i = 0; i < listLength; i++) {
				Map<String, String> tmpPathMap = (Map<String, String>) updateList
						.get(i);
				for (Map.Entry<String, String> tmp : tmpPathMap.entrySet()) {
					String oldPath = tmp.getKey();
					String newPath = tmp.getValue().substring(0,
							tmp.getValue().length());
					try {
						FileUtils
								.copyFile(
										contextPath + "/" + tmp.getValue(),
										uploadBasePath
												+ "/"
												+ newPath.substring(10,
														newPath.length()));
						FileUtils.delete(contextPath + "/" + tmp.getValue());
					} catch (FileNotFoundException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					for (int j = 0; j < cgFieldValue.length; j++) {
						if (cgFieldValue[j].equals(oldPath)) {
							cgFieldValue[j] = newPath.substring(10,
									newPath.length());
							break;
						}
					}
				}

			}
			String cgValue = "";
			for (int i = 0; i < cgFieldValue.length; i++) {
				if (i == 0) {
					cgValue = cgFieldValue[i];
				} else {
					cgValue = cgValue + "," + cgFieldValue[i];
				}
			}
			data.put(cgField, cgValue);
		}
	}

	@RequestMapping(params = "saveOrUpdate")
	@ResponseBody
	public AjaxJson saveOrUpdate(HttpServletRequest request) throws Exception {
		AjaxJson j = new AjaxJson();
		Map data = request.getParameterMap();
		String newOrUpdate = String
				.valueOf(request.getParameter("newOrUpdate"));
		if (data != null) {
			data = CommUtils.mapConvert(data);
			String tableName = (String) data.get("tableName");
			ExCgFormHeadEntity head = exDatabaseService
					.getCgFormHeadByTableName(tableName);
			ExCgFormFieldEntity keyField = head.getKeyField();

			String id = (String) data.get(keyField.getFieldName());

			/*
			 * Map files =
			 * (Map)request.getSession().getAttribute(tableName+"files");
			 * 
			 * if(files != null){ String fileField = (String)
			 * files.get("cgFormField"); String fileFieldValue =
			 * (String)files.get("cgFieldValue"); data.put(fileField,
			 * fileFieldValue); }
			 */

			if (newOrUpdate.equals("0")) {
				String filterName[] = { "tableName", "saveOrUpdate",
						"newOrUpdate" };
				data = CommUtils.attributeMapFilter(data, filterName);
				Set<ExCgFormFieldEntity> fields = head.getCgformFields();
				Iterator<ExCgFormFieldEntity> it = fields.iterator();
				List<String> list = new ArrayList<String>();
				while (it.hasNext()) {
					ExCgFormFieldEntity cgFormFieldEntity = it.next();
					String cgField = cgFormFieldEntity.getFieldName();
					String cgFieldValue = (String) data.get(cgField);
					String newCgFieldValue = (String) request.getSession()
							.getAttribute(
									tableName + "," + "new" + "," + cgField);
					request.getSession().removeAttribute(
							tableName + "," + "new" + "," + cgField);
					if (newCgFieldValue != null) {
						cgFieldValue = newCgFieldValue;
						data.put(cgField, cgFieldValue);
					}
				}

				try {
					int num = exDatabaseService.insertTable(tableName, data);
					TSFunction tsFunc = buildAndSaveFunction(data, tableName);
					String defaultRoleId = "4028db81407fdfbc01407fe0222a003a";
					addDefaultRoled(defaultRoleId, tsFunc);
					request.getServletContext().setAttribute("functionReload", "1");

					if (num > 0) {
						j.setSuccess(true);
						message = "添加成功";
					} else {
						j.setSuccess(false);
						message = "添加失败";
					}
				} catch (Exception e) {
					logger.error("" + e, e);
					j.setSuccess(false);
					message = "添加异常";
				}
			} else {
				/*
				 * String filterName[] = {"tableName", "saveOrUpdate",
				 * keyField.getFieldName() }; data =
				 * CommUtils.attributeMapFilter(data, filterName);
				 */
				Set<ExCgFormFieldEntity> fields = head.getCgformFields();
				Iterator<ExCgFormFieldEntity> it = fields.iterator();
				Map map = new HashMap(0);
				while (it.hasNext()) {
					ExCgFormFieldEntity cgFormFieldEntity = it.next();
					String cgField = cgFormFieldEntity.getFieldName();
					String cgFieldValue = (String) data.get(cgField);
					String delCgFieldValue = (String) request.getSession()
							.getAttribute(
									tableName + "," + "del" + "," + cgField);
					request.getSession().removeAttribute(
							tableName + "," + "del" + "," + cgField);
					List updateList = (List) request.getSession().getAttribute(
							tableName + "," + "update" + "," + cgField);
					request.getSession().removeAttribute(
							tableName + "," + "update" + "," + cgField);
					String newCgFieldValue = (String) request.getSession()
							.getAttribute(
									tableName + "," + "new" + "," + cgField);
					request.getSession().removeAttribute(
							tableName + "," + "new" + "," + cgField);
					if (newCgFieldValue != null) {
						cgFieldValue = newCgFieldValue;
					}
					if (delCgFieldValue != null) {
						cgFieldValue = delCgFieldValue(cgFieldValue,
								delCgFieldValue);
					}
					if (updateList != null) {
						map.put(cgField, updateList);
					}
					if (newCgFieldValue != null || delCgFieldValue != null
							|| updateList != null) {
						data.put(cgField, cgFieldValue);
					}
				}

				try {
					String filterName[] = { "tableName", "saveOrUpdate",
							keyField.getFieldName(), "newOrUpdate" };
					updateFile(tableName, request.getServletContext()
							.getRealPath("/"), map, data);
					data = CommUtils.attributeMapFilter(data, filterName);
					int num = exDatabaseService
							.updateTable(tableName, id, data);

					if (num > 0) {
						j.setSuccess(true);
						message = "更新成功";
					} else {
						j.setSuccess(false);
						message = "更新失败";
					}
				} catch (Exception e) {
					logger.error("" + e, e);
					j.setSuccess(false);
					message = "更新异常";
				}
			}
		}
		j.setMsg(message);
		j.setObj(data);
		return j;
	}
	@RequestMapping(params = "openViewFile")
	public ModelAndView openViewFile(HttpServletRequest request) {
		request.setAttribute("tableName", request.getParameter("tableName"));
		request.setAttribute("cgfield", request.getParameter("cgfield"));
		request.setAttribute("path", request.getParameter("path"));
		String filePath = request.getParameter("path");
		String extend  = filePath.substring(filePath.lastIndexOf(".") + 1);
		
		boolean isPicture = FileUtils.isPicture(extend);
		request.setAttribute("isPicture", isPicture);
		return new ModelAndView("ext/common/upload/view_file");

	}

	@RequestMapping(params = "viewFile")
	@ResponseBody
	public void viewFile(HttpServletRequest request,
			HttpServletResponse response) {
		String tableName = request.getParameter("tableName");
		String cgField = request.getParameter("cgfield");
		String path = request.getParameter("path");

		String filePath = "";

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
		
		if(tableName==null || tableName.equals("")){
			filePath = request.getServletContext()
					.getRealPath("/") + "/" + path;
		}else{
			
			filePath = uploadBasePath + "/" + path;
		}

		String extend  = filePath.substring(filePath.lastIndexOf(".") + 1);
	
		boolean isPicture = FileUtils.isPicture(extend);
		try {
			FileInputStream is = new FileInputStream(filePath);
			int i = is.available(); // 得到文件大小
			byte data[] = new byte[i];
			is.read(data); // 读数据
			is.close();
			if(isPicture){
				response.setContentType("image/*"); // 设置返回的文件类型
			}else{
				response.setContentType("multipart/form-data");
				response.setHeader("Content-Disposition", "attachment;fileName="+filePath.substring(filePath.lastIndexOf("/")+ 1));
			}
			OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
			toClient.write(data); // 输出数据
			toClient.close();
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}

	@RequestMapping(params = "fileRequest")
	@ResponseBody
	public void fileRequest(HttpServletRequest request,
			HttpServletResponse response) {

		String tableName = request.getParameter("tableName");
		String cgField = request.getParameter("cgfield");
		String path = request.getParameter("path");

		String filePath = "";

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

		if(tableName==null || tableName.equals("")){
			filePath = request.getServletContext()
					.getRealPath("/") + "/" + path;
		}else{
			
			filePath = uploadBasePath + "/" + path;
		}
		try {
			FileInputStream is = new FileInputStream(filePath);
			int i = is.available(); // 得到文件大小
			byte data[] = new byte[i];
			is.read(data); // 读数据
			is.close();
			response.setContentType("image/*"); // 设置返回的文件类型
			OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
			toClient.write(data); // 输出数据
			toClient.close();
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}

	@RequestMapping(params = "saveOrUpdateMore")
	@ResponseBody
	public AjaxJson saveOrUpdateMore(HttpServletRequest request)
			throws Exception {
		AjaxJson j = new AjaxJson();
		Map data = request.getParameterMap();
		String newOrUpdate  = String.valueOf(request.getParameter("newOrUpdate"));
		if (data != null) {
			data = CommUtils.mapConvert(data);
			String tableName = (String) data.get("tableName");
			ExCgFormHeadEntity head = exDatabaseService
					.getCgFormHeadByTableName(tableName);
			ExCgFormFieldEntity keyField = head.getKeyField();

			String id = (String) data.get(keyField.getFieldName());
			String filterName[] = {"newOrUpdate"};
			data = CommUtils.attributeMapFilter(data, filterName);
			
			// 处理 SQL语句 单引号问题。
			String sql =(String) data.get("cgr_sql");
			if(sql != null && !sql.equals("")){
				data.put("cgr_sql", StringEscapeUtils.escapeSql(sql));
			}


			Map mapMore = CommUtils.mapConvertMore(data, tableName);
			if (newOrUpdate.equals("0")){
				logger.info("一对多添加!!!!!");

				try {
					Map result = exDatabaseService.insertTableMore(mapMore,
							tableName);
					data.put("id", result.get("id"));
					TSFunction tsFunc = buildAndSaveFunction(data, tableName);
					String defaultRoleId = "4028db81407fdfbc01407fe0222a003a";
					addDefaultRoled(defaultRoleId, tsFunc);
					request.getServletContext().setAttribute("functionReload", "1");
					j.setSuccess(true);
					message = "添加成功";
				} catch (BusinessException e) {
					logger.error("" + e, e);
					j.setSuccess(false);
					message = e.getMessage();
				}
			} else {
				logger.info("一对多修改!!!!!");
				try {
					exDatabaseService.updateTableMore(mapMore, tableName);
					j.setSuccess(true);
					message = "修改成功";
				} catch (BusinessException e) {
					logger.error("" + e, e);
					j.setSuccess(false);
					message = e.getMessage();
				}
			}
		}
		j.setMsg(message);
		j.setObj(data);
		return j;
	}
	
	public void addDefaultRoled(String roleId, TSFunction tsFunc){
		TSRoleFunction tsRoleFunction = new TSRoleFunction();
		tsRoleFunction.setTSFunction(tsFunc);
		TSRole tsRole = new TSRole();
		tsRole.setId(roleId);
		tsRoleFunction.setTSRole(tsRole);
		systemService.save(tsRoleFunction);
	}
	
	public TSFunction buildAndSaveFunction(Map data, String tableName){
		String id = null;
		String url = null;
		if(tableName.equals("jform_cgreport_head")){
			
			id =(String)data.get("code");
			url = "exCgReportController.do?list&id=" + id;
			
		}else if (tableName.equals("cgform_head")){
			id = (String)data.get("table_name");
			url = "exCgAutoListController.do?list&id=" + id;	
		}
		
		if(!(id == null && url == null)){
			return buildDefaultFunction(id, url);	
		}
		return null;
	}
	
	public TSFunction buildDefaultFunction(String id, String url){
		TSFunction tsFunc = new TSFunction();
		TSFunction parent = getDefaultTSFuncParent("");
		tsFunc.setFunctionName(id);
		tsFunc.setFunctionUrl(url);
		tsFunc.setTSFunction(parent);
		TSIcon tsIcon = new TSIcon();
		tsIcon.setId("4028db81407fdfbc01407fe021070002");
		tsFunc.setTSIcon(tsIcon);
		tsFunc.setFunctionOrder(String.valueOf(parent.getTSFunctions().size() + 1)); //查出parent的TSFunction的数目 +１
		tsFunc.setFunctionLevel(Short.valueOf("1"));
		tsFunc.setTSFunctions(null);
		systemService.save(tsFunc);
		return tsFunc;
	}
	public TSFunction getDefaultTSFuncParent(String defaultId){
		defaultId = "0000db8111139000014273a112360001";
		List<TSFunction> list = (List<TSFunction>)exDatabaseService.findByProperty(TSFunction.class, "functionName", "默认菜单");
		TSFunction tsFun = null;
		if(list == null || list.size() == 0){
			tsFun = new TSFunction();
			tsFun.setTSFunction(null);
			tsFun.setId(defaultId);
			tsFun.setFunctionName("默认菜单");
			TSIcon tsIcon = new TSIcon();
			tsIcon.setId("4028db81407fdfbc01407fe021180007");
			tsFun.setTSIcon(tsIcon);
			tsFun.setFunctionLevel(Short.valueOf("0"));
			systemService.save(tsFun);
		}else{
			tsFun = list.get(0);
		}
		return tsFun;
	}
	
	

	private String message;

	@Resource(name = "exTemplateContext")
	private ExTemplateContext exTemplateContext;
	
	private SystemService systemService;

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}
	@Autowired
	private ExDatabaseService exDatabaseService;

	@Autowired
	private OnlineHeadFields onlineHeadFields;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ExTemplateContext getExTemplateContext() {
		return exTemplateContext;
	}

	public void setExTemplateContext(ExTemplateContext templetContext) {
		this.exTemplateContext = templetContext;
	}

	public ExDatabaseService getExDatabaseService() {
		return exDatabaseService;
	}

	public void setExDatabaseService(ExDatabaseService exDatabaseService) {
		this.exDatabaseService = exDatabaseService;
	}

	public OnlineHeadFields getOnlineHeadFields() {
		return onlineHeadFields;
	}

	public void setOnlineHeadFields(OnlineHeadFields onlineHeadFields) {
		this.onlineHeadFields = onlineHeadFields;
	}
}
