package com.cheyooh.service.sdk.notify;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.cheyooh.service.sdk.tools.StringTool;
import com.cheyooh.tools.log.Logger;

public class NotifyTimeSpan {
	static Logger logger=new Logger(NotifyTimeSpan.class);
	
	private List<TimeSpan> spans=new ArrayList<TimeSpan>();
	 
	public NotifyTimeSpan(){
		spans.add(new TimeSpan("10s"));
		spans.add(new TimeSpan("2m"));
		spans.add(new TimeSpan("10m"));
		spans.add(new TimeSpan("1h"));
		spans.add(new TimeSpan("2h"));
		spans.add(new TimeSpan("6h"));
		spans.add(new TimeSpan("15h"));
	}
	
	public int getSize(){
		return spans.size();
	}
	
	public void setSpans(List<Object> ts){		
		try{			 
			if(ts!=null && ts.size()>0){
				List<TimeSpan> tmp=new ArrayList<TimeSpan>();
				
				for(Object s:ts){
					tmp.add(new TimeSpan((String)s));
				}
				
				if(tmp.size()>0){
					Collections.sort(tmp);
					
					boolean changed=false;
					if(spans.size()!=tmp.size()){
						changed=true;
					}else{
						for(int i=0;i<spans.size();i++){
							if(spans.get(i).equals(tmp.get(i))==false){
								changed=true;
							}
						}
					}
					if(changed){	
						logger.info("Changed time span to: "+ts);
						spans.clear();
						spans.addAll(tmp);
					}
				}else{
					logger.error("Invalid time span: "+ts);
				}
			}					
		}catch(Exception e){
			logger.error(e+", invalid time span: "+ts,e);
		}
	}
	
	public TimeSpan getTimeSpan(int index){
		return spans.get(index);
	}
	
	static class TimeSpan implements Comparable<TimeSpan>{
		private long timeInMillis;
		private String ts;
		
		public TimeSpan(String ts){
			timeInMillis=StringTool.getTimeMillis(ts);
			this.ts=ts;			
		}
		
		public Date getBefore(){
			return new Date(System.currentTimeMillis()-timeInMillis);
		}
		
		public Date getAfter(){
			return new Date(System.currentTimeMillis()+timeInMillis);
		}
		
		public String getBeforeString(){
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getBefore());
		}
		public String getAfterString(){
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getAfter());
		}
	
		public int compareTo(TimeSpan o) {			 
			long thisVal = this.timeInMillis;
			long anotherVal = o.timeInMillis;
			return (thisVal<anotherVal ? -1 : (thisVal==anotherVal ? 0 : 1));
		}
		
		public int hashCode(){
			return (int)(timeInMillis ^ (timeInMillis >>> 32));
		}
		
		public boolean equals(Object other){
			if(other instanceof TimeSpan){
				return ((TimeSpan)other).getTimeInMillis()==this.timeInMillis;
			}
			return false;
		}

		public long getTimeInMillis() {
			return timeInMillis;
		}

		public void setTimeInMillis(long timeInMillis) {
			this.timeInMillis = timeInMillis;
		}

		public String getTs() {
			return ts;
		}

		public void setTs(String ts) {
			this.ts = ts;
		}

	}
}
