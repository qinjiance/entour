/**
 * 
 */
package com.qinjiance.tourist.model.vo;

import java.util.List;

import module.laohu.commons.model.BaseObject;

/**
 * @author "Jiance Qin"
 * 
 * @date 2015年12月16日
 * 
 * @time 下午12:06:01
 * 
 * @desc
 * 
 */
public class ActivityPreInfoVo extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4713516661525671475L;

	private Integer activityId;
	private Integer optionId;
	private String date;
	private String priceSum;
	private String taxSum;
	private List<ActivityPriceBreakDown> priceBreakDown;
	private List<Addition> activityAdditions;
	private List<PassengerInfoVo> passengerInfos;

	/**
	 * 
	 */
	public ActivityPreInfoVo() {

	}

	/**
	 * @return the activityAdditions
	 */
	public List<Addition> getActivityAdditions() {
		return activityAdditions;
	}

	/**
	 * @param activityAdditions
	 *            the activityAdditions to set
	 */
	public void setActivityAdditions(List<Addition> activityAdditions) {
		this.activityAdditions = activityAdditions;
	}

	public List<PassengerInfoVo> getPassengerInfos() {
		return passengerInfos;
	}

	public void setPassengerInfos(List<PassengerInfoVo> passengerInfos) {
		this.passengerInfos = passengerInfos;
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
	 * @return the optionId
	 */
	public Integer getOptionId() {
		return optionId;
	}

	/**
	 * @param optionId
	 *            the optionId to set
	 */
	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the priceSum
	 */
	public String getPriceSum() {
		return priceSum;
	}

	/**
	 * @param priceSum
	 *            the priceSum to set
	 */
	public void setPriceSum(String priceSum) {
		this.priceSum = priceSum;
	}

	/**
	 * @return the taxSum
	 */
	public String getTaxSum() {
		return taxSum;
	}

	/**
	 * @param taxSum
	 *            the taxSum to set
	 */
	public void setTaxSum(String taxSum) {
		this.taxSum = taxSum;
	}

	/**
	 * @return the priceBreakDown
	 */
	public List<ActivityPriceBreakDown> getPriceBreakDown() {
		return priceBreakDown;
	}

	/**
	 * @param priceBreakDown
	 *            the priceBreakDown to set
	 */
	public void setPriceBreakDown(List<ActivityPriceBreakDown> priceBreakDown) {
		this.priceBreakDown = priceBreakDown;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
