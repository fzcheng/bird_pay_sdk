package com.cheyooh.service.sdk.action.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.sdk.cfg.Cfg;
import com.cheyooh.service.sdk.db.dao.SdkGameShenzhoufuMapper;
import com.cheyooh.service.sdk.db.dao.SdkOrderMapper;
import com.cheyooh.service.sdk.db.dao.SdkOrderShenzhoufuMapper;
import com.cheyooh.service.sdk.db.entity.SdkGameShenzhoufu;
import com.cheyooh.service.sdk.db.entity.SdkGameShenzhoufuExample;
import com.cheyooh.service.sdk.db.entity.SdkOrder;
import com.cheyooh.service.sdk.db.entity.SdkOrderShenzhoufu;
import com.cheyooh.service.sdk.db.entity.SdkOrderShenzhoufuExample;
import com.cheyooh.tools.log.Logger;

/**
 * @author ljg
 *  
 */
@WebServlet("/dispatchJsp/payPageReturnUrlServlet")
public class payPageReturnUrlServlet extends HttpServlet {
//	private String paySuccessUrl="http://dev.leyogame.cn/dispatchJsp/paySuccessUrlServlet?";
//	private String payFailUrl="http://dev.leyogame.cn/dispatchJsp/payFaiUrlServlet?";
	
	private String paySuccessUrl=Cfg.cfg.getString("sdk.shengzhoufu.paySuccessUrl");
	private String payFailUrl=Cfg.cfg.getString("sdk.shengzhoufu.payFailUrl");
	
	private static Logger logger = new Logger(payPageReturnUrlServlet.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 6759013738613703017L;

	public payPageReturnUrlServlet() {
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
		try {
			String jspDispatchPath="";
			String version=request.getParameter("version");
			String merId=request.getParameter("merId");
			String payMoney=request.getParameter("payMoney");
			String orderId=request.getParameter("orderId");
			String payResult=request.getParameter("payResult");
			String privateField=request.getParameter("privateField");
			String payDetails=request.getParameter("payDetails");
			String md5String=request.getParameter("md5String");
			String privateKey="";
			int gameId=0;
			String orderNo=orderId;
			logger.info("the payPageReturnUrlServlet orderNo ="+orderId);
			
			SdkOrderMapper sdkOrderMapper=dal.getMapper(SdkOrderMapper.class);
			SdkOrder sdkOrder=sdkOrderMapper.selectByPrimaryKey(orderNo);
			if(sdkOrder!=null){
				gameId=sdkOrder.getGameId();
				SdkGameShenzhoufuMapper sdkGameShenzhoufuMapper=dal.getMapper(SdkGameShenzhoufuMapper.class);
				SdkGameShenzhoufuExample sdkGameShenzhoufuExample=new SdkGameShenzhoufuExample();
				sdkGameShenzhoufuExample.createCriteria().andGameIdEqualTo(gameId);
				SdkGameShenzhoufu sdkGameShenzhoufu=sdkGameShenzhoufuMapper.selectOne(sdkGameShenzhoufuExample);
				if(sdkGameShenzhoufu!=null){
					privateKey=sdkGameShenzhoufu.getPrivatekey();
				}
			}
			
			//参与签名的参数
			Map<String,String> map=new HashMap<>();
			map.put("version", version);
			map.put("merId", merId);
			map.put("payMoney", payMoney);
			map.put("orderId", orderId);
			map.put("payResult", payResult);
			map.put("privateField", privateField);
			map.put("payDetails", payDetails);
			map.put("privateKey", privateKey);
			String sign=getSign(map);
			if(!md5String.equals(sign)){
				logger.error("the payPageReturnUrlServlet appear md5 fail , md5String="+md5String+", sign="+sign);
				return ;
			}
			
			int orderStatus=0;
			if("1".equals(payResult)){
				orderStatus=1;
				jspDispatchPath=paySuccessUrl;
			}else{
				jspDispatchPath=payFailUrl;
			}
			if(sdkOrder!=null){
				sdkOrder.setStatus(orderStatus);
				sdkOrderMapper.updateByPrimaryKeySelective(sdkOrder);			
			}
			
			SdkOrderShenzhoufuMapper sdkOrderShenzhoufuMapper=dal.getMapper(SdkOrderShenzhoufuMapper.class);
			SdkOrderShenzhoufuExample sdkOrderShenzhoufuExample=new SdkOrderShenzhoufuExample();
			sdkOrderShenzhoufuExample.createCriteria().andOrderNoEqualTo(orderNo);
			SdkOrderShenzhoufu sdkOrderShenzhoufu=sdkOrderShenzhoufuMapper.selectOne(sdkOrderShenzhoufuExample);
			if(sdkOrderShenzhoufu!=null){
				sdkOrderShenzhoufu.setPayresult(payResult);
				Date time=new Date();
				sdkOrderShenzhoufu.setUpdatedTime(time);
				sdkOrderShenzhoufuMapper.updateByPrimaryKey(sdkOrderShenzhoufu);
			}
			dal.commit();
//			request.getRequestDispatcher(jspDispatchPath).forward(request, response);
			response.sendRedirect(jspDispatchPath);
		} catch (Exception e) {
			logger.error("the payPageReturnUrlServlet appear error is :"+e);
			return ;
		} finally {
			dal.close();
		}
	}
	
	private String getSign(Map<String,String> map){
		String result="";
		try{
			String plainText=map.get("version")
					+map.get("merId")
					+map.get("payMoney")
					+map.get("orderId")
					+map.get("payResult")
					+map.get("privateField")
					+map.get("payDetails")
					+map.get("privateKey");
			logger.debug("the payPageReturnUrlServlet plainText is :"+plainText);
			result=DigestUtils.md5Hex(plainText);;
			logger.debug("the payPageReturnUrlServlet md5String is :"+result);
		}catch(Exception e){
			logger.error("the payPageReturnUrlServlet getSign appear error is :"+e);
			return "";
		}
		return result;
	}
}
