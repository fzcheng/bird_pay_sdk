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

import com.cheyooh.tools.log.Logger;

/**
 * @author Merlin
 * 
 */
public class CuccWoOffGenerator extends Thread {
  private static final Logger logger = new Logger(CuccWoOffGenerator.class);
  private String gameId = "200";
  private String servicePhone = "020-38811170";
  private String money;
  private int count;
  private String fileName;
  
  /**
   * 
   */
  public CuccWoOffGenerator(String money, int count, String fileName) {
    this.money = money;
    this.count = count;
    this.fileName = fileName;
  }
  
  /* (non-Javadoc)
   * @see java.lang.Thread#run()
   */
  @Override
  public void run() {
    PrintWriter pw = null;
    try {
      pw = new PrintWriter(fileName);
      for (int i = 0; i < count; i++) {
        logger.info("----------------- " + money + " 元 " + i + " 次 -----------------");
        CuccWoOffInfo sms = fetchPaySms();
        if ("error".equals(sms.getSmsContent())) {
          logger.error(money + " 元 " + i + " 次 = error");
          continue;
        }
//        pw.println(sms.getSmsPort());
        pw.println(sms.getSmsContent());
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (pw != null) {
        pw.close();
      }
    }
  }

  public CuccWoOffInfo fetchPaySms() throws Exception {
    // http://sdk.leyogame.cn/api/m/GetWoplusPm?game_id=200&money=10&service_phone=020-38811170
    
    HttpClient httpclient = new DefaultHttpClient();
    try {
      StringBuffer query = new StringBuffer("http://sdk.leyogame.cn/api/m/GetWoplusPm?");
      query.append("game_id=").append(gameId);
      query.append("&money=").append(money);
      query.append("&service_phone=").append(servicePhone);

      HttpGet httpGet = new HttpGet(query.toString());
      HttpResponse response = httpclient.execute(httpGet);
      HttpEntity entity = response.getEntity();

      // logger.info("cucc wo off response: " + response);

      String content = EntityUtils.toString(entity, "UTF-8");

      // logger.info("cucc wo off response content - content = " + content);

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
    //"/Users/Merlin/Works/leyogame/leyogame-sdk/trunk/sources/server/sdk_sz/cs-service-sdk/1yuan-2014-12-11.txt"
    CuccWoOffGenerator one = new CuccWoOffGenerator("1", 2, "D:/svn/leyogame-sdk/trunk/sources/server/sdk_sz/cs-service-sdk/sms/1yuan-2015-04-29.txt");
    //CuccWoOffGenerator ten = new CuccWoOffGenerator("10", 400, "D:/svn/leyogame-sdk/trunk/sources/server/sdk_sz/cs-service-sdk/sms/10yuan-2015-04-01-1.txt");
    //CuccWoOffGenerator four = new CuccWoOffGenerator("4", 12500 - 6983, "/Users/Merlin/Works/leyogame/leyogame-sdk/trunk/sources/server/sdk_sz/cs-service-sdk/4yuan-2015-01-05-1.txt");
    //CuccWoOffGenerator two = new CuccWoOffGenerator("2", 1, "D:/ljg/tem/2yuan-2015-01-05-1.txt");
    
    one.start();
    //three.start();
    //five.start();
    //ten.start();
    //four.start();
    //two.start();
  }

}
