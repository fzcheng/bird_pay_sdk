package com.legame.paysdk.network.resultdata;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.legame.paysdk.models.Block;
import com.legame.paysdk.models.Command;
import com.legame.paysdk.models.Commands;
import com.legame.paysdk.models.Order;
import com.legame.paysdk.models.OrderInfo;
import com.legame.paysdk.models.SdkpayData;
import com.legame.paysdk.models.TipsInfo;
import com.legame.paysdk.utils.LogUtil;

/** 
 * 类说明：   
 * @author  huangliang/Xiaodm/KaiGuang
 * @date    2014-2-25/2014-10-10/2015-4-15
 * @version 1.0
 */
public class FastPaymentResultData extends BaseResultData {
	private static final String TAG = FastPaymentResultData.class.getSimpleName();
	private String mExpectType;
	
	private String orderlistTime;//多点计费多条短信之间的发送间隔
	private OrderInfo mOrderInfo;
	private Map<String, String> mapMM_WO_LoveGame_Gamebase;//四网数据
	private Map<String, String> mapSina;
	private Map<String, String> mapShell;//银贝壳
	private Map<String, String> mapWeixin;//星启天SDK微信支付
	
	private TipsInfo mTipsInfo;
	private List<Order> mOrderList;//短信多补点计费
	private List<SdkpayData> sdkMapList;//SDK多补点计费
	
	private String mType;
	private String sdkPayType;
	private Commands mCommands;
	
	private String payLoadingshowMsg;
	
	public FastPaymentResultData(){
		mOrderList = new ArrayList<Order>();
		sdkMapList = new ArrayList<SdkpayData>();
	}
	
	public OrderInfo getOrderInfo(){
		if("alipay".equals(mType)){
			return mOrderInfo;
		}
		if("unionpay".equals(mType)){
			return mOrderInfo;
		}
		if(mOrderList.size() == 0){
			return null;
		}
		mOrderInfo = mOrderList.get(0).getOrderInfo();
		return mOrderInfo;
	}
	
	public FastPaymentResultData(String expectType){
		this.mExpectType = expectType;
		mOrderList = new ArrayList<Order>();
		sdkMapList = new ArrayList<SdkpayData>();
	}
	
	public List<Order> getOrders(){
		return mOrderList;
	}
	
	public List<SdkpayData> getSdkMapList(){
		return sdkMapList;
	}

	public String getType(){
		return mType;
	}
	
	public String getSdkPayType(){
		return sdkPayType;
	}
	
	public String getIntervaTime(){
		if(orderlistTime == null)
			return "";
		return orderlistTime;
	}
	
	public Commands getCommands(){
		if(mOrderList.size() == 0){
			return null;
		}
		mCommands = mOrderList.get(0).getCommands();
		return mCommands;
	}
	
	public void setTipInfo(TipsInfo mTipsInfo){
		this.mTipsInfo = mTipsInfo;
	}
	public TipsInfo getTipInfo(){
		if(mTipsInfo == null){
			mTipsInfo = new TipsInfo();
		}
		return mTipsInfo;
	}
	
	public Map<String, String> getMapMM_WO_LoveGame_Gamebase() {
		return mapMM_WO_LoveGame_Gamebase;
	}

	public Map<String, String> getMapSina() {
		return mapSina;
	}

	public void setMapSina(Map<String, String> mapSina) {
		this.mapSina = mapSina;
	}
	
	public Map<String, String> getMapShell() {
		return mapShell;
	}
	
	public Map<String,String> getMapWeixin(){
		return mapWeixin;
	}
	
	public String getPayLoadingshowMsg(){
		return payLoadingshowMsg;
	}

	@Override
	public String getExpectPageType() {
		return mExpectType;
	}

	@Override
	public boolean parseXml(InputStream dateInput) {
//		XmlPullParser parser = Xml.newPullParser();
		try {
//			int count = 0;
//			while(count == 0){
//				count = dateInput.available();
//			}
//			byte buffer[] = new byte[count];
//			dateInput.read(buffer);
//			String str = new String(buffer);
//			Log.e(TAG, "xml info:"+str);
			
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser parser = factory.newPullParser();
			
			parser.setInput(dateInput, "UTF-8");
		    Map<String, String> map = null;
		    int event = parser.getEventType();
			String name = null;
			Command mCommand = null;
			Order order = null;
			SdkpayData sdkpayData = null;
			while (event != XmlPullParser.END_DOCUMENT) {
				if(isParseDataCanceled){
					return false;
				}
				event = parser.next();
				switch (event) {
				case XmlPullParser.START_TAG:
					name = parser.getName();
//					Log.i(TAG,"fast--name-->"+name);
					if(name.equals("orderlist")){
						map = getXmlAttributes(parser);
						orderlistTime = map.get("orderlisttime");
					}else if (name.equals("info")) {
						map = getXmlAttributes(parser);
						if (!parseInfoTag(map)) {
							LogUtil.w(TAG, "parseInfoTag error...");
							return false;
						}
					} else if (name.equals("order")) {
						map = getXmlAttributes(parser);
						mType = map.get("type");
						order = new Order();
						sdkpayData = new SdkpayData();
						mOrderInfo = new OrderInfo();
						mOrderInfo.setOrderNo(map.get("order_no"));
						String payInfo = map.get("pay_info");
						LogUtil.d(TAG, "payInfo:" + payInfo);
						mOrderInfo.setPayInfo(payInfo);
						mOrderInfo.setSms_Type(map.get("sms_type"));
						mOrderInfo.setSms_content_type(map.get("sms_content_type"));
						if(map.get("sms_pay_type") == null){
							
						}else{
							int sms_pay_type = Integer.parseInt(map.get("sms_pay_type"));
							mOrderInfo.setSms_pay_type(sms_pay_type);
						}
						order.setOrderInfo(mOrderInfo);
						sdkpayData.setOrderInfo(mOrderInfo);
					}else if (name.equals("mobilemmpm")||name.equals("mmpmlovegame")
							||name.equals("woappstore")||name.equals("cmccgamebase")) {
						//四网包数据解析
						mapMM_WO_LoveGame_Gamebase = getXmlAttributes(parser);
						
					}else if (name.equals("sinamonthpm")) {
						mapSina = getXmlAttributes(parser);
						
					}else if(name.equals("sshell")){
						mapShell = getXmlAttributes(parser);
						
					}/*else if(name.equals("sdkwimipay")){
						Map<String,String> mapMmpmwimi = getXmlAttributes(parser);
						
						sdkpayData.setDataMap(mapMmpmwimi);
						sdkMapList.add(sdkpayData);
					}else if(name.equals("snowfox")){
						Map<String,String> mapSnowfox = getXmlAttributes(parser);
						
						sdkpayData.setDataMap(mapSnowfox);
						sdkMapList.add(sdkpayData);
					}else if(name.equals("sdkbaixun")){
						Map<String,String> mapSdkbaixun = getXmlAttributes(parser);
						
						sdkpayData.setDataMap(mapSdkbaixun);
						sdkMapList.add(sdkpayData);
					}else if(name.equals("sdkskypay")){
						Map<String,String> mapSdkSky = getXmlAttributes(parser);
						
						sdkpayData.setDataMap(mapSdkSky);
						sdkMapList.add(sdkpayData);
					}else if(name.equals("sdktppay")){
						Map<String,String> mapTppay = getXmlAttributes(parser);
						
						sdkpayData.setDataMap(mapTppay);
						sdkMapList.add(sdkpayData);
					}else if(name.equals("cmccmmwaterwest")){
						Map<String,String> mapWaterWest = getXmlAttributes(parser);
						
						sdkpayData.setDataMap(mapWaterWest);
						sdkMapList.add(sdkpayData);
					}*/else if(name.equals("sdkxqtpay")){
						mapWeixin = getXmlAttributes(parser);
					}else if(name.equals("commands")){
						map = getXmlAttributes(parser);
						mCommands = new Commands();
						mCommands.setmIMSI(map.get("imsi"));
						mCommands.setmTime(map.get("time"));
						mCommands.setmStatus(map.get("status"));
						mCommands.setFlagSend(map.get("flagSend"));
						mCommands.mCommandList = new ArrayList<Command>();
						order.setCommands(mCommands);
						sdkpayData.setCommands(mCommands);
						mOrderList.add(order);
						if(mOrderInfo.getSms_pay_type() == 1 || 
								mOrderInfo.getSms_pay_type() == 2){
							sdkMapList.add(sdkpayData);
						}
					}else if(name.equals("command")){
						map = getXmlAttributes(parser);
						mCommand = new Command();
						mCommand.setmNumber(map.get("number"));
						mCommand.setmContent(map.get("content"));
						mCommand.setWapUrl(map.get("wapurl"));
						mCommand.blockList = new ArrayList<Block>();
						mCommands.mCommandList.add(mCommand);
					}else if(name.equals("block")){
						map = getXmlAttributes(parser);
						Block mBlock = new Block();
						mBlock.setmNumber(map.get("number"));
						mBlock.setmKeyWord(map.get("keyword"));
						mCommand.blockList.add(mBlock);
					}else if(name.equals("tip")){
						map = getXmlAttributes(parser);
						mTipsInfo = new TipsInfo();
//						Log.i(TAG,"二次确认框："+map.get("chargetip"));
//						Log.i(TAG,"计费成功或失败框："+map.get("chargesuceesstip"));
//						Log.i(TAG,"代计费的游戏名："+map.get("gamename"));
						mTipsInfo.setGamename(map.get("gamename"));
						mTipsInfo.setChargetip(map.get("chargetip"));
						mTipsInfo.setChargesuceesstip(map.get("chargesuceesstip"));
						mTipsInfo.setLoadingtip(map.get("loadingtipmin"));
						mTipsInfo.setChargefailtip(map.get("chargefailtip"));
						payLoadingshowMsg = map.get("sendingtip");
					}else{
						Map<String,String> dataMap = getXmlAttributes(parser);
						
						sdkpayData.setDataMap(dataMap);
						sdkMapList.add(sdkpayData);
					}
					break;
//				case XmlPullParser.END_TAG:
//					name = parser.getName();
//					if(name.equals("command")){
//						listBlocks.add(mBlocks);
//					}
//					break;
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
