package test.com.cheyooh.tools.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang.math.RandomUtils;

import com.cheyooh.tools.http.HttpResult;
import com.cheyooh.tools.http.HttpUtils;
import com.cheyooh.tools.log.Logger;



public class HttpUtilsTest {
	static Logger logger=new Logger(HttpUtilsTest.class);
		 
	private int port=9526;	
	private int numberOfResponseThreads=150;
	private int numberOfRequestThreads=100;
	private int totalRequest=100;
	
	private ThreadPoolExecutor poolRequest;	
	private ThreadPoolExecutor poolResponse;	
	private Thread serverThread;
	private ServerSocket ss;
	
	public void startServer()throws Exception{
		
		
		poolResponse=new ThreadPoolExecutor(numberOfResponseThreads,numberOfResponseThreads,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
		
		serverThread=new Thread(){
			public void run(){
				try{					
					ss=new ServerSocket(port);
					logger.info("Listening on port: "+port);
					while(!ss.isClosed()){
						try{
						final Socket s=ss.accept();
						
						poolResponse.submit(new Runnable(){ 
							public void run(){
								try{
									BufferedReader reader=new BufferedReader(new InputStreamReader(s.getInputStream(),"utf-8"));
								
									String cmd=reader.readLine();
									String request_line=cmd;
									while(cmd!=null){
										if(cmd.length()==0){
											break;
										}
										cmd=reader.readLine();
									}
									
									OutputStream out=s.getOutputStream();
									
									long delay=500+( RandomUtils.nextLong() % 2000);
									//logger.info("Delay: "+delay+" for: "+request_line);
									Thread.sleep(delay);
									
									String body="Request delay:"+delay+", url: "+request_line;
									StringBuilder sb=new StringBuilder();
									sb.append("HTTP/1.1 200 OK").append("\r\n");
									sb.append("Content-Length: "+body.getBytes("utf-8").length).append("\r\n");
									sb.append("\r\n");									
									sb.append(body);
									out.write(sb.toString().getBytes("utf-8"));
									out.flush();
									s.close();
								}catch(Exception e){
									logger.error(e);
								}
							}
						});	
						}catch(SocketException se){
							if(!se.getMessage().equals("socket closed")){
								logger.error(se);
							}
						}
					}
				}catch(Exception e){
					logger.error(e);
				}
			}
		};
		serverThread.start();
	}
	
	AtomicInteger respCount=new AtomicInteger();
	private Object lock=new Object();
	private int failCount=0;
	private int okCount=0;
	public void testHttpUtils()throws Exception{
		startServer();
		
		HttpUtils.timeout_connection=2000;
		HttpUtils.timeout_socket=3000;
		//HttpUtils.max_total_connections_per_route=50;
		
		poolRequest=new ThreadPoolExecutor(numberOfRequestThreads,numberOfRequestThreads,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());		
		for(int i=0;i<totalRequest;i++){
			final String path="R-"+i;
			poolRequest.submit(new Runnable(){
				public void run(){
					try{						 
						HttpResult r=HttpUtils.newGetRequest("http://127.0.0.1:"+port+"/"+path).sendRequest();												 
						 
						synchronized(lock){
							if(r.isOk()){
								okCount++;
							}else{
								failCount++;
							}
							logger.info("Respone("+respCount.getAndIncrement()+"), status: "+r.getStatus()+", use time: "+(r.getUsetime())+", result: "+r.getBody());
						}						 
					}catch(Exception e){
						respCount.getAndIncrement();
						
						logger.error(e);
						
					}
				}
			});
		}	
		
		while(respCount.get()<totalRequest){
			Thread.sleep(100);
		}
		
		 	
		poolRequest.shutdown();
		poolResponse.shutdown();
		ss.close();
		logger.info("Fail: "+failCount+", Ok: "+okCount);
	}
	
	 
	
	public static void main(String[] args) throws Exception{
		HttpUtilsTest test=new HttpUtilsTest();
		test.testHttpUtils();
	}

}
