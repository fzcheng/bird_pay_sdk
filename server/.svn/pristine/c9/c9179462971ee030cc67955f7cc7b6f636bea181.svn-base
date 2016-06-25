/**
 * 
 */
package com.mo9.clientdemo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.mokredit.payment.Md5Encrypt;
import com.mokredit.payment.MktPayment;
import com.mokredit.payment.MktPluginSetting;

/**
 * 
 * <p>支付流程发起界面</p>
 * 
 * <p>该Activity主要用于模拟商户集成mo9支付的，支付支付发起页面，例如待购买的道具列表页面，虚拟游戏币充值页面等.</p>
 * 
 ********************************************************  
 *  Date			Author		Changes
 *  2012-9-19		Eric Cao	创建
 ********************************************************
 */
public class MainActivity extends Activity
{
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main_act);
		//初始化事件监听
		findViewById(R.id.btn_integrate_browser).setOnClickListener(clickListener);
		findViewById(R.id.btn_integrate_webview).setOnClickListener(clickListener);
		findViewById(R.id.btn_integrate_sdk).setOnClickListener(clickListener);
	}
	
	/**
	 * 使用mo9提供的SDK(jar文件)发起支付流程.
	 *     mo9.com建议你使用基于SDK的支付接入模式，我们为你提供的Jar文件中已经完成的为你解决了android的
	 * 多版本兼容问题(例如个别版本不能正确识别mo9.com的HTTPS证书),统一加载进度条等功能。并且通过本地渲染，
	 * 以最小的流量，最快的速度，为用户呈现更精美的支付页面。
	 *  Notice:
	 *     1.使用SDK接入前，请在你的android工程中导入mokredit.jar，导入流程参考《android手机插件技术文档.pdf》
	 *     2.提前在AndroidManifest.xml中完成如下Activity和uses-permission配置.请参考《android手机插件技术文档.pdf》
	 *     <activity 	android:name="com.mokredit.payment.MktPayment"
	 *            	android:configChanges="keyboardHidden|orientation"
	 *				android:screenOrientation="portrait"
	 *				android:windowSoftInputMode="adjustResize"/>
     *		<!-- 联网权限 -->
	 *	    <uses-permission android:name="android.permission.INTERNET" />
	 *	    <!-- 读取网络状态 -->
	 *	    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
	 */
	private void startSDKPayFlow()
	{
		/**拼接支付请求URI*/
		String uri = loadPaymentURLFromYourServer();
		/**创建一个mokreidt插件配置项和Intent，并启动SDK的支付对话框.*/
		MktPluginSetting pluginSetting= new MktPluginSetting (uri);
		Intent intent=new Intent();
		intent.setClass(this, MktPayment.class);
		intent.putExtra("mokredit_android", pluginSetting);
		/***
		 * 开始支付流程
		 */
//		startActivity(intent);
		/**
		 * 开启同步支付流程，你可再onActivityResult(int requestCode, int resultCode, Intent data)处理同步支付结果.
		 */
		startActivityForResult(intent, 100);
	}
	
	private void startWebviewPayFlow()
	{
		String uri = loadPaymentURLFromYourServer();
		
		/**
		 * 加载支付流程的WebView：
		 * 当前示例代码采用当前Activity中的Webview加载支付流程,你可以根据你的实际情况选择界面的不同Webview执行加载.
		 */
		WebView view = (WebView)findViewById(R.id.payment_webview);
		view.setVisibility(View.VISIBLE);
		view.setVerticalScrollBarEnabled(false);
		view.getSettings().setSupportZoom(false);
		view.getSettings().setSaveFormData(false);
		view.getSettings().setSavePassword(false);
		view.getSettings().setJavaScriptEnabled(true);
		findViewById(R.id.switch_panel).setVisibility(View.GONE);//隐藏各实例代码的触发按钮，这行不用copy到你的android工程中.
		view.setWebViewClient(new WebViewClient() { 
			@SuppressLint("NewApi")
			@Override
			public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) 
			{/**
			   *忽略SSL证书错误，在极个别android版本中会出现mo9.com.cn/mokredit.com的HTTS证书不可信的情况，
			   *该操作主要用于忽略SSL证书错误的提醒.
			   */       
				handler.proceed();       
			}
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {	
				/**重载URL加载模式，支付流程的各URL都在当前WebView中加载，而非跳转到系统默认浏览器.*/
				return true;
			}
		});
		view.loadUrl(uri);
	}
	
	/**
	 * 呼出系统默认浏览器完成支付流程.
	 *     如果使用该方法发起支付流程，在支付过程中您的android应用将处于后台运行状态，发起支付流程的Activity将
	 * 处于pause状态，你可以适当的覆盖Activity的onPause,onResume方法，完成发起支付时暂停游戏，完成支付从新进入
	 * 该页面时刷新游戏等业务。
	 */
	private void startBrowserPayFlow()
	{
		/**拼装支付请求的URI*/
		String uri = loadPaymentURLFromYourServer();
		/**呼出系统默认浏览器，打开支付请求URI.**/
		Intent intent = new Intent();    
		intent.setAction("android.intent.action.VIEW");
		intent.setData(Uri.parse(uri));  
		startActivity(intent);
	}
	
	/**
	 * 从你的服务器端加载支付URL.
	 *    为了避免将mo9.com分配给你的签名key存储在android而导致的密钥泄露风险，我们强烈建议将key存储在你的服务器。
	 * 服务端完成对支付URL签名后，再回传给Android客户端加载解析.
	 * Notice:
	 *    该实例方法为逐行为你注解每个接口参数的意义，及取值范围。接口的详细描述可参阅文档《mo9手机开发集成文档.pdf》
	 * @return 签名后的支付请求URL地址
	 */
	private String loadPaymentURLFromYourServer()
	{	
		/**
		 * 支付请求地址：
		 *  我们一共提国际服务器,国际服务器沙箱环境，中国服务器，中国服务器沙箱环境四套环境，具体ＵＲＬ地址如下：
		 *  https://www.mo9.com/gateway/mobile.shtml?m=mobile
		 *  https://sandbox.mo9.com/gateway/mobile.shtml?m=mobile
		 *  https://www.mo9.com.cn/gateway/mobile.shtml?m=mobile
		 *  https://sandbox.mo9.com.cn/gateway/mobile.shtml?m=mobile
		 */
		String pay_uri="https://sandbox.mo9.com.cn/gateway/mobile.shtml?m=mobile";
	
		Map<String,String> payParams = new HashMap<String,String>();
		/**商户接入账号，你可以使用该账号登陆mo9后台查看支付订单*/
		payParams.put("pay_to_email", "ruanln@leyogame.cn");
		/**商户接入点应用ID,你可登录mo9.com/merchant,mo9.com.cn/merchant,sandbox.mo9.com/merchant,sandbox.mo9.com.cn/merchant创建应用，系统将自定为你分配app_id*/
		payParams.put("app_id", "leyo_game");
		/**支付接口版本号，直接填写2即可*/
		payParams.put("version", "2.1");	
		/***支付回调回调地址.*/
		payParams.put("notify_url", "http://localhost/serverDemo/notifyHandler.jsp");
		/**
		 * 请求凭证，要求每一次支付请求的invoice参数均不相同，mo9系统会放弃处理已经存在的invoice，
		 * 以避免重复处理支付请求。我们建议你使用你系统的交易订单号作为invoice参数值.
		 */
		payParams.put("invoice", System.currentTimeMillis()+"");
		/**
		 * 商家系统中唯一的用户id
		 * mo9建议使用商户系统中的用户表ID列，或者其他具有唯一性约束的数据列(例如登陆名，手机号码等)填充该字段。
		 * 如果你需要在mo9系统中接入多款游戏，或同一款游戏中因为分服(中国服务器,美国服务器等)，分区(游戏一区,二区等)
		 * 业务导致存在多个不同数据库源。请结合数据库源生成唯一的payer_id,以避免来自不同服务器的payerId发生重复。
		 * 你也可以使用手机终端的IMEI,imsi等属性作为你唯一的PayerID标识.
		 */
		payParams.put("payer_id", System.currentTimeMillis()+"");
		/**
		 * 用户所在的所有地区的国家缩写，例如中国为“CN”,美国“US”.
		 * 如果你的系统中已经保存了用户的所在地信息则可以直接使用，如果你的系统中没有用户所在地信息，则建议你按如下
		 * 优先级设置用户归属地信息：
		 * 1.使用手机终端SIM卡所在的归属地.TelephonyManager.getSimCountryIso()
		 * 2.使用手机终端所在的IP地址归属地.
		 * 3.使用手机操作系统的Local信息.Context.getResources().getConfiguration().locale.getCountry()
		 * 4.根据你的游戏运营情况，自定义归属地信息。例如你的游戏百分之九十都是中国用户，则可以直接设置为“CN”。
		 */
		payParams.put("lc", "CN");
		/***交易金额*/
		payParams.put("amount", "0.01");
		/**交易的货币类型*/
		payParams.put("currency", "CNY");
		/***交易的商品名称*/
		payParams.put("item_name", "10 Yuan");
		
		/**
		 * 商户接入私钥
		 * 商户登陆https://sandbox.mokredit.com/merchant,既可以在商户Setting菜单下看到你的私钥信息。
		 * 请勿为了开发方便，将该key放置在客户端，以免key泄露导致损失.
		 */
		//String privateKey="2364020799af4082b62a015eb85a7e40";
		String privateKey="3032ecd6d69841dbafff1fe6fee26958";
		/**
		 * com.mokredit.payment.Md5Encrypt位于我们提供的mokredit.jar文件中.
		 * 如果你不希望自己维护给予MD5的签名算法，可以导入mokredit.jar调用改方法，完成签名操作.
		 */
		String sign  = Md5Encrypt.sign(payParams, privateKey);
		payParams.put("sign", sign);
		
		/***
		 * 拼接请求参数,请在提交支付请求时使用UTF-8编码，以免item_name等包含汉字或特殊字符的属性不能正确识别.
		 */
		StringBuffer queryStr = new StringBuffer();
		Set<String> keys = payParams.keySet();
		for(String key:keys)
		{
			//将请求参数进行URL编码
			String value=null;
			try
			{
				value = URLEncoder.encode((String)payParams.get(key), "UTF-8");
			} catch (UnsupportedEncodingException e)
			{
				throw new IllegalArgumentException("封装支付请求URL失败.	",e);
			}
			queryStr.append("&"+key+"="+value);
		}
		
		return pay_uri+"&"+queryStr;
	}
	
	/**
	 * 点击监听器，监听界面点击，用户发起支付流程.
	 */
	private OnClickListener clickListener = new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			
			int clickSource = v.getId();
			switch(clickSource)
			{
				case R.id.btn_integrate_browser:
					startBrowserPayFlow();
					break;
				case R.id.btn_integrate_webview:
					startWebviewPayFlow();
					break;
				case R.id.btn_integrate_sdk:
					startSDKPayFlow();
					break;
			}
		}
	};
	
	/**
	 * 同步处理支付结果
	 * 可选操作.
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Toast.makeText(this, "RESULT reqCode:"+requestCode+",resCode:"+resultCode, Toast.LENGTH_LONG).show();
		if(requestCode==100 && resultCode==10)
		{
			Toast.makeText(this, "支付成，请下发用户购买商品.", Toast.LENGTH_LONG).show();
		}
	};
}
