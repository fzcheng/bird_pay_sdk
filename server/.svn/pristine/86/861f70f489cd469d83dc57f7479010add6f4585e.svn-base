package jeecg.ext.sdk.service;

import javapns.back.PushNotificationManager;
import javapns.back.SSLConnectionHelper;
import javapns.data.Device;
import javapns.data.PayLoad;

/**
 * 
 * @author fzcheng
 *
 */
public class DeviceToken {
	public static void main(String[] args) throws Exception 
	{
	        try
	        {
	            //从客户端获取的deviceToken，在此为了测试简单，写固定的一个测试设备标识。
	           String deviceToken = "83fb98bb baf81c5d d7e70f64 997339d5 8faf6284 8f2a6d75 386ece15 b3e4922a";
	            System.out.println("Push Start deviceToken:" + deviceToken);
	            //定义消息模式
	            PayLoad payLoad = new PayLoad();
	            payLoad.addAlert(args[1]);
	            payLoad.addBadge(1);//消息推送标记数，小红圈中显示的数字。
	            payLoad.addSound("default");
	            //注册deviceToken
	            PushNotificationManager pushManager = PushNotificationManager.getInstance();
	            pushManager.addDevice("iPhone", deviceToken);
	            //连接APNS
	            String host = "gateway.sandbox.push.apple.com";
	            //String host = "gateway.push.apple.com";
	            int port = 2195;
	            String certificatePath = "c:/sendbird.p12";//前面生成的用于JAVA后台连接APNS服务的*.p12文件位置
	            String certificatePassword = "abc123";//p12文件密码。
	            pushManager.initializeConnection(host, port, certificatePath, certificatePassword, SSLConnectionHelper.KEYSTORE_TYPE_PKCS12);
	            //发送推送
	            Device client = pushManager.getDevice("iPhone");
	            System.out.println("推送消息: " + client.getToken()+"\n"+payLoad.toString() +" ");
	            pushManager.sendNotification(client, payLoad);
	            //停止连接APNS
	            pushManager.stopConnection();
	            //删除deviceToken
	            pushManager.removeDevice("iPhone");
	            System.out.println("Push End");
	        }
	        catch (Exception ex)
	        {
	            ex.printStackTrace();
	        }
	}
}
