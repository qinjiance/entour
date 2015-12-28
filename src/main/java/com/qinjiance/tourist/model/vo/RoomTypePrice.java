/**
 * 
 */
package com.qinjiance.tourist.model.vo;

import java.util.List;

import module.laohu.commons.model.BaseObject;

/**
 * @author Administrator
 *
 * @datetime 2015年11月1日 下午11:57:04
 *
 * @desc
 */
public class RoomTypePrice extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3135390834601718100L;

	private String roomTypeCategory;
	private Integer hotelRoomTypeId;
	private String name;
	private Integer nights;
	private List<RoomPrice> roomPrices;

	/**
	 * 
	 */
	public RoomTypePrice() {
	}

	public Integer getNights() {
		return nights;
	}

	public void setNights(Integer nights) {
		this.nights = nights;
	}

	public String getRoomTypeCategory() {
		return roomTypeCategory;
	}

	public void setRoomTypeCategory(String roomTypeCategory) {
		this.roomTypeCategory = roomTypeCategory;
	}

	public Integer getHotelRoomTypeId() {
		return hotelRoomTypeId;
	}

	public void setHotelRoomTypeId(Integer hotelRoomTypeId) {
		this.hotelRoomTypeId = hotelRoomTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<RoomPrice> getRoomPrices() {
		return roomPrices;
	}

	public void setRoomPrices(List<RoomPrice> roomPrices) {
		this.roomPrices = roomPrices;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
