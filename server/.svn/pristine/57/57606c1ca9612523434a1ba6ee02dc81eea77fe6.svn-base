package jeecg.ext.sdk.controller;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.AdApp;
import jeecg.ext.sdk.entity.AdMission;
//import jeecg.ext.sdk.entity.App;
import jeecg.ext.tools.DateUtil;
import jeecg.ext.tools.GenerateSequenceUtil;
import jeecg.ext.tools.TagUtilExt;
import jeecg.system.pojo.base.TSIcon;
import jeecg.system.service.SystemService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/adMission")
public class AdMissionController  extends BaseController{
	static Log logger=LogFactory.getLog(AdMissionController.class);
	
	@Autowired
	private SystemService systemService;
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@RequestMapping(params="list")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response){
		if(request.getParameter("adId")!=null){
			request.setAttribute("adId", request.getParameter("adId"));
		}
		String showType = "";
		if(request.getParameter("showType")!=null){
			showType = request.getParameter("showType");
			request.setAttribute("showType", showType);
		}
		String url = "adMission/list"+showType;
		return new ModelAndView(url);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="datagrid")
	public void datagrid(AdMission adMission,HttpServletRequest request,HttpServletResponse response, DataGrid dataGrid){
		
		CriteriaQuery cq=new CriteriaQuery(AdMission.class,dataGrid);
		
		String showType = request.getParameter("showType");
	    if (showType != null) {
	    	adMission.setShowType(Integer.parseInt(showType));
		}
	    
		//查询条件组装器
		HqlGenerateUtil.installHql(cq, adMission);
		this.systemService.getDataGridReturn(cq, true);
		if(showType.equals("4")){
			List<AdMission> list = dataGrid.getReaults();
			List<AdMission> newList = new ArrayList<AdMission>();
			for(int i=0;i<list.size();i++){
				AdMission obj = list.get(i);
				String appId = obj.getAppId();
				AdApp app = systemService.findUniqueByProperty(AdApp.class, "adAppid", appId);
				obj.setPackageName(app.getPackagename());
				obj.setPackageNameDownload(app.getPackageName());
				obj.setVersionCode(app.getVersionCode());
				obj.setVersion(app.getVersion());
				newList.add(obj);
			}
			dataGrid.setReaults(newList);
		}
		TagUtilExt.datagrid(response, dataGrid,"id");
	}
	
	/**
	* 列表页面跳转
	* 
	* @return
	*/
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(String id, HttpServletRequest req) {
	 String missionId = GenerateSequenceUtil.generateSequenceNo();
	 AdMission adMission = new AdMission();
	 if (StringUtil.isNotEmpty(id)) {
		 adMission = systemService.getEntity(AdMission.class, Integer.valueOf(id));
	   req.setAttribute("adMission", adMission);
	   missionId = adMission.getMissionId();
	 }
	 req.setAttribute("missionid", missionId);
	 if(req.getParameter("showType")!=null){
		 req.setAttribute("adId", req.getParameter("adId"));
		 String showType = req.getParameter("showType");
		 String url = "adMission/add"+showType;
		 ModelAndView mv = new ModelAndView(url);
		 mv.addObject("adMission", adMission);
		 if(showType.equals("4")){
//			 List<App> appList = systemService.loadAll(App.class);
			 AdApp app = systemService.findUniqueByProperty(AdApp.class, "adAppid", adMission.getAppId());
			 mv.addObject("app", app);
		 }
		 return mv;
	 }
	 return null;
	}
	
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(AdMission adMission,AdApp app) {
		AjaxJson json = new AjaxJson();
		
		String missionId = GenerateSequenceUtil.generateSequenceNo();
		String appId = GenerateSequenceUtil.generateSequenceNo();
		String appKey = getAppkey();
		
		AdMission entity;
		AdApp entityApp;
		if (adMission.getId() == null) {
			entity = adMission;
			entityApp = app;
			entity.setMissionId(missionId);
			entity.setAppId(appId);
			entity.setCreateTime(DateUtil.getCurrentTime());
			entity.setLastTime(DateUtil.getCurrentTime());
			entityApp.setAdAppid(appId);
			entityApp.setAppKey(appKey);
			entityApp.setAppName(app.getAppName());
			entityApp.setCreateTime(DateUtil.getCurrentTime());
			entityApp.setLastTime(DateUtil.getCurrentTime());
			systemService.save(adMission);
			systemService.save(app);
		} else {
			entity = systemService.getEntity(AdMission.class, adMission.getId());
			entityApp = systemService.findUniqueByProperty(AdApp.class, "adAppid", entity.getAppId());
//			entityApp = systemService.getEntity(AdApp.class, app.getId());
			if (entity != null && entityApp != null) {
				entity.setLastTime(DateUtil.getCurrentTime());
				entityApp.setAppDetail(app.getAppDetail());
				entityApp.setAppName(app.getAppName());
				entityApp.setCallbackUrl(app.getCallbackUrl());
				entityApp.setDeviceToken(app.getDeviceToken());
				entityApp.setIcon(app.getIcon());
				entityApp.setImg1(app.getImg1());
				entityApp.setImg2(app.getImg2());
				entityApp.setImg3(app.getImg3());
				entityApp.setImg4(app.getImg4());
				entityApp.setImg5(app.getImg5());
				entityApp.setPackagename(app.getPackagename());
				entityApp.setPackageName(app.getPackageName());
				entityApp.setRate(app.getRate());
				entityApp.setRateUnit(app.getRateUnit());
				entityApp.setSize(app.getSize());
				entityApp.setVersion(app.getVersion());
				entityApp.setVersionCode(app.getVersionCode());
				entityApp.setLastTime(DateUtil.getCurrentTime());
			json.setMsg("修改成功");
			}
		}
		
		if (entity == null || entityApp == null) {
			json.setMsg("操作失败");
			json.setSuccess(false);
			return json;
		}
		    
		systemService.saveOrUpdate(entity);
		systemService.saveOrUpdate(entityApp);
		return json;
	}
	
	/**
	 * 上传图标
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "saveOrUpdateIcon", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson saveOrUpdateIcon(HttpServletRequest request) throws Exception {
		AjaxJson j = new AjaxJson();
		
		TSIcon icon = new TSIcon();
		Short iconType = oConvertUtils.getShort(request.getParameter("iconType"));
		String iconName = oConvertUtils.getString(request.getParameter("iconName"));
		String id = request.getParameter("id");
		icon.setId(id);
		icon.setIconName(iconName);
		icon.setIconType(iconType);
		// uploadFile.setBasePath("images/accordion");
		UploadFile uploadFile = new UploadFile(request, icon);
		uploadFile.setCusPath("plug-in/accordion/images");// 文件物理路径自定义子目录
		uploadFile.setExtend("extend");// 扩展名
		uploadFile.setTitleField("iconclas");// 文件名(标题)保存到数据库的对应实体类字段
		uploadFile.setRealPath("iconPath");// 文件保存在硬盘的全路径对应实体字段
		uploadFile.setObject(icon);// 文件对应实体对象
		uploadFile.setByteField("iconContent");// 二进制文件内容保存到数据库的对应实体类字段
		uploadFile.setRename(false);// 是否重命名
		systemService.uploadFile(uploadFile);
		// 图标的css样式
		String css = "." + icon.getIconClas() + "{background:url('../images/" + icon.getIconClas() + "." + icon.getExtend() + "') no-repeat}";
		write(request, css);
		message = "上传成功";
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 没有上传文件时更新信息
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(params = "update", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson update(HttpServletRequest request) throws Exception {
		AjaxJson j = new AjaxJson();
		Short iconType = oConvertUtils.getShort(request.getParameter("iconType"));
		String iconName = java.net.URLDecoder.decode(oConvertUtils.getString(request.getParameter("iconName")));
		String id = request.getParameter("id");
		TSIcon icon = systemService.get(TSIcon.class, id);
		icon.setId(id);
		icon.setIconName(iconName);
		icon.setIconType(iconType);
		systemService.saveOrUpdate(icon);
		// 图标的css样式
		String css = "." + icon.getIconClas() + "{background:url('../images/" + icon.getIconClas() + "." + icon.getExtend() + "') no-repeat}";
		write(request, css);
		message = "更新成功";
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 添加图标样式
	 * 
	 * @param request
	 * @param css
	 */
	protected void write(HttpServletRequest request, String css) {
		try {
			String path = request.getSession().getServletContext().getRealPath("/plug-in/accordion/css/icons.css");
			File file = new File(path);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter out = new FileWriter(file, true);
			out.write("\r\n");
			out.write(css);
			out.close();
		} catch (Exception e) {
		}
	}
	
	private String getAppkey() {
	  UUID uuid = UUID.randomUUID();
	  return uuid.toString().replace("-", "");
	}
	
}
