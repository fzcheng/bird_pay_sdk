package jeecg.ext.sdk.controller;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.SdkGame;
import jeecg.ext.sdk.entity.SdkGamePush;
import jeecg.ext.sdk.entity.SdkPushcode;
import jeecg.ext.tools.TagUtilExt;
import jeecg.system.service.SystemService;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;




import cn.jpush.api.ErrorCodeEnum;
import cn.jpush.api.IOSExtra;
import cn.jpush.api.JPushClient;
import cn.jpush.api.MessageResult;

@Controller
@RequestMapping("/pushMsg")
public class GamePushController extends BaseController {
	
	private static final Logger logger = Logger
			.getLogger(GamePushController.class);
	
	private static JPushClient jpush = null;
	
	public static final int MAX = Integer.MAX_VALUE;
	public static final int MIN = MAX/2;
	
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
		return new ModelAndView("gamepush/list2");
	}
	@RequestMapping(params="datagrid2")
	public void datagrid2(SdkGame sdkGame,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
		CriteriaQuery cq=new CriteriaQuery(SdkGame.class,dataGrid);
		if(StringUtil.isNotEmpty(sdkGame.getName())){
			sdkGame.setName("*"+sdkGame.getName()+"*");
		}
		HqlGenerateUtil.installHql(cq, sdkGame);
		this.systemService.getDataGridReturn(cq, true);
		TagUtilExt.datagrid(response, dataGrid,"gameId");
		
	}
	
	@RequestMapping(params="datagrid")
	public void datagrid(SdkGamePush entity,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
		CriteriaQuery cq=new CriteriaQuery(SdkGamePush.class,dataGrid);
		HqlGenerateUtil.installHql(cq, entity);
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	@RequestMapping(params="sendMsg")
	public ModelAndView sendMsg(String id,HttpServletRequest request){
		if(StringUtil.isNotEmpty(id)){
			request.setAttribute("gameId", id);
		}
		return new ModelAndView("gamepush/sendMsg");
	}
	
	@RequestMapping(params="saveSend")
	@ResponseBody
	public AjaxJson saveSend(HttpServletRequest request,SdkGamePush entity){
		AjaxJson json=new AjaxJson();
		if(entity!=null){
			if(entity.getContentType().equals("2") && !StringUtil.isNotEmpty(entity.getUrl())){
				json.setMsg("请填写url");
				return json;
			}
			if(!StringUtil.isNotEmpty(entity.getSdkGame().getGameId())){
				json.setMsg("请选择游戏");
				
			}
			if(entity.getUrl().indexOf("http")<0){
				entity.setUrl("http://"+entity.getUrl());
			}
			SdkPushcode sdkPushCode=this.systemService.findUniqueByProperty(SdkPushcode.class, "sdkGame.gameId",entity.getSdkGame().getGameId());
			if(sdkPushCode!=null ){
				 
				
				String msg=SendMsg(entity,sdkPushCode);
				json.setMsg(msg);
			}
			
		}
		return json;
	}
	
	@RequestMapping(params="addorupdate")
	public ModelAndView addorupdate(SdkGamePush entity,HttpServletRequest request){
		List<SdkGame> gameInfos=this.systemService.getList(SdkGame.class);
		request.setAttribute("gameInfos", gameInfos);
		return new ModelAndView("gamepush/add");
	}
	
	@RequestMapping(params="save")
	@ResponseBody
	public AjaxJson save(HttpServletRequest request,SdkGamePush entity){
		AjaxJson json=new AjaxJson();
		if(entity!=null){
			if(entity.getContentType().equals("2") && !StringUtil.isNotEmpty(entity.getUrl())){
				json.setMsg("请填写url");
				return json;
			}
			if(!StringUtil.isNotEmpty(entity.getSdkGame().getGameId())){
				json.setMsg("请选择游戏");
				
			}
			if(entity.getUrl().indexOf("http")<0){
				entity.setUrl("http://"+entity.getUrl());
			}
			SdkPushcode sdkPushCode=this.systemService.findUniqueByProperty(SdkPushcode.class, "gameInfo.id", entity.getSdkGame().getGameId());
			if(sdkPushCode!=null ){
				 
				
				String msg=SendMsg(entity,sdkPushCode);
				systemService.addLog(msg, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
				json.setMsg(msg);
			}
			
		}
		
		return json;
	}
	
	private String SendMsg(SdkGamePush entity,SdkPushcode sdkPushCode){
		 
		String masterSecret=sdkPushCode.getPushMasterSecret();
		String appKey=sdkPushCode.getPushAppKey();
		jpush = new JPushClient(masterSecret, appKey);
		 
		// 在实际业务中，建议 sendNo 是一个你自己的业务可以处理的一个自增数字。
		// 除非需要覆盖，请确保不要重复使用。详情请参考 API 文档相关说明。
		int sendNo =getRandomSendNo();
		entity.setMsgTime(new Timestamp(System.currentTimeMillis()));
		entity.setMsgId(sendNo+"");
		Map<String, Object> extra=new HashMap<String,Object>();
		if(StringUtil.isNotEmpty(entity.getMsgId())){
			extra.put("msg_id", entity.getMsgId());
		}
		if(StringUtil.isNotEmpty(entity.getMsgTime())){
			extra.put("msg_time", entity.getMsgTime());
		}
		if(StringUtil.isNotEmpty(entity.getUrl()) ){
			extra.put("url", entity.getUrl());
		}
		
		try {
			String title = new String(entity.getTitle().getBytes("UTF-8"),"UTF-8");
		    String msg=new String(entity.getMessage().getBytes("UTF-8"),"UTF-8");
		    
		    logger.info("the jpush message is : "+sendNo+", "+title+", "+msg+", "+entity.getContentType()+", "+extra);
		    logger.debug("the jpush message is : "+sendNo+", "+title+", "+msg+", "+entity.getContentType()+", "+extra);
		    
			MessageResult msgResult = jpush.sendCustomMessageWithAppKey(sendNo, title, msg,entity.getContentType(),extra);
	
			if (null != msgResult) {
			    if (msgResult.getErrcode() == ErrorCodeEnum.NOERROR.value()) {
			    	this.systemService.save(entity);
			        return "发送成功， sendNo=" + msgResult.getSendno();
			    } else {
			        return "发送失败， 错误代码=" + msgResult.getErrcode() + ", 错误消息=" + msgResult.getErrmsg();
			    }
			} else {
			    return "无法获取数据";
			}
			} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			return "无法获取数据";
		}
	}
	
	 
	/**
	 * 保持 sendNo 的唯一性是有必要的
	 * It is very important to keep sendNo unique.
	 * @return sendNo
	 */
	public static int getRandomSendNo() {
	    return (int) (MIN + Math.random() * (MAX - MIN));
	}
	 
}
