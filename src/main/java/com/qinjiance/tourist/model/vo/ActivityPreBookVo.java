/**
 * 
 */
package com.qinjiance.tourist.model.vo;

import java.util.List;

import module.laohu.commons.model.BaseObject;

/**
 * @author Administrator
 * 
 * @datetime 2015年11月1日 下午11:30:32
 * 
 * @desc
 */
public class ActivityPreBookVo extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6996889716386685262L;

	private String categoryName;
	private Integer activityId;
	private String activityName;
	private String activityDesc;
	private String stars;
	private String currency;
	private String address;
	private String country;
	private String phone;
	private String totalPrice;
	private String totalTax;
	private String voucherRemark;
	private List<ActivityPreInfoVo> activityPreInfoVos;

	/**
	 * 
	 */
	public ActivityPreBookVo() {
	}

	/**
	 * @return the voucherRemark
	 */
	public String getVoucherRemark() {
		return voucherRemark;
	}

	/**
	 * @param voucherRemark
	 *            the voucherRemark to set
	 */
	public void setVoucherRemark(String voucherRemark) {
		this.voucherRemark = voucherRemark;
	}

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName
	 *            the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * @return the activityId
	 */
	public Integer getActivityId() {
		return activityId;
	}

	/**
	 * @param activityId
	 *            the activityId to set
	 */
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	/**
	 * @return the activityName
	 */
	public String getActivityName() {
		return activityName;
	}

	/**
	 * @param activityName
	 *            the activityName to set
	 */
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	/**
	 * @return the activityDesc
	 */
	public String getActivityDesc() {
		return activityDesc;
	}

	/**
	 * @param activityDesc
	 *            the activityDesc to set
	 */
	public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc;
	}

	/**
	 * @return the stars
	 */
	public String getStars() {
		return stars;
	}

	/**
	 * @param stars
	 *            the stars to set
	 */
	public void setStars(String stars) {
		this.stars = stars;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency
	 *            the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the totalPrice
	 */
	public String getTotalPrice() {
		return totalPrice;
	}

	/**
	 * @param totalPrice
	 *            the totalPrice to set
	 */
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * @return the totalTax
	 */
	public String getTotalTax() {
		return totalTax;
	}

	/**
	 * @param totalTax
	 *            the totalTax to set
	 */
	public void setTotalTax(String totalTax) {
		this.totalTax = totalTax;
	}

	/**
	 * @return the activityPreInfoVos
	 */
	public List<ActivityPreInfoVo> getActivityPreInfoVos() {
		return activityPreInfoVos;
	}

	/**
	 * @param activityPreInfoVos
	 *            the activityPreInfoVos to set
	 */
	public void setActivityPreInfoVos(List<ActivityPreInfoVo> activityPreInfoVos) {
		this.activityPreInfoVos = activityPreInfoVos;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
