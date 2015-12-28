/**
 * 
 */
package com.arcgames.clientapi;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import module.laohu.commons.util.EncryptUtils;
import module.laohu.commons.util.SecurityUtil;

/**
 * @author "Jiance Qin"
 * 
 * @date 2014年7月6日
 * 
 * @time 下午12:30:10
 * 
 * @desc
 * 
 */
public class TestLocal {

	public static String encryptPassword(String password, String salt) {
		return EncryptUtils.saltSHA256(salt, SecurityUtil.md5(password + salt));
	}

	/**
	 * 
	 */
	public TestLocal() {

	}

	/**
	 * @param args
	 * @throws UnsupportedEncodingException
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		// String salt = SecurityUtil.getUUID();
		// System.out.println(salt);
		// String pwd = encryptPassword("shangshang880466", salt);
		// System.out.println(pwd);
		long t = new Date().getTime();
		System.out.println(t);
		Map<String, String> map = new TreeMap<String, String>();
		map.put("appId", "1001");
		map.put("timestamp", String.valueOf(t));
		map.put("userId", "1");
		map.put("token", "a3690510132340fe86be8afb2e9114f3");
		map.put("autoLogin", "0");
		String appKey = "lmb6e0savtgpmvfbazxbozxngtjq5gel";
		StringBuilder keySb = new StringBuilder();
		keySb.append(appKey.substring(2, 5)).append(appKey.substring(10, 16)).append(appKey.substring(18, 20))
				.append(appKey.substring(14, 19));
		appKey = keySb.toString();

		StringBuilder desSb = new StringBuilder();
		int i = 0;
		for (Entry<String, String> entry : map.entrySet()) {
			if (i > 0) {
				desSb.append("&");
			}
			desSb.append(entry.getKey()).append("=").append(entry.getValue());
			i++;
		}
		System.out.println(desSb.toString());
		String decryParams = EncryptUtils.saltSHA256(appKey, desSb.toString());
		System.out.println(URLEncoder.encode(decryParams, "UTF-8"));
	}

}
