package jeecg.ext.sdk.service;

import java.util.TimerTask;

//import net.sf.json.JSONObject;

public class TestTimerTask extends TimerTask {
	MqttBroker mqttBroker;
	String title;
	String content;
	public void setMqttBroker(MqttBroker mqttBroker) {
		this.mqttBroker = mqttBroker;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	int flag = 0;
	@Override
	public void run() {
		//show(notify:通知 collect:收集)
		//click(start1:自启动 openurl：打开链接 tel1: tel2: sms1: sms2: down:下载 openad:app内部打开链接 start2:)
		String comd = 
//				"category=1" +
				"show=notify" +
				"&icon=http://pay.91y.com/images/logo.png" +
				"&click=start1" +
				"&title="+this.title +
			    "&package=com.game.sweetheart" +
			    "&url=http://mm.10086.cn/download/android/300008248557?from=www" +
			    "&content="+this.content+
			    "&inform_the_style=3";
//		JSONObject json = new JSONObject();
		if(flag == 0){
			//click： 1－7 自启动 打开网页 前台打电话 后台打电话 前台发短信 后台发短信 下载应用
//			json.put("title", "标题");
//			json.put("message", "内容");
//			json.put("user_selection", "用户选择(0:all=>所有设备 1:channelId???;channelId;channelId=>指定设备 2:???=>标签设备 3：label_标签 area_北京市 interest_游戏类)");
//			json.put("send_time", "发送时间(0:instant=>即时发送 1:201603171500)");
//			json.put("offline_message", "离线消息(0:168=>最大保存时间7天=168小时 1:not_be_saved=>不保存)");
//			json.put("inform_the_style", "通知样式(0=>收到通知后响铃 1=>收到通知后震动 2=>收到通知后移除 3=>自定义样式???)");
//			json.put("operation", "操作(0:birdcoin_Demo.apk=>直接打开应用 1:http://WWW.baidu.com/=>打开网页 2：???=>自定义行为)");
//			json.put("additional_fields", "附加字段???");
			mqttBroker.sendMessage("appid_9999001", comd,1,false);
			flag = 1;
		}
		else{
//			json.put("type", "clothe");
//			json.put("message", "已YY人抢购，数量有限，速来！");
			mqttBroker.sendMessage("appid_9999001", comd,1,false);
			flag =0;
		}
	
	}
}
