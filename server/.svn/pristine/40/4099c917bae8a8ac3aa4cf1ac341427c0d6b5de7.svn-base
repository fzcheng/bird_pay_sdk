/**
 * 
 */
package com.cheyooh.service.sdk.action.external;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.ResultXJContent;
import com.cheyooh.service.sdk.action.notify.AbstractNotifyService;
import com.cheyooh.service.sdk.cfg.Cfg;
import com.cheyooh.service.sdk.db.dao.SdkGameMapper;
import com.cheyooh.service.sdk.db.dao.SdkOrderMapper;
import com.cheyooh.service.sdk.db.dao.SdkOrderMmdoMapper;
import com.cheyooh.service.sdk.db.dao.SdkWoplusOfflinePaySmsMapper;
import com.cheyooh.service.sdk.db.entity.SdkGame;
import com.cheyooh.service.sdk.db.entity.SdkOrder;
import com.cheyooh.service.sdk.db.entity.SdkOrderMmdo;
import com.cheyooh.service.sdk.db.entity.SdkWoplusOfflinePaySms;
import com.cheyooh.service.sdk.db.entity.SdkWoplusOfflinePaySmsExample;
import com.cheyooh.service.sdk.idata.CmdGetWoplusPm;
import com.cheyooh.service.sdk.tools.GenerateTool;

/**
 * @author Merlin
 *
 */
public class GetWoplusPm extends AbstractNotifyService<CmdGetWoplusPm> {
  private static final Integer PAY_OPERATOR = 9;
  private static final Integer CHINA_UNICOM = 2;
  private static final String ERROR_CONTENT = "error";
  private static final String WOPLUS_OFFLINE = "woplusoffline";
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
  protected Result verify(CmdGetWoplusPm cmd) {
    return null;
  }

  /* (non-Javadoc)
   * @see com.cheyooh.service.framework.basic.Service#execute(com.cheyooh.service.framework.idata.Cmd)
   */
  @Override
  protected Result execute(CmdGetWoplusPm cmd) {
    DAL dal = DALFactory.createDAL();
    String orderNo = GenerateTool.createOrderNo();
    String smsPort = Cfg.cfg.getString("sdk.external.unicom.woplusoffline.sms.port");
    try {
      if (StringUtils.isBlank(cmd.getGame_id()) ||
          !StringUtils.isNumeric(cmd.getGame_id()) ||
          StringUtils.isBlank(cmd.getMoney()) ||
          //StringUtils.isBlank(cmd.getOut_trade_no()) ||
          //StringUtils.isBlank(cmd.getProduct_name()) ||
          StringUtils.isBlank(cmd.getService_phone())) {
        logger.error("get the unicom wo+ offline pay sms parameter error cmd = " + cmd.toString());
        return sendError(orderNo, cmd.getOut_trade_no(), smsPort);
      }
      
      if (StringUtils.isBlank(cmd.getProduct_name()) || "null".equals(cmd.getProduct_name())) {
        cmd.setProduct_name(cmd.getMoney() + "元");
      }
      
      float money = Float.parseFloat(cmd.getMoney());
      
      Integer gameId = Integer.valueOf(cmd.getGame_id());
      SdkGameMapper gameMapper = dal.getMapper(SdkGameMapper.class);
      SdkGame game = gameMapper.selectByPrimaryKey(gameId);
      if (game == null) {
        logger.error("gameId = " + gameId + ", can not found!");
        return sendError(orderNo, cmd.getOut_trade_no(), smsPort);
      }
      
      Date time = new Date();
      SdkWoplusOfflinePaySms paySms = new SdkWoplusOfflinePaySms();
      paySms.setCreatedTime(time);
      paySms.setGameId(gameId);
      paySms.setMoney(money);
      //paySms.setNotifyRawData(notifyRawData);
      paySms.setOrderNo(orderNo);
      paySms.setOutTradeNo(cmd.getOut_trade_no());
      paySms.setProductName(cmd.getProduct_name());
      paySms.setServicePhone(cmd.getService_phone());
      //paySms.setSmsContent(smsContent);
      paySms.setSmsPort(smsPort);
      paySms.setImsi(cmd.getImsi());
      paySms.setImei(cmd.getImei());
      paySms.setStatus((byte) 0);
      paySms.setUpdatedTime(time);
      
      SdkWoplusOfflinePaySmsMapper paySmsMapper = dal.getMapper(SdkWoplusOfflinePaySmsMapper.class);
      if (StringUtils.isNotBlank(cmd.getImsi())) {
        int seconds = Cfg.cfg.getInt("sdk.game.request.timeinterval");
        SdkWoplusOfflinePaySmsExample example = new SdkWoplusOfflinePaySmsExample();
        example.createCriteria().andImsiEqualTo(cmd.getImsi()).andCreatedTimeGreaterThan(beforeDate(seconds));
        int count = paySmsMapper.countByExample(example);
        if (count > 0) {
          errorSms(paySms, Math.abs(seconds) + "秒内重复支付", null);
          paySmsMapper.insertSelective(paySms);
          dal.commit();
          return sendError(orderNo, cmd.getOut_trade_no(), smsPort);
        }
      }
      
      paySmsMapper.insertSelective(paySms);
      
      String smsContent = getSmsContent(game.getName(), paySms);
      
      SdkOrderMmdo orderMmdo = new SdkOrderMmdo();
      orderMmdo.setGameId(gameId);
      orderMmdo.setOperationType(CHINA_UNICOM);
      orderMmdo.setReqOrderAmount(money);
      orderMmdo.setReqSendContent(smsContent);
      orderMmdo.setReqSendNumber(smsPort);
      orderMmdo.setReqTime(time);
      orderMmdo.setReqImsi(cmd.getImsi());
      orderMmdo.setImei(cmd.getImei());
      orderMmdo.setIpAddr(cmd.getRequestHost());
      orderMmdo.setPayChannelCode(WOPLUS_OFFLINE);
      SdkOrderMmdoMapper orderMmdoMapper = dal.getMapper(SdkOrderMmdoMapper.class);
      orderMmdoMapper.insertSelective(orderMmdo);
      
      SdkOrder order = new SdkOrder();
      order.setAmount(money);
      order.setChannel("leyogame");
      order.setCpExt(cmd.getExt_data());
      order.setCpId(game.getCpId());
      order.setCreateTime(time);
      order.setGameId(game.getGameId());
      order.setOrderDesc(cmd.getProduct_name());
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
      
      paySmsMapper.updateByPrimaryKeySelective(paySms);
      
      dal.commit();
      return sendResponse(orderNo, cmd.getOut_trade_no(), smsPort, smsContent);
    } catch (Exception e) {
      logger.error("get the unicom wo+ offline pay sms error!", e);
      return sendError(orderNo, cmd.getOut_trade_no(), smsPort);
    } finally {
      dal.close();
    }
  }
  
  private String getSmsContent(String appName, final SdkWoplusOfflinePaySms paySms) {
    String content1 = "21";
    
    String content2 = " 1";
    
    String content3 = Cfg.cfg.getString("sdk.external.unicom.woplusoffline.channelid");//channelId
    if (content3.length() < 36) {
      content3 = StringUtils.leftPad(content3, 36);
    } else if (content3.length() > 36) {
      return errorSms(paySms, "channelId超过36个字符", null);
    }
    
    String content4;
    try {
      content4 = Hex.encodeHexString(appName.getBytes("GBK")).toUpperCase();
      if (content4.length() < 24) {
        content4 = StringUtils.leftPad(content4, 24);
      } else if (content4.length() > 24) {
        return errorSms(paySms, "应用名称 = " + appName + ",字节数超过24位", null);
      }
    } catch (UnsupportedEncodingException e) {
      return errorSms(paySms, "应用名称 = " + appName + ",转换GBK字节数组错误", e);
    }
    
    String content5 = Cfg.cfg.getString("sdk.external.unicom.woplusoffline.apptype");
    
    String content6;
    try {
      content6 = Hex.encodeHexString(paySms.getProductName().getBytes("GBK")).toUpperCase();
      if (content6.length() < 24) {
        content6 = StringUtils.leftPad(content6, 24);
      } else if (content6.length() > 24) {
        return errorSms(paySms, "商品名称 = " + paySms.getProductName() + ",字节数超过24个", null);
      }
    } catch (UnsupportedEncodingException e) {
      return errorSms(paySms, "商品名称 = " + paySms.getProductName() + ",转换GBK字节数组错误", e);
    }
    
    int amount = (int) (paySms.getMoney() * 100);
    String content8 = Integer.toHexString(amount);
    if (content8.length() < 4) {
      content8 = StringUtils.leftPad(content8, 4);
    } else if (content8.length() > 4) {
      return errorSms(paySms, "商品价格 = " + amount + "分,不能超过9999分", null);
    }
    
    String content7 = Cfg.cfg.getString("sdk.external.unicom.woplusoffline.producttype");
    
    String content9 = Cfg.cfg.getString("sdk.external.unicom.woplusoffline.paytype");
    
    String content10 = String.valueOf(paySms.getId());
    if (content10.length() < 11) {
      content10 = StringUtils.leftPad(content10, 11);
    } else if (content10.length() > 11) {
      return errorSms(paySms, "支付ID = " + paySms.getId() + ",长度不能超过11位", null);
    }
    
    String srcStr = content1.trim() + content2.trim() + content3.trim() + content4.trim() +
        content5.trim() + content6.trim() + content8.trim() + content7.trim() + content9.trim() +
        content10.trim() + Cfg.cfg.getString("sdk.external.unicom.woplusoffline.signkey1") +
        Cfg.cfg.getString("sdk.external.unicom.woplusoffline.signkey2");
    String sign = DigestUtils.md5Hex(srcStr);
    String content = content1 + content2 + content3 + content4 + content5 +
        content6 + content8 + content7 + content9 + content10 + sign;
    paySms.setSmsContent(content);
    return content;
  }

  private Result sendResponse(String orderNo, String outTradeNo, String smsPort, String smsContent) {
    String xml = String.format(XML_SMS, orderNo, outTradeNo, smsPort, smsContent);
    return new Result(new ResultXJContent(xml, xml));
  }
  
  private Result sendError(String orderNo, String outTradeNo, String smsPort) {
    String xml = String.format(XML_SMS, orderNo, outTradeNo, smsPort, ERROR_CONTENT);
    return new Result(new ResultXJContent(xml, xml));
  }
  
  private String errorSms(final SdkWoplusOfflinePaySms paySms, String err, Exception e) {
    paySms.setStatus((byte) -1);
    paySms.setSmsContent(err);
    if (e != null) {
      logger.error(err);
    } else {
      logger.error(err, e);
    }
    
    return ERROR_CONTENT;
  }
  
  private Date beforeDate(int seconds) {
    Calendar c = Calendar.getInstance();
    c.add(Calendar.SECOND, seconds);
    return c.getTime();
  }
}
