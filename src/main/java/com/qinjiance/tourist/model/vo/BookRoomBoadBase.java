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
 * @time 下午4:35:50
 * 
 * @desc
 * 
 */
public class BookRoomBoadBase extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7986838292949740192L;

	private Integer bbId;
	private Long price;
	private String name;

	/**
	 * 
	 */
	public BookRoomBoadBase() {

	}

	/**
	 * @return the bbId
	 */
	public Integer getBbId() {
		return bbId;
	}

	/**
	 * @param bbId
	 *            the bbId to set
	 */
	public void setBbId(Integer bbId) {
		this.bbId = bbId;
	}

	/**
	 * @return the price
	 */
	public Long getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(Long price) {
		this.price = price;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
