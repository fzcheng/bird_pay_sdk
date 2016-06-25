package com.cheyooh.service.framework.utils.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME) 
public @interface Match {
	/**
	 * @return 校验类型(email-电子邮件, mobile-手机号码, lpn-车牌号码, password-密码. 缺省为自定义的正则表达式)
	 */
	String type() default "";
	
	/**
	 * 正则表达式
	 * @return
	 */
	String regex() default "";
	
	/**
	 * 错误提示语句
	 * @return
	 */
	String message() default "";
}
