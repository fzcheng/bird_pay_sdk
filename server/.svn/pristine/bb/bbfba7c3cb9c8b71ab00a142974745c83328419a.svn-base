package com.cheyooh.tools.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.params.HttpParams;

import com.cheyooh.tools.log.Logger;

public class HttpClientCfg {
	static Logger logger = new Logger(HttpClientCfg.class);	
	private HttpParams params = null;
	private PlainSocketFactory plainSocketFactory = null;
	private SSLSocketFactory sslSocketFactory = null;

	public HttpClientCfg() {}

	public HttpClientCfg(String  localIpV4) throws UnknownHostException {
		this(localIpV4,null);
	}
	public HttpClientCfg(String  localIpV4,HttpParams params) throws UnknownHostException {
		byte[] ipv4=new byte[4];
		String[] sv=localIpV4.split("\\.");
		for(int i=0;i<sv.length;i++){
			ipv4[i]=(byte)(Integer.parseInt(sv[i])&0xFF);
		}
		InetAddress ia=InetAddress.getByAddress(ipv4);		
		InetSocketAddress localAddress=new InetSocketAddress(ia,0);		
		plainSocketFactory=new LocalAddressPlainSocketFactory(localAddress);		
		this.params=params;
	}
	
	public HttpClientCfg(InetSocketAddress localAddress) {
		plainSocketFactory=new LocalAddressPlainSocketFactory(localAddress);
	}

	public PlainSocketFactory getPlainSocketFactory() {
		return plainSocketFactory;
	}

	public void setPlainSocketFactory(PlainSocketFactory socketFactory) {
		this.plainSocketFactory = socketFactory;
	}

	public SSLSocketFactory getSslSocketFactory() {
		return sslSocketFactory;
	}

	public void setSslSocketFactory(SSLSocketFactory sslSocketFactory) {
		this.sslSocketFactory = sslSocketFactory;
	}

	public HttpParams getParams() {
		return params;
	}

	public void setParams(HttpParams params) {
		this.params = params;
	}

	static class LocalAddressPlainSocketFactory extends PlainSocketFactory {
		private InetSocketAddress localAddress;		
		public LocalAddressPlainSocketFactory(InetSocketAddress localAddress){
			this.localAddress=localAddress;
		}
		public Socket connectSocket(final Socket socket, final InetSocketAddress remoteAddress, final InetSocketAddress localAddress, final HttpParams params)
				throws IOException, ConnectTimeoutException {			 
			return super.connectSocket(socket, remoteAddress, this.localAddress, params);
		}
	}

}
