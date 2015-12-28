/**
 * 
 */
package com.qinjiance.tourist.model.vo;

import javax.validation.GroupSequence;
import javax.validation.constraints.Pattern;

import module.laohu.commons.model.BaseObject;

import org.hibernate.validator.constraints.NotBlank;

import com.qinjiance.tourist.model.vo.validation.IConstraintGroup;
import com.qinjiance.tourist.model.vo.validation.INotNullGroup;
import com.qinjiance.tourist.util.CheckStyleUtil;

/**
 * @author "Jiance Qin"
 * 
 * @date 2014年7月5日
 * 
 * @time 上午11:37:45
 * 
 * @desc
 * 
 */
@GroupSequence(value = { INotNullGroup.class, IConstraintGroup.class, RegisterForm.class })
public class RegisterForm extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1063296455698090596L;

	@NotBlank(message = "邮箱不可为空", groups = INotNullGroup.class)
	@Pattern(message = "请填写有效的邮箱", regexp = CheckStyleUtil.PATTERN_EMAIL, groups = IConstraintGroup.class)
	private String email;

	@NotBlank(message = "手机号不可为空", groups = INotNullGroup.class)
	@Pattern(message = "请填写有效的手机号码", regexp = CheckStyleUtil.PATTERN_MOBILE, groups = IConstraintGroup.class)
	private String mobile;

	@NotBlank(message = "密码不可为空", groups = INotNullGroup.class)
	@Pattern(message = "请填写6~20位密码", regexp = CheckStyleUtil.PATTERN_PWD, groups = IConstraintGroup.class)
	private String password;

	@NotBlank(message = "确认密码不可为空", groups = INotNullGroup.class)
	private String rePassword;

	@NotBlank(message = "验证码不可为空", groups = INotNullGroup.class)
	private String captcha;

	/**
	 * 
	 */
	public RegisterForm() {

	}

	/**
	 * @return the email
	 */
	public String getEmail() {

		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {

		this.email = email;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {

		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {

		this.mobile = mobile;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {

		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {

		this.password = password;
	}

	/**
	 * @return the rePassword
	 */
	public String getRePassword() {

		return rePassword;
	}

	/**
	 * @param rePassword
	 *            the rePassword to set
	 */
	public void setRePassword(String rePassword) {

		this.rePassword = rePassword;
	}

	/**
	 * @return the captcha
	 */
	public String getCaptcha() {

		return captcha;
	}

	/**
	 * @param captcha
	 *            the captcha to set
	 */
	public void setCaptcha(String captcha) {

		this.captcha = captcha;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {

		return serialVersionUID;
	}

}
