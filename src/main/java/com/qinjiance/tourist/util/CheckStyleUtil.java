package com.qinjiance.tourist.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class CheckStyleUtil {
	
	public final static String PATTERN_MOBILE = "^(1\\d{10})|(\\d+(\\-\\d+)*)$";
	public final static String PATTERN_MOBILE_INTEL = "^00\\d+$";
	public final static String PATTERN_ZH = "^[\u4e00-\u9fa5]+$";
	public final static String PATTERN_ZH_CHA_NUM = "^[\u4e00-\u9fa5\\w]+$";
	public final static String PATTERN_CHA_NUM = "^[A-Za-z0-9]+$";
	public final static String PATTERN_CHA_NUM_ORDER = "^[A-Za-z][A-Za-z0-9]+$";
	public final static String PATTERN_NUM = "^[0-9]+$";
	public final static String PATTERN_CHA = "^[A-Za-z]+$";
	public final static String PATTERN_EMAIL = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$";
	public final static String PATTERN_PWD = "^.{6,20}$";
	public final static String PATTERN_REALNAME = "^[\u4e00-\u9fa5]{2,5}$";
	
	/**
	 * 检测字符串格式
	 * @param str
	 * @param style
	 * @return
	 */
	public static boolean checkStyle(final String str, final String style) {
		if (StringUtils.isBlank(str)) {
			return false;
		}
		Pattern pattern = Pattern.compile(style);
		Matcher matcher = pattern.matcher(str);
		if (!matcher.matches()) {
			return false;
		}
		return true;
	}

}
