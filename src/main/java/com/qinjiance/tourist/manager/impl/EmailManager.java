/**
 * 
 */
package com.qinjiance.tourist.manager.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import module.laohu.commons.util.SpringUtils;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.qinjiance.tourist.constants.Constants;
import com.qinjiance.tourist.manager.IEmailManager;
import com.qinjiance.tourist.manager.exception.ManagerException;
import com.qinjiance.tourist.support.spring.mail.SpringMailServiceSupport;

/**
 * @author "Jiance Qin"
 * 
 * @date 2014年5月12日
 * 
 * @time 下午5:48:35
 * 
 * @desc
 * 
 */
@Service
public class EmailManager implements IEmailManager {

	@Value(value = "#{configProperties['webapp.title']}")
	private String webappTitle;
	@Value(value = "#{configProperties['register.sendFrom']}")
	private String registerSendFrom;
	@Value(value = "#{configProperties['register.subject']}")
	private String registerSubject;
	@Value(value = "#{configProperties['register.emailTemplatePath']}")
	private String registerEmailTemplatePath;

	@Autowired
	private SpringMailServiceSupport springMailServiceSupport;

	/**
	 * 
	 */
	public EmailManager() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qinjiance.tourist.manager.IEmailManager#sendEmailCaptcha(java.lang
	 * .String, java.lang.String)
	 */
	@Override
	public boolean sendEmailCaptcha(String email, String captcha) throws ManagerException {

		if (StringUtils.isBlank(email)) {
			throw new ManagerException("Email is empty.");
		}
		if (StringUtils.isBlank(captcha)) {
			throw new ManagerException("Captcha is empty.");
		}
		try {
			String[] sendTo = new String[] { email };

			Map<String, Object> data = new HashMap<String, Object>();
			data.put("captcha", captcha);
			data.put("webappTitle", webappTitle);
			data.put("email", email);
			data.put("expire", Constants.EMAIL_CAPTCHA_CACHE_TIME / 60);

			HttpServletRequest request = SpringUtils.getHttpServletRequest();
			String homeUrl = request.getScheme() + "://" + request.getServerName()
					+ (request.getServerPort() == 80 ? "" : (":" + request.getServerPort())) + request.getContextPath();
			data.put("homeUrl", homeUrl);

			return springMailServiceSupport.sendMimeEmailWithTemplate(sendTo, registerSendFrom, registerSubject, data,
					registerEmailTemplatePath);
		} catch (Exception e) {
			throw new ManagerException("Send email captcha error.", e);
		}
	}
}
