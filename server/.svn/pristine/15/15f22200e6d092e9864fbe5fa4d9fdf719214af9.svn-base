package jeecg.ext.sdk.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.SdkOutChannel;
import jeecg.ext.sdk.entity.SdkOutChannelDetail;
import jeecg.system.pojo.base.TSDepart;
import jeecg.system.pojo.base.TSRole;
import jeecg.system.pojo.base.TSRoleUser;
import jeecg.system.pojo.base.TSUser;
import jeecg.system.service.SystemService;
import jeecg.system.service.UserService;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.hibernate.qbc.HqlQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.PasswordUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/outChannel")
public class OutChannelController extends BaseController{

	private static final Logger logger = Logger.getLogger(OutChannelController.class);
	
	private String statsSqlString="SELECT channel_id as channelId, SUM(reg_number) as regNumber, DATE_FORMAT(input_time,'%Y-%m-%d') as inputTime " +
			"FROM `game_sdk`.`sdk_out_channel_detail` where channel_id={channel_id} and {wheretime} GROUP BY TO_DAYS(input_time)";
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private UserService userService;
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@RequestMapping(params="listone")
	public ModelAndView listone(){
		return new ModelAndView("outChannel/listone");
	}
	
	@RequestMapping(params="listtwo")
	public ModelAndView listtwo(){
		return new ModelAndView("outChannel/listtwo");
	}
	
	@RequestMapping(params="listthree")
	public ModelAndView listthree(){
		return new ModelAndView("outChannel/listthree");
	}
	@RequestMapping(params="getThreeList")
	public void getThreeList(SdkOutChannelDetail sdkOutChannelDetail,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid){
		TSUser user=ResourceUtil.getSessionUserName();
		String outChannelId=user.getUserName();
		if(outChannelId.contains(ResourceUtil.getConfigByName("outChannelPartner", "outChannel"))){
			outChannelId=outChannelId.replace(ResourceUtil.getConfigByName("outChannelPartner", "outChannel"), "");
			String exeSqlString=statsSqlString.replace("{channel_id}", outChannelId);
			String ctBegin = request.getParameter("inputTime_begin");
			String ctEnd = request.getParameter("inputTime_end");
			if(StringUtil.isNotEmpty(ctBegin) && StringUtil.isNotEmpty(ctEnd)){
				exeSqlString=exeSqlString.replace("{wheretime}", " (input_time BETWEEN '"+ctBegin+"' and '"+ctEnd+"') ");
			}else{
				exeSqlString=exeSqlString.replace("{wheretime}", " 1=1 ");
			}
			List<Map<String, Object>> maps=this.systemService.findForJdbc(exeSqlString,null);
			dataGrid.setReaults(getStatList(maps));
			TagUtil.datagrid(response, dataGrid);
		}
		
	}
	private List<StatsOutChannel> getStatList(List<Map<String, Object>> maps){
		List<StatsOutChannel> list=new ArrayList<OutChannelController.StatsOutChannel>();
		for(Map<String, Object> map:maps){
			StatsOutChannel statsOutChannel=new StatsOutChannel();
			SdkOutChannel outChannel=this.systemService.getEntity(SdkOutChannel.class, oConvertUtils.getInt(map.get("channelId").toString(),0));
			statsOutChannel.setSdkOutChannel(outChannel);
			statsOutChannel.setInputTime(map.get("inputTime").toString());
			statsOutChannel.setRegNumber(oConvertUtils.getInt(map.get("regNumber"), 0));
			list.add(statsOutChannel);
		}
		return list;
	}
	@RequestMapping(params="getOneList")
	public void getOneList(SdkOutChannel sdkOutChannel ,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid){
		CriteriaQuery cq=new CriteriaQuery(SdkOutChannel.class,dataGrid);
		cq.addOrder("createTime", SortDirection.desc);
		cq.add();
		String gameName=request.getParameter("gameName");
		if(StringUtils.isNotEmpty(gameName)&&StringUtils.isNotBlank(gameName)){
			cq.add(Restrictions.like("gameName", "%"+gameName+"%"));
		}
		//HqlGenerateUtil.installHql(cq, sdkOutChannel);
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	@RequestMapping(params="getTwoList")
	public void getTwoList(SdkOutChannelDetail sdkOutChannelDetail,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid){
		CriteriaQuery cq=new CriteriaQuery(SdkOutChannelDetail.class,dataGrid);
		cq.addOrder("sdkOutChannel.id", SortDirection.desc);
		cq.add();
		String sdkOutChannel_name=request.getParameter("sdkOutChannel_name");
		logger.info("the sdkOutChannel_name is : "+sdkOutChannel_name);
		if(StringUtils.isNotEmpty(sdkOutChannel_name)&&StringUtils.isNotBlank(sdkOutChannel_name)){
			cq.createAlias("sdkOutChannel", "socd");
			cq.add(Restrictions.like("socd.name", "%"+sdkOutChannel_name+"%"));
		}
		cq.addOrder("createTime", SortDirection.desc);
		cq.add();
		HqlGenerateUtil.installHql(cq, sdkOutChannelDetail);
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	@RequestMapping(params="addorupdateOutChannel")
	public ModelAndView addorupdateOutChannel(SdkOutChannel sdkOutChannel,HttpServletRequest request){
		if(StringUtil.isNotEmpty(sdkOutChannel.getId())){
			//更新
			SdkOutChannel outChannel=this.systemService.getEntity(SdkOutChannel.class, sdkOutChannel.getId());
			request.setAttribute("outChannel", outChannel);
		}
		return new ModelAndView("outChannel/addorupdateOutChannel");
	}
	
	@RequestMapping(params="addorupdateOutChannelDetail")
	public ModelAndView addorupdateOutChannelDetail(SdkOutChannelDetail sdkOutChannelDetail,HttpServletRequest request){
		if(StringUtil.isNotEmpty(sdkOutChannelDetail.getId())){
			SdkOutChannelDetail outChannelDetail=this.systemService.getEntity(SdkOutChannelDetail.class, sdkOutChannelDetail.getId());
			request.setAttribute("outChannelDetail", outChannelDetail);
		}
		request.setAttribute("sdkOutChannelList",this.systemService.getList(SdkOutChannel.class));
		return new ModelAndView("outChannel/addorupdateOutChannelDetail");
	}
	
	@RequestMapping(params="saveOutChannel")
	@ResponseBody
	public AjaxJson saveOutChannel(HttpServletRequest request,SdkOutChannel sdkOutChannel){
		AjaxJson json=new AjaxJson();
		if(StringUtil.isNotEmpty(sdkOutChannel.getId())){
			//更新
			
			this.systemService.saveOrUpdate(sdkOutChannel);
			this.systemService.addLog("外部渠道"+sdkOutChannel.getName()+"更新成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			json.setMsg("更新成功");
		}else{
			sdkOutChannel.setCreateTime(new Timestamp(System.currentTimeMillis()));
			this.systemService.saveOrUpdate(sdkOutChannel);
			String cp_pw=ResourceUtil.getConfigByName("cp_init_pw", "123456");
			String channel_roleCode=ResourceUtil.getConfigByName("outChannelPartner", "outChannel");
			TSUser user=new TSUser();
			TSDepart tsDepart=this.systemService.findUniqueByProperty(TSDepart.class, "departname", "信息部");
			user.setTSDepart(tsDepart);
//			if (user.getTSDepart().equals("")) {
//				user.setTSDepart(tsDepart);
//			}
			user.setRealName(sdkOutChannel.getName());
			user.setUserName(channel_roleCode+oConvertUtils.getString(sdkOutChannel.getId()));
			user.setPassword(PasswordUtil.encrypt(user.getUserName(), cp_pw, PasswordUtil.getStaticSalt()));
			user.setStatus(Globals.User_Normal);
			systemService.save(user);
			TSRole role=this.systemService.findUniqueByProperty(TSRole.class, "roleCode", channel_roleCode);
			if (StringUtil.isNotEmpty(role.getId())) {
				saveRoleUser(user, role.getId());
			}
			this.systemService.addLog("外部渠道"+sdkOutChannel.getName()+"添加成功", Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			json.setMsg("添加成功");
		}
		
		return json;
	}
	
	protected void saveRoleUser(TSUser user, String roleidstr) {
		String[] roleids = roleidstr.split(",");
		for (int i = 0; i < roleids.length; i++) {
			TSRoleUser rUser = new TSRoleUser();
			TSRole role = systemService.getEntity(TSRole.class, roleids[i]);
			rUser.setTSRole(role);
			rUser.setTSUser(user);
			systemService.save(rUser);

		}
	}
	@RequestMapping(params="saveOutChannelDetail")
	@ResponseBody
	public AjaxJson saveOutChannelDetail(HttpServletRequest request ,SdkOutChannelDetail sdkOutChannelDetail){
		AjaxJson json=new AjaxJson();
		if(StringUtil.isNotEmpty(sdkOutChannelDetail.getId())){
			
			json.setMsg("更新成功");
		}else {
			sdkOutChannelDetail.setCreateTime(new Timestamp(System.currentTimeMillis()));
			json.setMsg("添加成功");
		}
		this.systemService.saveOrUpdate(sdkOutChannelDetail);
		this.systemService.addLog("外部渠道详情"+sdkOutChannelDetail.getSdkOutChannel().getName()+json.getMsg(), 
				json.getMsg().equals("添加成功")?Globals.Log_Type_INSERT:Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		return json;
	}
	@RequestMapping(params="delOutChannelDetail",method=RequestMethod.POST)
	@ResponseBody
	public AjaxJson delOutChannelDetail(HttpServletRequest request,SdkOutChannelDetail sdkOutChannelDetail){
		AjaxJson json=new AjaxJson();
		if(StringUtil.isNotEmpty(sdkOutChannelDetail.getId())){
			SdkOutChannelDetail outChannelDetail=this.systemService.getEntity(SdkOutChannelDetail.class, sdkOutChannelDetail.getId());
			this.systemService.delete(outChannelDetail);
			json.setMsg("删除成功");
			this.systemService.addLog("外部渠道详情id"+sdkOutChannelDetail.getId()+"删除成功", Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}
		return json;
	}
	
	@RequestMapping(params="delOutChannel",method=RequestMethod.POST)
	@ResponseBody
	public AjaxJson delOutChannel(HttpServletRequest request,SdkOutChannel sdkOutChannel){
		AjaxJson json=new AjaxJson();
		if(StringUtil.isNotEmpty(sdkOutChannel.getId())){
			SdkOutChannel outChannel=this.systemService.getEntity(SdkOutChannel.class, sdkOutChannel.getId());
			outChannel.getSdkOutChannelDetails().clear();
			this.systemService.deleteAllEntitie(outChannel.getSdkOutChannelDetails());
			this.systemService.delete(outChannel);
			// 删除用户
			String channel_roleCode=ResourceUtil.getConfigByName("outChannelPartner", "outChannel");
			TSUser user=this.systemService.findUniqueByProperty(TSUser.class, "userName", channel_roleCode+oConvertUtils.getString(outChannel.getId()));
			if(user!=null){
				List<TSRoleUser> roleUser = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
				if (!user.getStatus().equals(Globals.User_ADMIN)) {
					if (roleUser.size()>0) {
						// 删除用户时先删除用户和角色关系表
						delRoleUser(user);
						userService.delete(user);
						message = "用户：" + user.getUserName() + "删除成功";
						systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
					} else {
						userService.delete(user);
						message = "用户：" + user.getUserName() + "删除成功";
					}
				
				}
			}
			json.setMsg("删除成功");
			this.systemService.addLog("外部渠道id"+sdkOutChannel.getId()+"删除成功", Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}
		
		return json;
	}
	public void delRoleUser(TSUser user) {
		// 同步删除用户角色关联表
		List<TSRoleUser> roleUserList = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
		if (roleUserList.size() >= 1) {
			for (TSRoleUser tRoleUser : roleUserList) {
				systemService.delete(tRoleUser);
			}
		}
	}
	
	
	
	public class StatsOutChannel{
		private SdkOutChannel sdkOutChannel;
		private Integer regNumber;
		private String inputTime;
		
		public SdkOutChannel getSdkOutChannel() {
			return sdkOutChannel;
		}
		public void setSdkOutChannel(SdkOutChannel sdkOutChannel) {
			this.sdkOutChannel = sdkOutChannel;
		}
		public Integer getRegNumber() {
			return regNumber;
		}
		public void setRegNumber(Integer regNumber) {
			this.regNumber = regNumber;
		}
		public String getInputTime() {
			return inputTime;
		}
		public void setInputTime(String inputTime) {
			this.inputTime = inputTime;
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
