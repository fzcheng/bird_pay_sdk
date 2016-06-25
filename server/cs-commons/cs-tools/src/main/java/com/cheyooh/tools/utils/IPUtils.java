package com.cheyooh.tools.utils;

public class IPUtils {
	/**
	 * IP转化为long
	 * @param strip
	 * @return
	 */
	public static long ip2Long(String strip) {
		String[] ip = strip.split("\\.");
		if (ip.length == 4) {
			// ip1*256*256*256+ip2*256*256+ip3*256+ip4
			return (Long.parseLong(ip[0]) << 24)
					+ (Long.parseLong(ip[1]) << 16)
					+ (Long.parseLong(ip[2]) << 8) + Long.parseLong(ip[3]); 
		} else {
			return 0;
		}
	}

	/**
	 * long型转化为ip
	 * @param longip
	 * @return
	 */
	public static String long2IP(long longip) {
		// 将10进制整数形式转换成127.0.0.1形式的ip地址，在命令提示符下输入ping 3396362403l
		StringBuffer sb = new StringBuffer("");
		sb.append(String.valueOf(longip >>> 24));// 直接右移24位
		sb.append(".");
		sb.append(String.valueOf((longip & 0x00ffffff) >>> 16)); // 将高8位置0，然后右移16位
		sb.append(".");
		sb.append(String.valueOf((longip & 0x0000ffff) >>> 8));
		sb.append(".");
		sb.append(String.valueOf(longip & 0x000000ff));
		sb.append(".");
		return sb.toString();
	}
}
