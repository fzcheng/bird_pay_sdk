package com.cheyooh.service.sdk.action.client;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.StatusCode;
import com.cheyooh.service.sdk.db.dao.SdkBulletinMapper;
import com.cheyooh.service.sdk.db.entity.SdkBulletin;
import com.cheyooh.service.sdk.db.entity.SdkBulletinExample;
import com.cheyooh.service.sdk.idata.CmdGeneral;
import com.cheyooh.service.sdk.idata.ResultBulletin;
import com.cheyooh.service.sdk.idata.ResultGameInfo;

/**
 * 说明：该流程用于客户端向服务器端获取登录公告。
 * 
 * @author zhouzg@cheyooh.com
 *
 */
public class Bulletin extends AbstractSdkClientService<CmdGeneral> {

	@Override
	protected boolean isLoginRequired() {		 
		return false;
	}

	@Override
	protected Result execute(CmdGeneral cmd) {
		DAL dal=DALFactory.createDAL();
		try{		
			SdkBulletinMapper mapper=dal.getMapper(SdkBulletinMapper.class);
			SdkBulletinExample example=new SdkBulletinExample();
			example.createCriteria().andStatusEqualTo(1);
			example.setOrderByClause("create_time desc");
			SdkBulletin b=mapper.selectOne(example);
			if(b!=null){
				ResultBulletin c=new ResultBulletin();
				c.setType(b.getType());
				c.setContent(b.getDetail());
				if(b.getType()==1){
					c.setUrl(b.getGameUrl());
				}else if(b.getType()==2){
					ResultGameInfo game=new ResultGameInfo();
					game.setCategory(b.getGameCategory());
					game.setDl_url(b.getGameUrl());
					game.setIcon_url(getImageUrl(b.getGameIcon()));
					game.setName(b.getGameName());
					game.setPackage_name(b.getGamePkg());
					game.setRating(b.getGameRating()==null?3:b.getGameRating());					 
					c.setGame(game);
				}
				
				return new Result(c);
			}else{
				return StatusCode.SUCCESS();
			}
		}finally{
			dal.close();
		}
	}

}
