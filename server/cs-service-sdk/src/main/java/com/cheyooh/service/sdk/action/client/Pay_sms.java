package com.cheyooh.service.sdk.action.client;

import org.apache.commons.lang.StringUtils;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.dal.mybatis.SqlStatementHandler;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.StatusCode;
import com.cheyooh.service.sdk.db.dao.SdkNotifySinaMonthlyMapper;
import com.cheyooh.service.sdk.db.entity.SdkNotifySinaMonthly;
import com.cheyooh.service.sdk.db.entity.SdkNotifySinaMonthlyExample;
import com.cheyooh.service.sdk.idata.CmdPay;
import com.cheyooh.service.sdk.idata.ResultPayMmdoBlock;
import com.cheyooh.service.sdk.idata.ResultPayMmdoCommand;
import com.cheyooh.service.sdk.idata.ResultPayMmdoCommandList;
import com.cheyooh.service.sdk.idata.ResultPayMmdoInfo;

public class Pay_sms extends AbstractSdkClientService<CmdPay> {
	private static final String SINA_MONTH_SMS = "sinamonthsms";
	private static final String STATUS_WAIT = "0";
	private static final String STATUS_SUCCESS = "1";
	private static final String STATUS_FAIL = "2";

	@Override
	protected boolean isLoginRequired() {
		return true;
	}

	protected Result verify(CmdPay cmd) {
		String orderNo = cmd.getServiceContext().getRequest().getParameter("order_no");
		if (StringUtils.isBlank(orderNo)) {
			return StatusCode.ERR_PARAMETER().setMessage("无法获取订单号");
		}
		return super.verify(cmd);
	}

	@Override
	protected Result execute(CmdPay cmd) {

		DAL dal = DALFactory.createDAL();
		SqlStatementHandler.startLogSql();
		try {
			String orderNo = cmd.getServiceContext().getRequest().getParameter("order_no");			
			SdkNotifySinaMonthlyMapper monthlyMapper = dal
					.getMapper(SdkNotifySinaMonthlyMapper.class);
			SdkNotifySinaMonthlyExample example = new SdkNotifySinaMonthlyExample();
			example.createCriteria().andOrderNoEqualTo(orderNo);
			SdkNotifySinaMonthly monthly = monthlyMapper.selectOne(example);
			
			if (monthly == null) {
				logger.error("the order is not existed, order no + " + orderNo);
				return StatusCode.ERR_NOTFOUND().setMessage("订单不存在！");
			}

			ResultPayMmdoCommandList payMmdoCommandList = new ResultPayMmdoCommandList();

			payMmdoCommandList.setImsi(monthly.getImsi());
			payMmdoCommandList.setTime(0);
			
			if ("8".equals(monthly.getSmsState())) {
				payMmdoCommandList.setStatus(STATUS_SUCCESS);
			} else if ("3".equals(monthly.getSmsState()) ||
					"5".equals(monthly.getSmsState()) ||
					"7".equals(monthly.getSmsState()) ||
					"9".equals(monthly.getSmsState())){
				payMmdoCommandList.setStatus(STATUS_FAIL);
			} else {
				payMmdoCommandList.setStatus(STATUS_WAIT);
			}
			
			ResultPayMmdoCommand payMmdoCommand = new ResultPayMmdoCommand();
			payMmdoCommand.setContent(monthly.getUpCmd());
			payMmdoCommand.setNumber(monthly.getUpPort());

			ResultPayMmdoBlock payMmdoBlock = new ResultPayMmdoBlock();
			if(!"00".equals(monthly.getSmsKeywords())||!"0000".equals(monthly.getSmsKeywords())){
				payMmdoBlock.setKeyword(monthly.getSmsKeywords());
				payMmdoBlock.setNumber(monthly.getSmsKeynum());
			}
				
			payMmdoCommand.addContent(payMmdoBlock);
			payMmdoCommandList.addContent(payMmdoCommand);
			
			ResultPayMmdoInfo payinfo = new ResultPayMmdoInfo();
		    payinfo.setOrder_no(monthly.getOrderNo());
		    payinfo.setSms_pay_type(1);
		    payinfo.setSms_type(SINA_MONTH_SMS);
		    payinfo.addContent(payMmdoCommandList);
		    payinfo.setType("mmdo");
			dal.commit();

			Result result = new Result(payinfo);
			return result;

		} finally {
			dal.close();
		}

	}

}
