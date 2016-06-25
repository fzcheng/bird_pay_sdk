package com.cheyooh.service.sdk.action.client;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.StatusCode;
import com.cheyooh.service.sdk.db.dao.SdkOrderMapper;
import com.cheyooh.service.sdk.db.dao.SdkOrderMmdoMapper;
import com.cheyooh.service.sdk.db.entity.SdkOrder;
import com.cheyooh.service.sdk.db.entity.SdkOrderMmdo;
import com.cheyooh.service.sdk.idata.CmdPaybackMmdo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Payback_mmdo extends AbstractSdkClientService<CmdPaybackMmdo> {
	private static final String DELIMITER = ",";
	private static final String CMCCZWJF = "cmcczwjf";
//	private static final String SPLIT_CONTENT_STRING = Cfg.cfg.getString(
//			"mmdo_content_spilt", "#");
	
	private static final ObjectMapper mapper = new ObjectMapper();
	static {
		// mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
	}

	@Override
	protected boolean isLoginRequired() {
		return true;
	}

	@Override
	protected Result execute(CmdPaybackMmdo cmd) {
		DAL dal = DALFactory.createDAL();
		try {
			String orderNo = cmd.getOrder_no();
			HttpServletRequest httpServletRequest = cmd.getServiceContext().getRequest();
			String originalcode = httpServletRequest.getParameter("originalcode");
			if (StringUtils.isEmpty(originalcode)
					|| StringUtils.isBlank(originalcode)) {
				originalcode = "";
			}

			SdkOrderMapper orderMapper = dal.getMapper(SdkOrderMapper.class);
			// 处理在订单表找不到订单号
			SdkOrder order = orderMapper.selectByPrimaryKey(orderNo);
			if (order == null) {
				logger.error("the pay back mmdo error, can not found the order no : "
						+ cmd.getOrder_no());
				return StatusCode.SUCCESS();
			}
			SdkOrderMmdoMapper orderMmdoMapper = dal
					.getMapper(SdkOrderMmdoMapper.class);
			SdkOrderMmdo orderMmdo = orderMmdoMapper.selectByPrimaryKey(order
					.getPayId());
			if (orderMmdo == null) {
				logger.error("the pay back mmdo error, can not found the mmdo order id : "
						+ order.getPayId());
				return StatusCode.SUCCESS();
			}

			String smstype = orderMmdo.getPayChannelCode();
			// 处理短信重发机制判断state，只有有一个state状态为1即表明已经成功发送出去
			String[] stateRespArray = cmd.getState().split(DELIMITER);
			boolean isPaid = false;
			for (String state : stateRespArray) {
				if ("1".equals(state)) {
					stateRespArray[0] = "1";
					isPaid = true;
					break;
				}
			}

			// 记录订单详细信息
			orderMmdo.setRespSendNumber(cmd.getNumber());
			orderMmdo.setRespSendContent(cmd.getContent());
			orderMmdo.setRespImsi(cmd.getImsi());
			orderMmdo.setRespTime(new Date());
			order.setOriginalcode(originalcode);

			logger.info("the payback_mmdo is : the orderNo =" + orderNo
					+ ", the pay id =" + orderMmdo.getPayId()
					+ ", the sdkOrderMmdo smstpye =" + cmd.getSms_type()
					+ ", the req sdkOrderMmdo state =" + cmd.getState()
					+ ", the last isPaid =" + isPaid
					+", and originalcode ="+originalcode);

			if (isPaid) {
				if(CMCCZWJF.equals(smstype)){
					orderMmdo.setRespStatus(1);
					order.setStatus(2);
					/*
					 * 	cmcczwjf01  --发送长指令
						cmcczwjf02 --截取确认短信
						cmcczwjf03 -- 发送确认短信
					*/
					if("cmcczwjf01".equals(originalcode)){
						order.setStatusDetail("移动卓望积分发送长指令成功");
					}else if("cmcczwjf02".equals(originalcode)){
						order.setStatusDetail("移动卓望积分截取确认短信成功");
					}else if("cmcczwjf03".equals(originalcode)){
						order.setStatusDetail("移动卓望积分发送确认短信成功");
					}
				}else {
					// 订单完成
					orderMmdo.setRespStatus(1);
					order.setStatus(1);
					order.setStatusDetail("");
				}
			} else {
				// 初始失败状态
				int orderstate = 3;
				String faildetail = "";
				if (originalcode.equals("leyosms1")) {
					// 未知原因错误，如拦截短信
					orderstate = 14;
					faildetail = "未知原因错误，如拦截短信";
				} else if (originalcode.equals("leyosms2")) {
					// 飞行模式
					orderstate = 15;
					faildetail = "飞行模式";
				} else if (originalcode.equals("leyosms3")) {
					// 长短信发送解析失败
					orderstate = 16;
					faildetail = "长短信发送解析失败";
				} else if (originalcode.equals("leyosms4")) {
					// 不在服务区
					orderstate = 17;
					faildetail = "不在服务区";
				} else if (originalcode.equals("leyosms-1000")) {
					// 超时，如余额不足
					orderstate = 18;
					faildetail = "超时，如余额不足";
				} else if (originalcode.equals("leyoordercancel")) {
					orderstate = 19;
					faildetail = "订单取消";
				}else if(originalcode.equals("leyosmsnull")){
					orderstate = 20;
					faildetail = "获取短信内容端口异常";
				}else if(originalcode.equals("leyo21")){
					orderstate = 21;
					faildetail = "获取验证码超时";
				}else if(originalcode.equals("cmcczwjf01")){
					orderstate = 2;
					faildetail = "移动卓望积分发送长指令失败";
				}
				else if(originalcode.equals("cmcczwjf02")){
					orderstate = 2;
					faildetail = "移动卓望积分截取确认短信失败";
				}
				else if(originalcode.equals("cmcczwjf03")){
					orderstate = 2;
					faildetail = "移动卓望积分发送确认短信失败";
				}
				orderMmdo.setRespStatus(orderstate);
				order.setStatus(orderstate);
				order.setStatusDetail(faildetail);
				logger.error("the Payback_mmdo error, orderNo =" + orderNo+", pay_id ="
						+orderMmdo.getPayId()+", db req sms port ="+orderMmdo.getReqSendNumber()
						+", db sms port ="+ orderMmdo.getRespSendNumber()
						+", receive sms port ="+cmd.getNumber()
						+", receive sms content ="+cmd.getContent()
						+", sms state ="+cmd.getState());
			}
			orderMmdoMapper.updateByPrimaryKeySelective(orderMmdo);
			orderMapper.updateByPrimaryKeySelective(order);
			dal.commit();
		} finally {
			dal.close();
		}
		return StatusCode.SUCCESS();
	}

}
