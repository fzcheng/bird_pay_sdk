/**
 * 
 */
package com.cheyooh.service.sdk.action.external;

import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
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
import com.cheyooh.service.sdk.cfg.Cfg;
import com.cheyooh.service.sdk.db.dao.SdkCmccReadIdoMapper;
import com.cheyooh.service.sdk.db.dao.SdkGameMapper;
import com.cheyooh.service.sdk.db.dao.SdkMmdoSettingMapper;
import com.cheyooh.service.sdk.db.dao.SdkOrderMapper;
import com.cheyooh.service.sdk.db.dao.SdkOrderMmdoMapper;
import com.cheyooh.service.sdk.db.entity.SdkCmccReadIdo;
import com.cheyooh.service.sdk.db.entity.SdkGame;
import com.cheyooh.service.sdk.db.entity.SdkMmdoSetting;
import com.cheyooh.service.sdk.db.entity.SdkMmdoSettingExample;
import com.cheyooh.service.sdk.db.entity.SdkOrder;
import com.cheyooh.service.sdk.db.entity.SdkOrderMmdo;
import com.cheyooh.service.sdk.idata.CmdExtCmccReadIdoPm;
import com.cheyooh.service.sdk.idata.gameserver.IdoSms;
import com.cheyooh.service.sdk.tools.GenerateTool;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Merlin
 * 
 */
public class CmccReadIdoPm extends AbstractNotifyService<CmdExtCmccReadIdoPm> {
  private static final Integer PAY_OPERATOR = 9;
  private static final Integer CHINA_MOBILE = 1;
  private static final String ERROR_CONTENT = "error";
  private static final String CMCC_READ_IDO = "cmccreadido";
  private final String XML_SMS = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<paycmd>\n" + "   <orderNo>%s</orderNo>\n"
      + "   <outTradeNo>%s</outTradeNo>\n" + "   <smsPort>%s</smsPort>\n" + "   <smsContent>%s</smsContent>\n" + "</paycmd>";
  private static final ObjectMapper mapper = new ObjectMapper();
  static {
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.setSerializationInclusion(Include.NON_NULL);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.cheyooh.service.framework.basic.Service#verify(com.cheyooh.service.framework.idata.Cmd)
   */
  @Override
  protected Result verify(CmdExtCmccReadIdoPm cmd) {
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.cheyooh.service.framework.basic.Service#execute(com.cheyooh.service.framework.idata.Cmd)
   */
  @Override
  protected Result execute(CmdExtCmccReadIdoPm cmd) {
    DAL dal = DALFactory.createDAL();
    String orderNo = GenerateTool.createOrderNo();
    try {
      if (StringUtils.isBlank(cmd.getGame_id()) || 
          !StringUtils.isNumeric(cmd.getGame_id()) || 
          StringUtils.isBlank(cmd.getRead_ido_code()) || 
          StringUtils.isBlank(cmd.getImsi()) || 
          StringUtils.isBlank(cmd.getImei())) {
        logger.error("get the cmcc read ido pay sms error, parameter cmd = " + cmd);
        return sendError(orderNo, cmd.getOut_trade_no());
      }

      Integer gameId = Integer.valueOf(cmd.getGame_id());
      SdkGameMapper gameMapper = dal.getMapper(SdkGameMapper.class);
      SdkGame game = gameMapper.selectByPrimaryKey(gameId);
      if (game == null) {
        logger.error("get the cmcc read ido pay sms error, gameId = " + gameId + ", can not found! parameter cmd = " + cmd);
        return sendError(orderNo, cmd.getOut_trade_no());
      }
      
      // 获取金额
      SdkMmdoSettingMapper mmdoSettingMapper = dal.getMapper(SdkMmdoSettingMapper.class);
      SdkMmdoSettingExample mmdoSettingExample = new SdkMmdoSettingExample();
      mmdoSettingExample.createCriteria().andContentEqualTo(cmd.getRead_ido_code());
      SdkMmdoSetting mmdoSetting = mmdoSettingMapper.selectOne(mmdoSettingExample);
      if (mmdoSetting == null) {
        logger.error("get the cmcc read ido pay sms error, can not found the pay code = " + cmd.getRead_ido_code());
        return sendError(orderNo, cmd.getOut_trade_no());
      }
      float money = mmdoSetting.getAmount();

      IdoSms sms = fetchReadIdoSms(cmd.getRead_ido_code());
      SdkCmccReadIdo sdkcmccreadido = new SdkCmccReadIdo();
      sdkcmccreadido.setOrderNo(orderNo);
      sdkcmccreadido.setOutTradeNo(sms.getOrderNo());
      sdkcmccreadido.setContent(sms.getContent());
      sdkcmccreadido.setResultCode(sms.getResultCode());
      sdkcmccreadido.setFeeCode(cmd.getRead_ido_code());
      sdkcmccreadido.setPrice(money);
      
      Date now = new Date();
      sdkcmccreadido.setCreateTime(now);
      sdkcmccreadido.setUpdatedTime(now);
      SdkCmccReadIdoMapper sdkcmccreadidomapper = dal.getMapper(SdkCmccReadIdoMapper.class);
      sdkcmccreadidomapper.insertSelective(sdkcmccreadido);
      
      if (!"1".equals(sms.getResultCode())) {
        dal.commit();
        return sendError(orderNo, cmd.getOut_trade_no());
      }
      
      String smsPort = Cfg.cfg.getString("sdk.ido.spnum");
      
      SdkOrderMmdo orderMmdo = new SdkOrderMmdo();
      orderMmdo.setGameId(gameId);
      orderMmdo.setOperationType(CHINA_MOBILE);
      orderMmdo.setReqOrderAmount(money);
      orderMmdo.setReqSendContent(sms.getContent());
      orderMmdo.setReqSendNumber(smsPort);
      orderMmdo.setReqTime(now);
      orderMmdo.setReqImsi(cmd.getImsi());
      orderMmdo.setImei(cmd.getImei());
      orderMmdo.setIpAddr(cmd.getRequestHost());
      orderMmdo.setPayChannelCode(CMCC_READ_IDO);
      SdkOrderMmdoMapper orderMmdoMapper = dal.getMapper(SdkOrderMmdoMapper.class);
      orderMmdoMapper.insertSelective(orderMmdo);
      
      SdkOrder order = new SdkOrder();
      order.setAmount(money);
      order.setChannel("leyogame");
      order.setCpExt(cmd.getExt_data());
      order.setCpId(game.getCpId());
      order.setCreateTime(now);
      order.setGameId(game.getGameId());
      //order.setOrderDesc(cmd.getProduct_name());
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
      
      return sendResponse(orderNo, cmd.getOut_trade_no(), smsPort, sms.getContent());
    } catch (Exception e) {
      logger.error("get the cmcc read ido pay sms error, parameter cmd = " + cmd, e);
      return sendError(orderNo, cmd.getOut_trade_no());
    } finally {
      dal.close();
    }
  }

  private Result sendResponse(String orderNo, String outTradeNo, String smsPort, String smsContent) {
    String xml = String.format(XML_SMS, orderNo, outTradeNo, smsPort, smsContent);
    return new Result(new ResultXJContent(xml, xml));
  }
  
  private Result sendError(String orderNo, String outTradeNo) {
    String xml = String.format(XML_SMS, StringUtils.defaultString(orderNo), StringUtils.defaultString(outTradeNo), "", ERROR_CONTENT);
    return new Result(new ResultXJContent(xml, xml));
  }

  private IdoSms fetchReadIdoSms(String idoCode) throws Exception {
    HttpClient httpclient = new DefaultHttpClient();
    IdoSms idosms = new IdoSms();
    try {
      String idoSmsUrl = Cfg.cfg.getString("sdk.ido.url");
      StringBuffer query = new StringBuffer(idoSmsUrl);
      query.append("?");
      String idoChannel = Cfg.cfg.getString("sdk.ido.channel");
      query.append("channel=").append(idoChannel);
      query.append("&feeCode=").append(idoCode);
      
      logger.debug("get the cmcc read ido pay sms, fetch from read ido url = " + query.toString());
      
      HttpGet httpget = new HttpGet(query.toString());
      
      String idoSecretKey = Cfg.cfg.getString("sdk.ido.secretkey");
      String plainText = idoChannel + idoSecretKey;
      String sign = DigestUtils.md5Hex(plainText);
      String signBase64 = Base64.encodeBase64String(sign.getBytes());
      
      logger.debug("get the cmcc read ido pay sms, read ido plainText = " + plainText + ", sign base64 = " + signBase64);
      
      httpget.addHeader("clientHash", signBase64);
      
      HttpResponse response = httpclient.execute(httpget);
      HttpEntity entity = response.getEntity();
      String content = EntityUtils.toString(entity, "UTF-8");
      
      logger.debug("get the cmcc read ido pay sms, read ido response content = " + content);
      idosms = mapper.readValue(content, IdoSms.class);
      return idosms;
    } catch (Exception e) {
      logger.error("get the cmcc read ido pay sms error, ", e);
      return null;
    } finally {
      httpclient.getConnectionManager().shutdown();
    }
  }
}
