/**
 * 
 */
package test.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.cheyooh.service.sdk.cfg.Cfg;
import com.cheyooh.service.sdk.idata.gameserver.WoplusSmsRes;
import com.cheyooh.service.sdk.tools.Hmacsha1;
import com.cheyooh.tools.log.Logger;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Merlin
 * 
 */
public class Hmacsha1Test {
  private static final String MAC_NAME = "HmacSHA1";
  private static final String ENCODING = "UTF-8";
  private static final Logger logger=new Logger(Hmacsha1Test.class);
  
  private static final String WO_PLUS_AUTHORIZATION = "platformID=\"%s\",password=\"%s\"";
  private static final ObjectMapper mapper = new ObjectMapper();
  static {
    //mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
    // or jackson 2.0
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.setSerializationInclusion(Include.NON_NULL);
    // jackson 1.9 and before
    //mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public static String sign(String encryptText, String encryptKey) throws Exception {
    byte[] data = encryptKey.getBytes(ENCODING);
    SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
    Mac mac = Mac.getInstance(MAC_NAME);
    mac.init(secretKey);

    byte[] text = encryptText.getBytes(ENCODING);
    byte[] signBytes = mac.doFinal(text);
    return Base64.encodeBase64String(signBytes);
  }

  private static String parseByte2HexStr(byte buf[]) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < buf.length; i++) {
      String hex = Integer.toHexString(buf[i] & 0xFF);
      if (hex.length() == 1) {
        hex = '0' + hex;
      }
      sb.append(hex.toLowerCase());
    }
    return sb.toString();
  }
  
  private static String signWoplus(WoplusSmsReq req) throws Exception {
    Map<String, Object> map = new TreeMap<String, Object>();
    BeanInfo bean = Introspector.getBeanInfo(req.getClass());
    PropertyDescriptor[] propertyDescriptors = bean.getPropertyDescriptors();
    for (PropertyDescriptor descriptor : propertyDescriptors) {
      String propertyName = descriptor.getName();
      if (!"class".equals(propertyName)) {
        Method readMethod = descriptor.getReadMethod();
        Object result = readMethod.invoke(req);
        if (result != null) {
          map.put(propertyName, result);
        } else {
          map.put(propertyName, null);
        }
      }
    }

    StringBuffer query = new StringBuffer();
    for (String key : map.keySet()) {
      if ("signType".equals(key) || "signature".equals(key) || map.get(key) == null) {
        continue;
      }
      query.append(key).append("=").append(map.get(key)).append("&");
    }
    if (query.length() > 0) {
      query.deleteCharAt(query.length() - 1);
    }
    logger.debug("the wo+ sign string = " + query.toString());
    //String encKey = Cfg.cfg.getString("sdk.woplus.platformId") + "&" + Cfg.cfg.getString("sdk.woplus.password");
    String encKey = "846edcfe-a7a3-464f-8115-11459022d33e" + "&" + "yk19840529";
    return Hmacsha1.sign(query.toString(), encKey);
  }

  public static void main(String[] args) throws Exception {
    HttpClient httpclient = new DefaultHttpClient();
    WoplusSmsRes res = new WoplusSmsRes();
    try {
      String smsUrl = Cfg.cfg.getString("sdk.woplus.smscontent.url");
      logger.debug("the wo+ fetch sms api = " + smsUrl);

      HttpPost post = new HttpPost(smsUrl);
      post.setHeader("Authorization",
          String.format(WO_PLUS_AUTHORIZATION, "846edcfe-a7a3-464f-8115-11459022d33e", "yk19840529"));
      post.setHeader("Accept", "application/json;charset=UTF-8");
      post.setHeader("Content-type", "application/json");

      WoplusSmsReq req = new WoplusSmsReq();
      req.setAppKey("game123");
      req.setAppName("斗地主");
      req.setCallbackData("123");
      req.setCallbackUrl("http://182.92.156.110/payReturn.php");
      req.setDescription("捕鱼达人金币购买");
      //req.setDeveloperId("");
      //req.setDeveloperName("");
      req.setIapId("");
      req.setImei("864668021321541");
      req.setImsi("460018103403215");
      req.setOutTradeNo("873232343243");
      req.setPrice(1);
      req.setQuantity(1);
      req.setSignType("HMAC-SHA1");
      req.setSubject("捕鱼达人金币");
      req.setTimeStamp("20140913050204");
      req.setTotalFee(1);
      String signature = signWoplus(req);
      req.setSignature(signature);
      
      String jsonBody = mapper.writeValueAsString(req);
      logger.debug("the wo+ request sms body = " + jsonBody);
      
      post.setEntity(new StringEntity(jsonBody, "UTF-8"));
      HttpResponse response = httpclient.execute(post);
      
       HttpEntity entity = response.getEntity();
       String content = EntityUtils.toString(entity, "UTF-8");
       logger.debug("the wo+ fetch sms response = " + content);
       res = mapper.readValue(content, WoplusSmsRes.class);
    } finally {
      httpclient.getConnectionManager().shutdown();
    }
    
    String text = "appKey=game123&appName=斗地主&callbackData=123&callbackUrl=http://182.92.156.110/payReturn.php&description=捕鱼达人金币购买&iapId=&imei=864668021321541&imsi=460018103403215&outTradeNo=873232343243&price=1&quantity=1&subject=捕鱼达人金币&timeStamp=20140913035945&totalFee=1";
    String key = "846edcfe-a7a3-464f-8115-11459022d33e&yk19840529";
    System.out.println(sign(text,key));
  }

  public static class WoplusSmsReq {
    private String outTradeNo;
    private String timeStamp;
    private String subject;
    private String description;
    private int price;
    private int quantity;
    private int totalFee;
    private String developerId;
    private String developerName;
    private String callbackUrl;
    private String callbackData;
    private String appKey;
    private String appName;
    private String iapId;
    private String imsi;
    private String imei;
    private String signType;
    private String signature;

    public WoplusSmsReq() {
    }
    
    public String getOutTradeNo() {
      return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
      this.outTradeNo = outTradeNo;
    }

    public String getTimeStamp() {
      return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
      this.timeStamp = timeStamp;
    }

    public String getSubject() {
      return subject;
    }

    public void setSubject(String subject) {
      this.subject = subject;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public int getPrice() {
      return price;
    }

    public void setPrice(int price) {
      this.price = price;
    }

    public int getQuantity() {
      return quantity;
    }

    public void setQuantity(int quantity) {
      this.quantity = quantity;
    }

    public int getTotalFee() {
      return totalFee;
    }

    public void setTotalFee(int totalFee) {
      this.totalFee = totalFee;
    }

    public String getDeveloperId() {
      return developerId;
    }

    public void setDeveloperId(String developerId) {
      this.developerId = developerId;
    }

    public String getDeveloperName() {
      return developerName;
    }

    public void setDeveloperName(String developerName) {
      this.developerName = developerName;
    }

    public String getCallbackUrl() {
      return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
      this.callbackUrl = callbackUrl;
    }

    public String getCallbackData() {
      return callbackData;
    }

    public void setCallbackData(String callbackData) {
      this.callbackData = callbackData;
    }

    public String getAppKey() {
      return appKey;
    }

    public void setAppKey(String appKey) {
      this.appKey = appKey;
    }

    public String getAppName() {
      return appName;
    }

    public void setAppName(String appName) {
      this.appName = appName;
    }

    public String getIapId() {
      return iapId;
    }

    public void setIapId(String iapId) {
      this.iapId = iapId;
    }

    public String getImsi() {
      return imsi;
    }

    public void setImsi(String imsi) {
      this.imsi = imsi;
    }

    public String getImei() {
      return imei;
    }

    public void setImei(String imei) {
      this.imei = imei;
    }

    public String getSignType() {
      return signType;
    }

    public void setSignType(String signType) {
      this.signType = signType;
    }

    public String getSignature() {
      return signature;
    }

    public void setSignature(String signature) {
      this.signature = signature;
    }

  }
}
