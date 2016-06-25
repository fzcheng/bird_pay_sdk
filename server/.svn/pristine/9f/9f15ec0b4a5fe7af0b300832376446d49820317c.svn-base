package com.cheyooh.service.sdk.action.client;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.sdk.cfg.Cfg;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.StatusCode;
import com.cheyooh.service.sdk.db.dao.SdkUserMapper;
import com.cheyooh.service.sdk.db.entity.SdkUser;
import com.cheyooh.service.sdk.idata.CmdModifyPwd;
import com.cheyooh.service.sdk.tools.CryptoUtil;

/**
说明：该协议用于用户修改密码
客户端的请求参数如下：
1.命令参数 m=modify_pwd
2.固定参数
3.sid --- 登录后获取的sid
4.old_pwd ---- 旧密码
5.new_pwd ---- 新密码

 * @author zhouzg@cheyooh.com
 *
 */
public class Modify_pwd extends AbstractSdkClientService<CmdModifyPwd> {

	@Override
	protected Result execute(CmdModifyPwd cmd) {
		DAL dal=DALFactory.createDAL();
		try{
			SdkUserMapper mapper=dal.getMapper(SdkUserMapper.class);
			SdkUser user=mapper.selectByPrimaryKey(gameSession.getUid());
			if(user!=null){
				// 修改密码加密方式
				String password=cmd.getOld_pwd();
				password=CryptoUtil.decrypt(password);
				String md5=DigestUtils.md5Hex(password);
				if(user.getPwd().equalsIgnoreCase(md5)){
					String nwd=cmd.getNew_pwd();
					// 修改密码加密方式
					nwd=CryptoUtil.decrypt(nwd);
					if(StringUtils.isNotEmpty(nwd)){
						user.setPwd(DigestUtils.md5Hex(nwd));
					}
					
					mapper.updateByPrimaryKeySelective(user);
					dal.commit();
					
					return StatusCode.SUCCESS();
				}else{
					return StatusCode.ERR_NOTFOUND().setMessage(Cfg.msg.getString("sdk.client.login.fail_pwd","用户或密码不正确!"));	
				}
			}else{
				return StatusCode.ERR_NOTFOUND().setMessage(Cfg.msg.getString("sdk.client.login.fail_user","用户或密码不正确!"));
			}
		}finally{
			dal.close();
		}
	}
	
	@Override
	protected boolean isLoginRequired() {		 
		return true;
	}
	
	

}
