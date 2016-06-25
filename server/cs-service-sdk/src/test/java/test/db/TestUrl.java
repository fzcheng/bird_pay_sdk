package test.db;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUrl {
  private static final ObjectMapper mapper = new ObjectMapper();
  static {
    // mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
    // or jackson 2.0
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.setSerializationInclusion(Include.NON_NULL);
    // jackson 1.9 and before
    // mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public static void main(String[] args) throws Exception {
    HttpClient httpclient = new DefaultHttpClient();
    try {
      StringBuffer query = new StringBuffer("http://ota.pay.mobile.sina.cn/platform/smspayRequest.php?sid=403279e82efca93c1a09c89b098e9814");

      // HttpGet httpGet = new HttpGet(query.toString());
      // HttpPost httpost = new HttpPost(query.toString());
      // httpost.setHeader("Content-type", "application/json;charset=UTF-8");

      // HttpResponse response = httpclient.execute(httpost);
      // HttpEntity entity = response.getEntity();
      // String content = EntityUtils.toString(entity, "UTF-8");
      String s = "{\"application\":\"null\",\"respCode\":\"0004\",\"resDesc\":\"\u53c2\u6570\u4e0a\u9001\u6709\u8bef\"}";
      String s2 = "{\"appName\":\"\u5185\u90e8\u6d4b\u8bd5\u6e38\u620f\"}";

      Map<String, String> m = mapper.readValue(s, new TypeReference<TreeMap<String, String>>() {
      });
      Map<String, String> m2 = mapper.readValue(s2, new TypeReference<TreeMap<String, String>>() {
      });
      System.out.println(StringUtils.isBlank(m.get("application")));
      System.out.println(m);

      String a = "10.11%";
      double b = 3/11d;
      System.out.println("b = " + b);
      NumberFormat nFormat = NumberFormat.getPercentInstance();
      System.out.println("b format is :" + nFormat.format(b));
      try {
        System.out.println("a format is :" + nFormat.parse(a));
      } catch (ParseException e) {
        e.printStackTrace();
      }
    } finally {
      httpclient.getConnectionManager().shutdown();
    }
  }

}
