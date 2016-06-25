package com.cheyooh.service.sdk.action.external;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.ResultXJContent;
import com.cheyooh.service.sdk.action.notify.AbstractNotifyService;
import com.cheyooh.service.sdk.db.dao.SdkGameMapper;
import com.cheyooh.service.sdk.db.dao.SdkOrderMapper;
import com.cheyooh.service.sdk.db.dao.SdkOrderMmdoMapper;
import com.cheyooh.service.sdk.db.entity.SdkGame;
import com.cheyooh.service.sdk.db.entity.SdkOrder;
import com.cheyooh.service.sdk.db.entity.SdkOrderMmdo;
import com.cheyooh.service.sdk.idata.CmdGetCmccZwjfExt;
import com.cheyooh.service.sdk.tools.GenerateTool;


public class GetCmccZwjfSms extends AbstractNotifyService<CmdGetCmccZwjfExt> {
	 private static final Integer PAY_OPERATOR=9;
	  private static final Integer CMCC = 1;
	  private static final String ERROR_CONTENT = "error";
	  private static final String CMCCZWJFEXTERNAL = "cmcczwjfexternal";
	  
	  
	  private final String XML_SMS = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + 
	      "<leyo>\n" +
	      "   <orderNo>%s</orderNo>\n" +
	      "   <outTradeNo>%s</outTradeNo>\n" +
	      "   <smsPort>%s</smsPort>\n" +
	      "   <smsContent>%s</smsContent>\n" +
	      "</leyo>";

	  
	  /* (non-Javadoc)
	   * @see com.cheyooh.service.framework.basic.Service#verify(com.cheyooh.service.framework.idata.Cmd)
	   */
	  @Override
	  protected Result verify(CmdGetCmccZwjfExt cmd) {
	    return null;
	  }

	  /* (non-Javadoc)
	   * @see com.cheyooh.service.framework.basic.Service#execute(com.cheyooh.service.framework.idata.Cmd)
	   */
	  @Override
	  protected Result execute(CmdGetCmccZwjfExt cmd) {
	    DAL dal = DALFactory.createDAL();
	    String orderNo = GenerateTool.createOrderNo();
	    String smsPort = "10658999";
	    String smsContent="";
	    try {
	    	//检查必要参数
	      if (StringUtils.isBlank(cmd.getGame_id()) ||
	          !StringUtils.isNumeric(cmd.getGame_id()) ||
	          StringUtils.isBlank(cmd.getMoney()) ||
	          !StringUtils.isNumeric(cmd.getMoney())
	          //StringUtils.isBlank(cmd.getOut_trade_no()) ||
	          //StringUtils.isBlank(cmd.getProduct_name()) ||
	          ) {
	        logger.error("the cmcczwjfsms pay , parameter error cmd = " + cmd.toString());
	        return sendError(orderNo, cmd.getOut_trade_no(), smsPort);
	      }
	      
	      //检查游戏ID
	      Integer gameId = Integer.valueOf(cmd.getGame_id());
	      SdkGameMapper gameMapper = dal.getMapper(SdkGameMapper.class);
	      SdkGame game = gameMapper.selectByPrimaryKey(gameId);
	      if (game == null) {
	        logger.error("the cmcczwjfsms pay , gameId = " + gameId + ", can not found!");
	        return sendError(orderNo, cmd.getOut_trade_no(), smsPort);
	      }
	      
	      float money = Float.parseFloat(cmd.getMoney());
	      if(money==1){
	    	  smsContent="DH931892";
	      }else if(money==5){
	    	  smsContent="DH933702";
	      }else if(money==10){
	    	  smsContent="DH933709";
	      }else if(money==20){
	    	  smsContent="DH933928";
	      }else if(money==30){
	    	  smsContent="DH933947";
	      }else if(money==50){
	    	  smsContent="DH933952";
	      }else if(money==100){
	    	  smsContent="DH934010";
	      }else{
	    	  logger.error("the cmcczwjfsms pay , money = " + money + ", can not found!");
	    	  return sendError(orderNo, cmd.getOut_trade_no(), smsPort);
	      }
	      
	      Date time = new Date();	   
	      SdkOrderMmdo orderMmdo = new SdkOrderMmdo();
	      orderMmdo.setGameId(gameId);
	      orderMmdo.setOperationType(CMCC);
	      orderMmdo.setReqOrderAmount(money);
	      orderMmdo.setReqSendContent(smsContent);
	      orderMmdo.setReqSendNumber(smsPort);
	      orderMmdo.setReqTime(time);
	      orderMmdo.setReqImsi(cmd.getImsi());
	      orderMmdo.setImei(cmd.getImei());
	      orderMmdo.setIpAddr(cmd.getRequestHost());
	      orderMmdo.setPayChannelCode(CMCCZWJFEXTERNAL);
	      SdkOrderMmdoMapper orderMmdoMapper = dal.getMapper(SdkOrderMmdoMapper.class);
	      orderMmdoMapper.insertSelective(orderMmdo);
	      
	      SdkOrder order = new SdkOrder();
	      order.setAmount(money);
	      order.setChannel("leyogame");
	      order.setCpExt(cmd.getOut_trade_no());
	      order.setCpId(game.getCpId());
	      order.setCreateTime(time);
	      order.setGameId(game.getGameId());
	      order.setOrderDesc(game.getName());
	      // order.setOrderName(cmd.getOrder_name());
	      // 更改订单名称为游戏的名称
	      order.setOrderName(game.getName());
	      order.setOrderNo(orderNo);
	      order.setPayId(orderMmdo.getPayId());
	      order.setStatus(0);
	      order.setNotifyStatus(0);
	      order.setType(PAY_OPERATOR);
	      //order.setUid(gameSession.getUid());
	      SdkOrderMapper orderMapper = dal.getMapper(SdkOrderMapper.class);
	      orderMapper.insertSelective(order);
	      
	      dal.commit();
	      return sendResponse(orderNo, cmd.getOut_trade_no(), smsPort, smsContent);
	    } catch (Exception e) {
	      logger.error("the cmcczwjfsms pay , sms error!", e);
	      return sendError(orderNo, cmd.getOut_trade_no(), smsPort);
	    } finally {
	      dal.close();
	    }
	    
	  }
	  
	  private Result sendResponse(String orderNo, String outTradeNo, String smsPort, String smsContent) {
	    String xml = String.format(XML_SMS, orderNo, outTradeNo, smsPort, smsContent);
	    return new Result(new ResultXJContent(xml, xml));
	  }
	  
	  private Result sendError(String orderNo, String outTradeNo, String smsPort) {
	    String xml = String.format(XML_SMS, orderNo, outTradeNo, smsPort, ERROR_CONTENT);
	    return new Result(new ResultXJContent(xml, xml));
	  }

	  private String getCmccZwjfSms(Map<String,String> map){
		  String result="";
		  HttpClient httpclient = new DefaultHttpClient();
			try {
				String url = "http://dev.leyogame.cn/api/m/GetCmccZwjfSms?";
				StringBuffer query = new StringBuffer(url);
				query.append("game_id=").append(map.get("game_id"));
				query.append("&money=").append(map.get("money"));
				query.append("&imsi=").append(map.get("imsi"));
				query.append("&imei=").append(map.get("imei"));
				query.append("&out_trade_no=").append(map.get("out_trade_no"));
				query.append("&ext_data=").append(map.get("ext_data"));
				
				HttpGet httpGet = new HttpGet(query.toString());
				HttpResponse response = httpclient.execute(httpGet);
				HttpEntity entity = response.getEntity();
				String content = EntityUtils.toString(entity, "UTF-8");
				logger.debug("the cmcczwjfsms request pay sms ,content = " + content);
				result=content;
			}catch(Exception e){
				logger.error("the cmcczwjfsms request pay sms ,error = "+e);
			}finally {
				httpclient.getConnectionManager().shutdown();
			}
		  return result;
	  }
	  
	  public static void main(String[] args) {
		  GetCmccZwjfSms t=new GetCmccZwjfSms();
		  Map<String,String> map=new HashMap<String,String>();
		  map.put("game_id", "100");
		  map.put("money", "1");
		  map.put("imsi", "460000280791037");
		  map.put("imei", "863777028297140");
		  map.put("out_trade_no", "9999999999");
		  map.put("ext_data", "9999999999");
		  String result=t.getCmccZwjfSms(map);
		  System.out.println(result);
	}
}
