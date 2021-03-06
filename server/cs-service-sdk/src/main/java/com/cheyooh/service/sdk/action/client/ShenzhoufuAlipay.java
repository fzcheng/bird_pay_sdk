package com.cheyooh.service.sdk.action.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.StatusCode;
import com.cheyooh.service.sdk.cfg.Cfg;
import com.cheyooh.service.sdk.db.dao.SdkGameShenzhoufuMapper;
import com.cheyooh.service.sdk.db.dao.SdkOperatorPayTypeMapper;
import com.cheyooh.service.sdk.db.dao.SdkOrderMapper;
import com.cheyooh.service.sdk.db.dao.SdkOrderShenzhoufuMapper;
import com.cheyooh.service.sdk.db.entity.SdkGameShenzhoufu;
import com.cheyooh.service.sdk.db.entity.SdkGameShenzhoufuExample;
import com.cheyooh.service.sdk.db.entity.SdkOperatorPayType;
import com.cheyooh.service.sdk.db.entity.SdkOperatorPayTypeExample;
import com.cheyooh.service.sdk.db.entity.SdkOrder;
import com.cheyooh.service.sdk.db.entity.SdkOrderShenzhoufu;
import com.cheyooh.service.sdk.idata.CmdPay;
import com.cheyooh.service.sdk.idata.ResultPayShenzhoufuWeixinInfo;


public class ShenzhoufuAlipay extends AbstractSdkClientService<CmdPay> {
	//paymentType 支付方式13是神州付支付宝
	private int paymentType=13;
	private String type="1";
	private String baseUrl="http://pay3.shenzhoufu.com/interface/version3/entry.aspx?";
	private String baseUrl_backup="http://pay6.shenzhoufu.com:8090/interface/version3/entry.aspx?";
	
//	private String pageReturnUrl="http://dev.leyogame.cn/dispatchJsp/payPageReturnUrlServlet?";
//	private String successPage_Url="http://dev.leyogame.cn/dispatchJsp/paySuccessUrlServlet?";
//	private String failPage_Url="http://dev.leyogame.cn/dispatchJsp/payFaiUrlServlet?";
	
	private String pageReturnUrl=Cfg.cfg.getString("sdk.shengzhoufu.pageReturnUrl");
	private String successPage_Url=Cfg.cfg.getString("sdk.shengzhoufu.paySuccessUrl");
	private String failPage_Url=Cfg.cfg.getString("sdk.shengzhoufu.payFailUrl");
	
	private String productUrl=Cfg.cfg.getString("sdk.shengzhoufu.aw.serverReturnUrl");
	private String serverReturnUrl=Cfg.cfg.getString("sdk.shengzhoufu.aw.serverReturnUrl");
			
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
			logger.error("the shenzhoufuWeixin appear exception :"+e);
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
		Result result=null;
		DAL dal = DALFactory.createDAL();
		try {
			SdkOrderMapper sdkOrderMapper = dal.getMapper(SdkOrderMapper.class);
			SdkOrder sdkOrder=createOrder(cmd,paymentType);
			
			String orderNo="";
			String version="3";
			String merId="";
			String payMoney="";
			
			//pc ： pc 端支付宝
			//mobile ：手机端 wap 支付宝
			String platform="pc";
			String verifyType="1";
			String returnType="3";
			String isDebug="0";
			String privateKey="";
			String sign="";
//			String ip = cmd.getRequestHost();
			String requestUrl="";
			String requestUrl_backup="";
			//微信支付充值传14，支付宝充值传16
			String gatewayId="16";
			
			orderNo=sdkOrder.getOrderNo();
			HttpServletRequest httpServletRequest = cmd.getServiceContext().getRequest();
			String sdkver_Param = httpServletRequest.getParameter("sdkver");
			sdkOrder.setSdkver(sdkver_Param);
			float amount=cmd.getAmount();
			int amountInt=(int)(amount*100);
			payMoney=String.valueOf(amountInt);
			SdkGameShenzhoufuMapper sdkGameShenzhoufuMapper=dal.getMapper(SdkGameShenzhoufuMapper.class);
			SdkGameShenzhoufuExample sdkGameShenzhoufuExample=new SdkGameShenzhoufuExample();
			sdkGameShenzhoufuExample.createCriteria().andGameIdEqualTo(game.getGameId());
			SdkGameShenzhoufu sdkGameShenzhoufu=sdkGameShenzhoufuMapper.selectOne(sdkGameShenzhoufuExample);
			if(sdkGameShenzhoufu!=null){
				merId=sdkGameShenzhoufu.getMerid();
				privateKey=sdkGameShenzhoufu.getPrivatekey();
			}else{
				return StatusCode.ERR_SYSTEM().setMessage("游戏没配置神州行微信支付");
			}
			
			//参与签名的参数
			Map<String,String> map=new HashMap<>();
			map.put("version", version);
			map.put("merId", merId);
			map.put("payMoney", payMoney);
			map.put("orderId", orderNo);
			map.put("pageReturnUrl", pageReturnUrl);
			map.put("serverReturnUrl", serverReturnUrl);
			map.put("privateField", orderNo);
			map.put("privateKey", privateKey);
			map.put("verifyType", verifyType);
			map.put("returnType", returnType);
			map.put("isDebug", isDebug);
			sign=getSign(map);
			
			requestUrl=baseUrl
					+"version="+version
					+"&merId="+merId
					+"&payMoney="+payMoney
					+"&orderId="+orderNo
					+"&pageReturnUrl="+pageReturnUrl
					+"&serverReturnUrl="+serverReturnUrl
					+"&platform="+platform
					+"&merUserName="+sdkOrder.getUid()
					+"&productUrl="+productUrl
					+"&privateField="+orderNo
					+"&gatewayId="+gatewayId
					+"&verifyType="+verifyType
					+"&returnType="+returnType
					+"&isDebug="+isDebug
					+"&md5String="+sign
					;
			
			requestUrl_backup=baseUrl_backup
					+"version="+version
					+"&merId="+merId
					+"&payMoney="+payMoney
					+"&orderId="+orderNo
					+"&pageReturnUrl="+pageReturnUrl
					+"&serverReturnUrl="+serverReturnUrl
					+"&platform="+platform
					+"&merUserName="+sdkOrder.getUid()
					+"&productUrl="+productUrl
					+"&privateField="+orderNo
					+"&gatewayId="+gatewayId
					+"&verifyType="+verifyType
					+"&returnType="+returnType
					+"&isDebug="+isDebug
					+"&md5String="+sign
					;
				
			SdkOrderShenzhoufuMapper sdkOrderShenzhoufuMapper=dal.getMapper(SdkOrderShenzhoufuMapper.class);
			SdkOrderShenzhoufu sdkOrderShenzhoufu=new SdkOrderShenzhoufu();
			sdkOrderShenzhoufu.setCreatedTime(sdkOrder.getCreateTime());
			sdkOrderShenzhoufu.setType(type);
			sdkOrderShenzhoufu.setOrderNo(orderNo);
			sdkOrderShenzhoufu.setMerid(merId);
			sdkOrderShenzhoufu.setVersion(version);
			sdkOrderShenzhoufu.setPaymoney(sdkOrder.getAmount());
			sdkOrderShenzhoufu.setPagereturnurl(pageReturnUrl);
			sdkOrderShenzhoufu.setServerreturnurl(serverReturnUrl);
			String merusername=String.valueOf(sdkOrder.getUid());
			sdkOrderShenzhoufu.setMerusername(merusername);
			sdkOrderShenzhoufu.setProducturl(productUrl);
			sdkOrderShenzhoufu.setPrivatefield(orderNo);
			sdkOrderShenzhoufuMapper.insertSelective(sdkOrderShenzhoufu);
			
			sdkOrder.setPayId(sdkOrderShenzhoufu.getPayId());
			sdkOrderMapper.insert(sdkOrder);
			dal.commit();
			result=new Result(new ResultPayShenzhoufuWeixinInfo(orderNo,requestUrl,requestUrl_backup,successPage_Url,failPage_Url,productUrl));
		} catch (Exception e) {
			logger.error("the shenzhoufuWeixin exception ="+e);
			dal.commit();
			result=StatusCode.ERR_SYSTEM().setMessage("订单处理异常,请稍候再试!");
		}finally {
			dal.close();
		}
		
		logger.debug("the shenzhoufuWeixin result is :"
				+ result.getXml("shenzhoufuWeixin"));
		return result;
	}

	/**
	 * 
	 * @param map里面的参数不为空
	 * @return
	 */
	private String getSign(Map<String, String> map) {
		String result="";
		try{
			String plainText=map.get("version")+"|"
					+map.get("merId")+"|"
					+map.get("payMoney")+"|"
					+map.get("orderId")+"|"
					+map.get("pageReturnUrl")+"|"
					+map.get("serverReturnUrl")+"|"
					+map.get("privateField")+"|"
					+map.get("privateKey")+"|"
					+map.get("verifyType")+"|"
					+map.get("returnType")+"|"
					+map.get("isDebug");
			logger.debug("the shenzhoufuWeixin plainText is :"+plainText);
			result=DigestUtils.md5Hex(plainText);
			logger.debug("the shenzhoufuWeixin md5String is :"+result);
		}catch (Exception e) {
			logger.error("the shenzhoufuWeixin getSign error ="+e);
			return null;
		}
		return result;
	}

	public static void main(String[] args) {
	}
}
