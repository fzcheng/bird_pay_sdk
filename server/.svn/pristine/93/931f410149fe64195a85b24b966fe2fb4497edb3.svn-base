package com.cheyooh.service.sdk.action.client;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.session.RowBounds;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.ResultContentPage;
import com.cheyooh.service.sdk.db.dao.SdkGiftMapper;
import com.cheyooh.service.sdk.db.dao.SdkGiftVcodeMapper;
import com.cheyooh.service.sdk.db.entity.SdkGift;
import com.cheyooh.service.sdk.db.entity.SdkGiftExample;
import com.cheyooh.service.sdk.db.entity.SdkGiftVcode;
import com.cheyooh.service.sdk.db.entity.SdkGiftVcodeExample;
import com.cheyooh.service.sdk.idata.CmdGeneral;
import com.cheyooh.service.sdk.idata.ResultGift;
import com.cheyooh.tools.utils.Pagination;


/**
 * 该流程用于客户端向服务器端获取礼包列表。
 * 
 * @author zhouzg@cheyooh.com
 *
 */
public class Gift_list extends AbstractSdkClientService<CmdGeneral> {

	@Override
	protected boolean isLoginRequired() { 
		return true;
	}

	@Override
	protected Result execute(CmdGeneral cmd) {
		DAL dal=DALFactory.createDAL();
		try{			 
			int uid=gameSession.getUid();
			int gameid=game.getGameId();
			 
			//查询用户已领取的礼包
			Set<Integer> accepts=new HashSet<Integer>();
			SdkGiftVcodeMapper mapperGiftVcode=dal.getMapper(SdkGiftVcodeMapper.class);
			SdkGiftVcodeExample examplVcode=new SdkGiftVcodeExample();
			examplVcode.createCriteria().andUidEqualTo(uid);
			List<SdkGiftVcode> vcodes=mapperGiftVcode.selectByExample(examplVcode);
			for(SdkGiftVcode v:vcodes){
				accepts.add(v.getGiftId());
			}
			
			//显示游戏礼包列表
			SdkGiftMapper mapperGift=dal.getMapper(SdkGiftMapper.class);
			SdkGiftExample example=new SdkGiftExample();			
			SdkGiftExample.Criteria c1=example.createCriteria();
			c1.andGameIdEqualTo(gameid).andBeginTimeIsNull();
			SdkGiftExample.Criteria c2=example.or();
			c2.andGameIdEqualTo(gameid).andBeginTimeLessThan(new Date());
			example.setOrderByClause("create_time desc");
			Pagination<SdkGift> page=mapperGift.selectByExample(example, new RowBounds(pageIndex*pageSize,pageSize));
			ResultContentPage rcp=new ResultContentPage("gift_list",page);
			List<SdkGift> gifts=page.getList();
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
			if(gifts!=null)for(SdkGift gift:gifts){
				ResultGift g=new ResultGift();
				g.setAccept(accepts.contains(gift.getGiftId())?1:0);
				g.setDescription(gift.getDetail());
				g.setExpiration(sdf.format(gift.getEndTime()));
				g.setIcon_url(getImageUrl(gift.getImage()));
				g.setId(gift.getGiftId());
				g.setName(gift.getTitle());
								 
				g.setRemain(""+getRemain(gift));
								
				rcp.addContent(g);
			}
			
			return new Result(rcp);
		}finally{
			dal.close();
		}
	}

	private int getRemain(SdkGift gift){
		Integer r=gift.getRemain();
		Integer t=gift.getTotal();
		if(r==null || t==null || r==0){
			return 0;
		}else if(r.intValue()==t.intValue()){
			return 100;
		}else{
			int p=(r*100) / t;
			if(p>=100){
				return 99;
			}else if(p==0){
				return 1;	
			}else{
				return p;
			}
		}
	}
	
	
}
