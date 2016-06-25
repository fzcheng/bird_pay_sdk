package com.cheyooh.tools.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.cheyooh.tools.log.Logger;


public class IOUtils {
	static Logger logger = new Logger(IOUtils.class);	
	/**
	 * 从一个输入流中根据一定的编码方式读取出内容文本
	 * 
	 * @param in
	 *            InputStream，输入流
	 * @param encode
	 *            String，编码方式
	 * @return String 内容文本
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public static String getInputStreamText(InputStream in, String encode)	throws UnsupportedEncodingException, IOException {
		StringBuffer contentBuf = new StringBuffer();
		Reader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(in, encode));
			char[] buf = new char[1024];
			int count = -1;
			while (true) {
				count = reader.read(buf);
				if (count == -1) {
					break;
				}
				contentBuf.append(buf, 0, count);
			}
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception ex) {

				}
			}
		}
		return contentBuf.toString();
	}
	
	public static List<String> getFileContentByLine(String fileName, String encode) throws IOException {
		List<String> contentList = new ArrayList<String>();
		File file = new File(fileName);
		if (!file.exists()){ 	return null; }
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encode));
			String line = null;
			while((line=reader.readLine()) != null) {  
				contentList.add(line);
			}
		}finally{
			if (reader != null) reader.close();
		}
		return contentList;
	}
	
	public static String getFileContent(String fileName, String encode) throws IOException {
		File file = new File(fileName);
		if (!file.exists())
			return null;
		Reader reader = null;
		StringBuffer contentBuf = new StringBuffer();
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encode));
			char[] buf = new char[1024];
			int count = -1;
			while (true) {
				count = reader.read(buf);
				if (count == -1) {
					break;
				}
				contentBuf.append(buf, 0, count);
			}
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception ex) {

				}
			}
		}
		return contentBuf.toString();
	}

	public static void writeToFile(String filename, byte[] contents,
			boolean isappend) throws IOException {
		OutputStream out = null;
		try {
			out = new BufferedOutputStream(new FileOutputStream(filename,isappend));
			out.write(contents);
			out.flush();
		} finally {
			if (out != null)
				out.close();
		}
	}

	public static void close(Closeable c) {
		if (c != null) {
			try {
				c.close();
			} catch (Exception e) {
				logger.error("" + e, e);
			}
		}
	}

	public static void close(InputStream in) {
		if (in != null) {
			try {
				in.close();
			} catch (Exception e) {
				logger.error("" + e, e);
			}
		}
	}

	public static void close(OutputStream out) {
		if (out != null) {
			try {
				out.close();
			} catch (Exception e) {
				logger.error("" + e, e);
			}
		}
	}

}
