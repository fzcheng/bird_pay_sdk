package com.cheyooh.service.sdk.action.client;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.StatusCode;
import com.cheyooh.service.sdk.cfg.Cfg;
import com.cheyooh.service.sdk.db.dao.SdkOrderMapper;
import com.cheyooh.service.sdk.db.dao.SdkOrderUpayMapper;
import com.cheyooh.service.sdk.db.entity.SdkOrder;
import com.cheyooh.service.sdk.db.entity.SdkOrderUpay;
import com.cheyooh.service.sdk.idata.CmdPay;
import com.cheyooh.service.sdk.idata.ResultPayinfo;

/**
 * upay支付方式
 * @author Jaly
 *
 */
public class Pay_upay  extends AbstractSdkClientService<CmdPay>{

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
		
		SdkOrder order=createOrder(cmd, 7);
		SdkOrderUpay upay=new SdkOrderUpay();
		DAL dal=DALFactory.createDAL();
		try {
			SdkOrderUpayMapper sdkOrderUpayMapper=dal.getMapper(SdkOrderUpayMapper.class);
			SdkOrderMapper sdkOrderMapper=dal.getMapper(SdkOrderMapper.class);
			upay.setReqDescription(order.getOrderDesc());
			upay.setReqExtrainfo(order.getOrderNo());
			upay.setReqPoint(order.getAmount());
			upay.setReqProductName(order.getOrderName());
			upay.setReqShowpayresult(true);
			upay.setReqTime(order.getCreateTime());
			upay.setReqTimeout(Cfg.cfg.getInteger("upay_timeout", 30000));
			sdkOrderUpayMapper.insertSelective(upay);
			order.setPayId(upay.getPayId());			 
			sdkOrderMapper.insert(order);
			dal.commit();
			
		} finally {
			dal.close();
		}
		return submitOrder(order, upay);
	}
	private Result submitOrder(SdkOrder order, SdkOrderUpay upay) {
		
		String pay_info=null;
		try {
			pay_info=getPayInfo(order,upay);
			
		} catch (Exception e) {
			logger.error(e);

			orderException(order, upay, e.getMessage());

			return StatusCode.ERR_SYSTEM().setMessage(Cfg.msg.getString("sdk.system.pay.exception", "订单处理失败,请稍候再试!"));
		}
		DAL dal=DALFactory.createDAL();
		try {
			order.setStatus(2);
			updateOrder(dal, order, upay);

			return new Result(new ResultPayinfo(order.getOrderNo(),pay_info));
		} finally {
			dal.close();
		}

	}
	private void updateOrder(DAL dal, SdkOrder order, SdkOrderUpay upay) {
		
		SdkOrderMapper sdkOrderMapper=dal.getMapper(SdkOrderMapper.class);
		SdkOrderUpayMapper sdkOrderUpayMapper=dal.getMapper(SdkOrderUpayMapper.class);
		
		sdkOrderUpayMapper.updateByPrimaryKey(upay);
		sdkOrderMapper.updateByPrimaryKey(order);
		dal.commit();
		
	}
	private void orderException(SdkOrder order, SdkOrderUpay upay,
			String message) {
		DAL dal=DALFactory.createDAL();
		try {
			order.setStatus(4);
			updateOrder(dal, order, upay);
		} finally {
			dal.close();
		}
		
	}
	private String getPayInfo(SdkOrder order, SdkOrderUpay upay) {
		
		StringBuilder sb=new StringBuilder();
		sb.append("productName=").append(upay.getReqProductName()).append("");
		sb.append("&");
		sb.append("point=").append((int)(upay.getReqPoint()*1)).append("");
		sb.append("&");
		sb.append("extraInfo=").append(upay.getReqExtrainfo()).append("");
		sb.append("&");
		sb.append("timeout=").append(upay.getReqTimeout()).append("");
		sb.append("&");
		sb.append("description=").append(upay.getReqDescription()).append("");
		sb.append("&");
		sb.append("showPayResult=").append(upay.getReqShowpayresult()).append("");
		return sb.toString();
	}

}
