package com.cheyooh.service.sdk.notify;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.session.RowBounds;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.sdk.cfg.Cfg;
import com.cheyooh.service.sdk.db.dao.SdkOrderMapper;
import com.cheyooh.service.sdk.db.entity.SdkGame;
import com.cheyooh.service.sdk.db.entity.SdkOrder;
import com.cheyooh.service.sdk.db.entity.SdkOrderExample;
import com.cheyooh.service.sdk.tools.DBHelper;
import com.cheyooh.service.sdk.tools.StringTool;
import com.cheyooh.tools.http.HttpResult;
import com.cheyooh.tools.http.HttpUtils;
import com.cheyooh.tools.http.HttpUtils.HttpRequest;
import com.cheyooh.tools.log.Logger;
import com.cheyooh.tools.utils.Pagination;

/**
 * 订单状态通知
 * @author zhouzg@cheyooh.com
 *
 */
public class OrderNotify extends Thread{
	static Logger logger=new Logger(OrderNotify.class);
	
	private static OrderNotify instance=new OrderNotify();
	 
	public static OrderNotify getInstance(){		 
		return instance;
	}
	 
	private BlockingQueue<SdkOrder> orders=new LinkedBlockingQueue<SdkOrder>(1000);
	
	private boolean running=false;
	private NotifyTimeSpan nts=new NotifyTimeSpan();
	private long lastRetryTime=0;
	
	private OrderNotify(){		
		
	}
	
	public void addOrder(SdkOrder order){
		//不要再这里增加通知订单,采用单独的订单通知进程
		
		//orders.add(order);
	}
	
	@SuppressWarnings("unchecked")
	public void run(){
		running=true;		
		 
		logger.info("Order notify running ...");		
		try{
			while(running){	
				try{
					nts.setSpans(Cfg.cfg.getList("sdk.notify.order.timespan"));		
					
					int pollDelay=Cfg.cfg.getInt("sdk.notify.order.poll_delay",1);
					
					SdkOrder order=orders.poll(pollDelay,TimeUnit.SECONDS);	
					
					if(order!=null){
						notifyOrder(order);
					}else{
						loadOrders();
					}					
				}catch(Throwable e){
					logger.error("Order notify exception: "+e,e);					
					if(running){
						try{ Thread.sleep(5000); }catch(Exception ex){}
					}
				}				 
			}			
		}finally{
			running=false;
			logger.error("Order notify exit!");
		}
	}
	
	public void shutdown(){
		this.running=false;
	}
	
	
	private void loadOrders(){		
		List<SdkOrder> list=getOrderQueues(0,null);
		
		List<SdkOrder> sos=new ArrayList<SdkOrder>();
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
	
	private String toOrderStrings(List<SdkOrder> list){
		StringBuilder sb=new StringBuilder();
		if(list==null || list.size()==0){
			sb.append("{}");
		}else{		
			sb.append("{");
			for(SdkOrder o:list){
				if(sb.length()>1){
					sb.append(", ");
				}					
				sb.append(o.getOrderNo());
			}
			sb.append("}");
		}
		
		return sb.toString();
	}
	
	private List<SdkOrder> getOrderQueues(int times,NotifyTimeSpan.TimeSpan ts){
		int batch_size  =Cfg.cfg.getInt("sdk.notify.order.batch_size",100);
		
		DAL dal=DALFactory.createDAL();
		try{			 
			List<Integer> status_list=new ArrayList<Integer>();
			//成功订单
			status_list.add(1);
			
			//是否通知失败订单
			if(Cfg.cfg.getBoolean("sdk.order.status_notify.game_status",false)){			
				status_list.add(3);
			}
		 	  
			SdkOrderMapper mapper=dal.getMapper(SdkOrderMapper.class);
			SdkOrderExample example=new SdkOrderExample();
			if(ts==null){
				SdkOrderExample.Criteria c1=example.createCriteria();
				c1.andStatusIn(status_list).andNotifyStatusEqualTo(0);
				
				SdkOrderExample.Criteria c2=example.or();
				c2.andStatusIn(status_list).andNotifyTimeIsNull();				
			}else{
				SdkOrderExample.Criteria c1=example.createCriteria();
				c1.andStatusIn(status_list).andNotifyStatusEqualTo(-times);				
				c1.andNotifyTimeLessThan(ts.getBefore());				
			}			
			example.setOrderByClause("notify_time asc");
			Pagination<SdkOrder> page=mapper.selectByExample(example, new RowBounds(0,batch_size));
			List<SdkOrder> orders=page.getList();
			
			return orders;					
		}finally{
			dal.close();
		}
	}
	
	private void notifyOrder(SdkOrder order){
		DAL dal=null;
		try{	
			doNotify(order);
			
			dal=DALFactory.createDAL();
			SdkOrderMapper mapper=dal.getMapper(SdkOrderMapper.class);
			mapper.updateByPrimaryKeySelective(order);
			
			dal.commit();
		}catch(Exception e){
			logger.error("Notify exception: "+e,e);
		}finally{
			if(dal!=null)dal.close();
		}
	}
	
	
	private void doNotify(SdkOrder order){
		SdkGame game=DBHelper.getGameById(order.getGameId());
		
		String http=game.getCallbackRecharge();
		HttpRequest request=HttpUtils.newPostRequest(http);
		
		setOrderRequestParameters(order,request);
		setupOrderRequest(order,http);
		
		order.setNotifyTime(new Date());
		HttpResult r=request.sendRequest();
		
		String body=r.getBody()==null?"":r.getBody();
		if(body.length()>200){
			body=body.substring(0,200)+" ...";
		}
		order.setNotifyResponse(r.getStatus()+": "+body);
	 	
		if(r.isOk() &&  body.trim().toLowerCase().startsWith("success")){
			logger.info("Notify success! "+getOrderStatusInfo(order)+" ,order request "+order.getNotifyRequest());
			order.setCompleteTime(new Date(System.currentTimeMillis()));
			order.setNotifyStatus(1);			
		}else{
			Integer ns=order.getNotifyStatus();
			if(ns==null){
				ns=-1;
			}else{
				ns--;
			}
			
			int notify_max=nts.getSize();
			if(-ns>notify_max){
				order.setNotifyStatus(2);
				
				String err="订单通知失败, 超过次数: "+notify_max;
				err+=", 错误: "+order.getNotifyResponse();
				order.setNotifyResponse(StringTool.max(err,200));
				
				logger.error("Notify fail! "+getOrderStatusInfo(order)+", "+err+" , NotifyRequest: "+order.getNotifyRequest());
			}else{
				order.setNotifyStatus(ns);
				
				logger.error("Notify error! "+getOrderStatusInfo(order)+", NotifyStatus: "+order.getNotifyStatus()+",  NotifyRequest: "+order.getNotifyRequest()+", http-status: "+r.getStatus()+", http-body: "+r.getBody());
			}
		}
	}
	
	private String getOrderStatusInfo(SdkOrder order){
		return "orderno:  "+order.getOrderNo()+", status: "+order.getStatus()+"("+DBHelper.getOrderStatusString(order.getStatus())+")";
	}
	
	private void setupOrderRequest(SdkOrder order,String http){
		StringBuilder notifyRequest=new StringBuilder(http);
		boolean b=notifyRequest.indexOf("?")>0;
		List<String[]> nvs=DBHelper.createOrderParameters(order);
		for(String[] nv:nvs){		
			if(nv[1]!=null){
				if(b){
					notifyRequest.append("&").append(nv[0]).append("=").append(nv[1]);
				}else{
					notifyRequest.append("?").append(nv[0]).append("=").append(nv[1]);
					b=true;
				}
			}
		}	
		order.setNotifyRequest(notifyRequest.toString());
	}
	
	private void setOrderRequestParameters(SdkOrder order,HttpRequest request){
		List<String[]> nvs=DBHelper.createOrderParameters(order);
		for(String[] nv:nvs){		
			if(nv[1]!=null){
				request.addParameter(nv[0],nv[1]);
			}
		}		 		
	}
 	 
}
