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
 * @time 下午3:31:06
 * 
 * @desc
 * 
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface SkipWhenUserLogin {

	/**
	 * 当用户已登录时，跳过该处理流程.
	 * 
	 * @return
	 */
	boolean isNeed() default true;

}
