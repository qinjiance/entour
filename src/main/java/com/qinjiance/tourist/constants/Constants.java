package com.qinjiance.tourist.constants;

/**
 * @author "Jiance Qin", tianbenzhen@pwrd.com
 * 
 * @date 2014年7月5日
 * 
 * @time 上午11:18:19
 * 
 * @desc
 * 
 */
public class Constants {
	public static final String CHARSET = "UTF-8";

	public static final int HTTP_TIMEOUT = 5000;
	public static final int SO_TIMEOUT = 5000;

	public static final int CODE_SERVER_ERROR = -99; // 服务器内部错误
	public static final int CODE_NEED_LOGIN = -98; // 需要登录
	public static final int CODE_VALIDATE_FAILED = -1; // 参数验证未通过
	public static final int CODE_SUCC = 0; // 处理成功
	public static final int CODE_FAIL = 1; // 处理失败
	public static final int CODE_EMAIL_EXIST = 2; // 邮箱已注册

	public static final int APPID_SIGN_CACHE_TIME = 60 * 60 * 24;// 一天内，请求签名不可重复

	public static final int EMAIL_CAPTCHA_CACHE_TIME = 180;// 3分, 邮箱验证码有效时间
	public static final int EMAIL_CAPTCHA_INTETVAl = 60;// 60秒， 发送间隔
}
