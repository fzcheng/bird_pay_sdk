package com.cheyooh.service.sdk.action.client;

import java.util.List;


import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.StatusCode;
import com.cheyooh.service.sdk.cfg.Cfg;
import com.cheyooh.service.sdk.db.dao.SdkMobileVcodeMapper;
import com.cheyooh.service.sdk.db.dao.SdkUserMapper;
import com.cheyooh.service.sdk.db.entity.SdkMobileVcode;
import com.cheyooh.service.sdk.db.entity.SdkMobileVcodeExample;
import com.cheyooh.service.sdk.db.entity.SdkUser;
import com.cheyooh.service.sdk.db.entity.SdkUserExample;
import com.cheyooh.service.sdk.idata.CmdVerifyPhone;
import com.cheyooh.service.sdk.tools.StringTool;

public class Bind_phone extends AbstractSdkClientService<CmdVerifyPhone> {

	@Override
	protected boolean isLoginRequired() {		 
		return true;
	}

	@Override
	protected Result execute(CmdVerifyPhone cmd) {
		DAL dal=DALFactory.createDAL();
		try{
			int uid=gameSession.getUid();
			
			SdkMobileVcodeMapper vcodeMapper=dal.getMapper(SdkMobileVcodeMapper.class);
			SdkMobileVcodeExample example=new SdkMobileVcodeExample();
			example.createCriteria().andVcodeEqualTo(cmd.getVcode()).andUidEqualTo(uid).andTypeEqualTo(1);
			example.setOrderByClause("create_time desc");
			List<SdkMobileVcode> codes=vcodeMapper.selectByExample(example);
			if(codes!=null &&codes.size()>0){
				String exp=Cfg.cfg.getString("sdk.client.bind_phone.vcode.expired","30m");
				long le=StringTool.getTimeMillis(exp);				
				
				SdkMobileVcode code=codes.get(0);
				long ts=System.currentTimeMillis()-code.getCreateTime().getTime();
				if(ts<le){
					
					SdkUserMapper userMapper=dal.getMapper(SdkUserMapper.class);
					SdkUserExample userExample=new SdkUserExample();

					SdkUser user=userMapper.selectByPrimaryKey(uid);
					
					if(user!=null){
						// 判断该号码是否之前已经绑定过这个帐号
						userExample.createCriteria().andUidEqualTo(uid).andMobileEqualTo(code.getMobile()).andBindEqualTo(1);
						List<SdkUser> bind_users1=userMapper.selectByExample(userExample);
						if(bind_users1!=null && bind_users1.size()>0){
							if(cmd.getContinue_confirm()!=null && cmd.getContinue_confirm().equals("1")){
								// 确认绑定
								
							}else {
								// 提示信息
								String code1=Cfg.msg.getString("sdk.client.bind_phone.samephone_code");
								String msg=Cfg.msg.getString("sdk.client.bind_phone.samephone_msg");
								return new Result(Integer.valueOf(code1),msg);
							}
						}else {
							// 该用户已经有其他手机号绑定
							userExample=new SdkUserExample();
							userExample.createCriteria().andUidEqualTo(uid).andMobileNotEqualTo(code.getMobile()).andBindEqualTo(1);
							List<SdkUser> bind_user2=userMapper.selectByExample(userExample);
							if(bind_user2!=null && bind_user2.size()>0){
								if(cmd.getContinue_confirm()!=null && cmd.getContinue_confirm().equals("1")){
									// 确认绑定
									
								}else {
									// 提示信息
									String code1=Cfg.msg.getString("sdk.client.bind_phone.hasphone_code");
									String msg=Cfg.msg.getString("sdk.client.bind_phone.hasphone_msg");
									return new Result(Integer.valueOf(code1),msg);
								}
							}else {
								
								// 该手机号已经有其他账号绑定
								userExample=new SdkUserExample();
								userExample.createCriteria().andUidNotEqualTo(uid).andMobileEqualTo(code.getMobile()).andBindEqualTo(1);
								List<SdkUser> bind_user3=userMapper.selectByExample(userExample);
								if(bind_user3!=null && bind_user3.size()>0){
									if(cmd.getContinue_confirm()!=null && cmd.getContinue_confirm().equals("1")){
										// 确认绑定
										//取消该手机号对其它UID的绑定
										SdkUser unbind_user=new SdkUser();
										unbind_user.setBind(0);
										SdkUserExample exampleUnbind=new SdkUserExample();
										exampleUnbind.createCriteria().andMobileEqualTo(code.getMobile());
										userMapper.updateByExampleSelective(unbind_user, exampleUnbind);
										
									}else {
										// 提示信息
										String code1=Cfg.msg.getString("sdk.client.bind_phone.hasuser_code");
										String msg=Cfg.msg.getString("sdk.client.bind_phone.hasuser_msg");
										return new Result(Integer.valueOf(code1),msg);
									}
								}else {
									
								}
							}
							
						}
					
						 
							//绑定手机
							user.setBind(1);
							user.setMobile(code.getMobile());
							userMapper.updateByPrimaryKeySelective(user);
							
							//删除验证码记录
							vcodeMapper.deleteByPrimaryKey(code.getVid());
							
							dal.commit();
							return StatusCode.SUCCESS();
						 
						}else {
							return StatusCode.ERR_NOTFOUND().setMessage(Cfg.msg.getString("sdk.client.user_not_found","用户不存在."));
						}
				}
			}			
			return StatusCode.ERR_INVLAID().setMessage(Cfg.msg.getString("sdk.client.bind_phone.vcode.invalid"));			
		}finally{
			dal.close();
		}
		
	}

}
