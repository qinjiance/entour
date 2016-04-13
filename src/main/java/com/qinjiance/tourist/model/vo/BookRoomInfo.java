/**
 * 
 */
package com.qinjiance.tourist.model.vo;

import java.util.List;

import module.laohu.commons.model.BaseObject;

/**
 * @author "Jiance Qin"
 * 
 * @date 2016年4月13日
 * 
 * @time 下午4:00:18
 * 
 * @desc
 * 
 */
public class BookRoomInfo extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7855058869430197847L;

	private Integer roomId;
	private String occuId;
	private Long occuPrice;
	private BookRoomContactPassenger contactPassenger;
	private BookRoomBoadBase boadBase;
	private List<BookRoomSupplement> supplements;
	private String bedding;
	private String note;
	private Integer adultNum;
	private Integer childNum;
	private List<Integer> childAges;

	/**
	 * 
	 */
	public BookRoomInfo() {
	}

	/**
	 * @return the occuId
	 */
	public String getOccuId() {
		return occuId;
	}

	/**
	 * @param occuId
	 *            the occuId to set
	 */
	public void setOccuId(String occuId) {
		this.occuId = occuId;
	}

	/**
	 * @return the occuPrice
	 */
	public Long getOccuPrice() {
		return occuPrice;
	}

	/**
	 * @param occuPrice
	 *            the occuPrice to set
	 */
	public void setOccuPrice(Long occuPrice) {
		this.occuPrice = occuPrice;
	}

	/**
	 * @return the roomId
	 */
	public Integer getRoomId() {
		return roomId;
	}

	/**
	 * @param roomId
	 *            the roomId to set
	 */
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	/**
	 * @return the contactPassenger
	 */
	public BookRoomContactPassenger getContactPassenger() {
		return contactPassenger;
	}

	/**
	 * @param contactPassenger
	 *            the contactPassenger to set
	 */
	public void setContactPassenger(BookRoomContactPassenger contactPassenger) {
		this.contactPassenger = contactPassenger;
	}

	/**
	 * @return the boadBase
	 */
	public BookRoomBoadBase getBoadBase() {
		return boadBase;
	}

	/**
	 * @param boadBase
	 *            the boadBase to set
	 */
	public void setBoadBase(BookRoomBoadBase boadBase) {
		this.boadBase = boadBase;
	}

	/**
	 * @return the supplements
	 */
	public List<BookRoomSupplement> getSupplements() {
		return supplements;
	}

	/**
	 * @param supplements
	 *            the supplements to set
	 */
	public void setSupplements(List<BookRoomSupplement> supplements) {
		this.supplements = supplements;
	}

	/**
	 * @return the bedding
	 */
	public String getBedding() {
		return bedding;
	}

	/**
	 * @param bedding
	 *            the bedding to set
	 */
	public void setBedding(String bedding) {
		this.bedding = bedding;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note
	 *            the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return the adultNum
	 */
	public Integer getAdultNum() {
		return adultNum;
	}

	/**
	 * @param adultNum
	 *            the adultNum to set
	 */
	public void setAdultNum(Integer adultNum) {
		this.adultNum = adultNum;
	}

	/**
	 * @return the childNum
	 */
	public Integer getChildNum() {
		return childNum;
	}

	/**
	 * @param childNum
	 *            the childNum to set
	 */
	public void setChildNum(Integer childNum) {
		this.childNum = childNum;
	}

	/**
	 * @return the childAges
	 */
	public List<Integer> getChildAges() {
		return childAges;
	}

	/**
	 * @param childAges
	 *            the childAges to set
	 */
	public void setChildAges(List<Integer> childAges) {
		this.childAges = childAges;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
