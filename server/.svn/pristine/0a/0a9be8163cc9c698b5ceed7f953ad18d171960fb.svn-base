package jeecg.ext.online.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.online.entity.IndexContent;
import jeecg.ext.online.service.SysConfUtils;
import jeecg.system.pojo.base.TSFunction;
import jeecg.system.pojo.base.TSRole;
import jeecg.system.pojo.base.TSRoleFunction;
import jeecg.system.pojo.base.TSRoleUser;
import jeecg.system.pojo.base.TSUser;
import jeecg.system.service.SystemService;

import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.util.FileUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/indexContentController")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IndexContentController {
	private SystemService systemService;

	@RequestMapping(params = "getContent")
	public String getContent(HttpServletRequest request,
			HttpServletResponse response) {
		TSUser user = ResourceUtil.getSessionUserName();
		SysConfUtils sysConfUtils = new SysConfUtils(systemService);
		List<TSRoleUser> rUsers = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
		String content = sysConfUtils.getSysConfKey("home", "content", rUsers.get(0).getId(), user.getId());
		request.setAttribute("content", content.replaceAll("#1#A#c#123", ","));
		return "main/home";
	}
	
	@RequestMapping(params = "getLogo")
	public void logoRequest(HttpServletRequest request,
			HttpServletResponse response){
		byte[] icon = null;
		SysConfUtils sysConfUtils = new SysConfUtils(systemService);
		TSUser user = ResourceUtil.getSessionUserName();
		String isLoginPage = request.getParameter("isLoginPage");
		String logo = "";
		if(isLoginPage !=null && isLoginPage.equals("loginPage")){
			logo = (String)sysConfUtils.getDefaultSysConf("home").get("logo");
			
		}else{
			List<TSRoleUser> rUsers = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
			String defaultSysConf = request.getParameter("default");
			boolean isDefault = defaultSysConf !=null &&defaultSysConf.equals("1")?true:false;
			if(isDefault){
				logo = (String)sysConfUtils.getDefaultSysConf("home").get("logo");
			}else{
				
				logo = sysConfUtils.getSysConfKey("home", "logo", rUsers.get(0).getId(), user.getId());
			}
		}

		icon = hex2byte(logo);
		response.setContentType("image/*"); // 设置返回的文件类型
		try {
			OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
			toClient.write(icon); // 输出数据
			toClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}
	
	

	@RequestMapping(params = "editContent")
	public String editContent(HttpServletRequest request,
			HttpServletResponse response) {
		SysConfUtils sysConfUtils = new SysConfUtils(systemService);
		TSUser user = ResourceUtil.getSessionUserName();
		List<TSRoleUser> rUsers = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
		
		Map keyMap = sysConfUtils.getSysConfKeyMap("home", rUsers.get(0).getId(), user.getId());
		IndexContent indexContent = new IndexContent();
		
		request.setAttribute("indexContent", indexContent);
		return "system/indexcontent/editIndexContent";
	}
	
	@RequestMapping(params = "edit_page")
	public ModelAndView getEditPage(HttpServletRequest request, HttpServletRequest response){
		SysConfUtils sysConfUtils = new SysConfUtils(systemService);
		TSUser user = ResourceUtil.getSessionUserName();
		List<TSRoleUser> rUsers = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
		String defaultSysConf = request.getParameter("default");
		boolean idDefault = defaultSysConf !=null &&defaultSysConf.equals("1")?true:false;
		Map keyMap = null;
		if(idDefault){
			keyMap = sysConfUtils.getDefaultSysConf("home");
		}else{
			keyMap = sysConfUtils.getSysConfKeyMap("home", rUsers.get(0).getId(), user.getId());
		}
		request.setAttribute("content", ((String)keyMap.get("content")).replaceAll("#1#A#c#123", ","));
		request.setAttribute("version", keyMap.get("version"));
		request.setAttribute("default", request.getParameter("default"));
		
		return new ModelAndView("system/indexcontent/editIndexContent");
	}

	
	@RequestMapping(params = "saveContentImage",method = RequestMethod.POST)
	public void saveContentImage(HttpServletRequest request,HttpServletResponse response){
		AjaxJson j = new AjaxJson();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest
				.getFile("upload");
		String path = request.getServletContext().getRealPath("/");
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddss");
		String fileName = file.getOriginalFilename();
		String extend = FileUtils.getExtend(fileName);
		String relPath = "index_content/" + sf.format(new Date()) +"." + extend;
		
		File dirPath = new File(path + "/" + relPath);
		File dir = new File(path + "/" + "index_content");
		if(!dir.exists()){
			dir.mkdir();
		}
		if(file.getSize() != 0){
			try {
				if(file.getSize() != 0)
					file.transferTo(dirPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			response.sendRedirect("webpage/ext/common/ckeditor_indexcontent/index_content_img.jsp?imgUrl="+relPath+"&fnNum="+request.getParameter("CKEditorFuncNum") );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(params = "saveFiles", method = RequestMethod.POST)
	public ModelAndView saveFiles(HttpServletRequest request,
			HttpServletResponse response, Object document) {
		AjaxJson j = new AjaxJson();
		SysConfUtils sysConfUtils = new SysConfUtils(systemService);
		Map<String, Object> attributes = new HashMap<String, Object>();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest
				.getFile("file");
		String path = request.getServletContext().getRealPath("/");
		File dirPath = new File(path + "/plug-in/login/images/toplogo-main.png");
		String version = request.getParameter("version");
		String content = request.getParameter("content");
		String newContent = content.replaceAll(",", "#1#A#c#123");
		String logo = "";
		if(file.getSize() != 0){
			logo = byte2hex(file.getBytes());
		}
		TSUser user = ResourceUtil.getSessionUserName();
		String user_id = user.getId();
		String defaultSysConf = request.getParameter("default");
		boolean isDefault = defaultSysConf !=null &&defaultSysConf.equals("1")?true:false;
		sysConfUtils.saveSysConfKey("home", "version", version, user_id, isDefault);
		sysConfUtils.saveSysConfKey("home", "content", newContent, user_id,isDefault);
		if(!logo.equals(""))
			sysConfUtils.saveSysConfKey("home", "logo", logo, user_id,isDefault);
		attributes.put("status", "0");
		j.setAttributes(attributes);
		request.setAttribute("content", content);
		request.setAttribute("version", version);
		request.setAttribute("default", defaultSysConf);
		return new ModelAndView("system/indexcontent/editIndexContent");
	}
	
	public static String byte2hex(byte[] b) // 二进制转字符串
	{
		StringBuffer sb = new StringBuffer();
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0XFF);
			if (stmp.length() == 1) {
				sb.append("0" + stmp);
			} else {
				sb.append(stmp);
			}
		}
		return sb.toString();
	}

	public static byte[] hex2byte(String str) { // 字符串转二进制
		if (str == null)
			return null;
		str = str.trim();
		int len = str.length();
		if (len == 0 || len % 2 == 1)
			return null;
		byte[] b = new byte[len / 2];
		try {
			for (int i = 0; i < str.length(); i += 2) {

				b[i / 2] = (byte) Integer

				.decode("0X" + str.substring(i, i + 2)).intValue();
			}
			return b;
		} catch (Exception e) {
			return null;
		}
	}

	
	
	
	@RequestMapping( params = "getFolderFunctionList")
	public ModelAndView getFolderFunctionList(HttpServletRequest request){
		List<TSFunction> parentFunction =(List<TSFunction>)systemService.findByProperty(TSFunction.class, "functionLevel" , Short.valueOf("0") );
		
		
		TSUser u = ResourceUtil.getSessionUserName();
		// 登陆者的权限
		List<TSFunction> ownList = new ArrayList();// 已有权限菜单
		List<TSRoleUser> rUsers = systemService.findByProperty(TSRoleUser.class, "TSUser.id", u.getId());
		for (TSRoleUser ru : rUsers) {
			TSRole role1 = ru.getTSRole();
			List<TSRoleFunction> roleFunctionList1 = systemService.findByProperty(TSRoleFunction.class, "TSRole.id", role1.getId());
			if (roleFunctionList1.size() > 0) {
				for (TSRoleFunction roleFunction : roleFunctionList1) {
					TSFunction function = (TSFunction) roleFunction.getTSFunction();
					ownList.add(function);
				}
			}
		}
		
		boolean isAdmin = false;
		for(TSRoleUser ru: rUsers){
			TSRole tmp = ru.getTSRole();
			if(tmp.getRoleCode().equals("admin")){
				isAdmin = true;
			}	
		}
		if(!isAdmin){
			
			parentFunction = filterAuthorityFunction(parentFunction, ownList);
		}
		
		request.setAttribute("folderFunctionList", parentFunction);
		return new ModelAndView("system/role/role");
		
	}
	
	public List<TSFunction> filterAuthorityFunction(List<TSFunction> functionList, List<TSFunction> ownList){

		functionList.retainAll(ownList);
	
		return functionList;
	}
	@RequestMapping( params = "getChildrenFunction" , method =RequestMethod.POST)
	@ResponseBody
	public AjaxJson getChildrenFunction(HttpServletRequest request){
		AjaxJson json = new AjaxJson();
		String parentFunctionId = request.getParameter("parentFunctionId");
		List<TSFunction> list =(List<TSFunction>)systemService.findByProperty(TSFunction.class, "id" , parentFunctionId);
		TSUser u = ResourceUtil.getSessionUserName();
		// 登陆者的权限
		List<TSFunction> ownList = new ArrayList();// 已有权限菜单
		List<TSRoleUser> rUsers = systemService.findByProperty(TSRoleUser.class, "TSUser.id", u.getId());
		for (TSRoleUser ru : rUsers) {
			TSRole role1 = ru.getTSRole();
			List<TSRoleFunction> roleFunctionList1 = systemService.findByProperty(TSRoleFunction.class, "TSRole.id", role1.getId());
			if (roleFunctionList1.size() > 0) {
				for (TSRoleFunction roleFunction : roleFunctionList1) {
					TSFunction function = (TSFunction) roleFunction.getTSFunction();
					ownList.add(function);
				}
			}
		}
		
		boolean isAdmin = false;
		for(TSRoleUser ru: rUsers){
			TSRole tmp = ru.getTSRole();
			if(tmp.getRoleCode().equals("admin")){
				isAdmin = true;
			}	
		}
		if(!isAdmin){
			
			list = filterAuthorityFunction(list, ownList);
		}
		
		TSFunction parent = null;
		if(list != null && list.size() > 0){
			parent = list.get(0);
			List<TSFunction> funcs = parent.getTSFunctions();
			List<Map<String,String>> list0 = new ArrayList();
			for(int i = 0; i < funcs.size(); i++){
				Map map = new HashMap(0);
				map.put("id", funcs.get(i).getId());
				map.put("functionName", funcs.get(i).getFunctionName());
				list0.add(map);
			}
			json.setObj(list0);
			
		}
		json.setSuccess(true);
		json.setMsg("dfdfdfd");
		return json;
	}

}
