package com.qinjiance.tourist.model.po;

import java.util.Date;

import module.laohu.commons.model.BaseObject;

import org.apache.ibatis.type.Alias;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * @author "Jiance Qin"
 * 
 * @date 2014年7月16日
 * 
 * @time 下午5:04:33
 * 
 * @desc
 * 
 */
@Alias("user")
public class User extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8572870599755706702L;

	private Long id;
	private String email;
	private String mobile;
	private String nickname;
	@JsonIgnore
	private String salt;
	@JsonIgnore
	private String password;
	private Integer level;
	private String registerIp;
	private Date registerTime;
	private Date updateTime;

	/**
	 * @return the id
	 */
	public Long getId() {

		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {

		this.id = id;
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
	 * @return the nickname
	 */
	public String getNickname() {

		return nickname;
	}

	/**
	 * @param nickname
	 *            the nickname to set
	 */
	public void setNickname(String nickname) {

		this.nickname = nickname;
	}

	/**
	 * @return the salt
	 */
	public String getSalt() {

		return salt;
	}

	/**
	 * @param salt
	 *            the salt to set
	 */
	public void setSalt(String salt) {

		this.salt = salt;
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
	 * @return the level
	 */
	public Integer getLevel() {

		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(Integer level) {

		this.level = level;
	}

	/**
	 * @return the registerIp
	 */
	public String getRegisterIp() {

		return registerIp;
	}

	/**
	 * @param registerIp
	 *            the registerIp to set
	 */
	public void setRegisterIp(String registerIp) {

		this.registerIp = registerIp;
	}

	/**
	 * @return the registerTime
	 */
	public Date getRegisterTime() {

		return registerTime;
	}

	/**
	 * @param registerTime
	 *            the registerTime to set
	 */
	public void setRegisterTime(Date registerTime) {

		this.registerTime = registerTime;
	}

	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {

		return updateTime;
	}

	/**
	 * @param updateTime
	 *            the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {

		this.updateTime = updateTime;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {

		return serialVersionUID;
	}

}
