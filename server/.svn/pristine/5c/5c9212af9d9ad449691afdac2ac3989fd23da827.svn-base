package com.cheyooh.service.sdk.action.client;



import java.util.List;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.ResultContentList;
import com.cheyooh.service.framework.idata.StatusCode;
import com.cheyooh.service.sdk.db.dao.*;
import com.cheyooh.service.sdk.db.entity.*;
 
import com.cheyooh.service.sdk.idata.CmdGeneral;
import com.cheyooh.service.sdk.idata.ResultPayment;

public class Get_payment extends AbstractSdkClientService<CmdGeneral>{

 
	@Override
	protected boolean isLoginRequired() {
		
		return true;
	}

	@Override
	protected Result execute(CmdGeneral cmd) {
		DAL dal=DALFactory.createDAL();
		try{
			SdkGamePaymentMapper sdkGamePaymentMapper=dal.getMapper(SdkGamePaymentMapper.class);
			SdkGamePaymentExample sdkGamePaymentExample=new SdkGamePaymentExample();
			
			SdkPaymentMapper paymentMapper=dal.getMapper(SdkPaymentMapper.class);
			
			
			sdkGamePaymentExample.createCriteria().andGameIdEqualTo(game.getGameId());
			sdkGamePaymentExample.setOrderByClause("idx asc");
			List<SdkGamePayment> sdkGamePayments=sdkGamePaymentMapper.selectByExample(sdkGamePaymentExample);
			if(sdkGamePayments!=null && sdkGamePayments.size()>0){
				ResultContentList contentList=new ResultContentList();
				contentList.setTagName("payment_type");
				for(SdkGamePayment sdkGamePayment: sdkGamePayments){
					SdkPaymentExample paymentExample=new SdkPaymentExample();
					paymentExample.createCriteria().andIdEqualTo(sdkGamePayment.getPaymentId());
					SdkPayment sdkPayment=paymentMapper.selectOne(paymentExample);
					if(sdkGamePayment!=null){
						ResultPayment resultPayment=new ResultPayment();
						resultPayment.setName(sdkPayment.getPayName());
						resultPayment.setShow(sdkGamePayment.getPayShow());
						contentList.addContent(resultPayment);
					}
				}
				return new Result(contentList);
			}
			return StatusCode.ERR_NOTFOUND();
			
		}finally{
			dal.close();
		}
		 
	}

}
