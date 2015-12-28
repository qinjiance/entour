/**
 * 
 */
package com.qinjiance.tourist.manager;

import com.qinjiance.tourist.manager.exception.ManagerException;

/**
 * @author "Jiance Qin"
 * 
 * @date 2014年5月12日
 * 
 * @time 下午5:45:35
 * 
 * @desc
 * 
 */
public interface IEmailManager {

	/**
	 * 发送验证码邮件.
	 * 
	 * @param email
	 * @param captcha
	 * @throws ServiceException
	 */
	public boolean sendEmailCaptcha(String email, String captcha) throws ManagerException;
}
