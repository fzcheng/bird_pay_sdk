package com.legame.paysdk.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/** 
 * 类说明：   
 * @author  Terry.Lu
 * @date    2013年10月15日
 * @version 1.0
 */
public class CryptoUtil {
	private static final String ALGORITHM = "AES";
	public static String SERVICE_KEY="SD8G6ASDG87S6S8D";
	public static String PRIVATE_KEY = "1123J87SDWFDSFS2";
	
	/**
	 * 加密
	 */
	public static String encrypt(String content , String sKey) {
		try {
			SecretKeySpec secretKey = new SecretKeySpec(sKey.getBytes(), ALGORITHM);
			Cipher cipher = Cipher.getInstance(ALGORITHM);// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return parseByte2HexStr(result); // 加密
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String encrypt(String content) {
		try {
			SecretKeySpec secretKey = new SecretKeySpec(PRIVATE_KEY.getBytes(), ALGORITHM);
			Cipher cipher = Cipher.getInstance(ALGORITHM);// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return parseByte2HexStr(result); // 加密
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 解密
	 * 
	 * @param content 加密的数据
	 * @param sKey
	 * @return
	 */
	public static String decrypt(String content , String sKey) {
		try {
			byte[] data = parseHexStr2Byte(content);
			SecretKeySpec key = new SecretKeySpec(sKey.getBytes(), ALGORITHM);
			Cipher cipher = Cipher.getInstance(ALGORITHM);// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(data);
			return new String(result); // 解密
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**将二进制转换成16进制 
	 * @param buf 
	 * @return 
	 */  
	private static String parseByte2HexStr(byte buf[]) {  
	        StringBuffer sb = new StringBuffer();  
	        for (int i = 0; i < buf.length; i++) {  
	                String hex = Integer.toHexString(buf[i] & 0xFF);  
	                if (hex.length() == 1) {  
	                        hex = '0' + hex;  
	                }  
	                sb.append(hex.toUpperCase());  
	        }  
	        return sb.toString();  
	}
	
	/**将16进制转换为二进制 
	 * @param hexStr 
	 * @return 
	 */  
	private static byte[] parseHexStr2Byte(String hexStr) {  
	        if (hexStr.length() < 1)  
	                return null;  
	        byte[] result = new byte[hexStr.length()/2];  
	        for (int i = 0;i< hexStr.length()/2; i++) {  
	                int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);  
	                int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);  
	                result[i] = (byte) (high * 16 + low);  
	        }  
	        return result;  
	}
	
//	public static void main(String[] args) {
//	String pwd = "12345";
//	String encryptValue = encrypt(pwd , mSKey);
//	System.out.println("encryptValue："+encryptValue);
//	pwd = decrypt(encryptValue , mSKey);
//	System.out.println("pwd:"+pwd);
//	
//}
	
}
