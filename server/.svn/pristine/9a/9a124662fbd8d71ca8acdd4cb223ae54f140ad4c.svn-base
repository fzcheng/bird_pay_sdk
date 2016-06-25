
package com.cheyooh.service.sdk.tools;

import java.util.Map;

/**
 * 华为SDK回调工具类
 * @author ljg
 *
 */
public abstract class CommonUtil
{
	
    public static boolean rsaDoCheck(Map<String, Object> params, String sign, String publicKey)
    {
        //获取待签名字符串
        String content = RSA.getSignData(params);
        //验签
        return RSA.doCheck(content, sign, publicKey);
    }
    
}
