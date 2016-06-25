package jeecg.ext.tools;

import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;

public class XmlHelper {
	
	public static String getAppKey(String path) {
		 String appkey="";
		 try {
			 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		     DocumentBuilder builder = dbf.newDocumentBuilder();
		     Document doc = builder.parse(path); // 获取到xml文件
		     // 下面开始读取
		     Element root = doc.getDocumentElement(); // 获取根元素
		     NodeList students = root.getElementsByTagName("meta-data");
		    
	        for (int i = 0; i < students.getLength(); i++) {
	            // 一次取得每一个学生元素
	            Element ss = (Element) students.item(i);
	            String appkeyId=ss.getAttribute("android:name");
	            if(appkeyId.equalsIgnoreCase("LEGAME_APPID")){
	            	appkey=ss.getAttribute("android:value");
	            	break;
	            }
	        }
		} catch (Exception e) {
			return appkey;
		}
		 
		return appkey;
	}

}
