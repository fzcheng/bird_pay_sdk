/**
 * 
 */
package test.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @author Merlin
 * 
 */
public class Md5Test {

  /**
   * 
   */
  public Md5Test() {
    // TODO Auto-generated constructor stub
  }

  public static String leftTrim(String str, char trim) {
    char[] vals = str.toCharArray();
    int pos = 0;
    while (pos < vals.length && vals[pos] == trim) {
      pos++;
    }
    return pos > 0 || pos < vals.length ? str.substring(pos) : str;
  }
  
  public static void main(String[] args) {
    System.out.println(DigestUtils.md5Hex("新手3839409&3839409&f3de9dae003e46c8bcdf75d7904418ee"));
    String str = "091000011";
    System.out.println(StringUtils.leftPad("100", 8, '0'));
  }
}
