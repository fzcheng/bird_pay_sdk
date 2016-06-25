package com.legame.paysdk.network.resultdata;

import java.util.Map;

import org.xmlpull.v1.XmlPullParser;

import com.legame.paysdk.models.Block;
import com.legame.paysdk.models.Command;

public class ZwjfpayResultData extends BaseXmlResultData{
	
	private String extrType;
	
	private String orderNo;
	
	private Command command;
	
	private Block block;
	
	public ZwjfpayResultData(String extrType){
		this.extrType = extrType;
		command = new Command();
		block = new Block();
	}

	@Override
	protected void getParams(String name, XmlPullParser parser) {
		if("order".equals(name)){
			Map<String, String> map = getXmlAttributes(parser);
			orderNo = map.get("order_no");
		}else if("command".equals(name)){
			Map<String, String> map = getXmlAttributes(parser);
			command.setmContent(map.get("content"));
			command.setmNumber(map.get("number"));
			command.setZwjf(map.get("zwjf"));
		}else if("block".equals(name)){
			Map<String, String> map = getXmlAttributes(parser);
			block.setmKeyWord(map.get("keyword"));
			block.setmNumber(map.get("number"));
		}
		
	}

	@Override
	public String getExpectPageType() {
		return extrType;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public Command getCommand() {
		return command;
	}

	public Block getBlock() {
		return block;
	}
	
}
