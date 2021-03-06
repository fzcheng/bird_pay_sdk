package com.cheyooh.service.sdk.action.client;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.alipay.client.security.RSASignature;
import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.StatusCode;
import com.cheyooh.service.sdk.db.dao.SdkGameAlipayMapper;
import com.cheyooh.service.sdk.db.dao.SdkOperatorPayTypeMapper;
import com.cheyooh.service.sdk.db.dao.SdkOrderAlipayMapper;
import com.cheyooh.service.sdk.db.dao.SdkOrderMapper;
import com.cheyooh.service.sdk.db.entity.SdkGameAlipay;
import com.cheyooh.service.sdk.db.entity.SdkGameAlipayExample;
import com.cheyooh.service.sdk.db.entity.SdkOperatorPayType;
import com.cheyooh.service.sdk.db.entity.SdkOperatorPayTypeExample;
import com.cheyooh.service.sdk.db.entity.SdkOrder;
import com.cheyooh.service.sdk.db.entity.SdkOrderAlipay;
import com.cheyooh.service.sdk.idata.CmdPay;
import com.cheyooh.service.sdk.idata.ResultPayinfo;

/**
 * 
 * 支付宝支付2.0版本
 * 
 * @author jiguang.liang@magicbirds.cn
 * 
 */
public class Alipay extends AbstractSdkClientService<CmdPay> {
	
	private int paymentType=1;
	
	protected Result verify(CmdPay cmd) {
		// TODO :发布时修改为1f
		if (cmd.getAmount() < 0.000001f) {
			return StatusCode.ERR_PARAMETER().setMessage("无效的支付金额: " + cmd.getAmount());
		}
		DAL dal = DALFactory.createDAL();
		try {
			// 
			SdkOperatorPayTypeMapper sdkOperatorPayTypeMapper = dal.getMapper(SdkOperatorPayTypeMapper.class);
			SdkOperatorPayTypeExample sdkOperatorPayTypeExample = new SdkOperatorPayTypeExample();
			sdkOperatorPayTypeExample.createCriteria().andTypeEqualTo(paymentType).andVerLessThanOrEqualTo(cmd.getSdkver());
			List<SdkOperatorPayType> sdkOperatorPayTypeLists = sdkOperatorPayTypeMapper.selectByExample(sdkOperatorPayTypeExample);
			if(sdkOperatorPayTypeLists!=null && sdkOperatorPayTypeLists.size()>0){
				for (SdkOperatorPayType sdkOperatorPayType : sdkOperatorPayTypeLists) {
					if(sdkOperatorPayType.getMinPrice()<=cmd.getAmount() && sdkOperatorPayType.getMaxPrice()>=cmd.getAmount()){
						return super.verify(cmd);
					}
				}
			}
		}catch(Exception e){
			logger.error("the Pay_wechat appear exception :"+e);
		}finally {
			dal.close();
		}
		// 匹配不到相关计费金额
		return StatusCode.ERR_PARAMETER().setMessage("不支持此计费金额" + cmd.getAmount());
	}
 
	@Override
	protected boolean isLoginRequired() {
		return true;
	}

	@Override
	protected Result execute(CmdPay cmd) {
		DAL dal = DALFactory.createDAL();
		SdkOrderMapper sdkOrderMapper = dal.getMapper(SdkOrderMapper.class);
		SdkOrder sdkOrder=createOrder(cmd,1);
		sdkOrder.setStatus(2);
		
		SdkOrderAlipayMapper sdkOrderAlipayMapper = dal.getMapper(SdkOrderAlipayMapper.class);
		SdkOrderAlipay sdkOrderAlipay = new SdkOrderAlipay();
		
		String pay_info = null;
		try {
			HttpServletRequest httpServletRequest = cmd.getServiceContext().getRequest();
			String sdkver_Param = httpServletRequest.getParameter("sdkver");
			sdkOrder.setSdkver(sdkver_Param);
			SdkGameAlipayMapper sdkGameAlipayMapper=dal.getMapper(SdkGameAlipayMapper.class);
			SdkGameAlipayExample sdkGameAlipayExample=new SdkGameAlipayExample();
			sdkGameAlipayExample.createCriteria().andGameIdEqualTo(game.getGameId());
			SdkGameAlipay sdkGameAlipay=sdkGameAlipayMapper.selectOne(sdkGameAlipayExample);
			
			if(sdkGameAlipay!=null){
				sdkOrderAlipay.setReqNotifyUrl(sdkGameAlipay.getNotifyUrl());
				sdkOrderAlipay.setReqPartner(sdkGameAlipay.getPartner());
				sdkOrderAlipay.setReqSeller(sdkGameAlipay.getSeller());
			}else{
				return StatusCode.ERR_SYSTEM().setMessage("游戏没配置支付宝支付");
			}
			// 帐号(财付通商户号或者财付通帐号)			   
			sdkOrderAlipay.setReqBody(sdkOrder.getOrderName());
			sdkOrderAlipay.setReqOutTradeNo(sdkOrder.getOrderNo());
			sdkOrderAlipay.setReqSubject(sdkOrder.getOrderName());
			sdkOrderAlipay.setReqTime(sdkOrder.getCreateTime());
			sdkOrderAlipay.setReqTotalFee(sdkOrder.getAmount());
//			sdkOrderAlipay.setReqNotifyUrl(PartnerConfig.NOTIFY_URL);
//			sdkOrderAlipay.setReqPartner(PartnerConfig.PARTNER);
//			sdkOrderAlipay.setReqSeller(PartnerConfig.SELLER);

			sdkOrderAlipayMapper.insertSelective(sdkOrderAlipay);
			sdkOrder.setPayId(sdkOrderAlipay.getPayId());			 
			sdkOrderMapper.insert(sdkOrder);

			pay_info = getSign(sdkOrderAlipay,sdkGameAlipay);
			logger.info("the pay_alipay orderNo ="+sdkOrder.getOrderNo()+", the pay_info is :"+pay_info);
			dal.commit();
		} catch (Exception e) {
			logger.error("the pay_alipay exception ="+e);
			sdkOrder.setStatus(4);
			sdkOrderMapper.insert(sdkOrder);
			dal.commit();
			return StatusCode.ERR_SYSTEM().setMessage("订单处理失败,请稍候再试!");
		}finally {
			dal.close();
		}
		
		Result result=new Result(new ResultPayinfo(sdkOrder.getOrderNo(),pay_info));
		logger.debug("the Pay_alipay result is :"
				+ result.getXml("Pay_alipay"));
		return result;
	}

	private String getSign(SdkOrderAlipay alipay,SdkGameAlipay sdkGameAlipay) {
		try{
			// 组装待签名数据
			StringBuilder sb = new StringBuilder();
			sb.append("partner=\"").append(alipay.getReqPartner()).append("\"");
			sb.append("&");
			sb.append("seller_id=\"").append(alipay.getReqSeller()).append("\"");
			sb.append("&");
			sb.append("out_trade_no=\"").append(alipay.getReqOutTradeNo()).append("\"");
			sb.append("&");
			sb.append("subject=\"").append(alipay.getReqSubject()).append("\"");
			sb.append("&");
			sb.append("body=\"").append(alipay.getReqBody()).append("\"");
			sb.append("&");
			sb.append("total_fee=\"").append(alipay.getReqTotalFee()).append("\"");
			sb.append("&");
			sb.append("notify_url=\"").append(URLEncoder.encode(alipay.getReqNotifyUrl(),"utf-8")).append("\"");
			//以下是新版本新参数
			// 服务接口名称， 固定值
			sb.append("&service=\"mobile.securitypay.pay\"");
			// 支付类型， 固定值
			sb.append("&payment_type=\"1\"");
			// 参数编码， 固定值
			sb.append("&_input_charset=\"utf-8\"");
			// 设置未付款交易的超时时间
			// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
			// 取值范围：1m～15d。
			// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
			// 该参数数值不接受小数点，如1.5h，可转换为90m。
			sb.append("&it_b_pay=\"30m\"");
			// extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
			// orderInfo += "&extern_token=" + "\"" + extern_token + "\"";
			// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
			sb.append("&return_url=\"m.alipay.com\"");
			// 签名
			String sign = RSASignature.sign(sb.toString(), sdkGameAlipay.getRsaPrivate());
//			String sign = RSASignature.sign(sb.toString(), PartnerConfig.RSA_PRIVATE);
			
		    //UrlEncode
			sign=URLEncoder.encode(sign,"utf-8");
			// 签名后的订单字符串
			sb.append("&");
			sb.append("sign_type=\"RSA\"");
			sb.append("&");
			sb.append("sign=\"").append(sign).append("\"");

			return sb.toString();
		}catch (Exception e) {
			logger.error("the pay_alipay getSign error ="+e);
			return null;
		}
	}
	
	public static void main(String[] args) {
		/*StringBuilder sb = new StringBuilder();
		sb.append("partner=\"").append("2088121306650932").append("\"");
		sb.append("&");
		sb.append("seller_id=\"").append("molixiaoniaozhifu@magicbirds.cn").append("\"");
		sb.append("&");
		String order_no=GenerateTool.createOrderNo();
		sb.append("out_trade_no=\"").append(order_no).append("\"");
		sb.append("&");
		sb.append("subject=\"").append("内测游戏").append("\"");
		sb.append("&");
		sb.append("body=\"").append("内测游戏").append("\"");
		sb.append("&");
		sb.append("total_fee=\"").append("0.1").append("\"");
		sb.append("&");
		try {
			sb.append("notify_url=\"").append(URLEncoder.encode(PartnerConfig.NOTIFY_URL,"utf-8")).append("\"");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 服务接口名称， 固定值
		sb.append("&service=\"mobile.securitypay.pay\"");
		// 支付类型， 固定值
		sb.append("&payment_type=\"1\"");
		// 参数编码， 固定值
		sb.append("&_input_charset=\"utf-8\"");
		// 设置未付款交易的超时时间
		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		sb.append("&it_b_pay=\"30m\"");
		// extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
		// orderInfo += "&extern_token=" + "\"" + extern_token + "\"";
		// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
		sb.append("&return_url=\"\"");
		// 签名
		String sign = RSASignature.sign(sb.toString(), PartnerConfig.RSA_PRIVATE);
		
	    //UrlEncode
		try {
			sign=URLEncoder.encode(sign,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 签名后的订单字符串
		sb.append("&");
		sb.append("sign_type=\"RSA\"");
		sb.append("&");
		sb.append("sign=\"").append(sign).append("\"");
		System.out.println(sb.toString());*/
	}

}
