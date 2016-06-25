package com.cheyooh.tools.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import com.cheyooh.tools.log.Logger;
import com.cheyooh.tools.utils.IOUtils;
import com.cheyooh.tools.utils.PropertyUtils;

/**
 * @author zzg {MSN:pingzzg@hotmail.com, QQ:11039850}
 * 
 */
public class HttpUtils {
	static Logger logger = new Logger(HttpUtils.class);

	public static boolean DEBUG = false;
	
	public static int timeout_connection = 10000;
	public static int timeout_socket     = 10000;

	public static int max_total_connections = 10000;
	public static int max_total_connections_per_route = 400;

	public static int socket_buffer_size = 256 * 1024;

	public static int reader_buffer_size = 64 * 1024;

	private static List<BasicHeader> basicHeaders = null;

	private static HttpClient httpClient = null;

	private static synchronized HttpClient getHttpClient() {
		if (httpClient == null) {
			httpClient = createClient(new HttpClientCfg());
		}
		return httpClient;
	}

	public static HttpClient createClient(HttpClientCfg cfg) {
		// ConnectionPoolTimeoutException,ConnectionTimeoutException,SocketTimeoutException
		HttpParams params = cfg.getParams();
		if (params == null) {
			params = getDefaultHttpParams();
		}

		PlainSocketFactory plainSocketFactory = cfg.getPlainSocketFactory();
		if (plainSocketFactory == null) {
			plainSocketFactory = PlainSocketFactory.getSocketFactory();
		}
		SSLSocketFactory sslSocketFactory = cfg.getSslSocketFactory();
		if (sslSocketFactory == null) {
			sslSocketFactory = SSLSocketFactory.getSocketFactory();
		}

		SchemeRegistry sch_reg = new SchemeRegistry();
		sch_reg.register(new Scheme("http", 80, plainSocketFactory));
		sch_reg.register(new Scheme("https", 443, sslSocketFactory));
		
		PoolingClientConnectionManager pccm=new PoolingClientConnectionManager(sch_reg);			
		pccm.setMaxTotal(max_total_connections);  
		pccm.setDefaultMaxPerRoute(max_total_connections_per_route); 		
		
		HttpClient client = new DefaultHttpClient(pccm, params);
 				
		return client;
	}

	public static HttpParams getDefaultHttpParams() {
		HttpParams params = new BasicHttpParams();

		HttpClientParams.setCookiePolicy(params, CookiePolicy.BROWSER_COMPATIBILITY);

		HttpConnectionParams.setConnectionTimeout(params, timeout_connection);
		HttpConnectionParams.setSoTimeout(params, timeout_socket);
		HttpConnectionParams.setSocketBufferSize(params, socket_buffer_size);
		  
		params.setParameter("http.default-headers", getBasicHeaders());

		params.setParameter(ClientPNames.HANDLE_REDIRECTS, true);

		return params;
	}

	public static void setBasicHeaders(List<BasicHeader> basicHeaders) {
		HttpUtils.basicHeaders = basicHeaders;
	}

	
	public static List<BasicHeader> getBasicHeaders() {
		if (basicHeaders == null) {
			List<BasicHeader> headers = new ArrayList<BasicHeader>();
			headers.add(new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"));
			headers.add(new BasicHeader("Accept-Language", "zh-cn,zh;q=0.5"));
			headers.add(new BasicHeader("Connection", "keep-alive"));
			headers.add(new BasicHeader("Accept-Encoding", "gzip, deflate"));
			headers.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:10.0.2) Gecko/20100101 Firefox/10.0.2"));
			return headers;
		} else {
			return basicHeaders;
		}
	}

	public static void shutdown() {
		if (httpClient != null) {
			httpClient.getConnectionManager().shutdown();
		}
	}

	public static HttpRequest newMultiPartPostRequest(String url) {
		return newMultiPartPostRequest(getHttpClient(),url);
	}

	public static HttpRequest newPostRequest(String url) {
		return newPostRequest(getHttpClient(),url);
	}

	public static HttpRequest newGetRequest(String url) {
		return newGetRequest(getHttpClient(),url);
	}
	
	public static HttpRequest newMultiPartPostRequest(HttpClient client,String url) {
		return new MultipartPostHttpRequest(client,url);
	}

	public static HttpRequest newPostRequest(HttpClient client,String url) {
		return new PostHttpRequest(client,url);
	}

	public static HttpRequest newGetRequest(HttpClient client,String url) {
		return new GetHttpRequest(client,url);
	}

	public static HttpRequest newHeadRequest(String url) {
		return newHeadRequest(getHttpClient(),url);
	}
	public static HttpRequest newHeadRequest(HttpClient client,String url) {
		return new HeadHttpRequest(client,url);
	}
	
	public static HttpRequest newOptionsRequest(String url) {
		return newOptionsRequest(getHttpClient(),url);
	}
	public static HttpRequest newOptionsRequest(HttpClient client,String url) {
		return new OptionsHttpRequest(client,url);
	}
	
	public static HttpRequest newDeleteRequest(String url) {
		return newDeleteRequest(getHttpClient(),url);
	}
	public static HttpRequest newDeleteRequest(HttpClient client,String url) {
		return new DeleteHttpRequest(client,url);
	}
 
	private static String getCharset(HttpEntity entity) {
		String charset = null;

		Header hc = entity.getContentEncoding();
		if (hc != null) {
			String cc = hc.getValue().toLowerCase();
			if (cc.indexOf("gb") >= 0) {
				charset = "GBK";
			}
		}

		if (charset == null) {
			hc = entity.getContentType();
			if (hc != null) {
				String cc = hc.getValue().toLowerCase();
				if (cc.indexOf("gb") >= 0) {
					charset = "GBK";
				}
			}
		}

		return charset == null ? "utf-8" : charset;

	}

	private static boolean isGzip(HttpEntity entity) {
		Header hc = entity.getContentEncoding();
		if (hc != null) {
			String v = hc.getValue();
			return v.toLowerCase().indexOf("gzip") >= 0;
		}
		return false;
	}

	private static InputStream getInputStream(HttpEntity entity) throws IOException {
		InputStream in = null;

		boolean gzip = isGzip(entity);
		InputStream cin = entity.getContent();
		if (gzip) {
			in = new GZIPInputStream(cin);
		} else {
			in = cin;
		}
		return in;
	}

	private static String getBodyAsString(HttpEntity entity,String charset) throws Exception {
		if (entity != null) {
			StringBuffer body = new StringBuffer();

			long content_length = entity.getContentLength();
			BufferedReader reader = null;

			try {				 
				if(charset==null){
					charset = getCharset(entity);
				}
				int read_len = 0;

				InputStream instream = getInputStream(entity);
				reader = new BufferedReader(new InputStreamReader(instream, charset));

				char[] buf = new char[reader_buffer_size];
				int len = reader.read(buf);
				while (len > 0) {
					read_len += len;
					body.append(buf, 0, len);

					if (content_length > 0) {
						if (DEBUG)
							logger.debug(read_len + "/" + content_length);
					}

					len = reader.read(buf);
				}
				return body.toString();
			} finally {
				IOUtils.close(reader);
			}
		}
		return null;
	}

	public static interface DownloadListener {
		public void downloading(String tofile, long downloadLength, long totalLength);
		public void downloadFinished(String tofile);
		public void downloadBreak(String tofile, long downloadLength, long totalLength);
		public void downloadException(String tofile, int errorCode, String errorMessage);
	}
	
	private static String trimUrl(String url){
		int p1=url.indexOf("#");
		if(p1>0){
			int p2=url.indexOf("#",p1);
			if(p2>0){
				return url.substring(0,p2);
			}
		}
		return url;
	}

	public static class GetHttpRequest extends HttpRequest {
		public GetHttpRequest(HttpClient client,String url) {
			super(client,new HttpGet(trimUrl(url)));
		}

		public HttpRequest addParameter(String name, final String value) {
			throw new RuntimeException("Get request cannot add parameters");
		}

		public HttpRequest addFile(File file, String contentType, String name) {
			throw new RuntimeException("Only MultipartPostHttpRequest support post file!");
		}

		public HttpRequest addStream(final InputStream in, final long length, String contentType, String name) {
			throw new RuntimeException("Only MultipartPostHttpRequest support post stream!");
		}
		
		public HttpRequest setBodyEntity(HttpEntity entity){
			throw new RuntimeException("Only PostHttpRequest support body entity!");
		}
	}
	
	public static class HeadHttpRequest extends HttpRequest {
		public HeadHttpRequest(HttpClient client,String url) {
			super(client,new HttpHead(trimUrl(url)));
		}

		public HttpRequest addParameter(String name, final String value) {
			throw new RuntimeException("Head request cannot add parameters");
		}

		public HttpRequest addFile(File file, String contentType, String name) {
			throw new RuntimeException("Only MultipartPostHttpRequest support post file!");
		}

		public HttpRequest addStream(final InputStream in, final long length, String contentType, String name) {
			throw new RuntimeException("Only MultipartPostHttpRequest support post stream!");
		}
		
		public HttpRequest setBodyEntity(HttpEntity entity){
			throw new RuntimeException("Only PostHttpRequest support body entity!");
		}
	}
	
	public static class OptionsHttpRequest extends HttpRequest {
		public OptionsHttpRequest(HttpClient client,String url) {
			super(client,new HttpOptions(trimUrl(url)));
		}

		public HttpRequest addParameter(String name, final String value) {
			throw new RuntimeException("Options request cannot add parameters");
		}

		public HttpRequest addFile(File file, String contentType, String name) {
			throw new RuntimeException("Only MultipartPostHttpRequest support post file!");
		}

		public HttpRequest addStream(final InputStream in, final long length, String contentType, String name) {
			throw new RuntimeException("Only MultipartPostHttpRequest support post stream!");
		}
		
		public HttpRequest setBodyEntity(HttpEntity entity){
			throw new RuntimeException("Only PostHttpRequest support body entity!");
		}
	}
	
	public static class DeleteHttpRequest extends HttpRequest {
		public DeleteHttpRequest(HttpClient client,String url) {
			super(client,new HttpDelete(trimUrl(url)));
		}

		public HttpRequest addParameter(String name, final String value) {
			throw new RuntimeException("Delete request cannot add parameters");
		}

		public HttpRequest addFile(File file, String contentType, String name) {
			throw new RuntimeException("Only MultipartPostHttpRequest support post file!");
		}

		public HttpRequest addStream(final InputStream in, final long length, String contentType, String name) {
			throw new RuntimeException("Only MultipartPostHttpRequest support post stream!");
		}
		
		public HttpRequest setBodyEntity(HttpEntity entity){
			throw new RuntimeException("Only PostHttpRequest support body entity!");
		}
	}

	public static class PostHttpRequest extends HttpRequest {
		private List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		
		private HttpEntity entity;
		
		public PostHttpRequest(HttpClient client,String url) {
			super(client,new HttpPost(trimUrl(url)));
		}

		/**
		 * UTF-8发送请求的参数
		 */
		public HttpResult sendRequest() {
			return sendRequest("UTF-8");			 
		}
		
		/**
		 * 采用指定编码集POST数据
		 * @param charset
		 * @return
		 */
		public HttpResult sendRequest(String charset) {
			return sendRequest(charset,null);
		}
		
		/**
		 * 采用指定的编码POST数据, 以及指定BODY编码集
		 * @param charsetRequest
		 * @param charsetResponse
		 * @return
		 */
		public HttpResult sendRequest(String charsetRequest,String charsetResponse) {
			if(entity!=null){
				((HttpPost) request).setEntity(entity);
			}else if (nvps.size() > 0) {
				try {
					((HttpPost) request).setEntity(new UrlEncodedFormEntity(nvps, charsetRequest));
				} catch (Exception e) {
					return new HttpResult(400, e.getMessage());
				}
			}
			return super.sendRequest();
		}
		
		public HttpRequest setBodyEntity(HttpEntity entity){
			this.entity=entity;
			
			return this;
		}

		public HttpRequest addParameter(String name, final String value) {
			nvps.add(new BasicNameValuePair(name, value));

			return this;
		}

		public HttpRequest addFile(File file, String contentType, String name) {
			throw new RuntimeException("Only MultipartPostHttpRequest support post file!");
		}

		public HttpRequest addStream(final InputStream in, final long length, String contentType, String name) {
			throw new RuntimeException("Only MultipartPostHttpRequest support post stream!");
		}
	}

	public static class MultipartPostHttpRequest extends HttpRequest {
		private MultipartEntity entity;

		public MultipartPostHttpRequest(HttpClient client,String url) {
			super(client,new HttpPost(trimUrl(url)));
			entity = new MultipartEntity();
			((HttpPost) request).setEntity(entity);
		}

		public HttpRequest addParameter(String name, final String value) {
			try {
				entity.addPart(name, new StringBody(value));
			} catch (UnsupportedEncodingException e) {
				logger.error("" + e, e);
			}
			return this;
		}

		public HttpRequest addFile(File file, String contentType, String name) {
			FileBody fileBody = new FileBody(file, contentType);
			entity.addPart(name, fileBody);
			return this;
		}

		public HttpRequest addStream(final InputStream in, final long length, String contentType, String name) {
			InputStreamBody inBody = new InputStreamBody(in, contentType, name) {
				public long getContentLength() {
					return length;
				}
			};
			entity.addPart(name, inBody);
			return this;

		}
		
		public HttpRequest setBodyEntity(HttpEntity entity){
			throw new RuntimeException("Only PostHttpRequest support body entity!");
		}
	}

	private static String getPercentString(long position, long total) {
		if (total <= 0) {
			return "0%";
		}
		if (position >= total) {
			return "100%";
		} else {
			return (position * 100 / total) + "%";
		}
	}

	public static abstract class HttpRequest {
		protected HttpRequestBase request;

		protected boolean running = true;

		protected HttpClient client;
		protected String charsetResponse;
		
		HttpRequest(HttpClient client, HttpRequestBase request) {
			this.client = client;
			this.request = request;
						
		}
		
		public void breakDownload() {
			running = false;
		}

		 
		public HttpResult sendRequest() {
			logger.debug(request.getMethod() + " " + request.getURI().toASCIIString());
			try {
				long tm = System.currentTimeMillis();

				HttpResponse response = getResponse();

				HttpEntity entity = response.getEntity();
				String body = getBodyAsString(entity,this.charsetResponse);

				int status = response.getStatusLine().getStatusCode();
				long use_time = System.currentTimeMillis() - tm;

				logger.debug("下载用时: " + use_time + " ms, URI: " + request.getURI());
				return new HttpResult(status, body, request.getURI().toString());
			} catch (SocketException e) {
				return new HttpResult(601, e.getClass() + ": "+e.getMessage(), request.getURI().toString());
			} catch (Exception e) {
				return new HttpResult(600, e.getClass() + ": "+ e.getMessage(), request.getURI().toString());
			} finally {
				abortRequest();
			}
		}

		private HttpResponse getResponse() throws ClientProtocolException, IOException {
			Header[] headers=request.getAllHeaders();
			
			HttpResponse response = client.execute(request);
			for (int i = 0; i < 3; i++) {
				int sc = response.getStatusLine().getStatusCode();
				if (sc == 301 || sc == 302) {
					Header location = response.getFirstHeader("Location");
					if (location != null) {
						abortRequest();

						logger.debug("Redirect(" + (i + 1) + ") to: " + location.getValue());
						request = new HttpGet(location.getValue());
						if (headers != null)
							for (Header h : headers) {
								request.addHeader(h);
							}
						response = client.execute(request);
					}
				} else {
					break;
				}
			}
			return response;
		}

		private int sendRequestAndWriteToStream(OutputStream out) throws IOException {
			int num_bytes = -1;
			InputStream in = null;

			try {
				HttpResponse response = getResponse();
				HttpEntity entity = response.getEntity();

				int code = response.getStatusLine().getStatusCode();
				if (entity != null && code >= 200 && code < 300) {
					in = getInputStream(entity);

					byte[] buf = new byte[reader_buffer_size];
					int len = in.read(buf);
					while (len > 0) {
						num_bytes += len;

						if (out != null) {
							out.write(buf, 0, len);
						}

						len = in.read(buf);
					}

				} else {
					logger.error("Http status error: " + code + ", URL: " + request.getURI());
				}
				return num_bytes;
			} finally {
				IOUtils.close(in);
				IOUtils.close(out);
			}
		}

		public int downToStream(OutputStream out) throws IOException {
			try {
				return sendRequestAndWriteToStream(out);
			} finally {
				abortRequest();
			}

		}

		/**
		 * 续传到文件
		 * 
		 * @param tofile
		 * @return 0-文件已下载完成, <0: 尚未下载完成
		 */
		public long appendToFile(String tofile, DownloadListener dl) {
			File f = new File(tofile);
			if (f.exists()) {
				if (dl != null)
					dl.downloadFinished(tofile);
				return 0;
			}

			File tmpf = new File(tofile + ".tmp");
			File inff = new File(tofile + ".tmf");

			Properties p = PropertyUtils.readToProperties(inff);
			long filelength = Long.parseLong(p.getProperty("File-Length", "" + Long.MIN_VALUE));

			long pos = 0;
			if (tmpf.exists()) {
				pos = tmpf.length();

				if (pos == filelength) {
					tmpf.renameTo(f);
					inff.delete();

					if (dl != null)
						dl.downloadFinished(f.getAbsolutePath());
					return 0;
				}
			}

			if (filelength == Long.MIN_VALUE && pos > 0) {
				tmpf.delete();
				pos = 0;
			}

			try {
				if (pos > 0) {
					request.setHeader("Range", "bytes=" + pos + "-");
				}
				
				HttpResponse response = getResponse();

				if (pos == 0) {
					Header hl = response.getLastHeader("Content-Length");
					if (hl != null) {
						filelength = Long.valueOf(hl.getValue());
					} else {
						logger.error("Unknow file length: " + request.getURI());
					}

					saveDownloadPosition(inff, filelength);
				} else {
					Header hl = response.getLastHeader("Content-Range");
					if (hl != null) {
						String s = hl.getValue();
						int x = s.lastIndexOf("/");
						long flen = Long.parseLong(s.substring(x + 1).trim());

						if (flen != filelength) { // 文件大小发生变化需重新下载
							logger.info("File changed, download refresh!");
							tmpf.delete();
							pos = 0;

							filelength = flen;
							saveDownloadPosition(inff, filelength);
						}
					}
				}

				return down(response, tmpf, inff, f, pos, dl, filelength);
			} catch (Exception e) {
				logger.error("Download error(" + e.getClass().getName() + ") from " + request.getURI() + " To: " + tofile);
				if (dl != null)
					dl.downloadException(tofile, 500, e.getMessage());
				return Long.MIN_VALUE;
			} finally {
				abortRequest();
			}
		}

		private long down(HttpResponse response, File tmpf, File inff, File f, long pos, DownloadListener dl, long totalLength) throws IOException {
			int code = response.getStatusLine().getStatusCode();
			if (code == 200 || code == 206) {
				RandomAccessFile raf = new RandomAccessFile(tmpf, "rw");
				if (pos > 0) {
					if (code != 206) {
						raf.seek(0);
					} else {
						raf.seek(pos);
					}
				}
				long downloadLength = pos;
				String old_percent = getPercentString(downloadLength, totalLength);

				HttpEntity entity = response.getEntity();
				InputStream in = entity.getContent();
				byte[] buf = new byte[reader_buffer_size];
				int len = in.read(buf);
				while (len > 0 && running) {
					raf.write(buf, 0, len);

					downloadLength += len;
					// logger.info("Running: "+running+", bytes: "+downloadLength+"/"+totalLength);
					if (dl != null) {
						String new_percent = getPercentString(downloadLength, totalLength);
						if (!new_percent.equals(old_percent)) {
							old_percent = new_percent;

							dl.downloading(f.getAbsolutePath(), downloadLength, totalLength);
						}
					}

					len = in.read(buf);
				}
				raf.close();
				if (running == false && len > 0) {
					logger.info("Download break!");
					if (dl != null) {
						dl.downloadBreak(f.getAbsolutePath(), downloadLength, totalLength);
					}
					return totalLength - downloadLength;
				} else if (totalLength == downloadLength) {
					tmpf.renameTo(f);
					inff.delete();

					if (dl != null)
						dl.downloadFinished(f.getAbsolutePath());
					return 0;
				} else {
					return downloadLength - totalLength;
				}
			} else {
				String error = "Download error(" + code + ") from " + request.getURI() + " To: " + f.getName();
				logger.error(error);

				if (dl != null)
					dl.downloadException(f.getAbsolutePath(), code, error);
				return Long.MIN_VALUE;
			}
		}

		private void saveDownloadPosition(File inff, long filelength) throws IOException {
			Properties p = new Properties();
			p.setProperty("File-Length", "" + filelength);
			FileOutputStream fos = new FileOutputStream(inff);
			try {
				p.store(fos, "");
			} finally {
				IOUtils.close(fos);
			}
		}

		public boolean downToFile(String tofile) {
			File f = new File(tofile);
			if (f.exists()) {
				return true;
			}

			File tmpf = new File(tofile + ".tmp");
			deleteIfExists(tmpf);
			try {
				OutputStream out = new FileOutputStream(tmpf);
				int num_bytes = sendRequestAndWriteToStream(out);
				if (num_bytes > 0) {
					logger.debug("Download(" + num_bytes + " bytes) from " + request.getURI() + " To: " + tofile);
					tmpf.renameTo(f);
					return true;
				} else {
					deleteIfExists(tmpf);
					return false;
				}
			} catch (Exception e) {
				deleteIfExists(tmpf);

				logger.error("Download error(" + e.getClass().getName() + ") from " + request.getURI() + " To: " + tofile);
				return false;
			} finally {
				abortRequest();
			}
		}

		private void deleteIfExists(File f) {
			if (f.exists()) {
				f.delete();
			}
		}

		protected void abortRequest() {
			if (request != null) {
				request.abort();
				client.getConnectionManager().closeIdleConnections(0,TimeUnit.MILLISECONDS);
			}
		}

		public HttpRequest addHeader(String name, String value) {
			if (value != null) {
				request.addHeader(name, value);
			}
			return this;
		}		  
		
		public HttpRequest setHeader(String name, String value) {
			request.setHeader(name, value);
			return this;
		}
		
		public HttpRequest addCookie(Cookie cookie) {
			CookieStore cs=((DefaultHttpClient)client).getCookieStore();
			cs.addCookie(cookie);			 
			return this;
		}
		 
		public HttpRequest addCookies(Cookie[] cookies) {
			CookieStore cs=((DefaultHttpClient)client).getCookieStore();
			for(Cookie cookie:cookies){
				cs.addCookie(cookie);		
			}
			return this;
		}
		
		public HttpRequest setReferer(String referer) {
			return addHeader("Referer", referer);
		}

		public abstract HttpRequest addParameter(String name, final String value);

		public abstract HttpRequest addFile(File file, String contentType, String name);

		public abstract HttpRequest addStream(final InputStream in, final long length, String contentType, String name);
		
		public abstract HttpRequest setBodyEntity(HttpEntity entity);
	}


}
