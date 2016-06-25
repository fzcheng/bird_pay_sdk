package jeecg.ext.sdk.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.ResetPasswordEntity;
import jeecg.ext.sdk.entity.SdkUser;
import jeecg.ext.sdk.entity.SdkUserAuto;
import jeecg.ext.tools.TagUtilExt;
import jeecg.system.service.SystemService;
import jeecg.system.service.UserService;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/resetPwd")
public class ResetPasswordController  extends BaseController{
	
	static Log logger=LogFactory.getLog(GameInfoController.class);
	
	
	@Autowired
	private UserService userService;
	@Autowired
	private SystemService systemService;
	
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response){
		return new ModelAndView("resetPwd/list");
	}
	@RequestMapping(params = "datagrid")
	public void datagrid(ResetPasswordEntity entity,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid){
		String searchFeild=request.getParameter("searchfield");
		List<ResetPasswordEntity> result=new ArrayList<ResetPasswordEntity>();
		List<SdkUser> sdkUsers=new ArrayList<SdkUser>();
		/*List<SdkUser> users1=null;
		List<SdkUser> users2=null;
		List<SdkUser> users3=null;*/
		if(StringUtil.isNotEmpty(searchFeild) && searchFeild.equals("phoneNumber") && StringUtil.isNotEmpty(entity.getPhoneNumber())){
			sdkUsers=this.systemService.findByProperty(SdkUser.class, "mobile", entity.getPhoneNumber());
		}
		if(StringUtil.isNotEmpty(searchFeild) && searchFeild.equals("userId") && StringUtil.isNotEmpty(entity.getUserId())){
			sdkUsers=this.systemService.findByProperty(SdkUser.class, "uid", oConvertUtils.getInt(entity.getUserId()));
		}
		if(StringUtil.isNotEmpty(searchFeild) && searchFeild.equals("phoneImei") &&StringUtil.isNotEmpty(entity.getPhoneImei())){
			SdkUserAuto sdkUserAuto=this.systemService.findUniqueByProperty(SdkUserAuto.class, "imei", entity.getPhoneImei());
			if(sdkUserAuto!=null){
				sdkUsers=this.systemService.findByProperty(SdkUser.class, "deviceId", sdkUserAuto.getDeviceId());
			}
		}
		// 求交集
		/*if(users1!=null){
			if(users2!=null){
				if(users3!=null){
					Collection<SdkUser> tmp=CollectionUtils.intersection(users1, users2);
					sdkUsers=CollectionUtils.intersection(tmp, users3);
				}else{
					sdkUsers=CollectionUtils.intersection(users1, users2);
				}
			}else {
				if(users3!=null){
					sdkUsers=CollectionUtils.intersection(users1, users3);
				}else{
					sdkUsers=users1;
				}
				
			}
		}else {
			if(users2!=null){
				if(users3!=null){
					sdkUsers=CollectionUtils.intersection(users2, users3);
				}else {
					sdkUsers=users2;
				}
			}else {
				if(users3!=null){
					sdkUsers=users3;
				}
			}
		}*/
		
		if(sdkUsers!=null && sdkUsers.size()>0){
			result=getList(sdkUsers);
		}
		dataGrid.setReaults(result);
		
		
		dataGrid.setTotal(result.size());
		TagUtilExt.datagrid(response, dataGrid,"userId");
	}
	
	@RequestMapping(params = "reset", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson reset(HttpServletRequest request,String userId){
		AjaxJson json=new AjaxJson();
		SdkUser sdkUser=this.systemService.findUniqueByProperty(SdkUser.class, "uid", oConvertUtils.getInt(userId));
		if(sdkUser!=null){
			sdkUser.setPwd(DigestUtils.md5Hex("123456"));
			this.systemService.updateEntitie(sdkUser);
			this.systemService.addLog("游戏用户"+sdkUser.getUid()+"密码重置成功", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			json.setMsg("游戏用户"+sdkUser.getUid()+"密码重置成功");
		}
		
		return json;
	}
	
	private List<ResetPasswordEntity> getList(Collection<SdkUser> list){
		List<ResetPasswordEntity> result=new ArrayList<ResetPasswordEntity>();
		for(SdkUser sdkUser:list){
			ResetPasswordEntity entity=new ResetPasswordEntity();
			entity.setBandStatus(sdkUser.getBind());
			SdkUserAuto sdkUserAuto=this.systemService.findUniqueByProperty(SdkUserAuto.class, "deviceId", sdkUser.getDeviceId());
			if(sdkUserAuto!=null) entity.setPhoneImei(sdkUserAuto.getImei());
			
			entity.setPhoneNumber(sdkUser.getMobile());
			entity.setRegIp(sdkUser.getRegIp());
			entity.setRegTime(sdkUser.getRegTime().toString());
			entity.setUserId(sdkUser.getUid().toString());
			entity.setNickName(sdkUser.getNickName());
			result.add(entity);
		}
		return result;
	}
	
}
