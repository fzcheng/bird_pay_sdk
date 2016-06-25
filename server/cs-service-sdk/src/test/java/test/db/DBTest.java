package test.db;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Date;

import org.nuxeo.common.xmap.XMap;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.sdk.db.dao.SdkGameCpMapper;
import com.cheyooh.service.sdk.db.entity.SdkGameCp;
import com.cheyooh.service.sdk.idata.gameserver.XmlAlipayNotify;
import com.cheyooh.service.sdk.tools.GameSession;
import com.cheyooh.service.sdk.tools.GenerateTool;
import com.cheyooh.tools.log.Logger;

public class DBTest {
	static Logger logger = new Logger(DBTest.class);

	public static void main(String[] args)throws Exception {
		System.out.println(GenerateTool.getRandomString(32));
		
		XMap xmap = new XMap();
		xmap.register(XmlAlipayNotify.class);
		String data="<notify>"+
		"<trade_status>TRADE_FINISHED</trade_status>"+
		"<total_fee>0.90</total_fee>"+
		"<subject>123456</subject>"+
		"<out_trade_no>1118060201-7555</out_trade_no>"+
		"<notify_reg_time>2013-11-1112:33:33.000</notify_reg_time>"+
		"<trade_no>1122334455</trade_no>"+
		"</notify>";
		ByteArrayInputStream in=new ByteArrayInputStream(data.getBytes("utf-8"));
		XmlAlipayNotify xml=(XmlAlipayNotify)xmap.load(in);
		 
		logger.info("tno="+xml.getTrade_no());
		
		GameSession gs=new GameSession(100,300);
		logger.info(gs.toString());
		 
		GameSession decode=new GameSession(gs.getSid());
		logger.info(decode.toString());
		logger.info("expired: "+decode.isExpired());
		
		logger.info("OrderNo: "+ GenerateTool.createOrderNo() );		
		
		DAL dal = DALFactory.createDAL();
		try {
			SdkGameCpMapper mapper_cp = dal.getMapper(SdkGameCpMapper.class);
			for(int i=150;i<153;i++){
				SdkGameCp old=mapper_cp.selectByPrimaryKey(i);
				if(i==150)dal.close();
				logger.info(old);
			}
			
			
			SdkGameCp record = new SdkGameCp();
			record.setCreateTime(new Date());
			record.setEmail("zhouzg@cheyooh.com");
			record.setMemo("测试");
			record.setName("cpname");
			record.setPwd("md5");
			record.setApiKey("****");
			mapper_cp.insert(record);
			dal.commit();

			logger.info("CPID: " + record.getCpId());
		} finally {
			dal.close();
		}
		
	}

}
