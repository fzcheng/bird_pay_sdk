/**
 * 
 */
package com.cheyooh.service.sdk.tools;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * @author Merlin
 * 
 */
public class Hmacsha1 {
  private static final String MAC_NAME = "HmacSHA1";
  private static final String ENCODING = "UTF-8";

  public static String sign(String encryptText, String encryptKey) throws Exception {
    byte[] data = encryptKey.getBytes(ENCODING);
    SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
    Mac mac = Mac.getInstance(MAC_NAME);
    mac.init(secretKey);

    byte[] text = encryptText.getBytes(ENCODING);
    byte[] signBytes = mac.doFinal(text);
    
    return Base64.encodeBase64String(signBytes);
  }
}
