package com.cheyooh.tools.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.cheyooh.tools.log.Logger;

/**
 * @author zzg.zhou@mbook.com.cn {MSN:zzgzhou@hotmail.com, QQ:11039850}
 * 
 */
public class WinCmd {
	static Logger logger = new Logger(WinCmd.class);

	/**
	 * 执行CMD命令,并返回String字符串
	 */
	public static String executeCmd(String strCmd) {
		logger.debug("EXE CMD: "+strCmd);
		try {
			Process p = Runtime.getRuntime().exec("cmd /c " + strCmd);
			StringBuilder sb = new StringBuilder();
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(),"GBK"));
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}

			return sb.toString();
		} catch (Exception e) {
			logger.error("Error cmd: "+strCmd,e);
			return null;
		}
	}

	/**
	 * 分析ipconfig 命令输出:
	 * 
	 * 
	 * PPP 适配器 HW_3G_02:
	 * 
	 * 连接特定的 DNS 后缀 . . . . . . . :
	 *  
	 *   IPv4 地址 . . . . . . . . . . . . : 113.112.26.79 
	 *   子网掩码 . . . . . . . . . . . . : 255.255.255.255 
	 *   默认网关. . . . . . . . . . . . . : 0.0.0.0
	 * 
	 * PPP 适配器 HW_3G_01:
	 * 
	 *   连接特定的 DNS 后缀 . . . . . . . : 
	 *   IPv4 地址 . . . . . . . . . . . . : 183.40.82.138
	 *   子网掩码 . . . . . . . . . . . . : 255.255.255.255 
	 *   默认网关. . . . . . . . . . . . . : 0.0.0.0
	 *   
	 */
	public static Map<String, Map<String, String>> getIpconfig() {
		Map<String, Map<String, String>> hIpconfigs = new LinkedHashMap<String, Map<String, String>>();
		String r = executeCmd("ipconfig");
		if (r != null) {
			BufferedReader reader = new BufferedReader(new StringReader(r.trim()));
			try {
				String line = reader.readLine();
				while (line != null) {
					if (StringUtils.isNotBlank(line)) {
						char c = line.charAt(0);

						if (!Character.isWhitespace(c)) {
							// 新项目
							Map<String, String> sfg = new LinkedHashMap<String, String>();
							hIpconfigs.put(line.trim(), sfg);

							// 读取子条目
							line = reader.readLine();
							while (line != null) {
								if (StringUtils.isNotBlank(line)) {
									int p = line.indexOf(":");
									String sk = line.substring(0, p).trim();
									String sv = line.substring(p + 1).trim();
									while (true) {
										if(sk.endsWith(". ")){
											sk = sk.substring(0, sk.length() - 2);
										}else if(sk.endsWith(".")){
											sk = sk.substring(0, sk.length() - 1);
										}else{
											break;
										}
									}
									sk=sk.trim();
									sfg.put(sk, sv);
								}

								line = reader.readLine();
								if (StringUtils.isBlank(line)) {
									break;
								}
							}
						}
					}

					if (line != null) {
						line = reader.readLine();
					}
				}
			} catch (Exception e) {
				logger.error(e);
			} finally {
				IOUtils.close(reader);
			}
		}
		return hIpconfigs;
	}
	
	public static void main(String[] args) {
		StringBuilder sb=new StringBuilder();	
		Map<String, Map<String, String>> cfgs=getIpconfig();
		
		for(String key:cfgs.keySet()){
			if(sb.length()>0){
				sb.append("\r\n\r\n");
			}
			sb.append(key+"\r\n");
			Map<String, String> cfg=cfgs.get(key);
			for(String sk:cfg.keySet()){
				sb.append("  "+sk+": "+cfg.get(sk)).append("\r\n");
			}
		}
		logger.info("ipconfig:\r\n"+sb);
	}
}
