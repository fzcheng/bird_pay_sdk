package com.legame.paysdk.network.engine;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import com.legame.paysdk.ErrorCode;
import com.legame.paysdk.GlobalVal;
import com.legame.paysdk.models.ErrorCodelnfo;
import com.legame.paysdk.network.engine.cachestrategy.CacheStrategy;
import com.legame.paysdk.network.resultdata.BaseResultData;
import com.legame.paysdk.network.utils.NetTools;
import com.legame.paysdk.utils.Constants;
import com.legame.paysdk.utils.LogUtil;

/**
 * 类说明：
 * 
 * @author Terry.Lu
 * @date 2013-5-15
 * @version 1.0
 */
public abstract class BaseNetEngine {
	public static final int HTTP_GET = 0;
	public static final int HTTP_POST = 1;
	public static final int HTTP_BOUNDARY = 2;

	protected static final String BOUNDARY = "----WebKitFormBoundaryMUvOZK7PK9dJE0vy";
	protected static final String PREFIX = "--";
	protected final String LINEND = "\r\n";

	private static final String TAG = "BaseNetEngine";
	private static final int TIME_OUT = 60 * 1000; // 1 min

	// 以下变量定义为protected，目的是为了可以在子类中直接使用，从而简化上层逻辑
	protected int mHttpMethod = HTTP_GET;
	protected boolean mIsConnectToOurServer = true;
	protected BaseResultData mResultData = null;
	protected CacheStrategy mCacheStrategy = null;

	private HttpURLConnection mHttpConn = null;
	private boolean mIsGetNetDataCanceled = false;

	/**
	 * 获取联网后的结果； 子类可以通过重载本函数实现返回不同的结果，比如在违章查询的时候，可能的结果有4种；
	 * 一般需要同时重载getNetData函数，来配合使用，以返回不同的结果
	 * */
	public BaseResultData getResultData() {
		return mResultData;
	}

	/**
	 * 取消联网获取数据
	 * */
	public void cancelGetNetData() {
		mIsGetNetDataCanceled = true;
		if (mHttpConn != null) {
			mHttpConn.disconnect();
		}

		if (mResultData != null) {
			mResultData.setParseCanceled(true);
		}
	}

	/**
	 * 获取网络数据
	 * */
	public int getNetData(Context c) {
		if (mCacheStrategy == null) {
			mCacheStrategy = new CacheStrategy(false, null);
		}

		File file = new File(mCacheStrategy.getCacheDir());

		if (!file.exists()) {
			file.mkdirs();
		}

		if (mResultData == null) {
			throw new RuntimeException(
					"mResultData is null, you should init it first");
		}

		mIsGetNetDataCanceled = false;
		mResultData.setParseCanceled(false);

		if (mCacheStrategy.isCacheable() && mCacheStrategy.getUrl() == null) {
			String paramsStr = getParamsData(c);
			int sidParamStartPos = paramsStr.indexOf("&sid");
			
			if (sidParamStartPos > 0) {
				int sidParamsEndPos = paramsStr.indexOf("&", sidParamStartPos + 1);
				if (sidParamsEndPos == -1) {
					sidParamsEndPos = paramsStr.length();
				}
				StringBuilder sb = new StringBuilder(paramsStr);
				paramsStr = sb.delete(sidParamStartPos, sidParamsEndPos).toString();
			}
			
			int csParamStartPos = paramsStr.indexOf("&checksign");
			
			if (csParamStartPos > 0) {
				int csParamEndPos = paramsStr.indexOf("&", csParamStartPos + 1);
				if (csParamEndPos == -1) {
					csParamEndPos = paramsStr.length();
				}
				StringBuilder sb = new StringBuilder(paramsStr);
				paramsStr = sb.delete(csParamStartPos, csParamEndPos).toString();
			}
			
//			LogUtil.d(TAG, "cache url:" + paramsStr);
			mCacheStrategy.setUrl(getHttpUrl(c) + paramsStr);
		}

		InputStream is = null;
		try {
			if (mCacheStrategy.isCacheable()) {
				if (mCacheStrategy.isNeedToDoNetWork()) {
					if ((is = doHttpDownload(c)) == null) {
						return ErrorCode.ERROR_FAIL;
					}

					mCacheStrategy.saveContentToCacheFile(is);
					is = mCacheStrategy.getInputStreamFromCahceFile();
				} else {
					is = mCacheStrategy.getInputStreamFromCahceFile();
				}
			} else {
				is = doHttpDownload(c);
			}

			if (is == null) {
				return ErrorCode.ERROR_FAIL;
			}

			if (mIsGetNetDataCanceled) {
				return ErrorCode.ERROR_FAIL;
			}

//			LogUtil.i(TAG, "parse data..");
			int error = mResultData.parseData(is);
			
			if (error != ErrorCode.ERROR_SUCCESS) {
				LogUtil.e(TAG, "parse XML error...");
				mCacheStrategy.deleteCahceFile();// 解析错误后，则删除文件，防止服务器出错后，一直无法正确解析
			}

			is.close();
			return error;
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.w(TAG, "some errors....");
			return ErrorCode.ERROR_FAIL;
		}
	}

	protected String getCommand() {
		// 这里什么也不做，具体需要子类自己实现
		return null;
	}

	/**
	 * 需要加sid的接口，需要覆盖此方法
	 * @param newSid
	 */
	public void onSidRefreshed(String newSid) {
		
	}
	
	private String getHttpUrl(Context c) {
		String command = getCommand();
		
		if (command == null) {
			return null;
		}

		String url = NetTools.getServerUrl();
		url += "?m=" + command;

		return url;
	}

	/**
	 * Http参数，除了m、固定参数和签名数据以外的其他参数
	 * 
	 */
	protected abstract Map<String, String> getParams(Context c);
	
	private String getParamsData(Context c) {
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("m", getCommand());
		paramsMap.put("appkey", GlobalVal.getAppId(c));
		paramsMap.put("version", NetTools.getVersionName(c));
		
		String channel = NetTools.getChannelNumber(c);
		paramsMap.put("channel", channel);
		paramsMap.put("sdkver", Constants.SDK_VERSION);
		Map<String, String> otherParamsMap = getParams(c);
		
		if (otherParamsMap != null) {
			paramsMap.putAll(otherParamsMap);
		}
		
		String paramsStr[] = sortConstructVars(paramsMap);
		String sign = md5(paramsStr[1] + "&" + NetTools.getSecrectKey());
		paramsStr[0] += "&checksign=" + sign;
//		Log.i(TAG,"params:" + paramsStr[0]);
//		LogUtil.d(TAG, "params:" + paramsStr[0]);
		return paramsStr[0];
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String[] sortConstructVars(Map<String, String> orig_map) {
		String[] s = new String[2];
		Map.Entry entry;
		Iterator itr;
		boolean first_param;
		
		List list = new LinkedList(orig_map.entrySet());
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getKey().toString())
						.compareTo(((Map.Entry) (o2)).getKey().toString());
			}
		});

		first_param = true;
		for (itr = list.iterator(); itr.hasNext();) {
			entry = (Map.Entry) itr.next();
			if ((entry.getKey().toString().length() > 0)
					&& (entry.getValue().toString().length() > 0)) {
				if (first_param) {
					s[1] = entry.getValue().toString();
					first_param = false;
					
					if (mHttpMethod == HTTP_POST && "m".equalsIgnoreCase(entry.getKey().toString())) {
						continue;
					}
					
					s[0] = entry.getKey().toString();
					s[0] += "=";
					s[0] += Uri.encode(entry.getValue().toString());
				} else {
					s[1] += "&";
					s[1] += entry.getValue().toString();
					
					if (mHttpMethod == HTTP_POST && "m".equalsIgnoreCase(entry.getKey().toString())) {
						continue;
					}
					s[0] += "&";
					s[0] += entry.getKey().toString();
					s[0] += "=";
					s[0] += Uri.encode(entry.getValue().toString());
				}
			}

		}

		return s;
	}
	protected void writeHttpPostBoundaryData(Context c, DataOutputStream dos)
			throws Exception {
		// 这里什么也不做，具体需要子类自己实现
	}

	protected InputStream httpGet(Context c) {
		URL url = null;
		int retryTimes = 3;
		String requestUrl = getHttpUrl(c);
//		LogUtil.i(TAG, "httpGet requesturl:" + requestUrl);
//		Log.i(TAG,"httpGet requesturl:" + requestUrl);
		if (requestUrl == null) {
			return null;
		}
		
		int pos = requestUrl.indexOf("?");
		requestUrl = requestUrl.substring(0, pos + 1);
		requestUrl += getParamsData(c);
		
		try {
			while (retryTimes > 0) {
				url = new URL(requestUrl);
				if (NetTools.isCmwap(c)) {
					LogUtil.i(TAG, "use cmwap...");
					mHttpConn = NetTools.getCmwapConnect(requestUrl);
				} else {
					mHttpConn = (HttpURLConnection) url.openConnection();
				}

				mHttpConn.setConnectTimeout(TIME_OUT);
				mHttpConn.setReadTimeout(TIME_OUT);
				NetTools.setCommonHttpHeader(mHttpConn);
				mHttpConn
						.setRequestProperty("Accept-Encoding", "gzip, deflate");
				mHttpConn.setRequestMethod("GET");

				int statusCode = mHttpConn.getResponseCode();
				if (statusCode != 200) {
					LogUtil.w(TAG, "httpGet statusCode error:" + statusCode);

					ErrorCodelnfo info = new ErrorCodelnfo();
					info.setErrorCode(statusCode);
					return null;
				}

				Map<String, String> header = NetTools
						.getHttpResponseHeader(mHttpConn);

				// 确定是自己的服务器下发的内容(有可能在使用CMWAP的时候出现移动服务器下发错误的内容)
				if (mIsConnectToOurServer) {
					String source = header.get("content-source");
					LogUtil.d(TAG, "content-source:" + source);

					if (source == null || !source.equals("www.txsb.com")) {
						LogUtil.i(TAG,
								"content-source is error and retry time="
										+ retryTimes);
						retryTimes--;
						Thread.sleep(1000);
						continue;
					}
				}

				InputStream is = mHttpConn.getInputStream();
				String gZipValue = header.get("content-encoding");
				if (gZipValue != null && gZipValue.indexOf("gzip") >= 0) {
					LogUtil.i(TAG, "httpGet Gzip Stream");
					is = new GZIPInputStream(is);
				}
				return is;
			}

		} catch(IOException e){   
	        LogUtil.i(TAG,"域名异常");
//	        NetTools.gIsDomainFailure = true; 
//			String strData = ListUrlInfo.getListUrl(c);
////			Log.i(TAG,"获取本地缓存的URL列表："+strData);
//			try {
//				JSONObject jsonObject = new JSONObject(strData.toString());
////				Log.i(TAG,"获取本地缓存的URL列表2："+jsonObject.toString());
//				NetTools.gListURLinfo  = UrlInfoParse.parseArray(jsonObject.getString("uInfo"));
////				if(NetTools.gTryIndex < NetTools.gListURLinfo.size()+1)
////				Log.i(TAG,"获取本地缓存的URL列表3："+NetTools.gListURLinfo.get(NetTools.gTryIndex).getDomain());
//			} catch (JSONException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (Exception e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.w(TAG, "httpGet error:" + e.toString());
		}
		return null;
	}

	protected InputStream httpPost(Context c) {
		int retryTimes = 3;
		String requestUrl = getHttpUrl(c);
		if (requestUrl == null) {
			return null;
		}

		LogUtil.i(TAG, "httpPost requesturl:" + requestUrl);
//		Log.i(TAG,"httpPost requesturl:" + requestUrl);
		try {
			while (retryTimes > 0) {
				URL url = new URL(requestUrl);
				if (NetTools.isCmwap(c)) {
					mHttpConn = NetTools.getCmwapConnect(requestUrl);
				} else {
					mHttpConn = (HttpURLConnection) url.openConnection();
				}

				mHttpConn.setDoOutput(true);
				mHttpConn.setDoInput(true);
				mHttpConn.setConnectTimeout(TIME_OUT);
				mHttpConn.setReadTimeout(TIME_OUT);
				mHttpConn.setRequestMethod("POST");
				NetTools.setCommonHttpHeader(mHttpConn);
				mHttpConn
						.setRequestProperty("Accept-Encoding", "gzip, deflate");
				mHttpConn.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded;charset=UTF-8");

				DataOutputStream out = new DataOutputStream(
						mHttpConn.getOutputStream());
				String content = getParamsData(c);
				out.write(content.getBytes("UTF-8"));
				out.flush();
				out.close();

				int statusCode = mHttpConn.getResponseCode();
				if (statusCode != 200) {
					LogUtil.w(TAG, "httpPost statusCode error:" + statusCode);

					ErrorCodelnfo info = new ErrorCodelnfo();
					info.setErrorCode(statusCode);
					return null;
				}

				Map<String, String> header = NetTools
						.getHttpResponseHeader(mHttpConn);

				// 确定是自己的服务器下发的内容(有可能在使用CMWAP的时候出现移动服务器下发错误的内容)
				if (mIsConnectToOurServer) {
					String source = header.get("content-source");
					LogUtil.d(TAG, "content-source:" + source);

					if (source == null || !source.equals("www.txsb.com")) {
						LogUtil.i(TAG,
								"content-source is error and retry time="
										+ retryTimes);
						retryTimes--;
						Thread.sleep(1000);
						continue;
					}
				}

				InputStream is = mHttpConn.getInputStream();
				String gZipValue = header.get("content-encoding");
				if (gZipValue != null && gZipValue.indexOf("gzip") >= 0) {
					LogUtil.i(TAG, "httpPost Gzip Stream");
					is = new GZIPInputStream(is);
				}
				return is;
			}

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.w(TAG, "httpPost error:" + e.toString());
		}
		return null;
	}

	protected InputStream httpPostBoundary(Context c) {
		int retryTimes = 3;
		String requestUrl = getHttpUrl(c);
		if (requestUrl == null) {
			return null;
		}

		LogUtil.i(TAG, "httpPostBoundary requesturl:" + requestUrl);

		try {
			while (retryTimes > 0) {
				URL url = new URL(requestUrl);
				if (NetTools.isCmwap(c)) {
					mHttpConn = NetTools.getCmwapConnect(requestUrl);
				} else {
					mHttpConn = (HttpURLConnection) url.openConnection();
				}
				mHttpConn.setConnectTimeout(TIME_OUT);
				mHttpConn.setReadTimeout(TIME_OUT);
				mHttpConn.setDoInput(true);
				mHttpConn.setDoOutput(true);
				mHttpConn.setUseCaches(false);
				mHttpConn.setRequestMethod("POST");
				mHttpConn.setRequestProperty("connection", "keep-alive");
				mHttpConn.setRequestProperty("Content-Type",
						"multipart/form-data;boundary=" + BOUNDARY);
				mHttpConn.setRequestProperty("Accept-Charset",
						"GBK,utf-8;q=0.7,*;q=0.3");
				mHttpConn.setRequestProperty("Accept-Encoding",
						"gzip,deflate,sdch");
				mHttpConn
						.setRequestProperty("Accept",
								"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
				mHttpConn
						.setRequestProperty(
								"User-Agent",
								"Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/14.0.835.163 ");

				DataOutputStream dos = new DataOutputStream(
						mHttpConn.getOutputStream());
				writeHttpPostBoundaryData(c, dos);

				// end data
				byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINEND)
						.getBytes();
				dos.write(end_data);
				dos.flush();

				int statusCode = mHttpConn.getResponseCode();
				if (statusCode != 200) {
					LogUtil.w(TAG, "httpPostBoundary statusCode error:"
							+ statusCode);
					ErrorCodelnfo info = new ErrorCodelnfo();
					info.setErrorCode(statusCode);
					return null;
				}
				Map<String, String> header = NetTools
						.getHttpResponseHeader(mHttpConn);

				if (mIsConnectToOurServer) {
					// 确定是自己的服务器下发的内容(有可能在使用CMWAP的时候出现移动服务器下发错误的内容)
					String source = header.get("content-source");
					LogUtil.d(TAG, "content-source:" + source);

					if (source == null || !source.equals("www.txsb.com")) {
						LogUtil.i(TAG,
								"content-source is error and retry time="
										+ retryTimes);
						retryTimes--;
						Thread.sleep(1000);
						continue;
					}
				}

				InputStream is = mHttpConn.getInputStream();
				String gZipValue = header.get("content-encoding");

				if (gZipValue != null && gZipValue.indexOf("gzip") >= 0) {
					LogUtil.i(TAG, "httpPostBoundary Gzip Stream");
					is = new GZIPInputStream(is);
				}

				return is;
			}

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.w(TAG, "httpPostBoundary error:" + e.toString());
		}

		return null;
	}

	protected String md5(String str) {
		if (str == null) {
			return null;
		}
		return md5(str, "utf-8");
	}

	private String md5(String str, String encodingType) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		try {
			md5.update(str.getBytes(encodingType));
		} catch (UnsupportedEncodingException e) {
			md5.update(str.getBytes());
		}

		byte[] md5Bytes = md5.digest();

		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	private InputStream doHttpDownload(Context c) {
		InputStream is = null;
		if (mHttpMethod == HTTP_GET) {
			is = httpGet(c);
		} else if (mHttpMethod == HTTP_POST) {
			is = httpPost(c);
		} else {
			is = httpPostBoundary(c);
		}
		return is;
	}

	protected String appendUrlParam(String url, String key, String value) {
		if (!TextUtils.isEmpty(value)) {
			if (url.indexOf("?") > 0) {
				url += "&" + key + "=" + value;
			} else {
				url += "?" + key + "=" + value;
			}
		}
		return url;
	}
}
