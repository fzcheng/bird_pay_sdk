package com.cheyooh.service.sdk.action.client;

import java.util.Date;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.StatusCode;
import com.cheyooh.service.sdk.cfg.Cfg;
import com.cheyooh.service.sdk.db.dao.SdkOrderMapper;
import com.cheyooh.service.sdk.db.dao.SdkOrderVpayMapper;
import com.cheyooh.service.sdk.db.entity.SdkOrder;
import com.cheyooh.service.sdk.db.entity.SdkOrderVpay;
import com.cheyooh.service.sdk.idata.CmdPay;
import com.cheyooh.service.sdk.idata.ResultPayinfo;
import com.tenpay.util.MD5Util;

public class Pay_vpay extends AbstractSdkClientService<CmdPay>{

	protected Result verify(CmdPay cmd) {
		// TODO :发布时修改为1f
		if (cmd.getAmount() < 0.000001f) {
			return StatusCode.ERR_PARAMETER().setMessage("无效的支付金额: " + cmd.getAmount());
		}
		return super.verify(cmd);
	}
	@Override
	protected boolean isLoginRequired() {
		
		return true;
	}

	@Override
	protected Result execute(CmdPay cmd) {
		
		SdkOrder sdkOrder=createOrder(cmd, 8);
		SdkOrderVpay vpay=new SdkOrderVpay();
		DAL dal=DALFactory.createDAL();
		try {
			SdkOrderMapper sdkOrderMapper=dal.getMapper(SdkOrderMapper.class);
			SdkOrderVpayMapper sdkOrderVpayMapper=dal.getMapper(SdkOrderVpayMapper.class);
			
			vpay.setReqMz((int)cmd.getAmount());
			vpay.setReqUid(String.valueOf(gameSession.getUid()));
			vpay.setReqOd(sdkOrder.getOrderNo());
			vpay.setReqTime(new Date(System.currentTimeMillis()));
			sdkOrderVpayMapper.insertSelective(vpay);
			sdkOrder.setPayId(vpay.getPayId());		
			sdkOrderMapper.insertSelective(sdkOrder);
			dal.commit();
		}finally{
			dal.close();
		}
		return submitOrder(sdkOrder, vpay);
	}
	private Result submitOrder(SdkOrder sdkOrder, SdkOrderVpay vpay) {
		
		String pay_info=null;
		try {
			pay_info=getPayInfo(sdkOrder,vpay);
			
		} catch (Exception e) {
			logger.error(e);

			orderException(sdkOrder, vpay, e.getMessage());

			return StatusCode.ERR_SYSTEM().setMessage(Cfg.msg.getString("sdk.system.pay.exception", "订单处理失败,请稍候再试!"));
		}
		DAL dal = DALFactory.createDAL();
		try{
			sdkOrder.setStatus(2);
			updateOrder(dal, sdkOrder, vpay);

			return new Result(new ResultPayinfo(sdkOrder.getOrderNo(),pay_info));
		} finally {
			dal.close();
		}
	}
	private void updateOrder(DAL dal, SdkOrder sdkOrder, SdkOrderVpay vpay) {
		
		SdkOrderMapper sdkOrderMapper=dal.getMapper(SdkOrderMapper.class);
		SdkOrderVpayMapper sdkOrderVpayMapper=dal.getMapper(SdkOrderVpayMapper.class);
		sdkOrderMapper.updateByPrimaryKeySelective(sdkOrder);
		sdkOrderVpayMapper.updateByPrimaryKeySelective(vpay);
		dal.commit();
	}
	private void orderException(SdkOrder sdkOrder, SdkOrderVpay vpay,
			String message) {
		DAL dal = DALFactory.createDAL();
		try {
			 
			sdkOrder.setStatus(4);

			updateOrder(dal, sdkOrder, vpay);
		} finally {
			dal.close();
		}
		
	}
	private String getPayInfo(SdkOrder sdkOrder, SdkOrderVpay vpay) {
		
		String md5=vpay.getReqOd()+"_"+vpay.getReqMz();
		String signMd5=MD5Util.MD5Encode(md5, "UTF-8");
		StringBuilder sb=new StringBuilder();
		sb.append("mz=").append(vpay.getReqMz()).append("&");
		sb.append("md5=").append(signMd5).append("&");
		sb.append("uid=").append(vpay.getReqUid()).append("&");
		sb.append("oid=").append(vpay.getReqOd());
		String baseUrl=Cfg.cfg.getString("vpay_callbackurl");
		return baseUrl+"?"+sb.toString();
	}

}
