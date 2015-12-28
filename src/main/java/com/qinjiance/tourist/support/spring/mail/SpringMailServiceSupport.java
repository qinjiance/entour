/**
 * 
 */
package com.qinjiance.tourist.support.spring.mail;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author "Jiance Qin"
 * 
 * @date 2014年5月12日
 * 
 * @time 上午10:48:13
 * 
 * @desc
 * 
 */
@Component
public class SpringMailServiceSupport {

	private static Logger logger = LoggerFactory.getLogger(SpringMailServiceSupport.class);

	public static final String MAIL_LIST_SEPARATOR = ",";
	public static final String MIME_MAIL_CHARSET = "UTF-8";

	@Value(value = "#{configProperties['springmaiservice.isTest']}")
	private boolean isTest = true;
	@Value(value = "#{configProperties['springmaiservice.testerEmailList']}")
	private String testerEmailList;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;

	/**
	 * 
	 */
	public SpringMailServiceSupport() {

	}

	/**
	 * 发送纯文本邮件.
	 * 
	 * @param sendTo
	 * @param sendFrom
	 * @param subject
	 * @param test
	 */
	public boolean sendTextEmail(String[] sendTo, String sendFrom, String subject, String test) {

		if (!StringUtils.hasText(sendFrom)) {
			return false;
		}
		if (isTest) {
			if (!StringUtils.hasText(testerEmailList)) {
				return false;
			}
			sendTo = testerEmailList.split(MAIL_LIST_SEPARATOR);
		}
		if (sendTo == null || sendTo.length == 0) {
			return false;
		}

		logger.info("SpringMailService - send text email to " + Arrays.toString(sendTo) + "<" + subject + ">" + test);
		try {
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setFrom(sendFrom);
			msg.setTo(sendTo);
			msg.setSubject(subject);
			msg.setText(test);
			mailSender.send(msg);
			logger.info("SpringMailService - send text email to " + Arrays.toString(sendTo) + " success!");
			return true;
		} catch (Exception e) {
			logger.error("SpringMailService - send text email to " + Arrays.toString(sendTo) + " error!", e);
			return false;
		}
	}

	/**
	 * 发送MIME邮件
	 * 
	 * @param sendTo
	 * @param sendFrom
	 * @param subject
	 * @param htmlMsg
	 * @throws MessagingException
	 */
	public boolean sendMimeEmail(String[] sendTo, String sendFrom, String subject, String htmlMsg) {

		if (!StringUtils.hasText(sendFrom)) {
			return false;
		}
		if (isTest) {
			if (!StringUtils.hasText(testerEmailList)) {
				return false;
			}
			sendTo = testerEmailList.split(MAIL_LIST_SEPARATOR);
		}
		if (sendTo == null || sendTo.length == 0) {
			return false;
		}

		String log = Arrays.toString(sendTo) + "<" + subject + ">";
		logger.info("SpringMailService - send html mime email to " + log + " ...");
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, MIME_MAIL_CHARSET);
			messageHelper.setFrom(sendFrom);
			messageHelper.setTo(sendTo);
			messageHelper.setSubject(subject);
			messageHelper.setText(htmlMsg, true);
			mailSender.send(mimeMessage);
			logger.info("SpringMailService - send html mime email to " + log + " success!");
			return true;
		} catch (Exception e) {
			logger.error("SpringMailService - send html mime email to " + log + " error!", e);
			return false;
		}
	}

	/**
	 * 使用邮件模板发送邮件.
	 * 
	 * @param sendTo
	 * @param sendFrom
	 * @param subject
	 * @param data
	 * @param templatePath
	 * @throws IOException
	 * @throws TemplateException
	 * @throws MessagingException
	 */
	public boolean sendMimeEmailWithTemplate(String[] sendTo, String sendFrom, String subject,
			Map<String, Object> data, String templatePath) {

		if (!StringUtils.hasText(sendFrom)) {
			return false;
		}
		if (isTest) {
			if (!StringUtils.hasText(testerEmailList)) {
				return false;
			}
			sendTo = testerEmailList.split(MAIL_LIST_SEPARATOR);
		}
		if (sendTo == null || sendTo.length == 0) {
			return false;
		}

		String log = Arrays.toString(sendTo) + "<" + subject + ">";
		logger.info("SpringMailService - send template email to " + log + " ...");
		String htmlText;
		try {
			Template tpl = freeMarkerConfigurer.getConfiguration().getTemplate(templatePath);
			htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(tpl, data);
			sendMimeEmail(sendTo, sendFrom, subject, htmlText);
			logger.info("SpringMailService - send template email to " + log + " success!");
			return true;
		} catch (Exception e) {
			logger.error("SpringMailService - send template email to " + log + " error!", e);
			return false;
		}
	}

}
