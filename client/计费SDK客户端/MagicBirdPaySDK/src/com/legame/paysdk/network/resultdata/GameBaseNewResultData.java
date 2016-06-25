package com.legame.paysdk.network.resultdata;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.legame.paysdk.models.Block;
import com.legame.paysdk.models.Command;
import com.legame.paysdk.models.Commands;
import com.legame.paysdk.models.OrderInfo;
import com.legame.paysdk.utils.LogUtil;

public class GameBaseNewResultData extends BaseResultData{
	private static final String TAG = "GameBaseSecodResultData";
	
	private String mExpectType;
	
	private String mType;
	
	private OrderInfo mOrderInfo;
	
	private Commands mCommands;
	
	private Command mCommand;
	
	public GameBaseNewResultData(String mExpectType){
		this.mExpectType = mExpectType;
	}
	
	@Override
	public String getExpectPageType() {
		// TODO Auto-generated method stub
		return mExpectType;
	}

	@Override
	public boolean parseXml(InputStream dataInput) {
		// TODO Auto-generated method stub
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			
			XmlPullParser parser = factory.newPullParser();
			parser.setInput(dataInput, "utf-8");
			
			Map<String,String> map = null;
			String name = null;
			int event = parser.getEventType();
			while(event != XmlPullParser.END_DOCUMENT){
				if(isParseDataCanceled){
					return false;
				}
				switch(event){
				
				case XmlPullParser.START_TAG:
					name = parser.getName();
					if("info".equals(name)){
						map = getXmlAttributes(parser);
						if (!parseInfoTag(map)) {
							LogUtil.w(TAG, "parseInfoTag error...");
							return false;
						}
					}else if("order".equals(name)){
						map = getXmlAttributes(parser);
						mType = map.get("type");
						mOrderInfo = new OrderInfo();
						mOrderInfo.setOrderNo(map.get("order_no"));
						String payInfo = map.get("pay_info");
						LogUtil.d(TAG, "payInfo:" + payInfo);
						mOrderInfo.setPayInfo(payInfo);
						mOrderInfo.setSms_Type(map.get("sms_type"));
						if(map.get("sms_pay_type") == null){
							
						}else{
							int sms_pay_type = Integer.parseInt(map.get("sms_pay_type"));
							mOrderInfo.setSms_pay_type(sms_pay_type);
						}
					}else if("commonds".equals(name)){
						map = getXmlAttributes(parser);
						mCommands = new Commands();
						mCommands.setmIMSI(map.get("imsi"));
						mCommands.setmTime(map.get("time"));
						mCommands.setmStatus(map.get("status"));
						mCommands.mCommandList = new ArrayList<Command>();
					}else if("commond".equals(name)){
						map = getXmlAttributes(parser);
						mCommand = new Command();
						mCommand.setmNumber(map.get("number"));
						mCommand.setmContent(map.get("content"));
						mCommand.blockList = new ArrayList<Block>();
						mCommands.mCommandList.add(mCommand);
					}
					break;
				
				}
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LogUtil.w(TAG, "parseXml error:" + e.toString());
		}
		
		return false;
	}

	public String getmType() {
		return mType;
	}

	public OrderInfo getmOrderInfo() {
		return mOrderInfo;
	}

	public Commands getmCommands() {
		return mCommands;
	}

	public Command getmCommand() {
		return mCommand;
	}
}
