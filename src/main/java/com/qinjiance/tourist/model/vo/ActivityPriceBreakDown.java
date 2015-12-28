/**
 * 
 */
package com.qinjiance.tourist.model.vo;

import module.laohu.commons.model.BaseObject;

/**
 * @author "Jiance Qin"
 *
 * @date 2015年12月16日
 *
 * @time 下午12:10:16
 *
 * @desc 
 *
 */
public class ActivityPriceBreakDown extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9037159788674610589L;

	private String name;
	private Integer num;
	private String amount;
	
	/**
	 * 
	 */
	public ActivityPriceBreakDown() {
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the num
	 */
	public Integer getNum() {
		return num;
	}

	/**
	 * @param num the num to set
	 */
	public void setNum(Integer num) {
		this.num = num;
	}

	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
