/**
 * 
 */
package com.qinjiance.tourist.model.vo;

import module.laohu.commons.model.BaseObject;

/**
 * @author Administrator
 *
 * @datetime 2015年10月23日 上午12:00:47
 *
 * @desc
 */
public class HotelVo extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7562552140434583280L;

	private String brandName;
	private Integer hotelId;
	private String hotelName;
	private String thumb;
	private String address;
	private String starsLevel;
	private String minAverPublishPrice;
	private String currency;
	private String roomTypes;
	private String supplements;
	private String boardbases;
	private String hotelCatalog;

	/**
	 * 
	 */
	public HotelVo() {

	}

	public String getHotelCatalog() {
		return hotelCatalog;
	}

	public void setHotelCatalog(String hotelCatalog) {
		this.hotelCatalog = hotelCatalog;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Integer getHotelId() {
		return hotelId;
	}

	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStarsLevel() {
		return starsLevel;
	}

	public void setStarsLevel(String starsLevel) {
		this.starsLevel = starsLevel;
	}

	public String getMinAverPublishPrice() {
		return minAverPublishPrice;
	}

	public void setMinAverPublishPrice(String minAverPublishPrice) {
		this.minAverPublishPrice = minAverPublishPrice;
	}

	public String getRoomTypes() {
		return roomTypes;
	}

	public void setRoomTypes(String roomTypes) {
		this.roomTypes = roomTypes;
	}

	public String getSupplements() {
		return supplements;
	}

	public void setSupplements(String supplements) {
		this.supplements = supplements;
	}

	public String getBoardbases() {
		return boardbases;
	}

	public void setBoardbases(String boardbases) {
		this.boardbases = boardbases;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
