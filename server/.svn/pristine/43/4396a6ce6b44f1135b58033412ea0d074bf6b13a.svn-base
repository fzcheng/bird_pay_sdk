package com.cheyooh.service.sdk.action.client;

import java.util.Date;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.StatusCode;
import com.cheyooh.service.sdk.cfg.Cfg;
import com.cheyooh.service.sdk.db.dao.SdkOrderMapper;
import com.cheyooh.service.sdk.db.dao.SdkOrderYeepayMapper;
import com.cheyooh.service.sdk.db.entity.SdkOrder;
import com.cheyooh.service.sdk.db.entity.SdkOrderYeepay;
import com.cheyooh.service.sdk.idata.CmdPayYeepay;
import com.cheyooh.service.sdk.idata.ResultPayinfo;
import com.cheyooh.service.sdk.tools.StringTool;
import com.yeepay.Configuration;
import com.yeepay.nonbankcard.NonBankcardPaymentResult;
import com.yeepay.nonbankcard.NonBankcardService;

/**
 * 
 * 易宝支付
 * 
 * @author zhouzg@cheyooh.com
 *
 */
public class Pay_yeepay extends AbstractSdkClientService<CmdPayYeepay> {
	protected Result verify(CmdPayYeepay cmd) {
		if (cmd.getAmount() < 1f) {
			return StatusCode.ERR_PARAMETER().setMessage("无效的支付金额: " + cmd.getAmount());
		}
		return super.verify(cmd);
	}
 
	@Override
	protected boolean isLoginRequired() {
		return true;
	}
	
	
	@Override
	protected Result execute(CmdPayYeepay cmd) {
		SdkOrder order=createOrder(cmd,3);
		SdkOrderYeepay yeepay=new SdkOrderYeepay();
		
		DAL dal=DALFactory.createDAL();
		try{
			SdkOrderYeepayMapper mapperYeepay=dal.getMapper(SdkOrderYeepayMapper.class);
			SdkOrderMapper       mapperOrder =dal.getMapper(SdkOrderMapper.class);
		 
			String p5_Pid=Cfg.cfg.getString("sdk.pay.yeepay.p5_Pid");
			String p6_Pcat=Cfg.cfg.getString("sdk.pay.yeepay.p6_Pcat");
			String callback=Cfg.cfg.getString("sdk.pay.yeepay.notify");
			String p1_MerId=Configuration.getInstance().getValue("p1_MerId");
						
			yeepay.setReqP0Cmd("ChargeCardDirect");
			yeepay.setReqP1Merid(p1_MerId);
			yeepay.setReqP2Order(order.getOrderNo());
			yeepay.setReqP3Amt(cmd.getAmount());
			yeepay.setReqP4Verifyamt("true");
			yeepay.setReqP5Pid(p5_Pid==null?"":p5_Pid);
			yeepay.setReqP6Pcat(p6_Pcat==null?"":p6_Pcat);
			yeepay.setReqP7Pdesc(order.getOrderName());
			yeepay.setReqP8Url(callback);
			yeepay.setReqPa7Cardamt(""+cmd.getAmount());
			yeepay.setReqPa8Cardno(cmd.getCard_no());
			yeepay.setReqPa9Cardpwd(cmd.getCard_pwd());
			yeepay.setReqPaMp("");
			yeepay.setReqPdFrpid(cmd.getFrpid());
			yeepay.setReqPrNeedresponse("1");
			yeepay.setReqPz1Userregtime("");
			yeepay.setReqPzUserid("");
			yeepay.setReqTime(order.getCreateTime());
			
			mapperYeepay.insertSelective(yeepay);
			
			
			order.setPayId(yeepay.getPayId());			 
			mapperOrder.insert(order);
			
			dal.commit();
		}finally{
			dal.close();
		}
		 
		return submitOrder(order,yeepay);
		 
	}
	
	private Result submitOrder(SdkOrder order,SdkOrderYeepay yeepay){	
		
		NonBankcardPaymentResult rp=null;
		try{
			rp=sendToYeepay(order,yeepay);
		}catch(Exception e){		
			logger.error(e);
			
			orderException(order,yeepay,e.getMessage());
				
			return StatusCode.ERR_SYSTEM().setMessage(Cfg.msg.getString("sdk.system.pay.exception","订单处理失败,请稍候再试!"));			 
		}
		
		DAL dal=DALFactory.createDAL();		
		try{		 
			yeepay.setRespTime(new Date());
			yeepay.setRespR1Code(Integer.parseInt(rp.getR1_Code()));
			yeepay.setRespRqReturnmsg(StringTool.max(rp.getRq_ReturnMsg(),200));
			
			order.setStatus(2);
			
			SdkOrderYeepayMapper mapperYeepay=dal.getMapper(SdkOrderYeepayMapper.class);
			SdkOrderMapper       mapperOrder =dal.getMapper(SdkOrderMapper.class);
			
			mapperYeepay.updateByPrimaryKeySelective(yeepay);
			mapperOrder.updateByPrimaryKeySelective(order);
			dal.commit();
			
			return new Result(new ResultPayinfo(order.getOrderNo()));
		}finally{
			dal.close();
		}
		
	}
	
	private void orderException(SdkOrder order,SdkOrderYeepay yeepay,String e){
		DAL dal=DALFactory.createDAL();
		try{
			yeepay.setRespTime(new Date());
			yeepay.setRespR1Code(500);
			yeepay.setRespRqReturnmsg(StringTool.max(e,200));
							 
			order.setStatus(4);
			
			SdkOrderYeepayMapper mapperYeepay=dal.getMapper(SdkOrderYeepayMapper.class);
			SdkOrderMapper       mapperOrder =dal.getMapper(SdkOrderMapper.class);
			
			mapperYeepay.updateByPrimaryKeySelective(yeepay);
			mapperOrder.updateByPrimaryKeySelective(order);
			dal.commit();						 
		}finally{
			dal.close();
		}
	}
	
	private NonBankcardPaymentResult sendToYeepay(SdkOrder order,SdkOrderYeepay yeepay){
		NonBankcardPaymentResult rp=NonBankcardService.pay(
				order.getOrderNo(),
				""+yeepay.getReqP3Amt(),
				yeepay.getReqP4Verifyamt(), 
				yeepay.getReqP5Pid(), 
				yeepay.getReqP6Pcat(), 
				yeepay.getReqP7Pdesc(),
				yeepay.getReqP8Url(), 
				yeepay.getReqPaMp(), 
				yeepay.getReqPa7Cardamt(),
				yeepay.getReqPa8Cardno(), 
				yeepay.getReqPa9Cardpwd(), 
				yeepay.getReqPdFrpid(), 
				yeepay.getReqPrNeedresponse(), 
				yeepay.getReqPzUserid(), 
				yeepay.getReqPz1Userregtime());
		
		return rp;
		
	}

}
