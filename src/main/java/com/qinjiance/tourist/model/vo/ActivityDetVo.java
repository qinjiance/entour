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
public class ActivityDetVo extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6996889716386685262L;

	private Integer categoryId;
	private String categoryName;
	private Integer activityId;
	private String activityName;
	private String activityDesc;
	private String stars;
	private String currency;
	private Integer minChildAge;
	private Integer maxChildAge;
	private String address;
	private String country;
	private String phone;
	private String voucherRemarks;
	private List<DescFrag> descFrags;
	private List<ActivityOptionVo> activityOptionVos;
	private List<String> imgs;

	/**
	 * 
	 */
	public ActivityDetVo() {
	}

	/**
	 * @return the categoryId
	 */
	public Integer getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId
	 *            the categoryId to set
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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
	 * @return the minChildAge
	 */
	public Integer getMinChildAge() {
		return minChildAge;
	}

	/**
	 * @param minChildAge
	 *            the minChildAge to set
	 */
	public void setMinChildAge(Integer minChildAge) {
		this.minChildAge = minChildAge;
	}

	/**
	 * @return the maxChildAge
	 */
	public Integer getMaxChildAge() {
		return maxChildAge;
	}

	/**
	 * @param maxChildAge
	 *            the maxChildAge to set
	 */
	public void setMaxChildAge(Integer maxChildAge) {
		this.maxChildAge = maxChildAge;
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
	 * @return the voucherRemarks
	 */
	public String getVoucherRemarks() {
		return voucherRemarks;
	}

	/**
	 * @param voucherRemarks
	 *            the voucherRemarks to set
	 */
	public void setVoucherRemarks(String voucherRemarks) {
		this.voucherRemarks = voucherRemarks;
	}

	/**
	 * @return the descFrags
	 */
	public List<DescFrag> getDescFrags() {
		return descFrags;
	}

	/**
	 * @param descFrags
	 *            the descFrags to set
	 */
	public void setDescFrags(List<DescFrag> descFrags) {
		this.descFrags = descFrags;
	}

	/**
	 * @return the activityOptionVos
	 */
	public List<ActivityOptionVo> getActivityOptionVos() {
		return activityOptionVos;
	}

	/**
	 * @param activityOptionVos
	 *            the activityOptionVos to set
	 */
	public void setActivityOptionVos(List<ActivityOptionVo> activityOptionVos) {
		this.activityOptionVos = activityOptionVos;
	}

	/**
	 * @return the imgs
	 */
	public List<String> getImgs() {
		return imgs;
	}

	/**
	 * @param imgs
	 *            the imgs to set
	 */
	public void setImgs(List<String> imgs) {
		this.imgs = imgs;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
