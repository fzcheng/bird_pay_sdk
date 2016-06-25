/**
 * 
 */
package test.util;

import java.io.ByteArrayInputStream;
import java.io.PrintWriter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.nuxeo.common.xmap.XMap;

import com.cheyooh.service.sdk.tools.GenerateTool;
import com.cheyooh.tools.log.Logger;

/**
 * @author Merlin
 *
 */
public class CuccWoOffGenerator10 {
  protected Logger logger=new Logger(this.getClass());
  
  public CuccWoOffInfo fetchPaySms() throws Exception {
    //http://sdk.leyogame.cn/api/m/GetWoplusPm?game_id=200&product_name=10元&money=10&service_phone=020-38811170
    HttpClient httpclient = new DefaultHttpClient();
    try {
      String gameId = "200";
      String money = "10";
      String productName = money + "元";
      String servicePhone = "020-38811170";
      StringBuffer query = new StringBuffer("http://sdk.leyogame.cn/api/m/GetWoplusPm?");
      query.append("game_id=").append(gameId);
      query.append("&money=").append(money);
      query.append("&product_name=").append(productName);
      query.append("&out_trade_no=").append(GenerateTool.createOrderNo());
      query.append("&service_phone=").append(servicePhone);

      HttpGet httpGet = new HttpGet(query.toString());
      HttpResponse response = httpclient.execute(httpGet);
      HttpEntity entity = response.getEntity();

      logger.info("cucc wo off response: " + response);

      String content = EntityUtils.toString(entity, "UTF-8");

      logger.info("cucc wo off response content - content = " + content);

      XMap xmap = new XMap();
      xmap.register(CuccWoOffInfo.class);
      ByteArrayInputStream in = new ByteArrayInputStream(content.getBytes("utf-8"));
      CuccWoOffInfo sms = (CuccWoOffInfo) xmap.load(in);

      return sms;
    } finally {
      httpclient.getConnectionManager().shutdown();
    }
  }
  
  public static void main(String[] args) {
    CuccWoOffGenerator10 gen = new CuccWoOffGenerator10();
    PrintWriter pw = null;
    try {
      pw = new PrintWriter("/Users/Merlin/Works/leyogame/leyogame-sdk/trunk/sources/server/sdk_sz/cs-service-sdk/10yuan.txt");
      for (int i = 0; i < 450; i++) {
        CuccWoOffInfo sms = gen.fetchPaySms();
        if ("error".equals(sms.getSmsContent())) {
          continue;
        }
        pw.println(sms.getSmsContent());
        Thread.sleep(5000);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (pw != null) {
        pw.close();
      }
    }
  }

}
