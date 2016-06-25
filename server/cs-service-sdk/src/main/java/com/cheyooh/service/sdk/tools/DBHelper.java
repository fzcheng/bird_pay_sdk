package com.cheyooh.service.sdk.tools;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.sdk.db.dao.SdkGameCpMapper;
import com.cheyooh.service.sdk.db.dao.SdkGameMapper;
import com.cheyooh.service.sdk.db.dao.SdkOrderMapper;
import com.cheyooh.service.sdk.db.dao.SdkUserMapper;
import com.cheyooh.service.sdk.db.entity.SdkGame;
import com.cheyooh.service.sdk.db.entity.SdkGameCp;
import com.cheyooh.service.sdk.db.entity.SdkGameExample;
import com.cheyooh.service.sdk.db.entity.SdkOrder;
import com.cheyooh.service.sdk.db.entity.SdkUser;

public class DBHelper {
	
	public static SdkOrder getOrderById(String orderno){
		DAL dal=DALFactory.createDAL();
		try{
			SdkOrderMapper mapper=dal.getMapper(SdkOrderMapper.class);
			return mapper.selectByPrimaryKey(orderno);
		}finally{
			dal.close();
		}
	}
	
	public static SdkGame getGameById(int gameid){
		DAL dal=DALFactory.createDAL();
		try{
			SdkGameMapper mapper=dal.getMapper(SdkGameMapper.class);
			return mapper.selectByPrimaryKey(gameid);
		}finally{
			dal.close();
		}
	}
	
	public static SdkUser getUserById(int uid){
		DAL dal=DALFactory.createDAL();
		try{
			SdkUserMapper mapper=dal.getMapper(SdkUserMapper.class);
			return mapper.selectByPrimaryKey(uid);
		}finally{
			dal.close();
		}
	}
	
	public static SdkGame getGameByAppkey(String appkey){
		DAL dal=DALFactory.createDAL();
		try{
			SdkGameMapper mapper=dal.getMapper(SdkGameMapper.class);
			SdkGameExample example=new SdkGameExample();
			example.createCriteria().andAppKeyEqualTo(appkey);
			List<SdkGame> games=mapper.selectByExample(example);
			return games.size()>0?games.get(0):null;
		}finally{
			dal.close();
		}
	}
	
	public static SdkGameCp getGameCpById(int cpid){
		DAL dal=DALFactory.createDAL();
		try{
			SdkGameCpMapper mapper=dal.getMapper(SdkGameCpMapper.class);
			return mapper.selectByPrimaryKey(cpid);
		}finally{
			dal.close();
		}
	}
	
	
	public static String getOrderStatusString(int status){
		String sstatus="订单创建";
		switch(status){
			case 0: sstatus="订单创建"; break; 
			case 1: sstatus="支付成功"; break;
			case 2: sstatus="等待结果"; break;
			case 3: sstatus="支付失败"; break;
			case 4: sstatus="订单异常"; break;
			default:
				sstatus="未知状态: "+status;
		}
		return sstatus;
	}
	
	public static List<String[]> createOrderParameters(SdkOrder order){
		/*
		order_no     M     varchar(32)   : 订单号(每笔充值记录的唯一标记)
		create_time  M     datetime      : 订单产生的时间(格式: yyyy-MM-dd HH:mm:ss, 如: 2013-01-02 16:00:04)
		amount       M     float         : 订单金额(单位:元)
		status       M     integer       : 订单状态: 0-订单创建, 1-支付成功, 2-等待结果, 3-支付失败
		sstatus      M     varchar(64)   : 状态的文字说明
		cp_id        M     integer       : 游戏服务商编号
		game_id      M     integer       : 游戏编号
		uid          M     integer       : 用户ID
		cp_ext       C     varchar(512)  : 游戏服务商扩展信息(原值返回)
		checksign    M     char(32)      : 签名数据
		*/
		
		String sstatus=getOrderStatusString(order.getStatus());		 
		
		List<String[]> nvs=new ArrayList<String[]>();
		nvs.add(new String[]{"order_no",order.getOrderNo()});
		nvs.add(new String[]{"create_time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(order.getCreateTime())});
		nvs.add(new String[]{"amount", ""+order.getAmount()});
		nvs.add(new String[]{"status", ""+order.getStatus()});
		nvs.add(new String[]{"sstatus", sstatus});
		nvs.add(new String[]{"cp_id",""+order.getCpId()});
		nvs.add(new String[]{"game_id",""+order.getGameId()});
		nvs.add(new String[]{"uid",""+order.getUid()});
		if(order.getCpExt()!=null){
			nvs.add(new String[]{"cp_ext",order.getCpExt()});
		}
		
		SdkGameCp cp=DBHelper.getGameCpById(order.getCpId());
		SignParameter sp=new SignParameter();
		sp.addParameters(nvs);
		String sign=sp.getSignString("&");
		sign+="&"+cp.getApiKey();
		String checksign=DigestUtils.md5Hex(sign);
		
		nvs.add(new String[]{"checksign",checksign});
		
		return nvs;
		 
	}
}
