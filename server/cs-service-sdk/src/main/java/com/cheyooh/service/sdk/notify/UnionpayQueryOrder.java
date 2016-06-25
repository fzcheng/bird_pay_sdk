package com.cheyooh.service.sdk.notify;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.session.RowBounds;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.sdk.cfg.Cfg;
import com.cheyooh.service.sdk.db.dao.SdkOrderMapper;
import com.cheyooh.service.sdk.db.dao.SdkOrderUnionpayMapper;
import com.cheyooh.service.sdk.db.entity.SdkGame;
import com.cheyooh.service.sdk.db.entity.SdkOrder;
import com.cheyooh.service.sdk.db.entity.SdkOrderExample;
import com.cheyooh.service.sdk.db.entity.SdkOrderUnionpay;
import com.cheyooh.service.sdk.db.entity.SdkOrderUnionpayExample;
import com.cheyooh.service.sdk.tools.DBHelper;
import com.cheyooh.service.sdk.tools.StringTool;
import com.cheyooh.tools.http.HttpResult;
import com.cheyooh.tools.http.HttpUtils;
import com.cheyooh.tools.http.HttpUtils.HttpRequest;
import com.cheyooh.tools.log.Logger;
import com.cheyooh.tools.utils.Pagination;
import com.unionpay.upmp.sdk.conf.UpmpConfig;
import com.unionpay.upmp.sdk.util.UpmpCore;

/**
 * 银联主动查询
 * @author Jaly
 *
 */
public class UnionpayQueryOrder extends Thread{

	static Logger logger=new Logger(UnionpayQueryOrder.class);
	
	private static UnionpayQueryOrder instance=new UnionpayQueryOrder();
	
	public static UnionpayQueryOrder getInstance(){
		return instance;
	}
	
	private BlockingQueue<SdkOrderUnionpay> orders=new LinkedBlockingQueue<SdkOrderUnionpay>(1000);
	
	private boolean running=false;
	private NotifyTimeSpan nts=new NotifyTimeSpan();
	private long lastRetryTime=0;
	
	private UnionpayQueryOrder(){
		
	}
	
	@SuppressWarnings("unchecked")
	public void run(){
		running=true;		
		 
		logger.info("Order Query running ...");		
		try{
			while(running){	
				try{
					nts.setSpans(Cfg.cfg.getList("sdk.notify.order.timespan"));		
					
					int pollDelay=Cfg.cfg.getInt("sdk.notify.order.poll_delay",1);
					
					SdkOrderUnionpay order=orders.poll(pollDelay,TimeUnit.SECONDS);	
					
					if(order!=null){
						notifyOrder(order);
					}else{
						loadOrders();
					}					
				}catch(Throwable e){
					logger.error("Order Query exception: "+e,e);					
					if(running){
						try{ Thread.sleep(5000); }catch(Exception ex){}
					}
				}				 
			}			
		}finally{
			running=false;
			logger.error("Order Query exit!");
		}
	}
	
	public void shutdown(){
		this.running=false;
	}
	
	private void loadOrders(){		
		List<SdkOrderUnionpay> list=getOrderQueues(0,null);
		
		List<SdkOrderUnionpay> sos=new ArrayList<SdkOrderUnionpay>();
		if(list!=null && list.size()>0){
			sos.addAll(list);
			
			logger.info("Loaded orders: "+list.size()+" "+toOrderStrings(list));
		}else{
			//执行重试
			long delay=System.currentTimeMillis()-lastRetryTime;
			int retryDelay=Cfg.cfg.getInt("sdk.notify.order.retry_delay",5);
			if(delay>(retryDelay*1000)){								
				for(int i=0;i<nts.getSize();i++){
					NotifyTimeSpan.TimeSpan ts=nts.getTimeSpan(i);					
					list=getOrderQueues(i+1,ts);
					if(list!=null && list.size()>0){						
						sos.addAll(list);
											
						logger.info("Retry times("+(i+1)+": "+ts.getTs()+"), Before: "+ts.getBeforeString()+", orders: "+list.size()+" "+toOrderStrings(list));
						break;
					}					
					
				}
				lastRetryTime=System.currentTimeMillis();
			}
		}
		
		if(sos.size()>0){
			orders.addAll(sos);			 
		}				
	}
	
	private String toOrderStrings(List<SdkOrderUnionpay> list){
		StringBuilder sb=new StringBuilder();
		if(list==null || list.size()==0){
			sb.append("{}");
		}else{		
			sb.append("{");
			for(SdkOrderUnionpay o:list){
				if(sb.length()>1){
					sb.append(", ");
				}					
				sb.append(o.getReqOrdernumber());
			}
			sb.append("}");
		}
		
		return sb.toString();
	}
	 
	private List<SdkOrderUnionpay> getOrderQueues(int times,NotifyTimeSpan.TimeSpan ts){
		int batch_size  =Cfg.cfg.getInt("sdk.notify.order.batch_size",100);
		
		DAL dal=DALFactory.createDAL();
		try{			 
			List<Integer> status_list=new ArrayList<Integer>();
			//等待通知订单
			status_list.add(2);
			status_list.add(3);
			//是否通知失败订单
			if(Cfg.cfg.getBoolean("sdk.order.status_notify.game_status",false)){			
				status_list.add(3);
			}
		 	  
			SdkOrderMapper mapper=dal.getMapper(SdkOrderMapper.class);
			SdkOrderUnionpayMapper sdkOrderUnionpayMapper=dal.getMapper(SdkOrderUnionpayMapper.class);
			SdkOrderExample example=new SdkOrderExample();
			if(ts==null){
				SdkOrderExample.Criteria c1=example.createCriteria();
				c1.andStatusIn(status_list).andQueryStatusEqualTo(0);
				c1.andTypeEqualTo(6);
				SdkOrderExample.Criteria c2=example.or();
				c2.andStatusIn(status_list).andQueryTimeIsNull();		
				c2.andTypeEqualTo(6);
			}else{
				SdkOrderExample.Criteria c1=example.createCriteria();
				c1.andStatusIn(status_list).andQueryStatusEqualTo(-times);				
				c1.andQueryTimeLessThan(ts.getBefore());
				c1.andTypeEqualTo(6);
			}
			
			//example.setOrderByClause("queryTime asc");
			Pagination<SdkOrder> page=mapper.selectByExample(example, new RowBounds(0,batch_size));
			List<SdkOrder> orders=page.getList();
			List<SdkOrderUnionpay> unionpayList=new ArrayList<SdkOrderUnionpay>();
			for(SdkOrder order:orders){
				SdkOrderUnionpay unionpay=sdkOrderUnionpayMapper.selectByPrimaryKey(order.getPayId());
				if(unionpay!=null){
					unionpayList.add(unionpay);
				}
			}
			return unionpayList;					
		}finally{
			dal.close();
		}
	}
	private void notifyOrder(SdkOrderUnionpay order){
		DAL dal=null;
		try{	
			doNotify(order);
		}catch(Exception e){
			logger.error("Notify exception: "+e,e);
		}finally{
			if(dal!=null)dal.close();
		}
	}
	private void doNotify(SdkOrderUnionpay sdkOrderUnionpay){
		DAL dal=DALFactory.createDAL();
		try {
			SdkOrderMapper mapper=dal.getMapper(SdkOrderMapper.class);
			SdkOrder order=mapper.selectByPrimaryKey(sdkOrderUnionpay.getReqOrdernumber());
			if(order!=null){
				String http=Cfg.cfg.getString("unionpay_upmp.query.url");
				HttpRequest request1=HttpUtils.newPostRequest(http);
				
				setOrderRequestParameters(sdkOrderUnionpay,request1,http);
		 
				
				order.setQueryTime(new Date());
				HttpResult r1=request1.sendRequest();
				
				String body=r1.getBody()==null?"":r1.getBody();
				logger.info("UnionpayQueryOrder:"+body);
				String tmpbodyString="";
				if(body.length()>200){
					tmpbodyString=body.substring(0,200)+" ...";
				}
				//order.setNotifyResponse(r1.getStatus()+": "+tmpbodyString);
				String[] param1=body.split("&");
				Map<String, String> paramMap=new HashMap<String, String>();
				for(String key:param1){
					String[] keyValue=key.split("=");
					if(keyValue.length>1){
						paramMap.put(keyValue[0], keyValue[1]);
					}else{
						paramMap.put(key, "");
					}
				}
				if(paramMap.get("transStatus")!=null && paramMap.get("transStatus").equals("00")){
					sdkOrderUnionpay.setNotifyTransstatus(paramMap.get("transStatus"));
					order.setStatus(1);
				}else {
					sdkOrderUnionpay.setNotifyTransstatus(paramMap.get("transStatus"));
					order.setStatus(3);
				}
				String msg="";
				try {
					msg=URLDecoder.decode(paramMap.get("respMsg"),"UTF-8");
				} catch (Exception e) {
					 
				}
				order.setStatusDetail("Unionpay."+msg);
				if(r1.isOk()){
					// 转发到通知地址
					String notifyUrl=sdkOrderUnionpay.getReqBackendurl();
					notifyUrl=notifyUrl+"?"+body;
					logger.info("UnionpayQueryOrder_notifyUrl:"+notifyUrl);
					HttpRequest queryRequest=HttpUtils.newPostRequest(notifyUrl);
					queryRequest.setHeader("Content-Type", "text/html");
					HttpResult notifyR=queryRequest.sendRequest();
					String notifybody=notifyR.getBody()==null?"":notifyR.getBody();
					if(notifyR.isOk() &&  notifybody.trim().toLowerCase().startsWith("success") &&
							paramMap.get("transStatus")!=null && paramMap.get("transStatus").equals("00")){
						logger.info("Notify orderno:  "+order.getOrderNo()+" success!");
						// 更新Order
						
						order.setQueryStatus(1);		
					}else{
						Integer ns=order.getQueryStatus();
						if(ns==null){
							ns=-1;
						}else{
							ns--;
						}
						
						int notify_max=nts.getSize();
						if(-ns>notify_max){
							order.setQueryStatus(2);
							
							String err="订单通知失败, 超过次数: "+notify_max;
							err+=", 错误: "+order.getNotifyResponse();
							//order.setNotifyResponse(StringTool.max(err,200));
							
							logger.error("Query orderno:  "+order.getOrderNo()+", "+err+" , NotifyUrl: "+notifyUrl);
						}else{
							order.setQueryStatus(ns);
							
							logger.error("Query orderno:  "+order.getOrderNo()+", NotifyStatus: "+order.getNotifyStatus()+",  NotifyUrl: "+notifyUrl+", http-status: "+notifyR.getStatus()+", http-body: "+notifyR.getBody());
						}						
					}
					logger.info("Notify orderno:  "+order.getOrderNo()+" success!");
					
					//order.setQueryStatus(1);			
				}else{
					Integer ns=order.getQueryStatus();
					if(ns==null){
						ns=-1;
					}else{
						ns--;
					}
					
					int notify_max=nts.getSize();
					if(-ns>notify_max){
						order.setQueryStatus(2);
						
						String err="订单通知失败, 超过次数: "+notify_max;
						err+=", 错误: "+order.getNotifyResponse();
						//order.setNotifyResponse(StringTool.max(err,200));
						
						logger.error("Notify orderno:  "+order.getOrderNo()+", "+err+" , NotifyUrl: "+http);
					}else{
						order.setQueryStatus(ns);
						
						logger.error("Notify orderno:  "+order.getOrderNo()+", NotifyStatus: "+order.getNotifyStatus()+",  NotifyUrl: "+http+", http-status: "+r1.getStatus()+", http-body: "+r1.getBody());
					}						
				}
				mapper.updateByPrimaryKeySelective(order);
				dal.commit();
				
			}
		} finally{
			dal.close();
		}
		
		
	}
 
	
	private void setOrderRequestParameters(SdkOrderUnionpay unionpay,HttpRequest request,String http){
 
		Map<String, String> req = new HashMap<String, String>();
		req.put("version", unionpay.getReqVersion());// 版本号
		req.put("charset", unionpay.getReqCharset());// 字符编码
		req.put("transType", unionpay.getReqTranstype());// 交易类型
		req.put("merId", unionpay.getReqMerid());// 商户代码
		req.put("backEndUrl", unionpay.getReqBackendurl());// 通知URL
		//req.put("frontEndUrl", UpmpConfig.MER_FRONT_END_URL);// 前台通知URL(可选)
		req.put("orderDescription", unionpay.getReqOrderdescription());// 订单描述(可选)
		req.put("orderTime", unionpay.getReqOrdertime());// 交易开始日期时间yyyyMMddHHmmss
		//req.put("orderTimeout", "");// 订单超时时间yyyyMMddHHmmss(可选)
		req.put("orderNumber", unionpay.getReqOrdernumber());//订单号(商户根据自己需要生成订单号)
		req.put("orderAmount", String.valueOf((int)(unionpay.getReqOrderamount()*100)));// 订单金额(分，不能有小数位)
        req.put("orderCurrency", unionpay.getReqOrdercurrency());// 交易币种(可选)
     // 除去数组中的空值和签名参数
        Map<String, String> filteredReq = UpmpCore.paraFilter(req);
		// 生成签名结果
		String signature = UpmpCore.buildSignature(filteredReq);

		// 签名结果与签名方式加入请求提交参数组中
		filteredReq.put(UpmpConfig.SIGNATURE, signature);
		filteredReq.put(UpmpConfig.SIGN_METHOD, UpmpConfig.SIGN_TYPE);
		String param=UpmpCore.createLinkString(filteredReq, false, true);
		DAL dal=DALFactory.createDAL();
		try {
			SdkOrderMapper mapper=dal.getMapper(SdkOrderMapper.class);
			SdkOrder sdkOrder=mapper.selectByPrimaryKey(unionpay.getReqOrdernumber());
			if(sdkOrder!=null){
				sdkOrder.setNotifyRequest(http+"?"+param);
				mapper.updateByPrimaryKeySelective(sdkOrder);
				dal.commit();
			}
		} finally{
			dal.close();
		}
		for(String key:filteredReq.keySet()){
			request.addParameter(key, filteredReq.get(key));
		}
		
	}
}
