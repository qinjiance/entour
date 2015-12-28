/**
 * 
 */
package com.qinjiance.tourist.model.vo;

import module.laohu.commons.model.BaseObject;

/**
 * @author Administrator
 *
 * @datetime 2015年11月1日 下午11:35:50
 *
 * @desc
 */
public class SupplementPrice extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3135390834601718100L;

	private Integer suppId;
	private String suppName;
	private Boolean suppIsMandatory;
	private Integer suppChargeType;
	private String publishPrice;

	/**
	 * 
	 */
	public SupplementPrice() {
	}

	public Integer getSuppId() {
		return suppId;
	}

	public void setSuppId(Integer suppId) {
		this.suppId = suppId;
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

	public Integer getSuppChargeType() {
		return suppChargeType;
	}

	public void setSuppChargeType(Integer suppChargeType) {
		this.suppChargeType = suppChargeType;
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
