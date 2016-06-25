package com.cheyooh.service.sdk.action.client;

import java.util.List;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.ResultContentList;
import com.cheyooh.service.framework.idata.StatusCode;
import com.cheyooh.service.sdk.db.dao.SdkGamePaymentMapper;
import com.cheyooh.service.sdk.db.dao.SdkPaymentMapper;
import com.cheyooh.service.sdk.db.entity.SdkGamePayment;
import com.cheyooh.service.sdk.db.entity.SdkGamePaymentExample;
import com.cheyooh.service.sdk.db.entity.SdkPayment;
import com.cheyooh.service.sdk.db.entity.SdkPaymentExample;
import com.cheyooh.service.sdk.idata.CmdGeneral;
import com.cheyooh.service.sdk.idata.ResultGetInitalParam;

public class Get_initial_param extends AbstractSdkClientService<CmdGeneral> {
	
	@Override
	protected boolean isLoginRequired() {
		return false;
	}

	@Override
	protected Result execute(CmdGeneral cmd) {
		Result result=null;
		DAL dal=DALFactory.createDAL();
		try{
			SdkPaymentMapper sdkPaymentMapper=dal.getMapper(SdkPaymentMapper.class);
			
			SdkGamePaymentMapper sdkGamePaymentMapper=dal.getMapper(SdkGamePaymentMapper.class);
			SdkGamePaymentExample sdkGamePaymentExample=new SdkGamePaymentExample();
			sdkGamePaymentExample.createCriteria().andGameIdEqualTo(game.getGameId()).andPayShowEqualTo(1);
			sdkGamePaymentExample.setOrderByClause("idx asc");
			List<SdkGamePayment> sdkGamePayments=sdkGamePaymentMapper.selectByExample(sdkGamePaymentExample);
			if(sdkGamePayments!=null && sdkGamePayments.size()>0){
				ResultContentList contentList=new ResultContentList();
				contentList.setTagName("item");
				for(SdkGamePayment sdkGamePayment: sdkGamePayments){
					SdkPaymentExample sdkPaymentExample=new SdkPaymentExample();
					sdkPaymentExample.createCriteria().andIdEqualTo(sdkGamePayment.getPaymentId());
					SdkPayment sdkPayment=sdkPaymentMapper.selectOne(sdkPaymentExample);
					if(sdkGamePayment!=null){
						ResultGetInitalParam resultGetInitalParam=new ResultGetInitalParam();
						resultGetInitalParam.setGamePaymentName(sdkPayment.getPayName());
						resultGetInitalParam.setGamePaymentIdx(sdkGamePayment.getIdx());
						contentList.addContent(resultGetInitalParam);
					}
				}
				result=new Result(contentList);
			}else{
				logger.info(game.getName()+", 游戏未配置支付方式");
				return StatusCode.ERR_NOTFOUND().setMessage("游戏未配置支付方式");
			}
		}catch(Exception e){
			logger.error(game.getName()+", appear exception, the exception ="+e);
			return StatusCode.ERR_SYSTEM().setMessage(game.getName()+" appear exception");
		}finally{
			dal.close();
		}
		logger.debug("the Get_initial_param result is:"
				+ result.getXml("Get_initial_param"));
		return result;
	}
}
