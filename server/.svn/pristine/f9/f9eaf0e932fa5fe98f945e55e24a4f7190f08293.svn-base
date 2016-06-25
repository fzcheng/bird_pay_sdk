package com.media360.jeecg;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.util.resource.ResourceCollection;
import org.eclipse.jetty.webapp.WebAppContext;

public class SdkMain {
	public static void main(String[] args)throws Exception {
		String home=System.getProperty("catalina.home");
		if(home==null){
			home="../sdk-jeecg";
			
			System.setProperty("catalina.home", home);
		}
		 
		File tmp=new File("target/work");
		if(tmp.exists()==false){			
			tmp.mkdirs();
		}
		 
		int port = 8080;		 
 		 
		WebAppContext webapp = new WebAppContext();
		 
		webapp.setContextPath("/sdk-jeecg");
		ResourceCollection resources = new ResourceCollection(new String[] {
		    "../lab-jeecg/WebRoot", 
		    "../lab-jeecg-ext/WebRoot", 
		    home+"/WebRoot", 
		});
		webapp.setBaseResource(resources);		 
		webapp.setTempDirectory(tmp);
		
	 	
		Server server = new Server();
		Connector connector = new SelectChannelConnector();
		connector.setPort(port);
		server.addConnector(connector);
		
		server.setHandler(webapp);
		
		server.start();

	}
	
	static String readFile(String path,String charset)throws IOException{
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		FileInputStream fin=new FileInputStream(path);
		byte[] buf=new byte[4*1024];
		int len=fin.read(buf);
		while(len>0){
			bos.write(buf, 0, len);
			len=fin.read(buf);
		}
		fin.close();
		return new String(bos.toByteArray(),charset);
	}
}
