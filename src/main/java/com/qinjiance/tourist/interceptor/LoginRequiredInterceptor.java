package com.qinjiance.tourist.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import module.laohu.commons.model.ResponseCode;
import module.laohu.commons.util.JsonUtils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.qinjiance.tourist.annotation.NeedCookie;
import com.qinjiance.tourist.annotation.SkipWhenUserLogin;
import com.qinjiance.tourist.constants.Constants;
import com.qinjiance.tourist.util.CookieUtil;
import com.qinjiance.tourist.util.ReflectUtil;

/**
 * 用户登录必须拦截器
 */
@Component
public class LoginRequiredInterceptor implements HandlerInterceptor {

	private final static Logger logger = LoggerFactory.getLogger(LoginRequiredInterceptor.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax
	 * .servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj,
			Exception exception) throws Exception {

		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet
	 * .http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj,
			ModelAndView modelandview) throws Exception {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet
	 * .http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {

		String requestPath = request.getRequestURI();

		// 浏览器请求.
		NeedCookie needCookie = ReflectUtil.findHandlerAnnotation(NeedCookie.class, obj);
		// 有，则检测根域名下logon cookie是否有效.
		if (needCookie != null && needCookie.isNeed()) {
			if (isLogonCookieValid(request, response)) {
				return true;
			} else {
				// 无效，则跳转用户登录页面.
				logger.info("Web request path: " + requestPath + ", invalid cookie, redirect to login.");
				CookieUtil.clearCookie(response);

				String acceptType = request.getHeader("Accept");

				if (acceptType.trim().contains("application/json")) {
					responseErrJson(request, response, Constants.CODE_NEED_LOGIN, "请先登录");
				} else {
					response.sendRedirect("/loginPage");
				}
				return false;
			}
		}

		// 如果用户已登录则跳过这些页面
		SkipWhenUserLogin skipWhenUserLogin = ReflectUtil.findHandlerAnnotation(SkipWhenUserLogin.class, obj);
		// 有，则检测根域名下logon cookie是否有效.
		if (skipWhenUserLogin != null && skipWhenUserLogin.isNeed()) {
			if (isLogonCookieValid(request, response)) {
				// 有效，则跳转登录后首页.
				logger.info("Web request path: " + requestPath + ", valid cookie, redirect to index.");
				response.sendRedirect("/");
				return false;
			} else {
				return true;
			}
		}
		return true;
	}

	/**
	 * 登录cookie是否有效.
	 * 
	 * @param request
	 * @return
	 */
	private boolean isLogonCookieValid(HttpServletRequest request, HttpServletResponse response) {

		Long userId = CookieUtil.getUserIdFromCookie();
		if (userId != null && userId.longValue() > 0) {
			logger.info("Web request path: " + request.getRequestURI() + ", found userId " + userId.toString());
			CookieUtil.updateExpire(response, CookieUtil.COOKIE_LIVE_10_DAYS);
			return true;
		}
		return false;
	}

	/**
	 * 返回错误信息，json格式
	 * 
	 * @param response
	 * @param errCode
	 * @param errMsg
	 * @throws IOException
	 */
	private void responseErrJson(HttpServletRequest request, HttpServletResponse response, int errCode, String errMsg)
			throws IOException {

		if (response != null) {
			if (StringUtils.isBlank(errMsg)) {
				errMsg = "Error";
			}
			logger.error("Request to " + request.getRequestURI() + " error: code=" + errCode + ", message=" + errMsg);
			ResponseCode rc = new ResponseCode();
			rc.setCode(errCode);
			rc.setMessage(errMsg);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().print(JsonUtils.objectToJson(rc));
			response.getWriter().flush();
			response.getWriter().close();
		}
	}
}
