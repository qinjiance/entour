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
public class RoomTypeVo extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3135390834601718100L;

	private Integer numOfBathrooms;
	private String roomTypeCategory;
	private Integer hotelRoomTypeId;
	private String name;
	private Integer roomId;
	private Integer maxChild;
	private Integer maxGuests;
	private String avrNightPublishPrice;
	private Integer bedType;
	private Integer hasBed;
	private List<SupplementVo> supplements;
	private List<BoardbaseVo> boardbases;
	private List<String> facilities;

	/**
	 * 
	 */
	public RoomTypeVo() {
	}

	public Integer getBedType() {
		return bedType;
	}

	public void setBedType(Integer bedType) {
		this.bedType = bedType;
	}

	public Integer getHasBed() {
		return hasBed;
	}

	public void setHasBed(Integer hasBed) {
		this.hasBed = hasBed;
	}

	public Integer getMaxChild() {
		return maxChild;
	}

	public void setMaxChild(Integer maxChild) {
		this.maxChild = maxChild;
	}

	public Integer getMaxGuests() {
		return maxGuests;
	}

	public void setMaxGuests(Integer maxGuests) {
		this.maxGuests = maxGuests;
	}

	public Integer getHotelRoomTypeId() {
		return hotelRoomTypeId;
	}

	public void setHotelRoomTypeId(Integer hotelRoomTypeId) {
		this.hotelRoomTypeId = hotelRoomTypeId;
	}

	public List<String> getFacilities() {
		return facilities;
	}

	public void setFacilities(List<String> facilities) {
		this.facilities = facilities;
	}

	public Integer getNumOfBathrooms() {
		return numOfBathrooms;
	}

	public void setNumOfBathrooms(Integer numOfBathrooms) {
		this.numOfBathrooms = numOfBathrooms;
	}

	public String getRoomTypeCategory() {
		return roomTypeCategory;
	}

	public void setRoomTypeCategory(String roomTypeCategory) {
		this.roomTypeCategory = roomTypeCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public String getAvrNightPublishPrice() {
		return avrNightPublishPrice;
	}

	public void setAvrNightPublishPrice(String avrNightPublishPrice) {
		this.avrNightPublishPrice = avrNightPublishPrice;
	}

	public List<SupplementVo> getSupplements() {
		return supplements;
	}

	public void setSupplements(List<SupplementVo> supplements) {
		this.supplements = supplements;
	}

	public List<BoardbaseVo> getBoardbases() {
		return boardbases;
	}

	public void setBoardbases(List<BoardbaseVo> boardbases) {
		this.boardbases = boardbases;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
