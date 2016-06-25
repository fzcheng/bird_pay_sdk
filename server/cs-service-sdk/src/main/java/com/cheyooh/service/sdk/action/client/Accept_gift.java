package com.cheyooh.service.sdk.action.client;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.collections.map.CaseInsensitiveMap;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.dal.DALMode;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.StatusCode;
import com.cheyooh.service.sdk.db.dao.SdkDDQMapper;
import com.cheyooh.service.sdk.db.dao.SdkGiftMapper;
import com.cheyooh.service.sdk.db.dao.SdkGiftVcodeMapper;
import com.cheyooh.service.sdk.db.entity.SdkGift;
import com.cheyooh.service.sdk.db.entity.SdkGiftVcode;
import com.cheyooh.service.sdk.db.entity.SdkGiftVcodeExample;
import com.cheyooh.service.sdk.idata.CmdGeneral;
import com.cheyooh.service.sdk.idata.ResultGiftVCode;
import com.mokredit.payment.StringUtils;


/**
 * 该流程用于客户端向服务器端领取礼包。
 * 
 * 
 * @author zhouzg@cheyooh.com
 *
 */
public class Accept_gift extends AbstractSdkClientService<CmdGeneral> {
	private static Map<Integer,AtomicInteger> hGiftRemains=new HashMap<Integer,AtomicInteger>();
	
	@Override
	protected boolean isLoginRequired() {
		return true;
	}

	@Override
	protected Result execute(CmdGeneral cmd) {
		int gift_id=this.getIntParameter("gift_id", 0);
		
		if(gift_id==0){
			return StatusCode.ERR_PARAMETER().setMessage("无效参数: gift_id");
		} 
		
		if(StringUtils.isNotEmpty(getParameter("reset"))){
			AtomicInteger remain=hGiftRemains.get(gift_id);			
			if(remain!=null){
				int i=getRemains(gift_id);
				remain.set(i);
				
				return StatusCode.SUCCESS().setMessage("Reset gift("+gift_id+") remain to: "+i);
			}else{
				return StatusCode.SUCCESS().setMessage("empty");
			}
			
		}				
		
		int left=0;		
		synchronized(hGiftRemains){
			AtomicInteger remain=hGiftRemains.get(gift_id);
			if(remain==null){
				remain=new AtomicInteger(getRemains(gift_id));
				hGiftRemains.put(gift_id, remain);
			}	
			if(remain.get()>0){
				left=remain.getAndDecrement();
			}
		}
		
		if(left<1){
			return StatusCode.SUCCESS();
		}else{
			return requestGiftVcode(gift_id);
		}
	}
	
	@SuppressWarnings("unchecked")
	private Result requestGiftVcode(int gift_id){
		DAL dal=DALFactory.createDAL();
		try{
			dal.setAccessMode(DALMode.MASTER);
			int uid=gameSession.getUid();
			
			SdkGiftVcodeMapper mapperGiftVcode=dal.getMapper(SdkGiftVcodeMapper.class);
			SdkGiftVcodeExample example=new SdkGiftVcodeExample();
			example.createCriteria().andGiftIdEqualTo(gift_id).andUidEqualTo(uid);
			SdkGiftVcode vcode=mapperGiftVcode.selectOne(example);
			if(vcode!=null){
				//该用户已领取, 剩余数+1
				AtomicInteger remain=hGiftRemains.get(gift_id);
				remain.incrementAndGet();
				
				return StatusCode.SUCCESS();
			}else{
				CaseInsensitiveMap param=new CaseInsensitiveMap();
				param.put("uid", uid);
				param.put("gift_id", gift_id);
				
				SdkDDQMapper ddq=dal.getMapper(SdkDDQMapper.class);
				int r=ddq.DDQUpdate("graspGiftVcode", param);
				dal.commit();				
				if(r==1){
					vcode=mapperGiftVcode.selectOne(example);			
					return new Result(new ResultGiftVCode(vcode.getVcode()));
				}else{
					return StatusCode.SUCCESS();
				}				 
			}
		}finally{
			dal.close();
		}
	}
	
	private Integer getRemains(int gift_id){
		DAL dal=DALFactory.createDAL();
		try{
			dal.setAccessMode(DALMode.MASTER);
			SdkGiftMapper mapper=dal.getMapper(SdkGiftMapper.class);
			SdkGift gift=mapper.selectByPrimaryKey(gift_id);
			if(gift!=null){
				return gift.getRemain();
			}else{
				return 0;
			}
		}finally{
			dal.close();
		}
	}

}
