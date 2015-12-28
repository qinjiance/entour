package com.qinjiance.tourist.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author "Jiance Qin"
 * 
 * @date 2014年7月17日
 * 
 * @time 下午3:30:53
 * 
 * @desc
 * 
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface NeedCookie {

	/**
	 * 浏览器请求
	 * 
	 * @return
	 */
	boolean isNeed() default true;

}
