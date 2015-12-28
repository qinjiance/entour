/**
 * 
 */
package com.qinjiance.tourist.util;

import module.laohu.commons.util.SecurityUtil;

import org.apache.commons.lang.RandomStringUtils;

/**
 * @author "Jiance Qin"
 * 
 * @date 2014年7月4日
 * 
 * @time 下午6:16:50
 * 
 * @desc
 * 
 */
public class RandomUtil {

	/**
	 * 多级唯一识别码
	 * 
	 * @return
	 */
	public static String getUUID(int level) {
		if (level <= 0) {
			level = 1;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < level; i++) {
			sb.append(SecurityUtil.getUUID());
		}
		return sb.toString();
	}

	/**
	 * @return
	 */
	public static String getRandomNumeric6() {
		return RandomStringUtils.randomNumeric(6);
	}
}
