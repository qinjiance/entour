/**
 * 
 */
package com.qinjiance.tourist.model.vo;

import module.laohu.commons.model.BaseObject;

/**
 * @author Administrator
 *
 * @datetime 2015年11月1日 下午11:30:32
 *
 * @desc
 */
public class HoltelDetPrice extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6996889716386685262L;

	private String country;
	private String city;
	private String location;
	private String brandName;
	private Integer hotelId;
	private String hotelName;
	private String address;
	private String starsLevel;
	private String currency;
	private String thumb;
	private RoomTypePrice roomType;
	private String hotelCatalog;

	/**
	 * 
	 */
	public HoltelDetPrice() {
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public RoomTypePrice getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomTypePrice roomType) {
		this.roomType = roomType;
	}

	public String getHotelCatalog() {
		return hotelCatalog;
	}

	public void setHotelCatalog(String hotelCatalog) {
		this.hotelCatalog = hotelCatalog;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
