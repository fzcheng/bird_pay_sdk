package com.legame.paysdk.utils;

import java.io.InputStream;
import java.io.OutputStream;


/** 
 * 类说明：   
 * @author  xinhui.cheng
 * @date    2013-4-7
 * @version 1.0
 */
public class IOTools {

	public static void copyStream(InputStream is, OutputStream os) {
		final int buffer_size = 1024 * 256;
		try {
			byte[] bytes = new byte[buffer_size];
			while (true) {
				int count = is.read(bytes, 0, buffer_size);
				if (count == -1)
					break;
				os.write(bytes, 0, count);
			}
		} catch (Exception ex) {
			// Ignore any Exception
		}
	}
	
}
