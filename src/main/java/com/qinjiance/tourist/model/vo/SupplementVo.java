/**
 * 
 */
package com.qinjiance.tourist.model.vo;

import com.qinjiance.tourist.constants.SuppChargeType;

import module.laohu.commons.model.BaseObject;

/**
 * @author Administrator
 *
 * @datetime 2015年11月1日 下午11:35:50
 *
 * @desc
 */
public class SupplementVo extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3135390834601718100L;

	private String suppName;
	private Boolean suppIsMandatory;
	private String publishPrice;

	/**
	 * 
	 */
	public SupplementVo() {
	}

	public String getSuppName() {
		return suppName;
	}

	public void setSuppName(String suppName) {
		this.suppName = suppName;
	}

	public Boolean getSuppIsMandatory() {
		return suppIsMandatory;
	}

	public void setSuppIsMandatory(Boolean suppIsMandatory) {
		this.suppIsMandatory = suppIsMandatory;
	}

	public String getPublishPrice() {
		return publishPrice;
	}

	public void setPublishPrice(String publishPrice) {
		this.publishPrice = publishPrice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
