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
public class HoltelDetVo extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6996889716386685262L;

	private String hotelPhone;
	private String country;
	private String state;
	private String city;
	private String location;
	private String zip;
	private String voucherRemark;
	private List<String> refPoints;
	private String shortDesc;
	private String longDesc;
	private String brandName;
	private Integer hotelId;
	private String hotelName;
	private String address;
	private String starsLevel;
	private String currency;
	private String minAverPublishPrice;
	private List<RoomTypeVo> roomTypes;
	private String hotelCatalog;
	private List<String> imgs;
	private List<String> amenities;

	/**
	 * 
	 */
	public HoltelDetVo() {
	}

	public String getMinAverPublishPrice() {
		return minAverPublishPrice;
	}

	public void setMinAverPublishPrice(String minAverPublishPrice) {
		this.minAverPublishPrice = minAverPublishPrice;
	}

	public String getHotelPhone() {
		return hotelPhone;
	}

	public void setHotelPhone(String hotelPhone) {
		this.hotelPhone = hotelPhone;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getVoucherRemark() {
		return voucherRemark;
	}

	public void setVoucherRemark(String voucherRemark) {
		this.voucherRemark = voucherRemark;
	}

	public List<String> getRefPoints() {
		return refPoints;
	}

	public void setRefPoints(List<String> refPoints) {
		this.refPoints = refPoints;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getLongDesc() {
		return longDesc;
	}

	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
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

	public List<RoomTypeVo> getRoomTypes() {
		return roomTypes;
	}

	public void setRoomTypes(List<RoomTypeVo> roomTypes) {
		this.roomTypes = roomTypes;
	}

	public String getHotelCatalog() {
		return hotelCatalog;
	}

	public void setHotelCatalog(String hotelCatalog) {
		this.hotelCatalog = hotelCatalog;
	}

	public List<String> getImgs() {
		return imgs;
	}

	public void setImgs(List<String> imgs) {
		this.imgs = imgs;
	}

	public List<String> getAmenities() {
		return amenities;
	}

	public void setAmenities(List<String> amenities) {
		this.amenities = amenities;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
