/**
 * 
 */
package com.qinjiance.tourist.model.vo;

import java.util.List;

import module.laohu.commons.model.BaseObject;

/**
 * @author Administrator
 *
 * @datetime 2015年12月14日 上午4:02:23
 *
 * @desc
 */
public class AvailabilitiesVo extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1155154175737540613L;

	private List<String> dates;
	private Integer maxAdults;
	private Integer maxChildren;
	private Integer maxUnits;
	private String adultPrice;
	private String childPrice;
	private String unitPrice;

	/**
	 * 
	 */
	public AvailabilitiesVo() {
	}

	public List<String> getDates() {
		return dates;
	}

	public void setDates(List<String> dates) {
		this.dates = dates;
	}

	public Integer getMaxAdults() {
		return maxAdults;
	}

	public void setMaxAdults(Integer maxAdults) {
		this.maxAdults = maxAdults;
	}

	public Integer getMaxChildren() {
		return maxChildren;
	}

	public void setMaxChildren(Integer maxChildren) {
		this.maxChildren = maxChildren;
	}

	public Integer getMaxUnits() {
		return maxUnits;
	}

	public void setMaxUnits(Integer maxUnits) {
		this.maxUnits = maxUnits;
	}

	public String getAdultPrice() {
		return adultPrice;
	}

	public void setAdultPrice(String adultPrice) {
		this.adultPrice = adultPrice;
	}

	public String getChildPrice() {
		return childPrice;
	}

	public void setChildPrice(String childPrice) {
		this.childPrice = childPrice;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
