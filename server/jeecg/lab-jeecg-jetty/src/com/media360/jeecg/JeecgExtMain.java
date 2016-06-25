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

public class JeecgExtMain {	
	public static void main(String[] args)throws Exception {						
		System.setProperty("catalina.home", "../../lab-jeecg-ext/trunk");
		File tmp=new File("target/work");
		if(tmp.exists()==false){			
			tmp.mkdirs();
		}
		 
		int port = 8020;		 
 		 
		WebAppContext webapp = new WebAppContext();
	 	
		webapp.setContextPath("/lab-jeecg");
		ResourceCollection resources = new ResourceCollection(new String[] {
		    "../../lab-jeecg/trunk/WebRoot", 
		    "../../lab-jeecg-ext/trunk/WebRoot", 
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
