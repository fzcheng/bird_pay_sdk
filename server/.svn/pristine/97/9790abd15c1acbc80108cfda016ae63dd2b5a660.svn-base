package com.cheyooh.service.sdk.action.client;

import javax.servlet.http.HttpServletRequest;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.ResultXJContent;
import com.cheyooh.service.framework.idata.StatusCode;
import com.cheyooh.service.sdk.db.dao.SdkOrderMapper;
import com.cheyooh.service.sdk.db.entity.SdkOrder;
import com.cheyooh.service.sdk.idata.CmdGeneral;

public class GetShenzhoufuPayResult extends AbstractSdkClientService<CmdGeneral> {
	private String resultMsg = "fail";

	@Override
	protected boolean isLoginRequired() {
		return true;
	}

	@Override
	protected Result execute(CmdGeneral cmd) {
		DAL dal = DALFactory.createDAL();
		try {
			SdkOrderMapper sdkOrderMapper = dal.getMapper(SdkOrderMapper.class);
			HttpServletRequest httpServletRequest = cmd.getServiceContext()
					.getRequest();
			String orderNo = httpServletRequest.getParameter("order_no");
			SdkOrder sdkOrder = sdkOrderMapper.selectByPrimaryKey(orderNo);
			if (sdkOrder != null) {
				if (sdkOrder.getStatus() == 1) {
					resultMsg = "success";
				}
			}
			logger.info("the GetShenzhoufuPayResult orderNo =" + orderNo+", the result ="+resultMsg);
			dal.commit();
		} catch (Exception e) {
			logger.error("the GetShenzhoufuPayResult exception =" + e);
			dal.commit();
			return StatusCode.ERR_SYSTEM().setMessage("订单处理失败,请稍候再试!");
		} finally {
			dal.close();
		}
		Result result = new Result(new ResultXJContent(resultMsg, resultMsg));
		return result;
	}

	public static void main(String[] args) {
	}
}
