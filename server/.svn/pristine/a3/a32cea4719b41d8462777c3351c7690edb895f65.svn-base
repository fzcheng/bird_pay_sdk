package com.cheyooh.service.sdk.action.external;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.sdk.db.dao.SdkGameMapper;
import com.cheyooh.service.sdk.db.dao.SdkOrderMapper;
import com.cheyooh.service.sdk.db.dao.SdkOrderMmdoMapper;
import com.cheyooh.service.sdk.db.entity.SdkGame;
import com.cheyooh.service.sdk.db.entity.SdkOrder;
import com.cheyooh.service.sdk.db.entity.SdkOrderMmdo;
import com.cheyooh.service.sdk.tools.GenerateTool;
import com.cheyooh.tools.log.Logger;

/**
 * @author ljg
 * 
 */
@WebServlet("/dispatchJsp/JincaijifenServlet")
public class JincaijifenServlet extends HttpServlet {

	private int operationType=1;
	private String channelCode="cmccZwjfWeb";
	private static final Integer PAY_OPERATOR=9;
	static Logger logger = new Logger(JincaijifenServlet.class);

	private static final long serialVersionUID = -4805739240172064516L;

	public JincaijifenServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doService(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doService(request, response);
	}

	protected void doService(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DAL dal = DALFactory.createDAL();
		String orderNo = GenerateTool.createOrderNo();
		String jspPath = "";
		String amount = (String) request.getParameter("amount");
		String gameidString=request.getParameter("gameId");
		String outOrderNo=request.getParameter("outOrderNo");
		//默认传值
		String sendcontent = "DH931892";
		// logger.debug("the ServletJincai amount =" + amount);
		try {
			if (StringUtils.isNotEmpty(amount)
					&& StringUtils.isNotBlank(amount)
					&&StringUtils.isNotEmpty(gameidString)
					&& StringUtils.isNotBlank(gameidString)
					&&StringUtils.isNumeric(gameidString)
					&&StringUtils.isNotBlank(outOrderNo)
					) {
				float amount_float = Float.valueOf(amount);
				if (amount_float == 1) {
					sendcontent = "DH931892";
				} else if (amount_float == 5) {
					sendcontent = "DH933702";
				} else if (amount_float == 10) {
					sendcontent = "DH933709";
				} else if (amount_float == 20) {
					sendcontent = "DH933928";
				} else if (amount_float == 30) {
					sendcontent = "DH933947";
				} else if (amount_float == 50) {
					sendcontent = "DH933952";
				} else if (amount_float == 100) {
					sendcontent = "DH934010";
				}
				int gameIdInt=parseInteger(gameidString);
				Date time = new Date();
				SdkOrderMmdo orderMmdo = new SdkOrderMmdo();
				orderMmdo.setGameId(gameIdInt);
				orderMmdo.setOperationType(operationType);
				orderMmdo.setReqOrderAmount(amount_float);
//				orderMmdo.setReqSendContent(smsContent);
//				orderMmdo.setReqSendNumber(smsPort);
				orderMmdo.setReqTime(time);
//				orderMmdo.setReqImsi(cmd.getImsi());
//				orderMmdo.setImei(cmd.getImei());
				orderMmdo.setIpAddr(request.getRemoteHost());
				orderMmdo.setPayChannelCode(channelCode);
				SdkOrderMmdoMapper orderMmdoMapper = dal
						.getMapper(SdkOrderMmdoMapper.class);
				orderMmdoMapper.insertSelective(orderMmdo);

				SdkOrder order = new SdkOrder();
				order.setAmount(amount_float);
				order.setChannel("cmccZwjfWeb");
//				order.setCpExt(cmd.getOut_trade_no());
				
				SdkGameMapper  sdkGameMapper=dal.getMapper(SdkGameMapper.class);
				SdkGame sdkGame=sdkGameMapper.selectByPrimaryKey(gameIdInt);
				if(sdkGame!=null){
					order.setCpId(sdkGame.getCpId());
					order.setOrderDesc(sdkGame.getName());
					order.setOrderName(sdkGame.getName());
					order.setCpExt(outOrderNo);
				}
				order.setCreateTime(time);
				order.setGameId(gameIdInt);
				order.setOrderNo(orderNo);
				order.setPayId(orderMmdo.getPayId());
				order.setStatus(0);
				order.setNotifyStatus(0);
				order.setType(PAY_OPERATOR);
				// order.setUid(gameSession.getUid());
				SdkOrderMapper orderMapper = dal.getMapper(SdkOrderMapper.class);
				orderMapper.insertSelective(order);
				request.setAttribute("sendcontent", sendcontent);
				request.setAttribute("orderNo", orderNo);
				jspPath = "/webpage/jsp/pay_zwjf.jsp";
				// request.getRequestDispatcher("/webpage/jsp/test.jsp").forward(request,response);
				// request.getRequestDispatcher("/webpage/jsp/success.jsp").forward(request,response);
				// request.getRequestDispatcher("/webpage/jsp/fail.jsp").forward(request,response);
			}else{
				jspPath="/webpage/jsp/fail.jsp";
			}
			dal.commit();
		} catch (Exception e) {
			logger.error("the CmccZwjfJincaiServlet appear error =" + e);
			jspPath = "/webpage/jsp/fail.jsp";
		} finally {
			dal.close();
			request.getRequestDispatcher(jspPath).forward(request, response);
		}
	}
	
	private Integer parseInteger(String str) {
		Integer num = null;
		try {
			if (StringUtils.isNotBlank(str)) {
				num = Integer.valueOf(str);
			}
		} catch (NumberFormatException e) {
			logger.warn("parse number string error! str = " + str);
		}
		return num;
	}
	
}
