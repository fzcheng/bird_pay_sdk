package org.jeecgframework.core.annotation.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)   
@Target(ElementType.METHOD)
/**
 * 菜单注释标签
 * 系统启动自动加载菜单配置
 */
public @interface MenuConfig {
	
	/**
	 * 菜单名称
	 * @return
	 */
	public String name();
	/**
	 * 等级
	 * @return
	 */
	public String level();
	/**
	 * 菜单地址
	 * @return
	 */
	public String url();
	/**
	 * 图标
	 * @return
	 */
	public String icon();
	/**
	 * 顺序
	 * @return
	 */
	public int index();
}


