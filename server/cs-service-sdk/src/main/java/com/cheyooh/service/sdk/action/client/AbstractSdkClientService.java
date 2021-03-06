package com.cheyooh.service.sdk.action.client;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;

import com.cheyooh.service.framework.basic.Service;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.StatusCode;
import com.cheyooh.service.sdk.cfg.Cfg;
import com.cheyooh.service.sdk.db.entity.SdkGame;
import com.cheyooh.service.sdk.db.entity.SdkLogAccess;
import com.cheyooh.service.sdk.db.entity.SdkOrder;
import com.cheyooh.service.sdk.idata.CmdGeneral;
import com.cheyooh.service.sdk.idata.CmdPay;
import com.cheyooh.service.sdk.thread.LogError;
import com.cheyooh.service.sdk.thread.LogWriteThread;
import com.cheyooh.service.sdk.tools.CacheManager;
import com.cheyooh.service.sdk.tools.DBHelper;
import com.cheyooh.service.sdk.tools.GameSession;
import com.cheyooh.service.sdk.tools.GenerateTool;
import com.cheyooh.service.sdk.tools.SignParameter;
import com.mokredit.payment.StringUtils;

public abstract class AbstractSdkClientService<C extends CmdGeneral> extends Service<C>{
	protected GameSession gameSession;
	protected SdkGame     game;	
	
	protected int pageIndex=0;
	protected int pageSize =20;
	
	protected SdkLogAccess logsdk=new SdkLogAccess();
	 
	protected Result verify(C cmd){		 
	  logger.debug("csdsc");
		String secret=Cfg.cfg.getString("sdk.client.secret","2013.sdk.legame.com");
		HttpServletRequest request=cmd.getServiceContext().getRequest();
		SignParameter sp=new SignParameter();
		sp.addParameters(request.getParameterMap());		 
		String sign=sp.getSignString("&")+"&"+secret;
		String md5=DigestUtils.md5Hex(sign);
		String checksign=request.getParameter("checksign");
		if(!md5.equalsIgnoreCase(checksign)){
			if(Cfg.cfg.getBoolean("sdk.debug",true) && request.getParameter("debug")!=null){
				return StatusCode.ERR_INVLAID().setMessage("非法的访问! debug: "+md5);
			}else{
				return StatusCode.ERR_INVLAID().setMessage("非法的访问!");
			}
		}else{
			if(isLoginRequired()){
				gameSession=new GameSession(cmd.getSid());
				if(gameSession.isExpired()){
					return StatusCode.EXPIRED().setMessage(Cfg.msg.getString("sdk.client.session.timeout","会话已过期,请重新登录!"));
				}else{
					try{
						//早先登录的用户不检查SESSION
						Date d=new SimpleDateFormat("yyyy-MM-dd").parse(Cfg.cfg.getString("sdk.session.dev.expire_day","2013-12-20"));
						if(d.after(gameSession.getTime())){
							if(!CacheManager.hasCache(cmd.getSid())){				
								return StatusCode.EXPIRED().setMessage(Cfg.msg.getString("sdk.client.session.timeout","会话已过期,请重新登录!"));
							}
						}
					}catch(Exception e){
						logger.error(e);
					}
				}
				
			}
			
			game=DBHelper.getGameByAppkey(cmd.getAppkey());
			if(game==null){
				return StatusCode.ERR_INVLAID().setMessage("无效的Appkey: "+cmd.getAppkey());
			}
			
			pageIndex=getIntParameter("pageIndex", 0);
			pageSize =getIntParameter("pageSize", 20);
			if(pageSize>1000){
				pageSize=1000;
			}
			if(pageSize<1){
				pageSize=1;
			}
			
			return null;
		}
	}	 
	
	protected String getParameter(String name){
		String v=_cmd.getServiceContext().getRequest().getParameter(name);
		return v;
	}
	
	protected int getIntParameter(String name,int def){
		String v=getParameter(name);
		if(StringUtils.isEmpty(v)){
			return def;
		}else{
			return Integer.parseInt(v);
		}
	}
	 
	/**
	 * 创建订单
	 * 
	 * @param type 充值方式:
				1- 支付宝
				2- 财付通
				3- 易宝(充值卡)  
				4- Mo9
				5- 微派   
	 * @return
	 */
	protected SdkOrder createOrder(CmdPay cmd,int type){
		Date time=new Date();
		String order_no=GenerateTool.createOrderNo();
		
		SdkOrder order=new SdkOrder();
		order.setAmount(cmd.getAmount());
		order.setChannel(cmd.getChannel());
		order.setCpExt(cmd.getCp_ext());
		order.setCpId(game.getCpId());
		order.setCreateTime(time);
		order.setGameId(game.getGameId());
		
		//order.setOrderName(cmd.getOrder_name());
		//更改订单名称为游戏的名称
		order.setOrderName(game.getName());
		
		order.setOrderDesc(cmd.getOrder_desc());
		order.setOrderNo(order_no);
		 
		order.setStatus(0);
		order.setNotifyStatus(0);
		order.setType(type);
		order.setUid(gameSession.getUid());
		
		return order;
	}
	 
	protected String getImageUrl(String img){
		if(StringUtils.isNotEmpty(img)){
			if(img.startsWith("http://") || img.startsWith("https://")){
				return img;
			}else{
				String base_url=Cfg.cfg.getString("sdk.image.base_url","http://127.0.0.1");
				if(!base_url.endsWith("/")){
					base_url+="/";
				}
				if(img.startsWith("/")){
					img=img.substring(1);
				}
				return base_url+img;
			}
		}else{
			return img;
		}
	}
	
	protected void log(C cmd, Result r){
		 if(game!=null){			
			logsdk.setAction(cmd.getCmd());
			logsdk.setChannel(cmd.getChannel());
			logsdk.setGameId(game.getGameId());
			logsdk.setLogIp(cmd.getRequestHost());
			logsdk.setLogTime(new Date());
			logsdk.setServerId(GenerateTool.WEB_SERVER_ID);
			
			GameSession gs=gameSession;
			if(gs==null){
				gs=new GameSession(cmd.getSid());
			}						
			logsdk.setSid(gs.getSid());
			if(gs.getUid()>0){
				logsdk.setUid(gs.getUid());
			}
			 
			
			logsdk.setStatus(r.getStatus());
			logsdk.setStatusString(r.getMessage());
			
			logsdk.setUseTime((int)r.getTimeService());
			logsdk.setVersion(cmd.getVersion());
			logsdk.setVersionCode(cmd.getVersionCode());
			
			if(cmd.getServiceContext().getRequest().getParameter("debug")!=null){
				LogError.logDebug(logsdk);
			}else{
				LogWriteThread.addLog(logsdk);
			}
		}
	}
	 
	
	/**
	 * 该功能是否要求用户已登录. 
	 * true: 用户必须是已经登录, 此时gameSession对象被设置
	 * false: 无需用户登录,gameSession对象为null
	 * @return
	 */
	protected abstract boolean isLoginRequired();

}
