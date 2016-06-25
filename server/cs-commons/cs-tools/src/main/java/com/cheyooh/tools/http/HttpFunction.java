package com.cheyooh.tools.http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;

import com.cheyooh.tools.log.Logger;
import com.cheyooh.tools.utils.IOUtils;

public class HttpFunction {
	private static Logger logger = new Logger(HttpFunction.class);
	
	/**
	 * 设置method访问的headers信息部分
	 * @param method
	 * @param paramMap
	 */
	public static void setHttpRequestHeaders(HttpMethod method,	HashMap<String, String> headMap) {
		// 剩余内置的对象设置
		if (method != null && headMap != null) {
			String[] keyarray = new String[headMap.size()];
			headMap.keySet().toArray(keyarray);
			for (String key : keyarray) {
				method.setRequestHeader(key, headMap.get(key));
			}
		}
	}
	
	/**
	 * 二维数组列表传递headers信息部分
	 * @param method
	 * @param headList
	 */
	public static void setHttpRequestHeaders(HttpMethod method,ArrayList<String[]> headList) { 
		if (method != null && headList != null) {
			for (String[] sarray : headList) {
				if (sarray.length == 2) {
					method.setRequestHeader(sarray[0], sarray[1]);
				}
			}
		}
	}

	/**
	 * 以hashMap设置post方法传递的body参数部分
	 * @param method
	 * @param paramMap
	 */
	private static void setPostMethodRequestBody(PostMethod method,HashMap<String, String> paramMap) {
		if (method != null && paramMap != null) {
			NameValuePair[] pairarray = new NameValuePair[paramMap.size()];
			String[] keyarray = new String[paramMap.size()];
			paramMap.keySet().toArray(keyarray);
			for (int i = 0; i < keyarray.length; i++) {
				// logger.info("postdata." + keyarray[i] + "=" +
				// paramMap.get(keyarray[i]));
				pairarray[i] = new NameValuePair(keyarray[i],	paramMap.get(keyarray[i]));
			}
			method.setRequestBody(pairarray);
		}
	}
	
	/**
	 * 以二维数组列表设置post方法传递的body参数部分
	 * @param method
	 * @param paramList
	 */
	private static void setPostMethodRequestBody(PostMethod method,ArrayList<String[]> paramList) {
		if (method != null && paramList != null) {
			ArrayList<String[]> filterList = new ArrayList<String[]>();
			for (String[] sarray : paramList) {
				if (sarray.length == 2) {
					filterList.add(sarray);
				}
			}
			NameValuePair[] pairarray = new NameValuePair[filterList.size()];
			for (int i = 0; i < filterList.size(); i++) {
				if (filterList.get(i).length == 2) {
					logger.info("postdata." + filterList.get(i)[0] + "="
							+ filterList.get(i)[1]);
					pairarray[i] = new NameValuePair(filterList.get(i)[0],
							filterList.get(i)[1]);
				}
			}
			method.setRequestBody(pairarray);
		}
	}
	
	/**
	 * 获取网络图片存储（验证码图片获取）
	 * @param client
	 * @param imgUrl
	 * @param headerMap
	 * @param picname
	 * @param picDir
	 * @return
	 * @throws Exception
	 */
	public static void getCityVerifyImageReturn(HttpClient client,String imgUrl,HashMap<String,String> headerMap,String picname,String picDir) throws Exception {
		logger.debug("验证码请求地址信息为 : " + imgUrl);
		GetMethod getMethod = new GetMethod(imgUrl);		
		if(headerMap!= null && headerMap.size() >0){
			setHttpRequestHeaders(getMethod, headerMap);	
		}
		
		client.executeMethod(getMethod);		
		logger.info("获取验证码 : " + getMethod.getStatusLine());
		
		if (getMethod.getStatusCode() == HttpStatus.SC_OK) { // 验证码下载成功后处理
			String imgurl = picDir + File.separator + picname;
			OutputStream outStream = new FileOutputStream(imgurl);
			org.apache.commons.io.IOUtils.copy(getMethod.getResponseBodyAsStream(), outStream);
			outStream.close();
			logger.info("验证码保存成功,访问地址: " + imgurl);			
			getMethod.releaseConnection();
		}else{
			String statusline = getMethod.getStatusLine().toString();
			getMethod.releaseConnection();
			throw new Exception("验证码请求返回非200状态,返回标记 : " + statusline);
		}		
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * POST方法调用
	 * @param client
	 * @param posturl
	 * @param paramter
	 * @param header
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public  static String post20xRT(HttpClient client, String posturl,Object paramter, Object header, String charset) throws Exception {
		if (StringUtils.isEmpty(charset))charset = "gbk";
		logger.info("本次POST请求地址信息为 : " + posturl);
		PostMethod postMethod = new PostMethod(posturl);
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, charset);
		
		/**分析处理param信息*/
		if (paramter != null) {
			if (paramter instanceof HashMap) {
				HashMap<String, String> paramMap = (HashMap<String, String>) paramter;
				setPostMethodRequestBody(postMethod, paramMap);
			} else if (paramter instanceof ArrayList) { // 导入的是2维数组
				ArrayList<String[]> paramList = (ArrayList<String[]>) paramter;
				setPostMethodRequestBody(postMethod, paramList);
			} else {
				logger.info("本次传递的paramter对象组无法解析....");
				throw new Exception("传递的paramter对象组无法解析! ");
			}
		}
		
		/**分析处理header信息*/
		if (header != null) {
			if (header instanceof HashMap) {
				HashMap<String, String> postHeaderMap = (HashMap<String, String>) header;
				setHttpRequestHeaders(postMethod, postHeaderMap);
			} else if (header instanceof ArrayList) { // 导入的是2维数组
				ArrayList<String[]> postHeaderList = (ArrayList<String[]>) header;
				setHttpRequestHeaders(postMethod, postHeaderList);
			} else {
				logger.info("本次传递的header对象组无法解析....");
				throw new Exception("传递的header对象组无法解析! ");
			}
		}
		client.executeMethod(postMethod);
		logger.info("本次POST请求结果页面 : " + postMethod.getStatusLine());

		if (postMethod.getStatusCode() == HttpStatus.SC_OK) {
			  String result = IOUtils.getInputStreamText(postMethod.getResponseBodyAsStream(), charset);
			  postMethod.releaseConnection();
			  return result;
		} else {
			String statusline = postMethod.getStatusLine().toString();
			postMethod.releaseConnection();
			throw new Exception("请求返回非200状态,返回标记 : " + statusline);
		}
	}
	
	/**
	 * POST方法调用
	 * @param client
	 * @param posturl
	 * @param postParamMap
	 * @param postHeaderMap
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public static String multiPartPost20xReturn(HttpClient client,String posturl,InputStream input,
			HashMap<String,String> postHeaderMap,String charset) throws Exception {				
		if(StringUtils.isEmpty(charset))charset="gbk";
		
		logger.info("本次POST请求地址信息为 : " + posturl);		
		PostMethod postMethod = new PostMethod(posturl); 
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,charset);		
		postMethod.setRequestBody(input);
		
		if(postHeaderMap != null && postHeaderMap.size() >0) {
			setHttpRequestHeaders(postMethod, postHeaderMap);	
		}
		client.executeMethod(postMethod);		
		logger.info("本次POST请求结果页面 : " + postMethod.getStatusLine());
		
		if(postMethod.getStatusCode() == HttpStatus.SC_OK){
			String result = IOUtils.getInputStreamText(postMethod.getResponseBodyAsStream(),charset);
			postMethod.releaseConnection();
			return result;
		}else{
			String statusline = postMethod.getStatusLine().toString();
			postMethod.releaseConnection();
			throw new Exception("请求返回非200状态,返回标记 : " +statusline);
		}
	}
	
	
	/**
	 * POST方法调用
	 * @param client
	 * @param posturl
	 * @param postParamMap
	 * @param postHeaderMap
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public static String post20xReturn(HttpClient client,String posturl,HashMap<String,String> postParamMap,
			HashMap<String,String> postHeaderMap,String charset) throws Exception {				
		if(StringUtils.isEmpty(charset))charset="gbk";
		
		logger.info("本次POST请求地址信息为 : " + posturl);		
		PostMethod postMethod = new PostMethod(posturl); 
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,charset);
		
		if(postParamMap != null && postParamMap.size() >0) {
			NameValuePair[] pairarray = new NameValuePair[postParamMap.size()];
			String[] keyarray = new String[postParamMap.size()];
			postParamMap.keySet().toArray(keyarray);		
			for(int i=0;i<keyarray.length;i++){
				logger.info("postdata."+keyarray[i]+"="+postParamMap.get(keyarray[i]));
				pairarray[i]= new NameValuePair(keyarray[i], postParamMap.get(keyarray[i]));
			}
			postMethod.setRequestBody(pairarray);
		}
		
		if(postHeaderMap != null && postHeaderMap.size() >0) {
			setHttpRequestHeaders(postMethod, postHeaderMap);	
		}
		client.executeMethod(postMethod);		
		logger.info("本次POST请求结果页面 : " + postMethod.getStatusLine());
		
		if(postMethod.getStatusCode() == HttpStatus.SC_OK){
			String result = IOUtils.getInputStreamText(postMethod.getResponseBodyAsStream(),charset);
			postMethod.releaseConnection();
			return result;
		}else{
			String statusline = postMethod.getStatusLine().toString();
			postMethod.releaseConnection();
			throw new Exception("请求返回非200状态,返回标记 : " +statusline);
		}
	}
	
	/**
	 * GET方法调用
	 * @param client
	 * @param getturl
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public static String get20xReturn(HttpClient client,String getturl,HashMap<String,String> headerMap,String charset) throws Exception {
		logger.info("本次GET请求地址信息为 : " + getturl);
		GetMethod getMethod = new GetMethod(getturl);
		if(headerMap!= null && headerMap.size() >0){
			setHttpRequestHeaders(getMethod, headerMap);	
		}
		client.executeMethod(getMethod);
		if(StringUtils.isEmpty(charset))charset="gbk";		
		if(getMethod.getStatusCode() == HttpStatus.SC_OK){
			String result = IOUtils.getInputStreamText(getMethod.getResponseBodyAsStream(),charset);
			getMethod.releaseConnection();
			return result;
		}else{
			String statusline = getMethod.getStatusLine().toString();
			getMethod.releaseConnection();
			throw new Exception("请求返回非200状态,返回标记 : " + statusline);
		}		
	}
	
	
	
	/**
	 * POST方法请求执行逻辑步骤
	 * @param client         公用client对象
	 * @param posturl        post地址信息
	 * @param paramMap       post参数组信息
	 * @param charset        编码
	 * @param succflag       post处理请求成功记录标记
	 * @param errflag        post处理请求失败记录标记
	 * @return
	 * @throws Exception
	 */
	public static String[] postSelfLogic(HttpClient client,String posturl,HashMap<String,String> postParamMap,
			HashMap<String,String> postHeaderMap,HashMap<String,String> turnHeaderMap,
			String charset,String succflag,String errflag,String city) throws Exception {	
		if(StringUtils.isEmpty(charset))charset="gbk";		
		logger.info("本次POST请求地址信息为 : " + posturl);	
		PostMethod postMethod = new PostMethod(posturl); 
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,charset);
		
		NameValuePair[] pairarray = new NameValuePair[postParamMap.size()];
		
		String[] keyarray = new String[postParamMap.size()];
		postParamMap.keySet().toArray(keyarray);		
		for(int i=0;i<keyarray.length;i++){
			logger.info("postdata."+keyarray[i]+"="+postParamMap.get(keyarray[i]));
			pairarray[i]= new NameValuePair(keyarray[i], postParamMap.get(keyarray[i]));
		}
		
		postMethod.setRequestBody(pairarray);
		//模拟访问访问的header部分
		setHttpRequestHeaders(postMethod, postHeaderMap);	
		client.executeMethod(postMethod);
		
		logger.info("本次POST请求结果页面 : " + postMethod.getStatusLine());
		
		if(postMethod.getStatusCode() != HttpStatus.SC_MOVED_PERMANENTLY &&
				postMethod.getStatusCode() != HttpStatus.SC_MOVED_TEMPORARILY	){
			//String resultHtml = IoUtils.getInputStreamText(postMethod.getResponseBodyAsStream(),charset);
			postMethod.releaseConnection();
			throw new Exception("Return Not 30x redirect ...");
		}
		
		//返回的是30X的处理逻辑
		String[] result = postTurn30XLogic(client,postMethod,turnHeaderMap,charset);
		postMethod.releaseConnection();		
		return result;
	}
	
	/**
	 * POST请求操作后302跳转逻辑处理
	 * @param postMethod
	 * @throws Exception
	 */
	private static String[] postTurn30XLogic(HttpClient client, PostMethod postMethod,
			HashMap<String, String> turnHeaderMap,String charset) throws Exception {
		String[] result = new String[2]; // 0-resultHtml;1-locationuri
		if (postMethod.getStatusCode() == HttpStatus.SC_MOVED_PERMANENTLY
				|| postMethod.getStatusCode() == HttpStatus.SC_MOVED_TEMPORARILY) {
			postMethod.releaseConnection();
			// 返回代码301或者302，从返回的Headers信息中获取Location信息处理
			String locationuri = "";
			Header[] header_array = postMethod.getResponseHeaders();
			for (Header header : header_array) {
				if (header.getName().equalsIgnoreCase("location")) {
					locationuri = header.getValue();
				}
			}
			logger.info("30X 跳转URL为 : " + locationuri);
			if (StringUtils.isEmpty(locationuri)) {
				throw new Exception("30X的返回headers中无法获取到location项值......");
			} else {
				if (!locationuri.toLowerCase().startsWith("http://")) {// 如果传递的是相对地址的话
					String host = turnHeaderMap.get("Host");
					locationuri = "http://"+ host + locationuri;
				}				
				logger.info("30X 跳转绝对URL为 : " + locationuri);				
				result[1] = locationuri;
				HttpMethod getMethod3 = new GetMethod(locationuri);

				// 模拟设置访问的header信息
				setHttpRequestHeaders(getMethod3, turnHeaderMap);
				client.executeMethod(getMethod3);
				result[0] = IOUtils.getInputStreamText(getMethod3.getResponseBodyAsStream(),charset);
				getMethod3.releaseConnection();
				return result;
			}
		} else {
			throw new Exception("返回处理非30X系列【" + postMethod.getStatusCode()	+ "】,暂不支持处理...");
		}
	}
}
