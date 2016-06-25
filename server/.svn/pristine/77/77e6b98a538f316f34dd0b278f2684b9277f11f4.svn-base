package com.cheyooh.service.sdk.action.client;

import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.sdk.cfg.Cfg;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.StatusCode;
import com.cheyooh.service.sdk.db.dao.SdkUserMapper;
import com.cheyooh.service.sdk.db.entity.SdkUser;
import com.cheyooh.service.sdk.db.entity.SdkUserExample;
import com.cheyooh.service.sdk.idata.CmdUserLogin;
import com.cheyooh.service.sdk.idata.ResultUserLogin;
import com.cheyooh.service.sdk.tools.CacheManager;
import com.cheyooh.service.sdk.tools.CryptoUtil;
import com.cheyooh.service.sdk.tools.GameSession;


/**
 * 说明：该协议包含登录和登录并修改密码功能
 * 客户端的请求参数如下：
1.命令参数 m=user_login
2.固定参数
3.user_name ---- 用户名
4.pwd ----- 密码
5.new_pwd ---- 新密码，如果该参数不为空，则表示该操作需要修改密码并登录。

 * @author zhouzg@cheyooh.com
 *
 */
public class User_login extends AbstractSdkClientService<CmdUserLogin> {

	@Override
	protected Result execute(CmdUserLogin cmd){
		DAL dal=DALFactory.createDAL();
		try{
			SdkUserMapper mapper=dal.getMapper(SdkUserMapper.class);
			String username=cmd.getUser_name();
			SdkUser user=null;
			if(username.length()==11){
				//手机号登录
				SdkUserExample example=new SdkUserExample();
				example.createCriteria().andMobileEqualTo(username).andBindEqualTo(1);
				List<SdkUser> users=mapper.selectByExample(example);
				if(users.size()>0){
					user=users.get(0);
				}
			}else{				
				int uid=0;
				try{
					uid=Integer.parseInt(username);
				}catch(NumberFormatException e){
					return StatusCode.ERR_PARAMETER().setMessage(Cfg.msg.getString("sdk.client.login.invalid_username","无效的用户: ")+username);
				}
				
				logsdk.setUid(uid);
				
				user=mapper.selectByPrimaryKey(uid);
			}
			
			if(user!=null){
				logsdk.setUid(user.getUid());
				
				// 修改密码方式
				String password=cmd.getPwd();
				password=CryptoUtil.decrypt(password);
				logger.info("the CryptoUtil decrypt is : "+password);
				String md5=DigestUtils.md5Hex(password);
				logger.info("the md5 is : "+md5);
				if(user.getPwd().equalsIgnoreCase(md5)){
					String nwd=cmd.getNew_pwd();
					
					if(StringUtils.isNotEmpty(nwd)){
						// 修改密码方式
						nwd=CryptoUtil.decrypt(nwd);
						user.setPwd(DigestUtils.md5Hex(nwd));
					}
					user.setLoginChannel(cmd.getChannel());
					user.setLoginVersion(cmd.getVersion());
					user.setLoginGame(game.getGameId());
					user.setLoginIp(cmd.getRequestHost());
					user.setLoginTime(new Date());						
					user.setLoginTimes(user.getLoginTimes()+1);
					user.setOsversion(cmd.getOsversion());
					user.setModel(cmd.getModel());
					if(user.getFirstLoginTime()==null){
						user.setFirstLoginTime(user.getLoginTime());
					}
					mapper.updateByPrimaryKeySelective(user);
					dal.commit();
					
					GameSession gs=new GameSession(user.getUid(),game.getGameId());
					ResultUserLogin rul=new ResultUserLogin();
					rul.setBind_phone(StringUtils.isNotEmpty(user.getMobile()));
					rul.setNick_name(user.getNickName());
					rul.setSid(gs.getSid());
					rul.setValid_time((int)(gs.getValidTime()/1000));
					// 加入缓存
					CacheManager.setValue(gs.getSid(), gs.getSid());
					return new Result(rul);
				}else{
					return StatusCode.ERR_NOTFOUND().setMessage(Cfg.msg.getString("sdk.client.login.fail_pwd","密码不正确!"));
				}
			}else{
				return StatusCode.ERR_NOTFOUND().setMessage(Cfg.msg.getString("sdk.client.login.fail_name","用户不存在!"));
			}
		}finally{
			dal.close();
		} 					 
	}

	@Override
	protected boolean isLoginRequired() {
		 
		return false;
	}

}
