/**
 * 
 */
package com.qinjiance.tourist.model.vo;

import module.laohu.commons.model.BaseObject;

/**
 * @author "Jiance Qin"
 * 
 * @date 2016年4月13日
 * 
 * @time 下午4:02:45
 * 
 * @desc
 * 
 */
public class BookRoomContactPassenger extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4980078046136118087L;

	private String firstname;
	private String middlename;
	private String lastname;
	private String homephone;
	private String mobilephone;

	/**
	 * 
	 */
	public BookRoomContactPassenger() {
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname
	 *            the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the middlename
	 */
	public String getMiddlename() {
		return middlename;
	}

	/**
	 * @param middlename
	 *            the middlename to set
	 */
	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname
	 *            the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the homephone
	 */
	public String getHomephone() {
		return homephone;
	}

	/**
	 * @param homephone
	 *            the homephone to set
	 */
	public void setHomephone(String homephone) {
		this.homephone = homephone;
	}

	/**
	 * @return the mobilephone
	 */
	public String getMobilephone() {
		return mobilephone;
	}

	/**
	 * @param mobilephone
	 *            the mobilephone to set
	 */
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
