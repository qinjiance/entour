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
 * @time 下午4:39:27
 * 
 * @desc
 * 
 */
public class BookRoomSupplement extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6485842814510875363L;

	private Integer suppId;
	private String suppName;
	private Boolean suppIsMandatory;
	private Integer suppChargeType;
	private Long publishPrice;
	private Integer suppType;

	/**
	 * 
	 */
	public BookRoomSupplement() {

	}

	/**
	 * @return the suppId
	 */
	public Integer getSuppId() {
		return suppId;
	}

	/**
	 * @param suppId
	 *            the suppId to set
	 */
	public void setSuppId(Integer suppId) {
		this.suppId = suppId;
	}

	/**
	 * @return the suppName
	 */
	public String getSuppName() {
		return suppName;
	}

	/**
	 * @param suppName
	 *            the suppName to set
	 */
	public void setSuppName(String suppName) {
		this.suppName = suppName;
	}

	/**
	 * @return the suppIsMandatory
	 */
	public Boolean getSuppIsMandatory() {
		return suppIsMandatory;
	}

	/**
	 * @param suppIsMandatory
	 *            the suppIsMandatory to set
	 */
	public void setSuppIsMandatory(Boolean suppIsMandatory) {
		this.suppIsMandatory = suppIsMandatory;
	}

	/**
	 * @return the suppChargeType
	 */
	public Integer getSuppChargeType() {
		return suppChargeType;
	}

	/**
	 * @param suppChargeType
	 *            the suppChargeType to set
	 */
	public void setSuppChargeType(Integer suppChargeType) {
		this.suppChargeType = suppChargeType;
	}

	/**
	 * @return the publishPrice
	 */
	public Long getPublishPrice() {
		return publishPrice;
	}

	/**
	 * @param publishPrice
	 *            the publishPrice to set
	 */
	public void setPublishPrice(Long publishPrice) {
		this.publishPrice = publishPrice;
	}

	/**
	 * @return the suppType
	 */
	public Integer getSuppType() {
		return suppType;
	}

	/**
	 * @param suppType
	 *            the suppType to set
	 */
	public void setSuppType(Integer suppType) {
		this.suppType = suppType;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
