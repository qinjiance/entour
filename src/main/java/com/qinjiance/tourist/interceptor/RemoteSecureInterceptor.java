package com.qinjiance.tourist.interceptor;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import module.laohu.commons.util.SecurityUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 远程接口安全拦截器
 * 
 * @author Jiance Qin
 * 
 * @Revision revision
 * 
 * @Date 2014-8-10
 * 
 * @Time 下午2:53:06
 * 
 */
public class RemoteSecureInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(RemoteSecureInterceptor.class);

	@Value(value = "#{configProperties['remote.securitykey']}")
	private String SECURETY_KEY;

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

		String signParam = request.getParameter("sign");
		String path = request.getRequestURI();
		logger.info("Remote Secure Interceptor: checking path[" + path + "], sign[" + signParam + "]...");
		String sign = SecurityUtil.md5(path + SECURETY_KEY);
		if (sign.equals(signParam)) {
			return true;
		} else {
			logger.error("Remote Secure Interceptor: sign error!");
			response.setContentType("application/json");
			Writer writer = null;
			try {
				writer = response.getWriter();
				writer.write("failed");
			} catch (Exception e) {
				logger.error("Remote Secure Interceptor: response writer error!");
			} finally {
				if (writer != null) {
					writer.flush();
					writer.close();
				}
			}
			return false;
		}

	}

}
