package com.cheyooh.service.sdk.action.external;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.sdk.db.dao.SdkOrderMapper;
import com.cheyooh.service.sdk.db.dao.SdkOrderMmdoMapper;
import com.cheyooh.service.sdk.db.entity.SdkOrder;
import com.cheyooh.service.sdk.db.entity.SdkOrderMmdo;
import com.cheyooh.service.sdk.idata.gameserver.JsonCmccZwjfConsumeVcodeReq;
import com.cheyooh.service.sdk.idata.gameserver.JsonCmccZwjfValidateVcodeReq;
import com.cheyooh.service.sdk.idata.gameserver.JsonCmccZwjfVirtualcodeRes;
import com.cheyooh.tools.log.Logger;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author ljg
 *  
 */
@WebServlet("/dispatchJsp/CmccZwjfServlet")
public class CmccZwjfServlet extends HttpServlet {
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = -2528734992032191689L;
	private static Logger logger = new Logger(JincaijifenServlet.class);	
	private String secretKey = "ee970c86054cb39e203024e7e74934e178d75142a9e8bf245e83ae00cef836330e022eb7ee4c2283ff2d294c6f493321c7c3bc11c7f5ab03180280b7396b730a";
	private String qudaoshangcode = "1006";
	private static final ObjectMapper mapper = new ObjectMapper();
	static {
		// mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		// or jackson 2.0
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		mapper.setSerializationInclusion(Include.NON_NULL);
		// jackson 1.9 and before
		// mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
		// false);
	}
	   
	public CmccZwjfServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request,response);
	}
 
	@SuppressWarnings("unused")
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		DAL dal = DALFactory.createDAL();
		
		String jspDispatchPath="";
		String resultCode = "fail";
		String resultMsg = "";
		String dealid="";
		String mobilephone ="";
		String virtualCode=request.getParameter("virtualCode");
		String orderNo=request.getParameter("orderNo");
		logger.debug("the ServletZwjf orderNo = "+orderNo+" , virtualCode ="+virtualCode);
		
		try{
			if(!StringUtils.isNotBlank(orderNo)||!StringUtils.isNotBlank(virtualCode)){
				logger.info("the ServletZwjf orderNo or virtualCode is empty");
				jspDispatchPath="/webpage/jsp/fail.jsp";
				request.getRequestDispatcher(jspDispatchPath).forward(request, response);
				return ;
			}
			SdkOrderMapper sdkOrderMapper = dal.getMapper(SdkOrderMapper.class);
			SdkOrder sdkOrder = sdkOrderMapper.selectByPrimaryKey(orderNo);
			if (sdkOrder == null) {
				resultCode = "fail";
				resultMsg = "sdkOrder无此订单号";
				jspDispatchPath="/webpage/jsp/fail.jsp";
				request.getRequestDispatcher(jspDispatchPath).forward(request, response);
				return ;
			}

			SdkOrderMmdoMapper sdkOrderMmdoMapper = dal
					.getMapper(SdkOrderMmdoMapper.class);
			SdkOrderMmdo sdkOrderMmdo = sdkOrderMmdoMapper
					.selectByPrimaryKey(sdkOrder.getPayId());

			int orderStatus = 3;
			int orderMmdoStatus=0;
			String orderStatusDetail = sdkOrder.getStatusDetail();
			String originalcode = sdkOrder.getOriginalcode();

			if (sdkOrderMmdo == null) {
				sdkOrder.setStatus(orderStatus);
				sdkOrder.setStatusDetail(orderStatusDetail);
				sdkOrder.setOriginalcode(originalcode);
				sdkOrderMapper.updateByPrimaryKeySelective(sdkOrder);
				dal.commit();
				
				resultCode = "fail";
				resultMsg = "sdkOrderMmdo无此订单号";
				jspDispatchPath="/webpage/jsp/fail.jsp";
				request.getRequestDispatcher(jspDispatchPath).forward(request, response);
				return ;
			}
			
			//防止重复提交成功订单
			if(sdkOrder.getStatus()==1
					&&StringUtils.isNotBlank(sdkOrderMmdo.getTradeid())
					&&StringUtils.isNotEmpty(sdkOrderMmdo.getTradeid())
					&&StringUtils.isNotBlank(sdkOrderMmdo.getRespSendContent())
					&&StringUtils.isNotEmpty(sdkOrderMmdo.getRespSendContent())
					){
				resultCode = "success";
				resultMsg = "";
				jspDispatchPath="/webpage/jsp/success.jsp";
				request.getRequestDispatcher(jspDispatchPath).forward(request, response);
				return ;
			}
			
			JsonCmccZwjfValidateVcodeReq jsonCmccZwjfValidateVcodeReq = new JsonCmccZwjfValidateVcodeReq();
			jsonCmccZwjfValidateVcodeReq.setVcode(virtualCode);
			Date validateVcodedate = new Date();
			String validatetime = DateToStr(validateVcodedate);
			jsonCmccZwjfValidateVcodeReq.setValidatetime(validatetime);
			String jsonOrderbeforeString = mapper
					.writeValueAsString(jsonCmccZwjfValidateVcodeReq);
			logger.debug("the ConsumeCmccZwjfVirtualCode ,  jsonOrderbeforeString ="
					+ jsonOrderbeforeString);
			String validateVcodeReq = getReq(jsonOrderbeforeString);
			JsonCmccZwjfVirtualcodeRes jsonCmccZwjfVirtualcodeRes = validateVcode(validateVcodeReq);
			logger.info("the ConsumeCmccZwjfVirtualCode ,jsonCmccZwjfVirtualcodeRes is:"+jsonCmccZwjfVirtualcodeRes.toString());

			mobilephone = jsonCmccZwjfVirtualcodeRes.getPhone();
			dealid=jsonCmccZwjfVirtualcodeRes.getDealid();
			if (!"0".equals(jsonCmccZwjfVirtualcodeRes.getCode())) {
				resultCode = "fail";
				resultMsg = jsonCmccZwjfVirtualcodeRes.getMsg();
				jspDispatchPath="/webpage/jsp/fail.jsp";
				
				sdkOrderMmdo.setRespSendContent(virtualCode);
				sdkOrderMmdo.setRespStatus(orderMmdoStatus);
				if(StringUtils.isNotBlank(mobilephone)){
					sdkOrderMmdo.setMobilephone(mobilephone);
				}
				if(StringUtils.isNotBlank(dealid)){
					sdkOrderMmdo.setTradeid(dealid);
				}
				sdkOrderMmdoMapper.updateByPrimaryKeySelective(sdkOrderMmdo);
				
				sdkOrder.setStatus(orderStatus);
				sdkOrder.setStatusDetail(orderStatusDetail);
				sdkOrder.setOriginalcode(originalcode);
				sdkOrderMapper.updateByPrimaryKeySelective(sdkOrder);
				dal.commit();
				
				jspDispatchPath="/webpage/jsp/fail.jsp";
				request.getRequestDispatcher(jspDispatchPath).forward(request, response);
				return ;
			} 
			
			JsonCmccZwjfConsumeVcodeReq jsonCmccZwjfConsumeVcodeReq = new JsonCmccZwjfConsumeVcodeReq();
			jsonCmccZwjfConsumeVcodeReq.setVcode(virtualCode);
			Date consumedate = new Date();
			String consumetime = DateToStr(consumedate);
			jsonCmccZwjfConsumeVcodeReq.setConsumetime(consumetime);
			jsonCmccZwjfConsumeVcodeReq.setMerchantcode("");
			String jsonOrderbeforeString2 = mapper
					.writeValueAsString(jsonCmccZwjfConsumeVcodeReq);
			String consumecodeReq = getReq(jsonOrderbeforeString2);
			JsonCmccZwjfVirtualcodeRes jsonCmccZwjfVirtualcodeRes2 = consumeVcode(consumecodeReq);
			logger.info("the ConsumeCmccZwjfVirtualCode ,jsonCmccZwjfVirtualcodeRes2 is:"+jsonCmccZwjfVirtualcodeRes2.toString());
			
			if ("0".equals(jsonCmccZwjfVirtualcodeRes2.getCode())) {
				orderStatus = 1;
				orderMmdoStatus=1;
				resultCode = "success";
				resultMsg = "";
				jspDispatchPath="/webpage/jsp/success.jsp";
			} else {
				orderStatus=3;
				orderMmdoStatus=0;
				resultCode = "fail";
				resultMsg = jsonCmccZwjfVirtualcodeRes2.getMsg();
				jspDispatchPath="/webpage/jsp/fail.jsp";
			}
			mobilephone = jsonCmccZwjfVirtualcodeRes.getPhone();
			dealid=jsonCmccZwjfVirtualcodeRes.getDealid();
			sdkOrderMmdo.setRespSendContent(virtualCode);
			sdkOrderMmdo.setRespStatus(orderMmdoStatus);
			sdkOrderMmdo.setMobilephone(mobilephone);
			sdkOrderMmdo.setTradeid(dealid);
			
			sdkOrder.setStatus(orderStatus);
			sdkOrder.setStatusDetail(orderStatusDetail);
			sdkOrder.setOriginalcode(originalcode);
			sdkOrderMapper.updateByPrimaryKeySelective(sdkOrder);
			dal.commit();
			request.getRequestDispatcher(jspDispatchPath).forward(request, response);
			
		}catch(Exception e){
			logger.error("the ServletZwjf appear error ="+e);
			jspDispatchPath="/webpage/jsp/fail.jsp";
			request.getRequestDispatcher(jspDispatchPath).forward(request, response);
		}finally{
			dal.close();
		}
	}
	
	/**
	 * 
	 * 
	 * @param date
	 * @return str
	 */
	public static String DateToStr(Date date) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
		String str = format.format(date);
		return str;
	}
	
	public String getReq(String jsonString) {
		String result = "";
		String jsonOrderAfterString = orderStrWithASC(jsonString);
		String sign = DigestUtils.md5Hex(secretKey + jsonOrderAfterString)
				.toLowerCase();
		String beforBase64String = sign + jsonString + qudaoshangcode;
		result = Base64.encodeBase64String(beforBase64String.getBytes());
		return result;
	}
	
	/**
	 * 
	 * 
	 * @param date
	 * @return str
	 */
	public static String orderStrWithASC(String param) {
		String result = "";
		if (StringUtils.isNotEmpty(param) && StringUtils.isNotBlank(param)) {
			char[] c = param.toCharArray();
			Arrays.sort(c);
			result = String.valueOf(c);
		} else {
			return null;
		}
		return result;
	}

	private JsonCmccZwjfVirtualcodeRes validateVcode(String req) {
		HttpClient httpclient = new DefaultHttpClient();
		JsonCmccZwjfVirtualcodeRes jsonCmccZwjfVirtualcodeRes = new JsonCmccZwjfVirtualcodeRes();
		try {
			
			String sendUrl = "http://jfj.i139.cn/aspire_itfc/validateVcode";
			//卓望测试地址
//			String sendUrl = "http://cacti.yunbobao.cn/validateVcode";
			logger.debug("the ConsumeCmccZwjfVirtualCode validateVcode send api = "
					+ sendUrl);
			HttpPost httpost = new HttpPost(sendUrl);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("req", req));
			httpost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			HttpResponse response = httpclient.execute(httpost);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity, "UTF-8");
			jsonCmccZwjfVirtualcodeRes = mapper.readValue(content,
					JsonCmccZwjfVirtualcodeRes.class);
			logger.debug("the ConsumeCmccZwjfVirtualCode validateVcode ,"
					+ jsonCmccZwjfVirtualcodeRes.toString());
		} catch (Exception e) {
			logger.error(
					"the ConsumeCmccZwjfVirtualCode validateVcode request error ="
							+ e, e);
			return null;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return jsonCmccZwjfVirtualcodeRes;
	}

	private JsonCmccZwjfVirtualcodeRes consumeVcode(String req) {
		HttpClient httpclient = new DefaultHttpClient();
		JsonCmccZwjfVirtualcodeRes jsonCmccZwjfVirtualcodeRes = new JsonCmccZwjfVirtualcodeRes();
		try {
			String sendUrl = "http://jfj.i139.cn/aspire_itfc/consumeVCode";
			//卓望测试地址
//			String sendUrl = "http://cacti.yunbobao.cn/consumeVCode";
			logger.debug("the ConsumeCmccZwjfVirtualCode consumeVcode send api = "
					+ sendUrl);
			HttpPost httpost = new HttpPost(sendUrl);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("req", req));
			httpost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			HttpResponse response = httpclient.execute(httpost);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity, "UTF-8");
			jsonCmccZwjfVirtualcodeRes = mapper.readValue(content,
					JsonCmccZwjfVirtualcodeRes.class);
			logger.debug("the ConsumeCmccZwjfVirtualCode consumeVcode ,"+jsonCmccZwjfVirtualcodeRes.toString());
		} catch (Exception e) {
			logger.error(
					"the ConsumeCmccZwjfVirtualCode consumeVcode request error ="
							+ e, e);
			return null;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return jsonCmccZwjfVirtualcodeRes;
	}
	
	public static void main(String[] args) {
		/*String s1="11";
		String s2=null;
		if(!StringUtils.isNotBlank(s1)||!StringUtils.isNotBlank(s2)){
			System.out.println("fail");
		}else{
			System.out.println("success");
		}*/
	}
}
