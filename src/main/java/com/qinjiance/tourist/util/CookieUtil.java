package com.qinjiance.tourist.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import module.laohu.commons.util.EncryptUtils;
import module.laohu.commons.util.SecurityUtil;
import module.laohu.commons.util.SpringUtils;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Rewrite CookieUtil
 */
public final class CookieUtil {

	public static final String CHARSET = "UTF-8";

	private static final Logger logger = LoggerFactory.getLogger(CookieUtil.class);
	private static String SPLITER = "|";

	public static String COOKIE_SECRET_KEY = "je@$dj34js%39a!dasdf2#3as@!$%1f23094";
	public static String COOKIE_SECURITY_KEY = "N68y=WG)'r@<9s1@wE;2Vi3L^7xXw~+i";

	public static final String LOGIN_COOKIE_KEY = "shit";
	public static final String AUTOLOGIN_COOKIE_KEY = "ohShit";
	/**
	 * ARC Games cookie域
	 */
	public static final String DOMAIN = ".my3w.com";
	public static final String PATH = "/";

	public static final int COOKIE_CLEAR = 0;
	public static final int COOKIE_LIVE_ONE_DAY = 60 * 60 * 24;
	public static final int COOKIE_LIVE_10_DAYS = 60 * 60 * 24 * 10;
	public static final int COOKIE_LIVE_30_DAYS = 60 * 60 * 24 * 30;
	/**
	 * session级别cookie的时间设置
	 */
	public static final int COOKIE_LIVE_SESSION = -1;

	static {
		Properties properties = new Properties();
		try {
			properties.load(CookieUtil.class.getResourceAsStream("/cookieSecurityKey.properties"));
			COOKIE_SECRET_KEY = properties.getProperty("secret.key");
			COOKIE_SECURITY_KEY = properties.getProperty("security.key");
		} catch (Exception e) {
			logger.error("cookieSecurityKey.properties not found, will be use default value : " + COOKIE_SECURITY_KEY);
		}
	}

	private static String getEncryptKey() {

		if (StringUtils.isBlank(COOKIE_SECRET_KEY)) {
			return null;
		} else {
			StringBuilder keySb = new StringBuilder();
			keySb.append(COOKIE_SECRET_KEY.substring(2, 5)).append(COOKIE_SECRET_KEY.substring(10, 16))
					.append(COOKIE_SECRET_KEY.substring(18, 20)).append(COOKIE_SECRET_KEY.substring(14, 19));
			return keySb.toString();
		}
	}

	/**
	 * 清除.
	 * 
	 * @param response
	 */
	public static void clearCookie(HttpServletResponse response) {

		Cookie cookie = getCookie(SpringUtils.getHttpServletRequest(), LOGIN_COOKIE_KEY);
		if (cookie != null) {
			setCookie(response, DOMAIN, LOGIN_COOKIE_KEY, "", COOKIE_CLEAR);
			cookie = getCookie(SpringUtils.getHttpServletRequest(), AUTOLOGIN_COOKIE_KEY);
			if (cookie != null) {
				setCookie(response, DOMAIN, AUTOLOGIN_COOKIE_KEY, "", COOKIE_CLEAR);
			}
		}
	}

	/**
	 * 添加用户Cookie,补充此cookie设置的时间点
	 * 
	 * @param response
	 * @param user
	 * @param sessionId
	 * @param liveSecond
	 * @return
	 */
	public static boolean setLoginCookie(HttpServletResponse response, Long userId, String nickname, String email,
			String mobile, int expireSeconds, boolean isAutoLogin) {

		String cookieValue = generateCookieValue(userId, nickname, email, mobile);
		boolean result = StringUtils.isNotBlank(cookieValue)
				&& setCookie(response, DOMAIN, LOGIN_COOKIE_KEY, cookieValue, expireSeconds);
		if (result && isAutoLogin) {
			result = setAutoLoginCookie(response, expireSeconds, cookieValue);
		}
		return result;
	}

	/**
	 * @param response
	 * @param expireSeconds
	 * @return
	 */
	public static boolean setAutoLoginCookie(HttpServletResponse response, int expireSeconds, String cookieValue) {

		if (StringUtils.isBlank(cookieValue)) {
			return false;
		}
		return setCookie(response, DOMAIN, AUTOLOGIN_COOKIE_KEY, cookieValue, expireSeconds);
	}

	/**
	 * 添加cookie
	 * 
	 * @param response
	 * @param domain
	 *            域
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @param expiry
	 *            过期时间[单位：/s]
	 * @return
	 */
	public static boolean setCookie(HttpServletResponse response, String domain, String key, String value, int expiry) {

		try {
			Cookie cookie = new Cookie(key, value); // will be throw new
													// IllegalArgumentException(errMsg);
			cookie.setPath(PATH); // very important
			cookie.setDomain(StringUtils.isEmpty(domain) ? DOMAIN : domain);
			cookie.setMaxAge(expiry);
			response.setHeader("P3P", "CP=\"IDC DSP COR CURa ADMa OUR IND PHY ONL COM STA\"");
			addHttpOnlyCookie(response, cookie);
			return true;
		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	/**
	 * 取一个cookie值
	 * 
	 * @param request
	 * @param key
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest request, String key) {

		try {
			Cookie[] cookies = request.getCookies();
			if ((cookies == null) || (cookies.length == 0)) {
				return null;
			}
			for (Cookie cooky : cookies) {
				if (cooky.getName().equals(key)) {
					return cooky.getValue();
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 取一个cookie
	 * 
	 * @param request
	 * @param key
	 * @return
	 */
	public static Cookie getCookie(HttpServletRequest request, String key) {

		try {
			Cookie[] cookies = request.getCookies();
			if ((cookies == null) || (cookies.length == 0)) {
				return null;
			}
			for (Cookie cooky : cookies) {
				if (cooky.getName().equals(key)) {
					return cooky;
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 取得cookie生存时间
	 * 
	 * @param request
	 * @param key
	 * @return
	 */
	public static int getCookieMaxAge(HttpServletRequest request, String key) {

		try {
			Cookie[] cookies = request.getCookies();
			if ((cookies == null) || (cookies.length == 0)) {
				return 0;
			}
			for (Cookie cooky : cookies) {
				if (cooky.getName().equals(key)) {
					return cooky.getMaxAge();
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return 0;

	}

	/**
	 * 从user cookie中取得mobile
	 * 
	 * @param request
	 * @return
	 */
	public static String getMobileFromCookie() {

		String[] strs = parseCookieValue(SpringUtils.getHttpServletRequest(), LOGIN_COOKIE_KEY);
		if (ArrayUtils.isEmpty(strs)) {
			return null;
		}
		try {
			return new String(EncryptUtils.base64Decode(strs[3]), CHARSET);
		} catch (NumberFormatException e) {
			return null;
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	/**
	 * 从user cookie中取得email
	 * 
	 * @param request
	 * @return
	 */
	public static String getEmailFromCookie() {

		String[] strs = parseCookieValue(SpringUtils.getHttpServletRequest(), LOGIN_COOKIE_KEY);
		if (ArrayUtils.isEmpty(strs)) {
			return null;
		}
		try {
			return new String(EncryptUtils.base64Decode(strs[2]), CHARSET);
		} catch (NumberFormatException e) {
			return null;
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	/**
	 * 从user cookie中取得nickname
	 * 
	 * @param request
	 * @return
	 */
	public static String getNicknameFromCookie() {

		String[] strs = parseCookieValue(SpringUtils.getHttpServletRequest(), LOGIN_COOKIE_KEY);
		if (ArrayUtils.isEmpty(strs)) {
			return null;
		}
		try {
			return new String(EncryptUtils.base64Decode(strs[1]), CHARSET);
		} catch (NumberFormatException e) {
			return null;
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	/**
	 * 从user cookie中取得userId
	 * 
	 * @param request
	 * @return
	 */
	public static Long getUserIdFromCookie() {

		String[] strs = parseCookieValue(SpringUtils.getHttpServletRequest(), LOGIN_COOKIE_KEY);
		if (ArrayUtils.isEmpty(strs)) {
			return null;
		}
		try {
			return Long.valueOf(new String(EncryptUtils.base64Decode(strs[0]), CHARSET));
		} catch (NumberFormatException e) {
			return null;
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	/**
	 * 从autologin cookie中取得userId
	 * 
	 * @param request
	 * @return
	 */
	public static Long getUserIdFromAutoLoginCookie() {

		String[] strs = parseCookieValue(SpringUtils.getHttpServletRequest(), AUTOLOGIN_COOKIE_KEY);
		if (ArrayUtils.isEmpty(strs)) {
			return null;
		}
		try {
			return Long.valueOf(new String(EncryptUtils.base64Decode(strs[0]), CHARSET));
		} catch (NumberFormatException e) {
			return null;
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	/**
	 * 更新cookie信息
	 * 
	 * @param response
	 * @param expire
	 */
	public static void updateCookieInfo(HttpServletResponse response, Long userId, String nickname, String email,
			String mobile, int expire) {

		String cookieValue = generateCookieValue(userId, nickname, email, mobile);
		if (StringUtils.isNotBlank(cookieValue)) {
			Long autoLoginUserId = CookieUtil.getUserIdFromAutoLoginCookie();
			if (autoLoginUserId != null && autoLoginUserId.longValue() > 0) {
				logger.info("Update auto login cookie info");
				setCookie(response, DOMAIN, LOGIN_COOKIE_KEY, cookieValue, expire);
				setCookie(response, DOMAIN, AUTOLOGIN_COOKIE_KEY, cookieValue, expire);
			} else {
				logger.info("Update login cookie info");
				setCookie(response, DOMAIN, LOGIN_COOKIE_KEY, cookieValue, COOKIE_LIVE_SESSION);
			}
		}
	}

	/**
	 * 更新cookie时间
	 * 
	 * @param response
	 * @param expire
	 */
	public static void updateExpire(HttpServletResponse response, int expire) {

		Long userId = CookieUtil.getUserIdFromCookie();
		if (userId != null && userId.longValue() > 0) {
			Long autoLoginUserId = CookieUtil.getUserIdFromAutoLoginCookie();
			if (autoLoginUserId != null && autoLoginUserId.longValue() > 0) {
				logger.info("Update cookie expire: " + expire + " seconds.");
				setCookie(response, DOMAIN, LOGIN_COOKIE_KEY,
						getCookieValue(SpringUtils.getHttpServletRequest(), LOGIN_COOKIE_KEY), expire);
				setCookie(response, DOMAIN, AUTOLOGIN_COOKIE_KEY,
						getCookieValue(SpringUtils.getHttpServletRequest(), AUTOLOGIN_COOKIE_KEY), expire);
			}
		}
	}

	/**
	 * Cookie签名
	 * 
	 * @param content
	 * @return
	 */
	private static String getSign(String content) {

		return EncryptUtils.saltSHA256(COOKIE_SECURITY_KEY, SecurityUtil.md5(content + COOKIE_SECURITY_KEY));
	}

	/**
	 * 解析cookieValue
	 * 
	 * @param request
	 * @return NULL cookie失效
	 */
	private static String[] parseCookieValue(HttpServletRequest request, String cookieKey) {

		String cookieValue = CookieUtil.getCookieValue(request, cookieKey);
		if (StringUtils.isNotBlank(cookieValue)) {
			try {
				cookieValue = URLDecoder.decode(cookieValue, CHARSET);
				if (StringUtils.isNotBlank(cookieValue)) {
					// 解密
					byte[] content = EncryptUtils.base64Decode(cookieValue);
					cookieValue = EncryptUtils.defaultAESDecrypt(getEncryptKey(), content);
					if (StringUtils.isNotBlank(cookieValue)) {
						String[] strs = cookieValue.split("\\" + SPLITER);
						// 长度
						if (strs.length == 5) {
							StringBuilder sb = new StringBuilder();
							sb.append(strs[0]).append(SPLITER).append(strs[1]).append(SPLITER).append(strs[2])
									.append(SPLITER).append(strs[3]);
							String sign = getSign(sb.toString());
							if (sign.equals(strs[4])) {
								return strs;
							}
						}
					}
				}
			} catch (Exception e) {
			}
		}
		logger.info("Invalid cookie: " + cookieKey);
		return null;
	}

	/**
	 * 生成user的cookie值
	 * 
	 * @param user
	 * @param sessionId
	 * @return
	 */
	private static String generateCookieValue(Long userId, String nickname, String email, String mobile) {

		if (StringUtils.isBlank(email)) {
			email = "";
		}
		if (StringUtils.isBlank(mobile)) {
			mobile = "";
		}
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(EncryptUtils.defaultBase64Encode(userId.toString())).append(SPLITER)
					.append(EncryptUtils.defaultBase64Encode(nickname)).append(SPLITER)
					.append(EncryptUtils.defaultBase64Encode(email)).append(SPLITER)
					.append(EncryptUtils.defaultBase64Encode(mobile));
			// 签名
			String sign = getSign(sb.toString());
			sb.append(SPLITER).append(sign);
			// 加密
			String encry = EncryptUtils.defaultAESEncrypt(getEncryptKey(), sb.toString());
			return URLEncoder.encode(encry, CHARSET);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * 
	 * 解决servlet3.0之前的Cookie无setHttpOnly方法.
	 * 
	 * @param response
	 *            HttpServletResponse类型的响应
	 * @param cookie
	 *            要设置httpOnly的cookie对象
	 */
	public static boolean addHttpOnlyCookie(HttpServletResponse response, Cookie cookie) {

		// 判断对象是否存在null的情况
		if (response == null || cookie == null) {
			return false;
		}

		// 依次取得cookie中的名称、值、最大生存时间、路径、域和是否为安全协议信息
		String cookieName = cookie.getName();
		String cookieValue = cookie.getValue();
		int maxAge = cookie.getMaxAge();
		String path = cookie.getPath();
		String domain = cookie.getDomain();
		boolean isSecure = cookie.getSecure();

		StringBuilder cookieSb = new StringBuilder();
		cookieSb.append(cookieName + "=" + cookieValue + ";");

		if (maxAge >= 0) {
			cookieSb.append("Max-Age=" + cookie.getMaxAge() + ";");
		}

		if (StringUtils.isNotBlank(domain)) {
			cookieSb.append("domain=" + domain + ";");
		}

		if (StringUtils.isNotBlank(path)) {
			cookieSb.append("path=" + path + ";");
		}
		if (isSecure) {// 只有在使用了HTTPS协议的时候才能添加，否则这个cookie将再也无法读出！
			cookieSb.append("secure;HTTPOnly;");
		} else {
			cookieSb.append("HTTPOnly;");
		}
		response.addHeader("Set-Cookie", cookieSb.toString());
		return true;
	}
}